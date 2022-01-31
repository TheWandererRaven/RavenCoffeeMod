package com.thewandererraven.ravencoffee.util.registries;

import com.thewandererraven.ravencoffee.customClasses.ThrowableFoodEntity;
import net.minecraft.core.Position;
import net.minecraft.core.dispenser.AbstractProjectileDispenseBehavior;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;

public interface IDispenserBehaviourRegistry {
    static void registerBehaviours() {
        DispenserBlock.registerBehavior(ItemsRegistry.MUFFIN::get, new AbstractProjectileDispenseBehavior() {
            @Override
            protected Projectile getProjectile(Level world, Position iPosition, ItemStack itemStack) {
                ThrowableFoodEntity foodEntity = new ThrowableFoodEntity(world, iPosition.x(), iPosition.y(), iPosition.z());
                foodEntity.setItem(itemStack);
                return foodEntity;
            }
        }
        );
    }
}
