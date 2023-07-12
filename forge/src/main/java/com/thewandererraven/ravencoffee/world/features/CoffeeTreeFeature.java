package com.thewandererraven.ravencoffee.world.features;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

import java.util.Random;

public abstract class CoffeeTreeFeature<U extends FeatureConfiguration> extends Feature<U> {
    public CoffeeTreeFeature(Codec<U> p_i231922_1_) {
        super(p_i231922_1_);
    }

    @Override
    public boolean place(FeaturePlaceContext<U> context) {
        WorldGenLevel reader = context.level();
        //ChunkGenerator generator = context.chunkGenerator();
        RandomSource rand = context.random();
        BlockPos pos = context.origin();
        U config = context.config();
        BlockState blockstate_trunk = this.getTrunkToPlace(rand, pos, config);
        int i = 0;

        for(int j = 0; j < this.getFlowerCount(config); ++j) {
            BlockPos blockpos = this.getNearbyPos(rand, pos, config);
            if (reader.isEmptyBlock(blockpos) &&
                    reader.isEmptyBlock(blockpos.above()) &&
                    blockpos.above().getY() < 255 &&
                    blockstate_trunk.canSurvive(reader, blockpos) && //blockstate_leaves.isValidPosition(reader, blockpos.up()) &&
                    this.isValidPosition(reader, blockpos, config)) {
                reader.setBlock(blockpos, blockstate_trunk, 2);
                reader.setBlock(blockpos.above(), this.getLeavesToPlace(rand, pos, config), 2);
                ++i;
            }
        }

        return i > 0;
    }

    public abstract boolean isValidPosition(LevelAccessor world, BlockPos pos, U config);

    public abstract int getFlowerCount(U config);

    public abstract BlockPos getNearbyPos(RandomSource rand, BlockPos pos, U config);

    public abstract BlockState getTrunkToPlace(RandomSource rand, BlockPos pos, U config);
    public abstract BlockState getLeavesToPlace(RandomSource rand, BlockPos pos, U config);
}
