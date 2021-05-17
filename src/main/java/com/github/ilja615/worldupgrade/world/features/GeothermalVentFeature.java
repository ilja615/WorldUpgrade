package com.github.ilja615.worldupgrade.world.features;

import com.github.ilja615.worldupgrade.init.ModBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.IWorldGenerationBaseReader;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;
import java.util.function.Function;

public class GeothermalVentFeature extends Feature<NoFeatureConfig>
{

    private static final BlockState SMOKE_VENT = ModBlocks.SMOKE_VENT.get().defaultBlockState();

    public GeothermalVentFeature(Codec<NoFeatureConfig> config)
    {
        super(config);
    }

    @Override
    public boolean place(ISeedReader worldIn, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config)
    {
        // Moving down until it is on the ground
        while (pos.getY() > 1 && isAirOrLeavesAt(worldIn, pos)) pos = pos.below();

        if (!isGrassOrDirt(worldIn, pos) && !(worldIn.isStateAtPosition(pos, state -> state.getBlock().equals(ModBlocks.ASH_DIRT.get()))) && !(worldIn.isStateAtPosition(pos, state -> state.getBlock().equals(ModBlocks.GRAVEL_DARK.get()))))
        {
            return false; // this tree is only allowed to grow on soil, but not on water or plant or other thing
        }

        pos = pos.below(1);
        int i = 2 + rand.nextInt(2);
        int j = 2 + rand.nextInt(2);

        for (BlockPos blockpos : BlockPos.betweenClosed(pos.offset(-i, 0, -j), pos.offset(i, 0, j)))
        {
            int k = pos.getX() - blockpos.getX();
            int l = pos.getZ() - blockpos.getZ();
            float c1 = (float) (k * k + l * l);
            float c2 = rand.nextFloat() * 8.0F - rand.nextFloat() * 6.0F;
            if (c1 <= c2 - 3)
            {
                setBlock(worldIn, blockpos, SMOKE_VENT);
                setBlock(worldIn, blockpos.above(), SMOKE_VENT);
                setBlock(worldIn, blockpos.above(2), ModBlocks.FUMAROLE.get().defaultBlockState());
            } else if (c1 <= c2)
            {
                setBlock(worldIn, blockpos, SMOKE_VENT);
                if (rand.nextBoolean())
                    setBlock(worldIn, blockpos.above(), SMOKE_VENT);
                else
                    setBlock(worldIn, blockpos.above(), ModBlocks.FUMAROLE.get().defaultBlockState());
            } else if (c1 <= c2 + 3)
            {
                setBlock(worldIn, blockpos, SMOKE_VENT);
            } else if ((double) rand.nextFloat() < 0.031D)
            {
                setBlock(worldIn, blockpos, SMOKE_VENT);
            }
        }
        for (BlockPos blockpos : BlockPos.betweenClosed(pos.offset(-i, 0, -j), pos.offset(i, 2, j)))
        {
            if (worldIn.getBlockState(blockpos).getBlock() == ModBlocks.SMOKE_VENT.get())
            {
                Block block = worldIn.getBlockState(blockpos.above()).getBlock();
                if (block == ModBlocks.GRAVEL_DARK.get()
                        || block == Blocks.DIRT
                        || block == Blocks.COARSE_DIRT
                        || block == Blocks.GRASS_BLOCK)
                    setBlock(worldIn, blockpos.above(), Blocks.AIR.defaultBlockState());
                Block block2 = worldIn.getBlockState(blockpos.above(2)).getBlock();
                if (block2 == ModBlocks.GRAVEL_DARK.get()
                        || block2 == Blocks.DIRT
                        || block2 == Blocks.COARSE_DIRT
                        || block2 == Blocks.GRASS_BLOCK)
                    setBlock(worldIn, blockpos.above(2), Blocks.AIR.defaultBlockState());
            }
        }
        return true;
    }

    public static boolean isAirOrLeavesAt(IWorldGenerationBaseReader p_236412_0_, BlockPos p_236412_1_)
    {
        return p_236412_0_.isStateAtPosition(p_236412_1_, (p_236411_0_) ->
        {
            return p_236411_0_.isAir() || p_236411_0_.is(BlockTags.LEAVES);
        });
    }
}
