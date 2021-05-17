package com.github.ilja615.worldupgrade.client;

import com.github.ilja615.worldupgrade.WorldUpgrade;
import com.github.ilja615.worldupgrade.init.ModBiomes;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = WorldUpgrade.MOD_ID, value = Dist.CLIENT)
public class ClientEventsForMod
{
    @SubscribeEvent
    public static void onFogColor(EntityViewRenderEvent.FogColors event)
    {
        Biome b = event.getInfo().getEntity().level.getBiome(event.getInfo().getEntity().blockPosition());
        if (event.getInfo().getEntity().level.registryAccess().registryOrThrow(Registry.BIOME_REGISTRY).getKey(b).toString().equals("worldupgrade:cloud_forest"))
        {
            event.setRed(0.92F);
            event.setGreen(0.95F);
            event.setBlue(0.95F);
        }
    }

    @SubscribeEvent
    public static void onFogDensity(EntityViewRenderEvent.FogDensity event)
    {
        Biome b = event.getInfo().getEntity().level.getBiome(event.getInfo().getEntity().blockPosition());
        if (event.getInfo().getEntity().level.registryAccess().registryOrThrow(Registry.BIOME_REGISTRY).getKey(b).toString().equals("worldupgrade:cloud_forest"))
        {
            event.setDensity(0.05f);
            event.setCanceled(true);
        }
    }
}
