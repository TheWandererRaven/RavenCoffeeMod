package com.thewandererraven.ravencoffee;

import com.thewandererraven.ravencoffee.blocks.RavenCoffeeBlocks;
import com.thewandererraven.ravencoffee.blocks.entitites.RavenCoffeeBlockEntities;
import com.thewandererraven.ravencoffee.items.RavenCoffeeBrewItems;
import com.thewandererraven.ravencoffee.items.RavenCoffeeItems;
import com.thewandererraven.ravencoffee.recipes.RavenCoffeeRecipes;
import com.thewandererraven.ravencoffee.screens.handlers.RavenCoffeeScreenHandlers;
import com.thewandererraven.ravencoffee.villagers.RavenCoffeeVillagerTrades;
import com.thewandererraven.ravencoffee.villagers.RavenCoffeeVillagers;
import com.thewandererraven.ravencoffee.world.feature.RavenCoffeeConfiguredFeatures;
import com.thewandererraven.ravencoffee.world.feature.RavenCoffeeFeatures;
import com.thewandererraven.ravencoffee.world.feature.RavenCoffeePlacedFeatures;
import com.thewandererraven.ravencoffee.world.feature.gen.RavenCoffeeWorldGen;
import com.thewandererraven.ravencoffee.world.village.VillageAdditions;
import net.fabricmc.api.ModInitializer;

public class RavenCoffeeFabric implements ModInitializer {
    
    @Override
    public void onInitialize() {
        
        // This method is invoked by the Fabric mod loader when it is ready
        // to load your mod. You can access Fabric and Common code in this
        // project.

        // Use Fabric to bootstrap the Common mod.
        RavenCoffeeScreenHandlers.registerAllScreenHandlers();
        RavenCoffeeCommon.init();
        RavenCoffeeBlocks.register();
        RavenCoffeeItems.register();
        RavenCoffeeBrewItems.register();
        RavenCoffeeBlockEntities.register();
        RavenCoffeeRecipes.registerRecipes();

        RavenCoffeeFeatures.registerFeatures();
        RavenCoffeeConfiguredFeatures.registerConfiguredFeatures();
        RavenCoffeePlacedFeatures.registerPlacedFeatures();

        VillageAdditions.registerNewVillageStructures();

        RavenCoffeeVillagers.registerVillagers();
        RavenCoffeeVillagerTrades.registerTrades();

        RavenCoffeeWorldGen.generateWorldGen();
    }
}
