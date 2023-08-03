package com.thewandererraven.ravencoffee.items;

import com.thewandererraven.ravencoffee.RavenCoffeeForge;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

public class ItemBase extends Item {
    public ItemBase() {
        super(new Item.Properties());
    }
    public ItemBase(CreativeModeTab tab) {
        super(new Item.Properties());
    }
    public ItemBase(Properties props) {
        super(props);
    }
}
