package com.tm.cspirit.init;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class InitRenderLayers {

    public static void init() {

        RenderTypeLookup.setRenderLayer(InitItems.PRESENT_UNWRAPPED.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(InitItems.PRESENT_WRAPPED_RED.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(InitItems.PRESENT_WRAPPED_GREEN.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(InitItems.PRESENT_WRAPPED_BLUE.get(), RenderType.getCutout());

        RenderTypeLookup.setRenderLayer(InitItems.GINGER.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(InitItems.PEPPERMINT.get(), RenderType.getCutout());

        RenderTypeLookup.setRenderLayer(InitItems.GARLAND.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(InitItems.GINGERBREAD_HOUSE.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(InitItems.GINGERBREAD_HOUSE.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(InitItems.MISTLETOE.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(InitItems.ICICLES.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(InitItems.FROSTED_GLASS.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(InitItems.FROSTED_GLASS_PANE.get(), RenderType.getTranslucent());
    }
}
