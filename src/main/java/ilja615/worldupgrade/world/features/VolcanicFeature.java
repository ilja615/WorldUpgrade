package ilja615.worldupgrade.world.features;

import com.mojang.datafixers.Dynamic;
import ilja615.worldupgrade.init.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.IWorldGenerationBaseReader;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;
import java.util.function.Function;

public class VolcanicFeature extends Feature<NoFeatureConfig> {

    private static final BlockState SMOKE_VENT = ModBlocks.SMOKE_VENT.getDefaultState();

    public VolcanicFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> configIn, boolean doBlockNotifyIn)
    {
        super(configIn, doBlockNotifyIn);
    }

    @Override
    public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos startPosition, NoFeatureConfig config)
    {
        while (startPosition.getY() > 1 && isAirOrLeaves(worldIn, startPosition)) startPosition = startPosition.down();
        worldIn.setBlockState(startPosition, ModBlocks.FIRE_JET.getDefaultState(), 2);
        worldIn.setBlockState(startPosition.south(), SMOKE_VENT, 2);
        worldIn.setBlockState(startPosition.north(), SMOKE_VENT, 2);
        worldIn.setBlockState(startPosition.west(), SMOKE_VENT, 2);
        worldIn.setBlockState(startPosition.east(), SMOKE_VENT, 2);
        if (rand.nextBoolean() == true)
            worldIn.setBlockState(startPosition.south(2), SMOKE_VENT, 2);
        if (rand.nextBoolean() == true)
            worldIn.setBlockState(startPosition.north(2), SMOKE_VENT, 2);
        if (rand.nextBoolean() == true)
            worldIn.setBlockState(startPosition.west(2), SMOKE_VENT, 2);
        if (rand.nextBoolean() == true)
            worldIn.setBlockState(startPosition.east(2), SMOKE_VENT, 2);
        if (rand.nextBoolean() == true)
            worldIn.setBlockState(startPosition.south().east(), SMOKE_VENT, 2);
        if (rand.nextBoolean() == true)
            worldIn.setBlockState(startPosition.north().west(), SMOKE_VENT, 2);
        if (rand.nextBoolean() == true)
            worldIn.setBlockState(startPosition.west().south(), SMOKE_VENT, 2);
        if (rand.nextBoolean() == true)
            worldIn.setBlockState(startPosition.east().north(), SMOKE_VENT, 2);
        return false;
    }

    protected static boolean isAirOrLeaves(IWorldGenerationBaseReader worldIn, BlockPos pos) {
        if (!(worldIn instanceof net.minecraft.world.IWorldReader)) // FORGE: Redirect to state method when possible
            return worldIn.hasBlockState(pos, (p_214581_0_) -> {
                return p_214581_0_.isAir() || p_214581_0_.isIn(BlockTags.LEAVES);
            });
        else return worldIn.hasBlockState(pos, state -> state.canBeReplacedByLeaves((net.minecraft.world.IWorldReader)worldIn, pos));
    }
}
