package com.github.technolution.technolution.objects.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class TurquoiseOreBlock extends Block{
    public TurquoiseOreBlock() {
        super(Block.Properties.create(Material.ROCK).hardnessAndResistance(10.0F, 1.0F));
    }
}
