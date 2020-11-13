package com.tm.cspirit.event;

import com.tm.cspirit.data.PresentWorldSavedData;
import com.tm.cspirit.util.helper.PresentHelper;
import com.tm.cspirit.util.helper.TimeHelper;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class PresentResetEvent {

    @SubscribeEvent
    public void playerJoinEvent(PlayerEvent.PlayerLoggedInEvent event) {

        if (event.getPlayer().getEntityWorld().isRemote) return;

        ServerWorld world = (ServerWorld) event.getPlayer().getEntityWorld();
        PresentWorldSavedData data = PresentWorldSavedData.get(world);

        if (!data.playerGiftData.containsKey(event.getPlayer().getUniqueID())) {
            data.playerGiftData.put(event.getPlayer().getUniqueID(), TimeHelper.getCurrentDay());
            data.markDirty();
            giveGift((ServerPlayerEntity) event.getPlayer());
        }

        else {
            if (data.playerGiftData.get(event.getPlayer().getUniqueID()) != TimeHelper.getCurrentDay()) {
                giveGift((ServerPlayerEntity) event.getPlayer());
            }
        }
    }

    private void giveGift(ServerPlayerEntity player) {
        PresentHelper.giveSantaPresent(player, TimeHelper.getCurrentDay());
    }
}
