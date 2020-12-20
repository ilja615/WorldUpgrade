package com.github.ilja615.worldupgrade.client.renders;

import com.github.ilja615.worldupgrade.client.models.BeaverModel;
import com.github.ilja615.worldupgrade.entities.BeaverEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class BeaverRender extends MobRenderer<BeaverEntity, BeaverModel>
{

    public BeaverRender(EntityRendererManager manager)
    {
        super(manager, new BeaverModel(), 0.2f);
    }

    @Nullable
    @Override
    public ResourceLocation getEntityTexture(BeaverEntity entity)
    {
        return new ResourceLocation("worldupgrade:textures/entity/beaver.png");
    }
}
