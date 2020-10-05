package com.TheWandererRaven.ravencoffee.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class CoffeeBeansRoastedBlock extends Block {
    public CoffeeBeansRoastedBlock() {
        super(AbstractBlock.Properties.create(Material.WOOD)
                .hardnessAndResistance(3.0f, 3.0f)
                .sound(SoundType.WOOD)
                .harvestLevel(0)
                .harvestTool(ToolType.AXE)
        );
    }
}
