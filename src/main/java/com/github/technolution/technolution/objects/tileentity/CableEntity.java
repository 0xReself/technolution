package com.github.technolution.technolution.objects.tileentity;

import com.github.technolution.technolution.Technolution;
import com.github.technolution.technolution.init.Register;
import com.github.technolution.technolution.objects.tools.CustomEnergyStorage;

import net.minecraft.block.BlockState;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

public class CableEntity extends TileEntity implements ITickableTileEntity{

    private CustomEnergyStorage energyStorage = createEnergy();
    private LazyOptional<IEnergyStorage> energy = LazyOptional.of(() -> energyStorage);

    public CableEntity() {
        super(Register.CABLE_ENTITY.get());
    }

    @Override
    public void tick() {
        recalculateSides();
    }

    public void recalculateSides() {
        BlockState state = world.getBlockState(pos);
        /*Technolution.LOGGER.debug("BlockID: " + getPos());
        Technolution.LOGGER.debug("north " + state.get(BlockStateProperties.NORTH));
        Technolution.LOGGER.debug("south " + state.get(BlockStateProperties.SOUTH));
        Technolution.LOGGER.debug("east " + state.get(BlockStateProperties.EAST));
        Technolution.LOGGER.debug("west " + state.get(BlockStateProperties.WEST));
        Technolution.LOGGER.debug("up " + state.get(BlockStateProperties.UP));
        Technolution.LOGGER.debug("down " + state.get(BlockStateProperties.DOWN));*/
        for (Direction dir : Direction.values()) { 
            TileEntity ent = world.getTileEntity(pos.offset(dir));
            if(ent != null) {
                boolean cont = ent.getCapability(CapabilityEnergy.ENERGY, dir).map(handler -> {
                    return true;
                }
                ).orElse(false);
                if(cont == true) {
                    world.setBlockState(pos, state.with(getPropertyFromDirection(dir), true), 
                    Constants.BlockFlags.NOTIFY_NEIGHBORS + Constants.BlockFlags.BLOCK_UPDATE);
                } else {
                    world.setBlockState(pos, state.with(getPropertyFromDirection(dir), false), 
                    Constants.BlockFlags.NOTIFY_NEIGHBORS + Constants.BlockFlags.BLOCK_UPDATE);
                }
            }
        }
    }
    //DOWN zu UP geht
    //NORTH zu SOUTH
    //WEST zu EAST
    public BooleanProperty getPropertyFromDirection(Direction dir) {
        if(dir == Direction.NORTH) {
            return BlockStateProperties.NORTH;
        } else if(dir == Direction.SOUTH) {
            return BlockStateProperties.SOUTH;
        } else if(dir == Direction.EAST) {
            return BlockStateProperties.EAST;
        } else if(dir == Direction.WEST) {
            return BlockStateProperties.WEST;
        } else if(dir == Direction.UP) {
            return BlockStateProperties.UP;
        } else {
            return BlockStateProperties.DOWN;
        }
    }

    private CustomEnergyStorage createEnergy() {
        return new CustomEnergyStorage(100, 5) {
            @Override
            public void onEnergyChanged() {
                markDirty();
            }
        };
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        if(cap == CapabilityEnergy.ENERGY) {
            return energy.cast();
        }
        return super.getCapability(cap, side);
    }
}
