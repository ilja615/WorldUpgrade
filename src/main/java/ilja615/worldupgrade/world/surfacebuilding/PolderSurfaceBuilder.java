package ilja615.worldupgrade.world.surfacebuilding;

import com.mojang.datafixers.Dynamic;
import ilja615.worldupgrade.init.ModBlocks;
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

import java.util.Random;
import java.util.function.Function;

public class PolderSurfaceBuilder extends SurfaceBuilder<SurfaceBuilderConfig>
{
    public static final SurfaceBuilderConfig DIRT_AND_CLAY_CONFIG = new SurfaceBuilderConfig(Blocks.CLAY.getDefaultState(), DIRT, Blocks.CLAY.getDefaultState());
    public static final SurfaceBuilderConfig DIRT_AND_GRASSY_CLAY_CONFIG = new SurfaceBuilderConfig(ModBlocks.GRASSY_CLAY.get().getDefaultState(), DIRT, Blocks.CLAY.getDefaultState());
    public static final PerlinNoiseGenerator perlinNoiseGenerator = new PerlinNoiseGenerator(new SharedSeedRandom(4208L), 4, 0);

    public PolderSurfaceBuilder(Function<Dynamic<?>, ? extends SurfaceBuilderConfig> deserializer)
    {
        super(deserializer);
    }

    @Override
    public void buildSurface(Random random, IChunk chunkIn, Biome biomeIn, int x, int z, int startHeight, double noise, BlockState defaultBlock, BlockState defaultFluid, int seaLevel, long seed, SurfaceBuilderConfig config)
    {
        if (noise > 0.35 && noise < 0.7) {
            SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, DIRT_AND_CLAY_CONFIG);
            for (int y = chunkIn.getTopBlockY(Heightmap.Type.WORLD_SURFACE, x, z); y> 61; y--) {
                chunkIn.setBlockState(new BlockPos(x, y, z), Blocks.AIR.getDefaultState(), false);
            }
            chunkIn.setBlockState(new BlockPos(x, 62, z), Blocks.WATER.getDefaultState(), false);
        } else {
            if (perlinNoiseGenerator.noiseAt(x, z, false) > 0.45) {
                SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, DIRT_AND_CLAY_CONFIG);
            } else {
                SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, DIRT_AND_GRASSY_CLAY_CONFIG);
            }
        }
    }
}
