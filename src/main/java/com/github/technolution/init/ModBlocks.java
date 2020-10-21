package com.github.technolution.init;

import com.github.technolution.technolution.objects.blocks.ExampleOre;
import com.github.technolution.technolution.objects.tileentity.EssenceFurnaceEntity;
import com.github.technolution.technolution.objects.blocks.EssenceFurnace;
import com.github.technolution.technolution.Technolution;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(Technolution.ModID)
public class ModBlocks {
    public static final Block Example_ore = new ExampleOre();
    public static final Block EssenceFurnace = new EssenceFurnace();
    public static final TileEntityType<EssenceFurnaceEntity> essencetile = TileEntityType.Builder.create(EssenceFurnaceEntity::new, EssenceFurnace).build(null);
}