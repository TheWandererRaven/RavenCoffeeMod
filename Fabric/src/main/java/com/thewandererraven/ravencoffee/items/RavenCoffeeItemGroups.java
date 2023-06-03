package com.thewandererraven.ravencoffee.items;

import com.thewandererraven.ravencoffee.Constants;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class RavenCoffeeItemGroups {
    public static final ItemGroup GENERAL_TAB = FabricItemGroupBuilder.build(new Identifier(Constants.MOD_ID, "ravencoffee_general_tab"), () -> new ItemStack(RavenCoffeeItems.COFFEE_BEANS_ROASTED));
    public static final ItemGroup COFFEE_MUG_TAB = FabricItemGroupBuilder.build(new Identifier(Constants.MOD_ID, "ravencoffee_mug_tab"), () -> new ItemStack(RavenCoffeeItems.COFFEE_ECLAIR));
    public static final ItemGroup CUP_SMALL_TAB = FabricItemGroupBuilder.build(new Identifier(Constants.MOD_ID, "ravencoffee_small_tab"), () -> new ItemStack(RavenCoffeeItems.CUP_SMALL_UNFIRED));
    public static final ItemGroup CUP_MEDIUM_TAB = FabricItemGroupBuilder.build(new Identifier(Constants.MOD_ID, "ravencoffee_medium_tab"), () -> new ItemStack(RavenCoffeeItems.CUP_MEDIUM_UNFIRED));
    public static final ItemGroup CUP_LARGE_TAB = FabricItemGroupBuilder.build(new Identifier(Constants.MOD_ID, "ravencoffee_large_tab"), () -> new ItemStack(RavenCoffeeItems.CUP_LARGE_UNFIRED));
}
