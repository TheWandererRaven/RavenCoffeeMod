package com.TheWandererRaven.ravencoffee.util.registries;

import com.TheWandererRaven.ravencoffee.RavenCoffee;
import com.TheWandererRaven.ravencoffee.customClasses.Brews;
import com.TheWandererRaven.ravencoffee.customClasses.CupSizes;
import com.TheWandererRaven.ravencoffee.items.CoffeeBrew;
import com.TheWandererRaven.ravencoffee.items.ItemBase;
import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BrewsRegistry {
    public static final DeferredRegister<Item> BREWS = DeferredRegister.create(
            ForgeRegistries.ITEMS,
            RavenCoffee.MOD_ID
    );

    // ################################################# COFFEE BREWS ##################################################
    public static final RegistryObject<Item> COFFEE_CUP_BREW_AMERICAN = BREWS.register(
            "coffee_cup_brew_american",
            () -> new CoffeeBrew(
                    CupSizes.MEDIUM,
                    ItemsRegistry.COFFEE_CUP.get(),
                    Brews.AMERICAN,
                    new Item.Properties().group(RavenCoffee.TAB)
            )
    );
}
