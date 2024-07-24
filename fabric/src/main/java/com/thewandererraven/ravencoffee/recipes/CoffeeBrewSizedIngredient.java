package com.thewandererraven.ravencoffee.recipes;

import com.thewandererraven.ravencoffee.util.Cups;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Locale;

public class CoffeeBrewSizedIngredient {
    public static final int cupSizesCount = 3;

    private final Ingredient ingredient;
    private final int[] countBySize;
    public static final CoffeeBrewSizedIngredient EMPTY = new CoffeeBrewSizedIngredient(Ingredient.EMPTY, 0, 0, 0);
    protected CoffeeBrewSizedIngredient(Ingredient ingredient, int[] countBySize) {
        this.ingredient = ingredient;
        this.countBySize = countBySize;
    }

    public CoffeeBrewSizedIngredient(Ingredient ingredient, int smallSize, int mediumSize, int largeSize) {
        this(ingredient, new int[] {smallSize, mediumSize, largeSize});
    }

    public boolean test(@Nullable ItemStack itemStack) {
        return getIngredient().test(itemStack);
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public int getCountBySize(Cups.Sizes sizeIndex) {
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
        return this.ingredient.isEmpty();// || isSizeListEmpty();
    }

}
