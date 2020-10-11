package com.TheWandererRaven.ravencoffee.items;

import com.TheWandererRaven.ravencoffee.RavenCoffee;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;

public class CoffeeCup extends BucketItem {
    public CoffeeCup() {
        super(() -> Fluids.EMPTY,
                new Item.Properties()
                .group(RavenCoffee.TAB)
                );
    }
}
