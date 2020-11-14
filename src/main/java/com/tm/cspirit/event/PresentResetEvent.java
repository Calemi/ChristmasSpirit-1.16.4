package com.tm.cspirit.event;

import com.tm.cspirit.data.DailyPresentDataFile;
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
        ServerPlayerEntity serverPlayer = (ServerPlayerEntity) event.getPlayer();

        if (!DailyPresentDataFile.hasReceivedPreset(serverPlayer)) {
            giveGift(serverPlayer);
        }
    }

    private void giveGift(ServerPlayerEntity player) {
        PresentHelper.giveSantaPresent(player, TimeHelper.getCurrentDay());
    }
}
