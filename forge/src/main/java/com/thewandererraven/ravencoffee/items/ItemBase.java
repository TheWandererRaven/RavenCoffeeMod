package com.thewandererraven.ravencoffee.items;

import com.thewandererraven.ravencoffee.RavenCoffee;
import com.thewandererraven.ravencoffee.RavenCoffeeForge;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

public class ItemBase extends Item {
    public ItemBase() {
        super(new Item.Properties().tab(RavenCoffeeForge.GENERAL_TAB));
    }
    public ItemBase(CreativeModeTab tab) {
        super(new Item.Properties().tab(tab));
    }
    public ItemBase(Properties props) {
        super(props);
    }
}
