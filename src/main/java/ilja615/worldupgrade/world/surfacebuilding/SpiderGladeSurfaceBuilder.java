package ilja615.worldupgrade.world.surfacebuilding;

import com.mojang.datafixers.Dynamic;
import ilja615.worldupgrade.init.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.surfacebuilders.DefaultSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

import java.util.Random;
import java.util.function.Function;

public class SpiderGladeSurfaceBuilder extends SurfaceBuilder<SurfaceBuilderConfig>
{
    private static final BlockState SILKIBLOCK = ModBlocks.SILKI_BLOCK.getDefaultState();
    private static final BlockState SILKIBLOCK_EXTRA = ModBlocks.SILKI_BLOCK_EXTRA.getDefaultState();
    private static final BlockState GRASSY_STONE = ModBlocks.GRASSY_STONE.getDefaultState();

    public static final SurfaceBuilderConfig SPIDERGLADESURFACEBUIDERCONFIG = new SurfaceBuilderConfig(GRASS_BLOCK, GRAVEL, SILKIBLOCK);

    public SpiderGladeSurfaceBuilder(Function<Dynamic<?>, ? extends SurfaceBuilderConfig> deserializer)
    {
        super(deserializer);
    }

    @Override
    public void buildSurface(Random random, IChunk chunkIn, Biome biomeIn, int x, int z, int startHeight, double noise, BlockState defaultBlock, BlockState defaultFluid, int seaLevel, long seed, SurfaceBuilderConfig config)
    {
        //SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, BRYANS_FAVORITE_CONFIG);

        BlockState lvt_15_1_ = GRASSY_STONE;
        BlockState lvt_16_1_;
        lvt_16_1_ = SILKIBLOCK;
        BlockPos.Mutable lvt_17_1_ = new BlockPos.Mutable();
        int lvt_18_1_ = -1;
        int lvt_19_1_ = (int)(noise / 3.0D + 45 + random.nextDouble() * 0.25D); // Default top layer thickness is 3
        int lvt_20_1_ = x & 15;
        int lvt_21_1_ = z & 15;

        for(int lvt_22_1_ = startHeight; lvt_22_1_ >= 0; --lvt_22_1_) {
            lvt_17_1_.setPos(lvt_20_1_, lvt_22_1_, lvt_21_1_);
            BlockState lvt_23_1_ = chunkIn.getBlockState(lvt_17_1_);
            if (lvt_23_1_.isAir()) {
                lvt_18_1_ = -1;
            } else if (lvt_23_1_.getBlock() == defaultBlock.getBlock()) {
                if (lvt_18_1_ == -1) {
                    if (lvt_19_1_ <= 0) {
                        lvt_15_1_ = Blocks.AIR.getDefaultState();
                        lvt_16_1_ = defaultBlock;
                    } else if (lvt_22_1_ >= seaLevel - 4 && lvt_22_1_ <= seaLevel + 1) {
                        lvt_15_1_ = GRASSY_STONE;
                        lvt_16_1_ = SILKIBLOCK;
                    }

                    if (lvt_22_1_ < seaLevel && (lvt_15_1_ == null || lvt_15_1_.isAir())) {
                        if (biomeIn.getTemperature(lvt_17_1_.setPos(x, lvt_22_1_, z)) < 0.15F) {
                            lvt_15_1_ = Blocks.ICE.getDefaultState();
                        } else {
                            lvt_15_1_ = defaultFluid;
                        }

                        lvt_17_1_.setPos(lvt_20_1_, lvt_22_1_, lvt_21_1_);
                    }

                    lvt_18_1_ = lvt_19_1_;
                    if (lvt_22_1_ >= seaLevel - 1) {
                        placementBlock(chunkIn, lvt_17_1_, lvt_15_1_, random);
                    } else if (lvt_22_1_ < seaLevel - 7 - lvt_19_1_) {
                        lvt_15_1_ = Blocks.AIR.getDefaultState();
                        lvt_16_1_ = defaultBlock;
                        placementBlock(chunkIn, lvt_17_1_, GRAVEL, random);
                    } else {
                        placementBlock(chunkIn, lvt_17_1_, lvt_16_1_, random);
                    }
                } else if (lvt_18_1_ > 0) {
                    --lvt_18_1_;
                    placementBlock(chunkIn, lvt_17_1_, lvt_16_1_, random);
                    if (lvt_18_1_ == 0 && lvt_16_1_.getBlock() == Blocks.SAND && lvt_19_1_ > 1) {
                        lvt_18_1_ = random.nextInt(4) + Math.max(0, lvt_22_1_ - 63);
                        lvt_16_1_ = lvt_16_1_.getBlock() == Blocks.RED_SAND ? Blocks.RED_SANDSTONE.getDefaultState() : Blocks.SANDSTONE.getDefaultState();
                    }
                }
            }
        }

    }

    private void placementBlock(IChunk chunkIn, BlockPos pos, BlockState state, Random random)
    {
        Double r;
        if (state == SILKIBLOCK)
        {
            if (chunkIn.getBlockState(pos.up()) == Blocks.WATER.getDefaultState())
            {
                chunkIn.setBlockState(pos, GRAVEL, false);
            } else {
                r = random.nextDouble();
                if (r > 0.3D)
                    chunkIn.setBlockState(pos, SILKIBLOCK, false);
                else
                    chunkIn.setBlockState(pos, SILKIBLOCK_EXTRA, false);
            }
        } else
            chunkIn.setBlockState(pos, state, false);
    }
}
