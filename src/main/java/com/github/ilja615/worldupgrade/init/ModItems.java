package com.github.ilja615.worldupgrade.init;

import com.github.ilja615.worldupgrade.WorldUpgrade;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModItems
{
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, WorldUpgrade.MOD_ID);

    public static final RegistryObject<Item> DISGUISAGER_SPAWN_EGG = ModItems.ITEMS.register("disguisager_spawn_egg", () -> new ForgeSpawnEggItem(() -> ModEntities.DISGUISAGER.get(), 6975012, 7001380, ModProperties.ITEM_PROPERTY));
}
