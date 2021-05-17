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
    private static final BlockState STEM = ModBlocks.BIG_PLANT_STEM.get().defaultBlockState();
    private static final BlockState LEAF = ModBlocks.BIG_PLANT_LEAF.get().defaultBlockState();
    private static final BlockState[] FLOWERPETALBLOCKS = new BlockState[]{ModBlocks.FLOWERPETALBLOCK_LIGHTPINK.get().defaultBlockState(),ModBlocks.FLOWERPETALBLOCK_LIGHTYELLOW.get().defaultBlockState()};

    public BigFlowerFeature(Codec<NoFeatureConfig> p_i231953_1_) {
        super(p_i231953_1_);
    }

    @Override
    public boolean place(ISeedReader worldIn, ChunkGenerator chunkGenerator, Random rand, BlockPos positionIn, NoFeatureConfig config)
    {
        //System.out.println("y"+positionIn.getY());
        positionIn = new BlockPos(positionIn.getX(), 128, positionIn.getZ());
        // Moving down until it is on the ground
        while (positionIn.getY() > 1 && isAir(worldIn, positionIn)) positionIn = positionIn.below();

        if (!isGrassOrDirt(worldIn, positionIn))
        {
            System.out.println("false.");
            return false; // this tree is only allowed to grow on soil, but not on water or plant or other thing
        }
        int stemHight = rand.nextInt(3) + 6;
        BlockState FPB = FLOWERPETALBLOCKS[rand.nextInt(FLOWERPETALBLOCKS.length)];
        for (int i = 1; i < stemHight; i++)
            ifAirSetBlock(worldIn, new BlockPos(positionIn.getX(), positionIn.getY() + i, positionIn.getZ()), STEM);

        ifAirSetBlock(worldIn, new BlockPos(positionIn.getX(), positionIn.getY() + stemHight, positionIn.getZ()), FPB);
        for (Direction d : DirectionUtil.DIRECTIONS_4h)
        {
            ifAirSetBlock(worldIn, new BlockPos(positionIn.getX(), positionIn.getY() + stemHight, positionIn.getZ()).relative(d), FPB);
            ifAirSetBlock(worldIn, new BlockPos(positionIn.getX(), positionIn.getY() + stemHight + 1, positionIn.getZ()).relative(d), FPB);
            ifAirSetBlock(worldIn, new BlockPos(positionIn.getX(), positionIn.getY() + stemHight + 1, positionIn.getZ()).relative(d).relative(DirectionUtil.getClockWise(d)), FPB);
            ifAirSetBlock(worldIn, new BlockPos(positionIn.getX(), positionIn.getY() + stemHight + 2, positionIn.getZ()).relative(d).relative(DirectionUtil.getClockWise(d)), FPB);
        }
        Direction leafsDirection1 = DirectionUtil.DIRECTIONS_4h[rand.nextInt(4)];
        Direction leafsDirection2 = leafsDirection1.getOpposite();
        for (int i = 2; i < 5; i++)
        {
            ifAirSetBlock(worldIn, positionIn.relative(leafsDirection1).above(i), LEAF);
            ifAirSetBlock(worldIn, positionIn.relative(leafsDirection1,2).above(i+1), LEAF);
        }
        for (int i = 3; i < 5; i++)
        {
            ifAirSetBlock(worldIn, positionIn.relative(leafsDirection2).above(i), LEAF);
            ifAirSetBlock(worldIn, positionIn.relative(leafsDirection2,2).above(i+1), LEAF);
        }
        return true;
    }

    private void ifAirSetBlock(ISeedReader worldIn, BlockPos pos, BlockState blockState)
    {
        if (isAir(worldIn, pos)) setBlock(worldIn, pos, blockState);
    }
}