package com.thewandererraven.ravencoffee.villagers;

import com.thewandererraven.ravencoffee.items.RavenCoffeeBrewItems;
import com.thewandererraven.ravencoffee.items.RavenCoffeeItems;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;

import java.util.List;
import java.util.function.Consumer;

public class RavenCoffeeVillagerTrades {
    public static final void addTradeOffersToVillagerLevelOne(List<TradeOffers.Factory> factories) {
        // =============================== OTHERS ===============================
        factories.add(((entity, random) ->
                new TradeOffer(
                        new ItemStack(RavenCoffeeItems.COFFEE_BEANS_ROASTED_GROUND, 10),
                        new ItemStack(Items.EMERALD, 1),
                        7, // Max uses
                        3, // Vill exp
                        0.2F // Price mult
                )
        ));
        factories.add(((entity, random) ->
                new TradeOffer(
                        new ItemStack(Items.EMERALD, 2),
                        new ItemStack(RavenCoffeeItems.COFFEE_BEANS_ROASTED, 10),
                        new ItemStack(RavenCoffeeItems.COFFEE_BEANS_ROASTED_GROUND, 10),
                        10, // Max uses
                        3, // Vill exp
                        0.2F // Price mult
                )
        ));
        factories.add(((entity, random) ->
                new TradeOffer(
                        new ItemStack(RavenCoffeeItems.COFFEE_BEANS_ROASTED, 30),
                        new ItemStack(Items.EMERALD, 2),
                        7, // Max uses
                        3, // Vill exp
                        0.2F // Price mult
                )
        ));
        factories.add(((entity, random) ->
                new TradeOffer(
                        new ItemStack(Items.EMERALD, 2),
                        new ItemStack(RavenCoffeeItems.COFFEE_BEANS_ROASTED, 10),
                        new ItemStack(RavenCoffeeItems.COFFEE_BEANS_ROASTED_GROUND, 10),
                        10, // Max uses
                        3, // Vill exp
                        0.2F // Price mult
                )
        ));
        // =============================== CUPS ===============================
        factories.add(((entity, random) ->
                new TradeOffer(
                        new ItemStack(Items.EMERALD, 1),
                        new ItemStack(RavenCoffeeItems.CUP_SMALL, 1),
                        3, // Max uses
                        1, // Vill exp
                        0F // Price mult
                )
        ));
        factories.add(((entity, random) ->
                new TradeOffer(
                        new ItemStack(Items.EMERALD, 1),
                        new ItemStack(RavenCoffeeItems.CUP_MEDIUM, 1),
                        3, // Max uses
                        1, // Vill exp
                        0.2F // Price mult
                )
        ));
        factories.add(((entity, random) ->
                new TradeOffer(
                        new ItemStack(Items.EMERALD, 1),
                        new ItemStack(RavenCoffeeItems.COFFEE_MUG, 1),
                        3, // Max uses
                        1, // Vill exp
                        2F // Price mult
                )
        ));
        factories.add(((entity, random) ->
                new TradeOffer(
                        new ItemStack(Items.EMERALD, 1),
                        new ItemStack(RavenCoffeeItems.CUP_LARGE, 1),
                        3, // Max uses
                        1, // Vill exp
                        2F // Price mult
                )
        ));
    }
    
    public static final void addTradeOffersToVillagerLevelTwo(List<TradeOffers.Factory> factories) {
        // =============================== OTHERS ===============================
        factories.add(((entity, random) ->
                new TradeOffer(
                        new ItemStack(Items.EMERALD, 4),
                        new ItemStack(RavenCoffeeItems.COFFEE_BEANS_ROASTED, 20),
                        new ItemStack(RavenCoffeeItems.COFFEE_BEANS_ROASTED_GROUND, 20),
                        10, // Max uses
                        5, // Vill exp
                        0.2F // Price mult
                )
        ));
        factories.add(((entity, random) ->
                new TradeOffer(
                        new ItemStack(Items.EMERALD, 3),
                        new ItemStack(RavenCoffeeItems.COFFEE_BEANS_ROASTED, 15),
                        new ItemStack(RavenCoffeeItems.COFFEE_BEANS_ROASTED_GROUND, 15),
                        10, // Max uses
                        4, // Vill exp
                        0.2F // Price mult
                )
        ));
        // =============================== CUPS ===============================
        // =============================== BREWS ===============================
        // +++++++++++++++ BASIC
        factories.add(((entity, random) ->
                new TradeOffer(
                        new ItemStack(Items.EMERALD, 1),
                        new ItemStack(RavenCoffeeItems.CUP_SMALL, 1),
                        new ItemStack(RavenCoffeeBrewItems.CUP_SMALL_BREW_BASIC, 1),
                        15, // Max uses
                        5, // Vill exp
                        0.2F // Price mult
                )
        ));
        factories.add(((entity, random) ->
                new TradeOffer(
                        new ItemStack(Items.EMERALD, 2),
                        new ItemStack(RavenCoffeeItems.CUP_MEDIUM, 1),
                        new ItemStack(RavenCoffeeBrewItems.CUP_MEDIUM_BREW_BASIC, 1),
                        10, // Max uses
                        5, // Vill exp
                        0.2F // Price mult
                )
        ));
        factories.add(((entity, random) ->
                new TradeOffer(
                        new ItemStack(Items.EMERALD, 2),
                        new ItemStack(RavenCoffeeItems.COFFEE_MUG, 1),
                        new ItemStack(RavenCoffeeBrewItems.COFFEE_MUG_BREW_BASIC, 1),
                        10, // Max uses
                        5, // Vill exp
                        0.2F // Price mult
                )
        ));
        factories.add(((entity, random) ->
                new TradeOffer(
                        new ItemStack(Items.EMERALD, 3),
                        new ItemStack(RavenCoffeeItems.CUP_LARGE, 1),
                        new ItemStack(RavenCoffeeBrewItems.CUP_LARGE_BREW_BASIC, 1),
                        5, // Max uses
                        5, // Vill exp
                        0.2F // Price mult
                )
        ));
        // +++++++++++++++ APPLE
        factories.add(((entity, random) ->
                new TradeOffer(
                        new ItemStack(Items.EMERALD, 2),
                        new ItemStack(RavenCoffeeItems.CUP_MEDIUM, 1),
                        new ItemStack(RavenCoffeeBrewItems.CUP_MEDIUM_BREW_APPLE, 1),
                        10, // Max uses
                        6, // Vill exp
                        0.2F // Price mult
                )
        ));
        factories.add(((entity, random) ->
                new TradeOffer(
                        new ItemStack(Items.EMERALD, 2),
                        new ItemStack(RavenCoffeeItems.COFFEE_MUG, 1),
                        new ItemStack(RavenCoffeeBrewItems.COFFEE_MUG_BREW_APPLE, 1),
                        10, // Max uses
                        6, // Vill exp
                        0.2F // Price mult
                )
        ));
        factories.add(((entity, random) ->
                new TradeOffer(
                        new ItemStack(Items.EMERALD, 3),
                        new ItemStack(RavenCoffeeItems.CUP_LARGE, 1),
                        new ItemStack(RavenCoffeeBrewItems.CUP_LARGE_BREW_APPLE, 1),
                        5, // Max uses
                        6, // Vill exp
                        0.2F // Price mult
                )
        ));
        // +++++++++++++++ BERRY
        factories.add(((entity, random) ->
                new TradeOffer(
                        new ItemStack(Items.EMERALD, 2),
                        new ItemStack(RavenCoffeeItems.CUP_MEDIUM, 1),
                        new ItemStack(RavenCoffeeBrewItems.CUP_MEDIUM_BREW_BERRY, 1),
                        10, // Max uses
                        6, // Vill exp
                        0.2F // Price mult
                )
        ));
        factories.add(((entity, random) ->
                new TradeOffer(
                        new ItemStack(Items.EMERALD, 2),
                        new ItemStack(RavenCoffeeItems.COFFEE_MUG, 1),
                        new ItemStack(RavenCoffeeBrewItems.COFFEE_MUG_BREW_BERRY, 1),
                        10, // Max uses
                        6, // Vill exp
                        0.2F // Price mult
                )
        ));
        factories.add(((entity, random) ->
                new TradeOffer(
                        new ItemStack(Items.EMERALD, 3),
                        new ItemStack(RavenCoffeeItems.CUP_LARGE, 1),
                        new ItemStack(RavenCoffeeBrewItems.CUP_LARGE_BREW_BERRY, 1),
                        5, // Max uses
                        6, // Vill exp
                        0.2F // Price mult
                )
        ));


        // +++++++++++++++ CHOCOLATE
        factories.add(((entity, random) ->
                new TradeOffer(
                        new ItemStack(Items.EMERALD, 3),
                        new ItemStack(RavenCoffeeItems.CUP_MEDIUM, 1),
                        new ItemStack(RavenCoffeeBrewItems.CUP_MEDIUM_BREW_CHOCOLATE, 1),
                        10, // Max uses
                        6, // Vill exp
                        0.2F // Price mult
                )
        ));
        factories.add(((entity, random) ->
                new TradeOffer(
                        new ItemStack(Items.EMERALD, 3),
                        new ItemStack(RavenCoffeeItems.COFFEE_MUG, 1),
                        new ItemStack(RavenCoffeeBrewItems.COFFEE_MUG_BREW_CHOCOLATE, 1),
                        10, // Max uses
                        6, // Vill exp
                        0.2F // Price mult
                )
        ));
        factories.add(((entity, random) ->
                new TradeOffer(
                        new ItemStack(Items.EMERALD, 4),
                        new ItemStack(RavenCoffeeItems.CUP_LARGE, 1),
                        new ItemStack(RavenCoffeeBrewItems.CUP_LARGE_BREW_CHOCOLATE, 1),
                        5, // Max uses
                        6, // Vill exp
                        0.2F // Price mult
                )
        ));


        // +++++++++++++++ HONEY
        factories.add(((entity, random) ->
                new TradeOffer(
                        new ItemStack(Items.EMERALD, 2),
                        new ItemStack(RavenCoffeeItems.CUP_MEDIUM, 1),
                        new ItemStack(RavenCoffeeBrewItems.CUP_MEDIUM_BREW_HONEY, 1),
                        10, // Max uses
                        6, // Vill exp
                        0.2F // Price mult
                )
        ));
        factories.add(((entity, random) ->
                new TradeOffer(
                        new ItemStack(Items.EMERALD, 2),
                        new ItemStack(RavenCoffeeItems.COFFEE_MUG, 1),
                        new ItemStack(RavenCoffeeBrewItems.COFFEE_MUG_BREW_HONEY, 1),
                        10, // Max uses
                        6, // Vill exp
                        0.2F // Price mult
                )
        ));
        factories.add(((entity, random) ->
                new TradeOffer(
                        new ItemStack(Items.EMERALD, 3),
                        new ItemStack(RavenCoffeeItems.CUP_LARGE, 1),
                        new ItemStack(RavenCoffeeBrewItems.CUP_LARGE_BREW_HONEY, 1),
                        5, // Max uses
                        6, // Vill exp
                        0.2F // Price mult
                )
        ));
    }
    
    public static final void addTradeOffersToVillagerLevelThree(List<TradeOffers.Factory> factories) {
        // +++++++++++++++ CARROT
        factories.add(((entity, random) ->
                new TradeOffer(
                        new ItemStack(Items.EMERALD, 2),
                        new ItemStack(RavenCoffeeItems.CUP_MEDIUM, 1),
                        new ItemStack(RavenCoffeeBrewItems.CUP_MEDIUM_BREW_CARROT, 1),
                        10, // Max uses
                        8, // Vill exp
                        0.2F // Price mult
                )
        ));
        factories.add(((entity, random) ->
                new TradeOffer(
                        new ItemStack(Items.EMERALD, 2),
                        new ItemStack(RavenCoffeeItems.COFFEE_MUG, 1),
                        new ItemStack(RavenCoffeeBrewItems.COFFEE_MUG_BREW_CARROT, 1),
                        10, // Max uses
                        8, // Vill exp
                        0.2F // Price mult
                )
        ));
        factories.add(((entity, random) ->
                new TradeOffer(
                        new ItemStack(Items.EMERALD, 3),
                        new ItemStack(RavenCoffeeItems.CUP_LARGE, 1),
                        new ItemStack(RavenCoffeeBrewItems.CUP_LARGE_BREW_CARROT, 1),
                        5, // Max uses
                        8, // Vill exp
                        0.2F // Price mult
                )
        ));


        // +++++++++++++++ MELON
        factories.add(((entity, random) ->
                new TradeOffer(
                        new ItemStack(Items.EMERALD, 2),
                        new ItemStack(RavenCoffeeItems.CUP_MEDIUM, 1),
                        new ItemStack(RavenCoffeeBrewItems.CUP_MEDIUM_BREW_MELON, 1),
                        10, // Max uses
                        8, // Vill exp
                        0.2F // Price mult
                )
        ));
        factories.add(((entity, random) ->
                new TradeOffer(
                        new ItemStack(Items.EMERALD, 2),
                        new ItemStack(RavenCoffeeItems.COFFEE_MUG, 1),
                        new ItemStack(RavenCoffeeBrewItems.COFFEE_MUG_BREW_MELON, 1),
                        10, // Max uses
                        8, // Vill exp
                        0.2F // Price mult
                )
        ));
        factories.add(((entity, random) ->
                new TradeOffer(
                        new ItemStack(Items.EMERALD, 3),
                        new ItemStack(RavenCoffeeItems.CUP_LARGE, 1),
                        new ItemStack(RavenCoffeeBrewItems.CUP_LARGE_BREW_MELON, 1),
                        5, // Max uses
                        8, // Vill exp
                        0.2F // Price mult
                )
        ));


        // +++++++++++++++ PUMPKIN SPICE LATTE
        factories.add(((entity, random) ->
                new TradeOffer(
                        new ItemStack(Items.EMERALD, 7),
                        new ItemStack(RavenCoffeeItems.CUP_MEDIUM, 1),
                        new ItemStack(RavenCoffeeBrewItems.CUP_MEDIUM_BREW_PUMPKINSPICELATTE, 1),
                        10, // Max uses
                        8, // Vill exp
                        0.2F // Price mult
                )
        ));
        factories.add(((entity, random) ->
                new TradeOffer(
                        new ItemStack(Items.EMERALD, 7),
                        new ItemStack(RavenCoffeeItems.COFFEE_MUG, 1),
                        new ItemStack(RavenCoffeeBrewItems.COFFEE_MUG_BREW_PUMPKINSPICELATTE, 1),
                        10, // Max uses
                        8, // Vill exp
                        0.2F // Price mult
                )
        ));
        factories.add(((entity, random) ->
                new TradeOffer(
                        new ItemStack(Items.EMERALD, 10),
                        new ItemStack(RavenCoffeeItems.CUP_LARGE, 1),
                        new ItemStack(RavenCoffeeBrewItems.CUP_LARGE_BREW_PUMPKINSPICELATTE, 1),
                        5, // Max uses
                        8, // Vill exp
                        0.2F // Price mult
                )
        ));


        // +++++++++++++++ COOKIES AND CREAM
        factories.add(((entity, random) ->
                new TradeOffer(
                        new ItemStack(Items.EMERALD, 4),
                        new ItemStack(RavenCoffeeItems.CUP_MEDIUM, 1),
                        new ItemStack(RavenCoffeeBrewItems.CUP_MEDIUM_BREW_COOKIESANDCREAM, 1),
                        10, // Max uses
                        8, // Vill exp
                        0.2F // Price mult
                )
        ));
        factories.add(((entity, random) ->
                new TradeOffer(
                        new ItemStack(Items.EMERALD, 4),
                        new ItemStack(RavenCoffeeItems.COFFEE_MUG, 1),
                        new ItemStack(RavenCoffeeBrewItems.COFFEE_MUG_BREW_COOKIESANDCREAM, 1),
                        10, // Max uses
                        8, // Vill exp
                        0.2F // Price mult
                )
        ));
        factories.add(((entity, random) ->
                new TradeOffer(
                        new ItemStack(Items.EMERALD, 5),
                        new ItemStack(RavenCoffeeItems.CUP_LARGE, 1),
                        new ItemStack(RavenCoffeeBrewItems.CUP_LARGE_BREW_COOKIESANDCREAM, 1),
                        5, // Max uses
                        8, // Vill exp
                        0.2F // Price mult
                )
        ));


        // +++++++++++++++ MOCHA
        factories.add(((entity, random) ->
                new TradeOffer(
                        new ItemStack(Items.EMERALD, 3),
                        new ItemStack(RavenCoffeeItems.CUP_MEDIUM, 1),
                        new ItemStack(RavenCoffeeBrewItems.CUP_MEDIUM_BREW_MOCHA, 1),
                        10, // Max uses
                        8, // Vill exp
                        0.2F // Price mult
                )
        ));
        factories.add(((entity, random) ->
                new TradeOffer(
                        new ItemStack(Items.EMERALD, 3),
                        new ItemStack(RavenCoffeeItems.COFFEE_MUG, 1),
                        new ItemStack(RavenCoffeeBrewItems.COFFEE_MUG_BREW_MOCHA, 1),
                        10, // Max uses
                        8, // Vill exp
                        0.2F // Price mult
                )
        ));
        factories.add(((entity, random) ->
                new TradeOffer(
                        new ItemStack(Items.EMERALD, 4),
                        new ItemStack(RavenCoffeeItems.CUP_LARGE, 1),
                        new ItemStack(RavenCoffeeBrewItems.CUP_LARGE_BREW_MOCHA, 1),
                        5, // Max uses
                        8, // Vill exp
                        0.2F // Price mult
                )
        ));
    }

    public static final void addTradeOffersToVillagerLevelFour(List<TradeOffers.Factory> factories) {
        // +++++++++++++++ CARROT GOLDEN
        factories.add(((entity, random) ->
                new TradeOffer(
                        new ItemStack(Items.EMERALD, 6),
                        new ItemStack(RavenCoffeeItems.CUP_MEDIUM, 1),
                        new ItemStack(RavenCoffeeBrewItems.CUP_MEDIUM_BREW_CARROT_GOLDEN, 1),
                        6, // Max uses
                        10, // Vill exp
                        0.2F // Price mult
                )
        ));
        factories.add(((entity, random) ->
                new TradeOffer(
                        new ItemStack(Items.EMERALD, 6),
                        new ItemStack(RavenCoffeeItems.COFFEE_MUG, 1),
                        new ItemStack(RavenCoffeeBrewItems.COFFEE_MUG_BREW_CARROT_GOLDEN, 1),
                        6, // Max uses
                        10, // Vill exp
                        0.2F // Price mult
                )
        ));
        factories.add(((entity, random) ->
                new TradeOffer(
                        new ItemStack(Items.EMERALD, 7),
                        new ItemStack(RavenCoffeeItems.CUP_LARGE, 1),
                        new ItemStack(RavenCoffeeBrewItems.CUP_LARGE_BREW_CARROT_GOLDEN, 1),
                        4, // Max uses
                        10, // Vill exp
                        0.2F // Price mult
                )
        ));


        // +++++++++++++++ MELON GOLDEN
        factories.add(((entity, random) ->
                new TradeOffer(
                        new ItemStack(Items.EMERALD, 6),
                        new ItemStack(RavenCoffeeItems.CUP_MEDIUM, 1),
                        new ItemStack(RavenCoffeeBrewItems.CUP_MEDIUM_BREW_MELON_GOLDEN, 1),
                        6, // Max uses
                        10, // Vill exp
                        0.2F // Price mult
                )
        ));
        factories.add(((entity, random) ->
                new TradeOffer(
                        new ItemStack(Items.EMERALD, 6),
                        new ItemStack(RavenCoffeeItems.COFFEE_MUG, 1),
                        new ItemStack(RavenCoffeeBrewItems.COFFEE_MUG_BREW_MELON_GOLDEN, 1),
                        6, // Max uses
                        10, // Vill exp
                        0.2F // Price mult
                )
        ));
        factories.add(((entity, random) ->
                new TradeOffer(
                        new ItemStack(Items.EMERALD, 7),
                        new ItemStack(RavenCoffeeItems.CUP_LARGE, 1),
                        new ItemStack(RavenCoffeeBrewItems.CUP_LARGE_BREW_MELON_GOLDEN, 1),
                        4, // Max uses
                        10, // Vill exp
                        0.2F // Price mult
                )
        ));
    }
    
    public static final void addTradeOffersToVillagerLevelFive(List<TradeOffers.Factory> factories) {
        // +++++++++++++++ END
        factories.add(((entity, random) ->
                new TradeOffer(
                        new ItemStack(Items.EMERALD, 10),
                        new ItemStack(RavenCoffeeItems.CUP_MEDIUM, 1),
                        new ItemStack(RavenCoffeeBrewItems.CUP_MEDIUM_BREW_END, 1),
                        3, // Max uses
                        12, // Vill exp
                        0.2F // Price mult
                )
        ));
        factories.add(((entity, random) ->
                new TradeOffer(
                        new ItemStack(Items.EMERALD, 10),
                        new ItemStack(RavenCoffeeItems.COFFEE_MUG, 1),
                        new ItemStack(RavenCoffeeBrewItems.COFFEE_MUG_BREW_END, 1),
                        3, // Max uses
                        12, // Vill exp
                        0.2F // Price mult
                )
        ));
        factories.add(((entity, random) ->
                new TradeOffer(
                        new ItemStack(Items.EMERALD, 12),
                        new ItemStack(RavenCoffeeItems.CUP_LARGE, 1),
                        new ItemStack(RavenCoffeeBrewItems.CUP_LARGE_BREW_END, 1),
                        2, // Max uses
                        12, // Vill exp
                        0.2F // Price mult
                )
        ));


        // +++++++++++++++ NETHER
        factories.add(((entity, random) ->
                new TradeOffer(
                        new ItemStack(Items.EMERALD, 10),
                        new ItemStack(RavenCoffeeItems.CUP_MEDIUM, 1),
                        new ItemStack(RavenCoffeeBrewItems.CUP_MEDIUM_BREW_NETHER, 1),
                        3, // Max uses
                        12, // Vill exp
                        0.2F // Price mult
                )
        ));
        factories.add(((entity, random) ->
                new TradeOffer(
                        new ItemStack(Items.EMERALD, 10),
                        new ItemStack(RavenCoffeeItems.COFFEE_MUG, 1),
                        new ItemStack(RavenCoffeeBrewItems.COFFEE_MUG_BREW_NETHER, 1),
                        3, // Max uses
                        12, // Vill exp
                        0.2F // Price mult
                )
        ));
        factories.add(((entity, random) ->
                new TradeOffer(
                        new ItemStack(Items.EMERALD, 12),
                        new ItemStack(RavenCoffeeItems.CUP_LARGE, 1),
                        new ItemStack(RavenCoffeeBrewItems.CUP_LARGE_BREW_NETHER, 1),
                        2, // Max uses
                        12, // Vill exp
                        0.2F // Price mult
                )
        ));


        // +++++++++++++++ PHANTASM
        factories.add(((entity, random) ->
                new TradeOffer(
                        new ItemStack(Items.EMERALD, 15),
                        new ItemStack(RavenCoffeeItems.CUP_MEDIUM, 1),
                        new ItemStack(RavenCoffeeBrewItems.CUP_MEDIUM_BREW_PHANTASM, 1),
                        2, // Max uses
                        15, // Vill exp
                        0.2F // Price mult
                )
        ));
        factories.add(((entity, random) ->
                new TradeOffer(
                        new ItemStack(Items.EMERALD, 15),
                        new ItemStack(RavenCoffeeItems.COFFEE_MUG, 1),
                        new ItemStack(RavenCoffeeBrewItems.COFFEE_MUG_BREW_PHANTASM, 1),
                        2, // Max uses
                        15, // Vill exp
                        0.2F // Price mult
                )
        ));
        factories.add(((entity, random) ->
                new TradeOffer(
                        new ItemStack(Items.EMERALD, 20),
                        new ItemStack(RavenCoffeeItems.CUP_LARGE, 1),
                        new ItemStack(RavenCoffeeBrewItems.CUP_LARGE_BREW_PHANTASM, 1),
                        1, // Max uses
                        15, // Vill exp
                        0.2F // Price mult
                )
        ));
    }


    public static void registerTrades() {
        TradeOfferHelper.registerVillagerOffers(RavenCoffeeVillagers.BARISTA, 1,
                RavenCoffeeVillagerTrades::addTradeOffersToVillagerLevelOne);
        TradeOfferHelper.registerVillagerOffers(RavenCoffeeVillagers.BARISTA, 2,
                RavenCoffeeVillagerTrades::addTradeOffersToVillagerLevelTwo);
        TradeOfferHelper.registerVillagerOffers(RavenCoffeeVillagers.BARISTA, 3,
                RavenCoffeeVillagerTrades::addTradeOffersToVillagerLevelThree);
        TradeOfferHelper.registerVillagerOffers(RavenCoffeeVillagers.BARISTA, 4,
                RavenCoffeeVillagerTrades::addTradeOffersToVillagerLevelFour);
        TradeOfferHelper.registerVillagerOffers(RavenCoffeeVillagers.BARISTA, 5,
                RavenCoffeeVillagerTrades::addTradeOffersToVillagerLevelFive);
    }
}
