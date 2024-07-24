package com.thewandererraven.ravencoffee.items;

import com.thewandererraven.ravenbrewscore.Brew;
import net.minecraft.entity.effect.StatusEffects;

public class Brews {
    // duration => x = seconds * 20
    public static final Brew BASIC = new Brew()
            .addEffect(StatusEffects.HASTE,3600, 1.0d)
            .addEffect(StatusEffects.SPEED, 1800, 0.3d);
    public static final Brew ESPRESSO = new Brew()
            .addEffect(StatusEffects.HASTE,3600, 1.0d)
            .addEffect(StatusEffects.SPEED, 3600, 1.0d);
    // TODO: ADD COUNTER-EFFECT TO ESPRESSO AFTER SOME TIME

    public static final Brew SUGAR = new Brew()
            .addEffect(StatusEffects.SPEED,1800, 1.0d)
            .addEffect(StatusEffects.HASTE, 900, 0.2d);

    public static final Brew APPLE = new Brew()
            .addEffect(StatusEffects.HASTE,1800, 0.5d)
            .addEffect(StatusEffects.SPEED, 900, 0.1d);

    public static final Brew BERRY = new Brew()
            .addEffect(StatusEffects.HASTE,1800, 0.5d)
            .addEffect(StatusEffects.SPEED, 900, 0.1d);

    public static final Brew HONEY = new Brew()
            .addEffect(StatusEffects.SPEED,3600, 1.0d)
            .addEffect(StatusEffects.REGENERATION, 900, 0.2d)
            .addRemovableEffect(StatusEffects.POISON);

    public static final Brew MILK = new Brew()
            .addEffect(StatusEffects.HASTE,1800, 0.75d);

    public static final Brew CHOCOLATE = new Brew()
            .addEffect(StatusEffects.HEALTH_BOOST,1800, 1.0d)
            .addEffect(StatusEffects.REGENERATION, 1800, 0.5d);

    public static final Brew MOCHA = new Brew()
            .addEffect(StatusEffects.HEALTH_BOOST,1800, 0.33d)
            .addEffect(StatusEffects.REGENERATION, 1800, 0.33d)
            .addEffect(StatusEffects.HASTE,1800, 0.33d);
    public static final Brew AWKWARD = new Brew()
            .addEffect(StatusEffects.HASTE,3600, 1.0d)
            .addEffect(StatusEffects.SPEED, 1800, 0.3d);
    public static final Brew CARROT = new Brew()
            .addEffect(StatusEffects.HASTE,1800, 0.5d)
            .addEffect(StatusEffects.SPEED, 900, 0.1d);
    public static final Brew CARROT_GOLDEN = new Brew()
            .addEffect(StatusEffects.NIGHT_VISION,1800, 0.3d);
    public static final Brew COOKIESANDCREAM = new Brew()
            .addEffect(StatusEffects.SPEED,900, 0.7d)
            .addEffect(StatusEffects.JUMP_BOOST, 900, 0.3d);
    public static final Brew END = new Brew()
            .addEffect(StatusEffects.LEVITATION,200, 1.0d)
            .addEffect(StatusEffects.SLOW_FALLING, 300, 1.0d);
    public static final Brew MELON = new Brew()
            .addEffect(StatusEffects.HASTE,1800, 0.5d);
    public static final Brew MELON_GOLDEN = new Brew()
            .addEffect(StatusEffects.INSTANT_HEALTH, 1, 1.0d);
    public static final Brew NETHER = new Brew()
            .addEffect(StatusEffects.FIRE_RESISTANCE, 1800, 1.0d)
            .addEffect(StatusEffects.SPEED, 1800, 1.0d);
    public static final Brew PHANTASM = new Brew()
            .addEffect(StatusEffects.INVISIBILITY, 1800, 1.0d)
            .addEffect(StatusEffects.SLOW_FALLING, 1800, 1.0d)
            .addEffect(StatusEffects.WEAKNESS, 1800, 1.0d);
    public static final Brew PUMPKINSPICELATTE = new Brew()
            .addEffect(StatusEffects.HASTE,3600, 1.0d)
            .addEffect(StatusEffects.SPEED, 1800, 0.3d);
    public static final Brew PUMPKINSPICELATTE_WITH_PUMPKIN = new Brew()
            .addEffect(StatusEffects.HASTE,3600, 1.0d)
            .addEffect(StatusEffects.SPEED, 1800, 0.3d);
}
