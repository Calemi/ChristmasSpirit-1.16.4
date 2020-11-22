package com.tm.cspirit.event;

import com.tm.cspirit.item.base.IItemSpiritSupplier;
import com.tm.cspirit.util.helper.ItemHelper;
import net.minecraft.item.Item;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
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

        if (ItemHelper.hasTag(item, "naughty")) {
            event.getToolTip().add(new StringTextComponent(""));
            event.getToolTip().add(new TranslationTextComponent("grinch.icon"));
        }

        if (ItemHelper.hasTag(item, "disabled")) {
            event.getToolTip().add(new StringTextComponent(""));
            event.getToolTip().add(new StringTextComponent(TextFormatting.RED + "Disabled by config!"));
        }

        if (item instanceof IItemSpiritSupplier) {

            IItemSpiritSupplier supplier = (IItemSpiritSupplier) item;
            event.getToolTip().add(new TranslationTextComponent("santa.icon." + supplier.getMaxStacks()));
        }
    }
}
