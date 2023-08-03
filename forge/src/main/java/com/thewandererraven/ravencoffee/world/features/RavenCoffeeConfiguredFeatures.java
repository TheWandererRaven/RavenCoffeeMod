package com.thewandererraven.ravencoffee.world.features;

import com.google.common.collect.ImmutableSet;
import com.thewandererraven.ravencoffee.Constants;
import com.thewandererraven.ravencoffee.world.features.configs.DualBlockPileFeatureConfig;
import com.thewandererraven.ravencoffee.blocks.RavenCoffeeBlocks;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraftforge.registries.DeferredRegister;

public class RavenCoffeeConfiguredFeatures {
    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES = DeferredRegister.create(
            Registries.CONFIGURED_FEATURE.registry(),
            Constants.MOD_ID
    );

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        register(context, COFFEE_TREE_KEY, RavenCoffeeFeatures.COFFEE_TREE.get(), FeatureConfigs.COFFEE_TREE_PATCH_DEFAULT_CONFIG);
    }

    public static final ResourceKey<ConfiguredFeature<?, ?>> COFFEE_TREE_KEY = registerKey("coffee_tree_configured_feature");
/*
    public static final RegistryObject<ConfiguredFeature<?, ?>> COFFEE_TREE = CONFIGURED_FEATURES.register(
            "coffee_tree_configured_feature",
            () -> new ConfiguredFeature<>(
                    RavenCoffeeFeatures.COFFEE_TREE.get(),
                    FeatureConfigs.COFFEE_TREE_PATCH_DEFAULT_CONFIG)
    );
*/
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

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(Constants.MOD_ID, name));
    }

    public static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }

    public static class States {
        protected static final BlockState COFFEE_TREE_TRUNK = RavenCoffeeBlocks.COFFEE_TREE_TRUNK_BLOCK.get().defaultBlockState();
        protected static final BlockState COFFEE_TREE_LEAVES = RavenCoffeeBlocks.COFFEE_TREE_LEAVES_BLOCK.get().defaultBlockState();
        protected static final BlockState GRASS_BLOCK = net.minecraft.world.level.block.Blocks.GRASS_BLOCK.defaultBlockState();
    }
}
