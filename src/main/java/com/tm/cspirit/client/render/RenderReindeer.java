package com.tm.cspirit.client.render;

import com.tm.cspirit.client.model.ModelReindeer;
import com.tm.cspirit.entity.EntityReindeer;
import com.tm.cspirit.main.CSReference;
import net.minecraft.client.renderer.entity.AbstractHorseRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderReindeer extends AbstractHorseRenderer<EntityReindeer, ModelReindeer<EntityReindeer>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(CSReference.MOD_ID, "textures/entity/reindeer.png");

    public RenderReindeer(EntityRendererManager renderManager) {
        super(renderManager, new ModelReindeer<>(0.0F), 1.1F);
        this.addLayer(new ReindeerMarkingsLayer(this));
        this.addLayer(new LeatherReindeerArmorLayer(this));
    }

    @Override
    public ResourceLocation getEntityTexture(EntityReindeer entity) {
        return TEXTURE;
    }
}
