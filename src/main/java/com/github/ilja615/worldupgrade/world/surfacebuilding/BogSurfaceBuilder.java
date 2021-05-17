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

public class BogSurfaceBuilder extends SurfaceBuilder<SurfaceBuilderConfig>
{
    public static final SurfaceBuilderConfig PEAT_CONFIG = new SurfaceBuilderConfig(ModBlocks.PEAT.get().defaultBlockState(), ModBlocks.PEAT.get().defaultBlockState(), ModBlocks.PEAT.get().defaultBlockState());
    public static final PerlinNoiseGenerator perlinNoiseGenerator = new PerlinNoiseGenerator(new SharedSeedRandom(2358L), Collections.singletonList(4));

    public BogSurfaceBuilder(Codec<SurfaceBuilderConfig> p_i232136_1_)
    {
        super(p_i232136_1_);
    }

    @Override
    public void apply(Random random, IChunk chunkIn, Biome biomeIn, int x, int z, int startHeight, double noise, BlockState defaultBlock, BlockState defaultFluid, int seaLevel, long seed, SurfaceBuilderConfig config)
    {
        if (noise > -1.5f && noise < 1.5f) {
            SurfaceBuilder.DEFAULT.apply(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, PEAT_CONFIG);
        } else {
            SurfaceBuilder.DEFAULT.apply(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, CONFIG_OCEAN_SAND);
        }
        if (perlinNoiseGenerator.getValue(x/100.0f, z/100.0f, false) > 0.3)
        {
            for (int y = 63; y > 61; y--)
            {
                chunkIn.setBlockState(new BlockPos(x, y, z), Blocks.AIR.defaultBlockState(), false);
            }
            chunkIn.setBlockState(new BlockPos(x, 62, z), Blocks.WATER.defaultBlockState(), false);
        }
    }
}
