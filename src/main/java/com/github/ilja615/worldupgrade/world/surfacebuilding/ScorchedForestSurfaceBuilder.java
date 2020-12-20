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

public class ScorchedForestSurfaceBuilder extends SurfaceBuilder<SurfaceBuilderConfig>
{
    public static final PerlinNoiseGenerator perlinNoiseGenerator = new PerlinNoiseGenerator(new SharedSeedRandom(8544L), Collections.singletonList(3));

    public static final BlockState GRAVEL_DARK = ModBlocks.GRAVEL_DARK.get().getDefaultState();
    public static final SurfaceBuilderConfig GRAVEL_DARK_CONFIG = new SurfaceBuilderConfig(GRAVEL_DARK, GRAVEL_DARK, Blocks.SAND.getDefaultState());
    public static final SurfaceBuilderConfig CONFIG_WITH_ASH = new SurfaceBuilderConfig(ModBlocks.ASH_DIRT.get().getDefaultState(), Blocks.DIRT.getDefaultState(), Blocks.SAND.getDefaultState());
    public static final SurfaceBuilderConfig CONFIG_WITH_ASH2 = new SurfaceBuilderConfig(ModBlocks.ASH_BLOCK.get().getDefaultState(), Blocks.DIRT.getDefaultState(), Blocks.SAND.getDefaultState());

    public ScorchedForestSurfaceBuilder(Codec<SurfaceBuilderConfig> p_i232136_1_) {
        super(p_i232136_1_);
    }


    @Override
    public void buildSurface(Random random, IChunk chunkIn, Biome biomeIn, int x, int z, int startHeight, double noise, BlockState defaultBlock, BlockState defaultFluid, int seaLevel, long seed, SurfaceBuilderConfig config)
    {
        if (perlinNoiseGenerator.noiseAt(x, z, false) + (random.nextFloat()/8.0f) > 0.3)
            SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, GRAVEL_DARK_CONFIG);
        else
        {
            SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, random.nextBoolean() ? CONFIG_WITH_ASH : CONFIG_WITH_ASH2);
        }
    }
}