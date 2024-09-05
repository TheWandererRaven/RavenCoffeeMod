package com.thewandererraven.ravencoffee.items;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.util.Util;

public class CoffeeCherriesItem extends BlockItem {

    private String translationKey;

    public CoffeeCherriesItem(Block block, Settings settings) {
        super(block, settings);
    }

    @Override
    public String getTranslationKey() {
        if (this.translationKey == null) {
            this.translationKey = Util.createTranslationKey("item", Registries.ITEM.getId(this));
        }
        return this.translationKey;
    }
}
