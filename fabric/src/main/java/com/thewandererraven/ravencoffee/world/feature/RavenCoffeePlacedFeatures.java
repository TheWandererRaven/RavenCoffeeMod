package com.thewandererraven.ravencoffee.world.feature;

import com.thewandererraven.ravencoffee.Constants;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.*;

import java.util.List;

public class RavenCoffeePlacedFeatures {

    public static final RegistryKey<PlacedFeature> COFFEE_TREE_JUNGLE_KEY = registerKey("coffee_tree_jungle");
    public static final RegistryKey<PlacedFeature> COFFEE_TREE_SAVANNA_KEY = registerKey("coffee_tree_savanna");


    public static RegistryKey<PlacedFeature> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(Constants.MOD_ID, name));
    }

    public static void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key, RegistryEntry<ConfiguredFeature<?, ?>> configuredFeature, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuredFeature, modifiers));
    }

    public static void bootstrap(Registerable<PlacedFeature> context) {
        var configuredFeatureEntryLookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        register(context, COFFEE_TREE_JUNGLE_KEY,
                configuredFeatureEntryLookup.getOrThrow(RavenCoffeeConfiguredFeatures.COFFEE_TREE_PATCH_JUNGLE_KEY),
                List.of(
                        CountPlacementModifier.of(10),
                        RarityFilterPlacementModifier.of(90),
                        SquarePlacementModifier.of(),
                        HeightmapPlacementModifier.of(Heightmap.Type.WORLD_SURFACE),
                        BiomePlacementModifier.of()
                )
        );
        register(context, COFFEE_TREE_SAVANNA_KEY,
                configuredFeatureEntryLookup.getOrThrow(RavenCoffeeConfiguredFeatures.COFFEE_TREE_PATCH_DEFAULT_KEY),
                List.of(
                        CountPlacementModifier.of(17),
                        RarityFilterPlacementModifier.of(200),
                        SquarePlacementModifier.of(),
                        HeightmapPlacementModifier.of(Heightmap.Type.WORLD_SURFACE),
                        BiomePlacementModifier.of()
                )
        );
    }

    public static void registerPlacedFeatures() {
        Constants.LOGGER.info("Registering Raven Coffee Placed Features");
    }
}
