package com.TheWandererRaven.ravencoffee.util.registries;

import com.TheWandererRaven.ravencoffee.RavenCoffee;
import net.minecraft.item.crafting.ICraftingRecipe;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;

public interface RecipeTypesRegistry {
    IRecipeType<ICraftingRecipe> COFFEE_GRINDING = register("coffee_grinding");

    /**
     * Registers a new recipe type, prefixing with the mod ID
     * @param name  Recipe type name
     * @param <T>   Recipe type
     * @return  Registered recipe type
     */
    static <T extends IRecipe<?>> IRecipeType<T> register(String name) {
        return IRecipeType.register(RavenCoffee.MOD_ID + ":" + name);
    }
}
