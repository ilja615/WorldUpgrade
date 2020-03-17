package ilja615.worldupgrade.client.models;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import ilja615.worldupgrade.WorldUpgrade;
import ilja615.worldupgrade.entities.WebSpiderEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WebSpiderModel extends EntityModel<WebSpiderEntity>
{
    private final ModelRenderer main;
    private final ModelRenderer legs;
    private final ModelRenderer silk1;
    private final ModelRenderer silk2;
    private final ModelRenderer silk3;
    private final ModelRenderer silk4;

    public WebSpiderModel() {
        textureWidth = 128;
        textureHeight = 128;

        main = new ModelRenderer(this);
        main.setRotationPoint(0.0F, 16.0F, 0.0F);
//        main.cubeList.add(new ModelBox(main, 40, 64, -11.0F, -12.0F, -11.0F, 2, 12, 2, 0.0F, false));
//        main.cubeList.add(new ModelBox(main, 0, 0, -24.0F, -16.0F, -4.0F, 8, 8, 8, 0.0F, false));
        main.addBox(40, 64, -11.0F, -12.0F, -11.0F, 2, 12, 2, 0.0F);
        main.addBox(0, 0, -24.0F, -16.0F, -4.0F, 8, 8, 8, 0.0F);

        legs = new ModelRenderer(this);
        legs.setRotationPoint(0.0F, 24.0F, 0.0F);
//        legs.cubeList.add(new ModelBox(legs, 40, 64, -11.0F, -12.0F, -11.0F, 2, 12, 2, 0.0F, false));
//        legs.cubeList.add(new ModelBox(legs, 32, 64, -4.0F, -12.0F, -11.0F, 2, 12, 2, 0.0F, false));
//        legs.cubeList.add(new ModelBox(legs, 24, 16, 2.0F, -12.0F, -11.0F, 2, 12, 2, 0.0F, false));
//        legs.cubeList.add(new ModelBox(legs, 16, 16, 9.0F, -12.0F, -11.0F, 2, 12, 2, 0.0F, false));
//        legs.cubeList.add(new ModelBox(legs, 8, 16, -11.0F, -12.0F, 9.0F, 2, 12, 2, 0.0F, false));
//        legs.cubeList.add(new ModelBox(legs, 0, 16, -4.0F, -12.0F, 9.0F, 2, 12, 2, 0.0F, false));
//        legs.cubeList.add(new ModelBox(legs, 0, 16, 2.0F, -12.0F, 9.0F, 2, 12, 2, 0.0F, false));
//        legs.cubeList.add(new ModelBox(legs, 0, 16, 9.0F, -12.0F, 9.0F, 2, 12, 2, 0.0F, false));
        legs.addBox(40, 64, -11.0F, -12.0F, -11.0F, 2, 12, 2, 0.0F);
        legs.addBox(32, 64, -4.0F, -12.0F, -11.0F, 2, 12, 2, 0.0F);
        legs.addBox(24, 16, 2.0F, -12.0F, -11.0F, 2, 12, 2, 0.0F);
        legs.addBox(16, 16, 9.0F, -12.0F, -11.0F, 2, 12, 2, 0.0F);
        legs.addBox(8, 16, -11.0F, -12.0F, 9.0F, 2, 12, 2, 0.0F);
        legs.addBox(0, 16, -4.0F, -12.0F, 9.0F, 2, 12, 2, 0.0F);
        legs.addBox(0, 16, 2.0F, -12.0F, 9.0F, 2, 12, 2, 0.0F);
        legs.addBox(0, 16, 9.0F, -12.0F, 9.0F, 2, 12, 2, 0.0F);

        silk1 = new ModelRenderer(this);
        silk1.setRotationPoint(-15.0F, -4.0F, 15.0F);
        setRotationAngle(silk1, 0.0F, -0.7854F, 0.0F);
        //silk1.cubeList.add(new ModelBox(silk1, 24, 60, -1.0F, -20.0F, 1.0F, 0, 40, 4, 0.0F, false));
        silk1.addBox(24, 60, -1.0F, -20.0F, 1.0F, 0, 40, 4, 0.0F);

        silk2 = new ModelRenderer(this);
        silk2.setRotationPoint(-17.0F, -4.0F, -16.0F);
        setRotationAngle(silk2, 0.0F, 0.7854F, 0.0F);
        //silk2.cubeList.add(new ModelBox(silk2, 16, 60, 1.0F, -20.0F, -4.0F, 0, 40, 4, 0.0F, false));
        silk1.addBox(16, 60, 1.0F, -20.0F, -4.0F, 0, 40, 4, 0.0F);

        silk3 = new ModelRenderer(this);
        silk3.setRotationPoint(17.0F, -4.0F, -16.0F);
        setRotationAngle(silk3, 0.0F, -0.7854F, 0.0F);
        //silk3.cubeList.add(new ModelBox(silk3, 8, 60, -1.0F, -20.0F, -4.0F, 0, 40, 4, 0.0F, false));
        silk1.addBox(8, 60, -1.0F, -20.0F, -4.0F, 0, 40, 4, 0.0F);

        silk4 = new ModelRenderer(this);
        silk4.setRotationPoint(15.0F, -4.0F, 16.0F);
        setRotationAngle(silk4, 0.0F, 0.7854F, 0.0F);
        //silk4.cubeList.add(new ModelBox(silk4, 0, 60, 1.0F, -20.0F, 0.0F, 0, 40, 4, 0.0F, false));
        silk1.addBox(0, 60, 1.0F, -20.0F, 0.0F, 0, 40, 4, 0.0F);

    }

    @Override
    public void setRotationAngles(WebSpiderEntity webSpiderEntity, float v, float v1, float v2, float v3, float v4) {
        setRotationAngle(silk1, 0.0F, -0.7854F, 0.0F);
        setRotationAngle(silk2, 0.0F, 0.7854F, 0.0F);
        setRotationAngle(silk3, 0.0F, -0.7854F, 0.0F);
        setRotationAngle(silk4, 0.0F, 0.7854F, 0.0F);
    }

    //    @Override
//    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
//        main.render(f5);
//        legs.render(f5);
//        silk1.render(f5);
//        silk2.render(f5);
//        silk3.render(f5);
//        silk4.render(f5);
//    }
//
//
    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder iVertexBuilder, int i, int i1, float v, float v1, float v2, float v3) {
        main.render(matrixStack, iVertexBuilder, i, i1, v, v1, v2, v3);
        legs.render(matrixStack, iVertexBuilder, i, i1, v, v1, v2, v3);
        silk1.render(matrixStack, iVertexBuilder, i, i1, v, v1, v2, v3);
        silk2.render(matrixStack, iVertexBuilder, i, i1, v, v1, v2, v3);
        silk3.render(matrixStack, iVertexBuilder, i, i1, v, v1, v2, v3);
        silk4.render(matrixStack, iVertexBuilder, i, i1, v, v1, v2, v3);
    }
}