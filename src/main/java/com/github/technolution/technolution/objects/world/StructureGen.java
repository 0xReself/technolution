package com.github.technolution.technolution.objects.world;

import com.github.technolution.technolution.init.Register;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

public class StructureGen {
    public static void generateStructures() {
		for (Biome biome : ForgeRegistries.BIOMES) {
            biome.addStructure(Register.MAGIC_FIELD_STRUCTURE.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
			biome.addFeature(Decoration.SURFACE_STRUCTURES,
					Register.MAGIC_FIELD_STRUCTURE.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG)
							.withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
		}
	}
}
