package com.tm.cspirit.item;

import com.tm.cspirit.item.base.ItemBase;
import com.tm.cspirit.main.ChristmasSpirit;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.extensions.IForgeItem;

public class ItemCookieCutter extends ItemBase implements IForgeItem {

    public ItemCookieCutter() {
        super(new Item.Properties().group(ChristmasSpirit.TAB_BAKING).maxStackSize(1));
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        return new ItemStack(getItem());
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return true;
    }
}
