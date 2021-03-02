package com.TheWandererRaven.ravencoffee.customClasses;

import net.minecraft.potion.*;

public class Brews {
    // duration => x = seconds * 20
    public static final Brew AMERICAN = new Brew()
            .addEffect(new EffectInstance(
                    Effects.HASTE,3600), 1.0d
            )
            .addEffect(new EffectInstance(
                    Effects.SPEED, 1800), 0.3d
            );
    public static final Brew ESPRESSO = new Brew()
            .addEffect(new EffectInstance(
                    Effects.HASTE,3600, 1), 1.0d
            )
            .addEffect(new EffectInstance(
                    Effects.SPEED, 3600, 1), 1.0d
            );
    // TODO: ADD COUNTER EFFECTS TO ESPRESSO AFTER SOME TIME

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
            );
    public static final Brew AWKWARD = new Brew()
            .addEffect(new EffectInstance(
                    Effects.HASTE,3600), 1.0d
            )
            .addEffect(new EffectInstance(
                    Effects.SPEED, 1800), 0.3d
            );
    public static final Brew CARROT = new Brew()
            .addEffect(new EffectInstance(
                    Effects.HASTE,1800), 0.5d
            )
            .addEffect(new EffectInstance(
                    Effects.SPEED, 900), 0.1d
            );
    public static final Brew CARROT_GOLDEN = new Brew()
            .addEffect(new EffectInstance(
                    Effects.NIGHT_VISION,1800), 0.3d
            );
    public static final Brew COOKIESANDCREAM = new Brew()
            .addEffect(new EffectInstance(
                    Effects.SPEED,900), 0.7d
            )
            .addEffect(new EffectInstance(
                    Effects.JUMP_BOOST, 900), 0.2d
            );
    public static final Brew END = new Brew()
            .addEffect(new EffectInstance(
                    Effects.LEVITATION,200), 1.0d
            )
            .addEffect(new EffectInstance(
                    Effects.SLOW_FALLING, 300), 1.0d
            );
    public static final Brew MELON = new Brew()
            .addEffect(new EffectInstance(
                    Effects.HASTE,1800), 0.5d
            );
    public static final Brew MELON_GOLDEN = new Brew()
            .addEffect(new EffectInstance(
                    Effects.INSTANT_HEALTH, 1, 0), 1.0d
            );
    public static final Brew NETHER = new Brew()
            .addEffect(new EffectInstance(
                    Effects.FIRE_RESISTANCE, 1800), 1.0d
            )
            .addEffect(new EffectInstance(
                    Effects.SPEED, 1800), 1.0d
            );
    public static final Brew PHANTASM = new Brew()
            .addEffect(new EffectInstance(
                    Effects.INVISIBILITY, 1800), 1.0d
            )
            .addEffect(new EffectInstance(
                    Effects.SLOW_FALLING, 1800), 1.0d
            )
            .addEffect(new EffectInstance(
                    Effects.WEAKNESS, 1800), 1.0d
            );
}
