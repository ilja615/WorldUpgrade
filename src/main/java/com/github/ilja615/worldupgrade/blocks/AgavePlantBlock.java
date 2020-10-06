package com.github.ilja615.worldupgrade.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BushBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;

public class AgavePlantBlock extends BushBlock implements IPlantable
{
    protected static final VoxelShape SHAPE = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D);

    public AgavePlantBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return SHAPE;
    }

    @Override
    public Block.OffsetType getOffsetType()
    {
        return Block.OffsetType.XYZ;
    }


//    @Override
//    protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
//        Block block = state.getBlock();
//        return block == Blocks.SAND || block == Blocks.RED_SAND || block == Blocks.COARSE_DIRT;
//    }


    @Override
    public PlantType getPlantType(IBlockReader world, BlockPos pos)
    {
        Block block = world.getBlockState(pos).getBlock();

        return PlantType.Desert;
    }
}
