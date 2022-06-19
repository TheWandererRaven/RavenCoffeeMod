package com.thewandererraven.ravencoffee.containers.inventory;

import com.thewandererraven.ravencoffee.util.registries.ItemsRegistry;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

public class CoffeePowderInputSlot extends SlotItemHandler {
    public CoffeePowderInputSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
    }

    @Override
    public boolean mayPlace(@NotNull ItemStack stack) {
        return stack.getItem().equals(ItemsRegistry.COFFEE_BEANS_ROASTED_GROUND.get());
    }
}
