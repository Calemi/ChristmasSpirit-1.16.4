package com.tm.cspirit.tab;

import com.tm.cspirit.init.InitItems;
import com.tm.cspirit.main.CSReference;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class CSTabMain extends ItemGroup {

    public CSTabMain() {
        super(CSReference.MOD_ID + ".tabMain");
    }

    @Override
    public ItemStack createIcon () {
        return new ItemStack(InitItems.CHRISTMAS_HAT.get());
    }
}
