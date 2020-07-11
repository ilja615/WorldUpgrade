package ilja615.worldupgrade.world.features;

import com.mojang.datafixers.Dynamic;
import ilja615.worldupgrade.blocks.BrambleBushBlock;
import ilja615.worldupgrade.blocks.BrambleFullBlock;
import ilja615.worldupgrade.blocks.DoubleReedPlantBlock;
import ilja615.worldupgrade.init.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.BlockStateProvidingFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;
import java.util.function.Function;

public class BrambleFeature extends Feature<NoFeatureConfig> {

    private static final BlockState BRAMBLE_FULL_2 = ModBlocks.BRAMBLE_FULL.get().getDefaultState().with(BrambleFullBlock.AGE, 2);
    private static final BlockState BRAMBLE_FULL_3 = ModBlocks.BRAMBLE_FULL.get().getDefaultState().with(BrambleFullBlock.AGE, 3);
    private static final BlockState BRAMBLE_BUSH_2 = ModBlocks.BRAMBLE_BUSH.get().getDefaultState().with(BrambleBushBlock.AGE, 2);
    private static final BlockState BRAMBLE_BUSH_3 = ModBlocks.BRAMBLE_BUSH.get().getDefaultState().with(BrambleBushBlock.AGE, 3);

    public BrambleFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> configIn)
    {
        super(configIn);
    }

    //

    public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> chunkGenerator ,Random rand ,BlockPos pos ,NoFeatureConfig config)
    {
        int i = 2 + rand.nextInt(3);
        int j = 2 + rand.nextInt(3);

        for(BlockPos blockpos : BlockPos.getAllInBoxMutable(pos.add(-i, 0, -j), pos.add(i, 1, j))) {
            int k = pos.getX() - blockpos.getX();
            int l = pos.getZ() - blockpos.getZ();
            if ((float)(k * k + l * l) <= rand.nextFloat() * 10.0F - rand.nextFloat() * 6.0F) {
                this.placeBlockAt(worldIn, blockpos, rand);
            } else if ((double)rand.nextFloat() < 0.031D) {
                this.placeBlockAt(worldIn, blockpos, rand);
            }
        }

        for(BlockPos blockpos : BlockPos.getAllInBoxMutable(pos.add(-i, 0, -j), pos.add(i, 1, j))) {
            if (worldIn.getBlockState(blockpos).getBlock() instanceof BrambleFullBlock && worldIn.getBlockState(blockpos.up()).getBlock() == Blocks.AIR)
            {
                if (rand.nextBoolean()) {if (rand.nextBoolean()) setBlockState(worldIn, blockpos, BRAMBLE_BUSH_2); else  setBlockState(worldIn, blockpos, BRAMBLE_BUSH_3);}
            }
        }

        return true;
    }

    private void placeBlockAt(IWorld world, BlockPos pos, Random rand)
    {
        if (world.getBlockState(pos).getBlock() == Blocks.AIR) {
            if (ModBlocks.BRAMBLE_BUSH.get().getDefaultState().isValidPosition(world, pos) || world.getBlockState(pos.down()).getBlock() instanceof BrambleFullBlock) {
                if (rand.nextBoolean()) setBlockState(world, pos, BRAMBLE_FULL_2);
                else setBlockState(world, pos, BRAMBLE_FULL_3);
            }
        }
    }
}
