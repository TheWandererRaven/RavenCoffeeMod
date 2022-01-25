package com.TheWandererRaven.ravencoffee.gen.features;

import com.TheWandererRaven.ravencoffee.blocks.CoffeeTreeTrunkBlock;
import com.TheWandererRaven.ravencoffee.gen.featureConfigs.DualBlockPileFeatureConfig;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

import java.util.*;

public class DefaultCoffeeTreesFeature<U> extends CoffeeTreeFeature<DualBlockPileFeatureConfig> {
    public DefaultCoffeeTreesFeature(Codec<DualBlockPileFeatureConfig> p_i231945_1_) {
        super(p_i231945_1_);
    }

    public boolean isValidPosition(IWorld world, BlockPos pos, DualBlockPileFeatureConfig config) {
        boolean isTrunkValid = (
                (world.isAirBlock(pos.north()) || !world.getBlockState(pos.north()).isSolid()) &&
                        (world.isAirBlock(pos.south()) || !world.getBlockState(pos.south()).isSolid()) &&
                        (world.isAirBlock(pos.east()) || !world.getBlockState(pos.east()).isSolid()) &&
                        (world.isAirBlock(pos.west()) || !world.getBlockState(pos.west()).isSolid())
        );
        boolean isLeavesValid = (
                (world.isAirBlock(pos.up().north()) || !world.getBlockState(pos.up().north()).isSolid()) &&
                        (world.isAirBlock(pos.up().south()) || !world.getBlockState(pos.up().south()).isSolid()) &&
                        (world.isAirBlock(pos.up().east()) || !world.getBlockState(pos.up().east()).isSolid()) &&
                        (world.isAirBlock(pos.up().west()) || !world.getBlockState(pos.up().west()).isSolid())
        );
        return !config.blacklist.contains(world.getBlockState(pos)) && isTrunkValid && isLeavesValid;
    }

    public int getFlowerCount(DualBlockPileFeatureConfig config) {
        return config.tryCount;
    }

    public BlockPos getNearbyPos(Random rand, BlockPos pos, DualBlockPileFeatureConfig config) {
        return pos.add(rand.nextInt(config.xSpread) - rand.nextInt(config.xSpread), rand.nextInt(config.ySpread) - rand.nextInt(config.ySpread), rand.nextInt(config.zSpread) - rand.nextInt(config.zSpread));
    }

    public BlockState getTrunkToPlace(Random rand, BlockPos pos, DualBlockPileFeatureConfig config) {
        return config.trunkStateProvider.getBlockState(rand, pos).with(CoffeeTreeTrunkBlock.AGE, 3);
    }
    public BlockState getLeavesToPlace(Random rand, BlockPos pos, DualBlockPileFeatureConfig config) {
        return config.leavesStateProvider.getBlockState(rand, pos).with(CoffeeTreeTrunkBlock.AGE, rand.nextInt(4));
    }
}
