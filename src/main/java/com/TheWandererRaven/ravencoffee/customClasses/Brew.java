package com.TheWandererRaven.ravencoffee.customClasses;

import net.minecraft.potion.EffectInstance;

import java.util.ArrayList;
import java.util.List;

public class Brew {
    public List<EffectInstance> effects;
    public List<Double> effectsChances;

    public Brew(){
        this.effects = new ArrayList<>();
        this.effectsChances = new ArrayList<>();
    }

    public Brew addEffect(EffectInstance effect, double effectChance) {
        this.effects.add(effect);
        this.effectsChances.add(effectChance);
        return this;
    }
}
