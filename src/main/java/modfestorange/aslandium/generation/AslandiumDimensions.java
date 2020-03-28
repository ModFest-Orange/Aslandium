package modfestorange.aslandium.generation;

import modfestorange.aslandium.AslandiumMod;
import net.fabricmc.fabric.api.dimension.v1.FabricDimensionType;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Heightmap;

public class AslandiumDimensions {
  public static final FabricDimensionType ASLANDIUM =
      FabricDimensionType.builder().defaultPlacer(
          (oldEntity, destinationWorld, portalDir, horizontalOffset, verticalOffset) -> new BlockPattern.TeleportTarget(
              new Vec3d(destinationWorld
                  .getTopPosition(Heightmap.Type.WORLD_SURFACE,
                      BlockPos.ORIGIN)), oldEntity.getVelocity(),
              (int) oldEntity.yaw)).factory(AslandiumDimension::new)
          .buildAndRegister(new Identifier(AslandiumMod.MOD_ID, "aslandium"));

  public static void register() {
    //
  }
}
