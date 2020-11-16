package com.tm.cspirit.init;

import com.tm.cspirit.event.*;
import com.tm.cspirit.main.ChristmasSpirit;
import net.minecraftforge.common.MinecraftForge;

public class InitEvents {

    public static void init() {
        MinecraftForge.EVENT_BUS.register(ChristmasSpirit.instance);
        MinecraftForge.EVENT_BUS.register(new JaredDiscEvent());
        MinecraftForge.EVENT_BUS.register(new PresentResetEvent());
        MinecraftForge.EVENT_BUS.register(new IceSkatesEvent());
        MinecraftForge.EVENT_BUS.register(new MobArmorEvent());
        MinecraftForge.EVENT_BUS.register(new SpriteCranberryEvent());
        MinecraftForge.EVENT_BUS.register(new NaughtyEvent());
        MinecraftForge.EVENT_BUS.register(new FrozenEvent());
        MinecraftForge.EVENT_BUS.register(new JackFrostEvent());
    }
}
