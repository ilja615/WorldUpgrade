package com.github.ilja615.worldupgrade.client.renders;

import com.github.ilja615.worldupgrade.client.models.SpoonBillModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class SpoonBillRender extends MobRenderer<ilja615.worldupgrade.entities.SpoonBillEntity, SpoonBillModel>
{
    private static final ResourceLocation[] SPOONBILL_TEXTURES = new ResourceLocation[]
    {
        new ResourceLocation("worldupgrade:textures/entity/common_spoonbill.png"),
        new ResourceLocation("worldupgrade:textures/entity/royal_spoonbill.png"),
        new ResourceLocation("worldupgrade:textures/entity/yellow_billed_spoonbill.png"),
        new ResourceLocation("worldupgrade:textures/entity/roseate_spoonbill.png")
    };

    public SpoonBillRender(EntityRendererManager manager)
    {
        super(manager, new SpoonBillModel(), 0.8f);
    }

    @Nullable
    @Override
    public ResourceLocation getEntityTexture(ilja615.worldupgrade.entities.SpoonBillEntity entity)
    {
        return SPOONBILL_TEXTURES[entity.getVariant()];
    }
}
