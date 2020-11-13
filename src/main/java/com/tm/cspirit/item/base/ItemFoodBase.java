package com.tm.cspirit.item.base;

import com.tm.cspirit.init.InitEffects;
import com.tm.cspirit.main.ChristmasSpirit;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;

public class ItemFoodBase extends ItemBase {

    private final int maxSpiritStack;
    private final boolean drink;

    public ItemFoodBase(Properties properties, int maxSpiritStack, boolean drink) {
        super(properties);
        this.drink = drink;
        this.maxSpiritStack = maxSpiritStack;
    }

    public ItemFoodBase(int hunger, float saturation, int stackSize, int maxSpiritStack, boolean drink) {
        this(new Item.Properties().maxStackSize(stackSize).group(ChristmasSpirit.TAB_BAKING).food(new Food.Builder().hunger(hunger).saturation(saturation).setAlwaysEdible().build()), maxSpiritStack, drink);
    }

    public ItemFoodBase(int hunger, float saturation, int maxSpiritStack, boolean drink) {
        this(hunger, saturation, 64, maxSpiritStack, drink);
    }

    @Override
    public void addInformation(ItemStack stack, World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        tooltip.add(new StringTextComponent("Max Effect Stacking: " + TextFormatting.GOLD + maxSpiritStack));
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World world, LivingEntity entityLiving) {

        int spiritStack = 0;

        if (entityLiving.getActivePotionEffect(InitEffects.CHRISTMAS_SPIRIT.get()) != null) {
            spiritStack = entityLiving.getActivePotionEffect(InitEffects.CHRISTMAS_SPIRIT.get()).getAmplifier() + 1;
        }

        if (spiritStack < maxSpiritStack) {
            entityLiving.addPotionEffect(new EffectInstance(InitEffects.CHRISTMAS_SPIRIT.get(), 20 * 60, spiritStack));
        }

        return super.onItemUseFinish(stack, world, entityLiving);
    }

    public UseAction getUseAction(ItemStack stack) {
        return drink ? UseAction.DRINK : UseAction.EAT;
    }
}
