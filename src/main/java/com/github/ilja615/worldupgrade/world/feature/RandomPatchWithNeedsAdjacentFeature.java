package com.github.ilja615.worldupgrade.world.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;

public class RandomPatchWithNeedsAdjacentFeature extends Feature<RandomPatchConfiguration>
{
    public RandomPatchWithNeedsAdjacentFeature(Codec<RandomPatchConfiguration> p_66605_) {
        super(p_66605_);
    }

    public boolean place(FeaturePlaceContext<RandomPatchConfiguration> p_160210_)
    {
        RandomPatchConfiguration randompatchconfiguration = p_160210_.config();
        RandomSource randomsource = p_160210_.random();
        BlockPos blockpos = p_160210_.origin();
        WorldGenLevel worldgenlevel = p_160210_.level();
        int i = 0;
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
        int j = randompatchconfiguration.xzSpread() + 1;
        int k = randompatchconfiguration.ySpread() + 1;

        for(int l = 0; l < randompatchconfiguration.tries(); ++l) {
            blockpos$mutableblockpos.setWithOffset(blockpos, randomsource.nextInt(j) - randomsource.nextInt(j), randomsource.nextInt(k) - randomsource.nextInt(k), randomsource.nextInt(j) - randomsource.nextInt(j));

            // Check if it has at least one good neighbor
            boolean flag = false;
            for (Direction direction : Direction.values())
            {
                if (!worldgenlevel.isEmptyBlock(blockpos$mutableblockpos.relative(direction)) && worldgenlevel.getBlockState(blockpos$mutableblockpos.relative(direction)).isCollisionShapeFullBlock(worldgenlevel, blockpos$mutableblockpos.relative(direction)))
                {
                    flag = true;
                    break;
                }
            }
            if (flag)
            {
                if (randompatchconfiguration.feature().value().place(worldgenlevel, p_160210_.chunkGenerator(), randomsource, blockpos$mutableblockpos)) {
                    ++i;
                }
            }
        }

        return i > 0;
    }
}