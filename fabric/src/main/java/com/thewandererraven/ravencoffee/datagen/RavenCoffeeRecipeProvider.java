package com.thewandererraven.ravencoffee.datagen;

import com.thewandererraven.ravencoffee.items.RavenCoffeeItems;
import com.thewandererraven.ravencoffee.util.RavenCoffeeTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.CookingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

public class RavenCoffeeRecipeProvider extends FabricRecipeProvider {

    public RavenCoffeeRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {

        // ================================================ COFFEE BEANS ===============================================
        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, RavenCoffeeItems.COFFEE_BEANS)
                .input(RavenCoffeeItems.COFFEE_CHERRIES)
                .criterion(hasItem(RavenCoffeeItems.COFFEE_CHERRIES), conditionsFromItem(RavenCoffeeItems.COFFEE_CHERRIES))
                .offerTo(exporter, new Identifier(getRecipeName(RavenCoffeeItems.COFFEE_BEANS)));
        CookingRecipeJsonBuilder.createSmelting(
                Ingredient.ofItems(new ItemConvertible[]{RavenCoffeeItems.COFFEE_BEANS}),
                RecipeCategory.FOOD,
                RavenCoffeeItems.COFFEE_BEANS_ROASTED,
                0.35F,
                200
        )
                .criterion(hasItem(RavenCoffeeItems.COFFEE_CHERRIES), conditionsFromItem(RavenCoffeeItems.COFFEE_CHERRIES))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, RavenCoffeeItems.COFFEE_BEANS_ROASTED_BLOCK_ITEM)
                .pattern("CCC")
                .pattern("CSC")
                .pattern("CCC")
                .input('C', RavenCoffeeItems.COFFEE_BEANS_ROASTED)
                .input('S', Items.SLIME_BALL)
                .criterion(hasItem(RavenCoffeeItems.COFFEE_BEANS_ROASTED), conditionsFromItem(RavenCoffeeItems.COFFEE_BEANS_ROASTED))
                .offerTo(exporter, new Identifier(getRecipeName(RavenCoffeeItems.COFFEE_BEANS_ROASTED_BLOCK_ITEM)));
        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, RavenCoffeeItems.COFFEE_BEANS_ROASTED, 8)
                .input(RavenCoffeeItems.COFFEE_BEANS_ROASTED_BLOCK_ITEM)
                .criterion(hasItem(RavenCoffeeItems.COFFEE_BEANS_ROASTED), conditionsFromItem(RavenCoffeeItems.COFFEE_BEANS_ROASTED))
                .offerTo(exporter, new Identifier(getRecipeName(RavenCoffeeItems.COFFEE_BEANS_ROASTED).concat("_from_block")));
        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, RavenCoffeeItems.COFFEE_BEANS_ROASTED_GROUND, 1)
                .input(RavenCoffeeTags.Items.ROASTED_BEANS)
                .input(RavenCoffeeTags.Items.ROASTED_BEANS)
                .input(RavenCoffeeTags.Items.ROASTED_BEANS)
                .criterion(hasItem(RavenCoffeeItems.COFFEE_CHERRIES), conditionsFromItem(RavenCoffeeItems.COFFEE_CHERRIES))
                .offerTo(exporter, new Identifier(getRecipeName(RavenCoffeeItems.COFFEE_BEANS_ROASTED_GROUND)));

        // ================================================ MAGMA BEANS ===============================================
        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, RavenCoffeeItems.COFFEE_BEANS_MAGMA, 8)
                .pattern("CCC")
                .pattern("CBC")
                .pattern("CCC")
                .input('C', RavenCoffeeItems.COFFEE_BEANS_ROASTED)
                .input('B', Items.BLAZE_POWDER)
                .criterion(hasItem(RavenCoffeeItems.COFFEE_BEANS_ROASTED), conditionsFromItem(RavenCoffeeItems.COFFEE_BEANS_ROASTED))
                .offerTo(exporter, new Identifier(getRecipeName(RavenCoffeeItems.COFFEE_BEANS_MAGMA)));
        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, RavenCoffeeItems.COFFEE_BEANS_MAGMA, 8)
                .input(RavenCoffeeItems.COFFEE_BEANS_MAGMA_BLOCK_ITEM)
                .criterion(hasItem(RavenCoffeeItems.COFFEE_BEANS_ROASTED), conditionsFromItem(RavenCoffeeItems.COFFEE_BEANS_ROASTED))
                .offerTo(exporter, new Identifier(getRecipeName(RavenCoffeeItems.COFFEE_BEANS_MAGMA).concat("_from_block")));
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, RavenCoffeeItems.COFFEE_BEANS_MAGMA_BLOCK_ITEM)
                .pattern("CCC")
                .pattern("CSC")
                .pattern("CCC")
                .input('C', RavenCoffeeItems.COFFEE_BEANS_MAGMA)
                .input('S', Items.SLIME_BALL)
                .criterion(hasItem(RavenCoffeeItems.COFFEE_BEANS_MAGMA), conditionsFromItem(RavenCoffeeItems.COFFEE_BEANS_MAGMA))
                .offerTo(exporter, new Identifier(getRecipeName(RavenCoffeeItems.COFFEE_BEANS_MAGMA_BLOCK_ITEM)));

        // ==================================================== CUPS ===================================================
        ShapelessRecipeJsonBuilder.create(RecipeCategory.BREWING, RavenCoffeeItems.CUP_SMALL_UNFIRED)
                .input(Items.CLAY_BALL)
                .criterion("has_brew_items",
                        conditionsFromItemPredicates(ItemPredicate.Builder.create().items(new ItemConvertible[]{
                                RavenCoffeeItems.COFFEE_BEANS_ROASTED_GROUND,
                                Items.CLAY_BALL
                        }).build())
                ).offerTo(exporter, new Identifier(getRecipeName(RavenCoffeeItems.CUP_SMALL_UNFIRED)));
        CookingRecipeJsonBuilder.createSmelting(
                Ingredient.ofItems(new ItemConvertible[]{RavenCoffeeItems.CUP_SMALL_UNFIRED}),
                RecipeCategory.BREWING,
                RavenCoffeeItems.CUP_SMALL,
                0.1F,
                200
        )
                .criterion(hasItem(RavenCoffeeItems.COFFEE_CHERRIES), conditionsFromItem(RavenCoffeeItems.COFFEE_CHERRIES))
                .offerTo(exporter);
        CookingRecipeJsonBuilder.createBlasting(
                Ingredient.ofItems(new ItemConvertible[]{RavenCoffeeItems.CUP_SMALL_UNFIRED}),
                RecipeCategory.BREWING,
                RavenCoffeeItems.CUP_SMALL,
                0.1F,
                100
        )
                .criterion(hasItem(RavenCoffeeItems.COFFEE_CHERRIES), conditionsFromItem(RavenCoffeeItems.COFFEE_CHERRIES))
                .offerTo(exporter, new Identifier(getRecipeName(RavenCoffeeItems.CUP_SMALL).concat("_from_blasting")));
        ShapedRecipeJsonBuilder.create(RecipeCategory.BREWING, RavenCoffeeItems.CUP_MEDIUM_UNFIRED)
                .pattern("C C")
                .pattern(" C ")
                .input('C', Items.CLAY_BALL)
                .criterion("has_brew_items",
                        conditionsFromItemPredicates(ItemPredicate.Builder.create().items(new ItemConvertible[]{
                                RavenCoffeeItems.COFFEE_BEANS_ROASTED_GROUND,
                                Items.CLAY_BALL
                        }).build())
                ).offerTo(exporter, new Identifier(getRecipeName(RavenCoffeeItems.CUP_MEDIUM_UNFIRED)));
        CookingRecipeJsonBuilder.createSmelting(
                Ingredient.ofItems(new ItemConvertible[]{RavenCoffeeItems.CUP_MEDIUM_UNFIRED}),
                RecipeCategory.BREWING,
                RavenCoffeeItems.CUP_MEDIUM,
                0.1F,
                200
        )
                .criterion(hasItem(RavenCoffeeItems.COFFEE_CHERRIES), conditionsFromItem(RavenCoffeeItems.COFFEE_CHERRIES))
                .offerTo(exporter);
        CookingRecipeJsonBuilder.createBlasting(
                Ingredient.ofItems(new ItemConvertible[]{RavenCoffeeItems.CUP_MEDIUM_UNFIRED}),
                RecipeCategory.BREWING,
                RavenCoffeeItems.CUP_MEDIUM,
                0.1F,
                100
        )
                .criterion(hasItem(RavenCoffeeItems.COFFEE_CHERRIES), conditionsFromItem(RavenCoffeeItems.COFFEE_CHERRIES))
                .offerTo(exporter, new Identifier(getRecipeName(RavenCoffeeItems.CUP_MEDIUM).concat("_from_blasting")));
        ShapedRecipeJsonBuilder.create(RecipeCategory.BREWING, RavenCoffeeItems.CUP_LARGE_UNFIRED)
                .pattern("C C")
                .pattern("C C")
                .pattern(" C ")
                .input('C', Items.CLAY_BALL)
                .criterion("has_brew_items",
                        conditionsFromItemPredicates(ItemPredicate.Builder.create().items(new ItemConvertible[]{
                                RavenCoffeeItems.COFFEE_BEANS_ROASTED_GROUND,
                                Items.CLAY_BALL
                        }).build())
                ).offerTo(exporter, new Identifier(getRecipeName(RavenCoffeeItems.CUP_LARGE_UNFIRED)));
        CookingRecipeJsonBuilder.createSmelting(
                Ingredient.ofItems(new ItemConvertible[]{RavenCoffeeItems.CUP_LARGE_UNFIRED}),
                RecipeCategory.BREWING,
                RavenCoffeeItems.CUP_LARGE,
                0.1F,
                200
        )
                .criterion(hasItem(RavenCoffeeItems.COFFEE_CHERRIES), conditionsFromItem(RavenCoffeeItems.COFFEE_CHERRIES))
                .offerTo(exporter);
        CookingRecipeJsonBuilder.createBlasting(
                Ingredient.ofItems(new ItemConvertible[]{RavenCoffeeItems.CUP_LARGE_UNFIRED}),
                RecipeCategory.BREWING,
                RavenCoffeeItems.CUP_LARGE,
                0.1F,
                100
        )
                .criterion(hasItem(RavenCoffeeItems.COFFEE_CHERRIES), conditionsFromItem(RavenCoffeeItems.COFFEE_CHERRIES))
                .offerTo(exporter, new Identifier(getRecipeName(RavenCoffeeItems.CUP_LARGE).concat("_from_blasting")));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.BREWING, RavenCoffeeItems.COFFEE_MUG)
                .input(RavenCoffeeItems.CUP_MEDIUM)
                .input(Items.WHITE_DYE)
                .criterion("has_brew_items",
                        conditionsFromItemPredicates(ItemPredicate.Builder.create().items(new ItemConvertible[]{
                                RavenCoffeeItems.COFFEE_BEANS_ROASTED_GROUND,
                                Items.CLAY_BALL
                        }).build())
                ).offerTo(exporter, new Identifier(getRecipeName(RavenCoffeeItems.COFFEE_MUG)));

        // =================================================== BAGEL ===================================================
        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, RavenCoffeeItems.BAGEL)
                .pattern(" W ")
                .pattern("W W")
                .pattern(" W ")
                .input('W', Items.WHEAT)
                .criterion(hasItem(Items.WHEAT), conditionsFromItem(Items.WHEAT))
                .offerTo(exporter, new Identifier(getRecipeName(RavenCoffeeItems.BAGEL)));
        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, RavenCoffeeItems.BAGEL_BEEF)
                .input(RavenCoffeeItems.BAGEL)
                .input(Items.COOKED_BEEF)
                .criterion(hasItem(Items.COOKED_BEEF), conditionsFromItem(Items.COOKED_BEEF))
                .criterion(hasItem(Items.WHEAT), conditionsFromItem(Items.WHEAT))
                .offerTo(exporter, new Identifier(getRecipeName(RavenCoffeeItems.BAGEL_BEEF)));
        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, RavenCoffeeItems.BAGEL_CHICKEN)
                .input(RavenCoffeeItems.BAGEL)
                .input(Items.COOKED_CHICKEN)
                .criterion(hasItem(Items.COOKED_CHICKEN), conditionsFromItem(Items.COOKED_CHICKEN))
                .criterion(hasItem(Items.WHEAT), conditionsFromItem(Items.WHEAT))
                .offerTo(exporter, new Identifier(getRecipeName(RavenCoffeeItems.BAGEL_CHICKEN)));
        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, RavenCoffeeItems.BAGEL_HAM)
                .input(RavenCoffeeItems.BAGEL)
                .input(Items.PORKCHOP)
                .criterion(hasItem(Items.PORKCHOP), conditionsFromItem(Items.PORKCHOP))
                .criterion(hasItem(Items.WHEAT), conditionsFromItem(Items.WHEAT))
                .offerTo(exporter, new Identifier(getRecipeName(RavenCoffeeItems.BAGEL_HAM)));

        // ================================================= CROISSANT =================================================
        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, RavenCoffeeItems.CROISSANT)
                .pattern(" W ")
                .pattern("W W")
                .input('W', Items.WHEAT)
                .criterion(hasItem(Items.WHEAT), conditionsFromItem(Items.WHEAT))
                .offerTo(exporter, new Identifier(getRecipeName(RavenCoffeeItems.CROISSANT)));
        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, RavenCoffeeItems.CROISSANT_BEEF)
                .input(RavenCoffeeItems.CROISSANT)
                .input(Items.COOKED_BEEF)
                .criterion(hasItem(Items.COOKED_BEEF), conditionsFromItem(Items.COOKED_BEEF))
                .criterion(hasItem(Items.WHEAT), conditionsFromItem(Items.WHEAT))
                .offerTo(exporter, new Identifier(getRecipeName(RavenCoffeeItems.CROISSANT_BEEF)));
        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, RavenCoffeeItems.CROISSANT_CHICKEN)
                .input(RavenCoffeeItems.CROISSANT)
                .input(Items.COOKED_CHICKEN)
                .criterion(hasItem(Items.COOKED_CHICKEN), conditionsFromItem(Items.COOKED_CHICKEN))
                .criterion(hasItem(Items.WHEAT), conditionsFromItem(Items.WHEAT))
                .offerTo(exporter, new Identifier(getRecipeName(RavenCoffeeItems.CROISSANT_CHICKEN)));
        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, RavenCoffeeItems.CROISSANT_HAM)
                .input(RavenCoffeeItems.CROISSANT)
                .input(Items.PORKCHOP)
                .criterion(hasItem(Items.PORKCHOP), conditionsFromItem(Items.PORKCHOP))
                .criterion(hasItem(Items.WHEAT), conditionsFromItem(Items.WHEAT))
                .offerTo(exporter, new Identifier(getRecipeName(RavenCoffeeItems.CROISSANT_HAM)));

        // ================================================== SANDWICH =================================================
        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, RavenCoffeeItems.SANDWICH_BEEF)
                .input(Items.BREAD)
                .input(Items.COOKED_BEEF)
                .criterion(hasItem(Items.COOKED_BEEF), conditionsFromItem(Items.COOKED_BEEF))
                .criterion(hasItem(Items.WHEAT), conditionsFromItem(Items.WHEAT))
                .offerTo(exporter, new Identifier(getRecipeName(RavenCoffeeItems.SANDWICH_BEEF)));
        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, RavenCoffeeItems.SANDWICH_CHICKEN)
                .input(Items.BREAD)
                .input(Items.COOKED_CHICKEN)
                .criterion(hasItem(Items.COOKED_CHICKEN), conditionsFromItem(Items.COOKED_CHICKEN))
                .criterion(hasItem(Items.WHEAT), conditionsFromItem(Items.WHEAT))
                .offerTo(exporter, new Identifier(getRecipeName(RavenCoffeeItems.SANDWICH_CHICKEN)));
        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, RavenCoffeeItems.SANDWICH_HAM)
                .input(Items.BREAD)
                .input(Items.PORKCHOP)
                .criterion(hasItem(Items.PORKCHOP), conditionsFromItem(Items.PORKCHOP))
                .criterion(hasItem(Items.WHEAT), conditionsFromItem(Items.WHEAT))
                .offerTo(exporter, new Identifier(getRecipeName(RavenCoffeeItems.SANDWICH_HAM)));

        // ================================================= OTHER FOOD ================================================
        offerReversibleCompactingRecipesWithReverseRecipeGroup(exporter, RecipeCategory.FOOD, RavenCoffeeItems.BROWNIE, RecipeCategory.FOOD, RavenCoffeeItems.BROWNIE_BLOCK_ITEM, "brownie_from_brownie_block", null);
        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, RavenCoffeeItems.BROWNIE, 2)
                .pattern("CC")
                .pattern("WW")
                .input('C', Items.COCOA_BEANS)
                .input('W', Items.WHEAT)
                .criterion(hasItem(Items.COCOA_BEANS), conditionsFromItem(Items.COCOA_BEANS))
                .offerTo(exporter, new Identifier(getRecipeName(RavenCoffeeItems.BROWNIE)));
        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, RavenCoffeeItems.COFFEE_ECLAIR, 3)
                .pattern("CCC")
                .pattern("GGG")
                .pattern("WWW")
                .input('C', Items.COCOA_BEANS)
                .input('G', RavenCoffeeItems.COFFEE_BEANS_ROASTED_GROUND)
                .input('W', Items.WHEAT)
                .criterion(hasItem(Items.COCOA_BEANS), conditionsFromItem(Items.COCOA_BEANS))
                .offerTo(exporter, new Identifier(getRecipeName(RavenCoffeeItems.COFFEE_ECLAIR)));
        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, RavenCoffeeItems.MELON_PAN)
                .pattern(" W ")
                .pattern("WMW")
                .pattern(" W ")
                .input('W', Items.WHEAT)
                .input('M', Items.MELON_SLICE)
                .criterion(hasItem(Items.MELON_SLICE), conditionsFromItem(Items.MELON_SLICE))
                .offerTo(exporter, new Identifier(getRecipeName(RavenCoffeeItems.MELON_PAN)));
        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, RavenCoffeeItems.MUFFIN)
                .pattern("W")
                .pattern("P")
                .input('W', Items.WHEAT)
                .input('P', Items.PAPER)
                .criterion("has_brew_items",
                        conditionsFromItemPredicates(ItemPredicate.Builder.create().items(new ItemConvertible[]{
                                Items.WHEAT,
                                Items.PAPER
                        }).build())
                ).offerTo(exporter, new Identifier(getRecipeName(RavenCoffeeItems.MUFFIN)));
        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, RavenCoffeeItems.POPCHORUS)
                .input(Items.POPPED_CHORUS_FRUIT)
                .input(Items.POPPED_CHORUS_FRUIT)
                .input(Items.POPPED_CHORUS_FRUIT)
                .input(Items.BOWL)
                .criterion(hasItem(Items.CHORUS_FRUIT), conditionsFromItem(Items.CHORUS_FRUIT))
                .offerTo(exporter, new Identifier(getRecipeName(RavenCoffeeItems.POPCHORUS)));
        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, RavenCoffeeItems.ROSCA_DE_REYES_BLOCK_ITEM)
                .pattern("SAG")
                .pattern("ZCZ")
                .pattern("BBB")
                .input('S', Items.SWEET_BERRIES)
                .input('A', Items.APPLE)
                .input('G', Items.GLOW_BERRIES)
                .input('Z', Items.SUGAR)
                .input('C', Items.CACTUS)
                .input('B', Items.BREAD)
                .criterion("has_specific_ingredients",
                        conditionsFromItemPredicates(ItemPredicate.Builder.create().items(new ItemConvertible[]{
                                Items.SWEET_BERRIES,
                                Items.GLOW_BERRIES,
                                Items.BREAD
                        }).build())
                ).offerTo(exporter, new Identifier(getRecipeName(RavenCoffeeItems.ROSCA_DE_REYES_BLOCK_ITEM)));
        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, RavenCoffeeItems.TIRAMISU_BLOCK_ITEM)
                .pattern("RCR")
                .pattern("EMZ")
                .pattern("BBB")
                .input('C', Items.COCOA_BEANS)
                .input('E', Items.EGG)
                .input('Z', Items.SUGAR)
                .input('R', RavenCoffeeItems.COFFEE_BEANS_ROASTED_GROUND)
                .input('M', Items.MILK_BUCKET)
                .input('B', Items.BREAD)
                .criterion("has_specific_ingredients",
                        conditionsFromItemPredicates(ItemPredicate.Builder.create().items(new ItemConvertible[]{
                                Items.COCOA_BEANS,
                                Items.EGG,
                                Items.BREAD
                        }).build())
                ).offerTo(exporter, new Identifier(getRecipeName(RavenCoffeeItems.TIRAMISU_BLOCK_ITEM)));

        // ================================================ OTHER ITEMS ===============================================
        ShapedRecipeJsonBuilder.create(RecipeCategory.BREWING, RavenCoffeeItems.COFFEE_GRINDER_ITEM)
                .pattern("PIP")
                .pattern("FFF")
                .pattern("PPP")
                .input('P', ItemTags.PLANKS)
                .input('F', Items.FLINT)
                .input('I', Items.IRON_INGOT)
                .criterion(hasItem(RavenCoffeeItems.COFFEE_BEANS_ROASTED), conditionsFromItem(RavenCoffeeItems.COFFEE_BEANS_ROASTED))
                .offerTo(exporter, new Identifier(getRecipeName(RavenCoffeeItems.COFFEE_GRINDER_ITEM)));
        ShapedRecipeJsonBuilder.create(RecipeCategory.BREWING, RavenCoffeeItems.COFFEE_MACHINE_ITEM)
                .pattern("IGI")
                .pattern("GWG")
                .pattern("IRI")
                .input('I', Items.IRON_INGOT)
                .input('G', Items.GLASS_PANE)
                .input('R', Items.REDSTONE)
                .input('W', Items.WATER_BUCKET)
                .criterion(hasItem(RavenCoffeeItems.COFFEE_BEANS_ROASTED_GROUND), conditionsFromItem(RavenCoffeeItems.COFFEE_BEANS_ROASTED_GROUND))
                .criterion("has_cups", conditionsFromTag(RavenCoffeeTags.Items.CUPS))
                .offerTo(exporter, new Identifier(getRecipeName(RavenCoffeeItems.COFFEE_MACHINE_ITEM)));
        ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, RavenCoffeeItems.SACK_BLOCK_ITEM)
                .pattern("SSS")
                .pattern("S S")
                .pattern("SSS")
                .input('S', Items.STRING)
                .criterion(hasItem(Items.STRING), conditionsFromItem(Items.STRING))
                .offerTo(exporter, new Identifier(getRecipeName(RavenCoffeeItems.SACK_BLOCK_ITEM)));
        CookingRecipeJsonBuilder.createSmelting(
                Ingredient.ofItems(new ItemConvertible[]{RavenCoffeeItems.COFFEE_BEANS_MAGMA_BLOCK_ITEM}),
                RecipeCategory.BUILDING_BLOCKS,
                RavenCoffeeItems.COFFEE_PLATES,
                0.15F,
                200
        )
                .criterion(hasItem(RavenCoffeeItems.COFFEE_BEANS_MAGMA), conditionsFromItem(RavenCoffeeItems.COFFEE_BEANS_MAGMA))
                .offerTo(exporter);
        CookingRecipeJsonBuilder.createBlasting(
                Ingredient.ofItems(new ItemConvertible[]{RavenCoffeeItems.COFFEE_BEANS_MAGMA_BLOCK_ITEM}),
                RecipeCategory.BREWING,
                RavenCoffeeItems.COFFEE_PLATES,
                0.15F,
                100
        )
                .criterion(hasItem(RavenCoffeeItems.COFFEE_BEANS_MAGMA), conditionsFromItem(RavenCoffeeItems.COFFEE_BEANS_MAGMA))
                .offerTo(exporter, new Identifier(getRecipeName(RavenCoffeeItems.COFFEE_PLATES).concat("_from_blasting")));
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, RavenCoffeeItems.COFFEE_INGOT)
                .input(RavenCoffeeItems.COFFEE_PLATES)
                .input(RavenCoffeeItems.COFFEE_PLATES)
                .input(RavenCoffeeItems.COFFEE_PLATES)
                .input(Items.GOLD_INGOT)
                .input(Items.GOLD_INGOT)
                .input(Items.GOLD_INGOT)
                .criterion(hasItem(RavenCoffeeItems.COFFEE_PLATES), conditionsFromItem(RavenCoffeeItems.COFFEE_PLATES))
                .offerTo(exporter, new Identifier(getRecipeName(RavenCoffeeItems.COFFEE_INGOT)));
    }
}
