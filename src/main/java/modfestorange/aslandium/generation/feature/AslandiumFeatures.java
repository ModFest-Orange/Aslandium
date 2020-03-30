package modfestorange.aslandium.generation.feature;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.DecoratorConfig;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

import static modfestorange.aslandium.util.IdentifierUtil.id;
import static modfestorange.aslandium.util.IdentifierUtil.idString;

public class AslandiumFeatures {
    public static StructureFeature<DefaultFeatureConfig> PORTAL_DUNGEON_FEATURE = Registry.register(
        Registry.STRUCTURE_FEATURE,
        id("portal_dungeon"),
        new PortalDungeonFeature(DefaultFeatureConfig::deserialize));

    public static void register() {
        Feature.STRUCTURES.put(idString("portal_dungeon"), PORTAL_DUNGEON_FEATURE);
        Biomes.OCEAN.addStructureFeature(PORTAL_DUNGEON_FEATURE.configure(FeatureConfig.DEFAULT));
        Biomes.OCEAN.addFeature(GenerationStep.Feature.SURFACE_STRUCTURES, PORTAL_DUNGEON_FEATURE
            .configure(FeatureConfig.DEFAULT)
            .createDecoratedFeature(Decorator.NOPE.configure(DecoratorConfig.DEFAULT)));
    }
}
