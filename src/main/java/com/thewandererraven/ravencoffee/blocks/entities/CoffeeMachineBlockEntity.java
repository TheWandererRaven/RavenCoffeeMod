package com.thewandererraven.ravencoffee.blocks.entities;

import com.thewandererraven.ravencoffee.containers.CoffeeMachineMenu;
import com.thewandererraven.ravencoffee.containers.inventory.BrewCupInputSlot;
import com.thewandererraven.ravencoffee.recipes.BrewSizedIngredient;
import com.thewandererraven.ravencoffee.recipes.CoffeeBrewRecipe;
import com.thewandererraven.ravencoffee.util.registries.BlockEntitiesRegistry;
import com.thewandererraven.ravencoffee.util.registries.ItemsRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
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
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class CoffeeMachineBlockEntity extends BlockEntity implements MenuProvider {
    public static final int OUTPUT_SLOT_COUNT = 1;
    public static final int CUPS_SLOT_COUNT = 1;
    public static final int INGREDIENTS_SLOT_COUNT = 1;
    public static final int OUTPUT_FIRST_SLOT_INDEX = 0;
    public static final int CUPS_FIRST_SLOT_INDEX = OUTPUT_FIRST_SLOT_INDEX + OUTPUT_SLOT_COUNT;
    public static final int INGREDIENTS_FIRST_SLOT_INDEX = CUPS_FIRST_SLOT_INDEX + CUPS_SLOT_COUNT;

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
        super(BlockEntitiesRegistry.COFFEE_MACHINE_BLOCK_ENTITY.get(), p_155229_, p_155230_);
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
    }

    private boolean hasNotReachedStackLimit() {
        return this.itemHandler.getStackInSlot(0).getCount() < this.itemHandler.getStackInSlot(1).getMaxStackSize();
    }

    private static void craftItem(CoffeeMachineBlockEntity blockEntity, ItemStack cup, ItemStack output) {

        for(int i = INGREDIENTS_FIRST_SLOT_INDEX; i < INGREDIENTS_FIRST_SLOT_INDEX + INGREDIENTS_SLOT_COUNT; i++) {
            BrewSizedIngredient ingredient = blockEntity.currentRecipe.getMatchingIngredient(blockEntity.itemHandler.getStackInSlot(i));
            if(ingredient != null)
                blockEntity.itemHandler.extractItem(i, ingredient.getCountBySize(BrewCupInputSlot.getCupSize(cup)), false);
        }
        //blockEntity.itemHandler.extractItem(INGREDIENTS_FIRST_SLOT_INDEX, 1, false);
        blockEntity.itemHandler.extractItem(CUPS_FIRST_SLOT_INDEX, 1, false);

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

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return lazyItemHandler.cast();
        }

        return super.getCapability(cap, side);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, CoffeeMachineBlockEntity blockEntity) {
        if(!level.isClientSide()) {
            SimpleContainer ingredientsInventory = getIngredientsInventory(blockEntity);
            // Check if the recipe still matches the ingredients
            if (blockEntity.currentRecipe != null)
                if (!blockEntity.currentRecipe.matches(ingredientsInventory, getCup(blockEntity), level))
                    blockEntity.resetCurrentRecipe();

            // Check if the block can still process an output
            // Verify output stack has not reached limit, item in cup slot is cup and item in ingredient is not empty
            if (blockEntity.hasNotReachedStackLimit() &&
                    BrewCupInputSlot.isCup(blockEntity.itemHandler.getStackInSlot(CUPS_FIRST_SLOT_INDEX)) &&
                    !blockEntity.itemHandler.getStackInSlot(INGREDIENTS_FIRST_SLOT_INDEX).isEmpty()
            ) {
                ItemStack cup = getCup(blockEntity);
                // Check if current recipe is not empty, if it is, search for the recipe given the ingredient
                if (blockEntity.currentRecipe == null) findRecipe(cup, ingredientsInventory, level).ifPresent(blockEntity::setCurrentRecipe);
                // Check if there's a recipe
                if (blockEntity.currentRecipe != null) {
                    ItemStack output = blockEntity.currentRecipe.getResultItem(
                            cup.getItem(),
                            blockEntity.itemHandler.getStackInSlot(OUTPUT_FIRST_SLOT_INDEX).getCount() + 1
                    );
                    Item outputCurrent = blockEntity.itemHandler.getStackInSlot(OUTPUT_FIRST_SLOT_INDEX).getItem();
                    // Check if the output is empty or the same as the result item
                    if (outputCurrent.equals(output.getItem()) || outputCurrent.equals(Items.AIR)) {
                        // If the process is complete craft the output item, if not, increase the progress
                        if (blockEntity.isBrewingProcessComplete()) craftItem(blockEntity, cup, output);
                        else blockEntity.tickCurrentProgress();
                        // If this point is reached, then this method has done everything it needs to do i.e. generating
                        // an output or increasing the progress
                        return;
                    }
                }
            }
            // If none of the filters pass, in other words, if the conditions for brewing/crafting are not met,
            // reset the progress
            if (blockEntity.currentProgress > 0) blockEntity.resetProgress();
        }
    }

    // ================================================= DISPLAY =================================================
    @Override
    public @NotNull Component getDisplayName() {
        return new TextComponent("Coffee Machine");
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
        return this.hasRecipe() && this.hasNotReachedStackLimit() && BrewCupInputSlot.isCup(this.itemHandler.getStackInSlot(2));
    }

    // ================================================= DATA =================================================

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
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

    private boolean hasRecipe() {
        return this.itemHandler.getStackInSlot(2).getItem() == ItemsRegistry.COFFEE_BEANS_ROASTED_GROUND.get();
    }

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
        SimpleContainer inventory = new SimpleContainer(blockEntity.itemHandler.getSlots() - INGREDIENTS_FIRST_SLOT_INDEX);
        for (int i = 0; i < inventory.getContainerSize(); i++) {
            inventory.setItem(i, blockEntity.itemHandler.getStackInSlot(i + INGREDIENTS_FIRST_SLOT_INDEX));
        }
        return inventory;
    }

    private static ItemStack getCup(CoffeeMachineBlockEntity blockEntity) {
        return blockEntity.itemHandler.getStackInSlot(CUPS_FIRST_SLOT_INDEX);
    }
}
