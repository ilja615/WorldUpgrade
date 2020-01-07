package ilja615.worldupgrade.blocks.special;

import ilja615.worldupgrade.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class JungleFossil extends Block
{

    public JungleFossil(Properties properties)
    {
        super(properties);

        setRegistryName("jungle_fossil");
        ModBlocks.BLOCKS.add(this);
    }



}
