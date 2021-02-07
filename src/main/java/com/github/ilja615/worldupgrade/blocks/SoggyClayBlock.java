package com.github.ilja615.worldupgrade.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.GrassBlock;
import net.minecraft.block.material.Material;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;

public class SoggyClayBlock extends Block
{
    public SoggyClayBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public boolean canSustainPlant(BlockState state, IBlockReader world, BlockPos pos, Direction facing, IPlantable plantable)
    {
        PlantType type = plantable.getPlantType(world, pos.up());

        if (PlantType.DESERT.equals(type) || PlantType.PLAINS.equals(type) || PlantType.CAVE.equals(type)) {
            return true;
        } else if (PlantType.NETHER.equals(type) || PlantType.WATER.equals(type) || PlantType.CROP.equals(type)) {
            return false;
        } else if (PlantType.BEACH.equals(type)) {
            return (world.getBlockState(pos.east()).getMaterial() == Material.WATER ||
                    world.getBlockState(pos.west()).getMaterial() == Material.WATER ||
                    world.getBlockState(pos.north()).getMaterial() == Material.WATER ||
                    world.getBlockState(pos.south()).getMaterial() == Material.WATER);
        }
        return false;
    }
}
