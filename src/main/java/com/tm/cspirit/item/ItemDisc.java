package com.tm.cspirit.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.item.Rarity;
import net.minecraft.util.SoundEvent;

import java.util.function.Supplier;

public class ItemDisc extends MusicDiscItem {

    public ItemDisc(Supplier<SoundEvent> soundSupplier) {
        super(15, soundSupplier, new Item.Properties().maxStackSize(1).rarity(Rarity.RARE));
    }

    public ItemDisc(Supplier<SoundEvent> soundSupplier, ItemGroup tab) {
        super(15, soundSupplier, new Item.Properties().group(tab).maxStackSize(1).rarity(Rarity.RARE));
    }
}
