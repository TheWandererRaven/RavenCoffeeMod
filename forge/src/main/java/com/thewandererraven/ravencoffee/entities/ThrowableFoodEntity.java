package com.thewandererraven.ravencoffee.entities;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

public class ThrowableFoodEntity extends Snowball {
    public ThrowableFoodEntity(EntityType<? extends Snowball> entityType, Level level) {
        super(entityType, level);
    }
    public ThrowableFoodEntity(Level level) {
        super(EntityType.SNOWBALL, level);
    }

    public ThrowableFoodEntity(Level level, LivingEntity livingEntity) {
        super(level, livingEntity);
    }

    public ThrowableFoodEntity(Level level, double x, double y, double z) {
        super(level, x, y, z);
    }

    protected void onHitEntity(EntityHitResult hitResult) {
        //super.onEntityHit(hitResult);
        Entity hitEntity = hitResult.getEntity();
        //int lvt_3_1_ = lvt_2_1_ instanceof BlazeEntity ? 3 : 0;
        if(hitEntity instanceof Sheep) ((Sheep)hitEntity).ate();

        if (hitEntity instanceof Player) {
            Player hitPlayer = (Player)hitEntity;
            if (hitPlayer.canEat(false)) {
                hitPlayer.eat(hitPlayer.level, this.getItem());
                // SOUND PITCH
                //      HIGH = 2
                //    NORMAL = 1
                //       LOW =
                hitPlayer.level.playSound(null, hitPlayer.blockPosition(), SoundEvents.GENERIC_EAT, SoundSource.AMBIENT, 1.0f, 1.0f);
                hitPlayer.level.playSound(null, hitPlayer.blockPosition(), SoundEvents.PLAYER_BURP, SoundSource.AMBIENT, 1.0f, 1.0f);
            } else
                hitPlayer.hurt(damageSources().thrown(this, this.getOwner()), (float)0.5);
        }
        else if (hitEntity instanceof Villager) {
            Villager hitVillager = (Villager)hitEntity;
            if (hitVillager.wantsMoreFood()) hitVillager.eat(hitVillager.level, Items.BREAD.getDefaultInstance());
            hitVillager.knockback(0.5f, this.position().x - hitEntity.getX(), this.position().z - hitEntity.getZ());
        }
        else if(hitEntity instanceof Animal) {
            Animal hitAnimal = (Animal)hitEntity;
            hitAnimal.setInLoveTime(600);
            this.level.broadcastEntityEvent(hitAnimal, (byte)18);
            hitAnimal.knockback(0.5f, this.position().x - hitEntity.getX(), this.position().z - hitEntity.getZ());
            // SOUND PITCH
            //      HIGH = 2
            //    NORMAL = 1
            //       LOW = 0
            hitAnimal.level.playSound(null, hitAnimal.blockPosition(), SoundEvents.GENERIC_EAT, SoundSource.AMBIENT, 1.0f, 1.15f);
            hitAnimal.level.playSound(null, hitAnimal.blockPosition(), SoundEvents.PLAYER_BURP, SoundSource.AMBIENT, 1.0f, 1.1f);
        }
        else {
            hitEntity.hurt(damageSources().thrown(this, this.getOwner()), (float) 1.0);
        }
    }

    protected void onHit(EntityHitResult hitResult) {
        super.onHit(hitResult);
        if (this.level.isClientSide) {
            this.level.broadcastEntityEvent(this, (byte)3);
            this.remove(RemovalReason.DISCARDED);
        }

    }
}
