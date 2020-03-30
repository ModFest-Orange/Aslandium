package modfestorange.aslandium.generation.structure;

import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.registry.Registry;

import static modfestorange.aslandium.util.IdentifierUtil.id;

public class AslandiumStructures {
    public static StructurePieceType PORTAL_DUNGEON_PIECE = Registry.register(
        Registry.STRUCTURE_PIECE,
        id("portal_dungeon"),
        PortalDungeonGenerator.Piece::new);

    public static void register() {
    }
}
