package com.thewandererraven.ravencoffee.datagen;

import com.thewandererraven.ravencoffee.items.RavenCoffeeItems;
import com.thewandererraven.ravencoffee.util.RavenCoffeeTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import org.jetbrains.annotations.Nullable;

public class RavenCoffeeItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public RavenCoffeeItemTagProvider(FabricDataGenerator dataGenerator, @Nullable BlockTagProvider blockTagProvider) {
        super(dataGenerator, blockTagProvider);
    }
    public RavenCoffeeItemTagProvider(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected void generateTags() {
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
