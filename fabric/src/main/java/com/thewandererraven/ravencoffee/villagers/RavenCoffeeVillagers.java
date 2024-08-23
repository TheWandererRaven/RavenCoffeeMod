package com.thewandererraven.ravencoffee.villagers;

import com.google.common.collect.ImmutableSet;
import com.thewandererraven.ravencoffee.Constants;
import com.thewandererraven.ravencoffee.blocks.RavenCoffeeBlocks;
import net.fabricmc.fabric.api.object.builder.v1.villager.VillagerProfessionBuilder;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.block.Block;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.poi.PointOfInterestType;

public class RavenCoffeeVillagers {

    public static final String BARISTA_POI_ID = "barista_block_poi";

    public static final PointOfInterestType BARISTA_POI = registerPOI(
            BARISTA_POI_ID,
            RavenCoffeeBlocks.COFFEE_GRINDER
    );

    public static final VillagerProfession BARISTA = registerProfession(
            "barista",
            RegistryKey.of(Registry.POINT_OF_INTEREST_TYPE_KEY,
                    new Identifier(Constants.MOD_ID, BARISTA_POI_ID)
            )
    );

    public static VillagerProfession registerProfession(String name, RegistryKey<PointOfInterestType> type) {
        return Registry.register(Registry.VILLAGER_PROFESSION, new Identifier(Constants.MOD_ID, name),
                VillagerProfessionBuilder.create().id(new Identifier(Constants.MOD_ID, name)).workstation(type)
                        .workSound(SoundEvents.BLOCK_BREWING_STAND_BREW).build());
    }

    public static PointOfInterestType registerPOI(String name, Block block) {
        return PointOfInterestHelper.register(new Identifier(Constants.MOD_ID, name),
                1, 1, ImmutableSet.copyOf(block.getStateManager().getStates()));
    }

    public static void registerVillagers() {
        Constants.LOGGER.info("Registering Raven Coffee Villagers");

    }
}
