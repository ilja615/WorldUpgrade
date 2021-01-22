package com.github.ilja615.worldupgrade.init;

import com.github.ilja615.worldupgrade.WorldUpgrade;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeMaker;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBiomes
{
    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, WorldUpgrade.MOD_ID);

    public static void registerBiomes()
    {
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(RegistryKey.getOrCreateKey(Registry.BIOME_KEY, new ResourceLocation("overgrown_peaks")), 4));
        BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(RegistryKey.getOrCreateKey(Registry.BIOME_KEY, new ResourceLocation("polder")), 8));
        BiomeManager.addBiome(BiomeManager.BiomeType.DESERT, new BiomeManager.BiomeEntry(RegistryKey.getOrCreateKey(Registry.BIOME_KEY, new ResourceLocation("dragon_desert")), 8));
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(RegistryKey.getOrCreateKey(Registry.BIOME_KEY, new ResourceLocation("dragon_forest")), 6));
        BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(RegistryKey.getOrCreateKey(Registry.BIOME_KEY, new ResourceLocation("cloud_forest")), 6));
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(RegistryKey.getOrCreateKey(Registry.BIOME_KEY, new ResourceLocation("scorched_forest")), 3));
        BiomeManager.addBiome(BiomeManager.BiomeType.DESERT, new BiomeManager.BiomeEntry(RegistryKey.getOrCreateKey(Registry.BIOME_KEY, new ResourceLocation("fumarole_fields")), 2));
        BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(RegistryKey.getOrCreateKey(Registry.BIOME_KEY, new ResourceLocation("cobbled_cliffs")), 4));
        //BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(RegistryKey.getOrCreateKey(Registry.BIOME_KEY, new ResourceLocation("heath")), 4));
        //BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(RegistryKey.getOrCreateKey(Registry.BIOME_KEY, new ResourceLocation("lily_pond")), 3));
        //BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(RegistryKey.getOrCreateKey(Registry.BIOME_KEY, new ResourceLocation("bog")), 5));
    }

    static
    {
        // Here we reserve biome IDs for the json version to replace
        BIOMES.register("overgrown_peaks", BiomeMaker::makeVoidBiome);
        BIOMES.register("polder", BiomeMaker::makeVoidBiome);
        BIOMES.register("dragon_forest", BiomeMaker::makeVoidBiome);
        BIOMES.register("dragon_desert", BiomeMaker::makeVoidBiome);
        BIOMES.register("cloud_forest", BiomeMaker::makeVoidBiome);
        BIOMES.register("scorched_forest", BiomeMaker::makeVoidBiome);
        BIOMES.register("fumarole_fields", BiomeMaker::makeVoidBiome);
        BIOMES.register("cobbled_cliffs", BiomeMaker::makeVoidBiome);
//        BIOMES.register("heath", BiomeMaker::makeVoidBiome);
//        BIOMES.register("lily_pond", BiomeMaker::makeVoidBiome);
//        BIOMES.register("bog", BiomeMaker::makeVoidBiome);
    }
}
