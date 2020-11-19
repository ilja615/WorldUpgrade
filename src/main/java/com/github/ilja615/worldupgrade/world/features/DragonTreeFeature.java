package com.github.ilja615.worldupgrade.world.features;

import com.github.ilja615.worldupgrade.blocks.CoarseSandBlock;
import com.github.ilja615.worldupgrade.init.ModBlocks;
import com.github.ilja615.worldupgrade.util.DirectionUtil;
import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.IWorldGenerationBaseReader;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

public class DragonTreeFeature extends Feature<NoFeatureConfig>
{
    private static final BlockState TRUNK = ModBlocks.DRAGON_LOG.get().getDefaultState();
    private static final BlockState LEAF = ModBlocks.DRAGON_LEAVES.get().getDefaultState().with(LeavesBlock.DISTANCE, 1);

    public DragonTreeFeature(Codec<NoFeatureConfig> p_i231953_1_) {
        super(p_i231953_1_);
    }

    @Override
    public boolean generate(ISeedReader worldIn, ChunkGenerator chunkGenerator, Random rand, BlockPos positionIn, NoFeatureConfig config)
    {
        // Moving down until it is on the ground
        while (positionIn.getY() > 1 && isAirOrLeavesAt(worldIn, positionIn)) positionIn = positionIn.down();

        if (!isDirtAt(worldIn, positionIn) && !(worldIn.hasBlockState(positionIn, state -> state.getBlock() instanceof CoarseSandBlock)))
        {
            return false; // this tree is only allowed to grow on soil, but not on water or plant or other thing
        }

        // Make main trunk
        int hight = rand.nextInt(4) * 2 + 3;
        if (positionIn.getY() >= 1 && positionIn.getY() + hight + 8 + 1 <= worldIn.getHeight())
        {
            for (int j = positionIn.getY() + 1; j <= positionIn.getY() + 1 + hight; ++j)
            {
                setBlockState(worldIn, new BlockPos(positionIn.getX(), j, positionIn.getZ()), TRUNK);
            }
        }

        //canopy leaf
        for (int ix = -5; ix <= 5; ++ix)
        {
            for (int iz = -5; iz <= 5; ++iz)
            {
                for (int iy = 1; iy <= 2; ++iy)
                {
                    if (ix * ix + iz * iz + 6 * iy * iy <= 34 + rand.nextInt(5) && ix * ix + iz * iz + 3 * iy * iy >= 8 + rand.nextInt(3))
                    {
                        setBlockState(worldIn, positionIn.add(ix, 4 + hight + iy, iz), LEAF);
                    }
                }
            }
        }

        // Make the branches that go to every side and connect main trunk with canopy leaf
        for (Direction direction : DirectionUtil.DIRECTIONS_4h)
        {
            setBlockState(worldIn, new BlockPos(positionIn.getX(), positionIn.up(hight).getY() + 2, positionIn.getZ()).offset(direction, 1), TRUNK);
            setBlockState(worldIn, new BlockPos(positionIn.getX(), positionIn.up(hight).getY() + 3, positionIn.getZ()).offset(direction, 2), TRUNK);
            setBlockState(worldIn, new BlockPos(positionIn.getX(), positionIn.up(hight).getY() + 3, positionIn.getZ()).offset(direction, 3), TRUNK);
            setBlockState(worldIn, new BlockPos(positionIn.getX(), positionIn.up(hight).getY() + 4, positionIn.getZ()).offset(direction, 4).offset(DirectionUtil.getClockWise(direction)), TRUNK);
            setBlockState(worldIn, new BlockPos(positionIn.getX(), positionIn.up(hight).getY() + 4, positionIn.getZ()).offset(direction, 4).offset(DirectionUtil.getCounterClockWise(direction)), TRUNK);
            setBlockState(worldIn, new BlockPos(positionIn.getX(), positionIn.up(hight).getY() + 3, positionIn.getZ()).offset(direction, 2).offset(DirectionUtil.getCounterClockWise(direction)), TRUNK);
            setBlockState(worldIn, new BlockPos(positionIn.getX(), positionIn.up(hight).getY() + 4, positionIn.getZ()).offset(direction, 2).offset(DirectionUtil.getCounterClockWise(direction), 2), TRUNK);
            setBlockState(worldIn, new BlockPos(positionIn.getX(), positionIn.up(hight).getY() + 5, positionIn.getZ()).offset(direction, 2).offset(DirectionUtil.getCounterClockWise(direction), 2), TRUNK);
        }

        return true;
    }

    public static boolean isAirOrLeavesAt(IWorldGenerationBaseReader p_236412_0_, BlockPos p_236412_1_)
    {
        return p_236412_0_.hasBlockState(p_236412_1_, (p_236411_0_) ->
        {
            return p_236411_0_.isAir() || p_236411_0_.isIn(BlockTags.LEAVES);
        });
    }
}