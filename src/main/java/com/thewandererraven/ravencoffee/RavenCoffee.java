package com.thewandererraven.ravencoffee;

import com.thewandererraven.ravencoffee.blocks.RavenCoffeeBlocks;
import com.thewandererraven.ravencoffee.containers.RavenCoffeeMenuTypes;
import com.thewandererraven.ravencoffee.containers.screen.CoffeeGrinderContainerScreen;
import com.thewandererraven.ravencoffee.items.RavenCoffeeBrewItems;
import com.thewandererraven.ravencoffee.items.RavenCoffeeItems;
import com.thewandererraven.ravencoffee.recipes.RavenCoffeeRecipeTypes;
import com.thewandererraven.ravencoffee.recipes.RavenCoffeeRecipes;
import com.thewandererraven.ravencoffee.util.configuration.ModConfiguration;
import com.thewandererraven.ravencoffee.util.registries.*;
import com.thewandererraven.ravencoffee.world.features.RavenCoffeeConfiguredFeatures;
import com.thewandererraven.ravencoffee.world.features.RavenCoffeeFeatures;
import com.thewandererraven.ravencoffee.world.features.RavenCoffeePlacedFeatures;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


// The value here should match an entry in the META-INF/mods.toml file
@Mod("ravencoffee")
public class RavenCoffee
{
    // Directly reference a log4j logger.
    public static final String MOD_ID = "ravencoffee";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public RavenCoffee() throws Exception {
        if(!isRavenBrewsPresent()) throw new Exception("Raven Brews Core not present!");
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();


        eventBus.addListener(this::setup);
        //MinecraftForge.EVENT_BUS.addListener(this::doBiomeStuff);
        eventBus.addListener(this::setupClient);
        eventBus.addListener(this::postInit);
        eventBus.addListener(this::registerEntityRenderers);

        RavenCoffeeRecipeTypes.RECIPE_TYPES.register(eventBus);
        RavenCoffeeRecipes.RECIPE_SERIALIZERS.register(eventBus);
        RavenCoffeeMenuTypes.CONTAINERS.register(eventBus);
        TileEntityTypeRegistry.TILE_ENTITY_TYPES.register(eventBus);
        RavenCoffeeBlocks.BLOCKS.register(eventBus);
        RavenCoffeeItems.ITEMS.register(eventBus);
        RavenCoffeeBrewItems.BREWS.register(eventBus);
        RavenCoffeeFeatures.FEATURES.register(eventBus);
        //ConfiguredFeaturesRegistry.CONFIGURED_FEATURES.register(eventBus);
        //PlacedFeaturesRegistry.PLACED_FEATURES.register(eventBus);
        RavenCoffeeConfiguredFeatures.CONFIGURED_FEATURES.register(eventBus);
        RavenCoffeePlacedFeatures.PLACED_FEATURES.register(eventBus);
        //ConfiguredFeaturesRegistry.register();
        //PlacedFeaturesRegistry.register();


        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ModConfiguration.COMMON_SPEC);

        MinecraftForge.EVENT_BUS.register(this);
        LOGGER.info("RAVEN COFFEE FINISHED SETUP!");
    }

    private boolean isRavenBrewsPresent() {
        try {
            Class.forName("com.thewandererraven.ravenbrewscore.Brew");
            return true;
        } catch (Exception e){
            return false;
        }
    }

    private void setup(final FMLCommonSetupEvent event) {
    }

    /*
    private void doBiomeStuff(final BiomeLoadingEvent event) {
        if(event.getCategory().equals(Biome.BiomeCategory.JUNGLE) && ModConfiguration.COMMON.COFFEE_TREE_JUNGLE_ENABLED.get())
            //event.getGeneration().getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION).add(() -> PlacedFeaturesRegistry.PATCH_COFFEE_TREE_JUNGLE);
            event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeaturesRegistry.PATCH_COFFEE_TREE_JUNGLE);
        if(event.getCategory().equals(Biome.BiomeCategory.SAVANNA) && ModConfiguration.COMMON.COFFEE_TREE_SAVANNAH_ENABLED.get())
            //event.getGeneration().getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION).add(() -> PlacedFeaturesRegistry.PATCH_COFFEE_TREE_SAVANNAH);
            event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeaturesRegistry.PATCH_COFFEE_TREE_SAVANNAH);
    }
     */

    private void setupClient(final FMLClientSetupEvent event) {
        //ItemBlockRenderTypes.setRenderLayer(BlocksRegistry.COFFEE_TREE_TRUNK_BLOCK.get(), RenderType.cutout());
        //ItemBlockRenderTypes.setRenderLayer(BlocksRegistry.COFFEE_TREE_LEAVES_BLOCK.get(), RenderType.cutout());
        MenuScreens.register(RavenCoffeeMenuTypes.COFFEE_GRINDER_CONTAINER.get(), CoffeeGrinderContainerScreen::new);
    }

    //@SubscribeEvent
    public void registerEntityRenderers(final EntityRenderersEvent.RegisterRenderers event) {
        // pre 1.17.1
        //event.registerBlockEntityRenderer(BlocksRegistry.COFFEE_TREE_TRUNK_BLOCK.get(), RenderType.cutout());
        //event.registerBlockEntityRenderer(BlocksRegistry.COFFEE_TREE_LEAVES_BLOCK.get(), RenderType.cutout());
    }

    private void postInit(FMLLoadCompleteEvent event) {
        // Register new behaviour for dispensing certain items
        IDispenserBehaviourRegistry.registerBehaviours();
    }

    public static final CreativeModeTab GENERAL_TAB = new CreativeModeTab("ravencoffee_general_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(RavenCoffeeItems.COFFEE_BEANS_ROASTED.get());
        }
    };
    public static final CreativeModeTab COFFEE_MUG_TAB = new CreativeModeTab("ravencoffee_mug_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(RavenCoffeeBrewItems.COFFEE_MUG_BREW_BASIC.get());
        }
    };
    public static final CreativeModeTab CUP_SMALL_TAB = new CreativeModeTab("ravencoffee_small_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(RavenCoffeeBrewItems.CUP_SMALL_BREW_BASIC.get());
        }
    };
    public static final CreativeModeTab CUP_MEDIUM_TAB = new CreativeModeTab("ravencoffee_medium_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(RavenCoffeeBrewItems.CUP_MEDIUM_BREW_BASIC.get());
        }
    };
    public static final CreativeModeTab CUP_LARGE_TAB = new CreativeModeTab("ravencoffee_large_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(RavenCoffeeBrewItems.CUP_LARGE_BREW_BASIC.get());
        }
    };
}
