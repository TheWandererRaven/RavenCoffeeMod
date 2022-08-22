package com.thewandererraven.ravencoffee.util.configuration;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class ModConfiguration {
    public static class Common {
        private static final boolean coffeeTreeSavannaEnabled = true;
        private static final int coffeeTreeSavannaCount = 5;
        private static final int coffeeTreeSavannaRarity = 100;
        private static final boolean coffeeTreeJungleEnabledGen = true;
        private static final int coffeeTreeJungleCount = 10;
        private static final int coffeeTreeJungleRarity = 70;

        public final ForgeConfigSpec.ConfigValue<Boolean> COFFEE_TREE_SAVANNA_ENABLED;
        public final ForgeConfigSpec.ConfigValue<Integer> COFFEE_TREE_SAVANNA_COUNT;
        public final ForgeConfigSpec.ConfigValue<Integer> COFFEE_TREE_SAVANNA_RARITY;

        public final ForgeConfigSpec.ConfigValue<Boolean> COFFEE_TREE_JUNGLE_ENABLED;
        public final ForgeConfigSpec.ConfigValue<Integer> COFFEE_TREE_JUNGLE_COUNT;
        public final ForgeConfigSpec.ConfigValue<Integer> COFFEE_TREE_JUNGLE_RARITY;


        public Common(ForgeConfigSpec.Builder builder)
        {
            builder.push("common");
            builder.push("worldgen");

            builder.push("savanna");
            this.COFFEE_TREE_SAVANNA_ENABLED = builder.comment("Generation enabled for this biome")
                    .define("enabled", coffeeTreeSavannaEnabled);
            this.COFFEE_TREE_SAVANNA_COUNT = builder.comment("Count of trees per trees patch")
                    .define("CoffeeTreeCountSav", coffeeTreeSavannaCount);
            this.COFFEE_TREE_SAVANNA_RARITY = builder.comment("The bigger the number the more likely it is to spawn.")
                    .define("CoffeeTreeRaritySav", coffeeTreeSavannaRarity);
            builder.pop();
            builder.push("jungle");
            this.COFFEE_TREE_JUNGLE_ENABLED = builder.comment("Generation enabled for this biome")
                    .define("enabled", coffeeTreeJungleEnabledGen);
            this.COFFEE_TREE_JUNGLE_COUNT = builder.comment("Count of trees per trees patch")
                    .define("count", coffeeTreeJungleCount);
            this.COFFEE_TREE_JUNGLE_RARITY = builder.comment("The bigger the number the more likely it is to spawn. More common for Jungle since jungle spawns a lot of plants")
                    .define("rarity", coffeeTreeJungleRarity);
            builder.pop(2);
        }
    }

    public static final Common COMMON;
    public static final ForgeConfigSpec COMMON_SPEC;

    static //constructor
    {
        Pair<Common, ForgeConfigSpec> commonSpecPair = new ForgeConfigSpec.Builder().configure(Common::new);
        COMMON = commonSpecPair.getLeft();
        COMMON_SPEC = commonSpecPair.getRight();
    }
}
