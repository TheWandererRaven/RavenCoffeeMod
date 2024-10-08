package com.thewandererraven.ravencoffee.items;

import com.thewandererraven.ravencoffee.blocks.StackingCupsBlock;
import com.thewandererraven.ravencoffee.blocks.entitites.StackingCupsBlockEntity;
import com.thewandererraven.ravencoffee.util.RavenCoffeeTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class StackingCupsBlockItem extends BlockItem {
    public StackingCupsBlockItem(Block p_41579_, Settings p_41580_) {
        super(p_41579_, p_41580_);
    }

    public String getTranslationKey() {
        return super.getOrCreateTranslationKey();
    }

    @Nullable
    @Override
    protected BlockState getPlacementState(ItemPlacementContext context) {
        BlockState blockstate = ((StackingCupsBlock) this.getBlock()).getPlacementState(context);
        return blockstate != null && this.canPlace(context, blockstate) ? blockstate : null;
    }

    @Override
    public ActionResult place(ItemPlacementContext context) {
        if (!context.canPlace()) {
            World contextWorld = context.getWorld();
            if(contextWorld.getBlockEntity(context.getBlockPos()) instanceof StackingCupsBlockEntity blockEntity) {
                if(!contextWorld.isClient) {
                    ItemStack itemInHand = context.getStack();
                    PlayerEntity player = context.getPlayer();
                    if (itemInHand.isIn(RavenCoffeeTags.Items.CUPS) && blockEntity.canPlaceItem(itemInHand)) {
                        ItemStack newStack = itemInHand.copy();
                        newStack.setCount(1);
                        blockEntity.placeItem(newStack);
                        if (player == null || !player.getAbilities().creativeMode) {
                            itemInHand.decrement(1);
                        }
                    }
                }
                    return ActionResult.success(contextWorld.isClient);
            } else
                return ActionResult.FAIL;
        } else {
            ItemPlacementContext blockplacecontext = this.getPlacementContext(context);
            if (blockplacecontext == null) {
                return ActionResult.FAIL;
            } else {
                BlockState blockstate = this.getPlacementState(blockplacecontext);
                if (blockstate == null) {
                    return ActionResult.FAIL;
                } else if (!this.place(blockplacecontext, blockstate)) {
                    return ActionResult.FAIL;
                } else {
                    BlockPos blockpos = blockplacecontext.getBlockPos();
                    World world = blockplacecontext.getWorld();
                    PlayerEntity player = blockplacecontext.getPlayer();
                    ItemStack itemstack = blockplacecontext.getStack();
                    BlockState blockstate1 = world.getBlockState(blockpos);
                    if (blockstate1.isOf(blockstate.getBlock())) {
                        //blockstate1 = this.updateBlockStateFromTag(blockpos, world, itemstack, blockstate1);
                        //this.updateCustomBlockEntityTag(blockpos, world, player, itemstack, blockstate1);
                        blockstate1.getBlock().onPlaced(world, blockpos, blockstate1, player, itemstack);
                        /*
                        if (player instanceof ServerPlayerEntity) {
                            CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayer)player, blockpos, itemstack);
                        }
                         */
                    }

                    //level.gameEvent(GameEvent.BLOCK_PLACE, blockpos, GameEvent.Context.of(player, blockstate1));
                    BlockSoundGroup soundtype = blockstate1.getSoundGroup();
                    world.playSound(player, blockpos, this.getPlaceSound(blockstate1), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
                    BlockEntity blockEntity = world.getBlockEntity(blockpos);
                    if(blockEntity instanceof StackingCupsBlockEntity) {
                        ItemStack newStack = itemstack.copy();
                        newStack.setCount(1);
                        ((StackingCupsBlockEntity) blockEntity).placeItem(newStack);
                    }
                    if (player == null || !player.getAbilities().creativeMode) {
                        itemstack.decrement(1);
                    }

                    return ActionResult.success(world.isClient);
                }
            }
        }
    }

}
