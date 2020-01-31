package ilja615.worldupgrade.world.surfacebuilding;

import com.mojang.datafixers.Dynamic;
import ilja615.worldupgrade.init.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.PerlinNoiseGenerator;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

import java.util.Random;
import java.util.function.Function;

public class DragonDessertSurfaceBuilder extends SurfaceBuilder<SurfaceBuilderConfig>
{
    public static final BlockState RED_PEBBLE = ModBlocks.RED_PEBBLE.getDefaultState();

    public static final SurfaceBuilderConfig RED_PEBBLE_CONFIG = new SurfaceBuilderConfig(RED_PEBBLE, RED_PEBBLE, RED_PEBBLE);
    public static final PerlinNoiseGenerator perlinNoiseGenerator = new PerlinNoiseGenerator(new Random(8544L), 4);
    private int r = 0;

    public DragonDessertSurfaceBuilder(Function<Dynamic<?>, ? extends SurfaceBuilderConfig> deserializer)
    {
        super(deserializer);
    }

    @Override
    public void buildSurface(Random random, IChunk chunkIn, Biome biomeIn, int x, int z, int startHeight, double noise, BlockState defaultBlock, BlockState defaultFluid, int seaLevel, long seed, SurfaceBuilderConfig config) {
        if (perlinNoiseGenerator.getValue(x, z) > -3) {
            SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, RED_PEBBLE_CONFIG);
        } else {
            SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, GRASS_DIRT_SAND_CONFIG);
        }
    }
}
