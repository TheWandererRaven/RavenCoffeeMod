package com.TheWandererRaven.ravencoffee.items;

import com.TheWandererRaven.ravencoffee.customClasses.Brew;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class ChorusBrew extends CoffeeBrew {
    public ChorusBrew(double _cupSize, Item _parentContainer, Brew _brew, Properties p_i48476_1_) {
        super(_cupSize, _parentContainer, _brew, p_i48476_1_);
    }
    public ItemStack onItemUseFinish(ItemStack p_77654_1_, World p_77654_2_, LivingEntity p_77654_3_) {
        ItemStack lvt_4_1_ = super.onItemUseFinish(p_77654_1_, p_77654_2_, p_77654_3_);
        if (!p_77654_2_.isRemote) {
            double lvt_5_1_ = p_77654_3_.getPosX();
            double lvt_7_1_ = p_77654_3_.getPosY();
            double lvt_9_1_ = p_77654_3_.getPosZ();

            for(int lvt_11_1_ = 0; lvt_11_1_ < 16; ++lvt_11_1_) {
                double lvt_12_1_ = p_77654_3_.getPosX() + (p_77654_3_.getRNG().nextDouble() - 0.5D) * 16.0D;
                double lvt_14_1_ = MathHelper.clamp(p_77654_3_.getPosY() + (double)(p_77654_3_.getRNG().nextInt(16) - 8), 0.0D, (double)(p_77654_2_.func_234938_ad_() - 1));
                double lvt_16_1_ = p_77654_3_.getPosZ() + (p_77654_3_.getRNG().nextDouble() - 0.5D) * 16.0D;
                if (p_77654_3_.isPassenger()) {
                    p_77654_3_.stopRiding();
                }

                if (p_77654_3_.attemptTeleport(lvt_12_1_, lvt_14_1_, lvt_16_1_, true)) {
                    SoundEvent lvt_18_1_ = p_77654_3_ instanceof FoxEntity ? SoundEvents.ENTITY_FOX_TELEPORT : SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT;
                    p_77654_2_.playSound((PlayerEntity)null, lvt_5_1_, lvt_7_1_, lvt_9_1_, lvt_18_1_, SoundCategory.PLAYERS, 1.0F, 1.0F);
                    p_77654_3_.playSound(lvt_18_1_, 1.0F, 1.0F);
                    break;
                }
            }

            if (p_77654_3_ instanceof PlayerEntity) {
                ((PlayerEntity)p_77654_3_).getCooldownTracker().setCooldown(this, 20);
            }
        }

        return lvt_4_1_;
    }
}
