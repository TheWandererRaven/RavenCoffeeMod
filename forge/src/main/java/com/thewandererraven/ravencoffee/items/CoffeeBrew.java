package com.thewandererraven.ravencoffee.items;

import com.thewandererraven.ravenbrewscore.Brew;
import com.thewandererraven.ravenbrewscore.BrewEffect;
import com.thewandererraven.ravenbrewscore.CupType;
import com.thewandererraven.ravencoffee.util.configuration.RavenCoffeeCommonConfigs;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class CoffeeBrew extends Item {
    private final Item parentVessel;
    private final CupType cupType;
    private final double cupSize;
    private final Brew brew;

    public CoffeeBrew(CupType _cupType, Item _parentVessel, Brew _brew, Item.Properties p_i48476_1_) {
        super(p_i48476_1_);
        this.parentVessel = _parentVessel;
        this.cupType = _cupType;
        this.cupSize = CupType.getSizeMultiplier(this.cupType);
        this.brew = _brew;
    }

    public CupType getCupType() {
        return this.cupType;
    }

    @Override
    @Nonnull
    public ItemStack getDefaultInstance() {
        return PotionUtils.setPotion(super.getDefaultInstance(), Potions.SWIFTNESS);
    }

    // TODO: ADD EFFECT AMPLIFIER (?)
    public MobEffectInstance adjustEffectToSize(BrewEffect brewEffect) {
        return new MobEffectInstance(
                brewEffect.effect,
                //(int) ((originalEffect.getDuration() / 20) * this.cupSize) * 20,
                (int) ((brewEffect.duration / 20) * this.cupSize) * 20
                //Math.max(((int) ((originalEffect.getAmplifier() + 1) * this.cupSize) - 1), 0)
        );
    }

    @Override
    @Nonnull
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        Player player = entity instanceof Player ? (Player)entity : null;
        if (player instanceof ServerPlayer) {
            CriteriaTriggers.CONSUME_ITEM.trigger((ServerPlayer)player, stack);
        }

        if (!level.isClientSide && !RavenCoffeeCommonConfigs.DISABLE_COFFEE_BREW_EFFECTS.get()) {
            for(BrewEffect brewEffect : this.brew.effects) {
                if(!brewEffect.isForRemoving) {
                    double randomValue = Math.random();
                    if(randomValue < brewEffect.chance && randomValue != 0.0d) {
                        if (brewEffect.effect.isInstantenous()) {
                            MobEffectInstance adjustedEffect = this.adjustEffectToSize(brewEffect);
                            // TODO: ADD AMPLIFIER TO BREW EFFECT
                            brewEffect.effect.applyInstantenousEffect(player, player, entity, 1, 1.0D);
                        } else {
                            entity.addEffect(this.adjustEffectToSize(brewEffect));
                        }
                    }
                } else {
                    entity.removeEffect(brewEffect.effect);
                }
            }
        }

        if (player != null && !RavenCoffeeCommonConfigs.DISABLE_COFFEE_BREW_FOOD_VALUES.get()) {
            player.eat(level, stack);
            player.awardStat(Stats.ITEM_USED.get(this));
            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
            }
        }

        if (player == null || !player.getAbilities().instabuild) {
            if (stack.isEmpty()) {
                return new ItemStack(this.parentVessel);
            }

            if (player != null) {
                player.getInventory().add(new ItemStack(this.parentVessel));
            }
        }

        level.gameEvent(entity, GameEvent.DRINK, entity.getEyePosition());
        return stack;
    }


    public int getUseDuration(@Nonnull ItemStack p_77626_1_) {
        return 32;
    }

    @Override
    @Nonnull
    public UseAnim getUseAnimation(@Nonnull ItemStack p_77661_1_) {
        return UseAnim.DRINK;
    }

    @Override
    @Nonnull
    public InteractionResultHolder<ItemStack> use(@Nonnull Level level, @Nonnull Player player, @Nonnull InteractionHand hand) {
        // ItemUtils.useDrink(...) ?????
        return ItemUtils.startUsingInstantly(level, player, hand);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(@Nonnull ItemStack p_77624_1_, @Nullable Level p_77624_2_, @Nonnull List<Component> p_77624_3_, @Nonnull TooltipFlag p_77624_4_) {
        String descriptionKey = this.getDescriptionId().concat(".tooltip");
        String completeDescription = (Component.translatable(descriptionKey)).getString();
        if(!completeDescription.equals(descriptionKey))
            for(String line: completeDescription.split("<br>")) {
                p_77624_3_.add(Component.nullToEmpty(String.format(
                        "\u00A77%s\u00A77",
                        line
                )));
            }
    }

    public boolean hasEffect(@Nonnull ItemStack p_77636_1_) {
        return super.hasCustomEntity(p_77636_1_) || !PotionUtils.getPotion(p_77636_1_).getEffects().isEmpty();
    }

}
