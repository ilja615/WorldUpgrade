package ilja615.worldupgrade.init;

import ilja615.worldupgrade.WorldUpgrade;
import ilja615.worldupgrade.blocks.*;
import net.minecraft.block.*;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<Block>(ForgeRegistries.BLOCKS, WorldUpgrade.MOD_ID);

    // General Terrain Blocks:
    public static final RegistryObject<Block> GRAVEL_DARK = BLOCKS.register("gravel_dark", () -> new BlockGravel( -8356741, ModProperties.GRAVEL_PROPERTY));
    public static final RegistryObject<Block> GRAVEL_LIGHT = BLOCKS.register("gravel_light", () -> new BlockGravel( -8356741, ModProperties.GRAVEL_PROPERTY));
    public static final RegistryObject<Block> GRASSY_CLAY = BLOCKS.register("grassy_clay", () -> new GrassyBlockBase(ModProperties.GRASSY_CLAY_PROPERTY));
    public static final RegistryObject<Block> RED_PEBBLE = BLOCKS.register("red_pebble", () -> new RedPebbleBlock(-8356741, ModProperties.GRAVEL_PROPERTY));
    public static final RegistryObject<Block> GRASSY_STONE = BLOCKS.register("grassy_stone", () -> new GrassyBlockBase(ModProperties.GRASSY_STONE_PROPERTY));
    public static final RegistryObject<Block> SMOKE_VENT = BLOCKS.register("smoke_vent", () -> new SmokeVentBlock(ModProperties.VOLCANIC_STUFF_PROPERTY));
    public static final RegistryObject<Block> FIRE_JET = BLOCKS.register("fire_jet", () -> new FireJetBlock(ModProperties.VOLCANIC_STUFF_PROPERTY));

    // JUngle Rocks
    public static final RegistryObject<Block> JUNGLE_ROCK = BLOCKS.register("jungle_rock", () -> new Block(ModProperties.JUNGLE_ROCK_PROPERTY));
    public static final RegistryObject<Block> JUNGLE_ROCK_OVERGROWN = BLOCKS.register("jungle_rock_overgrown", () -> new GrassyBlockBase(ModProperties.OVERGROWN_JUNGLE_ROCK_PROPERTY));
    public static final RegistryObject<Block> JUNGLE_FOSSIL = BLOCKS.register("jungle_fossil", () -> new JungleFossilBlock(ModProperties.JUNGLE_ROCK_PROPERTY));
    public static final RegistryObject<Block> JUNGLE_COBBLE = BLOCKS.register("jungle_cobble", () -> new Block(ModProperties.JUNGLE_COBBLE_PROPERTY));
    public static final RegistryObject<Block> JUNGLE_ROCK_BRICKS = BLOCKS.register("jungle_rock_bricks", () -> new Block(ModProperties.JUNGLE_COBBLE_PROPERTY));
    public static final RegistryObject<Block> JUNGLE_ROCK_BRICKS_MOSSY = BLOCKS.register("jungle_rock_bricks_mossy", () -> new Block(ModProperties.JUNGLE_COBBLE_PROPERTY));
    public static final RegistryObject<Block> JUNGLE_ROCK_BRICKS_CRACKED = BLOCKS.register("jungle_rock_bricks_cracked", () -> new Block(ModProperties.JUNGLE_COBBLE_PROPERTY));
    public static final RegistryObject<Block> JUNGLE_COBBLE_MOSSY = BLOCKS.register("jungle_cobble_mossy", () -> new Block(ModProperties.JUNGLE_COBBLE_PROPERTY));
    public static final RegistryObject<Block> JUNGLE_ROCK_CHISELED = BLOCKS.register("jungle_rock_chiseled", () -> new Block(ModProperties.JUNGLE_COBBLE_PROPERTY));
//    public static final RegistryObject<Block> JUNGLE_ROCK_STAIRS = BLOCKS.register("jungle_rock_stairs", () -> new StairsBlock((Supplier<BlockState>) JUNGLE_ROCK.get().getDefaultState(), ModProperties.JUNGLE_ROCK_PROPERTY));
//    public static final RegistryObject<Block> JUNGLE_COBBLE_STAIRS = BLOCKS.register("jungle_cobble_stairs", () -> new StairsBlock((Supplier<BlockState>) JUNGLE_ROCK.get().getDefaultState(), ModProperties.JUNGLE_ROCK_PROPERTY));
//    public static final RegistryObject<Block> JUNGLE_ROCK_BRICKS_STAIRS = BLOCKS.register("jungle_rock_bricks_stairs", () -> new StairsBlock((Supplier<BlockState>) JUNGLE_ROCK.get().getDefaultState(), ModProperties.JUNGLE_ROCK_PROPERTY));
//    public static final RegistryObject<Block> JUNGLE_ROCK_BRICKS_MOSSY_STAIRS = BLOCKS.register("jungle_rock_bricks_mossy_stairs", () -> new StairsBlock((Supplier<BlockState>) JUNGLE_ROCK.get().getDefaultState(), ModProperties.JUNGLE_ROCK_PROPERTY));
//    public static final RegistryObject<Block> JUNGLE_COBBLE_MOSSY_STAIRS = BLOCKS.register("jungle_cobble_mossy_stairs", () -> new StairsBlock((Supplier<BlockState>) JUNGLE_ROCK.get().getDefaultState(), ModProperties.JUNGLE_ROCK_PROPERTY));
    public static final RegistryObject<SlabBlock> JUNGLE_ROCK_SLAB = BLOCKS.register("jungle_rock_slab", () -> new SlabBlock(ModProperties.JUNGLE_ROCK_PROPERTY));
    public static final RegistryObject<SlabBlock> JUNGLE_COBBLE_SLAB = BLOCKS.register("jungle_cobble_slab", () -> new SlabBlock(ModProperties.JUNGLE_ROCK_PROPERTY));
    public static final RegistryObject<SlabBlock> JUNGLE_ROCK_BRICKS_SLAB = BLOCKS.register("jungle_rock_bricks_slab", () -> new SlabBlock(ModProperties.JUNGLE_ROCK_PROPERTY));
    public static final RegistryObject<SlabBlock> JUNGLE_ROCK_BRICKS_MOSSY_SLAB = BLOCKS.register("jungle_rock_bricks_mossy_slab", () -> new SlabBlock(ModProperties.JUNGLE_ROCK_PROPERTY));
    public static final RegistryObject<SlabBlock> JUNGLE_COBBLE_MOSSY_SLAB = BLOCKS.register("jungle_cobble_mossy_slab", () -> new SlabBlock(ModProperties.JUNGLE_ROCK_PROPERTY));
    public static final RegistryObject<WallBlock> JUNGLE_COBBLE_WALL = BLOCKS.register("jungle_cobble_wall", () -> new WallBlock(ModProperties.JUNGLE_ROCK_PROPERTY));
    public static final RegistryObject<WallBlock> JUNGLE_ROCK_BRICKS_WALL = BLOCKS.register("jungle_rock_bricks_wall", () -> new WallBlock(ModProperties.JUNGLE_ROCK_PROPERTY));
    public static final RegistryObject<WallBlock> JUNGLE_ROCK_BRICKS_MOSSY_WALL = BLOCKS.register("jungle_rock_bricks_mossy_wall", () -> new WallBlock(ModProperties.JUNGLE_ROCK_PROPERTY));
    public static final RegistryObject<WallBlock> JUNGLE_COBBLE_MOSSY_WALL = BLOCKS.register("jungle_cobble_mossy_wall", () -> new WallBlock(ModProperties.JUNGLE_ROCK_PROPERTY));


    //Plants
    public static final RegistryObject<Block> ALOE_VERA = BLOCKS.register("aloe_vera", () -> new AloeVeraOrAgaveBlock( ModProperties.ALOE_VERA_OR_AGAVE_PROPERTY));
    public static final RegistryObject<Block> AGAVE = BLOCKS.register("agave", () -> new AloeVeraOrAgaveBlock(ModProperties.ALOE_VERA_OR_AGAVE_PROPERTY));
    public static final RegistryObject<Block> TALL_REED = BLOCKS.register("tall_reed", () -> new DoubleReedPlantBlock(ModProperties.REED_PLANT_PROPERTY));
    public static final RegistryObject<Block> TOP_REED = BLOCKS.register("top_reed", () -> new ExtraReedBlock( ModProperties.REED_PLANT_PROPERTY));
    public static final RegistryObject<Block> DRY_TALL_REED = BLOCKS.register("dry_tall_reed", () -> new DriedDoubleReedPlantBlock(ModProperties.REED_PLANT_PROPERTY));
    public static final RegistryObject<Block> DRY_TOP_REED = BLOCKS.register("dry_top_reed", () -> new DriedExtraReedBlock( ModProperties.REED_PLANT_PROPERTY));
    public static final RegistryObject<Block> REED_BALE = BLOCKS.register("reed_bale", () -> new RotatedPillarBlock( Block.Properties.from(Blocks.HAY_BLOCK).harvestTool(ToolType.AXE)));
    public static final RegistryObject<Block> DRY_REED_BALE = BLOCKS.register("dry_reed_bale", () -> new RotatedPillarBlock( Block.Properties.from(Blocks.HAY_BLOCK).harvestTool(ToolType.AXE)));
    public static final RegistryObject<Block> LEAVES_PILE = BLOCKS.register("leaves_pile", () -> new LeavesPileBlock(ModProperties.LEAVES_PROPERTY));
    public static final RegistryObject<Block> BRAMBLE_FULL = BLOCKS.register("bramble_full", () -> new BrambleFullBlock( Block.Properties.from(Blocks.SWEET_BERRY_BUSH)));
    public static final RegistryObject<Block> BRAMBLE_BUSH = BLOCKS.register("bramble_bush", () -> new BrambleBushBlock( Block.Properties.from(Blocks.SWEET_BERRY_BUSH)));
    public static final RegistryObject<Block> FALLEN_LEAVES = BLOCKS.register("fallen_leaves", () -> new FallenLeavesBlock(ModProperties.LEAVES_PROPERTY));
    public static final RegistryObject<Block> AUTUMN_FALLEN_LEAVES = BLOCKS.register("autumn_fallen_leaves", () -> new FallenLeavesBlock(ModProperties.LEAVES_PROPERTY));
    public static final RegistryObject<Block> AUTUMN_LEAVES_PILE = BLOCKS.register("autumn_leaves_pile", () -> new LeavesPileBlock(ModProperties.LEAVES_PROPERTY));

    //wood
    public static final RegistryObject<Block> DEAD_LOG = BLOCKS.register("dead_log", () -> new RotatedPillarBlock(ModProperties.LOG_PROPERTY));
    public static final RegistryObject<Block> DEAD_LEAVES = BLOCKS.register("dead_leaves", () -> new LeavesBlock(ModProperties.LEAVES_PROPERTY));

    public static final RegistryObject<Block> DRAGON_LOG = BLOCKS.register("dragon_log", () -> new RotatedPillarBlock(ModProperties.LOG_PROPERTY));
    public static final RegistryObject<Block> DRAGON_LEAVES = BLOCKS.register("dragon_leaves", () -> new LeavesBlock(ModProperties.LEAVES_PROPERTY));

}
