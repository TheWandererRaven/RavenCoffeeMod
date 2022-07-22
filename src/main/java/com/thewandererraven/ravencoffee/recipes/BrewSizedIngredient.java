package com.thewandererraven.ravencoffee.recipes;

import com.thewandererraven.ravencoffee.containers.inventory.BrewCupInputSlot;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.Arrays;
import java.util.Locale;

public class BrewSizedIngredient {
    public static final int cupSizesCount = 3;

    private final Ingredient ingredient;
    private final int[] countBySize;
    public static final BrewSizedIngredient EMPTY = new BrewSizedIngredient(Ingredient.EMPTY, 0, 0, 0);
    protected BrewSizedIngredient(Ingredient ingredient, int[] countBySize) {
        this.ingredient = ingredient;
        this.countBySize = countBySize;
    }
    public BrewSizedIngredient(Ingredient ingredient, int smallSize, int mediumSize, int largeSize) {
        this(ingredient, new int[] {smallSize, mediumSize, largeSize});
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public int getCountBySize(BrewCupInputSlot.CupSizes sizeIndex) {
        if(sizeIndex == null) return 0;
        return getCountBySize(sizeIndex.ordinal());
    }

    public int getCountBySize(int sizeIndex) {
        if(sizeIndex >= countBySize.length) return 0;
        return this.countBySize[sizeIndex];
    }

    public int getCountBySize(String size) {
        return switch (size.toLowerCase(Locale.ROOT)) {
            case ("small") -> getCountBySize(0);
            case ("medium") -> getCountBySize(1);
            case ("large") -> getCountBySize(2);
            default -> 0;
        };
    }

    public boolean isSizeListEmpty() {
        return Arrays.stream(this.countBySize).anyMatch(count -> count <= 0);
    }

    public boolean isEmpty() {
        return this.ingredient.isEmpty() || isSizeListEmpty();
    }

}
