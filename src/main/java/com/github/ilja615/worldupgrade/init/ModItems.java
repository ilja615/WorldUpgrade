package com.github.ilja615.worldupgrade.init;

import com.github.ilja615.worldupgrade.WorldUpgrade;
import com.github.ilja615.worldupgrade.items.FlightArrowItem;
import com.github.ilja615.worldupgrade.items.SpoonBillEggItem;
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

    public static final RegistryObject<Item> GRIBBER_FEATHER = ITEMS.register("gribber_feather", () -> new Item(ModProperties.ITEM_PROPERTY));
    public static final RegistryObject<Item> PRICKLY_SHARD = ITEMS.register("prickly_shard", () -> new Item(ModProperties.ITEM_PROPERTY));
    public static final RegistryObject<Item> SPOONBILL_EGG = ITEMS.register("spoonbill_egg", () -> new SpoonBillEggItem(ModProperties.ITEM_PROPERTY.maxStackSize(16)));
    public static final RegistryObject<Item> FLIGHT_ARROW = ITEMS.register("flight_arrow", () -> new FlightArrowItem(ModProperties.ITEM_PROPERTY));

    public static final RegistryObject<Item> BUBBLE_EEL_BUCKET = ITEMS.register("bubble_eel_bucket", () -> new FishBucketItem(() -> ModEntities.BUBBLE_EEL.get(), () -> Fluids.WATER, new Item.Properties().maxStackSize(1).group(ItemGroup.MISC)));
}
