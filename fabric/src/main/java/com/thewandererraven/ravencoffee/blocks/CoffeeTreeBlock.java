package com.thewandererraven.ravencoffee.blocks;

import com.thewandererraven.ravencoffee.items.RavenCoffeeItems;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.RavagerEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

import javax.annotation.Nullable;
import java.util.Iterator;

public abstract class CoffeeTreeBlock extends CropBlock {
    public static final IntProperty AGE = Properties.AGE_3;

    public CoffeeTreeBlock(Settings properties) {
        super(properties);
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
        builder.add(AGE);
    }


    // ##################################### BLOCK #####################################

    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return floor.isIn(BlockTags.DIRT);
    }


    // ##################################### AGE #####################################
    @Override
    public IntProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getMaxAge() {
        return 3;
    }

    @Override
    public int getAge(BlockState state) {
        return (Integer)state.get(this.getAgeProperty());
    }

    @Override
    public BlockState withAge(int age) {
        return (BlockState)this.getDefaultState().with(this.getAgeProperty(), age);
    }

    // ##################################### TICKS #####################################

    @Override
    public boolean hasRandomTicks(BlockState state) {
        return !this.isMature(state);
    }

    @Override
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

    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos blockPos) {
        //return (world.getBaseLightLevel(pos, 0) >= 8 || world.isSkyVisible(pos)) && super.canPlaceAt(state, world, pos);
        BlockPos blockpos = blockPos.down();

        Iterator<Direction> var4 = Direction.Type.HORIZONTAL.iterator();
        Direction direction;
        BlockState blockstate;

        do {
            if (!var4.hasNext()) {
                boolean retVal = //(blockGetter.getLightEmission(blockPos) >= 8 || blockGetter.canSeeSky(blockPos)) &&
                        this.canPlantOnTop(world.getBlockState(blockpos), world, blockpos);
                return retVal;
            }

            direction = var4.next();
            blockstate = world.getBlockState(blockPos.offset(direction));
        } while((!blockstate.isSolid() || blockstate.isIn(BlockTags.LEAVES) || blockstate.isIn(BlockTags.FENCES) || blockstate.isIn(BlockTags.FENCE_GATES) || blockstate.isOf(Blocks.JIGSAW)) && !world.getFluidState(blockPos.offset(direction)).isIn(FluidTags.LAVA));
        return false;
    }

    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state, boolean isClient) {
        return !this.isMature(state);
    }

    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        this.applyGrowth(world, pos, state);
    }
}
