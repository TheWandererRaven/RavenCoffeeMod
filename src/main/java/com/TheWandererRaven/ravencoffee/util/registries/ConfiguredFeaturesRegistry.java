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
    public static final ConfiguredFeature<?, ?> COFFEE_TREE = register(
            "coffee_tree",
            FeaturesRegistry.COFFEE_TREE.get()
                    .withConfiguration(Configs.COFFEE_TREE_PATCH_CONFIG)
                    .withPlacement(Features.Placements.PATCH_PLACEMENT)
                    .func_242731_b(2)
    );
    public static class Configs {
        public static final DualBlockPileFeatureConfig COFFEE_TREE_PATCH_CONFIG = (
                new DualBlockPileFeatureConfig.Builder(
                        new SimpleBlockStateProvider(States.COFFEE_TREE_TRUNK),
                        new SimpleBlockStateProvider(States.COFFEE_TREE_LEAVES),
                        SimpleBlockPlacer.PLACER
                ))
                .tries(64)
                .whitelist(ImmutableSet.of(States.GRASS_BLOCK.getBlock()))
                .func_227317_b_()
                .build();
    }
    public static class Placements {
        public static final ConfiguredPlacement<?> COFFEE_TREE_PLACEMENT = Placement.SPREAD_32_ABOVE.configure(NoPlacementConfig.INSTANCE);
    }
    public static class States {
        protected static final BlockState COFFEE_TREE_TRUNK = ((CoffeeTreeTrunkBlock)BlocksRegistry.COFFEE_TREE_TRUNK_BLOCK.get()).getBiomeGenState();
        protected static final BlockState COFFEE_TREE_LEAVES = ((CoffeeTreeLeavesBlock)BlocksRegistry.COFFEE_TREE_LEAVES_BLOCK.get()).getBiomeGenState();
        protected static final BlockState GRASS_BLOCK = Blocks.GRASS_BLOCK.getDefaultState();
    }
    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String key, ConfiguredFeature<FC, ?> configuredFeature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, key, configuredFeature);
    }
}
