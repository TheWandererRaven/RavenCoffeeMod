package com.thewandererraven.ravencoffee.blocks.entities;

import com.thewandererraven.ravencoffee.containers.CoffeeMachineMenu;
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

public class CoffeeMachineBlockEntity extends BlockEntity implements MenuProvider {
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
        if(hasRecipe(blockEntity) && hasNotReachedStackLimit(blockEntity)) {
            craftItem(blockEntity);
        }
    }

    private static void craftItem(CoffeeMachineBlockEntity blockEntity) {
        blockEntity.itemHandler.extractItem(0, 1, false);

        blockEntity.itemHandler.setStackInSlot(1,
                new ItemStack(
                        BrewsRegistry.COFFEE_MUG_BREW_AMERICAN.get(),
                        blockEntity.itemHandler.getStackInSlot(1).getCount() + 1
                )
        );
    }

    private static boolean hasRecipe(CoffeeMachineBlockEntity blockEntity) {
        return blockEntity.itemHandler.getStackInSlot(0).getItem() == ItemsRegistry.COFFEE_BEANS_ROASTED_GROUND.get();
    }

    private static boolean hasNotReachedStackLimit(CoffeeMachineBlockEntity blockEntity) {
        return blockEntity.itemHandler.getStackInSlot(1).getCount() < blockEntity.itemHandler.getStackInSlot(1).getMaxStackSize();
    }
}
