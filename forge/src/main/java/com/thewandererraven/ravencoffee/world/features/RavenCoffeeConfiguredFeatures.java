package com.thewandererraven.ravencoffee.world.features;

import com.google.common.collect.ImmutableSet;
import com.thewandererraven.ravencoffee.Constants;
import com.thewandererraven.ravencoffee.blocks.CoffeeTreeTrunkBlock;
import com.thewandererraven.ravencoffee.world.features.configs.DualBlockPileFeatureConfig;
import com.thewandererraven.ravencoffee.blocks.RavenCoffeeBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;

public class RavenCoffeeConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> COFFEE_TREE_PATCH_DEFAULT_KEY = registerKey("coffee_tree_patch_default");
    public static final ResourceKey<ConfiguredFeature<?, ?>> COFFEE_TREE_PATCH_JUNGLE_KEY = registerKey("coffee_tree_patch_jungle");

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        register(context, COFFEE_TREE_PATCH_DEFAULT_KEY,
                RavenCoffeeFeatures.COFFEE_TREE.get(),
                FeatureConfigs.COFFEE_TREE_PATCH_DEFAULT_CONFIG
        );
        register(context, COFFEE_TREE_PATCH_JUNGLE_KEY,
                RavenCoffeeFeatures.COFFEE_TREE.get(),
                FeatureConfigs.COFFEE_TREE_PATCH_JUNGLE_CONFIG
        );
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(Constants.MOD_ID, name));
    }

    public static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC config) {
        context.register(key, new ConfiguredFeature(feature, config));
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
                .tries(64)
                .whitelist(ImmutableSet.of(States.GRASS_BLOCK.getBlock()))
                .func_227317_b_()
                .xSpread(7)
                .zSpread(3)
                .build();
    }
    public static class States {
        protected static final BlockState COFFEE_TREE_TRUNK = RavenCoffeeBlocks.COFFEE_TREE_TRUNK_BLOCK.get().defaultBlockState().setValue(CoffeeTreeTrunkBlock.HAS_LEAVES, true);
        protected static final BlockState COFFEE_TREE_LEAVES = RavenCoffeeBlocks.COFFEE_TREE_LEAVES_BLOCK.get().defaultBlockState();
        protected static final BlockState GRASS_BLOCK = net.minecraft.world.level.block.Blocks.GRASS_BLOCK.defaultBlockState();
    }
}
