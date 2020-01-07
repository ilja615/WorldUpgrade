package ilja615.worldupgrade.init;

import ilja615.worldupgrade.blocks.*;
import ilja615.worldupgrade.blocks.special.*;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;


public class ModBlocks
{
    public static final List<Block> BLOCKS = new ArrayList<Block>();

    // Normal Blocks:
    public static BlockGravel GRAVEL_DARK = new BlockGravel("gravel_dark", -8356741, ModProperties.GRAVEL_PROPERTY);
    public static BlockGravel GRAVEL_LIGHT = new BlockGravel("gravel_light", -8356741, ModProperties.GRAVEL_PROPERTY);
    public static BlockBase JUNGLE_ROCK = new BlockBase("jungle_rock", ModProperties.JUNGLE_ROCK_PROPERTY);
    public static OvergrownJungleRock JUNGLE_ROCK_OVERGROWN = new OvergrownJungleRock(ModProperties.OVERGROWN_JUNGLE_ROCK_PROPERTY);
    public static BlockBase JUNGLE_COBBLE = new BlockBase("jungle_cobble", ModProperties.JUNGLE_COBBLE_PROPERTY);

    // Tree or Wood Blocks
    public static BlockLog DEAD_LOG = new BlockLog("dead_log", ModProperties.LOG_PROPERTY);
    public static BlockLeaves DEAD_LEAVES = new BlockLeaves("dead_leaves", ModProperties.LEAVES_PROPERTY);
    public static BlockLeavesCarpet DEAD_LEAVES_CARPET = new BlockLeavesCarpet("dead_leaves_carpet", ModProperties.LEAVES_PROPERTY);

    public static BlockLog PALM_LOG = new BlockLog("palm_log", ModProperties.LOG_PROPERTY);
    public static BlockLeaves PALM_LEAVES = new BlockLeaves("palm_leaves", ModProperties.LEAVES_PROPERTY);
    public static BlockLeavesCarpet PALM_LEAVES_CARPET = new BlockLeavesCarpet("palm_leaves_carpet", ModProperties.LEAVES_PROPERTY);

    // Plants
    public static AloeVeraOrAgaveBlock ALOE_VERA = new AloeVeraOrAgaveBlock("aloe_vera", ModProperties.ALOE_VERA_OR_AGAVE_PROPERTY);
    public static AloeVeraOrAgaveBlock AGAVE = new AloeVeraOrAgaveBlock("agave", ModProperties.ALOE_VERA_OR_AGAVE_PROPERTY);

    // Variants From Vanilla:
    public static BlockLeavesCarpet OAK_LEAVES_CARPET = new BlockLeavesCarpet("oak_leaves_carpet", ModProperties.LEAVES_PROPERTY);
    public static BlockLeavesCarpet BIRCH_LEAVES_CARPET = new BlockLeavesCarpet("birch_leaves_carpet", ModProperties.LEAVES_PROPERTY);
    public static BlockLeavesCarpet SPRUCE_LEAVES_CARPET = new BlockLeavesCarpet("spruce_leaves_carpet", ModProperties.LEAVES_PROPERTY);
    public static BlockLeavesCarpet JUNGLE_LEAVES_CARPET = new BlockLeavesCarpet("jungle_leaves_carpet", ModProperties.LEAVES_PROPERTY);
    public static BlockLeavesCarpet ACACIA_LEAVES_CARPET = new BlockLeavesCarpet("acacia_leaves_carpet", ModProperties.LEAVES_PROPERTY);
    public static BlockLeavesCarpet DARK_OAK_LEAVES_CARPET = new BlockLeavesCarpet("dark_oak_leaves_carpet", ModProperties.LEAVES_PROPERTY);

    // Special Blocks:
    public static SmokeVent SMOKE_VENT = new SmokeVent(ModProperties.VOLCANIC_STUFF_PROPERTY);
    public static FireJet FIRE_JET = new FireJet(ModProperties.VOLCANIC_STUFF_PROPERTY);
    public static SilkiBlock SILKI_BLOCK = new SilkiBlock(ModProperties.SILKI_BLOCK_PROPERY);
    public static JungleFossil JUNGLE_FOSSIL = new JungleFossil(ModProperties.JUNGLE_ROCK_PROPERTY);

}
