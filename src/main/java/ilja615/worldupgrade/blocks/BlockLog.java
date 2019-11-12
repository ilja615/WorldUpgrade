package ilja615.worldupgrade.blocks;

import ilja615.worldupgrade.init.ModBlocks;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.tags.BlockTags;

public class BlockLog extends RotatedPillarBlock
{
    public BlockLog(String name)
    {
        super(Properties.create(Material.WOOD)
                .sound(SoundType.WOOD)
                .hardnessAndResistance(0.6F, 3.0F));

        setRegistryName(name);
        ModBlocks.BLOCKS.add(this);
    }
}
