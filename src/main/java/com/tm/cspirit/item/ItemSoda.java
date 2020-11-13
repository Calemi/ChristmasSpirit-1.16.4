package com.tm.cspirit.item;

import com.tm.cspirit.init.InitSounds;
import com.tm.cspirit.item.base.ItemFoodBase;
import com.tm.cspirit.util.helper.ItemHelper;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import java.util.List;

public class ItemSoda extends ItemFoodBase {

    public ItemSoda(int hunger, float saturation) {
        super(hunger, saturation, 1, 2, true);
    }

    @Override
    public void addInformation(ItemStack stack, World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        super.addInformation(stack, world, tooltip, flag);
        tooltip.add(new StringTextComponent(isOpened(stack) ? "Opened" : "Unopened"));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick (World world, PlayerEntity player, Hand hand) {
        player.setActiveHand(hand);
        return new ActionResult<>(ActionResultType.PASS, player.getHeldItem(hand));
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {

        if (!isOpened(stack)) {

            CompoundNBT nbt = ItemHelper.getNBT(stack);
            nbt.putBoolean("Opened", true);

            entityLiving.playSound(InitSounds.CAN_OPEN.get(), 1, 1);

            return stack;
        }

        return super.onItemUseFinish(stack, worldIn, entityLiving);
    }

    private boolean isOpened(ItemStack stack) {
        CompoundNBT nbt = ItemHelper.getNBT(stack);
        return nbt.getBoolean("Opened");
    }

    @Override
    public UseAction getUseAction (ItemStack stack) {
        return isOpened(stack) ? UseAction.DRINK : UseAction.BOW;
    }

    @Override
    public int getUseDuration (ItemStack stack) {
        return isOpened(stack) ? 40 : 20;
    }
}
