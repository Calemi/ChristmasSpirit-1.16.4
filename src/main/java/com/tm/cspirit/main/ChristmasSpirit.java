package com.tm.cspirit.main;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.commons.lang3.reflect.FieldUtils;

@Mod(CSReference.MOD_ID)
public class ChristmasSpirit {

    public ChristmasSpirit() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onCommonSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onClientSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onLoadComplete);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, CSConfig.spec, "ChristmasSpirit.toml");
    }

    private void onCommonSetup(final FMLCommonSetupEvent event) {

    }

    private void onClientSetup(final FMLClientSetupEvent event) {

    }

    private void onLoadComplete(final FMLLoadCompleteEvent event) {

        DeferredWorkQueue.runLater(() -> {

            for (Biome b : ForgeRegistries.BIOMES) {

                if (b.getRegistryName() != null && b.getRegistryName().toString().contains("ocean")) {
                    continue;
                }

                Biome.Climate climate = new Biome.Climate(Biome.RainType.SNOW, -1, Biome.TemperatureModifier.NONE, 50);

                try {
                    FieldUtils.writeField(b, "climate", climate, true);
                }

                catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
