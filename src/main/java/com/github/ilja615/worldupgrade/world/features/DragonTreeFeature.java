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
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.IWorldGenerationBaseReader;
import net.minecraft.world.gen.PerlinNoiseGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Collections;
import java.util.Random;

public class DragonTreeFeature extends Feature<NoFeatureConfig>
{
    private static final BlockState TRUNK = ModBlocks.DRAGON_LOG.get().defaultBlockState();
    private static final BlockState LEAF = ModBlocks.DRAGON_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1);

    public static final PerlinNoiseGenerator perlinNoiseGenerator = new PerlinNoiseGenerator(new SharedSeedRandom(9364L), Collections.singletonList(4));

    public DragonTreeFeature(Codec<NoFeatureConfig> p_i231953_1_) {
        super(p_i231953_1_);
    }

    @Override
    public boolean place(ISeedReader worldIn, ChunkGenerator chunkGenerator, Random rand, BlockPos positionIn, NoFeatureConfig config)
    {
        // Moving down until it is on the ground
        while (positionIn.getY() > 1 && isAirOrLeavesAt(worldIn, positionIn)) positionIn = positionIn.below();

        if (!isGrassOrDirt(worldIn, positionIn) && !(worldIn.isStateAtPosition(positionIn, state -> state.getBlock() instanceof CoarseSandBlock)))
        {
            return false; // this tree is only allowed to grow on soil, but not on water or plant or other thing
        }

        // Make main trunk
        int hight = rand.nextInt(4) * 2 + 3;
        if (positionIn.getY() >= 1 && positionIn.getY() + hight + 8 + 1 <= worldIn.getMaxBuildHeight())
        {
            for (int j = positionIn.getY() + 1; j <= positionIn.getY() + 1 + hight; ++j)
            {
                setBlock(worldIn, new BlockPos(positionIn.getX(), j, positionIn.getZ()), TRUNK);
            }
        }

        for (Direction d : DirectionUtil.DIRECTIONS_4h)
            setBlock(worldIn, positionIn.above(hight + 2).relative(d), TRUNK);

//        int b = 3 + rand.nextInt(3);
//        float f1 = rand.nextFloat() - 0.5f;
//        float f2 = rand.nextFloat() - 0.5f;
//        for (BlockPos blockpos : BlockPos.getAllInBoxMutable(positionIn.add(-b, hight + 3, -b), positionIn.add(b, hight + 2 + 4, b)))
//        {
//            int ix = positionIn.getX() - blockpos.getX();
//            int iy = positionIn.getY() + hight + 1 - blockpos.getY();
//            int iz = positionIn.getZ() - blockpos.getZ();
//            int ex = Math.round(f1 / 3 * iz + ix > 0 ? f2*2 : -f2*2);
//            int ez = Math.round(f2 / 3 * ix + iz > 0 ? f1*2 : -f1*2);
//            if ((ix == iy || iz == iy || iz == -iy || ix == -iy) && (ix == 0 || iz == 0))
//                setBlockState(worldIn, blockpos.add(ex, 0, ez), TRUNK);
//        }

        //canopy leaf
        for (int ix = -5; ix <= 5; ++ix)
        {
            for (int iz = -5; iz <= 5; ++iz)
            {
                for (int iy = 1; iy <= 2; ++iy)
                {
                    if (ix * ix + iz * iz + 6 * iy * iy <= 34 + rand.nextInt(5) && ix * ix + iz * iz + 3 * iy * iy >= 8 + rand.nextInt(3))
                    {
                        setBlock(worldIn, positionIn.offset(ix,  hight + iy + 4, iz), LEAF);
                    }
                }
            }
        }

        // Make the branches that go to every side and connect main trunk with canopy leaf
        for (Direction direction : DirectionUtil.DIRECTIONS_4h)
        {
            setBlock(worldIn, new BlockPos(positionIn.getX(), positionIn.above(hight).getY() + 2, positionIn.getZ()).relative(direction, 1), TRUNK);
            setBlock(worldIn, new BlockPos(positionIn.getX(), positionIn.above(hight).getY() + 3, positionIn.getZ()).relative(direction, 2), TRUNK);
            setBlock(worldIn, new BlockPos(positionIn.getX(), positionIn.above(hight).getY() + 3, positionIn.getZ()).relative(direction, 3), TRUNK);
            int c = rand.nextInt(2);
            if (c == 0)
            {
                setBlock(worldIn, new BlockPos(positionIn.getX(), positionIn.above(hight).getY() + 4, positionIn.getZ()).relative(direction, 4).relative(DirectionUtil.getClockWise(direction)), TRUNK);
                setBlock(worldIn, new BlockPos(positionIn.getX(), positionIn.above(hight).getY() + 4, positionIn.getZ()).relative(direction, 4).relative(DirectionUtil.getCounterClockWise(direction)), TRUNK);
                setBlock(worldIn, new BlockPos(positionIn.getX(), positionIn.above(hight).getY() + 3, positionIn.getZ()).relative(direction, 2).relative(DirectionUtil.getCounterClockWise(direction)), TRUNK);
                setBlock(worldIn, new BlockPos(positionIn.getX(), positionIn.above(hight).getY() + 4, positionIn.getZ()).relative(direction, 2).relative(DirectionUtil.getCounterClockWise(direction), 2), TRUNK);
                setBlock(worldIn, new BlockPos(positionIn.getX(), positionIn.above(hight).getY() + 5, positionIn.getZ()).relative(direction, 2).relative(DirectionUtil.getCounterClockWise(direction), 2), TRUNK);
            } else if (c == 1) {
                setBlock(worldIn, new BlockPos(positionIn.getX(), positionIn.above(hight).getY() + 4, positionIn.getZ()).relative(direction, 4).relative(DirectionUtil.getCounterClockWise(direction)), TRUNK);
                setBlock(worldIn, new BlockPos(positionIn.getX(), positionIn.above(hight).getY() + 4, positionIn.getZ()).relative(direction, 4).relative(DirectionUtil.getClockWise(direction)), TRUNK);
                setBlock(worldIn, new BlockPos(positionIn.getX(), positionIn.above(hight).getY() + 3, positionIn.getZ()).relative(direction, 2).relative(DirectionUtil.getClockWise(direction)), TRUNK);
                setBlock(worldIn, new BlockPos(positionIn.getX(), positionIn.above(hight).getY() + 4, positionIn.getZ()).relative(direction, 2).relative(DirectionUtil.getClockWise(direction), 2), TRUNK);
                setBlock(worldIn, new BlockPos(positionIn.getX(), positionIn.above(hight).getY() + 5, positionIn.getZ()).relative(direction, 2).relative(DirectionUtil.getClockWise(direction), 2), TRUNK);
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