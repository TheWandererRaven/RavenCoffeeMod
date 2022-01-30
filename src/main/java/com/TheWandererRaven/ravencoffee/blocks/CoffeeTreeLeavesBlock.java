package com.TheWandererRaven.ravencoffee.blocks;

import com.TheWandererRaven.ravencoffee.util.registries.BlocksRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CoffeeTreeLeavesBlock extends CoffeeTreeBlock {
    public static final IntegerProperty AGE;
    private static final VoxelShape[] SHAPE_BY_AGE;

    public CoffeeTreeLeavesBlock(Properties p_i48421_1_) {
        super(p_i48421_1_);
        this.registerDefaultState(this.getStateDefinition().any().setValue(this.getAgeProperty(), 0));
    }

    @Override
    protected boolean mayPlaceOn(BlockState p_200014_1_, BlockGetter p_200014_2_, BlockPos p_200014_3_) {
        return p_200014_1_.is(BlocksRegistry.COFFEE_TREE_TRUNK_BLOCK.get());
    }

    @Override
    public VoxelShape getShape(BlockState p_220053_1_, BlockGetter p_220053_2_, BlockPos p_220053_3_, CollisionContext p_220053_4_) {
        return SHAPE_BY_AGE[p_220053_1_.getValue(this.getAgeProperty())];
    }

    static {
        AGE = BlockStateProperties.AGE_3;
        SHAPE_BY_AGE = new VoxelShape[]{
                Block.box(
                        4.0D,// BOTTOM
                        0.0D,// VOLUME BOTTOM
                        5.0D,// LEFT
                        12.0D,// TOP
                        8.0D,// VOLUME TOP
                        12.0D// RIGHT
                ), Block.box(
                1.0D,
                0.0D,
                1.0D,
                15.0D,
                15.0D,
                14.0D
        ), Block.box(
                0.0D,
                0.0D,
                0.0D,
                16.0D,
                16.0D,
                16.0D
        ), Block.box(
                0.0D,
                0.0D,// volume bottom
                0.0D,
                16.0D,// top
                16.0D,// volume top
                16.0D// right
        )
        };
    }
    }
