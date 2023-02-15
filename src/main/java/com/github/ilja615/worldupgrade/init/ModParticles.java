package com.github.ilja615.worldupgrade.init;

import com.github.ilja615.worldupgrade.WorldUpgrade;
import com.github.ilja615.worldupgrade.particles.LeafParticle;
import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModParticles
{
    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, WorldUpgrade.MOD_ID);

    public static final RegistryObject<SimpleParticleType> LEAF_PARTICLE = PARTICLES.register("leaf", () -> new SimpleParticleType(false));

    //ParticleFactoryRegisterEvent
    public static void registerParticles(RegisterParticleProvidersEvent event) {
        Minecraft.getInstance().particleEngine.register(ModParticles.LEAF_PARTICLE.get(), LeafParticle.Factory::new);
    }
}
