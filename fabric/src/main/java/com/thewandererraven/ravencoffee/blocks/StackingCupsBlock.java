package com.thewandererraven.ravencoffee.blocks;

import com.thewandererraven.ravenbrewscore.CupType;
import com.thewandererraven.ravencoffee.Constants;
import com.thewandererraven.ravencoffee.blocks.entitites.StackingCupsBlockEntity;
import com.thewandererraven.ravencoffee.util.RavenCoffeeTags;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.util.shape.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class StackingCupsBlock extends BlockWithEntity implements BlockEntityProvider {
    public static final VoxelShape SHAPE_0 = Block.createCuboidShape(0, 0, 0, 16, 16, 16);
    public static final VoxelShape SHAPE_1 = Block.createCuboidShape(6, 0, 6, 10, 5, 10);
    public static final VoxelShape SHAPE_2 = Block.createCuboidShape(3, 0, 3, 13, 5, 13);
    public static final VoxelShape SHAPE_3 = Block.createCuboidShape(2, 0, 3, 13, 5, 13);
    public static final VoxelShape SHAPE_4 = Block.createCuboidShape(1, 0, 3, 13, 5, 13);
    public static final VoxelShape SHAPE_5 = Block.createCuboidShape(1, 0, 5, 13, 10, 14);
    public static final VoxelShape SHAPE_6 = Block.createCuboidShape(1, 0, 3, 14, 10, 15);
    public static final VoxelShape SHAPE_7 = Block.createCuboidShape(1, 0, 2, 15, 10, 16);
    public static final VoxelShape SHAPE_8 = Block.createCuboidShape(1, 0, 2, 16, 10, 16);
    public static final VoxelShape SHAPE_9 = Block.createCuboidShape(1, 0, 2, 16, 10, 16);

    public static final IntProperty CUP_COUNT = IntProperty.of("cup_count", 0, 9);
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    public static final EnumProperty<CupType> CUP_TYPE = EnumProperty.of("cup_type", CupType.class);

    public StackingCupsBlock() {
        super(Settings
                .of(Material.STONE)
                .sounds(BlockSoundGroup.STONE)
        );
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new StackingCupsBlockEntity(pos, state);
    }

    // ================================================= BLOSCKSTATE =================================================

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        return this.getDefaultState()
                .with(FACING, context.getPlayerFacing().getOpposite());
    }

    @Override
    protected void appendProperties(StateManager.Builder<net.minecraft.block.Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(CUP_COUNT);
        builder.add(FACING);
        builder.add(CUP_TYPE);
    }

    // ============================================== GENERAL INTERACTION ==============================================
/*
    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack stack) {
        Constants.LOGGER.info("AFTER BREAK");
        player.incrementStat(Stats.MINED.getOrCreateStat(this));
        player.addExhaustion(0.005F);
        dropStacks(state, world, pos, blockEntity, player, stack);
    }
*/

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


    /*
    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if(state.getBlock() != newState.getBlock()) {
            if(level.getBlockEntity(pos) instanceof StackingCupsBlockEntity blockEntity) {
                blockEntity.drops();
            }
        }
        super.onRemove(state, level, pos, newState, isMoving);
    }

     */

    @Override
    public ActionResult onUse(BlockState blockState, World world, BlockPos blockPos, PlayerEntity player, Hand interactionHand, BlockHitResult blockHitResult) {
        if (!world.isClient()) {
            if (world.getBlockEntity(blockPos) instanceof StackingCupsBlockEntity entity) {
                ItemStack itemInHand = player.getStackInHand(interactionHand);
                if (itemInHand.isIn(RavenCoffeeTags.Items.CUPS) && entity.canPlaceItem(itemInHand)) {
                    ItemStack itemToInsert = itemInHand.copy();
                    itemToInsert.setCount(1);
                    entity.placeItem(itemToInsert);
                    if (!player.getAbilities().creativeMode) {
                        itemInHand.decrement(1);
                    }
                }
            }
        }
        return ActionResult.success(world.isClient());
    }

/*
    @Override
    public ItemStack getCloneItemStack(BlockGetter p_49823_, BlockPos p_49824_, BlockState p_49825_) {
        return new ItemStack(((StackingCupsBlockEntity) p_49823_.getBlockEntity(p_49824_)).getItem());
    }
*/
    // =============================================== RENDER & SHAPES ================================================


    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    public boolean hasSidedTransparency(BlockState state) {
        return true;
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return switch (state.get(CUP_COUNT)) {
            case (1) -> rotateShape(SHAPE_1, state.get(FACING));
            case (2) -> rotateShape(SHAPE_2, state.get(FACING));
            case (3) -> rotateShape(SHAPE_3, state.get(FACING));
            case (4) -> rotateShape(SHAPE_4, state.get(FACING));
            case (5) -> rotateShape(SHAPE_5, state.get(FACING));
            case (6) -> rotateShape(SHAPE_6, state.get(FACING));
            case (7) -> rotateShape(SHAPE_7, state.get(FACING));
            case (8) -> rotateShape(SHAPE_8, state.get(FACING));
            case (9) -> rotateShape(SHAPE_9, state.get(FACING));
            default -> rotateShape(SHAPE_0, state.get(FACING));
        };
    }

    protected static VoxelShape rotateShape(VoxelShape shape, Direction to) {
        VoxelShape[] buffer = new VoxelShape[]{shape, VoxelShapes.empty()};

        int times = (to.getHorizontal() - Direction.NORTH.getHorizontal() + 4) % 4;
        for (int i = 0; i < times; i++) {
            buffer[0].forEachBox((minX, minY, minZ, maxX, maxY, maxZ) -> buffer[1] = VoxelShapes.union(buffer[1], VoxelShapes.cuboid(1 - maxZ, minY, minX, 1 - minZ, maxY, maxX)));
            buffer[0] = buffer[1];
            buffer[1] = VoxelShapes.empty();
        }
        return buffer[0];
    }
    /*
    protected static VoxelShape rotateShape(VoxelShape shape, Direction to) {
        VoxelShape[] buffer = new VoxelShape[]{shape, Shapes.empty()};

        int times = (to.get2DDataValue() - Direction.NORTH.get2DDataValue() + 4) % 4;
        for (int i = 0; i < times; i++) {
            buffer[0].forAllBoxes((minX, minY, minZ, maxX, maxY, maxZ) -> buffer[1] = Shapes.or(buffer[1], Shapes.create(1 - maxZ, minY, minX, 1 - minZ, maxY, maxX)));
            buffer[0] = buffer[1];
            buffer[1] = Shapes.empty();
        }
        return buffer[0];
    }

 */
    /*
    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(CUP_COUNT)) {
            case (1) -> rotateShape(SHAPE_1, state.getValue(FACING));
            case (2) -> rotateShape(SHAPE_2, state.getValue(FACING));
            case (3) -> rotateShape(SHAPE_3, state.getValue(FACING));
            case (4) -> rotateShape(SHAPE_4, state.getValue(FACING));
            case (5) -> rotateShape(SHAPE_5, state.getValue(FACING));
            case (6) -> rotateShape(SHAPE_6, state.getValue(FACING));
            case (7) -> rotateShape(SHAPE_7, state.getValue(FACING));
            case (8) -> rotateShape(SHAPE_8, state.getValue(FACING));
            case (9) -> rotateShape(SHAPE_9, state.getValue(FACING));
            default -> rotateShape(SHAPE_0, state.getValue(FACING));
        };
    }
     */

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Constants.LOGGER.debug("CLONE");
        return super.clone();
    }
}
