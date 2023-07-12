package com.thewandererraven.ravencoffee.blocks.entitites;

import com.thewandererraven.ravenbrewscore.CupType;
import com.thewandererraven.ravencoffee.Constants;
import com.thewandererraven.ravencoffee.blocks.StackingCupsBlock;
import com.thewandererraven.ravencoffee.util.ModTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;

import java.util.Arrays;
import java.util.Set;

public class StackingCupsBlockEntity extends LootableContainerBlockEntity implements Inventory {
    public static final int CONTENTS_SLOT_COUNT = 9;
    public static final int SLOT_MAX_COUNT = 1;

    private DefaultedList<ItemStack> inventory = DefaultedList.ofSize(CONTENTS_SLOT_COUNT, ItemStack.EMPTY);

    public StackingCupsBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(RavenCoffeeBlockEntities.STACKING_CUPS_BLOCK_ENTITY, blockPos, blockState);
    }

    public int getCount() {
        int count = 0;
        for (ItemStack item : inventory) {
            if (!item.isEmpty()) count++;
        }
        return count;
    }

    private void updateBlockCount() {

        this.world.setBlockState(
                this.pos,
                this.getWorld().getBlockState(this.pos).with(StackingCupsBlock.CUP_COUNT, this.getCount())
        );
    }

    // ================================================= INVENTORY =================================================

    @Override
    public int size() {
        return CONTENTS_SLOT_COUNT;
    }

    @Override
    public boolean isEmpty() {
        return this.getCount() == 0;
    }

    @Override
    public ItemStack getStack(int slot) {
        return this.inventory.get(slot);
    }

    @Override
    public ItemStack removeStack(int slot, int amount) {
        ItemStack oldStack = this.inventory.get(slot);
        if(!oldStack.isEmpty()) {
            this.inventory.set(slot, ItemStack.EMPTY);
            updateBlockCount();
            return duplicateItemWithCount(oldStack, amount);
        }
        return ItemStack.EMPTY;
    }
/*
    @Override
    public ItemStack removeItemNoUpdate(int slot) {
        ItemStack retVal = this.inventory.set(slot, ItemStack.EMPTY);
        updateBlockCount();
        return retVal;
    }
*/
    @Override
    public ItemStack removeStack(int slot) {
        return Inventories.removeStack(this.inventory, slot);
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        this.inventory.set(slot, stack.copy());
        updateBlockCount();
    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        return false;
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
    }

    // ================================================= OTHER =================================================

    /*
    public boolean contains(ItemStack itemStack) {
        for (ItemStack item : inventory) {
            if (!itemStack.isEmpty())
                if(itemStack.is(item.getItem()) && itemStack.getDisplayName().getContents().equals(item.getDisplayName().getContents()))
                    return true;
        }
        return false;
    }

    public boolean containsItem(Item item) {
        for (ItemStack itemStack : inventory) {
            if (!item.equals(Items.AIR))
                if(itemStack.is(item))
                    return true;
        }
        return false;
    }
    */

    public boolean canPlaceItem(int slot, ItemStack itemStack) {
        return canPlaceItem(itemStack);
    }

    public boolean canPlaceItem(ItemStack itemStack) {
        return (this.containsAny(Set.of(itemStack.getItem())) && this.getCount() < 9) || (itemStack.isIn(ModTags.Items.CUPS) && isEmpty());
    }

    public boolean placeItem(ItemStack itemStack) {
        for(int i = 0; i < this.inventory.size(); i++) {
            ItemStack currentItem = this.inventory.get(i);
            if(currentItem.isEmpty()) {
                if(this.isEmpty())
                     setCupTypeOnBlockstate(itemStack.getItem().toString());
                this.inventory.set(i, itemStack.copy());
                updateBlockCount();
                return true;
            }
        }
        return false;
    }

    private void setCupTypeOnBlockstate(String itemId) {
        String[] idArray = Arrays.stream(itemId.split("_")).filter(x -> !x.equals("cup")).toArray(String[]::new);
        String identifier = String.join("_", idArray);
        /*
        for(String word: idArray) {
            RavenCoffee.LOGGER.debug("WORD");
            RavenCoffee.LOGGER.debug(word);
            if(!word.equals("cup"))
                identifier = String.join(identifier, "_", word);
        }
         */
        this.world.setBlockState(
                this.pos,
                this.world.getBlockState(this.pos).with(StackingCupsBlock.CUP_TYPE,
                        CupType.byPrefix(identifier)
        ));
    }

    private static ItemStack duplicateItemWithCount(ItemStack originalStack, int newCount) {
        ItemStack newStack = originalStack.copy();
        newStack.setCount(newCount);
        return newStack;
    }

    @Override
    protected Text getContainerName() {
        return null;
    }

    @Override
    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return null;
    }

    // ================================================= DATA =================================================

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
    }
}
