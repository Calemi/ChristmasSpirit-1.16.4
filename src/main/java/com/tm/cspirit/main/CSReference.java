package com.tm.cspirit.main;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.loading.FMLPaths;

public class CSReference {

    public static final String MOD_ID = "cspirit";
    public static final String MOD_NAME = "Christmas Spirit";
    public static final String CONFIG_DIR = FMLPaths.CONFIGDIR.get().toString() + "/ChristmasSpirit";

    public static final ResourceLocation GUI_TEXTURES = new ResourceLocation(MOD_ID + ":textures/gui/gui_textures.png");
}
