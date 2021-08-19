package com.TheWandererRaven.ravencoffee.util.registries;

import net.minecraft.item.crafting.ICraftingRecipe;
import net.minecraft.item.crafting.IRecipeType;

public interface RecipeTypesRegistry {
    public static IRecipeType<ICraftingRecipe> COFFEE_GRINDER = IRecipeType.register("coffee_grinder");
}
