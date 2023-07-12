package com.thewandererraven.ravencoffee.recipes;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.thewandererraven.ravencoffee.Constants;
import com.thewandererraven.ravencoffee.containers.inventory.BrewCupInputSlot;
import com.thewandererraven.ravencoffee.items.RavenCoffeeBrewItems;
import com.thewandererraven.ravencoffee.items.RavenCoffeeItems;
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
    private final boolean[] acceptedCupSizes;
    private final NonNullList<BrewSizedIngredient> recipeItems;
    private final String brewType;
    private final int cookingTime;

    public CoffeeBrewRecipe(ResourceLocation id, String brewType, int cookingTime, boolean[] acceptedCupSizes, NonNullList<BrewSizedIngredient> recipeItems) {
        this.id = id;
        this.acceptedCupSizes = acceptedCupSizes;
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
            if(isCupSizeForBrew(BrewCupInputSlot.getCupSize(cup))) {
                matchesRecipe = true;
                // Go through all recipe ingredients and compare...
                for (int i = 0; i < recipeItems.size(); i++) {
                    // ...If the ingredient is not the same...
                    if (!(recipeItems.get(i).getIngredient().test(playerContainer.getItem(i)) &&
                            // ...Or if the amount on the stack matches is lesser than the needed for the cup size
                            recipeItems.get(i).getCountBySize(BrewCupInputSlot.getCupSize(cup)) <= playerContainer.getItem(i).getCount())) {
                        matchesRecipe = false;
                        break;
                    }
                }
            }
        }
        return matchesRecipe;
    }

    public boolean isCupSizeForBrew(BrewCupInputSlot.CupSizes sizeIndex) {
        if(sizeIndex == null) return false;
        return isCupSizeForBrew(sizeIndex.ordinal());
    }

    public boolean isCupSizeForBrew(int sizeIndex) {
        if(sizeIndex >= acceptedCupSizes.length) return false;
        return acceptedCupSizes[sizeIndex];
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
        String BrewId = Constants.MOD_ID + ":" + cup + "_" + this.brewType;
        return RavenCoffeeBrewItems.BREWS.getEntries().stream().filter(itemRegistryObject ->
                itemRegistryObject.getId().toString().equals(BrewId)
        ).findFirst().orElse(RavenCoffeeBrewItems.COFFEE_MUG_BREW_BASIC).get();
    }

    @Override
    public ItemStack getResultItem() {
        return new ItemStack(RavenCoffeeItems.COFFEE_MUG.get());
    }

    public ItemStack getResultItem(ItemStack cup, int count) {
        ItemStack resItem = new ItemStack(
                getBrew(cup.getItem()),
                count
        );
        if(!cup.getHoverName().getContents().equals(cup.getItem().getName(cup).getContents()))
            resItem.setHoverName(cup.getHoverName());
        return resItem;
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
        public static final String ID = String.format("%s.%s", Constants.MOD_ID, CoffeeBrewRecipe.TypeName);
    }

    public static class Serializer implements RecipeSerializer<CoffeeBrewRecipe> {
        public static final CoffeeBrewRecipe.Serializer INSTANCE = new CoffeeBrewRecipe.Serializer();
        public static final ResourceLocation ID = new ResourceLocation(Constants.MOD_ID, CoffeeBrewRecipe.TypeName);

        @Override
        public CoffeeBrewRecipe fromJson(ResourceLocation id, JsonObject json) {
            NonNullList<BrewSizedIngredient> brewSizedIngredients = readBrewIngredients(GsonHelper.getAsJsonArray(json, "brewingredients"));
            if (brewSizedIngredients.isEmpty())
                throw new JsonParseException("No ingredients for coffee brew recipe");
            boolean[] acceptedCupSizes = readBrewAcceptedSizes(GsonHelper.getAsJsonArray(json, "forCupSizes"));
            JsonObject result = GsonHelper.getAsJsonObject(json, "result");
            String brewType = result.get("brewtype").getAsString();
            int cookingTime = result.get("cookingtime").getAsInt();
            return new CoffeeBrewRecipe(id, brewType, cookingTime, acceptedCupSizes, brewSizedIngredients);
        }

        private static boolean[] readBrewAcceptedSizes(JsonArray acceptedCupsArray) {
            boolean[] acceptedCups = new boolean[acceptedCupsArray.size()];
            for(int i = 0; i < acceptedCupsArray.size(); ++i) {
                acceptedCups[i] = acceptedCupsArray.get(i).getAsBoolean();
            }
            return acceptedCups;
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
            boolean[] acceptedCupSizes = new boolean[buffer.readInt()];
            for(int i = 0; i < acceptedCupSizes.length; i++) {
                acceptedCupSizes[i] = buffer.readBoolean();
            }
            String brewType = buffer.readUtf();
            int cookingTime = buffer.readInt();
            return new CoffeeBrewRecipe(id, brewType, cookingTime, acceptedCupSizes, inputs);
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
            buffer.writeInt(recipe.recipeItems.size());
            for (BrewSizedIngredient sizedIngredient : recipe.recipeItems) {
                sizedIngredient.getIngredient().toNetwork(buffer);
                for(int i = 0; i < BrewSizedIngredient.cupSizesCount; i++) {
                    buffer.writeInt(sizedIngredient.getCountBySize(i));
                }
            }
            buffer.writeInt(recipe.acceptedCupSizes.length);
            for (boolean acceptedCupSize : recipe.acceptedCupSizes) {
                buffer.writeBoolean(acceptedCupSize);
            }
            buffer.writeUtf(recipe.brewType);
            buffer.writeInt(recipe.cookingTime);
        }

        private static <G> Class<G> castClass(Class<?> cls) {
            return (Class<G>)cls;
        }
    }
}
