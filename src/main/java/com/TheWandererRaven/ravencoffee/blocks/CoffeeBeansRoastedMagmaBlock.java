package com.TheWandererRaven.ravencoffee.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.MagmaBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;

public class CoffeeBeansRoastedMagmaBlock extends MagmaBlock {
    public CoffeeBeansRoastedMagmaBlock() {
        super(Block.Properties.of(Material.CACTUS)
                .strength(2.0f, 1.0f)
                .sound(SoundType.STONE)
                .requiresCorrectToolForDrops()
                .speedFactor(1.5f)
        );
    }
}
