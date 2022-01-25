package com.TheWandererRaven.ravencoffee.util.registries;

import com.TheWandererRaven.ravencoffee.RavenCoffee;
import com.TheWandererRaven.ravencoffee.containers.CoffeeGrinderContainer;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ContainersRegistry {
    public static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, RavenCoffee.MOD_ID);
    public static final RegistryObject<ContainerType<CoffeeGrinderContainer>> COFFEE_GRINDER_CONTAINER = CONTAINERS.register(
            "coffee_grinder",
            () -> IForgeContainerType.create(CoffeeGrinderContainer::createContainerClientSide)
    );
}
