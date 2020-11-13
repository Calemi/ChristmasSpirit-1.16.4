package com.tm.cspirit.present;

import com.tm.cspirit.init.InitItems;
import net.minecraft.block.Block;

public enum PresentStyle {

    RED (0, "Red", InitItems.PRESENT_WRAPPED_RED.get()),
    GREEN (1, "Green", InitItems.PRESENT_WRAPPED_GREEN.get()),
    BLUE (2, "Blue", InitItems.PRESENT_WRAPPED_BLUE.get());

    private final int index;
    private final String name;
    private final Block block;

    PresentStyle(int index, String name, Block block) {
        this.index = index;
        this.name = name;
        this.block = block;
    }

    public static PresentStyle fromIndex(int index) {

        PresentStyle foundColor = RED;

        for (PresentStyle color : PresentStyle.values()) {

            if (color.index == index) {
                foundColor = color;
                break;
            }
        }

        return foundColor;
    }

    public int getIndex() {
        return this.index;
    }

    public String getName() {
        return this.name;
    }

    public Block getBlock() {
        return this.block;
    }
}
