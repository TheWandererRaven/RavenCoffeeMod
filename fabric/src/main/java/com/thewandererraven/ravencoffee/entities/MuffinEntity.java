package com.thewandererraven.ravencoffee.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

public class MuffinEntity extends SnowballEntity {
    public MuffinEntity(EntityType<? extends SnowballEntity> entityType, World world) {
        super(entityType, world);
    }

    public MuffinEntity(World level, LivingEntity livingEntity) {
        super(level, livingEntity);
    }

    public MuffinEntity(World level, double x, double y, double z) {
        super(level, x, y, z);
    }

    protected void onEntityHit(EntityHitResult hitResult) {
        //super.onEntityHit(hitResult);
        Entity hitEntity = hitResult.getEntity();
        //int lvt_3_1_ = lvt_2_1_ instanceof BlazeEntity ? 3 : 0;
        if(hitEntity instanceof SheepEntity) ((SheepEntity)hitEntity).onEatingGrass();

        if (hitEntity instanceof PlayerEntity) {
            PlayerEntity hitPlayer = (PlayerEntity)hitEntity;
            if (hitPlayer.canConsume(false)) {
                hitPlayer.eatFood(hitPlayer.world, this.getItem());
                // SOUND PITCH
                //      HIGH = 2
                //    NORMAL = 1
                //       LOW =
                hitPlayer.world.playSound(null, hitPlayer.getBlockPos(), SoundEvents.ENTITY_GENERIC_EAT, SoundCategory.AMBIENT, 1.0f, 1.0f);
                hitPlayer.world.playSound(null, hitPlayer.getBlockPos(), SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.AMBIENT, 1.0f, 1.0f);
            } else
                hitPlayer.damage(DamageSource.thrownProjectile(this, this.getOwner()), (float)0.5);
        }
        else if (hitEntity instanceof VillagerEntity) {
            VillagerEntity hitVillager = (VillagerEntity)hitEntity;
            if (hitVillager.canBreed()) hitVillager.eatFood(hitVillager.world, Items.BREAD.getDefaultStack());
            hitVillager.takeKnockback(0.5f, this.getPos().x - hitEntity.getX(), this.getPos().z - hitEntity.getZ());
        }
        else if(hitEntity instanceof AnimalEntity) {
            AnimalEntity hitAnimal = (AnimalEntity)hitEntity;
            hitAnimal.setLoveTicks(600);
            this.world.sendEntityStatus(hitAnimal, (byte)18);
            hitAnimal.takeKnockback(0.5f, this.getPos().x - hitEntity.getX(), this.getPos().z - hitEntity.getZ());
            // SOUND PITCH
            //      HIGH = 2
            //    NORMAL = 1
            //       LOW = 0
            hitAnimal.world.playSound(null, hitAnimal.getBlockPos(), SoundEvents.ENTITY_GENERIC_EAT, SoundCategory.AMBIENT, 1.0f, 1.15f);
            hitAnimal.world.playSound(null, hitAnimal.getBlockPos(), SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.AMBIENT, 1.0f, 1.1f);
        }
        else if(!(hitEntity instanceof GolemEntity))  {
            hitEntity.damage(DamageSource.thrownProjectile(this, this.getOwner()), (float) 1.0);
        }
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (this.world.isClient) {
            this.world.sendEntityStatus(this, (byte)3);
            this.remove(RemovalReason.DISCARDED);
        }

    }
}
