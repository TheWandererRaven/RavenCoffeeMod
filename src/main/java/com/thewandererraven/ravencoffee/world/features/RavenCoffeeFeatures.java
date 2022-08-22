package com.thewandererraven.ravencoffee.world.features;

import com.thewandererraven.ravencoffee.RavenCoffee;
import com.thewandererraven.ravencoffee.world.features.configs.DualBlockPileFeatureConfig;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public abstract class RavenCoffeeFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, RavenCoffee.MOD_ID);
    public static final RegistryObject<Feature> COFFEE_TREE =
            FEATURES.register(
            "coffee_tree",
            () -> new DefaultCoffeeTreesFeature<DualBlockPileFeatureConfig>(DualBlockPileFeatureConfig.CODEC)
    );

}
