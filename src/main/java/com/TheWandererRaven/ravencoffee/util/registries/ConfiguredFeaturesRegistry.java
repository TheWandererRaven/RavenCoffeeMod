package com.TheWandererRaven.ravencoffee.util.registries;

import com.TheWandererRaven.ravencoffee.gen.featureConfigs.DualBlockPileFeatureConfig;
import com.TheWandererRaven.ravencoffee.util.configuration.ModConfiguration;
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
    public static final ConfiguredFeature<?, ?> PATCH_COFFEE_TREE_SAVANNAH = register(
            "patch_coffee_tree_savannah",
            FeaturesRegistry.COFFEE_TREE.get()
                    .configured(FeatureConfigs.COFFEE_TREE_PATCH_DEFAULT_CONFIG)
                    .decorated(Features.Decorators.HEIGHTMAP_DOUBLE_SQUARE)
                    .count(ModConfiguration.COMMON.COFFEE_TREE_SAVANNAH_COUNT.get())
                    .rarity(ModConfiguration.COMMON.COFFEE_TREE_SAVANNAH_RARITY.get())
    );
    public static final ConfiguredFeature<?, ?> PATCH_COFFEE_TREE_JUNGLE = register(
            "patch_coffee_tree_jungle",
            FeaturesRegistry.COFFEE_TREE.get()
                    .configured(FeatureConfigs.COFFEE_TREE_PATCH_JUNGLE_CONFIG)
                    .decorated(Features.Decorators.HEIGHTMAP_DOUBLE_SQUARE)
                    .count(ModConfiguration.COMMON.COFFEE_TREE_JUNGLE_COUNT.get())
                    .rarity(ModConfiguration.COMMON.COFFEE_TREE_JUNGLE_RARITY.get())
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
        public static final DualBlockPileFeatureConfig COFFEE_TREE_PATCH_JUNGLE_CONFIG = (
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
