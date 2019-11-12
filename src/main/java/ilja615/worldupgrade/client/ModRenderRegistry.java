package ilja615.worldupgrade.client;

import ilja615.worldupgrade.client.renders.WebSpiderRender;
import ilja615.worldupgrade.entities.WebSpiderEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

@OnlyIn(Dist.CLIENT)
public class ModRenderRegistry
{
    public static void registerEntityRenderers()
    {
        RenderingRegistry.registerEntityRenderingHandler(WebSpiderEntity.class, new WebSpiderRender.RenderFactory());
    }
}
