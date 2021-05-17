package com.github.ilja615.worldupgrade.init;

import com.github.ilja615.worldupgrade.util.WorldUpgradeItemGroup;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.common.ToolType;

public class ModProperties
{
    //blocsk default
    public static final AbstractBlock.Properties GRAVEL_PROPERTY = AbstractBlock.Properties.of(Material.DIRT).sound(SoundType.GRAVEL).strength(0.6F, 3.0F);
    public static final AbstractBlock.Properties LOG_PROPERTY = AbstractBlock.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2.0F, 10.0F).harvestLevel(0).harvestTool(ToolType.AXE);
    public static final AbstractBlock.Properties LEAVES_PROPERTY = AbstractBlock.Properties.of(Material.LEAVES).sound(SoundType.GRASS).strength(0.2F, 1.0F).randomTicks().noOcclusion();
    public static final AbstractBlock.Properties STONE_PROPERTY = AbstractBlock.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1.5F, 30.0F);
    public static final AbstractBlock.Properties COBBLE_PROPERTY = AbstractBlock.Properties.of(Material.STONE).sound(SoundType.STONE).strength(2.0F, 30.0F);
    public static final AbstractBlock.Properties SAND_PROPERTY = AbstractBlock.Properties.of(Material.SAND).sound(SoundType.SAND).strength(0.5F, 2.5F);
    public static final AbstractBlock.Properties FLOWER_PROPERTY = AbstractBlock.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS);

    //blocks special or a little bit special
    public static final AbstractBlock.Properties JUNGLE_ROCK_PROPERTY = AbstractBlock.Properties.of(Material.DIRT).sound(SoundType.GRAVEL).strength(1.2F, 21.0F);
    public static final AbstractBlock.Properties JUNGLE_COBBLE_PROPERTY = AbstractBlock.Properties.of(Material.DIRT).sound(SoundType.GRAVEL).strength(1.2F, 21.0F);
    public static final AbstractBlock.Properties OVERGROWN_JUNGLE_ROCK_PROPERTY = AbstractBlock.Properties.of(Material.DIRT).sound(SoundType.GRAVEL).strength(1.2F, 21.0F).randomTicks();
    public static final AbstractBlock.Properties AGAVE_PROPERTY = AbstractBlock.Properties.of(Material.PLANT).sound(SoundType.GRASS).strength(0.4F, 2.0F).noCollission();
    public static final AbstractBlock.Properties VOLCANIC_STUFF_PROPERTY = AbstractBlock.Properties.of(Material.DIRT).sound(SoundType.GRAVEL).strength(1.5F, 45.0F);
    public static final AbstractBlock.Properties FALLERN_LEAVES_PROPERTY = AbstractBlock.Properties.of(Material.LEAVES).sound(SoundType.GRASS).strength(0.2F, 1.0F).noCollission();
    public static final AbstractBlock.Properties GRASSY_CLAY_PROPERTY = AbstractBlock.Properties.of(Material.CLAY).sound(SoundType.GRAVEL).strength(0.6F, 3.0F).randomTicks();
    public static final AbstractBlock.Properties GRASSY_STONE_PROPERTY = AbstractBlock.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1.5F, 30.0F).randomTicks();
    public static final AbstractBlock.Properties REED_PLANT_PROPERTY = AbstractBlock.Properties.of(Material.PLANT).sound(SoundType.GRASS).strength(0.4F, 2.0F).noCollission().randomTicks();
    public static final AbstractBlock.Properties BIG_PLANT_OR_GUNNERA_PROPERTY = AbstractBlock.Properties.of(Material.LEAVES).sound(SoundType.GRASS).strength(0.2F, 1.0F);
    public static final AbstractBlock.Properties BIG_WATER_LILY_PROPERTY = AbstractBlock.Properties.of(Material.LEAVES).sound(SoundType.LILY_PAD).strength(0.2F, 1.0F);
    public static final AbstractBlock.Properties DRY_REED_PLANT_PROPERTY = AbstractBlock.Properties.of(Material.PLANT).sound(SoundType.GRASS).strength(0.4F, 2.0F).noCollission();

    //foods
    public static final Food BRAMBLE = (new Food.Builder()).nutrition(2).saturationMod(0.1F).effect(new EffectInstance(Effects.FIRE_RESISTANCE, 80, 0), 1.0F).build();
    public static final Food NETTLE_SOUP = (new Food.Builder()).nutrition(5).saturationMod(0.6F).build();
    public static final Food BRAMBLE_CHEESECAKE = (new Food.Builder()).nutrition(8).saturationMod(0.7F).build();
    public static final Food ALOE_VERA_PASTE = (new Food.Builder()).nutrition(4).saturationMod(0.0F).alwaysEat().build();

    //items
    public static final Item.Properties ITEM_PROPERTY = new Item.Properties().tab(WorldUpgradeItemGroup.INSTANCE);
    public static final Item.Properties BRAMBLE_FOOD_ITEM_PROPERTY = new Item.Properties().tab(WorldUpgradeItemGroup.INSTANCE).food(BRAMBLE);
    public static final Item.Properties NETTLE_SOUP_ITEM_PROPERTY = new Item.Properties().tab(WorldUpgradeItemGroup.INSTANCE).stacksTo(1).food(NETTLE_SOUP);
}
