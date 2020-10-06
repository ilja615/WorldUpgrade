package com.github.ilja615.worldupgrade.world.features.trees;


import com.github.ilja615.worldupgrade.init.ModBlocks;
import com.github.ilja615.worldupgrade.util.DirectionUtil;
import com.mojang.datafixers.Dynamic;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;

public class DeadTreeFeature extends AbstractTreeFeature<BaseTreeFeatureConfig>
{
    private static final BlockState TRUNK = ModBlocks.DEAD_LOG.get().getDefaultState();
    private static final BlockState LEAF = ModBlocks.DEAD_LEAVES.get().getDefaultState().with(LeavesBlock.DISTANCE, 1);

    //private final boolean useExtraRandomHeight;

    public DeadTreeFeature(Function<Dynamic<?>, ? extends BaseTreeFeatureConfig> configIn/*, boolean extraRandomHeightIn*/)
    {
        super(configIn);
        //this.useExtraRandomHeight = extraRandomHeightIn;
        //this.setSapling((net.minecraftforge.common.IPlantable)Blocks.BIRCH_SAPLING);
    }

    private void leafSpawn(IWorldGenerationReader worldIn, Random rand, BlockPos middle)
    {
        Direction randomDirection = DirectionUtil.DIRECTIONS_4h[rand.nextInt(4)];
        ifAirSetBlock(worldIn, middle.offset(randomDirection), LEAF);
    }

    private void branchSpawn(IWorldGenerationReader worldIn, Random rand, BlockPos middle)
    {
        Direction randomDirection = DirectionUtil.DIRECTIONS_4h[rand.nextInt(4)];
        if (rand.nextBoolean())
        {
            ifAirSetBlock(worldIn, middle.offset(randomDirection, 1), TRUNK.with(RotatedPillarBlock.AXIS, randomDirection.getAxis()));
            ifAirSetBlock(worldIn, middle.offset(randomDirection, 2), LEAF);
        } else
        {
            ifAirSetBlock(worldIn, middle.offset(randomDirection, 1), TRUNK.with(RotatedPillarBlock.AXIS, randomDirection.getAxis()));
            ifAirSetBlock(worldIn, middle.offset(randomDirection, 2), TRUNK.with(RotatedPillarBlock.AXIS, randomDirection.getAxis()));
            ifAirSetBlock(worldIn, middle.offset(randomDirection, 3), LEAF);
        }
    }

    private void longBranchSpawn(IWorldGenerationReader worldIn, Random rand, BlockPos startPosition, Direction randomDirection)
    {

        startPosition = startPosition.offset(randomDirection);
        for (int i = 1; i <= 4 + rand.nextInt(3); i++)
        {
            ifAirSetBlock(worldIn, startPosition, TRUNK.with(RotatedPillarBlock.AXIS, randomDirection.getAxis()));
            startPosition = startPosition.offset(randomDirection);

            Direction addedDirection = rand.nextBoolean() ? Direction.UP : Direction.DOWN;
            if (rand.nextInt(3) == 0) startPosition = startPosition.offset(addedDirection);

            int ejdnekjnwlkjnfw = rand.nextInt(3);
            if (ejdnekjnwlkjnfw == 0)
            {
                ifAirSetBlock(worldIn, startPosition.offset(DirectionUtil.getClockWise(randomDirection)), LEAF);
            }
            if (ejdnekjnwlkjnfw == 0)
            {
                ifAirSetBlock(worldIn, startPosition.offset(DirectionUtil.getCounterClockWise(randomDirection)), LEAF);
            }
        }
        ifAirSetBlock(worldIn, startPosition, LEAF);
    }

    @Override
    protected boolean place(IWorldGenerationReader generationReader, Random rand, BlockPos positionIn, Set<BlockPos> p_225557_4_, Set<BlockPos> p_225557_5_, MutableBoundingBox boundingBoxIn, BaseTreeFeatureConfig configIn)
    {
        // Moving down until it is on the ground
        while (positionIn.getY() > 1 && isAirOrLeaves(generationReader, positionIn)) positionIn = positionIn.down();

        if (!isSoil(generationReader, positionIn, null))
        {
            return false; // this tree is only allowed to grow on soil, but not on water or plant or other thing
        }
        // Make main trunk
        // Randomize the heights
        int hight = rand.nextInt(3) + 3;
        int hight2 = rand.nextInt(3) + 2;
        float lchance;
        if (positionIn.getY() >= 1 && positionIn.getY() + hight + hight2 + 1 <= generationReader.getMaxHeight())
        {
            for (int j = positionIn.getY() + 1; j <= positionIn.getY() + 1 + hight; ++j)
            {
                ifAirSetBlock(generationReader, new BlockPos(positionIn.getX(), j, positionIn.getZ()), TRUNK);

                // To spawn a leaf or branch.
                lchance = 1.0f;
                if (j > positionIn.getY() + 1)
                    lchance = rand.nextFloat(); // leaf or branch can not spawn on very bottom
                if (lchance < 0.3)
                    leafSpawn(generationReader, rand, new BlockPos(positionIn.getX(), j, positionIn.getZ()));
                else if (lchance < 0.6)
                    branchSpawn(generationReader, rand, new BlockPos(positionIn.getX(), j, positionIn.getZ()));
            }
        }

        // Trunk continue
        BlockPos continuePos = positionIn.up(hight + 2);
        int chance = rand.nextInt(4);
        int chance2 = rand.nextInt(3);
        chance += chance2 + 1;
        chance %= 4;
        if (chance == 0)
            continuePos = continuePos.east();
        if (chance == 1)
            continuePos = continuePos.west();
        if (chance == 2)
            continuePos = continuePos.north();
        if (chance == 3)
            continuePos = continuePos.south();

        ArrayList<Direction> directionArrayList = new ArrayList<>(Arrays.asList(Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST));


        for (int j = continuePos.getY(); j <= continuePos.getY() + hight2; ++j)
        {
            ifAirSetBlock(generationReader, new BlockPos(continuePos.getX(), j, continuePos.getZ()), TRUNK);
            Direction d = null;
            if (directionArrayList.size() > 0)
            {
                int c = rand.nextInt(directionArrayList.size());
                d = directionArrayList.get(c);
                directionArrayList.remove(c);
            }

            // To spawn a leaf or branch.
            lchance = rand.nextFloat();
            if (lchance < 0.4)
                leafSpawn(generationReader, rand, new BlockPos(continuePos.getX(), j, continuePos.getZ()));
            else if (lchance < 0.55)
                branchSpawn(generationReader, rand, new BlockPos(continuePos.getX(), j, continuePos.getZ()));
            else if (lchance < 0.7 && d != null)
                longBranchSpawn(generationReader, rand, new BlockPos(continuePos.getX(), j, continuePos.getZ()), d);
        }

        // Top leaves
        ifAirSetBlock(generationReader, new BlockPos(continuePos.getX(), continuePos.getY() + hight2 + 1, continuePos.getZ()), LEAF);
        if (rand.nextBoolean())
        {
            ifAirSetBlock(generationReader, new BlockPos(continuePos.getX(), continuePos.getY() + hight2 + 2, continuePos.getZ()), LEAF);
        }

        return true;
    }

    private void ifAirSetBlock(IWorldGenerationReader worldIn, BlockPos pos, BlockState blockState)
    {
        if (isAir(worldIn, pos)) setBlockState(worldIn, pos, blockState);
    }
}

