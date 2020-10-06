package com.github.ilja615.worldupgrade.init;

import com.github.ilja615.worldupgrade.WorldUpgrade;
import com.github.ilja615.worldupgrade.world.biomes.*;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = WorldUpgrade.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBiomes
{
    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, WorldUpgrade.MOD_ID);

    public static final RegistryObject<Biome> BLISTERING_PLAINS = BIOMES.register("blistering_plains", BlisteringPlainsBiome::new);
    public static final RegistryObject<Biome> OVERGROWN_PEAKS = BIOMES.register("overgrown_peaks", OvergrownPeaksBiome::new);
    public static final RegistryObject<Biome> DRAGON_DESERT = BIOMES.register("dragon_dessert", DragonDesertBiome::new);
    public static final RegistryObject<Biome> POLDER = BIOMES.register("polder", PolderBiome::new);
    public static final RegistryObject<Biome> CLOUD_FOREST = BIOMES.register("cloud_forrest", CloudForestBiome::new);
    public static final RegistryObject<Biome> CHARRED_FOREST = BIOMES.register("charred_forrest", CharredForestBiome::new);
    public static final RegistryObject<Biome> DRAGON_TREE_FOREST = BIOMES.register("dragon_tree_forrest", DragonTreeForestBiome::new);

    @SubscribeEvent
    public static void registerBiomes(RegistryEvent.Register<Biome> event)
    {
        registerBiome(BLISTERING_PLAINS.get(), 4, false, BiomeManager.BiomeType.DESERT, BiomeDictionary.Type.SPARSE);
        registerBiome(OVERGROWN_PEAKS.get(), 4, true, BiomeManager.BiomeType.WARM, BiomeDictionary.Type.JUNGLE, BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.LUSH);
        registerBiome(DRAGON_DESERT.get(), 8, false, BiomeManager.BiomeType.DESERT, BiomeDictionary.Type.DRY, BiomeDictionary.Type.SANDY);
        registerBiome(POLDER.get(), 8, true, BiomeManager.BiomeType.COOL, BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.WET);
        registerBiome(CLOUD_FOREST.get(), 6, true, BiomeManager.BiomeType.COOL, BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.CONIFEROUS, BiomeDictionary.Type.LUSH);
        registerBiome(CHARRED_FOREST.get(), 6, false, BiomeManager.BiomeType.DESERT, BiomeDictionary.Type.SPARSE);
        registerBiome(DRAGON_TREE_FOREST.get(), 6, true, BiomeManager.BiomeType.DESERT, BiomeDictionary.Type.DRY, BiomeDictionary.Type.SAVANNA);
    }

    public static void registerBiome(Biome biome, int weight, boolean isSpawnBiome, BiomeManager.BiomeType biomeManagerType, BiomeDictionary.Type... types)
    {
        BiomeDictionary.addTypes(biome, types);
        if (isSpawnBiome) BiomeManager.addSpawnBiome(biome);
        BiomeManager.addBiome(biomeManagerType, new BiomeManager.BiomeEntry(biome, weight));
    }
}
