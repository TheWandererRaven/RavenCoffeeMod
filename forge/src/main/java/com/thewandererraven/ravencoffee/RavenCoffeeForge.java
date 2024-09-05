package com.thewandererraven.ravencoffee;

import com.thewandererraven.ravencoffee.blocks.RavenCoffeeBlocks;
import com.thewandererraven.ravencoffee.blocks.entities.RavenCoffeeBlockEntities;
import com.thewandererraven.ravencoffee.containers.RavenCoffeeMenuTypes;
import com.thewandererraven.ravencoffee.containers.screen.CoffeeGrinderContainerScreen;
import com.thewandererraven.ravencoffee.containers.screen.SackScreen;
import com.thewandererraven.ravencoffee.items.RavenCoffeeBrewItems;
import com.thewandererraven.ravencoffee.items.RavenCoffeeCreativeModeTabs;
import com.thewandererraven.ravencoffee.items.RavenCoffeeItems;
import com.thewandererraven.ravencoffee.recipes.RavenCoffeeRecipeTypes;
import com.thewandererraven.ravencoffee.recipes.RavenCoffeeRecipes;
import com.thewandererraven.ravencoffee.containers.screen.CoffeeMachineScreen;
import com.thewandererraven.ravencoffee.util.configuration.RavenCoffeeClientConfigs;
import com.thewandererraven.ravencoffee.util.configuration.RavenCoffeeCommonConfigs;
import com.thewandererraven.ravencoffee.util.registries.*;
import com.thewandererraven.ravencoffee.villager.RavenCoffeeVillagers;
import com.thewandererraven.ravencoffee.world.features.RavenCoffeeFeatures;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Constants.MOD_ID)
public class RavenCoffeeForge
{

    public RavenCoffeeForge() throws Exception {
        if(!isRavenBrewsPresent()) throw new Exception("Raven Brews Core not present!");
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        eventBus.addListener(this::setupClient);
        eventBus.addListener(this::postInit);
        eventBus.addListener(this::commonSetup);

        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, RavenCoffeeClientConfigs.SPEC, "ravencoffee-client.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, RavenCoffeeCommonConfigs.SPEC, "ravencoffee-common.toml");

        RavenCoffeeRecipeTypes.RECIPE_TYPES.register(eventBus);
        RavenCoffeeRecipes.RECIPE_SERIALIZERS.register(eventBus);
        RavenCoffeeMenuTypes.MENUS.register(eventBus);
        RavenCoffeeBlocks.BLOCKS.register(eventBus);
        RavenCoffeeBlockEntities.BLOCK_ENTITIES.register(eventBus);
        RavenCoffeeCreativeModeTabs.register(eventBus);
        RavenCoffeeItems.ITEMS.register(eventBus);
        RavenCoffeeBrewItems.BREWS.register(eventBus);
        RavenCoffeeVillagers.POI_TYPES.register(eventBus);
        RavenCoffeeVillagers.VILLAGER_PROFESSIONS.register(eventBus);
        RavenCoffeeFeatures.FEATURES.register(eventBus);

        MinecraftForge.EVENT_BUS.register(this);
        Constants.LOGGER.info("RAVEN COFFEE FINISHED SETUP!");
    }

    private boolean isRavenBrewsPresent() {
        try {
            Class.forName("com.thewandererraven.ravenbrewscore.Brew");
            return true;
        } catch (Exception e){
            return false;
        }
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        /*
        // Pre 1.20
        event.enqueueWork(() -> {
            RavenCoffeeVillagers.registerPOIs();
        });
         */
    }

    private void setupClient(final FMLClientSetupEvent event) {
        MenuScreens.register(RavenCoffeeMenuTypes.COFFEE_GRINDER_MENU.get(), CoffeeGrinderContainerScreen::new);
        MenuScreens.register(RavenCoffeeMenuTypes.COFFEE_MACHINE_MENU.get(), CoffeeMachineScreen::new);
        MenuScreens.register(RavenCoffeeMenuTypes.SACK_MENU.get(), SackScreen::new);
    }

    private void postInit(FMLLoadCompleteEvent event) {
        // Register new behaviour for dispensing certain items
        IDispenserBehaviourRegistry.registerBehaviours();
    }
}
