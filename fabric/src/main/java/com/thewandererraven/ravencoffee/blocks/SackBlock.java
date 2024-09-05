package com.thewandererraven.ravencoffee.blocks;


import com.thewandererraven.ravencoffee.Constants;
import com.thewandererraven.ravencoffee.blocks.entitites.SackBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.mob.PiglinBrain;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContext;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.*;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.util.shape.VoxelShape;

import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.Stream;

public class SackBlock extends BlockWithEntity implements BlockEntityProvider, ItemConvertible {
    public static final VoxelShape SHAPE_0 = Block.createCuboidShape(2, 0, 2, 14, 3, 14);
    public static final VoxelShape SHAPE_1 = Block.createCuboidShape(2, 0, 2, 14, 5, 14);
    public static final VoxelShape SHAPE_2 = Block.createCuboidShape(1, 0, 1, 15, 8, 15);
    public static final VoxelShape SHAPE_3 = Block.createCuboidShape(0, 0, 0, 16, 11, 16);
    public static final VoxelShape SHAPE_4 = Stream.of(
            Block.createCuboidShape(0, 0, 0, 16, 12, 16),
            Block.createCuboidShape(3, 12, 3, 13, 16, 13)
    ).reduce((v1, v2) -> {return VoxelShapes.combine(v1, v2, BooleanBiFunction.OR);})
            .orElse(Block.createCuboidShape(0, 0, 0, 16, 16, 16));
    public static final IntProperty FULLNESS = IntProperty.of("fullness", 0, 4);
    public static final DirectionProperty FACING = Properties.FACING;

    protected SackBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState()
                .with(FACING, Direction.NORTH)
                .with(FULLNESS, 0)
        );
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new SackBlockEntity(pos, state);
    }

    // ================================================= BLOSCKSTATE =================================================

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        return this.getDefaultState()
                .with(FACING, context.getHorizontalPlayerFacing().getOpposite());
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
    protected void appendProperties(StateManager.Builder<net.minecraft.block.Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(FACING);
        builder.add(FULLNESS);
    }

    // ============================================== GENERAL INTERACTION ==============================================

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (!state.isOf(newState.getBlock())) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof Inventory) {
                ItemScatterer.spawn(world, pos, (Inventory)blockEntity);
                world.updateComparators(pos, this);
            }

            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    public List<ItemStack> getDroppedStacks(BlockState state, LootContext.Builder builder) {
        Constants.LOGGER.info("GET DROPPED STACKS");
        return super.getDroppedStacks(state, builder);
    }

    @Override
    public ActionResult onUse(BlockState blockState, World world, BlockPos blockPos, PlayerEntity player, Hand interactionHand, BlockHitResult blockHitResult) {
        if (world.isClient || interactionHand.equals(Hand.OFF_HAND)) {
            return ActionResult.SUCCESS;
        } else {
            if(world.getBlockEntity(blockPos) instanceof SackBlockEntity entity) {
                if(!blockHitResult.getSide().equals(Direction.UP)) {
                    NamedScreenHandlerFactory namedScreenHandlerFactory = this.createScreenHandlerFactory(blockState, world, blockPos);
                    if (namedScreenHandlerFactory != null) {
                        player.openHandledScreen(namedScreenHandlerFactory);
                        PiglinBrain.onGuardedBlockInteracted(player, true);
                    }
                } else {
                    ItemStack itemInHand = player.getMainHandStack();
                    if(!itemInHand.isEmpty() && entity.canPlaceItem(itemInHand)) {
                        ItemStack itemToInsert = itemInHand.copy();
                        itemToInsert.setCount(1);
                        entity.insertItem(itemToInsert);
                        itemInHand.decrement(1);
                    } else {
                        ItemStack grabbedItem = entity.grabItem();
                        ItemScatterer.spawn(world, blockHitResult.getBlockPos().getX(), blockHitResult.getBlockPos().getY() + 1.0d, blockHitResult.getBlockPos().getZ(), grabbedItem);
                    }
                }
            } else {
                throw new IllegalStateException("Our Container provider is missing!");
            }
        }
        return ActionResult.CONSUME;
    }

    // =============================================== RENDER & SHAPES ================================================

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return switch (state.get(FULLNESS)) {
            case (1) -> SHAPE_1;
            case (2) -> SHAPE_2;
            case (3) -> SHAPE_3;
            case (4) -> SHAPE_4;
            default -> SHAPE_0;
        };
    }
    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }


}