package ilja615.worldupgrade.client.renders;

import ilja615.worldupgrade.client.models.BabySpiderModel;
import ilja615.worldupgrade.client.models.WebSpiderModel;
import ilja615.worldupgrade.entities.BabySpiderEntity;
import ilja615.worldupgrade.entities.WebSpiderEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class BabySpiderRender extends MobRenderer<BabySpiderEntity, BabySpiderModel>
{

    public BabySpiderRender(EntityRendererManager manager) {
        super(manager, new BabySpiderModel(), 0.3f);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(BabySpiderEntity entity) {
        return new ResourceLocation("worldupgrade:textures/entity/baby_spider.png");
    }

   /*public static class RenderFactory implements IRenderFactory<WebSpiderEntity>
    {
        @Override
        public EntityRenderer<? super WebSpiderEntity> createRenderFor(EntityRendererManager manager)
        {
            return new WebSpiderRender(manager);
        }
    }*/
}
