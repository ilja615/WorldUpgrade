package ilja615.worldupgrade.blocks;

import ilja615.worldupgrade.init.ModBlocks;
import ilja615.worldupgrade.init.ModBlocksNew;
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

public class DriedExtraReedBlock extends BushBlock
{
    public static final IntegerProperty AGE = BlockStateProperties.AGE_0_15;
    protected static final VoxelShape SHAPE = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D);


    public static final BooleanProperty ABOVE = BooleanProperty.create("above");
    public DriedExtraReedBlock(Properties properties)
    {
        super(properties);

        this.setDefaultState(this.stateContainer.getBaseState().with(ABOVE, false));

        setRegistryName("dry_top_reed");
        //ModBlocks.BLOCKS.add(this);
    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos)
    {
        BlockState downblockstate = worldIn.getBlockState(pos.down());
        return (downblockstate.getBlock() == ModBlocksNew.DRY_TALL_REED.get() || downblockstate.getBlock() == ModBlocksNew.DRY_TOP_REED.get());
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(ABOVE, AGE);
    }

    @Override
    public BlockState updatePostPlacement(BlockState state, Direction direction, BlockState state2, IWorld world, BlockPos pos, BlockPos pos2) {
        if (world.getBlockState(pos.up()).getBlock() == ModBlocksNew.DRY_TALL_REED.get() || world.getBlockState(pos.up()).getBlock() == ModBlocksNew.DRY_TOP_REED.get()) {state = state.with(ABOVE, true);}
        else {state = state.with(ABOVE, false);}

        return super.updatePostPlacement(state, direction, state2, world, pos, pos2);
    }

    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
        if (!worldIn.isAreaLoaded(pos, 1)) return; // prevent growing from loading unloaded chunks with block update
        if (!state.isValidPosition(worldIn, pos)) {
            worldIn.destroyBlock(pos, true);
        } else {
            BlockPos blockpos = pos.up();
            if (worldIn.isAirBlock(blockpos) && worldIn.getBlockState(pos.down()).getBlock() == ModBlocksNew.DRY_TALL_REED.get()) {

                int j = state.get(AGE);
                if(net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, blockpos, state, true)) {
                    if (j == 15) {
                        worldIn.setBlockState(blockpos, ModBlocksNew.DRY_TOP_REED.get().getDefaultState());
                        BlockState blockstate = state.with(AGE, Integer.valueOf(0)).with(ABOVE, true);
                        worldIn.setBlockState(pos, blockstate, 4);
                        blockstate.neighborChanged(worldIn, blockpos, this, pos, false);
                    } else {
                        worldIn.setBlockState(pos, state.with(AGE, Integer.valueOf(j + 1)), 4);
                    }
                    net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state);
                }

            }
        }
    }
    public OffsetType getOffsetType() {
        return OffsetType.NONE;
    }
}