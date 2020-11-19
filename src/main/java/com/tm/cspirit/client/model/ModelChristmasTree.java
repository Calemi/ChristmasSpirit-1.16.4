package com.tm.cspirit.client.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.tm.cspirit.entity.EntityChristmasTree;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelChristmasTree extends EntityModel {

    private final ModelRenderer bb_main;

    public ModelChristmasTree() {

        textureWidth = 128;
        textureHeight = 128;

        bb_main = new ModelRenderer(this);
        bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
        bb_main.setTextureOffset(0, 0).addBox(-2.0F, -14.0F, -2.0F, 4.0F, 14.0F, 4.0F, 0.0F, false);
        bb_main.setTextureOffset(16, 0).addBox(-1.5F, -28.0F, -1.5F, 3.0F, 14.0F, 3.0F, 0.0F, false);
        bb_main.setTextureOffset(28, 0).addBox(-1.0F, -42.0F, -1.0F, 2.0F, 14.0F, 2.0F, 0.0F, false);
        bb_main.setTextureOffset(0, 18).addBox(-12.0F, -15.0F, -12.0F, 24.0F, 7.0F, 24.0F, 0.0F, false);
        bb_main.setTextureOffset(0, 18).addBox(-10.0F, -22.1F, -10.0F, 20.0F, 14.0F, 20.0F, 0.0F, false);
        bb_main.setTextureOffset(0, 18).addBox(-8.0F, -29.1F, -8.0F, 16.0F, 14.0F, 16.0F, 0.0F, false);
        bb_main.setTextureOffset(0, 18).addBox(-6.0F, -36.3F, -6.0F, 12.0F, 14.0F, 12.0F, 0.0F, false);
        bb_main.setTextureOffset(0, 18).addBox(-4.0F, -43.2F, -4.0F, 8.0F, 14.0F, 8.0F, 0.0F, false);
        bb_main.setTextureOffset(0, 18).addBox(-2.0F, -50.1F, -2.0F, 4.0F, 14.0F, 4.0F, 0.0F, false);
    }

    @Override
    public void setRotationAngles(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        bb_main.render(matrixStack, buffer, packedLight, packedOverlay);
    }
}