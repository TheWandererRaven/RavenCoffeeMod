package com.thewandererraven.ravencoffee.world.features;

import com.thewandererraven.ravencoffee.Constants;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class RavenCoffeePlacedFeatures {
    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES = DeferredRegister.create(
            Registries.PLACED_FEATURE.registry(),
            Constants.MOD_ID
    );

    public static final ResourceKey<PlacedFeature> COFFEE_TREE_JUNGLE_KEY = createKey("coffee_tree_jungle_placed_feature");
    public static final ResourceKey<PlacedFeature> COFFEE_TREE_SAVANNA_KEY = createKey("coffee_tree_savanna_placed_feature");

    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, COFFEE_TREE_JUNGLE_KEY,
                configuredFeatures.getOrThrow(RavenCoffeeConfiguredFeatures.COFFEE_TREE_KEY),
                List.of(
                        CountPlacement.of(10),
                        RarityFilter.onAverageOnceEvery(90),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                        BiomeFilter.biome()
                )
        );
        register(context, COFFEE_TREE_SAVANNA_KEY,
                configuredFeatures.getOrThrow(RavenCoffeeConfiguredFeatures.COFFEE_TREE_KEY),
                List.of(
                        CountPlacement.of(10),
                        RarityFilter.onAverageOnceEvery(200),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                        BiomeFilter.biome()
                )
        );
    }
/*
    public static final RegistryObject<PlacedFeature> COFFEE_TREE_JUNGLE = PLACED_FEATURES.register(
            "coffee_tree_jungle_placed_feature",
            () -> new PlacedFeature(
                    RavenCoffeeConfiguredFeatures.COFFEE_TREE_KEY.get(),
                    List.of(
                            CountPlacement.of(10),
                            RarityFilter.onAverageOnceEvery(90),
                            InSquarePlacement.spread(),
                            PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                            BiomeFilter.biome()
                    )
            )
    );

    public static final RegistryObject<PlacedFeature> COFFEE_TREE_SAVANNA = PLACED_FEATURES.register(
            "coffee_tree_savanna_placed_feature",
            () -> new PlacedFeature(
                    RavenCoffeeConfiguredFeatures.COFFEE_TREE.getHolder().get(),
                    List.of(
                            CountPlacement.of(10),
                            RarityFilter.onAverageOnceEvery(200),
                            InSquarePlacement.spread(),
                            PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                            BiomeFilter.biome()
                    )
            )
    );

 */

    public static ResourceKey<PlacedFeature> createKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(Constants.MOD_ID, name));
    }

    public static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
    public static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration, PlacementModifier... modifiers) {
        register(context, key, configuration, List.of(modifiers));
    }
}
