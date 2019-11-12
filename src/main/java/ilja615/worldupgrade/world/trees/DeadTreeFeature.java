package ilja615.worldupgrade.world.trees;

import com.mojang.datafixers.Dynamic;
import ilja615.worldupgrade.init.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
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
    protected boolean place(Set<BlockPos> changedBlocks, IWorldGenerationReader worldIn, Random rand, BlockPos startPosition, MutableBoundingBox p_208519_5_) {

        // First we down the canvas until we reache the ground
        while (startPosition.getY() > 1 && isAirOrLeaves(worldIn, startPosition)) startPosition = startPosition.down();

        // Then we paint the main trunk
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


        return false;
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
