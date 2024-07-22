package com.thewandererraven.ravencoffee.blocks;

import com.thewandererraven.ravencoffee.blocks.entities.SackBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;

public class SackBlock extends BaseEntityBlock {
    public static final VoxelShape SHAPE_0 = Block.box(2, 0, 2, 14, 3, 14);
    public static final VoxelShape SHAPE_1 = Block.box(2, 0, 2, 14, 5, 14);
    public static final VoxelShape SHAPE_2 = Block.box(1, 0, 1, 15, 8, 15);
    public static final VoxelShape SHAPE_3 = Block.box(0, 0, 0, 16, 11, 16);
    public static final VoxelShape SHAPE_4 = Stream.of(
            Block.box(0, 0, 0, 16, 12, 16),
            Block.box(3, 12, 3, 13, 16, 13)
    ).reduce((v1, v2) -> {return Shapes.join(v1, v2, BooleanOp.OR);})
            .orElse(Block.box(0, 0, 0, 16, 16, 16));
    public static final IntegerProperty FULLNESS = IntegerProperty.create("fullness", 0, 4);
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    protected SackBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any()
                .setValue(FACING, Direction.NORTH)
                .setValue(FULLNESS, 0)
        );
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new SackBlockEntity(blockPos, blockState);
    }

    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FULLNESS)) {
            case (1) -> SHAPE_1;
            case (2) -> SHAPE_2;
            case (3) -> SHAPE_3;
            case (4) -> SHAPE_4;
            default -> SHAPE_0;
        };
    }

    // ================================================= BLOSCKSTATE =================================================

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState()
                .setValue(FACING, context.getHorizontalDirection().getOpposite());
    }
    public static int getFullnessState(int percentage) {
        if(percentage >= 100)
            return 4;
        else if(percentage >= 75)
            return 3;
        else if(percentage >= 50)
            return 2;
        else if(percentage >= 25)
            return 1;
        else
            return 0;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FULLNESS);
        builder.add(FACING);
    }

    // ============================================== GENERAL INTERACTION ==============================================

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if(state.getBlock() != newState.getBlock()) {
            if(level.getBlockEntity(pos) instanceof SackBlockEntity blockEntity) {
                blockEntity.drops();
            }
        }
        super.onRemove(state, level, pos, newState, isMoving);
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        if(!level.isClientSide()) {
            if(level.getBlockEntity(blockPos) instanceof SackBlockEntity entity) {
                if(!blockHitResult.getDirection().equals(Direction.UP))
                    NetworkHooks.openScreen(((ServerPlayer) player), entity, blockPos);
                else {
                    ItemStack itemInHand = player.getItemInHand(interactionHand);
                    if(!itemInHand.isEmpty() && entity.canPlaceItem(itemInHand)) {
                        ItemStack itemToInsert = itemInHand.copy();
                        itemToInsert.setCount(1);
                        entity.insertItem(itemToInsert);
                        itemInHand.setCount(itemInHand.getCount() - 1);
                    } else {
                        ItemStack grabbedItem = entity.grabItem();
                        ItemEntity droppedItem = new ItemEntity(level, blockHitResult.getLocation().x, blockHitResult.getLocation().y, blockHitResult.getLocation().z, grabbedItem);
                        droppedItem.setDefaultPickUpDelay();
                        level.addFreshEntity(droppedItem);
                    }
                }
            } else {
                throw new IllegalStateException("Our Container provider is missing!");
            }
        }
        return InteractionResult.sidedSuccess(level.isClientSide());
    }

    // =============================================== RENDER & SHAPES ================================================

    @Override
    public RenderShape getRenderShape(BlockState p_49232_) {
        return RenderShape.MODEL;
    }

/*
    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos,
                               CollisionContext context) {
        return SHAPES.get(state.getValue(FACING));
    }
*/

}