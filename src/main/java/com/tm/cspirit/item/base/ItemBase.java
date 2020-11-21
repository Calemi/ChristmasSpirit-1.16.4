package com.tm.cspirit.item.base;

import com.tm.cspirit.main.ChristmasSpirit;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class ItemBase extends Item implements IItemTag {

    private String tag = "";

    public ItemBase(Properties properties) {
        super(properties);
    }

    public ItemBase(ItemGroup tab) {
        super(new Item.Properties().group(tab));
    }

    public ItemBase() {
        super(new Item.Properties().group(ChristmasSpirit.TAB_MAIN));
    }

    public ItemBase setTag(String tag) {
        this.tag = tag;
        return this;
    }

    @Override
    public String getTag() {
        return tag;
    }
}
