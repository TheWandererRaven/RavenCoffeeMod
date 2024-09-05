package com.thewandererraven.ravencoffee.items;

import com.thewandererraven.ravenbrewscore.CupType;
import com.thewandererraven.ravencoffee.Constants;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class RavenCoffeeBrewItems {

    // ################################################# COFFEE BREWS ##################################################
    //           #######################################   BASIC   #############################################
    public static final Item CUP_SMALL_BREW_BASIC = registerItem(
            "cup_small_brew_basic",
            new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.CUP_SMALL,
                    Brews.ESPRESSO,
                    new FabricItemSettings()
            )
    );
    public static final Item CUP_MEDIUM_BREW_BASIC = registerItem(
            "cup_medium_brew_basic",
            new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.CUP_MEDIUM,
                    Brews.BASIC,
                    new FabricItemSettings()
            )
    );
    public static final Item CUP_LARGE_BREW_BASIC = registerItem(
            "cup_large_brew_basic",
            new CoffeeBrew(
                    CupType.LARGE,
                    RavenCoffeeItems.CUP_LARGE,
                    Brews.BASIC,
                    new FabricItemSettings()
            )
    );
    public static final Item COFFEE_MUG_BREW_BASIC = registerItem(
            "coffee_mug_brew_basic",
            new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.COFFEE_MUG,
                    Brews.BASIC,
                    new FabricItemSettings()
            )
    );
    //           #######################################   SUGAR   #############################################
    public static final Item CUP_MEDIUM_BREW_SUGAR = registerItem(
            "cup_medium_brew_sugar",
            new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.CUP_MEDIUM,
                    Brews.SUGAR,
                    new FabricItemSettings()
            )
    );
    public static final Item CUP_LARGE_BREW_SUGAR = registerItem(
            "cup_large_brew_sugar",
            new CoffeeBrew(
                    CupType.LARGE,
                    RavenCoffeeItems.CUP_LARGE,
                    Brews.SUGAR,
                    new FabricItemSettings()
            )
    );
    public static final Item COFFEE_MUG_BREW_SUGAR = registerItem(
            "coffee_mug_brew_sugar",
            new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.COFFEE_MUG,
                    Brews.SUGAR,
                    new FabricItemSettings()
            )
    );
    //           #######################################   APPLE   #############################################
    public static final Item CUP_MEDIUM_BREW_APPLE = registerItem(
            "cup_medium_brew_apple",
            new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.CUP_MEDIUM,
                    Brews.APPLE,
                    new FabricItemSettings()
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
                    new FabricItemSettings()
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
                    new FabricItemSettings()
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
                    new FabricItemSettings()
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
                    new FabricItemSettings()
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
                    new FabricItemSettings()
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
                    new FabricItemSettings()
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
                    new FabricItemSettings()
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
                    new FabricItemSettings()
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
                    new FabricItemSettings()
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
                    new FabricItemSettings()
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
                    new FabricItemSettings()
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
                    new FabricItemSettings()
            )
    );
    public static final Item CUP_LARGE_BREW_MILK = registerItem(
            "cup_large_brew_milk",
            new CoffeeBrew(
                    CupType.LARGE,
                    RavenCoffeeItems.CUP_LARGE,
                    Brews.MILK,
                    new FabricItemSettings()
            )
    );
    public static final Item COFFEE_MUG_BREW_MILK = registerItem(
            "coffee_mug_brew_milk",
            new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.COFFEE_MUG,
                    Brews.MILK,
                    new FabricItemSettings()
            )
    );
    //           #######################################   MOCHA   #############################################
    public static final Item CUP_MEDIUM_BREW_MOCHA = registerItem(
            "cup_medium_brew_mocha",
            new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.CUP_MEDIUM,
                    Brews.MOCHA,
                    new FabricItemSettings()
            )
    );
    public static final Item CUP_LARGE_BREW_MOCHA = registerItem(
            "cup_large_brew_mocha",
            new CoffeeBrew(
                    CupType.LARGE,
                    RavenCoffeeItems.CUP_LARGE,
                    Brews.MOCHA,
                    new FabricItemSettings()
            )
    );
    public static final Item COFFEE_MUG_BREW_MOCHA = registerItem(
            "coffee_mug_brew_mocha",
            new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.COFFEE_MUG,
                    Brews.MOCHA,
                    new FabricItemSettings()
            )
    );
    //           #######################################   AWKWARD   #############################################
    public static final Item CUP_MEDIUM_BREW_AWKWARD = registerItem(
            "cup_medium_brew_awkward",
            new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.CUP_MEDIUM,
                    Brews.AWKWARD,
                    new FabricItemSettings()
            )
    );
    public static final Item CUP_LARGE_BREW_AWKWARD = registerItem(
            "cup_large_brew_awkward",
            new CoffeeBrew(
                    CupType.LARGE,
                    RavenCoffeeItems.CUP_LARGE,
                    Brews.AWKWARD,
                    new FabricItemSettings()
            )
    );
    public static final Item COFFEE_MUG_BREW_AWKWARD = registerItem(
            "coffee_mug_brew_awkward",
            new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.COFFEE_MUG,
                    Brews.AWKWARD,
                    new FabricItemSettings()
            )
    );
    //           #######################################   CARROT   #############################################
    public static final Item CUP_MEDIUM_BREW_CARROT = registerItem(
            "cup_medium_brew_carrot",
            new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.CUP_MEDIUM,
                    Brews.CARROT,
                    new FabricItemSettings()
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
                    new FabricItemSettings()
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
                    new FabricItemSettings()
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
                    new FabricItemSettings()
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
                    new FabricItemSettings()
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
                    new FabricItemSettings()
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
                    new FabricItemSettings()
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
                    new FabricItemSettings()
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
                    new FabricItemSettings()
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
                    new FabricItemSettings()
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
                    new FabricItemSettings()
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
                    new FabricItemSettings()
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
                    new FabricItemSettings()
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
                    new FabricItemSettings()
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
                    new FabricItemSettings()
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
                    new FabricItemSettings()
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
                    new FabricItemSettings()
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
                    new FabricItemSettings()
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
                    new FabricItemSettings()
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
                    new FabricItemSettings()
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
                    new FabricItemSettings()
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
                    new FabricItemSettings()
            )
    );
    public static final Item CUP_LARGE_BREW_PHANTASM = registerItem(
            "cup_large_brew_phantasm",
            new CoffeeBrew(
                    CupType.LARGE,
                    RavenCoffeeItems.CUP_LARGE,
                    Brews.PHANTASM,
                    new FabricItemSettings()
            )
    );
    public static final Item COFFEE_MUG_BREW_PHANTASM = registerItem(
            "coffee_mug_brew_phantasm",
            new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.COFFEE_MUG,
                    Brews.PHANTASM,
                    new FabricItemSettings()
            )
    );
    //           #######################################   PUMPKINSPICELATTE   #############################################
    public static final Item CUP_MEDIUM_BREW_PUMPKINSPICELATTE = registerItem(
            "cup_medium_brew_pumpkinspicelatte",
            new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.CUP_MEDIUM,
                    Brews.PUMPKINSPICELATTE,
                    new FabricItemSettings()
            )
    );
    public static final Item CUP_LARGE_BREW_PUMPKINSPICELATTE = registerItem(
            "cup_large_brew_pumpkinspicelatte",
            new CoffeeBrew(
                    CupType.LARGE,
                    RavenCoffeeItems.CUP_LARGE,
                    Brews.PUMPKINSPICELATTE,
                    new FabricItemSettings()
            )
    );
    public static final Item COFFEE_MUG_BREW_PUMPKINSPICELATTE = registerItem(
            "coffee_mug_brew_pumpkinspicelatte",
            new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.COFFEE_MUG,
                    Brews.PUMPKINSPICELATTE,
                    new FabricItemSettings()
            )
    );
    //           #######################################   PUMPKINSPICELATTE_WITH_PUMPKIN   #############################################
    public static final Item CUP_MEDIUM_BREW_PUMPKINSPICELATTE_WITH_PUMPKIN = registerItem(
            "cup_medium_brew_pumpkinspicelatte_with_pumpkin",
            new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.CUP_MEDIUM,
                    Brews.PUMPKINSPICELATTE_WITH_PUMPKIN,
                    new FabricItemSettings()
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
                    new FabricItemSettings()
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
                    new FabricItemSettings()
                            .food(new FoodComponent.Builder()
                                    .hunger(5)
                                    .saturationModifier(0.3f)
                                    .build())
            )
    );


    private static net.minecraft.item.Item registerItem(String name, net.minecraft.item.Item item) {
        return Registry.register(Registries.ITEM, new Identifier(Constants.MOD_ID, name), item);
    }

    private static void addItemsToGeneralTab(FabricItemGroupEntries entries) {
    }

    private static void addItemsToSmallBrewsTab(FabricItemGroupEntries entries) {
        entries.add(CUP_SMALL_BREW_BASIC);
    }

    private static void addItemsToMediumBrewsTab(FabricItemGroupEntries entries) {
        entries.add(CUP_MEDIUM_BREW_BASIC);
        entries.add(CUP_MEDIUM_BREW_SUGAR);
        entries.add(CUP_MEDIUM_BREW_APPLE);
        entries.add(CUP_MEDIUM_BREW_BERRY);
        entries.add(CUP_MEDIUM_BREW_HONEY);
        entries.add(CUP_MEDIUM_BREW_CHOCOLATE);
        entries.add(CUP_MEDIUM_BREW_MILK);
        entries.add(CUP_MEDIUM_BREW_MOCHA);
        entries.add(CUP_MEDIUM_BREW_AWKWARD);
        entries.add(CUP_MEDIUM_BREW_CARROT);
        entries.add(CUP_MEDIUM_BREW_CARROT_GOLDEN);
        entries.add(CUP_MEDIUM_BREW_COOKIESANDCREAM);
        entries.add(CUP_MEDIUM_BREW_END);
        entries.add(CUP_MEDIUM_BREW_MELON);
        entries.add(CUP_MEDIUM_BREW_MELON_GOLDEN);
        entries.add(CUP_MEDIUM_BREW_NETHER);
        entries.add(CUP_MEDIUM_BREW_PHANTASM);
        entries.add(CUP_MEDIUM_BREW_PUMPKINSPICELATTE);
        entries.add(CUP_MEDIUM_BREW_PUMPKINSPICELATTE_WITH_PUMPKIN);
    }

    private static void addItemsToLargeBrewsTab(FabricItemGroupEntries entries) {
        entries.add(CUP_LARGE_BREW_BASIC);
        entries.add(CUP_LARGE_BREW_SUGAR);
        entries.add(CUP_LARGE_BREW_APPLE);
        entries.add(CUP_LARGE_BREW_BERRY);
        entries.add(CUP_LARGE_BREW_HONEY);
        entries.add(CUP_LARGE_BREW_CHOCOLATE);
        entries.add(CUP_LARGE_BREW_MILK);
        entries.add(CUP_LARGE_BREW_MOCHA);
        entries.add(CUP_LARGE_BREW_AWKWARD);
        entries.add(CUP_LARGE_BREW_CARROT);
        entries.add(CUP_LARGE_BREW_CARROT_GOLDEN);
        entries.add(CUP_LARGE_BREW_COOKIESANDCREAM);
        entries.add(CUP_LARGE_BREW_END);
        entries.add(CUP_LARGE_BREW_MELON);
        entries.add(CUP_LARGE_BREW_MELON_GOLDEN);
        entries.add(CUP_LARGE_BREW_NETHER);
        entries.add(CUP_LARGE_BREW_PHANTASM);
        entries.add(CUP_LARGE_BREW_PUMPKINSPICELATTE);
        entries.add(CUP_LARGE_BREW_PUMPKINSPICELATTE_WITH_PUMPKIN);
    }

    private static void addItemsToMugBrewsTab(FabricItemGroupEntries entries) {
        entries.add(COFFEE_MUG_BREW_BASIC);
        entries.add(COFFEE_MUG_BREW_SUGAR);
        entries.add(COFFEE_MUG_BREW_APPLE);
        entries.add(COFFEE_MUG_BREW_BERRY);
        entries.add(COFFEE_MUG_BREW_HONEY);
        entries.add(COFFEE_MUG_BREW_CHOCOLATE);
        entries.add(COFFEE_MUG_BREW_MILK);
        entries.add(COFFEE_MUG_BREW_MOCHA);
        entries.add(COFFEE_MUG_BREW_AWKWARD);
        entries.add(COFFEE_MUG_BREW_CARROT);
        entries.add(COFFEE_MUG_BREW_CARROT_GOLDEN);
        entries.add(COFFEE_MUG_BREW_COOKIESANDCREAM);
        entries.add(COFFEE_MUG_BREW_END);
        entries.add(COFFEE_MUG_BREW_MELON);
        entries.add(COFFEE_MUG_BREW_MELON_GOLDEN);
        entries.add(COFFEE_MUG_BREW_NETHER);
        entries.add(COFFEE_MUG_BREW_PHANTASM);
        entries.add(COFFEE_MUG_BREW_PUMPKINSPICELATTE);
        entries.add(COFFEE_MUG_BREW_PUMPKINSPICELATTE_WITH_PUMPKIN);
    }

    public static void register() {
        ItemGroupEvents.modifyEntriesEvent(RavenCoffeeItemGroups.GENERAL_TAB).register(RavenCoffeeBrewItems::addItemsToGeneralTab);
        ItemGroupEvents.modifyEntriesEvent(RavenCoffeeItemGroups.CUP_SMALL_TAB).register(RavenCoffeeBrewItems::addItemsToSmallBrewsTab);
        ItemGroupEvents.modifyEntriesEvent(RavenCoffeeItemGroups.CUP_MEDIUM_TAB).register(RavenCoffeeBrewItems::addItemsToMediumBrewsTab);
        ItemGroupEvents.modifyEntriesEvent(RavenCoffeeItemGroups.CUP_LARGE_TAB).register(RavenCoffeeBrewItems::addItemsToLargeBrewsTab);
        ItemGroupEvents.modifyEntriesEvent(RavenCoffeeItemGroups.COFFEE_MUG_TAB).register(RavenCoffeeBrewItems::addItemsToMugBrewsTab);
        Constants.LOGGER.info(Constants.MOD_NAME + " has registered its items!");
    }
}
