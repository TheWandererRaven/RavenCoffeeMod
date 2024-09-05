package com.thewandererraven.ravencoffee.containers.inventory;

import com.thewandererraven.ravencoffee.blocks.entities.SackBlockEntity;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class SackContentsSlot extends Slot {
    public SackContentsSlot(Container container, int index, int xPosition, int yPosition) {
        super(container, index, xPosition, yPosition);
    }

    @Override
    public boolean mayPlace(@NotNull ItemStack stack) {
        return ((SackBlockEntity)container).canPlaceItem(stack);
    }
}
