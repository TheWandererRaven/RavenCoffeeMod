package com.thewandererraven.ravencoffee.screens.slots;

import com.thewandererraven.ravencoffee.blocks.entitites.SackBlockEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;

public class SackContentsSlot extends Slot {
    public SackContentsSlot(Inventory inventory, int index, int xPosition, int yPosition) {
        super(inventory, index, xPosition, yPosition);
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        return true;
    }
}
