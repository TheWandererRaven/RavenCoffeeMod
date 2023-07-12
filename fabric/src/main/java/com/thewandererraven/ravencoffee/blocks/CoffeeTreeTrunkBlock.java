package com.thewandererraven.ravencoffee.blocks;

import com.thewandererraven.ravencoffee.Constants;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class CoffeeTreeTrunkBlock extends CoffeeTreeBlock {
    private static final Block LEAVES_BLOCK = RavenCoffeeBlocks.COFFEE_TREE_LEAVES_BLOCK;
    public static final BooleanProperty HAS_LEAVES = BooleanProperty.of("has_leaves");
    private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{
            Block.createCuboidShape(
                    6.0D,//
                    0.0D,// VOLUME BOTTOM
                    6.0D,//
                    10.0D,// TOP
                    5.0D,// VOLUME TOP
                    10.0D// RIGHT
            ),
            Block.createCuboidShape(
                    4.0D,
                    0.0D,
                    4.0D,
                    12.0D,
                    8.0D,
                    10.0D
            ),
            Block.createCuboidShape(
                    3.0D,
                    0.0D,
                    3.0D,
                    14.0D,
                    15.0D,
                    14.0D
            ),
            Block.createCuboidShape(
                    1.0D,
                    0.0D,// volume bottom
                    1.0D,
                    15.0D,// top
                    16.0D,// volume top
                    15.0D// right
            )
    };

    public CoffeeTreeTrunkBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(HAS_LEAVES, false));
    }

    public Block getLeavesBlock() {
        return LEAVES_BLOCK;
    }

    public static VoxelShape[] getShapeByAge() {
        return SHAPE_BY_AGE;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, net.minecraft.util.math.BlockPos pos, ShapeContext context) {
        return SHAPE_BY_AGE[state.get(this.getAgeProperty())];
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(HAS_LEAVES);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        return super.getPlacementState(context).with(HAS_LEAVES, false);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        super.randomTick(state, world, pos, random);
        BlockPos abovePos = pos.up();
        BlockState newState = world.getBlockState(pos);
        if(newState.get(AGE) >= this.getMaxAge() && world.isAir(abovePos)) {
            growLeaves(world, pos, newState, abovePos);
        }
    }

    @Override
    public boolean hasRandomTicks(BlockState state) {
        return !super.isMature(state) || !state.get(HAS_LEAVES);
    }

    public void growLeaves(ServerWorld world, BlockPos trunkPos, BlockState trunkState, BlockPos leavesPos) {
        this.growLeaves(world, trunkPos, trunkState, leavesPos, 0);
    }

    public void growLeaves(ServerWorld world, BlockPos trunkPos, BlockState trunkState, BlockPos leavesPos, int age) {
        world.setBlockState(trunkPos, trunkState.with(HAS_LEAVES, true));
        world.setBlockState(leavesPos, this.getLeavesBlock().getDefaultState().with(CoffeeTreeTrunkBlock.AGE, age));
    }

    // ##################################### BONEMEAL #####################################


    @Override
    public void applyGrowth(World world, BlockPos pos, BlockState state) {
        super.applyGrowth(world, pos, state);
        BlockState trunkState = world.getBlockState(pos);
        if(isMature(trunkState) && world instanceof ServerWorld serverWorld) {
            growLeaves(serverWorld, pos, trunkState, pos.up());
        }
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        if(this.isMature(state)) {
            BlockState upState = world.getBlockState(pos.up());
            if(upState.isOf(this.getLeavesBlock())) {
                CoffeeTreeLeavesBlock upBlock = ((CoffeeTreeLeavesBlock)upState.getBlock());
                if(upBlock.isFertilizable(world, pos.up(), upState, world.isClient)) {
                    upBlock.applyGrowth(world, pos.up(), upState);
                }
            } else {
                growLeaves(world, pos, state, pos.up(), getGrowthAmount(world) - 1);
            }
        } else {
            super.grow(world, random, pos, state);
        }
    }

    @Override
    public boolean isFertilizable(BlockView world, BlockPos pos, BlockState state, boolean isClient) {
        if(world.getBlockState(pos.up()).isOf(this.getLeavesBlock())) {
            BlockState upBlock = world.getBlockState(pos.up());
            return !this.isMature(state) || !state.get(HAS_LEAVES) || !((CoffeeTreeLeavesBlock) upBlock.getBlock()).isMature(upBlock);
        } else
            return !this.isMature(state) || !state.get(HAS_LEAVES);
    }

}
