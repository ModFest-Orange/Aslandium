package modfestorange.aslandium.generation.biomes;

import net.minecraft.entity.EntityCategory;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;

public class BottomLayerBiome extends Biome {
  protected BottomLayerBiome() {
    super(new Biome.Settings().configureSurfaceBuilder(SurfaceBuilder.NOPE,
        SurfaceBuilder.GRASS_CONFIG).precipitation(Biome.Precipitation.NONE)
        .category(Biome.Category.NONE).depth(0.1f).scale(0.2f).temperature(0.9f)
        .downfall(0.1f).waterColor(4159204).waterFogColor(329011).parent(null));
    DefaultBiomeFeatures.addLandCarvers(this);
    DefaultBiomeFeatures.addDefaultStructures(this);
    DefaultBiomeFeatures.addDefaultLakes(this);
    DefaultBiomeFeatures.addDungeons(this);
    DefaultBiomeFeatures.addMineables(this);
    DefaultBiomeFeatures.addDefaultOres(this);
    DefaultBiomeFeatures.addDefaultDisks(this);
    DefaultBiomeFeatures.addDefaultMushrooms(this);
    DefaultBiomeFeatures.addSprings(this);
    DefaultBiomeFeatures.addFrozenTopLayer(this);
    this.addSpawn(EntityCategory.AMBIENT,
        new Biome.SpawnEntry(EntityType.BAT, 10, 8, 8));
    this.addSpawn(EntityCategory.MONSTER,
        new Biome.SpawnEntry(EntityType.SPIDER, 100, 4, 4));
    this.addSpawn(EntityCategory.MONSTER,
        new Biome.SpawnEntry(EntityType.ZOMBIE, 95, 4, 4));
    this.addSpawn(EntityCategory.MONSTER,
        new Biome.SpawnEntry(EntityType.ZOMBIE_VILLAGER, 5, 1, 1));
    this.addSpawn(EntityCategory.MONSTER,
        new Biome.SpawnEntry(EntityType.SKELETON, 100, 4, 4));
    this.addSpawn(EntityCategory.MONSTER,
        new Biome.SpawnEntry(EntityType.CREEPER, 100, 4, 4));
    this.addSpawn(EntityCategory.MONSTER,
        new Biome.SpawnEntry(EntityType.SLIME, 100, 4, 4));
    this.addSpawn(EntityCategory.MONSTER,
        new Biome.SpawnEntry(EntityType.ENDERMAN, 10, 1, 4));
    this.addSpawn(EntityCategory.MONSTER,
        new Biome.SpawnEntry(EntityType.WITCH, 5, 1, 1));
  }
}
