package com.thewandererraven.ravencoffee.world.features;

import com.thewandererraven.ravencoffee.RavenCoffee;
import com.thewandererraven.ravencoffee.util.configuration.ModConfiguration;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class RavenCoffeePlacedFeatures {
    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES = DeferredRegister.create(
            Registry.PLACED_FEATURE_REGISTRY, RavenCoffee.MOD_ID
    );

    public static final RegistryObject<PlacedFeature> COFFEE_TREE_JUNGLE = PLACED_FEATURES.register(
            "coffee_tree_jungle_placed_feature",
            () -> new PlacedFeature(
                    RavenCoffeeConfiguredFeatures.COFFEE_TREE.getHolder().get(),
                    List.of(
                            CountPlacement.of(10),
                            RarityFilter.onAverageOnceEvery(ModConfiguration.COMMON.COFFEE_TREE_JUNGLE_RARITY.getDefault()),
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
                            RarityFilter.onAverageOnceEvery(ModConfiguration.COMMON.COFFEE_TREE_SAVANNA_RARITY.getDefault()),
                            InSquarePlacement.spread(),
                            PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                            BiomeFilter.biome()
                    )
            )
    );
}
