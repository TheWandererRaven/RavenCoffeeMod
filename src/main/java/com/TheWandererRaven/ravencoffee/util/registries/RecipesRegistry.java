package com.TheWandererRaven.ravencoffee.util.registries;//package com.TheWandererRaven.ravencoffee.util.registries;

import com.TheWandererRaven.ravencoffee.RavenCoffee;
import com.TheWandererRaven.ravencoffee.recipes.CoffeeGrinderRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RecipesRegistry {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, RavenCoffee.MOD_ID);

    public static final RegistryObject<RecipeSerializer<?>> COFFEE_GRINDER_SERIALIZER = RECIPE_SERIALIZERS.register(
            "coffee_grinding",
            CoffeeGrinderRecipe.Serializer::new
    );
}
