package com.TheWandererRaven.ravencoffee.containers.slots;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.GrindstoneContainer;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.Map;

public class CoffeeGrinderOutputSlot extends Slot {

    public CoffeeGrinderOutputSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);

    }
    public boolean isItemValid(ItemStack stack) {
        return false;
    }

    @Nonnull
    @Override
    public ItemStack onTake(PlayerEntity thePlayer, ItemStack stack) {
        this.inventory.removeStackFromSlot(0);
        this.inventory.removeStackFromSlot(1);
        return stack;
    }
}
