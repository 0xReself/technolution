package com.github.technolution.technolution.objects.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CrystalOreBlock extends Block{
    private double xpAmount = 0.5f;
    private int xpDrop = 0;
    public CrystalOreBlock(int tier) {
        super(Block.Properties.create(Material.ROCK).hardnessAndResistance(1 + 1.5f * tier, 2.0F));
        xpAmount *= tier;
    }

    @Override
    public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
        if(xpAmount % 1 != 0){
            xpDrop = (int)(Math.random() * (xpAmount + (1 - xpAmount % 1) - xpAmount - (xpAmount / 1)) + xpAmount - (xpAmount / 1));
        }
        dropXpOnBlockBreak(worldIn, pos, xpDrop);
        super.onBlockHarvested(worldIn, pos, state, player);
    }
}
