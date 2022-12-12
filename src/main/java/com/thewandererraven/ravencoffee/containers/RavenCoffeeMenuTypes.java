package com.thewandererraven.ravencoffee.containers;

import com.thewandererraven.ravencoffee.RavenCoffee;
import com.thewandererraven.ravencoffee.containers.CoffeeGrinderContainer;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RavenCoffeeMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, RavenCoffee.MOD_ID);
    public static final RegistryObject<MenuType<CoffeeGrinderContainer>> COFFEE_GRINDER_MENU = MENUS.register(
            "coffee_grinder",
            () -> IForgeMenuType.create(CoffeeGrinderContainer::createContainerClientSide)
    );
    public static final RegistryObject<MenuType<CoffeeMachineMenu>> COFFEE_MACHINE_MENU = MENUS.register(
            "coffee_machine",
            () -> IForgeMenuType.create(CoffeeMachineMenu::new)
    );
    public static final RegistryObject<MenuType<SackMenu>> SACK_MENU = MENUS.register(
            "sack",
            () -> IForgeMenuType.create(SackMenu::new)
    );
}
