package com.thewandererraven.ravencoffee;

import com.thewandererraven.ravencoffee.blocks.RavenCoffeeBlocks;
import com.thewandererraven.ravencoffee.screens.SackScreen;
import com.thewandererraven.ravencoffee.screens.handlers.RavenCoffeeScreenHandlers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;

public class RavenCoffeeClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HandledScreens.register(RavenCoffeeScreenHandlers.SACK_SCREEN_HANDLER, SackScreen::new);
        BlockRenderLayerMap.INSTANCE.putBlock(RavenCoffeeBlocks.COFFEE_TREE_TRUNK_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(RavenCoffeeBlocks.COFFEE_TREE_LEAVES_BLOCK, RenderLayer.getCutout());
    }
}
