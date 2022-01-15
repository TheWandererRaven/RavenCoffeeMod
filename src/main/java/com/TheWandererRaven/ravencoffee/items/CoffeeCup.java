package com.TheWandererRaven.ravencoffee.items;

import com.TheWandererRaven.ravencoffee.RavenCoffee;
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
