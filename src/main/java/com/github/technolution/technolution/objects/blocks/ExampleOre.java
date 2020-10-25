package com.github.technolution.technolution.objects.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class ExampleOre extends Block{
    public ExampleOre() {
        super(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.0F, 1.0F));
    }
}
