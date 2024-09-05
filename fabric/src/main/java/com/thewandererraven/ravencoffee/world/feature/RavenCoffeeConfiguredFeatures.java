package com.thewandererraven.ravencoffee.world.feature;

import com.google.common.collect.ImmutableSet;
import com.thewandererraven.ravencoffee.Constants;
import com.thewandererraven.ravencoffee.blocks.CoffeeTreeTrunkBlock;
import com.thewandererraven.ravencoffee.blocks.RavenCoffeeBlocks;
import com.thewandererraven.ravencoffee.world.feature.configs.DualBlockPileFeatureConfig;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

public class RavenCoffeeConfiguredFeatures {

    public static final RegistryKey<ConfiguredFeature<?, ?>> COFFEE_TREE_PATCH_DEFAULT_KEY = registerKey("coffee_tree_patch_default");
    public static final RegistryKey<ConfiguredFeature<?, ?>> COFFEE_TREE_PATCH_JUNGLE_KEY = registerKey("coffee_tree_patch_jungle");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        register(context, COFFEE_TREE_PATCH_DEFAULT_KEY,
                RavenCoffeeFeatures.COFFEE_TREE,
                FeatureConfigs.COFFEE_TREE_PATCH_DEFAULT_CONFIG
        );
        register(context, COFFEE_TREE_PATCH_JUNGLE_KEY,
                RavenCoffeeFeatures.COFFEE_TREE,
                FeatureConfigs.COFFEE_TREE_PATCH_JUNGLE_CONFIG
        );
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(Constants.MOD_ID, name));
    }

    public static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> registerable, RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC config) {
        registerable.register(key, new ConfiguredFeature(feature, config));
    }

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
        protected static final BlockState COFFEE_TREE_TRUNK = RavenCoffeeBlocks.COFFEE_TREE_TRUNK_BLOCK.getDefaultState().with(CoffeeTreeTrunkBlock.HAS_LEAVES, true);
        protected static final BlockState COFFEE_TREE_LEAVES = RavenCoffeeBlocks.COFFEE_TREE_LEAVES_BLOCK.getDefaultState();
        protected static final BlockState GRASS_BLOCK = Blocks.GRASS_BLOCK.getDefaultState();
    }

    public static void registerConfiguredFeatures() {
        Constants.LOGGER.info("Registering Raven Coffee Configured Features");
    }
}
