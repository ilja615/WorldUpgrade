package com.github.ilja615.worldupgrade.init;

import com.github.ilja615.worldupgrade.WorldUpgrade;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

public class ModProperties
{
    //Block properties
    public static final BlockBehaviour.Properties GRAVEL_PROPERTY = BlockBehaviour.Properties.of(Material.DIRT).sound(SoundType.GRAVEL).strength(0.6F, 3.0F);

    //Item properties
    public static final Item.Properties ITEM_PROPERTY = new Item.Properties().tab(WorldUpgrade.TAB);
}
