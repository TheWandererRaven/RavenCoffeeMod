package com.thewandererraven.ravencoffee.blocks;

import com.thewandererraven.ravencoffee.items.RavenCoffeeItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
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
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.ForgeEventFactory;

import java.util.Iterator;

public class CoffeeTreeBlock extends CropBlock implements BonemealableBlock {
    public static final IntegerProperty AGE;
    private static final VoxelShape[] SHAPE_BY_AGE;

    public CoffeeTreeBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(this.getAgeProperty(), 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{AGE});
    }

    @Override
    public ItemStack getCloneItemStack(BlockGetter blockGetter, BlockPos pos, BlockState blockState) {
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
    protected int getAge(BlockState blockState) {
        return blockState.getValue(this.getAgeProperty());
    }

    @Override
    public boolean isMaxAge(BlockState blockState) {
        return blockState.getValue(this.getAgeProperty()) >= this.getMaxAge();
    }

    @Override
    public BlockState getStateForAge(int age) {
        return this.defaultBlockState().setValue(this.getAgeProperty(), age);
    }

    // ##################################### CROPS #####################################

    @Override
    protected ItemLike getBaseSeedId() {
        return RavenCoffeeItems.COFFEE_CHERRIES.get();
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
    public void tick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        if (!serverLevel.isAreaLoaded(blockPos, 1)) return;
        if (!blockState.canSurvive(serverLevel, blockPos)) {
            serverLevel.destroyBlock(blockPos, true);
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
    public void randomTick(BlockState blockState, ServerLevel server, BlockPos blockPos, RandomSource rand) {
        if (server.isAreaLoaded(blockPos, 1))
            if(isAboveBlockAcceptable(server, blockPos))
                if (server.getRawBrightness(blockPos, 0) >= 9 || server.canSeeSky(blockPos)) {
                    if (ForgeHooks.onCropsGrowPre(server, blockPos, blockState, rand.nextInt((int)(25.0F) + 1) == 0)) {
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
    public boolean isValidBonemealTarget(BlockGetter blockGetter, BlockPos blockPos, BlockState blockState, boolean isClient) {
        return !this.isMaxAge(blockState);
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel server, RandomSource rand, BlockPos blockPos, BlockState blockState) {
        this.growCrops(server, blockPos, blockState);
    }

    // ##################################### WORLD #####################################

    @Override
    protected boolean mayPlaceOn(BlockState floor, BlockGetter getter, BlockPos pos) {
        return floor.is(BlockTags.DIRT);
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
        } while((!material.isSolid() || material.equals(Material.LEAVES) || blockstate.is(Tags.Blocks.FENCES) || blockstate.is(Tags.Blocks.FENCE_GATES) || blockstate.is(Blocks.JIGSAW)) && !blockGetter.getFluidState(blockPos.offset(direction.getNormal())).is(FluidTags.LAVA));
        return false;
    }

    @Override
    public void entityInside(BlockState blockState, Level level, BlockPos blockPos, Entity entity) {
        if (entity instanceof Ravager && ForgeEventFactory.getMobGriefingEvent(level, entity)) {
            level.destroyBlock(blockPos, true, entity);
        }

        super.entityInside(blockState, level, blockPos, entity);
    }

    // ##################################### BLOCK SHAPE #####################################

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return SHAPE_BY_AGE[blockState.getValue(this.getAgeProperty())];
    }

    @Override
    public BlockState updateShape(BlockState blockState, Direction p_51158_, BlockState secondState, LevelAccessor levelAccessor, BlockPos pos, BlockPos secondPos) {
        if (!blockState.canSurvive(levelAccessor, pos)) {
            levelAccessor.scheduleTick(pos, this, 1);
        }

        return super.updateShape(blockState, p_51158_, secondState, levelAccessor, pos, secondPos);
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
