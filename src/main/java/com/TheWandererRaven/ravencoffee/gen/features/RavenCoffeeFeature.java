package com.TheWandererRaven.ravencoffee.gen.features;

import com.TheWandererRaven.ravencoffee.RavenCoffee;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public abstract class RavenCoffeeFeature {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, RavenCoffee.MOD_ID);
    public static final RegistryObject<DefaultCoffeeTreesFeature<DualBlockPileFeatureConfig>> COFFEE_TREE = FEATURES.register(
            "test_coffee_tree",
            () -> new DefaultCoffeeTreesFeature<DualBlockPileFeatureConfig>(DualBlockPileFeatureConfig.CODEC)
    );
}
