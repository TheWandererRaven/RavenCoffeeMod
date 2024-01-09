package com.thewandererraven.ravencoffee.recipes;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

import java.util.List;

public class CoffeeGrindingRecipe implements Recipe<Inventory> {
    private final Identifier id;
    private final ItemStack output;
    private final List<Ingredient> recipeItems;

    public CoffeeGrindingRecipe(Identifier id, List<Ingredient> ingredients, ItemStack itemStack) {
        this.id = id;
        this.output = itemStack;
        this.recipeItems = ingredients;
    }

    // #################################################### GENERAL ####################################################
    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public Identifier getId() {
        return this.id;
    }

    // ################################################### CRAFTING ###################################################
    @Override
    public boolean matches(Inventory inventory, World world) {
        if(!world.isClient()) {
            ItemStack craftingFirstItem = inventory.getStack(0);
            ItemStack craftingSecondItem = inventory.getStack(1);
            return (recipeItems.get(0).test(craftingFirstItem) && recipeItems.get(1).test(craftingSecondItem)) ||
                    (recipeItems.get(0).test(craftingSecondItem) && recipeItems.get(1).test(craftingFirstItem));
        }
        return false;
    }

    @Override
    public DefaultedList<Ingredient> getIngredients() {
        DefaultedList<Ingredient> list = DefaultedList.ofSize(this.recipeItems.size());
        list.addAll(this.recipeItems);
        return list;
    }

    @Override
    public ItemStack craft(Inventory inventory) {
        return this.output.copy();
    }
    @Override
    public ItemStack getOutput() {
        return this.output;
    }

    // #################################################### CLASSES ####################################################
    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<CoffeeGrindingRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "coffee_grinding";
    }

    public static class Serializer implements RecipeSerializer<CoffeeGrindingRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final String ID = "coffee_grinding";
        @Override
        public CoffeeGrindingRecipe read(Identifier id, JsonObject json) {
            List<Ingredient> nonnulllist = readIngredients(json.getAsJsonArray("ingredients"));
            if (nonnulllist.isEmpty()) {
                throw new JsonParseException("No ingredients for coffee grinder recipe");
            } else if (nonnulllist.size() > 2) {
                throw new JsonParseException("Too many ingredients for coffee grinder recipe the max is 2");
            } else {
                ItemStack itemstack = ShapedRecipe.outputFromJson(json.getAsJsonObject("result"));
                return new CoffeeGrindingRecipe(id, nonnulllist, itemstack);
            }
        }

        private static List<Ingredient> readIngredients(JsonArray ingredientArray) {
            List<Ingredient> nonnulllist = DefaultedList.of();

            for(int i = 0; i < ingredientArray.size(); ++i) {
                Ingredient ingredient = Ingredient.fromJson(ingredientArray.get(i));
                if (!ingredient.isEmpty()) {
                    nonnulllist.add(ingredient);
                }
            }

            return nonnulllist;
        }

        @Override
        public CoffeeGrindingRecipe read(Identifier id, PacketByteBuf buf) {
            List<Ingredient> inputs = DefaultedList.ofSize(buf.readInt(), Ingredient.EMPTY);

            for(int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromPacket(buf));
            }

            ItemStack output = buf.readItemStack();
            return new CoffeeGrindingRecipe(id, inputs, output);
        }

        @Override
        public void write(PacketByteBuf buf, CoffeeGrindingRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());
            for (Ingredient ingredient : recipe.getIngredients()) {
                ingredient.write(buf);
            }
            buf.writeItemStack(recipe.output);
        }
    }
}
