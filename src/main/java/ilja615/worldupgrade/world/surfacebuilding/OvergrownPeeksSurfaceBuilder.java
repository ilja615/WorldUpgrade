package ilja615.worldupgrade.world.surfacebuilding;

import com.mojang.datafixers.Dynamic;
import ilja615.worldupgrade.init.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

import java.util.Random;
import java.util.function.Function;

public class OvergrownPeeksSurfaceBuilder extends SurfaceBuilder<SurfaceBuilderConfig>
{
    public static final BlockState JUNGLE_ROCK = ModBlocks.JUNGLE_ROCK.getDefaultState();
    public static final BlockState OVERGROWN = ModBlocks.JUNGLE_ROCK_OVERGROWN.getDefaultState();
    public static final BlockState GRASS = Blocks.GRASS_BLOCK.getDefaultState();
    public static final BlockState SAND = Blocks.SAND.getDefaultState();

    public static final SurfaceBuilderConfig JUNGLE_ROCK_GRASS = new SurfaceBuilderConfig(GRASS, JUNGLE_ROCK, SAND);
    public static final SurfaceBuilderConfig JUNGLE_ROCK_OVERGROWN = new SurfaceBuilderConfig(OVERGROWN, JUNGLE_ROCK, SAND);

    private int r = 0;

    public OvergrownPeeksSurfaceBuilder(Function<Dynamic<?>, ? extends SurfaceBuilderConfig> deserializer)
    {
        super(deserializer);
    }

    @Override
    public void buildSurface(Random random, IChunk chunkIn, Biome biomeIn, int x, int z, int startHeight, double noise, BlockState defaultBlock, BlockState defaultFluid, int seaLevel, long seed, SurfaceBuilderConfig config)
    {
        if (random.nextBoolean() == true)
            SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, JUNGLE_ROCK_GRASS);
        else
            SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, JUNGLE_ROCK_OVERGROWN);
    }
}
