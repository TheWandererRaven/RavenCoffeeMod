package com.thewandererraven.ravencoffee.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;

public class CoffeeBeansRoastedBlock extends Block {
    public CoffeeBeansRoastedBlock() {
        super(Block.Properties.of()
                .strength(3.0f, 3.0f)
                .sound(SoundType.WOOD)
                .requiresCorrectToolForDrops()
        );
    }
}
