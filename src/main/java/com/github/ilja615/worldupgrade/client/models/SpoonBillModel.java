package com.github.ilja615.worldupgrade.client.models;

import com.github.ilja615.worldupgrade.entities.SpoonBillEntity;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class SpoonBillModel<T extends SpoonBillEntity> extends AgeableModel<SpoonBillEntity>
{
    private final ModelRenderer leg1;
    private final ModelRenderer leg2;
    private final ModelRenderer body;
    private final ModelRenderer wing1;
    private final ModelRenderer wing2;
    private final ModelRenderer wing3;
    private final ModelRenderer wing4;
    private final ModelRenderer neck;
    private final ModelRenderer idk1;
    private final ModelRenderer idk2;
    private final ModelRenderer feet1;
    private final ModelRenderer feet2;
    private final ModelRenderer head;
    private final ModelRenderer beak;


    public SpoonBillModel()
    {
        super(true, 4.0F, 4.0F, 2.0F, 2.0F, 24);
        texWidth = 64;
        texHeight = 64;

        leg1 = new ModelRenderer(this);
        leg1.setPos(-3.0F, 10.0F, 2.0F);
        leg1.texOffs(17, 27).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 6.0F, 1.0F, 0.0F, false);

        idk1 = new ModelRenderer(this);
        idk1.setPos(0.0F, 6.0F, 0.0F);
        leg1.addChild(idk1);
        idk1.texOffs(0, 21).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 8.0F, 1.0F, 0.0F, false);

        feet1 = new ModelRenderer(this);
        feet1.setPos(0.0F, 8.0F, 0.0F);
        idk1.addChild(feet1);
        feet1.texOffs(0, 50).addBox(-3.0F, 0.0F, -6.0F, 5.0F, 0.0F, 6.0F, 0.0F, false);

        leg2 = new ModelRenderer(this);
        leg2.setPos(3.0F, 10.0F, 2.0F);
        leg2.texOffs(6, 27).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 6.0F, 1.0F, 0.0F, false);

        idk2 = new ModelRenderer(this);
        idk2.setPos(0.0F, 6.0F, 0.0F);
        leg2.addChild(idk2);
        idk2.texOffs(25, 25).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 8.0F, 1.0F, 0.0F, false);

        feet2 = new ModelRenderer(this);
        feet2.setPos(0.0F, 8.0F, 0.0F);
        idk2.addChild(feet2);
        feet2.texOffs(0, 50).addBox(-2.0F, 0.0F, -6.0F, 5.0F, 0.0F, 6.0F, 0.0F, false);

        body = new ModelRenderer(this);
        body.setPos(0.0F, 6.5F, 2.0F);
        setRotationAngle(body, -0.3491F, 0.0F, 0.0F);
        body.texOffs(0, 0).addBox(-5.0F, -3.5F, -7.0F, 10.0F, 7.0F, 14.0F, 0.0F, false);

        wing1 = new ModelRenderer(this);
        wing1.setPos(-5.5F, 0.0F, 1.5F);
        body.addChild(wing1);
        wing1.texOffs(17, 26).addBox(-0.5F, -2.5F, -7.5F, 1.0F, 5.0F, 15.0F, 0.0F, false);

        wing2 = new ModelRenderer(this);
        wing2.setPos(5.5F, 0.0F, 1.5F);
        body.addChild(wing2);
        wing2.texOffs(0, 21).addBox(-0.5F, -2.5F, -7.5F, 1.0F, 5.0F, 15.0F, 0.0F, false);

        wing3 = new ModelRenderer(this);
        wing3.setPos(-5.0F, -2.0F, -2.5F);
        body.addChild(wing3);
        wing3.texOffs(24, 58).addBox(-15.0F, -0.5F, -2.5F, 15.0F, 1.0F, 5.0F, 0.0F, false);

        wing4 = new ModelRenderer(this);
        wing4.setPos(5.0F, -2.0F, -2.5F);
        body.addChild(wing4);
        wing4.texOffs(24, 48).addBox(0.0F, -0.5F, -2.5F, 15.0F, 1.0F, 5.0F, 0.0F, false);

        neck = new ModelRenderer(this);
        neck.setPos(0.0F, 23.0F, 1.0F);
        neck.texOffs(0, 0).addBox(-2.0F, -28.0F, -8.0F, 4.0F, 10.0F, 3.0F, 0.0F, false);

        head = new ModelRenderer(this);
        head.setPos(0.0F, 0.0F, -1.0F);
        neck.addChild(head);
        head.texOffs(34, 21).addBox(-2.0F, -32.0F, -9.0F, 4.0F, 4.0F, 5.0F, 0.0F, false);
        head.texOffs(0, 33).addBox(0.0F, -33.0F, -4.0F, 0.0F, 9.0F, 8.0F, 0.0F, false);

        beak = new ModelRenderer(this);
        beak.setPos(0.0F, -29.0F, -9.0F);
        head.addChild(beak);
        setRotationAngle(beak, 0.5236F, 0.0F, 0.0F);
        beak.texOffs(34, 0).addBox(-2.0F, -1.0F, -11.0F, 4.0F, 1.0F, 11.0F, 0.0F, false);
        beak.texOffs(34, 34).addBox(-3.0F, -1.0F, -16.0F, 6.0F, 1.0F, 5.0F, 0.0F, false);
    }

    @Override
    public void setupAnim(SpoonBillEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
    {
        if (entity.flying)
        {
            wing1.visible = false;
            wing2.visible = false;
            wing3.visible = true;
            wing4.visible = true;
            wing3.zRot = (float) (Math.sin(entity.wingSwing));
            wing4.zRot = -(float) (Math.sin(entity.wingSwing));
        } else
        {
            wing1.visible = true;
            wing2.visible = true;
            wing3.visible = false;
            wing4.visible = false;
        }
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha)
    {
        if (young) {
            matrixStack.translate(0, 1.0, 0);
            matrixStack.scale(0.3F, 0.5F, 0.3F);
        }
        leg1.render(matrixStack, buffer, packedLight, packedOverlay);
        leg2.render(matrixStack, buffer, packedLight, packedOverlay);
        body.render(matrixStack, buffer, packedLight, packedOverlay);
        neck.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    @Override
    protected Iterable<ModelRenderer> headParts() {
        return ImmutableList.of(this.neck, this.head, this.beak);
    }

    @Override
    protected Iterable<ModelRenderer> bodyParts() {
        return ImmutableList.of(this.leg1, this.leg2, this.body, this.wing1, this.wing2, this.wing3, this.wing4, this.idk1, this.idk2, this.feet1, this.feet2);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z)
    {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}