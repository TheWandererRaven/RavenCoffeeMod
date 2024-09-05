package com.thewandererraven.ravencoffee.items;

import com.thewandererraven.ravenbrewscore.Brew;
import com.thewandererraven.ravenbrewscore.BrewEffect;
import com.thewandererraven.ravenbrewscore.CupType;
import com.thewandererraven.ravencoffee.Constants;
import com.thewandererraven.ravencoffee.RavenCoffeeFabric;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CoffeeBrew extends Item {
    private final Item parentVessel;
    private final CupType cupType;
    private final double cupSize;
    private final Brew brew;

    public CoffeeBrew(CupType _cupType, Item _parentVessel, Brew _brew, Settings settings) {
        super(settings);
        this.parentVessel = _parentVessel;
        this.cupType = _cupType;
        this.cupSize = CupType.getSizeMultiplier(this.cupType);
        this.brew = _brew;
    }

    public CupType getCupType() {
        return this.cupType;
    }

    @Override
    public ItemStack getDefaultStack() {
        return PotionUtil.setPotion(super.getDefaultStack(), Potions.SWIFTNESS);
    }

    // TODO: ADD EFFECT AMPLIFIER (?)
    public StatusEffectInstance adjustEffectToSize(BrewEffect brewEffect) {
        return new StatusEffectInstance(
                brewEffect.effect,
                //(int) ((originalEffect.getDuration() / 20) * this.cupSize) * 20,
                (int) ((brewEffect.duration / 20) * this.cupSize) * 20
                //Math.max(((int) ((originalEffect.getAmplifier() + 1) * this.cupSize) - 1), 0)
        );
    }
    @Override
    public ItemStack finishUsing(ItemStack stack, World level, LivingEntity entity) {
        if (entity instanceof ServerPlayerEntity) {
            ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity)entity;
            Criteria.CONSUME_ITEM.trigger(serverPlayerEntity, stack);
            serverPlayerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
        }

        if (!level.isClient && !RavenCoffeeFabric.CONFIG.disableCoffeeBrewEffects()) {
            for(BrewEffect brewEffect : this.brew.effects) {
                if(!brewEffect.isForRemoving) {
                    double randomValue = Math.random();
                    if(randomValue < brewEffect.chance && randomValue != 0.0d) {
                        if (brewEffect.effect.isInstant()) {
                            StatusEffectInstance adjustedEffect = this.adjustEffectToSize(brewEffect);
                            // TODO: ADD AMPLIFIER TO BREW EFFECT
                            brewEffect.effect.applyInstantEffect(entity, entity, entity, 1, 1.0D);
                        } else {
                            entity.addStatusEffect(this.adjustEffectToSize(brewEffect));
                        }
                    }
                } else {
                    entity.removeStatusEffect(brewEffect.effect);
                }
            }
        }

        if(entity instanceof ServerPlayerEntity player) {
            if(!RavenCoffeeFabric.CONFIG.disableCoffeeBrewFoodValues())
                player.eatFood(level, stack);
            player.incrementStat(Stats.USED.getOrCreateStat(this));
            if (!player.getAbilities().creativeMode) {
                stack.decrement(1);
                if (stack.isEmpty()) {
                    return new ItemStack(this.parentVessel);
                } else {
                    player.getInventory().insertStack(new ItemStack(this.parentVessel));
                }
            }
            level.emitGameEvent(entity, GameEvent.DRINK, player.getEyePos());
        }


        return stack;
    }

    public int getMaxUseTime(ItemStack stack) {
        return 32;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        //return ItemUsage.consumeHeldItem(world, user, hand);
        ItemStack itemStack = user.getStackInHand(hand);
        if (user.canConsume(true)) {
            user.setCurrentHand(hand);
            return TypedActionResult.consume(itemStack);
        } else {
            return TypedActionResult.fail(itemStack);
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        String descriptionKey = this.getTranslationKey().concat(".tooltip");
        String completeDescription = (Text.translatable(descriptionKey)).getString();
        if(!completeDescription.equals(descriptionKey))
            for(String line: completeDescription.split("<br>")) {
                tooltip.add(Text.of(String.format(
                        "\u00A77%s\u00A77",
                        line
                )));
            }
    }
    /*
    public boolean hasEffect(@Nonnull ItemStack p_77636_1_) {
        return super.hasCustomEntity(p_77636_1_) || !PotionUtils.getPotion(p_77636_1_).getEffects().isEmpty();
    }

     */

}
