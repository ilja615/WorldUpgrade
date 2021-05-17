package com.github.ilja615.worldupgrade.world.features;

import com.github.ilja615.worldupgrade.blocks.CoarseSandBlock;
import com.github.ilja615.worldupgrade.blocks.RegolithBlock;
import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.BlockStateFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

import java.util.Iterator;
import java.util.Random;

public class RockFeature extends Feature<BlockStateFeatureConfig> {
    public RockFeature(Codec<BlockStateFeatureConfig> p_i231931_1_) {
        super(p_i231931_1_);
    }

    public boolean place(ISeedReader iSeedReader, ChunkGenerator chunkGenerator, Random rand, BlockPos pos, BlockStateFeatureConfig config) {
        for(; pos.getY() > 3; pos = pos.below()) {
            if (!iSeedReader.isEmptyBlock(pos.below())) {
                Block lvt_6_1_ = iSeedReader.getBlockState(pos.below()).getBlock();
                if (isDirt(lvt_6_1_) || isStone(lvt_6_1_) || lvt_6_1_ instanceof CoarseSandBlock || lvt_6_1_ instanceof RegolithBlock) {
                    break;
                }
            }
        }

        if (pos.getY() <= 3) {
            return false;
        } else {
            for(int lvt_6_2_ = 0; lvt_6_2_ < 3; ++lvt_6_2_) {
                int lvt_7_1_ = rand.nextInt(3);
                int lvt_8_1_ = rand.nextInt(4);
                int lvt_9_1_ = rand.nextInt(3);
                float lvt_10_1_ = (float)(lvt_7_1_ + lvt_8_1_ + lvt_9_1_) * 0.4F + 0.5F;
                Iterator var11 = BlockPos.betweenClosed(pos.offset(-lvt_7_1_, -lvt_8_1_, -lvt_9_1_), pos.offset(lvt_7_1_, lvt_8_1_, lvt_9_1_)).iterator();

                while(var11.hasNext()) {
                    BlockPos lvt_12_1_ = (BlockPos)var11.next();
                    if (lvt_12_1_.distSqr(pos) <= (double)(lvt_10_1_ * lvt_10_1_)) {
                        iSeedReader.setBlock(lvt_12_1_, config.state, 4);
                    }
                }

                pos = pos.offset(-1 + rand.nextInt(2), -rand.nextInt(2), -1 + rand.nextInt(2));
            }

            return true;
        }
    }
}
