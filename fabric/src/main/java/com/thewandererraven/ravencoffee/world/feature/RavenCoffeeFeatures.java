package com.thewandererraven.ravencoffee.world.feature;

import com.thewandererraven.ravencoffee.Constants;
import com.thewandererraven.ravencoffee.world.feature.configs.DualBlockPileFeatureConfig;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;

public class RavenCoffeeFeatures {
    public static final Feature<DualBlockPileFeatureConfig> COFFEE_TREE =
            register(
                    "coffee_tree",
                    new DefaultCoffeeTreesFeature<DualBlockPileFeatureConfig>(DualBlockPileFeatureConfig.CODEC)
            );


    private static <C extends FeatureConfig, F extends Feature<C>> F register(String name, F feature) {
        return Registry.register(Registry.FEATURE, name, feature);
    }

    public static void registerFeatures() {
        Constants.LOGGER.info("Registering Raven Coffee Features");
    }
}
