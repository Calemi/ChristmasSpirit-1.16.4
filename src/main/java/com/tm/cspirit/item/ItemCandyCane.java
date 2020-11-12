package com.tm.cspirit.item;

import com.tm.cspirit.block.base.BlockItemBase;
import com.tm.cspirit.main.ChristmasSpirit;
import net.minecraft.block.Block;
import net.minecraft.item.Food;
import net.minecraft.item.Item;

public class ItemCandyCane extends BlockItemBase {

    public ItemCandyCane(Block block) {
        super(block, new Item.Properties().group(ChristmasSpirit.TAB_BAKING).food(new Food.Builder().hunger(3).saturation(3).setAlwaysEdible().build()));
    }
}
