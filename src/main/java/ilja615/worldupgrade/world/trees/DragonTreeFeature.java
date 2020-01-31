package ilja615.worldupgrade.world.trees;


import com.mojang.datafixers.Dynamic;
import ilja615.worldupgrade.init.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;
import java.util.Set;
import java.util.function.Function;

public class DragonTreeFeature extends AbstractTreeFeature<NoFeatureConfig> {
    private static final BlockState TRUNK = ModBlocks.DRAGON_LOG.getDefaultState();
    private static final BlockState LEAF = ModBlocks.DRAGON_LEAVES.getDefaultState();
    private static final Direction[] DIRECTIONS_4 = {Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST};
    private final boolean useExtraRandomHeight;

    public DragonTreeFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> configIn, boolean doBlockNotifyIn, boolean extraRandomHeightIn) {
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
        int hight = rand.nextInt(4) + 3;
        if (startPosition.getY() >= 1 && startPosition.getY() + hight + 8 +1 <= worldIn.getMaxHeight())
        {
            for (int j = startPosition.getY() + 1; j <= startPosition.getY() + 1 + hight; ++j)
            {
                this.setLogState(changedBlocks, worldIn, new BlockPos(startPosition.getX(), j, startPosition.getZ()), TRUNK, p_208519_5_);
            }
        }

        // Make tree "foot"

        for (Direction direction : DIRECTIONS_4)
        {
            this.setLogState(changedBlocks, worldIn, new BlockPos(startPosition.getX(), startPosition.getY() + 1, startPosition.getZ()).offset(direction), TRUNK, p_208519_5_);
            if (rand.nextBoolean())
            {
                this.setLogState(changedBlocks, worldIn, new BlockPos(startPosition.getX(), startPosition.getY() + 2, startPosition.getZ()).offset(direction), TRUNK, p_208519_5_);
                if (rand.nextBoolean()) this.setLogState(changedBlocks, worldIn, new BlockPos(startPosition.getX(), startPosition.getY() + 3, startPosition.getZ()).offset(direction), TRUNK, p_208519_5_);
            }
        }


        //canopy leaf
        for (int ix = -5; ix <= 5; ++ix)
        {
            for (int iz = -5; iz <= 5; ++iz)
            {
                for (int iy = 0; iy <= 1; ++iy)
                {
                    if (ix*ix + iz*iz + 6*iy*iy <= 36)
                    {
                        placeLeafAt(changedBlocks, worldIn, startPosition.add(ix, 7+hight+iy,iz) , p_208519_5_);
                    }
                }
            }
        }

        // Make the branches that go to every side and connect main trunk with canopy leaf
        for (Direction direction : DIRECTIONS_4)
        {
            makeBranchAt(changedBlocks, worldIn, startPosition.up(hight), p_208519_5_, direction);
        }

        return true;
    }
    private void placeLeafAt(Set<BlockPos> changedBlocks, IWorldGenerationReader worldIn, BlockPos pos, MutableBoundingBox boundingBox) {
        if (isAirOrLeaves(worldIn, pos)) {
            setLogState(changedBlocks, worldIn, pos, LEAF, boundingBox);
        }
    }

    private void makeBranchAt(Set<BlockPos> changedBlocks, IWorldGenerationReader worldIn, BlockPos continuePos, MutableBoundingBox p_208519_5_, Direction direction)
    {
        for (int j = 1; j <= 6; ++j)
        {
            this.setLogState(changedBlocks, worldIn, new BlockPos(continuePos.getX(), continuePos.getY() + j +1, continuePos.getZ()).offset(direction, (int)Math.ceil(Math.pow(j,0.7D))), TRUNK, p_208519_5_);
        }
    }
}


