package com.github.ilja615.worldupgrade.world.feature;

import com.github.ilja615.worldupgrade.blocks.DoubleDryReedBlock;
import com.github.ilja615.worldupgrade.init.ModBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.Random;

public class DryReedsFeature extends Feature<NoneFeatureConfiguration>
{
    private static final BlockState BOTTOM_REED = ModBlocks.TALL_DRY_REED.get().defaultBlockState().setValue(DoubleDryReedBlock.HALF, DoubleBlockHalf.LOWER).setValue(DoubleDryReedBlock.ABOVE, true).setValue(DoubleDryReedBlock.WATERLOGGED, false);
    private static final BlockState MIDDLE_REED = ModBlocks.TALL_DRY_REED.get().defaultBlockState().setValue(DoubleDryReedBlock.HALF, DoubleBlockHalf.UPPER).setValue(DoubleDryReedBlock.ABOVE, true).setValue(DoubleDryReedBlock.WATERLOGGED, false);
    private static final BlockState TOP_REED_2 = ModBlocks.TALL_DRY_REED.get().defaultBlockState().setValue(DoubleDryReedBlock.HALF, DoubleBlockHalf.UPPER).setValue(DoubleDryReedBlock.ABOVE, false).setValue(DoubleDryReedBlock.WATERLOGGED, false);
    private static final BlockState TOP_REED_3 = ModBlocks.TOP_DRY_REED.get().defaultBlockState();

    public DryReedsFeature(Codec<NoneFeatureConfiguration> p_i231953_1_) {
        super(p_i231953_1_);
    }
    
    public static boolean isAirOrLeavesAt(WorldGenLevel level, BlockPos pos)
    {
        return level.isStateAtPosition(pos, (state) ->
        {
            return state.isAir() || state.is(BlockTags.LEAVES) || state.canBeReplaced();
        });
    }
    
    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context)
    {
        int currentWaterDepth = 0;
        BlockPos positionIn = new BlockPos(context.origin().getX(), 63, context.origin().getZ());
        // Moving down until it is on the ground
        while (positionIn.getY() > 1 && (isAirOrLeavesAt(context.level(), positionIn) || context.level().getBlockState(positionIn).is(Blocks.WATER)))
        {
            positionIn = positionIn.below();
            if (context.level().getBlockState(positionIn).is(Blocks.WATER)) currentWaterDepth++;
            if (currentWaterDepth > 1) return false;
        }

        if (!ModBlocks.TALL_REED.get().defaultBlockState().canSurvive(context.level(), positionIn))
        {
            return false; // to detect for a good ground to generate it on
        }

        if (context.level().getBlockState(positionIn.below()).getBlock() == ModBlocks.TOP_REED.get())
        {
            return false; // to detect for a good ground to generate it on
        }

        if (context.level().getBlockState(positionIn.below()).getBlock() == ModBlocks.TALL_REED.get())
        {
            return false; // to detect for a good ground to generate it on
        }

        boolean water = context.level().getFluidState(positionIn).is(FluidTags.WATER);

        int randomint = context.random().nextInt(10);
        if (randomint <= 6) //70% chance to get 3 hight
        {
            setBlock(context.level(), positionIn, BOTTOM_REED.setValue(DoubleDryReedBlock.WATERLOGGED, water));
            setBlock(context.level(), positionIn.above(1), MIDDLE_REED);
            setBlock(context.level(), positionIn.above(2), TOP_REED_3);

        } else
        { //30 % chance to get 2 hight
            setBlock(context.level(), positionIn, BOTTOM_REED);
            setBlock(context.level(), positionIn.above(1), TOP_REED_2);
        }
        return false;
    }
}
