package com.TheWandererRaven.ravencoffee.util.registries;

import com.TheWandererRaven.ravencoffee.RavenCoffee;
import com.TheWandererRaven.ravencoffee.customClasses.Brews;
import com.TheWandererRaven.ravencoffee.customClasses.CupSizes;
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
    //           #######################################   AMERICANO   #############################################
    public static final RegistryObject<Item> CUP_SMALL_BREW_AMERICAN = BREWS.register(
            "cup_small_brew_american",
            () -> new CoffeeBrew(
                    CupSizes.TINY,
                    ItemsRegistry.CUP_SMALL.get(),
                    Brews.AMERICAN,
                    new Item.Properties().group(RavenCoffee.TAB)
            )
    );
    public static final RegistryObject<Item> CUP_MEDIUM_BREW_AMERICAN = BREWS.register(
            "cup_medium_brew_american",
            () -> new CoffeeBrew(
                    CupSizes.MEDIUM,
                    ItemsRegistry.CUP_MEDIUM.get(),
                    Brews.AMERICAN,
                    new Item.Properties().group(RavenCoffee.TAB)
            )
    );
    public static final RegistryObject<Item> CUP_LARGE_BREW_AMERICAN = BREWS.register(
            "cup_large_brew_american",
            () -> new CoffeeBrew(
                    CupSizes.LARGE,
                    ItemsRegistry.CUP_LARGE.get(),
                    Brews.AMERICAN,
                    new Item.Properties().group(RavenCoffee.TAB)
            )
    );
    public static final RegistryObject<Item> COFFEE_MUG_BREW_AMERICAN = BREWS.register(
            "coffee_mug_brew_american",
            () -> new CoffeeBrew(
                    CupSizes.MEDIUM,
                    ItemsRegistry.COFFEE_MUG.get(),
                    Brews.AMERICAN,
                    new Item.Properties().group(RavenCoffee.TAB)
            )
    );
    //           #######################################   SUGAR   #############################################
    public static final RegistryObject<Item> CUP_MEDIUM_BREW_SUGAR = BREWS.register(
            "cup_medium_brew_sugar",
            () -> new CoffeeBrew(
                    CupSizes.MEDIUM,
                    ItemsRegistry.CUP_MEDIUM.get(),
                    Brews.SUGAR,
                    new Item.Properties().group(RavenCoffee.TAB)
            )
    );
    public static final RegistryObject<Item> CUP_LARGE_BREW_SUGAR = BREWS.register(
            "cup_large_brew_sugar",
            () -> new CoffeeBrew(
                    CupSizes.LARGE,
                    ItemsRegistry.CUP_LARGE.get(),
                    Brews.SUGAR,
                    new Item.Properties().group(RavenCoffee.TAB)
            )
    );
    public static final RegistryObject<Item> COFFEE_MUG_BREW_SUGAR = BREWS.register(
            "coffee_mug_brew_sugar",
            () -> new CoffeeBrew(
                    CupSizes.MEDIUM,
                    ItemsRegistry.COFFEE_MUG.get(),
                    Brews.SUGAR,
                    new Item.Properties().group(RavenCoffee.TAB)
            )
    );
    //           #######################################   APPLE   #############################################
    public static final RegistryObject<Item> CUP_MEDIUM_BREW_APPLE = BREWS.register(
            "cup_medium_brew_apple",
            () -> new CoffeeBrew(
                    CupSizes.MEDIUM,
                    ItemsRegistry.CUP_MEDIUM.get(),
                    Brews.APPLE,
                    new Item.Properties().group(RavenCoffee.TAB)
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
                    new Item.Properties().group(RavenCoffee.TAB)
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
                    new Item.Properties().group(RavenCoffee.TAB)
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
                    new Item.Properties().group(RavenCoffee.TAB)
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
                    new Item.Properties().group(RavenCoffee.TAB)
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
                    new Item.Properties().group(RavenCoffee.TAB)
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
                    new Item.Properties().group(RavenCoffee.TAB)
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
                    new Item.Properties().group(RavenCoffee.TAB)
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
                    new Item.Properties().group(RavenCoffee.TAB)
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
                    new Item.Properties().group(RavenCoffee.TAB)
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
                    new Item.Properties().group(RavenCoffee.TAB)
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
                    new Item.Properties().group(RavenCoffee.TAB)
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
                    new Item.Properties().group(RavenCoffee.TAB)
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
                    new Item.Properties().group(RavenCoffee.TAB)
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
                    new Item.Properties().group(RavenCoffee.TAB)
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
                    new Item.Properties().group(RavenCoffee.TAB)
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
                    new Item.Properties().group(RavenCoffee.TAB)
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
                    new Item.Properties().group(RavenCoffee.TAB)
                            .food(new Food.Builder()
                                    .saturation(0.4f)
                                    .build())
            )
    );
}
