package com.github.ilja615.worldupgrade.world.features;

import com.github.ilja615.worldupgrade.init.ModBlocks;
import com.github.ilja615.worldupgrade.util.DirectionUtil;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
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

public class BigFlowerFeature extends Feature<NoFeatureConfig>
{
    private static final BlockState STEM = ModBlocks.BIG_PLANT_STEM.get().getDefaultState();
    private static final BlockState LEAF = ModBlocks.BIG_PLANT_LEAF.get().getDefaultState();
    private static final BlockState[] FLOWERPETALBLOCKS = new BlockState[]{ModBlocks.FLOWERPETALBLOCK_LIGHTPINK.get().getDefaultState(),ModBlocks.FLOWERPETALBLOCK_LIGHTYELLOW.get().getDefaultState()};

    public BigFlowerFeature(Codec<NoFeatureConfig> p_i231953_1_) {
        super(p_i231953_1_);
    }

    @Override
    public boolean generate(ISeedReader worldIn, ChunkGenerator chunkGenerator, Random rand, BlockPos positionIn, NoFeatureConfig config)
    {
        //System.out.println("y"+positionIn.getY());
        positionIn = new BlockPos(positionIn.getX(), 128, positionIn.getZ());
        // Moving down until it is on the ground
        while (positionIn.getY() > 1 && isAirOrLeavesAt(worldIn, positionIn)) positionIn = positionIn.down();

        if (!isDirtAt(worldIn, positionIn))
        {
            System.out.println("false.");
            return false; // this tree is only allowed to grow on soil, but not on water or plant or other thing
        }
        //System.out.println("true. coord: "+positionIn.getCoordinatesAsString());
        int stemHight = rand.nextInt(4) + 5;
        BlockState FPB = FLOWERPETALBLOCKS[rand.nextInt(FLOWERPETALBLOCKS.length)];
        for (int i = 1; i < stemHight; i++)
            ifAirSetBlock(worldIn, new BlockPos(positionIn.getX(), positionIn.getY() + i, positionIn.getZ()), STEM);

        ifAirSetBlock(worldIn, new BlockPos(positionIn.getX(), positionIn.getY() + stemHight, positionIn.getZ()), FPB);
        for (Direction d : DirectionUtil.DIRECTIONS_4h)
        {
            ifAirSetBlock(worldIn, new BlockPos(positionIn.getX(), positionIn.getY() + stemHight, positionIn.getZ()).offset(d), FPB);
            ifAirSetBlock(worldIn, new BlockPos(positionIn.getX(), positionIn.getY() + stemHight + 1, positionIn.getZ()).offset(d), FPB);
            ifAirSetBlock(worldIn, new BlockPos(positionIn.getX(), positionIn.getY() + stemHight + 1, positionIn.getZ()).offset(d).offset(DirectionUtil.getClockWise(d)), FPB);
            ifAirSetBlock(worldIn, new BlockPos(positionIn.getX(), positionIn.getY() + stemHight + 2, positionIn.getZ()).offset(d).offset(DirectionUtil.getClockWise(d)), FPB);
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