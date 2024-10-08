package com.thewandererraven.ravencoffee.recipes;

import com.thewandererraven.ravencoffee.Constants;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RavenCoffeeRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Constants.MOD_ID);

    public static final RegistryObject<RecipeSerializer<?>> COFFEE_GRINDER_SERIALIZER = RECIPE_SERIALIZERS.register(
            "coffee_grinding",
            () -> CoffeeGrinderRecipe.Serializer.INSTANCE
    );

    public static final RegistryObject<RecipeSerializer<?>> COFFEE_BREWING_SERIALIZER = RECIPE_SERIALIZERS.register(
            "coffee_brewing",
            () -> CoffeeBrewRecipe.Serializer.INSTANCE
    );
}
