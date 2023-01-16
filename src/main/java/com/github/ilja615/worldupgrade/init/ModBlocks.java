package com.github.ilja615.worldupgrade.init;

import com.github.ilja615.worldupgrade.WorldUpgrade;
import com.github.ilja615.worldupgrade.blocks.BigPlantLeafBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks
{
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, WorldUpgrade.MOD_ID);

    public static final RegistryObject<Block> GUNNERA_LEAF = registerBlockWithItem("gunnera_leaf", () -> new BigPlantLeafBlock(ModProperties.BIG_PLANT_PROPERTY));
    public static final RegistryObject<Block> BIG_PLANT_STEM = registerBlockWithItem("big_plant_stem", () -> new RotatedPillarBlock(ModProperties.BIG_PLANT_PROPERTY));
    public static final RegistryObject<Block> LILY_PAD = registerBlockWithItem("lily_pad", () -> new Block(ModProperties.BIG_WATER_LILY_PROPERTY));
    public static final RegistryObject<Block> DRYGRASS = registerBlockWithItem("drygrass", () -> new BushBlock(ModProperties.GRASS_PROPERTY));

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
