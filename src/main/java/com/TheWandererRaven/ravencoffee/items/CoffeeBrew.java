package com.TheWandererRaven.ravencoffee.items;

import com.TheWandererRaven.ravencoffee.RavenCoffee;
import com.TheWandererRaven.ravencoffee.util.registries.ItemsRegistry;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.*;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DrinkHelper;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class CoffeeBrew extends Item {
    public CoffeeBrew(Food FoodProperties) {
        super(new Item.Properties()
                .group(RavenCoffee.TAB)
                .maxStackSize(1)
                .food(new Food.Builder()
                        .hunger(4)
                        .saturation(1.2f)
                        .setAlwaysEdible()
                        //                          Effect  ||   seconds * 20(ticks) || level     || chance of effect
                        .effect(() -> new EffectInstance(Effects.SPEED, 1800, 0), 0.4f)
                        .effect(() -> new EffectInstance(Effects.HASTE, 3600, 0), 1.0f)
                        .build())
        );
    }

    @Nonnull
    @Override
    public ItemStack onItemUseFinish(@Nonnull ItemStack p_77654_1_, World p_77654_2_, @Nonnull LivingEntity p_77654_3_) {
        if (!p_77654_2_.isRemote) {
            p_77654_3_.onFoodEaten(p_77654_2_, p_77654_1_);
        }

        if (p_77654_3_ instanceof ServerPlayerEntity) {
            ServerPlayerEntity serverplayerentity = (ServerPlayerEntity)p_77654_3_;
            CriteriaTriggers.CONSUME_ITEM.trigger(serverplayerentity, p_77654_1_);
            serverplayerentity.addStat(Stats.ITEM_USED.get(this));
        }

        if (p_77654_3_ instanceof PlayerEntity && !((PlayerEntity)p_77654_3_).abilities.isCreativeMode) {
            p_77654_1_.shrink(1);
        }

        return p_77654_1_.isEmpty() ? new ItemStack(ItemsRegistry.COFFEE_CUP.get()) : p_77654_1_;
    }

    @Nonnull
    @Override
    public UseAction getUseAction(@Nonnull ItemStack p_77661_1_) {
        return UseAction.DRINK;
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World p_77659_1_, @Nonnull PlayerEntity p_77659_2_, @Nonnull Hand p_77659_3_) {
        return DrinkHelper.func_234707_a_(p_77659_1_, p_77659_2_, p_77659_3_);
    }
}
