package com.tm.cspirit.event;

import com.tm.cspirit.item.base.IItemSpiritSupplier;
import com.tm.cspirit.item.base.IItemTag;
import net.minecraft.item.Item;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ItemTooltipOverrideEvent {

    @SubscribeEvent(priority = EventPriority.HIGH)
    public void onTooltip(ItemTooltipEvent event) {

        Item item = event.getItemStack().getItem();

        if (item.isFood() && item.getFood() != null) {

            event.getToolTip().add(new TranslationTextComponent("hunger.icon." + item.getFood().getHealing()));
            event.getToolTip().add(new TranslationTextComponent("saturation.icon." + item.getFood().getSaturation()));
        }

        if (item instanceof IItemTag) {

            IItemTag tag = (IItemTag) item;

            if (tag.getTag().equalsIgnoreCase("naughty")) {
                event.getToolTip().add(new StringTextComponent(""));
                event.getToolTip().add(new TranslationTextComponent("grinch.icon"));
            }
        }

        if (item instanceof IItemSpiritSupplier) {

            IItemSpiritSupplier supplier = (IItemSpiritSupplier) item;
            event.getToolTip().add(new TranslationTextComponent("santa.icon." + supplier.getMaxStacks()));
        }
    }
}
