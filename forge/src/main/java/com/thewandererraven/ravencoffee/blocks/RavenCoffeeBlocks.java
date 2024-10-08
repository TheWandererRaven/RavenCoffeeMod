package com.thewandererraven.ravencoffee.blocks;

import com.thewandererraven.ravencoffee.Constants;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.MagmaBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RavenCoffeeBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Constants.MOD_ID);
    public static final RegistryObject<Block> BROWNIE_BLOCK = BLOCKS.register(
            "brownie_block",
            () -> new Block(BlockBehaviour.Properties
                    .of(Material.CAKE)
                    .sound(SoundType.WOOL)
                    .destroyTime(0.5F)
            )
    );
    public static final RegistryObject<Block> ROSCA_DE_REYES_BLOCK = BLOCKS.register(
            "rosca_de_reyes_block",
            () -> new RoscaDeReyesBlock(BlockBehaviour.Properties
                    .of(Material.CAKE)
                    .sound(SoundType.WOOL)
                    .noOcclusion(),
                    2,
                    1.5F
            )
    );
    public static final RegistryObject<Block> TIRAMISU_BLOCK = BLOCKS.register(
            "tiramisu_block",
            () -> new TiramisuBlock(BlockBehaviour.Properties
                    .of(Material.CAKE)
                    .sound(SoundType.WOOL)
                    .noOcclusion()
            )
    );
    public static final RegistryObject<Block> COFFEE_BEANS_ROASTED_BLOCK = BLOCKS.register(
            "coffee_beans_roasted_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.CACTUS)
                    .strength(1.0f, 1.0f)
                    .sound(SoundType.WOOD)
            )
    );
    public static final RegistryObject<Block> COFFEE_BEANS_MAGMA_BLOCK = BLOCKS.register(
            "coffee_beans_magma_block",
            () -> new MagmaBlock(BlockBehaviour.Properties.of(Material.CACTUS)
                    .strength(1.0f, 1.0f)
                    .sound(SoundType.STONE)
                    .speedFactor(1.5f)
            )
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
                    .destroyTime(1.0F)
            )
    );

    public static final RegistryObject<Block> COFFEE_GRINDER = BLOCKS.register(
            "coffee_grinder_block",
            () -> new CoffeeGrinderBlock(Block.Properties.of(Material.WOOD)
                    .sound(SoundType.WOOD)
                    .destroyTime(0.8F)
            )
    );
    public static final RegistryObject<Block> COFFEE_MACHINE_BLOCK = BLOCKS.register(
            "coffee_machine_block",
            () -> new CoffeeMachineBlock(BlockBehaviour.Properties.of(Material.METAL)
                    .sound(SoundType.METAL)
                    .destroyTime(0.8F)
            )
    );
    public static final RegistryObject<Block> SACK_BLOCK = BLOCKS.register(
            "sack_block",
            () -> new SackBlock(BlockBehaviour.Properties
                        .of(Material.WOOL)
                        .sound(SoundType.WOOL)
                        .destroyTime(0.8F)
                        .noOcclusion()
            )
    );
    public static final RegistryObject<Block> STACKING_CUPS_BLOCK = BLOCKS.register(
            "stacking_cups_block",
            () -> new StackingCupsBlock(BlockBehaviour.Properties
                    .of(Material.STONE)
                    .sound(SoundType.STONE)
                    .destroyTime(0.5F)
            )
    );
}
