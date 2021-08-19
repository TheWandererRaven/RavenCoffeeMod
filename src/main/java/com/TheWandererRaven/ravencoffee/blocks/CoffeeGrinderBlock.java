package com.TheWandererRaven.ravencoffee.blocks;

import com.TheWandererRaven.ravencoffee.RavenCoffee;
import com.TheWandererRaven.ravencoffee.containers.CoffeeGrinderContainer;
import com.TheWandererRaven.ravencoffee.tileEntity.CoffeeGrinderTileEntity;
import com.TheWandererRaven.ravencoffee.util.registries.RecipesRegistry;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.inventory.container.WorkbenchContainer;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import org.apache.logging.log4j.LogManager;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class CoffeeGrinderBlock extends ContainerBlock {
    public static final VoxelShape SHAPE = Stream.of(
            Block.makeCuboidShape(4.5, 0, 4.5, 11.5, 0.5, 11.5),
            Block.makeCuboidShape(5, 0.5, 5, 11, 5.5, 11),
            Block.makeCuboidShape(4.5, 5.5, 4.5, 11.5, 6, 11.5),
            Block.makeCuboidShape(6, 6, 6, 10, 7, 10),
            Block.makeCuboidShape(7.5, 7, 7.5, 8.5, 9, 8.5),
            Block.makeCuboidShape(4.5, 9, 7.5, 8.5, 10, 8.5),
            Block.makeCuboidShape(4.75, 10, 7.75, 5.25, 10.5, 8.25),
            Block.makeCuboidShape(4.625, 10.5, 7.625, 5.375, 11.25, 8.375),
            Block.makeCuboidShape(6, 0.5, 4.75, 10, 2.5, 5.75)
    ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);})
            .orElse(Block.makeCuboidShape(4.625, 10.5, 7.625, 5.375, 11.25, 8.375));
    public static final Map<Direction, VoxelShape> SHAPES = new HashMap<Direction, VoxelShape>();
    public static final DirectionProperty HORIZONTAL_FACING = BlockStateProperties.HORIZONTAL_FACING;
    private static final ITextComponent CONTAINER_NAME = new TranslationTextComponent("container.coffee_grinder");

    public CoffeeGrinderBlock(AbstractBlock.Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(HORIZONTAL_FACING, Direction.NORTH));
        runCalculation(SHAPE);
    }

    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        World world = context.getWorld();
        ItemStack itemstack = context.getItem();
        CompoundNBT compoundnbt = itemstack.getTag();
        PlayerEntity playerentity = context.getPlayer();
        boolean flag = false;
        if (!world.isRemote && playerentity != null && compoundnbt != null && playerentity.canUseCommandBlock() && compoundnbt.contains("BlockEntityTag")) {
            CompoundNBT compoundnbt1 = compoundnbt.getCompound("BlockEntityTag");
            if (compoundnbt1.contains("Book")) {
                flag = true;
            }
        }

        return this.getDefaultState().with(HORIZONTAL_FACING, context.getPlacementHorizontalFacing().getOpposite());
    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPES.get(state.get(HORIZONTAL_FACING));
    }
    //                       DIRECTIONAL

    @SuppressWarnings("deprecation")
    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.toRotation(state.get(HORIZONTAL_FACING)));
    }

    @Override
    public BlockState rotate(BlockState state, IWorld world, BlockPos pos, Rotation direction) {
        return state.with(HORIZONTAL_FACING, direction.rotate(state.get(HORIZONTAL_FACING)));
    }

    //@Nullable
    //@Override
    //public BlockState getStateForPlacement(BlockItemUseContext context) {
    //    return this.getDefaultState().with(HORIZONTAL_FACING, context.getPlacementHorizontalFacing().getOpposite());
    //}

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(HORIZONTAL_FACING);
    }

    protected static void calculateShapes(Direction to, VoxelShape shape) {
        VoxelShape[] buffer = new VoxelShape[] { shape, VoxelShapes.empty() };

        int times = (to.getHorizontalIndex() - Direction.NORTH.getHorizontalIndex() + 4) % 4;
        for (int i = 0; i < times; i++) {
            buffer[0].forEachBox((minX, minY, minZ, maxX, maxY, maxZ) -> buffer[1] = VoxelShapes.or(buffer[1], VoxelShapes.create(1 - maxZ, minY, minX, 1 - minZ, maxY, maxX)));
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

    //                    ENDS DIRECTIONAL

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return createNewTileEntity(world);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(IBlockReader worldIn) {
        return new CoffeeGrinderTileEntity();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (worldIn.isRemote) return ActionResultType.SUCCESS; // on client side, don't do anything

        INamedContainerProvider namedContainerProvider = this.getContainer(state, worldIn, pos);
        if (namedContainerProvider != null) {
            if (!(player instanceof ServerPlayerEntity)) return ActionResultType.FAIL;  // should always be true, but just in case...
            ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity)player;
            NetworkHooks.openGui(serverPlayerEntity, namedContainerProvider, (packetBuffer)->{});
            // (packetBuffer)->{} is just a do-nothing because we have no extra data to send
        }
        return ActionResultType.SUCCESS;
    }

    @Override
    public INamedContainerProvider getContainer(BlockState state, World worldIn, BlockPos pos) {
        return new SimpleNamedContainerProvider((id, inventory, player) -> {
            return new CoffeeGrinderContainer(id, inventory, IWorldPosCallable.of(worldIn, pos));
        }, CONTAINER_NAME);
    }
    // This is where you can do something when the block is broken. In this case drop the inventory's contents
    // Code is copied directly from vanilla eg ChestBlock, CampfireBlock
    @Override
    public void onReplaced(BlockState state, World world, BlockPos blockPos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            TileEntity tileentity = world.getTileEntity(blockPos);
            if (tileentity instanceof CoffeeGrinderTileEntity) {
                CoffeeGrinderTileEntity tileEntityFurnace = (CoffeeGrinderTileEntity) tileentity;
                tileEntityFurnace.dropAllContents(world, blockPos);
            }
//      worldIn.updateComparatorOutputLevel(pos, this);  if the inventory is used to set redstone power for comparators
            super.onReplaced(state, world, blockPos, newState, isMoving);  // call it last, because it removes the TileEntity
        }
    }
}
