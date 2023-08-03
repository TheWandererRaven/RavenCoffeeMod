package com.thewandererraven.ravencoffee.items;

import com.thewandererraven.ravencoffee.Constants;
import com.thewandererraven.ravencoffee.blocks.StackingCupsBlock;
import com.thewandererraven.ravencoffee.blocks.entities.StackingCupsBlockEntity;
import com.thewandererraven.ravencoffee.util.ModTags;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class StackingCupsBlockItem extends BlockItem {
    public StackingCupsBlockItem(Block p_41579_, Item.Properties p_41580_) {
        super(p_41579_, p_41580_);
    }

    @Override
    public @NotNull String getDescriptionId() {
        return this.getOrCreateDescriptionId();
    }

    @Nullable
    protected BlockState getPlacementState(BlockPlaceContext p_40613_) {
        BlockState blockstate = ((StackingCupsBlock) this.getBlock()).getStateForPlacement(p_40613_);
        return blockstate != null && this.canPlace(p_40613_, blockstate) ? blockstate : null;
    }

    @Override
    protected boolean placeBlock(BlockPlaceContext p_40578_, BlockState p_40579_) {
        return p_40578_.getLevel().setBlock(p_40578_.getClickedPos(), p_40579_, 11);
    }

    @Override
    public InteractionResult place(BlockPlaceContext originalPlaceContext) {
        if (!originalPlaceContext.canPlace()) {
            Level contextLevel = originalPlaceContext.getLevel();
            if(contextLevel.getBlockEntity(originalPlaceContext.getClickedPos()) instanceof StackingCupsBlockEntity blockEntity) {
                if(!contextLevel.isClientSide) {
                    ItemStack itemInHand = originalPlaceContext.getItemInHand();
                    Player player = originalPlaceContext.getPlayer();
                    if (itemInHand.is(ModTags.Items.CUPS) && blockEntity.canPlaceItem(itemInHand)) {
                        ItemStack newStack = itemInHand.copy();
                        newStack.setCount(1);
                        blockEntity.placeItem(newStack);
                        if (player == null || !player.getAbilities().instabuild) {
                            itemInHand.shrink(1);
                        }
                    }
                }
                    return InteractionResult.sidedSuccess(contextLevel.isClientSide);
            } else
                return InteractionResult.FAIL;
        } else {
            BlockPlaceContext blockplacecontext = this.updatePlacementContext(originalPlaceContext);
            if (blockplacecontext == null) {
                return InteractionResult.FAIL;
            } else {
                BlockState blockstate = this.getPlacementState(blockplacecontext);
                if (blockstate == null) {
                    return InteractionResult.FAIL;
                } else if (!this.placeBlock(blockplacecontext, blockstate)) {
                    return InteractionResult.FAIL;
                } else {
                    BlockPos blockpos = blockplacecontext.getClickedPos();
                    Level level = blockplacecontext.getLevel();
                    Player player = blockplacecontext.getPlayer();
                    ItemStack itemstack = blockplacecontext.getItemInHand();
                    BlockState blockstate1 = level.getBlockState(blockpos);
                    if (blockstate1.is(blockstate.getBlock())) {
                        blockstate1 = this.updateBlockStateFromTag(blockpos, level, itemstack, blockstate1);
                        this.updateCustomBlockEntityTag(blockpos, level, player, itemstack, blockstate1);
                        blockstate1.getBlock().setPlacedBy(level, blockpos, blockstate1, player, itemstack);
                        if (player instanceof ServerPlayer) {
                            CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayer)player, blockpos, itemstack);
                        }
                    }

                    level.gameEvent(GameEvent.BLOCK_PLACE, blockpos, GameEvent.Context.of(player, blockstate1));
                    SoundType soundtype = blockstate1.getSoundType(level, blockpos, originalPlaceContext.getPlayer());
                    level.playSound(player, blockpos, this.getPlaceSound(blockstate1, level, blockpos, originalPlaceContext.getPlayer()), SoundSource.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
                    BlockEntity blockEntity = level.getBlockEntity(blockpos);
                    if(blockEntity instanceof StackingCupsBlockEntity) {
                        ItemStack newStack = itemstack.copy();
                        newStack.setCount(1);
                        ((StackingCupsBlockEntity) blockEntity).placeItem(newStack);
                    }
                    if (player == null || !player.getAbilities().instabuild) {
                        itemstack.shrink(1);
                    }

                    return InteractionResult.sidedSuccess(level.isClientSide);
                }
            }
        }
    }

    private BlockState updateBlockStateFromTag(BlockPos p_40603_, Level p_40604_, ItemStack p_40605_, BlockState p_40606_) {
        BlockState blockstate = p_40606_;
        CompoundTag compoundtag = p_40605_.getTag();
        if (compoundtag != null) {
            CompoundTag compoundtag1 = compoundtag.getCompound("BlockStateTag");
            StateDefinition<Block, BlockState> statedefinition = p_40606_.getBlock().getStateDefinition();

            for(String s : compoundtag1.getAllKeys()) {
                Property<?> property = statedefinition.getProperty(s);
                if (property != null) {
                    String s1 = compoundtag1.get(s).getAsString();
                    blockstate = updateState(blockstate, property, s1);
                }
            }
        }

        if (blockstate != p_40606_) {
            p_40604_.setBlock(p_40603_, blockstate, 2);
        }

        return blockstate;
    }

    private static <T extends Comparable<T>> BlockState updateState(BlockState p_40594_, Property<T> p_40595_, String p_40596_) {
        return p_40595_.getValue(p_40596_).map((p_40592_) -> {
            return p_40594_.setValue(p_40595_, p_40592_);
        }).orElse(p_40594_);
    }
    /*
    @Override
    public void fillItemCategory(CreativeModeTab p_41391_, NonNullList<ItemStack> p_41392_) {
        if (this.allowedIn(p_41391_)) {
            p_41392_.add(new ItemStack(this));
        }
    }

     */
}
