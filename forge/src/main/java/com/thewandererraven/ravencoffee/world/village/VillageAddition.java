package com.thewandererraven.ravencoffee.world.village;

import com.mojang.datafixers.util.Pair;
import com.thewandererraven.ravencoffee.Constants;
import com.thewandererraven.ravencoffee.util.configuration.RavenCoffeeCommonConfigs;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.pools.SinglePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import net.minecraftforge.event.server.ServerAboutToStartEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = Constants.MOD_ID)
public class VillageAddition {
    private static final ResourceKey<StructureProcessorList> EMPTY_PROCESSOR_LIST_KEY = ResourceKey.create(
            Registries.PROCESSOR_LIST, new ResourceLocation("minecraft", "empty"));

    /**
     * Adds the building to the targeted pool.
     * We will call this in addNewVillageBuilding method further down to add to every village.
     *
     * Note: This is an additive operation which means multiple mods can do this and they stack with each other safely.
     */
    private static void addBuildingToPool(Registry<StructureTemplatePool> templatePoolRegistry,
                                          Registry<StructureProcessorList> processorListRegistry,
                                          ResourceLocation poolRL,
                                          String nbtPieceRL,
                                          int weight) {

        // Grabs the processor list we want to use along with our piece.
        // This is a requirement as using the ProcessorLists.EMPTY field will cause the game to throw errors.
        // The reason why is the empty processor list in the world's registry is not the same instance as in that field once the world is started up.
        Holder<StructureProcessorList> emptyProcessorList = processorListRegistry.getHolderOrThrow(EMPTY_PROCESSOR_LIST_KEY);

        // Grab the pool we want to add to
        StructureTemplatePool pool = templatePoolRegistry.get(poolRL);
        if (pool == null) return;

        // Grabs the nbt piece and creates a SinglePoolElement of it that we can add to a structure's pool.
        // Use .legacy( for villages/outposts and .single( for everything else
        SinglePoolElement piece = SinglePoolElement.legacy(nbtPieceRL,
                emptyProcessorList).apply(StructureTemplatePool.Projection.RIGID);

        // Use AccessTransformer or Accessor Mixin to make StructureTemplatePool's templates field public for us to see.
        // Weight is handled by how many times the entry appears in this list.
        // We do not need to worry about immutability as this field is created using Lists.newArrayList(); which makes a mutable list.

        for (int i = 0; i < weight; i++) {
            pool.templates.add(piece);
        }

        // Use AccessTransformer or Accessor Mixin to make StructureTemplatePool's rawTemplates field public for us to see.
        // This list of pairs of pieces and weights is not used by vanilla by default but another mod may need it for efficiency.
        // So lets add to this list for completeness. We need to make a copy of the array as it can be an immutable list.
        List<Pair<StructurePoolElement, Integer>> listOfPieceEntries = new ArrayList<>(pool.rawTemplates);
        listOfPieceEntries.add(new Pair<>(piece, weight));
        pool.rawTemplates = listOfPieceEntries;
    }

    /**
     * We use FMLServerAboutToStartEvent as the dynamic registry exists now and all JSON worldgen files were parsed.
     * Mod compat is best done here.
     */
    @SubscribeEvent
    public static void addNewVillageBuilding(final ServerAboutToStartEvent event) {
        Constants.LOGGER.debug("ADD NEW VILLAGE BUILDING");
        Registry<StructureTemplatePool> templatePoolRegistry = event.getServer().registryAccess().registry(Registries.TEMPLATE_POOL).get();
        Registry<StructureProcessorList> processorListRegistry = event.getServer().registryAccess().registry(Registries.PROCESSOR_LIST).get();

        // Seed for Testing:
        int coffeeShopsWeight = 10;
        //int coffeeShopEntrancesWeight = 1;
        int coffeeShopEntrancesWeight = RavenCoffeeCommonConfigs.GENERATE_COFFEE_SHOPS.get() ? RavenCoffeeCommonConfigs.COFFEE_SHOPS_RARITY.get() : 0;

        // /setblock 22 -60 -1 minecraft:chest[facing=west]{LootTable:"chests/village/desert_coffe_shop"}
        // /setblock 22 -60 -1 minecraft:chest[facing=west]{LootTable:"ravencoffee:chests/village/desert_coffee_shop_chest"}
        // Desert village with shop coordinates
        //
        addBuildingToPool(templatePoolRegistry, processorListRegistry,
                new ResourceLocation("minecraft:village/desert/houses"),
                String.format("%s:village/desert/houses/desert_coffee_lounge", Constants.MOD_ID), coffeeShopsWeight);
        addBuildingToPool(templatePoolRegistry, processorListRegistry,
                new ResourceLocation("minecraft:village/desert/streets"),
                String.format("%s:village/desert/streets/coffee_lounge_entrance", Constants.MOD_ID), coffeeShopEntrancesWeight);


        // /setblock 22 -60 -1 minecraft:chest[facing=west]{LootTable:"ravencoffee:chests/village/plains_coffee_shop_chest"}
        // Plains village with shop coordinates
        //
        addBuildingToPool(templatePoolRegistry, processorListRegistry,
                new ResourceLocation("minecraft:village/plains/houses"),
                String.format("%s:village/plains/houses/plains_coffee_lounge", Constants.MOD_ID), coffeeShopsWeight);
        addBuildingToPool(templatePoolRegistry, processorListRegistry,
                new ResourceLocation("minecraft:village/plains/streets"),
                String.format("%s:village/plains/streets/coffee_lounge_entrance", Constants.MOD_ID), coffeeShopEntrancesWeight);


        // /setblock 22 -60 -1 minecraft:chest[facing=west]{LootTable:"ravencoffee:chests/village/savanna_coffee_shop_chest"}
        // Savanna village with shop coordinates
        //
        addBuildingToPool(templatePoolRegistry, processorListRegistry,
                new ResourceLocation("minecraft:village/savanna/decor"),
                String.format("%s:village/savanna/savanna_coffee_tree_fenced_1", Constants.MOD_ID), 3);
        addBuildingToPool(templatePoolRegistry, processorListRegistry,
                new ResourceLocation("minecraft:village/savanna/decor"),
                String.format("%s:village/savanna/savanna_coffee_tree_fenced_2", Constants.MOD_ID), 2);
        addBuildingToPool(templatePoolRegistry, processorListRegistry,
                new ResourceLocation("minecraft:village/savanna/houses"),
                String.format("%s:village/savanna/houses/savanna_coffee_lounge", Constants.MOD_ID), coffeeShopsWeight);
        addBuildingToPool(templatePoolRegistry, processorListRegistry,
                new ResourceLocation("minecraft:village/savanna/streets"),
                String.format("%s:village/savanna/streets/coffee_lounge_entrance", Constants.MOD_ID), coffeeShopEntrancesWeight);


        // /setblock 22 -60 -1 minecraft:chest[facing=west]{LootTable:"ravencoffee:chests/village/snowy_coffee_shop_chest"}
        // Snowy village with shop coordinates
        //
        addBuildingToPool(templatePoolRegistry, processorListRegistry,
                new ResourceLocation("minecraft:village/snowy/houses"),
                String.format("%s:village/snowy/houses/snowy_coffee_lounge", Constants.MOD_ID), coffeeShopsWeight);
        addBuildingToPool(templatePoolRegistry, processorListRegistry,
                new ResourceLocation("minecraft:village/snowy/streets"),
                String.format("%s:village/snowy/streets/coffee_lounge_entrance", Constants.MOD_ID), coffeeShopEntrancesWeight);


        // /setblock 22 -60 -1 minecraft:chest[facing=west]{LootTable:"ravencoffee:chests/village/taiga_coffee_shop_chest"}
        // Taiga village with shop coordinates
        //
        addBuildingToPool(templatePoolRegistry, processorListRegistry,
                new ResourceLocation("minecraft:village/taiga/houses"),
                String.format("%s:village/taiga/houses/taiga_coffee_lounge", Constants.MOD_ID), coffeeShopsWeight);
        addBuildingToPool(templatePoolRegistry, processorListRegistry,
                new ResourceLocation("minecraft:village/taiga/streets"),
                String.format("%s:village/taiga/streets/coffee_lounge_entrance", Constants.MOD_ID), coffeeShopEntrancesWeight);

        // Adds our piece to all village houses pool
        // Note, the resourcelocation is getting the pool files from the data folder. Not assets folder.
        //addBuildingToPool(templatePoolRegistry, processorListRegistry,
        //        new ResourceLocation("minecraft:village/plains/houses"),
        //        "modid:structure_nbt_resourcelocation", 5);

        //addBuildingToPool(templatePoolRegistry, processorListRegistry,
        //        new ResourceLocation("minecraft:village/snowy/houses"),
        //        "modid:structure_nbt_resourcelocation", 5);
        //
        //addBuildingToPool(templatePoolRegistry, processorListRegistry,
        //        new ResourceLocation("minecraft:village/savanna/houses"),
        //        "modid:structure_nbt_resourcelocation", 5);
        //
        //addBuildingToPool(templatePoolRegistry, processorListRegistry,
        //        new ResourceLocation("minecraft:village/taiga/houses"),
        //        "modid:structure_nbt_resourcelocation", 5);
        //
        //addBuildingToPool(templatePoolRegistry, processorListRegistry,
        //        new ResourceLocation("minecraft:village/desert/houses"),
        //        "modid:structure_nbt_resourcelocation", 5);
    }
}
