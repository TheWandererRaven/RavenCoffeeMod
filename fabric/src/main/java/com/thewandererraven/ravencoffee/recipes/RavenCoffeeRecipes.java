package com.thewandererraven.ravencoffee.recipes;

import com.thewandererraven.ravencoffee.Constants;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class RavenCoffeeRecipes {
    public static void registerRecipes() {
        Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(Constants.MOD_ID, CoffeeGrindingRecipe.Serializer.ID),
                CoffeeGrindingRecipe.Serializer.INSTANCE
        );
        Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(Constants.MOD_ID, CoffeeBrewingRecipe.Serializer.ID),
                CoffeeBrewingRecipe.Serializer.INSTANCE
        );
        Registry.register(Registries.RECIPE_TYPE, new Identifier(Constants.MOD_ID, CoffeeGrindingRecipe.Type.ID),
                CoffeeGrindingRecipe.Type.INSTANCE
        );
        Registry.register(Registries.RECIPE_TYPE, new Identifier(Constants.MOD_ID, CoffeeBrewingRecipe.Type.ID),
                CoffeeBrewingRecipe.Type.INSTANCE
        );
    }
}
