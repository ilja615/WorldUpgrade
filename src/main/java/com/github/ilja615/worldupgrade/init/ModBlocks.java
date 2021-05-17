package com.github.ilja615.worldupgrade.init;

import com.github.ilja615.worldupgrade.WorldUpgrade;
import com.github.ilja615.worldupgrade.blocks.*;
import com.google.common.collect.ImmutableSet;
import net.minecraft.block.*;
import net.minecraft.block.GravelBlock;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Set;

public class ModBlocks
{
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, WorldUpgrade.MOD_ID);

    // Terrain Blocks:
    public static final RegistryObject<Block> GRAVEL_DARK = BLOCKS.register("gravel_dark", () -> new GravelBlock(ModProperties.GRAVEL_PROPERTY));
    public static final RegistryObject<Block> GRAVEL_LIGHT = BLOCKS.register("gravel_light", () -> new GravelBlock(ModProperties.GRAVEL_PROPERTY));
    public static final RegistryObject<Block> GRASSY_CLAY = BLOCKS.register("grassy_clay", () -> new GrassyBlockBase(ModProperties.GRASSY_CLAY_PROPERTY));
    public static final RegistryObject<Block> REGOLITH = BLOCKS.register("regolith", () -> new RegolithBlock(ModProperties.STONE_PROPERTY));
    public static final RegistryObject<Block> GRASSY_STONE = BLOCKS.register("grassy_stone", () -> new GrassyBlockBase(ModProperties.GRASSY_STONE_PROPERTY));
    public static final RegistryObject<Block> SMOKE_VENT = BLOCKS.register("smoke_vent", () -> new SmokeVentBlock(ModProperties.VOLCANIC_STUFF_PROPERTY));
    public static final RegistryObject<Block> FUMAROLE = BLOCKS.register("fumarole", () -> new FumaroleBlock(ModProperties.VOLCANIC_STUFF_PROPERTY));
    public static final RegistryObject<Block> COARSE_SAND = BLOCKS.register("coarse_sand", () -> new CoarseSandBlock(ModProperties.SAND_PROPERTY));
    public static final RegistryObject<Block> ASH_DIRT = BLOCKS.register("ash_dirt", () -> new GrassyBlockBase(Block.Properties.from(Blocks.DIRT).tickRandomly()));
    public static final RegistryObject<Block> ASH_BLOCK = BLOCKS.register("ash_block", () -> new Block(Block.Properties.from(Blocks.DIRT)));
    public static final RegistryObject<Block> BOULDER = BLOCKS.register("boulder", () -> new RegolithBlock(ModProperties.STONE_PROPERTY));
    public static final RegistryObject<Block> DENSE_BOULDER = BLOCKS.register("dense_boulder", () -> new Block(ModProperties.STONE_PROPERTY));
    public static final RegistryObject<Block> PEAT = BLOCKS.register("peat", () -> new PeatBlock(Block.Properties.from(Blocks.CLAY)));
    public static final RegistryObject<Block> MOSSY_BOULDER = BLOCKS.register("mossy_boulder", () -> new RegolithBlock(ModProperties.STONE_PROPERTY));
    public static final RegistryObject<Block> MOSSY_DENSE_BOULDER = BLOCKS.register("mossy_dense_boulder", () -> new Block(ModProperties.STONE_PROPERTY));

    // JUngle Rocks
    public static final RegistryObject<Block> JUNGLE_ROCK = BLOCKS.register("jungle_rock", () -> new Block(ModProperties.JUNGLE_ROCK_PROPERTY));
    public static final RegistryObject<Block> JUNGLE_ROCK_OVERGROWN = BLOCKS.register("jungle_rock_overgrown", () -> new GrassyBlockBase(ModProperties.OVERGROWN_JUNGLE_ROCK_PROPERTY));
    public static final RegistryObject<Block> JUNGLE_FOSSIL = BLOCKS.register("jungle_fossil", () -> new JungleFossilBlock(ModProperties.JUNGLE_ROCK_PROPERTY));
    public static final RegistryObject<Block> JUNGLE_COBBLE = BLOCKS.register("jungle_cobble", () -> new Block(ModProperties.JUNGLE_COBBLE_PROPERTY));
    public static final RegistryObject<Block> JUNGLE_ROCK_BRICKS = BLOCKS.register("jungle_rock_bricks", () -> new Block(ModProperties.JUNGLE_COBBLE_PROPERTY));
    public static final RegistryObject<Block> JUNGLE_ROCK_BRICKS_MOSSY = BLOCKS.register("jungle_rock_bricks_mossy", () -> new Block(ModProperties.JUNGLE_COBBLE_PROPERTY));
    public static final RegistryObject<Block> JUNGLE_ROCK_BRICKS_CRACKED = BLOCKS.register("jungle_rock_bricks_cracked", () -> new Block(ModProperties.JUNGLE_COBBLE_PROPERTY));
    public static final RegistryObject<Block> JUNGLE_COBBLE_MOSSY = BLOCKS.register("jungle_cobble_mossy", () -> new Block(ModProperties.JUNGLE_COBBLE_PROPERTY));
    public static final RegistryObject<Block> JUNGLE_ROCK_PILLAR = BLOCKS.register("jungle_rock_pillar", () -> new RotatedPillarBlock(ModProperties.JUNGLE_COBBLE_PROPERTY));
    public static final RegistryObject<StairsBlock> JUNGLE_ROCK_STAIRS = BLOCKS.register("jungle_rock_stairs", () -> new StairsBlock(() -> JUNGLE_ROCK.get().getDefaultState(), ModProperties.JUNGLE_ROCK_PROPERTY));
    public static final RegistryObject<StairsBlock> JUNGLE_COBBLE_STAIRS = BLOCKS.register("jungle_cobble_stairs", () -> new StairsBlock(() -> JUNGLE_ROCK.get().getDefaultState(), ModProperties.JUNGLE_ROCK_PROPERTY));
    public static final RegistryObject<StairsBlock> JUNGLE_ROCK_BRICKS_STAIRS = BLOCKS.register("jungle_rock_bricks_stairs", () -> new StairsBlock(() -> JUNGLE_ROCK.get().getDefaultState(), ModProperties.JUNGLE_ROCK_PROPERTY));
    public static final RegistryObject<StairsBlock> JUNGLE_ROCK_BRICKS_MOSSY_STAIRS = BLOCKS.register("jungle_rock_bricks_mossy_stairs", () -> new StairsBlock(() -> JUNGLE_ROCK.get().getDefaultState(), ModProperties.JUNGLE_ROCK_PROPERTY));
    public static final RegistryObject<StairsBlock> JUNGLE_COBBLE_MOSSY_STAIRS = BLOCKS.register("jungle_cobble_mossy_stairs", () -> new StairsBlock(() -> JUNGLE_ROCK.get().getDefaultState(), ModProperties.JUNGLE_ROCK_PROPERTY));
    public static final RegistryObject<SlabBlock> JUNGLE_ROCK_SLAB = BLOCKS.register("jungle_rock_slab", () -> new SlabBlock(ModProperties.JUNGLE_ROCK_PROPERTY));
    public static final RegistryObject<SlabBlock> JUNGLE_COBBLE_SLAB = BLOCKS.register("jungle_cobble_slab", () -> new SlabBlock(ModProperties.JUNGLE_ROCK_PROPERTY));
    public static final RegistryObject<SlabBlock> JUNGLE_ROCK_BRICKS_SLAB = BLOCKS.register("jungle_rock_bricks_slab", () -> new SlabBlock(ModProperties.JUNGLE_ROCK_PROPERTY));
    public static final RegistryObject<SlabBlock> JUNGLE_ROCK_BRICKS_MOSSY_SLAB = BLOCKS.register("jungle_rock_bricks_mossy_slab", () -> new SlabBlock(ModProperties.JUNGLE_ROCK_PROPERTY));
    public static final RegistryObject<SlabBlock> JUNGLE_COBBLE_MOSSY_SLAB = BLOCKS.register("jungle_cobble_mossy_slab", () -> new SlabBlock(ModProperties.JUNGLE_ROCK_PROPERTY));
    public static final RegistryObject<WallBlock> JUNGLE_COBBLE_WALL = BLOCKS.register("jungle_cobble_wall", () -> new WallBlock(ModProperties.JUNGLE_ROCK_PROPERTY));
    public static final RegistryObject<WallBlock> JUNGLE_ROCK_BRICKS_WALL = BLOCKS.register("jungle_rock_bricks_wall", () -> new WallBlock(ModProperties.JUNGLE_ROCK_PROPERTY));
    public static final RegistryObject<WallBlock> JUNGLE_ROCK_BRICKS_MOSSY_WALL = BLOCKS.register("jungle_rock_bricks_mossy_wall", () -> new WallBlock(ModProperties.JUNGLE_ROCK_PROPERTY));
    public static final RegistryObject<WallBlock> JUNGLE_COBBLE_MOSSY_WALL = BLOCKS.register("jungle_cobble_mossy_wall", () -> new WallBlock(ModProperties.JUNGLE_ROCK_PROPERTY));
    public static final RegistryObject<WallBlock> JUNGLE_ROCK_WALL = BLOCKS.register("jungle_rock_wall", () -> new WallBlock(ModProperties.JUNGLE_ROCK_PROPERTY));

    //Plants
    public static final RegistryObject<Block> AGAVE = BLOCKS.register("agave", () -> new AgavePlantBlock(ModProperties.AGAVE_PROPERTY));
    public static final RegistryObject<Block> ALOE_VERY = BLOCKS.register("aloe_vera", () -> new AgavePlantBlock(ModProperties.AGAVE_PROPERTY));
    public static final RegistryObject<Block> TALL_REED = BLOCKS.register("tall_reed", () -> new DoubleReedPlantBlock(ModProperties.REED_PLANT_PROPERTY));
    public static final RegistryObject<Block> TOP_REED = BLOCKS.register("top_reed", () -> new ExtraReedBlock(ModProperties.REED_PLANT_PROPERTY));
    public static final RegistryObject<Block> DRY_TALL_REED = BLOCKS.register("dry_tall_reed", () -> new DriedDoubleReedPlantBlock(ModProperties.REED_PLANT_PROPERTY));
    public static final RegistryObject<Block> DRY_TOP_REED = BLOCKS.register("dry_top_reed", () -> new DriedExtraReedBlock(ModProperties.REED_PLANT_PROPERTY));
    public static final RegistryObject<Block> LEAVES_PILE = BLOCKS.register("leaves_pile", () -> new LeavesPileBlock(ModProperties.LEAVES_PROPERTY));
    public static final RegistryObject<Block> BRAMBLE_FULL = BLOCKS.register("bramble_full", () -> new BrambleFullBlock(Block.Properties.from(Blocks.SWEET_BERRY_BUSH).tickRandomly()));
    public static final RegistryObject<Block> BRAMBLE_BUSH = BLOCKS.register("bramble_bush", () -> new BrambleBushBlock(Block.Properties.from(Blocks.SWEET_BERRY_BUSH).tickRandomly()));
    public static final RegistryObject<Block> FALLEN_LEAVES = BLOCKS.register("fallen_leaves", () -> new FallenLeavesBlock(ModProperties.LEAVES_PROPERTY));
    public static final RegistryObject<Block> AUTUMN_FALLEN_LEAVES = BLOCKS.register("autumn_fallen_leaves", () -> new FallenLeavesBlock(ModProperties.LEAVES_PROPERTY));
    public static final RegistryObject<Block> AUTUMN_LEAVES_PILE = BLOCKS.register("autumn_leaves_pile", () -> new LeavesPileBlock(ModProperties.LEAVES_PROPERTY));
    public static final RegistryObject<Block> TALL_DEAD_BUSH = BLOCKS.register("tall_dead_bush", () -> new TallDeadBushBlock(Block.Properties.from(Blocks.DEAD_BUSH)));
    public static final RegistryObject<Block> TALL_CHARRED_BUSH = BLOCKS.register("tall_charred_bush", () -> new TallDeadBushBlock(Block.Properties.from(Blocks.DEAD_BUSH)));
    public static final RegistryObject<Block> CHARRED_BUSH = BLOCKS.register("charred_bush", () -> new DeadBushBlock(Block.Properties.from(Blocks.DEAD_BUSH)));
    public static final RegistryObject<Block> GUNNERA_STEM = BLOCKS.register("gunnera_stem", () -> new RotatedPillarBlock(ModProperties.BIG_PLANT_OR_GUNNERA_PROPERTY));
    public static final RegistryObject<Block> BIG_PLANT_LEAF = BLOCKS.register("big_plant_leaf", () -> new BigPlantLeafBlock(ModProperties.BIG_PLANT_OR_GUNNERA_PROPERTY));
    public static final RegistryObject<Block> BIG_PLANT_STEM = BLOCKS.register("big_plant_stem", () -> new RotatedPillarBlock(ModProperties.BIG_PLANT_OR_GUNNERA_PROPERTY));
    public static final RegistryObject<Block> LILY_PAD = BLOCKS.register("lily_pad", () -> new Block(ModProperties.BIG_WATER_LILY_PROPERTY));
    public static final RegistryObject<Block> LILY_STEM = BLOCKS.register("lily_stem", () -> new RotatedPillarBlock(ModProperties.BIG_WATER_LILY_PROPERTY));
    public static final RegistryObject<Block> FLOWERPETALBLOCK_LIGHTYELLOW = BLOCKS.register("flowerpetalblock_lightyellow", () -> new Block(ModProperties.LEAVES_PROPERTY));
    public static final RegistryObject<Block> FLOWERPETALBLOCK_LIGHTPINK = BLOCKS.register("flowerpetalblock_lightpink", () -> new Block(ModProperties.LEAVES_PROPERTY));
    public static final RegistryObject<Block> CHAMAENERION = BLOCKS.register("chamaenerion", () -> new DoublePlantBlock(ModProperties.FLOWER_PROPERTY));
//    public static final RegistryObject<Block> GUNNERA_FLOWER = BLOCKS.register("gunnera_flower", () -> new BushBlock(ModProperties.FLOWER_PROPERTY));
    public static final RegistryObject<Block> GUNNERA_TALLFLOWER = BLOCKS.register("tall_gunnera_flower", () -> new DoublePlantBlock(ModProperties.FLOWER_PROPERTY));
    public static final RegistryObject<Block> NETTLE = BLOCKS.register("nettle", () -> new BushBlock(ModProperties.FLOWER_PROPERTY));
    public static final RegistryObject<Block> LARGE_NETTLE = BLOCKS.register("large_nettle", () -> new DoublePlantBlock(ModProperties.FLOWER_PROPERTY));
    public static final RegistryObject<Block> SPHAGNUM_MOSS = BLOCKS.register("sphagnum_moss", () -> new MossBlock(Block.Properties.from(Blocks.VINE).tickRandomly()));

    // Plant crafted blocks
    public static final RegistryObject<Block> REED_BALE = BLOCKS.register("reed_bale", () -> new RotatedPillarBlock(Block.Properties.from(Blocks.HAY_BLOCK).harvestTool(ToolType.HOE)));
    public static final RegistryObject<Block> DRY_REED_BALE = BLOCKS.register("dry_reed_bale", () -> new RotatedPillarBlock(Block.Properties.from(Blocks.HAY_BLOCK).harvestTool(ToolType.HOE)));
    public static final RegistryObject<Block> DRY_REED_THATCH = BLOCKS.register("dry_reed_thatch", () -> new ThatchBlock(Block.Properties.from(Blocks.HAY_BLOCK).tickRandomly().harvestTool(ToolType.HOE)));
    public static final RegistryObject<Block> DRY_REED_THATCH_SLAB = BLOCKS.register("dry_reed_thatch_slab", () -> new ThatchSlabBlock(Block.Properties.from(Blocks.HAY_BLOCK).tickRandomly().harvestTool(ToolType.HOE)));
    public static final RegistryObject<Block> DRY_REED_THATCH_STAIRS = BLOCKS.register("dry_reed_thatch_stairs", () -> new ThatchStairsBlock(DRY_REED_THATCH.get().getDefaultState(), Block.Properties.from(Blocks.HAY_BLOCK).tickRandomly().harvestTool(ToolType.HOE)));

    // Other Blocks
    public static final RegistryObject<Block> INTERTWINED_TWIGS_BLOCK = BLOCKS.register("intertwined_twigs_block", () -> new Block(ModProperties.LOG_PROPERTY));

    //wood
    public static final RegistryObject<Block> CHARRED_LOG = BLOCKS.register("charred_log", () -> new RotatedPillarBlock(ModProperties.LOG_PROPERTY));
    public static final RegistryObject<Block> CHARRED_LEAVES = BLOCKS.register("charred_leaves", () -> new LeavesBlock(ModProperties.LEAVES_PROPERTY));
    public static final RegistryObject<Block> DEAD_LEAVES = BLOCKS.register("dead_leaves", () -> new LeavesBlock(ModProperties.LEAVES_PROPERTY));

    public static final RegistryObject<Block> DRAGON_LOG = BLOCKS.register("dragon_log", () -> new RotatedPillarBlock(ModProperties.LOG_PROPERTY));
    public static final RegistryObject<Block> DRAGON_LEAVES = BLOCKS.register("dragon_leaves", () -> new LeavesBlock(ModProperties.LEAVES_PROPERTY));


    // list stuff
    public static Set<Block> CARVABLE_BLOCKS;

    public static void initializeLists()
    {
        CARVABLE_BLOCKS = ImmutableSet.of( Blocks.CLAY, GRASSY_CLAY.get(), GRASSY_STONE.get(), JUNGLE_COBBLE.get(), JUNGLE_ROCK.get(), JUNGLE_ROCK_OVERGROWN.get(),
                GRAVEL_DARK.get(),ASH_DIRT.get(), ASH_BLOCK.get(), PEAT.get(),
                REGOLITH.get(), COARSE_SAND.get(), BOULDER.get(), DENSE_BOULDER.get(), MOSSY_BOULDER.get(), MOSSY_DENSE_BOULDER.get());
    }
}
