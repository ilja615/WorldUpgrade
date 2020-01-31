package ilja615.worldupgrade.blocks;

import ilja615.worldupgrade.init.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.FallingBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;

public class BlockGravel extends FallingBlock
{
    private int dustColor = -8356741;
    public BlockGravel(String name, int dustColor, Properties properties)
    {
        super(properties);

        this.dustColor = dustColor;
        setRegistryName(name);
        ModBlocks.BLOCKS.add(this);
    }

    @OnlyIn(Dist.CLIENT)
    public int getDustColor(BlockState state) {
        return this.dustColor;
    }

    @Override
    public ToolType getHarvestTool(BlockState state) {
        return ToolType.SHOVEL;
    }
}
