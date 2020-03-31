package ilja615.worldupgrade.world.surfacebuilding;

import com.mojang.datafixers.Dynamic;
import ilja615.worldupgrade.init.ModBlocks;
import ilja615.worldupgrade.init.ModBlocksNew;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.PerlinNoiseGenerator;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

import java.util.Random;
import java.util.function.Function;

public class PolderSurfaceBuilder extends SurfaceBuilder<SurfaceBuilderConfig>
{
    public static final SurfaceBuilderConfig DIRT_AND_CLAY_CONFIG = new SurfaceBuilderConfig(Blocks.CLAY.getDefaultState(), DIRT, Blocks.CLAY.getDefaultState());
    public static final SurfaceBuilderConfig DIRT_AND_GRASSY_CLAY_CONFIG = new SurfaceBuilderConfig(ModBlocksNew.GRASSY_CLAY.get().getDefaultState(), DIRT, Blocks.CLAY.getDefaultState());
    public static final PerlinNoiseGenerator perlinNoiseGenerator = new PerlinNoiseGenerator(new SharedSeedRandom(4208L), 4, 0);

    public PolderSurfaceBuilder(Function<Dynamic<?>, ? extends SurfaceBuilderConfig> deserializer)
    {
        super(deserializer);
    }

    @Override
    public void buildSurface(Random random, IChunk chunkIn, Biome biomeIn, int x, int z, int startHeight, double noise, BlockState defaultBlock, BlockState defaultFluid, int seaLevel, long seed, SurfaceBuilderConfig config)
    {
        if (noise > 5.6 && noise < 6.4) {
            SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight-2, noise, defaultBlock, defaultFluid, seaLevel, seed, DIRT_AND_CLAY_CONFIG);
        } else {
            if (perlinNoiseGenerator.noiseAt(x, z, false) > 7) {
                SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, DIRT_AND_CLAY_CONFIG);
            } else {
                SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, DIRT_AND_GRASSY_CLAY_CONFIG);
            }
        }
    }
}
