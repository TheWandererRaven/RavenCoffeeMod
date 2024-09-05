package com.thewandererraven.ravencoffee.screens.handlers;

import com.thewandererraven.ravencoffee.Constants;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class RavenCoffeeScreenHandlers {
    public static ScreenHandlerType<SackScreenHandler> SACK_SCREEN_HANDLER = Registry.register(Registries.SCREEN_HANDLER,
            new Identifier(Constants.MOD_ID, "sack"),
            new ExtendedScreenHandlerType<>(SackScreenHandler::new)
    );
    public static ScreenHandlerType<CoffeeGrinderScreenHandler> COFFEE_GRINDER_SCREEN_HANDLER = Registry.register(Registries.SCREEN_HANDLER,
            new Identifier(Constants.MOD_ID, "coffee_grinder"),
            new ScreenHandlerType(CoffeeGrinderScreenHandler::new, FeatureFlags.VANILLA_FEATURES)
    );
    public static ScreenHandlerType<CoffeeMachineScreenHandler> COFFEE_MACHINE_SCREEN_HANDLER = Registry.register(Registries.SCREEN_HANDLER,
            new Identifier(Constants.MOD_ID, "coffee_machine"),
            new ExtendedScreenHandlerType<>(CoffeeMachineScreenHandler::new)
    );

    public static void registerAllScreenHandlers() {
        Constants.LOGGER.info("Registering Screen Handlers for " + Constants.MOD_ID);
    }
}
