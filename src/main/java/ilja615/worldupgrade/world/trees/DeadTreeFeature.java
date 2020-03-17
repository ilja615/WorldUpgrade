package ilja615.worldupgrade.world.trees;


import com.mojang.datafixers.Dynamic;
import ilja615.worldupgrade.init.ModBlocks;
import ilja615.worldupgrade.util.DirectionsUtilities;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.material.Material;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;

public class DeadTreeFeature extends AbstractTreeFeature<BaseTreeFeatureConfig> {
    private static final BlockState TRUNK = ModBlocks.DEAD_LOG.getDefaultState();
    private static final BlockState LEAF = ModBlocks.DEAD_LEAVES.getDefaultState();

    private final boolean useExtraRandomHeight;

    public DeadTreeFeature(Function<Dynamic<?>, ? extends BaseTreeFeatureConfig> configIn, boolean extraRandomHeightIn) {
        super(configIn);
        this.useExtraRandomHeight = extraRandomHeightIn;
        //this.setSapling((net.minecraftforge.common.IPlantable)Blocks.BIRCH_SAPLING);
    }


    private void leafSpawn(IWorldGenerationReader worldIn, Random rand, BlockPos middle)
    {
        Direction randomDirection = DirectionsUtilities.DIRECTIONS_4h[rand.nextInt(4)];
        setBlockState(worldIn, middle.offset(randomDirection), LEAF);
    }

    private void branchSpawn(IWorldGenerationReader worldIn, Random rand, BlockPos middle)
    {
        Direction randomDirection = DirectionsUtilities.DIRECTIONS_4h[rand.nextInt(4)];
        setBlockState(worldIn, middle.offset(randomDirection, 1), TRUNK.with(RotatedPillarBlock.AXIS, DirectionsUtilities.getDirectionAxisFromDirection(randomDirection)));
        setBlockState(worldIn, middle.offset(randomDirection, 2), LEAF);
    }

    private void longBranchSpawn(IWorldGenerationReader worldIn, Random rand, BlockPos middle)
    {
        Direction randomDirection = DirectionsUtilities.DIRECTIONS_4h[rand.nextInt(4)];
        setBlockState(worldIn, middle.offset(randomDirection, 1), TRUNK.with(RotatedPillarBlock.AXIS, DirectionsUtilities.getDirectionAxisFromDirection(randomDirection)));
        setBlockState(worldIn, middle.offset(randomDirection, 2), LEAF);
    }

    @Override
    protected boolean func_225557_a_(IWorldGenerationReader worldIn, Random rand, BlockPos startPosition, Set<BlockPos> posSet1, Set<BlockPos> posSet2, MutableBoundingBox p_225557_6_, BaseTreeFeatureConfig config) {

        // Moving down until it is on the ground
        while (startPosition.getY() > 1 && isAirOrLeaves(worldIn, startPosition)) startPosition = startPosition.down();

        if (!isSoil(worldIn, startPosition, null))
        {
            return false; // this tree is only allowed to grow on soil, but not on water or plant or other thing
        }
        // Make main trunk
        // Randonmize the heights
        int hight = rand.nextInt(3) + 2;
        int hight2 = rand.nextInt(2) + 2;
        float lchance = 0;
        if (startPosition.getY() >= 1 && startPosition.getY() + hight + hight2 + 1 <= worldIn.getMaxHeight())
        {
            for (int j = startPosition.getY() + 1; j <= startPosition.getY() + 1 + hight; ++j)
            {
                setBlockState(worldIn, new BlockPos(startPosition.getX(), j, startPosition.getZ()), TRUNK);

                // To spawn a leaf or branch.
                lchance = rand.nextFloat();
                if (lchance < 0.3)
                    leafSpawn(worldIn, rand, new BlockPos(startPosition.getX(), j, startPosition.getZ()));
                else if (lchance < 0.6)
                    branchSpawn(worldIn, rand, new BlockPos(startPosition.getX(), j, startPosition.getZ()));
                else if (lchance < 0.7)
                    longBranchSpawn(worldIn, rand, new BlockPos(startPosition.getX(), j, startPosition.getZ()));
            }
        }

        // Trunk continue
        BlockPos continuePos = startPosition.up(hight + 2);
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

        for (int j = continuePos.getY(); j <= continuePos.getY() + hight2; ++j)
        {
            setBlockState(worldIn, new BlockPos(continuePos.getX(), j, continuePos.getZ()), TRUNK);

            // To spawn a leaf or branch.
            lchance = rand.nextFloat();
            if (lchance < 0.4)
                leafSpawn(worldIn, rand, new BlockPos(startPosition.getX(), j, startPosition.getZ()));
            else if (lchance < 0.55)
                branchSpawn(worldIn, rand, new BlockPos(startPosition.getX(), j, startPosition.getZ()));
            else if (lchance < 0.6)
                longBranchSpawn(worldIn, rand, new BlockPos(startPosition.getX(), j, startPosition.getZ()));
        }

        // Top leaves
        setBlockState(worldIn, new BlockPos(continuePos.getX(), continuePos.getY() + hight2 + 1, continuePos.getZ()), LEAF);
        if (rand.nextBoolean() == true)
            setBlockState(worldIn, new BlockPos(continuePos.getX(), continuePos.getY() + hight2 + 2, continuePos.getZ()), LEAF);


        return true;
    }
}

