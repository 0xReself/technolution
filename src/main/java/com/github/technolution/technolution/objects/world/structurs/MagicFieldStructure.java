package com.github.technolution.technolution.objects.world.structurs;

import java.util.Random;
import java.util.function.Function;

import com.github.technolution.technolution.Technolution;
import com.mojang.datafixers.Dynamic;

import net.minecraft.util.Rotation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeManager;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.gen.Heightmap;

public class MagicFieldStructure extends Structure<NoFeatureConfig>{

  public MagicFieldStructure(Function<Dynamic<?>, ? extends NoFeatureConfig> config) {
    super(config);
  }

  @Override
  public boolean canBeGenerated(BiomeManager biomeManagerIn, ChunkGenerator<?> generatorIn, Random randIn, int chunkX,
      int chunkZ, Biome biomeIn) {
    ChunkPos pos = this.getStartPositionForPosition(generatorIn, randIn, chunkX, chunkZ, 0, 0);

    if (chunkX == pos.x && chunkZ == pos.z) {
      if (generatorIn.hasStructure(biomeIn, this)) {
        return true;
      }
    }

    return false;
  }

  @Override
  public IStartFactory getStartFactory() {
    return MagicFieldStructure.Start::new;
  }

  @Override
  public String getStructureName() {
    return Technolution.ModID + ":magicalField";
  }

  @Override
  public int getSize() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
	protected ChunkPos getStartPositionForPosition(ChunkGenerator<?> generatorIn, Random randIn, int x, int z, int offX,
			int offZ) {
		int maxDistance = 3;
		int minDistance = 2;

		int xTemp = x + maxDistance * offX;
		int ztemp = z + maxDistance * offZ;
		int xTemp2 = xTemp < 0 ? xTemp - maxDistance + 1 : xTemp;
		int zTemp2 = ztemp < 0 ? ztemp - maxDistance + 1 : ztemp;
		int validChunkX = xTemp2 / maxDistance;
		int validChunkZ = zTemp2 / maxDistance;

		((SharedSeedRandom) randIn).setLargeFeatureSeedWithSalt(generatorIn.getSeed(), validChunkX, validChunkZ,
				this.getSeedModifier());
		validChunkX = validChunkX * maxDistance;
		validChunkZ = validChunkZ * maxDistance;
		validChunkX = validChunkX + randIn.nextInt(maxDistance - minDistance);
		validChunkZ = validChunkZ + randIn.nextInt(maxDistance - minDistance);

		return new ChunkPos(validChunkX, validChunkZ);
	}

  protected int getSeedModifier() {
    return 1029384756;
  }
  
  public static class Start extends StructureStart {

		public Start(Structure<?> structure, int chunkX, int chunkZ, MutableBoundingBox boundingBox, int reference,
				long seed) {
			super(structure, chunkX, chunkZ, boundingBox, reference, seed);
		}

		@Override
		public void init(ChunkGenerator<?> generator, TemplateManager templateManagerIn, int chunkX, int chunkZ,
				Biome biomeIn) {
			Rotation rotation = Rotation.values()[this.rand.nextInt(Rotation.values().length)];

			int x = (chunkX << 4) + 7;
			int z = (chunkX << 4) + 7;
			int y = generator.func_222531_c(x, z, Heightmap.Type.WORLD_SURFACE_WG);
			BlockPos pos = new BlockPos(x, y, z);

			MagicFieldPieces.start(templateManagerIn, pos, rotation, this.components, this.rand);

			this.recalculateStructureSize();

			Technolution.LOGGER.info("We can find a house at: " + pos);
		}
	}
}