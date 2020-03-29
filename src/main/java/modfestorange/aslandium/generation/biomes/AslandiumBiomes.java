package modfestorange.aslandium.generation.biomes;

import modfestorange.aslandium.AslandiumMod;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;

public class AslandiumBiomes {
  public static final Biome TOP_LAYER = Registry.register(Registry.BIOME,
      new Identifier(AslandiumMod.MOD_ID, "top_layer"), new TopLayerBiome());
  public static final Biome MID_LAYER = Registry.register(Registry.BIOME,
      new Identifier(AslandiumMod.MOD_ID, "mid_layer"), new MidLayerBiome());
  public static final Biome BOTTOM_LAYER = Registry.register(Registry.BIOME,
      new Identifier(AslandiumMod.MOD_ID, "bottom_layer"),
      new BottomLayerBiome());
}
