package com.github.ilja615.worldupgrade.client.renders;

import com.github.ilja615.worldupgrade.client.models.BubbleEelModel;
import com.github.ilja615.worldupgrade.entities.BubbleEelEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class BubbleEelRender extends MobRenderer<BubbleEelEntity, BubbleEelModel>
{

    public BubbleEelRender(EntityRendererManager manager)
    {
        super(manager, new BubbleEelModel(), 0.5f);
    }

    @Nullable
    @Override
    public ResourceLocation getTextureLocation(BubbleEelEntity entity)
    {
        return new ResourceLocation("worldupgrade:textures/entity/bubble_eel.png");
    }
}
