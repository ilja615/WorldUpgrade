package ilja615.worldupgrade.world.features;

import com.mojang.datafixers.Dynamic;
import ilja615.worldupgrade.blocks.AloeVeraOrAgaveBlock;
import ilja615.worldupgrade.init.ModBlocks;
import ilja615.worldupgrade.init.ModBlocksNew;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;
import java.util.function.Function;

public class AloeVeraFeature extends Feature<NoFeatureConfig> {
   private static final Block ALOE_VERA_BLOCK = ModBlocksNew.ALOE_VERA.get();

   public AloeVeraFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> p_i49894_1_) {
      super(p_i49894_1_);
   }

   public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, NoFeatureConfig config) {
      for(BlockState blockstate = worldIn.getBlockState(pos); (blockstate.isAir(worldIn, pos) || blockstate.isIn(BlockTags.LEAVES)) && pos.getY() > 0; blockstate = worldIn.getBlockState(pos)) {
         pos = pos.down();
      }

      BlockState blockstate1 = ALOE_VERA_BLOCK.getDefaultState();

      for(int i = 0; i < 4; ++i) {
         BlockPos blockpos = pos.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
         if (worldIn.isAirBlock(blockpos) && blockstate1.isValidPosition(worldIn, blockpos)) {
            worldIn.setBlockState(blockpos, blockstate1, 2);
         }
      }

      return true;
   }
}