package com.thewandererraven.ravencoffee.blocks;

import com.thewandererraven.ravencoffee.blocks.entities.StackingCupsBlockEntity;
import com.thewandererraven.ravencoffee.util.CupType;
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
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class StackingCupsBlock extends BaseEntityBlock {
    public static final VoxelShape SHAPE_0 = Block.box(0, 0, 0, 16, 16, 16);
    public static final VoxelShape SHAPE_1 = Block.box(5, 0, 5, 8, 4, 8);
    public static final VoxelShape SHAPE_2 = Block.box(3, 0, 3, 12, 4, 12);
    public static final VoxelShape SHAPE_3 = Block.box(2, 0, 4, 12, 4, 12);
    public static final VoxelShape SHAPE_4 = Block.box(3, 0, 5, 13, 4, 13);
    public static final VoxelShape SHAPE_5 = Block.box(3, 0, 4, 12, 8, 13);
    public static final VoxelShape SHAPE_6 = Block.box(2, 0, 4, 12, 8, 14);
    public static final VoxelShape SHAPE_7 = Block.box(1, 0, 3, 12, 8, 14);
    public static final VoxelShape SHAPE_8 = Block.box(1, 0, 2, 13, 8, 14);
    public static final VoxelShape SHAPE_9 = Block.box(1, 0, 3, 14, 8, 14);

    public static final IntegerProperty CUP_COUNT = IntegerProperty.create("cup_count", 0, 9);
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final EnumProperty<CupType> CUP_TYPE = EnumProperty.create("cup_type", CupType.class);

    public StackingCupsBlock() {
        super(Properties
                .of(Material.STONE)
                .sound(SoundType.STONE)
        );
        this.registerDefaultState(this.getStateDefinition().any()
                .setValue(FACING, Direction.NORTH)
                .setValue(CUP_COUNT, 1)
                .setValue(CUP_TYPE, CupType.COFFEE_MUG)
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
        builder.add(CUP_TYPE);
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
                    if (!player.getAbilities().instabuild) {
                        itemInHand.shrink(1);
                    }
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

    protected static VoxelShape rotateShape(VoxelShape shape, Direction to) {
        VoxelShape[] buffer = new VoxelShape[] { shape, Shapes.empty() };

        int times = (to.get2DDataValue() - Direction.NORTH.get2DDataValue() + 4) % 4;
        for (int i = 0; i < times; i++) {
            buffer[0].forAllBoxes((minX, minY, minZ, maxX, maxY, maxZ) -> buffer[1] = Shapes.or(buffer[1], Shapes.create(1 - maxZ, minY, minX, 1 - minZ, maxY, maxX)));
            buffer[0] = buffer[1];
            buffer[1] = Shapes.empty();
        }
        return buffer[0];
    }
}
