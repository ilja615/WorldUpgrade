package com.github.ilja615.worldupgrade.world.features;

import com.github.ilja615.worldupgrade.blocks.CoarseSandBlock;
import com.github.ilja615.worldupgrade.blocks.RegolithBlock;
import com.github.ilja615.worldupgrade.init.ModBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.NoiseChunkGenerator;
import net.minecraft.world.gen.feature.BlockStateFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

import java.util.Iterator;
import java.util.Random;

public class RockWithDenseBuilderFeature extends Feature<BlockStateFeatureConfig> {
    private static final BlockState DENSE_BOULDER = ModBlocks.DENSE_BOULDER.get().getDefaultState();
    private static final BlockState MOSSY_DENSE_BOULDER = ModBlocks.MOSSY_DENSE_BOULDER.get().getDefaultState();
    private static final BlockState MOSSY_BOULDER = ModBlocks.MOSSY_BOULDER.get().getDefaultState();

    public RockWithDenseBuilderFeature(Codec<BlockStateFeatureConfig> p_i231931_1_) {
        super(p_i231931_1_);
    }

    public boolean generate(ISeedReader iSeedReader, ChunkGenerator chunkGenerator, Random rand, BlockPos pos, BlockStateFeatureConfig config)
    {
        for(; pos.getY() > 8; pos = pos.down()) {
            if (!iSeedReader.isAirBlock(pos.down())) {
                Block lvt_6_1_ = iSeedReader.getBlockState(pos.down()).getBlock();
                if (isDirt(lvt_6_1_) || isStone(lvt_6_1_) || lvt_6_1_ instanceof CoarseSandBlock || lvt_6_1_ instanceof RegolithBlock) {
                    break;
                }
            }
        }
        if (pos.getY() <= 8) {
            return false;
        } else {
            for(int lvt_6_2_ = 0; lvt_6_2_ < 4; ++lvt_6_2_) {
                int lvt_7_1_ = 1 + rand.nextInt(2);
                int lvt_8_1_ = 1 + rand.nextInt(3);
                int lvt_9_1_ = 1 + rand.nextInt(2);
                float lvt_10_1_ = (float)(lvt_7_1_ + lvt_8_1_ + lvt_9_1_) * 0.3F + 0.5F;
                Iterator var11 = BlockPos.getAllInBoxMutable(pos.add(-lvt_7_1_, -lvt_8_1_, -lvt_9_1_), pos.add(lvt_7_1_, lvt_8_1_, lvt_9_1_)).iterator();
                boolean mossy = rand.nextBoolean();

                while(var11.hasNext()) {
                    BlockPos lvt_12_1_ = (BlockPos)var11.next();
                    if (lvt_12_1_.distanceSq(pos) <= (double)(lvt_10_1_ * lvt_10_1_)) {
                        boolean hi = rand.nextFloat() > 0.8f;
                        iSeedReader.setBlockState(lvt_12_1_, rand.nextFloat() * (pos.getY() - lvt_12_1_.getY() + 0.5f) > 0.5f ? (mossy && hi? MOSSY_DENSE_BOULDER : DENSE_BOULDER) : (mossy && hi? MOSSY_BOULDER : config.state), 4);
                    }
                }
                pos = pos.add(-1 + rand.nextInt(2), -rand.nextInt(2), -1 + rand.nextInt(2));
            }
            return true;
        }
    }
}
