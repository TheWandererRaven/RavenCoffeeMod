package com.thewandererraven.ravencoffee.util;

import com.thewandererraven.ravencoffee.Constants;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModTags {
    public static class Items {
        public static final TagKey<Item> CUPS = tag("cups");
        public static final TagKey<Item> CUPS_SMALL = tag("cups_small");
        public static final TagKey<Item> CUPS_MEDIUM = tag("cups_medium");
        public static final TagKey<Item> CUPS_LARGE = tag("cups_large");
        public static final TagKey<Item> BREW_INGREDIENTS = tag("brew_ingredients");
        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(Constants.MOD_ID, name));
        }
        private static TagKey<Item> forgeTag(String name) {
            return ItemTags.create(new ResourceLocation("forge", name));
        }
    }
}
