package com.thewandererraven.ravencoffee.recipes;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.thewandererraven.ravencoffee.RavenCoffee;
import com.thewandererraven.ravencoffee.containers.inventory.BrewCupInputSlot;
import com.thewandererraven.ravencoffee.util.registries.BrewsRegistry;
import com.thewandererraven.ravencoffee.util.registries.ItemsRegistry;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CoffeeBrewRecipe implements Recipe<SimpleContainer> {
    public static String TypeName = "coffee_brewing";
    private final ResourceLocation id;
    private final NonNullList<BrewSizedIngredient> recipeItems;
    private final String brewType;
    private final int cookingTime;

    public CoffeeBrewRecipe(ResourceLocation id, String brewType, int cookingTime, NonNullList<BrewSizedIngredient> recipeItems) {
        this.id = id;
        this.recipeItems = recipeItems;
        this.brewType = brewType;
        this.cookingTime = cookingTime;
    }

    @Override
    public ItemStack assemble(SimpleContainer inventory) {
        return null;
    }

    @Override
    public boolean canCraftInDimensions(int p_43999_, int p_44000_) {
        return true;
    }

    @Override
    public @NotNull ResourceLocation getId() {
        return id;
    }

    public int getCookingTime() {
        return this.cookingTime;
    }

    @Override
    public boolean matches(SimpleContainer p_44002_, Level p_44003_) {
        return false;
    }

    public boolean matches(SimpleContainer playerContainer, ItemStack cup, Level playerLevel) {
        boolean matchesRecipe = false;
        if(!playerLevel.isClientSide()) {
            matchesRecipe = true;
            // Go through all recipe ingredients and compare...
            for(int i = 0; i < recipeItems.size(); i++) {
                // ...If the ingredient is not the same...
                if(!(recipeItems.get(i).getIngredient().test(playerContainer.getItem(i)) &&
                        // ...Or if the amount on the stack matches is lesser than the needed for the cup size
                        recipeItems.get(i).getCountBySize(BrewCupInputSlot.getCupSize(cup)) <= playerContainer.getItem(i).getCount())) {
                    matchesRecipe = false;
                    break;
                }
            }
        }
        return matchesRecipe;
    }

    public BrewSizedIngredient getMatchingIngredient(ItemStack item) {
        for(BrewSizedIngredient sizedIngredient : this.recipeItems) {
            if (sizedIngredient.getIngredient().test(item))
                return sizedIngredient;
        }
        return null;
    }

    // ================================================ OUTPUT ================================================
    private Item getBrew(Item cup) {
        String BrewId = RavenCoffee.MOD_ID + ":" + cup + "_" + this.brewType;
        return BrewsRegistry.BREWS.getEntries().stream().filter(itemRegistryObject ->
                itemRegistryObject.getId().toString().equals(BrewId)
        ).findFirst().orElse(BrewsRegistry.COFFEE_MUG_BREW_AMERICAN).get();
    }

    @Override
    public ItemStack getResultItem() {
        return new ItemStack(ItemsRegistry.COFFEE_MUG.get());
    }

    public ItemStack getResultItem(Item cup, int count) {
        return new ItemStack(
                getBrew(cup),
                count
        );
    }

    // ================================================ CLASSES ================================================
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
            NonNullList<BrewSizedIngredient> brewSizedIngredients = readBrewIngredients(GsonHelper.getAsJsonArray(json, "brewingredients"));
            if (brewSizedIngredients.isEmpty()) {
                throw new JsonParseException("No ingredients for coffee brew recipe");
            } else {
                JsonObject result = GsonHelper.getAsJsonObject(json, "result");
                String brewType = result.get("brewtype").getAsString();
                int cookingTime = result.get("cookingtime").getAsInt();
                return new CoffeeBrewRecipe(id, brewType, cookingTime, brewSizedIngredients);
            }
        }

        private static NonNullList<BrewSizedIngredient> readBrewIngredients(JsonArray ingredientArray) {
            NonNullList<BrewSizedIngredient> nonnulllist = NonNullList.create();

            for(int i = 0; i < ingredientArray.size(); ++i) {
                JsonArray ingredientspersizeJSON = ingredientArray.get(i).getAsJsonObject().get("ingredientspersize").getAsJsonArray();
                int[] ingredientsPerSize = new int[ingredientspersizeJSON.size()];
                for(int j = 0; j < ingredientspersizeJSON.size(); j++)
                    ingredientsPerSize[j] = ingredientspersizeJSON.get(j).getAsInt();

                BrewSizedIngredient sizedIngredient = new BrewSizedIngredient(Ingredient.fromJson(ingredientArray.get(i)), ingredientsPerSize);
                if (!sizedIngredient.isEmpty()) {
                    nonnulllist.add(sizedIngredient);
                }
            }
            return nonnulllist;
        }

        @Nullable
        @Override
        public CoffeeBrewRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buffer) {
            NonNullList<BrewSizedIngredient> inputs = NonNullList.withSize(buffer.readInt(), BrewSizedIngredient.EMPTY);
            for(int i = 0; i < inputs.size(); i++) {
                inputs.set(i, new BrewSizedIngredient(Ingredient.fromNetwork(buffer), getSizesFromNetwork(buffer)));
            }

            String brewType = buffer.readUtf();
            int cookingTime = buffer.readInt();
            return new CoffeeBrewRecipe(id, brewType, cookingTime, inputs);
        }

        public int[] getSizesFromNetwork(FriendlyByteBuf buffer) {
            int[] array = new int[BrewSizedIngredient.cupSizesCount];
            for (int i = 0; i < array.length; i++) {
                array[i] = buffer.readInt();
            }
            return array;
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, CoffeeBrewRecipe recipe) {
            buffer.writeInt(recipe.getIngredients().size());
            for (BrewSizedIngredient sizedIngredient : recipe.recipeItems) {
                sizedIngredient.getIngredient().toNetwork(buffer);
                for(int i = 0; i < BrewSizedIngredient.cupSizesCount; i++) {
                    buffer.writeInt(sizedIngredient.getCountBySize(i));
                }
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
