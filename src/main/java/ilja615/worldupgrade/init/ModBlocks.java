package ilja615.worldupgrade.init;

import ilja615.worldupgrade.blocks.*;
import ilja615.worldupgrade.blocks.special.*;
import net.minecraft.block.Block;

import java.util.ArrayList;
import java.util.List;


public class ModBlocks
{
    public static final List<Block> BLOCKS = new ArrayList<Block>();

    // Normal Blocks:
    public static BlockGravel GRAVEL_DARK = new BlockGravel("gravel_dark", -8356741, ModProperties.GRAVEL_PROPERTY);
    public static BlockGravel GRAVEL_LIGHT = new BlockGravel("gravel_light", -8356741, ModProperties.GRAVEL_PROPERTY);
    public static BlockBase JUNGLE_ROCK = new BlockBase("jungle_rock", ModProperties.JUNGLE_ROCK_PROPERTY);
    public static GrassyBlockBase JUNGLE_ROCK_OVERGROWN = new GrassyBlockBase("jungle_rock_overgrown", ModProperties.OVERGROWN_JUNGLE_ROCK_PROPERTY);
    public static GrassyBlockBase GRASSY_CLAY = new GrassyBlockBase("grassy_clay",ModProperties.GRASSY_CLAY_PROPERTY);
    public static BlockBase JUNGLE_COBBLE = new BlockBase("jungle_cobble", ModProperties.JUNGLE_COBBLE_PROPERTY);
    public static RedPebbleBlock RED_PEBBLE = new RedPebbleBlock("red_pebble", -8356741, ModProperties.GRAVEL_PROPERTY);
    public static GrassyBlockBase GRASSY_STONE = new GrassyBlockBase("grassy_stone",ModProperties.GRASSY_STONE_PROPERTY);

    // Tree or Wood Blocks
    public static BlockLog DEAD_LOG = new BlockLog("dead_log", ModProperties.LOG_PROPERTY);
    public static BlockLeaves DEAD_LEAVES = new BlockLeaves("dead_leaves", ModProperties.LEAVES_PROPERTY);
    public static BlockLeavesCarpet DEAD_LEAVES_CARPET = new BlockLeavesCarpet("dead_leaves_carpet", ModProperties.FALLERN_LEAVES_PROPERTY);

    public static BlockLog DRAGON_LOG = new BlockLog("dragon_log", ModProperties.LOG_PROPERTY);
    public static BlockLeaves DRAGON_LEAVES = new BlockLeaves("dragon_leaves", ModProperties.LEAVES_PROPERTY);
    public static BlockLeavesCarpet DRAGON_LEAVES_CARPET = new BlockLeavesCarpet("dragon_leaves_carpet", ModProperties.FALLERN_LEAVES_PROPERTY);

    // Plants
    public static AloeVeraOrAgaveBlock ALOE_VERA = new AloeVeraOrAgaveBlock("aloe_vera", ModProperties.ALOE_VERA_OR_AGAVE_PROPERTY);
    public static AloeVeraOrAgaveBlock AGAVE = new AloeVeraOrAgaveBlock("agave", ModProperties.ALOE_VERA_OR_AGAVE_PROPERTY);
    public static DoubleReedPlantBlock TALL_REED = new DoubleReedPlantBlock("tall_reed", ModProperties.REED_PLANT_PROPERTY);
    public static ExtraReedBlock TOP_REED = new ExtraReedBlock("top_reed", ModProperties.REED_PLANT_PROPERTY);
    public static DRiedDoubleReedPlantBlock DRY_TALL_REED = new DRiedDoubleReedPlantBlock("dry_tall_reed", ModProperties.REED_PLANT_PROPERTY);
    public static DriedExtraReedBlock DRY_TOP_REED = new DriedExtraReedBlock("dry_top_reed", ModProperties.REED_PLANT_PROPERTY);

    // Variants For Vanilla:
    public static BlockLeavesCarpet OAK_LEAVES_CARPET = new BlockLeavesCarpet("oak_leaves_carpet", ModProperties.FALLERN_LEAVES_PROPERTY);
    public static BlockLeavesCarpet BIRCH_LEAVES_CARPET = new BlockLeavesCarpet("birch_leaves_carpet", ModProperties.FALLERN_LEAVES_PROPERTY);
    public static BlockLeavesCarpet SPRUCE_LEAVES_CARPET = new BlockLeavesCarpet("spruce_leaves_carpet", ModProperties.FALLERN_LEAVES_PROPERTY);
    public static BlockLeavesCarpet JUNGLE_LEAVES_CARPET = new BlockLeavesCarpet("jungle_leaves_carpet", ModProperties.FALLERN_LEAVES_PROPERTY);
    public static BlockLeavesCarpet ACACIA_LEAVES_CARPET = new BlockLeavesCarpet("acacia_leaves_carpet", ModProperties.FALLERN_LEAVES_PROPERTY);
    public static BlockLeavesCarpet DARK_OAK_LEAVES_CARPET = new BlockLeavesCarpet("dark_oak_leaves_carpet", ModProperties.FALLERN_LEAVES_PROPERTY);

    // Special Blocks:
    public static SmokeVentBlock SMOKE_VENT = new SmokeVentBlock(ModProperties.VOLCANIC_STUFF_PROPERTY);
    public static FireJetBlock FIRE_JET = new FireJetBlock(ModProperties.VOLCANIC_STUFF_PROPERTY);
    public static SilkiBlock SILKI_BLOCK = new SilkiBlock("silki_block",ModProperties.SILKI_BLOCK_PROPERY);
    public static SilkiBlock SILKI_BLOCK_EXTRA = new SilkiBlock("silki_block_extra",ModProperties.SILKI_BLOCK_PROPERY);
    public static JungleFossilBlock JUNGLE_FOSSIL = new JungleFossilBlock(ModProperties.JUNGLE_ROCK_PROPERTY);

}
