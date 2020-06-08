package ilja615.worldupgrade.world.features;

import com.mojang.datafixers.Dynamic;
import ilja615.worldupgrade.blocks.DriedDoubleReedPlantBlock;
import ilja615.worldupgrade.blocks.DriedExtraReedBlock;
import ilja615.worldupgrade.init.ModBlocks;
import net.minecraft.block.BambooBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.state.properties.BambooLeaves;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.IWorldGenerationBaseReader;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.ProbabilityConfig;

import java.util.Random;
import java.util.function.Function;

public class DryReedsFeature extends Feature<NoFeatureConfig> {

    // oi janky code mate
    private static final BlockState DRY_REED_1_234 = ModBlocks.DRY_TALL_REED.get().getDefaultState().with(DriedDoubleReedPlantBlock.HALF, DoubleBlockHalf.LOWER).with(DriedDoubleReedPlantBlock.ABOVE, true).with(DriedDoubleReedPlantBlock.WATERLOGGED, false);
    private static final BlockState DRY_REED_2_2 = ModBlocks.DRY_TALL_REED.get().getDefaultState().with(DriedDoubleReedPlantBlock.HALF, DoubleBlockHalf.UPPER).with(DriedDoubleReedPlantBlock.ABOVE, false).with(DriedDoubleReedPlantBlock.WATERLOGGED, false);
    private static final BlockState DRY_REED_2_34 = ModBlocks.DRY_TALL_REED.get().getDefaultState().with(DriedDoubleReedPlantBlock.HALF, DoubleBlockHalf.UPPER).with(DriedDoubleReedPlantBlock.ABOVE, true).with(DriedDoubleReedPlantBlock.WATERLOGGED, false);
    private static final BlockState DRY_REED_3_3 = ModBlocks.DRY_TOP_REED.get().getDefaultState().with(DriedExtraReedBlock.ABOVE, false);
    private static final BlockState DRY_REED_3_4 = ModBlocks.DRY_TOP_REED.get().getDefaultState().with(DriedExtraReedBlock.ABOVE, true);
    private static final BlockState DRY_REED_4_4 = ModBlocks.DRY_TOP_REED.get().getDefaultState().with(DriedExtraReedBlock.ABOVE, false); // Yeah its the same as 3_3 :T
    private static final BlockState DRY_REED_1_234_w = ModBlocks.DRY_TALL_REED.get().getDefaultState().with(DriedDoubleReedPlantBlock.HALF, DoubleBlockHalf.LOWER).with(DriedDoubleReedPlantBlock.ABOVE, true).with(DriedDoubleReedPlantBlock.WATERLOGGED, true);


    public DryReedsFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> configIn)
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


        if (!ModBlocks.DRY_TALL_REED.get().getDefaultState().isValidPosition(worldIn, startPosition))
        {
            return false; // to detect for a good ground to generate it on
        }

        if (worldIn.getBlockState(startPosition).getBlock() == ModBlocks.TOP_REED.get().getBlock())
        {
            return false; // to detect for a good ground to generate it on
        }

        int randomint = rand.nextInt(10);
        if (randomint <= 1) //20% chance to get 3 hight
        {
            setBlockState(worldIn, startPosition, DRY_REED_1_234);
            setBlockState(worldIn, startPosition.up(1), DRY_REED_2_34);
            setBlockState(worldIn, startPosition.up(2), DRY_REED_3_3);

        } else if (randomint <= 3 ) //20% chance to get 4 hight
        {
            setBlockState(worldIn, startPosition, DRY_REED_1_234);
            setBlockState(worldIn, startPosition.up(1), DRY_REED_2_34);
            setBlockState(worldIn, startPosition.up(2), DRY_REED_3_4);
            setBlockState(worldIn, startPosition.up(2), DRY_REED_4_4);

        } else { //60 % chance to get 2 hight

            setBlockState(worldIn, startPosition, DRY_REED_1_234);
            setBlockState(worldIn, startPosition.up(1), DRY_REED_2_2);

        }
        return s;
    }
}
