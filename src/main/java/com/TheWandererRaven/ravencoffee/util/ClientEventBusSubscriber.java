package com.TheWandererRaven.ravencoffee.util;

import com.TheWandererRaven.ravencoffee.RavenCoffee;
import com.TheWandererRaven.ravencoffee.util.registries.BlocksRegistry;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = RavenCoffee.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        // Add transparency to block
        RenderTypeLookup.setRenderLayer(BlocksRegistry.COFFEE_TREE_TRUNK_BLOCK.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlocksRegistry.COFFEE_TREE_LEAVES_BLOCK.get(), RenderType.getCutout());
    }
}
