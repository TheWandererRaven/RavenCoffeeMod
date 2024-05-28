package com.thewandererraven.ravencoffee.items;

import com.thewandererraven.ravencoffee.entities.MuffinEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.Objects;

public class MuffinItem extends Item {
    public MuffinItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World level, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
        level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (player.getRandom().nextFloat() * 0.4F + 0.8F));

        if (!level.isClient) {
            MuffinEntity foodEntity = new MuffinEntity(level, player);
            foodEntity.setItem(stack);
            foodEntity.setVelocity(player, player.getPitch(), player.getYaw(), 0.0F, 1.5F, 1.0F);
            level.spawnEntity(foodEntity);
        }

        player.increaseStat(Stats.USED.getOrCreateStat(this), 1);
        if (!player.isCreative()) {
            stack.decrement(1);
        }
        return TypedActionResult.success(stack, !level.isClient);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World level = context.getWorld();
        PlayerEntity player = context.getPlayer();
        Hand hand = context.getHand();
        ItemStack itemStack = context.getStack();

        if(player == null) return ActionResult.FAIL;
        if (this.isFood()) {
            if (player.canConsume(Objects.requireNonNull(this.getFoodComponent()).isAlwaysEdible())) {
                //player.startUsingItem(hand);
                player.setCurrentHand(hand);
                return TypedActionResult.consume(itemStack).getResult();
            } else {
                return TypedActionResult.fail(itemStack).getResult();
            }
        } else {
            return TypedActionResult.pass(player.getStackInHand(hand)).getResult();
        }
    }

}
