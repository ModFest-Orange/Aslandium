package modfestorange.aslandium;

import modfestorange.aslandium.command.LocateBiomeCommand;
import modfestorange.aslandium.command.TpxCommand;
import modfestorange.aslandium.generation.AslandiumDimensions;
import modfestorange.aslandium.generation.biomes.AslandiumBiomes;
import modfestorange.aslandium.generation.feature.AslandiumFeatures;
import modfestorange.aslandium.generation.structure.AslandiumStructures;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.registry.CommandRegistry;

public class AslandiumMod implements ModInitializer {
    public static final String MOD_ID = "aslandium";

    @Override
    public void onInitialize() {
        AslandiumStructures.register();
        AslandiumFeatures.register();
        AslandiumBiomes.register();
        AslandiumDimensions.register();

        CommandRegistry.INSTANCE.register(false, LocateBiomeCommand::register);
        CommandRegistry.INSTANCE.register(false, TpxCommand::register);
    }
}
