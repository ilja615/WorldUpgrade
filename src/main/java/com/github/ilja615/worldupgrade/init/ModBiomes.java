package com.github.ilja615.worldupgrade.init;

import com.github.ilja615.worldupgrade.WorldUpgrade;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeMaker;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ModBiomes
{
    // thx affehund
    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, WorldUpgrade.MOD_ID);

    static {
        createBiome("overgrown_peaks", BiomeMaker::makeVoidBiome);
        createBiome("polder", BiomeMaker::makeVoidBiome);
        createBiome("dragon_desert", BiomeMaker::makeVoidBiome);
        createBiome("dragon_forest", BiomeMaker::makeVoidBiome);
        createBiome("cloud_forest", BiomeMaker::makeVoidBiome);
        createBiome("scorched_forest", BiomeMaker::makeVoidBiome);
        createBiome("fumarole_fields", BiomeMaker::makeVoidBiome);
        createBiome("cobbled_cliffs", BiomeMaker::makeVoidBiome);
        createBiome("lily_pond", BiomeMaker::makeVoidBiome);
        createBiome("bog", BiomeMaker::makeVoidBiome);
    }

    public static RegistryObject<Biome> createBiome(String name, Supplier<Biome> biome) {
        return BIOMES.register(name, biome);
    }

    //register tje biome and add it to the biome manager
    public static RegistryKey<Biome> OVERGROWN_PEAKS_KEY = registryKey("overgrown_peaks");
    public static RegistryKey<Biome> POLDER_KEY = registryKey("polder");
    public static RegistryKey<Biome> DRAGON_DESERT_KEY = registryKey("dragon_desert");
    public static RegistryKey<Biome> DRAGON_FOREST_KEY = registryKey("dragon_forest");
    public static RegistryKey<Biome> CLOUD_FOREST_KEY = registryKey("cloud_forest");
    public static RegistryKey<Biome> SCORCHED_FOREST_KEY = registryKey("scorched_forest");
    public static RegistryKey<Biome> FUMAROLE_FIELDS_KEY = registryKey("fumarole_fields");
    public static RegistryKey<Biome> COBBLED_CLIFFS_KEY = registryKey("cobbled_cliffs");
    public static RegistryKey<Biome> LILY_POND = registryKey("lily_pond");
    public static RegistryKey<Biome> BOG = registryKey("bog");

    public static RegistryKey<Biome> registryKey(String name) {
        return RegistryKey.getOrCreateKey(Registry.BIOME_KEY, new ResourceLocation(WorldUpgrade.MOD_ID, name));
    }

    public static void registerBiomes() {
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(OVERGROWN_PEAKS_KEY, 4));
        BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(POLDER_KEY, 8));
        BiomeManager.addBiome(BiomeManager.BiomeType.DESERT, new BiomeManager.BiomeEntry(DRAGON_DESERT_KEY, 8));
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(DRAGON_FOREST_KEY, 6));
        BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(CLOUD_FOREST_KEY, 4));
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(SCORCHED_FOREST_KEY, 3));
        BiomeManager.addBiome(BiomeManager.BiomeType.DESERT, new BiomeManager.BiomeEntry(FUMAROLE_FIELDS_KEY, 2));
        BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(COBBLED_CLIFFS_KEY, 4));
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(LILY_POND, 2));
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(BOG, 6));
    }
}
