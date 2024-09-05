package com.thewandererraven.ravencoffee.util.configuration;

import net.minecraftforge.common.ForgeConfigSpec;

public class RavenCoffeeClientConfigs {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    static {
        BUILDER.push("Raven Coffee Configuration");

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
