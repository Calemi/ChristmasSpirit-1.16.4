package com.tm.cspirit.main;

import com.tm.cspirit.init.InitFreezeWorld;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(CSReference.MOD_ID)
public class ChristmasSpirit {

    public ChristmasSpirit() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onCommonSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onClientSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onLoadComplete);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, CSConfig.spec, "ChristmasSpirit.toml");
    }

    private void onCommonSetup(final FMLCommonSetupEvent event) {}

    private void onClientSetup(final FMLClientSetupEvent event) {}

    private void onLoadComplete(final FMLLoadCompleteEvent event) {
        InitFreezeWorld.init();
    }
}
