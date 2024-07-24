package com.thewandererraven.ravencoffee.items;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.util.Util;
import net.minecraft.util.registry.Registry;

public class CoffeeCherriesItem extends BlockItem {

    private String translationKey;

    public CoffeeCherriesItem(Block block, Settings settings) {
        super(block, settings);
    }

    @Override
    public String getTranslationKey() {
        if (this.translationKey == null) {
            this.translationKey = Util.createTranslationKey("item", Registry.ITEM.getId(this));
        }
        return this.translationKey;
    }
}
