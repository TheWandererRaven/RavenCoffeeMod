package com.thewandererraven.ravencoffee.items;

import com.thewandererraven.ravenbrewscore.Brew;
import com.thewandererraven.ravenbrewscore.CupType;
import com.thewandererraven.ravencoffee.util.configuration.RavenCoffeeCommonConfigs;
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
import net.minecraft.world.level.Level;

import javax.annotation.Nonnull;

public class ChorusBrew extends CoffeeBrew {
    public ChorusBrew(CupType _cupType, Item _parentContainer, Brew _brew, Properties p_i48476_1_) {
        super(_cupType, _parentContainer, _brew, p_i48476_1_);
    }
    @Nonnull
    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity entity) {
        ItemStack lvt_4_1_ = super.finishUsingItem(stack, world, entity);
        if (!world.isClientSide && !RavenCoffeeCommonConfigs.DISABLE_COFFEE_BREW_EFFECTS.get()) {
            double lvt_5_1_ = entity.getX();
            double lvt_7_1_ = entity.getY();
            double lvt_9_1_ = entity.getZ();

            for(int lvt_11_1_ = 0; lvt_11_1_ < 16; ++lvt_11_1_) {
                double lvt_12_1_ = entity.getX() + (entity.getRandom().nextDouble() - 0.5D) * 16.0D;
                // TODO: UPDATE SO The Y POSITION (VERTICAL POS) IS SLIGHTLY RANDOM INSTEAD OF TELEPORTING THE PLAYER AT GROUND LEVEL
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
