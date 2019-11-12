package ilja615.worldupgrade.init;

import ilja615.worldupgrade.world.biomes.BiomeOvergrownPeaks;
import ilja615.worldupgrade.world.biomes.BiomeVolcanicPlains;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
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
        registerBiome(new BiomeVolcanicPlains(), "volcanic_plains", BiomeDictionary.Type.SPOOKY);
        registerBiome(new BiomeOvergrownPeaks(), "overgrown_peaks", BiomeDictionary.Type.JUNGLE, BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.LUSH);

    }

    public static void registerBiome(Biome biome, String name, BiomeDictionary.Type... types)
    {
        biome.setRegistryName(name);
        ForgeRegistries.BIOMES.register(biome);
        BiomeDictionary.addTypes(biome, types);
//        if (biome.canSpawnInBiome)
//        {
//            BiomeManager.addSpawnBiome(biome);
//        }

    }
}
