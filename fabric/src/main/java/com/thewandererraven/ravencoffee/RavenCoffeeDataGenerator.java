package com.thewandererraven.ravencoffee;

import com.thewandererraven.ravencoffee.datagen.*;
import com.thewandererraven.ravencoffee.world.feature.RavenCoffeeConfiguredFeatures;
import com.thewandererraven.ravencoffee.world.feature.RavenCoffeePlacedFeatures;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;

public class RavenCoffeeDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(RavenCoffeeBlockTagProvider::new);
        pack.addProvider(RavenCoffeeItemTagProvider::new);
        pack.addProvider(RavenCoffeeLootTableTagProvider::new);
        pack.addProvider(RavenCoffeeModelProvider::new);
        pack.addProvider(RavenCoffeeRecipeProvider::new);
        pack.addProvider(RavenCoffeeWorldGenerator::new);
        pack.addProvider(RavenCoffeePoiTagProvider::new);
    }

    @Override
    public void buildRegistry(RegistryBuilder registryBuilder) {
        registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, RavenCoffeeConfiguredFeatures::bootstrap);
        registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, RavenCoffeePlacedFeatures::bootstrap);
    }
}
