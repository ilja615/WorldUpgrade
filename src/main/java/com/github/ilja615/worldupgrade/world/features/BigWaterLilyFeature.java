package com.github.ilja615.worldupgrade.world.features;

import com.github.ilja615.worldupgrade.init.ModBlocks;
import com.github.ilja615.worldupgrade.util.DirectionUtil;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.IWorldGenerationBaseReader;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

public class BigWaterLilyFeature extends Feature<NoFeatureConfig>
{
    private static final BlockState STEM = ModBlocks.LILY_STEM.get().defaultBlockState();
    private static final BlockState PAD = ModBlocks.LILY_PAD.get().defaultBlockState();
    private static final BlockState[] FLOWERPETALBLOCKS = new BlockState[]{ModBlocks.FLOWERPETALBLOCK_LIGHTPINK.get().defaultBlockState(),ModBlocks.FLOWERPETALBLOCK_LIGHTYELLOW.get().defaultBlockState()};
    private static final BlockState AIR = Blocks.AIR.defaultBlockState();
    private static final BlockState WATER = Blocks.WATER.defaultBlockState();

    public BigWaterLilyFeature(Codec<NoFeatureConfig> p_i231953_1_) {
        super(p_i231953_1_);
    }

    @Override
    public boolean place(ISeedReader worldIn, ChunkGenerator chunkGenerator, Random rand, BlockPos positionIn, NoFeatureConfig config) {
        positionIn = new BlockPos(positionIn.getX(), 63, positionIn.getZ());
        // Moving down until it is on the ground
        while (positionIn.getY() > 1 && isAirOrLeavesOrWaterAt(worldIn, positionIn)) positionIn = positionIn.below();

        if (!isGrassOrDirt(worldIn, positionIn)) {
            return false; // this lily is only allowed to grow on soil, but not on water or plant or other thing
        }
        if (positionIn.getY() > 59) {
            return false; // needs a few blocks of water
        }
        // lily stem
        BlockPos pos1 = positionIn;
        while (pos1.getY() < 62 + rand.nextInt(2)) {
            if (rand.nextFloat() < 0.1f) pos1 = pos1.relative(DirectionUtil.DIRECTIONS_4h[rand.nextInt(4)]);
            ifAirOrWaterSetBlock(worldIn, pos1, STEM);
            pos1 = pos1.above();
        }

        // lily pad
        int range = 2 + rand.nextInt(3);
        for (int ix = -range; ix <= range; ++ix) {
            for (int iy = -range; iy <= range; ++iy) {
                if (range == 2) {
                    if (ix * ix + iy * iy <= 6) {
                        if (ix * ix + iy * iy >= 4)
                            ifAirOrWaterSetBlock(worldIn, pos1.offset(ix, 1, iy), PAD);
                        else
                            ifAirOrWaterSetBlock(worldIn, pos1.offset(ix, 0, iy), PAD);
                    }
                } else {
                    if (ix * ix + iy * iy <= range * range * 1.15) {
                        if (ix * ix + iy * iy >= range * range * 0.8)
                            ifAirOrWaterSetBlock(worldIn, pos1.offset(ix, 1, iy), PAD);
                        else
                            ifAirOrWaterSetBlock(worldIn, pos1.offset(ix, 0, iy), PAD);
                    }
                }
            }
        }

        final int y1 = pos1.getY();

        // pac man
        Direction pacmanDirection = DirectionUtil.DIRECTIONS_4h[rand.nextInt(4)];
        if (range == 2)
        {
            worldIn.setBlock(pos1.relative(pacmanDirection), y1 == 62 ? WATER : AIR, 3);
            worldIn.setBlock(pos1.relative(pacmanDirection, 2).above(), AIR, 3);
        }
        if (range == 3)
        {
            worldIn.setBlock(pos1.relative(pacmanDirection, 1), y1 == 62 ? WATER : AIR, 3);
            worldIn.setBlock(pos1.relative(pacmanDirection, 2), y1 == 62 ? WATER : AIR, 3);
            worldIn.setBlock(pos1.relative(pacmanDirection, 2).relative(DirectionUtil.getClockWise(pacmanDirection)), y1 == 62 ? WATER : AIR, 3);
            worldIn.setBlock(pos1.relative(pacmanDirection, 2).relative(DirectionUtil.getCounterClockWise(pacmanDirection)), y1 == 62 ? WATER : AIR, 3);
            worldIn.setBlock(pos1.relative(pacmanDirection, 3).above(), AIR, 3);
            worldIn.setBlock(pos1.relative(pacmanDirection, 3).above().relative(DirectionUtil.getClockWise(pacmanDirection)), AIR, 3);
            worldIn.setBlock(pos1.relative(pacmanDirection, 3).above().relative(DirectionUtil.getCounterClockWise(pacmanDirection)), AIR, 3);
        }
        if (range == 4)
        {
            worldIn.setBlock(pos1.relative(pacmanDirection, 2), y1 == 62 ? WATER : AIR, 3);
            worldIn.setBlock(pos1.relative(pacmanDirection, 3), y1 == 62 ? WATER : AIR, 3);
            worldIn.setBlock(pos1.relative(pacmanDirection, 3).relative(DirectionUtil.getClockWise(pacmanDirection)), y1 == 62 ? WATER : AIR, 3);
            worldIn.setBlock(pos1.relative(pacmanDirection, 3).relative(DirectionUtil.getCounterClockWise(pacmanDirection)), y1 == 62 ? WATER : AIR, 3);
            worldIn.setBlock(pos1.relative(pacmanDirection, 4).above(), AIR, 3);
            worldIn.setBlock(pos1.relative(pacmanDirection, 4).above().relative(DirectionUtil.getClockWise(pacmanDirection)), AIR, 3);
            worldIn.setBlock(pos1.relative(pacmanDirection, 4).above().relative(DirectionUtil.getCounterClockWise(pacmanDirection)), AIR, 3);
        }

        // lotus flower
        if (rand.nextInt(5) == 0 && range != 2) {
            BlockState FPB = FLOWERPETALBLOCKS[rand.nextInt(FLOWERPETALBLOCKS.length)];
            ifAirOrWaterSetBlock(worldIn, new BlockPos(pos1.getX(), y1+1, pos1.getZ()), FPB);
            for (Direction d : DirectionUtil.DIRECTIONS_4h) {
                ifAirOrWaterSetBlock(worldIn, new BlockPos(pos1.getX(), y1 + 1, pos1.getZ()).relative(d), FPB);
                ifAirOrWaterSetBlock(worldIn, new BlockPos(pos1.getX(), y1 + 2, pos1.getZ()).relative(d), FPB);
                ifAirOrWaterSetBlock(worldIn, new BlockPos(pos1.getX(), y1 + 2, pos1.getZ()).relative(d).relative(DirectionUtil.getClockWise(d)), FPB);
                ifAirOrWaterSetBlock(worldIn, new BlockPos(pos1.getX(), y1 + 3, pos1.getZ()).relative(d).relative(DirectionUtil.getClockWise(d)), FPB);
            }
        }
        return true;
    }

    public static boolean isAirOrLeavesOrWaterAt(IWorldGenerationBaseReader p_236412_0_, BlockPos p_236412_1_)
    {
        return p_236412_0_.isStateAtPosition(p_236412_1_, (p_236411_0_) ->
        {
            return p_236411_0_.isAir() || p_236411_0_.is(BlockTags.LEAVES) || p_236411_0_.getBlock() == Blocks.WATER;
        });
    }

    private void ifAirOrWaterSetBlock(ISeedReader worldIn, BlockPos pos, BlockState blockState)
    {
        if (isAir(worldIn, pos) || worldIn.getBlockState(pos).getBlock() == Blocks.WATER || worldIn.getBlockState(pos).getBlock() == Blocks.LILY_PAD || worldIn.getBlockState(pos).getBlock() == Blocks.SEAGRASS || worldIn.getBlockState(pos).getBlock() == Blocks.TALL_SEAGRASS)
            setBlock(worldIn, pos, blockState);
    }
}