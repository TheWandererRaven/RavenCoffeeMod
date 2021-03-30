package com.TheWandererRaven.ravencoffee.items;

import com.TheWandererRaven.ravencoffee.customClasses.ThrowableFoodEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.world.World;

import java.util.UUID;

public class ItemThrowable extends ItemBase {
    public ItemThrowable() { super(); }
    public ItemThrowable(ItemGroup tab) { super(tab); }
    public ItemThrowable(Properties props) { super(props); }
    public ActionResult<ItemStack> onItemRightClick(World p_77659_1_, PlayerEntity p_77659_2_, Hand p_77659_3_) {
        ItemStack lvt_4_1_ = p_77659_2_.getHeldItem(p_77659_3_);
        p_77659_1_.playSound((PlayerEntity)null, p_77659_2_.getPosX(), p_77659_2_.getPosY(), p_77659_2_.getPosZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
        if (!p_77659_1_.isRemote) {
            ThrowableFoodEntity lvt_5_1_ = new ThrowableFoodEntity(p_77659_1_, p_77659_2_);
            lvt_5_1_.setItem(lvt_4_1_);
            lvt_5_1_.func_234612_a_(p_77659_2_, p_77659_2_.rotationPitch, p_77659_2_.rotationYaw, 0.0F, 1.5F, 1.0F);
            p_77659_1_.addEntity(lvt_5_1_);
        }

        p_77659_2_.addStat(Stats.ITEM_USED.get(this));
        if (!p_77659_2_.abilities.isCreativeMode) {
            lvt_4_1_.shrink(1);
        }

        return ActionResult.func_233538_a_(lvt_4_1_, p_77659_1_.isRemote());
    }
    public ActionResultType onItemUse(ItemUseContext p_195939_1_) {
        World p_77659_1_ = p_195939_1_.getWorld();
        PlayerEntity p_77659_2_ = p_195939_1_.getPlayer();
        Hand p_77659_3_ = p_195939_1_.getHand();


        if (this.isFood()) {
            ItemStack itemstack = p_77659_2_.getHeldItem(p_77659_3_);
            if (p_77659_2_.canEat(this.getFood().canEatWhenFull())) {
                p_77659_2_.setActiveHand(p_77659_3_);
                return ActionResult.resultConsume(itemstack).getType();
            } else {
                return ActionResult.resultFail(itemstack).getType();
            }
        } else {
            return ActionResult.resultPass(p_77659_2_.getHeldItem(p_77659_3_)).getType();
        }
    }
}
