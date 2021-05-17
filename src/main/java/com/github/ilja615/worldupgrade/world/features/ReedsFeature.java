package com.github.ilja615.worldupgrade.world.features;

import com.github.ilja615.worldupgrade.blocks.DoubleReedPlantBlock;
import com.github.ilja615.worldupgrade.init.ModBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.IWorldGenerationBaseReader;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;
import java.util.function.Function;

public class ReedsFeature extends Feature<NoFeatureConfig>
{
    private static final BlockState BOTTOM_REED = ModBlocks.TALL_REED.get().defaultBlockState().setValue(DoubleReedPlantBlock.HALF, DoubleBlockHalf.LOWER).setValue(DoubleReedPlantBlock.ABOVE, true).setValue(DoubleReedPlantBlock.WATERLOGGED, false);
    private static final BlockState BOTTOM_REED_W = ModBlocks.TALL_REED.get().defaultBlockState().setValue(DoubleReedPlantBlock.HALF, DoubleBlockHalf.LOWER).setValue(DoubleReedPlantBlock.ABOVE, true).setValue(DoubleReedPlantBlock.WATERLOGGED, true);
    private static final BlockState MIDDLE_REED = ModBlocks.TALL_REED.get().defaultBlockState().setValue(DoubleReedPlantBlock.HALF, DoubleBlockHalf.UPPER).setValue(DoubleReedPlantBlock.ABOVE, true).setValue(DoubleReedPlantBlock.WATERLOGGED, false);
    private static final BlockState TOP_REED_2 = ModBlocks.TALL_REED.get().defaultBlockState().setValue(DoubleReedPlantBlock.HALF, DoubleBlockHalf.UPPER).setValue(DoubleReedPlantBlock.ABOVE, false).setValue(DoubleReedPlantBlock.WATERLOGGED, false);
    private static final BlockState TOP_REED_3 = ModBlocks.TOP_REED.get().defaultBlockState();

    public ReedsFeature(Codec<NoFeatureConfig> p_i231953_1_) {
        super(p_i231953_1_);
    }


    protected static boolean isAirOrLeaves(IWorldGenerationBaseReader worldIn, BlockPos pos)
    {
        if (!(worldIn instanceof net.minecraft.world.IWorldReader)) // FORGE: Redirect to state method when possible
            return worldIn.isStateAtPosition(pos, (p_214581_0_) -> p_214581_0_.isAir() || p_214581_0_.is(BlockTags.LEAVES));
        else
            return worldIn.isStateAtPosition(pos, state -> state.canBeReplacedByLeaves((net.minecraft.world.IWorldReader) worldIn, pos));
    }

    @Override
    public boolean place(ISeedReader worldIn, ChunkGenerator chunkGenerator, Random rand, BlockPos startPosition, NoFeatureConfig config)
    {
        // Moving down until it is on the ground
        while (startPosition.getY() > 1 && isAirOrLeaves(worldIn, startPosition))
            startPosition = startPosition.below();

        startPosition = startPosition.above();


        if (!ModBlocks.TALL_REED.get().defaultBlockState().canSurvive(worldIn, startPosition))
        {
            return false; // to detect for a good ground to generate it on
        }

        if (worldIn.getBlockState(startPosition.below()).getBlock() == ModBlocks.TOP_REED.get().getBlock())
        {
            return false; // to detect for a good ground to generate it on
        }

        if (worldIn.getBlockState(startPosition.below()).getBlock() == ModBlocks.TALL_REED.get().getBlock())
        {
            return false; // to detect for a good ground to generate it on
        }

        int randomint = rand.nextInt(10);
        if (randomint <= 6) //70% chance to get 3 hight
        {
            setBlock(worldIn, startPosition, BOTTOM_REED);
            setBlock(worldIn, startPosition.above(1), MIDDLE_REED);
            setBlock(worldIn, startPosition.above(2), TOP_REED_3);

        } else
        { //30 % chance to get 2 hight

            setBlock(worldIn, startPosition, BOTTOM_REED);
            setBlock(worldIn, startPosition.above(1), TOP_REED_2);

        }
        return false;
    }
}
