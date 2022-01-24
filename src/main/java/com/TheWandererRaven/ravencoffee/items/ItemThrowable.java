package com.TheWandererRaven.ravencoffee.items;

import com.TheWandererRaven.ravencoffee.RavenCoffee;
import com.TheWandererRaven.ravencoffee.customClasses.ThrowableFoodEntity;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

import java.util.Objects;
import java.util.UUID;

public class ItemThrowable extends ItemBase {
    public ItemThrowable() { super(); }
    public ItemThrowable(CreativeModeTab tab) { super(tab); }
    public ItemThrowable(Properties props) { super(props); }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.SNOWBALL_THROW, SoundSource.NEUTRAL, 0.5F, 0.4F / (player.getRandom().nextFloat() * 0.4F + 0.8F));

        if (!level.isClientSide) {
            ThrowableFoodEntity foodEntity = new ThrowableFoodEntity(level, player);
            foodEntity.setItem(stack);
            foodEntity.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
            player.sendMessage(Component.nullToEmpty(foodEntity.position().toString()), UUID.randomUUID());
            level.addFreshEntity(foodEntity);
        }

        player.awardStat(Stats.ITEM_USED.get(this));
        if (!player.isCreative()) {
            stack.shrink(1);
        }

        return InteractionResultHolder.sidedSuccess(stack, !level.isClientSide);
    }

    @Override
    public InteractionResult onItemUseFirst(ItemStack itemstack, UseOnContext context) {
        Level level = context.getLevel();
        Player player = context.getPlayer();
        InteractionHand hand = context.getHand();

        if(player == null) return InteractionResult.FAIL;
        if (this.isEdible()) {
            if (player.canEat(Objects.requireNonNull(this.getFoodProperties()).canAlwaysEat())) {
                player.startUsingItem(hand);
                return InteractionResultHolder.consume(itemstack).getResult();
            } else {
                return InteractionResultHolder.fail(itemstack).getResult();
            }
        } else {
            return InteractionResultHolder.pass(player.getItemInHand(hand)).getResult();
        }
    }
}
