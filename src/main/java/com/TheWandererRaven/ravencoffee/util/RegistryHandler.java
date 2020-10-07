package com.TheWandererRaven.ravencoffee.util;

import com.TheWandererRaven.ravencoffee.RavenCoffee;
import com.TheWandererRaven.ravencoffee.blocks.CoffeeBeansRoastedBlock;
import com.TheWandererRaven.ravencoffee.blocks.CoffeeBeansRoastedMagmaBlock;
import com.TheWandererRaven.ravencoffee.items.ItemBase;
import com.TheWandererRaven.ravencoffee.tools.RavenCoffeeItemTier;
import net.minecraft.block.Block;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RegistryHandler {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, RavenCoffee.MOD_ID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, RavenCoffee.MOD_ID);
    public static void init() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    // ITEMS
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
    public static final RegistryObject<Item> COFFEE_INGOT = ITEMS.register(
            "coffee_ingot",
            ItemBase::new
    );

    // TOOLS
    //                               Material Tier || Extra Attack Points || Attack Speed = 4 + n || Creative tab
    public static final RegistryObject<Item> COFFEE_PICKAXE = ITEMS.register(
            "coffee_pickaxe",
            () -> new PickaxeItem(RavenCoffeeItemTier.COFFEE, 3, -2.8f, new Item.Properties().group(RavenCoffee.TAB))
    );
    public static final RegistryObject<Item> COFFEE_AXE = ITEMS.register(
            "coffee_axe",
            () -> new AxeItem(RavenCoffeeItemTier.COFFEE, 8, -2.7f, new Item.Properties().group(RavenCoffee.TAB))
    );
    public static final RegistryObject<Item> COFFEE_HOE = ITEMS.register(
            "coffee_hoe",
            () -> new HoeItem(RavenCoffeeItemTier.COFFEE, 0, 1.0f, new Item.Properties().group(RavenCoffee.TAB))
    );
    public static final RegistryObject<Item> COFFEE_SHOVEL = ITEMS.register(
            "coffee_shovel",
            () -> new ShovelItem(RavenCoffeeItemTier.COFFEE, 4, -3.0f, new Item.Properties().group(RavenCoffee.TAB))
    );
    public static final RegistryObject<Item> COFFEE_SWORD = ITEMS.register(
            "coffee_sword",
            () -> new SwordItem(RavenCoffeeItemTier.COFFEE, 5, -2.0f, new Item.Properties().group(RavenCoffee.TAB))
    );

    // BLOCKS
    public static final RegistryObject<Block> COFFEE_BEANS_ROASTED_BLOCK = BLOCKS.register(
            "coffee_beans_roasted_block",
            CoffeeBeansRoastedBlock::new
    );
    public static final RegistryObject<Block> COFFEE_BEANS_ROASTED_MAGMA_BLOCK = BLOCKS.register(
            "coffee_beans_roasted_magma_block",
            CoffeeBeansRoastedMagmaBlock::new
    );

    // BLOCKS ITEMS
    public static final RegistryObject<Item> COFFEE_BEANS_ROASTED_BLOCK_ITEM = ITEMS.register(
            "coffee_beans_roasted_block",
            () -> new BlockItem(COFFEE_BEANS_ROASTED_BLOCK.get(), new Item.Properties().group(RavenCoffee.TAB))
    );
    public static final RegistryObject<Item> COFFEE_BEANS_ROASTED_MAGMA_BLOCK_ITEM = ITEMS.register(
            "coffee_beans_roasted_magma_block",
            () -> new BlockItem(COFFEE_BEANS_ROASTED_MAGMA_BLOCK.get(), new Item.Properties().group(RavenCoffee.TAB))
    );
}
