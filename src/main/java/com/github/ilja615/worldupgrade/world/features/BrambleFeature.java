package com.github.ilja615.worldupgrade.world.features;

import com.github.ilja615.worldupgrade.blocks.BrambleBushBlock;
import com.github.ilja615.worldupgrade.blocks.BrambleFullBlock;
import com.github.ilja615.worldupgrade.init.ModBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;
import java.util.function.Function;

public class BrambleFeature extends Feature<NoFeatureConfig>
{

    private static final BlockState BRAMBLE_FULL_2 = ModBlocks.BRAMBLE_FULL.get().defaultBlockState().setValue(BrambleFullBlock.AGE, 2);
    private static final BlockState BRAMBLE_FULL_3 = ModBlocks.BRAMBLE_FULL.get().defaultBlockState().setValue(BrambleFullBlock.AGE, 3);
    private static final BlockState BRAMBLE_BUSH_2 = ModBlocks.BRAMBLE_BUSH.get().defaultBlockState().setValue(BrambleBushBlock.AGE, 2);
    private static final BlockState BRAMBLE_BUSH_3 = ModBlocks.BRAMBLE_BUSH.get().defaultBlockState().setValue(BrambleBushBlock.AGE, 3);

    public BrambleFeature(Codec<NoFeatureConfig> p_i231953_1_) {
        super(p_i231953_1_);
    }


    @Override
    public boolean place(ISeedReader worldIn, ChunkGenerator chunkGenerator, Random rand, BlockPos pos, NoFeatureConfig config)
    {
        int i = 3 + rand.nextInt(3);
        int j = 3 + rand.nextInt(3);

        for (BlockPos blockpos : BlockPos.betweenClosed(pos.offset(-i, 0, -j), pos.offset(i, 1, j)))
        {
            int k = pos.getX() - blockpos.getX();
            int l = pos.getZ() - blockpos.getZ();
            if ((float) (k * k + l * l) <= rand.nextFloat() * 16.0F - rand.nextFloat() * 6.0F)
            {
                this.placeBlockAt(worldIn, blockpos, rand);
            } else if ((double) rand.nextFloat() < 0.031D)
            {
                this.placeBlockAt(worldIn, blockpos, rand);
            }
        }

        for (BlockPos blockpos : BlockPos.betweenClosed(pos.offset(-i, 0, -j), pos.offset(i, 1, j)))
        {
            if (worldIn.getBlockState(blockpos).getBlock() instanceof BrambleFullBlock && worldIn.getBlockState(blockpos.above()).getBlock() == Blocks.AIR)
            {
                if (rand.nextBoolean())
                {
                    if (rand.nextBoolean()) setBlock(worldIn, blockpos, BRAMBLE_BUSH_2);
                    else setBlock(worldIn, blockpos, BRAMBLE_BUSH_3);
                }
            }
        }

        return true;
    }

    private void placeBlockAt(IWorld world, BlockPos pos, Random rand)
    {
        if (world.getBlockState(pos).getBlock() == Blocks.AIR)
        {
            if (ModBlocks.BRAMBLE_BUSH.get().defaultBlockState().canSurvive(world, pos) || world.getBlockState(pos.below()).getBlock() instanceof BrambleFullBlock)
            {
                if (rand.nextBoolean()) setBlock(world, pos, BRAMBLE_FULL_2);
                else setBlock(world, pos, BRAMBLE_FULL_3);
            }
        }
    }
}
