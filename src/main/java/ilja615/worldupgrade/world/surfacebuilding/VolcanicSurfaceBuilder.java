package ilja615.worldupgrade.world.surfacebuilding;

import com.mojang.datafixers.Dynamic;
import ilja615.worldupgrade.init.ModBlocks;
import ilja615.worldupgrade.init.ModBlocksNew;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

import java.util.Random;
import java.util.function.Function;

public class VolcanicSurfaceBuilder extends SurfaceBuilder<SurfaceBuilderConfig>
{
    public static final BlockState GRAVEL_DARK = ModBlocksNew.GRAVEL_DARK.get().getDefaultState();
    //public static final BlockState GRAVEL_LIGHT = ModBlocks.GRAVEL_LIGHT.getDefaultState();
    public static final BlockState COARSE_DIRT = Blocks.COARSE_DIRT.getDefaultState();

    public static final SurfaceBuilderConfig GRAVEL_DARK_CONFIG = new SurfaceBuilderConfig(GRAVEL_DARK, GRAVEL_DARK, GRAVEL_DARK);
    //public static final SurfaceBuilderConfig GRAVEL_LIGHT_CONFIG = new SurfaceBuilderConfig(GRAVEL_LIGHT, GRAVEL_LIGHT, GRAVEL_LIGHT);
    public static final SurfaceBuilderConfig COARSE_DIRT_CONFIG = new SurfaceBuilderConfig(COARSE_DIRT, COARSE_DIRT, COARSE_DIRT);
    private int r = 0;

    public VolcanicSurfaceBuilder(Function<Dynamic<?>, ? extends SurfaceBuilderConfig> deserializer)
    {
        super(deserializer);
    }

    @Override
    public void buildSurface(Random random, IChunk chunkIn, Biome biomeIn, int x, int z, int startHeight, double noise, BlockState defaultBlock, BlockState defaultFluid, int seaLevel, long seed, SurfaceBuilderConfig config) {

//        if (noise > 2.5D) {
//            SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, GRAVEL_DARK_CONFIG);
//        } else if (noise > 1.0D) {
//            if (random.nextBoolean() == true)
//                SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, GRAVEL_DARK_CONFIG);
//            else
//                SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, SurfaceBuilder.GRAVEL_CONFIG);
//        } else if (noise > -1.0D) {
//            SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, SurfaceBuilder.GRAVEL_CONFIG);
//        } else if (noise > -2.5D) {
//            if (random.nextBoolean() == true)
//                SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, GRAVEL_LIGHT_CONFIG);
//            else
//                SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, SurfaceBuilder.GRAVEL_CONFIG);
//        } else {
//            SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, GRAVEL_LIGHT_CONFIG);
//        }

//        if (noise > 2.3D) {
//            SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, GRAVEL_DARK_CONFIG);
//        } else if (noise < -2.7D) {
//            SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, SurfaceBuilder.GRASS_DIRT_GRAVEL_CONFIG);
//        } else {
//            r = random.nextInt(3);
//            if (r == 1 || r == 3)
//                SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, GRASS_DIRT_GRAVEL_CONFIG);
//            if (r == 2 || r == 4)
//                SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, GRAVEL_DARK_CONFIG);
//            if (r == 0)
//                SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, COARSE_DIRT_CONFIG);
//        }

        if (noise > 2.0D) {
            SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, GRAVEL_DARK_CONFIG);
        } else if (noise < -2.5D) {
            SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, SurfaceBuilder.GRASS_DIRT_GRAVEL_CONFIG);
        } else {
            r = random.nextInt(2);
            if (r == 0)
                SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, GRASS_DIRT_GRAVEL_CONFIG);
            if (r == 1)
                SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, GRAVEL_DARK_CONFIG);
        }
    }
}
