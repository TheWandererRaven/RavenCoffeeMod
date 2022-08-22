package com.thewandererraven.ravencoffee.util.registries;

import com.thewandererraven.ravencoffee.RavenCoffee;
import com.thewandererraven.ravencoffee.containers.CoffeeGrinderContainer;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ContainersRegistry {
    public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, RavenCoffee.MOD_ID);
    public static final RegistryObject<MenuType<CoffeeGrinderContainer>> COFFEE_GRINDER_CONTAINER = CONTAINERS.register(
            "coffee_grinder",
            () -> IForgeMenuType.create(CoffeeGrinderContainer::createContainerClientSide)
    );
}
