package com.tm.cspirit.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.tm.cspirit.client.model.ModelChristmasTree;
import com.tm.cspirit.entity.EntityChristmasTree;
import com.tm.cspirit.init.InitItems;
import com.tm.cspirit.main.CSReference;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;

import java.util.Random;

public class RenderChristmasTree extends EntityRenderer<EntityChristmasTree> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(CSReference.MOD_ID, "textures/entity/christmas_tree.png");
    private static final ResourceLocation TEXTURE_WHITE = new ResourceLocation(CSReference.MOD_ID, "textures/entity/christmas_tree_white.png");
    private final ModelChristmasTree christmasTree = new ModelChristmasTree();

    public RenderChristmasTree(EntityRendererManager renderManager) {
        super(renderManager);
    }

    @Override
    public void render(EntityChristmasTree entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight) {

        if (entity.hasStar()) {
            matrixStack.push();
            matrixStack.rotate(Vector3f.YN.rotationDegrees(entityYaw));
            renderDecoration(entity.getItemStackFromSlot(EquipmentSlotType.HEAD), 0, 0, 1.235F, 0 , partialTicks, matrixStack, buffer, combinedLight);
            matrixStack.pop();
        }

        ItemStack firstDecor = entity.getItemStackFromSlot(EquipmentSlotType.FEET);
        ItemStack secondDecor = entity.getItemStackFromSlot(EquipmentSlotType.LEGS);
        ItemStack thirdDecor = entity.getItemStackFromSlot(EquipmentSlotType.CHEST);

        if (!firstDecor.isEmpty()) {

            Random random1 = new Random(50000);
            Random random2 = new Random(60000);
            Random random3 = new Random(70000);

            float randomY1 = random1.nextFloat() * 0.1F - 0.045F;
            float randomY2 = random2.nextFloat() * 0.1F - 0.045F;
            float randomY3 = random3.nextFloat() * 0.1F - 0.045F;

            renderDecoration(firstDecor, randomY1, 0.2F, 0.12F + randomY1, -0.315F, partialTicks, matrixStack, buffer, combinedLight);
            renderDecoration(firstDecor, randomY2,-0.315F, 0.12F + randomY2, 0.2F, partialTicks, matrixStack, buffer, combinedLight);
            renderDecoration(firstDecor, randomY3,-0.1F, 0.12F + randomY1, 0.315F, partialTicks, matrixStack, buffer, combinedLight);

            renderDecoration(firstDecor, randomY1,-0.1F, 0.3F + randomY1, -0.264F, partialTicks, matrixStack, buffer, combinedLight);
            renderDecoration(firstDecor, randomY2,0.264F, 0.3F + randomY2, -0.1F, partialTicks, matrixStack, buffer, combinedLight);
            renderDecoration(firstDecor, randomY3,-0.264F, 0.3F + randomY3, -0.1F, partialTicks, matrixStack, buffer, combinedLight);

            renderDecoration(firstDecor, randomY1,-0.21F, 0.48F + randomY1, 0.1F, partialTicks, matrixStack, buffer, combinedLight);
            renderDecoration(firstDecor, randomY2,0.21F, 0.48F + randomY2, -0.05F, partialTicks, matrixStack, buffer, combinedLight);

            renderDecoration(firstDecor, randomY1,-0.1F, 0.66F + randomY1, -0.155F, partialTicks, matrixStack, buffer, combinedLight);
            renderDecoration(firstDecor, randomY2,0.1F, 0.66F +randomY2, 0.155F, partialTicks, matrixStack, buffer, combinedLight);

            renderDecoration(firstDecor, randomY1,0.11F, 0.84F + randomY1, 0F, partialTicks, matrixStack, buffer, combinedLight);

            renderDecoration(firstDecor, randomY1,-0.06F, 1.02F + randomY1, 0F, partialTicks, matrixStack, buffer, combinedLight);
        }

        if (!firstDecor.isEmpty()) {

            Random random1 = new Random(80000);
            Random random2 = new Random(90000);
            Random random3 = new Random(100000);

            float randomY1 = random1.nextFloat() * 0.1F - 0.05F;
            float randomY2 = random2.nextFloat() * 0.1F - 0.05F;
            float randomY3 = random3.nextFloat() * 0.1F - 0.05F;

            renderDecoration(secondDecor, randomY1,-0.15F, 0.12F + randomY1, -0.315F, partialTicks, matrixStack, buffer, combinedLight);
            renderDecoration(secondDecor, randomY2,0.315F, 0.12F + randomY2, -0.15F, partialTicks, matrixStack, buffer, combinedLight);
            renderDecoration(secondDecor, randomY3,0.315F, 0.12F + randomY3, 0.15F, partialTicks, matrixStack, buffer, combinedLight);

            renderDecoration(secondDecor, randomY1,0.1F, 0.3F + randomY1, -0.264F, partialTicks, matrixStack, buffer, combinedLight);
            renderDecoration(secondDecor, randomY2,0.264F, 0.3F + randomY2, 0.1F, partialTicks, matrixStack, buffer, combinedLight);
            renderDecoration(secondDecor, randomY3,0.15F, 0.3F + randomY3, 0.264F, partialTicks, matrixStack, buffer, combinedLight);

            renderDecoration(secondDecor, randomY1,-0.1F, 0.48F + randomY1, -0.21F, partialTicks, matrixStack, buffer, combinedLight);
            renderDecoration(secondDecor, randomY2,-0.1F, 0.48F + randomY2, 0.21F, partialTicks, matrixStack, buffer, combinedLight);

            renderDecoration(secondDecor, randomY1,-0.155F, 0.66F + randomY1, -0.1F, partialTicks, matrixStack, buffer, combinedLight);
            renderDecoration(secondDecor, randomY2,0.155F, 0.66F + randomY2, 0.1F, partialTicks, matrixStack, buffer, combinedLight);

            renderDecoration(secondDecor, randomY1,0F, 0.84F + randomY1, 0.11F, partialTicks, matrixStack, buffer, combinedLight);

            renderDecoration(secondDecor, randomY1,0.06F, 1.02F + randomY1, 0F, partialTicks, matrixStack, buffer, combinedLight);
        }

        if (!firstDecor.isEmpty()) {

            Random random1 = new Random(110000);
            Random random2 = new Random(120000);
            Random random3 = new Random(1300000);

            float randomY1 = random1.nextFloat() * 0.1F - 0.05F;
            float randomY2 = random2.nextFloat() * 0.1F - 0.05F;
            float randomY3 = random3.nextFloat() * 0.1F - 0.05F;

            renderDecoration(thirdDecor, randomY1,-0.315F, 0.12F + randomY1, -0.14F, partialTicks, matrixStack, buffer, combinedLight);
            renderDecoration(thirdDecor, randomY2,0.15F, 0.12F + randomY2, 0.315F, partialTicks, matrixStack, buffer, combinedLight);
            renderDecoration(thirdDecor, randomY3,0.05F, 0.12F + randomY3, -0.315F, partialTicks, matrixStack, buffer, combinedLight);

            renderDecoration(thirdDecor, randomY1,-0.1F, 0.3F + randomY1, 0.264F, partialTicks, matrixStack, buffer, combinedLight);
            renderDecoration(thirdDecor, randomY2,-0.264F, 0.3F + randomY2, 0.1F, partialTicks, matrixStack, buffer, combinedLight);

            renderDecoration(thirdDecor, randomY1,0.1F, 0.48F + randomY1, -0.21F, partialTicks, matrixStack, buffer, combinedLight);
            renderDecoration(thirdDecor, randomY2,-0.21F, 0.48F + randomY2, -0.1F, partialTicks, matrixStack, buffer, combinedLight);

            renderDecoration(thirdDecor, randomY1,-0.1F, 0.66F + randomY1, 0.155F, partialTicks, matrixStack, buffer, combinedLight);
            renderDecoration(thirdDecor, randomY2,0.155F, 0.66F + randomY2, -0.1F, partialTicks, matrixStack, buffer, combinedLight);

            renderDecoration(thirdDecor, randomY1,-0.11F, 0.84F + randomY1, 0.05F, partialTicks, matrixStack, buffer, combinedLight);
        }

        matrixStack.push();
        matrixStack.translate(0.0D, 1.5D, 0.0D);
        matrixStack.scale(-1.0F, -1.0F, 1.0F);

        IVertexBuilder ivertexbuilder = buffer.getBuffer(christmasTree.getRenderType(getEntityTexture(entity)));
        christmasTree.render(matrixStack, ivertexbuilder, combinedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);

        matrixStack.pop();
    }

    private void renderDecoration(ItemStack stack, float yRot, float x, float y, float z, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight) {

        matrixStack.push();
        matrixStack.scale(2.4F, 2.4F, 2.4F);
        matrixStack.translate(x, y, z);
        matrixStack.rotate(Vector3f.YP.rotationDegrees(yRot * 360 * 8));

        if (Block.getBlockFromItem(stack.getItem()) != InitItems.ORNAMENT_RED.get() && Block.getBlockFromItem(stack.getItem()) != InitItems.ORNAMENT_GREEN.get() && Block.getBlockFromItem(stack.getItem()) != InitItems.ORNAMENT_BLUE.get()) {

            if (stack.getItem() != InitItems.CHRISTMAS_LIGHT_WHITE.get() && stack.getItem() != InitItems.CHRISTMAS_LIGHT_RED.get() && stack.getItem() != InitItems.CHRISTMAS_LIGHT_GREEN.get() && stack.getItem() != InitItems.CHRISTMAS_LIGHT_BLUE.get()) {

                if (Block.getBlockFromItem(stack.getItem()) != InitItems.STAR.get()) {
                    matrixStack.scale(0.5F, 0.5F, 0.5F);
                }
            }
        }

        Minecraft.getInstance().getItemRenderer().renderItem(stack, ItemCameraTransforms.TransformType.GROUND, combinedLight, OverlayTexture.NO_OVERLAY, matrixStack, buffer);
        matrixStack.pop();
    }

    @Override
    public ResourceLocation getEntityTexture(EntityChristmasTree entity) {
        return entity.isWhite() ? TEXTURE_WHITE : TEXTURE;
    }
}
