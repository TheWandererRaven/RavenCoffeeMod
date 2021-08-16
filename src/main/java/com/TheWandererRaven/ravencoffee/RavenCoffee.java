package com.TheWandererRaven.ravencoffee;

import com.TheWandererRaven.ravencoffee.containers.screen.CoffeeGrinderContainerScreen;
import com.TheWandererRaven.ravencoffee.util.registries.*;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
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
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "ravencoffee";

    public RavenCoffee() {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        //FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        //FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::postInit);

        //RecipesRegistry.RECIPE_SERIALIZERS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ContainersRegistry.CONTAINERS.register(FMLJavaModLoadingContext.get().getModEventBus());

        BlocksRegistry.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        TileEntityTypeRegistry.TILE_ENTITY_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());

        ItemsRegistry.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        BrewsRegistry.BREWS.register(FMLJavaModLoadingContext.get().getModEventBus());

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {

    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        ScreenManager.registerFactory(ContainersRegistry.COFFEE_GRINDER_CONTAINER.get(), CoffeeGrinderContainerScreen::new);
    }

    private void postInit(FMLLoadCompleteEvent event) {
        // Register new behaviour for dispensing certain items
        IDispenserBehaviourRegistry.registerBehaviours();
    }

    public static final ItemGroup GENERAL_TAB = new ItemGroup("ravencoffee_general_Tab") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ItemsRegistry.COFFEE_BEANS_ROASTED.get());
        }
        @Override
        public ITextComponent getGroupName() {
            return ITextComponent.getTextComponentOrEmpty("Coffee Items");
        }
    };
    public static final ItemGroup COFFEE_MUG_TAB = new ItemGroup("ravencoffee_mug_Tab") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(BrewsRegistry.COFFEE_MUG_BREW_AMERICAN.get());
        }
        @Override
        public ITextComponent getGroupName() {
            return ITextComponent.getTextComponentOrEmpty("Coffee Mugs");
        }
    };
    public static final ItemGroup CUP_SMALL_TAB = new ItemGroup("ravencoffee_small_Tab") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(BrewsRegistry.CUP_SMALL_BREW_AMERICAN.get());
        }
        @Override
        public ITextComponent getGroupName() {
            return ITextComponent.getTextComponentOrEmpty("Small Coffee Cups");
        }
    };
    public static final ItemGroup CUP_MEDIUM_TAB = new ItemGroup("ravencoffee_medium_Tab") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(BrewsRegistry.CUP_MEDIUM_BREW_AMERICAN.get());
        }
        @Override
        public ITextComponent getGroupName() {
            return ITextComponent.getTextComponentOrEmpty("Medium Coffee Cups");
        }
    };
    public static final ItemGroup CUP_LARGE_TAB = new ItemGroup("ravencoffee_large_Tab") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(BrewsRegistry.CUP_LARGE_BREW_AMERICAN.get());
        }
        @Override
        public ITextComponent getGroupName() {
            return ITextComponent.getTextComponentOrEmpty("Large Coffee Cups");
        }
    };
}
