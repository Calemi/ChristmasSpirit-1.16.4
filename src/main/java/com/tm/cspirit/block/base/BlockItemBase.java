package com.tm.cspirit.block.base;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class BlockItemBase extends BlockItem {

    public BlockItemBase(Block block, Item.Properties properties) {
        super(block, properties);
    }

    public BlockItemBase(Block block, ItemGroup tab) {
        super(block, new Item.Properties().group(tab));
    }

    public BlockItemBase(Block block) {
        super(block, new Item.Properties());
    }
}
