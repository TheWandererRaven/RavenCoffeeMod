package com.thewandererraven.ravencoffee.blocks;

import com.thewandererraven.ravencoffee.blocks.entities.StackingCupsBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import com.thewandererraven.ravencoffee.util.ModTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class StackingCupsBlock extends BaseEntityBlock {
    public static final VoxelShape SHAPE_0 = Block.box(0, 0, 0, 16, 16, 16);
    public static final VoxelShape SHAPE_1 = Block.box(0, 0, 0, 16, 16, 16);
    public static final VoxelShape SHAPE_2 = Block.box(0, 0, 0, 16, 16, 16);
    public static final VoxelShape SHAPE_3 = Block.box(0, 0, 0, 16, 16, 16);
    public static final VoxelShape SHAPE_4 = Block.box(0, 0, 0, 16, 16, 16);
    public static final VoxelShape SHAPE_5 = Block.box(0, 0, 0, 16, 16, 16);
    public static final VoxelShape SHAPE_6 = Block.box(0, 0, 0, 16, 16, 16);
    public static final VoxelShape SHAPE_7 = Block.box(0, 0, 0, 16, 16, 16);
    public static final VoxelShape SHAPE_8 = Block.box(0, 0, 0, 16, 16, 16);
    public static final VoxelShape SHAPE_9 = Block.box(0, 0, 0, 16, 16, 16);

    public static final IntegerProperty CUP_COUNT = IntegerProperty.create("cup_count", 0, 9);
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public StackingCupsBlock() {
        super(Properties
                .of(Material.STONE)
                .sound(SoundType.STONE)
        );
        this.registerDefaultState(this.getStateDefinition().any()
                .setValue(FACING, Direction.NORTH)
                .setValue(CUP_COUNT, 1)
        );
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new StackingCupsBlockEntity(blockPos, blockState);
    }

    // ================================================= BLOSCKSTATE =================================================

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState()
                .setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(CUP_COUNT);
        builder.add(FACING);
    }

    // ============================================== GENERAL INTERACTION ==============================================

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if(state.getBlock() != newState.getBlock()) {
            if(level.getBlockEntity(pos) instanceof StackingCupsBlockEntity blockEntity) {
                blockEntity.drops();
            }
        }
        super.onRemove(state, level, pos, newState, isMoving);
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        if(!level.isClientSide()) {
            if(level.getBlockEntity(blockPos) instanceof StackingCupsBlockEntity entity) {
                ItemStack itemInHand = player.getItemInHand(interactionHand);
                if (itemInHand.is(ModTags.Items.CUPS) && entity.canPlaceItem(itemInHand)) {
                    entity.placeItem(new ItemStack(itemInHand.getItem(), 1));
                    level.setBlockAndUpdate(blockPos, blockState.setValue(CUP_COUNT, entity.getCount()));
                    itemInHand.setCount(itemInHand.getCount() - 1);
                }
            }
        }
        return InteractionResult.sidedSuccess(level.isClientSide());
    }

    // =============================================== RENDER & SHAPES ================================================

    @Override
    public RenderShape getRenderShape(BlockState p_49232_) {
        return RenderShape.MODEL;
    }

    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(CUP_COUNT)) {
            case (1) -> SHAPE_1;
            case (2) -> SHAPE_2;
            case (3) -> SHAPE_3;
            case (4) -> SHAPE_4;
            case (5) -> SHAPE_5;
            case (6) -> SHAPE_6;
            case (7) -> SHAPE_7;
            case (8) -> SHAPE_8;
            case (9) -> SHAPE_9;
            default -> SHAPE_0;
        };
    }
}
