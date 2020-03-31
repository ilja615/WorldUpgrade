package ilja615.worldupgrade.world.trees;


import com.mojang.datafixers.Dynamic;
import ilja615.worldupgrade.init.ModBlocks;
import ilja615.worldupgrade.init.ModBlocksNew;
import net.minecraft.block.BlockState;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;
import java.util.Set;
import java.util.function.Function;

public class DragonTreeFeature extends AbstractTreeFeature<BaseTreeFeatureConfig> {
    private static final BlockState TRUNK = ModBlocksNew.DRAGON_LOG.get().getDefaultState();
    private static final BlockState LEAF = ModBlocksNew.DRAGON_LEAVES.get().getDefaultState();
    private static final Direction[] DIRECTIONS_4 = {Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST};
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
        int hight = rand.nextInt(4) + 3;
        if (startPosition.getY() >= 1 && startPosition.getY() + hight + 8 +1 <= worldIn.getMaxHeight())
        {
            for (int j = startPosition.getY() + 1; j <= startPosition.getY() + 1 + hight; ++j)
            {
                setBlockState(worldIn, new BlockPos(startPosition.getX(), j, startPosition.getZ()), TRUNK);
            }
        }

        // Make tree "foot"

        for (Direction direction : DIRECTIONS_4)
        {
            setBlockState(worldIn, new BlockPos(startPosition.getX(), startPosition.getY() + 1, startPosition.getZ()).offset(direction), TRUNK);
            if (rand.nextBoolean())
            {
                setBlockState(worldIn, new BlockPos(startPosition.getX(), startPosition.getY() + 2, startPosition.getZ()).offset(direction), TRUNK);

                if (rand.nextBoolean()) setBlockState(worldIn, new BlockPos(startPosition.getX(), startPosition.getY() + 3, startPosition.getZ()).offset(direction), TRUNK);

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
                        setBlockState(worldIn, startPosition.add(ix, 7+hight+iy,iz), LEAF);
                    }
                }
            }
        }

        // Make the branches that go to every side and connect main trunk with canopy leaf
        for (Direction direction : DIRECTIONS_4)
        {
            for (int j = 1; j <= 6; ++j)
            {
                setBlockState(worldIn, new BlockPos(startPosition.getX(), startPosition.up(hight).getY() + j +1, startPosition.getZ()).offset(direction, (int)Math.ceil(Math.pow(j,0.7D))), TRUNK);
            }
        }

        return true;
    }
}


