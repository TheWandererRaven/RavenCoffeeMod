package com.thewandererraven.ravencoffee.datagen;

import com.thewandererraven.ravencoffee.Constants;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.tag.PointOfInterestTypeTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.poi.PointOfInterestType;

public class RavenCoffeePoiTagProvider extends FabricTagProvider<PointOfInterestType> {
    /**
     * Construct a new {@link FabricTagProvider} with the default computed path.
     *
     * <p>Common implementations of this class are provided. For example @see BlockTagProvider
     *
     * @param dataGenerator The data generator instance
     * @param registry      The backing registry for the Tag type.
     */
    public RavenCoffeePoiTagProvider(FabricDataGenerator dataGenerator, Registry<PointOfInterestType> registry) {
        super(dataGenerator, registry);
    }
    public RavenCoffeePoiTagProvider(FabricDataGenerator dataGenerator) {
        this(dataGenerator, Registry.POINT_OF_INTEREST_TYPE);
    }

    @Override
    protected void generateTags() {
        this.getOrCreateTagBuilder(PointOfInterestTypeTags.ACQUIRABLE_JOB_SITE)
                .add(new Identifier(Constants.MOD_ID, "barista_block_poi"));
    }
}
