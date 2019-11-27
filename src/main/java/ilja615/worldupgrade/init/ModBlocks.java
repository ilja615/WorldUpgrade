package ilja615.worldupgrade.init;

import ilja615.worldupgrade.blocks.*;
import ilja615.worldupgrade.blocks.special.*;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraftforge.registries.ObjectHolder;

import java.util.ArrayList;
import java.util.List;


public class ModBlocks
{
    public static final List<Block> BLOCKS = new ArrayList<Block>();

    // Normal Blocks:
    public static BlockGravel GRAVEL_DARK = new BlockGravel("gravel_dark", -8356741);
    public static BlockGravel GRAVEL_LIGHT = new BlockGravel("gravel_light", -8356741);
    public static BlockLog DEAD_LOG = new BlockLog("dead_log");
    public static BlockLeaves DEAD_LEAVES = new BlockLeaves("dead_leaves");
    public static BlockLeavesCarpet DEAD_LEAVES_CARPET = new BlockLeavesCarpet("dead_leaves_carpet");
    public static BlockBase JUNGLE_ROCK = new BlockBase("jungle_rock", Material.ROCK, SoundType.STONE, 1.5F, 30.0F);
    public static OvergrownJungleRock JUNGLE_ROCK_OVERGROWN = new OvergrownJungleRock();
    public static BlockBase JUNGLE_COBBLE = new BlockBase("jungle_cobble", Material.ROCK, SoundType.STONE, 1.5F, 30.0F);



    // Variants From Vanilla:
    public static BlockLeavesCarpet OAK_LEAVES_CARPET = new BlockLeavesCarpet("oak_leaves_carpet");
    public static BlockLeavesCarpet BIRCH_LEAVES_CARPET = new BlockLeavesCarpet("birch_leaves_carpet");
    public static BlockLeavesCarpet SPRUCE_LEAVES_CARPET = new BlockLeavesCarpet("spruce_leaves_carpet");
    public static BlockLeavesCarpet JUNGLE_LEAVES_CARPET = new BlockLeavesCarpet("jungle_leaves_carpet");
    public static BlockLeavesCarpet ACACIA_LEAVES_CARPET = new BlockLeavesCarpet("acacia_leaves_carpet");
    public static BlockLeavesCarpet DARK_OAK_LEAVES_CARPET = new BlockLeavesCarpet("dark_oak_leaves_carpet");


    // Special Blocks:
    public static SmokeVent SMOKE_VENT = new SmokeVent();
    public static FireJet FIRE_JET = new FireJet();
    public static CobbleWebStone COBBLEWEBSTONE = new CobbleWebStone();
    public static JungleFossil JUNGLE_FOSSIL = new JungleFossil();

}
