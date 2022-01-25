package com.TheWandererRaven.ravencoffee.blocks;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class CoffeeBeansRoastedMagmaBlock extends MagmaBlock {
    public CoffeeBeansRoastedMagmaBlock() {
        super(AbstractBlock.Properties.create(Material.CACTUS)
                .hardnessAndResistance(2.0f, 1.0f)
                .sound(SoundType.STONE)
                .harvestLevel(0)
                .harvestTool(ToolType.PICKAXE)
                .speedFactor(1.5f)
        );
    }
}
