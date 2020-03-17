package ilja615.worldupgrade.client.models;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import ilja615.worldupgrade.entities.BabySpiderEntity;
import ilja615.worldupgrade.entities.WebSpiderEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BabySpiderModel extends EntityModel<BabySpiderEntity>
{
    private final ModelRenderer head;
    private final ModelRenderer body;
    private final ModelRenderer body2;
    private final ModelRenderer leg_1;
    private final ModelRenderer leg_2;
    private final ModelRenderer leg_3;
    private final ModelRenderer leg_4;
    private final ModelRenderer leg_5;
    private final ModelRenderer leg_6;
    private final ModelRenderer leg_7;
    private final ModelRenderer leg_8;



    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder iVertexBuilder, int i, int i1, float v, float v1, float v2, float v3) {
        head.render(matrixStack, iVertexBuilder, i, i1, v, v1, v2, v3);
        body.render(matrixStack, iVertexBuilder, i, i1, v, v1, v2, v3);
        body2.render(matrixStack, iVertexBuilder, i, i1, v, v1, v2, v3);
        leg_1.render(matrixStack, iVertexBuilder, i, i1, v, v1, v2, v3);
        leg_2.render(matrixStack, iVertexBuilder, i, i1, v, v1, v2, v3);
        leg_3.render(matrixStack, iVertexBuilder, i, i1, v, v1, v2, v3);
        leg_4.render(matrixStack, iVertexBuilder, i, i1, v, v1, v2, v3);
        leg_5.render(matrixStack, iVertexBuilder, i, i1, v, v1, v2, v3);
        leg_6.render(matrixStack, iVertexBuilder, i, i1, v, v1, v2, v3);
        leg_7.render(matrixStack, iVertexBuilder, i, i1, v, v1, v2, v3);
        leg_8.render(matrixStack, iVertexBuilder, i, i1, v, v1, v2, v3);
    }

    public BabySpiderModel() {
        textureWidth = 64;
        textureHeight = 64;

        head = new ModelRenderer(this);
        head.setRotationPoint(0.0F, 18.0F, -4.0F);
        //head.cubeList.add(new ModelBox(head, 0, 12, -2.5F, -1.0F, -4.0F, 5, 5, 5, 0.0F, false));
        head.addBox(0, 12, -2.5F, -1.0F, -4.0F, 5, 5, 5, 0.0F);

        body = new ModelRenderer(this);
        body.setRotationPoint(0.0F, 19.0F, -1.0F);
        //body.cubeList.add(new ModelBox(body, 13, 22, -1.0F, 0.0F, -2.0F, 2, 2, 3, 0.0F, false));
        body.addBox(13, 22, -1.0F, 0.0F, -2.0F, 2, 2, 3, 0.0F);

        body2 = new ModelRenderer(this);
        body2.setRotationPoint(0.0F, 19.0F, 6.0F);
        //body2.cubeList.add(new ModelBox(body2, 0, 0, -2.5F, -2.0F, -6.0F, 5, 5, 7, 0.0F, false));
        body2.addBox(0, 0, -2.5F, -2.0F, -6.0F, 5, 5, 7, 0.0F);

        leg_1 = new ModelRenderer(this);
        leg_1.setRotationPoint(1.0F, 20.0F, -2.25F);
        setRotationAngle(leg_1, 0.0F, 0.3491F, 0.5236F);
        //leg_1.cubeList.add(new ModelBox(leg_1, 0, 22, 0.0F, -0.5F, -0.5F, 7, 1, 1, 0.0F, false));
        leg_1.addBox(0, 22, 0.0F, -0.5F, -0.5F, 7, 1, 1, 0.0F);

        leg_2 = new ModelRenderer(this);
        leg_2.setRotationPoint(-1.0F, 20.0F, -2.25F);
        setRotationAngle(leg_2, 0.0F, -0.3491F, -0.5236F);
        //leg_2.cubeList.add(new ModelBox(leg_2, 20, 17, -7.0F, -0.5F, -0.5F, 7, 1, 1, 0.0F, false));
        leg_2.addBox(20, 17, -7.0F, -0.5F, -0.5F, 7, 1, 1, 0.0F);

        leg_3 = new ModelRenderer(this);
        leg_3.setRotationPoint(1.0F, 20.0F, -1.75F);
        setRotationAngle(leg_3, 0.0F, 0.0873F, 0.5236F);
        //leg_3.cubeList.add(new ModelBox(leg_3, 20, 20, 0.0F, -0.5F, -0.5F, 7, 1, 1, 0.0F, false));
        leg_3.addBox(20, 20, 0.0F, -0.5F, -0.5F, 7, 1, 1, 0.0F);

        leg_4 = new ModelRenderer(this);
        leg_4.setRotationPoint(-1.0F, 20.0F, -1.75F);
        setRotationAngle(leg_4, 0.0F, -0.0873F, -0.5236F);
        //leg_4.cubeList.add(new ModelBox(leg_4, 17, 4, -7.0F, -0.5F, -0.5F, 7, 1, 1, 0.0F, false));
        leg_4.addBox(17, 4, -7.0F, -0.5F, -0.5F, 7, 1, 1, 0.0F);

        leg_5 = new ModelRenderer(this);
        leg_5.setRotationPoint(1.0F, 20.0F, -1.25F);
        setRotationAngle(leg_5, 0.0F, -0.1745F, 0.5236F);
        //leg_5.cubeList.add(new ModelBox(leg_5, 17, 2, 0.0F, -0.5F, -0.5F, 7, 1, 1, 0.0F, false));
        leg_5.addBox(17, 2, 0.0F, -0.5F, -0.5F, 7, 1, 1, 0.0F);

        leg_6 = new ModelRenderer(this);
        leg_6.setRotationPoint(-1.0F, 20.0F, -0.25F);
        setRotationAngle(leg_6, 0.0F, 0.1745F, -0.5236F);
        //leg_6.cubeList.add(new ModelBox(leg_6, 17, 0, -7.0F, -0.5F, -1.5F, 7, 1, 1, 0.0F, false));
        leg_6.addBox(17, 0, -7.0F, -0.5F, -1.5F, 7, 1, 1, 0.0F);

        leg_7 = new ModelRenderer(this);
        leg_7.setRotationPoint(1.0F, 20.0F, -0.75F);
        setRotationAngle(leg_7, 0.0F, -0.4363F, 0.5236F);
        //leg_7.cubeList.add(new ModelBox(leg_7, 15, 12, 0.0F, -0.5F, -0.5F, 7, 1, 1, 0.0F, false));
        leg_7.addBox(15, 12, 0.0F, -0.5F, -0.5F, 7, 1, 1, 0.0F);

        leg_8 = new ModelRenderer(this);
        leg_8.setRotationPoint(-1.0F, 20.0F, -0.75F);
        setRotationAngle(leg_8, 0.0F, 0.4363F, -0.5236F);
        //leg_8.cubeList.add(new ModelBox(leg_8, 15, 15, -7.0F, -0.5F, -0.5F, 7, 1, 1, 0.0F, false));
        leg_8.addBox(15, 15, -7.0F, -0.5F, -0.5F, 7, 1, 1, 0.0F);
    }

    @Override
    public void setRotationAngles(BabySpiderEntity babySpiderEntity, float v, float v1, float v2, float v3, float v4) {
        setRotationAngle(leg_1, 0.0F, 0.3491F, 0.5236F);
        setRotationAngle(leg_2, 0.0F, -0.3491F, -0.5236F);
        setRotationAngle(leg_3, 0.0F, 0.0873F, 0.5236F);
        setRotationAngle(leg_4, 0.0F, -0.0873F, -0.5236F);
        setRotationAngle(leg_5, 0.0F, -0.1745F, 0.5236F);
        setRotationAngle(leg_6, 0.0F, 0.1745F, -0.5236F);
        setRotationAngle(leg_7, 0.0F, -0.4363F, 0.5236F);
        setRotationAngle(leg_8, 0.0F, 0.4363F, -0.5236F);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}