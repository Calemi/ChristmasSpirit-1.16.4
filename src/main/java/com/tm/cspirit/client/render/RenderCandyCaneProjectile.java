package com.tm.cspirit.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.tm.cspirit.entity.EntityCandyCaneProjectile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;

public class RenderCandyCaneProjectile extends EntityRenderer<EntityCandyCaneProjectile> {

    public RenderCandyCaneProjectile(EntityRendererManager renderManager) {
        super(renderManager);
    }

    @Override
    public void render(EntityCandyCaneProjectile entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight) {
        super.render(entity, entityYaw, partialTicks, matrixStack, buffer, combinedLight);

        ItemStack cane = entity.getCandyCaneStack();

        matrixStack.push();

        matrixStack.rotate(Vector3f.YP.rotationDegrees(MathHelper.lerp(partialTicks, entity.prevRotationYaw, entity.rotationYaw) - 90.0F));
        matrixStack.rotate(Vector3f.ZP.rotationDegrees(MathHelper.lerp(partialTicks, entity.prevRotationPitch + 90, entity.rotationPitch + 90)));

        renderItem(cane, 0, 0, 0, partialTicks, matrixStack, buffer, combinedLight);

        matrixStack.pop();
    }

    private void renderItem (ItemStack stack, float x, float y, float z, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight) {

        matrixStack.push();
        matrixStack.scale(1, 1, 1);
        matrixStack.translate(x + 0.25F, y, z);
        Minecraft.getInstance().getItemRenderer().renderItem(stack, ItemCameraTransforms.TransformType.GROUND, combinedLight, OverlayTexture.NO_OVERLAY, matrixStack, buffer);
        matrixStack.pop();
    }

    @Override
    public ResourceLocation getEntityTexture(EntityCandyCaneProjectile entity) {
        return null;
    }
}
