package modfestorange.aslandium.generation;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.source.BiomeSourceType;
import net.minecraft.world.biome.source.FixedBiomeSourceConfig;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorType;
import net.minecraft.world.gen.chunk.FlatChunkGeneratorConfig;

import javax.annotation.Nullable;

public class AslandiumDimension extends Dimension {
  public AslandiumDimension(World world, DimensionType type) {
    super(world, type, 0.0f);
  }

  @Override public ChunkGenerator<?> createChunkGenerator() {
    // TODO replace with actual generation
    FlatChunkGeneratorConfig generatorConfig =
        FlatChunkGeneratorConfig.getDefaultConfig();
    // The biome everywhere will be jungle
    FixedBiomeSourceConfig biomeConfig =
        BiomeSourceType.FIXED.getConfig(world.getLevelProperties())
            .setBiome(Biomes.JUNGLE);
    return ChunkGeneratorType.FLAT
        .create(world, BiomeSourceType.FIXED.applyConfig(biomeConfig),
            generatorConfig);

  }

  @Nullable @Override public BlockPos getSpawningBlockInChunk(ChunkPos chunkPos,
      boolean checkMobSpawnValidity) {
    return null;
  }

  @Nullable @Override public BlockPos getTopSpawningBlockPosition(int x, int z,
      boolean checkMobSpawnValidity) {
    return null;
  }

  @Override public float getSkyAngle(long timeOfDay, float tickDelta) {
    final int dayLength = 24000;
    double daysPassed = ((double) timeOfDay + tickDelta) / dayLength;
    return (float) MathHelper.fractionalPart(daysPassed - 0.25);
  }

  @Override public boolean hasVisibleSky() {
    return true;
  }

  @Environment(EnvType.CLIENT) @Override
  public Vec3d getFogColor(float skyAngle, float tickDelta) {
    // cloned from OverworldDimension
    float f = MathHelper.cos(skyAngle * 6.2831855F) * 2.0F + 0.5F;
    f = MathHelper.clamp(f, 0.0F, 1.0F);
    float g = 0.7529412F;
    float h = 0.84705883F;
    float i = 1.0F;
    g *= f * 0.94F + 0.06F;
    h *= f * 0.94F + 0.06F;
    i *= f * 0.91F + 0.09F;
    return new Vec3d((double) g, (double) h, (double) i);
  }

  @Override public boolean canPlayersSleep() {
    return false;
  }

  @Override public boolean isFogThick(int x, int z) {
    return false;
  }

  @Override public DimensionType getType() {
    return AslandiumDimensions.ASLANDIUM;
  }
}
