package com.tm.cspirit.main;

import com.tm.cspirit.command.CSCommandBase;
import com.tm.cspirit.data.DailyPresentDataFile;
import com.tm.cspirit.data.NaughtyListFile;
import com.tm.cspirit.data.SantaGiftListFile;
import com.tm.cspirit.event.*;
import com.tm.cspirit.gui.ScreenPresentUnwrapped;
import com.tm.cspirit.init.*;
import com.tm.cspirit.packet.PacketWrapPresent;
import com.tm.cspirit.tab.CSTabBaking;
import com.tm.cspirit.tab.CSTabMain;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

@Mod(CSReference.MOD_ID)
@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class ChristmasSpirit {

    public static IEventBus MOD_EVENT_BUS;
    public static SimpleChannel network;

    public static final ItemGroup TAB_MAIN = new CSTabMain();
    public static final ItemGroup TAB_BAKING = new CSTabBaking();

    public ChristmasSpirit() {

        MOD_EVENT_BUS = FMLJavaModLoadingContext.get().getModEventBus();
        MOD_EVENT_BUS.addListener(this::onCommonSetup);
        MOD_EVENT_BUS.addListener(this::onClientSetup);
        MOD_EVENT_BUS.addListener(this::onLoadComplete);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, CSConfig.spec, CSReference.CONFIG_DIR + "/ChristmasSpirit.toml");
        InitSounds.SOUNDS.register(MOD_EVENT_BUS);
        InitEffects.POTION_TYPES.register(MOD_EVENT_BUS);
        InitTileEntityTypes.TILE_ENTITY_TYPES.register(MOD_EVENT_BUS);
        InitContainerTypes.CONTAINER_TYPES.register(MOD_EVENT_BUS);
        InitItems.init();
        DailyPresentDataFile.init();
        SantaGiftListFile.init();
        NaughtyListFile.init();
    }

    private void onCommonSetup(final FMLCommonSetupEvent event) {

        network = NetworkRegistry.newSimpleChannel(new ResourceLocation(CSReference.MOD_ID, CSReference.MOD_ID), () -> "1.0", s -> true, s -> true);
        network.registerMessage(0, PacketWrapPresent.class, PacketWrapPresent::toBytes, PacketWrapPresent::new, PacketWrapPresent::handle);

        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new JaredDiscEvent());
        MinecraftForge.EVENT_BUS.register(new PresentResetEvent());
        MinecraftForge.EVENT_BUS.register(new IceSkatesEvent());
        MinecraftForge.EVENT_BUS.register(new MobArmorEvent());
        MinecraftForge.EVENT_BUS.register(new SpriteCranberryEvent());
        MinecraftForge.EVENT_BUS.register(new NaughtyEvent());
    }

    private void onClientSetup(final FMLClientSetupEvent event) {
        InitRenderLayers.init();

        ScreenManager.registerFactory(InitContainerTypes.PRESENT_UNWRAPPED.get(), ScreenPresentUnwrapped::new);
    }

    private void onLoadComplete(final FMLLoadCompleteEvent event) {
        InitFreezeWorld.init();
    }

    @SubscribeEvent
    public void onServerStarting (FMLServerStartingEvent event) {
        CSCommandBase.register(event.getServer().getFunctionManager().getCommandDispatcher());
    }
}
