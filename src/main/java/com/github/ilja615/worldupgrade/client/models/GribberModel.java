package com.github.ilja615.worldupgrade.client.models;

import com.github.ilja615.worldupgrade.entities.GribberEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class GribberModel extends EntityModel<GribberEntity> {
    private final ModelRenderer leg1;
    private final ModelRenderer feet1;
    private final ModelRenderer toe;
    private final ModelRenderer feetwing;
    private final ModelRenderer leg2;
    private final ModelRenderer feet2;
    private final ModelRenderer toe2;
    private final ModelRenderer feetwing2;
    private final ModelRenderer body;
    private final ModelRenderer bodybonearmor;
    private final ModelRenderer tail;
    private final ModelRenderer wing1;
    private final ModelRenderer wing2;
    private final ModelRenderer neck;
    private final ModelRenderer head;
    private final ModelRenderer headbonearmor;
    private final ModelRenderer headbonespike;

    public GribberModel() {
        texWidth = 128;
        texHeight = 128;

        leg1 = new ModelRenderer(this);
        leg1.setPos(-5.0F, 12.0F, 4.0F);
        leg1.texOffs(53, 63).addBox(-2.0F, 0.0F, -1.0F, 3.0F, 12.0F, 2.0F, 0.0F, false);

        feet1 = new ModelRenderer(this);
        feet1.setPos(7.0F, 13.0F, -4.0F);
        leg1.addChild(feet1);
        feet1.texOffs(0, 34).addBox(-10.0F, -3.0F, -2.0F, 5.0F, 2.0F, 5.0F, 0.0F, false);

        toe = new ModelRenderer(this);
        toe.setPos(-8.5F, -2.0F, -2.0F);
        feet1.addChild(toe);
        toe.texOffs(0, 19).addBox(-1.5F, -1.0F, -3.0F, 5.0F, 2.0F, 3.0F, 0.0F, false);

        feetwing = new ModelRenderer(this);
        feetwing.setPos(-11.0F, -2.0F, 0.5F);
        feet1.addChild(feetwing);
        feetwing.texOffs(0, 63).addBox(-11.0F, 0.0F, -3.5F, 12.0F, 0.0F, 7.0F, 0.0F, false);

        leg2 = new ModelRenderer(this);
        leg2.setPos(5.0F, 12.0F, 4.0F);
        leg2.texOffs(0, 0).addBox(-1.0F, 0.0F, -1.0F, 3.0F, 12.0F, 2.0F, 0.0F, false);

        feet2 = new ModelRenderer(this);
        feet2.setPos(-7.0F, 12.0F, -4.0F);
        leg2.addChild(feet2);
        feet2.texOffs(0, 27).addBox(5.0F, -2.0F, -2.0F, 5.0F, 2.0F, 5.0F, 0.0F, false);

        toe2 = new ModelRenderer(this);
        toe2.setPos(6.5F, -2.0F, -2.0F);
        feet2.addChild(toe2);
        toe2.texOffs(0, 14).addBox(-1.5F, 0.0F, -3.0F, 5.0F, 2.0F, 3.0F, 0.0F, false);

        feetwing2 = new ModelRenderer(this);
        feetwing2.setPos(11.0F, -1.0F, 0.5F);
        feet2.addChild(feetwing2);
        feetwing2.texOffs(46, 42).addBox(-1.0F, 0.0F, -3.5F, 12.0F, 0.0F, 7.0F, 0.0F, false);

        body = new ModelRenderer(this);
        body.setPos(0.0F, 8.0F, 1.0F);
        body.texOffs(0, 32).addBox(-8.0F, -6.0F, -8.0F, 16.0F, 10.0F, 21.0F, 0.0F, false);

        bodybonearmor = new ModelRenderer(this);
        bodybonearmor.setPos(0.0F, 24.0F, -6.0F);
        body.addChild(bodybonearmor);
        bodybonearmor.texOffs(54, 97).addBox(-8.0F, -30.0F, -2.0F, 16.0F, 10.0F, 21.0F, 0.5F, false);

        tail = new ModelRenderer(this);
        tail.setPos(0.0F, 0.0F, 13.0F);
        body.addChild(tail);
        setRotationAngle(tail, 0.1745F, 0.0F, 0.0F);
        tail.texOffs(50, 50).addBox(-6.0F, 0.0F, 0.0F, 12.0F, 0.0F, 24.0F, 0.0F, false);

        wing1 = new ModelRenderer(this);
        wing1.setPos(-8.0F, -5.0F, -2.0F);
        body.addChild(wing1);
        wing1.texOffs(0, 16).addBox(-30.0F, 0.0F, -8.0F, 30.0F, 0.0F, 16.0F, 0.0F, false);

        wing2 = new ModelRenderer(this);
        wing2.setPos(8.0F, -7.0F, -5.0F);
        body.addChild(wing2);
        wing2.texOffs(0, 0).addBox(0.0F, 2.0F, -5.0F, 30.0F, 0.0F, 16.0F, 0.0F, false);

        neck = new ModelRenderer(this);
        neck.setPos(0.0F, 4.0F, -7.0F);
        setRotationAngle(neck, 0.3491F, 0.0F, 0.0F);
        neck.texOffs(31, 63).addBox(-3.0F, -12.0F, -3.0F, 6.0F, 12.0F, 5.0F, 0.0F, false);

        head = new ModelRenderer(this);
        head.setPos(0.0F, -12.0F, -1.0F);
        neck.addChild(head);
        head.texOffs(58, 16).addBox(-4.0F, -8.0F, -14.0F, 8.0F, 8.0F, 18.0F, 0.0F, false);
        head.texOffs(92, 23).addBox(-4.0F, -8.0F, 4.0F, 8.0F, 8.0F, 2.0F, 0.0F, false);

        headbonearmor = new ModelRenderer(this);
        headbonearmor.setPos(0.0F, 41.0F, 0.0F);
        head.addChild(headbonearmor);
        headbonearmor.texOffs(0, 101).addBox(-4.0F, -49.0F, -14.0F, 8.0F, 8.0F, 19.0F, 0.5F, false);

        headbonespike = new ModelRenderer(this);
        headbonespike.setPos(-0.5F, -49.5F, 0.5F);
        headbonearmor.addChild(headbonespike);
        setRotationAngle(headbonespike, -0.6109F, 0.0F, 0.0F);
        headbonespike.texOffs(0, 104).addBox(-1.5F, -10.5F, -1.5F, 4.0F, 12.0F, 3.0F, 0.0F, false);
    }

    @Override
    public void setupAnim(GribberEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
    {
        //previously the render function, render code was moved to a method below
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        leg1.render(matrixStack, buffer, packedLight, packedOverlay);
        leg2.render(matrixStack, buffer, packedLight, packedOverlay);
        body.render(matrixStack, buffer, packedLight, packedOverlay);
        neck.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
