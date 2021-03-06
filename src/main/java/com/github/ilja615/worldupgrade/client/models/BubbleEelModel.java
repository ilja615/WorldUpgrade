package com.github.ilja615.worldupgrade.client.models;
// Made with Blockbench 3.5.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


import com.github.ilja615.worldupgrade.entities.BubbleEelEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class BubbleEelModel extends EntityModel<BubbleEelEntity>
{
    private final ModelRenderer body;
    private final ModelRenderer body2;

    public BubbleEelModel()
    {
        texWidth = 64;
        texHeight = 64;

        body = new ModelRenderer(this);
        body.setPos(-2.0F, 21.5F, 0.0F);
        body.texOffs(0, 10).addBox(-12.0F, -2.5F, -2.5F, 12.0F, 5.0F, 5.0F, 0.0F, false);
        body.texOffs(0, 41).addBox(-12.0F, -5.5F, 0.0F, 12.0F, 3.0F, 0.0F, 0.0F, false);

        ModelRenderer tail = new ModelRenderer(this);
        tail.setPos(-12.0F, 0.5F, 0.0F);
        body.addChild(tail);
        tail.texOffs(0, 20).addBox(-12.0F, -2.0F, -1.5F, 12.0F, 4.0F, 3.0F, 0.0F, false);
        tail.texOffs(0, 27).addBox(-18.0F, -4.0F, 0.0F, 12.0F, 8.0F, 0.0F, 0.0F, false);

        body2 = new ModelRenderer(this);
        body2.setPos(-2.0F, 21.5F, 0.0F);
        body2.texOffs(0, 0).addBox(0.0F, -2.5F, -2.5F, 12.0F, 5.0F, 5.0F, 0.0F, false);
        body2.texOffs(0, 38).addBox(0.0F, -5.5F, 0.0F, 12.0F, 3.0F, 0.0F, 0.0F, false);

        ModelRenderer body3 = new ModelRenderer(this);
        body3.setPos(12.0F, 0.5F, 0.0F);
        body2.addChild(body3);
        body3.texOffs(25, 25).addBox(0.0F, -2.0F, -2.5F, 8.0F, 4.0F, 5.0F, 0.0F, false);

        ModelRenderer fin1 = new ModelRenderer(this);
        fin1.setPos(2.0F, 1.0F, -2.0F);
        body3.addChild(fin1);
        fin1.texOffs(28, 6).addBox(-2.0F, 0.0F, -6.0F, 4.0F, 0.0F, 6.0F, 0.0F, false);

        ModelRenderer fin2 = new ModelRenderer(this);
        fin2.setPos(2.0F, 1.0F, 2.0F);
        body3.addChild(fin2);
        fin2.texOffs(28, 0).addBox(-2.0F, 0.0F, 0.0F, 4.0F, 0.0F, 6.0F, 0.0F, false);

        ModelRenderer jaw1 = new ModelRenderer(this);
        jaw1.setPos(8.0F, 1.0F, 0.0F);
        body3.addChild(jaw1);
        jaw1.texOffs(21, 34).addBox(0.0F, 0.0F, -1.5F, 4.0F, 1.0F, 3.0F, 0.0F, false);

        ModelRenderer jaw2 = new ModelRenderer(this);
        jaw2.setPos(8.0F, 1.0F, 0.0F);
        body3.addChild(jaw2);
        jaw2.texOffs(30, 20).addBox(0.0F, -2.0F, -1.5F, 4.0F, 2.0F, 3.0F, 0.0F, false);
    }

    @Override
    public void setupAnim(BubbleEelEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
    {

    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha)
    {
        body.render(matrixStack, buffer, packedLight, packedOverlay);
        body2.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngles(ModelRenderer modelRenderer, float x, float y, float z)
    {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
