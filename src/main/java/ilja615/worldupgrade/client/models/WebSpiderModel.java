package ilja615.worldupgrade.client.models;

import ilja615.worldupgrade.entities.WebSpiderEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WebSpiderModel extends EntityModel<WebSpiderEntity>
{
    private final RendererModel main;
    private final RendererModel legs;
    private final RendererModel silk1;
    private final RendererModel silk2;
    private final RendererModel silk3;
    private final RendererModel silk4;

    public WebSpiderModel() {
        textureWidth = 128;
        textureHeight = 128;

        main = new RendererModel(this);
        main.setRotationPoint(0.0F, 16.0F, 0.0F);
        main.cubeList.add(new ModelBox(main, 0, 0, -16.0F, -36.0F, -17.0F, 32, 32, 32, 0.0F, false));
        main.cubeList.add(new ModelBox(main, 0, 0, -24.0F, -16.0F, -4.0F, 8, 8, 8, 0.0F, false));

        legs = new RendererModel(this);
        legs.setRotationPoint(0.0F, 24.0F, 0.0F);
        legs.cubeList.add(new ModelBox(legs, 40, 64, -10.0F, -12.0F, -10.0F, 2, 12, 2, 0.0F, false));
        legs.cubeList.add(new ModelBox(legs, 32, 64, -1.0F, -12.0F, -10.0F, 2, 12, 2, 0.0F, false));
        legs.cubeList.add(new ModelBox(legs, 24, 16, 8.0F, -12.0F, -10.0F, 2, 12, 2, 0.0F, false));
        legs.cubeList.add(new ModelBox(legs, 16, 16, -10.0F, -12.0F, 8.0F, 2, 12, 2, 0.0F, false));
        legs.cubeList.add(new ModelBox(legs, 8, 16, -1.0F, -12.0F, 8.0F, 2, 12, 2, 0.0F, false));
        legs.cubeList.add(new ModelBox(legs, 0, 16, 8.0F, -12.0F, 8.0F, 2, 12, 2, 0.0F, false));

        silk1 = new RendererModel(this);
        silk1.setRotationPoint(-15.0F, -4.0F, 15.0F);
        setRotationAngle(silk1, 0.0F, -0.7854F, 0.0F);
        silk1.cubeList.add(new ModelBox(silk1, 24, 60, -1.0F, -20.0F, 1.0F, 0, 40, 4, 0.0F, false));

        silk2 = new RendererModel(this);
        silk2.setRotationPoint(-17.0F, -4.0F, -16.0F);
        setRotationAngle(silk2, 0.0F, 0.7854F, 0.0F);
        silk2.cubeList.add(new ModelBox(silk2, 16, 60, 1.0F, -20.0F, -4.0F, 0, 40, 4, 0.0F, false));

        silk3 = new RendererModel(this);
        silk3.setRotationPoint(17.0F, -4.0F, -16.0F);
        setRotationAngle(silk3, 0.0F, -0.7854F, 0.0F);
        silk3.cubeList.add(new ModelBox(silk3, 8, 60, -1.0F, -20.0F, -4.0F, 0, 40, 4, 0.0F, false));

        silk4 = new RendererModel(this);
        silk4.setRotationPoint(15.0F, -4.0F, 16.0F);
        setRotationAngle(silk4, 0.0F, 0.7854F, 0.0F);
        silk4.cubeList.add(new ModelBox(silk4, 0, 60, 1.0F, -20.0F, 0.0F, 0, 40, 4, 0.0F, false));
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
    public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }


    @Override
    public void render(WebSpiderEntity entity, float f1, float f2, float f3, float f4, float f5, float f6) {
        main.render(f5);
        legs.render(f5);
        silk1.render(f5);
        silk2.render(f5);
        silk3.render(f5);
        silk4.render(f5);
    }
}