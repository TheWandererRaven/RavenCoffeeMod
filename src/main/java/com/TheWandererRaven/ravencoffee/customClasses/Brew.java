package com.TheWandererRaven.ravencoffee.customClasses;

import net.minecraft.client.resources.I18n;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.text.*;

import java.util.ArrayList;
import java.util.List;

public class Brew {
    public List<EffectInstance> effects;
    public List<Double> effectsChances;

    public Brew(){
        this.effects = new ArrayList<EffectInstance>();
        this.effectsChances = new ArrayList<Double>();
    }

    public Brew addEffect(EffectInstance effect, double effectChance) {
        this.effects.add(effect);
        this.effectsChances.add(effectChance);
        return this;
    }

    public Brew adjustToSize(double cupSize) {
        this.effects.forEach((effect) -> this.effects.set(this.effects.indexOf(effect),
                new EffectInstance(
                        effect.getPotion(),
                        (int) (effect.getDuration() * cupSize),
                        Math.max(((int) ((effect.getAmplifier() + 1) * cupSize) - 1), 0)
                )
        ));
        return this;
    }
}
