package com.TheWandererRaven.ravencoffee.util.registries;//package com.TheWandererRaven.ravencoffee.util.registries;

import com.TheWandererRaven.ravencoffee.RavenCoffee;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;

public interface RecipeTypesRegistry {
    RecipeType<CraftingRecipe> COFFEE_GRINDING = register("coffee_grinding");

    /**
     * Registers a new recipe type, prefixing with the mod ID
     * @param name  Recipe type name
     * @param <T>   Recipe type
     * @return  Registered recipe type
     */
    static <T extends Recipe<?>> RecipeType<T> register(String name) {
        return RecipeType.register(RavenCoffee.MOD_ID + ":" + name);
    }
}
