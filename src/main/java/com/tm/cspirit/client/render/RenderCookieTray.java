package com.tm.cspirit.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.tm.cspirit.tileentity.TileEntityCookieTray;
import com.tm.cspirit.tileentity.base.CSItemHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderCookieTray extends TileEntityRenderer<TileEntityCookieTray> {

    public RenderCookieTray (TileEntityRendererDispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    public void render (TileEntityCookieTray tileEntity, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {

        CSItemHandler inv = tileEntity.getInventory();

        for (int i = 0; i < inv.getStackInSlot(0).getCount(); i++) {
            renderItem(inv.getStackInSlot(0), 0.5F, 0.1F + (i * 0.029F), 0.6F, partialTicks, matrixStack, buffer, combinedLight);
        }

        for (int i = 0; i < inv.getStackInSlot(1).getCount(); i++) {
            renderItem(inv.getStackInSlot(1), 0.9F, 0.099F + (i * 0.029F), 0.8F, partialTicks, matrixStack, buffer, combinedLight);
        }

        for (int i = 0; i < inv.getStackInSlot(2).getCount(); i++) {
            renderItem(inv.getStackInSlot(2), 0.9F, 0.098F + (i * 0.029F), 0.35F, partialTicks, matrixStack, buffer, combinedLight);
        }
    }

    private void renderItem (ItemStack stack, float x, float y, float z, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight) {

        matrixStack.push();
        matrixStack.scale(0.7F, 0.7F, 0.7F);
        matrixStack.translate(x, y, z);
        matrixStack.rotate(Vector3f.XP.rotationDegrees(90));
        Minecraft.getInstance().getItemRenderer().renderItem(stack, ItemCameraTransforms.TransformType.GROUND, combinedLight, OverlayTexture.NO_OVERLAY, matrixStack, buffer);
        matrixStack.pop();
    }
}
