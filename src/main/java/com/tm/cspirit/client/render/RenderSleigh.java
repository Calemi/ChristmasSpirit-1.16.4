package com.tm.cspirit.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.tm.cspirit.entity.EntitySleigh;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class RenderSleigh extends EntityRenderer<EntitySleigh> {

    public RenderSleigh(EntityRendererManager renderManager) {
        super(renderManager);
    }

    @Override
    public void render(EntitySleigh entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight) {
        super.render(entity, entityYaw, partialTicks, matrixStack, buffer, combinedLight);
    }

    @Override
    public ResourceLocation getEntityTexture(EntitySleigh entity) {
        return null;
    }
}
