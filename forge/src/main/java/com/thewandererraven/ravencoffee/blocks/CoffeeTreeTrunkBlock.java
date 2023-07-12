package com.thewandererraven.ravencoffee.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CoffeeTreeTrunkBlock extends CoffeeTreeBlock {
    public static final IntegerProperty AGE;
    private static final VoxelShape[] SHAPE_BY_AGE;
    private static final Block LEAVES_BLOCK;

    public CoffeeTreeTrunkBlock(Properties p_i48421_1_) {
        super(p_i48421_1_);
        this.registerDefaultState(this.getStateDefinition().any().setValue(this.getAgeProperty(), 0));
    }

    @Override
    protected boolean mayPlaceOn(BlockState p_200014_1_, BlockGetter p_200014_2_, BlockPos p_200014_3_) {
        return p_200014_1_.is(net.minecraft.world.level.block.Blocks.GRASS_BLOCK) || p_200014_1_.is(net.minecraft.world.level.block.Blocks.DIRT);
    }

    public Block getLeavesBlock() {
        return LEAVES_BLOCK;
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return true;//!this.isMaxAge(state);
    }

    @Override
    public boolean isAboveBlockAcceptable(Level level,  BlockPos blockPos) {
        return level.isEmptyBlock(blockPos.above());
    }

    @Override
    public void tickGrow(int age, BlockState blockState, ServerLevel server, BlockPos blockPos) {
        super.tickGrow(age, blockState, server, blockPos);
        BlockPos abovePos = blockPos.above();
        //boolean isMaxAge = (age+1) >= this.getMaxAge(), isAboveEmpty = server.isEmptyBlock(abovePos);
        if((age+1) >= this.getMaxAge() && server.isEmptyBlock(abovePos)) {
            server.setBlock(abovePos, this.getLeavesBlock().defaultBlockState(), 2);
        }
    }

    @Override
    public VoxelShape getShape(BlockState p_220053_1_, BlockGetter p_220053_2_, BlockPos p_220053_3_, CollisionContext p_220053_4_) {
        return SHAPE_BY_AGE[p_220053_1_.getValue(this.getAgeProperty())];
    }
        
    static {
        LEAVES_BLOCK = RavenCoffeeBlocks.COFFEE_TREE_LEAVES_BLOCK.get();
        AGE = BlockStateProperties.AGE_3;
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
