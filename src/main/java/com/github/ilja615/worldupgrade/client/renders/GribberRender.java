package com.github.ilja615.worldupgrade.client.renders;

import com.github.ilja615.worldupgrade.client.models.BubbleEelModel;
import com.github.ilja615.worldupgrade.client.models.GribberModel;
import com.github.ilja615.worldupgrade.entities.BubbleEelEntity;
import com.github.ilja615.worldupgrade.entities.GribberEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class GribberRender extends MobRenderer<GribberEntity, GribberModel>
{

    public GribberRender(EntityRendererManager manager)
    {
        super(manager, new GribberModel(), 0.5f);
    }

    @Nullable
    @Override
    public ResourceLocation getEntityTexture(GribberEntity entity)
    {
        return new ResourceLocation("worldupgrade:textures/entity/gribber.png");
    }
}
