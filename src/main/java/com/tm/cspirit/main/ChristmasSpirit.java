package com.tm.cspirit.main;

import com.tm.cspirit.event.JaredDiscEvent;
import com.tm.cspirit.init.InitFreezeWorld;
import com.tm.cspirit.init.InitItems;
import com.tm.cspirit.init.InitSounds;
import com.tm.cspirit.tab.CSTabMain;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(CSReference.MOD_ID)
public class ChristmasSpirit {

    public static IEventBus MOD_EVENT_BUS;

    public static final ItemGroup TAB_MAIN = new CSTabMain();

    public ChristmasSpirit() {

        MOD_EVENT_BUS = FMLJavaModLoadingContext.get().getModEventBus();
        MOD_EVENT_BUS.addListener(this::onCommonSetup);
        MOD_EVENT_BUS.addListener(this::onClientSetup);
        MOD_EVENT_BUS.addListener(this::onLoadComplete);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, CSConfig.spec, "ChristmasSpirit.toml");
        InitSounds.SOUNDS.register(MOD_EVENT_BUS);
        InitItems.init();
    }

    private void onCommonSetup(final FMLCommonSetupEvent event) {
        MinecraftForge.EVENT_BUS.register(new JaredDiscEvent());
    }

    private void onClientSetup(final FMLClientSetupEvent event) {}

    private void onLoadComplete(final FMLLoadCompleteEvent event) {
        InitFreezeWorld.init();
    }
}
