package ilja615.worldupgrade.world.surfacebuilding;

import com.mojang.datafixers.Dynamic;
import ilja615.worldupgrade.init.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

import java.util.Random;
import java.util.function.Function;

public class OvergrownPeeksSurfaceBuilder extends SurfaceBuilder<SurfaceBuilderConfig>
{
    private static final BlockState JUNGLE_ROCK = ModBlocks.JUNGLE_ROCK.get().getDefaultState();
    private static final BlockState OVERGROWN = ModBlocks.JUNGLE_ROCK_OVERGROWN.get().getDefaultState();
    private static final BlockState GRASS = Blocks.GRASS_BLOCK.getDefaultState();
    private static final BlockState SAND = Blocks.SAND.getDefaultState();
    private static final BlockState JUNGLE_COBBLE = ModBlocks.JUNGLE_COBBLE.get().getDefaultState();
    private static final BlockState JUNGLE_FOSSIL = ModBlocks.JUNGLE_FOSSIL.get().getDefaultState();


    public OvergrownPeeksSurfaceBuilder(Function<Dynamic<?>, ? extends SurfaceBuilderConfig> deserializer)
    {
        super(deserializer);
    }

    @Override
    public void buildSurface(Random random, IChunk chunkIn, Biome biomeIn, int x, int z, int startHeight, double noise, BlockState defaultBlock, BlockState defaultFluid, int seaLevel, long seed, SurfaceBuilderConfig config)
    {
        if (random.nextBoolean())
            this.buildOverGrownPeeksSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, GRASS, JUNGLE_ROCK, SAND, seaLevel, 8);
        else
            this.buildOverGrownPeeksSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, OVERGROWN, JUNGLE_ROCK, SAND, seaLevel, 8);
    }

    private void buildOverGrownPeeksSurface(Random random, IChunk chunkIn, Biome biomeIn, int x, int z, int startHeight, double noise, BlockState defaultBlock, BlockState defaultFluid, BlockState topMaterial, BlockState underMaterial, BlockState underWaterMaterial, int seaLevel, int topLayerThickness) {
        BlockState lvt_15_1_ = topMaterial;
        BlockState lvt_16_1_;
        lvt_16_1_ = underMaterial;
        BlockPos.Mutable lvt_17_1_ = new BlockPos.Mutable();
        int lvt_18_1_ = -1;
        int lvt_19_1_ = (int)(noise / 3.0D + topLayerThickness + random.nextDouble() * 0.25D); // Default top layer thickness is 3
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
                        lvt_15_1_ = topMaterial;
                        lvt_16_1_ = underMaterial;
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
                        placementBlock(chunkIn, lvt_17_1_, underWaterMaterial, random);
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
        if (state == JUNGLE_ROCK)
        {
            if (chunkIn.getBlockState(pos.up()) == Blocks.WATER.getDefaultState())
            {
                chunkIn.setBlockState(pos, SAND, false);
            } else {
                r = random.nextDouble();
                if (r > 0.4D)
                    chunkIn.setBlockState(pos, JUNGLE_ROCK, false);
                else if (r > 0.01D)
                    chunkIn.setBlockState(pos, JUNGLE_COBBLE, false);
                else
                    chunkIn.setBlockState(pos, JUNGLE_FOSSIL, false);
            }
        }
        else
            chunkIn.setBlockState(pos, state, false);
    }

}
