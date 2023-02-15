package com.github.ilja615.worldupgrade.events;

import com.github.ilja615.worldupgrade.WorldUpgrade;
import com.github.ilja615.worldupgrade.capability.fog.PlayerFogProvider;
import com.github.ilja615.worldupgrade.client.ClientFogData;
import com.github.ilja615.worldupgrade.networking.ModMessages;
import com.github.ilja615.worldupgrade.networking.packet.FogDataSyncS2CPacket;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = WorldUpgrade.MOD_ID, value = Dist.CLIENT)

public class ClientEvents
{
    @SubscribeEvent
    public static void onRenderFog(ViewportEvent.RenderFog event)
    {
        Entity en = event.getCamera().getEntity();
        Level l = en.level;
        Biome b = l.getBiome(event.getCamera().getEntity().blockPosition()).get();
        if (l.registryAccess().registryOrThrow(Registries.BIOME).getKey(b).toString().equals("worldupgrade:cloud_forest"))
        {
//            event.setNearPlaneDistance(event.getNearPlaneDistance() * 0.01f);
//            event.setFarPlaneDistance(event.getFarPlaneDistance() * 0.08f);
            event.setNearPlaneDistance(Math.min(event.getNearPlaneDistance(),event.getNearPlaneDistance() / (float)ClientFogData.getPlayerFog()));
            event.setFarPlaneDistance(Math.min(event.getFarPlaneDistance(), 8 * event.getFarPlaneDistance() / (float)ClientFogData.getPlayerFog()));
            if (en instanceof ServerPlayer player)
            {
                player.getCapability(PlayerFogProvider.PLAYER_FOG).ifPresent(fog -> {
                    fog.addFog(1);

                    ModMessages.sendToPlayer(new FogDataSyncS2CPacket(fog.getFog()), player);
                });
            }
            event.setCanceled(true);
        } else {
            if (en instanceof ServerPlayer player)
            {
                player.getCapability(PlayerFogProvider.PLAYER_FOG).ifPresent(fog -> {
                    fog.substractFog(1);

                    ModMessages.sendToPlayer(new FogDataSyncS2CPacket(fog.getFog()), player);
                });
            }
        }
    }

    @SubscribeEvent
    public static void onFogColor(ViewportEvent.ComputeFogColor event)
    {
        Level l = event.getCamera().getEntity().level;
        Biome b = l.getBiome(event.getCamera().getEntity().blockPosition()).get();
        if (l.registryAccess().registryOrThrow(Registries.BIOME).getKey(b).toString().equals("worldupgrade:cloud_forest"))
        {
            event.setRed(0.92F);
            event.setGreen(0.95F);
            event.setBlue(0.95F);
        }
    }
}
