package com.thewandererraven.ravencoffee.world.feature;

import com.thewandererraven.ravencoffee.Constants;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.placementmodifier.*;

import java.util.List;

public class RavenCoffeePlacedFeatures {

    public static final RegistryEntry<PlacedFeature> COFFEE_TREE_JUNGLE = PlacedFeatures.register(
            "coffee_tree_jungle_placed_feature",
            RavenCoffeeConfiguredFeatures.COFFEE_TREE,
            List.of(
                    CountPlacementModifier.of(10),
                    RarityFilterPlacementModifier.of(90),
                    SquarePlacementModifier.of(),
                    HeightmapPlacementModifier.of(Heightmap.Type.WORLD_SURFACE),
                    BiomePlacementModifier.of()
            )
    );

    public static final RegistryEntry<PlacedFeature> COFFEE_TREE_SAVANNA = PlacedFeatures.register(
            "coffee_tree_savanna_placed_feature",
            RavenCoffeeConfiguredFeatures.COFFEE_TREE,
            List.of(
                    CountPlacementModifier.of(10),
                    RarityFilterPlacementModifier.of(200),
                    SquarePlacementModifier.of(),
                    HeightmapPlacementModifier.of(Heightmap.Type.WORLD_SURFACE),
                    BiomePlacementModifier.of()
            )
    );

    public static void registerPlacedFeatures() {
        Constants.LOGGER.info("Registering Raven Coffee Placed Features");
    }
}
