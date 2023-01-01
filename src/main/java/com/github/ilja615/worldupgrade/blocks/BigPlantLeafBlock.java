package com.github.ilja615.worldupgrade.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import org.jetbrains.annotations.Nullable;

public class BigPlantLeafBlock extends Block
{
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public BigPlantLeafBlock(Properties properties)
    {
        super(properties);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext p_49820_)
    {
        return super.getStateForPlacement(p_49820_);
    }

    @Override
    public BlockState rotate(BlockState state, LevelAccessor level, BlockPos pos, Rotation direction)
    {
        return super.rotate(state, level, pos, direction);
    }

    @Override
    public BlockState mirror(BlockState p_60528_, Mirror p_60529_)
    {
        return super.mirror(p_60528_, p_60529_);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        builder.add(FACING);
    }
}