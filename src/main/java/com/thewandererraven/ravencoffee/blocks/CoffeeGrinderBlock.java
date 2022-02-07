package com.thewandererraven.ravencoffee.blocks;

import com.thewandererraven.ravencoffee.containers.CoffeeGrinderContainer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.fmllegacy.network.NetworkHooks;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class CoffeeGrinderBlock extends BaseEntityBlock {
    public static final VoxelShape SHAPE = Stream.of(
            Block.box(4.5, 0, 4.5, 11.5, 0.5, 11.5),
            Block.box(5, 0.5, 5, 11, 5.5, 11),
            Block.box(4.5, 5.5, 4.5, 11.5, 6, 11.5),
            Block.box(6, 6, 6, 10, 7, 10),
            Block.box(7.5, 7, 7.5, 8.5, 9, 8.5),
            Block.box(4.5, 9, 7.5, 8.5, 10, 8.5),
            Block.box(4.75, 10, 7.75, 5.25, 10.5, 8.25),
            Block.box(4.625, 10.5, 7.625, 5.375, 11.25, 8.375),
            Block.box(6, 0.5, 4.75, 10, 2.5, 5.75)
    ).reduce((v1, v2) -> {return Shapes.join(v1, v2, BooleanOp.OR);})
            .orElse(Block.box(4.625, 10.5, 7.625, 5.375, 11.25, 8.375));
    public static final Map<Direction, VoxelShape> SHAPES = new HashMap<Direction, VoxelShape>();
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    private static final Component CONTAINER_NAME = new TranslatableComponent("container.coffee_grinder");

    public CoffeeGrinderBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.NORTH));
        runCalculation(SHAPE);
    }

    @Override
    public RenderShape getRenderShape(BlockState p_49232_) {
        return RenderShape.MODEL;
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
    //                       DIRECTIONAL

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

    //                    ENDS DIRECTIONAL

//    @Override
//    public BlockEntity createTileEntity(BlockState state, BlockGetter world) {
//        Blocks.GRINDSTONE
//        return createNewTileEntity(world);
//    }
//
//    @Override
//    public boolean hasTileEntity(BlockState state) {
//        return true;
//    }

    @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        if (worldIn.isClientSide) return InteractionResult.SUCCESS; // on client side, don't do anything

        MenuProvider namedContainerProvider = this.getMenuProvider(state, worldIn, pos);
        if (namedContainerProvider != null) {
            if (!(player instanceof ServerPlayer serverPlayerEntity)) return InteractionResult.FAIL;  // should always be true, but just in case...
            NetworkHooks.openGui(serverPlayerEntity, namedContainerProvider, (packetBuffer)->{});
            // (packetBuffer)->{} is just a do-nothing because we have no extra data to send
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public MenuProvider getMenuProvider(BlockState state, Level worldIn, BlockPos pos) {
        return new SimpleMenuProvider((id, inventory, player) -> {
            return new CoffeeGrinderContainer(id, inventory, ContainerLevelAccess.create(worldIn, pos));
        }, new TranslatableComponent("container.ravencoffee.coffee_grinder_registry_name"));
    }

//    @Nullable
//    @Override
//    public TileEntity createNewTileEntity(IBlockReader worldIn) {
//        return null;
//    }

    @Override
    public BlockEntity newBlockEntity(BlockPos p_153215_, BlockState p_153216_) {
        return null;
    }
}
