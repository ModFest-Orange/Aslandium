package modfestorange.aslandium.command;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.command.EntitySelector;
import net.minecraft.command.arguments.DimensionArgumentType;
import net.minecraft.command.arguments.EntityArgumentType;
import net.minecraft.entity.Entity;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.TranslatableText;
import net.minecraft.world.dimension.DimensionType;

import java.util.ArrayList;
import java.util.Collection;

public class TpxCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("tpx")
            .requires(source -> source.hasPermissionLevel(2)).then(
                CommandManager.argument("targets", EntityArgumentType.entities())
                    .then(CommandManager
                        .argument("dimension", DimensionArgumentType.dimension())
                        .executes(ctx -> execute(ctx.getSource(),
                            EntityArgumentType.getEntities(ctx, "targets"),
                            DimensionArgumentType
                                .getDimensionArgument(ctx, "dimension"))))));
    }

    private static int execute(ServerCommandSource source,
                               Collection<? extends Entity> targets, DimensionType dimension) {
        for (Entity target : targets) {
            target.changeDimension(dimension);
        }
        source.sendFeedback(new TranslatableText("command.tpx.success",
            EntitySelector.getNames(new ArrayList<>(targets)),
            DimensionType.getId(dimension)), false);
        return 1;
    }
}
