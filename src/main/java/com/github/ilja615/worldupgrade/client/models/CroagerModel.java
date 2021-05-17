package com.github.ilja615.worldupgrade.client.models;

import com.github.ilja615.worldupgrade.entities.BeaverEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class CroagerModel extends EntityModel<BeaverEntity> {
    private final ModelRenderer body;
    private final ModelRenderer headbottom;
    private final ModelRenderer cube_r1;
    private final ModelRenderer headtop;
    private final ModelRenderer legfront1;
    private final ModelRenderer legfront2;
    private final ModelRenderer legback1;
    private final ModelRenderer legback2;

    public CroagerModel() {
        textureWidth = 64;
        textureHeight = 64;

        body = new ModelRenderer(this);
        body.setRotationPoint(0.0F, 14.5F, 0.0F);
        setRotationAngle(body, -0.3491F, 0.0F, 0.0F);
        body.setTextureOffset(0, 0).addBox(-6.0F, -5.184F, -5.1206F, 12.0F, 10.0F, 12.0F, 0.0F, false);

        headbottom = new ModelRenderer(this);
        headbottom.setRotationPoint(0.0F, 9.0F, -4.0F);


        cube_r1 = new ModelRenderer(this);
        cube_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
        headbottom.addChild(cube_r1);
        setRotationAngle(cube_r1, 0.3054F, 0.0F, 0.0F);
        cube_r1.setTextureOffset(0, 45).addBox(-4.0F, 0.0F, -7.0F, 8.0F, 5.0F, 7.0F, 0.0F, false);

        headtop = new ModelRenderer(this);
        headtop.setRotationPoint(0.0F, 9.0F, -4.0F);
        headtop.setTextureOffset(0, 22).addBox(-4.0F, -2.0F, -7.0F, 8.0F, 2.0F, 7.0F, 0.0F, false);
        headtop.setTextureOffset(36, 0).addBox(-4.0F, -4.0F, -6.0F, 1.0F, 2.0F, 4.0F, 0.0F, false);
        headtop.setTextureOffset(36, 6).addBox(3.0F, -4.0F, -6.0F, 1.0F, 2.0F, 4.0F, 0.0F, false);

        legfront1 = new ModelRenderer(this);
        legfront1.setRotationPoint(-4.0F, 16.0F, -6.0F);
        legfront1.setTextureOffset(48, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);
        legfront1.setTextureOffset(0, 60).addBox(-3.0F, 8.0F, -4.0F, 5.0F, 0.0F, 4.0F, 0.0F, false);

        legfront2 = new ModelRenderer(this);
        legfront2.setRotationPoint(4.0F, 16.0F, -6.0F);
        legfront2.setTextureOffset(56, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);
        legfront2.setTextureOffset(0, 60).addBox(-2.0F, 8.0F, -4.0F, 5.0F, 0.0F, 4.0F, 0.0F, false);

        legback1 = new ModelRenderer(this);
        legback1.setRotationPoint(-6.0F, 19.0F, 3.0F);
        legback1.setTextureOffset(0, 31).addBox(-2.0F, -1.0F, -5.0F, 2.0F, 6.0F, 7.0F, 0.0F, false);
        legback1.setTextureOffset(42, 58).addBox(-4.0F, 5.0F, -8.0F, 5.0F, 0.0F, 6.0F, 0.0F, false);

        legback2 = new ModelRenderer(this);
        legback2.setRotationPoint(7.0F, 19.0F, 2.0F);
        legback2.setTextureOffset(18, 31).addBox(-1.0F, -1.0F, -4.0F, 2.0F, 6.0F, 7.0F, 0.0F, false);
        legback2.setTextureOffset(42, 58).addBox(-2.0F, 5.0F, -7.0F, 5.0F, 0.0F, 6.0F, 0.0F, false);
    }

    @Override
    public void setRotationAngles(BeaverEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
        //previously the render function, render code was moved to a method below
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        body.render(matrixStack, buffer, packedLight, packedOverlay);
        headbottom.render(matrixStack, buffer, packedLight, packedOverlay);
        headtop.render(matrixStack, buffer, packedLight, packedOverlay);
        legfront1.render(matrixStack, buffer, packedLight, packedOverlay);
        legfront2.render(matrixStack, buffer, packedLight, packedOverlay);
        legback1.render(matrixStack, buffer, packedLight, packedOverlay);
        legback2.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}