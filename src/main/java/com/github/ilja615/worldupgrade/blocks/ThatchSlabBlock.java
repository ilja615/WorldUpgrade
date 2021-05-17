package com.github.ilja615.worldupgrade.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SlabBlock;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

import net.minecraft.block.AbstractBlock.Properties;

public class ThatchSlabBlock extends SlabBlock
{
    public static final IntegerProperty DECAY = IntegerProperty.create("decay", 0, 2);

    public ThatchSlabBlock(Properties properties)
    {
        super(properties);
    }
    public boolean isRandomlyTicking(BlockState state) {
        return state.getValue(DECAY) < 2;
    }
    @Override
    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random)
    {
        if (random.nextInt(12) == 0 && state.hasProperty(DECAY) && state.getValue(DECAY) < 2)
        {
            worldIn.setBlock(pos, state.setValue(DECAY, state.getValue(DECAY) + 1), 3);
        }
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> p_206840_1_)
    {
        super.createBlockStateDefinition(p_206840_1_);
        p_206840_1_.add(DECAY);
    }
}
