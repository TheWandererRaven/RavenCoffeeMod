package com.thewandererraven.ravencoffee.mixin;

import com.thewandererraven.ravencoffee.entities.MuffinEntity;
import com.thewandererraven.ravencoffee.items.RavenCoffeeItems;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.ProjectileDispenserBehavior;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Util;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DispenserBlock.class)
public class DispenserMixin {
    @Inject(method = "getBehaviorForItem", at = @At("HEAD"), cancellable = true)
    public void registerRavenCoffeeDispenserBehaviors(ItemStack stack, CallbackInfoReturnable info)
    {
        if(stack.isOf(RavenCoffeeItems.MUFFIN)) {
            info.setReturnValue(new ProjectileDispenserBehavior() {
                protected ProjectileEntity createProjectile(World world, Position position, ItemStack stack) {
                    return Util.make(new MuffinEntity(
                            world, position.getX(), position.getY(), position.getZ()), (entity) -> entity.setItem(stack)
                    );
                }
            });
        }
    }
}
