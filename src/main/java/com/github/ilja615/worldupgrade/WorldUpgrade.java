package com.github.ilja615.worldupgrade;

import com.github.ilja615.worldupgrade.init.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import terrablender.api.Regions;

import static com.github.ilja615.worldupgrade.WorldUpgrade.MOD_ID;

@Mod(MOD_ID)
public class WorldUpgrade
{
    public static final String MOD_ID = "worldupgrade";

    public WorldUpgrade()
    {
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::clientSetup);

        ModItems.ITEMS.register(modEventBus);
        ModBlocks.BLOCKS.register(modEventBus);
        ModFeatures.FEATURES.register(modEventBus);
        ModEntities.ENTITY_TYPES.register(modEventBus);
        ModSounds.SOUND_EVENTS.register(modEventBus);
        ModParticles.PARTICLES.register(modEventBus);
        ModCreativeTabs.TABS.register(modEventBus);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(WorldUpgrade::afterCommonSetup);
    }

    static void afterCommonSetup()
    {
        System.out.println("WorldUpgrade afterCommonSetup now run.");

        // Terrablender WorldUpgrade biomes region
        Regions.register(new ModRegion(new ResourceLocation(MOD_ID, "overworld"), 4));

        // Terrablender WorldUpgrade register our surface rules
        ModSurfaceRules.makeRules();
    }

    private void clientSetup(final FMLClientSetupEvent event)
    {
        event.enqueueWork(WorldUpgrade::afterClientSetup);
    }

    static void afterClientSetup()
    {
        System.out.println("WorldUpgrade afterClientSetup now run.");
    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class Events
    {

        @SubscribeEvent(priority = EventPriority.LOWEST)
        public static void registerParticles(RegisterParticleProvidersEvent event) {
            ModParticles.registerParticles(event); //It registers particles
        }

        @SubscribeEvent
        public static void entityAttributes(final EntityAttributeCreationEvent event) {
            ModEntities.createEntityAttributes(event); //It creates entity attributes
        }
    }
}

