package com.thewandererraven.ravencoffee.blocks;

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
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.WorldView;
import net.minecraft.world.event.GameEvent;

public class RoscaDeReyesBlock extends Block {
    public static final int MAX_BITES = 11;
    public static final IntProperty BITES = IntProperty.of("bites", 0, 11);
    //public static final int FULL_SIGNAL = getOutputSignal(0);
    protected static final VoxelShape[] SHAPE_BY_BITE = new VoxelShape[]{
            Block.createCuboidShape(0, 0, 0, 16, 4, 16),
            Block.createCuboidShape(0, 0, 0, 16, 4, 16),
            Block.createCuboidShape(0, 0, 0, 16, 4, 16),
            Block.createCuboidShape(0, 0, 0, 16, 4, 16),
            Block.createCuboidShape(4, 0, 0, 16, 4, 16),
            Block.createCuboidShape(4, 0, 0, 16, 4, 16),
            Block.createCuboidShape(4, 0, 0, 16, 4, 16),
            Block.createCuboidShape(4, 0, 0, 16, 4, 12),
            Block.createCuboidShape(4, 0, 0, 16, 4, 8),
            Block.createCuboidShape(4, 0, 0, 16, 4, 4),
            Block.createCuboidShape(4, 0, 0, 12, 4, 4),
            Block.createCuboidShape(4, 0, 0, 8, 4, 4)
    };
    
    public RoscaDeReyesBlock(Settings p_49795_) {
        super(p_49795_);
        this.setDefaultState(this.getDefaultState()
                .with(BITES, 0)
        );
    }

    @Override
    public VoxelShape getOutlineShape(BlockState blockState, BlockView blockView, BlockPos blockPos, ShapeContext context) {
        return SHAPE_BY_BITE[blockState.get(BITES)];
    }

    @Override
    public ActionResult onUse(BlockState blockState, World world, BlockPos blockPos, PlayerEntity player, Hand interactionHand, BlockHitResult hit) {
        ItemStack itemstack = player.getStackInHand(interactionHand);
        if (world.isClient) {
            if (tryEat(world, blockPos, blockState, player).isAccepted()) {
                return ActionResult.SUCCESS;
            }

            if (itemstack.isEmpty()) {
                return ActionResult.CONSUME;
            }
        }

        ActionResult eatRes = tryEat(world, blockPos, blockState, player);
        if(eatRes == ActionResult.SUCCESS) {
            int rand = world.getRandom().nextInt(60000);
            if (rand < 1) {
                ItemEntity droppedItem = new ItemEntity(
                        world, blockPos.getX(), blockPos.getY(), blockPos.getZ(), new ItemStack(Items.TOTEM_OF_UNDYING)
                );
                droppedItem.resetPickupDelay();
                world.spawnEntity(droppedItem);
            }
        }
        return eatRes;
    }

    protected static ActionResult tryEat(World levelAccessor, BlockPos blockPos, BlockState blockState, PlayerEntity player) {
        if (!player.canConsume(false)) {
            return ActionResult.PASS;
        } else {
            player.getHungerManager().add(1, 1.0F);
            int i = blockState.get(BITES);
            levelAccessor.emitGameEvent(player, GameEvent.EAT, blockPos);
            if (i < MAX_BITES) {
                levelAccessor.setBlockState(blockPos, blockState.with(BITES, i + 1), 3);
            } else {
                levelAccessor.removeBlock(blockPos, false);
                levelAccessor.emitGameEvent(player, GameEvent.BLOCK_DESTROY, blockPos);
            }

            return ActionResult.SUCCESS;
        }
    }

    @Override
    public boolean canPlaceAt(BlockState blockState, WorldView worldView, BlockPos blockPos) {
        return worldView.getBlockState(blockPos.down()).getMaterial().isSolid();
    }

    @Override
    protected void appendProperties(StateManager.Builder<net.minecraft.block.Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(BITES);
    }

    @Override
    public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return getComparatorOutput(state.get(BITES));
    }

    public static int getComparatorOutput(int p_152747_) {
        return (13 - p_152747_) * 2;
    }

    @Override
    public boolean hasComparatorOutput(BlockState state) {
        return true;
    }

    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return false;
    }

}
