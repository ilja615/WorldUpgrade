package com.github.ilja615.worldupgrade.world.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.HugeMushroomBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.AbstractHugeMushroomFeature;
import net.minecraft.world.level.levelgen.feature.configurations.HugeMushroomFeatureConfiguration;

public class HugeBogBeaconMushroomFeature extends AbstractHugeMushroomFeature
{
    public HugeBogBeaconMushroomFeature(Codec<HugeMushroomFeatureConfiguration> p_65879_) {
        super(p_65879_);
    }

    protected void makeCap(LevelAccessor p_225043_, RandomSource p_225044_, BlockPos p_225045_, int p_225046_, BlockPos.MutableBlockPos p_225047_, HugeMushroomFeatureConfiguration p_225048_) {
        for(int h = 0; h <= 2*p_225048_.foliageRadius; ++h) {
            int i = (h == 0 || h == 2*p_225048_.foliageRadius) ? p_225048_.foliageRadius-1 : p_225048_.foliageRadius;
            for(int j = -i; j <= i; ++j) {
                for(int k = -i; k <= i; ++k) {
                    boolean flag = j == -i;
                    boolean flag1 = j == i;
                    boolean flag2 = k == -i;
                    boolean flag3 = k == i;
                    boolean isCornerX = flag || flag1;
                    boolean isCornerZ = flag2 || flag3;
                    if (!isCornerX || !isCornerZ) {
                        p_225047_.setWithOffset(p_225045_, j, p_225046_ + h, k);
                        if (!p_225043_.getBlockState(p_225047_).isSolidRender(p_225043_, p_225047_)) {
                            boolean flag6 = flag || isCornerZ && j == 1 - i;
                            boolean flag7 = flag1 || isCornerZ && j == i - 1;
                            boolean flag8 = flag2 || isCornerX && k == 1 - i;
                            boolean flag9 = flag3 || isCornerX && k == i - 1;
                            boolean flag10 = (h == 1 && Math.abs(j) + Math.abs(k) >= 2);
                            boolean flag11 = ((h == 2*p_225048_.foliageRadius-1 && Math.abs(j) + Math.abs(k) >= 2) || h == 2*p_225048_.foliageRadius);
                            BlockState blockstate = p_225048_.capProvider.getState(p_225044_, p_225045_);
                            if (blockstate.hasProperty(HugeMushroomBlock.WEST) && blockstate.hasProperty(HugeMushroomBlock.EAST) && blockstate.hasProperty(HugeMushroomBlock.NORTH) && blockstate.hasProperty(HugeMushroomBlock.SOUTH)) {
                                blockstate = blockstate.setValue(HugeMushroomBlock.WEST, Boolean.valueOf(flag6)).setValue(HugeMushroomBlock.EAST, Boolean.valueOf(flag7)).setValue(HugeMushroomBlock.NORTH, Boolean.valueOf(flag8)).setValue(HugeMushroomBlock.SOUTH, Boolean.valueOf(flag9)).setValue(HugeMushroomBlock.DOWN, Boolean.valueOf(flag10)).setValue(HugeMushroomBlock.UP, Boolean.valueOf(flag11));
                            }

                            this.setBlock(p_225043_, p_225047_, blockstate);
                        }
                    }
                }
            }
        }
    }

    protected int getTreeRadiusForHeight(int p_65881_, int p_65882_, int p_65883_, int p_65884_) {
        return 2;
    }
}
