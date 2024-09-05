package com.thewandererraven.ravencoffee.world.features;

import com.thewandererraven.ravencoffee.blocks.CoffeeTreeTrunkBlock;
import com.thewandererraven.ravencoffee.world.features.configs.DualBlockPileFeatureConfig;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;

public class DefaultCoffeeTreesFeature<U> extends CoffeeTreeFeature<DualBlockPileFeatureConfig> {
    public DefaultCoffeeTreesFeature(Codec<DualBlockPileFeatureConfig> p_i231922_1_) {
        super(p_i231922_1_);
    }

    boolean isSurroundingBlockAcceptable(LevelAccessor world, BlockPos pos) {
        return world.isEmptyBlock(pos) || (!world.getBlockState(pos).isSolid() && !world.getBlockState(pos).is(BlockTags.LEAVES));
    }

    public boolean isValidPosition(LevelAccessor world, BlockPos pos, DualBlockPileFeatureConfig config) {
        boolean isTrunkValid = (
                isSurroundingBlockAcceptable(world, pos.north()) && isSurroundingBlockAcceptable(world, pos.south())
                        && isSurroundingBlockAcceptable(world, pos.east()) && isSurroundingBlockAcceptable(world, pos.west())
        );
        boolean isLeavesValid = (
                isSurroundingBlockAcceptable(world, pos.above().north()) && isSurroundingBlockAcceptable(world, pos.above().south()) &&
                        isSurroundingBlockAcceptable(world, pos.above().east()) && isSurroundingBlockAcceptable(world, pos.above().west())
        );
        return !config.blacklist.contains(world.getBlockState(pos)) && isTrunkValid && isLeavesValid;
    }

    public int getFlowerCount(DualBlockPileFeatureConfig config) {
        return config.tryCount;
    }

    public BlockPos getNearbyPos(RandomSource rand, BlockPos pos, DualBlockPileFeatureConfig config) {
        return pos.offset(rand.nextInt(config.xSpread) - rand.nextInt(config.xSpread), rand.nextInt(config.ySpread) - rand.nextInt(config.ySpread), rand.nextInt(config.zSpread) - rand.nextInt(config.zSpread));
    }

    public BlockState getTrunkToPlace(RandomSource rand, BlockPos pos, DualBlockPileFeatureConfig config) {
        return config.trunkStateProvider.getState(rand, pos).setValue(CoffeeTreeTrunkBlock.AGE, 3);
    }
    public BlockState getLeavesToPlace(RandomSource rand, BlockPos pos, DualBlockPileFeatureConfig config) {
        return config.leavesStateProvider.getState(rand, pos).setValue(CoffeeTreeTrunkBlock.AGE, rand.nextInt(4));
    }
}
