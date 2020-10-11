package com.TheWandererRaven.ravencoffee.items;

import net.minecraft.item.Food;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

import java.util.function.Supplier;

public class CoffeeBrews {
    public static final Supplier<CoffeeBrew> AMERICAN = () -> new CoffeeBrew(
            new Food.Builder()
                    .hunger(4)
                    .saturation(1.2f)
                    .setAlwaysEdible()
                    //                          Effect  ||   seconds * 20(ticks) || level     || chance of effect
                    .effect(() -> new EffectInstance(Effects.SPEED, 1800, 0), 0.4f)
                    .effect(() -> new EffectInstance(Effects.HASTE, 3600, 0), 1.0f)
                    .build()
    );
}
