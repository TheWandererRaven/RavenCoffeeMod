package com.thewandererraven.ravencoffee.util.registries;

import com.thewandererraven.ravencoffee.gen.featureConfigs.DualBlockPileFeatureConfig;
import com.thewandererraven.ravencoffee.util.configuration.ModConfiguration;
import com.google.common.collect.ImmutableSet;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class PlacedFeaturesRegistry {
    public static final PlacedFeature PATCH_COFFEE_TREE_SAVANNAH = register(
            "patch_coffee_tree_savannah",
            ConfiguredFeatures.PATCH_COFFEE_TREE_SAVANNAH_CONFIGURED.placed()
    );
    public static final PlacedFeature PATCH_COFFEE_TREE_JUNGLE = register(
            "patch_coffee_tree_jungle",
            ConfiguredFeatures.PATCH_COFFEE_TREE_JUNGLE_CONFIGURED.placed()
    );
    public static class ConfiguredFeatures {
        public static final ConfiguredFeature<?, ?> PATCH_COFFEE_TREE_SAVANNAH_CONFIGURED =
                FeaturesRegistry.COFFEE_TREE.get()
                        .configured(FeatureConfigs.COFFEE_TREE_PATCH_DEFAULT_CONFIG);
                //.decorated(Features.Decorators.HEIGHTMAP_DOUBLE_SQUARE)
                //.count(ModConfiguration.COMMON.COFFEE_TREE_SAVANNAH_COUNT.get())
                //.rarity(ModConfiguration.COMMON.COFFEE_TREE_SAVANNAH_RARITY.get())
        public static final ConfiguredFeature<?, ?> PATCH_COFFEE_TREE_JUNGLE_CONFIGURED =
                FeaturesRegistry.COFFEE_TREE.get()
                        .configured(FeatureConfigs.COFFEE_TREE_PATCH_JUNGLE_CONFIG);
                //.decorated(Features.Decorators.HEIGHTMAP_DOUBLE_SQUARE)
                //.count(ModConfiguration.COMMON.COFFEE_TREE_JUNGLE_COUNT.get())
                //.rarity(ModConfiguration.COMMON.COFFEE_TREE_JUNGLE_RARITY.get())
    }
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
                .tries(250)
                .whitelist(ImmutableSet.of(States.GRASS_BLOCK.getBlock()))
                .func_227317_b_()
                .xSpread(15)
                .zSpread(15)
                .build();
    }
    public static class States {
        protected static final BlockState COFFEE_TREE_TRUNK = BlocksRegistry.COFFEE_TREE_TRUNK_BLOCK.get().defaultBlockState();
        protected static final BlockState COFFEE_TREE_LEAVES = BlocksRegistry.COFFEE_TREE_LEAVES_BLOCK.get().defaultBlockState();
        protected static final BlockState GRASS_BLOCK = Blocks.GRASS_BLOCK.defaultBlockState();
    }
    private static PlacedFeature register(String key, PlacedFeature configuredFeature) {
        return Registry.register(BuiltinRegistries.PLACED_FEATURE, key, configuredFeature);
    }
}
