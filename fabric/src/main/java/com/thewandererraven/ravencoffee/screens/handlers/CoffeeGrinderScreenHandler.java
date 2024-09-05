package com.thewandererraven.ravencoffee.screens.handlers;

import com.thewandererraven.ravencoffee.recipes.CoffeeGrindingRecipe;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.CraftingResultInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.recipe.book.RecipeBookCategory;
import net.minecraft.screen.*;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

import java.util.Optional;

public class CoffeeGrinderScreenHandler extends AbstractRecipeScreenHandler<CraftingInventory> {

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

    protected final CraftingResultInventory output = new CraftingResultInventory();
    protected final CraftingInventory input = new CraftingInventory(this, 1, 2);
    private final ScreenHandlerContext context;
    protected final PlayerEntity player;

    public CoffeeGrinderScreenHandler(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        this(syncId, playerInventory, ScreenHandlerContext.EMPTY);
    }

    public CoffeeGrinderScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory) {
        this(syncId, playerInventory, ScreenHandlerContext.EMPTY);
    }

    public CoffeeGrinderScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, ScreenHandlerContext.EMPTY);
    }



    public CoffeeGrinderScreenHandler(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(RavenCoffeeScreenHandlers.COFFEE_GRINDER_SCREEN_HANDLER, syncId);
        this.player = playerInventory.player;
        this.context = context;
        // Add the tile output containers to the gui
        // NOTE: originally I was using a custom slot CoffeeGrinderResultSlot for this
        addSlot(new Slot(this.output, OUTPUT_SLOT_COUNT, OUTPUT_SLOT_POS_X,  OUTPUT_SLOT_POS_Y) {
            public boolean canInsert(ItemStack stack) {
                return false;
            }

            public boolean canTakeItems(PlayerEntity playerEntity) {
                return CoffeeGrinderScreenHandler.this.canTakeOutput(playerEntity, this.hasStack());
            }

            public void onTakeItem(PlayerEntity player, ItemStack stack) {
                CoffeeGrinderScreenHandler.this.onTakeOutput(player, stack);
            }
        });

        // Add the tile input containers to the gui
        for (int x = 0; x < INPUT_SLOT_COUNT; x++) {
            addSlot(new Slot(this.input, x, INPUT_SLOT_POS_X,  INPUT_SLOT_POS_Y + INPUT_SLOT_Y_SPACING * x));
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

    @Override
    public RecipeBookCategory getCategory() {
        return null;
    }

    public boolean canUse(PlayerEntity player) {
        return true;
    }

    // ############################################### SCREEN ###############################################


    @Override
    public void onClosed(PlayerEntity player) {
        super.onClosed(player);
        this.dropInventory(this.player, this.input);
    }

    // ################################################### CRAFTING ###################################################
    @Override
    public void onContentChanged(Inventory inventory) {
        super.onContentChanged(inventory);
        if (inventory == this.input) {
            updateResult(this, this.player.getWorld(), this.player, this.input, this.output);
        }
    }

    protected static void updateResult(ScreenHandler handler, World world, PlayerEntity player, CraftingInventory craftingInventory, CraftingResultInventory resultInventory) {
        if (!world.isClient) {
            ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity)player;
            ItemStack itemStack = ItemStack.EMPTY;
            Optional<CoffeeGrindingRecipe> optional = world.getServer().getRecipeManager().getFirstMatch(CoffeeGrindingRecipe.Type.INSTANCE, craftingInventory, world);
            if (optional.isPresent()) {
                CoffeeGrindingRecipe craftingRecipe = optional.get();
                if (resultInventory.shouldCraftRecipe(world, serverPlayerEntity, craftingRecipe)) {
                    itemStack = craftingRecipe.craft(craftingInventory, world.getRegistryManager());
                }
            }

            resultInventory.setStack(0, itemStack);
        }
    }

    @Override
    public void populateRecipeFinder(RecipeMatcher finder) {
        this.input.provideRecipeInputs(finder);
    }

    protected boolean canTakeOutput(PlayerEntity player, boolean present) {
        return true;
    }

    protected void onTakeOutput(PlayerEntity player, ItemStack stack) {
        stack.onCraft(player.getWorld(), player, stack.getCount());
        //this.output.unlockLastRecipe(player);
        this.decrementStack(0);
        this.decrementStack(1);
        this.context.run((world, pos) -> {
            world.playSound((PlayerEntity)null, pos, SoundEvents.UI_STONECUTTER_TAKE_RESULT, SoundCategory.BLOCKS, 1.0F, 1.0F);
            //world.syncWorldEvent(1044, pos, 0);
        });
    }

    // ##################################################### SLOTS #####################################################
    @Override
    public void clearCraftingSlots() {
        this.input.clear();
        this.output.clear();
    }

    @Override
    public boolean matches(Recipe<? super CraftingInventory> recipe) {
        return recipe.matches(this.input, this.player.getWorld());
    }

    @Override
    public int getCraftingResultSlotIndex() {
        return OUTPUT_FIRST_SLOT_INDEX;
    }

    @Override
    public int getCraftingWidth() {
        return 1;
    }

    @Override
    public int getCraftingHeight() {
        return 2;
    }

    @Override
    public int getCraftingSlotCount() {
        return INPUT_SLOT_COUNT;
    }

    @Override
    public boolean canInsertIntoSlot(int index) {
        return index != OUTPUT_FIRST_SLOT_INDEX;
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot.hasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            // If selected item is in output...
            if (index == OUTPUT_FIRST_SLOT_INDEX) {
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
            else if (index >= VANILLA_FIRST_SLOT_INDEX && index < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOTS_COUNT) {
                // ...move to input slots...
                if (!this.insertItem(itemstack1, INPUT_FIRST_SLOT_INDEX, INPUT_FIRST_SLOT_INDEX + INPUT_SLOT_COUNT, false))
                    // ...if input slots are full, and if selected slot is from player inventory...
                    if (index < PLAYER_INVENTORY_FIRST_SLOT_INDEX + PLAYER_INVENTORY_SLOTS_COUNT) {
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
                slot.insertStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            ItemStack itemstack2 = slot.getStack();
            slot.onTakeItem(player, itemstack1);
            if (index == OUTPUT_FIRST_SLOT_INDEX)
                player.dropItem(itemstack2, false);
        }

        return itemstack;
    }
    private void decrementStack(int slot) {
        ItemStack itemStack = this.input.getStack(slot);
        itemStack.decrement(1);
        this.input.setStack(slot, itemStack);
    }
}
