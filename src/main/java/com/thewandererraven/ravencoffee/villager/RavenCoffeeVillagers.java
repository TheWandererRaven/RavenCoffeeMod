package com.thewandererraven.ravencoffee.villager;

import com.google.common.collect.ImmutableSet;
import com.thewandererraven.ravencoffee.RavenCoffee;
import com.thewandererraven.ravencoffee.blocks.RavenCoffeeBlocks;
import com.thewandererraven.ravencoffee.items.RavenCoffeeItems;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.ai.village.poi.PoiTypes;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

public class RavenCoffeeVillagers {
    public static final DeferredRegister<PoiType> POI_TYPES = DeferredRegister.create(ForgeRegistries.POI_TYPES, RavenCoffee.MOD_ID);
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS = DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS, RavenCoffee.MOD_ID);
    public static final RegistryObject<PoiType> BARISTA_BLOCK_POI = POI_TYPES.register(
            "barista_block_poi",
            () -> new PoiType(ImmutableSet.copyOf(
                    RavenCoffeeBlocks.COFFEE_GRINDER.get().getStateDefinition().getPossibleStates()),
                    1,
                    1
            )
    );
    public static final RegistryObject<VillagerProfession> BARISTA = VILLAGER_PROFESSIONS.register(
            "barista",
            () -> new VillagerProfession("barista",
                    x -> x.get() == BARISTA_BLOCK_POI.get(), // GET THE PROFESSION
                    x -> x.get() == BARISTA_BLOCK_POI.get(), // MAINTAIN THE PROFESSION
                    ImmutableSet.of(RavenCoffeeItems.COFFEE_BEANS_ROASTED_GROUND.get(), RavenCoffeeItems.COFFEE_BEANS_ROASTED.get()),
                    ImmutableSet.of(), // ??
                    SoundEvents.VILLAGER_WORK_CLERIC
                    )
    );

    public static void registerPOIs() {
        try {
            ObfuscationReflectionHelper
                    .findMethod(PoiTypes.class, "registerBlockStates", Holder.class)
                    .invoke(null, BARISTA_BLOCK_POI.getHolder(), ImmutableSet.of());
        } catch (InvocationTargetException | IllegalAccessException exception) {
            exception.printStackTrace();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
