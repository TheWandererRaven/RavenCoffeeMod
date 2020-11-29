package com.TheWandererRaven.ravencoffee.items;

import com.TheWandererRaven.ravencoffee.customClasses.Brew;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.*;
import net.minecraft.potion.*;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DrinkHelper;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

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
        return PotionUtils.addPotionToItemStack(super.getDefaultInstance(), Potions.SWIFTNESS);
    }

    public EffectInstance adjustEffectToSize(EffectInstance originalEffect) {
        return new EffectInstance(
                originalEffect.getPotion(),
                (int) ((originalEffect.getDuration() / 20) * this.cupSize) * 20,
                Math.max(((int) ((originalEffect.getAmplifier() + 1) * this.cupSize) - 1), 0)
        );
    }

    @Override
    @Nonnull
    public ItemStack onItemUseFinish(@Nonnull ItemStack p_77654_1_, @Nonnull World p_77654_2_, @Nonnull LivingEntity p_77654_3_) {
        PlayerEntity lvt_4_1_ = p_77654_3_ instanceof PlayerEntity ? (PlayerEntity)p_77654_3_ : null;
        if (lvt_4_1_ instanceof ServerPlayerEntity) {
            CriteriaTriggers.CONSUME_ITEM.trigger((ServerPlayerEntity)lvt_4_1_, p_77654_1_);
        }
        if (!p_77654_2_.isRemote) {
            for(EffectInstance lvt_7_1_: brew.effects) {
                p_77654_3_.sendMessage(ITextComponent.getTextComponentOrEmpty(
                        lvt_7_1_.getEffectName() + " effect time: " + lvt_7_1_.getDuration()/20 + " seconds"),
                        UUID.randomUUID()
                );
                double randVal = Math.random();
                int effectIndex = brew.effects.indexOf(lvt_7_1_);
                double effectChance = (brew.effectsChances.size() >= effectIndex + 1)
                        ? brew.effectsChances.get(effectIndex)
                        : 0.0d;
                if (randVal < effectChance && randVal != 0.0d) {
                    if (lvt_7_1_.getPotion().isInstant()) {
                        lvt_7_1_.getPotion().affectEntity(lvt_4_1_, lvt_4_1_, p_77654_3_, lvt_7_1_.getAmplifier(), 0.0d);
                    } else {
                        p_77654_3_.addPotionEffect(this.adjustEffectToSize(lvt_7_1_));
                    }
                }
            }
            if (!brew.removableEffects.isEmpty())
                for(Effect lvt_7_2_: brew.removableEffects) {
                    p_77654_3_.removePotionEffect(lvt_7_2_);
                }
        }

        if (lvt_4_1_ != null) {
            lvt_4_1_.addStat(Stats.ITEM_USED.get(this));
            if (!lvt_4_1_.abilities.isCreativeMode) {
                p_77654_1_.shrink(1);
            }
        }

        if (lvt_4_1_ == null || !lvt_4_1_.abilities.isCreativeMode) {
            if (p_77654_1_.isEmpty()) {
                return new ItemStack(parentContainer);
            }

            if (lvt_4_1_ != null) {
                lvt_4_1_.inventory.addItemStackToInventory(new ItemStack(parentContainer));
            }
        }

        return p_77654_1_;
    }

    public int getUseDuration(@Nonnull ItemStack p_77626_1_) {
        return 32;
    }

    @Override
    @Nonnull
    public UseAction getUseAction(@Nonnull ItemStack p_77661_1_) {
        return UseAction.DRINK;
    }

    @Override
    @Nonnull
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World p_77659_1_, @Nonnull PlayerEntity p_77659_2_, @Nonnull Hand p_77659_3_) {
        return DrinkHelper.startDrinking(p_77659_1_, p_77659_2_, p_77659_3_);
    }

    @OnlyIn(Dist.CLIENT)
    public void addInformation(@Nonnull ItemStack p_77624_1_, @Nullable World p_77624_2_, @Nonnull List<ITextComponent> p_77624_3_, @Nonnull ITooltipFlag p_77624_4_) {
        String descriptionKey = this.getTranslationKey().concat(".description");
        String completeDescription = (new TranslationTextComponent(descriptionKey)).getString();
        if(!completeDescription.equals(descriptionKey))
            for(String line: completeDescription.split("<br>")) {
                p_77624_3_.add(ITextComponent.getTextComponentOrEmpty(String.format(
                        "\u00A77%s\u00A77",
                        line
                )));
            }
    }

    public boolean hasEffect(@Nonnull ItemStack p_77636_1_) {
        return super.hasEffect(p_77636_1_) || !PotionUtils.getEffectsFromStack(p_77636_1_).isEmpty();
    }

}
