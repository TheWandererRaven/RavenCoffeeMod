package com.TheWandererRaven.ravencoffee.items;

import com.TheWandererRaven.ravencoffee.customClasses.Brew;
import com.TheWandererRaven.ravencoffee.customClasses.BrewEffect;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class CoffeeBrew extends Item {
    private final Item parentContainer;
    private final double cupSize;
    private final Brew brew;

    public CoffeeBrew(double _cupSize, Item _parentContainer, Brew _brew, Item.Properties p_i48476_1_) {
        super(p_i48476_1_);
        this.parentContainer = _parentContainer;
        this.cupSize = _cupSize;
        this.brew = _brew;
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
    public ItemStack finishUsingItem(@Nonnull ItemStack stack, @Nonnull Level world, @Nonnull LivingEntity entity) {
        Player player = entity instanceof Player ? (Player)entity : null;
        if (player instanceof ServerPlayer) {
            CriteriaTriggers.CONSUME_ITEM.trigger((ServerPlayer)player, stack);
        }
        if (world.isClientSide) {
            for(BrewEffect brewEffect: brew.effects) {
                if(!brewEffect.isForRemoving) {
                    double randVal = Math.random();
                    if (randVal < brewEffect.chance && randVal != 0.0d) {
                        // TODO: REWORK THIS CODE (I have no idea if the effect works smoothly without the conditionals)
                        if (brewEffect.effect.isInstantenous()) {
                            MobEffectInstance adjustedEffect = this.adjustEffectToSize(brewEffect);
                            adjustedEffect.applyEffect(entity);
                        } else {
                            if (brewEffect.effect.equals(MobEffects.HEAL))
                                entity.setHealth(entity.getHealth() + 2.0f);
                            else
                                entity.addEffect(this.adjustEffectToSize(brewEffect));
                        }
                    }
                } else {
                    entity.removeEffect(brewEffect.effect);
                }
            }
        }

        if (player != null) {
            player.awardStat(Stats.ITEM_USED.get(this));
            if (!player.isCreative()) {
                stack.shrink(1);
            }
        }

        if (player == null || !player.isCreative()) {
            if (stack.isEmpty()) {
                return new ItemStack(parentContainer);
            }

            if (player != null) {
                player.getInventory().add(new ItemStack(parentContainer));
            }
        }

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
    public InteractionResultHolder<ItemStack> use(@Nonnull Level p_77659_1_, @Nonnull Player p_77659_2_, @Nonnull InteractionHand p_77659_3_) {
        // ItemUtils.useDrink(...) ?????
        return ItemUtils.startUsingInstantly(p_77659_1_, p_77659_2_, p_77659_3_);
    }

    @OnlyIn(Dist.CLIENT)
    public void addInformation(@Nonnull ItemStack p_77624_1_, @Nullable Level p_77624_2_, @Nonnull List<Component> p_77624_3_, @Nonnull TooltipFlag p_77624_4_) {
        String descriptionKey = this.getDescriptionId().concat(".description");
        String completeDescription = (new TranslatableComponent(descriptionKey)).getString();
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
