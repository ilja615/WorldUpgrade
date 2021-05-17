package com.github.ilja615.worldupgrade.world.features;

import com.github.ilja615.worldupgrade.init.ModBlocks;
import com.github.ilja615.worldupgrade.util.DirectionUtil;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.IWorldGenerationBaseReader;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class GunneraPlantFeature extends Feature<NoFeatureConfig>
{
    private static final BlockState STEM = ModBlocks.GUNNERA_STEM.get().defaultBlockState();
    private static final BlockState LEAF = ModBlocks.BIG_PLANT_LEAF.get().defaultBlockState();

    public GunneraPlantFeature(Codec<NoFeatureConfig> p_i231953_1_) {
        super(p_i231953_1_);
    }

    private void branchStart(ISeedReader worldIn, Random rand, BlockPos startPosition, Direction randomDirection)
    {
        int a = rand.nextInt(4);
        if (a == 0)
        {
            ifAirSetBlock(worldIn, startPosition.relative(randomDirection), STEM);
            ifAirSetBlock(worldIn, startPosition.above().relative(randomDirection, 2), STEM);
            ifAirSetBlock(worldIn, startPosition.above(2).relative(randomDirection, 3), STEM.setValue(RotatedPillarBlock.AXIS, randomDirection.getAxis()));
            ifAirSetBlock(worldIn, startPosition.above(2).relative(randomDirection, 4), STEM.setValue(RotatedPillarBlock.AXIS, randomDirection.getAxis()));
            bigLeaf(worldIn, rand, startPosition.above(3).relative(randomDirection, 4), randomDirection);
        }
        if (a == 1)
        {
            ifAirSetBlock(worldIn, startPosition.relative(randomDirection), STEM);
            ifAirSetBlock(worldIn, startPosition.above().relative(randomDirection), STEM);
            ifAirSetBlock(worldIn, startPosition.above(2).relative(randomDirection,2), STEM);
            ifAirSetBlock(worldIn, startPosition.above(3).relative(randomDirection, 3), STEM.setValue(RotatedPillarBlock.AXIS, randomDirection.getAxis()));
            ifAirSetBlock(worldIn, startPosition.above(3).relative(randomDirection, 4), STEM.setValue(RotatedPillarBlock.AXIS, randomDirection.getAxis()));
            bigLeaf(worldIn, rand, startPosition.above(4).relative(randomDirection, 4), randomDirection);
        }
        if (a == 2)
        {
            ifAirSetBlock(worldIn, startPosition.relative(randomDirection), STEM);
            ifAirSetBlock(worldIn, startPosition.above().relative(randomDirection, 2), STEM.setValue(RotatedPillarBlock.AXIS, randomDirection.getAxis()));
            ifAirSetBlock(worldIn, startPosition.above().relative(randomDirection, 3), STEM.setValue(RotatedPillarBlock.AXIS, randomDirection.getAxis()));
            smolLeaf(worldIn, rand, startPosition.above(2).relative(randomDirection, 3), randomDirection);
        }
        if (a == 3)
        {
            ifAirSetBlock(worldIn, startPosition.relative(randomDirection), STEM);
            ifAirSetBlock(worldIn, startPosition.above().relative(randomDirection, 2), STEM);
            ifAirSetBlock(worldIn, startPosition.above(2).relative(randomDirection, 2), STEM);
            ifAirSetBlock(worldIn, startPosition.above(3).relative(randomDirection, 3), STEM);
            ifAirSetBlock(worldIn, startPosition.above(4).relative(randomDirection, 4), STEM.setValue(RotatedPillarBlock.AXIS, randomDirection.getAxis()));
            ifAirSetBlock(worldIn, startPosition.above(4).relative(randomDirection, 5), STEM.setValue(RotatedPillarBlock.AXIS, randomDirection.getAxis()));
            bigLeaf(worldIn, rand, startPosition.above(5).relative(randomDirection, 5), randomDirection);
        }
    }

    private void bigLeaf(ISeedReader worldIn, Random rand, BlockPos startPosition, Direction randomDirection)
    {
        BlockState configuredLeafState = LEAF.setValue(HorizontalBlock.FACING, randomDirection.getOpposite());
        ifAirSetBlock(worldIn, startPosition, configuredLeafState);
        for (int i = 0; i < 5; i++)
        {
            ifAirSetBlock(worldIn, startPosition.relative(DirectionUtil.getClockWise(randomDirection)).relative(randomDirection, i), configuredLeafState);
            ifAirSetBlock(worldIn, startPosition.relative(DirectionUtil.getCounterClockWise(randomDirection)).relative(randomDirection, i), configuredLeafState);
        }
        ifAirSetBlock(worldIn, startPosition.relative(randomDirection, 4), configuredLeafState);
        for (int i = 1; i < 4; i++)
        {
            ifAirSetBlock(worldIn, startPosition.relative(DirectionUtil.getClockWise(randomDirection),2).relative(randomDirection, i), configuredLeafState);
            ifAirSetBlock(worldIn, startPosition.relative(DirectionUtil.getCounterClockWise(randomDirection),2).relative(randomDirection, i), configuredLeafState);
        }
        for (int i = 1; i < 4; i++)
        {
            ifAirSetBlock(worldIn, startPosition.relative(randomDirection, i), STEM.setValue(RotatedPillarBlock.AXIS, randomDirection.getAxis()));
        }
    }

    private void smolLeaf(ISeedReader worldIn, Random rand, BlockPos startPosition, Direction randomDirection)
    {
        BlockState configuredLeafState = LEAF.setValue(HorizontalBlock.FACING, randomDirection.getOpposite());
        ifAirSetBlock(worldIn, startPosition, configuredLeafState);
        ifAirSetBlock(worldIn, startPosition.relative(randomDirection,1), STEM.setValue(RotatedPillarBlock.AXIS, randomDirection.getAxis()));
        ifAirSetBlock(worldIn, startPosition.relative(randomDirection,2), STEM.setValue(RotatedPillarBlock.AXIS, randomDirection.getAxis()));
        ifAirSetBlock(worldIn, startPosition.relative(DirectionUtil.getClockWise(randomDirection)).relative(randomDirection,1), configuredLeafState);
        ifAirSetBlock(worldIn, startPosition.relative(DirectionUtil.getClockWise(randomDirection)).relative(randomDirection,2), configuredLeafState);
        ifAirSetBlock(worldIn, startPosition.relative(DirectionUtil.getCounterClockWise(randomDirection)).relative(randomDirection,1), configuredLeafState);
        ifAirSetBlock(worldIn, startPosition.relative(DirectionUtil.getCounterClockWise(randomDirection)).relative(randomDirection,2), configuredLeafState);
        ifAirSetBlock(worldIn, startPosition.relative(randomDirection,3), configuredLeafState);
    }

    @Override
    public boolean place(ISeedReader worldIn, ChunkGenerator chunkGenerator, Random rand, BlockPos positionIn, NoFeatureConfig config)
    {
        // Moving down until it is on the ground
        while (positionIn.getY() > 1 && isAirOrLeavesAt(worldIn, positionIn)) positionIn = positionIn.below();

        if (!isGrassOrDirt(worldIn, positionIn))
        {
            return false; // this tree is only allowed to grow on soil, but not on water or plant or other thing
        }

        ifAirSetBlock(worldIn, new BlockPos(positionIn.getX(), positionIn.getY() + 1, positionIn.getZ()), STEM);

        ArrayList<Direction> dirs = new ArrayList<Direction>(Arrays.asList(DirectionUtil.DIRECTIONS_4h));
        dirs.remove(rand.nextInt(4));
        if (rand.nextBoolean()) dirs.remove(rand.nextInt(3));
        for (Direction direction : dirs)
        {
            branchStart(worldIn, rand, new BlockPos(positionIn.getX(), positionIn.getY() + 2, positionIn.getZ()),direction);
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

    private void ifAirSetBlock(ISeedReader worldIn, BlockPos pos, BlockState blockState)
    {
        if (isAir(worldIn, pos)) setBlock(worldIn, pos, blockState);
    }
}