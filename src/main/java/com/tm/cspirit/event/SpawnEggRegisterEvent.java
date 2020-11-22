package com.tm.cspirit.event;

import com.tm.cspirit.item.base.ItemSpawnEggBase;
import net.minecraft.entity.EntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class SpawnEggRegisterEvent {

    @SubscribeEvent
    public void onRegisterEntities(final RegistryEvent.Register<EntityType<?>> event) {
        ItemSpawnEggBase.initSpawnEggs();
    }
}
