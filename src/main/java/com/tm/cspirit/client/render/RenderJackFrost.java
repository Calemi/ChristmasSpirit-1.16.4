package com.tm.cspirit.client.render;

import com.tm.cspirit.client.model.ModelJackFrost;
import com.tm.cspirit.entity.EntityJackFrost;
import com.tm.cspirit.main.CSReference;
import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.util.ResourceLocation;

public class RenderJackFrost extends BipedRenderer<EntityJackFrost, ModelJackFrost<EntityJackFrost>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(CSReference.MOD_ID, "textures/entity/jack_frost.png");

    public RenderJackFrost(EntityRendererManager renderManager) {
        super(renderManager, new ModelJackFrost<>(0.0F, false), 0.5F);
        this.addLayer(new BipedArmorLayer<>(this, new ModelJackFrost<>(0.5F, true), new ModelJackFrost<>(1.0F, true)));
    }

    @Override
    public ResourceLocation getEntityTexture(EntityJackFrost entity) {
        return TEXTURE;
    }
}
