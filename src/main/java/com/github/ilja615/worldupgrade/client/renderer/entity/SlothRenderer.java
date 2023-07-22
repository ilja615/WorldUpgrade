package com.github.ilja615.worldupgrade.client.renderer.entity;

import com.github.ilja615.worldupgrade.WorldUpgrade;
import com.github.ilja615.worldupgrade.client.models.SlothModel;
import com.github.ilja615.worldupgrade.entity.Sloth;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nullable;

public class SlothRenderer extends MobRenderer<Sloth, SlothModel<Sloth>>
{
    public static final ModelLayerLocation ENTITY_LAYER = new ModelLayerLocation(
            new ResourceLocation(WorldUpgrade.MOD_ID, "sloth"), "main");

    private static final ResourceLocation ENTITY_TEXTURE = new ResourceLocation(WorldUpgrade.MOD_ID,
            "textures/entity/sloth.png");

    public SlothRenderer(EntityRendererProvider.Context context) {
        super(context,  new SlothModel<>(context.bakeLayer(ENTITY_LAYER)), 1.0f);
    }

    @Override
    public void render(Sloth sloth, float p_115456_, float p_115457_, PoseStack p_115458_, MultiBufferSource p_115459_, int p_115460_)
    {
        super.render(sloth, p_115456_, p_115457_, p_115458_, p_115459_, p_115460_);
    }

    @Nullable
    @Override
    public ResourceLocation getTextureLocation(Sloth entity)
    {
        return ENTITY_TEXTURE;
    }
}
