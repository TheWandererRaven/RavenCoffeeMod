package com.thewandererraven.ravencoffee.blocks;

import com.thewandererraven.ravencoffee.Constants;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

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
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if(!world.isClient) {
            super.onBreak(world, pos, state, player);
            BlockState blockDown = world.getBlockState(pos.down());
            if (blockDown.isOf(RavenCoffeeBlocks.COFFEE_TREE_TRUNK_BLOCK))
                if (blockDown.get(CoffeeTreeTrunkBlock.HAS_LEAVES))
                    world.setBlockState(pos.down(), blockDown.with(CoffeeTreeTrunkBlock.HAS_LEAVES, false));
        }
    }

    /*
    @Override
    protected boolean mayPlaceOn(BlockState p_200014_1_, BlockGetter p_200014_2_, BlockPos p_200014_3_) {
        return p_200014_1_.is(RavenCoffeeBlocks.COFFEE_TREE_TRUNK_BLOCK.get());
    }
*/

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, net.minecraft.util.math.BlockPos pos, ShapeContext context) {
        return SHAPE_BY_AGE[state.get(this.getAgeProperty())];
    }

    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state, boolean isClient) {
        return true;
    }
}
