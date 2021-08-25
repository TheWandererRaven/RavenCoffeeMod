package com.TheWandererRaven.ravencoffee.util.registries;

import com.TheWandererRaven.ravencoffee.RavenCoffee;
import com.TheWandererRaven.ravencoffee.blocks.*;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.RegistryObject;
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
            () -> new CoffeeTreeLeavesBlock(AbstractBlock.Properties.create(Material.CACTUS)
                    .sound(SoundType.PLANT)
                    .notSolid()
                    .tickRandomly()
            )
    );
    public static final RegistryObject<Block> COFFEE_TREE_TRUNK_BLOCK = BLOCKS.register(
            "coffee_tree_trunk_block",
            () -> new CoffeeTreeTrunkBlock(AbstractBlock.Properties.create(Material.CACTUS)
                    .sound(SoundType.PLANT)
                    .notSolid()
                    .tickRandomly()
            )
    );
    public static final RegistryObject<Block> COFFEE_GRINDER = BLOCKS.register(
            "coffee_grinder_block",
            () -> new CoffeeGrinderBlock(AbstractBlock.Properties.create(Material.WOOD)
                    .sound(SoundType.WOOD)
            )
    );
}
