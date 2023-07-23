package com.github.ilja615.worldupgrade.world.trunk_placers;

import com.github.ilja615.worldupgrade.init.ModFeatures;
import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.List;
import java.util.OptionalInt;
import java.util.function.BiConsumer;

public class CrookedTrunkPlacer extends TrunkPlacer
{
    public static final Codec<CrookedTrunkPlacer> CODEC = RecordCodecBuilder.create((p_70161_) -> {
        return trunkPlacerParts(p_70161_).apply(p_70161_, CrookedTrunkPlacer::new);
    });

    public CrookedTrunkPlacer(int p_70268_, int p_70269_, int p_70270_)
    {
        super(p_70268_, p_70269_, p_70270_);
    }

    @Override
    protected TrunkPlacerType<?> type()
    {
        return ModFeatures.CROOKED_TRUNK_PLACER_TYPE.get();
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> log, RandomSource rand, int height, BlockPos origin, TreeConfiguration config)
    {
        setDirtAt(level, log, rand, origin.below(), config);
        List<FoliagePlacer.FoliageAttachment> list = Lists.newArrayList();
        Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(rand);
        Direction direction1 = Direction.Plane.HORIZONTAL.getRandomDirection(rand);
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
        int k1 = origin.getX();
        int l1 = origin.getZ();
        OptionalInt optionalint1 = OptionalInt.empty();
        for (int i1 = 0; i1 <= height; i1++)
        {
            if (i1 >= 2 && rand.nextBoolean()) {
                k1 += direction.getStepX();
                l1 += direction.getStepZ();
            }
            if (this.placeLog(level, log, rand, blockpos$mutableblockpos.set(k1, origin.getY()+i1, l1), config))
            {
                optionalint1 = OptionalInt.of(i1 + 1);
            }
            if (i1 == ((int)Math.ceil(height/3.0f)))
            {
                if (direction1 != direction)
                {
                    int k2 = k1;
                    int l2 = l1;
                    OptionalInt optionalint2 = OptionalInt.empty();
                    for (int i2 = 0; i2 <= 4; i2++)
                    {
                        k2 += direction1.getStepX();
                        l2 += direction1.getStepZ();
                        if (this.placeLog(level, log, rand, blockpos$mutableblockpos.set(k2, origin.getY()+i1+i2, l2), config))
                        {
                            optionalint2 = OptionalInt.of(i1 + i2 + 1);
                        }
                    }
                    if (optionalint2.isPresent()) {
                        list.add(new FoliagePlacer.FoliageAttachment(new BlockPos(k2, origin.getY()+optionalint2.getAsInt(), l2), 1, false));
                    }
                }
            }
            if (i1 == ((int)Math.ceil(height/3.0f)*2))
            {
                if (direction1.getOpposite() != direction)
                {
                    int k2 = k1;
                    int l2 = l1;
                    OptionalInt optionalint2 = OptionalInt.empty();
                    for (int i2 = 0; i2 <= 4; i2++)
                    {
                        k2 += direction1.getOpposite().getStepX();
                        l2 += direction1.getOpposite().getStepZ();
                        if (this.placeLog(level, log, rand, blockpos$mutableblockpos.set(k2, origin.getY()+i1+i2, l2), config))
                        {
                            optionalint2 = OptionalInt.of(i1 + i2 + 1);
                        }
                    }
                    if (optionalint2.isPresent()) {
                        list.add(new FoliagePlacer.FoliageAttachment(new BlockPos(k2, origin.getY()+optionalint2.getAsInt(), l2), 1, false));
                    }
                }
            }
        }

        if (optionalint1.isPresent()) {
            list.add(new FoliagePlacer.FoliageAttachment(new BlockPos(k1, origin.getY()+optionalint1.getAsInt(), l1), 1, false));
        }

        return list;
    }
}
