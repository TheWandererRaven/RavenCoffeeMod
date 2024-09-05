package com.thewandererraven.ravencoffee.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public class CoffeeTreeLeavesBlock extends CoffeeTreeBlock {
    private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{
            Block.createCuboidShape(
                    4.0D,// BOTTOM
                    0.0D,// VOLUME BOTTOM
                    5.0D,// LEFT
                    12.0D,// TOP
                    8.0D,// VOLUME TOP
                    12.0D// RIGHT
            ),
            Block.createCuboidShape(
                    1.0D,
                    0.0D,
                    1.0D,
                    15.0D,
                    15.0D,
                    14.0D
            ),
            Block.createCuboidShape(
                    0.0D,
                    0.0D,
                    0.0D,
                    16.0D,
                    16.0D,
                    16.0D
            ),
            Block.createCuboidShape(
                    0.0D,
                    0.0D,// volume bottom
                    0.0D,
                    16.0D,// top
                    16.0D,// volume top
                    16.0D// right
        )
    };

    public CoffeeTreeLeavesBlock(Settings settings) {
        super(settings);
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        boolean flag = false;
        BlockState belowBlock = world.getBlockState(pos.down());
        if(belowBlock.isOf(RavenCoffeeBlocks.COFFEE_TREE_TRUNK_BLOCK))
            flag = ((CoffeeTreeTrunkBlock)belowBlock.getBlock()).isMature(belowBlock);
        return flag;//super.canPlaceAt(state, world, pos) && flag;
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        super.onBreak(world, pos, state, player);
        if(!world.isClient) {
            BlockState blockDown = world.getBlockState(pos.down());
            if (blockDown.isOf(RavenCoffeeBlocks.COFFEE_TREE_TRUNK_BLOCK))
                if (blockDown.get(CoffeeTreeTrunkBlock.HAS_LEAVES))
                    world.setBlockState(pos.down(), blockDown.with(CoffeeTreeTrunkBlock.HAS_LEAVES, false));
        }
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, net.minecraft.util.math.BlockPos pos, ShapeContext context) {
        return SHAPE_BY_AGE[state.get(this.getAgeProperty())];
    }
}
