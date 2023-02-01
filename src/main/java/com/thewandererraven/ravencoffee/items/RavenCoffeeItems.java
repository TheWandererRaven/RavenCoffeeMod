package com.thewandererraven.ravencoffee.items;

import com.thewandererraven.ravencoffee.RavenCoffee;
import com.thewandererraven.ravencoffee.blocks.RavenCoffeeBlocks;
import com.thewandererraven.ravencoffee.items.tools.RavenCoffeeItemTier;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RavenCoffeeItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(
            ForgeRegistries.ITEMS,
            RavenCoffee.MOD_ID
    );

    // ############################################### BASIC COFFEE STUFF ##############################################
    public static final RegistryObject<Item> COFFEE_CHERRIES = ITEMS.register(
            "coffee_cherries",
            () -> new ItemNameBlockItem(
                    RavenCoffeeBlocks.COFFEE_TREE_TRUNK_BLOCK.get(),
                    new Item.Properties().tab(RavenCoffee.GENERAL_TAB)
            )
    );
    public static final RegistryObject<Item> COFFEE_BEANS = ITEMS.register(
            "coffee_beans",
            ItemBase::new
    );
    public static final RegistryObject<Item> COFFEE_BEANS_ROASTED = ITEMS.register(
            "coffee_beans_roasted",
            ItemBase::new
    );
    public static final RegistryObject<Item> COFFEE_BEANS_MAGMA = ITEMS.register(
            "coffee_beans_magma",
            ItemBase::new
    );

    // ############################################### COFFEE INGREDIENTS ##############################################
    public static final RegistryObject<Item> COFFEE_BEANS_ROASTED_GROUND = ITEMS.register(
            "coffee_beans_roasted_ground",
            ItemBase::new
    );

    public static final RegistryObject<Item> COFFEE_BEANS_MAGMA_GROUND = ITEMS.register(
            "coffee_beans_magma_ground",
            ItemBase::new
    );

    // ############################################### FOODSTUFF ##############################################
    public static final RegistryObject<Item> POPCHORUS = ITEMS.register(
            "popchorus",
            () -> new Item(new Item.Properties()
                    .tab(RavenCoffee.GENERAL_TAB)
                    .food(new FoodProperties.Builder()
                            .nutrition(12)
                            .saturationMod(0.15f)
                            .build()
                    )
            )
    );
    public static final RegistryObject<Item> MUFFIN = ITEMS.register(
            "muffin",
            () -> new ItemThrowable(new Item.Properties()
                    .tab(RavenCoffee.GENERAL_TAB)
                    .food(new FoodProperties.Builder()
                            .nutrition(2)
                            .saturationMod(0.5f/2f)
                            .build()
                    )
            )
    );
    public static final RegistryObject<Item> MELON_PAN = ITEMS.register(
            "melon_pan",
            () -> new Item(new Item.Properties()
                    .tab(RavenCoffee.GENERAL_TAB)
                    .food(new FoodProperties.Builder()
                            .nutrition(7)
                            .saturationMod(0.8f/2f)
                            .build()
                    )
            )
    );
    public static final RegistryObject<Item> COFFEE_ECLAIR = ITEMS.register(
            "coffee_eclair",
            () -> new Item(new Item.Properties()
                    .tab(RavenCoffee.GENERAL_TAB)
                    .food(new FoodProperties.Builder()
                            .nutrition(4)
                            .saturationMod(0.6f/2f)
                            .build()
                    )
            )
    );
    public static final RegistryObject<Item> BROWNIE = ITEMS.register(
            "brownie",
            () -> new Item(new Item.Properties()
                    .tab(RavenCoffee.GENERAL_TAB)
                    .food(new FoodProperties.Builder()
                            .nutrition(2)
                            .saturationMod(0.4f/2f)
                            .build()
                    )
            )
    );
    public static final RegistryObject<Item> SANDWICH_HAM = ITEMS.register(
            "sandwich_ham",
            () -> new Item(new Item.Properties()
                    .tab(RavenCoffee.GENERAL_TAB)
                    .food(new FoodProperties.Builder()
                            .nutrition(8)
                            .saturationMod(0.9f/2f)
                            .build()
                    )
            )
    );
    public static final RegistryObject<Item> SANDWICH_BEEF = ITEMS.register(
            "sandwich_beef",
            () -> new Item(new Item.Properties()
                    .tab(RavenCoffee.GENERAL_TAB)
                    .food(new FoodProperties.Builder()
                            .nutrition(11)
                            .saturationMod(1.4f/2f)
                            .build()
                    )
            )
    );
    public static final RegistryObject<Item> SANDWICH_CHICKEN = ITEMS.register(
            "sandwich_chicken",
            () -> new Item(new Item.Properties()
                    .tab(RavenCoffee.GENERAL_TAB)
                    .food(new FoodProperties.Builder()
                            .nutrition(9)
                            .saturationMod(1.2f/2f)
                            .build()
                    )
            )
    );
    public static final RegistryObject<Item> CROISSANT = ITEMS.register(
            "croissant",
            () -> new Item(new Item.Properties()
                    .tab(RavenCoffee.GENERAL_TAB)
                    .food(new FoodProperties.Builder()
                            .nutrition(5)
                            .saturationMod(1.2f/2f)
                            .build()
                    )
            )
    );
    public static final RegistryObject<Item> CROISSANT_HAM = ITEMS.register(
            "croissant_ham",
            () -> new Item(new Item.Properties()
                    .tab(RavenCoffee.GENERAL_TAB)
                    .food(new FoodProperties.Builder()
                            .nutrition(8)
                            .saturationMod(0.9f/2f)
                            .build()
                    )
            )
    );
    public static final RegistryObject<Item> CROISSANT_BEEF = ITEMS.register(
            "croissant_beef",
            () -> new Item(new Item.Properties()
                    .tab(RavenCoffee.GENERAL_TAB)
                    .food(new FoodProperties.Builder()
                            .nutrition(11)
                            .saturationMod(1.4f/2f)
                            .build()
                    )
            )
    );
    public static final RegistryObject<Item> CROISSANT_CHICKEN = ITEMS.register(
            "croissant_chicken",
            () -> new Item(new Item.Properties()
                    .tab(RavenCoffee.GENERAL_TAB)
                    .food(new FoodProperties.Builder()
                            .nutrition(9)
                            .saturationMod(1.2f/2f)
                            .build()
                    )
            )
    );
    public static final RegistryObject<Item> BAGEL = ITEMS.register(
            "bagel",
            () -> new Item(new Item.Properties()
                    .tab(RavenCoffee.GENERAL_TAB)
                    .food(new FoodProperties.Builder()
                            .nutrition(7)
                            .saturationMod(1.2f/2f)
                            .build()
                    )
            )
    );
    public static final RegistryObject<Item> BAGEL_HAM = ITEMS.register(
            "bagel_ham",
            () -> new Item(new Item.Properties()
                    .tab(RavenCoffee.GENERAL_TAB)
                    .food(new FoodProperties.Builder()
                            .nutrition(10)
                            .saturationMod(0.9f/2f)
                            .build()
                    )
            )
    );
    public static final RegistryObject<Item> BAGEL_BEEF = ITEMS.register(
            "bagel_beef",
            () -> new Item(new Item.Properties()
                    .tab(RavenCoffee.GENERAL_TAB)
                    .food(new FoodProperties.Builder()
                            .nutrition(13)
                            .saturationMod(1.4f/2f)
                            .build()
                    )
            )
    );
    public static final RegistryObject<Item> BAGEL_CHICKEN = ITEMS.register(
            "bagel_chicken",
            () -> new Item(new Item.Properties()
                    .tab(RavenCoffee.GENERAL_TAB)
                    .food(new FoodProperties.Builder()
                            .nutrition(11)
                            .saturationMod(1.2f/2f)
                            .build()
                    )
            )
    );

    // ###################################################### CUPS #####################################################
    public static final RegistryObject<Item> CUP_SMALL_UNFIRED = ITEMS.register(
            "cup_small_unfired",
            () -> new ItemBase(RavenCoffee.CUP_SMALL_TAB)
    );
    public static final RegistryObject<Item> CUP_SMALL = ITEMS.register(
            "cup_small",
            () -> new StackingCupsBlockItem(
                    RavenCoffeeBlocks.STACKING_CUPS_BLOCK.get(),
                    (new Item.Properties()).tab(RavenCoffee.CUP_SMALL_TAB)
            )
    );
    public static final RegistryObject<Item> CUP_MEDIUM_UNFIRED = ITEMS.register(
            "cup_medium_unfired",
            () -> new ItemBase(RavenCoffee.CUP_MEDIUM_TAB)
    );
    public static final RegistryObject<Item> CUP_MEDIUM = ITEMS.register(
            "cup_medium",
            () -> new StackingCupsBlockItem(
                    RavenCoffeeBlocks.STACKING_CUPS_BLOCK.get(),
                    (new Item.Properties()).tab(RavenCoffee.CUP_MEDIUM_TAB)
            )
    );
    public static final RegistryObject<Item> CUP_LARGE_UNFIRED = ITEMS.register(
            "cup_large_unfired",
            () -> new ItemBase(RavenCoffee.CUP_LARGE_TAB)
    );
    public static final RegistryObject<Item> CUP_LARGE = ITEMS.register(
            "cup_large",
            () -> new StackingCupsBlockItem(RavenCoffeeBlocks.STACKING_CUPS_BLOCK.get(),
                    (new Item.Properties()).tab(RavenCoffee.CUP_LARGE_TAB)
            )
    );
    public static final RegistryObject<Item> COFFEE_MUG = ITEMS.register(
            "coffee_mug",
            () -> new StackingCupsBlockItem(
                    RavenCoffeeBlocks.STACKING_CUPS_BLOCK.get(),
                    (new Item.Properties()).tab(RavenCoffee.COFFEE_MUG_TAB)
            )
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
    //                               Material Tier || Extra Attack Points || Attack Speed = 4 + n || Creative GENERAL_TAB
    public static final RegistryObject<Item> COFFEE_PICKAXE = ITEMS.register(
            "coffee_pickaxe",
            () -> new PickaxeItem(RavenCoffeeItemTier.COFFEE, 3, -2.8f, new Item.Properties().tab(RavenCoffee.GENERAL_TAB))
    );
    public static final RegistryObject<Item> COFFEE_AXE =  ITEMS.register(
            "coffee_axe",
            () -> new AxeItem(RavenCoffeeItemTier.COFFEE, 8, -2.7f, new Item.Properties().tab(RavenCoffee.GENERAL_TAB))
    );
    public static final RegistryObject<Item> COFFEE_HOE =  ITEMS.register(
            "coffee_hoe",
            () -> new HoeItem(RavenCoffeeItemTier.COFFEE, 0, 1.0f, new Item.Properties().tab(RavenCoffee.GENERAL_TAB))
    );
    public static final RegistryObject<Item> COFFEE_SHOVEL =  ITEMS.register(
            "coffee_shovel",
            () -> new ShovelItem(RavenCoffeeItemTier.COFFEE, 4, -3.0f, new Item.Properties().tab(RavenCoffee.GENERAL_TAB))
    );
    public static final RegistryObject<Item> COFFEE_SWORD =  ITEMS.register(
            "coffee_sword",
            () -> new SwordItem(RavenCoffeeItemTier.COFFEE, 5, -2.0f, new Item.Properties().tab(RavenCoffee.GENERAL_TAB))
    );

    // ############################################### COFFEE BLOCK ITEMS ################################################
    public static final RegistryObject<Item> BROWNIE_BLOCK_ITEM = ITEMS.register(
            "brownie_block",
            () -> new BlockItem(RavenCoffeeBlocks.BROWNIE_BLOCK.get(), new Item.Properties().tab(RavenCoffee.GENERAL_TAB)
                    .food(new FoodProperties.Builder()
                            .nutrition(25)
                            .saturationMod(5.0f/2f)
                            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 6000, 5), 1.0f)
                            .build()
                    ))
    );
    public static final RegistryObject<Item> ROSCA_DE_REYES_BLOCK_ITEM = ITEMS.register(
            "rosca_de_reyes_block",
            () -> new BlockItem(RavenCoffeeBlocks.ROSCA_DE_REYES_BLOCK.get(), new Item.Properties().tab(RavenCoffee.GENERAL_TAB))
    );
    public static final RegistryObject<Item> COFFEE_BEANS_ROASTED_BLOCK_ITEM = ITEMS.register(
            "coffee_beans_roasted_block",
            () -> new BlockItem(RavenCoffeeBlocks.COFFEE_BEANS_ROASTED_BLOCK.get(), new Item.Properties().tab(RavenCoffee.GENERAL_TAB))
    );
    public static final RegistryObject<Item> COFFEE_BEANS_MAGMA_BLOCK_ITEM = ITEMS.register(
            "coffee_beans_magma_block",
            () -> new BlockItem(RavenCoffeeBlocks.COFFEE_BEANS_MAGMA_BLOCK.get(), new Item.Properties().tab(RavenCoffee.GENERAL_TAB))
    );

    public static final RegistryObject<Item> COFFEE_GRINDER_ITEM = ITEMS.register(
            "coffee_grinder_block",
            () -> new BlockItem(RavenCoffeeBlocks.COFFEE_GRINDER.get(), new Item.Properties().tab(RavenCoffee.GENERAL_TAB))
    );

    public static final RegistryObject<Item> COFFEE_MACHINE_ITEM = ITEMS.register(
            "coffee_machine_block",
            () -> new BlockItem(RavenCoffeeBlocks.COFFEE_MACHINE_BLOCK.get(), new Item.Properties().tab(RavenCoffee.GENERAL_TAB))
    );

    public static final RegistryObject<Item> SACK_BLOCK_ITEM = ITEMS.register(
            "sack_block",
            () -> new BlockItem(RavenCoffeeBlocks.SACK_BLOCK.get(), new Item.Properties().tab(RavenCoffee.GENERAL_TAB))
    );
}
