package com.thewandererraven.ravencoffee.items;

import com.thewandererraven.ravenbrewscore.Brew;
import com.thewandererraven.ravenbrewscore.CupType;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class ChorusBrew extends CoffeeBrew {
    public ChorusBrew(CupType cupType, Item item, Brew brew, FabricItemSettings settings) {
        super(cupType, item, brew, settings);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity entity) {
        ItemStack lvt_4_1_ = super.finishUsing(stack, world, entity);
        if (!world.isClient()) {
            double lvt_5_1_ = entity.getX();
            double lvt_7_1_ = entity.getY();
            double lvt_9_1_ = entity.getZ();

            for(int lvt_11_1_ = 0; lvt_11_1_ < 16; ++lvt_11_1_) {
                double lvt_12_1_ = entity.getX() + (entity.getRandom().nextDouble() - 0.5D) * 16.0D;
                // TODO: UPDATE SO The Y POSITION (VERTICAL POS) IS SLIGHTLY RANDOM INSTEAD OF TELEPORTING THE PLAYER AT GROUND LEVEL
                double lvt_14_1_ = MathHelper.clamp(entity.getY() + (double)(entity.getRandom().nextInt(16) - 8), (double)world.getBottomY(), (double)(world.getBottomY() + ((ServerWorld)world).getHeight() - 1));
                double lvt_16_1_ = entity.getZ() + (entity.getRandom().nextDouble() - 0.5D) * 16.0D;
                if (entity.hasVehicle()) {
                    entity.stopRiding();
                }

                if (entity.teleport(lvt_12_1_, lvt_14_1_, lvt_16_1_, true)) {
                    SoundEvent lvt_18_1_ = entity instanceof FoxEntity ? SoundEvents.ENTITY_FOX_TELEPORT : SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT;
                    world.playSound((PlayerEntity) null, lvt_5_1_, lvt_7_1_, lvt_9_1_, lvt_18_1_, SoundCategory.PLAYERS, 1.0F, 1.0F);
                    entity.playSound(lvt_18_1_, 1.0F, 1.0F);
                    break;
                }
            }

            if (entity instanceof PlayerEntity) {
                ((PlayerEntity)entity).getItemCooldownManager().set(this, 20);
            }
        }

        return lvt_4_1_;
    }
}
