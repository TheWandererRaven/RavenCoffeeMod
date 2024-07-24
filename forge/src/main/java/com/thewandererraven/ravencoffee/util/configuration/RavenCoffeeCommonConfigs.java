package com.thewandererraven.ravencoffee.util.configuration;

import net.minecraftforge.common.ForgeConfigSpec;

public class RavenCoffeeCommonConfigs {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    private static final boolean disableCoffeeBrewEffects = false;
    private static final boolean disableCoffeeBrewFoodValues = false;
    private static final boolean generateCoffeeShops = true;
    private static final int coffeeShopsRarity = 1;

    //private static final boolean coffeeTreeSavannaEnabled = true;
    //private static final int coffeeTreeSavannaRarity = 25;
    //private static final boolean coffeeTreeJungleEnabledGen = true;
    //private static final int coffeeTreeJungleRarity = 64;

    public static final ForgeConfigSpec.ConfigValue<Boolean> DISABLE_COFFEE_BREW_EFFECTS;
    public static final ForgeConfigSpec.ConfigValue<Boolean> DISABLE_COFFEE_BREW_FOOD_VALUES;
    public static final ForgeConfigSpec.ConfigValue<Boolean> GENERATE_COFFEE_SHOPS;
    public static final ForgeConfigSpec.ConfigValue<Integer> COFFEE_SHOPS_RARITY;

    //public static final ForgeConfigSpec.ConfigValue<Boolean> COFFEE_TREE_SAVANNA_ENABLED;
    //public static final ForgeConfigSpec.ConfigValue<Integer> COFFEE_TREE_SAVANNA_RARITY;

    //public static final ForgeConfigSpec.ConfigValue<Boolean> COFFEE_TREE_JUNGLE_ENABLED;
    //public static final ForgeConfigSpec.ConfigValue<Integer> COFFEE_TREE_JUNGLE_RARITY;

    static {
        BUILDER.push("RavenCoffeeCommonConfig");

        BUILDER.push("Items");
        BUILDER.push("Brews");
        DISABLE_COFFEE_BREW_EFFECTS = BUILDER
                .comment("Disable the effects given by the Coffee Brews (does not remove the food values)")
                .define("DisableCoffeeBrewEffects", disableCoffeeBrewEffects);
        DISABLE_COFFEE_BREW_FOOD_VALUES = BUILDER.comment("Disable the hunger refill given by the Coffee Brews (does not remove the brew effects)")
                .define("DisableCoffeeBrewFoodValues", disableCoffeeBrewFoodValues);
        BUILDER.pop(2);

        BUILDER.push("WorldGeneration");
        BUILDER.push("Villages");
        GENERATE_COFFEE_SHOPS = BUILDER.comment("Allow generation of Coffee Shops")
                .define("Enabled", generateCoffeeShops);
        COFFEE_SHOPS_RARITY = BUILDER.comment("The bigger the number the more likely for the building to be generated")
                .define("Rarity", coffeeShopsRarity);
        BUILDER.pop();

        BUILDER.push("CoffeeTreeSavannaGeneration");
        //COFFEE_TREE_SAVANNA_ENABLED = BUILDER.comment("Generation enabled for this biome")
        //        .define("Enabled", coffeeTreeSavannaEnabled);
        BUILDER.pop();
        BUILDER.push("CoffeeTreeJungleGeneration");
        //COFFEE_TREE_JUNGLE_ENABLED = BUILDER.comment("Generation enabled for this biome")
        //        .define("Enabled", coffeeTreeJungleEnabledGen);

        BUILDER.pop(2);
        SPEC = BUILDER.build();
    }
}
