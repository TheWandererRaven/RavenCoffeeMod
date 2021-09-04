package com.TheWandererRaven.ravencoffee.gen.features;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.math.shapes.BitSetVoxelShapePart;
import net.minecraft.util.math.shapes.VoxelShapePart;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldWriter;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.IWorldGenerationBaseReader;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.foliageplacer.FoliagePlacer;

import java.util.*;

public class DefaultCoffeeTreesFeature<U> extends CoffeeTreeFeature<DualBlockPileFeatureConfig> {
    public DefaultCoffeeTreesFeature(Codec<DualBlockPileFeatureConfig> p_i231945_1_) {
        super(p_i231945_1_);
    }

    public boolean isValidPosition(IWorld world, BlockPos pos, DualBlockPileFeatureConfig config) {
        return !config.blacklist.contains(world.getBlockState(pos));
    }

    public int getFlowerCount(DualBlockPileFeatureConfig config) {
        return config.tryCount;
    }

    public BlockPos getNearbyPos(Random rand, BlockPos pos, DualBlockPileFeatureConfig config) {
        return pos.add(rand.nextInt(config.xSpread) - rand.nextInt(config.xSpread), rand.nextInt(config.ySpread) - rand.nextInt(config.ySpread), rand.nextInt(config.zSpread) - rand.nextInt(config.zSpread));
    }

    public BlockState getTrunkToPlace(Random rand, BlockPos pos, DualBlockPileFeatureConfig config) {
        return config.trunkStateProvider.getBlockState(rand, pos);
    }
    public BlockState getLeavesToPlace(Random rand, BlockPos pos, DualBlockPileFeatureConfig config) {
        return config.leavesStateProvider.getBlockState(rand, pos);
    }
}
