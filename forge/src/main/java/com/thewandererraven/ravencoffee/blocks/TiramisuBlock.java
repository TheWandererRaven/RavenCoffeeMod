package com.thewandererraven.ravencoffee.blocks;

import com.thewandererraven.ravencoffee.items.RavenCoffeeItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class TiramisuBlock extends Block {
    public static final int MAX_SLICES = 16;
    public static final IntegerProperty SLICES = IntegerProperty.create("slices", 1, 16);
    public static final int FULL_SIGNAL = getOutputSignal(0);
    protected static final VoxelShape[] SHAPE_BY_BITE = new VoxelShape[]{
            Block.box(11, 0, 2, 14, 4, 5),
            Block.box(11, 0, 2, 14, 4, 8),
            Block.box(11, 0, 2, 14, 4, 11),
            Block.box(11, 0, 2, 14, 4, 14),
            Block.box(8, 0, 2, 14, 4, 14),
            Block.box(8, 0, 2, 14, 4, 14),
            Block.box(8, 0, 2, 14, 4, 14),
            Block.box(8, 0, 2, 14, 4, 14),
            Block.box(5, 0, 2, 14, 4, 14),
            Block.box(5, 0, 2, 14, 4, 14),
            Block.box(5, 0, 2, 14, 4, 14),
            Block.box(5, 0, 2, 14, 4, 14),
            Block.box(2, 0, 2, 14, 4, 14),
            Block.box(2, 0, 2, 14, 4, 14),
            Block.box(2, 0, 2, 14, 4, 14),
            Block.box(2, 0, 2, 14, 4, 14),
    };

    public TiramisuBlock(Properties p_49795_) {
        super(p_49795_);
        this.registerDefaultState(this.stateDefinition.any().setValue(SLICES, Integer.valueOf(16)));
    }

    /*
    @Override
    public void setPlacedBy(Level level, BlockPos blockPos, BlockState blockState, @Nullable LivingEntity entity, ItemStack itemStack) {
        super.setPlacedBy(level, blockPos, blockState, entity, itemStack);
        if(itemStack.is(RavenCoffeeItems.TIRAMISU_SLICE.get()))
            level.setBlockAndUpdate(blockPos, blockState.setValue(SLICES, Integer.valueOf(1)));
    }
     */

    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext context) {
        return SHAPE_BY_BITE[blockState.getValue(SLICES) - 1];
    }

    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult hit) {
        ItemStack itemstack = player.getItemInHand(interactionHand);
        if (!level.isClientSide) {
            InteractionResult result = InteractionResult.FAIL;
            if(itemstack.is(RavenCoffeeItems.TIRAMISU_SLICE.get())) {
                result = addSlice(level, blockPos, blockState, player);
                if(result == InteractionResult.CONSUME)
                    itemstack.setCount(itemstack.getCount() - 1);
            } else {
                result = removeSlice(level, blockPos, blockState, player);
                if(result == InteractionResult.CONSUME) {
                    ItemStack slice = new ItemStack(RavenCoffeeItems.TIRAMISU_SLICE.get());
                    ItemEntity droppedItem = new ItemEntity(level, hit.getLocation().x, hit.getLocation().y + 0.1, hit.getLocation().z, slice);
                    droppedItem.setDefaultPickUpDelay();
                    level.addFreshEntity(droppedItem);
                }
            }
            return result;
        }
        return InteractionResult.sidedSuccess(level.isClientSide());
    }

    private InteractionResult addSlice(LevelAccessor levelAccessor, BlockPos blockPos, BlockState blockState, Player player) {
        Integer slices = blockState.getValue(SLICES);
        if(slices >= MAX_SLICES) return InteractionResult.PASS;
        updateBlcokstate(levelAccessor, blockPos, blockState, player, blockState.getValue(SLICES) + 1);
        return InteractionResult.CONSUME;
    }

    private InteractionResult removeSlice(LevelAccessor levelAccessor, BlockPos blockPos, BlockState blockState, Player player) {
        updateBlcokstate(levelAccessor, blockPos, blockState, player, blockState.getValue(SLICES) - 1);
        return InteractionResult.CONSUME;
    }

    private static void updateBlcokstate(LevelAccessor levelAccessor, BlockPos blockPos, BlockState blockState, Player player, Integer newSlices) {
        if (newSlices > 0) {
            levelAccessor.setBlock(blockPos, blockState.setValue(SLICES, newSlices), 3);
        } else {
            levelAccessor.removeBlock(blockPos, false);
            levelAccessor.gameEvent(player, GameEvent.BLOCK_DESTROY, blockPos);
        }
    }

    public boolean canSurvive(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
        return levelReader.getBlockState(blockPos.below()).getMaterial().isSolid();
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(SLICES);
    }

    public int getAnalogOutputSignal(BlockState blockState, Level level, BlockPos blockPos) {
        return getOutputSignal(blockState.getValue(SLICES));
    }

    public static int getOutputSignal(int p_152747_) {
        return (17 - p_152747_) * 2;
    }

    public boolean hasAnalogOutputSignal(BlockState blockState) {
        return true;
    }

    public boolean isPathfindable(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, PathComputationType pathComputationType) {
        return false;
    }

}
