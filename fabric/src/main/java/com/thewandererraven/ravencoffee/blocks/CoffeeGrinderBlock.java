package com.thewandererraven.ravencoffee.blocks;

import com.thewandererraven.ravencoffee.Constants;
import com.thewandererraven.ravencoffee.screens.handlers.CoffeeGrinderScreenHandler;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
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

public class CoffeeGrinderBlock extends Block {
    private static final Text TITLE = Text.translatable("container." + Constants.MOD_ID + ".coffee_grinder_registry_name");
    public static final VoxelShape SHAPE = VoxelShapes.union(
            Block.createCuboidShape(4.5, 0, 4.5, 11.5, 0.5, 11.5),
            Block.createCuboidShape(5, 0.5, 5, 11, 5.5, 11),
            Block.createCuboidShape(4.5, 5.5, 4.5, 11.5, 6, 11.5),
            Block.createCuboidShape(6, 6, 6, 10, 7, 10),
            Block.createCuboidShape(7.5, 7, 7.5, 8.5, 9, 8.5),
            Block.createCuboidShape(4.5, 9, 7.5, 8.5, 10, 8.5),
            Block.createCuboidShape(4.75, 10, 7.75, 5.25, 10.5, 8.25),
            Block.createCuboidShape(4.625, 10.5, 7.625, 5.375, 11.25, 8.375),
            Block.createCuboidShape(6, 0.5, 4.75, 10, 2.5, 5.75)
    );
    public static final Map<Direction, VoxelShape> SHAPES = new HashMap<Direction, VoxelShape>();
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;

    public CoffeeGrinderBlock(Settings settings) {
        super(settings);
        this.setDefaultState((this.stateManager.getDefaultState()).with(FACING, Direction.NORTH));
        this.runCalculation(SHAPE);
    }

    // ################################################## BLOCKSTATE ##################################################
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return super.getPlacementState(ctx).with(FACING, ctx.getPlayerFacing().getOpposite());
    }

    // #################################################### SHAPES ####################################################
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

    // #################################################### MODEL ####################################################
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPES.get(state.get(FACING));
    }

    // ##################################################### OTHER #####################################################
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            player.openHandledScreen(state.createScreenHandlerFactory(world, pos));
        }
        return ActionResult.success(world.isClient);
    }

    public NamedScreenHandlerFactory createScreenHandlerFactory(BlockState state, World world, BlockPos pos) {
        return new SimpleNamedScreenHandlerFactory((syncId, inventory, player) -> {
            return new CoffeeGrinderScreenHandler(syncId, inventory);
        }, TITLE);
    }
}
