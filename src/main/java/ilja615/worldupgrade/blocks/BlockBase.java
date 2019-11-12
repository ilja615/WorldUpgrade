package ilja615.worldupgrade.blocks;

import ilja615.worldupgrade.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockBase extends Block
{
    public BlockBase(String name, Material material, SoundType soundType, float hardness, float resistance)
    {
        super(Properties.create(material)
                .sound(soundType)
                .hardnessAndResistance(hardness, resistance));

        setRegistryName(name);
        ModBlocks.BLOCKS.add(this);
    }
}
