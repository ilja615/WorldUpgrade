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

public class DeadTreeFeature extends AbstractTreeFeature<NoFeatureConfig>
{
    private static final BlockState TRUNK = ModBlocks.DEAD_LOG.getDefaultState();
    private static final BlockState LEAF = ModBlocks.DEAD_LEAVES.getDefaultState();

    private final boolean useExtraRandomHeight;

    public DeadTreeFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> configIn, boolean doBlockNotifyIn, boolean extraRandomHeightIn)
    {
        super(configIn, doBlockNotifyIn);
        this.useExtraRandomHeight = extraRandomHeightIn;
        //this.setSapling((net.minecraftforge.common.IPlantable)Blocks.BIRCH_SAPLING);
    }

    @Override
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
}
