package com.tm.cspirit.item.base;

import com.tm.cspirit.main.ChristmasSpirit;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;

public class ItemBase extends Item {

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

    @Override
    public void addInformation(ItemStack stack, World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
       if (hasTag(stack.getItem(), "naughty")) tooltip.add(new StringTextComponent(TextFormatting.RED + "Naughty Item"));
    }

    public ItemBase setTag(String tag) {
        this.tag = tag;
        return this;
    }

    public static boolean hasTag(Item item, String tag) {

        if (item instanceof ItemBase) {
            return ((ItemBase)item).tag.equalsIgnoreCase(tag);
        }

        return false;
    }
}
