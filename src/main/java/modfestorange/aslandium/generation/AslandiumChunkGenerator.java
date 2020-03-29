package modfestorange.aslandium.generation;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.noise.OctaveSimplexNoiseSampler;
import net.minecraft.world.ChunkRegion;
import net.minecraft.world.Heightmap;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig;

public class AslandiumChunkGenerator
    extends ChunkGenerator<ChunkGeneratorConfig> {
  public static final int TOP_LAYER_START = 144;
  public static final int TOP_LAYER_END = 208;
  public static final int MID_LAYER_START = 64;
  public static final int MID_LAYER_SURFACE = 96;
  public static final int BOTTOM_LAYER_START = 0;
  private static final long SEED_XOR_MASK = 0x766f6c64656d6170L;

  ChunkRandom random;
  OctaveSimplexNoiseSampler midLayerHeightSampler;
  OctaveSimplexNoiseSampler midLayerStoneDepthSampler;
  OctaveSimplexNoiseSampler surfaceDepthNoiseSampler;

  public AslandiumChunkGenerator(IWorld world, BiomeSource biomeSource,
      ChunkGeneratorConfig config) {
    super(world, biomeSource, config);
    this.random = new ChunkRandom(this.seed ^ SEED_XOR_MASK);
    this.midLayerHeightSampler =
        new OctaveSimplexNoiseSampler(this.random, 3, 0);
    this.midLayerStoneDepthSampler =
        new OctaveSimplexNoiseSampler(this.random, 3, 0);
    this.surfaceDepthNoiseSampler =
        new OctaveSimplexNoiseSampler(this.random, 3, 0);
  }

  private static final double SURFACE_DEPTH_COORDINATE_NOISE_FACTOR = 0.0625;

  @Override public void buildSurface(ChunkRegion chunkRegion, Chunk chunk) {
    BlockState stone = Blocks.STONE.getDefaultState();
    BlockState water = Blocks.WATER.getDefaultState();
    BlockPos.Mutable mutable = new BlockPos.Mutable();
    ChunkRandom chunkRandom = new ChunkRandom();
    ChunkPos chunkPos = chunk.getPos();
    chunkRandom.setSeed(chunkPos.x, chunkPos.z);
    for (int localX = 0; localX < 16; ++localX) {
      for (int localZ = 0; localZ < 16; ++localZ) {
        int x = (chunkPos.x << 4) + localX;
        int z = (chunkPos.z << 4) + localZ;
        for (int y = 0; y < 256; ++y) {
          if (chunk.getBlockState(mutable.set(localX, y, localZ))
              .getBlock() == Blocks.STONE && chunk
              .getBlockState(mutable.set(localX, y + 1, localZ)).isAir()) {
            double noise = surfaceDepthNoiseSampler
                .sample(SURFACE_DEPTH_COORDINATE_NOISE_FACTOR * x,
                    SURFACE_DEPTH_COORDINATE_NOISE_FACTOR * z,
                    SURFACE_DEPTH_COORDINATE_NOISE_FACTOR,
                    SURFACE_DEPTH_COORDINATE_NOISE_FACTOR * localX) * 15.0;
            Biome biome = chunkRegion.getBiome(mutable.set(x, y + 1, z));
            biome.buildSurface(random, chunk, x, z, y, noise, stone, water, 0,
                this.world.getSeed());
          }
        }
      }
    }
  }

  @Override public int getSpawnHeight() {
    return 63;
  }

  private static final double MID_LAYER_SURFACE_NOISE_SCALE = 40.0;

  @Override public void populateNoise(IWorld world, Chunk chunk) {
    BlockState stone = Blocks.STONE.getDefaultState();
    BlockPos.Mutable mutable = new BlockPos.Mutable();
    for (int localX = 0; localX < 16; ++localX) {
      for (int localZ = 0; localZ < 16; ++localZ) {
        int x = (chunk.getPos().x << 4) + localX;
        int z = (chunk.getPos().z << 4) + localZ;
        // Bottom layer
        // Mid layer
        double midLayerHeightSample = midLayerHeightSampler
            .sample(x / MID_LAYER_SURFACE_NOISE_SCALE,
                z / MID_LAYER_SURFACE_NOISE_SCALE, true);
        if (midLayerHeightSample >= 0) {
          double midLayerSurfaceHeight =
              (TOP_LAYER_START - MID_LAYER_SURFACE) * midLayerHeightSample;
          double midLayerStoneDepthMultiplier = (midLayerSurfaceHeight >= 5) ?
              1.0 :
              (0.1 + midLayerSurfaceHeight * 0.18);
          midLayerSurfaceHeight += MID_LAYER_SURFACE;
          double midLayerStoneDepthSample =
              0.5 + 0.5 * midLayerStoneDepthSampler.sample(x, z, true);
          midLayerStoneDepthSample *= midLayerStoneDepthMultiplier;
          double midLayerBottomHeight =
              MID_LAYER_SURFACE - (MID_LAYER_SURFACE - MID_LAYER_START) * midLayerStoneDepthSample;
          for (int y =
               (int) midLayerBottomHeight; y < (int) midLayerSurfaceHeight; ++y) {
            chunk.setBlockState(mutable.set(x, y, z), stone, false);
          }
        }
        // Top layer
      }
    }
  }

  @Override
  public int getHeightOnGround(int x, int z, Heightmap.Type heightmapType) {
    return 100;
  }
}
