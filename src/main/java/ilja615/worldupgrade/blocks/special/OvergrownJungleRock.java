package ilja615.worldupgrade.blocks.special;

import ilja615.worldupgrade.init.ModBlocks;
import net.minecraft.block.GrassBlock;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class OvergrownJungleRock extends GrassBlock
{
    public OvergrownJungleRock()
    {
        super(Properties.create(Material.ROCK)
                .tickRandomly()
                .sound(SoundType.STONE)
                .hardnessAndResistance(1.5F, 30.0F));

        setRegistryName("jungle_rock_overgrown");
        ModBlocks.BLOCKS.add(this);
    }
}
