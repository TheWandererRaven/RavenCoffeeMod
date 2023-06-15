package com.thewandererraven.ravencoffee.screens.handlers;

import net.minecraft.screen.ScreenHandlerType;

public class RavenCoffeeScreenHandlers {
    public static ScreenHandlerType<SackScreenHandler> SACK_SCREEN_HANDLER;

    public static void registerAllScreenHandlers() {
        SACK_SCREEN_HANDLER = new ScreenHandlerType<>(SackScreenHandler::new);
    }
}