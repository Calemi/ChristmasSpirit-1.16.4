package com.tm.cspirit.tab;

import com.tm.cspirit.init.InitItems;
import com.tm.cspirit.main.CSReference;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class CSTabBaking extends ItemGroup {

    public CSTabBaking() {
        super(CSReference.MOD_ID + ".tabBaking");
    }

    @Override
    public ItemStack createIcon () {
        ItemStack stack = new ItemStack(InitItems.SUGAR_COOKIE_CIRCLE.get());
        stack.setDamage(14);
        return stack;
    }
}
