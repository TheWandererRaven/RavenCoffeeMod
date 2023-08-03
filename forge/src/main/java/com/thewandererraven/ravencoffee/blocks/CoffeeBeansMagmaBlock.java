package com.thewandererraven.ravencoffee.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.MagmaBlock;
import net.minecraft.world.level.block.SoundType;

public class CoffeeBeansMagmaBlock extends MagmaBlock {
    public CoffeeBeansMagmaBlock() {
        super(Block.Properties.of()
                .strength(2.0f, 1.0f)
                .sound(SoundType.STONE)
                .requiresCorrectToolForDrops()
                .speedFactor(1.5f)
        );
    }
}
