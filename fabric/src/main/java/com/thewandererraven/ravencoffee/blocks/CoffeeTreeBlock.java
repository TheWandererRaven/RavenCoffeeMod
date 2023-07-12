package com.thewandererraven.ravencoffee.blocks;

import com.thewandererraven.ravencoffee.Constants;
import com.thewandererraven.ravencoffee.items.RavenCoffeeItems;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.mob.RavagerEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

import javax.annotation.Nullable;

public abstract class CoffeeTreeBlock extends PlantBlock implements Fertilizable {
    public static final IntProperty AGE = Properties.AGE_3;
    /*
    private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{
            Block.createCuboidShape(
                    0.0D,
                    0.0D,// volume bottom
                    0.0D,
                    16.0D,// top
                    16.0D,// volume top
                    16.0D// right
            ),
            Block.createCuboidShape(
                    0.0D,
                    0.0D,// volume bottom
                    0.0D,
                    16.0D,// top
                    16.0D,// volume top
                    16.0D// right
            ),
            Block.createCuboidShape(
                    0.0D,
                    0.0D,// volume bottom
                    0.0D,
                    16.0D,// top
                    16.0D,// volume top
                    16.0D// right
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

     */

    public CoffeeTreeBlock(Settings properties) {
        super(properties);
        this.setDefaultState(this.getDefaultState().with(this.getAgeProperty(), 0));
    }

    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (entity instanceof RavagerEntity) {
            world.breakBlock(pos, true, entity);
        }

        super.onEntityCollision(state, world, pos, entity);
    }

    protected ItemConvertible getSeedsItem() {
        return RavenCoffeeItems.COFFEE_CHERRIES;
    }

    public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
        return new ItemStack(this.getSeedsItem());
    }

    // ================================================= BLOSCKSTATE =================================================

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        return this.getDefaultState()
                .with(AGE, 0);
    }

    @Override
    protected void appendProperties(StateManager.Builder<net.minecraft.block.Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(AGE);
    }


    // ##################################### BLOCK #####################################

    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return floor.isOf(Blocks.GRASS_BLOCK) || floor.isOf(Blocks.DIRT);
    }


    // ##################################### AGE #####################################
    public IntProperty getAgeProperty() {
        return AGE;
    }

    public int getMaxAge() {
        return 3;
    }

    protected int getAge(BlockState state) {
        return (Integer)state.get(this.getAgeProperty());
    }

    public BlockState withAge(int age) {
        return (BlockState)this.getDefaultState().with(this.getAgeProperty(), age);
    }

    public boolean isMature(BlockState state) {
        return (Integer)state.get(this.getAgeProperty()) >= this.getMaxAge();
    }

    // ##################################### TICKS #####################################

    @Override
    public boolean hasRandomTicks(BlockState state) {
        return !this.isMature(state);
    }

    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (world.getBaseLightLevel(pos, 0) >= 9) {
            int i = this.getAge(state);
            if (i < this.getMaxAge()) {
                if (random.nextInt((int)(25.0F) + 1) == 0) {
                    world.setBlockState(pos, this.withAge(i + 1), 2);
                }
            }
        }
    }

    // ##################################### BONEMEAL #####################################

    public void applyGrowth(World world, BlockPos pos, BlockState state) {
        int i = this.getAge(state) + this.getGrowthAmount(world);
        int j = this.getMaxAge();
        if (i > j) {
            i = j;
        }
        world.setBlockState(pos, this.withAge(i), 2);
    }

    protected int getGrowthAmount(World world) {
        return MathHelper.nextInt(world.random, 1, 3);
    }

    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return (world.getBaseLightLevel(pos, 0) >= 8 || world.isSkyVisible(pos)) && super.canPlaceAt(state, world, pos);
    }

    public boolean isFertilizable(BlockView world, BlockPos pos, BlockState state, boolean isClient) {
        // TODO: Update to enable fertilizable if leaves block can be too
        return !this.isMature(state);
    }

    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        this.applyGrowth(world, pos, state);
    }

}
