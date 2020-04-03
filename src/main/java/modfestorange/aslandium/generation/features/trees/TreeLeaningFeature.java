package modfestorange.aslandium.generation.features.trees;

import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

import java.util.*;

public class TreeLeaningFeature extends ProceduralTreeFeature<TreeFeatureConfig> {
	public TreeLeaningFeature() {
		super(TreeFeatureConfig::deserialize);
	}

	@Override
	protected boolean generate(ModifiableTestableWorld world, Random random, BlockPos pos, Set<BlockPos> logPositions, Set<BlockPos> leavesPositions, BlockBox blockBox, TreeFeatureConfig config) {
		if(!isNaturalDirtOrGrass(world, pos.down()))
			return false;

		int numberOfTrunks = random.nextInt(4) + 2;
		int baseHeight = random.nextInt(4) + 2;

		//all angels are calculated relative to positive Z
		float leanDirection = random.nextFloat() * fullAngleConst; // angle relative to positive Z

		Set<BlockPos> tempLogPos = new HashSet<>();
		Set<BlockPos> tempLeavesPos = new HashSet<>();

		for(int i = 0; i < baseHeight; i++){
			tempLogPos.add(pos);
			pos = pos.up();
		}

		for(int i = 0; i < numberOfTrunks; i++) {
			float leanAngle = random.nextFloat() * fullAngleConst / 4; //angle relative to surface
			int length = random.nextInt(config.baseHeight - 5) + 5;

			int leanDistance = (int) Math.round(Math.cos(leanAngle) * length);
			int height = (int) Math.round(Math.sin(leanAngle) * length);

			BlockPos endPos = new BlockPos(
					pos.getX() + (int) Math.round(Math.sin(leanDirection) * leanDistance),
					pos.getY() + height,
					pos.getZ() + (int) Math.round(Math.cos(leanDirection) * leanDistance)
			);  // position on which the log of the trunk ends

			generateLine(pos, endPos, tempLogPos);
			generateFoliage(endPos, tempLeavesPos);

			leanDirection += goldenAngle;
		}

		if(!doesTreeFit(world, tempLogPos))
			return false;

		tempLogPos.forEach((position) -> setLogBlockState(world, random, position, leavesPositions, blockBox, config));

		tempLeavesPos.forEach((position) -> setLeavesBlockState(world, random, position, logPositions, blockBox, config));

		return true;
	}

	private void generateFoliage(BlockPos begin, Set<BlockPos> pos){
		for(int i = 0; i < 2; i++){
			for(int j = -1; j < 2; j++){
				for(int k = -1; k < 2; k++){
					pos.add(begin.up(i).north(j).west(k));
				}
			}
		}

		Direction dir = Direction.NORTH;

		for(int i = 0; i < 4; i++){
			for(int j = -1; j < 2; j++){
				pos.add(begin.offset(dir, 2).offset(dir.rotateYClockwise(), j));
			}

			dir = dir.rotateYClockwise();
		}

		pos.add(begin.up(2));
	}
}