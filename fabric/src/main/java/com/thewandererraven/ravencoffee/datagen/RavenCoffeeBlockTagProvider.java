package com.thewandererraven.ravencoffee.datagen;

import com.thewandererraven.ravencoffee.blocks.RavenCoffeeBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.tag.BlockTags;

public class RavenCoffeeBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public RavenCoffeeBlockTagProvider(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected void generateTags() {
        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE)
                .add(RavenCoffeeBlocks.COFFEE_BEANS_ROASTED_BLOCK)
                .add(RavenCoffeeBlocks.COFFEE_BEANS_MAGMA_BLOCK)
        ;
    }
}
