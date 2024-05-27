package com.thewandererraven.ravencoffee.world.village;

import com.thewandererraven.ravencoffee.Constants;
import fzzyhmstrs.structurized_reborn.impl.FabricStructurePoolRegistry;
import net.minecraft.util.Identifier;

public class VillageAdditions {
    public static void registerNewVillageStructures() {
        // Seed for Testing:
        int coffeeShopsWeight = 10;
        int coffeeShopEntrancesWeight = 1;

        // /setblock 22 -60 -1 minecraft:chest[facing=west]{LootTable:"chests/village/desert_coffe_shop"}
        // /setblock 22 -60 -1 minecraft:chest[facing=west]{LootTable:"ravencoffee:chests/village/desert_coffee_shop_chest"}
        // Desert village with shop coordinates
        //
        FabricStructurePoolRegistry.registerSimple(
                new Identifier("minecraft:village/desert/houses"),
                new Identifier(Constants.MOD_ID, "village/desert/houses/desert_coffee_lounge"),
                coffeeShopsWeight
        );
        FabricStructurePoolRegistry.registerSimple(
                new Identifier("minecraft:village/desert/streets"),
                new Identifier(Constants.MOD_ID, "village/desert/streets/coffee_lounge_entrance"),
                coffeeShopEntrancesWeight
        );


        // /setblock 22 -60 -1 minecraft:chest[facing=west]{LootTable:"ravencoffee:chests/village/plains_coffee_shop_chest"}
        // Plains village with shop coordinates
        //
        FabricStructurePoolRegistry.registerSimple(
                new Identifier("minecraft:village/plains/houses"),
                new Identifier(Constants.MOD_ID, "village/plains/houses/plains_coffee_lounge"),
                coffeeShopsWeight
        );
        FabricStructurePoolRegistry.registerSimple(
                new Identifier("minecraft:village/plains/streets"),
                new Identifier(Constants.MOD_ID, "village/plains/streets/coffee_lounge_entrance"),
                coffeeShopEntrancesWeight
        );


        // /setblock 22 -60 -1 minecraft:chest[facing=west]{LootTable:"ravencoffee:chests/village/savanna_coffee_shop_chest"}
        // Savanna village with shop coordinates
        //
        FabricStructurePoolRegistry.registerSimple(
                new Identifier("minecraft:village/savanna/decor"),
                new Identifier(Constants.MOD_ID, "village/savanna/savanna_coffee_tree_fenced_1"),
                3
        );
        FabricStructurePoolRegistry.registerSimple(
                new Identifier("minecraft:village/savanna/decor"),
                new Identifier(Constants.MOD_ID, "village/savanna/savanna_coffee_tree_fenced_2"),
                2
        );
        FabricStructurePoolRegistry.registerSimple(
                new Identifier("minecraft:village/savanna/houses"),
                new Identifier(Constants.MOD_ID, "village/savanna/houses/savanna_coffee_lounge"),
                coffeeShopsWeight
        );
        FabricStructurePoolRegistry.registerSimple(
                new Identifier("minecraft:village/savanna/streets"),
                new Identifier(Constants.MOD_ID, "village/savanna/streets/coffee_lounge_entrance"),
                coffeeShopEntrancesWeight
        );


        // /setblock 22 -60 -1 minecraft:chest[facing=west]{LootTable:"ravencoffee:chests/village/snowy_coffee_shop_chest"}
        // Snowy village with shop coordinates
        //
        FabricStructurePoolRegistry.registerSimple(
                new Identifier("minecraft:village/snowy/houses"),
                new Identifier(Constants.MOD_ID, "village/snowy/houses/snowy_coffee_lounge"),
                coffeeShopsWeight
        );
        FabricStructurePoolRegistry.registerSimple(
                new Identifier("minecraft:village/snowy/streets"),
                new Identifier(Constants.MOD_ID, "village/snowy/streets/coffee_lounge_entrance"),
                coffeeShopEntrancesWeight
        );


        // /setblock 22 -60 -1 minecraft:chest[facing=west]{LootTable:"ravencoffee:chests/village/taiga_coffee_shop_chest"}
        // Taiga village with shop coordinates
        //
        FabricStructurePoolRegistry.registerSimple(
                new Identifier("minecraft:village/taiga/houses"),
                new Identifier(Constants.MOD_ID, "village/taiga/houses/taiga_coffee_lounge"),
                coffeeShopsWeight
        );
        FabricStructurePoolRegistry.registerSimple(
                new Identifier("minecraft:village/taiga/streets"),
                new Identifier(Constants.MOD_ID, "village/taiga/streets/coffee_lounge_entrance"),
                coffeeShopEntrancesWeight
        );

    }
}
