package ilja615.worldupgrade.client.renders;

import ilja615.worldupgrade.client.models.BubbleEelModel;
import ilja615.worldupgrade.client.models.SpoonBillModel;
import ilja615.worldupgrade.entities.BubbleEelEntity;
import ilja615.worldupgrade.entities.SpoonBillEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class SpoonBillRender extends MobRenderer<SpoonBillEntity, SpoonBillModel>
{

    public SpoonBillRender(EntityRendererManager manager) {
        super(manager, new SpoonBillModel(), 0.8f);
    }

    @Nullable
    @Override
    public ResourceLocation getEntityTexture(SpoonBillEntity entity) {
        return new ResourceLocation("worldupgrade:textures/entity/common_spoonbill.png");
    }
}
