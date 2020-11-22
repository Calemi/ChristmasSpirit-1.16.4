package com.tm.cspirit.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.tm.cspirit.client.model.ModelSleigh;
import com.tm.cspirit.entity.EntitySleigh;
import com.tm.cspirit.main.CSReference;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;

public class RenderSleigh extends EntityRenderer<EntitySleigh> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(CSReference.MOD_ID, "textures/entity/sleigh.png");

    private final ModelSleigh sleigh = new ModelSleigh();

    public RenderSleigh(EntityRendererManager renderManager) {
        super(renderManager);
    }

    @Override
    public void render(EntitySleigh entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight) {

        matrixStack.push();
        matrixStack.translate(0.0D, 1.1D, 0.0D);
        matrixStack.scale(-1.0F, -1.0F, 1.0F);
        matrixStack.scale(0.9F, 0.9F, 0.9F);
        matrixStack.rotate(Vector3f.YP.rotationDegrees(MathHelper.lerp(partialTicks, entity.prevRotationYaw, entity.rotationYaw) - 180));
        matrixStack.translate(0.0D, 0.0D, -0.5D);

        IVertexBuilder ivertexbuilder = buffer.getBuffer(sleigh.getRenderType(getEntityTexture(entity)));
        sleigh.render(matrixStack, ivertexbuilder, combinedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);

        matrixStack.pop();
    }

    @Override
    public ResourceLocation getEntityTexture(EntitySleigh entity) {
        return TEXTURE;
    }
}
