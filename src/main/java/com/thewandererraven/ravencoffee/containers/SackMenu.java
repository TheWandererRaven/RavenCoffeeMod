package com.thewandererraven.ravencoffee.containers;

import com.thewandererraven.ravencoffee.blocks.entities.CoffeeMachineBlockEntity;
import com.thewandererraven.ravencoffee.blocks.entities.SackBlockEntity;
import com.thewandererraven.ravencoffee.containers.inventory.SackContentsSlot;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;

public class SackMenu extends AbstractContainerMenu {
    private final SackBlockEntity blockEntity;

    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;

    private static final int PLAYER_INVENTORY_SLOTS_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOTS_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOTS_COUNT;

    private static final int VANILLA_FIRST_SLOT_INDEX = SackBlockEntity.CONTENTS_FIRST_SLOT_INDEX + SackBlockEntity.CONTENTS_SLOT_COUNT;
    private static final int PLAYER_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX;
    private static final int HOTBAR_FIRST_SLOT_INDEX = PLAYER_INVENTORY_FIRST_SLOT_INDEX + PLAYER_INVENTORY_SLOTS_COUNT;

    // ########################### POSITIONS ###########################

    public static final int PLAYER_HOTBAR_XPOS = 8;
    public static final int PLAYER_HOTBAR_YPOS = 126;
    public static final int PLAYER_INVENTORY_XPOS = 8;
    public static final int PLAYER_INVENTORY_YPOS = 68;

    public static final int INVENTORY_SLOT_POS_X = 71;
    public static final int INVENTORY_SLOT_POS_Y = 18;

    public static final int SLOT_X_SPACING = 18;
    public static final int SLOT_Y_SPACING = 18;

    public static final int COLUMNS = 2;
    public static final int ROWS = 2;

    public SackMenu(int containerId, Inventory inventory, FriendlyByteBuf extraData) {
        this(containerId, inventory, inventory.player.level.getBlockEntity(extraData.readBlockPos()));
    }

    public SackMenu(int containerId, Inventory playerInventory, BlockEntity blockEntity) {
        super(RavenCoffeeMenuTypes.SACK_MENU.get(), containerId);
        this.blockEntity = (SackBlockEntity) blockEntity;

        int containerSize = this.blockEntity.getContainerSize();

        for(int y = 0; y < ROWS; y++) {
            for(int x = 0; x < COLUMNS; x++) {
                int slotIndex = (y * COLUMNS) + x;
                if(slotIndex <= containerSize)
                    addSlot(new SackContentsSlot(this.blockEntity, slotIndex, INVENTORY_SLOT_POS_X + (x * SLOT_X_SPACING), INVENTORY_SLOT_POS_Y + (y * SLOT_Y_SPACING)));
            }
        }

        for (int y = 0; y < PLAYER_INVENTORY_ROW_COUNT; y++) {
            for (int x = 0; x < PLAYER_INVENTORY_COLUMN_COUNT; x++) {
                int slotNumber = HOTBAR_SLOT_COUNT + y * PLAYER_INVENTORY_COLUMN_COUNT + x;
                int xpos = PLAYER_INVENTORY_XPOS + x * SLOT_X_SPACING;
                int ypos = PLAYER_INVENTORY_YPOS + y * SLOT_Y_SPACING;
                addSlot(new Slot(playerInventory, slotNumber,  xpos, ypos));
            }
        }

        for (int x = 0; x < HOTBAR_SLOT_COUNT; x++) {
            addSlot(new Slot(playerInventory, x, PLAYER_HOTBAR_XPOS + SLOT_X_SPACING * x, PLAYER_HOTBAR_YPOS));
        }
    }

    @Override
    public boolean stillValid(Player p_38874_) {
        //return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()), player, RavenCoffeeBlocks.COFFEE_MACHINE_BLOCK.get());
        return true;
    }

    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            // If selected item is in sack inventory...
            if (index >= SackBlockEntity.CONTENTS_FIRST_SLOT_INDEX && index < SackBlockEntity.CONTENTS_FIRST_SLOT_INDEX + SackBlockEntity.CONTENTS_SLOT_COUNT) {
                if (!this.moveItemStackTo(itemstack1, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOTS_COUNT, true))
                    return ItemStack.EMPTY;
            }
            // If selected item is in vanilla slots...
            else if (index >= VANILLA_FIRST_SLOT_INDEX && index < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOTS_COUNT) {
                // ...move to sack slots...
                if (!this.moveItemStackTo(itemstack1, SackBlockEntity.CONTENTS_FIRST_SLOT_INDEX, SackBlockEntity.CONTENTS_FIRST_SLOT_INDEX + SackBlockEntity.CONTENTS_SLOT_COUNT, false))
                    // ...if sack slots are full, and if selected slot is from player inventory...
                    if (index < PLAYER_INVENTORY_FIRST_SLOT_INDEX + PLAYER_INVENTORY_SLOTS_COUNT) {
                        // ...move to hotbar...
                        if (!this.moveItemStackTo(itemstack1, HOTBAR_FIRST_SLOT_INDEX, HOTBAR_FIRST_SLOT_INDEX + HOTBAR_SLOT_COUNT, false)) {
                            return ItemStack.EMPTY;
                        }
                    }
                // ...otherwise, selected slot is from hotbar so, move to inventory
                    else if (!this.moveItemStackTo(itemstack1, PLAYER_INVENTORY_FIRST_SLOT_INDEX, PLAYER_INVENTORY_FIRST_SLOT_INDEX + PLAYER_INVENTORY_SLOTS_COUNT, false))
                        return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            ItemStack itemstack2 = slot.getItem();
            slot.onTake(playerIn, itemstack1);
            if (index == CoffeeMachineBlockEntity.OUTPUT_FIRST_SLOT_INDEX)
                playerIn.drop(itemstack2, false);
        }

        return itemstack;
    }
}
