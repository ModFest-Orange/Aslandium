package modfestorange.aslandium.generation.features.trees;

import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class TreeTangledFeature extends ProceduralTreeFeature<TreeFeatureConfig> {
	public TreeTangledFeature() {
		super(TreeFeatureConfig::deserialize);
	}

	@Override
	protected boolean generate(ModifiableTestableWorld world, Random random, BlockPos pos, Set<BlockPos> logPositions, Set<BlockPos> leavesPositions, BlockBox blockBox, TreeFeatureConfig config) {
		int numberOfRoots = random.nextInt(18) + 8;
		float rootAngle = random.nextFloat() * fullAngleConst;
		int rootHeight = random.nextInt(3) + 3;
		int trunkHeight = random.nextInt(4) + 4;

		Set<BlockPos> logBlocks = new HashSet<>();

		for(int i = 0; i < numberOfRoots; i++){
			int length = random.nextInt(5) + rootHeight;
			int x = (int) Math.round(Math.sin(rootAngle) * length);
			int z = (int) Math.round(Math.cos(rootAngle) * length);

			int y = 0;
			int cnt = 0;

			while(!isNaturalDirtOrGrass(world, pos.north(x).west(z).up(y - 1)) && Math.abs(y) < rootHeight && cnt < 20) {
				if(isAir(world, pos.north(x).west(z).up(y - 1)))
					y--;
				else
					y++;

				cnt++;
			}

			if(!isNaturalDirtOrGrass(world, pos.north(x).west(z).up(y - 1)))
				continue;//return false;

			generateLine(pos.north(x).west(z).up(y), pos.up(rootHeight).east(x > 0 ? 1 : -1).south(z > 0 ? 1 : -1), logBlocks);

			rootAngle += goldenAngle;
		}

		if(logBlocks.isEmpty())
			return  false;

		for(int i = 0; i < trunkHeight; i++)
			logBlocks.add(pos.up(rootHeight).up(i));

		if(!doesTreeFit(world, logBlocks))
			return false;

		logBlocks.forEach((position) -> setLogBlockState(world, random, position, logPositions, blockBox, config));

		return true;
	}
}
