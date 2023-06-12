package com.thewandererraven.ravencoffee.blocks.entitites;

import com.thewandererraven.ravencoffee.Constants;
import com.thewandererraven.ravencoffee.blocks.RavenCoffeeBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class RavenCoffeeBlockEntities {
    public static BlockEntityType<StackingCupsBlockEntity> STACKING_CUPS_BLOCK_ENTITY;

    public static void registerBlockEntities() {
        STACKING_CUPS_BLOCK_ENTITY = (BlockEntityType<StackingCupsBlockEntity>) register("stacking_cups_block_entity", StackingCupsBlockEntity::new, RavenCoffeeBlocks.STACKING_CUPS_BLOCK);
        /*
        STACKING_CUPS_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                new Identifier(
                        Constants.MOD_ID,
                        "name"),
                FabricBlockEntityTypeBuilder.create(
                        StackingCupsBlockEntity::new,
                        RavenCoffeeBlocks.BROWNIE_BLOCK
                ).build(null)
        );
         */
    }

    private static <T extends BlockEntity> BlockEntityType<?> register(String name, FabricBlockEntityTypeBuilder.Factory<? extends T> factory, Block block) {
        return Registry.register(Registry.BLOCK_ENTITY_TYPE,
                new Identifier(
                        Constants.MOD_ID,
                        name),
                FabricBlockEntityTypeBuilder.create(
                        factory,
                        block
                ).build(null)
                );
    }
}
