package com.github.ilja615.worldupgrade.client.renderer.entity;

import com.github.ilja615.worldupgrade.WorldUpgrade;
import com.github.ilja615.worldupgrade.client.models.DisguisagerModel;
import com.github.ilja615.worldupgrade.entity.Disguisager;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.IllagerModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.IllagerRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Vindicator;

import javax.annotation.Nullable;

public class DisguisagerRenderer extends MobRenderer<Disguisager, DisguisagerModel<Disguisager>>
{
    public static final ModelLayerLocation ENTITY_LAYER = new ModelLayerLocation(
            new ResourceLocation(WorldUpgrade.MOD_ID, "disguisager"), "main");

    private static final ResourceLocation ENTITY_TEXTURE = new ResourceLocation(WorldUpgrade.MOD_ID,
            "textures/entity/disguisager.png");

    public DisguisagerRenderer(EntityRendererProvider.Context context) {
        super(context,  new DisguisagerModel<>(context.bakeLayer(ENTITY_LAYER)), 0.5f);
        this.addLayer(new ItemInHandLayer<Disguisager, DisguisagerModel<Disguisager>>(this, context.getItemInHandRenderer())
        {
            public void render(PoseStack p_116352_, MultiBufferSource p_116353_, int p_116354_, Disguisager d, float p_116356_, float p_116357_, float p_116358_, float p_116359_, float p_116360_, float p_116361_) {
                if (!d.isDisguised()) {
                    super.render(p_116352_, p_116353_, p_116354_, d, p_116356_, p_116357_, p_116358_, p_116359_, p_116360_, p_116361_);
                }
            }
        });
    }

    @Override
    public void render(Disguisager disguisager, float p_115456_, float p_115457_, PoseStack p_115458_, MultiBufferSource p_115459_, int p_115460_)
    {
        this.model.isDisguisagerDisguised = disguisager.isDisguised();
        this.model.isDisguisagerBlowingHorn = disguisager.isBlowingHorn();
        super.render(disguisager, p_115456_, p_115457_, p_115458_, p_115459_, p_115460_);
    }

    @Nullable
    @Override
    public ResourceLocation getTextureLocation(Disguisager entity)
    {
        return ENTITY_TEXTURE;
    }
}
