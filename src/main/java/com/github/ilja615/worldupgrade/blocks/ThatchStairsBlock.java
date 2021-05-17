package com.github.ilja615.worldupgrade.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.StairsBlock;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class ThatchStairsBlock extends StairsBlock
{
    public static final IntegerProperty DECAY = IntegerProperty.create("decay", 0, 2);

    public ThatchStairsBlock(BlockState state, Properties properties)
    {
        super(state, properties);
    }
    public boolean ticksRandomly(BlockState state) {
        return state.get(DECAY) < 2;
    }
    @Override
    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random)
    {
        if (random.nextInt(12) == 0 && state.hasProperty(DECAY))
        {
            worldIn.setBlockState(pos, state.with(DECAY, Math.max(0, Math.min(state.get(DECAY) + 1, 2))), 11);
        }
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> p_206840_1_)
    {
        super.fillStateContainer(p_206840_1_);
        p_206840_1_.add(DECAY);
    }
}
