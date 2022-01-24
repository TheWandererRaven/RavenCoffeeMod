package com.TheWandererRaven.ravencoffee.util.registries;

import com.TheWandererRaven.ravencoffee.RavenCoffee;
import com.TheWandererRaven.ravencoffee.blocks.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlocksRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, RavenCoffee.MOD_ID);
    public static final RegistryObject<Block> COFFEE_BEANS_ROASTED_BLOCK = BLOCKS.register(
            "coffee_beans_roasted_block",
            CoffeeBeansRoastedBlock::new
    );
    public static final RegistryObject<Block> COFFEE_BEANS_ROASTED_MAGMA_BLOCK = BLOCKS.register(
            "coffee_beans_roasted_magma_block",
            CoffeeBeansRoastedMagmaBlock::new
    );
    public static final RegistryObject<Block> COFFEE_TREE_LEAVES_BLOCK = BLOCKS.register(
            "coffee_tree_leaves_block",
            () -> new CoffeeTreeLeavesBlock(BlockBehaviour.Properties.of(Material.PLANT)
                    .noOcclusion()
                    .sound(SoundType.GRASS)
                    .randomTicks()
            )
    );
    public static final RegistryObject<Block> COFFEE_TREE_TRUNK_BLOCK = BLOCKS.register(
            "coffee_tree_trunk_block",
            () -> new CoffeeTreeTrunkBlock(CropBlock.Properties.of(Material.PLANT)
                    .sound(SoundType.GRASS)
                    .randomTicks()
            )
    );

    public static final RegistryObject<Block> COFFEE_GRINDER = BLOCKS.register(
            "coffee_grinder_block",
            () -> new CoffeeGrinderBlock(Block.Properties.of(Material.WOOD)
                    .sound(SoundType.WOOD)
            )
    );
}
