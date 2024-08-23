package com.thewandererraven.ravencoffee.datagen;

import com.thewandererraven.ravencoffee.blocks.RavenCoffeeBlocks;
import com.thewandererraven.ravencoffee.items.RavenCoffeeItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.block.StemBlock;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;

public class RavenCoffeeLootTableTagProvider extends FabricBlockLootTableProvider {

    public RavenCoffeeLootTableTagProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(RavenCoffeeBlocks.BROWNIE_BLOCK);
        addDrop(RavenCoffeeBlocks.COFFEE_BEANS_MAGMA_BLOCK);
        addDrop(RavenCoffeeBlocks.COFFEE_BEANS_ROASTED_BLOCK);
        addDrop(RavenCoffeeBlocks.COFFEE_GRINDER);
        addDrop(RavenCoffeeBlocks.COFFEE_MACHINE_BLOCK);
        addDrop(RavenCoffeeBlocks.SACK_BLOCK);

        addDrop(RavenCoffeeBlocks.COFFEE_TREE_TRUNK_BLOCK, RavenCoffeeItems.COFFEE_CHERRIES);
        addDrop(RavenCoffeeBlocks.COFFEE_TREE_LEAVES_BLOCK, leavesDrops(RavenCoffeeBlocks.COFFEE_TREE_LEAVES_BLOCK, RavenCoffeeItems.COFFEE_CHERRIES,
                BlockStatePropertyLootCondition.builder(RavenCoffeeBlocks.COFFEE_TREE_LEAVES_BLOCK).
                        properties(net.minecraft.predicate.StatePredicate.Builder.create()
                                .exactMatch(StemBlock.AGE, 3)
                        )
                )
        );

        //TODO: ADD CHEST LOOT

    }

    public LootTable.Builder leavesDrops(Block crop, Item product, net.minecraft.loot.condition.LootCondition.Builder condition) {
        return (LootTable.Builder) applyExplosionDecay(crop, LootTable.builder()
                .pool(LootPool.builder()
                        .with(
                                ItemEntry.builder(product)
                                        .apply(ApplyBonusLootFunction.binomialWithBonusCount(Enchantments.FORTUNE, 0.5714286F, 3))
                                        .conditionally(condition)
                        )
                )
        );
    }
}
