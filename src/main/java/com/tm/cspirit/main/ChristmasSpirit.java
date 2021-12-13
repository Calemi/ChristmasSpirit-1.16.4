package com.tm.cspirit.main;

import com.tm.cspirit.client.gui.ScreenCookieTray;
import com.tm.cspirit.client.gui.ScreenPresentUnwrapped;
import com.tm.cspirit.client.render.*;
import com.tm.cspirit.command.CSCommandBase;
import com.tm.cspirit.data.DailyPresentDataFile;
import com.tm.cspirit.data.NaughtyListFile;
import com.tm.cspirit.data.SantaGiftListFile;
import com.tm.cspirit.entity.EntityJackFrost;
import com.tm.cspirit.entity.EntityReindeer;
import com.tm.cspirit.entity.data.CSDataSerializers;
import com.tm.cspirit.event.ItemTooltipOverrideEvent;
import com.tm.cspirit.event.SpawnEggRegisterEvent;
import com.tm.cspirit.init.*;
import com.tm.cspirit.packet.PacketReindeerJump;
import com.tm.cspirit.packet.PacketWrapPresent;
import com.tm.cspirit.tab.CSTabBaking;
import com.tm.cspirit.tab.CSTabDecoration;
import com.tm.cspirit.tab.CSTabMain;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.item.ItemGroup;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
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

    public static ChristmasSpirit instance;

    public static IEventBus MOD_EVENT_BUS;
    public static SimpleChannel network;

    public static final ItemGroup TAB_MAIN = new CSTabMain();
    public static final ItemGroup TAB_DECORATION = new CSTabDecoration();
    public static final ItemGroup TAB_BAKING = new CSTabBaking();

    public ChristmasSpirit() {

        instance = this;

        MOD_EVENT_BUS = FMLJavaModLoadingContext.get().getModEventBus();
        MOD_EVENT_BUS.addListener(this::onCommonSetup);
        MOD_EVENT_BUS.addListener(this::onClientSetup);
        MOD_EVENT_BUS.addListener(this::onLoadComplete);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, CSConfig.spec, CSReference.CONFIG_DIR + "/ChristmasSpirit.toml");
        DataSerializers.registerSerializer(CSDataSerializers.ITEMSTACK_ARRAY_4);
        InitSounds.SOUNDS.register(MOD_EVENT_BUS);
        InitEffects.POTION_TYPES.register(MOD_EVENT_BUS);
        InitTileEntityTypes.TILE_ENTITY_TYPES.register(MOD_EVENT_BUS);
        InitContainerTypes.CONTAINER_TYPES.register(MOD_EVENT_BUS);
        InitEntityTypes.ENTITY_TYPES.register(MOD_EVENT_BUS);
        InitItems.init();
        DailyPresentDataFile.init();
        SantaGiftListFile.init();
        NaughtyListFile.init();
    }

    private void onCommonSetup(final FMLCommonSetupEvent event) {

        network = NetworkRegistry.newSimpleChannel(new ResourceLocation(CSReference.MOD_ID, CSReference.MOD_ID), () -> "1.0", s -> true, s -> true);
        network.registerMessage(0, PacketWrapPresent.class, PacketWrapPresent::toBytes, PacketWrapPresent::new, PacketWrapPresent::handle);
        network.registerMessage(1, PacketReindeerJump.class, PacketReindeerJump::toBytes, PacketReindeerJump::new, PacketReindeerJump::handle);

        InitEvents.init();

        DeferredWorkQueue.runLater(() -> {
            GlobalEntityTypeAttributes.put(InitEntityTypes.JACK_FROST.get(), EntityJackFrost.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(InitEntityTypes.REINDEER.get(), EntityReindeer.setCustomAttributes().create());
        });
    }

    private void onClientSetup(final FMLClientSetupEvent event) {
        InitRenderLayers.init();

        ScreenManager.registerFactory(InitContainerTypes.PRESENT_UNWRAPPED.get(), ScreenPresentUnwrapped::new);
        ScreenManager.registerFactory(InitContainerTypes.COOKIE_TRAY.get(), ScreenCookieTray::new);

        RenderingRegistry.registerEntityRenderingHandler(InitEntityTypes.JACK_FROST.get(), RenderJackFrost::new);
        RenderingRegistry.registerEntityRenderingHandler(InitEntityTypes.REINDEER.get(), RenderReindeer::new);
        RenderingRegistry.registerEntityRenderingHandler(InitEntityTypes.CANDY_CANE_PROJECTILE.get(), RenderCandyCaneProjectile::new);
        RenderingRegistry.registerEntityRenderingHandler(InitEntityTypes.SLEIGH.get(), RenderSleigh::new);
        RenderingRegistry.registerEntityRenderingHandler(InitEntityTypes.CHRISTMAS_TREE.get(), RenderChristmasTree::new);

        ClientRegistry.bindTileEntityRenderer(InitTileEntityTypes.COOKIE_TRAY.get(), RenderCookieTray::new);

        MinecraftForge.EVENT_BUS.register(new SpawnEggRegisterEvent());
        MinecraftForge.EVENT_BUS.register(new ItemTooltipOverrideEvent());
    }

    private void onLoadComplete(final FMLLoadCompleteEvent event) {
        InitWorldGen.init();
    }

    @SubscribeEvent
    public void onServerStarting (FMLServerStartingEvent event) {
        CSCommandBase.register(event.getServer().getFunctionManager().getCommandDispatcher());
    }
}
