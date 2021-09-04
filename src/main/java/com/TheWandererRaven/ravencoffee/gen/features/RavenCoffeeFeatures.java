package com.TheWandererRaven.ravencoffee.gen.features;

import com.TheWandererRaven.ravencoffee.util.registries.BlocksRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.BushFoliagePlacer;
import net.minecraft.world.gen.placement.ConfiguredPlacement;
import net.minecraft.world.gen.placement.NoPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;

public class RavenCoffeeFeatures {
    public static final ConfiguredFeature<?, ?> COFFEE_TREE = register(
            "coffee_tree",
            RavenCoffeeFeature.COFFEE_TREE.get()
                    .withConfiguration(Features.Configs.GRASS_PATCH_CONFIG)
                    .withPlacement(Features.Placements.PATCH_PLACEMENT)
                    .func_242731_b(2)
    );
    public static class Configs {
        /*
        public static final BlockClusterFeatureConfig COFFEE_TREE_PATCH_CONFIG = (new BlockClusterFeatureConfig.Builder(
                new SimpleBlockStateProvider(Features.States.GRASS),
                SimpleBlockPlacer.PLACER)
        ).tries(32).build();
         */
    }
    public static class Placements {
        public static final ConfiguredPlacement<?> COFFEE_TREE_PLACEMENT = Placement.SPREAD_32_ABOVE.configure(NoPlacementConfig.INSTANCE);
    }
    public static class States {
        protected static final BlockState COFFEE_TREE_TRUNK = BlocksRegistry.COFFEE_TREE_TRUNK_BLOCK.get().getDefaultState();
        protected static final BlockState COFFEE_TREE_LEAVES = BlocksRegistry.COFFEE_TREE_TRUNK_BLOCK.get().getDefaultState();
    }
    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String key, ConfiguredFeature<FC, ?> configuredFeature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, key, configuredFeature);
    }
}
