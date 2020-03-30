package modfestorange.aslandium.generation.structure;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import modfestorange.aslandium.generation.structure.pool.ExtendedSinglePoolElement;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.structure.PoolStructurePiece;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructurePiece;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolBasedGenerator;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.chunk.ChunkGenerator;

import java.util.List;

import static modfestorange.aslandium.util.IdentifierUtil.id;

public class PortalDungeonGenerator {
    static {
        pool(
            id("portal_dungeon/start_island"),
            ImmutableList.of(
                element(id("portal_dungeon/start_island"), 1)
            )
        );

        pool(
            id("portal_dungeon/hall"),
            ImmutableList.of(
                element(id("portal_dungeon/vertical_hall"), 1),
                element(id("portal_dungeon/x_hall"), 1),
                element(id("portal_dungeon/t_hall"), 1),
                element(id("portal_dungeon/roofed_x_hall"), 1),
                element(id("portal_dungeon/roofed_t_hall"), 1)
            )
        );


        pool(
            id("portal_dungeon/bridge"),
            ImmutableList.of(
                element(id("portal_dungeon/bridge"), 1),
                element(id("portal_dungeon/bridge_stair"), 1)
            )
        );

        pool(
            id("portal_dungeon/premise"),
            ImmutableList.of(
                element(id("portal_dungeon/room_exterior"), 4),
                element(id("portal_dungeon/x_hall"), 1),
                element(id("portal_dungeon/t_hall"), 1),
                element(id("portal_dungeon/roofed_x_hall"), 1),
                element(id("portal_dungeon/roofed_t_hall"), 1)
            )
        );

        pool(
            id("portal_dungeon/island"),
            ImmutableList.of(
                element(id("portal_dungeon/start_island"), 1)
            )
        );

        pool(
            id("portal_dungeon/room"),
            ImmutableList.of(
                element(id("portal_dungeon/room/arboretum"), 1),
                element(id("portal_dungeon/room/auditorium"), 1),
                element(id("portal_dungeon/room/boss"), 8),
                element(id("portal_dungeon/room/contamination"), 1),
                element(id("portal_dungeon/room/garden"), 1),
                element(id("portal_dungeon/room/library"), 1),
                element(id("portal_dungeon/room/loot_display"), 1),
                element(id("portal_dungeon/room/portal"), 3),
                element(id("portal_dungeon/room/redstone"), 1),
                element(id("portal_dungeon/room/smithy"), 1),
                element(id("portal_dungeon/room/spawner"), 1),
                element(id("portal_dungeon/room/treasury"), 1)
            )
        );
    }

    public static void addPieces(ChunkGenerator<?> chunkGenerator, StructureManager structureManager, BlockPos pos, List<StructurePiece> pieces, ChunkRandom random) {
        StructurePoolBasedGenerator.addPieces(
            id("portal_dungeon/start_island"),
            8,
            Piece::new,
            chunkGenerator,
            structureManager,
            pos,
            pieces,
            random
        );
    }

    private static Pair<StructurePoolElement, Integer> element(Identifier id, Integer weight) {
        return Pair.of(new ExtendedSinglePoolElement(id, ImmutableList.of()), weight);
    }

    private static void pool(Identifier id, List<Pair<StructurePoolElement, Integer>> elements) {
        StructurePoolBasedGenerator.REGISTRY.add(new StructurePool(id, new Identifier("empty"), elements, StructurePool.Projection.RIGID));
    }

    private static void pool(Identifier id, Identifier terminatorId, List<Pair<StructurePoolElement, Integer>> elements) {
        StructurePoolBasedGenerator.REGISTRY.add(new StructurePool(id, terminatorId, elements, StructurePool.Projection.RIGID));
    }

    public static class Piece extends PoolStructurePiece {
        public Piece(StructureManager manager, StructurePoolElement element, BlockPos pos, int groundLevelDelta, BlockRotation rotation, BlockBox boundingBox) {
            super(AslandiumStructures.PORTAL_DUNGEON_PIECE, manager, element, pos, groundLevelDelta, rotation, boundingBox);
        }

        public Piece(StructureManager manager, CompoundTag tag) {
            super(manager, tag, AslandiumStructures.PORTAL_DUNGEON_PIECE);
        }
    }
}
