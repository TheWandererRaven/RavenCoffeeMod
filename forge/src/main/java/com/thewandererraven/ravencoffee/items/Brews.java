package com.thewandererraven.ravencoffee.items;

import com.thewandererraven.ravenbrewscore.Brew;
import net.minecraft.world.effect.MobEffects;

public class Brews {
    // duration => x = seconds * 20
    public static final Brew BASIC = new Brew()
            .addEffect(MobEffects.DIG_SPEED,3600, 1.0d)
            .addEffect(MobEffects.MOVEMENT_SPEED, 1800, 0.3d);
    public static final Brew ESPRESSO = new Brew()
            .addEffect(MobEffects.DIG_SPEED,3600, 1.0d)
            .addEffect(MobEffects.MOVEMENT_SPEED, 3600, 1.0d);
    // TODO: ADD COUNTER-EFFECT TO ESPRESSO AFTER SOME TIME

    public static final Brew SUGAR = new Brew()
            .addEffect(MobEffects.MOVEMENT_SPEED,1800, 1.0d)
            .addEffect(MobEffects.DIG_SPEED, 900, 0.2d);

    public static final Brew APPLE = new Brew()
            .addEffect(MobEffects.DIG_SPEED,1800, 0.5d)
            .addEffect(MobEffects.MOVEMENT_SPEED, 900, 0.1d);

    public static final Brew BERRY = new Brew()
            .addEffect(MobEffects.DIG_SPEED,1800, 0.5d)
            .addEffect(MobEffects.MOVEMENT_SPEED, 900, 0.1d);

    public static final Brew HONEY = new Brew()
            .addEffect(MobEffects.MOVEMENT_SPEED,3600, 1.0d)
            .addEffect(MobEffects.REGENERATION, 900, 0.2d)
            .addRemovableEffect(MobEffects.POISON);

    public static final Brew MILK = new Brew()
            .addEffect(MobEffects.DIG_SPEED,1800, 0.75d);

    public static final Brew CHOCOLATE = new Brew()
            .addEffect(MobEffects.HEALTH_BOOST,1800, 1.0d)
            .addEffect(MobEffects.REGENERATION, 1800, 0.5d);

    public static final Brew MOCHA = new Brew()
            .addEffect(MobEffects.HEALTH_BOOST,1800, 0.33d)
            .addEffect(MobEffects.REGENERATION, 1800, 0.33d)
            .addEffect(MobEffects.DIG_SPEED,1800, 0.33d);
    public static final Brew AWKWARD = new Brew()
            .addEffect(MobEffects.DIG_SPEED,3600, 1.0d)
            .addEffect(MobEffects.MOVEMENT_SPEED, 1800, 0.3d);
    public static final Brew CARROT = new Brew()
            .addEffect(MobEffects.DIG_SPEED,1800, 0.5d)
            .addEffect(MobEffects.MOVEMENT_SPEED, 900, 0.1d);
    public static final Brew CARROT_GOLDEN = new Brew()
            .addEffect(MobEffects.NIGHT_VISION,1800, 0.3d);
    public static final Brew COOKIESANDCREAM = new Brew()
            .addEffect(MobEffects.MOVEMENT_SPEED,900, 0.7d)
            .addEffect(MobEffects.JUMP, 900, 0.3d);
    public static final Brew END = new Brew()
            .addEffect(MobEffects.LEVITATION,200, 1.0d)
            .addEffect(MobEffects.SLOW_FALLING, 300, 1.0d);
    public static final Brew MELON = new Brew()
            .addEffect(MobEffects.DIG_SPEED,1800, 0.5d);
    public static final Brew MELON_GOLDEN = new Brew()
            .addEffect(MobEffects.HEAL, 1, 1.0d);
    public static final Brew NETHER = new Brew()
            .addEffect(MobEffects.FIRE_RESISTANCE, 1800, 1.0d)
            .addEffect(MobEffects.MOVEMENT_SPEED, 1800, 1.0d);
    public static final Brew PHANTASM = new Brew()
            .addEffect(MobEffects.INVISIBILITY, 1800, 1.0d)
            .addEffect(MobEffects.SLOW_FALLING, 1800, 1.0d)
            .addEffect(MobEffects.WEAKNESS, 1800, 1.0d);
    public static final Brew PUMPKINSPICELATTE = new Brew()
            .addEffect(MobEffects.DIG_SPEED,3600, 1.0d)
            .addEffect(MobEffects.MOVEMENT_SPEED, 1800, 0.3d);
    public static final Brew PUMPKINSPICELATTE_WITH_PUMPKIN = new Brew()
            .addEffect(MobEffects.DIG_SPEED,3600, 1.0d)
            .addEffect(MobEffects.MOVEMENT_SPEED, 1800, 0.3d);
}
