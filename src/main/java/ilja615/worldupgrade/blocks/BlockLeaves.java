package ilja615.worldupgrade.blocks;

import ilja615.worldupgrade.init.ModBlocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockLeaves extends LeavesBlock
{
    public BlockLeaves(String name, Properties properties)
    {
        super(properties);

        setRegistryName(name);
        ModBlocks.BLOCKS.add(this);
    }
}
