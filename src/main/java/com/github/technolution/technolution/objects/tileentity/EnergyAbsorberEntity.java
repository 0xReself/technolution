package com.github.technolution.technolution.objects.tileentity;

import java.util.concurrent.atomic.AtomicInteger;

import com.github.technolution.technolution.init.Config;
import com.github.technolution.technolution.objects.tools.CustomEnergyStorage;

import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class EnergyAbsorberEntity extends TileEntity implements ITickableTileEntity {

    private ItemStackHandler itemHandler = createHandler();
    private CustomEnergyStorage energyStorage = createEnergy();

    private LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);
    private LazyOptional<IEnergyStorage> energy = LazyOptional.of(() -> energyStorage);

    protected int blockTier = 1;

    private int counter = 0;
    

    public EnergyAbsorberEntity(TileEntityType<?> type) {
        super(type);
    }

    public int getTier() {
        return blockTier;
    }

    @Override
    public void tick() {
        if(world.isRemote) {
            return;
        }

        if(counter > 0) {
            counter--;
            if(counter <= 0) {
                energyStorage.addEnergy(Config.ENERGY_ABSORBER_GENERATE.get() * blockTier);
            }
            markDirty();
        }

        if (counter <= 0) {
            ItemStack stack = itemHandler.getStackInSlot(0);
            if (stack.getItem() == Items.DIAMOND) {
                itemHandler.extractItem(0, 1, false);
                counter = 20 * Config.ENERGY_ABSORBER_TIME.get();
                markDirty();
            }
        }

        //Update blockstate
        BlockState blockState = world.getBlockState(pos);
        if(blockState.get(BlockStateProperties.POWERED) != counter > 0) {
            world.setBlockState(pos, blockState.with(BlockStateProperties.POWERED, counter > 0), 
                Constants.BlockFlags.NOTIFY_NEIGHBORS + Constants.BlockFlags.BLOCK_UPDATE);
        }

        sendOutPower();
    }
    
    private void sendOutPower() {
        AtomicInteger capacity = new AtomicInteger(energyStorage.getEnergyStored());
        BlockState blockState = world.getBlockState(pos);
        if (capacity.get() > 0) {
            for (Direction direction : Direction.values()) {
                if(blockState.get(BlockStateProperties.FACING) == direction) {
                    return;
                }
                TileEntity te = world.getTileEntity(pos.offset(direction));
                if (te != null) {
                    boolean doContinue = te.getCapability(CapabilityEnergy.ENERGY, direction).map(handler -> {
                                if (handler.canReceive()) {
                                    int received = handler.receiveEnergy(Math.min(capacity.get(), Config.ENERGY_ABSORBER_SEND.get() * blockTier), false);
                                    capacity.addAndGet(-received);
                                    energyStorage.consumeEnergy(received);
                                    markDirty();
                                    return capacity.get() > 0;
                                } else {
                                    return true;
                                }
                            }
                    ).orElse(true);
                    if (!doContinue) {
                        return;
                    }
                }
            }
        }
    }

    @Override
    public void read(CompoundNBT tag) {
        itemHandler.deserializeNBT(tag.getCompound("inv"));
        energyStorage.deserializeNBT(tag.getCompound("energy"));

        counter = tag.getInt("counter");
        super.read(tag);
    }

    @Override
    public CompoundNBT write(CompoundNBT tag) {
        tag.put("inv", itemHandler.serializeNBT());
        tag.put("energy", energyStorage.serializeNBT());

        tag.putInt("counter", counter);
        return super.write(tag);
    }

    private ItemStackHandler createHandler() {
        return new ItemStackHandler() {
            @Override
            protected void onContentsChanged(int slot) {
                markDirty();
            }

            @Override
            public boolean isItemValid(int slot, ItemStack stack) {
                //TODO return true when crystal
                if(stack.getItem() == Items.DIAMOND) {
                    return true;
                }
                return false;
            }

            @Override
            public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
                //TODO only accept crystal
                if(stack.getItem() != Items.DIAMOND) {
                    return stack;
                }
                return super.insertItem(slot, stack, simulate);
            }
        };
    }

    private CustomEnergyStorage createEnergy() {
        //TODO MaxCap get from Config not working right now
        return new CustomEnergyStorage(Config.ENERGY_ABSORBER_MAXPOWER.get() * blockTier, Config.ENERGY_ABSORBER_SEND.get() * blockTier) {
            @Override
            public void onEnergyChanged() {
                markDirty();
            }
        };
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        BlockState state = world.getBlockState(pos);
        if(state.get(BlockStateProperties.FACING) != side) {
            if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
                return handler.cast();
            }
            if(cap == CapabilityEnergy.ENERGY) {
                return energy.cast();
            }
        }
        return super.getCapability(cap, side);
    }
}
