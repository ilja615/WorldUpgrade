package com.github.ilja615.worldupgrade.init;

import com.github.ilja615.worldupgrade.util.ModItemGroup;
import net.minecraft.world.item.Item;

public class ModProperties
{
    //items properties
    public static final Item.Properties ITEM_PROPERTY = new Item.Properties().tab(ModItemGroup.instance);
    public static final Item.Properties ITEM_PROPERTY_NOT_STACKABLE = new Item.Properties().tab(ModItemGroup.instance).stacksTo(1);

    //block properties

}
