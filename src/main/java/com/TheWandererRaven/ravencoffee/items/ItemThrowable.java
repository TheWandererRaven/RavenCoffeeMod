package com.TheWandererRaven.ravencoffee.items;

import com.TheWandererRaven.ravencoffee.customClasses.ThrowableFoodEntity;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

import java.util.Objects;

public class ItemThrowable extends ItemBase {
    public ItemThrowable() { super(); }
    public ItemThrowable(CreativeModeTab tab) { super(tab); }
    public ItemThrowable(Properties props) { super(props); }
    public InteractionResultHolder<ItemStack> onItemRightClick(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.SNOWBALL_THROW, SoundSource.NEUTRAL, 0.5F, 0.4F / (player.getRandom().nextFloat() * 0.4F + 0.8F));
        if (level.isClientSide) {
            ThrowableFoodEntity foodEntity = new ThrowableFoodEntity(level, player);
            foodEntity.setItem(stack);
            foodEntity.shootFromRotation(player, player.xRotO, player.yRotO, 0.0F, 1.5F, 1.0F);
            // TODO: SEARCH AND PROPERLY IMPLEMENT THIS ADD ENTITY
            level.getChunkAt(player.blockPosition()).addEntity(foodEntity);
        }

        player.awardStat(Stats.ITEM_USED.get(this));
        if (!player.isCreative()) {
            stack.shrink(1);
        }

        return InteractionResultHolder.sidedSuccess(stack, !level.isClientSide);
    }
    public InteractionResult onItemUse(UseOnContext p_195939_1_) {
        Level level = p_195939_1_.getLevel();
        Player player = p_195939_1_.getPlayer();
        InteractionHand hand = p_195939_1_.getHand();

        if(player == null) return InteractionResult.FAIL;
        if (this.isEdible()) {
            ItemStack itemstack = player.getItemInHand(hand);
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
