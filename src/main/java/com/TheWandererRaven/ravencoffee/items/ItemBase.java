package com.TheWandererRaven.ravencoffee.items;

import com.TheWandererRaven.ravencoffee.RavenCoffee;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class ItemBase extends Item {
    public ItemBase() {
        super(new Item.Properties().group(RavenCoffee.GENERAL_TAB));
    }
    public ItemBase(ItemGroup tab) {
        super(new Item.Properties().group(tab));
    }
}
