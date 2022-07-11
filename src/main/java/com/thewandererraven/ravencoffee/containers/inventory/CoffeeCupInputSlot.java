package com.thewandererraven.ravencoffee.containers.inventory;

import com.mojang.datafixers.util.Pair;
import com.thewandererraven.ravencoffee.RavenCoffee;
import com.thewandererraven.ravencoffee.util.ModTags;
import com.thewandererraven.ravencoffee.util.registries.ItemsRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class CoffeeCupInputSlot extends SlotItemHandler {
    public ArrayList<Item> Cups = new ArrayList<Item>(Arrays.asList(
            ItemsRegistry.COFFEE_MUG.get(),
            ItemsRegistry.CUP_SMALL.get(),
            ItemsRegistry.CUP_MEDIUM.get(),
            ItemsRegistry.CUP_LARGE.get()
    ));

    public CoffeeCupInputSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
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
}
