package com.tm.cspirit.item.base;

import com.tm.cspirit.init.InitEffects;
import com.tm.cspirit.main.ChristmasSpirit;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;

public class ItemFoodBase extends ItemBase {

    public ItemFoodBase(int hunger, float saturation) {
        super(new Item.Properties().group(ChristmasSpirit.TAB_BAKING).food(new Food.Builder().hunger(hunger).saturation(saturation).setAlwaysEdible().effect(() -> new EffectInstance(InitEffects.CHRISTMAS_SPIRIT.get(), 400, 1), 1).build()));
    }
}
