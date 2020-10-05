package com.TheWandererRaven.ravencoffee.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class CoffeeBeansRoastedMagmaBlock extends Block {
    public CoffeeBeansRoastedMagmaBlock() {
        super(AbstractBlock.Properties.create(Material.GOURD)
                .hardnessAndResistance(2.0f, 1.0f)
                .sound(SoundType.SNOW)
                .harvestLevel(0)
                .harvestTool(ToolType.PICKAXE)
        );
    }
}
