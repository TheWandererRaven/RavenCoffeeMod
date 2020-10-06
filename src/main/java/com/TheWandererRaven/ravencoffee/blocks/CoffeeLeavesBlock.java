package com.TheWandererRaven.ravencoffee.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.material.Material;

public class CoffeeLeavesBlock extends LeavesBlock {
    public CoffeeLeavesBlock() {
        super(AbstractBlock.Properties.create(Material.LEAVES));
    }
}
