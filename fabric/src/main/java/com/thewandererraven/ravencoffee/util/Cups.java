package com.thewandererraven.ravencoffee.util;

import com.thewandererraven.ravencoffee.items.RavenCoffeeItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;

public class Cups {
    public ArrayList<Item> Cups = new ArrayList<Item>(Arrays.asList(
            RavenCoffeeItems.COFFEE_MUG,
            RavenCoffeeItems.CUP_SMALL,
            RavenCoffeeItems.CUP_MEDIUM,
            RavenCoffeeItems.CUP_LARGE
    ));

    public static boolean isCup(ItemStack item) {
        return item.isIn(ModTags.Items.CUPS);
    }

    public static String getCupSizeName(Item item) {
        return switch (getCupSize(item)) {
            case SMALL -> "small";
            case MEDIUM -> "medium";
            case LARGE -> "large";
            default -> "no size";
        };
    }

    public static Sizes getCupSize(Item item) {
        if(item.getRegistryEntry().isIn(ModTags.Items.CUPS_SMALL)) return Sizes.SMALL;
        //if(item.isIn(ModTags.Items.CUPS_MEDIUM)) return CupSizes.MEDIUM;
        if(item.getRegistryEntry().isIn(ModTags.Items.CUPS_LARGE)) return Sizes.LARGE;
        return Sizes.MEDIUM;
    }

    public enum Sizes {
        SMALL,
        MEDIUM,
        LARGE
    }
}
