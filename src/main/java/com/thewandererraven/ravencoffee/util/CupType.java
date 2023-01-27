package com.thewandererraven.ravencoffee.util;

import net.minecraft.util.StringRepresentable;

import java.util.Locale;

public enum CupType implements StringRepresentable {
    SMALL("small"),
    MEDIUM("medium"),
    COFFEE_MUG("coffee_mug"),
    BIG("big");

    private final String prefix;

    CupType(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String getSerializedName() {
        return this.prefix;
    }

    public static CupType byPrefix(String prefix) {
        return CupType.valueOf(prefix.toUpperCase(Locale.ROOT));
    }
}
