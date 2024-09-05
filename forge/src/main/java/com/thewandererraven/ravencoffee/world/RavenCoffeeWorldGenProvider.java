package com.thewandererraven.ravencoffee.world;

import com.thewandererraven.ravencoffee.Constants;
import com.thewandererraven.ravencoffee.world.features.RavenCoffeeConfiguredFeatures;
import com.thewandererraven.ravencoffee.world.features.RavenCoffeePlacedFeatures;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class RavenCoffeeWorldGenProvider extends DatapackBuiltinEntriesProvider {
    //TOOD: This should be integrated for datagen if I ever want to add that to Forge

    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, RavenCoffeeConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, RavenCoffeePlacedFeatures::bootstrap);

    public RavenCoffeeWorldGenProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(Constants.MOD_ID));
    }
}
