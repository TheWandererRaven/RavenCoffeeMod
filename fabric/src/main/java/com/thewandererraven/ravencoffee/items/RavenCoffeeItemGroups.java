package com.thewandererraven.ravencoffee.items;

import com.thewandererraven.ravencoffee.Constants;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class RavenCoffeeItemGroups {
    public static Identifier general_tab_id = new Identifier(Constants.MOD_ID, "general_tab");
    public static final RegistryKey<ItemGroup> GENERAL_TAB = RegistryKey.of(
            RegistryKeys.ITEM_GROUP,
            new Identifier(Constants.MOD_ID,"general_tab")
    );

    public static Identifier mug_tab_id = new Identifier(Constants.MOD_ID, "mug_tab");
    public static final RegistryKey<ItemGroup> COFFEE_MUG_TAB = RegistryKey.of(
            RegistryKeys.ITEM_GROUP,
            new Identifier(Constants.MOD_ID,"mug_tab")
    );

    public static Identifier small_tab_id = new Identifier(Constants.MOD_ID, "small_tab");
    public static final RegistryKey<ItemGroup> CUP_SMALL_TAB = RegistryKey.of(
            RegistryKeys.ITEM_GROUP,
            new Identifier(Constants.MOD_ID,"small_tab")
    );

    public static Identifier medium_tab_id = new Identifier(Constants.MOD_ID, "medium_tab");
    public static final RegistryKey<ItemGroup> CUP_MEDIUM_TAB = RegistryKey.of(
            RegistryKeys.ITEM_GROUP,
            new Identifier(Constants.MOD_ID,"medium_tab")
    );

    public static Identifier large_tab_id = new Identifier(Constants.MOD_ID, "large_tab");
    public static final RegistryKey<ItemGroup> CUP_LARGE_TAB = RegistryKey.of(
            RegistryKeys.ITEM_GROUP,
            new Identifier(Constants.MOD_ID,"large_tab")
    );

    public static void registerItemGroups() {
        Constants.LOGGER.info("Registering Item Groups for " + Constants.MOD_NAME);
        Registry.register(Registries.ITEM_GROUP, GENERAL_TAB, FabricItemGroup.builder()
                .icon(() -> new ItemStack(RavenCoffeeItems.COFFEE_BEANS_ROASTED))
                .displayName(Text.translatable(general_tab_id.toTranslationKey()))
                .build()
        );
        Registry.register(Registries.ITEM_GROUP, COFFEE_MUG_TAB, FabricItemGroup.builder()
                .icon(() -> new ItemStack(RavenCoffeeItems.COFFEE_MUG))
                .displayName(Text.translatable(mug_tab_id.toTranslationKey()))
                .build()
        );
        Registry.register(Registries.ITEM_GROUP, CUP_SMALL_TAB, FabricItemGroup.builder()
                .icon(() -> new ItemStack(RavenCoffeeItems.CUP_SMALL))
                .displayName(Text.translatable(small_tab_id.toTranslationKey()))
                .build()
        );
        Registry.register(Registries.ITEM_GROUP, CUP_MEDIUM_TAB, FabricItemGroup.builder()
                .icon(() -> new ItemStack(RavenCoffeeItems.CUP_MEDIUM))
                .displayName(Text.translatable(medium_tab_id.toTranslationKey()))
                .build()
        );
        Registry.register(Registries.ITEM_GROUP, CUP_LARGE_TAB, FabricItemGroup.builder()
                .icon(() -> new ItemStack(RavenCoffeeItems.CUP_LARGE))
                .displayName(Text.translatable(large_tab_id.toTranslationKey()))
                .build()
        );
    }
}
