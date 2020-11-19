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

    private static final BlockState SMOKE_VENT = ModBlocks.SMOKE_VENT.get().getDefaultState();

    public GeothermalVentFeature(Codec<NoFeatureConfig> config)
    {
        super(config);
    }


    protected static boolean isAirOrLeaves(IWorldGenerationBaseReader worldIn, BlockPos pos)
    {
        if (!(worldIn instanceof net.minecraft.world.IWorldReader)) // FORGE: Redirect to state method when possible
            return worldIn.hasBlockState(pos, (p_214581_0_) -> p_214581_0_.isAir() || p_214581_0_.isIn(BlockTags.LEAVES));
        else
            return worldIn.hasBlockState(pos, state -> state.canBeReplacedByLeaves((net.minecraft.world.IWorldReader) worldIn, pos));
    }

    @Override
    public boolean generate(ISeedReader worldIn, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config)
    {
        pos = pos.down(2);
        int i = 2 + rand.nextInt(2);
        int j = 2 + rand.nextInt(2);

        for (BlockPos blockpos : BlockPos.getAllInBoxMutable(pos.add(-i, 0, -j), pos.add(i, 0, j)))
        {
            int k = pos.getX() - blockpos.getX();
            int l = pos.getZ() - blockpos.getZ();
            float c1 = (float) (k * k + l * l);
            float c2 = rand.nextFloat() * 8.0F - rand.nextFloat() * 6.0F;
            if (c1 <= c2 - 3)
            {
                setBlockState(worldIn, blockpos, SMOKE_VENT);
                setBlockState(worldIn, blockpos.up(), SMOKE_VENT);
                setBlockState(worldIn, blockpos.up(2), ModBlocks.FUMAROLE.get().getDefaultState());
            } else if (c1 <= c2)
            {
                setBlockState(worldIn, blockpos, SMOKE_VENT);
                if (rand.nextBoolean())
                    setBlockState(worldIn, blockpos.up(), SMOKE_VENT);
                else
                    setBlockState(worldIn, blockpos.up(), ModBlocks.FUMAROLE.get().getDefaultState());
            } else if (c1 <= c2 + 3)
            {
                setBlockState(worldIn, blockpos, SMOKE_VENT);
            } else if ((double) rand.nextFloat() < 0.031D)
            {
                setBlockState(worldIn, blockpos, SMOKE_VENT);
            }
        }
        for (BlockPos blockpos : BlockPos.getAllInBoxMutable(pos.add(-i, 0, -j), pos.add(i, 2, j)))
        {
            if (worldIn.getBlockState(blockpos).getBlock() == ModBlocks.SMOKE_VENT.get())
            {
                Block block = worldIn.getBlockState(blockpos.up()).getBlock();
                if (block == ModBlocks.GRAVEL_DARK.get()
                        || block == Blocks.DIRT
                        || block == Blocks.COARSE_DIRT
                        || block == Blocks.GRASS_BLOCK)
                    setBlockState(worldIn, blockpos.up(), Blocks.AIR.getDefaultState());
                Block block2 = worldIn.getBlockState(blockpos.up(2)).getBlock();
                if (block2 == ModBlocks.GRAVEL_DARK.get()
                        || block2 == Blocks.DIRT
                        || block2 == Blocks.COARSE_DIRT
                        || block2 == Blocks.GRASS_BLOCK)
                    setBlockState(worldIn, blockpos.up(2), Blocks.AIR.getDefaultState());
            }
        }
        return true;
    }
}
