package com.TheWandererRaven.ravencoffee.blocks;

import com.TheWandererRaven.ravencoffee.util.registries.BlocksRegistry;
import com.TheWandererRaven.ravencoffee.util.registries.ItemsRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Ravager;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.event.ForgeEventFactory;

import java.util.Iterator;
import java.util.Random;

public class CoffeeTreeLeavesBlock extends CropBlock implements BonemealableBlock {
        public static final IntegerProperty AGE;
        private static final VoxelShape[] SHAPE_BY_AGE;

        public CoffeeTreeLeavesBlock(Properties p_i48421_1_) {
            super(p_i48421_1_);
            this.registerDefaultState(this.getStateDefinition().any().setValue(this.getAgeProperty(), 0));
        }

        public VoxelShape getShape(BlockState p_220053_1_, BlockGetter p_220053_2_, BlockPos p_220053_3_, CollisionContext p_220053_4_) {
            return SHAPE_BY_AGE[(Integer)p_220053_1_.getValue(this.getAgeProperty())];
        }

        protected boolean isValidGround(BlockState p_200014_1_, BlockGetter p_200014_2_, BlockPos p_200014_3_) {
            return p_200014_1_.is(BlocksRegistry.COFFEE_TREE_TRUNK_BLOCK.get());
        }

        public IntegerProperty getAgeProperty() {
            return AGE;
        }

        public int getMaxAge() {
            return 3;
        }

        protected int getAge(BlockState p_185527_1_) {
            return (Integer)p_185527_1_.getValue(this.getAgeProperty());
        }

        public BlockState withAge(int p_185528_1_) {
            return this.defaultBlockState().setValue(this.getAgeProperty(), p_185528_1_);
        }

        public boolean isMaxAge(BlockState p_185525_1_) {
            return (Integer)p_185525_1_.getValue(this.getAgeProperty()) >= this.getMaxAge();
        }

        public boolean ticksRandomly(BlockState p_149653_1_) {
            return true;
        }

        public void randomTick(BlockState p_225542_1_, ServerLevel p_225542_2_, BlockPos p_225542_3_, Random p_225542_4_) {
            if (p_225542_2_.isAreaLoaded(p_225542_3_, 1)) {
                if (p_225542_2_.getRawBrightness(p_225542_3_, 0) >= 9) {
                    float f = getGrowthSpeed(this, p_225542_2_, p_225542_3_);
                    if (ForgeHooks.onCropsGrowPre(p_225542_2_, p_225542_3_, p_225542_1_, p_225542_4_.nextInt((int)(25.0F / f) + 1) == 0)) {
                        int i = (Integer)p_225542_1_.getValue(AGE);
                        if (i < this.getMaxAge()) {
                            p_225542_2_.setBlock(p_225542_3_, (BlockState)p_225542_1_.setValue(AGE, i + 1), 2);
                        }
                        ForgeHooks.onCropsGrowPost(p_225542_2_, p_225542_3_, p_225542_1_);
                    }
                }

            }
        }

        public void grow(Level p_176487_1_, BlockPos p_176487_2_, BlockState p_176487_3_) {
            int i = this.getAge(p_176487_3_) + this.getBonemealAgeIncrease(p_176487_1_);
            int j = this.getMaxAge();
            if (i > j) {
                i = j;
            }

            p_176487_1_.setBlock(p_176487_2_, this.withAge(i), 2);
        }

        protected int getBonemealAgeIncrease(Level p_185529_1_) {
            return Mth.nextInt(p_185529_1_.random, 2, 5);
        }

        protected static float getGrowthChance(Block p_180672_0_, BlockGetter p_180672_1_, BlockPos p_180672_2_) {
            float f = 1.0F;
            BlockPos blockpos = p_180672_2_.below();

            for(int i = -1; i <= 1; ++i) {
                for(int j = -1; j <= 1; ++j) {
                    float f1 = 0.0F;
                    BlockState blockstate = p_180672_1_.getBlockState(blockpos.offset(i, 0, j));
                    if (blockstate.canSustainPlant(p_180672_1_, blockpos.offset(i, 0, j), Direction.UP, (IPlantable)p_180672_0_)) {
                        f1 = 1.0F;
                        if (blockstate.isFertile(p_180672_1_, p_180672_2_.offset(i, 0, j))) {
                            f1 = 3.0F;
                        }
                    }

                    if (i != 0 || j != 0) {
                        f1 /= 4.0F;
                    }

                    f += f1;
                }
            }

            BlockPos blockpos1 = p_180672_2_.north();
            BlockPos blockpos2 = p_180672_2_.south();
            BlockPos blockpos3 = p_180672_2_.west();
            BlockPos blockpos4 = p_180672_2_.east();
            boolean flag = p_180672_0_ == p_180672_1_.getBlockState(blockpos3).getBlock() || p_180672_0_ == p_180672_1_.getBlockState(blockpos4).getBlock();
            boolean flag1 = p_180672_0_ == p_180672_1_.getBlockState(blockpos1).getBlock() || p_180672_0_ == p_180672_1_.getBlockState(blockpos2).getBlock();
            if (flag && flag1) {
                f /= 2.0F;
            } else {
                boolean flag2 = p_180672_0_ == p_180672_1_.getBlockState(blockpos3.north()).getBlock() || p_180672_0_ == p_180672_1_.getBlockState(blockpos4.north()).getBlock() || p_180672_0_ == p_180672_1_.getBlockState(blockpos4.south()).getBlock() || p_180672_0_ == p_180672_1_.getBlockState(blockpos3.south()).getBlock();
                if (flag2) {
                    f /= 2.0F;
                }
            }

            return f;
        }

        public boolean isValidPosition(BlockState p_196260_1_, BlockGetter p_196260_2_, BlockPos p_196260_3_) {
            BlockPos blockpos = p_196260_3_.below();

            Iterator<Direction> var4 = Direction.Plane.HORIZONTAL.iterator();
            Direction direction;
            Material material;
            BlockState blockstate;
            do {
                if (!var4.hasNext()) {
                    BlockState blockstate1 = p_196260_2_.getBlockState(p_196260_3_.below());
                    return (p_196260_2_.getLightEmission(p_196260_3_) >= 8 /*|| p_196260_2_.canSeeSky(p_196260_3_)*/)
                            && this.isValidGround(p_196260_2_.getBlockState(blockpos), p_196260_2_, blockpos);
                }

                direction = var4.next();
                blockstate = p_196260_2_.getBlockState(p_196260_3_.offset(direction.getNormal()));
                material = blockstate.getMaterial();
            } while(!material.isSolid() && !p_196260_2_.getFluidState(p_196260_3_.offset(direction.getNormal())).is(FluidTags.LAVA));
            return false;
        }

        public void onEntityCollision(BlockState p_196262_1_, Level p_196262_2_, BlockPos p_196262_3_, Entity p_196262_4_) {
            if (p_196262_4_ instanceof Ravager && ForgeEventFactory.getMobGriefingEvent(p_196262_2_, p_196262_4_)) {
                p_196262_2_.destroyBlock(p_196262_3_, true, p_196262_4_);
            }

            super.entityInside(p_196262_1_, p_196262_2_, p_196262_3_, p_196262_4_);
        }

        protected ItemLike getSeedsItem() {
            return ItemsRegistry.COFFEE_CHERRIES.get();
        }

        public ItemStack getItem(BlockGetter p_185473_1_, BlockPos p_185473_2_, BlockState p_185473_3_) {
            return new ItemStack(this.getSeedsItem());
        }

        public boolean canGrow(BlockGetter p_176473_1_, BlockPos p_176473_2_, BlockState p_176473_3_, boolean p_176473_4_) {
            return !this.isMaxAge(p_176473_3_);
        }

        public boolean canUseBonemeal(Level p_180670_1_, Random p_180670_2_, BlockPos p_180670_3_, BlockState p_180670_4_) {
            return true;
        }

        public void grow(ServerLevel p_225535_1_, Random p_225535_2_, BlockPos p_225535_3_, BlockState p_225535_4_) {
            this.grow(p_225535_1_, p_225535_3_, p_225535_4_);
        }

        protected void fillStateContainer(StateDefinition.Builder<Block, BlockState> p_206840_1_) {
            p_206840_1_.add(new Property[]{AGE});
        }

        public BlockState getBiomeGenState() {
            Random R = new Random();
            return this.stateDefinition.any().setValue(this.getAgeProperty(), R.nextInt(3));
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
