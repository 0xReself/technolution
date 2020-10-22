package com.github.technolution.technolution.objects.tileentity;

import com.github.technolution.technolution.init.Register;

import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class EssenceFurnaceEntity extends TileEntity implements ITickableTileEntity {

    public EssenceFurnaceEntity() {
        super(Register.ESSENCE_FURNACE_ENTITY.get());
    }

    @Override
    public void tick() {
        // itemHandler.setStackInSlot(0, new ItemStack(Items.DIAMOND, 2));
    }

    private FluidTank fluidHandler = new FluidTank(5000) {
        @Override
        public boolean isFluidValid(int tank, FluidStack stack) {
            if(stack.getFluid() == Fluids.WATER) {
                return true;
            }
            return false;
        }
    };

    public FluidTank getTank() {
        return fluidHandler;
    }

    private ItemStackHandler itemHandler = new ItemStackHandler(1) {
        @Override
        protected void onContentsChanged(int slot) {
            markDirty();
        }
          
        @Override
        public boolean isItemValid(int slot, ItemStack stack)
        {
            if(stack.getItem() == Items.DIAMOND) {
                return true;
            }
            return false;
        }

        @Override
        public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
            if(stack.getItem() != Items.DIAMOND) {
                return stack;
            }
            return super.insertItem(slot, stack, simulate);
        }
    };

    private LazyOptional<IItemHandler> itemHandlerCap = LazyOptional.of(() -> itemHandler);
    private LazyOptional<IFluidHandler> fluidHandlerCap = LazyOptional.of(() -> fluidHandler);

    @Override
    public void read(CompoundNBT tag) {
        itemHandler.deserializeNBT(tag.getCompound("inventory"));
        fluidHandler.readFromNBT(tag);
        super.read(tag);
    }

    @Override
    public CompoundNBT write(CompoundNBT tag) {
        tag.put("inventory", itemHandler.serializeNBT());
        fluidHandler.writeToNBT(tag);
        return super.write(tag);
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return itemHandlerCap.cast();
        }
        if(cap == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
            return fluidHandlerCap.cast();
        }
        return super.getCapability(cap, side);
    }
}
