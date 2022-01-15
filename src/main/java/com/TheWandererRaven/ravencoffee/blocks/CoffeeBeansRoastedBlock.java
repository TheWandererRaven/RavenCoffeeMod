package com.TheWandererRaven.ravencoffee.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;

public class CoffeeBeansRoastedBlock extends Block {
    public CoffeeBeansRoastedBlock() {
        super(Block.Properties.of(Material.WOOD)
                .strength(3.0f, 3.0f)
                .sound(SoundType.WOOD)
                .requiresCorrectToolForDrops()
        );
    }
}
