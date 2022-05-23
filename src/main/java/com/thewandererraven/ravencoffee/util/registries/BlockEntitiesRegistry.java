package com.thewandererraven.ravencoffee.util.registries;

import com.thewandererraven.ravencoffee.RavenCoffee;
import com.thewandererraven.ravencoffee.blocks.entities.CoffeeMachineBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntitiesRegistry {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, RavenCoffee.MOD_ID);

    public static final RegistryObject<BlockEntityType<CoffeeMachineBlockEntity>> COFFEE_MACHINE_BLOCK_ENTITY = BLOCK_ENTITIES.register(
            "coffee_machine_block_entity",
            () -> BlockEntityType.Builder.of(
                    CoffeeMachineBlockEntity::new, BlocksRegistry.COFFEE_MACHINE_BLOCK.get()
            ).build(null)
    );

    /*public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }*/

}
