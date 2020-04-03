package modfestorange.aslandium.generation.features.trees;

import com.mojang.datafixers.Dynamic;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

import java.util.Set;
import java.util.function.Function;

public abstract class ProceduralTreeFeature<T extends TreeFeatureConfig> extends AbstractTreeFeature<T> {
	protected final static float fullAngleConst = 6.28319f; //2 * π
	protected final static float goldenAngle = 2.39996f;

	public ProceduralTreeFeature(Function<Dynamic<?>, ? extends T> configFactory) {
		super(configFactory);
	}

	protected static boolean doesTreeFit(TestableWorld world, final Set<BlockPos> logs){
		for(BlockPos pos : logs){
			if(!canTreeReplace(world, pos)){
				return false;
			}
		}

		return true;
	}

	protected static void generateLine(BlockPos start, BlockPos end, Set<BlockPos> pos){
		float dz = end.getZ() - start.getZ();
		float dy = end.getY() - start.getY();
		float dx = end.getX() - start.getX();

		pos.add(start);

		float x = start.getX(), y = start.getY(), z = start.getZ();

		if(Math.abs(dx) >= Math.abs(dy) && Math.abs(dx) >= Math.abs(dz)) {
			float ex = (dx > 0 ? 1 : -1);
			float ey = dy / Math.abs(dx);
			float ez = dz / Math.abs(dx);

			while(Math.round(x) != end.getX()){
				x += ex;
				y += ey;
				z += ez;

				pos.add(new BlockPos(Math.round(x), Math.round(y), Math.round(z)));
			}
		}else if(dy >= Math.abs(dx) && dy >= Math.abs(dz)){
			float ex = dx / Math.abs(dy);
			float ey = (dy > 0 ? 1 : -1);
			float ez = dz / Math.abs(dy);

			while(Math.round(y) != end.getY()){
				x += ex;
				y += ey;
				z += ez;

				pos.add(new BlockPos(Math.round(x), Math.round(y), Math.round(z)));
			}
		}else{
			float ex = dx / Math.abs(dz);
			float ey = dy / Math.abs(dz);
			float ez = (dz > 0 ? 1 : -1);

			while(Math.round(z) != end.getZ()){
				x += ex;
				y += ey;
				z += ez;

				pos.add(new BlockPos(Math.round(x), Math.round(y), Math.round(z)));
			}
		}
	}
}
