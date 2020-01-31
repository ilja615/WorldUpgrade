package ilja615.worldupgrade.world.trees;


import com.mojang.datafixers.Dynamic;
import ilja615.worldupgrade.init.ModBlocks;
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
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;
import java.util.Set;
import java.util.function.Function;

public class DeadTreeFeature extends AbstractTreeFeature<NoFeatureConfig> {
    private static final BlockState TRUNK = ModBlocks.DEAD_LOG.getDefaultState();
    private static final BlockState LEAF = ModBlocks.DEAD_LEAVES.getDefaultState();

    private final boolean useExtraRandomHeight;

    public DeadTreeFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> configIn, boolean doBlockNotifyIn, boolean extraRandomHeightIn) {
        super(configIn, doBlockNotifyIn);
        this.useExtraRandomHeight = extraRandomHeightIn;
        //this.setSapling((net.minecraftforge.common.IPlantable)Blocks.BIRCH_SAPLING);
    }

    @Override
    protected boolean place(Set<BlockPos> changedBlocks, IWorldGenerationReader worldIn, Random rand, BlockPos startPosition, MutableBoundingBox p_208519_5_) {

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
        int lchance = 0;
        if (startPosition.getY() >= 1 && startPosition.getY() + hight + hight2 + 1 <= worldIn.getMaxHeight())
        {
            for (int j = startPosition.getY() + 1; j <= startPosition.getY() + 1 + hight; ++j)
            {
                this.setLogState(changedBlocks, worldIn, new BlockPos(startPosition.getX(), j, startPosition.getZ()), TRUNK, p_208519_5_);

                // To spawn a leaf or branch.
                lchance = rand.nextInt(7);
                if (lchance == 0 || lchance == 1 || lchance == 2)
                    leafSpawn(changedBlocks, worldIn, rand, new BlockPos(startPosition.getX(), j, startPosition.getZ()), p_208519_5_);
                if (lchance == 3 || lchance == 4)
                    branchSpawn(changedBlocks, worldIn, rand, new BlockPos(startPosition.getX(), j, startPosition.getZ()), p_208519_5_);
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
            this.setLogState(changedBlocks, worldIn, new BlockPos(continuePos.getX(), j, continuePos.getZ()), TRUNK, p_208519_5_);

            // To spawn a leaf or branch.
            lchance = rand.nextInt(6);
            if (lchance == 0 || lchance == 1 || lchance == 2)
                leafSpawn(changedBlocks, worldIn, rand, new BlockPos(continuePos.getX(), j, continuePos.getZ()), p_208519_5_);
            if (lchance == 3)
                branchSpawn(changedBlocks, worldIn, rand, new BlockPos(continuePos.getX(), j, continuePos.getZ()), p_208519_5_);
        }

        // Top leaves
        this.setLogState(changedBlocks, worldIn, new BlockPos(continuePos.getX(), continuePos.getY() + hight2 + 1, continuePos.getZ()), LEAF, p_208519_5_);
        if (rand.nextBoolean() == true)
            this.setLogState(changedBlocks, worldIn, new BlockPos(continuePos.getX(), continuePos.getY() + hight2 + 2, continuePos.getZ()), LEAF, p_208519_5_);


        return true;
    }


    private void leafSpawn(Set<BlockPos> changedBlocks, IWorldGenerationReader worldIn, Random rand, BlockPos middle, MutableBoundingBox p_208519_5_)
    {
        int chance = rand.nextInt(4);
        if (chance == 0) {
            this.setLogState(changedBlocks, worldIn, middle.east(1), LEAF, p_208519_5_);
        }
        if (chance == 1) {
            this.setLogState(changedBlocks, worldIn, middle.west(1), LEAF, p_208519_5_);
        }
        if (chance == 2) {
            this.setLogState(changedBlocks, worldIn, middle.north(1), LEAF, p_208519_5_);
        }
        if (chance == 3) {
            this.setLogState(changedBlocks, worldIn, middle.south(1), LEAF, p_208519_5_);
        }
    }

    private void branchSpawn(Set<BlockPos> changedBlocks, IWorldGenerationReader worldIn, Random rand, BlockPos middle, MutableBoundingBox p_208519_5_)
    {
        int chance = rand.nextInt(4);
        if (chance == 0) {
            this.setLogState(changedBlocks, worldIn, middle.east(1), TRUNK.with(RotatedPillarBlock.AXIS, Direction.Axis.X), p_208519_5_);
            this.setLogState(changedBlocks, worldIn, middle.east(2), LEAF, p_208519_5_);
        }
        if (chance == 1) {
            this.setLogState(changedBlocks, worldIn, middle.west(1), TRUNK.with(RotatedPillarBlock.AXIS, Direction.Axis.X), p_208519_5_);
            this.setLogState(changedBlocks, worldIn, middle.west(2), LEAF, p_208519_5_);
        }
        if (chance == 2) {
            this.setLogState(changedBlocks, worldIn, middle.north(1), TRUNK.with(RotatedPillarBlock.AXIS, Direction.Axis.Z), p_208519_5_);
            this.setLogState(changedBlocks, worldIn, middle.north(2), LEAF, p_208519_5_);
        }
        if (chance == 3) {
            this.setLogState(changedBlocks, worldIn, middle.south(1), TRUNK.with(RotatedPillarBlock.AXIS, Direction.Axis.Z), p_208519_5_);
            this.setLogState(changedBlocks, worldIn, middle.south(2), LEAF, p_208519_5_);
        }
    }
}





    /*@Override
    public boolean place(Set<BlockPos> changedBlocks, IWorldGenerationReader worldIn, Random rand, BlockPos startPos, MutableBoundingBox p_208519_5_)
    {
        // Moving down until it is on the ground
        while (startPos.getY() > 1 && isAirOrLeaves(worldIn, startPos))
        {
            startPos = startPos.down();
        }

        // Randonmize the heights
        int hight1 = rand.nextInt(4) + 2;
        int hight2 = rand.nextInt(3) + 1;
        int leaveTopHight = rand.nextInt(4) + 1;

        // Pick a random direction for the trunk to bend to
        int directionChooserNumber = rand.nextInt(4);
        Direction direction = Direction.NORTH;
        if (directionChooserNumber == 0) direction = Direction.NORTH;
        if (directionChooserNumber == 1) direction = Direction.SOUTH;
        if (directionChooserNumber == 2) direction = Direction.EAST;
        if (directionChooserNumber == 3) direction = Direction.WEST;

        // The tree it starts 1 block above the ground
        BlockPos treeGroundPos = startPos.up();
        BlockPos currentPos = treeGroundPos;

        // Exits if the tree doesn't fit under the world hight limit
        if (!(currentPos.getY() >= 1 && currentPos.getY() + hight1 + hight2 + leaveTopHight + 1 <= worldIn.getMaxHeight()))
        {
            return false;
        }

        // Make trunk 1
        for (int i = treeGroundPos.getY(); i <= treeGroundPos.getY() + hight1 + 1; ++i)
        {
            currentPos = new BlockPos(treeGroundPos.getX(), startPos.getY() + 1 + i, startPos.getZ());
            if (isAirOrLeaves(worldIn, currentPos))
            {
                this.setLogState(changedBlocks, worldIn, currentPos, TRUNK, p_208519_5_);
            }
        }

        // Make trunk 2
        for (int i = startPos.getY() + hight1 + 1; i <= startPos.getY() + hight1 + hight2 + 1; ++i)
        {
            currentPos = new BlockPos(startPos.getX(), startPos.getY() + 1 + i, startPos.getZ()).offset(direction);
            if (isAirOrLeaves(worldIn, currentPos))
            {
                this.setLogState(changedBlocks, worldIn, currentPos, TRUNK, p_208519_5_);
            }
        }

        // Added some branches

        // Added leafs on the very top
        for (int i = startPos.getY() + hight1 + hight2 + 1; i <= startPos.getY() + hight1 + hight2 + leaveTopHight + 1; ++i)
        {
            currentPos = new BlockPos(startPos.getX(), startPos.getY() + 1 + i, startPos.getZ()).offset(direction);
            if (isAirOrLeaves(worldIn, currentPos))
            {
                this.setLogState(changedBlocks, worldIn, currentPos, LEAF, p_208519_5_);
            }
        }

        return true;
    }
}*/
