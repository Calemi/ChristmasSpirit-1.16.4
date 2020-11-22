package com.tm.cspirit.event;

import com.tm.cspirit.main.CSConfig;
import com.tm.cspirit.util.helper.ItemHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class DisableItemsEvent {

    @SubscribeEvent(priority = EventPriority.HIGH)
    public void onEntityUpdate(LivingEvent.LivingUpdateEvent event) {

        if (!CSConfig.misc.naughtyItems.get()) {

            if (!event.getEntityLiving().world.isRemote) {

                if (event.getEntityLiving().getEntityWorld().getGameTime() % 20 * 60 == 0) {

                    if (event.getEntityLiving() instanceof PlayerEntity) {

                        PlayerEntity player = (PlayerEntity) event.getEntityLiving();

                        for (int i = 0; i < player.inventory.getSizeInventory(); i++) {

                            ItemStack stackInSlot = player.inventory.getStackInSlot(i);

                            if (ItemHelper.hasTag(stackInSlot.getItem(), "disabled")) {

                                player.inventory.setInventorySlotContents(i, ItemStack.EMPTY);
                            }
                        }
                    }
                }
            }
        }
    }
}
