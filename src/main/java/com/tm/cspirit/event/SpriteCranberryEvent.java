package com.tm.cspirit.event;

import com.tm.cspirit.init.InitItems;
import com.tm.cspirit.init.InitSounds;
import com.tm.cspirit.util.helper.SoundHelper;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class SpriteCranberryEvent {

    @SubscribeEvent
    public void theAnswerIsClear(PlayerEvent.ItemPickupEvent event) {

        if (event.getStack().getItem() == InitItems.SODA_SPRITE_CRANBERRY.get()) {

            if (!event.getEntityLiving().getEntityWorld().isRemote) SoundHelper.sendSoundToClient((ServerPlayerEntity) event.getPlayer(), InitSounds.THE_ANSWER_IS_CLEAR.get());
            event.getPlayer().playSound(InitSounds.THE_ANSWER_IS_CLEAR.get(), 1, 1);
        }
    }

    @SubscribeEvent
    public void wannaSpriteCranberry(ItemTossEvent event) {

        if (!event.getEntity().getEntityWorld().isRemote) {

            if (event.getEntityItem().getItem().getItem() == InitItems.SODA_SPRITE_CRANBERRY.get()) {
                event.getEntity().playSound(InitSounds.WANNA_SPRITE_CRANBERRY.get(), 1, 1);
            }
        }
    }
}
