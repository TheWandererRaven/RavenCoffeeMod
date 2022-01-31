package com.thewandererraven.ravencoffee.items;

import com.thewandererraven.ravencoffee.RavenCoffee;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.Fluids;

public class CoffeeCup extends BucketItem {
    public CoffeeCup() {
        super(() -> Fluids.EMPTY,
                new Item.Properties()
                .tab(RavenCoffee.GENERAL_TAB)
                );
    }
}
