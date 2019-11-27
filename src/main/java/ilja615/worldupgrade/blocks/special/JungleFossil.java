package ilja615.worldupgrade.blocks.special;

import ilja615.worldupgrade.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class JungleFossil extends Block
{

    public JungleFossil()
    {
        super(Properties.create(Material.ROCK)
                .sound(SoundType.STONE)
                .hardnessAndResistance(1.5F, 30.0F));

        setRegistryName("jungle_fossil");
        ModBlocks.BLOCKS.add(this);
    }



}
