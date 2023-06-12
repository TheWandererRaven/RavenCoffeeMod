package com.thewandererraven.ravencoffee.blocks;

import com.thewandererraven.ravencoffee.Constants;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class RavenCoffeeBlocks {
    public static final Block BROWNIE_BLOCK = registerItem(
            "brownie_block",
            new Block(AbstractBlock.Settings
                    .of(Material.CAKE)
                    .sounds(BlockSoundGroup.WOOL)
            )
    );
    /*
    public static final Block ROSCA_DE_REYES_BLOCK = registerItem(
            "rosca_de_reyes_block",
            () -> new RoscaDeReyesBlock(BlockBehaviour.Properties
                    .of(Material.CAKE)
                    .sound(SoundType.WOOL)
                    .noOcclusion()
            )
    );
    public static final Block TIRAMISU_BLOCK = registerItem(
            "tiramisu_block",
            () -> new TiramisuBlock(BlockBehaviour.Properties
                    .of(Material.CAKE)
                    .sound(SoundType.WOOL)
                    .noOcclusion()
            )
    );
    public static final Block COFFEE_BEANS_ROASTED_BLOCK = registerItem(
            "coffee_beans_roasted_block",
            CoffeeBeansRoastedBlock::new
    );
    public static final Block COFFEE_BEANS_MAGMA_BLOCK = registerItem(
            "coffee_beans_magma_block",
            CoffeeBeansMagmaBlock::new
    );
    public static final Block COFFEE_TREE_LEAVES_BLOCK = registerItem(
            "coffee_tree_leaves_block",
            () -> new CoffeeTreeLeavesBlock(BlockBehaviour.Properties.of(Material.PLANT)
                    .noOcclusion()
                    .sound(SoundType.GRASS)
                    .randomTicks()
            )
    );
    public static final Block COFFEE_TREE_TRUNK_BLOCK = registerItem(
            "coffee_tree_trunk_block",
            () -> new CoffeeTreeTrunkBlock(CropBlock.Properties.of(Material.PLANT)
                    .sound(SoundType.GRASS)
                    .randomTicks()
            )
    );

    public static final Block COFFEE_GRINDER = registerItem(
            "coffee_grinder_block",
            () -> new CoffeeGrinderBlock(net.minecraft.world.level.block.Block.Properties.of(Material.WOOD)
                    .sound(SoundType.WOOD)
            )
    );
    public static final Block COFFEE_MACHINE_BLOCK = registerItem(
            "coffee_machine_block",
            CoffeeMachineBlock::new
    );
    public static final Block SACK_BLOCK = registerItem(
            "sack_block",
            SackBlock::new
    );

     */
    public static final Block STACKING_CUPS_BLOCK = registerItem(
            "stacking_cups_block",
            new StackingCupsBlock()
    );
    
    
    
    private static Block registerItem(String name, Block item) {
        return Registry.register(Registry.BLOCK, new Identifier(Constants.MOD_ID, name), item);
    }

    public static void register() {
        Constants.LOGGER.info(Constants.MOD_NAME + " has registered its blocks!");
    }
}
