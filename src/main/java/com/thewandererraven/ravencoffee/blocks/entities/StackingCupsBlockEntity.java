package com.thewandererraven.ravencoffee.blocks.entities;

import com.thewandererraven.ravencoffee.blocks.StackingCupsBlock;
import com.thewandererraven.ravencoffee.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class StackingCupsBlockEntity extends BlockEntity implements Container {
    public static final int CONTENTS_SLOT_COUNT = 9;
    public static final int SLOT_MAX_COUNT = 1;

    private NonNullList<ItemStack> items = NonNullList.withSize(CONTENTS_SLOT_COUNT, ItemStack.EMPTY);

    public StackingCupsBlockEntity(BlockPos p_155229_, BlockState p_155230_) {
        super(RavenCoffeeBlockEntities.STACKING_CUPS_BLOCK_ENTITY.get(), p_155229_, p_155230_);
    }

    public int getCount() {
        int count = 0;
        for (ItemStack item : items) {
            if (!item.isEmpty()) count++;
        }
        return count;
    }

    private void updateBlockCount() {
        this.level.setBlock(
                this.worldPosition,
                this.getBlockState().setValue(StackingCupsBlock.CUP_COUNT, this.getCount()),
                1
        );
    }

    // ================================================= CONTAINER =================================================

    public int getContainerSize() {
        return CONTENTS_SLOT_COUNT;
    }

    @Override
    public boolean isEmpty() {
        return this.getCount() == 0;
    }

    public void drops() {
        Containers.dropContents(this.level, this.worldPosition, this);
    }

    public boolean contains(ItemStack itemStack) {
        for (ItemStack item : items) {
            if (!itemStack.isEmpty())
                if(itemStack.is(item.getItem()))
                    return true;
        }
        return false;
    }

    @Override
    public boolean canPlaceItem(int slot, ItemStack itemStack) {
        return canPlaceItem(itemStack);
    }

    public boolean canPlaceItem(ItemStack itemStack) {
        return (this.contains(itemStack) && this.getCount() < 9) || (itemStack.is(ModTags.Items.CUPS) && isEmpty());
    }

    public boolean placeItem(ItemStack itemStack) {
        for(int i = 0; i < this.items.size(); i++) {
            ItemStack currentItem = this.items.get(i);
            if(currentItem.isEmpty()) {
                this.items.set(i, itemStack);
                updateBlockCount();
                return true;
            }
        }
        return false;
    }

    @Override
    public ItemStack getItem(int slot) {
        return this.items.get(slot);
    }

    @Override
    public ItemStack removeItem(int slot, int count) {
        ItemStack oldStack = this.items.get(slot);
        if(!oldStack.isEmpty()) {
            this.items.set(slot, ItemStack.EMPTY);
            updateBlockCount();
            return new ItemStack(oldStack.getItem(), count);
        }
        return ItemStack.EMPTY;
    }

    @Override
    public ItemStack removeItemNoUpdate(int slot) {
        ItemStack retVal = this.items.set(slot, ItemStack.EMPTY);
        updateBlockCount();
        return retVal;
    }

    @Override
    public void setItem(int slot, ItemStack itemStack) {
        this.items.set(slot, itemStack);
        updateBlockCount();
    }

    @Override
    public boolean stillValid(Player p_18946_) {
        return true;
    }

    // ================================================= DATA =================================================

    public void load(CompoundTag tag) {
        super.load(tag);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(tag, this.items);

    }

    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        ContainerHelper.saveAllItems(tag, this.items);
    }

    @Override
    public void clearContent() {

    }
}
