package com.github.ilja615.worldupgrade.world.surfacebuilding;

import com.github.ilja615.worldupgrade.init.ModBlocks;
import com.mojang.datafixers.Dynamic;
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

public class VolcanicSurfaceBuilder extends SurfaceBuilder<SurfaceBuilderConfig>
{
    public static final PerlinNoiseGenerator perlinNoiseGenerator = new PerlinNoiseGenerator(new SharedSeedRandom(6010L), 3, 0);

    public static final BlockState GRAVEL_DARK = ModBlocks.GRAVEL_DARK.get().getDefaultState();
    //public static final BlockState GRAVEL_LIGHT = ModBlocks.GRAVEL_LIGHT.getDefaultState();
    public static final BlockState COARSE_DIRT = Blocks.COARSE_DIRT.getDefaultState();

    public static final SurfaceBuilderConfig GRAVEL_DARK_CONFIG = new SurfaceBuilderConfig(GRAVEL_DARK, GRAVEL_DARK, GRAVEL_DARK);
    //public static final SurfaceBuilderConfig GRAVEL_LIGHT_CONFIG = new SurfaceBuilderConfig(GRAVEL_LIGHT, GRAVEL_LIGHT, GRAVEL_LIGHT);
    public static final SurfaceBuilderConfig COARSE_DIRT_CONFIG = new SurfaceBuilderConfig(COARSE_DIRT, COARSE_DIRT, COARSE_DIRT);

    public VolcanicSurfaceBuilder(Function<Dynamic<?>, ? extends SurfaceBuilderConfig> deserializer)
    {
        super(deserializer);
    }

    @Override
    public void buildSurface(Random random, IChunk chunkIn, Biome biomeIn, int x, int z, int startHeight, double noise, BlockState defaultBlock, BlockState defaultFluid, int seaLevel, long seed, SurfaceBuilderConfig config)
    {
        if (perlinNoiseGenerator.noiseAt(x, z, false) > 0.45)
        {
            SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, COARSE_DIRT_CONFIG);
        } else
        {
            if (noise > 2.0D)
            {
                SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, GRAVEL_DARK_CONFIG);
            } else if (noise < -2.5D)
            {
                SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, SurfaceBuilder.GRASS_DIRT_GRAVEL_CONFIG);
            } else
            {
                int r = random.nextInt(2);
                if (r == 0)
                    SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, GRASS_DIRT_GRAVEL_CONFIG);
                if (r == 1)
                    SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, GRAVEL_DARK_CONFIG);
            }
        }
    }
}
