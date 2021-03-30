package com.TheWandererRaven.ravencoffee.customClasses;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.memory.MemoryModuleType;
import net.minecraft.entity.merchant.villager.VillagerData;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.monster.BlazeEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.SnowballEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ItemParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.UUID;

public class ThrowableFoodEntity extends SnowballEntity {
    public ThrowableFoodEntity(EntityType<? extends SnowballEntity> p_i50159_1_, World p_i50159_2_) {
        super(p_i50159_1_, p_i50159_2_);
    }

    public ThrowableFoodEntity(World p_i1774_1_, LivingEntity p_i1774_2_) {
        super(p_i1774_1_, p_i1774_2_);
    }

    public ThrowableFoodEntity(World p_i1775_1_, double p_i1775_2_, double p_i1775_4_, double p_i1775_6_) {
        super(p_i1775_1_, p_i1775_2_, p_i1775_4_, p_i1775_6_);
    }

    protected void onEntityHit(EntityRayTraceResult p_213868_1_) {
        //super.onEntityHit(p_213868_1_);
        Entity hitEntity = p_213868_1_.getEntity();
        //int lvt_3_1_ = lvt_2_1_ instanceof BlazeEntity ? 3 : 0;
        if(hitEntity instanceof SheepEntity) ((SheepEntity)hitEntity).eatGrassBonus();

        if (hitEntity instanceof PlayerEntity) {
            PlayerEntity hitPlayer = (PlayerEntity)hitEntity;
            if (hitPlayer.canEat(false)) {
                hitPlayer.onFoodEaten(hitPlayer.world, this.getItem());
                // SOUND PITCH
                //      HIGH = 2
                //    NORMAL = 1
                //       LOW = 0
                hitPlayer.world.playSound(null, hitPlayer.getPosition(), SoundEvents.ENTITY_GENERIC_EAT, SoundCategory.AMBIENT, 1.0f, 1.0f);
                hitPlayer.world.playSound(null, hitPlayer.getPosition(), SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.AMBIENT, 1.0f, 1.0f);
            } else
                hitPlayer.attackEntityFrom(DamageSource.causeThrownDamage(this, this.func_234616_v_()), (float)0.5);
        }
        else if (hitEntity instanceof VillagerEntity) {
            VillagerEntity hitVillager = (VillagerEntity)hitEntity;
            if (hitVillager.wantsMoreFood()) hitVillager.onFoodEaten(hitVillager.world, Items.BREAD.getDefaultInstance());
            hitVillager.applyKnockback((float)0.5, this.getPositionVec().getX() - hitEntity.getPosX(), this.getPositionVec().getZ() - hitEntity.getPosZ());
        }
        else if(hitEntity instanceof AnimalEntity) {
            AnimalEntity hitAnimal = (AnimalEntity)hitEntity;
            hitAnimal.setInLove(600);
            this.world.setEntityState(hitAnimal, (byte)18);
            hitAnimal.applyKnockback(0.5f, this.getPositionVec().getX() - hitEntity.getPosX(), this.getPositionVec().getZ() - hitEntity.getPosZ());
            // SOUND PITCH
            //      HIGH = 2
            //    NORMAL = 1
            //       LOW = 0
            hitAnimal.world.playSound(null, hitAnimal.getPosition(), SoundEvents.ENTITY_GENERIC_EAT, SoundCategory.AMBIENT, 1.0f, 1.15f);
            hitAnimal.world.playSound(null, hitAnimal.getPosition(), SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.AMBIENT, 1.0f, 1.1f);
        }
        else {
            hitEntity.attackEntityFrom(DamageSource.causeThrownDamage(this, this.func_234616_v_()), (float) 1.0);
        }
    }

    protected void onImpact(RayTraceResult p_70227_1_) {
        super.onImpact(p_70227_1_);
        if (!this.world.isRemote) {
            this.world.setEntityState(this, (byte)3);
            this.remove();
        }

    }
}
