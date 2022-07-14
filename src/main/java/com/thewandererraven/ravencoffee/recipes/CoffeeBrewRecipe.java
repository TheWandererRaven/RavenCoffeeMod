package com.thewandererraven.ravencoffee.recipes;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.thewandererraven.ravencoffee.RavenCoffee;
import com.thewandererraven.ravencoffee.util.registries.BrewsRegistry;
import com.thewandererraven.ravencoffee.util.registries.ItemsRegistry;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class CoffeeBrewRecipe implements Recipe<SimpleContainer> {
    public static String TypeName = "coffee_brewing";
    private final ResourceLocation id;
    private final NonNullList<Ingredient> recipeItems;
    private final String brewType;
    private final int cookingTime;

    public CoffeeBrewRecipe(ResourceLocation id, String brewType, int cookingTime, NonNullList<Ingredient> recipeItems) {
        this.id = id;
        this.recipeItems = recipeItems;
        this.brewType = brewType;
        this.cookingTime = cookingTime;
    }

    @Override
    public boolean matches(SimpleContainer playerContainer, Level playerLevel) {
        if(!playerLevel.isClientSide()) {
            ItemStack craftingFirstItem = playerContainer.getItem(0);
            /*
            ItemStack craftingSecondItem = playerContainer.getItem(1);
            return (recipeItems.get(0).test(craftingFirstItem) && recipeItems.get(1).test(craftingSecondItem)) ||
                    (recipeItems.get(0).test(craftingSecondItem) && recipeItems.get(1).test(craftingFirstItem));
             */
            return recipeItems.get(0).test(craftingFirstItem);
        }
        return false;
    }

    @Override
    public ItemStack assemble(SimpleContainer p_44001_) {
        return null;
    }

    @Override
    public boolean canCraftInDimensions(int p_43999_, int p_44000_) {
        return true;
    }

    private Item getBrew(Item cup) {
        return BrewsRegistry.BREWS.getEntries().stream().filter(itemRegistryObject ->
            itemRegistryObject.getId().toString().equals(cup.toString())
        ).findFirst().orElse(BrewsRegistry.COFFEE_MUG_BREW_AMERICAN).get();
    }

    @Override
    public ItemStack getResultItem() {
        return new ItemStack(ItemsRegistry.COFFEE_MUG.get());
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return CoffeeBrewRecipe.Type.INSTANCE;
    }

    public static class Type implements RecipeType<CoffeeBrewRecipe> {
        private Type() { }
        public static final CoffeeBrewRecipe.Type INSTANCE = new CoffeeBrewRecipe.Type();
        public static final String ID = String.format("%s.%s", RavenCoffee.MOD_ID, CoffeeBrewRecipe.TypeName);
    }

    public static class Serializer implements RecipeSerializer<CoffeeBrewRecipe> {
        public static final CoffeeBrewRecipe.Serializer INSTANCE = new CoffeeBrewRecipe.Serializer();
        public static final ResourceLocation ID = new ResourceLocation(RavenCoffee.MOD_ID, CoffeeBrewRecipe.TypeName);

        @Override
        public CoffeeBrewRecipe fromJson(ResourceLocation id, JsonObject json) {
            NonNullList<Ingredient> nonnulllist = readIngredients(GsonHelper.getAsJsonArray(json, "ingredients"));
            if (nonnulllist.isEmpty()) {
                throw new JsonParseException("No ingredients for coffee brew recipe");
            } else {
                JsonObject result = GsonHelper.getAsJsonObject(json, "result");
                String brewType = result.get("brewtype").getAsString();
                int cookingTime = result.get("cookingtime").getAsInt();
                return new CoffeeBrewRecipe(id, brewType, cookingTime, nonnulllist);
            }
        }

        private static NonNullList<Ingredient> readIngredients(JsonArray ingredientArray) {
            NonNullList<Ingredient> nonnulllist = NonNullList.create();

            for(int i = 0; i < ingredientArray.size(); ++i) {
                Ingredient ingredient = Ingredient.fromJson(ingredientArray.get(i));
                if (!ingredient.isEmpty()) {
                    nonnulllist.add(ingredient);
                }
            }

            return nonnulllist;
        }

        @Nullable
        @Override
        public CoffeeBrewRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buffer) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(buffer.readInt(), Ingredient.EMPTY);
            for(int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromNetwork(buffer));
            }

            String brewType = buffer.readUtf();
            int cookingTime = buffer.readInt();
            return new CoffeeBrewRecipe(id, brewType, cookingTime, inputs);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, CoffeeBrewRecipe recipe) {
            buffer.writeInt(recipe.getIngredients().size());
            for (Ingredient ingredient : recipe.getIngredients()) {
                ingredient.toNetwork(buffer);
            }
            buffer.writeUtf(recipe.brewType);
            buffer.writeInt(recipe.cookingTime);
        }

        @Override
        public RecipeSerializer<?> setRegistryName(ResourceLocation name) {
            return INSTANCE;
        }

        @Nullable
        @Override
        public ResourceLocation getRegistryName() {
            return ID;
        }

        @Override
        public Class<RecipeSerializer<?>> getRegistryType() {
            return CoffeeBrewRecipe.Serializer.castClass(RecipeSerializer.class);
        }

        private static <G> Class<G> castClass(Class<?> cls) {
            return (Class<G>)cls;
        }
    }
}
