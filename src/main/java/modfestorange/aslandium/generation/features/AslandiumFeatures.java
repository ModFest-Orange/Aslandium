package modfestorange.aslandium.generation.features;

import modfestorange.aslandium.AslandiumMod;
import modfestorange.aslandium.generation.features.trees.TreeFlamingFeature;
import modfestorange.aslandium.generation.features.trees.TreeLeaningFeature;
import modfestorange.aslandium.generation.features.trees.TreeTangledFeature;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.ChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;

public class AslandiumFeatures {
	public static final TreeLeaningFeature TREE_LEANING = new TreeLeaningFeature();
	public static final TreeTangledFeature TREE_TANGLED = new TreeTangledFeature();
	public static final TreeFlamingFeature TREE_FLAMING = new TreeFlamingFeature();

	public static final ConfiguredFeature<?, ?> CONFIGURED_TREE_LEANING = TREE_LEANING.configure(
			new TreeFeatureConfig.Builder(
					new SimpleBlockStateProvider(Blocks.BIRCH_LOG.getDefaultState()),
					new SimpleBlockStateProvider(Blocks.ACACIA_LEAVES.getDefaultState())
			).baseHeight(8).build()
	).createDecoratedFeature(Decorator.CHANCE_HEIGHTMAP.configure(new ChanceDecoratorConfig(2)));

	public static final ConfiguredFeature<?, ?> CONFIGURED_TREE_TANGLED = TREE_TANGLED.configure(
			new TreeFeatureConfig.Builder(
					new SimpleBlockStateProvider(Blocks.OAK_LOG.getDefaultState()),
					new SimpleBlockStateProvider(Blocks.ACACIA_LEAVES.getDefaultState())
			).baseHeight(8).build()
	).createDecoratedFeature(Decorator.CHANCE_HEIGHTMAP.configure(new ChanceDecoratorConfig(2)));

	public static final ConfiguredFeature<?, ?> CONFIGURED_TREE_FLAMING = TREE_FLAMING.configure(
			new TreeFeatureConfig.Builder(
					new SimpleBlockStateProvider(Blocks.NETHER_BRICKS.getDefaultState()),
					new SimpleBlockStateProvider(Blocks.MAGMA_BLOCK.getDefaultState())
			).baseHeight(8).build()
	).createDecoratedFeature(Decorator.CHANCE_HEIGHTMAP.configure(new ChanceDecoratorConfig(2)));

	public static void register(){
		Registry.register(Registry.FEATURE, new Identifier(AslandiumMod.MOD_ID, "tree_leaning_feature"), TREE_LEANING);
		Registry.register(Registry.FEATURE, new Identifier(AslandiumMod.MOD_ID, "tree_tangled_feature"), TREE_TANGLED);
		Registry.register(Registry.FEATURE, new Identifier(AslandiumMod.MOD_ID, "tree_flaming_feature"), TREE_FLAMING);

		Registry.BIOME.forEach((biome) -> {
			biome.addFeature(
					GenerationStep.Feature.VEGETAL_DECORATION,
					CONFIGURED_TREE_LEANING
			);
		});

		Registry.BIOME.forEach((biome) -> {
			biome.addFeature(
					GenerationStep.Feature.VEGETAL_DECORATION,
					CONFIGURED_TREE_FLAMING
			);
		});
/*
		Registry.BIOME.forEach((biome) -> {
			biome.addFeature(
					GenerationStep.Feature.VEGETAL_DECORATION,
					CONFIGURED_TREE_TANGLED
			);
		});

 */
	}

}
