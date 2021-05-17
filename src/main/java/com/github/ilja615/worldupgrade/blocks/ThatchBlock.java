package com.github.ilja615.worldupgrade.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IGrowable;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class ThatchBlock extends Block
{
    public static final IntegerProperty DECAY = IntegerProperty.create("decay", 0, 2);

    public ThatchBlock(Properties properties)
    {
        super(properties);
    }

    public boolean ticksRandomly(BlockState state) {
        return state.get(DECAY) < 2;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random)
    {
        if (random.nextInt(12) == 0 && state.hasProperty(DECAY) && state.get(DECAY) < 2)
        {
            worldIn.setBlockState(pos, state.with(DECAY, state.get(DECAY) + 1), 3);
        }
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> p_206840_1_)
    {
        p_206840_1_.add(DECAY);
    }
}
