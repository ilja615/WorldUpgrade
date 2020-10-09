package com.github.ilja615.worldupgrade.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.FallingBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;

public class GravelBlock extends FallingBlock
{
    private final int dustColor;

    public GravelBlock(int dustColor, Properties properties)
    {
        super(properties);

        this.dustColor = dustColor;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public int getDustColor(BlockState state, IBlockReader reader, BlockPos pos) {
        return this.dustColor;
    }

    @Override
    public ToolType getHarvestTool(BlockState state)
    {
        return ToolType.SHOVEL;
    }
}
