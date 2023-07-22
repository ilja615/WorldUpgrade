package com.github.ilja615.worldupgrade.init;

import com.github.ilja615.worldupgrade.WorldUpgrade;
import com.github.ilja615.worldupgrade.blocks.BigLilyPadBlock;
import com.github.ilja615.worldupgrade.blocks.BigPlantLeafBlock;
import com.github.ilja615.worldupgrade.blocks.DoubleBromeliadBlock;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PlaceOnWaterBlockItem;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks
{
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, WorldUpgrade.MOD_ID);

    public static final RegistryObject<Block> GUNNERA_LEAF = registerBlockWithItem("gunnera_leaf", () -> new BigPlantLeafBlock(ModProperties.BIG_PLANT_PROPERTY));
    public static final RegistryObject<Block> BIG_PLANT_STEM = registerBlockWithItem("big_plant_stem", () -> new RotatedPillarBlock(ModProperties.BIG_PLANT_PROPERTY));
    public static final RegistryObject<Block> LILY_PAD = registerBlockWithItem("lily_pad", () -> new BigLilyPadBlock(ModProperties.BIG_WATER_LILY_PROPERTY), () -> new PlaceOnWaterBlockItem(ModBlocks.LILY_PAD.get(), new Item.Properties()));
    public static final RegistryObject<Block> DRYGRASS = registerBlockWithItem("drygrass", () -> new BushBlock(ModProperties.GRASS_PROPERTY));
    public static final RegistryObject<Block> BROMELIAD_FLAMING_SWORD = registerBlockWithItem("bromeliad_flaming_sword", () -> new DoubleBromeliadBlock(ModProperties.GRASS_PROPERTY));
    public static final RegistryObject<Block> BOG_BEACON = registerBlockWithItem("bog_beacon", () -> new MushroomBlock(BlockBehaviour.Properties.copy(Blocks.BROWN_MUSHROOM).lightLevel((p_50892_) -> {
        return 4;
    }), ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(WorldUpgrade.MOD_ID, "huge_bog_beacon_mushroom"))));

    public static <BLOCK extends Block, ITEM extends BlockItem> RegistryObject<BLOCK> registerBlockWithItem(String name, Supplier<BLOCK> blockSupplier, Supplier<ITEM> itemSupplier)
    {
        RegistryObject<BLOCK> block = ModBlocks.BLOCKS.register(name, blockSupplier);
        ModItems.ITEMS.register(name, itemSupplier);
        return block;
    }

    public static <BLOCK extends Block> RegistryObject<BLOCK> registerBlockWithItem(String name, Supplier<BLOCK> blockSupplier)
    {
        RegistryObject<BLOCK> block = ModBlocks.BLOCKS.register(name, blockSupplier);
        ModCreativeTabs.addToTab(ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), ModProperties.ITEM_PROPERTY)));
        return block;
    }
}
