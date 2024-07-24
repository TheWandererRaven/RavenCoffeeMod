package com.thewandererraven.ravencoffee.world.feature;

import com.mojang.serialization.Codec;
import com.thewandererraven.ravencoffee.blocks.CoffeeTreeTrunkBlock;
import com.thewandererraven.ravencoffee.world.feature.configs.DualBlockPileFeatureConfig;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;

public class DefaultCoffeeTreesFeature<U> extends CoffeeTreeFeature<DualBlockPileFeatureConfig> {
    public DefaultCoffeeTreesFeature(Codec<DualBlockPileFeatureConfig> p_i231922_1_) {
        super(p_i231922_1_);
    }

    boolean isSurroundingBlockAcceptable(StructureWorldAccess world, BlockPos pos) {
        return world.isAir(pos) || (!world.getBlockState(pos).getMaterial().isSolid() && !world.getBlockState(pos).getMaterial().equals(Material.LEAVES));
    }

    public boolean isValidPosition(StructureWorldAccess world, BlockPos pos, DualBlockPileFeatureConfig config) {
        boolean isTrunkValid = (
                isSurroundingBlockAcceptable(world, pos.north()) && isSurroundingBlockAcceptable(world, pos.south())
                        && isSurroundingBlockAcceptable(world, pos.east()) && isSurroundingBlockAcceptable(world, pos.west())
        );
        boolean isLeavesValid = (
                isSurroundingBlockAcceptable(world, pos.up().north()) && isSurroundingBlockAcceptable(world, pos.up().south()) &&
                        isSurroundingBlockAcceptable(world, pos.up().east()) && isSurroundingBlockAcceptable(world, pos.up().west())
        );
        return !config.blacklist.contains(world.getBlockState(pos)) && isTrunkValid && isLeavesValid;
    }

    public int getFlowerCount(DualBlockPileFeatureConfig config) {
        return config.tryCount;
    }

    public BlockPos getNearbyPos(Random rand, BlockPos pos, DualBlockPileFeatureConfig config) {
        // Used .offset() in forge
        return pos.add(rand.nextInt(config.xSpread) - rand.nextInt(config.xSpread), rand.nextInt(config.ySpread) - rand.nextInt(config.ySpread), rand.nextInt(config.zSpread) - rand.nextInt(config.zSpread));
    }

    public BlockState getTrunkToPlace(Random rand, BlockPos pos, DualBlockPileFeatureConfig config) {
        return config.trunkStateProvider.getBlockState(rand, pos).with(CoffeeTreeTrunkBlock.AGE, 3);
    }
    public BlockState getLeavesToPlace(Random rand, BlockPos pos, DualBlockPileFeatureConfig config) {
        return config.leavesStateProvider.getBlockState(rand, pos).with(CoffeeTreeTrunkBlock.AGE, rand.nextInt(4));
    }
}
