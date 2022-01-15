package com.TheWandererRaven.ravencoffee.customClasses;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;

import java.util.List;

public class BrewEffect {
    public MobEffect effect;
    public double duration;
    public double chance;
    public boolean isForRemoving;
    public BrewEffect(MobEffect _effect, double _duration, double _chance) {
        this.effect = _effect;
        this.duration = _duration;
        this.chance = _chance;
        this.isForRemoving = false;
    }
    public BrewEffect(MobEffect _effect) {
        this.effect = _effect;
        this.duration = 0.0d;
        this.chance = 0.0d;
        this.isForRemoving = true;
    }
}
