package com.thewandererraven.ravencoffee.items;

import com.thewandererraven.ravenbrewscore.CupType;
import com.thewandererraven.ravencoffee.Constants;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class RavenCoffeeBrewItems {

    // ################################################# COFFEE BREWS ##################################################
    //           #######################################   BASIC   #############################################
    public static final Item CUP_SMALL_BREW_BASIC = registerItem(
            "cup_small_brew_basic",
            new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.CUP_SMALL,
                    Brews.ESPRESSO,
                    new FabricItemSettings().group(RavenCoffeeItemGroups.CUP_SMALL_TAB)
            )
    );
    public static final Item CUP_MEDIUM_BREW_BASIC = registerItem(
            "cup_medium_brew_basic",
            new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.CUP_MEDIUM,
                    Brews.BASIC,
                    new FabricItemSettings().group(RavenCoffeeItemGroups.CUP_MEDIUM_TAB)
            )
    );
    public static final Item CUP_LARGE_BREW_BASIC = registerItem(
            "cup_large_brew_basic",
            new CoffeeBrew(
                    CupType.LARGE,
                    RavenCoffeeItems.CUP_LARGE,
                    Brews.BASIC,
                    new FabricItemSettings().group(RavenCoffeeItemGroups.CUP_LARGE_TAB)
            )
    );
    public static final Item COFFEE_MUG_BREW_BASIC = registerItem(
            "coffee_mug_brew_basic",
            new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.COFFEE_MUG,
                    Brews.BASIC,
                    new FabricItemSettings().group(RavenCoffeeItemGroups.COFFEE_MUG_TAB)
            )
    );
    /*
    //           #######################################   SUGAR   #############################################
    public static final RegistryObject<net.minecraft.world.item.Item> CUP_MEDIUM_BREW_SUGAR = BREWS.register(
            "cup_medium_brew_sugar",
            () -> new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.CUP_MEDIUM.get(),
                    Brews.SUGAR,
                    new net.minecraft.world.item.Item.Properties().tab(RavenCoffeeForge.CUP_MEDIUM_TAB)
            )
    );
    public static final RegistryObject<net.minecraft.world.item.Item> CUP_LARGE_BREW_SUGAR = BREWS.register(
            "cup_large_brew_sugar",
            () -> new CoffeeBrew(
                    CupType.LARGE,
                    RavenCoffeeItems.CUP_LARGE.get(),
                    Brews.SUGAR,
                    new net.minecraft.world.item.Item.Properties().tab(RavenCoffeeForge.CUP_LARGE_TAB)
            )
    );
    public static final RegistryObject<net.minecraft.world.item.Item> COFFEE_MUG_BREW_SUGAR = BREWS.register(
            "coffee_mug_brew_sugar",
            () -> new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.COFFEE_MUG.get(),
                    Brews.SUGAR,
                    new net.minecraft.world.item.Item.Properties().tab(RavenCoffeeForge.COFFEE_MUG_TAB)
            )
    );
    //           #######################################   APPLE   #############################################
    public static final RegistryObject<net.minecraft.world.item.Item> CUP_MEDIUM_BREW_APPLE = BREWS.register(
            "cup_medium_brew_apple",
            () -> new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.CUP_MEDIUM.get(),
                    Brews.APPLE,
                    new net.minecraft.world.item.Item.Properties().tab(RavenCoffeeForge.CUP_MEDIUM_TAB)
                            .food(new FoodProperties.Builder()
                                    .nutrition(2)
                                    .saturationMod(0.3f)
                                    .build())
            )
    );
    public static final RegistryObject<net.minecraft.world.item.Item> CUP_LARGE_BREW_APPLE = BREWS.register(
            "cup_large_brew_apple",
            () -> new CoffeeBrew(
                    CupType.LARGE,
                    RavenCoffeeItems.CUP_LARGE.get(),
                    Brews.APPLE,
                    new net.minecraft.world.item.Item.Properties().tab(RavenCoffeeForge.CUP_LARGE_TAB)
                            .food(new FoodProperties.Builder()
                                    .nutrition(3)
                                    .saturationMod(0.3f)
                                    .build())
            )
    );
    public static final RegistryObject<net.minecraft.world.item.Item> COFFEE_MUG_BREW_APPLE = BREWS.register(
            "coffee_mug_brew_apple",
            () -> new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.COFFEE_MUG.get(),
                    Brews.APPLE,
                    new net.minecraft.world.item.Item.Properties().tab(RavenCoffeeForge.COFFEE_MUG_TAB)
                            .food(new FoodProperties.Builder()
                                    .nutrition(2)
                                    .saturationMod(0.3f)
                                    .build())
            )
    );
    //           #######################################   BERRY   #############################################
    public static final RegistryObject<net.minecraft.world.item.Item> CUP_MEDIUM_BREW_BERRY = BREWS.register(
            "cup_medium_brew_berry",
            () -> new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.CUP_MEDIUM.get(),
                    Brews.BERRY,
                    new net.minecraft.world.item.Item.Properties().tab(RavenCoffeeForge.CUP_MEDIUM_TAB)
                            .food(new FoodProperties.Builder()
                                    .nutrition(1)
                                    .saturationMod(0.1f)
                                    .build())
            )
    );
    public static final RegistryObject<net.minecraft.world.item.Item> CUP_LARGE_BREW_BERRY = BREWS.register(
            "cup_large_brew_berry",
            () -> new CoffeeBrew(
                    CupType.LARGE,
                    RavenCoffeeItems.CUP_LARGE.get(),
                    Brews.BERRY,
                    new net.minecraft.world.item.Item.Properties().tab(RavenCoffeeForge.CUP_LARGE_TAB)
                            .food(new FoodProperties.Builder()
                                    .nutrition(2)
                                    .saturationMod(0.1f)
                                    .build())
            )
    );
    public static final RegistryObject<net.minecraft.world.item.Item> COFFEE_MUG_BREW_BERRY = BREWS.register(
            "coffee_mug_brew_berry",
            () -> new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.COFFEE_MUG.get(),
                    Brews.BERRY,
                    new net.minecraft.world.item.Item.Properties().tab(RavenCoffeeForge.COFFEE_MUG_TAB)
                            .food(new FoodProperties.Builder()
                                    .nutrition(1)
                                    .saturationMod(0.1f)
                                    .build())
            )
    );
    //           #######################################   HONEY   #############################################
    public static final RegistryObject<net.minecraft.world.item.Item> CUP_MEDIUM_BREW_HONEY = BREWS.register(
            "cup_medium_brew_honey",
            () -> new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.CUP_MEDIUM.get(),
                    Brews.HONEY,
                    new net.minecraft.world.item.Item.Properties().tab(RavenCoffeeForge.CUP_MEDIUM_TAB)
                            .food(new FoodProperties.Builder()
                                    .nutrition(6)
                                    .saturationMod(0.1f)
                                    .build())
            )
    );
    public static final RegistryObject<net.minecraft.world.item.Item> CUP_LARGE_BREW_HONEY = BREWS.register(
            "cup_large_brew_honey",
            () -> new CoffeeBrew(
                    CupType.LARGE,
                    RavenCoffeeItems.CUP_LARGE.get(),
                    Brews.HONEY,
                    new net.minecraft.world.item.Item.Properties().tab(RavenCoffeeForge.CUP_LARGE_TAB)
                            .food(new FoodProperties.Builder()
                                    .nutrition(9)
                                    .saturationMod(0.1f)
                                    .build())
            )
    );
    public static final RegistryObject<net.minecraft.world.item.Item> COFFEE_MUG_BREW_HONEY = BREWS.register(
            "coffee_mug_brew_honey",
            () -> new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.COFFEE_MUG.get(),
                    Brews.HONEY,
                    new net.minecraft.world.item.Item.Properties().tab(RavenCoffeeForge.COFFEE_MUG_TAB)
                            .food(new FoodProperties.Builder()
                                    .nutrition(6)
                                    .saturationMod(0.1f)
                                    .build())
            )
    );
    //           #######################################   CHOCOLATE   #############################################
    public static final RegistryObject<net.minecraft.world.item.Item> CUP_MEDIUM_BREW_CHOCOLATE = BREWS.register(
            "cup_medium_brew_chocolate",
            () -> new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.CUP_MEDIUM.get(),
                    Brews.CHOCOLATE,
                    new net.minecraft.world.item.Item.Properties().tab(RavenCoffeeForge.CUP_MEDIUM_TAB)
                            .food(new FoodProperties.Builder()
                                    .nutrition(2)
                                    .saturationMod(0.1f)
                                    .build())
            )
    );
    public static final RegistryObject<net.minecraft.world.item.Item> CUP_LARGE_BREW_CHOCOLATE = BREWS.register(
            "cup_large_brew_chocolate",
            () -> new CoffeeBrew(
                    CupType.LARGE,
                    RavenCoffeeItems.CUP_LARGE.get(),
                    Brews.CHOCOLATE,
                    new net.minecraft.world.item.Item.Properties().tab(RavenCoffeeForge.CUP_LARGE_TAB)
                            .food(new FoodProperties.Builder()
                                    .nutrition(3)
                                    .saturationMod(0.1f)
                                    .build())
            )
    );
    public static final RegistryObject<net.minecraft.world.item.Item> COFFEE_MUG_BREW_CHOCOLATE = BREWS.register(
            "coffee_mug_brew_chocolate",
            () -> new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.COFFEE_MUG.get(),
                    Brews.CHOCOLATE,
                    new net.minecraft.world.item.Item.Properties().tab(RavenCoffeeForge.COFFEE_MUG_TAB)
                            .food(new FoodProperties.Builder()
                                    .nutrition(2)
                                    .saturationMod(0.1f)
                                    .build())
            )
    );
    //           #######################################   MILK   #############################################
    public static final RegistryObject<net.minecraft.world.item.Item> CUP_MEDIUM_BREW_MILK = BREWS.register(
            "cup_medium_brew_milk",
            () -> new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.CUP_MEDIUM.get(),
                    Brews.MILK,
                    new net.minecraft.world.item.Item.Properties().tab(RavenCoffeeForge.CUP_MEDIUM_TAB)
            )
    );
    public static final RegistryObject<net.minecraft.world.item.Item> CUP_LARGE_BREW_MILK = BREWS.register(
            "cup_large_brew_milk",
            () -> new CoffeeBrew(
                    CupType.LARGE,
                    RavenCoffeeItems.CUP_LARGE.get(),
                    Brews.MILK,
                    new net.minecraft.world.item.Item.Properties().tab(RavenCoffeeForge.CUP_LARGE_TAB)
            )
    );
    public static final RegistryObject<net.minecraft.world.item.Item> COFFEE_MUG_BREW_MILK = BREWS.register(
            "coffee_mug_brew_milk",
            () -> new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.COFFEE_MUG.get(),
                    Brews.MILK,
                    new net.minecraft.world.item.Item.Properties().tab(RavenCoffeeForge.COFFEE_MUG_TAB)
            )
    );
    //           #######################################   MOCHA   #############################################
    public static final RegistryObject<net.minecraft.world.item.Item> CUP_MEDIUM_BREW_MOCHA = BREWS.register(
            "cup_medium_brew_mocha",
            () -> new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.CUP_MEDIUM.get(),
                    Brews.MOCHA,
                    new net.minecraft.world.item.Item.Properties().tab(RavenCoffeeForge.CUP_MEDIUM_TAB)
            )
    );
    public static final RegistryObject<net.minecraft.world.item.Item> CUP_LARGE_BREW_MOCHA = BREWS.register(
            "cup_large_brew_mocha",
            () -> new CoffeeBrew(
                    CupType.LARGE,
                    RavenCoffeeItems.CUP_LARGE.get(),
                    Brews.MOCHA,
                    new net.minecraft.world.item.Item.Properties().tab(RavenCoffeeForge.CUP_LARGE_TAB)
            )
    );
    public static final RegistryObject<net.minecraft.world.item.Item> COFFEE_MUG_BREW_MOCHA = BREWS.register(
            "coffee_mug_brew_mocha",
            () -> new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.COFFEE_MUG.get(),
                    Brews.MOCHA,
                    new net.minecraft.world.item.Item.Properties().tab(RavenCoffeeForge.COFFEE_MUG_TAB)
            )
    );
    //           #######################################   AWKWARD   #############################################
    public static final RegistryObject<net.minecraft.world.item.Item> CUP_MEDIUM_BREW_AWKWARD = BREWS.register(
            "cup_medium_brew_awkward",
            () -> new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.CUP_MEDIUM.get(),
                    Brews.AWKWARD,
                    new net.minecraft.world.item.Item.Properties().tab(RavenCoffeeForge.CUP_MEDIUM_TAB)
            )
    );
    public static final RegistryObject<net.minecraft.world.item.Item> CUP_LARGE_BREW_AWKWARD = BREWS.register(
            "cup_large_brew_awkward",
            () -> new CoffeeBrew(
                    CupType.LARGE,
                    RavenCoffeeItems.CUP_LARGE.get(),
                    Brews.AWKWARD,
                    new net.minecraft.world.item.Item.Properties().tab(RavenCoffeeForge.CUP_LARGE_TAB)
            )
    );
    public static final RegistryObject<net.minecraft.world.item.Item> COFFEE_MUG_BREW_AWKWARD = BREWS.register(
            "coffee_mug_brew_awkward",
            () -> new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.COFFEE_MUG.get(),
                    Brews.AWKWARD,
                    new net.minecraft.world.item.Item.Properties().tab(RavenCoffeeForge.COFFEE_MUG_TAB)
            )
    );
    //           #######################################   CARROT   #############################################
    public static final RegistryObject<net.minecraft.world.item.Item> CUP_MEDIUM_BREW_CARROT = BREWS.register(
            "cup_medium_brew_carrot",
            () -> new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.CUP_MEDIUM.get(),
                    Brews.CARROT,
                    new net.minecraft.world.item.Item.Properties().tab(RavenCoffeeForge.CUP_MEDIUM_TAB)
                            .food(new FoodProperties.Builder()
                                    .nutrition(3)
                                    .saturationMod(0.3f)
                                    .build())
            )
    );
    public static final RegistryObject<net.minecraft.world.item.Item> CUP_LARGE_BREW_CARROT = BREWS.register(
            "cup_large_brew_carrot",
            () -> new CoffeeBrew(
                    CupType.LARGE,
                    RavenCoffeeItems.CUP_LARGE.get(),
                    Brews.CARROT,
                    new net.minecraft.world.item.Item.Properties().tab(RavenCoffeeForge.CUP_LARGE_TAB)
                            .food(new FoodProperties.Builder()
                                    .nutrition(3)
                                    .saturationMod(0.3f)
                                    .build())
            )
    );
    public static final RegistryObject<net.minecraft.world.item.Item> COFFEE_MUG_BREW_CARROT = BREWS.register(
            "coffee_mug_brew_carrot",
            () -> new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.COFFEE_MUG.get(),
                    Brews.CARROT,
                    new net.minecraft.world.item.Item.Properties().tab(RavenCoffeeForge.COFFEE_MUG_TAB)
                            .food(new FoodProperties.Builder()
                                    .nutrition(3)
                                    .saturationMod(0.3f)
                                    .build())
            )
    );
    //           #######################################   CARROT_GOLDEN   #############################################
    public static final RegistryObject<net.minecraft.world.item.Item> CUP_MEDIUM_BREW_CARROT_GOLDEN = BREWS.register(
            "cup_medium_brew_carrot_golden",
            () -> new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.CUP_MEDIUM.get(),
                    Brews.CARROT_GOLDEN,
                    new net.minecraft.world.item.Item.Properties().tab(RavenCoffeeForge.CUP_MEDIUM_TAB)
                            .food(new FoodProperties.Builder()
                                    .nutrition(6)
                                    .saturationMod(0.8f)
                                    .build())
            )
    );
    public static final RegistryObject<net.minecraft.world.item.Item> CUP_LARGE_BREW_CARROT_GOLDEN = BREWS.register(
            "cup_large_brew_carrot_golden",
            () -> new CoffeeBrew(
                    CupType.LARGE,
                    RavenCoffeeItems.CUP_LARGE.get(),
                    Brews.CARROT_GOLDEN,
                    new net.minecraft.world.item.Item.Properties().tab(RavenCoffeeForge.CUP_LARGE_TAB)
                            .food(new FoodProperties.Builder()
                                    .nutrition(9)
                                    .saturationMod(0.8f)
                                    .build())
            )
    );
    public static final RegistryObject<net.minecraft.world.item.Item> COFFEE_MUG_BREW_CARROT_GOLDEN = BREWS.register(
            "coffee_mug_brew_carrot_golden",
            () -> new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.COFFEE_MUG.get(),
                    Brews.CARROT_GOLDEN,
                    new net.minecraft.world.item.Item.Properties().tab(RavenCoffeeForge.COFFEE_MUG_TAB)
                            .food(new FoodProperties.Builder()
                                    .nutrition(6)
                                    .saturationMod(0.8f)
                                    .build())
            )
    );
    //           #######################################   COOKIESANDCREAM   #############################################
    public static final RegistryObject<net.minecraft.world.item.Item> CUP_MEDIUM_BREW_COOKIESANDCREAM = BREWS.register(
            "cup_medium_brew_cookiesandcream",
            () -> new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.CUP_MEDIUM.get(),
                    Brews.COOKIESANDCREAM,
                    new net.minecraft.world.item.Item.Properties().tab(RavenCoffeeForge.CUP_MEDIUM_TAB)
                            .food(new FoodProperties.Builder()
                                    .nutrition(4)
                                    .saturationMod(0.1f)
                                    .build())
            )
    );
    public static final RegistryObject<net.minecraft.world.item.Item> CUP_LARGE_BREW_COOKIESANDCREAM = BREWS.register(
            "cup_large_brew_cookiesandcream",
            () -> new CoffeeBrew(
                    CupType.LARGE,
                    RavenCoffeeItems.CUP_LARGE.get(),
                    Brews.COOKIESANDCREAM,
                    new net.minecraft.world.item.Item.Properties().tab(RavenCoffeeForge.CUP_LARGE_TAB)
                            .food(new FoodProperties.Builder()
                                    .nutrition(4)
                                    .saturationMod(0.1f)
                                    .build())
            )
    );
    public static final RegistryObject<net.minecraft.world.item.Item> COFFEE_MUG_BREW_COOKIESANDCREAM = BREWS.register(
            "coffee_mug_brew_cookiesandcream",
            () -> new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.COFFEE_MUG.get(),
                    Brews.COOKIESANDCREAM,
                    new net.minecraft.world.item.Item.Properties().tab(RavenCoffeeForge.COFFEE_MUG_TAB)
                            .food(new FoodProperties.Builder()
                                    .nutrition(4)
                                    .saturationMod(0.1f)
                                    .build())
            )
    );
    //           #######################################   END   #############################################
    public static final RegistryObject<net.minecraft.world.item.Item> CUP_MEDIUM_BREW_END = BREWS.register(
            "cup_medium_brew_end",
            () -> new ChorusBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.CUP_MEDIUM.get(),
                    Brews.END,
                    new net.minecraft.world.item.Item.Properties().tab(RavenCoffeeForge.CUP_MEDIUM_TAB)
                            .food(new FoodProperties.Builder()
                                    .nutrition(2)
                                    .saturationMod(0.3f)
                                    .build())
            )
    );
    public static final RegistryObject<net.minecraft.world.item.Item> CUP_LARGE_BREW_END = BREWS.register(
            "cup_large_brew_end",
            () -> new ChorusBrew(
                    CupType.LARGE,
                    RavenCoffeeItems.CUP_LARGE.get(),
                    Brews.END,
                    new net.minecraft.world.item.Item.Properties().tab(RavenCoffeeForge.CUP_LARGE_TAB)
                            .food(new FoodProperties.Builder()
                                    .nutrition(3)
                                    .saturationMod(0.3f)
                                    .build())
            )
    );
    public static final RegistryObject<net.minecraft.world.item.Item> COFFEE_MUG_BREW_END = BREWS.register(
            "coffee_mug_brew_end",
            () -> new ChorusBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.COFFEE_MUG.get(),
                    Brews.END,
                    new net.minecraft.world.item.Item.Properties().tab(RavenCoffeeForge.COFFEE_MUG_TAB)
                            .food(new FoodProperties.Builder()
                                    .nutrition(2)
                                    .saturationMod(0.3f)
                                    .build())
            )
    );
    //           #######################################   MELON   #############################################
    public static final RegistryObject<net.minecraft.world.item.Item> CUP_MEDIUM_BREW_MELON = BREWS.register(
            "cup_medium_brew_melon",
            () -> new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.CUP_MEDIUM.get(),
                    Brews.MELON,
                    new net.minecraft.world.item.Item.Properties().tab(RavenCoffeeForge.CUP_MEDIUM_TAB)
                            .food(new FoodProperties.Builder()
                                    .nutrition(2)
                                    .saturationMod(0.3f)
                                    .build())
            )
    );
    public static final RegistryObject<net.minecraft.world.item.Item> CUP_LARGE_BREW_MELON = BREWS.register(
            "cup_large_brew_melon",
            () -> new CoffeeBrew(
                    CupType.LARGE,
                    RavenCoffeeItems.CUP_LARGE.get(),
                    Brews.MELON,
                    new net.minecraft.world.item.Item.Properties().tab(RavenCoffeeForge.CUP_LARGE_TAB)
                            .food(new FoodProperties.Builder()
                                    .nutrition(3)
                                    .saturationMod(0.3f)
                                    .build())
            )
    );
    public static final RegistryObject<net.minecraft.world.item.Item> COFFEE_MUG_BREW_MELON = BREWS.register(
            "coffee_mug_brew_melon",
            () -> new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.COFFEE_MUG.get(),
                    Brews.MELON,
                    new net.minecraft.world.item.Item.Properties().tab(RavenCoffeeForge.COFFEE_MUG_TAB)
                            .food(new FoodProperties.Builder()
                                    .nutrition(2)
                                    .saturationMod(0.3f)
                                    .build())
            )
    );
    //           #######################################   MELON_GOLDEN   #############################################
    public static final RegistryObject<net.minecraft.world.item.Item> CUP_MEDIUM_BREW_MELON_GOLDEN = BREWS.register(
            "cup_medium_brew_melon_golden",
            () -> new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.CUP_MEDIUM.get(),
                    Brews.MELON_GOLDEN,
                    new net.minecraft.world.item.Item.Properties().tab(RavenCoffeeForge.CUP_MEDIUM_TAB)
                            .food(new FoodProperties.Builder()
                                    .nutrition(2)
                                    .saturationMod(0.3f)
                                    .build())
            )
    );
    public static final RegistryObject<net.minecraft.world.item.Item> CUP_LARGE_BREW_MELON_GOLDEN = BREWS.register(
            "cup_large_brew_melon_golden",
            () -> new CoffeeBrew(
                    CupType.LARGE,
                    RavenCoffeeItems.CUP_LARGE.get(),
                    Brews.MELON_GOLDEN,
                    new net.minecraft.world.item.Item.Properties().tab(RavenCoffeeForge.CUP_LARGE_TAB)
                            .food(new FoodProperties.Builder()
                                    .nutrition(3)
                                    .saturationMod(0.3f)
                                    .build())
            )
    );
    public static final RegistryObject<net.minecraft.world.item.Item> COFFEE_MUG_BREW_MELON_GOLDEN = BREWS.register(
            "coffee_mug_brew_melon_golden",
            () -> new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.COFFEE_MUG.get(),
                    Brews.MELON_GOLDEN,
                    new net.minecraft.world.item.Item.Properties().tab(RavenCoffeeForge.COFFEE_MUG_TAB)
                            .food(new FoodProperties.Builder()
                                    .nutrition(2)
                                    .saturationMod(0.3f)
                                    .build())
            )
    );
    //           #######################################   NETHER   #############################################
    public static final RegistryObject<net.minecraft.world.item.Item> CUP_MEDIUM_BREW_NETHER = BREWS.register(
            "cup_medium_brew_nether",
            () -> new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.CUP_MEDIUM.get(),
                    Brews.NETHER,
                    new net.minecraft.world.item.Item.Properties().tab(RavenCoffeeForge.CUP_MEDIUM_TAB)
                            .food(new FoodProperties.Builder()
                                    .nutrition(1)
                                    .saturationMod(0.1f)
                                    .build())
            )
    );
    public static final RegistryObject<net.minecraft.world.item.Item> CUP_LARGE_BREW_NETHER = BREWS.register(
            "cup_large_brew_nether",
            () -> new CoffeeBrew(
                    CupType.LARGE,
                    RavenCoffeeItems.CUP_LARGE.get(),
                    Brews.NETHER,
                    new net.minecraft.world.item.Item.Properties().tab(RavenCoffeeForge.CUP_LARGE_TAB)
                            .food(new FoodProperties.Builder()
                                    .nutrition(1)
                                    .saturationMod(0.1f)
                                    .build())
            )
    );
    public static final RegistryObject<net.minecraft.world.item.Item> COFFEE_MUG_BREW_NETHER = BREWS.register(
            "coffee_mug_brew_nether",
            () -> new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.COFFEE_MUG.get(),
                    Brews.NETHER,
                    new net.minecraft.world.item.Item.Properties().tab(RavenCoffeeForge.COFFEE_MUG_TAB)
                            .food(new FoodProperties.Builder()
                                    .nutrition(1)
                                    .saturationMod(0.1f)
                                    .build())
            )
    );
    //           #######################################   PHANTASM   #############################################
    public static final RegistryObject<net.minecraft.world.item.Item> CUP_MEDIUM_BREW_PHANTASM = BREWS.register(
            "cup_medium_brew_phantasm",
            () -> new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.CUP_MEDIUM.get(),
                    Brews.PHANTASM,
                    new net.minecraft.world.item.Item.Properties().tab(RavenCoffeeForge.CUP_MEDIUM_TAB)
            )
    );
    public static final RegistryObject<net.minecraft.world.item.Item> CUP_LARGE_BREW_PHANTASM = BREWS.register(
            "cup_large_brew_phantasm",
            () -> new CoffeeBrew(
                    CupType.LARGE,
                    RavenCoffeeItems.CUP_LARGE.get(),
                    Brews.PHANTASM,
                    new net.minecraft.world.item.Item.Properties().tab(RavenCoffeeForge.CUP_LARGE_TAB)
            )
    );
    public static final RegistryObject<net.minecraft.world.item.Item> COFFEE_MUG_BREW_PHANTASM = BREWS.register(
            "coffee_mug_brew_phantasm",
            () -> new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.COFFEE_MUG.get(),
                    Brews.PHANTASM,
                    new net.minecraft.world.item.Item.Properties().tab(RavenCoffeeForge.COFFEE_MUG_TAB)
            )
    );
    //           #######################################   PUMPKINSPICELATTE   #############################################
    public static final RegistryObject<net.minecraft.world.item.Item> CUP_MEDIUM_BREW_PUMPKINSPICELATTE = BREWS.register(
            "cup_medium_brew_pumpkinspicelatte",
            () -> new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.CUP_MEDIUM.get(),
                    Brews.PUMPKINSPICELATTE,
                    new net.minecraft.world.item.Item.Properties().tab(RavenCoffeeForge.CUP_MEDIUM_TAB)
            )
    );
    public static final RegistryObject<net.minecraft.world.item.Item> CUP_LARGE_BREW_PUMPKINSPICELATTE = BREWS.register(
            "cup_large_brew_pumpkinspicelatte",
            () -> new CoffeeBrew(
                    CupType.LARGE,
                    RavenCoffeeItems.CUP_LARGE.get(),
                    Brews.PUMPKINSPICELATTE,
                    new net.minecraft.world.item.Item.Properties().tab(RavenCoffeeForge.CUP_LARGE_TAB)
            )
    );
    public static final RegistryObject<net.minecraft.world.item.Item> COFFEE_MUG_BREW_PUMPKINSPICELATTE = BREWS.register(
            "coffee_mug_brew_pumpkinspicelatte",
            () -> new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.COFFEE_MUG.get(),
                    Brews.PUMPKINSPICELATTE,
                    new net.minecraft.world.item.Item.Properties().tab(RavenCoffeeForge.COFFEE_MUG_TAB)
            )
    );
    //           #######################################   PUMPKINSPICELATTE_WITH_PUMPKIN   #############################################
    public static final RegistryObject<net.minecraft.world.item.Item> CUP_MEDIUM_BREW_PUMPKINSPICELATTE_WITH_PUMPKIN = BREWS.register(
            "cup_medium_brew_pumpkinspicelatte_with_pumpkin",
            () -> new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.CUP_MEDIUM.get(),
                    Brews.PUMPKINSPICELATTE_WITH_PUMPKIN,
                    new net.minecraft.world.item.Item.Properties().tab(RavenCoffeeForge.CUP_MEDIUM_TAB)
                            .food(new FoodProperties.Builder()
                                    .nutrition(5)
                                    .saturationMod(0.3f)
                                    .build())
            )
    );
    public static final RegistryObject<net.minecraft.world.item.Item> CUP_LARGE_BREW_PUMPKINSPICELATTE_WITH_PUMPKIN = BREWS.register(
            "cup_large_brew_pumpkinspicelatte_with_pumpkin",
            () -> new CoffeeBrew(
                    CupType.LARGE,
                    RavenCoffeeItems.CUP_LARGE.get(),
                    Brews.PUMPKINSPICELATTE_WITH_PUMPKIN,
                    new net.minecraft.world.item.Item.Properties().tab(RavenCoffeeForge.CUP_LARGE_TAB)
                            .food(new FoodProperties.Builder()
                                    .nutrition(5)
                                    .saturationMod(0.3f)
                                    .build())
            )
    );
    public static final RegistryObject<net.minecraft.world.item.Item> COFFEE_MUG_BREW_PUMPKINSPICELATTE_WITH_PUMPKIN = BREWS.register(
            "coffee_mug_brew_pumpkinspicelatte_with_pumpkin",
            () -> new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.COFFEE_MUG.get(),
                    Brews.PUMPKINSPICELATTE_WITH_PUMPKIN,
                    new Item.Properties().tab(RavenCoffeeForge.COFFEE_MUG_TAB)
                            .food(new FoodProperties.Builder()
                                    .nutrition(5)
                                    .saturationMod(0.3f)
                                    .build())
            )
    );
     */


    private static net.minecraft.item.Item registerItem(String name, net.minecraft.item.Item item) {
        return Registry.register(Registry.ITEM, new Identifier(Constants.MOD_ID, name), item);
    }
    public static void register() {
        Constants.LOGGER.info(Constants.MOD_NAME + " has registered its items!");
    }
}
