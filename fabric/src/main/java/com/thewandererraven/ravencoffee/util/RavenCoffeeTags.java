package com.thewandererraven.ravencoffee.util;

import com.thewandererraven.ravencoffee.Constants;
import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class RavenCoffeeTags {
    public static class Items {
        public static final TagKey<Item> CUPS = tag("cups");
        public static final TagKey<Item> CUPS_SMALL = tag("cups_small");
        public static final TagKey<Item> CUPS_MEDIUM = tag("cups_medium");
        public static final TagKey<Item> CUPS_LARGE = tag("cups_large");
        public static final TagKey<Item> BREW_INGREDIENTS = tag("brew_ingredients");
        private static TagKey<Item> tag(String name) {
            return TagKey.of(Registry.ITEM_KEY, new Identifier(Constants.MOD_ID, name));
        }
    }
}
