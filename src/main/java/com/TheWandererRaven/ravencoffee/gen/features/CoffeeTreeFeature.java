package com.TheWandererRaven.ravencoffee.gen.features;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;

import java.util.Random;

public abstract class CoffeeTreeFeature<U extends IFeatureConfig> extends Feature<U> {
    public CoffeeTreeFeature(Codec<U> p_i231922_1_) {
        super(p_i231922_1_);
    }

    public boolean generate(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, U config) {
        BlockState blockstate_trunk = this.getTrunkToPlace(rand, pos, config);
        int i = 0;

        for(int j = 0; j < this.getFlowerCount(config); ++j) {
            BlockPos blockpos = this.getNearbyPos(rand, pos, config);
            if (reader.isAirBlock(blockpos) &&
                    reader.isAirBlock(blockpos.up()) &&
                    blockpos.up().getY() < 255 &&
                    blockstate_trunk.isValidPosition(reader, blockpos) && //blockstate_leaves.isValidPosition(reader, blockpos.up()) &&
                    this.isValidPosition(reader, blockpos, config)) {
                reader.setBlockState(blockpos, blockstate_trunk, 2);
                reader.setBlockState(blockpos.up(), this.getLeavesToPlace(rand, pos, config), 2);
                ++i;
            }
        }

        return i > 0;
    }

    public abstract boolean isValidPosition(IWorld world, BlockPos pos, U config);

    public abstract int getFlowerCount(U config);

    public abstract BlockPos getNearbyPos(Random rand, BlockPos pos, U config);

    public abstract BlockState getTrunkToPlace(Random rand, BlockPos pos, U config);
    public abstract BlockState getLeavesToPlace(Random rand, BlockPos pos, U config);
}
