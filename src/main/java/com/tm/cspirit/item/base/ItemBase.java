package com.tm.cspirit.item.base;

import com.tm.cspirit.main.ChristmasSpirit;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class ItemBase extends Item implements IItemTag {

    private String[] tags = new String[] {};

    public ItemBase(Properties properties) {
        super(properties);
    }

    public ItemBase(ItemGroup tab) {
        super(new Item.Properties().group(tab));
    }

    public ItemBase() {
        super(new Item.Properties().group(ChristmasSpirit.TAB_MAIN));
    }

    public ItemBase addTag(String tag) {
        tags = new String[tags.length + 1];
        tags[tags.length - 1] = tag;
        return this;
    }

    @Override
    public String[] getItemTags() {
        return tags;
    }
}
