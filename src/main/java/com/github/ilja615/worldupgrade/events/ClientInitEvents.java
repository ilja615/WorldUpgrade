package com.github.ilja615.worldupgrade.events;

import com.github.ilja615.worldupgrade.WorldUpgrade;
import com.github.ilja615.worldupgrade.client.models.DisguisagerModel;
import com.github.ilja615.worldupgrade.client.renderer.entity.DisguisagerRenderer;
import com.github.ilja615.worldupgrade.init.ModBlocks;
import com.github.ilja615.worldupgrade.init.ModEntities;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = WorldUpgrade.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientInitEvents
{
    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event)
    {
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.DRYGRASS.get(), RenderType.cutout());
    }

    @SubscribeEvent
    public static void registerEntityRenders(EntityRenderersEvent.RegisterRenderers event)
    {
        event.registerEntityRenderer(ModEntities.DISGUISAGER.get(), DisguisagerRenderer::new);
    }

    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event)
    {
        event.registerLayerDefinition(DisguisagerRenderer.ENTITY_LAYER, DisguisagerModel::createBodyLayer);
    }
}
