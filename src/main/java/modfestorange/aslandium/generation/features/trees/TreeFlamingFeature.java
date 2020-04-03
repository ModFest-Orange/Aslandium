package modfestorange.aslandium.generation.features.trees;

import net.minecraft.block.Material;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class TreeFlamingFeature extends ProceduralTreeFeature<TreeFeatureConfig> {
	public TreeFlamingFeature() {
		super(TreeFeatureConfig::deserialize);
	}

	@Override
	protected boolean generate(ModifiableTestableWorld world, Random random, BlockPos pos, Set<BlockPos> logPositions, Set<BlockPos> leavesPositions, BlockBox blockBox, TreeFeatureConfig config) {
		float direction = random.nextFloat() * fullAngleConst;
		float angle = (random.nextFloat() * fullAngleConst / 4) + fullAngleConst / 8;
		int length = random.nextInt(config.baseHeight - 5) + 5;

		BlockPos endPos = new BlockPos(
			pos.getX() + Math.round(Math.sin(direction) * Math.cos(angle) * length),
			pos.getY() + Math.round(Math.sin(angle) * length),
			pos.getZ() + Math.round(Math.cos(direction) * Math.cos(angle) * length)
		);

		if(!world.testBlockState(pos.down(), (block) -> block.getMaterial() == Material.SAND))
			return false;

		Set<BlockPos> logPos = new HashSet<>();

		generateLine(pos, endPos, logPos);

		endPos = endPos.up();

		logPos.add(endPos);

		if(!doesTreeFit(world, logPos))
			return false;

		logPos.forEach(position -> setLogBlockState(world, random, position, logPositions, blockBox, config));

		setLeavesBlockState(world, random, endPos.west(-3).down(), leavesPositions, blockBox, config);
		setLeavesBlockState(world, random, endPos.south(-3).down(), leavesPositions, blockBox, config);
		setLeavesBlockState(world, random, endPos.west(4).down(), leavesPositions, blockBox, config);
		setLeavesBlockState(world, random, endPos.south(4).down(), leavesPositions, blockBox, config);

		setLeavesBlockState(world, random, endPos.up(), leavesPositions, blockBox, config);

		for(int i = -3; i < 4; i++){
			setLeavesBlockState(world, random, endPos.west(i), leavesPositions, blockBox, config);
			setLeavesBlockState(world, random, endPos.south(i), leavesPositions, blockBox, config);
		}

		return true;
	}
}
