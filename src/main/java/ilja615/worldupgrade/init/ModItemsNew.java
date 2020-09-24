package ilja615.worldupgrade.init;

import ilja615.worldupgrade.WorldUpgrade;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.FishBucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItemsNew {
    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<Item>(ForgeRegistries.ITEMS, WorldUpgrade.MOD_ID);

    public static final RegistryObject<Item> TRILOBITE_SHELL = ITEMS.register("trilobite_shell", () -> new Item(ModProperties.ITEM_PROPERTY));
    public static final RegistryObject<Item> BUBBLE_EEL_BUCKET = ITEMS.register("bubble_eel_bucket", () -> new FishBucketItem(ModEntities.BUBBLE_EEL, Fluids.WATER, new Item.Properties().maxStackSize(1).group(ItemGroup.MISC)));


}
