package com.TheWandererRaven.ravencoffee.blocks;

import com.TheWandererRaven.ravencoffee.RavenCoffee;
import com.TheWandererRaven.ravencoffee.util.registries.ItemsRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Ravager;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.PlantType;
import net.minecraftforge.event.ForgeEventFactory;

import java.util.Iterator;
import java.util.Random;
import java.util.UUID;

public class CoffeeTreeBlock extends CropBlock implements BonemealableBlock {
    public static final IntegerProperty AGE;
    private static final VoxelShape[] SHAPE_BY_AGE;

    public CoffeeTreeBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(this.getAgeProperty(), 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_206840_1_) {
        p_206840_1_.add(new Property[]{AGE});
    }

    @Override
    public ItemStack getCloneItemStack(BlockGetter p_185473_1_, BlockPos p_185473_2_, BlockState p_185473_3_) {
        return new ItemStack(this.getBaseSeedId());
    }

    // ##################################### AGE #####################################

    @Override
    public IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getMaxAge() {
        return 3;
    }

    @Override
    protected int getAge(BlockState p_185527_1_) {
        return p_185527_1_.getValue(this.getAgeProperty());
    }

    @Override
    public boolean isMaxAge(BlockState p_185525_1_) {
        return p_185525_1_.getValue(this.getAgeProperty()) >= this.getMaxAge();
    }

    @Override
    public BlockState getStateForAge(int p_52290_) {
        return this.defaultBlockState().setValue(this.getAgeProperty(), p_52290_);
    }

    // ##################################### CROPS #####################################

    @Override
    protected ItemLike getBaseSeedId() {
        return ItemsRegistry.COFFEE_CHERRIES.get();
    }

    @Override
    public PlantType getPlantType(BlockGetter world, BlockPos pos) {
        return PlantType.CROP;
    }

    @Override
    public BlockState getPlant(BlockGetter world, BlockPos pos) {
        return defaultBlockState();
    }

    @Override
    public void growCrops(Level level, BlockPos blockPos, BlockState blockState) {
        int i = this.getAge(blockState) + this.getBonemealAgeIncrease(level);
        int j = this.getMaxAge();
        if (i > j) {
            i = j;
        }

        level.setBlock(blockPos, this.getStateForAge(i), 2);
    }

    // ##################################### TICKS #####################################

    @Override
    public void tick(BlockState p_51138_, ServerLevel p_51139_, BlockPos p_51140_, Random p_51141_) {
        if (!p_51139_.isAreaLoaded(p_51140_, 1)) return;
        if (!p_51138_.canSurvive(p_51139_, p_51140_)) {
            p_51139_.destroyBlock(p_51140_, true);
        }

    }

    public void tickGrow(int age, BlockState blockState, ServerLevel server, BlockPos blockPos) {
        if (age < this.getMaxAge()) {
            server.setBlock(blockPos, blockState.setValue(AGE, age + 1), 2);
        }
    }

    public boolean isAboveBlockAcceptable(Level level,  BlockPos blockPos) {
        return true;
    }

    @Override
    public void randomTick(BlockState blockState, ServerLevel server, BlockPos blockPos, Random rand) {
        if (server.isAreaLoaded(blockPos, 1))
            if(isAboveBlockAcceptable(server, blockPos))
                if (server.getRawBrightness(blockPos, 0) >= 9 || server.canSeeSky(blockPos)) {
                    float f = getGrowthSpeed(this, server, blockPos);
                    if (ForgeHooks.onCropsGrowPre(server, blockPos, blockState, rand.nextInt((int)(25.0F / f) + 1) == 0)) {
                        this.tickGrow(blockState.getValue(AGE), blockState, server, blockPos);
                        ForgeHooks.onCropsGrowPost(server, blockPos, blockState);
                    }
                }
    }

    // ##################################### BONEMEAL #####################################

    @Override
    protected int getBonemealAgeIncrease(Level p_185529_1_) {
        return Mth.nextInt(p_185529_1_.random, 2, 5);
    }

    @Override
    public boolean isValidBonemealTarget(BlockGetter p_176473_1_, BlockPos p_176473_2_, BlockState p_176473_3_, boolean p_176473_4_) {
        return !this.isMaxAge(p_176473_3_);
    }

    @Override
    public boolean isBonemealSuccess(Level p_180670_1_, Random p_180670_2_, BlockPos p_180670_3_, BlockState p_180670_4_) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel server, Random rand, BlockPos blockPos, BlockState blockState) {
        this.growCrops(server, blockPos, blockState);
    }

    // ##################################### WORLD #####################################

    @Override
    protected boolean mayPlaceOn(BlockState p_200014_1_, BlockGetter p_200014_2_, BlockPos p_200014_3_) {
        return p_200014_1_.is(Blocks.GRASS_BLOCK) || p_200014_1_.is(Blocks.DIRT);
    }

    public BlockState getBiomeGenState() {
        return this.stateDefinition.any().setValue(this.getAgeProperty(), 3);
    }

    @Override
    public boolean canSurvive(BlockState blockState, LevelReader blockGetter, BlockPos blockPos) {
        BlockPos blockpos = blockPos.below();

        Iterator<Direction> var4 = Direction.Plane.HORIZONTAL.iterator();
        Direction direction;
        Material material;
        BlockState blockstate;

        do {
            if (!var4.hasNext()) {
                return //(blockGetter.getLightEmission(blockPos) >= 8 || blockGetter.canSeeSky(blockPos)) &&
                        this.mayPlaceOn(blockGetter.getBlockState(blockpos), blockGetter, blockpos);
            }

            direction = var4.next();
            blockstate = blockGetter.getBlockState(blockPos.offset(direction.getNormal()));
            material = blockstate.getMaterial();
        } while((!material.isSolid() || material.equals(Material.LEAVES)) && !blockGetter.getFluidState(blockPos.offset(direction.getNormal())).is(FluidTags.LAVA));
        return false;
    }

    @Override
    public void entityInside(BlockState p_196262_1_, Level p_196262_2_, BlockPos p_196262_3_, Entity p_196262_4_) {
        if (p_196262_4_ instanceof Ravager && ForgeEventFactory.getMobGriefingEvent(p_196262_2_, p_196262_4_)) {
            p_196262_2_.destroyBlock(p_196262_3_, true, p_196262_4_);
        }

        super.entityInside(p_196262_1_, p_196262_2_, p_196262_3_, p_196262_4_);
    }

    // ##################################### BLOCK SHAPE #####################################

    @Override
    public VoxelShape getShape(BlockState p_220053_1_, BlockGetter p_220053_2_, BlockPos p_220053_3_, CollisionContext p_220053_4_) {
        return SHAPE_BY_AGE[p_220053_1_.getValue(this.getAgeProperty())];
    }

    @Override
    public BlockState updateShape(BlockState p_51157_, Direction p_51158_, BlockState p_51159_, LevelAccessor p_51160_, BlockPos p_51161_, BlockPos p_51162_) {
        if (!p_51157_.canSurvive(p_51160_, p_51161_)) {
            p_51160_.getBlockTicks().scheduleTick(p_51161_, this, 1);
        }

        return super.updateShape(p_51157_, p_51158_, p_51159_, p_51160_, p_51161_, p_51162_);
    }

    static {
        AGE = BlockStateProperties.AGE_3;
        SHAPE_BY_AGE = new VoxelShape[]{
                Block.box(
                        0.0D,
                        0.0D,// volume bottom
                        0.0D,
                        16.0D,// top
                        16.0D,// volume top
                        16.0D// right
                ),
                Block.box(
                        0.0D,
                        0.0D,// volume bottom
                        0.0D,
                        16.0D,// top
                        16.0D,// volume top
                        16.0D// right
                ),
                Block.box(
                        0.0D,
                        0.0D,// volume bottom
                        0.0D,
                        16.0D,// top
                        16.0D,// volume top
                        16.0D// right
                ),
                Block.box(
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
