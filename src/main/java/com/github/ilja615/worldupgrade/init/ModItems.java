package com.github.ilja615.worldupgrade.init;

import com.github.ilja615.worldupgrade.WorldUpgrade;
import com.github.ilja615.worldupgrade.items.ModSpawnEggItem;
import com.github.ilja615.worldupgrade.util.WorldUpgradeItemGroup;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.FishBucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems
{
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, WorldUpgrade.MOD_ID);

    public static final RegistryObject<Item> TRILOBITE_SHELL = ITEMS.register("trilobite_shell", () -> new Item(ModProperties.ITEM_PROPERTY));
    public static final RegistryObject<Item> BUBBLE_EEL_BUCKET = ITEMS.register("bubble_eel_bucket", () -> new FishBucketItem(ModEntities.BUBBLE_EEL, () -> Fluids.WATER, new Item.Properties().maxStackSize(1).group(ItemGroup.MISC)));
    public static final RegistryObject<Item> BUBBLE_EEL_EGG = ITEMS.register("bubble_eel_egg", () -> new ModSpawnEggItem(
            ModEntities.BUBBLE_EEL::get,
            0xffffff,
            0x121212,
            new Item.Properties().group(WorldUpgradeItemGroup.INSTANCE))
    );
    public static final RegistryObject<Item> SPOONBILL_EGG = ITEMS.register("spoonbill_egg", () -> new ModSpawnEggItem(
            ModEntities.SPOONBILL::get,
            0xff345f,
            0xaa8112,
            new Item.Properties().group(WorldUpgradeItemGroup.INSTANCE))
    );
}
