package com.github.ilja615.worldupgrade.world.surfacebuilding;

import com.github.ilja615.worldupgrade.init.ModBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.PerlinNoiseGenerator;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

import java.util.Collections;
import java.util.Random;
import java.util.function.Function;

public class PolderSurfaceBuilder extends SurfaceBuilder<SurfaceBuilderConfig>
{
    public static final SurfaceBuilderConfig CLAY_CONFIG = new SurfaceBuilderConfig(Blocks.CLAY.defaultBlockState(), Blocks.CLAY.defaultBlockState(), Blocks.CLAY.defaultBlockState());
    public static final SurfaceBuilderConfig GRASSY_CLAY_CONFIG = new SurfaceBuilderConfig(ModBlocks.GRASSY_CLAY.get().defaultBlockState(), Blocks.CLAY.defaultBlockState(), Blocks.CLAY.defaultBlockState());
    public static final PerlinNoiseGenerator perlinNoiseGenerator = new PerlinNoiseGenerator(new SharedSeedRandom(4208L), Collections.singletonList(4));

    public PolderSurfaceBuilder(Codec<SurfaceBuilderConfig> p_i232136_1_)
    {
        super(p_i232136_1_);
    }

    @Override
    public void apply(Random random, IChunk chunkIn, Biome biomeIn, int x, int z, int startHeight, double noise, BlockState defaultBlock, BlockState defaultFluid, int seaLevel, long seed, SurfaceBuilderConfig config)
    {
        if (noise > 0.2 && noise < 0.85)
        {
            SurfaceBuilder.DEFAULT.apply(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, CLAY_CONFIG);
            for (int y = chunkIn.getHeight(Heightmap.Type.WORLD_SURFACE, x, z); y > 61; y--)
            {
                chunkIn.setBlockState(new BlockPos(x, y, z), Blocks.AIR.defaultBlockState(), false);
            }
            chunkIn.setBlockState(new BlockPos(x, 62, z), Blocks.WATER.defaultBlockState(), false);
        } else
        {
            if (perlinNoiseGenerator.getValue(x/100.0f, z/100.0f, false) > 0.4)
            {
                SurfaceBuilder.DEFAULT.apply(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, CLAY_CONFIG);
            } else
            {
                SurfaceBuilder.DEFAULT.apply(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, GRASSY_CLAY_CONFIG);
            }
        }
    }
}
