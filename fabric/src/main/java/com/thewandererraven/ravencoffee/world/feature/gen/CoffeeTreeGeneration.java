package com.thewandererraven.ravencoffee.world.feature.gen;

import com.thewandererraven.ravencoffee.world.feature.RavenCoffeePlacedFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;

public class CoffeeTreeGeneration {
    public static void generateCoffeeTrees() {
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.SAVANNA),
                GenerationStep.Feature.VEGETAL_DECORATION, RavenCoffeePlacedFeatures.COFFEE_TREE_SAVANNA.getKey().get()
        );
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.SAVANNA_PLATEAU),
                GenerationStep.Feature.VEGETAL_DECORATION, RavenCoffeePlacedFeatures.COFFEE_TREE_SAVANNA.getKey().get()
        );
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.SPARSE_JUNGLE),
                GenerationStep.Feature.VEGETAL_DECORATION, RavenCoffeePlacedFeatures.COFFEE_TREE_SAVANNA.getKey().get()
        );
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.JUNGLE),
                GenerationStep.Feature.VEGETAL_DECORATION, RavenCoffeePlacedFeatures.COFFEE_TREE_JUNGLE.getKey().get()
        );
    }
}
