package com.thewandererraven.ravencoffee.util.registries;

import com.thewandererraven.ravenbrewscore.Brews;
import com.thewandererraven.ravenbrewscore.CupSizes;
import com.thewandererraven.ravencoffee.RavenCoffee;
import com.thewandererraven.ravencoffee.items.ChorusBrew;
import com.thewandererraven.ravencoffee.items.CoffeeBrew;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BrewsRegistry {
    public static final DeferredRegister<Item> BREWS = DeferredRegister.create(
            ForgeRegistries.ITEMS,
            RavenCoffee.MOD_ID
    );

    // ################################################# COFFEE BREWS ##################################################
    //           #######################################   ESPRESSO   #############################################
    public static final RegistryObject<Item> CUP_SMALL_BREW_AMERICAN = BREWS.register(
            "cup_small_brew_espresso",
            () -> new CoffeeBrew(
                    CupSizes.MEDIUM,
                    ItemsRegistry.CUP_SMALL.get(),
                    Brews.ESPRESSO,
                    new Item.Properties().tab(RavenCoffee.CUP_SMALL_TAB)
            )
    );
    //           #######################################   AMERICANO   #############################################
    public static final RegistryObject<Item> CUP_MEDIUM_BREW_AMERICAN = BREWS.register(
            "cup_medium_brew_american",
            () -> new CoffeeBrew(
                    CupSizes.MEDIUM,
                    ItemsRegistry.CUP_MEDIUM.get(),
                    Brews.AMERICAN,
                    new Item.Properties().tab(RavenCoffee.CUP_MEDIUM_TAB)
            )
    );
    public static final RegistryObject<Item> CUP_LARGE_BREW_AMERICAN = BREWS.register(
            "cup_large_brew_american",
            () -> new CoffeeBrew(
                    CupSizes.LARGE,
                    ItemsRegistry.CUP_LARGE.get(),
                    Brews.AMERICAN,
                    new Item.Properties().tab(RavenCoffee.CUP_LARGE_TAB)
            )
    );
    public static final RegistryObject<Item> COFFEE_MUG_BREW_AMERICAN = BREWS.register(
            "coffee_mug_brew_american",
            () -> new CoffeeBrew(
                    CupSizes.MEDIUM,
                    ItemsRegistry.COFFEE_MUG.get(),
                    Brews.AMERICAN,
                    new Item.Properties().tab(RavenCoffee.COFFEE_MUG_TAB)
            )
    );
    //           #######################################   SUGAR   #############################################
    public static final RegistryObject<Item> CUP_MEDIUM_BREW_SUGAR = BREWS.register(
            "cup_medium_brew_sugar",
            () -> new CoffeeBrew(
                    CupSizes.MEDIUM,
                    ItemsRegistry.CUP_MEDIUM.get(),
                    Brews.SUGAR,
                    new Item.Properties().tab(RavenCoffee.CUP_MEDIUM_TAB)
            )
    );
    public static final RegistryObject<Item> CUP_LARGE_BREW_SUGAR = BREWS.register(
            "cup_large_brew_sugar",
            () -> new CoffeeBrew(
                    CupSizes.LARGE,
                    ItemsRegistry.CUP_LARGE.get(),
                    Brews.SUGAR,
                    new Item.Properties().tab(RavenCoffee.CUP_LARGE_TAB)
            )
    );
    public static final RegistryObject<Item> COFFEE_MUG_BREW_SUGAR = BREWS.register(
            "coffee_mug_brew_sugar",
            () -> new CoffeeBrew(
                    CupSizes.MEDIUM,
                    ItemsRegistry.COFFEE_MUG.get(),
                    Brews.SUGAR,
                    new Item.Properties().tab(RavenCoffee.COFFEE_MUG_TAB)
            )
    );
    //           #######################################   APPLE   #############################################
    public static final RegistryObject<Item> CUP_MEDIUM_BREW_APPLE = BREWS.register(
            "cup_medium_brew_apple",
            () -> new CoffeeBrew(
                    CupSizes.MEDIUM,
                    ItemsRegistry.CUP_MEDIUM.get(),
                    Brews.APPLE,
                    new Item.Properties().tab(RavenCoffee.CUP_MEDIUM_TAB)
                    .food(new FoodProperties.Builder()
                            .nutrition(2)
                            .saturationMod(0.3f)
                            .build())
            )
    );
    public static final RegistryObject<Item> CUP_LARGE_BREW_APPLE = BREWS.register(
            "cup_large_brew_apple",
            () -> new CoffeeBrew(
                    CupSizes.LARGE,
                    ItemsRegistry.CUP_LARGE.get(),
                    Brews.APPLE,
                    new Item.Properties().tab(RavenCoffee.CUP_LARGE_TAB)
                            .food(new FoodProperties.Builder()
                                    .nutrition(3)
                                    .saturationMod(0.3f)
                                    .build())
            )
    );
    public static final RegistryObject<Item> COFFEE_MUG_BREW_APPLE = BREWS.register(
            "coffee_mug_brew_apple",
            () -> new CoffeeBrew(
                    CupSizes.MEDIUM,
                    ItemsRegistry.COFFEE_MUG.get(),
                    Brews.APPLE,
                    new Item.Properties().tab(RavenCoffee.COFFEE_MUG_TAB)
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
                    CupSizes.MEDIUM,
                    ItemsRegistry.CUP_MEDIUM.get(),
                    Brews.BERRY,
                    new Item.Properties().tab(RavenCoffee.CUP_MEDIUM_TAB)
                            .food(new FoodProperties.Builder()
                                    .nutrition(1)
                                    .saturationMod(0.1f)
                                    .build())
            )
    );
    public static final RegistryObject<Item> CUP_LARGE_BREW_BERRY = BREWS.register(
            "cup_large_brew_berry",
            () -> new CoffeeBrew(
                    CupSizes.LARGE,
                    ItemsRegistry.CUP_LARGE.get(),
                    Brews.BERRY,
                    new Item.Properties().tab(RavenCoffee.CUP_LARGE_TAB)
                            .food(new FoodProperties.Builder()
                                    .nutrition(2)
                                    .saturationMod(0.1f)
                                    .build())
            )
    );
    public static final RegistryObject<Item> COFFEE_MUG_BREW_BERRY = BREWS.register(
            "coffee_mug_brew_berry",
            () -> new CoffeeBrew(
                    CupSizes.MEDIUM,
                    ItemsRegistry.COFFEE_MUG.get(),
                    Brews.BERRY,
                    new Item.Properties().tab(RavenCoffee.COFFEE_MUG_TAB)
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
                    CupSizes.MEDIUM,
                    ItemsRegistry.CUP_MEDIUM.get(),
                    Brews.HONEY,
                    new Item.Properties().tab(RavenCoffee.CUP_MEDIUM_TAB)
                            .food(new FoodProperties.Builder()
                                    .nutrition(6)
                                    .saturationMod(0.1f)
                                    .build())
            )
    );
    public static final RegistryObject<Item> CUP_LARGE_BREW_HONEY = BREWS.register(
            "cup_large_brew_honey",
            () -> new CoffeeBrew(
                    CupSizes.LARGE,
                    ItemsRegistry.CUP_LARGE.get(),
                    Brews.HONEY,
                    new Item.Properties().tab(RavenCoffee.CUP_LARGE_TAB)
                            .food(new FoodProperties.Builder()
                                    .nutrition(9)
                                    .saturationMod(0.1f)
                                    .build())
            )
    );
    public static final RegistryObject<Item> COFFEE_MUG_BREW_HONEY = BREWS.register(
            "coffee_mug_brew_honey",
            () -> new CoffeeBrew(
                    CupSizes.MEDIUM,
                    ItemsRegistry.COFFEE_MUG.get(),
                    Brews.HONEY,
                    new Item.Properties().tab(RavenCoffee.COFFEE_MUG_TAB)
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
                    CupSizes.MEDIUM,
                    ItemsRegistry.CUP_MEDIUM.get(),
                    Brews.CHOCOLATE,
                    new Item.Properties().tab(RavenCoffee.CUP_MEDIUM_TAB)
                            .food(new FoodProperties.Builder()
                                    .nutrition(2)
                                    .saturationMod(0.1f)
                                    .build())
            )
    );
    public static final RegistryObject<Item> CUP_LARGE_BREW_CHOCOLATE = BREWS.register(
            "cup_large_brew_chocolate",
            () -> new CoffeeBrew(
                    CupSizes.LARGE,
                    ItemsRegistry.CUP_LARGE.get(),
                    Brews.CHOCOLATE,
                    new Item.Properties().tab(RavenCoffee.CUP_LARGE_TAB)
                            .food(new FoodProperties.Builder()
                                    .nutrition(3)
                                    .saturationMod(0.1f)
                                    .build())
            )
    );
    public static final RegistryObject<Item> COFFEE_MUG_BREW_CHOCOLATE = BREWS.register(
            "coffee_mug_brew_chocolate",
            () -> new CoffeeBrew(
                    CupSizes.MEDIUM,
                    ItemsRegistry.COFFEE_MUG.get(),
                    Brews.CHOCOLATE,
                    new Item.Properties().tab(RavenCoffee.COFFEE_MUG_TAB)
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
                    CupSizes.MEDIUM,
                    ItemsRegistry.CUP_MEDIUM.get(),
                    Brews.MILK,
                    new Item.Properties().tab(RavenCoffee.CUP_MEDIUM_TAB)
            )
    );
    public static final RegistryObject<Item> CUP_LARGE_BREW_MILK = BREWS.register(
            "cup_large_brew_milk",
            () -> new CoffeeBrew(
                    CupSizes.LARGE,
                    ItemsRegistry.CUP_LARGE.get(),
                    Brews.MILK,
                    new Item.Properties().tab(RavenCoffee.CUP_LARGE_TAB)
            )
    );
    public static final RegistryObject<Item> COFFEE_MUG_BREW_MILK = BREWS.register(
            "coffee_mug_brew_milk",
            () -> new CoffeeBrew(
                    CupSizes.MEDIUM,
                    ItemsRegistry.COFFEE_MUG.get(),
                    Brews.MILK,
                    new Item.Properties().tab(RavenCoffee.COFFEE_MUG_TAB)
            )
    );
    //           #######################################   MOCHA   #############################################
    public static final RegistryObject<Item> CUP_MEDIUM_BREW_MOCHA = BREWS.register(
            "cup_medium_brew_mocha",
            () -> new CoffeeBrew(
                    CupSizes.MEDIUM,
                    ItemsRegistry.CUP_MEDIUM.get(),
                    Brews.MOCHA,
                    new Item.Properties().tab(RavenCoffee.CUP_MEDIUM_TAB)
            )
    );
    public static final RegistryObject<Item> CUP_LARGE_BREW_MOCHA = BREWS.register(
            "cup_large_brew_mocha",
            () -> new CoffeeBrew(
                    CupSizes.LARGE,
                    ItemsRegistry.CUP_LARGE.get(),
                    Brews.MOCHA,
                    new Item.Properties().tab(RavenCoffee.CUP_LARGE_TAB)
            )
    );
    public static final RegistryObject<Item> COFFEE_MUG_BREW_MOCHA = BREWS.register(
            "coffee_mug_brew_mocha",
            () -> new CoffeeBrew(
                    CupSizes.MEDIUM,
                    ItemsRegistry.COFFEE_MUG.get(),
                    Brews.MOCHA,
                    new Item.Properties().tab(RavenCoffee.COFFEE_MUG_TAB)
            )
    );
    //           #######################################   AWKWARD   #############################################
    public static final RegistryObject<Item> CUP_MEDIUM_BREW_AWKWARD = BREWS.register(
            "cup_medium_brew_awkward",
            () -> new CoffeeBrew(
                    CupSizes.MEDIUM,
                    ItemsRegistry.CUP_MEDIUM.get(),
                    Brews.AWKWARD,
                    new Item.Properties().tab(RavenCoffee.CUP_MEDIUM_TAB)
            )
    );
    public static final RegistryObject<Item> CUP_LARGE_BREW_AWKWARD = BREWS.register(
            "cup_large_brew_awkward",
            () -> new CoffeeBrew(
                    CupSizes.LARGE,
                    ItemsRegistry.CUP_LARGE.get(),
                    Brews.AWKWARD,
                    new Item.Properties().tab(RavenCoffee.CUP_LARGE_TAB)
            )
    );
    public static final RegistryObject<Item> COFFEE_MUG_BREW_AWKWARD = BREWS.register(
            "coffee_mug_brew_awkward",
            () -> new CoffeeBrew(
                    CupSizes.MEDIUM,
                    ItemsRegistry.COFFEE_MUG.get(),
                    Brews.AWKWARD,
                    new Item.Properties().tab(RavenCoffee.COFFEE_MUG_TAB)
            )
    );
    //           #######################################   CARROT   #############################################
    public static final RegistryObject<Item> CUP_MEDIUM_BREW_CARROT = BREWS.register(
            "cup_medium_brew_carrot",
            () -> new CoffeeBrew(
                    CupSizes.MEDIUM,
                    ItemsRegistry.CUP_MEDIUM.get(),
                    Brews.CARROT,
                    new Item.Properties().tab(RavenCoffee.CUP_MEDIUM_TAB)
                            .food(new FoodProperties.Builder()
                                    .nutrition(3)
                                    .saturationMod(0.3f)
                                    .build())
            )
    );
    public static final RegistryObject<Item> CUP_LARGE_BREW_CARROT = BREWS.register(
            "cup_large_brew_carrot",
            () -> new CoffeeBrew(
                    CupSizes.LARGE,
                    ItemsRegistry.CUP_LARGE.get(),
                    Brews.CARROT,
                    new Item.Properties().tab(RavenCoffee.CUP_LARGE_TAB)
                            .food(new FoodProperties.Builder()
                                    .nutrition(3)
                                    .saturationMod(0.3f)
                                    .build())
            )
    );
    public static final RegistryObject<Item> COFFEE_MUG_BREW_CARROT = BREWS.register(
            "coffee_mug_brew_carrot",
            () -> new CoffeeBrew(
                    CupSizes.MEDIUM,
                    ItemsRegistry.COFFEE_MUG.get(),
                    Brews.CARROT,
                    new Item.Properties().tab(RavenCoffee.COFFEE_MUG_TAB)
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
                    CupSizes.MEDIUM,
                    ItemsRegistry.CUP_MEDIUM.get(),
                    Brews.CARROT_GOLDEN,
                    new Item.Properties().tab(RavenCoffee.CUP_MEDIUM_TAB)
                            .food(new FoodProperties.Builder()
                                    .nutrition(6)
                                    .saturationMod(0.8f)
                                    .build())
            )
    );
    public static final RegistryObject<Item> CUP_LARGE_BREW_CARROT_GOLDEN = BREWS.register(
            "cup_large_brew_carrot_golden",
            () -> new CoffeeBrew(
                    CupSizes.LARGE,
                    ItemsRegistry.CUP_LARGE.get(),
                    Brews.CARROT_GOLDEN,
                    new Item.Properties().tab(RavenCoffee.CUP_LARGE_TAB)
                            .food(new FoodProperties.Builder()
                                    .nutrition(9)
                                    .saturationMod(0.8f)
                                    .build())
            )
    );
    public static final RegistryObject<Item> COFFEE_MUG_BREW_CARROT_GOLDEN = BREWS.register(
            "coffee_mug_brew_carrot_golden",
            () -> new CoffeeBrew(
                    CupSizes.MEDIUM,
                    ItemsRegistry.COFFEE_MUG.get(),
                    Brews.CARROT_GOLDEN,
                    new Item.Properties().tab(RavenCoffee.COFFEE_MUG_TAB)
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
                    CupSizes.MEDIUM,
                    ItemsRegistry.CUP_MEDIUM.get(),
                    Brews.COOKIESANDCREAM,
                    new Item.Properties().tab(RavenCoffee.CUP_MEDIUM_TAB)
                            .food(new FoodProperties.Builder()
                                    .nutrition(4)
                                    .saturationMod(0.1f)
                                    .build())
            )
    );
    public static final RegistryObject<Item> CUP_LARGE_BREW_COOKIESANDCREAM = BREWS.register(
            "cup_large_brew_cookiesandcream",
            () -> new CoffeeBrew(
                    CupSizes.LARGE,
                    ItemsRegistry.CUP_LARGE.get(),
                    Brews.COOKIESANDCREAM,
                    new Item.Properties().tab(RavenCoffee.CUP_LARGE_TAB)
                            .food(new FoodProperties.Builder()
                                    .nutrition(4)
                                    .saturationMod(0.1f)
                                    .build())
            )
    );
    public static final RegistryObject<Item> COFFEE_MUG_BREW_COOKIESANDCREAM = BREWS.register(
            "coffee_mug_brew_cookiesandcream",
            () -> new CoffeeBrew(
                    CupSizes.MEDIUM,
                    ItemsRegistry.COFFEE_MUG.get(),
                    Brews.COOKIESANDCREAM,
                    new Item.Properties().tab(RavenCoffee.COFFEE_MUG_TAB)
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
                    CupSizes.MEDIUM,
                    ItemsRegistry.CUP_MEDIUM.get(),
                    Brews.END,
                    new Item.Properties().tab(RavenCoffee.CUP_MEDIUM_TAB)
                            .food(new FoodProperties.Builder()
                                    .nutrition(2)
                                    .saturationMod(0.3f)
                                    .build())
            )
    );
    public static final RegistryObject<Item> CUP_LARGE_BREW_END = BREWS.register(
            "cup_large_brew_end",
            () -> new ChorusBrew(
                    CupSizes.LARGE,
                    ItemsRegistry.CUP_LARGE.get(),
                    Brews.END,
                    new Item.Properties().tab(RavenCoffee.CUP_LARGE_TAB)
                            .food(new FoodProperties.Builder()
                                    .nutrition(3)
                                    .saturationMod(0.3f)
                                    .build())
            )
    );
    public static final RegistryObject<Item> COFFEE_MUG_BREW_END = BREWS.register(
            "coffee_mug_brew_end",
            () -> new ChorusBrew(
                    CupSizes.MEDIUM,
                    ItemsRegistry.COFFEE_MUG.get(),
                    Brews.END,
                    new Item.Properties().tab(RavenCoffee.COFFEE_MUG_TAB)
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
                    CupSizes.MEDIUM,
                    ItemsRegistry.CUP_MEDIUM.get(),
                    Brews.MELON,
                    new Item.Properties().tab(RavenCoffee.CUP_MEDIUM_TAB)
                            .food(new FoodProperties.Builder()
                                    .nutrition(2)
                                    .saturationMod(0.3f)
                                    .build())
            )
    );
    public static final RegistryObject<Item> CUP_LARGE_BREW_MELON = BREWS.register(
            "cup_large_brew_melon",
            () -> new CoffeeBrew(
                    CupSizes.LARGE,
                    ItemsRegistry.CUP_LARGE.get(),
                    Brews.MELON,
                    new Item.Properties().tab(RavenCoffee.CUP_LARGE_TAB)
                            .food(new FoodProperties.Builder()
                                    .nutrition(3)
                                    .saturationMod(0.3f)
                                    .build())
            )
    );
    public static final RegistryObject<Item> COFFEE_MUG_BREW_MELON = BREWS.register(
            "coffee_mug_brew_melon",
            () -> new CoffeeBrew(
                    CupSizes.MEDIUM,
                    ItemsRegistry.COFFEE_MUG.get(),
                    Brews.MELON,
                    new Item.Properties().tab(RavenCoffee.COFFEE_MUG_TAB)
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
                    CupSizes.MEDIUM,
                    ItemsRegistry.CUP_MEDIUM.get(),
                    Brews.MELON_GOLDEN,
                    new Item.Properties().tab(RavenCoffee.CUP_MEDIUM_TAB)
                            .food(new FoodProperties.Builder()
                                    .nutrition(2)
                                    .saturationMod(0.3f)
                                    .build())
            )
    );
    public static final RegistryObject<Item> CUP_LARGE_BREW_MELON_GOLDEN = BREWS.register(
            "cup_large_brew_melon_golden",
            () -> new CoffeeBrew(
                    CupSizes.LARGE,
                    ItemsRegistry.CUP_LARGE.get(),
                    Brews.MELON_GOLDEN,
                    new Item.Properties().tab(RavenCoffee.CUP_LARGE_TAB)
                            .food(new FoodProperties.Builder()
                                    .nutrition(3)
                                    .saturationMod(0.3f)
                                    .build())
            )
    );
    public static final RegistryObject<Item> COFFEE_MUG_BREW_MELON_GOLDEN = BREWS.register(
            "coffee_mug_brew_melon_golden",
            () -> new CoffeeBrew(
                    CupSizes.MEDIUM,
                    ItemsRegistry.COFFEE_MUG.get(),
                    Brews.MELON_GOLDEN,
                    new Item.Properties().tab(RavenCoffee.COFFEE_MUG_TAB)
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
                    CupSizes.MEDIUM,
                    ItemsRegistry.CUP_MEDIUM.get(),
                    Brews.NETHER,
                    new Item.Properties().tab(RavenCoffee.CUP_MEDIUM_TAB)
                            .food(new FoodProperties.Builder()
                                    .nutrition(1)
                                    .saturationMod(0.1f)
                                    .build())
            )
    );
    public static final RegistryObject<Item> CUP_LARGE_BREW_NETHER = BREWS.register(
            "cup_large_brew_nether",
            () -> new CoffeeBrew(
                    CupSizes.LARGE,
                    ItemsRegistry.CUP_LARGE.get(),
                    Brews.NETHER,
                    new Item.Properties().tab(RavenCoffee.CUP_LARGE_TAB)
                            .food(new FoodProperties.Builder()
                                    .nutrition(1)
                                    .saturationMod(0.1f)
                                    .build())
            )
    );
    public static final RegistryObject<Item> COFFEE_MUG_BREW_NETHER = BREWS.register(
            "coffee_mug_brew_nether",
            () -> new CoffeeBrew(
                    CupSizes.MEDIUM,
                    ItemsRegistry.COFFEE_MUG.get(),
                    Brews.NETHER,
                    new Item.Properties().tab(RavenCoffee.COFFEE_MUG_TAB)
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
                    CupSizes.MEDIUM,
                    ItemsRegistry.CUP_MEDIUM.get(),
                    Brews.PHANTASM,
                    new Item.Properties().tab(RavenCoffee.CUP_MEDIUM_TAB)
            )
    );
    public static final RegistryObject<Item> CUP_LARGE_BREW_PHANTASM = BREWS.register(
            "cup_large_brew_phantasm",
            () -> new CoffeeBrew(
                    CupSizes.LARGE,
                    ItemsRegistry.CUP_LARGE.get(),
                    Brews.PHANTASM,
                    new Item.Properties().tab(RavenCoffee.CUP_LARGE_TAB)
            )
    );
    public static final RegistryObject<Item> COFFEE_MUG_BREW_PHANTASM = BREWS.register(
            "coffee_mug_brew_phantasm",
            () -> new CoffeeBrew(
                    CupSizes.MEDIUM,
                    ItemsRegistry.COFFEE_MUG.get(),
                    Brews.PHANTASM,
                    new Item.Properties().tab(RavenCoffee.COFFEE_MUG_TAB)
            )
    );
    //           #######################################   PUMPKINSPICELATTE   #############################################
    public static final RegistryObject<Item> CUP_MEDIUM_BREW_PUMPKINSPICELATTE = BREWS.register(
            "cup_medium_brew_pumpkinspicelatte",
            () -> new CoffeeBrew(
                    CupSizes.MEDIUM,
                    ItemsRegistry.CUP_MEDIUM.get(),
                    Brews.PUMPKINSPICELATTE,
                    new Item.Properties().tab(RavenCoffee.CUP_MEDIUM_TAB)
            )
    );
    public static final RegistryObject<Item> CUP_LARGE_BREW_PUMPKINSPICELATTE = BREWS.register(
            "cup_large_brew_pumpkinspicelatte",
            () -> new CoffeeBrew(
                    CupSizes.LARGE,
                    ItemsRegistry.CUP_LARGE.get(),
                    Brews.PUMPKINSPICELATTE,
                    new Item.Properties().tab(RavenCoffee.CUP_LARGE_TAB)
            )
    );
    public static final RegistryObject<Item> COFFEE_MUG_BREW_PUMPKINSPICELATTE = BREWS.register(
            "coffee_mug_brew_pumpkinspicelatte",
            () -> new CoffeeBrew(
                    CupSizes.MEDIUM,
                    ItemsRegistry.COFFEE_MUG.get(),
                    Brews.PUMPKINSPICELATTE,
                    new Item.Properties().tab(RavenCoffee.COFFEE_MUG_TAB)
            )
    );
    //           #######################################   PUMPKINSPICELATTE_WITH_PUMPKIN   #############################################
    public static final RegistryObject<Item> CUP_MEDIUM_BREW_PUMPKINSPICELATTE_WITH_PUMPKIN = BREWS.register(
            "cup_medium_brew_pumpkinspicelatte_with_pumpkin",
            () -> new CoffeeBrew(
                    CupSizes.MEDIUM,
                    ItemsRegistry.CUP_MEDIUM.get(),
                    Brews.PUMPKINSPICELATTE_WITH_PUMPKIN,
                    new Item.Properties().tab(RavenCoffee.CUP_MEDIUM_TAB)
                            .food(new FoodProperties.Builder()
                                    .nutrition(5)
                                    .saturationMod(0.3f)
                                    .build())
            )
    );
    public static final RegistryObject<Item> CUP_LARGE_BREW_PUMPKINSPICELATTE_WITH_PUMPKIN = BREWS.register(
            "cup_large_brew_pumpkinspicelatte_with_pumpkin",
            () -> new CoffeeBrew(
                    CupSizes.LARGE,
                    ItemsRegistry.CUP_LARGE.get(),
                    Brews.PUMPKINSPICELATTE_WITH_PUMPKIN,
                    new Item.Properties().tab(RavenCoffee.CUP_LARGE_TAB)
                            .food(new FoodProperties.Builder()
                                    .nutrition(5)
                                    .saturationMod(0.3f)
                                    .build())
            )
    );
    public static final RegistryObject<Item> COFFEE_MUG_BREW_PUMPKINSPICELATTE_WITH_PUMPKIN = BREWS.register(
            "coffee_mug_brew_pumpkinspicelatte_with_pumpkin",
            () -> new CoffeeBrew(
                    CupSizes.MEDIUM,
                    ItemsRegistry.COFFEE_MUG.get(),
                    Brews.PUMPKINSPICELATTE_WITH_PUMPKIN,
                    new Item.Properties().tab(RavenCoffee.COFFEE_MUG_TAB)
                            .food(new FoodProperties.Builder()
                                    .nutrition(5)
                                    .saturationMod(0.3f)
                                    .build())
            )
    );
}
