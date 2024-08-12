package com.thewandererraven.ravencoffee.items;

import com.thewandererraven.ravencoffee.Constants;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class RavenCoffeeItemGroups {
    public static final ItemGroup GENERAL_TAB = FabricItemGroup.builder(new Identifier(Constants.MOD_ID,"ravencoffee_general_tab"))
            .icon(() -> new ItemStack(RavenCoffeeItems.COFFEE_BEANS_ROASTED))
            .build();
    public static final ItemGroup COFFEE_MUG_TAB = FabricItemGroup.builder(new Identifier(Constants.MOD_ID,"ravencoffee_mug_tab"))
            .icon(() -> new ItemStack(RavenCoffeeItems.COFFEE_MUG))
            .build();
    public static final ItemGroup CUP_SMALL_TAB = FabricItemGroup.builder(new Identifier(Constants.MOD_ID,"ravencoffee_small_tab"))
            .icon(() -> new ItemStack(RavenCoffeeItems.CUP_SMALL))
            .build();
    public static final ItemGroup CUP_MEDIUM_TAB = FabricItemGroup.builder(new Identifier(Constants.MOD_ID,"ravencoffee_medium_tab"))
            .icon(() -> new ItemStack(RavenCoffeeItems.CUP_MEDIUM))
            .build();
    public static final ItemGroup CUP_LARGE_TAB = FabricItemGroup.builder(new Identifier(Constants.MOD_ID,"ravencoffee_large_tab"))
            .icon(() -> new ItemStack(RavenCoffeeItems.CUP_LARGE))
            .build();

    public static void registerItemGroups() {
        Constants.LOGGER.info("Registering Item Groups for " + Constants.MOD_NAME);
    }
}
