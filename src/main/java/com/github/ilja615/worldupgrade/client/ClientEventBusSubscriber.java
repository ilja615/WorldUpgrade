package com.github.ilja615.worldupgrade.client;

import com.github.ilja615.worldupgrade.WorldUpgrade;
import com.github.ilja615.worldupgrade.world.biomes.CloudForestBiome;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = WorldUpgrade.MOD_ID, value = Dist.CLIENT)
public class ClientEventBusSubscriber
{
    @SubscribeEvent
    public static void onFogColor(EntityViewRenderEvent.FogColors event)
    {
        if (event.getInfo().getRenderViewEntity().world.getBiome(event.getInfo().getRenderViewEntity().getPosition()) instanceof CloudForestBiome)
        {
            event.setRed(0.92F);
            event.setGreen(0.95F);
            event.setBlue(0.95F);
        }
    }

    @SubscribeEvent
    public static void onFogDensity(EntityViewRenderEvent.FogDensity event)
    {
        if (event.getInfo().getRenderViewEntity().world.getBiome(event.getInfo().getRenderViewEntity().getPosition()) instanceof CloudForestBiome)
        {
            if (event.getDensity() < 0.05)
                event.setDensity(event.getDensity() + 0.005F);
            else if (event.getDensity() > 0.05)
                event.setDensity(event.getDensity() - 0.005F);
            event.setCanceled(true);
        }
    }
}
