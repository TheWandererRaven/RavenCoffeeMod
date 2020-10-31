package com.TheWandererRaven.ravencoffee.util.registries;

import com.TheWandererRaven.ravencoffee.RavenCoffee;
import com.TheWandererRaven.ravencoffee.customClasses.Brews;
import com.TheWandererRaven.ravencoffee.customClasses.CupSizes;
import com.TheWandererRaven.ravencoffee.items.CoffeeBrew;
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
}
