package modfestorange.aslandium;

import modfestorange.aslandium.command.TpxCommand;
import modfestorange.aslandium.generation.AslandiumDimensions;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.registry.CommandRegistry;

public class AslandiumMod implements ModInitializer {
	public static final String MOD_ID = "aslandium";

	@Override public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		System.out.println("Hello Fabric world!");

		AslandiumDimensions.register();

		CommandRegistry.INSTANCE.register(false, TpxCommand::register);
	}
}
