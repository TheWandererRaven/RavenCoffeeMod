package com.thewandererraven.ravencoffee.blocks.entities;

import com.thewandererraven.ravencoffee.Constants;
import com.thewandererraven.ravencoffee.blocks.SackBlock;
import com.thewandererraven.ravencoffee.containers.SackMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SackBlockEntity extends BlockEntity implements MenuProvider, Container, WorldlyContainer {
    public static final int CONTENTS_SLOT_COUNT = 4;
    public static final int CONTENTS_FIRST_SLOT_INDEX = 0;

    private final int[] SLOTS_FOR_HOPPER = new int[CONTENTS_SLOT_COUNT];

    private NonNullList<ItemStack> items = NonNullList.withSize(CONTENTS_SLOT_COUNT, ItemStack.EMPTY);

    public SackBlockEntity(BlockPos p_155229_, BlockState p_155230_) {
        super(RavenCoffeeBlockEntities.SACK_BLOCK_ENTITY.get(), p_155229_, p_155230_);

        for(int i = 0; i < CONTENTS_SLOT_COUNT; i++) {
            SLOTS_FOR_HOPPER[i] = i;
        }
    }

    public Item getSackItem() {
        for(ItemStack stack : this.items)
            if(!stack.isEmpty()) return stack.getItem();
        return ItemStack.EMPTY.getItem();
    }

    public ItemStack getSackItemStack() {
        for(ItemStack stack : this.items)
            if(!stack.isEmpty()) return stack;
        return ItemStack.EMPTY;
    }

    // ================================================= FULLNESS =================================================

    private int getFullnessPercentage() {
        int maxStackSize = 0;
        int totalCount = 0;
        for(ItemStack stack : this.items)
            if(!stack.isEmpty()) {
                if(maxStackSize <= 0) maxStackSize = stack.getMaxStackSize();
                totalCount += stack.getCount();
            }
        if(maxStackSize == 0 || totalCount == 0) return 0;
        return (totalCount * 100) / (maxStackSize * CONTENTS_SLOT_COUNT);
    }

    private void updateFullness() {
        this.level.setBlock(
                this.worldPosition,
                this.getBlockState().setValue(SackBlock.FULLNESS, SackBlock.getFullnessState(getFullnessPercentage())),
                3
                );
    }

    // ================================================= CONTAINER =================================================

    public int getContainerSize() {
        return CONTENTS_SLOT_COUNT;
    }

    @Override
    public boolean isEmpty() {
        for(ItemStack stack : this.items)
            if(!stack.isEmpty()) return false;
        return true;
    }

    public boolean isFull() {
        for(ItemStack stack : this.items)
            if (stack.getCount() < stack.getItem().getMaxStackSize(stack)) return false;
        return true;
    }

    public void drops() {
        Containers.dropContents(this.level, this.worldPosition, this);
    }

    @Override
    public boolean canPlaceItem(int slot, ItemStack itemStack) {
        ItemStack itemInSlot = this.items.get(slot);
        return itemInSlot.is(itemStack.getItem()) && itemInSlot.getDisplayName().equals(itemStack.getDisplayName());
    }

    public boolean canPlaceItem(ItemStack itemStack) {
        ItemStack sackItemStack = getSackItemStack();
        return isEmpty() || (
                itemStack.is(sackItemStack.getItem()) &&
                itemStack.getDisplayName().getContents().equals(sackItemStack.getDisplayName().getContents()) &&
                        !isFull()
        );
    }

    public boolean insertItem(ItemStack itemStack) {
        int firstEmptySlot = -1;
        for(int i = 0; i < this.items.size(); i++) {
            ItemStack currentItem = this.items.get(i);
            if(!currentItem.equals(ItemStack.EMPTY)) {
                if((currentItem.getCount() + itemStack.getCount()) <= currentItem.getMaxStackSize()) {
                    currentItem.setCount(currentItem.getCount() + itemStack.getCount());
                    updateFullness();
                    return true;
                }
            } else
                firstEmptySlot = i;
        }
        if(firstEmptySlot >= 0) {
            this.items.set(firstEmptySlot, itemStack.copy());
            updateFullness();
            return true;
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
        if(oldStack.getCount() > count)
            this.items.set(slot, duplicateItemWithCount(oldStack, oldStack.getCount() - count));
        else
            this.items.set(slot, ItemStack.EMPTY);
        updateFullness();
        return duplicateItemWithCount(oldStack, count);
    }

    private static ItemStack duplicateItemWithCount(ItemStack originalStack, int newCount) {
        ItemStack newStack = originalStack.copy();
        newStack.setCount(newCount);
        return newStack;
    }

    public ItemStack grabItem() {
        for(int i = 0; i < this.items.size(); i++) {
            if(!this.items.get(i).isEmpty()) return this.removeItem(i, 1);
        }
        return ItemStack.EMPTY;
    }

    @Override
    public ItemStack removeItemNoUpdate(int slot) {
        ItemStack retVal = this.items.set(slot, ItemStack.EMPTY);
        updateFullness();
        return retVal;
    }

    @Override
    public void setItem(int slot, ItemStack itemStack) {
        this.items.set(slot, itemStack);
        updateFullness();
    }

    @Override
    public boolean stillValid(Player p_18946_) {
        return true;
    }

    // ================================================= DISPLAY =================================================

    protected Component getDefaultName() {
        return Component.translatable(String.format("container.%s.sack", Constants.MOD_ID));
    }

    @Override
    public Component getDisplayName() {
        return this.getDefaultName();
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, @NotNull Inventory inventory, @NotNull Player player) {
        return new SackMenu(containerId, inventory, this);
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

    // ================================================= WORLDLY CONTAINER =================================================

    @Override
    public int[] getSlotsForFace(Direction direction) {
        return SLOTS_FOR_HOPPER;
    }

    @Override
    public boolean canPlaceItemThroughFace(int slot, ItemStack itemStack, @Nullable Direction direction) {
        return direction.equals(Direction.UP);
    }

    @Override
    public boolean canTakeItemThroughFace(int slot, ItemStack itemStack, Direction direction) {
        return false;
    }
}
