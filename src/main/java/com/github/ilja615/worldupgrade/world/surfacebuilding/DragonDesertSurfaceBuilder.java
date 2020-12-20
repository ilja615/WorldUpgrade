package com.github.ilja615.worldupgrade.world.surfacebuilding;

import com.github.ilja615.worldupgrade.init.ModBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.PerlinNoiseGenerator;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

import java.util.Collections;
import java.util.Random;
import java.util.function.Function;

public class DragonDesertSurfaceBuilder extends SurfaceBuilder<SurfaceBuilderConfig>
{
    public static final PerlinNoiseGenerator perlinNoiseGenerator = new PerlinNoiseGenerator(new SharedSeedRandom(8544L), Collections.singletonList(4));

    public static final BlockState REGOLITH = ModBlocks.REGOLITH.get().getDefaultState();
    public static final BlockState COARSE_SAND = ModBlocks.COARSE_SAND.get().getDefaultState();
    public static final SurfaceBuilderConfig CONFIG1 = new SurfaceBuilderConfig(REGOLITH, REGOLITH, REGOLITH);
    public static final SurfaceBuilderConfig CONFIG2 = new SurfaceBuilderConfig(COARSE_SAND, COARSE_SAND, COARSE_SAND);

    private int r = 0;

    public DragonDesertSurfaceBuilder(Codec<SurfaceBuilderConfig> p_i232136_1_)
    {
        super(p_i232136_1_);
    }

    @Override
    public void buildSurface(Random random, IChunk chunkIn, Biome biomeIn, int x, int z, int startHeight, double noise, BlockState defaultBlock, BlockState defaultFluid, int seaLevel, long seed, SurfaceBuilderConfig config)
    {
        if (perlinNoiseGenerator.noiseAt(x, z, false)+(random.nextFloat()/5.0f) < -0.1)
        {
            SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, CONFIG1);
        }
        else
        {
            SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, CONFIG2);
        }
    }
}
