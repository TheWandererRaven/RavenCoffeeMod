package com.thewandererraven.ravencoffee.world.features;

import com.thewandererraven.ravencoffee.Constants;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class RavenCoffeePlacedFeatures {

    public static final ResourceKey<PlacedFeature> COFFEE_TREE_JUNGLE_KEY = registerKey("coffee_tree_jungle");
    public static final ResourceKey<PlacedFeature> COFFEE_TREE_SAVANNA_KEY = registerKey("coffee_tree_savanna");


    public static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(Constants.MOD_ID, name));
    }

    public static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuredFeature, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuredFeature, modifiers));
    }

    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        var configuredFeatureEntryLookup = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, COFFEE_TREE_JUNGLE_KEY,
                configuredFeatureEntryLookup.getOrThrow(RavenCoffeeConfiguredFeatures.COFFEE_TREE_PATCH_JUNGLE_KEY),
                List.of(
                        CountPlacement.of(10),
                        RarityFilter.onAverageOnceEvery(90),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                        BiomeFilter.biome()
                )
        );
        register(context, COFFEE_TREE_SAVANNA_KEY,
                configuredFeatureEntryLookup.getOrThrow(RavenCoffeeConfiguredFeatures.COFFEE_TREE_PATCH_DEFAULT_KEY),
                List.of(
                        CountPlacement.of(10),
                        RarityFilter.onAverageOnceEvery(200),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                        BiomeFilter.biome()
                )
        );
    }

}
