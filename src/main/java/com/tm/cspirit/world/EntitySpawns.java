package com.tm.cspirit.world;

import com.tm.cspirit.init.InitEntityTypes;
import com.tm.cspirit.main.CSReference;
import net.minecraft.entity.EntityClassification;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = CSReference.MOD_ID)
public class EntitySpawns {

    @SubscribeEvent
    public static void onBiomeLoaded(BiomeLoadingEvent event) {

        List<MobSpawnInfo.Spawners> spawner = event.getSpawns().getSpawner(EntityClassification.CREATURE);

        if(event.getCategory() != Biome.Category.NETHER && event.getCategory() != Biome.Category.THEEND && event.getCategory() != Biome.Category.OCEAN) {
            spawner.add(new MobSpawnInfo.Spawners(InitEntityTypes.REINDEER.get(), 3, 2, 4));
        }
    }
}
