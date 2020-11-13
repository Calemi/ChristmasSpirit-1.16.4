package com.tm.cspirit.item;

import com.tm.cspirit.block.base.BlockItemBase;
import com.tm.cspirit.init.InitEffects;
import com.tm.cspirit.main.ChristmasSpirit;
import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;

public class ItemCandyCane extends BlockItemBase {

    public ItemCandyCane(Block block) {
        super(block, new Item.Properties().group(ChristmasSpirit.TAB_BAKING).food(new Food.Builder().hunger(3).saturation(3).setAlwaysEdible().build()));
    }

    @Override
    public void addInformation(ItemStack stack, World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        tooltip.add(new StringTextComponent("Max Effect Stacking: " + TextFormatting.GOLD + 2));
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World world, LivingEntity entityLiving) {

        int spiritStack = 0;

        if (entityLiving.getActivePotionEffect(InitEffects.CHRISTMAS_SPIRIT.get()) != null) {
            spiritStack = entityLiving.getActivePotionEffect(InitEffects.CHRISTMAS_SPIRIT.get()).getAmplifier() + 1;
        }

        if (spiritStack < 2) {
            entityLiving.addPotionEffect(new EffectInstance(InitEffects.CHRISTMAS_SPIRIT.get(), 20 * 60, spiritStack));
        }

        return super.onItemUseFinish(stack, world, entityLiving);
    }
}
