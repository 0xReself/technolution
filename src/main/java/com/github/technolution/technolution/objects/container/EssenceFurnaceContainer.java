package com.github.technolution.technolution.objects.container;

import com.github.technolution.technolution.init.Register;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EssenceFurnaceContainer extends Container {



    public EssenceFurnaceContainer(int windowId, World world, BlockPos pos, PlayerInventory playerInventory, PlayerEntity player) {
        super(Register.ESSENCE_FURNACE_CONTAINER.get(), windowId);
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        // TODO Auto-generated method stub
        return false;
    }
    
}
