package com.TheWandererRaven.ravencoffee.customClasses;

import net.minecraft.world.effect.MobEffect;

public class BrewEffect {
    public MobEffect effect;
    public int duration;
    public double chance;
    public boolean isForRemoving;
    public BrewEffect(MobEffect _effect, int _duration, double _chance) {
        this.effect = _effect;
        this.duration = _duration;
        this.chance = _chance;
        this.isForRemoving = false;
    }
    public BrewEffect(MobEffect _effect) {
        this.effect = _effect;
        this.duration = 0;
        this.chance = 0.0d;
        this.isForRemoving = true;
    }
}
