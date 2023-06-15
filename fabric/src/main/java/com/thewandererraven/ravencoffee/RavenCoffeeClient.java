package com.thewandererraven.ravencoffee;

import com.thewandererraven.ravencoffee.screens.SackScreen;
import com.thewandererraven.ravencoffee.screens.handlers.RavenCoffeeScreenHandlers;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class RavenCoffeeClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HandledScreens.register(RavenCoffeeScreenHandlers.SACK_SCREEN_HANDLER, SackScreen::new);
    }
}
