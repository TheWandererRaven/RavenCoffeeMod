package com.thewandererraven.ravencoffee.util.registries;

import com.thewandererraven.ravencoffee.RavenCoffee;
import com.thewandererraven.ravencoffee.gen.features.DefaultCoffeeTreesFeature;
import com.thewandererraven.ravencoffee.gen.featureConfigs.DualBlockPileFeatureConfig;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public abstract class FeaturesRegistry {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, RavenCoffee.MOD_ID);
    public static final RegistryObject<DefaultCoffeeTreesFeature<DualBlockPileFeatureConfig>> COFFEE_TREE = FEATURES.register(
            "coffee_tree",
            () -> new DefaultCoffeeTreesFeature<DualBlockPileFeatureConfig>(DualBlockPileFeatureConfig.CODEC)
    );
}
