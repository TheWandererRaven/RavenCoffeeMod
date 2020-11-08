package com.TheWandererRaven.ravencoffee.customClasses;

import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class Brews {
    public static final Brew AMERICAN = new Brew()
            .addEffect(new EffectInstance(
                    Effects.HASTE,3600), 1.0d
            )
            .addEffect(new EffectInstance(
                    Effects.SPEED, 1800), 0.3d
            );

    public static final Brew SUGAR = new Brew()
            .addEffect(new EffectInstance(
                    Effects.SPEED,1800), 1.0d
            )
            .addEffect(new EffectInstance(
                    Effects.HASTE, 900), 0.2d
            );

    public static final Brew APPLE = new Brew()
            .addEffect(new EffectInstance(
                    Effects.HASTE,1800), 0.5d
            )
            .addEffect(new EffectInstance(
                    Effects.SPEED, 900), 0.1d
            );

    public static final Brew BERRY = new Brew()
            .addEffect(new EffectInstance(
                    Effects.HASTE,1800), 0.5d
            )
            .addEffect(new EffectInstance(
                    Effects.SPEED, 900), 0.1d
            );

    public static final Brew HONEY = new Brew()
            .addEffect(new EffectInstance(
                    Effects.SPEED,3600), 1.0d
            )
            .addEffect(new EffectInstance(
                    Effects.REGENERATION, 900), 0.2d
            )
            .addRemovableEffect(
                    Effects.POISON
            );

    public static final Brew MILK = new Brew()
            .addEffect(new EffectInstance(
                    Effects.HASTE,1800), 0.75d
            );

    public static final Brew CHOCOLATE = new Brew()
            .addEffect(new EffectInstance(
                    Effects.HEALTH_BOOST,1800), 1.0d
            )
            .addEffect(new EffectInstance(
                    Effects.REGENERATION, 1800), 0.5d
            );

    public static final Brew MOCHA = new Brew()
            .addEffect(new EffectInstance(
                    Effects.HEALTH_BOOST,1800), 0.33d
            )
            .addEffect(new EffectInstance(
                    Effects.REGENERATION, 1800), 0.33d
            )
            .addEffect(new EffectInstance(
                    Effects.HASTE,1800), 0.33d
            );;
}
