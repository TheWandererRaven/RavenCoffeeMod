package com.thewandererraven.ravencoffee;

import com.thewandererraven.ravencoffee.datagen.*;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class RavenCoffeeDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        fabricDataGenerator.addProvider(RavenCoffeeBlockTagProvider::new);
        fabricDataGenerator.addProvider(RavenCoffeeItemTagProvider::new);
        fabricDataGenerator.addProvider(RavenCoffeeLootTableTagProvider::new);
        fabricDataGenerator.addProvider(RavenCoffeeModelProvider::new);
        fabricDataGenerator.addProvider(RavenCoffeeRecipeProvider::new);
        fabricDataGenerator.addProvider(RavenCoffeePoiTagProvider::new);
    }
}
