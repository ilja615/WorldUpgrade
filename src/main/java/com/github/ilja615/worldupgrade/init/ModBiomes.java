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
    public static RegistryKey<Biome> OVERGROWN_PEAKS = registryKey("overgrown_peaks");
    public static RegistryKey<Biome> POLDER = registryKey("polder");
    public static RegistryKey<Biome> DRAGON_FOREST = registryKey("dragon_forest");
    public static RegistryKey<Biome> DRAGON_DESERT = registryKey("dragon_desert");
    public static RegistryKey<Biome> CLOUD_FOREST = registryKey("cloud_forest");
    public static RegistryKey<Biome> SCORCHED_FOREST = registryKey("scorched_forest");
    public static RegistryKey<Biome> FUMAROLE_FIELDS = registryKey("fumarole_fields");
    public static RegistryKey<Biome> COBBLED_CLIFFS = registryKey("cobbled_cliffs");
    public static RegistryKey<Biome> HEATH = registryKey("heath");
    public static RegistryKey<Biome> LILY_POND = registryKey("lily_pond");
    public static RegistryKey<Biome> PEAT_BOG = registryKey("peat_bog");

    public static void registerBiomes()
    {
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(OVERGROWN_PEAKS, 4));
        BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(POLDER, 8));
        BiomeManager.addBiome(BiomeManager.BiomeType.DESERT, new BiomeManager.BiomeEntry(DRAGON_DESERT, 8));
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(DRAGON_FOREST, 6));
        BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(CLOUD_FOREST, 6));
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(SCORCHED_FOREST, 3));
        BiomeManager.addBiome(BiomeManager.BiomeType.DESERT, new BiomeManager.BiomeEntry(FUMAROLE_FIELDS, 2));
        BiomeManager.addBiome(BiomeManager.BiomeType.DESERT, new BiomeManager.BiomeEntry(COBBLED_CLIFFS, 4));
        BiomeManager.addBiome(BiomeManager.BiomeType.DESERT, new BiomeManager.BiomeEntry(HEATH, 6));
        BiomeManager.addBiome(BiomeManager.BiomeType.DESERT, new BiomeManager.BiomeEntry(LILY_POND, 4));
        BiomeManager.addBiome(BiomeManager.BiomeType.DESERT, new BiomeManager.BiomeEntry(PEAT_BOG, 5));
    }

    public static RegistryKey<Biome> registryKey(String name)
    {
        return RegistryKey.getOrCreateKey(Registry.BIOME_KEY, new ResourceLocation(WorldUpgrade.MOD_ID, name));
    }

    public static void reserveBiomeIDs()
    {
        // Here we reserve biome IDs for the json version to replace
        Registry.register(WorldGenRegistries.BIOME, new ResourceLocation(WorldUpgrade.MOD_ID, "overgrown_peaks"), BiomeMaker.makeOceanBiome(false));
        Registry.register(WorldGenRegistries.BIOME, new ResourceLocation(WorldUpgrade.MOD_ID, "polder"), BiomeMaker.makeOceanBiome(false));
        Registry.register(WorldGenRegistries.BIOME, new ResourceLocation(WorldUpgrade.MOD_ID, "dragon_forest"), BiomeMaker.makeOceanBiome(false));
        Registry.register(WorldGenRegistries.BIOME, new ResourceLocation(WorldUpgrade.MOD_ID, "dragon_desert"), BiomeMaker.makeOceanBiome(false));
        Registry.register(WorldGenRegistries.BIOME, new ResourceLocation(WorldUpgrade.MOD_ID, "cloud_forest"), BiomeMaker.makeOceanBiome(false));
        Registry.register(WorldGenRegistries.BIOME, new ResourceLocation(WorldUpgrade.MOD_ID, "scorched_forest"), BiomeMaker.makeOceanBiome(false));
        Registry.register(WorldGenRegistries.BIOME, new ResourceLocation(WorldUpgrade.MOD_ID, "fumarole_fields"), BiomeMaker.makeOceanBiome(false));
        Registry.register(WorldGenRegistries.BIOME, new ResourceLocation(WorldUpgrade.MOD_ID, "cobbled_cliffs"), BiomeMaker.makeOceanBiome(false));
        Registry.register(WorldGenRegistries.BIOME, new ResourceLocation(WorldUpgrade.MOD_ID, "heath"), BiomeMaker.makeOceanBiome(false));
        Registry.register(WorldGenRegistries.BIOME, new ResourceLocation(WorldUpgrade.MOD_ID, "lily_pond"), BiomeMaker.makeOceanBiome(false));
        Registry.register(WorldGenRegistries.BIOME, new ResourceLocation(WorldUpgrade.MOD_ID, "peat_bog"), BiomeMaker.makeOceanBiome(false));
    }
}
