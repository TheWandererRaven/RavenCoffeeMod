package com.thewandererraven.ravencoffee.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
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

public class RoscaDeReyesBlock extends Block {
    public static final int MAX_BITES = 11;
    public static final IntegerProperty BITES = IntegerProperty.create("bites", 0, 11);
    public static final int FULL_SIGNAL = getOutputSignal(0);
    protected static final VoxelShape[] SHAPE_BY_BITE = new VoxelShape[]{
            Block.box(0, 0, 0, 16, 4, 16),
            Block.box(0, 0, 0, 16, 4, 16),
            Block.box(0, 0, 0, 16, 4, 16),
            Block.box(0, 0, 0, 16, 4, 16),
            Block.box(4, 0, 0, 16, 4, 16),
            Block.box(4, 0, 0, 16, 4, 16),
            Block.box(4, 0, 0, 16, 4, 16),
            Block.box(4, 0, 0, 16, 4, 12),
            Block.box(4, 0, 0, 16, 4, 8),
            Block.box(4, 0, 0, 16, 4, 4),
            Block.box(4, 0, 0, 12, 4, 4),
            Block.box(4, 0, 0, 8, 4, 4)
    };
    
    public RoscaDeReyesBlock(Properties p_49795_) {
        super(p_49795_);
        this.registerDefaultState(this.stateDefinition.any().setValue(BITES, Integer.valueOf(0)));
    }

    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext context) {
        return SHAPE_BY_BITE[blockState.getValue(BITES)];
    }

    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult hit) {
        ItemStack itemstack = player.getItemInHand(interactionHand);
        if (level.isClientSide) {
            if (eat(level, blockPos, blockState, player).consumesAction()) {
                return InteractionResult.SUCCESS;
            }

            if (itemstack.isEmpty()) {
                return InteractionResult.CONSUME;
            }
        }

        InteractionResult eatRes = eat(level, blockPos, blockState, player);
        if(eatRes == InteractionResult.SUCCESS) {
            int rand = level.getRandom().nextInt(60000);
            if (rand < 1) {
                ItemEntity droppedItem = new ItemEntity(
                        level, blockPos.getX(), blockPos.getY(), blockPos.getZ(), new ItemStack(Items.TOTEM_OF_UNDYING)
                );
                droppedItem.setDefaultPickUpDelay();
                level.addFreshEntity(droppedItem);
            }
        }
        return eatRes;
    }

    protected static InteractionResult eat(LevelAccessor levelAccessor, BlockPos blockPos, BlockState blockState, Player player) {
        if (!player.canEat(false)) {
            return InteractionResult.PASS;
        } else {
            player.getFoodData().eat(1, 1.0F);
            int i = blockState.getValue(BITES);
            levelAccessor.gameEvent(player, GameEvent.EAT, blockPos);
            if (i < MAX_BITES) {
                levelAccessor.setBlock(blockPos, blockState.setValue(BITES, Integer.valueOf(i + 1)), 3);
            } else {
                levelAccessor.removeBlock(blockPos, false);
                levelAccessor.gameEvent(player, GameEvent.BLOCK_DESTROY, blockPos);
            }

            return InteractionResult.SUCCESS;
        }
    }

    public boolean canSurvive(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
        return levelReader.getBlockState(blockPos.below()).getMaterial().isSolid();
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BITES);
    }

    public int getAnalogOutputSignal(BlockState blockState, Level level, BlockPos blockPos) {
        return getOutputSignal(blockState.getValue(BITES));
    }

    public static int getOutputSignal(int p_152747_) {
        return (13 - p_152747_) * 2;
    }

    public boolean hasAnalogOutputSignal(BlockState blockState) {
        return true;
    }

    public boolean isPathfindable(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, PathComputationType pathComputationType) {
        return false;
    }

}
