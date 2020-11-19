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
    private static final BlockState STEM = ModBlocks.GUNNERA_STEM.get().getDefaultState();
    private static final BlockState LEAF = ModBlocks.BIG_PLANT_LEAF.get().getDefaultState();

    public GunneraPlantFeature(Codec<NoFeatureConfig> p_i231953_1_) {
        super(p_i231953_1_);
    }

    private void branchStart(ISeedReader worldIn, Random rand, BlockPos startPosition, Direction randomDirection)
    {
        int a = rand.nextInt(4);
        if (a == 0)
        {
            ifAirSetBlock(worldIn, startPosition.offset(randomDirection), STEM);
            ifAirSetBlock(worldIn, startPosition.up().offset(randomDirection, 2), STEM);
            ifAirSetBlock(worldIn, startPosition.up(2).offset(randomDirection, 3), STEM.with(RotatedPillarBlock.AXIS, randomDirection.getAxis()));
            ifAirSetBlock(worldIn, startPosition.up(2).offset(randomDirection, 4), STEM.with(RotatedPillarBlock.AXIS, randomDirection.getAxis()));
            bigLeaf(worldIn, rand, startPosition.up(3).offset(randomDirection, 4), randomDirection);
        }
        if (a == 1)
        {
            ifAirSetBlock(worldIn, startPosition.offset(randomDirection), STEM);
            ifAirSetBlock(worldIn, startPosition.up().offset(randomDirection), STEM);
            ifAirSetBlock(worldIn, startPosition.up(2).offset(randomDirection,2), STEM);
            ifAirSetBlock(worldIn, startPosition.up(3).offset(randomDirection, 3), STEM.with(RotatedPillarBlock.AXIS, randomDirection.getAxis()));
            ifAirSetBlock(worldIn, startPosition.up(3).offset(randomDirection, 4), STEM.with(RotatedPillarBlock.AXIS, randomDirection.getAxis()));
            bigLeaf(worldIn, rand, startPosition.up(4).offset(randomDirection, 4), randomDirection);
        }
        if (a == 2)
        {
            ifAirSetBlock(worldIn, startPosition.offset(randomDirection), STEM);
            ifAirSetBlock(worldIn, startPosition.up().offset(randomDirection, 2), STEM.with(RotatedPillarBlock.AXIS, randomDirection.getAxis()));
            ifAirSetBlock(worldIn, startPosition.up().offset(randomDirection, 3), STEM.with(RotatedPillarBlock.AXIS, randomDirection.getAxis()));
            smolLeaf(worldIn, rand, startPosition.up(2).offset(randomDirection, 3), randomDirection);
        }
        if (a == 3)
        {
            ifAirSetBlock(worldIn, startPosition.offset(randomDirection), STEM);
            ifAirSetBlock(worldIn, startPosition.up().offset(randomDirection, 2), STEM);
            ifAirSetBlock(worldIn, startPosition.up(2).offset(randomDirection, 2), STEM);
            ifAirSetBlock(worldIn, startPosition.up(3).offset(randomDirection, 3), STEM);
            ifAirSetBlock(worldIn, startPosition.up(4).offset(randomDirection, 4), STEM.with(RotatedPillarBlock.AXIS, randomDirection.getAxis()));
            ifAirSetBlock(worldIn, startPosition.up(4).offset(randomDirection, 5), STEM.with(RotatedPillarBlock.AXIS, randomDirection.getAxis()));
            bigLeaf(worldIn, rand, startPosition.up(5).offset(randomDirection, 5), randomDirection);
        }
    }

    private void bigLeaf(ISeedReader worldIn, Random rand, BlockPos startPosition, Direction randomDirection)
    {
        BlockState configuredLeafState = LEAF.with(HorizontalBlock.HORIZONTAL_FACING, randomDirection);
        ifAirSetBlock(worldIn, startPosition, configuredLeafState);
        for (int i = 0; i < 5; i++)
        {
            ifAirSetBlock(worldIn, startPosition.offset(DirectionUtil.getClockWise(randomDirection)).offset(randomDirection, i), configuredLeafState);
            ifAirSetBlock(worldIn, startPosition.offset(DirectionUtil.getCounterClockWise(randomDirection)).offset(randomDirection, i), configuredLeafState);
        }
        ifAirSetBlock(worldIn, startPosition.offset(randomDirection, 4), configuredLeafState);
        for (int i = 1; i < 4; i++)
        {
            ifAirSetBlock(worldIn, startPosition.offset(DirectionUtil.getClockWise(randomDirection),2).offset(randomDirection, i), configuredLeafState);
            ifAirSetBlock(worldIn, startPosition.offset(DirectionUtil.getCounterClockWise(randomDirection),2).offset(randomDirection, i), configuredLeafState);
        }
        for (int i = 1; i < 4; i++)
        {
            ifAirSetBlock(worldIn, startPosition.offset(randomDirection, i), STEM.with(RotatedPillarBlock.AXIS, randomDirection.getAxis()));
        }
    }

    private void smolLeaf(ISeedReader worldIn, Random rand, BlockPos startPosition, Direction randomDirection)
    {
        BlockState configuredLeafState = LEAF.with(HorizontalBlock.HORIZONTAL_FACING, randomDirection);
        ifAirSetBlock(worldIn, startPosition, configuredLeafState);
        ifAirSetBlock(worldIn, startPosition.offset(randomDirection,1), STEM.with(RotatedPillarBlock.AXIS, randomDirection.getAxis()));
        ifAirSetBlock(worldIn, startPosition.offset(randomDirection,2), STEM.with(RotatedPillarBlock.AXIS, randomDirection.getAxis()));
        ifAirSetBlock(worldIn, startPosition.offset(DirectionUtil.getClockWise(randomDirection)).offset(randomDirection,1), configuredLeafState);
        ifAirSetBlock(worldIn, startPosition.offset(DirectionUtil.getClockWise(randomDirection)).offset(randomDirection,2), configuredLeafState);
        ifAirSetBlock(worldIn, startPosition.offset(DirectionUtil.getCounterClockWise(randomDirection)).offset(randomDirection,1), configuredLeafState);
        ifAirSetBlock(worldIn, startPosition.offset(DirectionUtil.getCounterClockWise(randomDirection)).offset(randomDirection,2), configuredLeafState);
        ifAirSetBlock(worldIn, startPosition.offset(randomDirection,3), configuredLeafState);
    }

    @Override
    public boolean generate(ISeedReader worldIn, ChunkGenerator chunkGenerator, Random rand, BlockPos positionIn, NoFeatureConfig config)
    {
        // Moving down until it is on the ground
        while (positionIn.getY() > 1 && isAirOrLeavesAt(worldIn, positionIn)) positionIn = positionIn.down();

        if (!isDirtAt(worldIn, positionIn))
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
        return p_236412_0_.hasBlockState(p_236412_1_, (p_236411_0_) ->
        {
            return p_236411_0_.isAir() || p_236411_0_.isIn(BlockTags.LEAVES);
        });
    }

    private void ifAirSetBlock(ISeedReader worldIn, BlockPos pos, BlockState blockState)
    {
        if (isAirAt(worldIn, pos)) setBlockState(worldIn, pos, blockState);
    }
}