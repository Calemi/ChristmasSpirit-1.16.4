package com.tm.cspirit.event;

import com.tm.cspirit.init.InitItems;
import com.tm.cspirit.util.ItemHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class JaredDiscEvent {

    @SubscribeEvent
    public void onKillJared(LivingDeathEvent event) {

        LivingEntity entity = event.getEntityLiving();

        if (entity instanceof PlayerEntity) {

            if (event.getSource().getTrueSource() instanceof PlayerEntity) {

                if (entity.getDisplayName().getString().equalsIgnoreCase("WilfredThePig")) {
                    ItemHelper.spawnStackAtEntity(entity.getEntityWorld(), entity, new ItemStack(InitItems.DISC_JARED.get()));
                }
            }
        }
    }
}
