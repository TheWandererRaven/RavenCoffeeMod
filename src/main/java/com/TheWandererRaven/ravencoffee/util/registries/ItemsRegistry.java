package com.TheWandererRaven.ravencoffee.util.registries;

import com.TheWandererRaven.ravencoffee.RavenCoffee;
import com.TheWandererRaven.ravencoffee.items.ItemBase;
import com.TheWandererRaven.ravencoffee.tools.RavenCoffeeItemTier;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemsRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(
            ForgeRegistries.ITEMS,
            RavenCoffee.MOD_ID);

    // ############################################### BASIC COFFEE STUFF ##############################################
    public static final RegistryObject<Item> COFFEE_CHERRIES = ITEMS.register(
            "coffee_cherries",
            ItemBase::new
    );
    public static final RegistryObject<Item> COFFEE_BEANS = ITEMS.register(
            "coffee_beans",
            ItemBase::new
    );
    public static final RegistryObject<Item> COFFEE_BEANS_ROASTED = ITEMS.register(
            "coffee_beans_roasted",
            ItemBase::new
    );
    public static final RegistryObject<Item> COFFEE_BEANS_ROASTED_MAGMA = ITEMS.register(
            "coffee_beans_roasted_magma",
            ItemBase::new
    );

    // ############################################### COFFEE INGREDIENTS ##############################################
    public static final RegistryObject<Item> COFFEE_BEANS_ROASTED_GROUND = ITEMS.register(
            "coffee_beans_roasted_ground",
            ItemBase::new
    );

    // ###################################################### CUPS #####################################################
    public static final RegistryObject<Item> CUP_SMALL_UNFIRED = ITEMS.register(
            "cup_small_unfired",
            ItemBase::new
    );
    public static final RegistryObject<Item> CUP_SMALL = ITEMS.register(
            "cup_small",
            ItemBase::new
    );
    public static final RegistryObject<Item> CUP_MEDIUM_UNFIRED = ITEMS.register(
            "cup_medium_unfired",
            ItemBase::new
    );
    public static final RegistryObject<Item> CUP_MEDIUM = ITEMS.register(
            "cup_medium",
            ItemBase::new
    );
    public static final RegistryObject<Item> CUP_LARGE_UNFIRED = ITEMS.register(
            "cup_large_unfired",
            ItemBase::new
    );
    public static final RegistryObject<Item> CUP_LARGE = ITEMS.register(
            "cup_large",
            ItemBase::new
    );
    public static final RegistryObject<Item> COFFEE_MUG = ITEMS.register(
            "coffee_mug",
            ItemBase::new
    );

    // ############################################### COFFEE MATERIALS ################################################
    public static final RegistryObject<Item> COFFEE_PLATES = ITEMS.register(
            "coffee_plates",
            ItemBase::new
    );
    public static final RegistryObject<Item> COFFEE_INGOT = ITEMS.register(
            "coffee_ingot",
            ItemBase::new
    );

    // ################################################# COFFEE TOOLS ##################################################
    //                               Material Tier || Extra Attack Points || Attack Speed = 4 + n || Creative tab
    public static final RegistryObject<Item> COFFEE_PICKAXE = ITEMS.register(
            "coffee_pickaxe",
            () -> new PickaxeItem(RavenCoffeeItemTier.COFFEE, 3, -2.8f, new Item.Properties().group(RavenCoffee.TAB))
    );
    public static final RegistryObject<Item> COFFEE_AXE =  ITEMS.register(
            "coffee_axe",
            () -> new AxeItem(RavenCoffeeItemTier.COFFEE, 8, -2.7f, new Item.Properties().group(RavenCoffee.TAB))
    );
    public static final RegistryObject<Item> COFFEE_HOE =  ITEMS.register(
            "coffee_hoe",
            () -> new HoeItem(RavenCoffeeItemTier.COFFEE, 0, 1.0f, new Item.Properties().group(RavenCoffee.TAB))
    );
    public static final RegistryObject<Item> COFFEE_SHOVEL =  ITEMS.register(
            "coffee_shovel",
            () -> new ShovelItem(RavenCoffeeItemTier.COFFEE, 4, -3.0f, new Item.Properties().group(RavenCoffee.TAB))
    );
    public static final RegistryObject<Item> COFFEE_SWORD =  ITEMS.register(
            "coffee_sword",
            () -> new SwordItem(RavenCoffeeItemTier.COFFEE, 5, -2.0f, new Item.Properties().group(RavenCoffee.TAB))
    );

    // ############################################### COFFEE BLOCK ITEMS ################################################
    public static final RegistryObject<Item> COFFEE_BEANS_ROASTED_BLOCK_ITEM = ITEMS.register(
            "coffee_beans_roasted_block",
            () -> new BlockItem(BlocksRegistry.COFFEE_BEANS_ROASTED_BLOCK.get(), new Item.Properties().group(RavenCoffee.TAB))
    );
    public static final RegistryObject<Item> COFFEE_BEANS_ROASTED_MAGMA_BLOCK_ITEM = ITEMS.register(
            "coffee_beans_roasted_magma_block",
            () -> new BlockItem(BlocksRegistry.COFFEE_BEANS_ROASTED_MAGMA_BLOCK.get(), new Item.Properties().group(RavenCoffee.TAB))
    );
}
