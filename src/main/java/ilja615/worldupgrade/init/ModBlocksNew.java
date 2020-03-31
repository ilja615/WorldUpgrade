package ilja615.worldupgrade.init;

import ilja615.worldupgrade.WorldUpgrade;
import ilja615.worldupgrade.blocks.*;
import net.minecraft.block.Block;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlocksNew {
    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<Block>(ForgeRegistries.BLOCKS, WorldUpgrade.MOD_ID);

    // Terrain Blocks:
    public static final RegistryObject<Block> GRAVEL_DARK = BLOCKS.register("gravel_dark", () -> new BlockGravel("gravel_dark", -8356741, ModProperties.GRAVEL_PROPERTY));
    public static final RegistryObject<Block> GRAVEL_LIGHT = BLOCKS.register("gravel_light", () -> new BlockGravel("gravel_light", -8356741, ModProperties.GRAVEL_PROPERTY));
    public static final RegistryObject<Block> JUNGLE_ROCK = BLOCKS.register("jungle_rock", () -> new Block(ModProperties.JUNGLE_ROCK_PROPERTY));
    public static final RegistryObject<Block> JUNGLE_ROCK_OVERGROWN = BLOCKS.register("jungle_rock_overgrown", () -> new GrassyBlockBase("jungle_rock_overgrown", ModProperties.OVERGROWN_JUNGLE_ROCK_PROPERTY));
    public static final RegistryObject<Block> GRASSY_CLAY = BLOCKS.register("grassy_clay", () -> new GrassyBlockBase("grassy_clay", ModProperties.GRASSY_CLAY_PROPERTY));
    public static final RegistryObject<Block> JUNGLE_COBBLE = BLOCKS.register("jungle_cobble", () -> new Block(ModProperties.JUNGLE_COBBLE_PROPERTY));
    public static final RegistryObject<Block> RED_PEBBLE = BLOCKS.register("red_pebble", () -> new RedPebbleBlock("red_pebble", -8356741, ModProperties.GRAVEL_PROPERTY));
    public static final RegistryObject<Block> GRASSY_STONE = BLOCKS.register("grassy_stone", () -> new GrassyBlockBase("grassy_stone", ModProperties.GRASSY_STONE_PROPERTY));
    public static final RegistryObject<Block> SMOKE_VENT = BLOCKS.register("smoke_vent", () -> new SmokeVentBlock(ModProperties.VOLCANIC_STUFF_PROPERTY));
    public static final RegistryObject<Block> FIRE_JET = BLOCKS.register("fire_jet", () -> new FireJetBlock(ModProperties.VOLCANIC_STUFF_PROPERTY));
    public static final RegistryObject<Block> SILKI_BLOCK = BLOCKS.register("silki_block", () -> new SilkiBlock("silki_block",ModProperties.SILKI_BLOCK_PROPERY));
    public static final RegistryObject<Block> SILKI_BLOCK_EXTRA = BLOCKS.register("silki_block_extra", () -> new SilkiBlock("silki_block_extra",ModProperties.SILKI_BLOCK_PROPERY));
    public static final RegistryObject<Block> JUNGLE_FOSSIL = BLOCKS.register("jungle_fossil", () -> new JungleFossilBlock(ModProperties.JUNGLE_ROCK_PROPERTY));

    //Plants
    public static final RegistryObject<Block> ALOE_VERA = BLOCKS.register("aloe_vera", () -> new AloeVeraOrAgaveBlock("aloe_vera", ModProperties.ALOE_VERA_OR_AGAVE_PROPERTY));
    public static final RegistryObject<Block> AGAVE = BLOCKS.register("agave", () -> new AloeVeraOrAgaveBlock("agave", ModProperties.ALOE_VERA_OR_AGAVE_PROPERTY));
    public static final RegistryObject<Block> TALL_REED = BLOCKS.register("tall_reed", () -> new DoubleReedPlantBlock(ModProperties.REED_PLANT_PROPERTY));
    public static final RegistryObject<Block> TOP_REED = BLOCKS.register("top_reed", () -> new ExtraReedBlock( ModProperties.REED_PLANT_PROPERTY));
    public static final RegistryObject<Block> DRY_TALL_REED = BLOCKS.register("dry_tall_reed", () -> new DRiedDoubleReedPlantBlock(ModProperties.REED_PLANT_PROPERTY));
    public static final RegistryObject<Block> DRY_TOP_REED = BLOCKS.register("dry_top_reed", () -> new DriedExtraReedBlock( ModProperties.REED_PLANT_PROPERTY));

    //wood
    public static final RegistryObject<Block> DEAD_LOG = BLOCKS.register("dead_log", () -> new RotatedPillarBlock(ModProperties.LOG_PROPERTY));
    public static final RegistryObject<Block> DEAD_LEAVES = BLOCKS.register("dead_leaves", () -> new LeavesBlock(ModProperties.LOG_PROPERTY));

    public static final RegistryObject<Block> DRAGON_LOG = BLOCKS.register("dragon_log", () -> new RotatedPillarBlock(ModProperties.LOG_PROPERTY));
    public static final RegistryObject<Block> DRAGON_LEAVES = BLOCKS.register("dragon_leaves", () -> new LeavesBlock(ModProperties.LOG_PROPERTY));


    //fallen leaves
    public static final RegistryObject<Block> FALLEN_DEAD_LEAVES = BLOCKS.register("fallen_dead_leaves", () -> new FallenLeavesBlock("fallen_dead_leaves", ModProperties.FALLERN_LEAVES_PROPERTY));
    public static final RegistryObject<Block> FALLEN_DRAGON_LEAVES = BLOCKS.register("fallen_dragon_leaves", () -> new FallenLeavesBlock("fallen_dragon_leaves", ModProperties.FALLERN_LEAVES_PROPERTY));
//vanilla variants fallen leaves
    public static final RegistryObject<Block> FALLEN_OAK_LEAVES = BLOCKS.register("fallen_oak_leaves", () -> new FallenLeavesBlock("fallen_oak_leaves", ModProperties.FALLERN_LEAVES_PROPERTY));
    public static final RegistryObject<Block> FALLEN_BIRCH_LEAVES = BLOCKS.register("fallen_birch_leaves", () -> new FallenLeavesBlock("fallen_birch_leaves", ModProperties.FALLERN_LEAVES_PROPERTY));
    public static final RegistryObject<Block> FALLEN_SPRUZE_LEAVES = BLOCKS.register("fallen_spruce_leaves", () -> new FallenLeavesBlock("fallen_spruce_leaves", ModProperties.FALLERN_LEAVES_PROPERTY));
    public static final RegistryObject<Block> FALLEN_JUNGLE_LEAVES = BLOCKS.register("fallen_jungle_leaves", () -> new FallenLeavesBlock("fallen_jungle_leaves", ModProperties.FALLERN_LEAVES_PROPERTY));
    public static final RegistryObject<Block> FALLEN_ACACIA_LEAVES = BLOCKS.register("fallen_acacia_leaves", () -> new FallenLeavesBlock("fallen_acacia_leaves", ModProperties.FALLERN_LEAVES_PROPERTY));
    public static final RegistryObject<Block> FALLEN_DARK_OAK_LEAVES = BLOCKS.register("fallen_dark_oak_leaves", () -> new FallenLeavesBlock("fallen_dark_oak_leaves", ModProperties.FALLERN_LEAVES_PROPERTY));

}
