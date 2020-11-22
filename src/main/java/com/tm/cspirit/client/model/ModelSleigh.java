package com.tm.cspirit.client.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSleigh extends EntityModel<Entity> {

    private final ModelRenderer Body;
    private final ModelRenderer Skate;
    private final ModelRenderer Skate2;
    private final ModelRenderer Chest;
    private final ModelRenderer Wall;
    private final ModelRenderer Loop;
    private final ModelRenderer Loop2;
    private final ModelRenderer Engine;
    private final ModelRenderer Detailing;
    private final ModelRenderer Detailing2;

    public ModelSleigh() {
        textureWidth = 256;
        textureHeight = 256;

        Body = new ModelRenderer(this);
        Body.setRotationPoint(-19.6F, 24.0F, -7.3F);
        Body.setTextureOffset(0, 0).addBox(4.0F, -14.0F, -19.0F, 32.0F, 2.0F, 55.0F, 0.0F, false);
        Body.setTextureOffset(0, 115).addBox(3.975F, -21.025F, -21.65F, 32.0F, 3.0F, 1.0F, 0.0F, false);

        ModelRenderer cube_r1 = new ModelRenderer(this);
        cube_r1.setRotationPoint(8.975F, -24.975F, -12.725F);
        Body.addChild(cube_r1);
        setRotationAngle(cube_r1, -2.3387F, 0.0F, 0.0F);
        cube_r1.setTextureOffset(0, 115).addBox(-5.0F, -3.0F, 0.0F, 32.0F, 3.0F, 1.0F, 0.0F, false);

        ModelRenderer cube_r2 = new ModelRenderer(this);
        cube_r2.setRotationPoint(36.0F, -15.9F, 4.0F);
        Body.addChild(cube_r2);
        setRotationAngle(cube_r2, 1.5708F, 0.0F, 0.0F);
        cube_r2.setTextureOffset(194, 24).addBox(-2.0F, -9.0F, 0.0F, 2.0F, 14.0F, 1.0F, 0.0F, false);
        cube_r2.setTextureOffset(197, 16).addBox(-32.025F, -9.0F, 0.0F, 2.0F, 14.0F, 1.0F, 0.0F, false);

        ModelRenderer cube_r3 = new ModelRenderer(this);
        cube_r3.setRotationPoint(36.0F, -31.45F, 23.35F);
        Body.addChild(cube_r3);
        setRotationAngle(cube_r3, -1.5708F, 0.0F, 0.0F);
        cube_r3.setTextureOffset(186, 20).addBox(-2.0F, -9.0F, 0.0F, 2.0F, 9.0F, 1.0F, 0.0F, false);

        ModelRenderer cube_r4 = new ModelRenderer(this);
        cube_r4.setRotationPoint(4.975F, -31.45F, 23.15F);
        Body.addChild(cube_r4);
        setRotationAngle(cube_r4, -1.5708F, 0.0F, 0.0F);
        cube_r4.setTextureOffset(184, 30).addBox(-1.0F, -9.0F, 0.0F, 2.0F, 9.0F, 1.0F, 0.0F, false);

        ModelRenderer cube_r5 = new ModelRenderer(this);
        cube_r5.setRotationPoint(36.0F, -31.9F, 32.175F);
        Body.addChild(cube_r5);
        setRotationAngle(cube_r5, -0.7854F, 0.0F, 0.0F);
        cube_r5.setTextureOffset(206, 16).addBox(-2.0F, -18.0F, 0.0F, 2.0F, 19.0F, 1.0F, 0.0F, false);
        cube_r5.setTextureOffset(227, 22).addBox(-32.025F, -18.0F, 0.0F, 2.0F, 19.0F, 1.0F, 0.0F, false);

        ModelRenderer cube_r6 = new ModelRenderer(this);
        cube_r6.setRotationPoint(36.0F, -16.6F, 8.475F);
        Body.addChild(cube_r6);
        setRotationAngle(cube_r6, -0.7854F, 0.0F, 0.0F);
        cube_r6.setTextureOffset(192, 20).addBox(-2.0F, -21.0F, 0.0F, 2.0F, 21.0F, 1.0F, 0.0F, false);

        ModelRenderer cube_r7 = new ModelRenderer(this);
        cube_r7.setRotationPoint(4.975F, -16.6F, 8.275F);
        Body.addChild(cube_r7);
        setRotationAngle(cube_r7, -0.7854F, 0.0F, 0.0F);
        cube_r7.setTextureOffset(189, 15).addBox(-1.0F, -21.0F, 0.0F, 2.0F, 21.0F, 1.0F, 0.0F, false);

        ModelRenderer cube_r8 = new ModelRenderer(this);
        cube_r8.setRotationPoint(32.975F, -33.05F, 50.35F);
        Body.addChild(cube_r8);
        setRotationAngle(cube_r8, 0.48F, 0.0F, 0.0F);
        cube_r8.setTextureOffset(70, 99).addBox(-27.0F, -8.0F, -1.0F, 28.0F, 6.0F, 2.0F, 0.0F, false);

        ModelRenderer cube_r9 = new ModelRenderer(this);
        cube_r9.setRotationPoint(34.875F, -33.95F, 49.95F);
        Body.addChild(cube_r9);
        setRotationAngle(cube_r9, 0.5236F, 0.0F, 0.0F);
        cube_r9.setTextureOffset(190, 18).addBox(-1.0F, -11.0F, -1.0F, 2.0F, 10.0F, 2.0F, 0.0F, false);

        ModelRenderer cube_r10 = new ModelRenderer(this);
        cube_r10.setRotationPoint(5.075F, -33.85F, 49.95F);
        Body.addChild(cube_r10);
        setRotationAngle(cube_r10, 0.5236F, 0.0F, 0.0F);
        cube_r10.setTextureOffset(191, 28).addBox(-1.0F, -11.0F, -1.0F, 2.0F, 10.0F, 2.0F, 0.0F, false);

        ModelRenderer cube_r11 = new ModelRenderer(this);
        cube_r11.setRotationPoint(4.975F, -22.55F, 44.1F);
        Body.addChild(cube_r11);
        setRotationAngle(cube_r11, -0.3142F, 0.0F, 0.0F);
        cube_r11.setTextureOffset(0, 99).addBox(-1.0F, -14.0F, 0.0F, 32.0F, 14.0F, 2.0F, 0.0F, false);

        ModelRenderer cube_r12 = new ModelRenderer(this);
        cube_r12.setRotationPoint(4.975F, -13.45F, 34.625F);
        Body.addChild(cube_r12);
        setRotationAngle(cube_r12, -0.7854F, 0.0F, 0.0F);
        cube_r12.setTextureOffset(0, 99).addBox(-1.0F, -14.0F, 0.0F, 32.0F, 14.0F, 2.0F, 0.0F, false);

        ModelRenderer cube_r13 = new ModelRenderer(this);
        cube_r13.setRotationPoint(4.975F, -15.9F, -5.0F);
        Body.addChild(cube_r13);
        setRotationAngle(cube_r13, 0.7854F, 0.0F, 0.0F);
        cube_r13.setTextureOffset(0, 115).addBox(-1.0F, -9.0F, 0.0F, 32.0F, 9.0F, 1.0F, 0.0F, false);

        ModelRenderer cube_r14 = new ModelRenderer(this);
        cube_r14.setRotationPoint(8.975F, -25.825F, -15.575F);
        Body.addChild(cube_r14);
        setRotationAngle(cube_r14, -1.8588F, 0.0F, 0.0F);
        cube_r14.setTextureOffset(0, 115).addBox(-5.0F, -3.0F, 0.0F, 32.0F, 3.0F, 1.0F, 0.0F, false);

        ModelRenderer cube_r15 = new ModelRenderer(this);
        cube_r15.setRotationPoint(8.975F, -25.9F, -18.55F);
        Body.addChild(cube_r15);
        setRotationAngle(cube_r15, -1.597F, 0.0F, 0.0F);
        cube_r15.setTextureOffset(0, 115).addBox(-5.0F, -3.0F, 0.0F, 32.0F, 3.0F, 1.0F, 0.0F, false);

        ModelRenderer cube_r16 = new ModelRenderer(this);
        cube_r16.setRotationPoint(8.975F, -23.85F, -20.75F);
        Body.addChild(cube_r16);
        setRotationAngle(cube_r16, -0.6981F, 0.0F, 0.0F);
        cube_r16.setTextureOffset(0, 115).addBox(-5.0F, -3.0F, 0.0F, 32.0F, 3.0F, 1.0F, 0.0F, false);

        ModelRenderer cube_r17 = new ModelRenderer(this);
        cube_r17.setRotationPoint(8.975F, -21.0F, -21.65F);
        Body.addChild(cube_r17);
        setRotationAngle(cube_r17, -0.3054F, 0.0F, 0.0F);
        cube_r17.setTextureOffset(0, 115).addBox(-5.0F, -3.0F, 0.0F, 32.0F, 3.0F, 1.0F, 0.0F, false);

        ModelRenderer cube_r18 = new ModelRenderer(this);
        cube_r18.setRotationPoint(8.975F, -15.225F, -21.025F);
        Body.addChild(cube_r18);
        setRotationAngle(cube_r18, 0.2182F, 0.0F, 0.0F);
        cube_r18.setTextureOffset(0, 115).addBox(-5.0F, -3.0F, 0.0F, 32.0F, 3.0F, 1.0F, 0.0F, false);

        ModelRenderer cube_r19 = new ModelRenderer(this);
        cube_r19.setRotationPoint(8.975F, -13.025F, -19.0F);
        Body.addChild(cube_r19);
        setRotationAngle(cube_r19, 0.7418F, 0.0F, 0.0F);
        cube_r19.setTextureOffset(0, 115).addBox(-5.0F, -3.0F, 0.0F, 32.0F, 3.0F, 1.0F, 0.0F, false);

        Skate = new ModelRenderer(this);
        Skate.setRotationPoint(1.85F, 27.4F, -0.05F);
        setRotationAngle(Skate, 0.0F, 0.0F, 0.3927F);
        Skate.setTextureOffset(0, 181).addBox(-20.6F, -1.0F, -42.3F, 1.0F, 1.0F, 75.0F, 0.0F, false);
        Skate.setTextureOffset(0, 245).addBox(-20.55F, -9.0F, 0.0F, 1.0F, 9.0F, 1.0F, 0.0F, false);
        Skate.setTextureOffset(0, 245).addBox(-20.55F, -9.0F, -20.875F, 1.0F, 9.0F, 1.0F, 0.0F, false);
        Skate.setTextureOffset(0, 245).addBox(-20.55F, -9.0F, 20.725F, 1.0F, 9.0F, 1.0F, 0.0F, false);

        ModelRenderer cube_r20 = new ModelRenderer(this);
        cube_r20.setRotationPoint(-19.55F, 0.0F, 0.0F);
        Skate.addChild(cube_r20);
        setRotationAngle(cube_r20, -1.1781F, 0.0F, 0.0F);
        cube_r20.setTextureOffset(4, 232).addBox(-1.006F, -23.3268F, -0.5471F, 1.0F, 22.0F, 1.0F, 0.0F, false);
        cube_r20.setTextureOffset(4, 232).addBox(-1.006F, -4.0409F, -8.5356F, 1.0F, 22.0F, 1.0F, 0.0F, false);

        ModelRenderer cube_r21 = new ModelRenderer(this);
        cube_r21.setRotationPoint(-19.6F, -1.575F, -43.125F);
        Skate.addChild(cube_r21);
        setRotationAngle(cube_r21, -0.6981F, 0.0F, 0.0F);
        cube_r21.setTextureOffset(71, 251).addBox(-1.0F, -0.338F, -2.3285F, 1.0F, 1.0F, 4.0F, 0.0F, false);

        ModelRenderer cube_r22 = new ModelRenderer(this);
        cube_r22.setRotationPoint(-19.6F, -4.325F, -45.425F);
        Skate.addChild(cube_r22);
        setRotationAngle(cube_r22, -1.1345F, 0.0F, 0.0F);
        cube_r22.setTextureOffset(71, 251).addBox(-1.0F, -0.338F, -2.3285F, 1.0F, 1.0F, 4.0F, 0.0F, false);

        ModelRenderer cube_r23 = new ModelRenderer(this);
        cube_r23.setRotationPoint(-19.6F, -7.8F, -46.275F);
        Skate.addChild(cube_r23);
        setRotationAngle(cube_r23, -1.6144F, 0.0F, 0.0F);
        cube_r23.setTextureOffset(71, 251).addBox(-1.0F, -0.338F, -2.3285F, 1.0F, 1.0F, 4.0F, 0.0F, false);

        ModelRenderer cube_r24 = new ModelRenderer(this);
        cube_r24.setRotationPoint(-19.6F, -11.2F, -45.375F);
        Skate.addChild(cube_r24);
        setRotationAngle(cube_r24, -2.138F, 0.0F, 0.0F);
        cube_r24.setTextureOffset(71, 251).addBox(-1.0F, -0.338F, -2.3285F, 1.0F, 1.0F, 4.0F, 0.0F, false);

        ModelRenderer cube_r25 = new ModelRenderer(this);
        cube_r25.setRotationPoint(-19.6F, -13.45F, -42.9F);
        Skate.addChild(cube_r25);
        setRotationAngle(cube_r25, -2.7925F, 0.0F, 0.0F);
        cube_r25.setTextureOffset(71, 251).addBox(-1.0F, -0.338F, -2.3285F, 1.0F, 1.0F, 4.0F, 0.0F, false);

        ModelRenderer cube_r26 = new ModelRenderer(this);
        cube_r26.setRotationPoint(-19.6F, -13.85F, -39.5F);
        Skate.addChild(cube_r26);
        setRotationAngle(cube_r26, 2.9234F, 0.0F, 0.0F);
        cube_r26.setTextureOffset(67, 247).addBox(-1.0F, -0.338F, -6.3285F, 1.0F, 1.0F, 8.0F, 0.0F, false);

        ModelRenderer cube_r27 = new ModelRenderer(this);
        cube_r27.setRotationPoint(-19.6F, -2.075F, 36.6F);
        Skate.addChild(cube_r27);
        setRotationAngle(cube_r27, -0.6109F, 0.0F, 0.0F);
        cube_r27.setTextureOffset(74, 250).addBox(-1.0F, -5.0F, 0.0F, 1.0F, 5.0F, 1.0F, 0.0F, false);

        ModelRenderer cube_r28 = new ModelRenderer(this);
        cube_r28.setRotationPoint(-19.6F, -0.95F, 32.375F);
        Skate.addChild(cube_r28);
        setRotationAngle(cube_r28, -1.2654F, 0.0F, 0.0F);
        cube_r28.setTextureOffset(74, 250).addBox(-1.0F, -5.0F, 0.0F, 1.0F, 5.0F, 1.0F, 0.0F, false);

        Skate2 = new ModelRenderer(this);
        Skate2.setRotationPoint(-1.25F, 27.4F, -0.05F);
        setRotationAngle(Skate2, 0.0F, 0.0F, -0.3927F);
        Skate2.setTextureOffset(0, 177).addBox(19.6F, -1.0F, -42.3F, 1.0F, 1.0F, 75.0F, 0.0F, false);
        Skate2.setTextureOffset(0, 245).addBox(19.55F, -9.0F, -20.875F, 1.0F, 9.0F, 1.0F, 0.0F, false);
        Skate2.setTextureOffset(0, 245).addBox(19.55F, -9.0F, -0.075F, 1.0F, 9.0F, 1.0F, 0.0F, false);
        Skate2.setTextureOffset(0, 245).addBox(19.55F, -9.0F, 20.725F, 1.0F, 9.0F, 1.0F, 0.0F, false);

        ModelRenderer cube_r29 = new ModelRenderer(this);
        cube_r29.setRotationPoint(19.55F, 0.0F, 0.0F);
        Skate2.addChild(cube_r29);
        setRotationAngle(cube_r29, -1.1781F, 0.0F, 0.0F);
        cube_r29.setTextureOffset(4, 232).addBox(0.006F, -22.2576F, -0.5758F, 1.0F, 21.0F, 1.0F, 0.0F, false);
        cube_r29.setTextureOffset(4, 232).addBox(0.006F, -3.0409F, -8.5356F, 1.0F, 21.0F, 1.0F, 0.0F, false);

        ModelRenderer cube_r30 = new ModelRenderer(this);
        cube_r30.setRotationPoint(19.6F, -1.575F, -43.125F);
        Skate2.addChild(cube_r30);
        setRotationAngle(cube_r30, -0.6981F, 0.0F, 0.0F);
        cube_r30.setTextureOffset(71, 251).addBox(0.0F, -0.338F, -2.3285F, 1.0F, 1.0F, 4.0F, 0.0F, false);

        ModelRenderer cube_r31 = new ModelRenderer(this);
        cube_r31.setRotationPoint(19.6F, -4.325F, -45.425F);
        Skate2.addChild(cube_r31);
        setRotationAngle(cube_r31, -1.1345F, 0.0F, 0.0F);
        cube_r31.setTextureOffset(71, 251).addBox(0.0F, -0.338F, -2.3285F, 1.0F, 1.0F, 4.0F, 0.0F, false);

        ModelRenderer cube_r32 = new ModelRenderer(this);
        cube_r32.setRotationPoint(19.6F, -7.8F, -46.275F);
        Skate2.addChild(cube_r32);
        setRotationAngle(cube_r32, -1.6144F, 0.0F, 0.0F);
        cube_r32.setTextureOffset(71, 251).addBox(0.0F, -0.338F, -2.3285F, 1.0F, 1.0F, 4.0F, 0.0F, false);

        ModelRenderer cube_r33 = new ModelRenderer(this);
        cube_r33.setRotationPoint(19.6F, -11.2F, -45.375F);
        Skate2.addChild(cube_r33);
        setRotationAngle(cube_r33, -2.138F, 0.0F, 0.0F);
        cube_r33.setTextureOffset(71, 251).addBox(0.0F, -0.338F, -2.3285F, 1.0F, 1.0F, 4.0F, 0.0F, false);

        ModelRenderer cube_r34 = new ModelRenderer(this);
        cube_r34.setRotationPoint(19.6F, -13.45F, -42.9F);
        Skate2.addChild(cube_r34);
        setRotationAngle(cube_r34, -2.7925F, 0.0F, 0.0F);
        cube_r34.setTextureOffset(71, 251).addBox(0.0F, -0.338F, -2.3285F, 1.0F, 1.0F, 4.0F, 0.0F, false);

        ModelRenderer cube_r35 = new ModelRenderer(this);
        cube_r35.setRotationPoint(19.6F, -13.85F, -39.5F);
        Skate2.addChild(cube_r35);
        setRotationAngle(cube_r35, 2.9234F, 0.0F, 0.0F);
        cube_r35.setTextureOffset(67, 247).addBox(0.0F, -0.338F, -6.3285F, 1.0F, 1.0F, 8.0F, 0.0F, false);

        ModelRenderer cube_r36 = new ModelRenderer(this);
        cube_r36.setRotationPoint(19.6F, -2.075F, 36.6F);
        Skate2.addChild(cube_r36);
        setRotationAngle(cube_r36, -0.6109F, 0.0F, 0.0F);
        cube_r36.setTextureOffset(74, 250).addBox(0.0F, -5.0F, 0.0F, 1.0F, 5.0F, 1.0F, 0.0F, false);

        ModelRenderer cube_r37 = new ModelRenderer(this);
        cube_r37.setRotationPoint(19.6F, -0.95F, 32.375F);
        Skate2.addChild(cube_r37);
        setRotationAngle(cube_r37, -1.2654F, 0.0F, 0.0F);
        cube_r37.setTextureOffset(74, 250).addBox(0.0F, -5.0F, 0.0F, 1.0F, 5.0F, 1.0F, 0.0F, false);

        Chest = new ModelRenderer(this);
        Chest.setRotationPoint(0.0F, 10.0F, 16.55F);
        Chest.setTextureOffset(152, 66).addBox(-13.5F, -10.15F, -6.05F, 28.0F, 5.0F, 23.0F, 0.0F, false);
        Chest.setTextureOffset(168, 82).addBox(-13.5F, -15.15F, 15.45F, 28.0F, 5.0F, 7.0F, 0.0F, false);
        Chest.setTextureOffset(194, 56).addBox(-14.0F, -25.15F, 22.25F, 29.0F, 8.0F, 2.0F, 0.0F, false);
        Chest.setTextureOffset(164, 116).addBox(-13.5F, -17.15F, 0.95F, 28.0F, 12.0F, 4.0F, 0.0F, false);
        Chest.setTextureOffset(166, 116).addBox(-13.5F, -24.15F, 20.95F, 28.0F, 12.0F, 2.0F, 0.0F, false);
        Chest.setTextureOffset(230, 116).addBox(-1.0F, -14.15F, -5.05F, 3.0F, 4.0F, 6.0F, 0.0F, false);
        Chest.setTextureOffset(230, 116).addBox(-1.0F, -19.15F, 16.25F, 3.0F, 4.0F, 6.0F, 0.0F, false);
        Chest.setTextureOffset(152, 94).addBox(-14.0F, -6.0F, -4.0F, 29.0F, 6.0F, 16.0F, 0.0F, false);

        Wall = new ModelRenderer(this);
        Wall.setRotationPoint(0.0F, 24.0F, 0.0F);
        Wall.setTextureOffset(0, 0).addBox(15.5F, -43.0F, -28.0F, 0.0F, 29.0F, 70.0F, 0.0F, false);
        Wall.setTextureOffset(0, 0).addBox(14.5F, -43.0F, -28.0F, 0.0F, 29.0F, 70.0F, 0.0F, false);
        Wall.setTextureOffset(0, 0).addBox(-15.0F, -43.0F, -28.0F, 0.0F, 29.0F, 70.0F, 0.0F, false);
        Wall.setTextureOffset(0, 0).addBox(-13.7F, -43.0F, -28.0F, 0.0F, 29.0F, 70.0F, 0.0F, false);

        Loop = new ModelRenderer(this);
        Loop.setRotationPoint(-14.2F, 23.625F, 11.975F);
        setRotationAngle(Loop, 0.2618F, 0.0F, 0.0F);
        Loop.setTextureOffset(162, 100).addBox(-1.0F, -35.05F, -32.525F, 1.0F, 2.0F, 1.0F, 0.0F, false);
        Loop.setTextureOffset(162, 100).addBox(-1.0F, -36.45F, -32.525F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        Loop.setTextureOffset(162, 100).addBox(-0.3F, -35.75F, -32.525F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        Loop.setTextureOffset(162, 100).addBox(-1.7F, -35.75F, -32.525F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        ModelRenderer cube_r38 = new ModelRenderer(this);
        cube_r38.setRotationPoint(0.0F, -35.025F, -32.525F);
        Loop.addChild(cube_r38);
        setRotationAngle(cube_r38, 0.0F, 0.0F, 0.7854F);
        cube_r38.setTextureOffset(162, 100).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        ModelRenderer cube_r39 = new ModelRenderer(this);
        cube_r39.setRotationPoint(-1.0F, -35.025F, -32.525F);
        Loop.addChild(cube_r39);
        setRotationAngle(cube_r39, 0.0F, 0.0F, 0.7854F);
        cube_r39.setTextureOffset(162, 100).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        ModelRenderer cube_r40 = new ModelRenderer(this);
        cube_r40.setRotationPoint(0.0F, -34.05F, -32.525F);
        Loop.addChild(cube_r40);
        setRotationAngle(cube_r40, 0.0F, 0.0F, 0.7854F);
        cube_r40.setTextureOffset(162, 100).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        ModelRenderer cube_r41 = new ModelRenderer(this);
        cube_r41.setRotationPoint(-1.0F, -34.05F, -32.525F);
        Loop.addChild(cube_r41);
        setRotationAngle(cube_r41, 0.0F, 0.0F, 0.7854F);
        cube_r41.setTextureOffset(162, 100).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        Loop2 = new ModelRenderer(this);
        Loop2.setRotationPoint(16.0F, 23.625F, 11.975F);
        setRotationAngle(Loop2, 0.2618F, 0.0F, 0.0F);
        Loop2.setTextureOffset(167, 98).addBox(-1.0F, -35.05F, -32.525F, 1.0F, 2.0F, 1.0F, 0.0F, false);
        Loop2.setTextureOffset(167, 98).addBox(-1.0F, -36.45F, -32.525F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        Loop2.setTextureOffset(167, 98).addBox(-0.3F, -35.75F, -32.525F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        Loop2.setTextureOffset(167, 98).addBox(-1.7F, -35.75F, -32.525F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        ModelRenderer cube_r42 = new ModelRenderer(this);
        cube_r42.setRotationPoint(0.0F, -35.025F, -32.525F);
        Loop2.addChild(cube_r42);
        setRotationAngle(cube_r42, 0.0F, 0.0F, 0.7854F);
        cube_r42.setTextureOffset(167, 98).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        ModelRenderer cube_r43 = new ModelRenderer(this);
        cube_r43.setRotationPoint(-1.0F, -35.025F, -32.525F);
        Loop2.addChild(cube_r43);
        setRotationAngle(cube_r43, 0.0F, 0.0F, 0.7854F);
        cube_r43.setTextureOffset(167, 98).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        ModelRenderer cube_r44 = new ModelRenderer(this);
        cube_r44.setRotationPoint(0.0F, -34.05F, -32.525F);
        Loop2.addChild(cube_r44);
        setRotationAngle(cube_r44, 0.0F, 0.0F, 0.7854F);
        cube_r44.setTextureOffset(167, 98).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        ModelRenderer cube_r45 = new ModelRenderer(this);
        cube_r45.setRotationPoint(-1.0F, -34.05F, -32.525F);
        Loop2.addChild(cube_r45);
        setRotationAngle(cube_r45, 0.0F, 0.0F, 0.7854F);
        cube_r45.setTextureOffset(167, 98).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        Engine = new ModelRenderer(this);
        Engine.setRotationPoint(0.0F, 24.0F, 0.0F);
        Engine.setTextureOffset(154, 132).addBox(-1.0F, -6.0F, 13.0F, 3.0F, 1.0F, 20.0F, 0.0F, false);
        Engine.setTextureOffset(177, 140).addBox(-1.0F, -7.0F, -6.0F, 3.0F, 1.0F, 20.0F, 0.0F, false);
        Engine.setTextureOffset(190, 141).addBox(-1.0F, -12.25F, 13.0F, 3.0F, 1.0F, 20.0F, 0.0F, false);
        Engine.setTextureOffset(166, 143).addBox(-1.0F, -11.25F, -6.0F, 3.0F, 1.0F, 20.0F, 0.0F, false);
        Engine.setTextureOffset(153, 195).addBox(-2.5F, -11.5F, 30.75F, 6.0F, 6.0F, 1.0F, 0.0F, false);
        Engine.setTextureOffset(178, 133).addBox(-1.0F, -10.15F, -14.0F, 3.0F, 3.0F, 36.0F, 0.0F, false);

        ModelRenderer cube_r46 = new ModelRenderer(this);
        cube_r46.setRotationPoint(5.0F, -11.1F, -2.25F);
        Engine.addChild(cube_r46);
        setRotationAngle(cube_r46, 0.0F, 0.0F, -0.7854F);
        cube_r46.setTextureOffset(181, 150).addBox(-3.0F, -1.0F, 0.0F, 6.0F, 1.0F, 1.0F, 0.0F, false);

        ModelRenderer cube_r47 = new ModelRenderer(this);
        cube_r47.setRotationPoint(5.0F, -11.2F, 6.75F);
        Engine.addChild(cube_r47);
        setRotationAngle(cube_r47, 0.0F, 0.0F, -0.7854F);
        cube_r47.setTextureOffset(77, 73).addBox(-3.0F, -1.0F, 0.0F, 6.0F, 1.0F, 1.0F, 0.0F, false);
        cube_r47.setTextureOffset(91, 73).addBox(-2.0F, -1.0F, 7.0F, 5.0F, 1.0F, 1.0F, 0.0F, false);
        cube_r47.setTextureOffset(115, 73).addBox(-2.0F, -1.0F, 13.0F, 5.0F, 1.0F, 1.0F, 0.0F, false);

        ModelRenderer cube_r48 = new ModelRenderer(this);
        cube_r48.setRotationPoint(-3.0F, -10.2F, 25.75F);
        Engine.addChild(cube_r48);
        setRotationAngle(cube_r48, 0.0F, 0.0F, 0.7854F);
        cube_r48.setTextureOffset(103, 73).addBox(-4.0F, -1.0F, 0.0F, 5.0F, 1.0F, 1.0F, 0.0F, false);
        cube_r48.setTextureOffset(168, 139).addBox(-4.0F, -1.0F, -28.0F, 6.0F, 1.0F, 1.0F, 0.0F, false);
        cube_r48.setTextureOffset(189, 156).addBox(-4.0F, -1.0F, -19.0F, 6.0F, 1.0F, 1.0F, 0.0F, false);
        cube_r48.setTextureOffset(103, 18).addBox(-4.0F, -1.0F, -12.0F, 5.0F, 1.0F, 1.0F, 0.0F, false);
        cube_r48.setTextureOffset(198, 154).addBox(-4.0F, -1.0F, -6.0F, 5.0F, 1.0F, 1.0F, 0.0F, false);

        ModelRenderer cube_r49 = new ModelRenderer(this);
        cube_r49.setRotationPoint(4.6F, -10.7F, 6.75F);
        Engine.addChild(cube_r49);
        setRotationAngle(cube_r49, 0.0F, 0.0F, -0.7854F);
        cube_r49.setTextureOffset(189, 156).addBox(-4.0F, -1.0F, 0.0F, 6.0F, 1.0F, 1.0F, 0.0F, false);

        ModelRenderer cube_r50 = new ModelRenderer(this);
        cube_r50.setRotationPoint(5.0F, -10.2F, 13.75F);
        Engine.addChild(cube_r50);
        setRotationAngle(cube_r50, 0.0F, 0.0F, -0.7854F);
        cube_r50.setTextureOffset(103, 18).addBox(-4.0F, -1.0F, 0.0F, 5.0F, 1.0F, 1.0F, 0.0F, false);

        ModelRenderer cube_r51 = new ModelRenderer(this);
        cube_r51.setRotationPoint(4.6929F, -11.6142F, 20.25F);
        Engine.addChild(cube_r51);
        setRotationAngle(cube_r51, 0.0F, 0.0F, -0.7854F);
        cube_r51.setTextureOffset(198, 154).addBox(-2.5F, -0.5F, -0.5F, 5.0F, 1.0F, 1.0F, 0.0F, false);

        ModelRenderer cube_r52 = new ModelRenderer(this);
        cube_r52.setRotationPoint(-1.125F, -8.125F, 13.0F);
        Engine.addChild(cube_r52);
        setRotationAngle(cube_r52, 0.0F, 0.0F, -1.5708F);
        cube_r52.setTextureOffset(169, 142).addBox(-1.0F, -1.0F, -19.0F, 3.0F, 1.0F, 20.0F, 0.0F, false);

        ModelRenderer cube_r53 = new ModelRenderer(this);
        cube_r53.setRotationPoint(-2.125F, -8.125F, 32.0F);
        Engine.addChild(cube_r53);
        setRotationAngle(cube_r53, 0.0F, 0.0F, -1.5708F);
        cube_r53.setTextureOffset(174, 146).addBox(-1.0F, -1.0F, -19.0F, 3.0F, 1.0F, 20.0F, 0.0F, false);

        ModelRenderer cube_r54 = new ModelRenderer(this);
        cube_r54.setRotationPoint(0.0F, -6.7F, 13.1F);
        Engine.addChild(cube_r54);
        setRotationAngle(cube_r54, 0.0F, 0.0F, -2.3562F);
        cube_r54.setTextureOffset(171, 172).addBox(0.0F, -1.0F, -19.0F, 2.0F, 1.0F, 20.0F, 0.0F, false);

        ModelRenderer cube_r55 = new ModelRenderer(this);
        cube_r55.setRotationPoint(2.4F, -8.125F, 13.1F);
        Engine.addChild(cube_r55);
        setRotationAngle(cube_r55, 0.0F, 0.0F, 2.3562F);
        cube_r55.setTextureOffset(158, 173).addBox(0.0F, -1.0F, -19.0F, 2.0F, 1.0F, 19.0F, 0.0F, false);

        ModelRenderer cube_r56 = new ModelRenderer(this);
        cube_r56.setRotationPoint(-0.7F, -11.25F, 13.1F);
        Engine.addChild(cube_r56);
        setRotationAngle(cube_r56, 0.0F, 0.0F, 2.3562F);
        cube_r56.setTextureOffset(161, 174).addBox(0.0F, -1.0F, -19.0F, 2.0F, 1.0F, 20.0F, 0.0F, false);

        ModelRenderer cube_r57 = new ModelRenderer(this);
        cube_r57.setRotationPoint(3.125F, -9.825F, 13.1F);
        Engine.addChild(cube_r57);
        setRotationAngle(cube_r57, 0.0F, 0.0F, -2.3562F);
        cube_r57.setTextureOffset(167, 174).addBox(0.0F, -1.0F, -19.0F, 2.0F, 1.0F, 20.0F, 0.0F, false);

        ModelRenderer cube_r58 = new ModelRenderer(this);
        cube_r58.setRotationPoint(3.125F, -8.125F, 13.0F);
        Engine.addChild(cube_r58);
        setRotationAngle(cube_r58, 0.0F, 0.0F, -1.5708F);
        cube_r58.setTextureOffset(186, 141).addBox(-1.0F, -1.0F, -19.0F, 3.0F, 1.0F, 20.0F, 0.0F, false);

        ModelRenderer cube_r59 = new ModelRenderer(this);
        cube_r59.setRotationPoint(4.125F, -8.125F, 32.0F);
        Engine.addChild(cube_r59);
        setRotationAngle(cube_r59, 0.0F, 0.0F, -1.5708F);
        cube_r59.setTextureOffset(167, 138).addBox(-1.0F, -1.0F, -19.0F, 3.0F, 1.0F, 20.0F, 0.0F, false);

        ModelRenderer cube_r60 = new ModelRenderer(this);
        cube_r60.setRotationPoint(2.7F, -11.55F, 32.0F);
        Engine.addChild(cube_r60);
        setRotationAngle(cube_r60, 0.0F, 0.0F, 0.7854F);
        cube_r60.setTextureOffset(186, 141).addBox(-1.0F, 0.0F, -19.0F, 3.0F, 1.0F, 20.0F, 0.0F, false);

        ModelRenderer cube_r61 = new ModelRenderer(this);
        cube_r61.setRotationPoint(2.7F, -5.7F, 32.0F);
        Engine.addChild(cube_r61);
        setRotationAngle(cube_r61, 0.0F, 0.0F, -0.7854F);
        cube_r61.setTextureOffset(162, 147).addBox(-1.0F, -1.0F, -19.0F, 3.0F, 1.0F, 20.0F, 0.0F, false);

        ModelRenderer cube_r62 = new ModelRenderer(this);
        cube_r62.setRotationPoint(-1.7F, -11.55F, 32.0F);
        Engine.addChild(cube_r62);
        setRotationAngle(cube_r62, 0.0F, 0.0F, -0.7854F);
        cube_r62.setTextureOffset(188, 136).addBox(-2.0F, 0.0F, -19.0F, 3.0F, 1.0F, 20.0F, 0.0F, false);

        ModelRenderer cube_r63 = new ModelRenderer(this);
        cube_r63.setRotationPoint(-1.7F, -5.7F, 32.0F);
        Engine.addChild(cube_r63);
        setRotationAngle(cube_r63, 0.0F, 0.0F, 0.7854F);
        cube_r63.setTextureOffset(202, 143).addBox(-2.0F, -1.0F, -19.0F, 3.0F, 1.0F, 20.0F, 0.0F, false);

        Detailing = new ModelRenderer(this);
        Detailing.setRotationPoint(0.0F, 24.0F, 0.0F);


        ModelRenderer cube_r64 = new ModelRenderer(this);
        cube_r64.setRotationPoint(16.25F, -21.0F, 21.0F);
        Detailing.addChild(cube_r64);
        setRotationAngle(cube_r64, -0.7854F, 0.0F, 0.0F);
        cube_r64.setTextureOffset(8, 224).addBox(-1.0F, -20.0F, 0.0F, 1.0F, 30.0F, 1.0F, 0.0F, false);

        ModelRenderer cube_r65 = new ModelRenderer(this);
        cube_r65.setRotationPoint(16.25F, -21.0F, 26.0F);
        Detailing.addChild(cube_r65);
        setRotationAngle(cube_r65, -0.7854F, 0.0F, 0.0F);
        cube_r65.setTextureOffset(8, 236).addBox(-1.0F, -8.0F, 0.0F, 1.0F, 18.0F, 1.0F, 0.0F, false);

        ModelRenderer cube_r66 = new ModelRenderer(this);
        cube_r66.setRotationPoint(16.25F, -21.0F, 16.0F);
        Detailing.addChild(cube_r66);
        setRotationAngle(cube_r66, -0.7854F, 0.0F, 0.0F);
        cube_r66.setTextureOffset(8, 243).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 11.0F, 1.0F, 0.0F, false);

        Detailing2 = new ModelRenderer(this);
        Detailing2.setRotationPoint(-30.75F, 24.0F, 0.0F);


        ModelRenderer cube_r67 = new ModelRenderer(this);
        cube_r67.setRotationPoint(16.25F, -21.0F, 21.0F);
        Detailing2.addChild(cube_r67);
        setRotationAngle(cube_r67, -0.7854F, 0.0F, 0.0F);
        cube_r67.setTextureOffset(8, 224).addBox(-1.0F, -20.0F, 0.0F, 1.0F, 30.0F, 1.0F, 0.0F, false);

        ModelRenderer cube_r68 = new ModelRenderer(this);
        cube_r68.setRotationPoint(16.25F, -21.0F, 26.0F);
        Detailing2.addChild(cube_r68);
        setRotationAngle(cube_r68, -0.7854F, 0.0F, 0.0F);
        cube_r68.setTextureOffset(8, 236).addBox(-1.0F, -8.0F, 0.0F, 1.0F, 18.0F, 1.0F, 0.0F, false);

        ModelRenderer cube_r69 = new ModelRenderer(this);
        cube_r69.setRotationPoint(16.25F, -21.0F, 16.0F);
        Detailing2.addChild(cube_r69);
        setRotationAngle(cube_r69, -0.7854F, 0.0F, 0.0F);
        cube_r69.setTextureOffset(8, 243).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 11.0F, 1.0F, 0.0F, false);
    }

    @Override
    public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
        //previously the render function, render code was moved to a method below
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        Body.render(matrixStack, buffer, packedLight, packedOverlay);
        Skate.render(matrixStack, buffer, packedLight, packedOverlay);
        Skate2.render(matrixStack, buffer, packedLight, packedOverlay);
        Chest.render(matrixStack, buffer, packedLight, packedOverlay);
        Wall.render(matrixStack, buffer, packedLight, packedOverlay);
        Loop.render(matrixStack, buffer, packedLight, packedOverlay);
        Loop2.render(matrixStack, buffer, packedLight, packedOverlay);
        Engine.render(matrixStack, buffer, packedLight, packedOverlay);
        Detailing.render(matrixStack, buffer, packedLight, packedOverlay);
        Detailing2.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}