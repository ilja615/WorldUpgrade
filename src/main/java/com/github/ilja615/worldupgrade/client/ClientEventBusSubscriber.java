package com.github.ilja615.worldupgrade.client;

import com.github.ilja615.worldupgrade.WorldUpgrade;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = WorldUpgrade.MOD_ID, value = Dist.CLIENT)

public class ClientEventBusSubscriber
{
    @SubscribeEvent
    public static void onFogColor(ViewportEvent.RenderFog event)
    {
        Level l = event.getCamera().getEntity().level;
        Biome b = l.getBiome(event.getCamera().getEntity().blockPosition()).get();
        if (l.registryAccess().registryOrThrow(Registries.BIOME).getKey(b).toString().equals("worldupgrade:cloud_forest"))
        {
            event.setNearPlaneDistance(event.getNearPlaneDistance() * 0.2f);
            event.setFarPlaneDistance(event.getFarPlaneDistance() * 0.2f);
        }
    }
}
