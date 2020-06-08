package ilja615.worldupgrade.world.carvers;

import com.mojang.datafixers.Dynamic;
import net.minecraft.block.Blocks;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.PerlinNoiseGenerator;
import net.minecraft.world.gen.carver.ICarverConfig;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.ProbabilityConfig;

import java.util.BitSet;
import java.util.Random;
import java.util.function.Function;

public class PolderTinyRiverCarver extends WorldCarver<ProbabilityConfig>
{
    public static final PerlinNoiseGenerator perlinNoiseGenerator = new PerlinNoiseGenerator(new SharedSeedRandom(4208L), 4, 0);

    public PolderTinyRiverCarver(Function<Dynamic<?>, ? extends ProbabilityConfig> p_i49921_1_, int p_i49921_2_) {
        super(p_i49921_1_, p_i49921_2_);
    }

    @Override
    public boolean func_225555_a_(IChunk chunkIn, Function<BlockPos, Biome> blockPosBiomeFunction, Random rand, int p_225555_4_, int p_225555_5_, int p_225555_6_, int p_225555_7_, int p_225555_8_, BitSet bitSet, ProbabilityConfig config)
    {
        double noise = perlinNoiseGenerator.noiseAt(p_225555_4_, p_225555_5_, false);
        if (noise > 0.4 && noise < 0.7)
        {
            for (int y = chunkIn.getTopBlockY(Heightmap.Type.WORLD_SURFACE, p_225555_4_, p_225555_5_); y> 61; y--) {
                chunkIn.setBlockState(new BlockPos(p_225555_4_, y, p_225555_5_), Blocks.AIR.getDefaultState(), false);
            }
            chunkIn.setBlockState(new BlockPos(p_225555_4_, 62, p_225555_5_), Blocks.OBSIDIAN.getDefaultState(), false);
        }
        return true;
    }

    @Override
    public boolean shouldCarve(Random rand, int chunkX, int chunkZ, ProbabilityConfig config) {
        //return rand.nextFloat() <= config.probability;
        return true;
    }

    @Override
    protected boolean func_222708_a(double p_222708_1_, double p_222708_3_, double p_222708_5_, int p_222708_7_) {
        return true;
    }
}
