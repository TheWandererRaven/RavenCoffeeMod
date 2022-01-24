package com.TheWandererRaven.ravencoffee.util.registries;

import com.TheWandererRaven.ravencoffee.RavenCoffee;
import com.TheWandererRaven.ravencoffee.gen.features.DefaultCoffeeTreesFeature;
import com.TheWandererRaven.ravencoffee.gen.featureConfigs.DualBlockPileFeatureConfig;
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
