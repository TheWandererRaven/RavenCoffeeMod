package com.thewandererraven.ravencoffee.blocks.entitites;

import com.thewandererraven.ravencoffee.Constants;
import com.thewandererraven.ravencoffee.blocks.RavenCoffeeBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class RavenCoffeeBlockEntities {
    public static BlockEntityType<StackingCupsBlockEntity> STACKING_CUPS_BLOCK_ENTITY;
    public static BlockEntityType<SackBlockEntity> SACK_BLOCK_ENTITY;
    public static BlockEntityType<CoffeeMachineBlockEntity> COFFEE_MACHINE;

    static {
        STACKING_CUPS_BLOCK_ENTITY = (BlockEntityType<StackingCupsBlockEntity>) register("stacking_cups_block_entity", StackingCupsBlockEntity::new, RavenCoffeeBlocks.STACKING_CUPS_BLOCK);
        SACK_BLOCK_ENTITY = (BlockEntityType<SackBlockEntity>) register("sack_block_entity", SackBlockEntity::new, RavenCoffeeBlocks.SACK_BLOCK);
        COFFEE_MACHINE = (BlockEntityType<CoffeeMachineBlockEntity>) register("coffee_machine_block_entity", CoffeeMachineBlockEntity::new, RavenCoffeeBlocks.COFFEE_MACHINE_BLOCK);
    }

    private static <T extends BlockEntity> BlockEntityType<?> register(String name, FabricBlockEntityTypeBuilder.Factory<? extends T> factory, Block block) {
        return Registry.register(Registries.BLOCK_ENTITY_TYPE,
                new Identifier(
                        Constants.MOD_ID,
                        name),
                FabricBlockEntityTypeBuilder.create(
                        factory,
                        block
                ).build()
                );
    }

    public static void register() {
        Constants.LOGGER.info(Constants.MOD_NAME + " has registered its block entities!");
    }
}
