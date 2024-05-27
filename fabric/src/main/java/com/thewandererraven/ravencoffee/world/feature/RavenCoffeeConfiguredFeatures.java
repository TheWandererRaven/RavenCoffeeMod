package com.thewandererraven.ravencoffee.world.feature;

import com.google.common.collect.ImmutableSet;
import com.thewandererraven.ravencoffee.Constants;
import com.thewandererraven.ravencoffee.blocks.RavenCoffeeBlocks;
import com.thewandererraven.ravencoffee.world.feature.configs.DualBlockPileFeatureConfig;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

public class RavenCoffeeConfiguredFeatures {


    public static final RegistryEntry<ConfiguredFeature<DualBlockPileFeatureConfig, ?>> COFFEE_TREE = ConfiguredFeatures.register(
            "coffee_tree_configured_feature",
            RavenCoffeeFeatures.COFFEE_TREE,
            FeatureConfigs.COFFEE_TREE_PATCH_DEFAULT_CONFIG
    );

    public static class FeatureConfigs {
        public static final DualBlockPileFeatureConfig COFFEE_TREE_PATCH_DEFAULT_CONFIG = (
                new DualBlockPileFeatureConfig.Builder(
                        BlockStateProvider.of(States.COFFEE_TREE_TRUNK),
                        BlockStateProvider.of(States.COFFEE_TREE_LEAVES)
                ))
                .tries(25)
                .whitelist(ImmutableSet.of(States.GRASS_BLOCK.getBlock()))
                .func_227317_b_()
                .xSpread(10)
                .zSpread(10)
                .build();
        public static final DualBlockPileFeatureConfig COFFEE_TREE_PATCH_JUNGLE_CONFIG = (
                new DualBlockPileFeatureConfig.Builder(
                        BlockStateProvider.of(States.COFFEE_TREE_TRUNK),
                        BlockStateProvider.of(States.COFFEE_TREE_LEAVES)
                ))
                .tries(64)
                .whitelist(ImmutableSet.of(States.GRASS_BLOCK.getBlock()))
                .func_227317_b_()
                .xSpread(7)
                .zSpread(3)
                .build();
    }

    public static class States {
        protected static final BlockState COFFEE_TREE_TRUNK = RavenCoffeeBlocks.COFFEE_TREE_TRUNK_BLOCK.getDefaultState();
        protected static final BlockState COFFEE_TREE_LEAVES = RavenCoffeeBlocks.COFFEE_TREE_LEAVES_BLOCK.getDefaultState();
        protected static final BlockState GRASS_BLOCK = Blocks.GRASS_BLOCK.getDefaultState();
    }

    public static void registerConfiguredFeatures() {
        Constants.LOGGER.info("Registering Raven Coffee Configured Features");
    }
}
