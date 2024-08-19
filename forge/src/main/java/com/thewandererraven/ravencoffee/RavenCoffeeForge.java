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
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;


// The value here should match an entry in the META-INF/mods.toml file
@Mod(Constants.MOD_ID)
public class RavenCoffeeForge
{

    public RavenCoffeeForge() throws Exception {
        if(!isRavenBrewsPresent()) throw new Exception("Raven Brews Core not present!");
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        eventBus.addListener(this::setupClient);
        eventBus.addListener(this::postInit);
        eventBus.addListener(this::addCreative);
        eventBus.addListener(this::commonSetup);

        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, RavenCoffeeClientConfigs.SPEC, "ravencoffee-client.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, RavenCoffeeCommonConfigs.SPEC, "ravencoffee-common.toml");

        RavenCoffeeRecipeTypes.RECIPE_TYPES.register(eventBus);
        RavenCoffeeRecipes.RECIPE_SERIALIZERS.register(eventBus);
        RavenCoffeeMenuTypes.MENUS.register(eventBus);
        RavenCoffeeBlocks.BLOCKS.register(eventBus);
        RavenCoffeeBlockEntities.BLOCK_ENTITIES.register(eventBus);
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
        event.enqueueWork(() -> {
            RavenCoffeeVillagers.registerPOIs();
        });
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

    private void addCreative(CreativeModeTabEvent.BuildContents event) {
        if(event.getTab() == RavenCoffeeCreativeModeTabs.GENERAL_TAB) {
            event.accept(RavenCoffeeItems.COFFEE_CHERRIES);
            event.accept(RavenCoffeeItems.COFFEE_BEANS);
            event.accept(RavenCoffeeItems.COFFEE_BEANS_ROASTED);
            event.accept(RavenCoffeeItems.COFFEE_BEANS_MAGMA);
            event.accept(RavenCoffeeItems.COFFEE_BEANS_ROASTED_GROUND);
            event.accept(RavenCoffeeItems.COFFEE_BEANS_MAGMA_GROUND);
            event.accept(RavenCoffeeItems.POPCHORUS);
            event.accept(RavenCoffeeItems.MUFFIN);
            event.accept(RavenCoffeeItems.MELON_PAN);
            event.accept(RavenCoffeeItems.COFFEE_ECLAIR);
            event.accept(RavenCoffeeItems.BROWNIE);
            event.accept(RavenCoffeeItems.TIRAMISU_SLICE);
            event.accept(RavenCoffeeItems.SANDWICH_HAM);
            event.accept(RavenCoffeeItems.SANDWICH_BEEF);
            event.accept(RavenCoffeeItems.SANDWICH_CHICKEN);
            event.accept(RavenCoffeeItems.CROISSANT);
            event.accept(RavenCoffeeItems.CROISSANT_HAM);
            event.accept(RavenCoffeeItems.CROISSANT_BEEF);
            event.accept(RavenCoffeeItems.CROISSANT_CHICKEN);
            event.accept(RavenCoffeeItems.BAGEL);
            event.accept(RavenCoffeeItems.BAGEL_BEEF);
            event.accept(RavenCoffeeItems.BAGEL_CHICKEN);
            event.accept(RavenCoffeeItems.COFFEE_PLATES);
            event.accept(RavenCoffeeItems.COFFEE_INGOT);
            event.accept(RavenCoffeeItems.COFFEE_PICKAXE);
            event.accept(RavenCoffeeItems.COFFEE_AXE);
            event.accept(RavenCoffeeItems.COFFEE_HOE);
            event.accept(RavenCoffeeItems.COFFEE_SHOVEL);
            event.accept(RavenCoffeeItems.COFFEE_SWORD);
            event.accept(RavenCoffeeItems.BROWNIE_BLOCK_ITEM);
            event.accept(RavenCoffeeItems.ROSCA_DE_REYES_BLOCK_ITEM);
            event.accept(RavenCoffeeItems.TIRAMISU_BLOCK_ITEM);
            event.accept(RavenCoffeeItems.COFFEE_BEANS_ROASTED_BLOCK_ITEM);
            event.accept(RavenCoffeeItems.COFFEE_BEANS_MAGMA_BLOCK_ITEM);
            event.accept(RavenCoffeeItems.COFFEE_GRINDER_ITEM);
            event.accept(RavenCoffeeItems.COFFEE_MACHINE_ITEM);
            event.accept(RavenCoffeeItems.SACK_BLOCK_ITEM);
        }
        if(event.getTab() == RavenCoffeeCreativeModeTabs.CUP_SMALL_TAB) {
            event.accept(RavenCoffeeItems.CUP_SMALL_UNFIRED);
            event.accept(RavenCoffeeItems.CUP_SMALL);
            event.accept(RavenCoffeeBrewItems.CUP_SMALL_BREW_BASIC);
        }
        if(event.getTab() == RavenCoffeeCreativeModeTabs.CUP_MEDIUM_TAB) {
            event.accept(RavenCoffeeItems.CUP_MEDIUM_UNFIRED);
            event.accept(RavenCoffeeItems.CUP_MEDIUM);
            event.accept(RavenCoffeeBrewItems.CUP_MEDIUM_BREW_BASIC);
            event.accept(RavenCoffeeBrewItems.CUP_MEDIUM_BREW_SUGAR);
            event.accept(RavenCoffeeBrewItems.CUP_MEDIUM_BREW_APPLE);
            event.accept(RavenCoffeeBrewItems.CUP_MEDIUM_BREW_BERRY);
            event.accept(RavenCoffeeBrewItems.CUP_MEDIUM_BREW_HONEY);
            event.accept(RavenCoffeeBrewItems.CUP_MEDIUM_BREW_CHOCOLATE);
            event.accept(RavenCoffeeBrewItems.CUP_MEDIUM_BREW_MILK);
            event.accept(RavenCoffeeBrewItems.CUP_MEDIUM_BREW_MOCHA);
            event.accept(RavenCoffeeBrewItems.CUP_MEDIUM_BREW_AWKWARD);
            event.accept(RavenCoffeeBrewItems.CUP_MEDIUM_BREW_CARROT);
            event.accept(RavenCoffeeBrewItems.CUP_MEDIUM_BREW_CARROT_GOLDEN);
            event.accept(RavenCoffeeBrewItems.CUP_MEDIUM_BREW_COOKIESANDCREAM);
            event.accept(RavenCoffeeBrewItems.CUP_MEDIUM_BREW_END);
            event.accept(RavenCoffeeBrewItems.CUP_MEDIUM_BREW_MELON);
            event.accept(RavenCoffeeBrewItems.CUP_MEDIUM_BREW_MELON_GOLDEN);
            event.accept(RavenCoffeeBrewItems.CUP_MEDIUM_BREW_NETHER);
            event.accept(RavenCoffeeBrewItems.CUP_MEDIUM_BREW_PHANTASM);
            event.accept(RavenCoffeeBrewItems.CUP_MEDIUM_BREW_PUMPKINSPICELATTE);
            event.accept(RavenCoffeeBrewItems.CUP_MEDIUM_BREW_PUMPKINSPICELATTE_WITH_PUMPKIN);
        }
        if(event.getTab() == RavenCoffeeCreativeModeTabs.CUP_LARGE_TAB) {
            event.accept(RavenCoffeeItems.CUP_LARGE_UNFIRED);
            event.accept(RavenCoffeeItems.CUP_LARGE);
            event.accept(RavenCoffeeBrewItems.CUP_LARGE_BREW_BASIC);
            event.accept(RavenCoffeeBrewItems.CUP_LARGE_BREW_SUGAR);
            event.accept(RavenCoffeeBrewItems.CUP_LARGE_BREW_APPLE);
            event.accept(RavenCoffeeBrewItems.CUP_LARGE_BREW_BERRY);
            event.accept(RavenCoffeeBrewItems.CUP_LARGE_BREW_HONEY);
            event.accept(RavenCoffeeBrewItems.CUP_LARGE_BREW_CHOCOLATE);
            event.accept(RavenCoffeeBrewItems.CUP_LARGE_BREW_MILK);
            event.accept(RavenCoffeeBrewItems.CUP_LARGE_BREW_MOCHA);
            event.accept(RavenCoffeeBrewItems.CUP_LARGE_BREW_AWKWARD);
            event.accept(RavenCoffeeBrewItems.CUP_LARGE_BREW_CARROT);
            event.accept(RavenCoffeeBrewItems.CUP_LARGE_BREW_CARROT_GOLDEN);
            event.accept(RavenCoffeeBrewItems.CUP_LARGE_BREW_COOKIESANDCREAM);
            event.accept(RavenCoffeeBrewItems.CUP_LARGE_BREW_END);
            event.accept(RavenCoffeeBrewItems.CUP_LARGE_BREW_MELON);
            event.accept(RavenCoffeeBrewItems.CUP_LARGE_BREW_MELON_GOLDEN);
            event.accept(RavenCoffeeBrewItems.CUP_LARGE_BREW_NETHER);
            event.accept(RavenCoffeeBrewItems.CUP_LARGE_BREW_PHANTASM);
            event.accept(RavenCoffeeBrewItems.CUP_LARGE_BREW_PUMPKINSPICELATTE);
            event.accept(RavenCoffeeBrewItems.CUP_LARGE_BREW_PUMPKINSPICELATTE_WITH_PUMPKIN);
        }
        if(event.getTab() == RavenCoffeeCreativeModeTabs.COFFEE_MUG_TAB) {
            event.accept(RavenCoffeeItems.COFFEE_MUG);
            event.accept(RavenCoffeeBrewItems.COFFEE_MUG_BREW_BASIC);
            event.accept(RavenCoffeeBrewItems.COFFEE_MUG_BREW_SUGAR);
            event.accept(RavenCoffeeBrewItems.COFFEE_MUG_BREW_APPLE);
            event.accept(RavenCoffeeBrewItems.COFFEE_MUG_BREW_BERRY);
            event.accept(RavenCoffeeBrewItems.COFFEE_MUG_BREW_HONEY);
            event.accept(RavenCoffeeBrewItems.COFFEE_MUG_BREW_CHOCOLATE);
            event.accept(RavenCoffeeBrewItems.COFFEE_MUG_BREW_MILK);
            event.accept(RavenCoffeeBrewItems.COFFEE_MUG_BREW_MOCHA);
            event.accept(RavenCoffeeBrewItems.COFFEE_MUG_BREW_AWKWARD);
            event.accept(RavenCoffeeBrewItems.COFFEE_MUG_BREW_CARROT);
            event.accept(RavenCoffeeBrewItems.COFFEE_MUG_BREW_CARROT_GOLDEN);
            event.accept(RavenCoffeeBrewItems.COFFEE_MUG_BREW_COOKIESANDCREAM);
            event.accept(RavenCoffeeBrewItems.COFFEE_MUG_BREW_END);
            event.accept(RavenCoffeeBrewItems.COFFEE_MUG_BREW_MELON);
            event.accept(RavenCoffeeBrewItems.COFFEE_MUG_BREW_MELON_GOLDEN);
            event.accept(RavenCoffeeBrewItems.COFFEE_MUG_BREW_NETHER);
            event.accept(RavenCoffeeBrewItems.COFFEE_MUG_BREW_PHANTASM);
            event.accept(RavenCoffeeBrewItems.COFFEE_MUG_BREW_PUMPKINSPICELATTE);
            event.accept(RavenCoffeeBrewItems.COFFEE_MUG_BREW_PUMPKINSPICELATTE_WITH_PUMPKIN);
        }
    }
}
