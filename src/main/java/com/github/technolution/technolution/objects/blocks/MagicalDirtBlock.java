package com.github.technolution.technolution.objects.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class MagicalDirtBlock extends Block {
    private Boolean isGrass = false;

    public MagicalDirtBlock(Boolean state) {
        super(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.6f, 0.6f).lightValue(10).speedFactor(0.5f)
                .sound(SoundType.GROUND));
        isGrass = state;
    }
/*
    @Override
    public int getLightValue(BlockState state) {
        if(this.isGrass){
            return 15;
        }else{
            return 5;
        }
    }*/
}
