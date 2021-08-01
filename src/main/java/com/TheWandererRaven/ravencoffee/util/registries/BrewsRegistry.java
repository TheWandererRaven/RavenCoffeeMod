package com.TheWandererRaven.ravencoffee.util.registries;

import com.TheWandererRaven.ravencoffee.RavenCoffee;
import com.TheWandererRaven.ravencoffee.customClasses.Brews;
import com.TheWandererRaven.ravencoffee.customClasses.CupSizes;
import com.TheWandererRaven.ravencoffee.items.ChorusBrew;
import com.TheWandererRaven.ravencoffee.items.CoffeeBrew;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
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
                    new Item.Properties().group(RavenCoffee.CUP_SMALL_TAB)
            )
    );
    //           #######################################   AMERICANO   #############################################
    public static final RegistryObject<Item> CUP_MEDIUM_BREW_AMERICAN = BREWS.register(
            "cup_medium_brew_american",
            () -> new CoffeeBrew(
                    CupSizes.MEDIUM,
                    ItemsRegistry.CUP_MEDIUM.get(),
                    Brews.AMERICAN,
                    new Item.Properties().group(RavenCoffee.CUP_MEDIUM_TAB)
            )
    );
    public static final RegistryObject<Item> CUP_LARGE_BREW_AMERICAN = BREWS.register(
            "cup_large_brew_american",
            () -> new CoffeeBrew(
                    CupSizes.LARGE,
                    ItemsRegistry.CUP_LARGE.get(),
                    Brews.AMERICAN,
                    new Item.Properties().group(RavenCoffee.CUP_LARGE_TAB)
            )
    );
    public static final RegistryObject<Item> COFFEE_MUG_BREW_AMERICAN = BREWS.register(
            "coffee_mug_brew_american",
            () -> new CoffeeBrew(
                    CupSizes.MEDIUM,
                    ItemsRegistry.COFFEE_MUG.get(),
                    Brews.AMERICAN,
                    new Item.Properties().group(RavenCoffee.COFFEE_MUG_TAB)
            )
    );
    //           #######################################   SUGAR   #############################################
    public static final RegistryObject<Item> CUP_MEDIUM_BREW_SUGAR = BREWS.register(
            "cup_medium_brew_sugar",
            () -> new CoffeeBrew(
                    CupSizes.MEDIUM,
                    ItemsRegistry.CUP_MEDIUM.get(),
                    Brews.SUGAR,
                    new Item.Properties().group(RavenCoffee.CUP_MEDIUM_TAB)
            )
    );
    public static final RegistryObject<Item> CUP_LARGE_BREW_SUGAR = BREWS.register(
            "cup_large_brew_sugar",
            () -> new CoffeeBrew(
                    CupSizes.LARGE,
                    ItemsRegistry.CUP_LARGE.get(),
                    Brews.SUGAR,
                    new Item.Properties().group(RavenCoffee.CUP_LARGE_TAB)
            )
    );
    public static final RegistryObject<Item> COFFEE_MUG_BREW_SUGAR = BREWS.register(
            "coffee_mug_brew_sugar",
            () -> new CoffeeBrew(
                    CupSizes.MEDIUM,
                    ItemsRegistry.COFFEE_MUG.get(),
                    Brews.SUGAR,
                    new Item.Properties().group(RavenCoffee.COFFEE_MUG_TAB)
            )
    );
    //           #######################################   APPLE   #############################################
    public static final RegistryObject<Item> CUP_MEDIUM_BREW_APPLE = BREWS.register(
            "cup_medium_brew_apple",
            () -> new CoffeeBrew(
                    CupSizes.MEDIUM,
                    ItemsRegistry.CUP_MEDIUM.get(),
                    Brews.APPLE,
                    new Item.Properties().group(RavenCoffee.CUP_MEDIUM_TAB)
                    .food(new Food.Builder()
                            .hunger(2)
                            .saturation(1.2f)
                            .build())
            )
    );
    public static final RegistryObject<Item> CUP_LARGE_BREW_APPLE = BREWS.register(
            "cup_large_brew_apple",
            () -> new CoffeeBrew(
                    CupSizes.LARGE,
                    ItemsRegistry.CUP_LARGE.get(),
                    Brews.APPLE,
                    new Item.Properties().group(RavenCoffee.CUP_LARGE_TAB)
                            .food(new Food.Builder()
                                    .hunger(4)
                                    .saturation(2.4f)
                                    .build())
            )
    );
    public static final RegistryObject<Item> COFFEE_MUG_BREW_APPLE = BREWS.register(
            "coffee_mug_brew_apple",
            () -> new CoffeeBrew(
                    CupSizes.MEDIUM,
                    ItemsRegistry.COFFEE_MUG.get(),
                    Brews.APPLE,
                    new Item.Properties().group(RavenCoffee.COFFEE_MUG_TAB)
                            .food(new Food.Builder()
                                    .hunger(2)
                                    .saturation(1.2f)
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
                    new Item.Properties().group(RavenCoffee.CUP_MEDIUM_TAB)
                            .food(new Food.Builder()
                                    .hunger(1)
                                    .saturation(0.4f)
                                    .build())
            )
    );
    public static final RegistryObject<Item> CUP_LARGE_BREW_BERRY = BREWS.register(
            "cup_large_brew_berry",
            () -> new CoffeeBrew(
                    CupSizes.LARGE,
                    ItemsRegistry.CUP_LARGE.get(),
                    Brews.BERRY,
                    new Item.Properties().group(RavenCoffee.CUP_LARGE_TAB)
                            .food(new Food.Builder()
                                    .hunger(2)
                                    .saturation(0.8f)
                                    .build())
            )
    );
    public static final RegistryObject<Item> COFFEE_MUG_BREW_BERRY = BREWS.register(
            "coffee_mug_brew_berry",
            () -> new CoffeeBrew(
                    CupSizes.MEDIUM,
                    ItemsRegistry.COFFEE_MUG.get(),
                    Brews.BERRY,
                    new Item.Properties().group(RavenCoffee.COFFEE_MUG_TAB)
                            .food(new Food.Builder()
                                    .hunger(1)
                                    .saturation(0.4f)
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
                    new Item.Properties().group(RavenCoffee.CUP_MEDIUM_TAB)
                            .food(new Food.Builder()
                                    .hunger(6)
                                    .saturation(1.2f)
                                    .build())
            )
    );
    public static final RegistryObject<Item> CUP_LARGE_BREW_HONEY = BREWS.register(
            "cup_large_brew_honey",
            () -> new CoffeeBrew(
                    CupSizes.LARGE,
                    ItemsRegistry.CUP_LARGE.get(),
                    Brews.HONEY,
                    new Item.Properties().group(RavenCoffee.CUP_LARGE_TAB)
                            .food(new Food.Builder()
                                    .hunger(8)
                                    .saturation(2.4f)
                                    .build())
            )
    );
    public static final RegistryObject<Item> COFFEE_MUG_BREW_HONEY = BREWS.register(
            "coffee_mug_brew_honey",
            () -> new CoffeeBrew(
                    CupSizes.MEDIUM,
                    ItemsRegistry.COFFEE_MUG.get(),
                    Brews.HONEY,
                    new Item.Properties().group(RavenCoffee.COFFEE_MUG_TAB)
                            .food(new Food.Builder()
                                    .hunger(6)
                                    .saturation(1.2f)
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
                    new Item.Properties().group(RavenCoffee.CUP_MEDIUM_TAB)
                            .food(new Food.Builder()
                                    .hunger(2)
                                    .saturation(0.4f)
                                    .build())
            )
    );
    public static final RegistryObject<Item> CUP_LARGE_BREW_CHOCOLATE = BREWS.register(
            "cup_large_brew_chocolate",
            () -> new CoffeeBrew(
                    CupSizes.LARGE,
                    ItemsRegistry.CUP_LARGE.get(),
                    Brews.CHOCOLATE,
                    new Item.Properties().group(RavenCoffee.CUP_LARGE_TAB)
                            .food(new Food.Builder()
                                    .hunger(3)
                                    .saturation(0.8f)
                                    .build())
            )
    );
    public static final RegistryObject<Item> COFFEE_MUG_BREW_CHOCOLATE = BREWS.register(
            "coffee_mug_brew_chocolate",
            () -> new CoffeeBrew(
                    CupSizes.MEDIUM,
                    ItemsRegistry.COFFEE_MUG.get(),
                    Brews.CHOCOLATE,
                    new Item.Properties().group(RavenCoffee.COFFEE_MUG_TAB)
                            .food(new Food.Builder()
                                    .hunger(2)
                                    .saturation(0.4f)
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
                    new Item.Properties().group(RavenCoffee.CUP_MEDIUM_TAB)
                            .food(new Food.Builder()
                                    .saturation(0.4f)
                                    .build())
            )
    );
    public static final RegistryObject<Item> CUP_LARGE_BREW_MILK = BREWS.register(
            "cup_large_brew_milk",
            () -> new CoffeeBrew(
                    CupSizes.LARGE,
                    ItemsRegistry.CUP_LARGE.get(),
                    Brews.MILK,
                    new Item.Properties().group(RavenCoffee.CUP_LARGE_TAB)
                            .food(new Food.Builder()
                                    .saturation(0.8f)
                                    .build())
            )
    );
    public static final RegistryObject<Item> COFFEE_MUG_BREW_MILK = BREWS.register(
            "coffee_mug_brew_milk",
            () -> new CoffeeBrew(
                    CupSizes.MEDIUM,
                    ItemsRegistry.COFFEE_MUG.get(),
                    Brews.MILK,
                    new Item.Properties().group(RavenCoffee.COFFEE_MUG_TAB)
                            .food(new Food.Builder()
                                    .saturation(0.4f)
                                    .build())
            )
    );
    //           #######################################   MOCHA   #############################################
    public static final RegistryObject<Item> CUP_MEDIUM_BREW_MOCHA = BREWS.register(
            "cup_medium_brew_mocha",
            () -> new CoffeeBrew(
                    CupSizes.MEDIUM,
                    ItemsRegistry.CUP_MEDIUM.get(),
                    Brews.MOCHA,
                    new Item.Properties().group(RavenCoffee.CUP_MEDIUM_TAB)
                            .food(new Food.Builder()
                                    .saturation(0.4f)
                                    .build())
            )
    );
    public static final RegistryObject<Item> CUP_LARGE_BREW_MOCHA = BREWS.register(
            "cup_large_brew_mocha",
            () -> new CoffeeBrew(
                    CupSizes.LARGE,
                    ItemsRegistry.CUP_LARGE.get(),
                    Brews.MOCHA,
                    new Item.Properties().group(RavenCoffee.CUP_LARGE_TAB)
                            .food(new Food.Builder()
                                    .saturation(0.8f)
                                    .build())
            )
    );
    public static final RegistryObject<Item> COFFEE_MUG_BREW_MOCHA = BREWS.register(
            "coffee_mug_brew_mocha",
            () -> new CoffeeBrew(
                    CupSizes.MEDIUM,
                    ItemsRegistry.COFFEE_MUG.get(),
                    Brews.MOCHA,
                    new Item.Properties().group(RavenCoffee.COFFEE_MUG_TAB)
                            .food(new Food.Builder()
                                    .saturation(0.4f)
                                    .build())
            )
    );
    //           #######################################   AWKWARD   #############################################
    public static final RegistryObject<Item> CUP_MEDIUM_BREW_AWKWARD = BREWS.register(
            "cup_medium_brew_awkward",
            () -> new CoffeeBrew(
                    CupSizes.MEDIUM,
                    ItemsRegistry.CUP_MEDIUM.get(),
                    Brews.AWKWARD,
                    new Item.Properties().group(RavenCoffee.CUP_MEDIUM_TAB)
            )
    );
    public static final RegistryObject<Item> CUP_LARGE_BREW_AWKWARD = BREWS.register(
            "cup_large_brew_awkward",
            () -> new CoffeeBrew(
                    CupSizes.LARGE,
                    ItemsRegistry.CUP_LARGE.get(),
                    Brews.AWKWARD,
                    new Item.Properties().group(RavenCoffee.CUP_LARGE_TAB)
            )
    );
    public static final RegistryObject<Item> COFFEE_MUG_BREW_AWKWARD = BREWS.register(
            "coffee_mug_brew_awkward",
            () -> new CoffeeBrew(
                    CupSizes.MEDIUM,
                    ItemsRegistry.COFFEE_MUG.get(),
                    Brews.AWKWARD,
                    new Item.Properties().group(RavenCoffee.COFFEE_MUG_TAB)
            )
    );
    //           #######################################   CARROT   #############################################
    public static final RegistryObject<Item> CUP_MEDIUM_BREW_CARROT = BREWS.register(
            "cup_medium_brew_carrot",
            () -> new CoffeeBrew(
                    CupSizes.MEDIUM,
                    ItemsRegistry.CUP_MEDIUM.get(),
                    Brews.CARROT,
                    new Item.Properties().group(RavenCoffee.CUP_MEDIUM_TAB)
                            .food(new Food.Builder()
                                    .hunger(3)
                                    .saturation(3.6f)
                                    .build())
            )
    );
    public static final RegistryObject<Item> CUP_LARGE_BREW_CARROT = BREWS.register(
            "cup_large_brew_carrot",
            () -> new CoffeeBrew(
                    CupSizes.LARGE,
                    ItemsRegistry.CUP_LARGE.get(),
                    Brews.CARROT,
                    new Item.Properties().group(RavenCoffee.CUP_LARGE_TAB)
                            .food(new Food.Builder()
                                    .hunger(4)
                                    .saturation(4.1f)
                                    .build())
            )
    );
    public static final RegistryObject<Item> COFFEE_MUG_BREW_CARROT = BREWS.register(
            "coffee_mug_brew_carrot",
            () -> new CoffeeBrew(
                    CupSizes.MEDIUM,
                    ItemsRegistry.COFFEE_MUG.get(),
                    Brews.CARROT,
                    new Item.Properties().group(RavenCoffee.COFFEE_MUG_TAB)
                            .food(new Food.Builder()
                                    .hunger(3)
                                    .saturation(3.6f)
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
                    new Item.Properties().group(RavenCoffee.CUP_MEDIUM_TAB)
                            .food(new Food.Builder()
                                    .hunger(5)
                                    .saturation(12.0f)
                                    .build())
            )
    );
    public static final RegistryObject<Item> CUP_LARGE_BREW_CARROT_GOLDEN = BREWS.register(
            "cup_large_brew_carrot_golden",
            () -> new CoffeeBrew(
                    CupSizes.LARGE,
                    ItemsRegistry.CUP_LARGE.get(),
                    Brews.CARROT_GOLDEN,
                    new Item.Properties().group(RavenCoffee.CUP_LARGE_TAB)
                            .food(new Food.Builder()
                                    .hunger(8)
                                    .saturation(17.0f)
                                    .build())
            )
    );
    public static final RegistryObject<Item> COFFEE_MUG_BREW_CARROT_GOLDEN = BREWS.register(
            "coffee_mug_brew_carrot_golden",
            () -> new CoffeeBrew(
                    CupSizes.MEDIUM,
                    ItemsRegistry.COFFEE_MUG.get(),
                    Brews.CARROT_GOLDEN,
                    new Item.Properties().group(RavenCoffee.COFFEE_MUG_TAB)
                            .food(new Food.Builder()
                                    .hunger(5)
                                    .saturation(12.0f)
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
                    new Item.Properties().group(RavenCoffee.CUP_MEDIUM_TAB)
                            .food(new Food.Builder()
                                    .hunger(4)
                                    .saturation(1.0f)
                                    .build())
            )
    );
    public static final RegistryObject<Item> CUP_LARGE_BREW_COOKIESANDCREAM = BREWS.register(
            "cup_large_brew_cookiesandcream",
            () -> new CoffeeBrew(
                    CupSizes.LARGE,
                    ItemsRegistry.CUP_LARGE.get(),
                    Brews.COOKIESANDCREAM,
                    new Item.Properties().group(RavenCoffee.CUP_LARGE_TAB)
                            .food(new Food.Builder()
                                    .hunger(7)
                                    .saturation(1.5f)
                                    .build())
            )
    );
    public static final RegistryObject<Item> COFFEE_MUG_BREW_COOKIESANDCREAM = BREWS.register(
            "coffee_mug_brew_cookiesandcream",
            () -> new CoffeeBrew(
                    CupSizes.MEDIUM,
                    ItemsRegistry.COFFEE_MUG.get(),
                    Brews.COOKIESANDCREAM,
                    new Item.Properties().group(RavenCoffee.COFFEE_MUG_TAB)
                            .food(new Food.Builder()
                                    .hunger(4)
                                    .saturation(1.0f)
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
                    new Item.Properties().group(RavenCoffee.CUP_MEDIUM_TAB)
                            .food(new Food.Builder()
                                    .hunger(2)
                                    .saturation(1.0f)
                                    .build())
            )
    );
    public static final RegistryObject<Item> CUP_LARGE_BREW_END = BREWS.register(
            "cup_large_brew_end",
            () -> new ChorusBrew(
                    CupSizes.LARGE,
                    ItemsRegistry.CUP_LARGE.get(),
                    Brews.END,
                    new Item.Properties().group(RavenCoffee.CUP_LARGE_TAB)
                            .food(new Food.Builder()
                                    .hunger(3)
                                    .saturation(1.5f)
                                    .build())
            )
    );
    public static final RegistryObject<Item> COFFEE_MUG_BREW_END = BREWS.register(
            "coffee_mug_brew_end",
            () -> new ChorusBrew(
                    CupSizes.MEDIUM,
                    ItemsRegistry.COFFEE_MUG.get(),
                    Brews.END,
                    new Item.Properties().group(RavenCoffee.COFFEE_MUG_TAB)
                            .food(new Food.Builder()
                                    .hunger(2)
                                    .saturation(1.0f)
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
                    new Item.Properties().group(RavenCoffee.CUP_MEDIUM_TAB)
                            .food(new Food.Builder()
                                    .hunger(2)
                                    .saturation(1.5f)
                                    .build())
            )
    );
    public static final RegistryObject<Item> CUP_LARGE_BREW_MELON = BREWS.register(
            "cup_large_brew_melon",
            () -> new CoffeeBrew(
                    CupSizes.LARGE,
                    ItemsRegistry.CUP_LARGE.get(),
                    Brews.MELON,
                    new Item.Properties().group(RavenCoffee.CUP_LARGE_TAB)
                            .food(new Food.Builder()
                                    .hunger(3)
                                    .saturation(3.0f)
                                    .build())
            )
    );
    public static final RegistryObject<Item> COFFEE_MUG_BREW_MELON = BREWS.register(
            "coffee_mug_brew_melon",
            () -> new CoffeeBrew(
                    CupSizes.MEDIUM,
                    ItemsRegistry.COFFEE_MUG.get(),
                    Brews.MELON,
                    new Item.Properties().group(RavenCoffee.COFFEE_MUG_TAB)
                            .food(new Food.Builder()
                                    .hunger(2)
                                    .saturation(1.5f)
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
                    new Item.Properties().group(RavenCoffee.CUP_MEDIUM_TAB)
                            .food(new Food.Builder()
                                    .hunger(2)
                                    .saturation(1.5f)
                                    .build())
            )
    );
    public static final RegistryObject<Item> CUP_LARGE_BREW_MELON_GOLDEN = BREWS.register(
            "cup_large_brew_melon_golden",
            () -> new CoffeeBrew(
                    CupSizes.LARGE,
                    ItemsRegistry.CUP_LARGE.get(),
                    Brews.MELON_GOLDEN,
                    new Item.Properties().group(RavenCoffee.CUP_LARGE_TAB)
                            .food(new Food.Builder()
                                    .hunger(3)
                                    .saturation(3.0f)
                                    .build())
            )
    );
    public static final RegistryObject<Item> COFFEE_MUG_BREW_MELON_GOLDEN = BREWS.register(
            "coffee_mug_brew_melon_golden",
            () -> new CoffeeBrew(
                    CupSizes.MEDIUM,
                    ItemsRegistry.COFFEE_MUG.get(),
                    Brews.MELON_GOLDEN,
                    new Item.Properties().group(RavenCoffee.COFFEE_MUG_TAB)
                            .food(new Food.Builder()
                                    .hunger(2)
                                    .saturation(1.5f)
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
                    new Item.Properties().group(RavenCoffee.CUP_MEDIUM_TAB)
                            .food(new Food.Builder()
                                    .hunger(1)
                                    .saturation(0.5f)
                                    .build())
            )
    );
    public static final RegistryObject<Item> CUP_LARGE_BREW_NETHER = BREWS.register(
            "cup_large_brew_nether",
            () -> new CoffeeBrew(
                    CupSizes.LARGE,
                    ItemsRegistry.CUP_LARGE.get(),
                    Brews.NETHER,
                    new Item.Properties().group(RavenCoffee.CUP_LARGE_TAB)
                            .food(new Food.Builder()
                                    .hunger(2)
                                    .saturation(1.0f)
                                    .build())
            )
    );
    public static final RegistryObject<Item> COFFEE_MUG_BREW_NETHER = BREWS.register(
            "coffee_mug_brew_nether",
            () -> new CoffeeBrew(
                    CupSizes.MEDIUM,
                    ItemsRegistry.COFFEE_MUG.get(),
                    Brews.NETHER,
                    new Item.Properties().group(RavenCoffee.COFFEE_MUG_TAB)
                            .food(new Food.Builder()
                                    .hunger(1)
                                    .saturation(0.5f)
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
                    new Item.Properties().group(RavenCoffee.CUP_MEDIUM_TAB)
            )
    );
    public static final RegistryObject<Item> CUP_LARGE_BREW_PHANTASM = BREWS.register(
            "cup_large_brew_phantasm",
            () -> new CoffeeBrew(
                    CupSizes.LARGE,
                    ItemsRegistry.CUP_LARGE.get(),
                    Brews.PHANTASM,
                    new Item.Properties().group(RavenCoffee.CUP_LARGE_TAB)
            )
    );
    public static final RegistryObject<Item> COFFEE_MUG_BREW_PHANTASM = BREWS.register(
            "coffee_mug_brew_phantasm",
            () -> new CoffeeBrew(
                    CupSizes.MEDIUM,
                    ItemsRegistry.COFFEE_MUG.get(),
                    Brews.PHANTASM,
                    new Item.Properties().group(RavenCoffee.COFFEE_MUG_TAB)
            )
    );
    //           #######################################   PUMPKINSPICELATTE   #############################################
    public static final RegistryObject<Item> CUP_MEDIUM_BREW_PUMPKINSPICELATTE = BREWS.register(
            "cup_medium_brew_pumpkinspicelatte",
            () -> new CoffeeBrew(
                    CupSizes.MEDIUM,
                    ItemsRegistry.CUP_MEDIUM.get(),
                    Brews.PUMPKINSPICELATTE,
                    new Item.Properties().group(RavenCoffee.CUP_MEDIUM_TAB)
            )
    );
    public static final RegistryObject<Item> CUP_LARGE_BREW_PUMPKINSPICELATTE = BREWS.register(
            "cup_large_brew_pumpkinspicelatte",
            () -> new CoffeeBrew(
                    CupSizes.LARGE,
                    ItemsRegistry.CUP_LARGE.get(),
                    Brews.PUMPKINSPICELATTE,
                    new Item.Properties().group(RavenCoffee.CUP_LARGE_TAB)
            )
    );
    public static final RegistryObject<Item> COFFEE_MUG_BREW_PUMPKINSPICELATTE = BREWS.register(
            "coffee_mug_brew_pumpkinspicelatte",
            () -> new CoffeeBrew(
                    CupSizes.MEDIUM,
                    ItemsRegistry.COFFEE_MUG.get(),
                    Brews.PUMPKINSPICELATTE,
                    new Item.Properties().group(RavenCoffee.COFFEE_MUG_TAB)
            )
    );
    //           #######################################   PUMPKINSPICELATTE_WITH_PUMPKIN   #############################################
    public static final RegistryObject<Item> CUP_MEDIUM_BREW_PUMPKINSPICELATTE_WITH_PUMPKIN = BREWS.register(
            "cup_medium_brew_pumpkinspicelatte_with_pumpkin",
            () -> new CoffeeBrew(
                    CupSizes.MEDIUM,
                    ItemsRegistry.CUP_MEDIUM.get(),
                    Brews.PUMPKINSPICELATTE_WITH_PUMPKIN,
                    new Item.Properties().group(RavenCoffee.CUP_MEDIUM_TAB)
            )
    );
    public static final RegistryObject<Item> CUP_LARGE_BREW_PUMPKINSPICELATTE_WITH_PUMPKIN = BREWS.register(
            "cup_large_brew_pumpkinspicelatte_with_pumpkin",
            () -> new CoffeeBrew(
                    CupSizes.LARGE,
                    ItemsRegistry.CUP_LARGE.get(),
                    Brews.PUMPKINSPICELATTE_WITH_PUMPKIN,
                    new Item.Properties().group(RavenCoffee.CUP_LARGE_TAB)
            )
    );
    public static final RegistryObject<Item> COFFEE_MUG_BREW_PUMPKINSPICELATTE_WITH_PUMPKIN = BREWS.register(
            "coffee_mug_brew_pumpkinspicelatte_with_pumpkin",
            () -> new CoffeeBrew(
                    CupSizes.MEDIUM,
                    ItemsRegistry.COFFEE_MUG.get(),
                    Brews.PUMPKINSPICELATTE_WITH_PUMPKIN,
                    new Item.Properties().group(RavenCoffee.COFFEE_MUG_TAB)
            )
    );
}
