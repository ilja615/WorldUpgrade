package com.github.ilja615.worldupgrade.util;

import com.github.ilja615.worldupgrade.init.ModBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class WorldUpgradeItemGroup extends ItemGroup
{
    public static final WorldUpgradeItemGroup INSTANCE = new WorldUpgradeItemGroup(ItemGroup.GROUPS.length, "worldupgrade");

    private WorldUpgradeItemGroup(int index, String label)
    {
        super(index, label);
    }

    @Override
    public ItemStack createIcon()
    {
        return new ItemStack(ModBlocks.GRAVEL_DARK.get());
    }
}
