package ilja615.worldupgrade.blocks;

import ilja615.worldupgrade.init.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.LogBlock;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.ToolType;

public class BlockLog extends LogBlock
{
    public BlockLog(String name, Properties properties)
    {
        super(MaterialColor.WOOD, properties);

        setRegistryName(name);
        ModBlocks.BLOCKS.add(this);
    }
}
