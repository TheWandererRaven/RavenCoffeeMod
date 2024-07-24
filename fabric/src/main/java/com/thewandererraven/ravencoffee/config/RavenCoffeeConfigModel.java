package com.thewandererraven.ravencoffee.config;

import io.wispforest.owo.config.annotation.Config;

@Config(name = "raven-coffee-config", wrapperName = "RavenCoffeeConfig")
public class RavenCoffeeConfigModel {
    public boolean disableCoffeeBrewEffects = false;
    public boolean disableCoffeeBrewFoodValues = false;
    public boolean generateCoffeeShops = true;
    public int coffeeShopsRarity = 1;
}
