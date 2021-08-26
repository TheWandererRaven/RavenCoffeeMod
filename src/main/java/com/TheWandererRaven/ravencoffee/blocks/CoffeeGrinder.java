package com.TheWandererRaven.ravencoffee.blocks;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import java.util.stream.Stream;

public class CoffeeGrinder extends BaseHorizontalBlock {

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

    public CoffeeGrinder(Properties builder) {
        super(builder);
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
}
