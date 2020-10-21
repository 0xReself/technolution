package com.github.technolution.technolution.objects.blocks;

import com.github.technolution.technolution.objects.tileentity.EssenceFurnaceEntity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class EssenceFurnace extends Block {

    public EssenceFurnace() {
        super(Block.Properties.create(Material.ROCK));
    }
    
    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new EssenceFurnaceEntity();
    }
}
