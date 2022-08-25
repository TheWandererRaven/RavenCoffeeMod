package com.thewandererraven.ravencoffee.containers;

import com.thewandererraven.ravencoffee.blocks.RavenCoffeeBlocks;
import com.thewandererraven.ravencoffee.blocks.entities.CoffeeMachineBlockEntity;
import com.thewandererraven.ravencoffee.containers.inventory.BrewCupInputSlot;
import com.thewandererraven.ravencoffee.containers.inventory.BrewIngredientInputSlot;
import com.thewandererraven.ravencoffee.containers.inventory.CoffeeMachineResultSlot;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.CapabilityItemHandler;

public class CoffeeMachineMenu extends AbstractContainerMenu {
    private final CoffeeMachineBlockEntity blockEntity;
    private final Level level;
    private final ContainerData data;

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

    public CoffeeMachineMenu(int containerId, Inventory inventory, FriendlyByteBuf extraData) {
        this(containerId, inventory, inventory.player.level.getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(2));
    }

    public CoffeeMachineMenu(int containerId, Inventory inventory, BlockEntity blockEntity, ContainerData data) {
        super(RavenCoffeeMenuTypes.COFFEE_MACHINE_MENU.get(), containerId);
        checkContainerSize(inventory, COFFEE_MACHINE_SLOT_CONT);
        this.blockEntity = ((CoffeeMachineBlockEntity) blockEntity);
        this.level = inventory.player.level;
        this.data = data;

        this.blockEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(handler -> {
            // ADD THE CUP OUTPUT SLOT
            addSlot(new CoffeeMachineResultSlot(handler, CoffeeMachineBlockEntity.OUTPUT_FIRST_SLOT_INDEX, CUP_OUTPUT_SLOT_POS_X,  CUP_OUTPUT_SLOT_POS_Y));
            // ADD CUPS SLOT
            addSlot(new BrewCupInputSlot(handler, CoffeeMachineBlockEntity.CUPS_FIRST_SLOT_INDEX, CUPS_SLOT_POS_X,  CUPS_SLOT_POS_Y));
            // ADD THE INGREDIENTS SLOT
            addSlot(new BrewIngredientInputSlot(handler, CoffeeMachineBlockEntity.INGREDIENTS_FIRST_SLOT_INDEX, INGREDIENTS_SLOT_POS_X,  INGREDIENTS_SLOT_POS_Y));
        });

        // Add the rest of the players inventory to the gui
        for (int y = 0; y < PLAYER_INVENTORY_ROW_COUNT; y++) {
            for (int x = 0; x < PLAYER_INVENTORY_COLUMN_COUNT; x++) {
                int slotNumber = HOTBAR_SLOT_COUNT + y * PLAYER_INVENTORY_COLUMN_COUNT + x;
                int xpos = PLAYER_INVENTORY_XPOS + x * SLOT_X_SPACING;
                int ypos = PLAYER_INVENTORY_YPOS + y * SLOT_Y_SPACING;
                addSlot(new Slot(inventory, slotNumber,  xpos, ypos));
            }
        }

        // Add the players hotbar to the gui - the [xpos, ypos] location of each item
        for (int x = 0; x < HOTBAR_SLOT_COUNT; x++) {
            addSlot(new Slot(inventory, x, PLAYER_HOTBAR_XPOS + SLOT_X_SPACING * x, PLAYER_HOTBAR_YPOS));
        }

        addDataSlots(data);
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()), player, RavenCoffeeBlocks.COFFEE_MACHINE_BLOCK.get());
    }

    public boolean isCupsSlotEmpty() {
        return !this.slots.get(CoffeeMachineBlockEntity.CUPS_FIRST_SLOT_INDEX).hasItem();
    }

    public boolean isOutputSlotEmpty() {
        return !this.slots.get(CoffeeMachineBlockEntity.OUTPUT_FIRST_SLOT_INDEX).hasItem();
    }

    public float getBrewingProgress() {
        return (float) this.data.get(0) / this.data.get(1);
    }

    // This is where you specify what happens when a player shift clicks a slot in the gui
    //  (when you shift click a slot in the TileEntity Inventory, it moves it to the first available position in the hotbar and/or
    //    player inventory.  When you you shift-click a hotbar or player inventory item, it moves it to the first available
    //    position in the TileEntity inventory)
    // At the very least you must override this and return ItemStack.EMPTY or the game will crash when the player shift clicks a slot
    // returns ItemStack.EMPTY if the source slot is empty, or if none of the the source slot item could be moved
    //   otherwise, returns a copy of the source stack

    /**
     * Handle when the stack in slot {@code index} is shift-clicked. Normally this moves the stack between the player
     * inventory and the other inventory(s).
     */
    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            // If selected item is in output...
            if (index == CoffeeMachineBlockEntity.OUTPUT_FIRST_SLOT_INDEX) {
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
                if (!this.moveItemStackTo(itemstack1, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOTS_COUNT, true))
                    return ItemStack.EMPTY;
                //slot.onSlotChanged(itemstack1, itemstack);
                slot.onQuickCraft(itemstack1, itemstack);
            }
            // If selected item is in vanilla slots...
            else if (index >= VANILLA_FIRST_SLOT_INDEX && index < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOTS_COUNT) {
                // ...move to input slots...
                if (!this.moveItemStackTo(itemstack1, CoffeeMachineBlockEntity.INGREDIENTS_FIRST_SLOT_INDEX, CoffeeMachineBlockEntity.INGREDIENTS_FIRST_SLOT_INDEX + CoffeeMachineBlockEntity.INGREDIENTS_SLOT_COUNT, false))
                    // ...if input slots are full, and if selected slot is from player inventory...
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
            // If selected item is in input slots...
            // ...move to Player vanilla slots (first to player inventory, then to hotbar)
            else if (!this.moveItemStackTo(itemstack1, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOTS_COUNT, false))
                return ItemStack.EMPTY;

            if (itemstack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            //ItemStack itemstack2 = slot.onTake(playerIn, itemstack1);
            ItemStack itemstack2 = slot.getItem();
            slot.onTake(playerIn, itemstack1);
            if (index == CoffeeMachineBlockEntity.OUTPUT_FIRST_SLOT_INDEX)
                playerIn.drop(itemstack2, false);
        }

        return itemstack;
    }
}
