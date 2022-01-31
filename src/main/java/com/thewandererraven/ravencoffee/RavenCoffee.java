package com.thewandererraven.ravencoffee;

import com.thewandererraven.ravencoffee.containers.screen.CoffeeGrinderContainerScreen;
import com.thewandererraven.ravencoffee.util.configuration.ModConfiguration;
import com.thewandererraven.ravencoffee.util.registries.*;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.ModLoadingException;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.moddiscovery.ModFileInfo;
import net.minecraftforge.fml.loading.moddiscovery.ModInfo;
import net.minecraftforge.forgespi.language.IModInfo;
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
        MinecraftForge.EVENT_BUS.addListener(this::doBiomeStuff);
        eventBus.addListener(this::setupClient);
        eventBus.addListener(this::postInit);
        eventBus.addListener(this::registerEntityRenderers);


        // Register the enqueueIMC method for modloading
        //FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        //FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        // Register the doClientStuff method for modloading

        RecipesRegistry.RECIPE_SERIALIZERS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ContainersRegistry.CONTAINERS.register(FMLJavaModLoadingContext.get().getModEventBus());

        TileEntityTypeRegistry.TILE_ENTITY_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());

        BlocksRegistry.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ItemsRegistry.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());

        BrewsRegistry.BREWS.register(FMLJavaModLoadingContext.get().getModEventBus());

        FeaturesRegistry.FEATURES.register(FMLJavaModLoadingContext.get().getModEventBus());

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ModConfiguration.COMMON_SPEC);

        MinecraftForge.EVENT_BUS.register(this);
        LOGGER.info("RAVEN COFFEE FINISHED SETUP!");
    }

    private boolean isRavenBrewsPresent() {
        try {
            Class.forName("com.thewandererraven.ravenbrewscore.Brews");
            return true;
        } catch (Exception e){
            return false;
        }
    }

    private void setup(final FMLCommonSetupEvent event) {
    }

    private void doBiomeStuff(final BiomeLoadingEvent event) {
        if(event.getCategory().equals(Biome.BiomeCategory.JUNGLE) && ModConfiguration.COMMON.COFFEE_TREE_JUNGLE_ENABLED.get())
            event.getGeneration().getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION).add(() -> ConfiguredFeaturesRegistry.PATCH_COFFEE_TREE_JUNGLE);
        if(event.getCategory().equals(Biome.BiomeCategory.SAVANNA) && ModConfiguration.COMMON.COFFEE_TREE_SAVANNAH_ENABLED.get())
            event.getGeneration().getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION).add(() -> ConfiguredFeaturesRegistry.PATCH_COFFEE_TREE_SAVANNAH);
    }

    private void setupClient(final FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(BlocksRegistry.COFFEE_TREE_TRUNK_BLOCK.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlocksRegistry.COFFEE_TREE_LEAVES_BLOCK.get(), RenderType.cutout());
        MenuScreens.register(ContainersRegistry.COFFEE_GRINDER_CONTAINER.get(), CoffeeGrinderContainerScreen::new);
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
            return new ItemStack(ItemsRegistry.COFFEE_BEANS_ROASTED.get());
        }
    };
    public static final CreativeModeTab COFFEE_MUG_TAB = new CreativeModeTab("ravencoffee_mug_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(BrewsRegistry.COFFEE_MUG_BREW_AMERICAN.get());
        }
    };
    public static final CreativeModeTab CUP_SMALL_TAB = new CreativeModeTab("ravencoffee_small_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(BrewsRegistry.CUP_SMALL_BREW_AMERICAN.get());
        }
    };
    public static final CreativeModeTab CUP_MEDIUM_TAB = new CreativeModeTab("ravencoffee_medium_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(BrewsRegistry.CUP_MEDIUM_BREW_AMERICAN.get());
        }
    };
    public static final CreativeModeTab CUP_LARGE_TAB = new CreativeModeTab("ravencoffee_large_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(BrewsRegistry.CUP_LARGE_BREW_AMERICAN.get());
        }
    };
}
