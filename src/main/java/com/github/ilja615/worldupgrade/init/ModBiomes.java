package com.github.ilja615.worldupgrade.init;

import com.github.ilja615.worldupgrade.WorldUpgrade;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeMaker;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.event.RegistryEvent;

public class ModBiomes
{
    public static RegistryKey<Biome> OVERGROWN_PEAKS = registryKey("overgrown_peaks");;

    public static void registerBiomes()
    {
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(OVERGROWN_PEAKS, 4));
    }

    public static RegistryKey<Biome> registryKey(String name)
    {
        return RegistryKey.getOrCreateKey(Registry.BIOME_KEY, new ResourceLocation(WorldUpgrade.MOD_ID, name));
    }

    public static void reserveBiomeIDs()
    {
        //Reserve biome IDs for the json version to replace
        Registry.register(WorldGenRegistries.BIOME, new ResourceLocation(WorldUpgrade.MOD_ID, "overgrown_peaks"), BiomeMaker.makeOceanBiome(false));
        Registry.register(WorldGenRegistries.BIOME, new ResourceLocation(WorldUpgrade.MOD_ID, "polder"), BiomeMaker.makeOceanBiome(false));
        Registry.register(WorldGenRegistries.BIOME, new ResourceLocation(WorldUpgrade.MOD_ID, "dragon_desert"), BiomeMaker.makeOceanBiome(false));
    }
}
