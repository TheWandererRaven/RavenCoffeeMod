package com.thewandererraven.ravencoffee;

import com.thewandererraven.ravencoffee.blocks.RavenCoffeeBlocks;
import com.thewandererraven.ravencoffee.blocks.entitites.RavenCoffeeBlockEntities;
import com.thewandererraven.ravencoffee.items.RavenCoffeeBrewItems;
import com.thewandererraven.ravencoffee.items.RavenCoffeeItems;
import com.thewandererraven.ravencoffee.recipes.RavenCoffeeRecipes;
import com.thewandererraven.ravencoffee.screens.handlers.RavenCoffeeScreenHandlers;
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
    }
}
