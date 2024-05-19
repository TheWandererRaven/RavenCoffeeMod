package com.thewandererraven.ravencoffee.blocks;

import com.thewandererraven.ravencoffee.Constants;
import com.thewandererraven.ravencoffee.blocks.entitites.CoffeeMachineBlockEntity;
import com.thewandererraven.ravencoffee.blocks.entitites.RavenCoffeeBlockEntities;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class CoffeeMachineBlock extends BlockWithEntity implements BlockEntityProvider {
    private static final Text TITLE = Text.translatable("container." + Constants.MOD_ID + ".coffee_grinder_registry_name");
    public static final VoxelShape SHAPE = VoxelShapes.union(
            Block.createCuboidShape(1, 0, 1, 15, 1, 5),
            Block.createCuboidShape(1, 0, 5, 15, 11, 15),
            Block.createCuboidShape(3, 7, 3, 4, 10, 4),
            Block.createCuboidShape(1, 8, 4, 11, 11, 5),
            Block.createCuboidShape(6, 11, 6, 10, 12, 10),
            Block.createCuboidShape(5, 12, 5, 11, 15, 11),
            Block.createCuboidShape(6, 15, 6, 10, 16, 10)
    );
    public static final Map<Direction, VoxelShape> SHAPES = new HashMap<Direction, VoxelShape>();

    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    public static final BooleanProperty ACTIVE = BooleanProperty.of("active");
    public static final BooleanProperty ENABLED = BooleanProperty.of("enabled");
    public static final BooleanProperty HAS_INPUT_CUP = BooleanProperty.of("has_input_cup");
    public static final BooleanProperty HAS_COFFEE = BooleanProperty.of("has_coffee");
    public static final BooleanProperty HAS_OUTPUT = BooleanProperty.of("has_output");

    protected CoffeeMachineBlock(Settings settings) {
        super(settings);
        this.setDefaultState(((BlockState) this.getDefaultState())
                .with(FACING, net.minecraft.util.math.Direction.NORTH)
                .with(ENABLED, true)
                .with(ACTIVE, false)
                .with(HAS_OUTPUT, false)
                .with(HAS_INPUT_CUP, false)
                .with(HAS_COFFEE, false)
        );
        this.runCalculation(SHAPE);
    }

    // ################################################## BLOCKSTATE ##################################################
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
        builder.add(ENABLED);
        builder.add(ACTIVE);
        builder.add(HAS_OUTPUT);
        builder.add(HAS_INPUT_CUP);
        builder.add(HAS_COFFEE);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return super.getPlacementState(ctx).with(FACING, ctx.getPlayerFacing().getOpposite());
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        checkPoweredState(world, pos, state);
    }

    private void checkPoweredState(World world, BlockPos pos, BlockState blockState) {
        boolean flag = !world.isReceivingRedstonePower(pos);
        if (flag != blockState.get(ENABLED)) {
            world.setBlockState(pos, blockState.with(ENABLED, Boolean.valueOf(flag)), 4);
        }
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof CoffeeMachineBlockEntity) {
                ItemScatterer.spawn(world, pos, (CoffeeMachineBlockEntity)blockEntity);
                world.updateComparators(pos,this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    // ################################################# SHAPE & MODEL #################################################
    protected static void calculateShapes(Direction to, VoxelShape shape) {
        VoxelShape[] buffer = new VoxelShape[] { shape, VoxelShapes.empty() };

        int times = (to.getHorizontal() - Direction.NORTH.getHorizontal() + 4) % 4;
        for (int i = 0; i < times; i++) {
            buffer[0].forEachBox((minX, minY, minZ, maxX, maxY, maxZ) -> buffer[1] = VoxelShapes.union(buffer[1], VoxelShapes.cuboidUnchecked(1 - maxZ, minY, minX, 1 - minZ, maxY, maxX)));
            buffer[0] = buffer[1];
            buffer[1] = VoxelShapes.empty();
        }
        SHAPES.put(to, buffer[0]);
    }

    protected void runCalculation(VoxelShape shape) {
        for(Direction direction : Direction.values()) {
            calculateShapes(direction, shape);
        }
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPES.get(state.get(FACING));
    }

    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    // #################################################### GENERAL ####################################################

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new CoffeeMachineBlockEntity(pos, state);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            NamedScreenHandlerFactory screenHandlerFactory = state.createScreenHandlerFactory(world, pos);

            if (screenHandlerFactory != null) {
                player.openHandledScreen(screenHandlerFactory);
            }
        }

        return ActionResult.SUCCESS;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, RavenCoffeeBlockEntities.COFFEE_MACHINE, CoffeeMachineBlockEntity::tick);
    }
}
