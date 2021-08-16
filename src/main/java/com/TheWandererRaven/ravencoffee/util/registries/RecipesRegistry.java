package com.TheWandererRaven.ravencoffee.util.registries;

import com.TheWandererRaven.ravencoffee.RavenCoffee;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RecipesRegistry {
    public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, RavenCoffee.MOD_ID);
    /*
    public static final RegistryObject<IRecipeSerializer<?>> COFFEE_GRINDER_SERIALIZER = RECIPE_SERIALIZERS.register(
            "coffee_grinder",
            CoffeeGrinderRecipe.Serializer::new
    );
    */
}
