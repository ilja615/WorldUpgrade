package com.github.ilja615.worldupgrade.util;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;

public class ModItemGroup extends CreativeModeTab
{
    public static final ModItemGroup instance = new ModItemGroup(CreativeModeTab.TABS.length, "worldupgrade");

    private ModItemGroup(int index, String label)
    {
        super(index, label);
    }

    @Override
    public ItemStack makeIcon()
    {
        return new ItemStack(Blocks.COARSE_DIRT);
    }
}
