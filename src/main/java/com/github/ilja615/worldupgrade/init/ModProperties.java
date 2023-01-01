package com.github.ilja615.worldupgrade.init;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

public class ModProperties
{
    //items properties
    public static final Item.Properties ITEM_PROPERTY = new Item.Properties();
    public static final Item.Properties ITEM_PROPERTY_NOT_STACKABLE = new Item.Properties().stacksTo(1);

    //block properties
    public static final BlockBehaviour.Properties BIG_PLANT_PROPERTY = BlockBehaviour.Properties.of(Material.PLANT).sound(SoundType.GRASS).strength(0.2F, 1.0F);
    public static final BlockBehaviour.Properties BIG_WATER_LILY_PROPERTY = BlockBehaviour.Properties.of(Material.PLANT).sound(SoundType.LILY_PAD).strength(0.2F, 1.0F);
    public static final BlockBehaviour.Properties GRASS_PROPERTY = BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT).noCollission().instabreak().sound(SoundType.GRASS);
}
