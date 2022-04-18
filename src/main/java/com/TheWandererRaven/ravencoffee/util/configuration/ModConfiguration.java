package com.TheWandererRaven.ravencoffee.util.configuration;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class ModConfiguration {
    public static class Common {
        private static final boolean coffeeTreeSavannahEnabled = true;
        private static final int coffeeTreeSavannahCount = 5;
        private static final int coffeeTreeSavannahRarity = 30;
        private static final boolean coffeeTreeJungleEnabledGen = true;
        private static final int coffeeTreeJungleCount = 10;
        private static final int coffeeTreeJungleRarity = 25;

        public final ForgeConfigSpec.ConfigValue<Boolean> COFFEE_TREE_SAVANNAH_ENABLED;
        public final ForgeConfigSpec.ConfigValue<Integer> COFFEE_TREE_SAVANNAH_COUNT;
        public final ForgeConfigSpec.ConfigValue<Integer> COFFEE_TREE_SAVANNAH_PLACEMENT_TRIES;

        public final ForgeConfigSpec.ConfigValue<Boolean> COFFEE_TREE_JUNGLE_ENABLED;
        public final ForgeConfigSpec.ConfigValue<Integer> COFFEE_TREE_JUNGLE_COUNT;
        public final ForgeConfigSpec.ConfigValue<Integer> COFFEE_TREE_JUNGLE_PLACEMENT_TRIES;


        public Common(ForgeConfigSpec.Builder builder)
        {
            builder.push("common");
            builder.push("worldgen");

            builder.push("savannah");
            this.COFFEE_TREE_SAVANNAH_ENABLED = builder.comment("Generation enabled for this biome")
                    .define("enabled", coffeeTreeSavannahEnabled);
            this.COFFEE_TREE_SAVANNAH_COUNT = builder.comment("Count of trees patch")
                    .define("count", coffeeTreeSavannahCount);
            this.COFFEE_TREE_SAVANNAH_PLACEMENT_TRIES = builder.comment("The bigger the number the more likely it is to appear in the overworld. World gen tries more times to find a good spot to spawn the tree.")
                    .define("PlacementTries", coffeeTreeSavannahRarity);
            builder.pop();
            builder.push("jungle");
            this.COFFEE_TREE_JUNGLE_ENABLED = builder.comment("Generation enabled for this biome")
                    .define("enabled", coffeeTreeJungleEnabledGen);
            this.COFFEE_TREE_JUNGLE_COUNT = builder.comment("Count of trees patch")
                    .define("count", coffeeTreeJungleCount);
            this.COFFEE_TREE_JUNGLE_PLACEMENT_TRIES = builder.comment("The bigger the number the more likely it is to appear in the overworld. World gen tries more times to find a good spot to spawn the tree.")
                    .define("PlacementTries", coffeeTreeJungleRarity);
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
