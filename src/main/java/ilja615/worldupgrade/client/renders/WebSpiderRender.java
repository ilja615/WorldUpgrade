package ilja615.worldupgrade.client.renders;

import ilja615.worldupgrade.client.models.WebSpiderModel;
import ilja615.worldupgrade.entities.WebSpiderEntity;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class WebSpiderRender extends LivingRenderer<WebSpiderEntity, WebSpiderModel>
{

    public WebSpiderRender(EntityRendererManager manager) {
        super(manager, new WebSpiderModel(), 1.0f);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(WebSpiderEntity entity) {
        return new ResourceLocation("worldupgrade:textures/entity/web_spider.png");
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
