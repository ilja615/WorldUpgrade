package com.github.ilja615.worldupgrade.world.surfacebuilding;

import com.github.ilja615.worldupgrade.init.ModBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.PerlinNoiseGenerator;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import org.lwjgl.system.CallbackI;

import java.util.Collections;
import java.util.Random;

public class CobbledCliffsSurfaceBuilder extends SurfaceBuilder<SurfaceBuilderConfig>
{
    private static final BlockState DIRT = Blocks.DIRT.getDefaultState();
    private static final BlockState STONE = Blocks.STONE.getDefaultState();
    private static final BlockState BOULDER = ModBlocks.BOULDER.get().getDefaultState();
    private static final BlockState DENSE_BOULDER = ModBlocks.DENSE_BOULDER.get().getDefaultState();
    private static final BlockState GRASS_BLOCK = Blocks.GRASS_BLOCK.getDefaultState();

    private static BlockState[] layersBlocksArray = new BlockState[]{STONE, STONE, DENSE_BOULDER, BOULDER, BOULDER, DENSE_BOULDER, DENSE_BOULDER, DIRT, DIRT, STONE, DIRT, STONE, BOULDER, BOULDER, DIRT, BOULDER};
    public CobbledCliffsSurfaceBuilder(Codec<SurfaceBuilderConfig> p_i232136_1_)
    {
        super(p_i232136_1_);
    }

    @Override
    public void buildSurface(Random random, IChunk chunkIn, Biome biomeIn, int x, int z, int startHeight, double noise, BlockState defaultBlock, BlockState defaultFluid, int seaLevel, long seed, SurfaceBuilderConfig config)
    {
        if (noise > 0.0f) {
            BlockPos.Mutable localMutable = new BlockPos.Mutable().setPos(x & 15, startHeight, z & 15);
            int cliffBaseTallNess = 20; // Later i can make that even the cliffBaseTallNess can vary !!!! :-)))
            int cliffTallNess = noise > 0.2f ? (noise > 0.4f ? ( noise > 0.7f ? cliffBaseTallNess : cliffBaseTallNess - (int)((0.7f-noise)*10.0f) ) : (int)(noise*10.0f)) : 1;
            //Add extra blocks to the height of the terrain in this noise range.
            for(int y = 0; y <= cliffTallNess; y++)
            {
                chunkIn.setBlockState(localMutable, y == cliffTallNess ? GRASS_BLOCK : (y == cliffTallNess - 1 ? DIRT : layersBlocksArray[localMutable.getY() % layersBlocksArray.length]), false);
                localMutable.move(Direction.UP);
            }
            SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight + cliffTallNess, noise, defaultBlock, defaultFluid, seaLevel, seed, STONE_STONE_GRAVEL_CONFIG);
        } else {
            SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, GRASS_DIRT_SAND_CONFIG);
        }
    }
}
