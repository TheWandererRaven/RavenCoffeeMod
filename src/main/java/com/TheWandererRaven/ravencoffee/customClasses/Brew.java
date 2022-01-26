package com.TheWandererRaven.ravencoffee.customClasses;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;

import java.util.ArrayList;
import java.util.List;

public class Brew {
    public List<BrewEffect> effects;

    public Brew(){
        this.effects = new ArrayList<>();
    }

    public Brew addEffect(MobEffect effect, int duration, double effectChance) {
        this.effects.add(new BrewEffect(effect, duration, effectChance));
        return this;
    }

    public Brew addRemovableEffect(MobEffect effect) {
        this.effects.add(new BrewEffect(effect));
        return this;
    }
}
