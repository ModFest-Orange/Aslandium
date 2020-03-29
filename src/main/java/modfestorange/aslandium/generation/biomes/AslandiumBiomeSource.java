package modfestorange.aslandium.generation.biomes;

import com.google.common.collect.ImmutableSet;
import modfestorange.aslandium.generation.AslandiumChunkGenerator;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.source.BiomeSource;

import java.util.Set;

public class AslandiumBiomeSource extends BiomeSource {
  private static final Set<Biome> BIOMES = ImmutableSet
      .of(AslandiumBiomes.TOP_LAYER, AslandiumBiomes.MID_LAYER,
          AslandiumBiomes.BOTTOM_LAYER);

  public AslandiumBiomeSource() {
    super(BIOMES);
  }

  @Override
  public Biome getBiomeForNoiseGen(int biomeX, int biomeY, int biomeZ) {
    if (biomeY < AslandiumChunkGenerator.MID_LAYER_START) {
      return AslandiumBiomes.BOTTOM_LAYER;
    } else if (biomeY < AslandiumChunkGenerator.TOP_LAYER_START) {
      return AslandiumBiomes.MID_LAYER;
    } else {
      return AslandiumBiomes.TOP_LAYER;
    }
  }
}
