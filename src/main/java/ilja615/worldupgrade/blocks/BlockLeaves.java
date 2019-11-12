package ilja615.worldupgrade.blocks;

import ilja615.worldupgrade.init.ModBlocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockLeaves extends LeavesBlock
{
    public BlockLeaves(String name)
    {
        super(Properties.create(Material.PLANTS)
                .tickRandomly()
                .sound(SoundType.PLANT)
                .hardnessAndResistance(0.2F, 1.0F));

        setRegistryName(name);
        ModBlocks.BLOCKS.add(this);
    }
}
