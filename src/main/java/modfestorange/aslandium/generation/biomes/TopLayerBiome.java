package modfestorange.aslandium.generation.biomes;

import net.minecraft.entity.EntityCategory;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;

public class TopLayerBiome extends Biome {
  public TopLayerBiome() {
    super(new Biome.Settings().configureSurfaceBuilder(SurfaceBuilder.DEFAULT,
        SurfaceBuilder.GRASS_CONFIG).precipitation(Biome.Precipitation.NONE)
        .category(Biome.Category.FOREST).depth(0.1f).scale(0.2f)
        .temperature(0.7f).downfall(0.8f).waterColor(4159204)
        .waterFogColor(329011).parent(null));
    DefaultBiomeFeatures.addLandCarvers(this);
    DefaultBiomeFeatures.addDefaultStructures(this);
    DefaultBiomeFeatures.addDefaultLakes(this);
    DefaultBiomeFeatures.addDungeons(this);
    DefaultBiomeFeatures.addForestFlowers(this);
    DefaultBiomeFeatures.addMineables(this);
    DefaultBiomeFeatures.addDefaultOres(this);
    DefaultBiomeFeatures.addDefaultDisks(this);
    DefaultBiomeFeatures.addForestTrees(this);
    DefaultBiomeFeatures.addDefaultFlowers(this);
    DefaultBiomeFeatures.addForestGrass(this);
    DefaultBiomeFeatures.addDefaultMushrooms(this);
    DefaultBiomeFeatures.addDefaultVegetation(this);
    DefaultBiomeFeatures.addSprings(this);
    DefaultBiomeFeatures.addFrozenTopLayer(this);
    this.addSpawn(EntityCategory.CREATURE,
        new Biome.SpawnEntry(EntityType.SHEEP, 12, 4, 4));
    this.addSpawn(EntityCategory.CREATURE,
        new Biome.SpawnEntry(EntityType.PIG, 10, 4, 4));
    this.addSpawn(EntityCategory.CREATURE,
        new Biome.SpawnEntry(EntityType.CHICKEN, 10, 4, 4));
    this.addSpawn(EntityCategory.CREATURE,
        new Biome.SpawnEntry(EntityType.COW, 8, 4, 4));
    this.addSpawn(EntityCategory.CREATURE,
        new Biome.SpawnEntry(EntityType.WOLF, 5, 4, 4));
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
