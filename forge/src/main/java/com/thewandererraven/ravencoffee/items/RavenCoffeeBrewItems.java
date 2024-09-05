package com.thewandererraven.ravencoffee.items;

import com.thewandererraven.ravencoffee.Constants;
import com.thewandererraven.ravencoffee.RavenCoffeeForge;
import com.thewandererraven.ravenbrewscore.CupType;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RavenCoffeeBrewItems {
    public static final DeferredRegister<Item> BREWS = DeferredRegister.create(
            ForgeRegistries.ITEMS,
            Constants.MOD_ID
    );

    // ################################################# COFFEE BREWS ##################################################
    //           #######################################   BASIC   #############################################
    public static final RegistryObject<Item> CUP_SMALL_BREW_BASIC = BREWS.register(
            "cup_small_brew_basic",
            () -> new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.CUP_SMALL.get(),
                    Brews.ESPRESSO,
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> CUP_MEDIUM_BREW_BASIC = BREWS.register(
            "cup_medium_brew_basic",
            () -> new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.CUP_MEDIUM.get(),
                    Brews.BASIC,
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> CUP_LARGE_BREW_BASIC = BREWS.register(
            "cup_large_brew_basic",
            () -> new CoffeeBrew(
                    CupType.LARGE,
                    RavenCoffeeItems.CUP_LARGE.get(),
                    Brews.BASIC,
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> COFFEE_MUG_BREW_BASIC = BREWS.register(
            "coffee_mug_brew_basic",
            () -> new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.COFFEE_MUG.get(),
                    Brews.BASIC,
                    new Item.Properties()
            )
    );
    //           #######################################   SUGAR   #############################################
    public static final RegistryObject<Item> CUP_MEDIUM_BREW_SUGAR = BREWS.register(
            "cup_medium_brew_sugar",
            () -> new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.CUP_MEDIUM.get(),
                    Brews.SUGAR,
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> CUP_LARGE_BREW_SUGAR = BREWS.register(
            "cup_large_brew_sugar",
            () -> new CoffeeBrew(
                    CupType.LARGE,
                    RavenCoffeeItems.CUP_LARGE.get(),
                    Brews.SUGAR,
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> COFFEE_MUG_BREW_SUGAR = BREWS.register(
            "coffee_mug_brew_sugar",
            () -> new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.COFFEE_MUG.get(),
                    Brews.SUGAR,
                    new Item.Properties()
            )
    );
    //           #######################################   APPLE   #############################################
    public static final RegistryObject<Item> CUP_MEDIUM_BREW_APPLE = BREWS.register(
            "cup_medium_brew_apple",
            () -> new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.CUP_MEDIUM.get(),
                    Brews.APPLE,
                    new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(2)
                            .saturationMod(0.3f)
                            .build())
            )
    );
    public static final RegistryObject<Item> CUP_LARGE_BREW_APPLE = BREWS.register(
            "cup_large_brew_apple",
            () -> new CoffeeBrew(
                    CupType.LARGE,
                    RavenCoffeeItems.CUP_LARGE.get(),
                    Brews.APPLE,
                    new Item.Properties()
                            .food(new FoodProperties.Builder()
                                    .nutrition(3)
                                    .saturationMod(0.3f)
                                    .build())
            )
    );
    public static final RegistryObject<Item> COFFEE_MUG_BREW_APPLE = BREWS.register(
            "coffee_mug_brew_apple",
            () -> new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.COFFEE_MUG.get(),
                    Brews.APPLE,
                    new Item.Properties()
                            .food(new FoodProperties.Builder()
                                    .nutrition(2)
                                    .saturationMod(0.3f)
                                    .build())
            )
    );
    //           #######################################   BERRY   #############################################
    public static final RegistryObject<Item> CUP_MEDIUM_BREW_BERRY = BREWS.register(
            "cup_medium_brew_berry",
            () -> new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.CUP_MEDIUM.get(),
                    Brews.BERRY,
                    new Item.Properties()
                            .food(new FoodProperties.Builder()
                                    .nutrition(1)
                                    .saturationMod(0.1f)
                                    .build())
            )
    );
    public static final RegistryObject<Item> CUP_LARGE_BREW_BERRY = BREWS.register(
            "cup_large_brew_berry",
            () -> new CoffeeBrew(
                    CupType.LARGE,
                    RavenCoffeeItems.CUP_LARGE.get(),
                    Brews.BERRY,
                    new Item.Properties()
                            .food(new FoodProperties.Builder()
                                    .nutrition(2)
                                    .saturationMod(0.1f)
                                    .build())
            )
    );
    public static final RegistryObject<Item> COFFEE_MUG_BREW_BERRY = BREWS.register(
            "coffee_mug_brew_berry",
            () -> new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.COFFEE_MUG.get(),
                    Brews.BERRY,
                    new Item.Properties()
                            .food(new FoodProperties.Builder()
                                    .nutrition(1)
                                    .saturationMod(0.1f)
                                    .build())
            )
    );
    //           #######################################   HONEY   #############################################
    public static final RegistryObject<Item> CUP_MEDIUM_BREW_HONEY = BREWS.register(
            "cup_medium_brew_honey",
            () -> new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.CUP_MEDIUM.get(),
                    Brews.HONEY,
                    new Item.Properties()
                            .food(new FoodProperties.Builder()
                                    .nutrition(6)
                                    .saturationMod(0.1f)
                                    .build())
            )
    );
    public static final RegistryObject<Item> CUP_LARGE_BREW_HONEY = BREWS.register(
            "cup_large_brew_honey",
            () -> new CoffeeBrew(
                    CupType.LARGE,
                    RavenCoffeeItems.CUP_LARGE.get(),
                    Brews.HONEY,
                    new Item.Properties()
                            .food(new FoodProperties.Builder()
                                    .nutrition(9)
                                    .saturationMod(0.1f)
                                    .build())
            )
    );
    public static final RegistryObject<Item> COFFEE_MUG_BREW_HONEY = BREWS.register(
            "coffee_mug_brew_honey",
            () -> new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.COFFEE_MUG.get(),
                    Brews.HONEY,
                    new Item.Properties()
                            .food(new FoodProperties.Builder()
                                    .nutrition(6)
                                    .saturationMod(0.1f)
                                    .build())
            )
    );
    //           #######################################   CHOCOLATE   #############################################
    public static final RegistryObject<Item> CUP_MEDIUM_BREW_CHOCOLATE = BREWS.register(
            "cup_medium_brew_chocolate",
            () -> new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.CUP_MEDIUM.get(),
                    Brews.CHOCOLATE,
                    new Item.Properties()
                            .food(new FoodProperties.Builder()
                                    .nutrition(2)
                                    .saturationMod(0.1f)
                                    .build())
            )
    );
    public static final RegistryObject<Item> CUP_LARGE_BREW_CHOCOLATE = BREWS.register(
            "cup_large_brew_chocolate",
            () -> new CoffeeBrew(
                    CupType.LARGE,
                    RavenCoffeeItems.CUP_LARGE.get(),
                    Brews.CHOCOLATE,
                    new Item.Properties()
                            .food(new FoodProperties.Builder()
                                    .nutrition(3)
                                    .saturationMod(0.1f)
                                    .build())
            )
    );
    public static final RegistryObject<Item> COFFEE_MUG_BREW_CHOCOLATE = BREWS.register(
            "coffee_mug_brew_chocolate",
            () -> new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.COFFEE_MUG.get(),
                    Brews.CHOCOLATE,
                    new Item.Properties()
                            .food(new FoodProperties.Builder()
                                    .nutrition(2)
                                    .saturationMod(0.1f)
                                    .build())
            )
    );
    //           #######################################   MILK   #############################################
    public static final RegistryObject<Item> CUP_MEDIUM_BREW_MILK = BREWS.register(
            "cup_medium_brew_milk",
            () -> new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.CUP_MEDIUM.get(),
                    Brews.MILK,
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> CUP_LARGE_BREW_MILK = BREWS.register(
            "cup_large_brew_milk",
            () -> new CoffeeBrew(
                    CupType.LARGE,
                    RavenCoffeeItems.CUP_LARGE.get(),
                    Brews.MILK,
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> COFFEE_MUG_BREW_MILK = BREWS.register(
            "coffee_mug_brew_milk",
            () -> new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.COFFEE_MUG.get(),
                    Brews.MILK,
                    new Item.Properties()
            )
    );
    //           #######################################   MOCHA   #############################################
    public static final RegistryObject<Item> CUP_MEDIUM_BREW_MOCHA = BREWS.register(
            "cup_medium_brew_mocha",
            () -> new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.CUP_MEDIUM.get(),
                    Brews.MOCHA,
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> CUP_LARGE_BREW_MOCHA = BREWS.register(
            "cup_large_brew_mocha",
            () -> new CoffeeBrew(
                    CupType.LARGE,
                    RavenCoffeeItems.CUP_LARGE.get(),
                    Brews.MOCHA,
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> COFFEE_MUG_BREW_MOCHA = BREWS.register(
            "coffee_mug_brew_mocha",
            () -> new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.COFFEE_MUG.get(),
                    Brews.MOCHA,
                    new Item.Properties()
            )
    );
    //           #######################################   AWKWARD   #############################################
    public static final RegistryObject<Item> CUP_MEDIUM_BREW_AWKWARD = BREWS.register(
            "cup_medium_brew_awkward",
            () -> new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.CUP_MEDIUM.get(),
                    Brews.AWKWARD,
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> CUP_LARGE_BREW_AWKWARD = BREWS.register(
            "cup_large_brew_awkward",
            () -> new CoffeeBrew(
                    CupType.LARGE,
                    RavenCoffeeItems.CUP_LARGE.get(),
                    Brews.AWKWARD,
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> COFFEE_MUG_BREW_AWKWARD = BREWS.register(
            "coffee_mug_brew_awkward",
            () -> new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.COFFEE_MUG.get(),
                    Brews.AWKWARD,
                    new Item.Properties()
            )
    );
    //           #######################################   CARROT   #############################################
    public static final RegistryObject<Item> CUP_MEDIUM_BREW_CARROT = BREWS.register(
            "cup_medium_brew_carrot",
            () -> new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.CUP_MEDIUM.get(),
                    Brews.CARROT,
                    new Item.Properties()
                            .food(new FoodProperties.Builder()
                                    .nutrition(3)
                                    .saturationMod(0.3f)
                                    .build())
            )
    );
    public static final RegistryObject<Item> CUP_LARGE_BREW_CARROT = BREWS.register(
            "cup_large_brew_carrot",
            () -> new CoffeeBrew(
                    CupType.LARGE,
                    RavenCoffeeItems.CUP_LARGE.get(),
                    Brews.CARROT,
                    new Item.Properties()
                            .food(new FoodProperties.Builder()
                                    .nutrition(3)
                                    .saturationMod(0.3f)
                                    .build())
            )
    );
    public static final RegistryObject<Item> COFFEE_MUG_BREW_CARROT = BREWS.register(
            "coffee_mug_brew_carrot",
            () -> new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.COFFEE_MUG.get(),
                    Brews.CARROT,
                    new Item.Properties()
                            .food(new FoodProperties.Builder()
                                    .nutrition(3)
                                    .saturationMod(0.3f)
                                    .build())
            )
    );
    //           #######################################   CARROT_GOLDEN   #############################################
    public static final RegistryObject<Item> CUP_MEDIUM_BREW_CARROT_GOLDEN = BREWS.register(
            "cup_medium_brew_carrot_golden",
            () -> new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.CUP_MEDIUM.get(),
                    Brews.CARROT_GOLDEN,
                    new Item.Properties()
                            .food(new FoodProperties.Builder()
                                    .nutrition(6)
                                    .saturationMod(0.8f)
                                    .build())
            )
    );
    public static final RegistryObject<Item> CUP_LARGE_BREW_CARROT_GOLDEN = BREWS.register(
            "cup_large_brew_carrot_golden",
            () -> new CoffeeBrew(
                    CupType.LARGE,
                    RavenCoffeeItems.CUP_LARGE.get(),
                    Brews.CARROT_GOLDEN,
                    new Item.Properties()
                            .food(new FoodProperties.Builder()
                                    .nutrition(9)
                                    .saturationMod(0.8f)
                                    .build())
            )
    );
    public static final RegistryObject<Item> COFFEE_MUG_BREW_CARROT_GOLDEN = BREWS.register(
            "coffee_mug_brew_carrot_golden",
            () -> new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.COFFEE_MUG.get(),
                    Brews.CARROT_GOLDEN,
                    new Item.Properties()
                            .food(new FoodProperties.Builder()
                                    .nutrition(6)
                                    .saturationMod(0.8f)
                                    .build())
            )
    );
    //           #######################################   COOKIESANDCREAM   #############################################
    public static final RegistryObject<Item> CUP_MEDIUM_BREW_COOKIESANDCREAM = BREWS.register(
            "cup_medium_brew_cookiesandcream",
            () -> new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.CUP_MEDIUM.get(),
                    Brews.COOKIESANDCREAM,
                    new Item.Properties()
                            .food(new FoodProperties.Builder()
                                    .nutrition(4)
                                    .saturationMod(0.1f)
                                    .build())
            )
    );
    public static final RegistryObject<Item> CUP_LARGE_BREW_COOKIESANDCREAM = BREWS.register(
            "cup_large_brew_cookiesandcream",
            () -> new CoffeeBrew(
                    CupType.LARGE,
                    RavenCoffeeItems.CUP_LARGE.get(),
                    Brews.COOKIESANDCREAM,
                    new Item.Properties()
                            .food(new FoodProperties.Builder()
                                    .nutrition(4)
                                    .saturationMod(0.1f)
                                    .build())
            )
    );
    public static final RegistryObject<Item> COFFEE_MUG_BREW_COOKIESANDCREAM = BREWS.register(
            "coffee_mug_brew_cookiesandcream",
            () -> new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.COFFEE_MUG.get(),
                    Brews.COOKIESANDCREAM,
                    new Item.Properties()
                            .food(new FoodProperties.Builder()
                                    .nutrition(4)
                                    .saturationMod(0.1f)
                                    .build())
            )
    );
    //           #######################################   END   #############################################
    public static final RegistryObject<Item> CUP_MEDIUM_BREW_END = BREWS.register(
            "cup_medium_brew_end",
            () -> new ChorusBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.CUP_MEDIUM.get(),
                    Brews.END,
                    new Item.Properties()
                            .food(new FoodProperties.Builder()
                                    .nutrition(2)
                                    .saturationMod(0.3f)
                                    .build())
            )
    );
    public static final RegistryObject<Item> CUP_LARGE_BREW_END = BREWS.register(
            "cup_large_brew_end",
            () -> new ChorusBrew(
                    CupType.LARGE,
                    RavenCoffeeItems.CUP_LARGE.get(),
                    Brews.END,
                    new Item.Properties()
                            .food(new FoodProperties.Builder()
                                    .nutrition(3)
                                    .saturationMod(0.3f)
                                    .build())
            )
    );
    public static final RegistryObject<Item> COFFEE_MUG_BREW_END = BREWS.register(
            "coffee_mug_brew_end",
            () -> new ChorusBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.COFFEE_MUG.get(),
                    Brews.END,
                    new Item.Properties()
                            .food(new FoodProperties.Builder()
                                    .nutrition(2)
                                    .saturationMod(0.3f)
                                    .build())
            )
    );
    //           #######################################   MELON   #############################################
    public static final RegistryObject<Item> CUP_MEDIUM_BREW_MELON = BREWS.register(
            "cup_medium_brew_melon",
            () -> new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.CUP_MEDIUM.get(),
                    Brews.MELON,
                    new Item.Properties()
                            .food(new FoodProperties.Builder()
                                    .nutrition(2)
                                    .saturationMod(0.3f)
                                    .build())
            )
    );
    public static final RegistryObject<Item> CUP_LARGE_BREW_MELON = BREWS.register(
            "cup_large_brew_melon",
            () -> new CoffeeBrew(
                    CupType.LARGE,
                    RavenCoffeeItems.CUP_LARGE.get(),
                    Brews.MELON,
                    new Item.Properties()
                            .food(new FoodProperties.Builder()
                                    .nutrition(3)
                                    .saturationMod(0.3f)
                                    .build())
            )
    );
    public static final RegistryObject<Item> COFFEE_MUG_BREW_MELON = BREWS.register(
            "coffee_mug_brew_melon",
            () -> new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.COFFEE_MUG.get(),
                    Brews.MELON,
                    new Item.Properties()
                            .food(new FoodProperties.Builder()
                                    .nutrition(2)
                                    .saturationMod(0.3f)
                                    .build())
            )
    );
    //           #######################################   MELON_GOLDEN   #############################################
    public static final RegistryObject<Item> CUP_MEDIUM_BREW_MELON_GOLDEN = BREWS.register(
            "cup_medium_brew_melon_golden",
            () -> new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.CUP_MEDIUM.get(),
                    Brews.MELON_GOLDEN,
                    new Item.Properties()
                            .food(new FoodProperties.Builder()
                                    .nutrition(2)
                                    .saturationMod(0.3f)
                                    .build())
            )
    );
    public static final RegistryObject<Item> CUP_LARGE_BREW_MELON_GOLDEN = BREWS.register(
            "cup_large_brew_melon_golden",
            () -> new CoffeeBrew(
                    CupType.LARGE,
                    RavenCoffeeItems.CUP_LARGE.get(),
                    Brews.MELON_GOLDEN,
                    new Item.Properties()
                            .food(new FoodProperties.Builder()
                                    .nutrition(3)
                                    .saturationMod(0.3f)
                                    .build())
            )
    );
    public static final RegistryObject<Item> COFFEE_MUG_BREW_MELON_GOLDEN = BREWS.register(
            "coffee_mug_brew_melon_golden",
            () -> new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.COFFEE_MUG.get(),
                    Brews.MELON_GOLDEN,
                    new Item.Properties()
                            .food(new FoodProperties.Builder()
                                    .nutrition(2)
                                    .saturationMod(0.3f)
                                    .build())
            )
    );
    //           #######################################   NETHER   #############################################
    public static final RegistryObject<Item> CUP_MEDIUM_BREW_NETHER = BREWS.register(
            "cup_medium_brew_nether",
            () -> new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.CUP_MEDIUM.get(),
                    Brews.NETHER,
                    new Item.Properties()
                            .food(new FoodProperties.Builder()
                                    .nutrition(1)
                                    .saturationMod(0.1f)
                                    .build())
            )
    );
    public static final RegistryObject<Item> CUP_LARGE_BREW_NETHER = BREWS.register(
            "cup_large_brew_nether",
            () -> new CoffeeBrew(
                    CupType.LARGE,
                    RavenCoffeeItems.CUP_LARGE.get(),
                    Brews.NETHER,
                    new Item.Properties()
                            .food(new FoodProperties.Builder()
                                    .nutrition(1)
                                    .saturationMod(0.1f)
                                    .build())
            )
    );
    public static final RegistryObject<Item> COFFEE_MUG_BREW_NETHER = BREWS.register(
            "coffee_mug_brew_nether",
            () -> new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.COFFEE_MUG.get(),
                    Brews.NETHER,
                    new Item.Properties()
                            .food(new FoodProperties.Builder()
                                    .nutrition(1)
                                    .saturationMod(0.1f)
                                    .build())
            )
    );
    //           #######################################   PHANTASM   #############################################
    public static final RegistryObject<Item> CUP_MEDIUM_BREW_PHANTASM = BREWS.register(
            "cup_medium_brew_phantasm",
            () -> new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.CUP_MEDIUM.get(),
                    Brews.PHANTASM,
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> CUP_LARGE_BREW_PHANTASM = BREWS.register(
            "cup_large_brew_phantasm",
            () -> new CoffeeBrew(
                    CupType.LARGE,
                    RavenCoffeeItems.CUP_LARGE.get(),
                    Brews.PHANTASM,
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> COFFEE_MUG_BREW_PHANTASM = BREWS.register(
            "coffee_mug_brew_phantasm",
            () -> new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.COFFEE_MUG.get(),
                    Brews.PHANTASM,
                    new Item.Properties()
            )
    );
    //           #######################################   PUMPKINSPICELATTE   #############################################
    public static final RegistryObject<Item> CUP_MEDIUM_BREW_PUMPKINSPICELATTE = BREWS.register(
            "cup_medium_brew_pumpkinspicelatte",
            () -> new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.CUP_MEDIUM.get(),
                    Brews.PUMPKINSPICELATTE,
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> CUP_LARGE_BREW_PUMPKINSPICELATTE = BREWS.register(
            "cup_large_brew_pumpkinspicelatte",
            () -> new CoffeeBrew(
                    CupType.LARGE,
                    RavenCoffeeItems.CUP_LARGE.get(),
                    Brews.PUMPKINSPICELATTE,
                    new Item.Properties()
            )
    );
    public static final RegistryObject<Item> COFFEE_MUG_BREW_PUMPKINSPICELATTE = BREWS.register(
            "coffee_mug_brew_pumpkinspicelatte",
            () -> new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.COFFEE_MUG.get(),
                    Brews.PUMPKINSPICELATTE,
                    new Item.Properties()
            )
    );
    //           #######################################   PUMPKINSPICELATTE_WITH_PUMPKIN   #############################################
    public static final RegistryObject<Item> CUP_MEDIUM_BREW_PUMPKINSPICELATTE_WITH_PUMPKIN = BREWS.register(
            "cup_medium_brew_pumpkinspicelatte_with_pumpkin",
            () -> new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.CUP_MEDIUM.get(),
                    Brews.PUMPKINSPICELATTE_WITH_PUMPKIN,
                    new Item.Properties()
                            .food(new FoodProperties.Builder()
                                    .nutrition(5)
                                    .saturationMod(0.3f)
                                    .build())
            )
    );
    public static final RegistryObject<Item> CUP_LARGE_BREW_PUMPKINSPICELATTE_WITH_PUMPKIN = BREWS.register(
            "cup_large_brew_pumpkinspicelatte_with_pumpkin",
            () -> new CoffeeBrew(
                    CupType.LARGE,
                    RavenCoffeeItems.CUP_LARGE.get(),
                    Brews.PUMPKINSPICELATTE_WITH_PUMPKIN,
                    new Item.Properties()
                            .food(new FoodProperties.Builder()
                                    .nutrition(5)
                                    .saturationMod(0.3f)
                                    .build())
            )
    );
    public static final RegistryObject<Item> COFFEE_MUG_BREW_PUMPKINSPICELATTE_WITH_PUMPKIN = BREWS.register(
            "coffee_mug_brew_pumpkinspicelatte_with_pumpkin",
            () -> new CoffeeBrew(
                    CupType.MEDIUM,
                    RavenCoffeeItems.COFFEE_MUG.get(),
                    Brews.PUMPKINSPICELATTE_WITH_PUMPKIN,
                    new Item.Properties()
                            .food(new FoodProperties.Builder()
                                    .nutrition(5)
                                    .saturationMod(0.3f)
                                    .build())
            )
    );
}
