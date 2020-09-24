package ilja615.worldupgrade.world.features;

import com.mojang.datafixers.Dynamic;
import ilja615.worldupgrade.blocks.DoubleReedPlantBlock;
import ilja615.worldupgrade.init.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;
import java.util.function.Function;

public class TallDeadBushFeature extends Feature<NoFeatureConfig> {

    private static final BlockState BOTTOM_TALL_DEAD_BUSH = ModBlocks.TALL_DEAD_BUSH.get().getDefaultState().with(DoubleReedPlantBlock.HALF, DoubleBlockHalf.LOWER);
    private static final BlockState TOP_TALL_DEAD_BUSH = ModBlocks.TALL_DEAD_BUSH.get().getDefaultState().with(DoubleReedPlantBlock.HALF, DoubleBlockHalf.UPPER);

    public TallDeadBushFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> configIn)
    {
        super(configIn);
    }

    //

    public boolean place(IWorld worldIn ,                                                  /* Previously: p_212245_1_ */
                         ChunkGenerator<? extends GenerationSettings> chunkGenerator ,    /* Previously: p_212245_2_ */
                         Random rand ,                                                    /* Previously: p_212245_3_ */
                         BlockPos startPosition ,                                         /* Previously: p_212245_4_ */
                         NoFeatureConfig config                                           /* Previously: p_212245_5_ */
    ) {
        boolean s = false;
    // Moving down until it is on the ground
        while (startPosition.getY() > 1 && AbstractTreeFeature.isAirOrLeaves(worldIn, startPosition)) startPosition = startPosition.down();

        startPosition = startPosition.up();


        if (!ModBlocks.TALL_DEAD_BUSH.get().getDefaultState().isValidPosition(worldIn, startPosition))
        {
            return false; // to detect for a good ground to generate it on
        }



            setBlockState(worldIn, startPosition, BOTTOM_TALL_DEAD_BUSH);
            setBlockState(worldIn, startPosition.up(1), TOP_TALL_DEAD_BUSH);


        return s;















    }
}
