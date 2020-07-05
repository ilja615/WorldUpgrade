package ilja615.worldupgrade.world.trees;


import com.mojang.datafixers.Dynamic;
import ilja615.worldupgrade.init.ModBlocks;
import ilja615.worldupgrade.util.DirectionsUtilities;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;

import java.util.Random;
import java.util.Set;
import java.util.function.Function;

public class DragonTreeFeature extends AbstractTreeFeature<BaseTreeFeatureConfig> {
    private static final BlockState TRUNK = ModBlocks.DRAGON_LOG.get().getDefaultState();
    private static final BlockState LEAF = ModBlocks.DRAGON_LEAVES.get().getDefaultState().with(LeavesBlock.DISTANCE, 1);
    private final boolean useExtraRandomHeight;

    public DragonTreeFeature(Function<Dynamic<?>, ? extends BaseTreeFeatureConfig> configIn, boolean extraRandomHeightIn) {
        super(configIn);
        this.useExtraRandomHeight = extraRandomHeightIn;
        //this.setSapling((net.minecraftforge.common.IPlantable)Blocks.BIRCH_SAPLING);
    }

    @Override
    protected boolean func_225557_a_(IWorldGenerationReader worldIn, Random rand, BlockPos startPosition, Set<BlockPos> p_225557_4_, Set<BlockPos> p_225557_5_, MutableBoundingBox p_225557_6_, BaseTreeFeatureConfig p_225557_7_) {

        // Moving down until it is on the ground
        while (startPosition.getY() > 1 && isAirOrLeaves(worldIn, startPosition)) startPosition = startPosition.down();

        if (!isSoil(worldIn, startPosition, null))
        {
            return false; // this tree is only allowed to grow on soil, but not on water or plant or other thing
        }
// Make main trunk
        int hight = rand.nextInt(3) + 3;
        if (startPosition.getY() >= 1 && startPosition.getY() + hight + 8 +1 <= worldIn.getMaxHeight())
        {
            for (int j = startPosition.getY() + 1; j <= startPosition.getY() + 1 + hight; ++j)
            {
                setBlockState(worldIn, new BlockPos(startPosition.getX(), j, startPosition.getZ()), TRUNK);
            }
        }


        //canopy leaf
        for (int ix = -5; ix <= 5; ++ix)
        {
            for (int iz = -5; iz <= 5; ++iz)
            {
                for (int iy = 0; iy <= 2; ++iy)
                {
                    if (ix*ix + iz*iz + 6*iy*iy <= 36)
                    {
                        setBlockState(worldIn, startPosition.add(ix, 5+hight+iy,iz), LEAF);
                    }
                }
            }
        }

        // Make the branches that go to every side and connect main trunk with canopy leaf
        for (Direction direction : DirectionsUtilities.DIRECTIONS_4h)
        {
            setBlockState(worldIn, new BlockPos(startPosition.getX(), startPosition.up(hight).getY() + 2, startPosition.getZ()).offset(direction, 1), TRUNK);
            setBlockState(worldIn, new BlockPos(startPosition.getX(), startPosition.up(hight).getY() + 3, startPosition.getZ()).offset(direction, 2), TRUNK);
            setBlockState(worldIn, new BlockPos(startPosition.getX(), startPosition.up(hight).getY() + 3, startPosition.getZ()).offset(direction, 3), TRUNK);
            setBlockState(worldIn, new BlockPos(startPosition.getX(), startPosition.up(hight).getY() + 4, startPosition.getZ()).offset(direction, 4).offset(DirectionsUtilities.getClockWise(direction)), TRUNK);
            setBlockState(worldIn, new BlockPos(startPosition.getX(), startPosition.up(hight).getY() + 4, startPosition.getZ()).offset(direction, 4).offset(DirectionsUtilities.getCounterClockWise(direction)), TRUNK);
            setBlockState(worldIn, new BlockPos(startPosition.getX(), startPosition.up(hight).getY() + 3, startPosition.getZ()).offset(direction, 2).offset(DirectionsUtilities.getCounterClockWise(direction)), TRUNK);
            setBlockState(worldIn, new BlockPos(startPosition.getX(), startPosition.up(hight).getY() + 4, startPosition.getZ()).offset(direction, 2).offset(DirectionsUtilities.getCounterClockWise(direction), 2), TRUNK);
        }

        return true;
    }
}


