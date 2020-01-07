package ilja615.worldupgrade.client.models;

import ilja615.worldupgrade.entities.BabySpiderEntity;
import ilja615.worldupgrade.entities.WebSpiderEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BabySpiderModel extends EntityModel<BabySpiderEntity>
{
    private final RendererModel head;
    private final RendererModel body;
    private final RendererModel body2;
    private final RendererModel leg_1;
    private final RendererModel leg_2;
    private final RendererModel leg_3;
    private final RendererModel leg_4;
    private final RendererModel leg_5;
    private final RendererModel leg_6;
    private final RendererModel leg_7;
    private final RendererModel leg_8;



    @Override
    public void render(BabySpiderEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        head.render(scale);
        body.render(scale);
        body2.render(scale);
        leg_1.render(scale);
        leg_2.render(scale);
        leg_3.render(scale);
        leg_4.render(scale);
        leg_5.render(scale);
        leg_6.render(scale);
        leg_7.render(scale);
        leg_8.render(scale);
    }

    public BabySpiderModel() {
        textureWidth = 64;
        textureHeight = 64;

        head = new RendererModel(this);
        head.setRotationPoint(0.0F, 18.0F, -4.0F);
        head.cubeList.add(new ModelBox(head, 0, 12, -2.5F, -1.0F, -4.0F, 5, 5, 5, 0.0F, false));

        body = new RendererModel(this);
        body.setRotationPoint(0.0F, 19.0F, -1.0F);
        body.cubeList.add(new ModelBox(body, 13, 22, -1.0F, 0.0F, -2.0F, 2, 2, 3, 0.0F, false));

        body2 = new RendererModel(this);
        body2.setRotationPoint(0.0F, 19.0F, 6.0F);
        body2.cubeList.add(new ModelBox(body2, 0, 0, -2.5F, -2.0F, -6.0F, 5, 5, 7, 0.0F, false));

        leg_1 = new RendererModel(this);
        leg_1.setRotationPoint(1.0F, 20.0F, -2.25F);
        setRotationAngle(leg_1, 0.0F, 0.3491F, 0.5236F);
        leg_1.cubeList.add(new ModelBox(leg_1, 0, 22, 0.0F, -0.5F, -0.5F, 7, 1, 1, 0.0F, false));

        leg_2 = new RendererModel(this);
        leg_2.setRotationPoint(-1.0F, 20.0F, -2.25F);
        setRotationAngle(leg_2, 0.0F, -0.3491F, -0.5236F);
        leg_2.cubeList.add(new ModelBox(leg_2, 20, 17, -7.0F, -0.5F, -0.5F, 7, 1, 1, 0.0F, false));

        leg_3 = new RendererModel(this);
        leg_3.setRotationPoint(1.0F, 20.0F, -1.75F);
        setRotationAngle(leg_3, 0.0F, 0.0873F, 0.5236F);
        leg_3.cubeList.add(new ModelBox(leg_3, 20, 20, 0.0F, -0.5F, -0.5F, 7, 1, 1, 0.0F, false));

        leg_4 = new RendererModel(this);
        leg_4.setRotationPoint(-1.0F, 20.0F, -1.75F);
        setRotationAngle(leg_4, 0.0F, -0.0873F, -0.5236F);
        leg_4.cubeList.add(new ModelBox(leg_4, 17, 4, -7.0F, -0.5F, -0.5F, 7, 1, 1, 0.0F, false));

        leg_5 = new RendererModel(this);
        leg_5.setRotationPoint(1.0F, 20.0F, -1.25F);
        setRotationAngle(leg_5, 0.0F, -0.1745F, 0.5236F);
        leg_5.cubeList.add(new ModelBox(leg_5, 17, 2, 0.0F, -0.5F, -0.5F, 7, 1, 1, 0.0F, false));

        leg_6 = new RendererModel(this);
        leg_6.setRotationPoint(-1.0F, 20.0F, -0.25F);
        setRotationAngle(leg_6, 0.0F, 0.1745F, -0.5236F);
        leg_6.cubeList.add(new ModelBox(leg_6, 17, 0, -7.0F, -0.5F, -1.5F, 7, 1, 1, 0.0F, false));

        leg_7 = new RendererModel(this);
        leg_7.setRotationPoint(1.0F, 20.0F, -0.75F);
        setRotationAngle(leg_7, 0.0F, -0.4363F, 0.5236F);
        leg_7.cubeList.add(new ModelBox(leg_7, 15, 12, 0.0F, -0.5F, -0.5F, 7, 1, 1, 0.0F, false));

        leg_8 = new RendererModel(this);
        leg_8.setRotationPoint(-1.0F, 20.0F, -0.75F);
        setRotationAngle(leg_8, 0.0F, 0.4363F, -0.5236F);
        leg_8.cubeList.add(new ModelBox(leg_8, 15, 15, -7.0F, -0.5F, -0.5F, 7, 1, 1, 0.0F, false));
    }

    public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}