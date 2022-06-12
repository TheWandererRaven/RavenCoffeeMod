package com.thewandererraven.ravencoffee.blocks;

import com.thewandererraven.ravencoffee.blocks.entities.CoffeeMachineBlockEntity;
import com.thewandererraven.ravencoffee.util.registries.BlockEntitiesRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class CoffeeMachineBlock extends BaseEntityBlock {
    private static final VoxelShape SHAPE = Stream.of(
            Block.box(1, 0, 1, 15, 1, 15),
            Block.box(1, 1, 6, 15, 5, 15),
            Block.box(1, 5, 6, 15, 12, 13),
            Block.box(1, 9, 1, 10, 12, 6),
            Block.box(2, 8, 1, 8, 9, 6),
            Block.box(3, 7, 2, 6, 8, 5),
            Block.box(1, 5, 13, 2, 12, 15),
            Block.box(14, 5, 13, 15, 12, 15),
            Block.box(2, 5, 13, 14, 11, 14),
            Block.box(2, 11, 13, 14, 12, 15),
            Block.box(2, 5, 14, 14, 11, 15),
            Block.box(3, 12, 4, 6, 15, 7),
            Block.box(2, 13, 3, 7, 15, 4),
            Block.box(2, 13, 7, 7, 15, 8),
            Block.box(6, 13, 4, 7, 15, 7),
            Block.box(2, 13, 4, 3, 15, 7),
            Block.box(3, 15, 4, 6, 16, 7)
            ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final Map<Direction, VoxelShape> SHAPES = new HashMap<Direction, VoxelShape>();
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public CoffeeMachineBlock() {
        super(Properties.of(Material.METAL)
                .strength(2.0f, 2.0f)
                .sound(SoundType.METAL)
        );
        this.registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.NORTH));
        runCalculation(SHAPE);
    }

    @Override
    public RenderShape getRenderShape(BlockState p_49232_) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new CoffeeMachineBlockEntity(pos, state);
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if(state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if(blockEntity instanceof CoffeeMachineBlockEntity) {
                ((CoffeeMachineBlockEntity) blockEntity).drops();
            }
        }
        super.onRemove(state, level, pos, newState, isMoving);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if(!level.isClientSide()) {
            BlockEntity entity = level.getBlockEntity(pos);
            if(entity instanceof CoffeeMachineBlockEntity) {
                NetworkHooks.openGui(((ServerPlayer) player), (CoffeeMachineBlockEntity)entity, pos);
            } else {
                throw new IllegalStateException("Our Container provider is missing!");
            }
        }
        return InteractionResult.sidedSuccess(level.isClientSide());
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        return createTickerHelper(blockEntityType, BlockEntitiesRegistry.COFFEE_MACHINE_BLOCK_ENTITY.get(), CoffeeMachineBlockEntity::tick);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Level level = context.getLevel();
        ItemStack itemstack = context.getItemInHand();
        CompoundTag compoundtag = itemstack.getTag();
        Player playerentity = context.getPlayer();
        boolean flag = false;
        // ============= pre 1.17.1
        //if (!world.isRemote && playerentity != null && compoundnbt != null && playerentity.canUseCommandBlock() && compoundnbt.contains("BlockEntityTag")) {
        if (level.isClientSide && playerentity != null && compoundtag != null && playerentity.canUseGameMasterBlocks() && compoundtag.contains("BlockEntityTag")) {
            CompoundTag compoundntagsub = compoundtag.getCompound("BlockEntityTag");
            if (compoundntagsub.contains("Book")) {
                flag = true;
            }
        }
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos,
                               CollisionContext context) {
        return SHAPES.get(state.getValue(FACING));
    }

    @SuppressWarnings("deprecation")
    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
    }

    @Override
    public BlockState rotate(BlockState state, LevelAccessor world, BlockPos pos, Rotation direction) {
        return state.setValue(FACING, direction.rotate(state.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING);
    }

    protected static void calculateShapes(Direction to, VoxelShape shape) {
        VoxelShape[] buffer = new VoxelShape[] { shape, Shapes.empty() };

        int times = (to.get2DDataValue() - Direction.NORTH.get2DDataValue() + 4) % 4;
        for (int i = 0; i < times; i++) {
            buffer[0].forAllBoxes((minX, minY, minZ, maxX, maxY, maxZ) -> buffer[1] = Shapes.or(buffer[1], Shapes.create(1 - maxZ, minY, minX, 1 - minZ, maxY, maxX)));
            buffer[0] = buffer[1];
            buffer[1] = Shapes.empty();
        }
        SHAPES.put(to, buffer[0]);
    }

    protected void runCalculation(VoxelShape shape) {
        for(Direction direction : Direction.values()) {
            calculateShapes(direction, shape);
        }
    }
}