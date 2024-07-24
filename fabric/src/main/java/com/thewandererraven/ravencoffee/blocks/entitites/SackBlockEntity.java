package com.thewandererraven.ravencoffee.blocks.entitites;

import com.thewandererraven.ravencoffee.Constants;
import com.thewandererraven.ravencoffee.blocks.RavenCoffeeBlocks;
import com.thewandererraven.ravencoffee.blocks.SackBlock;
import com.thewandererraven.ravencoffee.screens.handlers.SackScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;

public class SackBlockEntity extends LootableContainerBlockEntity implements Inventory {
    public static final int CONTENTS_SLOT_COUNT = 4;
    public static final int CONTENTS_FIRST_SLOT_INDEX = 0;

    private final int[] SLOTS_FOR_HOPPER = new int[CONTENTS_SLOT_COUNT];

    private DefaultedList<ItemStack> inventory = DefaultedList.ofSize(CONTENTS_SLOT_COUNT, ItemStack.EMPTY);

    public SackBlockEntity(BlockPos p_155229_, BlockState p_155230_) {
        super(RavenCoffeeBlockEntities.SACK_BLOCK_ENTITY, p_155229_, p_155230_);

        for(int i = 0; i < CONTENTS_SLOT_COUNT; i++) {
            SLOTS_FOR_HOPPER[i] = i;
        }
    }

    public Item getSackItem() {
        for(ItemStack stack : this.inventory)
            if(!stack.isEmpty()) return stack.getItem();
        return ItemStack.EMPTY.getItem();
    }

    public ItemStack getSackItemStack() {
        for(ItemStack stack : this.inventory)
            if(!stack.isEmpty()) return stack;
        return ItemStack.EMPTY;
    }

    // ================================================= FULLNESS =================================================

    private int getFullnessPercentage() {
        int maxStackSize = 0;
        int totalCount = 0;
        for(ItemStack stack : this.inventory)
            if(!stack.isEmpty()) {
                if(maxStackSize <= 0) maxStackSize = stack.getMaxCount();
                totalCount += stack.getCount();
            }
        if(maxStackSize == 0 || totalCount == 0) return 0;
        return (totalCount * 100) / (maxStackSize * CONTENTS_SLOT_COUNT);
    }

    private void updateFullness() {
        this.world.setBlockState(
                this.pos,
                this.getCachedState().with(SackBlock.FULLNESS, SackBlock.getFullnessState(getFullnessPercentage())),
                3
                );
    }

    // ================================================= INVENTORY =================================================

    @Override
    public int size() {
        return CONTENTS_SLOT_COUNT;
    }

    @Override
    public boolean isEmpty() {
        for(ItemStack stack : this.inventory)
            if(!stack.isEmpty()) return false;
        return true;
    }
/*
    @Override
    public boolean isEmpty() {
        return this.getCount() == 0;
    }
*/
    @Override
    public ItemStack getStack(int slot) {
        return this.inventory.get(slot);
    }

    @Override
    public ItemStack removeStack(int slot, int amount) {
        ItemStack oldStack = this.inventory.get(slot);
        if(oldStack.getCount() > amount)
            this.inventory.set(slot, duplicateItemWithCount(oldStack, oldStack.getCount() - amount));
        else
            this.inventory.set(slot, ItemStack.EMPTY);
        this.markDirty();
        updateFullness();
        return duplicateItemWithCount(oldStack, amount);
    }
    @Override
    public ItemStack removeStack(int slot) {
        ItemStack retStack = Inventories.removeStack(this.inventory, slot);
        this.markDirty();
        return retStack;
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        this.inventory.set(slot, stack);
        updateFullness();
    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        return true;
    }

    @Override
    public void clear() {
        this.inventory.clear();
    }

    @Override
    protected DefaultedList<ItemStack> getInvStackList() {
        return this.inventory;
    }

    @Override
    protected void setInvStackList(DefaultedList<ItemStack> list) {
        this.inventory = list;
        updateFullness();
    }

    public boolean isFull() {
        for(ItemStack stack : this.inventory)
            if (stack.getCount() < stack.getItem().getMaxCount()) return false;
        return true;
    }

    public boolean canPlaceItem(int slot, ItemStack itemStack) {
        ItemStack itemInSlot = this.inventory.get(slot);
        return itemInSlot.isOf(itemStack.getItem()) && itemInSlot.getTranslationKey().equals(itemStack.getTranslationKey());
    }

    public boolean canPlaceItem(ItemStack itemStack) {
        ItemStack sackItemStack = getSackItemStack();
        return isEmpty() || (
                itemStack.isOf(sackItemStack.getItem()) &&
                itemStack.getTranslationKey().equals(sackItemStack.getTranslationKey()) &&
                        !isFull()
        );
    }

    public boolean insertItem(ItemStack itemStack) {
        int firstEmptySlot = -1;
        for(int i = 0; i < this.inventory.size(); i++) {
            ItemStack currentItem = this.inventory.get(i);
            if(!currentItem.equals(ItemStack.EMPTY)) {
                if((currentItem.getCount() + itemStack.getCount()) <= currentItem.getMaxCount()) {
                    currentItem.setCount(currentItem.getCount() + itemStack.getCount());
                    updateFullness();
                    this.markDirty();
                    return true;
                }
            } else if(firstEmptySlot < 0) {
                firstEmptySlot = i;
            }
        }
        if(firstEmptySlot >= 0) {
            this.inventory.set(firstEmptySlot, itemStack.copy());
            updateFullness();
            this.markDirty();
            return true;
        }
        return false;
    }

    private static ItemStack duplicateItemWithCount(ItemStack originalStack, int newCount) {
        ItemStack newStack = originalStack.copy();
        newStack.setCount(newCount);
        return newStack;
    }

    public ItemStack grabItem() {
        for(int i = 0; i < this.inventory.size(); i++) {
            if(!this.inventory.get(i).isEmpty()) return this.removeStack(i, 1);
        }
        return ItemStack.EMPTY;
    }

    // ================================================= DISPLAY =================================================

    @Override
    protected Text getContainerName() {
        return Text.translatable(RavenCoffeeBlocks.SACK_BLOCK.getTranslationKey());
    }

    @Override
    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return new SackScreenHandler(syncId, playerInventory, this);
    }

    // ================================================= DATA =================================================
/*
    public void load(CompoundTag tag) {
        super.load(tag);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(tag, this.items);

    }

    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        ContainerHelper.saveAllItems(tag, this.items);
    }
*/

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
    }

    // ================================================= WORLDLY CONTAINER =================================================
/*
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

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, net.minecraft.world.entity.player.Inventory inventory, Player player) {
        return null;
    }

 */
}
