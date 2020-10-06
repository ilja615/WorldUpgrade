package com.github.ilja615.worldupgrade.world.features;

import com.github.ilja615.worldupgrade.blocks.DoubleReedPlantBlock;
import com.github.ilja615.worldupgrade.init.ModBlocks;
import com.mojang.datafixers.Dynamic;
import net.minecraft.block.BlockState;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;
import java.util.function.Function;

public class ReedsFeature extends Feature<NoFeatureConfig>
{
    private static final BlockState BOTTOM_REED = ModBlocks.TALL_REED.get().getDefaultState().with(DoubleReedPlantBlock.HALF, DoubleBlockHalf.LOWER).with(DoubleReedPlantBlock.ABOVE, true).with(DoubleReedPlantBlock.WATERLOGGED, false);
    private static final BlockState BOTTOM_REED_W = ModBlocks.TALL_REED.get().getDefaultState().with(DoubleReedPlantBlock.HALF, DoubleBlockHalf.LOWER).with(DoubleReedPlantBlock.ABOVE, true).with(DoubleReedPlantBlock.WATERLOGGED, true);
    private static final BlockState MIDDLE_REED = ModBlocks.TALL_REED.get().getDefaultState().with(DoubleReedPlantBlock.HALF, DoubleBlockHalf.UPPER).with(DoubleReedPlantBlock.ABOVE, true).with(DoubleReedPlantBlock.WATERLOGGED, false);
    private static final BlockState TOP_REED_2 = ModBlocks.TALL_REED.get().getDefaultState().with(DoubleReedPlantBlock.HALF, DoubleBlockHalf.UPPER).with(DoubleReedPlantBlock.ABOVE, false).with(DoubleReedPlantBlock.WATERLOGGED, false);
    private static final BlockState TOP_REED_3 = ModBlocks.TOP_REED.get().getDefaultState();

    public ReedsFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> configIn)
    {
        super(configIn);
    }

    //

    @Override
    public boolean place(IWorld worldIn,                                                  /* Previously: p_212245_1_ */
                         ChunkGenerator<? extends GenerationSettings> chunkGenerator,    /* Previously: p_212245_2_ */
                         Random rand,                                                    /* Previously: p_212245_3_ */
                         BlockPos startPosition,                                         /* Previously: p_212245_4_ */
                         NoFeatureConfig config                                           /* Previously: p_212245_5_ */
    )
    {
        // Moving down until it is on the ground
        while (startPosition.getY() > 1 && AbstractTreeFeature.isAirOrLeaves(worldIn, startPosition))
            startPosition = startPosition.down();

        startPosition = startPosition.up();


        if (!ModBlocks.TALL_REED.get().getDefaultState().isValidPosition(worldIn, startPosition))
        {
            return false; // to detect for a good ground to generate it on
        }

        if (worldIn.getBlockState(startPosition.down()).getBlock() == ModBlocks.TOP_REED.get().getBlock())
        {
            return false; // to detect for a good ground to generate it on
        }

        if (worldIn.getBlockState(startPosition.down()).getBlock() == ModBlocks.TALL_REED.get().getBlock())
        {
            return false; // to detect for a good ground to generate it on
        }

        int randomint = rand.nextInt(10);
        if (randomint <= 6) //70% chance to get 3 hight
        {
            setBlockState(worldIn, startPosition, BOTTOM_REED);
            setBlockState(worldIn, startPosition.up(1), MIDDLE_REED);
            setBlockState(worldIn, startPosition.up(2), TOP_REED_3);

        } else
        { //30 % chance to get 2 hight

            setBlockState(worldIn, startPosition, BOTTOM_REED);
            setBlockState(worldIn, startPosition.up(1), TOP_REED_2);

        }
        return false;
    }
}
