package com.TheWandererRaven.ravencoffee.util.registries;

import com.TheWandererRaven.ravencoffee.blocks.CoffeeTreeLeavesBlock;
import com.TheWandererRaven.ravencoffee.blocks.CoffeeTreeTrunkBlock;
import com.TheWandererRaven.ravencoffee.gen.featureConfigs.DualBlockPileFeatureConfig;
import com.google.common.collect.ImmutableSet;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placement.ConfiguredPlacement;
import net.minecraft.world.gen.placement.NoPlacementConfig;
import net.minecraft.world.gen.placement.Placement;

public class ConfiguredFeaturesRegistry {
    public static final ConfiguredFeature<?, ?> PATCH_COFFEE_TREE_DEFAULT = register(
            "patch_coffee_tree",
            FeaturesRegistry.COFFEE_TREE.get()
                    .withConfiguration(Configs.COFFEE_TREE_PATCH_DEFAULT_CONFIG)
                    .withPlacement(Features.Placements.PATCH_PLACEMENT)
                    .func_242731_b(5)
    );
    public static final ConfiguredFeature<?, ?> PATCH_COFFEE_TREE_SPARSE = register(
            "patch_coffee_tree_sparse", PATCH_COFFEE_TREE_DEFAULT.chance(6)
    );
    public static final ConfiguredFeature<?, ?> PATCH_COFFEE_TREE_TIGHT = register(
            "patch_coffee_tree_sparse",
            FeaturesRegistry.COFFEE_TREE.get()
                    .withConfiguration(Configs.COFFEE_TREE_PATCH_TIGHT_CONFIG)
                    .withPlacement(Features.Placements.PATCH_PLACEMENT)
                    .func_242731_b(20)
    );
    public static class Configs {
        public static final DualBlockPileFeatureConfig COFFEE_TREE_PATCH_DEFAULT_CONFIG = (
                new DualBlockPileFeatureConfig.Builder(
                        new SimpleBlockStateProvider(States.COFFEE_TREE_TRUNK),
                        new SimpleBlockStateProvider(States.COFFEE_TREE_LEAVES),
                        SimpleBlockPlacer.PLACER
                ))
                .tries(128)
                .whitelist(ImmutableSet.of(States.GRASS_BLOCK.getBlock()))
                .func_227317_b_()
                .xSpread(10)
                .zSpread(10)
                .build();
        public static final DualBlockPileFeatureConfig COFFEE_TREE_PATCH_TIGHT_CONFIG = (
                new DualBlockPileFeatureConfig.Builder(
                        new SimpleBlockStateProvider(States.COFFEE_TREE_TRUNK),
                        new SimpleBlockStateProvider(States.COFFEE_TREE_LEAVES),
                        SimpleBlockPlacer.PLACER
                ))
                .tries(200)
                .whitelist(ImmutableSet.of(States.GRASS_BLOCK.getBlock()))
                .func_227317_b_()
                .xSpread(6)
                .zSpread(6)
                .build();
    }
    public static class Placements {
        public static final ConfiguredPlacement<?> COFFEE_TREE_PLACEMENT = Placement.SPREAD_32_ABOVE.configure(NoPlacementConfig.INSTANCE);
    }
    public static class States {
        protected static final BlockState COFFEE_TREE_TRUNK = BlocksRegistry.COFFEE_TREE_TRUNK_BLOCK.get().getDefaultState();
        protected static final BlockState COFFEE_TREE_LEAVES = BlocksRegistry.COFFEE_TREE_LEAVES_BLOCK.get().getDefaultState();
        protected static final BlockState GRASS_BLOCK = Blocks.GRASS_BLOCK.getDefaultState();
    }
    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String key, ConfiguredFeature<FC, ?> configuredFeature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, key, configuredFeature);
    }
}
