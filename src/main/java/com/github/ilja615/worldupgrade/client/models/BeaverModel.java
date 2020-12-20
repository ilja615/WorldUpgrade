package com.github.ilja615.worldupgrade.client.models;

import com.github.ilja615.worldupgrade.entities.BeaverEntity;
import com.github.ilja615.worldupgrade.entities.GribberEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class BeaverModel extends EntityModel<BeaverEntity> {
    private final ModelRenderer body;
    private final ModelRenderer head;
    private final ModelRenderer tail;
    private final ModelRenderer frontleg1;
    private final ModelRenderer frontleg2;
    private final ModelRenderer backleg1;
    private final ModelRenderer backleg2;

    public BeaverModel() {
        textureWidth = 64;
        textureHeight = 64;

        body = new ModelRenderer(this);
        body.setRotationPoint(0.0F, 24.0F, 0.0F);
        body.setTextureOffset(0, 0).addBox(-5.5F, -9.0F, -7.0F, 11.0F, 7.0F, 14.0F, 0.0F, false);

        head = new ModelRenderer(this);
        head.setRotationPoint(0.0F, 18.75F, -7.0F);
        head.setTextureOffset(24, 21).addBox(-3.5F, -2.75F, -5.0F, 7.0F, 5.0F, 5.0F, 0.0F, false);
        head.setTextureOffset(0, 10).addBox(-1.5F, -0.75F, -6.0F, 3.0F, 2.0F, 1.0F, 0.0F, false);
        head.setTextureOffset(8, 10).addBox(3.5F, -2.75F, -2.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
        head.setTextureOffset(0, 21).addBox(-4.5F, -2.75F, -2.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
        head.setTextureOffset(39, 1).addBox(-2.5F, 2.25F, -5.0F, 5.0F, 1.0F, 0.0F, 0.0F, false);

        tail = new ModelRenderer(this);
        tail.setRotationPoint(-0.5F, 19.5F, 7.5F);
        tail.setTextureOffset(0, 21).addBox(-3.0F, -0.5F, -0.5F, 7.0F, 1.0F, 10.0F, 0.0F, false);

        frontleg1 = new ModelRenderer(this);
        frontleg1.setRotationPoint(4.0F, 22.0F, -4.0F);
        frontleg1.setTextureOffset(6, 2).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
        frontleg1.setTextureOffset(9, 1).addBox(-1.0F, 2.0F, -2.0F, 2.0F, 0.0F, 1.0F, 0.0F, false);

        frontleg2 = new ModelRenderer(this);
        frontleg2.setRotationPoint(-4.0F, 22.0F, -4.0F);
        frontleg2.setTextureOffset(6, 6).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
        frontleg2.setTextureOffset(9, 0).addBox(-1.0F, 2.0F, -2.0F, 2.0F, 0.0F, 1.0F, 0.0F, false);

        backleg1 = new ModelRenderer(this);
        backleg1.setRotationPoint(4.0F, 22.0F, 5.0F);
        backleg1.setTextureOffset(0, 4).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
        backleg1.setTextureOffset(0, 8).addBox(-1.0F, 2.0F, -3.0F, 2.0F, 0.0F, 2.0F, 0.0F, false);

        backleg2 = new ModelRenderer(this);
        backleg2.setRotationPoint(-4.0F, 22.0F, 4.0F);
        backleg2.setTextureOffset(0, 0).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
        backleg2.setTextureOffset(4, 0).addBox(-1.0F, 2.0F, -2.0F, 2.0F, 0.0F, 2.0F, 0.0F, false);
    }

    @Override
    public void setRotationAngles(BeaverEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
        //previously the render function, render code was moved to a method below
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        body.render(matrixStack, buffer, packedLight, packedOverlay);
        head.render(matrixStack, buffer, packedLight, packedOverlay);
        tail.render(matrixStack, buffer, packedLight, packedOverlay);
        frontleg1.render(matrixStack, buffer, packedLight, packedOverlay);
        frontleg2.render(matrixStack, buffer, packedLight, packedOverlay);
        backleg1.render(matrixStack, buffer, packedLight, packedOverlay);
        backleg2.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
