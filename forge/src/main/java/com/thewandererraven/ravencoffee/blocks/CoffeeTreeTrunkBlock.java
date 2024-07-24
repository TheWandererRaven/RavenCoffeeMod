package com.thewandererraven.ravencoffee.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CoffeeTreeTrunkBlock extends CoffeeTreeBlock {
    public static final IntegerProperty AGE;
    public static final BooleanProperty HAS_LEAVES;
    private static final VoxelShape[] SHAPE_BY_AGE;
    private static final Block LEAVES_BLOCK;

    public CoffeeTreeTrunkBlock(Properties p_i48421_1_) {
        super(p_i48421_1_);
        this.registerDefaultState(this.getStateDefinition().any()
                .setValue(this.getAgeProperty(), 0)
                .setValue(HAS_LEAVES, false)
        );
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(HAS_LEAVES);
    }

    public Block getLeavesBlock() {
        return LEAVES_BLOCK;
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return !super.isMaxAge(state) || !state.getValue(HAS_LEAVES);
    }

    @Override
    public boolean isAboveBlockAcceptable(Level level,  BlockPos blockPos) {
        return level.isEmptyBlock(blockPos.above());
    }

    public void growLeaves(ServerLevel level, BlockPos trunkPos, BlockState trunkState, BlockPos leavesPos) {
        this.growLeaves(level, trunkPos, trunkState, leavesPos, 0);
    }

    public void growLeaves(ServerLevel level, BlockPos trunkPos, BlockState trunkState, BlockPos leavesPos, int age) {
        level.setBlock(trunkPos, trunkState.setValue(HAS_LEAVES, true), 2);
        level.setBlock(leavesPos, this.getLeavesBlock().defaultBlockState().setValue(CoffeeTreeTrunkBlock.AGE, age), 2);
    }

    @Override
    public void tickGrow(int age, BlockState blockState, ServerLevel level, BlockPos blockPos) {
        super.tickGrow(age, blockState, level, blockPos);
        BlockPos abovePos = blockPos.above();
        BlockState newState = level.getBlockState(blockPos);
        if((age+1) >= this.getMaxAge() && level.isEmptyBlock(abovePos)) {
            growLeaves(level, blockPos, newState, abovePos);
        } else if (level.getBlockState(abovePos).is(getLeavesBlock()))
            level.destroyBlock(abovePos, true);
    }

    @Override
    public void growCrops(Level level, BlockPos blockPos, BlockState blockState) {
        super.growCrops(level, blockPos, blockState);
        BlockState newState = level.getBlockState(blockPos);
        if(isMaxAge(newState) && level instanceof ServerLevel serverLevel) {
            growLeaves(serverLevel, blockPos, newState, blockPos.above(), 0);
        }
    }

    @Override
    public VoxelShape getShape(BlockState p_220053_1_, BlockGetter p_220053_2_, BlockPos p_220053_3_, CollisionContext p_220053_4_) {
        return SHAPE_BY_AGE[p_220053_1_.getValue(this.getAgeProperty())];
    }

    // ##################################### BONEMEAL #####################################


    @Override
    public void applyGrowth(ServerLevel world, BlockPos pos, BlockState state) {
        super.applyGrowth(world, pos, state);
        BlockState trunkState = world.getBlockState(pos);
        if(isMaxAge(trunkState)) {
            growLeaves(world, pos, trunkState, pos.above());
        }
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource rand, BlockPos blockPos, BlockState blockState) {
        if(this.isMaxAge(blockState)) {
            BlockState aboveState = level.getBlockState(blockPos.above());
            if(aboveState.is(this.getLeavesBlock())) {
                CoffeeTreeLeavesBlock upBlock = ((CoffeeTreeLeavesBlock)aboveState.getBlock());
                if(upBlock.isBonemealSuccess(level, rand, blockPos.above(), aboveState)) {
                    upBlock.applyGrowth(level, blockPos.above(), aboveState);
                }
            } else {
                growLeaves(level, blockPos, blockState, blockPos.above(), getBonemealAgeIncrease(level) - 1);
            }
        } else
            this.growCrops(level, blockPos, blockState);
        BlockState latestState = level.getBlockState(blockPos);
    }

    @Override
    public boolean isValidBonemealTarget(BlockGetter blockGetter, BlockPos blockPos, BlockState blockState, boolean isClient) {
        if(blockGetter.getBlockState(blockPos.above()).is(this.getLeavesBlock())) {
            BlockState upBlock = blockGetter.getBlockState(blockPos.above());
            return !this.isMaxAge(blockState) || !blockState.getValue(HAS_LEAVES) || !((CoffeeTreeLeavesBlock) upBlock.getBlock()).isMaxAge(upBlock);
        } else
            return !this.isMaxAge(blockState) || !blockState.getValue(HAS_LEAVES);
    }
        
    static {
        LEAVES_BLOCK = RavenCoffeeBlocks.COFFEE_TREE_LEAVES_BLOCK.get();
        AGE = BlockStateProperties.AGE_3;
        HAS_LEAVES = BooleanProperty.create("has_leaves");
        SHAPE_BY_AGE = new VoxelShape[]{
                Block.box(
                        6.0D,//
                        0.0D,// VOLUME BOTTOM
                        6.0D,//
                        10.0D,// TOP
                        5.0D,// VOLUME TOP
                        10.0D// RIGHT
                ), Block.box(
                4.0D,
                0.0D,
                4.0D,
                12.0D,
                8.0D,
                10.0D
        ), Block.box(
                3.0D,
                0.0D,
                3.0D,
                14.0D,
                15.0D,
                14.0D
        ), Block.box(
                1.0D,
                0.0D,// volume bottom
                1.0D,
                15.0D,// top
                16.0D,// volume top
                15.0D// right
        )
        };
    }
    }
