package com.tm.cspirit.event;

import com.tm.cspirit.init.InitWorldGen;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class WorldGenEvents {

    @SubscribeEvent(priority = EventPriority.HIGH)
    public void onBiomeLoad(BiomeLoadingEvent event) {
        //Register our cool new winter garden <3
        Biome.Category category = event.getCategory();
        if (category == Biome.Category.FOREST || category == Biome.Category.PLAINS || category == Biome.Category.TAIGA || category == Biome.Category.ICY || category == Biome.Category.EXTREME_HILLS || category == Biome.Category.SWAMP) {
            event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> InitWorldGen.WINTER_GARDEN);
        }
    }

}
