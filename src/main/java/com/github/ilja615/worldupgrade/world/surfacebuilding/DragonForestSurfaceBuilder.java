package com.github.ilja615.worldupgrade.world.surfacebuilding;

import com.github.ilja615.worldupgrade.init.ModBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.PerlinNoiseGenerator;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

import java.util.Collections;
import java.util.Random;
import java.util.function.Function;

public class DragonForestSurfaceBuilder extends SurfaceBuilder<SurfaceBuilderConfig>
{
    public static final PerlinNoiseGenerator perlinNoiseGenerator = new PerlinNoiseGenerator(new SharedSeedRandom(2448L), Collections.singletonList(4));

    public static final BlockState REGOLITH = ModBlocks.REGOLITH.get().defaultBlockState();
    public static final BlockState COARSE_SAND = ModBlocks.COARSE_SAND.get().defaultBlockState();
    public static final SurfaceBuilderConfig CONFIG1 = new SurfaceBuilderConfig(REGOLITH, REGOLITH, REGOLITH);
    public static final SurfaceBuilderConfig CONFIG2 = new SurfaceBuilderConfig(COARSE_SAND, COARSE_SAND, COARSE_SAND);

    public DragonForestSurfaceBuilder(Codec<SurfaceBuilderConfig> p_i232136_1_)
    {
        super(p_i232136_1_);
    }

    @Override
    public void apply(Random random, IChunk chunkIn, Biome biomeIn, int x, int z, int startHeight, double noise, BlockState defaultBlock, BlockState defaultFluid, int seaLevel, long seed, SurfaceBuilderConfig config)
    {
        if (noise +(random.nextFloat()) < 2.5f && noise +(random.nextFloat()) > -2.5f)
        {
            if (perlinNoiseGenerator.getValue(x, z, false)+(random.nextFloat()/5.0f) < -0.1)
                SurfaceBuilder.DEFAULT.apply(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, CONFIG1);
            else
                SurfaceBuilder.DEFAULT.apply(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, CONFIG2);
        }
        else
            SurfaceBuilder.DEFAULT.apply(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, CONFIG_OCEAN_SAND);
    }
}