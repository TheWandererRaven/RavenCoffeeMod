package com.TheWandererRaven.ravencoffee.gen.features;

import com.TheWandererRaven.ravencoffee.util.registries.BlocksRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.blockplacer.ColumnBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.BushFoliagePlacer;
import net.minecraft.world.gen.placement.ConfiguredPlacement;
import net.minecraft.world.gen.placement.NoPlacementConfig;
import net.minecraft.world.gen.placement.NoiseDependant;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;

public class CoffeeTreeConfiguredFeatures {
    public static final ConfiguredFeature<?, ?> COFFEE_TREE = register(
            "coffee_tree",
            Feature.TREE
                    .withConfiguration((
                            new BaseTreeFeatureConfig.Builder(
                                    new SimpleBlockStateProvider(States.COFFEE_TREE_TRUNK),
                                    new SimpleBlockStateProvider(States.COFFEE_TREE_LEAVES),
                                    new BushFoliagePlacer(FeatureSpread.func_242252_a(1), FeatureSpread.func_242252_a(1), 1),
                                    new StraightTrunkPlacer(1, 0, 0),
                                    new TwoLayerFeature(0, 0, 0)
                            )
                    ).func_236702_a_(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES).build())
    );
    /*
    public static final ConfiguredFeature<?, ?> PATCH_TALL_GRASS_2 = register(
            "patch_tall_grass_2",
            Feature.RANDOM_PATCH
                    .withConfiguration(Features.Configs.TALL_GRASS_CONFIG)
                    .withPlacement(Features.Placements.VEGETATION_PLACEMENT)
                    .withPlacement(Features.Placements.FLOWER_TALL_GRASS_PLACEMENT)
                    .square()
                    .withPlacement(Placement.COUNT_NOISE.configure(new NoiseDependant(-0.8D, 0, 7)))
    );*/
    /*
    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> OAK = register("oak",
            Feature.TREE
                    .withConfiguration((
                            new BaseTreeFeatureConfig.Builder(
                                    new SimpleBlockStateProvider(Features.States.OAK_LOG),
                                    new SimpleBlockStateProvider(Features.States.OAK_LEAVES),
                                    new BlobFoliagePlacer(FeatureSpread.func_242252_a(2), FeatureSpread.func_242252_a(0),3),
                                    new StraightTrunkPlacer(4, 2, 0),
                                    new TwoLayerFeature(1, 0, 1)
                            )
                    ).setIgnoreVines().build())
    );
    public static final ConfiguredFeature<?, ?> JUNGLE_BUSH = register("jungle_bush",
            Feature.TREE
                    .withConfiguration((
                            new BaseTreeFeatureConfig.Builder(
                                    new SimpleBlockStateProvider(Features.States.JUNGLE_LOG),
                                    new SimpleBlockStateProvider(Features.States.OAK_LEAVES),
                                    new BushFoliagePlacer(FeatureSpread.func_242252_a(2), FeatureSpread.func_242252_a(1), 2),
                                    new StraightTrunkPlacer(1, 0, 0),
                                    new TwoLayerFeature(0, 0, 0)
                            )
                    ).func_236702_a_(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES).build())
    );*/
    public static class Configs {
        /*
        public static final BlockClusterFeatureConfig COFFEE_TREE_CONFIG = (
                new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(CoffeeTreeConfiguredFeatures.States.SUGAR_CANE),
                        new ColumnBlockPlacer(2, 2)))
                .tries(20)
                .xSpread(4)
                .ySpread(0)
                .zSpread(4)
                .func_227317_b_()
                .requiresWater()
                .build();
         */
    }
    public static class Placements {
        public static final ConfiguredPlacement<?> COFFEE_TREE_PLACEMENT = Placement.SPREAD_32_ABOVE.configure(NoPlacementConfig.INSTANCE);
    }
    public static class States {
        protected static final BlockState COFFEE_TREE_TRUNK = BlocksRegistry.COFFEE_TREE_TRUNK_BLOCK.get().getDefaultState();
        protected static final BlockState COFFEE_TREE_LEAVES = BlocksRegistry.COFFEE_TREE_TRUNK_BLOCK.get().getDefaultState();
    }
    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String key, ConfiguredFeature<FC, ?> configuredFeature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, key, configuredFeature);
    }
}
