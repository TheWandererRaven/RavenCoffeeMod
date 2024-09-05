package com.thewandererraven.ravencoffee.world.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.util.FeatureContext;

public abstract class CoffeeTreeFeature<U extends FeatureConfig> extends Feature<U> {
    public CoffeeTreeFeature(Codec<U> p_i231922_1_) {
        super(p_i231922_1_);
    }

    @Override
    public boolean generate(FeatureContext<U> context) {
        StructureWorldAccess reader = context.getWorld();
        Random rand = context.getRandom();
        BlockPos pos = context.getOrigin();
        U config = context.getConfig();
        BlockState blockstate_trunk = this.getTrunkToPlace(rand, pos, config);
        int i = 0;

        for(int j = 0; j < this.getFlowerCount(config); ++j) {
            BlockPos blockpos = this.getNearbyPos(rand, pos, config);
            if (reader.isAir(blockpos) &&
                    reader.isAir(blockpos.up()) &&
                    blockpos.up().getY() < 255 &&
                    blockstate_trunk.canPlaceAt(reader, blockpos) && //blockstate_leaves.isValidPosition(reader, blockpos.up()) &&
                    this.isValidPosition(reader, blockpos, config)) {
                reader.setBlockState(blockpos, blockstate_trunk, 2);
                reader.setBlockState(blockpos.up(), this.getLeavesToPlace(rand, pos, config), 2);
                ++i;
            }
        }

        return i > 0;
    }

    public abstract boolean isValidPosition(StructureWorldAccess world, BlockPos pos, U config);

    public abstract int getFlowerCount(U config);

    public abstract BlockPos getNearbyPos(Random rand, BlockPos pos, U config);

    public abstract BlockState getTrunkToPlace(Random rand, BlockPos pos, U config);
    public abstract BlockState getLeavesToPlace(Random rand, BlockPos pos, U config);
}
