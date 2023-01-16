package com.github.ilja615.worldupgrade.world.feature;

import com.github.ilja615.worldupgrade.init.ModBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class GunneraFeature extends Feature<NoneFeatureConfiguration>
{
    private static final BlockState STEM = ModBlocks.BIG_PLANT_STEM.get().defaultBlockState();
    private static final BlockState LEAF = ModBlocks.GUNNERA_LEAF.get().defaultBlockState();

    public GunneraFeature(Codec<NoneFeatureConfiguration> configurationCodec) {
        super(configurationCodec);
    }

    private void branchStart(WorldGenLevel level, RandomSource rand, BlockPos startPosition, Direction randomDirection)
    {
        int a = rand.nextInt(4);
        if (a == 0)
        {
            ifAirSetBlock(level, startPosition.relative(randomDirection), STEM);
            ifAirSetBlock(level, startPosition.above().relative(randomDirection, 2), STEM);
            ifAirSetBlock(level, startPosition.above(2).relative(randomDirection, 3), STEM.setValue(RotatedPillarBlock.AXIS, randomDirection.getAxis()));
            ifAirSetBlock(level, startPosition.above(2).relative(randomDirection, 4), STEM.setValue(RotatedPillarBlock.AXIS, randomDirection.getAxis()));
            bigLeaf(level, rand, startPosition.above(3).relative(randomDirection, 4), randomDirection);
        }
        if (a == 1)
        {
            ifAirSetBlock(level, startPosition.relative(randomDirection), STEM);
            ifAirSetBlock(level, startPosition.above().relative(randomDirection), STEM);
            ifAirSetBlock(level, startPosition.above(2).relative(randomDirection,2), STEM);
            ifAirSetBlock(level, startPosition.above(3).relative(randomDirection, 3), STEM.setValue(RotatedPillarBlock.AXIS, randomDirection.getAxis()));
            ifAirSetBlock(level, startPosition.above(3).relative(randomDirection, 4), STEM.setValue(RotatedPillarBlock.AXIS, randomDirection.getAxis()));
            bigLeaf(level, rand, startPosition.above(4).relative(randomDirection, 4), randomDirection);
        }
        if (a == 2)
        {
            ifAirSetBlock(level, startPosition.relative(randomDirection), STEM);
            ifAirSetBlock(level, startPosition.above().relative(randomDirection, 2), STEM.setValue(RotatedPillarBlock.AXIS, randomDirection.getAxis()));
            ifAirSetBlock(level, startPosition.above().relative(randomDirection, 3), STEM.setValue(RotatedPillarBlock.AXIS, randomDirection.getAxis()));
            smallLeaf(level, rand, startPosition.above(2).relative(randomDirection, 3), randomDirection);
        }
        if (a == 3)
        {
            ifAirSetBlock(level, startPosition.relative(randomDirection), STEM);
            ifAirSetBlock(level, startPosition.above().relative(randomDirection, 2), STEM);
            ifAirSetBlock(level, startPosition.above(2).relative(randomDirection, 2), STEM);
            ifAirSetBlock(level, startPosition.above(3).relative(randomDirection, 3), STEM);
            ifAirSetBlock(level, startPosition.above(4).relative(randomDirection, 4), STEM.setValue(RotatedPillarBlock.AXIS, randomDirection.getAxis()));
            ifAirSetBlock(level, startPosition.above(4).relative(randomDirection, 5), STEM.setValue(RotatedPillarBlock.AXIS, randomDirection.getAxis()));
            bigLeaf(level, rand, startPosition.above(5).relative(randomDirection, 5), randomDirection);
        }
    }

    private void bigLeaf(WorldGenLevel level, RandomSource rand, BlockPos startPosition, Direction randomDirection)
    {
        BlockState configuredLeafState = LEAF.setValue(HorizontalDirectionalBlock.FACING, randomDirection.getOpposite());
        ifAirSetBlock(level, startPosition, configuredLeafState);
        for (int i = 0; i < 5; i++)
        {
            ifAirSetBlock(level, startPosition.relative(randomDirection.getClockWise()).relative(randomDirection, i), configuredLeafState);
            ifAirSetBlock(level, startPosition.relative(randomDirection.getCounterClockWise()).relative(randomDirection, i), configuredLeafState);
        }
        ifAirSetBlock(level, startPosition.relative(randomDirection, 4), configuredLeafState);
        for (int i = 1; i < 4; i++)
        {
            ifAirSetBlock(level, startPosition.relative(randomDirection.getClockWise(),2).relative(randomDirection, i), configuredLeafState);
            ifAirSetBlock(level, startPosition.relative(randomDirection.getCounterClockWise(),2).relative(randomDirection, i), configuredLeafState);
        }
        for (int i = 1; i < 4; i++)
        {
            ifAirSetBlock(level, startPosition.relative(randomDirection, i), STEM.setValue(RotatedPillarBlock.AXIS, randomDirection.getAxis()));
        }
    }

    private void smallLeaf(WorldGenLevel level, RandomSource rand, BlockPos startPosition, Direction randomDirection)
    {
        BlockState configuredLeafState = LEAF.setValue(HorizontalDirectionalBlock.FACING, randomDirection.getOpposite());
        ifAirSetBlock(level, startPosition, configuredLeafState);
        ifAirSetBlock(level, startPosition.relative(randomDirection,1), STEM.setValue(RotatedPillarBlock.AXIS, randomDirection.getAxis()));
        ifAirSetBlock(level, startPosition.relative(randomDirection,2), STEM.setValue(RotatedPillarBlock.AXIS, randomDirection.getAxis()));
        ifAirSetBlock(level, startPosition.relative(randomDirection.getClockWise()).relative(randomDirection,1), configuredLeafState);
        ifAirSetBlock(level, startPosition.relative(randomDirection.getClockWise()).relative(randomDirection,2), configuredLeafState);
        ifAirSetBlock(level, startPosition.relative(randomDirection.getCounterClockWise()).relative(randomDirection,1), configuredLeafState);
        ifAirSetBlock(level, startPosition.relative(randomDirection.getCounterClockWise()).relative(randomDirection,2), configuredLeafState);
        ifAirSetBlock(level, startPosition.relative(randomDirection,3), configuredLeafState);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context)
    {
        BlockPos startPos = context.origin();

        // Moving down until it is on the ground
        while (startPos.getY() > 1 && isAirOrLeavesAt(context.level(), startPos)) startPos = startPos.below();

        if (!isGrassOrDirt(context.level(), startPos))
        {
            return false; // this tree is only allowed to grow on soil, but not on water or plant or other thing
        }

        ifAirSetBlock(context.level(), new BlockPos(startPos.getX(), startPos.getY() + 1, startPos.getZ()), STEM);

        ArrayList<Direction> dirs = Arrays.stream(Direction.values()).filter(direction -> direction.getAxis() != Direction.Axis.Y).collect(Collectors.toCollection(ArrayList::new));
        dirs.remove(context.random().nextInt(4));
        if (context.random().nextBoolean()) dirs.remove(context.random().nextInt(3));
        for (Direction direction : dirs)
        {
            branchStart(context.level(), context.random(), new BlockPos(startPos.getX(), startPos.getY() + 2, startPos.getZ()),direction);
        }

        return true;
    }

    public static boolean isAirOrLeavesAt(WorldGenLevel level, BlockPos pos)
    {
        return level.isEmptyBlock(pos) || level.getBlockState(pos).is(BlockTags.LEAVES);
    }

    private void ifAirSetBlock(WorldGenLevel level, BlockPos pos, BlockState blockState)
    {
        if (level.isEmptyBlock(pos)) setBlock(level, pos, blockState);
    }
}