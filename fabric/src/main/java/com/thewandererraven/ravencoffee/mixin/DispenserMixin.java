package com.thewandererraven.ravencoffee.mixin;

import com.thewandererraven.ravencoffee.Constants;
import com.thewandererraven.ravencoffee.entities.MuffinEntity;
import com.thewandererraven.ravencoffee.items.RavenCoffeeItems;
import net.fabricmc.tinyremapper.extension.mixin.common.Logger;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.DispenserBehavior;
import net.minecraft.block.dispenser.ProjectileDispenserBehavior;
import net.minecraft.block.entity.DispenserBlockEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Util;
import net.minecraft.util.math.Position;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.awt.event.ItemListener;

@Mixin(DispenserBehavior.class)
public interface DispenserMixin {
    @Inject(method = "registerDefaults()V", at = @At("TAIL"))
    private static void registerRavenCoffeeDispenserBehaviors(CallbackInfo info)
    {
        DispenserBlock.registerBehavior(RavenCoffeeItems.MUFFIN, new ProjectileDispenserBehavior() {
            protected ProjectileEntity createProjectile(World world, Position position, ItemStack stack) {
                return (ProjectileEntity) Util.make(new MuffinEntity(world, position.getX(), position.getY(), position.getZ()), (entity) -> {
                    entity.setItem(stack);
                });
            }
        });
    }
}
