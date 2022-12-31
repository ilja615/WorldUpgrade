package com.github.ilja615.worldupgrade.init;

import com.github.ilja615.worldupgrade.WorldUpgrade;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModItems
{
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, WorldUpgrade.MOD_ID);

    public static <BLOCK extends Block> RegistryObject<BLOCK> registerBlockWithItem(String name, Supplier<BLOCK> blockSupplier, Item.Properties properties)
    {
        RegistryObject<BLOCK> block = ModBlocks.BLOCKS.register(name, blockSupplier);
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), properties));
        return block;
    }

    public static <BLOCK extends Block> RegistryObject<BLOCK> registerBlockWithItem(String name, Supplier<BLOCK> blockSupplier)
    {
        RegistryObject<BLOCK> block = ModBlocks.BLOCKS.register(name, blockSupplier);
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), ModProperties.ITEM_PROPERTY));
        return block;
    }
}
