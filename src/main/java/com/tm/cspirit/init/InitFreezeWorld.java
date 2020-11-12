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

            DeferredWorkQueue.runLater(() -> {

                for (Biome biome : ForgeRegistries.BIOMES) {

                    if (!CSConfig.worldGen.freezeOceans.get()) {

                        if (biome.getRegistryName() != null && biome.getRegistryName().toString().contains("ocean")) {
                            continue;
                        }
                    }

                    Biome.Climate climate = new Biome.Climate(Biome.RainType.SNOW, -1, Biome.TemperatureModifier.NONE, 1);
                    BiomeAmbience ambience = biome.getAmbience();
                    BiomeAmbience.GrassColorModifier colorModifier = BiomeAmbience.GrassColorModifier.NONE;

                    try {
                        FieldUtils.writeField(biome, "climate", climate, true);
                        FieldUtils.writeField(ambience, "grassColorModifier", colorModifier, true);
                        FieldUtils.writeField(biome, "effects", ambience, true);
                    }

                    catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
