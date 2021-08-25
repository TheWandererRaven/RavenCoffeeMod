package com.TheWandererRaven.ravencoffee.containers;

import com.TheWandererRaven.ravencoffee.util.registries.ContainersRegistry;
import com.TheWandererRaven.ravencoffee.util.registries.RecipeTypesRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.CraftResultInventory;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.*;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ICraftingRecipe;
import net.minecraft.network.play.server.SSetSlotPacket;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.world.World;
import net.minecraftforge.items.wrapper.PlayerInvWrapper;

import javax.annotation.Nonnull;
import java.util.Optional;

public class CoffeeGrinderContainer extends Container {
    private final PlayerEntity player;
    private final IWorldPosCallable worldPosCallable;

    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;

    private static final int INPUT_SLOT_COUNT = 2;
    private static final int OUTPUT_SLOT_COUNT = 1;
    private static final int COFFEE_GRINDER_SLOTS_COUNT = INPUT_SLOT_COUNT + OUTPUT_SLOT_COUNT;

    private static final int FIRST_OUTPUT_SLOT_INDEX = 0;
    private static final int FIRST_INPUT_SLOT_INDEX = FIRST_OUTPUT_SLOT_INDEX + OUTPUT_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = FIRST_INPUT_SLOT_INDEX + INPUT_SLOT_COUNT;
    private static final int HOTBAR_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX;
    private static final int PLAYER_INVENTORY_FIRST_SLOT_INDEX = HOTBAR_FIRST_SLOT_INDEX + HOTBAR_SLOT_COUNT;

    public static final int PLAYER_HOTBAR_XPOS = 8;
    public static final int PLAYER_HOTBAR_YPOS = 142;
    public static final int PLAYER_INVENTORY_XPOS = 8;
    public static final int PLAYER_INVENTORY_YPOS = 84;

    public static final int OUTPUT_SLOT_POS_X = 116;
    public static final int OUTPUT_SLOT_POS_Y = 35;

    public static final int INPUT_SLOT_POS_X = 56;
    public static final int INPUT_SLOT_Y_SPACING = 19;
    public static final int INPUT_SLOT_POS_Y = 25;

    public static final int SLOT_X_SPACING = 18;
    public static final int SLOT_Y_SPACING = 18;

    private final CraftingInventory craftMatrix = new CraftingInventory(this, 1, 2);
    private final CraftResultInventory craftResult = new CraftResultInventory();

    public static CoffeeGrinderContainer createContainerServerSide(int windowID, PlayerInventory playerInventory) {
        return new CoffeeGrinderContainer(windowID, playerInventory);
    }

    public static CoffeeGrinderContainer createContainerClientSide(int windowID, PlayerInventory playerInventory, net.minecraft.network.PacketBuffer extraData) {
        //  don't need extraData for this example; if you want you can use it to provide extra information from the server, that you can use
        //  when creating the client container
        //  eg String detailedDescription = extraData.readString(128);

        // on the client side there is no parent TileEntity to communicate with, so we:
        // 1) use a dummy inventory
        // 2) use "do nothing" lambda functions for canPlayerAccessInventory and markDirty
        return new CoffeeGrinderContainer(windowID, playerInventory);
    }

    // must assign a slot number to each of the slots used by the GUI.
    // For this container, we can see both the tile inventory's slots as well as the player inventory slots and the hotbar.
    // Each time we add a Slot to the container, it automatically increases the slotIndex, which means
    //  0 - 8 = hotbar slots (which will map to the InventoryPlayer slot numbers 0 - 8)
    //  9 - 35 = player inventory slots (which map to the InventoryPlayer slot numbers 9 - 35)
    //  36 - 44 = TileInventory slots, which map to our TileEntity slot numbers 0 - 8)

    private CoffeeGrinderContainer(int windowID, PlayerInventory playerInventory) {
        this(windowID, playerInventory, IWorldPosCallable.DUMMY);
    }

    public CoffeeGrinderContainer(int windowID, PlayerInventory playerInventory, IWorldPosCallable worldPosCallable) {
        super(ContainersRegistry.COFFEE_GRINDER_CONTAINER.get(), windowID);
        this.worldPosCallable = worldPosCallable;
        if (ContainersRegistry.COFFEE_GRINDER_CONTAINER == null)
            throw new IllegalStateException("Must initialise containerBasicContainerType before constructing a ContainerBasic!");

        this.player = playerInventory.player;
        PlayerInvWrapper playerInventoryForge = new PlayerInvWrapper(playerInventory);  // wrap the IInventory in a Forge IItemHandler.
        // Not actually necessary - can use Slot(playerInventory) instead of SlotItemHandler(playerInventoryForge)

        // Add the tile output containers to the gui
        addSlot(new CraftingResultSlot(playerInventory.player, this.craftMatrix, this.craftResult, OUTPUT_SLOT_COUNT, OUTPUT_SLOT_POS_X,  OUTPUT_SLOT_POS_Y));
        // Add the tile input containers to the gui
        for (int x = 0; x < INPUT_SLOT_COUNT; x++) {
            addSlot(new Slot(craftMatrix, x, INPUT_SLOT_POS_X,  INPUT_SLOT_POS_Y + INPUT_SLOT_Y_SPACING * x));
        }
        // Add the players hotbar to the gui - the [xpos, ypos] location of each item
        for (int x = 0; x < HOTBAR_SLOT_COUNT; x++) {
            addSlot(new Slot(playerInventory, x, PLAYER_HOTBAR_XPOS + SLOT_X_SPACING * x, PLAYER_HOTBAR_YPOS));
        }

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

    // Vanilla calls this method every tick to make sure the player is still able to access the inventory, and if not closes the gui
    // Called on the SERVER side only
    @Override
    public boolean canInteractWith(@Nonnull PlayerEntity playerEntity)
    {
        // This is typically a check that the player is within 8 blocks of the container.
        //  Some containers perform it using just the block placement:
        //  return isWithinUsableDistance(this.iWorldPosCallable, playerIn, Blocks.MYBLOCK); eg see BeaconContainer
        //  where iWorldPosCallable is a lambda that retrieves the blockstate at a particular world blockpos
        // for other containers, it defers to the IInventory provided to the Container (i.e. the TileEntity) which does the same
        //  calculation
        // return this.furnaceInventory.isUsableByPlayer(playerEntity);
        // Sometimes it perform an additional check (eg for EnderChests - the player owns the chest)

        return craftMatrix.isUsableByPlayer(playerEntity) && craftResult.isUsableByPlayer(playerEntity);
    }

    // This is where you specify what happens when a player shift clicks a slot in the gui
    //  (when you shift click a slot in the TileEntity Inventory, it moves it to the first available position in the hotbar and/or
    //    player inventory.  When you you shift-click a hotbar or player inventory item, it moves it to the first available
    //    position in the TileEntity inventory)
    // At the very least you must override this and return ItemStack.EMPTY or the game will crash when the player shift clicks a slot
    // returns ItemStack.EMPTY if the source slot is empty, or if none of the the source slot item could be moved
    //   otherwise, returns a copy of the source stack
    @Nonnull
    @Override

    /**
     * Handle when the stack in slot {@code index} is shift-clicked. Normally this moves the stack between the player
     * inventory and the other inventory(s).
     */
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        // Get selected slot
        Slot slot = this.inventorySlots.get(index);
        // if slot has items...
        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            // if clicked on OUTPUT SLOT
            if (index == FIRST_OUTPUT_SLOT_INDEX) {
                this.worldPosCallable.consume((p_217067_2_, p_217067_3_) -> {
                    itemstack1.getItem().onCreated(itemstack1, p_217067_2_, playerIn);
                });
                if (!this.mergeItemStack(itemstack1, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            // if clicked on VANILLA INVENTORY
            else if (index >= VANILLA_FIRST_SLOT_INDEX && index < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
                // send to INPUT SLOTS
                if (!this.mergeItemStack(itemstack1, FIRST_INPUT_SLOT_INDEX, INPUT_SLOT_COUNT, false)) {
                    if (index < HOTBAR_FIRST_SLOT_INDEX) {
                        // HOTBAR
                        if (!this.mergeItemStack(itemstack1, HOTBAR_FIRST_SLOT_INDEX, HOTBAR_FIRST_SLOT_INDEX + HOTBAR_SLOT_COUNT, false)) {
                            return ItemStack.EMPTY;
                        }
                        // INVENTORY
                    } else if (!this.mergeItemStack(itemstack1, PLAYER_INVENTORY_FIRST_SLOT_INDEX, PLAYER_INVENTORY_FIRST_SLOT_INDEX + PLAYER_INVENTORY_SLOT_COUNT, false)) {
                        return ItemStack.EMPTY;
                    }
                }
            }
            // senf to VANILLA INVENTORY
            else if (!this.mergeItemStack(itemstack1, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            }
            else {
                slot.onSlotChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            ItemStack itemstack2 = slot.onTake(playerIn, itemstack1);
            if (index == FIRST_OUTPUT_SLOT_INDEX) {
                playerIn.dropItem(itemstack2, false);
            }
        }

        return itemstack;
    }

    // pass the close container message to the parent inventory (not strictly needed for this example)
    //  see ContainerChest and TileEntityChest - used to animate the lid when no players are accessing the chest any more
    @Override
    public void onContainerClosed(@Nonnull PlayerEntity playerIn)
    {
        super.onContainerClosed(playerIn);
        this.worldPosCallable.consume((p_217068_2_, p_217068_3_) -> {
            this.clearContainer(playerIn, p_217068_2_, this.craftMatrix);
        });
    }

    // ########################################### RECIPES #############################################################
    public static ItemStack getGrindingResultForItems(World world, CraftingInventory craftingInventory) {
        Optional<ICraftingRecipe> matchingRecipe = getMatchingRecipeForInput(world, craftingInventory);
        // beware! You must deep copy otherwise you will alter the recipe itself
        return matchingRecipe.map(iCraftingRecipe -> iCraftingRecipe.getRecipeOutput().copy()).orElse(ItemStack.EMPTY);
    }
    // gets the recipe which matches the given input, or Missing if none.
    public static Optional<ICraftingRecipe> getMatchingRecipeForInput(World world, CraftingInventory craftingInventory) {
        return world.getRecipeManager().getRecipe(RecipeTypesRegistry.COFFEE_GRINDING, craftingInventory, world);
    }
    public void updateCraftingResult(int id, World world, PlayerEntity player, CraftingInventory inventory, CraftResultInventory inventoryResult) {
        if (!world.isRemote) {
            ServerPlayerEntity serverplayerentity = (ServerPlayerEntity)player;
            ItemStack itemstack = getGrindingResultForItems(world, inventory);

            inventoryResult.setInventorySlotContents(0, itemstack);
            serverplayerentity.connection.sendPacket(new SSetSlotPacket(id, 0, itemstack));
        }
    }
    /**
     * Callback for when the crafting matrix is changed.
     */
    public void onCraftMatrixChanged(@Nonnull IInventory inventoryIn) {
        this.worldPosCallable.consume((p_217069_1_, p_217069_2_) -> {
            updateCraftingResult(this.windowId, p_217069_1_, this.player, this.craftMatrix, this.craftResult);
        });
    }
}
