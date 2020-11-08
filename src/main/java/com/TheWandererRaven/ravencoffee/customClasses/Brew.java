package com.TheWandererRaven.ravencoffee.customClasses;

import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Potion;

import java.util.ArrayList;
import java.util.List;

public class Brew {
    public List<EffectInstance> effects;
    public List<Double> effectsChances;
    public List<Effect> removableEffects;

    public Brew(){
        this.effects = new ArrayList<>();
        this.effectsChances = new ArrayList<>();
        this.removableEffects = new ArrayList<>();
    }

    public Brew addEffect(EffectInstance effect, double effectChance) {
        this.effects.add(effect);
        this.effectsChances.add(effectChance);
        return this;
    }

    public Brew addRemovableEffect(Effect effect) {
        this.removableEffects.add(effect);
        return this;
    }
}
