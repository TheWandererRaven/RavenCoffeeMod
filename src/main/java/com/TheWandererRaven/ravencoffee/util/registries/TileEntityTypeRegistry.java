package com.TheWandererRaven.ravencoffee.util.registries;

import com.TheWandererRaven.ravencoffee.RavenCoffee;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TileEntityTypeRegistry {
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, RavenCoffee.MOD_ID);
    /*
    public static final RegistryObject<TileEntityType<CoffeeGrinderTileEntity>> COFFEE_GRINDER_TILE_ENTITY_TYPE = TILE_ENTITY_TYPES.register(
            "coffee_grinder",
            () -> TileEntityType.Builder.create(CoffeeGrinderTileEntity::new, BlocksRegistry.COFFEE_GRINDER.get()).build(null)
    );
    */
}
