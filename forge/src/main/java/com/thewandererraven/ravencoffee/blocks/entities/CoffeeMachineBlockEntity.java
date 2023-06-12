package com.thewandererraven.ravencoffee.blocks.entities;

import com.thewandererraven.ravencoffee.Constants;
import com.thewandererraven.ravencoffee.blocks.CoffeeMachineBlock;
import com.thewandererraven.ravencoffee.containers.CoffeeMachineMenu;
import com.thewandererraven.ravencoffee.containers.inventory.BrewCupInputSlot;
import com.thewandererraven.ravencoffee.containers.inventory.BrewIngredientInputSlot;
import com.thewandererraven.ravencoffee.recipes.BrewSizedIngredient;
import com.thewandererraven.ravencoffee.recipes.CoffeeBrewRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class CoffeeMachineBlockEntity extends BlockEntity implements MenuProvider, WorldlyContainer {
    public static final int OUTPUT_SLOT_COUNT = 1;
    public static final int CUPS_SLOT_COUNT = 1;
    public static final int INGREDIENTS_SLOT_COUNT = 1;

    public static final int OUTPUT_FIRST_SLOT_INDEX = 0;
    public static final int CUPS_FIRST_SLOT_INDEX = OUTPUT_FIRST_SLOT_INDEX + OUTPUT_SLOT_COUNT;
    public static final int INGREDIENTS_FIRST_SLOT_INDEX = CUPS_FIRST_SLOT_INDEX + CUPS_SLOT_COUNT;

    private final int[] SLOTS_FOR_OUTPUT = new int[OUTPUT_SLOT_COUNT];
    private final int[] SLOTS_FOR_CUPS = new int[CUPS_SLOT_COUNT];
    private final int[] SLOTS_FOR_INGREDIENTS = new int[CUPS_SLOT_COUNT];


    private CoffeeBrewRecipe currentRecipe;
    private int currentProgress = 0;
    private int brewingTime = 0;
    public ContainerData data;

    private final ItemStackHandler itemHandler = new ItemStackHandler(3){
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };

    private LazyOptional<ItemStackHandler> lazyItemHandler = LazyOptional.empty();

    public CoffeeMachineBlockEntity(BlockPos p_155229_, BlockState p_155230_) {
        super(RavenCoffeeBlockEntityType.COFFEE_MACHINE_BLOCK_ENTITY.get(), p_155229_, p_155230_);

        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> CoffeeMachineBlockEntity.this.currentProgress;
                    case 1 -> CoffeeMachineBlockEntity.this.brewingTime;
                    default -> 0;
                };
            }
            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> CoffeeMachineBlockEntity.this.currentProgress = value;
                    case 1 -> CoffeeMachineBlockEntity.this.brewingTime = value;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };

        for(int i = 0; i < OUTPUT_SLOT_COUNT; i++) {
            SLOTS_FOR_OUTPUT[i] = OUTPUT_FIRST_SLOT_INDEX + i;
        }
        for(int i = 0; i < CUPS_SLOT_COUNT; i++) {
            SLOTS_FOR_CUPS[i] = CUPS_FIRST_SLOT_INDEX + i;
        }
        for(int i = 0; i < INGREDIENTS_SLOT_COUNT; i++) {
            SLOTS_FOR_INGREDIENTS[i] = INGREDIENTS_FIRST_SLOT_INDEX + i;
        }
    }

    private boolean hasNotReachedStackLimit() {
        int outputCurrentCount = 0;
        int outputMaxStackSize = 0;
        for(int i = 0; i < OUTPUT_SLOT_COUNT; i++) {
            outputCurrentCount += this.itemHandler.getStackInSlot(i + OUTPUT_FIRST_SLOT_INDEX).getCount();
            outputMaxStackSize += this.itemHandler.getStackInSlot(i + OUTPUT_FIRST_SLOT_INDEX).getMaxStackSize();
        }
        return outputCurrentCount < outputMaxStackSize;
    }

    private static void craftItem(CoffeeMachineBlockEntity blockEntity, ItemStack cup, ItemStack output) {
        for(int i = 0; i < INGREDIENTS_SLOT_COUNT; i++) {
            BrewSizedIngredient ingredient = blockEntity.currentRecipe.getMatchingIngredient(blockEntity.itemHandler.getStackInSlot(
                    i + INGREDIENTS_FIRST_SLOT_INDEX
            ));
            if(ingredient != null)
                blockEntity.itemHandler.extractItem(i + INGREDIENTS_FIRST_SLOT_INDEX, ingredient.getCountBySize(BrewCupInputSlot.getCupSize(cup)), false);
        }
        //blockEntity.itemHandler.extractItem(INGREDIENTS_FIRST_SLOT_INDEX, 1, false);
        for(int i = 0; i < CUPS_SLOT_COUNT; i++) {
            if(!blockEntity.itemHandler.getStackInSlot(i + CUPS_FIRST_SLOT_INDEX).isEmpty()){
                blockEntity.itemHandler.extractItem(i + CUPS_FIRST_SLOT_INDEX, 1, false);
                break;
            }
        }

        blockEntity.itemHandler.setStackInSlot(OUTPUT_FIRST_SLOT_INDEX, output);
        blockEntity.resetProgress();
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    // Tick method is currently designed only with the Simple Coffee Machine in mind
    public static void tick(Level level, BlockPos pos, BlockState state, CoffeeMachineBlockEntity blockEntity) {
        if(!level.isClientSide()) {
            SimpleContainer ingredientsInventory = getIngredientsInventory(blockEntity);
            // Check if the recipe still matches the ingredients
            if (blockEntity.currentRecipe != null)
                if (!blockEntity.currentRecipe.matches(ingredientsInventory, getCup(blockEntity), level)) {
                    blockEntity.resetCurrentRecipe();
                    if (state.getValue(CoffeeMachineBlock.ACTIVE)) level.setBlock(pos, state.setValue(CoffeeMachineBlock.ACTIVE, false), 3);
                }

            Item outputCurrent = blockEntity.itemHandler.getStackInSlot(OUTPUT_FIRST_SLOT_INDEX).getItem();
            ItemStack cup = getCup(blockEntity);
            Item inputCurrent = blockEntity.itemHandler.getStackInSlot(INGREDIENTS_FIRST_SLOT_INDEX).getItem();

            boolean stateIsEnabled = state.getValue(CoffeeMachineBlock.ENABLED);
            boolean stateHasInputCup = state.getValue(CoffeeMachineBlock.HAS_INPUT_CUP);
            boolean stateHasCoffee = state.getValue(CoffeeMachineBlock.HAS_COFFEE);
            boolean stateHasOutput = state.getValue(CoffeeMachineBlock.HAS_OUTPUT);

            boolean isCurrentCupEmpty = cup.getItem().equals(Items.AIR);
            boolean isCurrentInputEmpty = inputCurrent.equals(Items.AIR);
            boolean isCurrentOutputEmpty = outputCurrent.equals(Items.AIR);

            if (!stateHasInputCup && !isCurrentCupEmpty)
                level.setBlock(pos, state.setValue(CoffeeMachineBlock.HAS_INPUT_CUP, true), 3);
            if (stateHasInputCup && isCurrentCupEmpty)
                level.setBlock(pos, state.setValue(CoffeeMachineBlock.HAS_INPUT_CUP, false), 3);
            if (!stateHasCoffee && !isCurrentInputEmpty)
                level.setBlock(pos, state.setValue(CoffeeMachineBlock.HAS_COFFEE, true), 3);
            if (stateHasCoffee && isCurrentInputEmpty)
                level.setBlock(pos, state.setValue(CoffeeMachineBlock.HAS_COFFEE, false), 3);
            // Check if the block can still process an output
            // Verify output stack has not reached limit, item in cup slot is cup and item in ingredient is not empty
            if (blockEntity.hasNotReachedStackLimit() &&
                    BrewCupInputSlot.isCup(blockEntity.itemHandler.getStackInSlot(CUPS_FIRST_SLOT_INDEX)) &&
                    !blockEntity.itemHandler.getStackInSlot(INGREDIENTS_FIRST_SLOT_INDEX).isEmpty() &&
                    stateIsEnabled
            ) {
                // Check if current recipe is not empty, if it is, search for the recipe given the ingredient
                if (blockEntity.currentRecipe == null) findRecipe(cup, ingredientsInventory, level).ifPresent(blockEntity::setCurrentRecipe);
                // Check if there's a recipe
                if (blockEntity.currentRecipe != null) {
                    ItemStack output = blockEntity.currentRecipe.getResultItem(
                            cup,
                            blockEntity.itemHandler.getStackInSlot(OUTPUT_FIRST_SLOT_INDEX).getCount() + 1
                    );
                    // Check if the output is empty or the same as the result item
                    if (outputCurrent.equals(output.getItem()) || outputCurrent.equals(Items.AIR)) {
                        // If the process is complete craft the output item, if not, increase the progress
                        if (blockEntity.isBrewingProcessComplete()) craftItem(blockEntity, cup, output);
                        else blockEntity.tickCurrentProgress();
                        if (!state.getValue(CoffeeMachineBlock.ACTIVE))
                            level.setBlock(pos, state.setValue(CoffeeMachineBlock.ACTIVE, true), 3);
                        if (!state.getValue(CoffeeMachineBlock.HAS_OUTPUT))
                            level.setBlock(pos, state.setValue(CoffeeMachineBlock.HAS_OUTPUT, true), 3);
                        // If this point is reached, then this method has done everything it needs to do i.e. generating
                        // an output or increasing the progress
                        return;
                    }
                }
            }
            // If none of the filters pass, in other words, if the conditions for brewing/crafting are not met,
            // reset the progress
            if (blockEntity.currentProgress > 0) blockEntity.resetProgress();
            if (state.getValue(CoffeeMachineBlock.ACTIVE)) level.setBlock(pos, state.setValue(CoffeeMachineBlock.ACTIVE, false), 3);

            if (!stateHasOutput && !isCurrentOutputEmpty)
                level.setBlock(pos, state.setValue(CoffeeMachineBlock.HAS_OUTPUT, true), 3);
            if (stateHasOutput && isCurrentOutputEmpty)
                level.setBlock(pos, state.setValue(CoffeeMachineBlock.HAS_OUTPUT, false), 3);
        }
    }

    // ================================================= DISPLAY =================================================
    @Override
    public @NotNull Component getDisplayName() {
        return Component.translatable(String.format("container.%s.coffee_machine", Constants.MOD_ID));
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, @NotNull Inventory inventory, @NotNull Player player) {
        return new CoffeeMachineMenu(containerId, inventory, this, this.data);
    }

    // ================================================ PROGRESS ================================================
    public void resetProgress() {
        this.currentProgress = 0;
    }

    public boolean isBrewingProcessComplete(){
        if(this.currentRecipe != null)
            return this.currentProgress >= this.currentRecipe.getCookingTime();
        else
            return false;
    }

    public void tickCurrentProgress() {
        this.currentProgress += 1;
    }

    public void decreaseCurrentProgress() {
        this.currentProgress -= 1;
    }

    public boolean isBrewing() {
        return this.currentProgress > 0 || this.canBrew();
    }

    private boolean canBrew() {
        boolean areCups = false;
        for(int i = 0; i < CUPS_SLOT_COUNT; i++) {
            if(BrewCupInputSlot.isCup(this.itemHandler.getStackInSlot(i + CUPS_FIRST_SLOT_INDEX))){
                areCups = true;
                break;
            }
        }
        return this.hasNotReachedStackLimit() && areCups;
    }

    // ================================================= DATA =================================================

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        tag.put("inventory", itemHandler.serializeNBT());
        tag.putInt("coffee_machine.currentProgress", currentProgress);
        super.saveAdditional(tag);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
        currentProgress = nbt.getInt("coffee_machine.currentProgress");
    }

    // ================================================= RECIPES =================================================

    private static Optional<CoffeeBrewRecipe> findRecipe(ItemStack cup, SimpleContainer inventory, Level level) {
        CoffeeBrewRecipe foundRecipe = null;
        for(CoffeeBrewRecipe recipe : level.getRecipeManager().getAllRecipesFor(CoffeeBrewRecipe.Type.INSTANCE)) {
            if (recipe.matches(inventory, cup, level)) {
                foundRecipe = recipe;
                break;
            }
        }
        return foundRecipe != null ? Optional.of(foundRecipe) : Optional.empty();
    }

    private void setCurrentRecipe(CoffeeBrewRecipe recipe) {
        this.currentRecipe = recipe;
        this.brewingTime = recipe.getCookingTime();
    }

    private void resetCurrentRecipe() {
        this.currentRecipe = null;
        this.brewingTime = 0;
    }

    private static SimpleContainer getIngredientsInventory(CoffeeMachineBlockEntity blockEntity) {
        SimpleContainer inventory = new SimpleContainer(INGREDIENTS_SLOT_COUNT);
        for (int i = 0; i < inventory.getContainerSize(); i++) {
            inventory.setItem(i, blockEntity.itemHandler.getStackInSlot(i + INGREDIENTS_FIRST_SLOT_INDEX));
        }
        return inventory;
    }

    private static ItemStack getCup(CoffeeMachineBlockEntity blockEntity) {
        ItemStackHandler handler = blockEntity.itemHandler;
        for(int i = 0; i < CUPS_SLOT_COUNT; i++) {
            ItemStack stack = handler.getStackInSlot(i + CUPS_FIRST_SLOT_INDEX);
            if(!stack.isEmpty())
                return stack;
        }
        return ItemStack.EMPTY;
    }

    // =============================================== WORLDLY CONTAINER ===============================================

    @Override
    public int[] getSlotsForFace(Direction direction) {
        if(direction == Direction.DOWN) return SLOTS_FOR_OUTPUT;
        else if(direction == Direction.UP) return SLOTS_FOR_INGREDIENTS;
        else return SLOTS_FOR_CUPS;
    }

    @Override
    public boolean canPlaceItemThroughFace(int slot, ItemStack itemStack, @Nullable Direction direction) {
        if(direction != Direction.DOWN) {
            if (INGREDIENTS_FIRST_SLOT_INDEX <= slot && slot < INGREDIENTS_FIRST_SLOT_INDEX + INGREDIENTS_SLOT_COUNT && direction == Direction.UP)
                return BrewIngredientInputSlot.isBrewIngredient(itemStack);
            else if(CUPS_FIRST_SLOT_INDEX <= slot && slot < CUPS_FIRST_SLOT_INDEX + CUPS_SLOT_COUNT)
                return BrewCupInputSlot.isCup(itemStack);
        }
        return false;
    }

    @Override
    public boolean canTakeItemThroughFace(int slot, ItemStack itemStack, Direction direction) {
        return direction == Direction.DOWN && ((OUTPUT_FIRST_SLOT_INDEX <= slot && slot < OUTPUT_FIRST_SLOT_INDEX + OUTPUT_SLOT_COUNT) || itemStack.is(Items.BUCKET));
    }

    @Override
    public int getContainerSize() {
        return this.itemHandler.getSlots();
    }

    @Override
    public boolean isEmpty() {
        for(int i = 0; i < this.itemHandler.getSlots(); i++) {
            if(this.itemHandler.getStackInSlot(i).isEmpty())
                return false;
        }
        return true;
    }

    @Override
    public ItemStack getItem(int slot) {
        return this.itemHandler.getStackInSlot(slot);
    }

    @Override
    public ItemStack removeItem(int slot, int count) {
        return this.itemHandler.extractItem(slot, count, false);
    }

    @Override
    public ItemStack removeItemNoUpdate(int slot) {
        return this.itemHandler.extractItem(slot, this.itemHandler.getStackInSlot(slot).getCount(), false);
    }

    @Override
    public void setItem(int slot, ItemStack itemStack) {
        this.itemHandler.setStackInSlot(slot, itemStack);
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    @Override
    public void clearContent() {
        for(int i = 0; i < this.itemHandler.getSlots(); i++) {
            this.itemHandler.setStackInSlot(i, ItemStack.EMPTY);
        }
    }

    // ================================================= CAPABILITIES =================================================

    net.minecraftforge.common.util.LazyOptional<? extends net.minecraftforge.items.IItemHandler>[] handlers =
            net.minecraftforge.items.wrapper.SidedInvWrapper.create(this, Direction.UP, Direction.DOWN, Direction.NORTH);

    @Override
    public <T> net.minecraftforge.common.util.LazyOptional<T> getCapability(net.minecraftforge.common.capabilities.Capability<T> capability, @javax.annotation.Nullable Direction facing) {
        if (!this.remove && capability == ForgeCapabilities.ITEM_HANDLER) {
            if(facing == null)
                return lazyItemHandler.cast();
            else if (facing == Direction.UP)
                return handlers[0].cast();
            else if (facing == Direction.DOWN)
                return handlers[1].cast();
            else
                return handlers[2].cast();
        }
        return super.getCapability(capability, facing);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
        for (LazyOptional<? extends net.minecraftforge.items.IItemHandler> handler : handlers) handler.invalidate();
    }

    @Override
    public void reviveCaps() {
        super.reviveCaps();
        this.handlers = net.minecraftforge.items.wrapper.SidedInvWrapper.create(this, Direction.UP, Direction.DOWN, Direction.NORTH);
    }
}
