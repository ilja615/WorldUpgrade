package com.github.ilja615.worldupgrade.init;

import com.github.ilja615.worldupgrade.util.WorldUpgradeItemGroup;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;

public class ModProperties
{
    //block default
    public static final Block.Properties GRAVEL_PROPERTY = Block.Properties.create(Material.EARTH).sound(SoundType.GROUND).hardnessAndResistance(0.6F, 3.0F);
    public static final Block.Properties LOG_PROPERTY = Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.0F, 10.0F).harvestLevel(0).harvestTool(ToolType.AXE);
    public static final Block.Properties LEAVES_PROPERTY = Block.Properties.create(Material.LEAVES).sound(SoundType.PLANT).hardnessAndResistance(0.2F, 1.0F).tickRandomly().notSolid();
    public static final Block.Properties STONE_PROPERTY = Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(1.5F, 30.0F);
    public static final Block.Properties COBBLE_PROPERTY = Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2.0F, 30.0F);
    public static final Block.Properties SAND_PROPERTY = Block.Properties.create(Material.SAND).sound(SoundType.SAND).hardnessAndResistance(0.5F, 2.5F);

    //blocks special or a little bit special
    public static final Block.Properties JUNGLE_ROCK_PROPERTY = Block.Properties.create(Material.EARTH).sound(SoundType.GROUND).hardnessAndResistance(1.2F, 21.0F);
    public static final Block.Properties JUNGLE_COBBLE_PROPERTY = Block.Properties.create(Material.EARTH).sound(SoundType.GROUND).hardnessAndResistance(1.2F, 21.0F);
    public static final Block.Properties OVERGROWN_JUNGLE_ROCK_PROPERTY = Block.Properties.create(Material.EARTH).sound(SoundType.GROUND).hardnessAndResistance(1.2F, 21.0F).tickRandomly();
    public static final Block.Properties AGAVE_PROPERTY = Block.Properties.create(Material.PLANTS).sound(SoundType.PLANT).hardnessAndResistance(0.4F, 2.0F).doesNotBlockMovement();
    public static final Block.Properties VOLCANIC_STUFF_PROPERTY = Block.Properties.create(Material.EARTH).sound(SoundType.GROUND).hardnessAndResistance(1.5F, 45.0F);
    public static final Block.Properties SILKI_BLOCK_PROPERY = Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(1.5F, 30.0F);
    public static final Block.Properties FALLERN_LEAVES_PROPERTY = Block.Properties.create(Material.LEAVES).sound(SoundType.PLANT).hardnessAndResistance(0.2F, 1.0F).doesNotBlockMovement();
    public static final Block.Properties GRASSY_CLAY_PROPERTY = Block.Properties.create(Material.CLAY).sound(SoundType.GROUND).hardnessAndResistance(0.6F, 3.0F).tickRandomly();
    public static final Block.Properties GRASSY_STONE_PROPERTY = Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(1.5F, 30.0F).tickRandomly();
    public static final Block.Properties REED_PLANT_PROPERTY = Block.Properties.create(Material.PLANTS).sound(SoundType.PLANT).hardnessAndResistance(0.4F, 2.0F).doesNotBlockMovement().tickRandomly();
    public static final Block.Properties CHAR_EMBERS_PROPRETY = Block.Properties.create(Material.EARTH).sound(SoundType.GROUND).hardnessAndResistance(1.5F, 45.0F).doesNotBlockMovement();

    //foods
    public static final Food BRAMBLE = (new Food.Builder()).hunger(2).saturation(0.1F).build();

    //items
    public static final Item.Properties ITEM_PROPERTY = new Item.Properties().group(WorldUpgradeItemGroup.INSTANCE);
    public static final Item.Properties BRAMBLE_FOOD_ITEM_PROPERTY = new Item.Properties().group(WorldUpgradeItemGroup.INSTANCE).food(BRAMBLE);
}
