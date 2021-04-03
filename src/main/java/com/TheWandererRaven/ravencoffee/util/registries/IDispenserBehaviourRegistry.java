package com.TheWandererRaven.ravencoffee.util.registries;

import com.TheWandererRaven.ravencoffee.customClasses.ThrowableFoodEntity;
import net.minecraft.block.DispenserBlock;
import net.minecraft.dispenser.IPosition;
import net.minecraft.dispenser.ProjectileDispenseBehavior;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface IDispenserBehaviourRegistry {
    static void registerBehaviours() {
        DispenserBlock.registerDispenseBehavior(ItemsRegistry.MUFFIN::get, new ProjectileDispenseBehavior() {
            @Override
            protected ProjectileEntity getProjectileEntity(World world, IPosition iPosition, ItemStack itemStack) {
                ThrowableFoodEntity foodEntity = new ThrowableFoodEntity(world, iPosition.getX(), iPosition.getY(), iPosition.getZ());
                foodEntity.setItem(itemStack);
                return foodEntity;
            }
        }
        );
    }
}
