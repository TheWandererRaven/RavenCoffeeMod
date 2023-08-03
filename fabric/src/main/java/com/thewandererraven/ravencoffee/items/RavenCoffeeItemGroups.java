package com.thewandererraven.ravencoffee.items;

import com.thewandererraven.ravencoffee.Constants;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class RavenCoffeeItemGroups {
    public static final ItemGroup GENERAL_TAB = FabricItemGroup.builder()
            .displayName(Text.translatable("ravencoffee_general_tab"))
            .icon(() -> new ItemStack(RavenCoffeeItems.COFFEE_BEANS_ROASTED))
            .build();
    public static final ItemGroup COFFEE_MUG_TAB = FabricItemGroup.builder()
            .displayName(Text.translatable("ravencoffee_mug_tab"))
            .icon(() -> new ItemStack(RavenCoffeeItems.COFFEE_MUG))
            .build();
    public static final ItemGroup CUP_SMALL_TAB = FabricItemGroup.builder()
            .displayName(Text.translatable("ravencoffee_small_tab"))
            .icon(() -> new ItemStack(RavenCoffeeItems.CUP_SMALL))
            .build();
    public static final ItemGroup CUP_MEDIUM_TAB = FabricItemGroup.builder()
            .displayName(Text.translatable("ravencoffee_medium_tab"))
            .icon(() -> new ItemStack(RavenCoffeeItems.CUP_MEDIUM))
            .build();
    public static final ItemGroup CUP_LARGE_TAB = FabricItemGroup.builder()
            .displayName(Text.translatable("ravencoffee_large_tab"))
            .icon(() -> new ItemStack(RavenCoffeeItems.CUP_LARGE))
            .build();
}
