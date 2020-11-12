package com.tm.cspirit.item.base;

import com.tm.cspirit.main.ChristmasSpirit;
import net.minecraft.item.Food;
import net.minecraft.item.Item;

public class ItemFoodBase extends ItemBase {

    public ItemFoodBase(int hunger, float saturation) {
        super(new Item.Properties().group(ChristmasSpirit.TAB_BAKING).food(new Food.Builder().hunger(hunger).saturation(saturation).setAlwaysEdible().build()));
    }
}
