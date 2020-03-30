package modfestorange.aslandium.generation.feature;

import com.mojang.datafixers.Dynamic;
import modfestorange.aslandium.generation.structure.PortalDungeonGenerator;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructureStart;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.AbstractTempleFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

import java.util.function.Function;

import static modfestorange.aslandium.util.IdentifierUtil.idString;

public class PortalDungeonFeature extends AbstractTempleFeature<DefaultFeatureConfig> {
    public PortalDungeonFeature(Function<Dynamic<?>, ? extends DefaultFeatureConfig> configFactory) {
        super(configFactory);
    }

    @Override
    protected int getSeedModifier() {
        return 0;
    }

    @Override
    public StructureStartFactory getStructureStartFactory() {
        return Start::new;
    }

    @Override
    public String getName() {
        return idString("portal_dungeon");
    }

    @Override
    public int getRadius() {
        return 8;
    }

    public static class Start extends StructureStart {
        public Start(StructureFeature<?> structureFeature, int chunkX, int chunkZ, BlockBox blockBox, int i, long l) {
            super(structureFeature, chunkX, chunkZ, blockBox, i, l);
        }

        public void initialize(ChunkGenerator<?> chunkGenerator, StructureManager structureManager, int x, int z, Biome biome) {
            PortalDungeonGenerator.addPieces(
                chunkGenerator,
                structureManager,
                new BlockPos(x * 16, chunkGenerator.getMaxY(), z * 16),
                this.children,
                this.random
            );
            this.setBoundingBoxFromChildren();
            this.method_14978(chunkGenerator.getMaxY(), this.random, 84);
        }
    }
}
