package com.thewandererraven.ravencoffee.blocks.entities;

import com.thewandererraven.ravencoffee.RavenCoffee;
import com.thewandererraven.ravencoffee.containers.CoffeeMachineMenu;
import com.thewandererraven.ravencoffee.containers.inventory.BrewCupInputSlot;
import com.thewandererraven.ravencoffee.recipes.CoffeeBrewRecipe;
import com.thewandererraven.ravencoffee.util.registries.BlockEntitiesRegistry;
import com.thewandererraven.ravencoffee.util.registries.BrewsRegistry;
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
import net.minecraft.world.item.ItemStack;
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

    private int completeProgress = 60;
    private int currentProgress = 0;
    private final ItemStackHandler itemHandler = new ItemStackHandler(3){
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };

    private LazyOptional<ItemStackHandler> lazyItemHandler = LazyOptional.empty();

    public CoffeeMachineBlockEntity(BlockPos p_155229_, BlockState p_155230_) {
        super(BlockEntitiesRegistry.COFFEE_MACHINE_BLOCK_ENTITY.get(), p_155229_, p_155230_);
    }

    @Override
    public Component getDisplayName() {
        return new TextComponent("Coffee Machine");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player player) {
        return new CoffeeMachineMenu(containerId, inventory, this);
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return lazyItemHandler.cast();
        }

        return super.getCapability(cap, side);
    }

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
        super.saveAdditional(tag);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, CoffeeMachineBlockEntity blockEntity) {
        if(blockEntity.hasRecipe() && blockEntity.hasNotReachedStackLimit() && BrewCupInputSlot.isCup(blockEntity.itemHandler.getStackInSlot(1))) {
            if(blockEntity.isBrewingProcessComplete())
                craftItem(blockEntity);
            else
                blockEntity.tickCurrentProgress();
        } else if(blockEntity.currentProgress > 0) {
            blockEntity.decreaseCurrentProgress();
        }
    }

    public void tickCurrentProgress() {
        this.currentProgress += 1;
    }

    public void decreaseCurrentProgress() {
        this.currentProgress -= 1;
    }

    private boolean canBrew() {
        return this.hasRecipe() && this.hasNotReachedStackLimit() && BrewCupInputSlot.isCup(this.itemHandler.getStackInSlot(2));
    }

    public boolean isBrewing() {
        return this.currentProgress > 0 || this.canBrew();
    }

    public void resetProgress() {
        this.currentProgress = 0;
    }

    public boolean isBrewingProcessComplete(){
        return this.currentProgress == this.completeProgress;
    }

    public float getCurrentProgressPercentage() {
        // From 0 to 1
        return (float) this.currentProgress / this.completeProgress;
    }

    private static SimpleContainer getIngredientsInventory(CoffeeMachineBlockEntity blockEntity) {
        SimpleContainer inventory = new SimpleContainer(blockEntity.itemHandler.getSlots() - INGREDIENTS_FIRST_SLOT_INDEX);
        for (int i = 0; i < inventory.getContainerSize(); i++) {
            inventory.setItem(i, blockEntity.itemHandler.getStackInSlot(i + INGREDIENTS_FIRST_SLOT_INDEX));
        }
        return inventory;
    }

    private static void craftItem(CoffeeMachineBlockEntity blockEntity) {
        Optional<CoffeeBrewRecipe> match = blockEntity.level.getRecipeManager().getRecipeFor(
                CoffeeBrewRecipe.Type.INSTANCE,
                getIngredientsInventory(blockEntity),
                blockEntity.level
        );
        RavenCoffee.LOGGER.debug("#########################################");
        RavenCoffee.LOGGER.debug(match.isPresent() ? "MATCHES!" : "DOESNT MATCH");


        blockEntity.itemHandler.extractItem(CUPS_FIRST_SLOT_INDEX, 1, false);
        blockEntity.itemHandler.extractItem(INGREDIENTS_FIRST_SLOT_INDEX, 1, false);

        blockEntity.itemHandler.setStackInSlot(OUTPUT_FIRST_SLOT_INDEX,
                new ItemStack(
                        BrewsRegistry.COFFEE_MUG_BREW_AMERICAN.get(),
                        blockEntity.itemHandler.getStackInSlot(OUTPUT_FIRST_SLOT_INDEX).getCount() + 1
                )
        );
        blockEntity.resetProgress();
    }

    private boolean hasRecipe() {
        return this.itemHandler.getStackInSlot(2).getItem() == ItemsRegistry.COFFEE_BEANS_ROASTED_GROUND.get();
    }

    private boolean hasNotReachedStackLimit() {
        return this.itemHandler.getStackInSlot(0).getCount() < this.itemHandler.getStackInSlot(1).getMaxStackSize();
    }
}
