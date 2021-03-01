package com.github.ilja615.worldupgrade.client;

import com.github.ilja615.worldupgrade.client.renders.*;
import com.github.ilja615.worldupgrade.init.ModEntities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

@OnlyIn(Dist.CLIENT)
public class ModRenderRegistry
{
    public static void registerEntityRenderers()
    {
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.BUBBLE_EEL.get(), BubbleEelRender::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.SPOONBILL.get(), SpoonBillRender::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.GRIBBER.get(), GribberRender::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.BEAVER.get(), BeaverRender::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.SPOONBILL_EGG.get(), (renderManager) -> new SpriteRenderer<>(renderManager, Minecraft.getInstance().getItemRenderer()));
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.FLIGHT_ARROW.get(), FlightArrowRenderer::new);
    }
}
