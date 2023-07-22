package com.github.ilja615.worldupgrade.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;

public class BromeliadBlock extends BushBlock
{
    public BromeliadBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter blockGetter, BlockPos pos) {
        return super.mayPlaceOn(state, blockGetter, pos) || state.is(BlockTags.LOGS);
    }
}
