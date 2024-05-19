package com.thewandererraven.ravencoffee.items;

import com.thewandererraven.ravenbrewscore.CupType;
import com.thewandererraven.ravencoffee.Constants;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.FoodComponent;
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
    //           #######################################   SUGAR   #############################################
    public static final Item CUP_MEDIUM_BREW_SUGAR = registerItem(
            "cup_medium_brew_sugar",
            new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.CUP_MEDIUM,
                    Brews.SUGAR,
                    new FabricItemSettings().group(RavenCoffeeItemGroups.CUP_MEDIUM_TAB)
            )
    );
    public static final Item CUP_LARGE_BREW_SUGAR = registerItem(
            "cup_large_brew_sugar",
            new CoffeeBrew(
                    CupType.LARGE,
                    RavenCoffeeItems.CUP_LARGE,
                    Brews.SUGAR,
                    new FabricItemSettings().group(RavenCoffeeItemGroups.CUP_LARGE_TAB)
            )
    );
    public static final Item COFFEE_MUG_BREW_SUGAR = registerItem(
            "coffee_mug_brew_sugar",
            new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.COFFEE_MUG,
                    Brews.SUGAR,
                    new FabricItemSettings().group(RavenCoffeeItemGroups.COFFEE_MUG_TAB)
            )
    );
    //           #######################################   APPLE   #############################################
    public static final Item CUP_MEDIUM_BREW_APPLE = registerItem(
            "cup_medium_brew_apple",
            new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.CUP_MEDIUM,
                    Brews.APPLE,
                    new FabricItemSettings().group(RavenCoffeeItemGroups.CUP_MEDIUM_TAB)
                            .food(new FoodComponent.Builder()
                                    .hunger(2)
                                    .saturationModifier(0.3f)
                                    .build())
            )
    );
    public static final Item CUP_LARGE_BREW_APPLE = registerItem(
            "cup_large_brew_apple",
            new CoffeeBrew(
                    CupType.LARGE,
                    RavenCoffeeItems.CUP_LARGE,
                    Brews.APPLE,
                    new FabricItemSettings().group(RavenCoffeeItemGroups.CUP_LARGE_TAB)
                            .food(new FoodComponent.Builder()
                                    .hunger(3)
                                    .saturationModifier(0.3f)
                                    .build())
            )
    );
    public static final Item COFFEE_MUG_BREW_APPLE = registerItem(
            "coffee_mug_brew_apple",
            new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.COFFEE_MUG,
                    Brews.APPLE,
                    new FabricItemSettings().group(RavenCoffeeItemGroups.COFFEE_MUG_TAB)
                            .food(new FoodComponent.Builder()
                                    .hunger(2)
                                    .saturationModifier(0.3f)
                                    .build())
            )
    );
    //           #######################################   BERRY   #############################################
    public static final Item CUP_MEDIUM_BREW_BERRY = registerItem(
            "cup_medium_brew_berry",
            new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.CUP_MEDIUM,
                    Brews.BERRY,
                    new FabricItemSettings().group(RavenCoffeeItemGroups.CUP_MEDIUM_TAB)
                            .food(new FoodComponent.Builder()
                                    .hunger(1)
                                    .saturationModifier(0.1f)
                                    .build())
            )
    );
    public static final Item CUP_LARGE_BREW_BERRY = registerItem(
            "cup_large_brew_berry",
            new CoffeeBrew(
                    CupType.LARGE,
                    RavenCoffeeItems.CUP_LARGE,
                    Brews.BERRY,
                    new FabricItemSettings().group(RavenCoffeeItemGroups.CUP_LARGE_TAB)
                            .food(new FoodComponent.Builder()
                                    .hunger(2)
                                    .saturationModifier(0.1f)
                                    .build())
            )
    );
    public static final Item COFFEE_MUG_BREW_BERRY = registerItem(
            "coffee_mug_brew_berry",
            new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.COFFEE_MUG,
                    Brews.BERRY,
                    new FabricItemSettings().group(RavenCoffeeItemGroups.COFFEE_MUG_TAB)
                            .food(new FoodComponent.Builder()
                                    .hunger(1)
                                    .saturationModifier(0.1f)
                                    .build())
            )
    );
    //           #######################################   HONEY   #############################################
    public static final Item CUP_MEDIUM_BREW_HONEY = registerItem(
            "cup_medium_brew_honey",
            new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.CUP_MEDIUM,
                    Brews.HONEY,
                    new FabricItemSettings().group(RavenCoffeeItemGroups.CUP_MEDIUM_TAB)
                            .food(new FoodComponent.Builder()
                                    .hunger(6)
                                    .saturationModifier(0.1f)
                                    .build())
            )
    );
    public static final Item CUP_LARGE_BREW_HONEY = registerItem(
            "cup_large_brew_honey",
            new CoffeeBrew(
                    CupType.LARGE,
                    RavenCoffeeItems.CUP_LARGE,
                    Brews.HONEY,
                    new FabricItemSettings().group(RavenCoffeeItemGroups.CUP_LARGE_TAB)
                            .food(new FoodComponent.Builder()
                                    .hunger(9)
                                    .saturationModifier(0.1f)
                                    .build())
            )
    );
    public static final Item COFFEE_MUG_BREW_HONEY = registerItem(
            "coffee_mug_brew_honey",
            new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.COFFEE_MUG,
                    Brews.HONEY,
                    new FabricItemSettings().group(RavenCoffeeItemGroups.COFFEE_MUG_TAB)
                            .food(new FoodComponent.Builder()
                                    .hunger(6)
                                    .saturationModifier(0.1f)
                                    .build())
            )
    );
    //           #######################################   CHOCOLATE   #############################################
    public static final Item CUP_MEDIUM_BREW_CHOCOLATE = registerItem(
            "cup_medium_brew_chocolate",
            new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.CUP_MEDIUM,
                    Brews.CHOCOLATE,
                    new FabricItemSettings().group(RavenCoffeeItemGroups.CUP_MEDIUM_TAB)
                            .food(new FoodComponent.Builder()
                                    .hunger(2)
                                    .saturationModifier(0.1f)
                                    .build())
            )
    );
    public static final Item CUP_LARGE_BREW_CHOCOLATE = registerItem(
            "cup_large_brew_chocolate",
            new CoffeeBrew(
                    CupType.LARGE,
                    RavenCoffeeItems.CUP_LARGE,
                    Brews.CHOCOLATE,
                    new FabricItemSettings().group(RavenCoffeeItemGroups.CUP_LARGE_TAB)
                            .food(new FoodComponent.Builder()
                                    .hunger(3)
                                    .saturationModifier(0.1f)
                                    .build())
            )
    );
    public static final Item COFFEE_MUG_BREW_CHOCOLATE = registerItem(
            "coffee_mug_brew_chocolate",
            new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.COFFEE_MUG,
                    Brews.CHOCOLATE,
                    new FabricItemSettings().group(RavenCoffeeItemGroups.COFFEE_MUG_TAB)
                            .food(new FoodComponent.Builder()
                                    .hunger(2)
                                    .saturationModifier(0.1f)
                                    .build())
            )
    );
    //           #######################################   MILK   #############################################
    public static final Item CUP_MEDIUM_BREW_MILK = registerItem(
            "cup_medium_brew_milk",
            new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.CUP_MEDIUM,
                    Brews.MILK,
                    new FabricItemSettings().group(RavenCoffeeItemGroups.CUP_MEDIUM_TAB)
            )
    );
    public static final Item CUP_LARGE_BREW_MILK = registerItem(
            "cup_large_brew_milk",
            new CoffeeBrew(
                    CupType.LARGE,
                    RavenCoffeeItems.CUP_LARGE,
                    Brews.MILK,
                    new FabricItemSettings().group(RavenCoffeeItemGroups.CUP_LARGE_TAB)
            )
    );
    public static final Item COFFEE_MUG_BREW_MILK = registerItem(
            "coffee_mug_brew_milk",
            new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.COFFEE_MUG,
                    Brews.MILK,
                    new FabricItemSettings().group(RavenCoffeeItemGroups.COFFEE_MUG_TAB)
            )
    );
    //           #######################################   MOCHA   #############################################
    public static final Item CUP_MEDIUM_BREW_MOCHA = registerItem(
            "cup_medium_brew_mocha",
            new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.CUP_MEDIUM,
                    Brews.MOCHA,
                    new FabricItemSettings().group(RavenCoffeeItemGroups.CUP_MEDIUM_TAB)
            )
    );
    public static final Item CUP_LARGE_BREW_MOCHA = registerItem(
            "cup_large_brew_mocha",
            new CoffeeBrew(
                    CupType.LARGE,
                    RavenCoffeeItems.CUP_LARGE,
                    Brews.MOCHA,
                    new FabricItemSettings().group(RavenCoffeeItemGroups.CUP_LARGE_TAB)
            )
    );
    public static final Item COFFEE_MUG_BREW_MOCHA = registerItem(
            "coffee_mug_brew_mocha",
            new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.COFFEE_MUG,
                    Brews.MOCHA,
                    new FabricItemSettings().group(RavenCoffeeItemGroups.COFFEE_MUG_TAB)
            )
    );
    //           #######################################   AWKWARD   #############################################
    public static final Item CUP_MEDIUM_BREW_AWKWARD = registerItem(
            "cup_medium_brew_awkward",
            new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.CUP_MEDIUM,
                    Brews.AWKWARD,
                    new FabricItemSettings().group(RavenCoffeeItemGroups.CUP_MEDIUM_TAB)
            )
    );
    public static final Item CUP_LARGE_BREW_AWKWARD = registerItem(
            "cup_large_brew_awkward",
            new CoffeeBrew(
                    CupType.LARGE,
                    RavenCoffeeItems.CUP_LARGE,
                    Brews.AWKWARD,
                    new FabricItemSettings().group(RavenCoffeeItemGroups.CUP_LARGE_TAB)
            )
    );
    public static final Item COFFEE_MUG_BREW_AWKWARD = registerItem(
            "coffee_mug_brew_awkward",
            new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.COFFEE_MUG,
                    Brews.AWKWARD,
                    new FabricItemSettings().group(RavenCoffeeItemGroups.COFFEE_MUG_TAB)
            )
    );
    //           #######################################   CARROT   #############################################
    public static final Item CUP_MEDIUM_BREW_CARROT = registerItem(
            "cup_medium_brew_carrot",
            new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.CUP_MEDIUM,
                    Brews.CARROT,
                    new FabricItemSettings().group(RavenCoffeeItemGroups.CUP_MEDIUM_TAB)
                            .food(new FoodComponent.Builder()
                                    .hunger(3)
                                    .saturationModifier(0.3f)
                                    .build())
            )
    );
    public static final Item CUP_LARGE_BREW_CARROT = registerItem(
            "cup_large_brew_carrot",
            new CoffeeBrew(
                    CupType.LARGE,
                    RavenCoffeeItems.CUP_LARGE,
                    Brews.CARROT,
                    new FabricItemSettings().group(RavenCoffeeItemGroups.CUP_LARGE_TAB)
                            .food(new FoodComponent.Builder()
                                    .hunger(3)
                                    .saturationModifier(0.3f)
                                    .build())
            )
    );
    public static final Item COFFEE_MUG_BREW_CARROT = registerItem(
            "coffee_mug_brew_carrot",
            new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.COFFEE_MUG,
                    Brews.CARROT,
                    new FabricItemSettings().group(RavenCoffeeItemGroups.COFFEE_MUG_TAB)
                            .food(new FoodComponent.Builder()
                                    .hunger(3)
                                    .saturationModifier(0.3f)
                                    .build())
            )
    );
    //           #######################################   CARROT_GOLDEN   #############################################
    public static final Item CUP_MEDIUM_BREW_CARROT_GOLDEN = registerItem(
            "cup_medium_brew_carrot_golden",
            new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.CUP_MEDIUM,
                    Brews.CARROT_GOLDEN,
                    new FabricItemSettings().group(RavenCoffeeItemGroups.CUP_MEDIUM_TAB)
                            .food(new FoodComponent.Builder()
                                    .hunger(6)
                                    .saturationModifier(0.8f)
                                    .build())
            )
    );
    public static final Item CUP_LARGE_BREW_CARROT_GOLDEN = registerItem(
            "cup_large_brew_carrot_golden",
            new CoffeeBrew(
                    CupType.LARGE,
                    RavenCoffeeItems.CUP_LARGE,
                    Brews.CARROT_GOLDEN,
                    new FabricItemSettings().group(RavenCoffeeItemGroups.CUP_LARGE_TAB)
                            .food(new FoodComponent.Builder()
                                    .hunger(9)
                                    .saturationModifier(0.8f)
                                    .build())
            )
    );
    public static final Item COFFEE_MUG_BREW_CARROT_GOLDEN = registerItem(
            "coffee_mug_brew_carrot_golden",
            new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.COFFEE_MUG,
                    Brews.CARROT_GOLDEN,
                    new FabricItemSettings().group(RavenCoffeeItemGroups.COFFEE_MUG_TAB)
                            .food(new FoodComponent.Builder()
                                    .hunger(6)
                                    .saturationModifier(0.8f)
                                    .build())
            )
    );
    //           #######################################   COOKIESANDCREAM   #############################################
    public static final Item CUP_MEDIUM_BREW_COOKIESANDCREAM = registerItem(
            "cup_medium_brew_cookiesandcream",
            new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.CUP_MEDIUM,
                    Brews.COOKIESANDCREAM,
                    new FabricItemSettings().group(RavenCoffeeItemGroups.CUP_MEDIUM_TAB)
                            .food(new FoodComponent.Builder()
                                    .hunger(4)
                                    .saturationModifier(0.1f)
                                    .build())
            )
    );
    public static final Item CUP_LARGE_BREW_COOKIESANDCREAM = registerItem(
            "cup_large_brew_cookiesandcream",
            new CoffeeBrew(
                    CupType.LARGE,
                    RavenCoffeeItems.CUP_LARGE,
                    Brews.COOKIESANDCREAM,
                    new FabricItemSettings().group(RavenCoffeeItemGroups.CUP_LARGE_TAB)
                            .food(new FoodComponent.Builder()
                                    .hunger(4)
                                    .saturationModifier(0.1f)
                                    .build())
            )
    );
    public static final Item COFFEE_MUG_BREW_COOKIESANDCREAM = registerItem(
            "coffee_mug_brew_cookiesandcream",
            new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.COFFEE_MUG,
                    Brews.COOKIESANDCREAM,
                    new FabricItemSettings().group(RavenCoffeeItemGroups.COFFEE_MUG_TAB)
                            .food(new FoodComponent.Builder()
                                    .hunger(4)
                                    .saturationModifier(0.1f)
                                    .build())
            )
    );
    //           #######################################   END   #############################################
    public static final Item CUP_MEDIUM_BREW_END = registerItem(
            "cup_medium_brew_end",
            new ChorusBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.CUP_MEDIUM,
                    Brews.END,
                    new FabricItemSettings().group(RavenCoffeeItemGroups.CUP_MEDIUM_TAB)
                            .food(new FoodComponent.Builder()
                                    .hunger(2)
                                    .saturationModifier(0.3f)
                                    .build())
            )
    );
    public static final Item CUP_LARGE_BREW_END = registerItem(
            "cup_large_brew_end",
            new ChorusBrew(
                    CupType.LARGE,
                    RavenCoffeeItems.CUP_LARGE,
                    Brews.END,
                    new FabricItemSettings().group(RavenCoffeeItemGroups.CUP_LARGE_TAB)
                            .food(new FoodComponent.Builder()
                                    .hunger(3)
                                    .saturationModifier(0.3f)
                                    .build())
            )
    );
    public static final Item COFFEE_MUG_BREW_END = registerItem(
            "coffee_mug_brew_end",
            new ChorusBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.COFFEE_MUG,
                    Brews.END,
                    new FabricItemSettings().group(RavenCoffeeItemGroups.COFFEE_MUG_TAB)
                            .food(new FoodComponent.Builder()
                                    .hunger(2)
                                    .saturationModifier(0.3f)
                                    .build())
            )
    );
    //           #######################################   MELON   #############################################
    public static final Item CUP_MEDIUM_BREW_MELON = registerItem(
            "cup_medium_brew_melon",
            new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.CUP_MEDIUM,
                    Brews.MELON,
                    new FabricItemSettings().group(RavenCoffeeItemGroups.CUP_MEDIUM_TAB)
                            .food(new FoodComponent.Builder()
                                    .hunger(2)
                                    .saturationModifier(0.3f)
                                    .build())
            )
    );
    public static final Item CUP_LARGE_BREW_MELON = registerItem(
            "cup_large_brew_melon",
            new CoffeeBrew(
                    CupType.LARGE,
                    RavenCoffeeItems.CUP_LARGE,
                    Brews.MELON,
                    new FabricItemSettings().group(RavenCoffeeItemGroups.CUP_LARGE_TAB)
                            .food(new FoodComponent.Builder()
                                    .hunger(3)
                                    .saturationModifier(0.3f)
                                    .build())
            )
    );
    public static final Item COFFEE_MUG_BREW_MELON = registerItem(
            "coffee_mug_brew_melon",
            new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.COFFEE_MUG,
                    Brews.MELON,
                    new FabricItemSettings().group(RavenCoffeeItemGroups.COFFEE_MUG_TAB)
                            .food(new FoodComponent.Builder()
                                    .hunger(2)
                                    .saturationModifier(0.3f)
                                    .build())
            )
    );
    //           #######################################   MELON_GOLDEN   #############################################
    public static final Item CUP_MEDIUM_BREW_MELON_GOLDEN = registerItem(
            "cup_medium_brew_melon_golden",
            new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.CUP_MEDIUM,
                    Brews.MELON_GOLDEN,
                    new FabricItemSettings().group(RavenCoffeeItemGroups.CUP_MEDIUM_TAB)
                            .food(new FoodComponent.Builder()
                                    .hunger(2)
                                    .saturationModifier(0.3f)
                                    .build())
            )
    );
    public static final Item CUP_LARGE_BREW_MELON_GOLDEN = registerItem(
            "cup_large_brew_melon_golden",
            new CoffeeBrew(
                    CupType.LARGE,
                    RavenCoffeeItems.CUP_LARGE,
                    Brews.MELON_GOLDEN,
                    new FabricItemSettings().group(RavenCoffeeItemGroups.CUP_LARGE_TAB)
                            .food(new FoodComponent.Builder()
                                    .hunger(3)
                                    .saturationModifier(0.3f)
                                    .build())
            )
    );
    public static final Item COFFEE_MUG_BREW_MELON_GOLDEN = registerItem(
            "coffee_mug_brew_melon_golden",
            new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.COFFEE_MUG,
                    Brews.MELON_GOLDEN,
                    new FabricItemSettings().group(RavenCoffeeItemGroups.COFFEE_MUG_TAB)
                            .food(new FoodComponent.Builder()
                                    .hunger(2)
                                    .saturationModifier(0.3f)
                                    .build())
            )
    );
    //           #######################################   NETHER   #############################################
    public static final Item CUP_MEDIUM_BREW_NETHER = registerItem(
            "cup_medium_brew_nether",
            new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.CUP_MEDIUM,
                    Brews.NETHER,
                    new FabricItemSettings().group(RavenCoffeeItemGroups.CUP_MEDIUM_TAB)
                            .food(new FoodComponent.Builder()
                                    .hunger(1)
                                    .saturationModifier(0.1f)
                                    .build())
            )
    );
    public static final Item CUP_LARGE_BREW_NETHER = registerItem(
            "cup_large_brew_nether",
            new CoffeeBrew(
                    CupType.LARGE,
                    RavenCoffeeItems.CUP_LARGE,
                    Brews.NETHER,
                    new FabricItemSettings().group(RavenCoffeeItemGroups.CUP_LARGE_TAB)
                            .food(new FoodComponent.Builder()
                                    .hunger(1)
                                    .saturationModifier(0.1f)
                                    .build())
            )
    );
    public static final Item COFFEE_MUG_BREW_NETHER = registerItem(
            "coffee_mug_brew_nether",
            new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.COFFEE_MUG,
                    Brews.NETHER,
                    new FabricItemSettings().group(RavenCoffeeItemGroups.COFFEE_MUG_TAB)
                            .food(new FoodComponent.Builder()
                                    .hunger(1)
                                    .saturationModifier(0.1f)
                                    .build())
            )
    );
    //           #######################################   PHANTASM   #############################################
    public static final Item CUP_MEDIUM_BREW_PHANTASM = registerItem(
            "cup_medium_brew_phantasm",
            new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.CUP_MEDIUM,
                    Brews.PHANTASM,
                    new FabricItemSettings().group(RavenCoffeeItemGroups.CUP_MEDIUM_TAB)
            )
    );
    public static final Item CUP_LARGE_BREW_PHANTASM = registerItem(
            "cup_large_brew_phantasm",
            new CoffeeBrew(
                    CupType.LARGE,
                    RavenCoffeeItems.CUP_LARGE,
                    Brews.PHANTASM,
                    new FabricItemSettings().group(RavenCoffeeItemGroups.CUP_LARGE_TAB)
            )
    );
    public static final Item COFFEE_MUG_BREW_PHANTASM = registerItem(
            "coffee_mug_brew_phantasm",
            new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.COFFEE_MUG,
                    Brews.PHANTASM,
                    new FabricItemSettings().group(RavenCoffeeItemGroups.COFFEE_MUG_TAB)
            )
    );
    //           #######################################   PUMPKINSPICELATTE   #############################################
    public static final Item CUP_MEDIUM_BREW_PUMPKINSPICELATTE = registerItem(
            "cup_medium_brew_pumpkinspicelatte",
            new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.CUP_MEDIUM,
                    Brews.PUMPKINSPICELATTE,
                    new FabricItemSettings().group(RavenCoffeeItemGroups.CUP_MEDIUM_TAB)
            )
    );
    public static final Item CUP_LARGE_BREW_PUMPKINSPICELATTE = registerItem(
            "cup_large_brew_pumpkinspicelatte",
            new CoffeeBrew(
                    CupType.LARGE,
                    RavenCoffeeItems.CUP_LARGE,
                    Brews.PUMPKINSPICELATTE,
                    new FabricItemSettings().group(RavenCoffeeItemGroups.CUP_LARGE_TAB)
            )
    );
    public static final Item COFFEE_MUG_BREW_PUMPKINSPICELATTE = registerItem(
            "coffee_mug_brew_pumpkinspicelatte",
            new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.COFFEE_MUG,
                    Brews.PUMPKINSPICELATTE,
                    new FabricItemSettings().group(RavenCoffeeItemGroups.COFFEE_MUG_TAB)
            )
    );
    //           #######################################   PUMPKINSPICELATTE_WITH_PUMPKIN   #############################################
    public static final Item CUP_MEDIUM_BREW_PUMPKINSPICELATTE_WITH_PUMPKIN = registerItem(
            "cup_medium_brew_pumpkinspicelatte_with_pumpkin",
            new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.CUP_MEDIUM,
                    Brews.PUMPKINSPICELATTE_WITH_PUMPKIN,
                    new FabricItemSettings().group(RavenCoffeeItemGroups.CUP_MEDIUM_TAB)
                            .food(new FoodComponent.Builder()
                                    .hunger(5)
                                    .saturationModifier(0.3f)
                                    .build())
            )
    );
    public static final Item CUP_LARGE_BREW_PUMPKINSPICELATTE_WITH_PUMPKIN = registerItem(
            "cup_large_brew_pumpkinspicelatte_with_pumpkin",
            new CoffeeBrew(
                    CupType.LARGE,
                    RavenCoffeeItems.CUP_LARGE,
                    Brews.PUMPKINSPICELATTE_WITH_PUMPKIN,
                    new FabricItemSettings().group(RavenCoffeeItemGroups.CUP_LARGE_TAB)
                            .food(new FoodComponent.Builder()
                                    .hunger(5)
                                    .saturationModifier(0.3f)
                                    .build())
            )
    );
    public static final Item COFFEE_MUG_BREW_PUMPKINSPICELATTE_WITH_PUMPKIN = registerItem(
            "coffee_mug_brew_pumpkinspicelatte_with_pumpkin",
            new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.COFFEE_MUG,
                    Brews.PUMPKINSPICELATTE_WITH_PUMPKIN,
                    new FabricItemSettings().group(RavenCoffeeItemGroups.COFFEE_MUG_TAB)
                            .food(new FoodComponent.Builder()
                                    .hunger(5)
                                    .saturationModifier(0.3f)
                                    .build())
            )
    );


    private static net.minecraft.item.Item registerItem(String name, net.minecraft.item.Item item) {
        return Registry.register(Registry.ITEM, new Identifier(Constants.MOD_ID, name), item);
    }
    public static void register() {
        Constants.LOGGER.info(Constants.MOD_NAME + " has registered its items!");
    }
}
