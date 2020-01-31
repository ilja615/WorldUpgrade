package ilja615.worldupgrade.blocks;


import ilja615.worldupgrade.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.WoodButtonBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;

public class BlockWoodenButtonBase extends WoodButtonBlock
{
    public BlockWoodenButtonBase(String name, Properties properties)
    {
        super(properties);

        setRegistryName(name);
        ModBlocks.BLOCKS.add(this);
    }

    @Override
    public int getFlammability(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
        return 0;
    }

    @Override
    public ToolType getHarvestTool(BlockState state) {
        return ToolType.AXE;
    }
}
