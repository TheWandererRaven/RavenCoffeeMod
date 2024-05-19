package com.thewandererraven.ravencoffee.screens.handlers;

import com.thewandererraven.ravencoffee.blocks.entitites.CoffeeMachineBlockEntity;
import com.thewandererraven.ravencoffee.recipes.CoffeeBrewingRecipe;
import com.thewandererraven.ravencoffee.util.Cups;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class CoffeeMachineScreenHandler extends ScreenHandler {
    private final Inventory inventory;
    private final PropertyDelegate propertyDelegate;

    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;

    private static final int COFFEE_MACHINE_SLOT_CONT = CoffeeMachineBlockEntity.OUTPUT_SLOT_COUNT + CoffeeMachineBlockEntity.CUPS_SLOT_COUNT + CoffeeMachineBlockEntity.INGREDIENTS_SLOT_COUNT;

    private static final int PLAYER_INVENTORY_SLOTS_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOTS_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOTS_COUNT;



    private static final int VANILLA_FIRST_SLOT_INDEX = CoffeeMachineBlockEntity.INGREDIENTS_FIRST_SLOT_INDEX + CoffeeMachineBlockEntity.INGREDIENTS_SLOT_COUNT;
    private static final int PLAYER_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX;
    private static final int HOTBAR_FIRST_SLOT_INDEX = PLAYER_INVENTORY_FIRST_SLOT_INDEX + PLAYER_INVENTORY_SLOTS_COUNT;

    // ########################### POSITIONS ###########################

    public static final int PLAYER_HOTBAR_XPOS = 8;
    public static final int PLAYER_HOTBAR_YPOS = 142;
    public static final int PLAYER_INVENTORY_XPOS = 8;
    public static final int PLAYER_INVENTORY_YPOS = 84;

    public static final int CUP_OUTPUT_SLOT_POS_X = 80;
    public static final int CUP_OUTPUT_SLOT_POS_Y = 49;

    public static final int CUPS_SLOT_POS_X = 53;
    public static final int CUPS_SLOT_POS_Y = 49;

    public static final int INGREDIENTS_SLOT_POS_X = 80;
    public static final int INGREDIENTS_SLOT_POS_Y = 17;

    public static final int SLOT_X_SPACING = 18;
    public static final int SLOT_Y_SPACING = 18;

    public CoffeeMachineScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, new SimpleInventory(3), new ArrayPropertyDelegate(3));
    }

    public CoffeeMachineScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate delegate) {
        super(RavenCoffeeScreenHandlers.COFFEE_MACHINE_SCREEN_HANDLER, syncId);
        checkSize(inventory, 3);
        this.inventory = inventory;
        inventory.onOpen(playerInventory.player);
        this.propertyDelegate = delegate;
        // Add the tile output containers to the gui

        addOutputSlot();
        addCupsSlots();
        addIngredientSlots();

        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);

        addProperties(delegate);
    }

    public float getScaledProgress() {
        int progress = this.propertyDelegate.get(0);
        int maxProgress = this.propertyDelegate.get(1);

        return ((float) progress) / ((float) maxProgress);
    }

    @Override
    public ItemStack transferSlot(PlayerEntity player, int invSlot) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot.hasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            // If selected item is in output...
            if (invSlot == CoffeeMachineBlockEntity.OUTPUT_FIRST_SLOT_INDEX) {
                /*this.worldPosCallable.execute((p_217067_2_, p_217067_3_) -> {
                    itemstack1.getItem().onCraftedBy(itemstack1, p_217067_2_, playerIn);
                });*/
                // ...move to rightmost hotbar slot...
                /*
                if (!this.moveItemStackTo(itemstack1, HOTBAR_FIRST_SLOT_INDEX, HOTBAR_FIRST_SLOT_INDEX + HOTBAR_SLOT_COUNT, true))
                    // ...if hotbar is full, move to rightmost and downmost empty slot
                    if (!this.moveItemStackTo(itemstack1, PLAYER_INVENTORY_FIRST_SLOT_INDEX, PLAYER_INVENTORY_FIRST_SLOT_INDEX + PLAYER_INVENTORY_SLOTS_COUNT, true))
                        return ItemStack.EMPTY;
                 */
                if (!this.insertItem(itemstack1, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOTS_COUNT, true))
                    return ItemStack.EMPTY;
                //slot.onSlotChanged(itemstack1, itemstack);
                slot.onQuickTransfer(itemstack1, itemstack);
            }
            // If selected item is in vanilla slots...
            else if (invSlot >= VANILLA_FIRST_SLOT_INDEX && invSlot < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOTS_COUNT) {
                // ...move to input slots...
                if (!this.insertItem(itemstack1, CoffeeMachineBlockEntity.INGREDIENTS_FIRST_SLOT_INDEX, CoffeeMachineBlockEntity.INGREDIENTS_FIRST_SLOT_INDEX + CoffeeMachineBlockEntity.INGREDIENTS_SLOT_COUNT, false))
                    // ...if ingredients slots are full, try with cups slot
                    if (!this.insertItem(itemstack1, CoffeeMachineBlockEntity.CUPS_FIRST_SLOT_INDEX, CoffeeMachineBlockEntity.CUPS_FIRST_SLOT_INDEX + CoffeeMachineBlockEntity.CUPS_SLOT_COUNT, false))
                        // ...if input slots are full, and if selected slot is from player inventory...
                        if (invSlot < PLAYER_INVENTORY_FIRST_SLOT_INDEX + PLAYER_INVENTORY_SLOTS_COUNT) {
                            // ...move to hotbar...
                            if (!this.insertItem(itemstack1, HOTBAR_FIRST_SLOT_INDEX, HOTBAR_FIRST_SLOT_INDEX + HOTBAR_SLOT_COUNT, false)) {
                                return ItemStack.EMPTY;
                            }
                        }
                        // ...otherwise, selected slot is from hotbar so, move to inventory
                        else if (!this.insertItem(itemstack1, PLAYER_INVENTORY_FIRST_SLOT_INDEX, PLAYER_INVENTORY_FIRST_SLOT_INDEX + PLAYER_INVENTORY_SLOTS_COUNT, false))
                            return ItemStack.EMPTY;
            }
            // If selected item is in input slots...
            // ...move to Player vanilla slots (first to player inventory, then to hotbar)
            else if (!this.insertItem(itemstack1, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOTS_COUNT, false))
                return ItemStack.EMPTY;

            if (itemstack1.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                //slot.setChanged();
                slot.markDirty();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            //ItemStack itemstack2 = slot.onTake(playerIn, itemstack1);
            ItemStack itemstack2 = slot.getStack();
            slot.onTakeItem(player, itemstack1);
            if (invSlot == CoffeeMachineBlockEntity.OUTPUT_FIRST_SLOT_INDEX)
                player.dropItem(itemstack2, false);
        }

        return itemstack;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    // ##################################################### SLOTS #####################################################

    private void addPlayerInventory(PlayerInventory playerInventory) {
        // Add the rest of the players inventory to the gui
        for (int y = 0; y < PLAYER_INVENTORY_ROW_COUNT; y++) {
            for (int x = 0; x < PLAYER_INVENTORY_COLUMN_COUNT; x++) {
                int slotNumber = HOTBAR_SLOT_COUNT + y * PLAYER_INVENTORY_COLUMN_COUNT + x;
                int xpos = PLAYER_INVENTORY_XPOS + x * SLOT_X_SPACING;
                int ypos = PLAYER_INVENTORY_YPOS + y * SLOT_Y_SPACING;
                addSlot(new Slot(playerInventory, slotNumber,  xpos, ypos));
            }
        }
    }

    private void addPlayerHotbar(PlayerInventory playerInventory) {
        // Add the players hotbar to the gui - the [xpos, ypos] location of each item
        for (int x = 0; x < HOTBAR_SLOT_COUNT; x++) {
            addSlot(new Slot(playerInventory, x, PLAYER_HOTBAR_XPOS + SLOT_X_SPACING * x, PLAYER_HOTBAR_YPOS));
        }
    }

    private void addIngredientSlots() {
        // Add the tile input containers to the gui
        for (int x = 0; x < CoffeeMachineBlockEntity.INGREDIENTS_SLOT_COUNT; x++) {
            addSlot(new Slot(this.inventory, CoffeeMachineBlockEntity.INGREDIENTS_FIRST_SLOT_INDEX + x, INGREDIENTS_SLOT_POS_X,  INGREDIENTS_SLOT_POS_Y + (SLOT_Y_SPACING * x)) {
                @Override
                public boolean canInsert(ItemStack stack) {
                    return CoffeeBrewingRecipe.isBrewIngredient(stack);
                }
            });
        }
    }

    private void addCupsSlots() {
        // Add the tile input containers to the gui
        for (int x = 0; x < CoffeeMachineBlockEntity.CUPS_SLOT_COUNT; x++) {
            addSlot(new Slot(this.inventory, CoffeeMachineBlockEntity.CUPS_FIRST_SLOT_INDEX + x, CUPS_SLOT_POS_X, CUPS_SLOT_POS_Y + (SLOT_Y_SPACING * x)) {
                @Override
                public boolean canInsert(ItemStack stack) {
                    return Cups.isCup(stack);
                }
            });
        }
    }

    private void addOutputSlot() {
        // NOTE: originally I was using a custom slot CoffeeGrinderResultSlot for this
        addSlot(new Slot(this.inventory, CoffeeMachineBlockEntity.OUTPUT_FIRST_SLOT_INDEX, CUP_OUTPUT_SLOT_POS_X,  CUP_OUTPUT_SLOT_POS_Y) {
            public boolean canInsert(ItemStack stack) {
                return false;
            }

            public boolean canTakeItems(PlayerEntity playerEntity) {
                //return CoffeeMachineScreenHandler.this.canTakeOutput(playerEntity, this.hasStack());
                return true;
            }

            public void onTakeItem(PlayerEntity player, ItemStack stack) {
                //CoffeeMachineScreenHandler.this.onTakeOutput(player, stack);
            }
        });
    }

    public boolean isCupsSlotEmpty() {
        return this.inventory.getStack(CoffeeMachineBlockEntity.CUPS_FIRST_SLOT_INDEX).isEmpty();
    }

    public boolean isOutputSlotEmpty() {
        return this.inventory.getStack(CoffeeMachineBlockEntity.OUTPUT_FIRST_SLOT_INDEX).isEmpty();
    }
}
