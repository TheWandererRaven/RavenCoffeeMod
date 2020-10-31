package com.TheWandererRaven.ravencoffee.customClasses;

import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

import java.util.function.Supplier;

public class Brews {
    public static final Brew AMERICAN = new Brew()
            .addEffect(new EffectInstance(
                    Effects.HASTE,3600), 1.0d
            )
            .addEffect(new EffectInstance(
                    Effects.SPEED, 1800), 0.3d
            );
}
