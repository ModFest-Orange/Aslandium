package modfestorange.aslandium;

import modfestorange.aslandium.command.TpxCommand;
import modfestorange.aslandium.generation.AslandiumDimensions;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.registry.CommandRegistry;

public class AslandiumMod implements ModInitializer {
	public static final String MOD_ID = "aslandium";

	@Override public void onInitialize() {
		AslandiumDimensions.register();

		CommandRegistry.INSTANCE.register(false, TpxCommand::register);
	}
}
