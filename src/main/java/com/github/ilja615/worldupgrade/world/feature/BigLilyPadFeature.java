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
import net.minecraft.world.level.material.Fluids;

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
        if (positionIn.getY() > 60) {
            return false; // needs enough blocks of water
        }
        
        // lily stem
        BlockPos pos1 = positionIn;
        ArrayList<BlockPos> stem = new ArrayList<>();
        while (pos1.getY() < 63) {
            ArrayList<Direction> dirs = Arrays.stream(Direction.values()).filter(direction -> direction.getAxis() != Direction.Axis.Y).collect(Collectors.toCollection(ArrayList::new));
            if (context.random().nextFloat() < 0.1f) pos1 = pos1.relative(dirs.get(context.random().nextInt(4)));
            if (context.level().getFluidState(pos1).is(Fluids.WATER)) // The stem should be completely in the water
            {
                stem.add(pos1);
            }
            pos1 = pos1.above();
        }

        // detection
        int range = 2 + context.random().nextInt(3);
        boolean overlap = false;
        for (int ix = -range-1; ix <= range+1; ++ix)
        {
            for (int iy = -range-1; iy <= range+1; ++iy)
            {
                if (ix * ix + iy * iy <= (range+1)*(range+1) * 1.15)
                {
                    if (!(context.level().getBlockState(pos1.offset(ix, 0, iy)).canBeReplaced() || context.level().getBlockState(pos1.offset(ix, 0, iy)).isAir()))
                        overlap = true;
                    if (!context.level().getFluidState(pos1.offset(ix, -1, iy)).is(Fluids.WATER))
                        return false; // The lily pad should be completely above the water, and not above land or air
                }
            }
        }
        if (overlap)
        {
            // Try if the smallest one fits
            range = 2;
            overlap = false;
            for (int ix = -range-1; ix <= range+1; ++ix)
            {
                for (int iy = -range-1; iy <= range+1; ++iy)
                {
                    if (ix * ix + iy * iy <= (range+1)*(range+1) * 1.15)
                    {
                        if (!(context.level().getBlockState(pos1.offset(ix, 0, iy)).canBeReplaced() || context.level().getBlockState(pos1.offset(ix, 0, iy)).isAir()))
                            overlap = true;
                        if (!context.level().getFluidState(pos1.offset(ix, -1, iy)).is(Fluids.WATER))
                            return false; // The lily pad should be completely above the water, and not above land or air
                    }
                }
            }
            if (overlap)
            {
                // If there is no space for the lily pad, placement should be cancelled.
                return false;
            }
        }

        // if there was no problem with the placement, time to place the stem and the lily pad.
        for (BlockPos stempos : stem)
            setBlock(context.level(), stempos, STEM);
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
        setBlock(context.level(), pos1.relative(pacmanDirection), y1 <= 62 ? WATER : AIR);
        setBlock(context.level(), pos1.relative(pacmanDirection, 2), y1 <= 62 ? WATER : AIR);
        if (range >= 3)
        {
            setBlock(context.level(), pos1.relative(pacmanDirection, 3), y1 <= 62 ? WATER : AIR);
        }
        if (range >= 4)
        {
            setBlock(context.level(), pos1.relative(pacmanDirection, 4), y1 <= 62 ? WATER : AIR);
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