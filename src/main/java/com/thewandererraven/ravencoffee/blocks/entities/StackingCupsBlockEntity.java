package com.thewandererraven.ravencoffee.blocks.entities;

import com.thewandererraven.ravencoffee.RavenCoffee;
import com.thewandererraven.ravencoffee.blocks.StackingCupsBlock;
import com.thewandererraven.ravenbrewscore.CupType;
import com.thewandererraven.ravencoffee.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Arrays;

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
        this.level.setBlockAndUpdate(
                this.worldPosition,
                this.getBlockState().setValue(StackingCupsBlock.CUP_COUNT, this.getCount())
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
                if(this.isEmpty())
                     setCupTypeOnBlockstate(itemStack.getItem().toString());
                this.items.set(i, itemStack);
                updateBlockCount();
                return true;
            }
        }
        return false;
    }

    public Item getItem() {
        return this.items.get(0).getItem();
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
        this.level.setBlockAndUpdate(
                this.worldPosition,
                this.level.getBlockState(this.worldPosition).setValue(StackingCupsBlock.CUP_TYPE,
                        CupType.byPrefix(identifier)
        ));
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
