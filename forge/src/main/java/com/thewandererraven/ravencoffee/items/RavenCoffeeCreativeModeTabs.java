package com.thewandererraven.ravencoffee.items;

import com.thewandererraven.ravencoffee.Constants;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Constants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RavenCoffeeCreativeModeTabs {

    public static CreativeModeTab GENERAL_TAB;
    public static CreativeModeTab COFFEE_MUG_TAB;
    public static CreativeModeTab CUP_SMALL_TAB;
    public static CreativeModeTab CUP_MEDIUM_TAB;
    public static CreativeModeTab CUP_LARGE_TAB;

    @SubscribeEvent
    public static void registerCreativeModeTabs(CreativeModeTabEvent.Register event) {
        ResourceLocation general_tab_id = new ResourceLocation(Constants.MOD_ID, "general_tab");
        GENERAL_TAB = event.registerCreativeModeTab(general_tab_id,
                builder -> builder
                        .icon(() -> new ItemStack(RavenCoffeeItems.COFFEE_BEANS_ROASTED.get()))
                .title(Component.translatable("itemGroup.".concat(general_tab_id.toLanguageKey())))
        );
        ResourceLocation mug_tab_id = new ResourceLocation(Constants.MOD_ID, "mug_tab");
        COFFEE_MUG_TAB = event.registerCreativeModeTab(mug_tab_id,
                builder -> builder
                        .icon(() -> new ItemStack(RavenCoffeeBrewItems.COFFEE_MUG_BREW_BASIC.get()))
                .title(Component.translatable("itemGroup.".concat(mug_tab_id.toLanguageKey())))
        );
        ResourceLocation small_tab_id = new ResourceLocation(Constants.MOD_ID, "small_tab");
        CUP_SMALL_TAB = event.registerCreativeModeTab(small_tab_id,
                builder -> builder
                        .icon(() -> new ItemStack(RavenCoffeeBrewItems.CUP_SMALL_BREW_BASIC.get()))
                .title(Component.translatable("itemGroup.".concat(small_tab_id.toLanguageKey())))
        );
        ResourceLocation medium_tab_id = new ResourceLocation(Constants.MOD_ID, "medium_tab");
        CUP_MEDIUM_TAB = event.registerCreativeModeTab(medium_tab_id,
                builder -> builder
                        .icon(() -> new ItemStack(RavenCoffeeBrewItems.CUP_MEDIUM_BREW_BASIC.get()))
                .title(Component.translatable("itemGroup.".concat(medium_tab_id.toLanguageKey())))
        );
        ResourceLocation large_tab_id = new ResourceLocation(Constants.MOD_ID, "large_tab");
        CUP_LARGE_TAB = event.registerCreativeModeTab(large_tab_id,
                builder -> builder
                        .icon(() -> new ItemStack(RavenCoffeeBrewItems.CUP_LARGE_BREW_BASIC.get()))
                .title(Component.translatable("itemGroup.".concat(large_tab_id.toLanguageKey())))
        );
    }
}
