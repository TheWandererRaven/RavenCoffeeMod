package com.thewandererraven.ravencoffee.blocks.entities;

import com.thewandererraven.ravencoffee.Constants;
import com.thewandererraven.ravencoffee.blocks.RavenCoffeeBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RavenCoffeeBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Constants.MOD_ID);

    public static final RegistryObject<BlockEntityType<CoffeeMachineBlockEntity>> COFFEE_MACHINE_BLOCK_ENTITY = BLOCK_ENTITIES.register(
            "coffee_machine_block_entity",
            () -> BlockEntityType.Builder.of(
                    CoffeeMachineBlockEntity::new, RavenCoffeeBlocks.COFFEE_MACHINE_BLOCK.get()
            ).build(null)
    );

    public static final RegistryObject<BlockEntityType<SackBlockEntity>> SACK_BLOCK_ENTITY = BLOCK_ENTITIES.register(
            "sack_block_entity",
            () -> BlockEntityType.Builder.of(
                    SackBlockEntity::new, RavenCoffeeBlocks.SACK_BLOCK.get()
            ).build(null)
    );

    public static final RegistryObject<BlockEntityType<StackingCupsBlockEntity>> STACKING_CUPS_BLOCK_ENTITY = BLOCK_ENTITIES.register(
            "stacking_cups_block_entity",
            () -> BlockEntityType.Builder.of(
                    StackingCupsBlockEntity::new, RavenCoffeeBlocks.STACKING_CUPS_BLOCK.get()
            ).build(null)
    );
    /*public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }*/

}
