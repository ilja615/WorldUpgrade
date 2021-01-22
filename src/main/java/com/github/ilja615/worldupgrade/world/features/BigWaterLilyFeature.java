package com.github.ilja615.worldupgrade.world.features;

import com.github.ilja615.worldupgrade.init.ModBlocks;
import com.github.ilja615.worldupgrade.util.DirectionUtil;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
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
    private static final BlockState STEM = ModBlocks.LILY_STEM.get().getDefaultState();
    private static final BlockState PAD = ModBlocks.LILY_PAD.get().getDefaultState();
    private static final BlockState[] FLOWERPETALBLOCKS = new BlockState[]{ModBlocks.FLOWERPETALBLOCK_LIGHTPINK.get().getDefaultState(),ModBlocks.FLOWERPETALBLOCK_LIGHTYELLOW.get().getDefaultState()};

    public BigWaterLilyFeature(Codec<NoFeatureConfig> p_i231953_1_) {
        super(p_i231953_1_);
    }

    @Override
    public boolean generate(ISeedReader worldIn, ChunkGenerator chunkGenerator, Random rand, BlockPos positionIn, NoFeatureConfig config)
    {
        positionIn = new BlockPos(positionIn.getX(), 58, positionIn.getZ());
        // Moving down until it is on the ground
        while (positionIn.getY() > 1 && isAirOrLeavesAt(worldIn, positionIn)) positionIn = positionIn.down();

        if (!isDirtAt(worldIn, positionIn))
        {
            System.out.println("false.");
            return false; // this tree is only allowed to grow on soil, but not on water or plant or other thing
        }
        //System.out.println("true. coord: "+positionIn.getCoordinatesAsString());
        BlockPos pos1 = positionIn;
        for (int i = 1; i < 62+rand.nextInt(2); i++)
        {
            pos1 = pos1.up();
            if (rand.nextFloat() < 0.1f) pos1 = pos1.offset(DirectionUtil.DIRECTIONS_4h[rand.nextInt(4)]);
            ifAirSetBlock(worldIn, pos1, STEM);
        }

        pos1 = pos1.up();

        int range = 3 + rand.nextInt(3);
        for (int ix = -range; ix <= range; ++ix)
        {
            for (int iy = -range; iy <= range; ++iy)
            {
                if (ix * ix + iy * iy <= range * range)
                {
                    if (ix * ix + iy * iy >= range * range * 0.7)
                        ifAirSetBlock(worldIn, pos1.add(ix, 1, iy), PAD);
                    else
                        ifAirSetBlock(worldIn, pos1.add(ix, 0, iy), PAD);
                }
            }
        }

//        BlockState FPB = FLOWERPETALBLOCKS[rand.nextInt(FLOWERPETALBLOCKS.length)];

//        ifAirSetBlock(worldIn, new BlockPos(positionIn.getX(), positionIn.getY() + stemHight, positionIn.getZ()), FPB);
//        for (Direction d : DirectionUtil.DIRECTIONS_4h)
//        {
//            ifAirSetBlock(worldIn, new BlockPos(positionIn.getX(), positionIn.getY() + stemHight, positionIn.getZ()).offset(d), FPB);
//            ifAirSetBlock(worldIn, new BlockPos(positionIn.getX(), positionIn.getY() + stemHight + 1, positionIn.getZ()).offset(d), FPB);
//            ifAirSetBlock(worldIn, new BlockPos(positionIn.getX(), positionIn.getY() + stemHight + 1, positionIn.getZ()).offset(d).offset(DirectionUtil.getClockWise(d)), FPB);
//            ifAirSetBlock(worldIn, new BlockPos(positionIn.getX(), positionIn.getY() + stemHight + 2, positionIn.getZ()).offset(d).offset(DirectionUtil.getClockWise(d)), FPB);
//        }
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