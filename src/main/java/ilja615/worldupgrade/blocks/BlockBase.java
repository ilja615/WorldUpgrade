package ilja615.worldupgrade.blocks;


import ilja615.worldupgrade.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class BlockBase extends Block
{
    public ToolType toolType;
    public BlockBase(String name, Properties properties)
    {
        super(properties);

        setRegistryName(name);
        ModBlocks.BLOCKS.add(this);
    }

    @Override
    public ToolType getHarvestTool(BlockState state) {
        return ToolType.AXE;
    }
}
