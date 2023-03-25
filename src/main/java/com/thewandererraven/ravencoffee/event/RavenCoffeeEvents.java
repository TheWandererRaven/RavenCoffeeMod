package com.thewandererraven.ravencoffee.event;

import com.thewandererraven.ravencoffee.RavenCoffee;
import com.thewandererraven.ravencoffee.items.RavenCoffeeBrewItems;
import com.thewandererraven.ravencoffee.items.RavenCoffeeItems;
import com.thewandererraven.ravencoffee.villager.RavenCoffeeVillagers;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = RavenCoffee.MOD_ID)
public class RavenCoffeeEvents {
    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event) {
        if(event.getType() == RavenCoffeeVillagers.BARISTA.get()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

            // =============================== OTHERS ===============================
            trades.get(1).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 2),
                    new ItemStack(RavenCoffeeItems.COFFEE_BEANS_ROASTED.get(), 10),
                    new ItemStack(RavenCoffeeItems.COFFEE_BEANS_ROASTED_GROUND.get(), 10),
                    10, // Max uses
                    3, // Vill exp
                    0.2F // Price mult
            ));
            trades.get(1).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(RavenCoffeeItems.COFFEE_BEANS_ROASTED.get(), 30),
                    new ItemStack(Items.EMERALD, 2),
                    7, // Max uses
                    3, // Vill exp
                    0.2F // Price mult
            ));
            trades.get(1).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(RavenCoffeeItems.COFFEE_BEANS_ROASTED_GROUND.get(), 10),
                    new ItemStack(Items.EMERALD, 1),
                    7, // Max uses
                    3, // Vill exp
                    0.2F // Price mult
            ));
            trades.get(1).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 2),
                    new ItemStack(RavenCoffeeItems.COFFEE_BEANS_ROASTED.get(), 10),
                    new ItemStack(RavenCoffeeItems.COFFEE_BEANS_ROASTED_GROUND.get(), 10),
                    10, // Max uses
                    3, // Vill exp
                    0.2F // Price mult
            ));
            trades.get(2).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 3),
                    new ItemStack(RavenCoffeeItems.COFFEE_BEANS_ROASTED.get(), 15),
                    new ItemStack(RavenCoffeeItems.COFFEE_BEANS_ROASTED_GROUND.get(), 15),
                    10, // Max uses
                    4, // Vill exp
                    0.2F // Price mult
            ));
            trades.get(2).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 4),
                    new ItemStack(RavenCoffeeItems.COFFEE_BEANS_ROASTED.get(), 20),
                    new ItemStack(RavenCoffeeItems.COFFEE_BEANS_ROASTED_GROUND.get(), 20),
                    10, // Max uses
                    5, // Vill exp
                    0.2F // Price mult
            ));

            // =============================== CUPS ===============================
            trades.get(1).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 1),
                    new ItemStack(RavenCoffeeItems.CUP_SMALL.get(), 1),
                    3, // Max uses
                    1, // Vill exp
                    0F // Price mult
            ));
            trades.get(1).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 1),
                    new ItemStack(RavenCoffeeItems.CUP_MEDIUM.get(), 1),
                    3, // Max uses
                    1, // Vill exp
                    0.2F // Price mult
            ));
            trades.get(1).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 1),
                    new ItemStack(RavenCoffeeItems.COFFEE_MUG.get(), 1),
                    3, // Max uses
                    1, // Vill exp
                    2F // Price mult
            ));
            trades.get(1).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 1),
                    new ItemStack(RavenCoffeeItems.CUP_LARGE.get(), 1),
                    3, // Max uses
                    1, // Vill exp
                    2F // Price mult
            ));

            // =============================== BREWS ===============================

            // +++++++++++++++ BASIC
            trades.get(2).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 1),
                    new ItemStack(RavenCoffeeItems.CUP_SMALL.get(), 1),
                    new ItemStack(RavenCoffeeBrewItems.CUP_SMALL_BREW_BASIC.get(), 1),
                    15, // Max uses
                    5, // Vill exp
                    0.2F // Price mult
            ));
            trades.get(2).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 2),
                    new ItemStack(RavenCoffeeItems.CUP_MEDIUM.get(), 1),
                    new ItemStack(RavenCoffeeBrewItems.CUP_MEDIUM_BREW_BASIC.get(), 1),
                    10, // Max uses
                    5, // Vill exp
                    0.2F // Price mult
            ));
            trades.get(2).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 2),
                    new ItemStack(RavenCoffeeItems.COFFEE_MUG.get(), 1),
                    new ItemStack(RavenCoffeeBrewItems.COFFEE_MUG_BREW_BASIC.get(), 1),
                    10, // Max uses
                    5, // Vill exp
                    0.2F // Price mult
            ));
            trades.get(2).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 3),
                    new ItemStack(RavenCoffeeItems.CUP_LARGE.get(), 1),
                    new ItemStack(RavenCoffeeBrewItems.CUP_LARGE_BREW_BASIC.get(), 1),
                    5, // Max uses
                    5, // Vill exp
                    0.2F // Price mult
            ));


            // +++++++++++++++ APPLE
            trades.get(2).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 2),
                    new ItemStack(RavenCoffeeItems.CUP_MEDIUM.get(), 1),
                    new ItemStack(RavenCoffeeBrewItems.CUP_MEDIUM_BREW_APPLE.get(), 1),
                    10, // Max uses
                    6, // Vill exp
                    0.2F // Price mult
            ));
            trades.get(2).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 2),
                    new ItemStack(RavenCoffeeItems.COFFEE_MUG.get(), 1),
                    new ItemStack(RavenCoffeeBrewItems.COFFEE_MUG_BREW_APPLE.get(), 1),
                    10, // Max uses
                    6, // Vill exp
                    0.2F // Price mult
            ));
            trades.get(2).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 3),
                    new ItemStack(RavenCoffeeItems.CUP_LARGE.get(), 1),
                    new ItemStack(RavenCoffeeBrewItems.CUP_LARGE_BREW_APPLE.get(), 1),
                    5, // Max uses
                    6, // Vill exp
                    0.2F // Price mult
            ));


            // +++++++++++++++ BERRY
            trades.get(2).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 2),
                    new ItemStack(RavenCoffeeItems.CUP_MEDIUM.get(), 1),
                    new ItemStack(RavenCoffeeBrewItems.CUP_MEDIUM_BREW_BERRY.get(), 1),
                    10, // Max uses
                    6, // Vill exp
                    0.2F // Price mult
            ));
            trades.get(2).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 2),
                    new ItemStack(RavenCoffeeItems.COFFEE_MUG.get(), 1),
                    new ItemStack(RavenCoffeeBrewItems.COFFEE_MUG_BREW_BERRY.get(), 1),
                    10, // Max uses
                    6, // Vill exp
                    0.2F // Price mult
            ));
            trades.get(2).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 3),
                    new ItemStack(RavenCoffeeItems.CUP_LARGE.get(), 1),
                    new ItemStack(RavenCoffeeBrewItems.CUP_LARGE_BREW_BERRY.get(), 1),
                    5, // Max uses
                    6, // Vill exp
                    0.2F // Price mult
            ));


            // +++++++++++++++ CHOCOLATE
            trades.get(2).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 3),
                    new ItemStack(RavenCoffeeItems.CUP_MEDIUM.get(), 1),
                    new ItemStack(RavenCoffeeBrewItems.CUP_MEDIUM_BREW_CHOCOLATE.get(), 1),
                    10, // Max uses
                    6, // Vill exp
                    0.2F // Price mult
            ));
            trades.get(2).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 3),
                    new ItemStack(RavenCoffeeItems.COFFEE_MUG.get(), 1),
                    new ItemStack(RavenCoffeeBrewItems.COFFEE_MUG_BREW_CHOCOLATE.get(), 1),
                    10, // Max uses
                    6, // Vill exp
                    0.2F // Price mult
            ));
            trades.get(2).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 4),
                    new ItemStack(RavenCoffeeItems.CUP_LARGE.get(), 1),
                    new ItemStack(RavenCoffeeBrewItems.CUP_LARGE_BREW_CHOCOLATE.get(), 1),
                    5, // Max uses
                    6, // Vill exp
                    0.2F // Price mult
            ));


            // +++++++++++++++ HONEY
            trades.get(2).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 2),
                    new ItemStack(RavenCoffeeItems.CUP_MEDIUM.get(), 1),
                    new ItemStack(RavenCoffeeBrewItems.CUP_MEDIUM_BREW_HONEY.get(), 1),
                    10, // Max uses
                    6, // Vill exp
                    0.2F // Price mult
            ));
            trades.get(2).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 2),
                    new ItemStack(RavenCoffeeItems.COFFEE_MUG.get(), 1),
                    new ItemStack(RavenCoffeeBrewItems.COFFEE_MUG_BREW_HONEY.get(), 1),
                    10, // Max uses
                    6, // Vill exp
                    0.2F // Price mult
            ));
            trades.get(2).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 3),
                    new ItemStack(RavenCoffeeItems.CUP_LARGE.get(), 1),
                    new ItemStack(RavenCoffeeBrewItems.CUP_LARGE_BREW_HONEY.get(), 1),
                    5, // Max uses
                    6, // Vill exp
                    0.2F // Price mult
            ));


            // +++++++++++++++ CARROT
            trades.get(3).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 2),
                    new ItemStack(RavenCoffeeItems.CUP_MEDIUM.get(), 1),
                    new ItemStack(RavenCoffeeBrewItems.CUP_MEDIUM_BREW_CARROT.get(), 1),
                    10, // Max uses
                    8, // Vill exp
                    0.2F // Price mult
            ));
            trades.get(3).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 2),
                    new ItemStack(RavenCoffeeItems.COFFEE_MUG.get(), 1),
                    new ItemStack(RavenCoffeeBrewItems.COFFEE_MUG_BREW_CARROT.get(), 1),
                    10, // Max uses
                    8, // Vill exp
                    0.2F // Price mult
            ));
            trades.get(3).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 3),
                    new ItemStack(RavenCoffeeItems.CUP_LARGE.get(), 1),
                    new ItemStack(RavenCoffeeBrewItems.CUP_LARGE_BREW_CARROT.get(), 1),
                    5, // Max uses
                    8, // Vill exp
                    0.2F // Price mult
            ));


            // +++++++++++++++ MELON
            trades.get(3).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 2),
                    new ItemStack(RavenCoffeeItems.CUP_MEDIUM.get(), 1),
                    new ItemStack(RavenCoffeeBrewItems.CUP_MEDIUM_BREW_MELON.get(), 1),
                    10, // Max uses
                    8, // Vill exp
                    0.2F // Price mult
            ));
            trades.get(3).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 2),
                    new ItemStack(RavenCoffeeItems.COFFEE_MUG.get(), 1),
                    new ItemStack(RavenCoffeeBrewItems.COFFEE_MUG_BREW_MELON.get(), 1),
                    10, // Max uses
                    8, // Vill exp
                    0.2F // Price mult
            ));
            trades.get(3).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 3),
                    new ItemStack(RavenCoffeeItems.CUP_LARGE.get(), 1),
                    new ItemStack(RavenCoffeeBrewItems.CUP_LARGE_BREW_MELON.get(), 1),
                    5, // Max uses
                    8, // Vill exp
                    0.2F // Price mult
            ));


            // +++++++++++++++ PUMPKIN SPICE LATTE
            trades.get(3).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 7),
                    new ItemStack(RavenCoffeeItems.CUP_MEDIUM.get(), 1),
                    new ItemStack(RavenCoffeeBrewItems.CUP_MEDIUM_BREW_PUMPKINSPICELATTE.get(), 1),
                    10, // Max uses
                    8, // Vill exp
                    0.2F // Price mult
            ));
            trades.get(3).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 7),
                    new ItemStack(RavenCoffeeItems.COFFEE_MUG.get(), 1),
                    new ItemStack(RavenCoffeeBrewItems.COFFEE_MUG_BREW_PUMPKINSPICELATTE.get(), 1),
                    10, // Max uses
                    8, // Vill exp
                    0.2F // Price mult
            ));
            trades.get(3).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 10),
                    new ItemStack(RavenCoffeeItems.CUP_LARGE.get(), 1),
                    new ItemStack(RavenCoffeeBrewItems.CUP_MEDIUM_BREW_PUMPKINSPICELATTE.get(), 1),
                    5, // Max uses
                    8, // Vill exp
                    0.2F // Price mult
            ));


            // +++++++++++++++ COOKIES AND CREAM
            trades.get(3).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 4),
                    new ItemStack(RavenCoffeeItems.CUP_MEDIUM.get(), 1),
                    new ItemStack(RavenCoffeeBrewItems.CUP_MEDIUM_BREW_COOKIESANDCREAM.get(), 1),
                    10, // Max uses
                    8, // Vill exp
                    0.2F // Price mult
            ));
            trades.get(3).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 4),
                    new ItemStack(RavenCoffeeItems.COFFEE_MUG.get(), 1),
                    new ItemStack(RavenCoffeeBrewItems.COFFEE_MUG_BREW_COOKIESANDCREAM.get(), 1),
                    10, // Max uses
                    8, // Vill exp
                    0.2F // Price mult
            ));
            trades.get(3).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 5),
                    new ItemStack(RavenCoffeeItems.CUP_LARGE.get(), 1),
                    new ItemStack(RavenCoffeeBrewItems.CUP_LARGE_BREW_COOKIESANDCREAM.get(), 1),
                    5, // Max uses
                    8, // Vill exp
                    0.2F // Price mult
            ));


            // +++++++++++++++ MOCHA
            trades.get(3).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 3),
                    new ItemStack(RavenCoffeeItems.CUP_MEDIUM.get(), 1),
                    new ItemStack(RavenCoffeeBrewItems.CUP_MEDIUM_BREW_MOCHA.get(), 1),
                    10, // Max uses
                    8, // Vill exp
                    0.2F // Price mult
            ));
            trades.get(3).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 3),
                    new ItemStack(RavenCoffeeItems.COFFEE_MUG.get(), 1),
                    new ItemStack(RavenCoffeeBrewItems.COFFEE_MUG_BREW_MOCHA.get(), 1),
                    10, // Max uses
                    8, // Vill exp
                    0.2F // Price mult
            ));
            trades.get(3).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 4),
                    new ItemStack(RavenCoffeeItems.CUP_LARGE.get(), 1),
                    new ItemStack(RavenCoffeeBrewItems.CUP_LARGE_BREW_MOCHA.get(), 1),
                    5, // Max uses
                    8, // Vill exp
                    0.2F // Price mult
            ));


            // +++++++++++++++ CARROT GOLDEN
            trades.get(4).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 6),
                    new ItemStack(RavenCoffeeItems.CUP_MEDIUM.get(), 1),
                    new ItemStack(RavenCoffeeBrewItems.CUP_MEDIUM_BREW_CARROT_GOLDEN.get(), 1),
                    6, // Max uses
                    10, // Vill exp
                    0.2F // Price mult
            ));
            trades.get(4).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 6),
                    new ItemStack(RavenCoffeeItems.COFFEE_MUG.get(), 1),
                    new ItemStack(RavenCoffeeBrewItems.COFFEE_MUG_BREW_CARROT_GOLDEN.get(), 1),
                    6, // Max uses
                    10, // Vill exp
                    0.2F // Price mult
            ));
            trades.get(4).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 7),
                    new ItemStack(RavenCoffeeItems.CUP_LARGE.get(), 1),
                    new ItemStack(RavenCoffeeBrewItems.CUP_LARGE_BREW_CARROT_GOLDEN.get(), 1),
                    4, // Max uses
                    10, // Vill exp
                    0.2F // Price mult
            ));


            // +++++++++++++++ MELON GOLDEN
            trades.get(4).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 6),
                    new ItemStack(RavenCoffeeItems.CUP_MEDIUM.get(), 1),
                    new ItemStack(RavenCoffeeBrewItems.CUP_MEDIUM_BREW_MELON_GOLDEN.get(), 1),
                    6, // Max uses
                    10, // Vill exp
                    0.2F // Price mult
            ));
            trades.get(4).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 6),
                    new ItemStack(RavenCoffeeItems.COFFEE_MUG.get(), 1),
                    new ItemStack(RavenCoffeeBrewItems.COFFEE_MUG_BREW_MELON_GOLDEN.get(), 1),
                    6, // Max uses
                    10, // Vill exp
                    0.2F // Price mult
            ));
            trades.get(4).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 7),
                    new ItemStack(RavenCoffeeItems.CUP_LARGE.get(), 1),
                    new ItemStack(RavenCoffeeBrewItems.CUP_LARGE_BREW_MELON_GOLDEN.get(), 1),
                    4, // Max uses
                    10, // Vill exp
                    0.2F // Price mult
            ));


            // +++++++++++++++ END
            trades.get(5).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 10),
                    new ItemStack(RavenCoffeeItems.CUP_MEDIUM.get(), 1),
                    new ItemStack(RavenCoffeeBrewItems.CUP_MEDIUM_BREW_END.get(), 1),
                    3, // Max uses
                    12, // Vill exp
                    0.2F // Price mult
            ));
            trades.get(5).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 10),
                    new ItemStack(RavenCoffeeItems.COFFEE_MUG.get(), 1),
                    new ItemStack(RavenCoffeeBrewItems.COFFEE_MUG_BREW_END.get(), 1),
                    3, // Max uses
                    12, // Vill exp
                    0.2F // Price mult
            ));
            trades.get(5).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 12),
                    new ItemStack(RavenCoffeeItems.CUP_LARGE.get(), 1),
                    new ItemStack(RavenCoffeeBrewItems.CUP_LARGE_BREW_END.get(), 1),
                    2, // Max uses
                    12, // Vill exp
                    0.2F // Price mult
            ));


            // +++++++++++++++ NETHER
            trades.get(5).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 10),
                    new ItemStack(RavenCoffeeItems.CUP_MEDIUM.get(), 1),
                    new ItemStack(RavenCoffeeBrewItems.CUP_MEDIUM_BREW_NETHER.get(), 1),
                    3, // Max uses
                    12, // Vill exp
                    0.2F // Price mult
            ));
            trades.get(5).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 10),
                    new ItemStack(RavenCoffeeItems.COFFEE_MUG.get(), 1),
                    new ItemStack(RavenCoffeeBrewItems.COFFEE_MUG_BREW_NETHER.get(), 1),
                    3, // Max uses
                    12, // Vill exp
                    0.2F // Price mult
            ));
            trades.get(5).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 12),
                    new ItemStack(RavenCoffeeItems.CUP_LARGE.get(), 1),
                    new ItemStack(RavenCoffeeBrewItems.CUP_LARGE_BREW_NETHER.get(), 1),
                    2, // Max uses
                    12, // Vill exp
                    0.2F // Price mult
            ));


            // +++++++++++++++ PHANTASM
            trades.get(5).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 15),
                    new ItemStack(RavenCoffeeItems.CUP_MEDIUM.get(), 1),
                    new ItemStack(RavenCoffeeBrewItems.CUP_MEDIUM_BREW_PHANTASM.get(), 1),
                    2, // Max uses
                    15, // Vill exp
                    0.2F // Price mult
            ));
            trades.get(5).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 15),
                    new ItemStack(RavenCoffeeItems.COFFEE_MUG.get(), 1),
                    new ItemStack(RavenCoffeeBrewItems.COFFEE_MUG_BREW_PHANTASM.get(), 1),
                    2, // Max uses
                    15, // Vill exp
                    0.2F // Price mult
            ));
            trades.get(5).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 20),
                    new ItemStack(RavenCoffeeItems.CUP_LARGE.get(), 1),
                    new ItemStack(RavenCoffeeBrewItems.CUP_LARGE_BREW_PHANTASM.get(), 1),
                    1, // Max uses
                    15, // Vill exp
                    0.2F // Price mult
            ));
        }
    }
}
