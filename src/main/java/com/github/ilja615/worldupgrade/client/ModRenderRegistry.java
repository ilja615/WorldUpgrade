package com.github.ilja615.worldupgrade.client;

import com.github.ilja615.worldupgrade.client.renders.BubbleEelRender;
import com.github.ilja615.worldupgrade.client.renders.SpoonBillRender;
import com.github.ilja615.worldupgrade.init.ModEntities;
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
    }
}
