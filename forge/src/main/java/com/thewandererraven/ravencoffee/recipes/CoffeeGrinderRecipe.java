package com.thewandererraven.ravencoffee.recipes;

import com.thewandererraven.ravencoffee.Constants;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class CoffeeGrinderRecipe implements Recipe<CraftingContainer> {
    public static String TypeName = "coffee_grinding";
    private final ResourceLocation id;
    private final ItemStack output;
    private final NonNullList<Ingredient> recipeItems;

    public CoffeeGrinderRecipe(ResourceLocation id, ItemStack output, NonNullList<Ingredient> recipeItems) {
        this.id = id;
        this.output = output;
        this.recipeItems = recipeItems;
    }

    @Override
    public boolean matches(CraftingContainer playerContainer, Level playerLevel) {
        if(!playerLevel.isClientSide()) {
            ItemStack craftingFirstItem = playerContainer.getItem(0);
            ItemStack craftingSecondItem = playerContainer.getItem(1);
            return (recipeItems.get(0).test(craftingFirstItem) && recipeItems.get(1).test(craftingSecondItem)) ||
                    (recipeItems.get(0).test(craftingSecondItem) && recipeItems.get(1).test(craftingFirstItem));
        }
        return false;
    }

    @Override
    public ItemStack assemble(CraftingContainer inventory, RegistryAccess registryAccess) {
        return output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int p_43999_, int p_44000_) {
        return true;
    }


    @Override
    public ItemStack getResultItem(RegistryAccess p_267052_) {
        return output.copy();
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
        return CoffeeGrinderRecipe.Type.INSTANCE;
    }

    public static class Type implements RecipeType<CoffeeGrinderRecipe> {
        private Type() { }
        public static final CoffeeGrinderRecipe.Type INSTANCE = new CoffeeGrinderRecipe.Type();
        public static final String ID = String.format("%s.%s", Constants.MOD_ID, CoffeeGrinderRecipe.TypeName);
    }

    public static class Serializer implements RecipeSerializer<CoffeeGrinderRecipe> {
        public static final CoffeeGrinderRecipe.Serializer INSTANCE = new CoffeeGrinderRecipe.Serializer();
        public static final ResourceLocation ID = new ResourceLocation(Constants.MOD_ID, CoffeeGrinderRecipe.TypeName);

        @Override
        public CoffeeGrinderRecipe fromJson(ResourceLocation id, JsonObject json) {
            NonNullList<Ingredient> nonnulllist = readIngredients(GsonHelper.getAsJsonArray(json, "ingredients"));
            if (nonnulllist.isEmpty()) {
                throw new JsonParseException("No ingredients for coffee grinder recipe");
            } else if (nonnulllist.size() > 2) {
                throw new JsonParseException("Too many ingredients for coffee grinder recipe the max is 2");
            } else {
                ItemStack itemstack = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "result"));
                return new CoffeeGrinderRecipe(id, itemstack, nonnulllist);
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
        public CoffeeGrinderRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buffer) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(buffer.readInt(), Ingredient.EMPTY);

            for(int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromNetwork(buffer));
            }

            ItemStack output = buffer.readItem();
            return new CoffeeGrinderRecipe(id, output, inputs);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, CoffeeGrinderRecipe recipe) {
            buffer.writeInt(recipe.getIngredients().size());
            for (Ingredient ingredient : recipe.getIngredients()) {
                ingredient.toNetwork(buffer);
            }
            buffer.writeItemStack(recipe.getResultItem(null), false);
        }

        private static <G> Class<G> castClass(Class<?> cls) {
            return (Class<G>)cls;
        }
    }
}
