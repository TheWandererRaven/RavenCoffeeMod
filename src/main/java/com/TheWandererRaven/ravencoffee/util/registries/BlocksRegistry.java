package com.TheWandererRaven.ravencoffee.util.registries;

import com.TheWandererRaven.ravencoffee.RavenCoffee;
import com.TheWandererRaven.ravencoffee.blocks.CoffeeBeansRoastedBlock;
import com.TheWandererRaven.ravencoffee.blocks.CoffeeBeansRoastedMagmaBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BushBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.Item;
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
    public static final RegistryObject<Block> COFFEE_BEANS_BUSH_BLOCK = BLOCKS.register(
            "coffee_bush_block",
            () -> new CoffeeBeansBushBlock(AbstractBlock.Properties.create(Material.CACTUS)
                    .sound(SoundType.PLANT)
                    .notSolid()
                    .tickRandomly()
            )
    );
}
