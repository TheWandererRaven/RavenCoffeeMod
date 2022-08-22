package com.thewandererraven.ravencoffee.util.registries;

import com.thewandererraven.ravencoffee.RavenCoffee;
import com.thewandererraven.ravencoffee.gen.features.DefaultCoffeeTreesFeature;
import com.thewandererraven.ravencoffee.gen.featureConfigs.DualBlockPileFeatureConfig;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.RandomPatchFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public abstract class FeaturesRegistry {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, RavenCoffee.MOD_ID);
    public static final RegistryObject<Feature> COFFEE_TREE =
            FEATURES.register(
            "coffee_tree",
            () -> new DefaultCoffeeTreesFeature<DualBlockPileFeatureConfig>(DualBlockPileFeatureConfig.CODEC)
    );

}
