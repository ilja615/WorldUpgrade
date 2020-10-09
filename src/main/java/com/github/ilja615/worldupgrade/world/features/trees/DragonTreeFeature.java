//package com.github.ilja615.worldupgrade.world.features.trees;
//
//
//import com.github.ilja615.worldupgrade.blocks.CoarseSandBlock;
//import com.github.ilja615.worldupgrade.init.ModBlocks;
//import com.github.ilja615.worldupgrade.util.DirectionUtil;
//import com.mojang.datafixers.Dynamic;
//import net.minecraft.block.BlockState;
//import net.minecraft.block.LeavesBlock;
//import net.minecraft.util.Direction;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.util.math.MutableBoundingBox;
//import net.minecraft.world.gen.IWorldGenerationReader;
//import net.minecraft.world.gen.feature.AbstractTreeFeature;
//import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
//
//import java.util.Random;
//import java.util.Set;
//import java.util.function.Function;
//
//public class DragonTreeFeature extends AbstractTreeFeature<BaseTreeFeatureConfig>
//{
//    private static final BlockState TRUNK = ModBlocks.DRAGON_LOG.get().getDefaultState();
//    private static final BlockState LEAF = ModBlocks.DRAGON_LEAVES.get().getDefaultState().with(LeavesBlock.DISTANCE, 1);
//    //private final boolean useExtraRandomHeight;
//
//    public DragonTreeFeature(Function<Dynamic<?>, ? extends BaseTreeFeatureConfig> configIn, boolean extraRandomHeightIn)
//    {
//        super(configIn);
//        //this.useExtraRandomHeight = extraRandomHeightIn;
//        //this.setSapling((net.minecraftforge.common.IPlantable)Blocks.BIRCH_SAPLING);
//    }
//
//    @Override
//    protected boolean place(IWorldGenerationReader generationReader, Random rand, BlockPos positionIn, Set<BlockPos> p_225557_4_, Set<BlockPos> p_225557_5_, MutableBoundingBox boundingBoxIn, BaseTreeFeatureConfig configIn)
//    {
//        // Moving down until it is on the ground
//        while (positionIn.getY() > 1 && isAirOrLeaves(generationReader, positionIn)) positionIn = positionIn.down();
//
//        if (!isSoil(generationReader, positionIn, null) && !(generationReader.hasBlockState(positionIn, state -> state.getBlock() instanceof CoarseSandBlock)))
//        {
//            return false; // this tree is only allowed to grow on soil, but not on water or plant or other thing
//        }
//
//        // Make main trunk
//        int hight = rand.nextInt(4) * 2 + 3;
//        if (positionIn.getY() >= 1 && positionIn.getY() + hight + 8 + 1 <= generationReader.getMaxHeight())
//        {
//            for (int j = positionIn.getY() + 1; j <= positionIn.getY() + 1 + hight; ++j)
//            {
//                setBlockState(generationReader, new BlockPos(positionIn.getX(), j, positionIn.getZ()), TRUNK);
//            }
//        }
//
//        //canopy leaf
//        for (int ix = -5; ix <= 5; ++ix)
//        {
//            for (int iz = -5; iz <= 5; ++iz)
//            {
//                for (int iy = 1; iy <= 2; ++iy)
//                {
//                    if (ix * ix + iz * iz + 6 * iy * iy <= 34 + rand.nextInt(5) && ix * ix + iz * iz + 3 * iy * iy >= 8 + rand.nextInt(3))
//                    {
//                        setBlockState(generationReader, positionIn.add(ix, 4 + hight + iy, iz), LEAF);
//                    }
//                }
//            }
//        }
//
//        // Make the branches that go to every side and connect main trunk with canopy leaf
//        for (Direction direction : DirectionUtil.DIRECTIONS_4h)
//        {
//            setBlockState(generationReader, new BlockPos(positionIn.getX(), positionIn.up(hight).getY() + 2, positionIn.getZ()).offset(direction, 1), TRUNK);
//            setBlockState(generationReader, new BlockPos(positionIn.getX(), positionIn.up(hight).getY() + 3, positionIn.getZ()).offset(direction, 2), TRUNK);
//            setBlockState(generationReader, new BlockPos(positionIn.getX(), positionIn.up(hight).getY() + 3, positionIn.getZ()).offset(direction, 3), TRUNK);
//            setBlockState(generationReader, new BlockPos(positionIn.getX(), positionIn.up(hight).getY() + 4, positionIn.getZ()).offset(direction, 4).offset(DirectionUtil.getClockWise(direction)), TRUNK);
//            setBlockState(generationReader, new BlockPos(positionIn.getX(), positionIn.up(hight).getY() + 4, positionIn.getZ()).offset(direction, 4).offset(DirectionUtil.getCounterClockWise(direction)), TRUNK);
//            setBlockState(generationReader, new BlockPos(positionIn.getX(), positionIn.up(hight).getY() + 3, positionIn.getZ()).offset(direction, 2).offset(DirectionUtil.getCounterClockWise(direction)), TRUNK);
//            setBlockState(generationReader, new BlockPos(positionIn.getX(), positionIn.up(hight).getY() + 4, positionIn.getZ()).offset(direction, 2).offset(DirectionUtil.getCounterClockWise(direction), 2), TRUNK);
//            setBlockState(generationReader, new BlockPos(positionIn.getX(), positionIn.up(hight).getY() + 5, positionIn.getZ()).offset(direction, 2).offset(DirectionUtil.getCounterClockWise(direction), 2), TRUNK);
//        }
//
//        return true;
//    }
//}
//
//
