package com.TheWandererRaven.ravencoffee.items;

import com.TheWandererRaven.ravencoffee.customClasses.Brew;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class ChorusBrew extends CoffeeBrew {
    public ChorusBrew(double _cupSize, Item _parentContainer, Brew _brew, Properties p_i48476_1_) {
        super(_cupSize, _parentContainer, _brew, p_i48476_1_);
    }
    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity entity) {
        ItemStack lvt_4_1_ = super.finishUsingItem(stack, world, entity);
        if (world.isClientSide) {
            double lvt_5_1_ = entity.getX();
            double lvt_7_1_ = entity.getY();
            double lvt_9_1_ = entity.getZ();

            for(int lvt_11_1_ = 0; lvt_11_1_ < 16; ++lvt_11_1_) {
                double lvt_12_1_ = entity.getX() + (entity.getRandom().nextDouble() - 0.5D) * 16.0D;
                double lvt_14_1_ = Mth.clamp(entity.getY() + (double)(entity.getRandom().nextInt(16) - 8), (double)world.getMinBuildHeight(), (double)(world.getMinBuildHeight() + ((ServerLevel)world).getHeight() - 1));
                double lvt_16_1_ = entity.getZ() + (entity.getRandom().nextDouble() - 0.5D) * 16.0D;
                if (entity.isPassenger()) {
                    entity.stopRiding();
                }

                if (entity.randomTeleport(lvt_12_1_, lvt_14_1_, lvt_16_1_, true)) {
                    SoundEvent lvt_18_1_ = entity instanceof Fox ? SoundEvents.FOX_TELEPORT : SoundEvents.CHORUS_FRUIT_TELEPORT;
                    world.playSound((Player) null, lvt_5_1_, lvt_7_1_, lvt_9_1_, lvt_18_1_, SoundSource.PLAYERS, 1.0F, 1.0F);
                    entity.playSound(lvt_18_1_, 1.0F, 1.0F);
                    break;
                }
            }

            if (entity instanceof Player) {
                ((Player)entity).getCooldowns().addCooldown(this, 20);
            }
        }

        return lvt_4_1_;
    }
}
