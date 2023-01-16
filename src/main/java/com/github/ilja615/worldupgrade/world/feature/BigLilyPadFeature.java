package com.github.ilja615.worldupgrade.world.feature;

import com.github.ilja615.worldupgrade.init.ModBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class BigLilyPadFeature extends Feature<NoneFeatureConfiguration>
{
    private static final BlockState STEM = ModBlocks.BIG_PLANT_STEM.get().defaultBlockState();
    private static final BlockState PAD = ModBlocks.LILY_PAD.get().defaultBlockState();
    private static final BlockState AIR = Blocks.AIR.defaultBlockState();
    private static final BlockState WATER = Blocks.WATER.defaultBlockState();

    public BigLilyPadFeature(Codec<NoneFeatureConfiguration> configurationCodec) {
        super(configurationCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) 
    {
        BlockPos positionIn = new BlockPos(context.origin().getX(), 63, context.origin().getZ());
        // Moving down until it is on the ground
        while (positionIn.getY() > 1 && isAirOrLeavesOrWaterAt(context.level(), positionIn)) positionIn = positionIn.below();

        if (!isGrassOrDirt(context.level(), positionIn)) {
            return false; // this lily is only allowed to grow on soil, but not on water or plant or other thing
        }
        if (positionIn.getY() > 59) {
            return false; // needs enough blocks of water
        }
        
        // lily stem
        BlockPos pos1 = positionIn;
        while (pos1.getY() < 62 + context.random().nextInt(2)) {
            ArrayList<Direction> dirs = Arrays.stream(Direction.values()).filter(direction -> direction.getAxis() != Direction.Axis.Y).collect(Collectors.toCollection(ArrayList::new));
            if (context.random().nextFloat() < 0.1f) pos1 = pos1.relative(dirs.get(context.random().nextInt(4)));
            ifAirOrWaterSetBlock(context.level(), pos1, STEM);
            pos1 = pos1.above();
        }

        // lily pad
        int range = 2 + context.random().nextInt(3);
        for (int ix = -range; ix <= range; ++ix) {
            for (int iy = -range; iy <= range; ++iy) {
                if (range == 2) {
                    if (ix * ix + iy * iy <= 6) {
                        ifAirOrWaterSetBlock(context.level(), pos1.offset(ix, 0, iy), PAD);
                    }
                } else {
                    if (ix * ix + iy * iy <= range * range * 1.15) {
                        ifAirOrWaterSetBlock(context.level(), pos1.offset(ix, 0, iy), PAD);
                    }
                }
            }
        }

        final int y1 = pos1.getY();

        // pac man
        ArrayList<Direction> dirs = Arrays.stream(Direction.values()).filter(direction -> direction.getAxis() != Direction.Axis.Y).collect(Collectors.toCollection(ArrayList::new));
        Direction pacmanDirection = dirs.get(context.random().nextInt(4));
        if (range == 2)
        {
            setBlock(context.level(), pos1.relative(pacmanDirection), y1 <= 62 ? WATER : AIR);
        }
        setBlock(context.level(), pos1.relative(pacmanDirection, 2), y1 <= 62 ? WATER : AIR);
        if (range == 3)
        {
            setBlock(context.level(), pos1.relative(pacmanDirection, 2).relative(pacmanDirection.getClockWise()), y1 <= 62 ? WATER : AIR);
            setBlock(context.level(), pos1.relative(pacmanDirection, 2).relative(pacmanDirection.getCounterClockWise()), y1 <= 62 ? WATER : AIR);
        }
        if (range > 2)
        {
            setBlock(context.level(), pos1.relative(pacmanDirection, 3), y1 <= 62 ? WATER : AIR);
            setBlock(context.level(), pos1.relative(pacmanDirection, 3).relative(pacmanDirection.getClockWise()), y1 <= 62 ? WATER : AIR);
            setBlock(context.level(), pos1.relative(pacmanDirection, 3).relative(pacmanDirection.getCounterClockWise()), y1 <= 62 ? WATER : AIR);
        }
        if (range == 4)
        {
            setBlock(context.level(), pos1.relative(pacmanDirection, 4), y1 <= 62 ? WATER : AIR);
            setBlock(context.level(), pos1.relative(pacmanDirection, 4).relative(pacmanDirection.getClockWise()), y1 <= 62 ? WATER : AIR);
            setBlock(context.level(), pos1.relative(pacmanDirection, 4).relative(pacmanDirection.getCounterClockWise()), y1 <= 62 ? WATER : AIR);
        }
        return true;
    }

    public static boolean isAirOrLeavesOrWaterAt(WorldGenLevel level, BlockPos pos)
    {
        return level.isStateAtPosition(pos, (state) ->
        {
            return state.isAir() || state.is(BlockTags.LEAVES) || state.getBlock() == Blocks.WATER || state.canBeReplaced();
        });
    }

    private void ifAirOrWaterSetBlock(WorldGenLevel level, BlockPos pos, BlockState blockState)
    {
        if(isAirOrLeavesOrWaterAt(level, pos))
            setBlock(level, pos, blockState);
    }
}