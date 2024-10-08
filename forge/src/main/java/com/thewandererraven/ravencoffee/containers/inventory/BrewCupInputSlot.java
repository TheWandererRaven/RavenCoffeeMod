package com.thewandererraven.ravencoffee.containers.inventory;

import com.thewandererraven.ravencoffee.items.RavenCoffeeItems;
import com.thewandererraven.ravencoffee.util.ModTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;

public class BrewCupInputSlot extends SlotItemHandler {
    public ArrayList<Item> Cups = new ArrayList<Item>(Arrays.asList(
            RavenCoffeeItems.COFFEE_MUG.get(),
            RavenCoffeeItems.CUP_SMALL.get(),
            RavenCoffeeItems.CUP_MEDIUM.get(),
            RavenCoffeeItems.CUP_LARGE.get()
    ));

    public BrewCupInputSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
    }

    @Override
    public boolean mayPlace(@NotNull ItemStack stack) {
        //return Cups.contains(stack.getItem());
        return isCup(stack);
    }

    public static boolean isCup(ItemStack item) {
        //return Registry.ITEM.getHolderOrThrow(Registry.ITEM.getResourceKey(item.getItem()).get()).is(ModTags.Items.CUPS);
        return item.is(ModTags.Items.CUPS);
    }

    public static String getCupSizeName(ItemStack item) {
        return switch (getCupSize(item)) {
            case SMALL -> "small";
            case MEDIUM -> "medium";
            case LARGE -> "large";
            default -> "no size";
        };
    }

    public static CupSizes getCupSize(ItemStack item) {
        if(item.is(ModTags.Items.CUPS_SMALL)) return CupSizes.SMALL;
        if(item.is(ModTags.Items.CUPS_MEDIUM)) return CupSizes.MEDIUM;
        if(item.is(ModTags.Items.CUPS_LARGE)) return CupSizes.LARGE;
        return null;
    }

    public enum CupSizes {
        SMALL,
        MEDIUM,
        LARGE
    }
}
