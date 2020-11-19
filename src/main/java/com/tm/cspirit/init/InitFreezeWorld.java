package com.tm.cspirit.init;

import com.tm.cspirit.main.CSConfig;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.commons.lang3.reflect.FieldUtils;

public class InitFreezeWorld {

    public static void init() {

        if (CSConfig.worldGen.freezeWorld.get()) {
            DeferredWorkQueue.runLater(() -> freezeBiomes("field_242423_j", "field_242526_h", "field_235052_p_"));
            DeferredWorkQueue.runLater(() -> freezeBiomes("climate", "grassColorModifier", "effects"));
        }
    }

    private static void freezeBiomes(String climateVar, String grassColorModifierVar, String effectsVar) {

        for (Biome biome : ForgeRegistries.BIOMES) {

            if (!CSConfig.worldGen.freezeOceans.get()) {

                if (biome.getRegistryName() != null && biome.getRegistryName().toString().contains("ocean")) {
                    continue;
                }
            }

            Biome.Climate climate = new Biome.Climate(Biome.RainType.SNOW, -1, Biome.TemperatureModifier.NONE, 1);
            BiomeAmbience.GrassColorModifier colorModifier = BiomeAmbience.GrassColorModifier.NONE;
            BiomeAmbience ambience = biome.getAmbience();

            try {
                FieldUtils.writeField(biome, climateVar, climate, true);
                FieldUtils.writeField(ambience, grassColorModifierVar, colorModifier, true);
                FieldUtils.writeField(biome, effectsVar, ambience, true);
            }

            catch (IllegalAccessException ignored) {}
        }
    }
}
