package com.tm.cspirit.item.base;

import com.tm.cspirit.main.ChristmasSpirit;
import net.minecraft.item.Item;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.item.Rarity;
import net.minecraft.util.SoundEvent;

public class ItemDiscBase extends MusicDiscItem {

    public ItemDiscBase(java.util.function.Supplier<SoundEvent> soundSupplier) {
        super(15, soundSupplier, new Item.Properties().group(ChristmasSpirit.TAB_MAIN).maxStackSize(1).rarity(Rarity.RARE));
    }
}
