package com.thewandererraven.ravencoffee.blocks;

import com.thewandererraven.ravencoffee.items.RavenCoffeeItems;
import net.minecraft.block.*;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.WorldView;
import net.minecraft.world.event.GameEvent;


public class TiramisuBlock extends Block {
    public static final int MAX_SLICES = 16;
    public static final IntProperty SLICES = IntProperty.of("slices", 1, 16);
    public static final int FULL_SIGNAL = getOutputSignal(0);
    protected static final VoxelShape[] SHAPE_BY_BITE = new VoxelShape[]{
            Block.createCuboidShape(11, 0, 2, 14, 4, 5),
            Block.createCuboidShape(11, 0, 2, 14, 4, 8),
            Block.createCuboidShape(11, 0, 2, 14, 4, 11),
            Block.createCuboidShape(11, 0, 2, 14, 4, 14),
            Block.createCuboidShape(8, 0, 2, 14, 4, 14),
            Block.createCuboidShape(8, 0, 2, 14, 4, 14),
            Block.createCuboidShape(8, 0, 2, 14, 4, 14),
            Block.createCuboidShape(8, 0, 2, 14, 4, 14),
            Block.createCuboidShape(5, 0, 2, 14, 4, 14),
            Block.createCuboidShape(5, 0, 2, 14, 4, 14),
            Block.createCuboidShape(5, 0, 2, 14, 4, 14),
            Block.createCuboidShape(5, 0, 2, 14, 4, 14),
            Block.createCuboidShape(2, 0, 2, 14, 4, 14),
            Block.createCuboidShape(2, 0, 2, 14, 4, 14),
            Block.createCuboidShape(2, 0, 2, 14, 4, 14),
            Block.createCuboidShape(2, 0, 2, 14, 4, 14),
    };

    public TiramisuBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(SLICES, 16));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState blockState, BlockView blockView, BlockPos blockPos, ShapeContext context) {
        return SHAPE_BY_BITE[blockState.get(SLICES) - 1];
    }

    @Override
    public ActionResult onUse(BlockState blockState, World world, BlockPos blockPos, PlayerEntity player, Hand interactionHand, BlockHitResult hit) {
        ItemStack itemstack = player.getStackInHand(interactionHand);
        if (!world.isClient) {
            ActionResult result = ActionResult.FAIL;
            if(itemstack.isOf(RavenCoffeeItems.TIRAMISU_SLICE)) {
                result = addSlice(world, blockPos, blockState, player);
                if(result == ActionResult.CONSUME)
                    itemstack.setCount(itemstack.getCount() - 1);
            } else {
                result = removeSlice(world, blockPos, blockState, player);
                if(result == ActionResult.CONSUME) {
                    ItemStack slice = new ItemStack(RavenCoffeeItems.TIRAMISU_SLICE);
                    ItemEntity droppedItem = new ItemEntity(world, hit.getBlockPos().getX(), hit.getBlockPos().getY() + 0.1, hit.getBlockPos().getZ(), slice);
                    droppedItem.resetPickupDelay();
                    world.spawnEntity(droppedItem);
                }
            }
            return result;
        }
        return ActionResult.success(world.isClient);
    }

    private ActionResult addSlice(World levelAccessor, BlockPos blockPos, BlockState blockState, PlayerEntity player) {
        Integer slices = blockState.get(SLICES);
        if(slices >= MAX_SLICES) return ActionResult.PASS;
        updateBlcokstate(levelAccessor, blockPos, blockState, player, blockState.get(SLICES) + 1);
        return ActionResult.CONSUME;
    }

    private ActionResult removeSlice(World levelAccessor, BlockPos blockPos, BlockState blockState, PlayerEntity player) {
        updateBlcokstate(levelAccessor, blockPos, blockState, player, blockState.get(SLICES) - 1);
        return ActionResult.CONSUME;
    }

    private static void updateBlcokstate(World world, BlockPos blockPos, BlockState blockState, PlayerEntity player, Integer newSlices) {
        if (newSlices > 0) {
            world.setBlockState(blockPos, blockState.with(SLICES, newSlices), 3);
        } else {
            world.removeBlock(blockPos, false);
            world.emitGameEvent(player, GameEvent.BLOCK_DESTROY, blockPos);
        }
    }

    @Override
    public boolean canPlaceAt(BlockState blockState, WorldView worldView, BlockPos blockPos) {
        return worldView.getBlockState(blockPos.down()).isSolidBlock(worldView, blockPos.down());
    }

    @Override
    protected void appendProperties(StateManager.Builder<net.minecraft.block.Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(SLICES);
    }

    @Override
    public int getStrongRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        return getOutputSignal(state.get(SLICES));
    }

    @Override
    public int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        return 1;
    }

    public static int getOutputSignal(int p_152747_) {
        return (17 - p_152747_) * 2;
    }

    @Override
    public boolean emitsRedstonePower(BlockState state) {
        return true;
    }

    @Override
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return false;
    }

}
