package com.TheWandererRaven.ravencoffee.util.registries;

import com.TheWandererRaven.ravencoffee.gen.featureConfigs.DualBlockPileFeatureConfig;
import com.google.common.collect.ImmutableSet;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.Features;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.blockplacers.SimpleBlockPlacer;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.level.levelgen.placement.ConfiguredDecorator;

public class ConfiguredFeaturesRegistry {
    public static final ConfiguredFeature<?, ?> PATCH_COFFEE_TREE_DEFAULT = register(
            "patch_coffee_tree",
            FeaturesRegistry.COFFEE_TREE.get()
                    .configured(FeatureConfigs.COFFEE_TREE_PATCH_DEFAULT_CONFIG)
                    .decorated(Features.Decorators.HEIGHTMAP_DOUBLE_SQUARE)
                    .count(5)
    );
    public static final ConfiguredFeature<?, ?> PATCH_COFFEE_TREE_SPARSE = register(
            "patch_coffee_tree_sparse", PATCH_COFFEE_TREE_DEFAULT.rarity(100)
    );
    public static final ConfiguredFeature<?, ?> PATCH_COFFEE_TREE_TIGHT = register(
            "patch_coffee_tree_tight",
            FeaturesRegistry.COFFEE_TREE.get()
                    .configured(FeatureConfigs.COFFEE_TREE_PATCH_TIGHT_CONFIG)
                    .decorated(Features.Decorators.HEIGHTMAP_DOUBLE_SQUARE)
                    .count(10)
                    .rarity(20)
    );
    public static class FeatureConfigs {
        public static final DualBlockPileFeatureConfig COFFEE_TREE_PATCH_DEFAULT_CONFIG = (
                new DualBlockPileFeatureConfig.Builder(
                        new SimpleStateProvider(States.COFFEE_TREE_TRUNK),
                        new SimpleStateProvider(States.COFFEE_TREE_LEAVES),
                        SimpleBlockPlacer.INSTANCE
                ))
                .tries(25)
                .whitelist(ImmutableSet.of(States.GRASS_BLOCK.getBlock()))
                .func_227317_b_()
                .xSpread(10)
                .zSpread(10)
                .build();
        public static final DualBlockPileFeatureConfig COFFEE_TREE_PATCH_TIGHT_CONFIG = (
                new DualBlockPileFeatureConfig.Builder(
                        new SimpleStateProvider(States.COFFEE_TREE_TRUNK),
                        new SimpleStateProvider(States.COFFEE_TREE_LEAVES),
                        SimpleBlockPlacer.INSTANCE
                ))
                .tries(250)
                .whitelist(ImmutableSet.of(States.GRASS_BLOCK.getBlock()))
                .func_227317_b_()
                .xSpread(15)
                .zSpread(15)
                .build();
    }
    public static class Placements {
        public static final ConfiguredDecorator<?> COFFEE_TREE_PLACEMENT = Features.Decorators.ADD_32;
    }
    public static class States {
        protected static final BlockState COFFEE_TREE_TRUNK = BlocksRegistry.COFFEE_TREE_TRUNK_BLOCK.get().defaultBlockState();
        protected static final BlockState COFFEE_TREE_LEAVES = BlocksRegistry.COFFEE_TREE_LEAVES_BLOCK.get().defaultBlockState();
        protected static final BlockState GRASS_BLOCK = Blocks.GRASS_BLOCK.defaultBlockState();
    }
    private static <FC extends FeatureConfiguration> ConfiguredFeature<FC, ?> register(String key, ConfiguredFeature<FC, ?> configuredFeature) {
        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, key, configuredFeature);
    }
}
