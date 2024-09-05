package com.thewandererraven.ravencoffee.datagen;

import com.thewandererraven.ravencoffee.items.RavenCoffeeItems;
import com.thewandererraven.ravencoffee.util.RavenCoffeeTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class RavenCoffeeItemTagProvider extends FabricTagProvider.ItemTagProvider {


    public RavenCoffeeItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture, @Nullable BlockTagProvider blockTagProvider) {
        super(output, completableFuture, blockTagProvider);
    }

    public RavenCoffeeItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(RavenCoffeeTags.Items.CUPS)
                .forceAddTag(RavenCoffeeTags.Items.CUPS_SMALL)
                .forceAddTag(RavenCoffeeTags.Items.CUPS_MEDIUM)
                .forceAddTag(RavenCoffeeTags.Items.CUPS_LARGE)
        ;
        getOrCreateTagBuilder(RavenCoffeeTags.Items.CUPS_SMALL)
                .add(RavenCoffeeItems.CUP_SMALL)
        ;
        getOrCreateTagBuilder(RavenCoffeeTags.Items.CUPS_MEDIUM)
                .add(RavenCoffeeItems.CUP_MEDIUM)
                .add(RavenCoffeeItems.COFFEE_MUG)
        ;
        getOrCreateTagBuilder(RavenCoffeeTags.Items.CUPS_LARGE)
                .add(RavenCoffeeItems.CUP_LARGE)
        ;
        getOrCreateTagBuilder(RavenCoffeeTags.Items.BREW_INGREDIENTS)
                .add(RavenCoffeeItems.COFFEE_BEANS_ROASTED_GROUND)
                .add(RavenCoffeeItems.COFFEE_BEANS_MAGMA_GROUND)
        ;
    }
}
