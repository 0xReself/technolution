package com.github.technolution.technolution.objects.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CrystalOreBlock extends Block{
    private int xpAmount = 1;
    public CrystalOreBlock(int tier) {
        super(Block.Properties.create(Material.ROCK).hardnessAndResistance(1 + 1.5f * tier, 2.0F));
        xpAmount += tier * 2 ;
    }

    @Override
    public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
        if(!worldIn.isRemote()){
            dropXpOnBlockBreak(worldIn, pos, this.xpAmount);
        }
        super.onBlockHarvested(worldIn, pos, state, player);
    }
}
