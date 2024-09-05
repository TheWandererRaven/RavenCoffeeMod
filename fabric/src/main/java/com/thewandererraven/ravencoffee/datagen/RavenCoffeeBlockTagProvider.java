package com.thewandererraven.ravencoffee.datagen;

import com.thewandererraven.ravencoffee.blocks.RavenCoffeeBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class RavenCoffeeBlockTagProvider extends FabricTagProvider.BlockTagProvider {

    public RavenCoffeeBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE)
                .add(RavenCoffeeBlocks.COFFEE_BEANS_ROASTED_BLOCK)
                .add(RavenCoffeeBlocks.COFFEE_BEANS_MAGMA_BLOCK)
        ;
    }
}
