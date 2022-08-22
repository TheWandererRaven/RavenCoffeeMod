package com.thewandererraven.ravencoffee.world.features;

import com.google.common.collect.ImmutableSet;
import com.thewandererraven.ravencoffee.RavenCoffee;
import com.thewandererraven.ravencoffee.gen.featureConfigs.DualBlockPileFeatureConfig;
import com.thewandererraven.ravencoffee.util.registries.BlocksRegistry;
import com.thewandererraven.ravencoffee.util.registries.FeaturesRegistry;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ConfiguredFeatures {
    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES = DeferredRegister.create(
            Registry.CONFIGURED_FEATURE_REGISTRY, RavenCoffee.MOD_ID
    );

    public static final RegistryObject<ConfiguredFeature<?, ?>> COFFEE_TREE = CONFIGURED_FEATURES.register(
            "coffee_tree_configured_feature",
            () -> new ConfiguredFeature<>(
                    FeaturesRegistry.COFFEE_TREE.get(),
                    FeatureConfigs.COFFEE_TREE_PATCH_DEFAULT_CONFIG)
    );

    public static class FeatureConfigs {
        public static final DualBlockPileFeatureConfig COFFEE_TREE_PATCH_DEFAULT_CONFIG = (
                new DualBlockPileFeatureConfig.Builder(
                        SimpleStateProvider.simple(States.COFFEE_TREE_TRUNK),
                        SimpleStateProvider.simple(States.COFFEE_TREE_LEAVES)
                ))
                .tries(25)
                .whitelist(ImmutableSet.of(States.GRASS_BLOCK.getBlock()))
                .func_227317_b_()
                .xSpread(10)
                .zSpread(10)
                .build();
        public static final DualBlockPileFeatureConfig COFFEE_TREE_PATCH_JUNGLE_CONFIG = (
                new DualBlockPileFeatureConfig.Builder(
                        SimpleStateProvider.simple(States.COFFEE_TREE_TRUNK),
                        SimpleStateProvider.simple(States.COFFEE_TREE_LEAVES)
                ))
                .tries(64)
                .whitelist(ImmutableSet.of(States.GRASS_BLOCK.getBlock()))
                .func_227317_b_()
                .xSpread(7)
                .zSpread(3)
                .build();
    }
    public static class States {
        protected static final BlockState COFFEE_TREE_TRUNK = BlocksRegistry.COFFEE_TREE_TRUNK_BLOCK.get().defaultBlockState();
        protected static final BlockState COFFEE_TREE_LEAVES = BlocksRegistry.COFFEE_TREE_LEAVES_BLOCK.get().defaultBlockState();
        protected static final BlockState GRASS_BLOCK = Blocks.GRASS_BLOCK.defaultBlockState();
    }
}
