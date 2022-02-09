package com.thewandererraven.ravencoffee.util.registries;

import com.thewandererraven.ravencoffee.RavenCoffee;
import com.thewandererraven.ravencoffee.containers.CoffeeGrinderContainer;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ContainersRegistry {
    public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, RavenCoffee.MOD_ID);
    public static final RegistryObject<MenuType<CoffeeGrinderContainer>> COFFEE_GRINDER_CONTAINER = CONTAINERS.register(
            "coffee_grinder",
            () -> IForgeContainerType.create(CoffeeGrinderContainer::createContainerClientSide)
    );
}
