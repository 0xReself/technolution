package com.github.technolution.technolution.objects.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class ThetaOreBlock extends Block{
    public ThetaOreBlock() {
        super(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 2.0F));
    }
}
