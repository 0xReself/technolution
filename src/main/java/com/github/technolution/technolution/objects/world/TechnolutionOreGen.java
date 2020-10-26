package com.github.technolution.technolution.objects.world;

import com.github.technolution.technolution.init.Register;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.ConfiguredPlacement;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.world.gen.GenerationStage;

public class TechnolutionOreGen {
    public static void generateOre() {
        // for number of Bioms
        for (Biome biome : ForgeRegistries.BIOMES) {
            ConfiguredPlacement<?> customConfig = Placement.COUNT_RANGE.configure(new CountRangeConfig(20, 5, 5, 25));
            biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
                    Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE,
                            Register.THETA_ORE_BLOCK.get().getDefaultState(), 10)).withPlacement(customConfig));
        }
    }
}
