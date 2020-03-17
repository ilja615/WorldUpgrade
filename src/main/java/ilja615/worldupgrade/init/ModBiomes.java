package ilja615.worldupgrade.init;

import ilja615.worldupgrade.world.biomes.*;
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
        registerBiome(new BiomeVolcanicPlains(), "volcanic_plains", 100, BiomeManager.BiomeType.DESERT, BiomeDictionary.Type.SPARSE);
        registerBiome(new BiomeOvergrownPeaks(), "overgrown_peaks", 100, BiomeManager.BiomeType.WARM, BiomeDictionary.Type.JUNGLE, BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.LUSH);
        registerBiome(new BiomeSpiderGlade(), "spider_glade", 10, BiomeManager.BiomeType.COOL, BiomeDictionary.Type.SPOOKY, BiomeDictionary.Type.DEAD);
        registerBiome(new BiomeDragonDessert(), "dragon_dessert", 100, BiomeManager.BiomeType.DESERT, BiomeDictionary.Type.DRY, BiomeDictionary.Type.SANDY);
        registerBiome(new BiomePolder(), "polder", 100, BiomeManager.BiomeType.COOL, BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.WET);

    }

    public static void registerBiome(Biome biome, String name, int weight, BiomeManager.BiomeType biomeManagerType, BiomeDictionary.Type... types)
    {
        biome.setRegistryName(name);
        ForgeRegistries.BIOMES.register(biome);
        BiomeDictionary.addTypes(biome, types);
        BiomeManager.addSpawnBiome(biome);
        BiomeManager.addBiome(biomeManagerType, new BiomeManager.BiomeEntry(biome, weight));

    }
}
