package ilja615.worldupgrade.blocks;

import ilja615.worldupgrade.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BushBlock;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class ExtraReedBlock extends BushBlock
{
    protected static final VoxelShape SHAPE = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D);

    public ExtraReedBlock(Properties properties) { super(properties); }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos)
    {
        BlockState downblockstate = worldIn.getBlockState(pos.down());
        return (downblockstate.getBlock() == ModBlocks.TALL_REED.get()/* || downblockstate.getBlock() == ModBlocks.TOP_REED.get()*/ );
    }

//    @Override
////    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
////    {
////        builder.add(ABOVE/*, AGE*/);
////    }

//    @Override
//    public BlockState updatePostPlacement(BlockState state, Direction direction, BlockState state2, IWorld world, BlockPos pos, BlockPos pos2) {
//        if (world.getBlockState(pos.up()).getBlock() == ModBlocks.TALL_REED.get() || world.getBlockState(pos.up()).getBlock() == ModBlocks.TOP_REED.get()) {state = state.with(ABOVE, true);}
//        else {state = state.with(ABOVE, false);}
//
//        return super.updatePostPlacement(state, direction, state2, world, pos, pos2);
//    }

//    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
//        if (!worldIn.isAreaLoaded(pos, 1)) return; // prevent growing from loading unloaded chunks with block update
//        if (!state.isValidPosition(worldIn, pos)) {
//            worldIn.destroyBlock(pos, true);
//        } else {
//            BlockPos blockpos = pos.up();
//            if (worldIn.isAirBlock(blockpos) && worldIn.getBlockState(pos.down()).getBlock() == ModBlocks.TALL_REED.get()) {
//
//                int j = state.get(AGE);
//                if(net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, blockpos, state, true)) {
//                    if (j == 3) {
//                        worldIn.setBlockState(blockpos, ModBlocks.TOP_REED.get().getDefaultState());
//                        BlockState blockstate = state.with(AGE, Integer.valueOf(0)).with(ABOVE, true);
//                        worldIn.setBlockState(pos, blockstate, 3);
//                        blockstate.neighborChanged(worldIn, blockpos, this, pos, false);
//                    } else {
//                        worldIn.setBlockState(pos, state.with(AGE, Integer.valueOf(j + 1)), 4);
//                    }
//                    net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state);
//                }
//
//            }
//        }
//    }
    public Block.OffsetType getOffsetType() {
        return OffsetType.NONE;
    }
}