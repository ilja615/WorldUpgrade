//package ilja615.worldupgrade.datagen;
//
//import ilja615.worldupgrade.WorldUpgrade;
//import ilja615.worldupgrade.init.ModBlocks;
//import net.minecraft.block.SlabBlock;
//import net.minecraft.block.WallBlock;
//import net.minecraft.data.DataGenerator;
//import net.minecraft.data.IDataProvider;
//import net.minecraft.util.ResourceLocation;
//import net.minecraftforge.client.model.generators.BlockStateProvider;
//import net.minecraftforge.client.model.generators.ExistingFileHelper;
////-XstartOnFirstThread
//public class BlockStates extends BlockStateProvider
//{
//    public BlockStates(DataGenerator gen, ExistingFileHelper exFileHelper)
//    {
//        super(gen, WorldUpgrade.MOD_ID, exFileHelper);
//    }
//
//    @Override
//    protected void registerStatesAndModels()
//    {
//        this.slabBlock(ModBlocks.JUNGLE_COBBLE_SLAB.get(), new ResourceLocation( "worldupgrade:textures/block/jungle_cobble.png"), new ResourceLocation( "worldupgrade:textures/block/jungle_cobble.png"));
//        this.slabBlock(ModBlocks.JUNGLE_ROCK_SLAB.get(), new ResourceLocation( "worldupgrade:textures/block/jungle_rock.png"), new ResourceLocation( "worldupgrade:textures/block/jungle_rock.png"));
//        this.slabBlock(ModBlocks.JUNGLE_ROCK_BRICKS_MOSSY_SLAB.get(), new ResourceLocation( "worldupgrade:textures/block/jungle_rock_bricks_mossy.png"), new ResourceLocation( "worldupgrade:textures/block/jungle_rock_bricks_mossy.png"));
//        this.slabBlock(ModBlocks.JUNGLE_ROCK_BRICKS_SLAB.get(), new ResourceLocation( "worldupgrade:textures/block/jungle_rock_bricks.png"), new ResourceLocation( "worldupgrade:textures/block/jungle_rock_bricks.png"));
//        this.slabBlock(ModBlocks.JUNGLE_COBBLE_MOSSY_SLAB.get(), new ResourceLocation( "worldupgrade:textures/block/jungle_cobble_mossy.png"), new ResourceLocation( "worldupgrade:textures/block/jungle_cobble_mossy.png"));
//
//        this.wallBlock(ModBlocks.JUNGLE_COBBLE_WALL.get(), new ResourceLocation( "worldupgrade:textures/block/jungle_cobble.png"));
//        this.wallBlock(ModBlocks.JUNGLE_COBBLE_MOSSY_WALL.get(), new ResourceLocation( "worldupgrade:textures/block/jungle_cobble_mossy.png"));
//        this.wallBlock(ModBlocks.JUNGLE_ROCK_BRICKS_WALL.get(), new ResourceLocation( "worldupgrade:textures/block/jungle_rock_bricks.png"));
//        this.wallBlock(ModBlocks.JUNGLE_ROCK_BRICKS_MOSSY_WALL.get(), new ResourceLocation( "worldupgrade:textures/block/jungle_rock_bricks_mossy.png"));
//
//    }
//}
