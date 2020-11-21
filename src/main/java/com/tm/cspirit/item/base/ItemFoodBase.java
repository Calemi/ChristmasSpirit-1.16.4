package com.tm.cspirit.item.base;

import com.tm.cspirit.main.ChristmasSpirit;
import com.tm.cspirit.util.helper.EffectHelper;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import java.util.List;

public class ItemFoodBase extends ItemBase implements IItemSpiritSupplier {

    private final int maxSpiritStack;
    private final boolean drink;

    public ItemFoodBase(Properties properties, int maxSpiritStack, boolean drink) {
        super(properties);
        this.drink = drink;
        this.maxSpiritStack = maxSpiritStack;
    }

    public ItemFoodBase(int foodHeal, float saturationHeal, int stackSize, int maxSpiritStack, boolean drink) {
        this(new Item.Properties().maxStackSize(stackSize).group(ChristmasSpirit.TAB_BAKING).food(new Food.Builder().hunger(foodHeal).saturation(saturationHeal).setAlwaysEdible().build()), maxSpiritStack, drink);
    }

    public ItemFoodBase(int foodHeal, float saturationHeal, int maxSpiritStack, boolean drink) {
        this(foodHeal, saturationHeal, 64, maxSpiritStack, drink);
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World world, LivingEntity entityLiving) {
        EffectHelper.giveHolidaySpiritStackEffect((PlayerEntity)entityLiving, maxSpiritStack);

        return super.onItemUseFinish(stack, world, entityLiving);
    }

    public UseAction getUseAction(ItemStack stack) {
        return drink ? UseAction.DRINK : UseAction.EAT;
    }

    @Override
    public int getMaxStacks() {
        return maxSpiritStack;
    }
}
