package ilja615.worldupgrade.util;

import ilja615.worldupgrade.init.ModBlocks;
import ilja615.worldupgrade.init.ModBlocksNew;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ItemGroupWU extends ItemGroup
{
    public static final ItemGroupWU instance = new ItemGroupWU(ItemGroup.GROUPS.length, "worldupgrade");

    private ItemGroupWU(int index, String label)
    {
        super(index, label);
    }

    @Override
    public ItemStack createIcon()
    {
        return new ItemStack(ModBlocksNew.GRAVEL_DARK.get());
    }
}
