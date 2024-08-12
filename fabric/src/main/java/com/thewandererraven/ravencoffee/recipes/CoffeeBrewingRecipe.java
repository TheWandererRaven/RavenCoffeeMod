package com.thewandererraven.ravencoffee.recipes;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.thewandererraven.ravencoffee.Constants;
import com.thewandererraven.ravencoffee.items.RavenCoffeeBrewItems;
import com.thewandererraven.ravencoffee.items.RavenCoffeeItems;
import com.thewandererraven.ravencoffee.util.Cups;
import com.thewandererraven.ravencoffee.util.RavenCoffeeTags;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CoffeeBrewingRecipe implements Recipe<SimpleInventory> {
    public static String TypeName = "coffee_brewing";
    private final Identifier id;
    private final boolean[] acceptedCupSizes;
    private final List<CoffeeBrewSizedIngredient> recipeItems;

    private final String brewType;
    private final int cookingTime;

    public CoffeeBrewingRecipe(Identifier id, String brewType, int cookingTime, boolean[] acceptedCupSizes, List<CoffeeBrewSizedIngredient> recipeItems) {
        this.id = id;
        this.acceptedCupSizes = acceptedCupSizes;
        this.recipeItems = recipeItems;
        this.brewType = brewType;
        this.cookingTime = cookingTime;
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

    public int getCookingTime() {
        return cookingTime;
    }

    // ################################################### CRAFTING ###################################################
    @Override
    public boolean matches(SimpleInventory inventory, World world) {
        return false;
    }

    public boolean matches(SimpleInventory playerContainer, Item cup, World playerLevel) {
        boolean matchesRecipe = false;
        if(!playerLevel.isClient) {
            if(isCupSizeForBrew(Cups.getCupSize(cup))) {
                matchesRecipe = true;
                // Go through all recipe ingredients and compare...
                for (int i = 0; i < recipeItems.size(); i++) {
                    // ...If the ingredient is not the same...
                    if (!(recipeItems.get(i).getIngredient().test(playerContainer.getStack(i)) &&
                            // ...Or if the amount on the stack matches is lesser than the needed for the cup size
                            recipeItems.get(i).getCountBySize(Cups.getCupSize(cup)) <= playerContainer.getStack(i).getCount())) {
                        matchesRecipe = false;
                        break;
                    }
                }
            }
        }
        return matchesRecipe;
    }

    public boolean isCupSizeForBrew(Cups.Sizes sizeIndex) {
        if(sizeIndex == null) return false;
        return isCupSizeForBrew(sizeIndex.ordinal());
    }

    public boolean isCupSizeForBrew(int sizeIndex) {
        if(sizeIndex >= acceptedCupSizes.length) return false;
        return acceptedCupSizes[sizeIndex];
    }

    @Override
    public DefaultedList<Ingredient> getIngredients() {
        return DefaultedList.of();
    }

    public CoffeeBrewSizedIngredient getMatchingIngredient(ItemStack item) {
        for(CoffeeBrewSizedIngredient sizedIngredient : this.recipeItems) {
            if (sizedIngredient.getIngredient().test(item))
                return sizedIngredient;
        }
        return null;
    }

    @Override
    public ItemStack craft(SimpleInventory inventory) {
        return ItemStack.EMPTY;
    }


    public static boolean isBrewIngredient(@NotNull ItemStack stack){
        return stack.isIn(RavenCoffeeTags.Items.BREW_INGREDIENTS);
    }
    // ================================================ OUTPUT ================================================

    private Item getBrew(Item cup) {
        String BrewId = Constants.MOD_ID + ":" + cup + "_" + this.brewType;
        return Registries.ITEM
                .getOrEmpty(new Identifier(BrewId))
                .orElse(RavenCoffeeBrewItems.COFFEE_MUG_BREW_BASIC).asItem();
    }

    @Override
    public ItemStack getOutput() {
        return new ItemStack(RavenCoffeeItems.COFFEE_MUG);
    }

    public ItemStack getOutput(ItemStack cup, int count) {
        return getOutput(cup.getItem(), count);
    }

    public ItemStack getOutput(Item cup) {
        return getOutput(cup, 1);
    }

    public ItemStack getOutput(Item cup, int count) {
        return new ItemStack(
                getBrew(cup),
                count
        );
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

    public static class Type implements RecipeType<CoffeeBrewingRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = TypeName;
    }

    public static class Serializer implements RecipeSerializer<CoffeeBrewingRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final String ID = TypeName;
        @Override
        public CoffeeBrewingRecipe read(Identifier id, JsonObject json) {
            List<CoffeeBrewSizedIngredient> brewSizedIngredients = readBrewIngredients(json.getAsJsonArray("brewingredients"));
            if (brewSizedIngredients.isEmpty())
                throw new JsonParseException("No ingredients for coffee brew recipe");
            boolean[] acceptedCupSizes = readBrewAcceptedSizes(json.getAsJsonArray("forCupSizes"));
            JsonObject result = json.getAsJsonObject("result");
            String brewType = result.get("brewtype").getAsString();
            int cookingTime = result.get("cookingtime").getAsInt();
            return new CoffeeBrewingRecipe(id, brewType, cookingTime, acceptedCupSizes, brewSizedIngredients);
        }
        @Override
        public CoffeeBrewingRecipe read(Identifier id, PacketByteBuf buffer) {
            List<CoffeeBrewSizedIngredient> inputs = DefaultedList.ofSize(buffer.readInt(), CoffeeBrewSizedIngredient.EMPTY);
            for(int i = 0; i < inputs.size(); i++) {
                inputs.set(i, new CoffeeBrewSizedIngredient(Ingredient.fromPacket(buffer), getSizesFromNetwork(buffer)));
            }
            boolean[] acceptedCupSizes = new boolean[buffer.readInt()];
            for(int i = 0; i < acceptedCupSizes.length; i++) {
                acceptedCupSizes[i] = buffer.readBoolean();
            }
            String brewType = buffer.readString();
            int cookingTime = buffer.readInt();
            return new CoffeeBrewingRecipe(id, brewType, cookingTime, acceptedCupSizes, inputs);
        }

        @Override
        public void write(PacketByteBuf buffer, CoffeeBrewingRecipe recipe) {
            buffer.writeInt(recipe.recipeItems.size());
            for (CoffeeBrewSizedIngredient sizedIngredient : recipe.recipeItems) {
                sizedIngredient.getIngredient().write(buffer);
                for(int i = 0; i < CoffeeBrewSizedIngredient.cupSizesCount; i++) {
                    buffer.writeInt(sizedIngredient.getCountBySize(i));
                }
            }
            buffer.writeInt(recipe.acceptedCupSizes.length);
            for (boolean acceptedCupSize : recipe.acceptedCupSizes) {
                buffer.writeBoolean(acceptedCupSize);
            }
            buffer.writeString(recipe.brewType);
            buffer.writeInt(recipe.cookingTime);
        }

        private static List<CoffeeBrewSizedIngredient> readBrewIngredients(JsonArray ingredientArray) {
            List<CoffeeBrewSizedIngredient> nonnulllist = DefaultedList.of();

            for(int i = 0; i < ingredientArray.size(); ++i) {
                JsonArray ingredientspersizeJSON = ingredientArray.get(i).getAsJsonObject().get("ingredientspersize").getAsJsonArray();
                int[] ingredientsPerSize = new int[ingredientspersizeJSON.size()];
                for(int j = 0; j < ingredientspersizeJSON.size(); j++)
                    ingredientsPerSize[j] = ingredientspersizeJSON.get(j).getAsInt();

                CoffeeBrewSizedIngredient sizedIngredient = new CoffeeBrewSizedIngredient(Ingredient.fromJson(ingredientArray.get(i)), ingredientsPerSize);
                if (!sizedIngredient.isEmpty()) {
                    nonnulllist.add(sizedIngredient);
                }
            }

            return nonnulllist;
        }

        private static boolean[] readBrewAcceptedSizes(JsonArray acceptedCupsArray) {
            boolean[] acceptedCups = new boolean[acceptedCupsArray.size()];
            for(int i = 0; i < acceptedCupsArray.size(); ++i) {
                acceptedCups[i] = acceptedCupsArray.get(i).getAsBoolean();
            }
            return acceptedCups;
        }

        public int[] getSizesFromNetwork(PacketByteBuf buffer) {
            int[] array = new int[CoffeeBrewSizedIngredient.cupSizesCount];
            for (int i = 0; i < array.length; i++) {
                array[i] = buffer.readInt();
            }
            return array;
        }
    }
}
