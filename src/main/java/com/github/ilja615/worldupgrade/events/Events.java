package com.github.ilja615.worldupgrade.events;

import com.github.ilja615.worldupgrade.WorldUpgrade;
import com.github.ilja615.worldupgrade.capability.fog.PlayerFog;
import com.github.ilja615.worldupgrade.capability.fog.PlayerFogProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = WorldUpgrade.MOD_ID)
public class Events
{
    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if(event.getObject() instanceof Player) {
            if(!event.getObject().getCapability(PlayerFogProvider.PLAYER_FOG).isPresent()) {
                event.addCapability(new ResourceLocation(WorldUpgrade.MOD_ID, "properties"), new PlayerFogProvider());
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if(event.isWasDeath()) {
            event.getOriginal().getCapability(PlayerFogProvider.PLAYER_FOG).ifPresent(oldStore -> {
                event.getOriginal().getCapability(PlayerFogProvider.PLAYER_FOG).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                });
            });
        }
    }
}
