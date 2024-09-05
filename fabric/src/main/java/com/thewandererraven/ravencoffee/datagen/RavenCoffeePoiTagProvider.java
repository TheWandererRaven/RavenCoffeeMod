package com.thewandererraven.ravencoffee.datagen;

import com.thewandererraven.ravencoffee.Constants;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.*;
import net.minecraft.registry.tag.PointOfInterestTypeTags;
import net.minecraft.util.Identifier;
import net.minecraft.world.poi.PointOfInterestType;

import java.util.concurrent.CompletableFuture;

public class RavenCoffeePoiTagProvider extends FabricTagProvider<PointOfInterestType> {
    public RavenCoffeePoiTagProvider(FabricDataOutput output, RegistryKey<? extends Registry<PointOfInterestType>> registryKey, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registryKey, registriesFuture);
    }
    public RavenCoffeePoiTagProvider(FabricDataOutput dataGenerator, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        this(dataGenerator, RegistryKeys.POINT_OF_INTEREST_TYPE, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        this.getOrCreateTagBuilder(PointOfInterestTypeTags.ACQUIRABLE_JOB_SITE)
                .add(new Identifier(Constants.MOD_ID, "barista_block_poi"));
    }
}
