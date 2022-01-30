package com.TheWandererRaven.ravencoffee.containers;

import com.TheWandererRaven.ravencoffee.containers.inventory.CoffeeGrinderResultSlot;
import com.TheWandererRaven.ravencoffee.util.registries.ContainersRegistry;
import com.TheWandererRaven.ravencoffee.util.registries.RecipeTypesRegistry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.wrapper.PlayerInvWrapper;

import java.util.Optional;


public class CoffeeGrinderContainer extends AbstractContainerMenu {
    private final Player player;
    private final ContainerLevelAccess worldPosCallable;

    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;

    private static final int INPUT_SLOT_COUNT = 2;
    private static final int OUTPUT_SLOT_COUNT = 1;

    private static final int PLAYER_INVENTORY_SLOTS_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOTS_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOTS_COUNT;
    private static final int COFFEE_GRINDER_SLOTS_COUNT = INPUT_SLOT_COUNT + OUTPUT_SLOT_COUNT;


    private static final int OUTPUT_FIRST_SLOT_INDEX = 0;
    private static final int INPUT_FIRST_SLOT_INDEX = OUTPUT_FIRST_SLOT_INDEX + OUTPUT_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = INPUT_FIRST_SLOT_INDEX + INPUT_SLOT_COUNT;
    private static final int PLAYER_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX;
    private static final int HOTBAR_FIRST_SLOT_INDEX = PLAYER_INVENTORY_FIRST_SLOT_INDEX + PLAYER_INVENTORY_SLOTS_COUNT;

    // ########################### POSITIONS ###########################

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

    private final CraftingContainer craftMatrix = new CraftingContainer(this, 1, 2);
    private final ResultContainer craftResult = new ResultContainer();

    public static CoffeeGrinderContainer createContainerServerSide(int windowID, Inventory playerInventory) {
        return new CoffeeGrinderContainer(windowID, playerInventory);
    }

    public static CoffeeGrinderContainer createContainerClientSide(int windowID, Inventory playerInventory, FriendlyByteBuf extraData) {
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

    private CoffeeGrinderContainer(int windowID, Inventory playerInventory) {
        this(windowID, playerInventory, ContainerLevelAccess.NULL);
    }

    public CoffeeGrinderContainer(int windowID, Inventory playerInventory, ContainerLevelAccess worldPosCallable) {
        super(ContainersRegistry.COFFEE_GRINDER_CONTAINER.get(), windowID);
        this.worldPosCallable = worldPosCallable;
        if (ContainersRegistry.COFFEE_GRINDER_CONTAINER == null)
            throw new IllegalStateException("Must initialise containerBasicContainerType before constructing a ContainerBasic!");

        this.player = playerInventory.player;
        PlayerInvWrapper playerInventoryForge = new PlayerInvWrapper(playerInventory);  // wrap the IInventory in a Forge IItemHandler.
        // Not actually necessary - can use Slot(playerInventory) instead of SlotItemHandler(playerInventoryForge)

        // Add the tile output containers to the gui
        addSlot(new CoffeeGrinderResultSlot(playerInventory.player, this.craftMatrix, this.craftResult, OUTPUT_SLOT_COUNT, OUTPUT_SLOT_POS_X,  OUTPUT_SLOT_POS_Y));
        // Add the tile input containers to the gui
        for (int x = 0; x < INPUT_SLOT_COUNT; x++) {
            addSlot(new Slot(craftMatrix, x, INPUT_SLOT_POS_X,  INPUT_SLOT_POS_Y + INPUT_SLOT_Y_SPACING * x));
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

        // Add the players hotbar to the gui - the [xpos, ypos] location of each item
        for (int x = 0; x < HOTBAR_SLOT_COUNT; x++) {
            addSlot(new Slot(playerInventory, x, PLAYER_HOTBAR_XPOS + SLOT_X_SPACING * x, PLAYER_HOTBAR_YPOS));
        }
    }

    // Vanilla calls this method every tick to make sure the player is still able to access the inventory, and if not closes the gui
    // Called on the SERVER side only
    @Override
    public boolean stillValid(Player playerEntity)
    {
        // This is typically a check that the player is within 8 blocks of the container.
        //  Some containers perform it using just the block placement:
        //  return isWithinUsableDistance(this.iWorldPosCallable, playerIn, Blocks.MYBLOCK); eg see BeaconContainer
        //  where iWorldPosCallable is a lambda that retrieves the blockstate at a particular world blockpos
        // for other containers, it defers to the IInventory provided to the Container (i.e. the TileEntity) which does the same
        //  calculation
        // return this.furnaceInventory.isUsableByPlayer(playerEntity);
        // Sometimes it perform an additional check (eg for EnderChests - the player owns the chest)

        return craftMatrix.stillValid(playerEntity) && craftResult.stillValid(playerEntity);
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
            if (index == OUTPUT_FIRST_SLOT_INDEX) {
                this.worldPosCallable.execute((p_217067_2_, p_217067_3_) -> {
                    itemstack1.getItem().onCraftedBy(itemstack1, p_217067_2_, playerIn);
                });
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
                if (!this.moveItemStackTo(itemstack1, INPUT_FIRST_SLOT_INDEX, INPUT_FIRST_SLOT_INDEX + INPUT_SLOT_COUNT, false))
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
            if (index == OUTPUT_FIRST_SLOT_INDEX)
                playerIn.drop(itemstack2, false);
        }

        return itemstack;
    }

    // pass the close container message to the parent inventory (not strictly needed for this example)
    //  see ContainerChest and TileEntityChest - used to animate the lid when no players are accessing the chest any more
    @Override
    public void removed(Player playerIn)
    {
        super.removed(playerIn);
        this.worldPosCallable.execute((p_217068_2_, p_217068_3_) -> {
            this.clearContainer(playerIn, this.craftMatrix);
        });
    }

    // ########################################### RECIPES #############################################################
    public static ItemStack getGrindingResultForItems(Level world, CraftingContainer craftingInventory) {
        Optional<CraftingRecipe> matchingRecipe = getMatchingRecipeForInput(world, craftingInventory);
        // beware! You must deep copy otherwise you will alter the recipe itself
        return matchingRecipe.map(recipe -> recipe.getResultItem().copy()).orElse(ItemStack.EMPTY);
    }
    // gets the recipe which matches the given input, or Missing if none.
    public static Optional<CraftingRecipe> getMatchingRecipeForInput(Level world, CraftingContainer craftingInventory) {
        return world.getRecipeManager().getRecipeFor(RecipeTypesRegistry.COFFEE_GRINDING, craftingInventory, world);
    }

    public void updateCraftingResult(AbstractContainerMenu containerMenu, Level world, Player player, CraftingContainer inventory, ResultContainer inventoryResult) {
        if (!world.isClientSide) {
            ServerPlayer serverplayer = (ServerPlayer)player;
            ItemStack itemstack = ItemStack.EMPTY;
            Optional<CraftingRecipe> optional = world.getServer().getRecipeManager().getRecipeFor(RecipeTypesRegistry.COFFEE_GRINDING, inventory, world);
            if (optional.isPresent()) {
                CraftingRecipe craftingrecipe = optional.get();
                if (inventoryResult.setRecipeUsed(world, serverplayer, craftingrecipe)) {
                    itemstack = craftingrecipe.assemble(inventory);
                }
            }
            inventoryResult.setItem(0, itemstack);
            containerMenu.setRemoteSlot(0, itemstack);
            serverplayer.connection.send(new ClientboundContainerSetSlotPacket(containerMenu.containerId, containerMenu.incrementStateId(), 0, itemstack));
        }
    }
    /**
     * Callback for when the crafting matrix is changed.
     */
    @Override
    public void slotsChanged(Container inventoryIn) {
        this.worldPosCallable.execute((p_217069_1_, p_217069_2_) -> {
            updateCraftingResult(this, p_217069_1_, this.player, this.craftMatrix, this.craftResult);
        });
    }
}
