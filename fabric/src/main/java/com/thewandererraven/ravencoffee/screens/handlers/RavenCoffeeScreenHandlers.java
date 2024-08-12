package com.thewandererraven.ravencoffee.screens.handlers;

import com.thewandererraven.ravencoffee.Constants;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class RavenCoffeeScreenHandlers {
    public static ScreenHandlerType<SackScreenHandler> SACK_SCREEN_HANDLER = Registry.register(Registries.SCREEN_HANDLER,
            new Identifier(Constants.MOD_ID, "sack"),
            new ScreenHandlerType<>(SackScreenHandler::new)
    );
    public static ScreenHandlerType<CoffeeGrinderScreenHandler> COFFEE_GRINDER_SCREEN_HANDLER = Registry.register(Registries.SCREEN_HANDLER,
            new Identifier(Constants.MOD_ID, "coffee_grinder"),
            new ScreenHandlerType<>(CoffeeGrinderScreenHandler::new)
    );
    public static ScreenHandlerType<CoffeeMachineScreenHandler> COFFEE_MACHINE_SCREEN_HANDLER = Registry.register(Registries.SCREEN_HANDLER,
            new Identifier(Constants.MOD_ID, "coffee_machine"),
            new ScreenHandlerType<>(CoffeeMachineScreenHandler::new)
    );

    public static void registerAllScreenHandlers() {
        Constants.LOGGER.info("Registering Screen Handlers for " + Constants.MOD_ID);
    }
}
