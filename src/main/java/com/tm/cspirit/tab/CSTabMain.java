package com.tm.cspirit.tab;

import com.tm.cspirit.init.InitItems;
import com.tm.cspirit.main.CSReference;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class CSTabMain extends ItemGroup {

    public CSTabMain() {
        super(CSReference.MOD_ID + ".tabMain");
    }

    @Override
    public ItemStack createIcon () {
        ItemStack stack = new ItemStack(InitItems.PEPPERMINT_LEAF.get());
        stack.setDamage(14);
        return stack;
    }
}
