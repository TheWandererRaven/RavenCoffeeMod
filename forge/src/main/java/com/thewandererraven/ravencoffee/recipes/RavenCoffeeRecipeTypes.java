package com.thewandererraven.ravencoffee.recipes;

import com.thewandererraven.ravencoffee.Constants;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public interface RavenCoffeeRecipeTypes {
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, Constants.MOD_ID);

    RegistryObject<RecipeType<?>> COFFEE_GRINDING = RECIPE_TYPES.register(CoffeeGrinderRecipe.Type.ID, () -> CoffeeGrinderRecipe.Type.INSTANCE);
    RegistryObject<RecipeType<?>> COFFEE_BREWING = RECIPE_TYPES.register(CoffeeBrewRecipe.Type.ID, () -> CoffeeBrewRecipe.Type.INSTANCE);

}
