package com.github.ilja615.worldupgrade.init;

import com.github.ilja615.worldupgrade.world.biomes.*;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBiomes
{
    @SubscribeEvent
    public static void registerBiomes(RegistryEvent.Register<Biome> event)
    {
        registerBiome(new BlisteringPlainsBiome(), "blistering_plains", 4, false, BiomeManager.BiomeType.DESERT, BiomeDictionary.Type.SPARSE);
        registerBiome(new OvergrownPeaksBiome(), "overgrown_peaks", 4, true, BiomeManager.BiomeType.WARM, BiomeDictionary.Type.JUNGLE, BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.LUSH);
        registerBiome(new DragonDesertBiome(), "dragon_dessert", 8, false, BiomeManager.BiomeType.DESERT, BiomeDictionary.Type.DRY, BiomeDictionary.Type.SANDY);
        registerBiome(new PolderBiome(), "polder", 8, true, BiomeManager.BiomeType.COOL, BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.WET);
        registerBiome(new CloudForestBiome(), "cloud_forrest", 6, true, BiomeManager.BiomeType.COOL, BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.CONIFEROUS, BiomeDictionary.Type.LUSH);
        registerBiome(new CharredForestBiome(), "charred_forrest", 6, false, BiomeManager.BiomeType.DESERT, BiomeDictionary.Type.SPARSE);
        registerBiome(new DragonTreeForestBiome(), "dragon_tree_forrest", 6, true, BiomeManager.BiomeType.DESERT, BiomeDictionary.Type.DRY, BiomeDictionary.Type.SAVANNA);

    }

    public static void registerBiome(Biome biome, String name, int weight, boolean isSpawnBiome, BiomeManager.BiomeType biomeManagerType, BiomeDictionary.Type... types)
    {
        biome.setRegistryName(name);
        ForgeRegistries.BIOMES.register(biome);
        BiomeDictionary.addTypes(biome, types);
        if (isSpawnBiome) BiomeManager.addSpawnBiome(biome);
        BiomeManager.addBiome(biomeManagerType, new BiomeManager.BiomeEntry(biome, weight));
    }
}
