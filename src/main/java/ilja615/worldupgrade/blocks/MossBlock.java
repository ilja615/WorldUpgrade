package ilja615.worldupgrade.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SixWayBlock;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;

import javax.annotation.Nullable;
import java.util.Map;

public class MossBlock extends Block
{
    public static final BooleanProperty NORTH = BlockStateProperties.NORTH;
    public static final BooleanProperty EAST = BlockStateProperties.EAST;
    public static final BooleanProperty SOUTH = BlockStateProperties.SOUTH;
    public static final BooleanProperty WEST = BlockStateProperties.WEST;
    public static final BooleanProperty UP = BlockStateProperties.UP;
    public static final BooleanProperty DOWN = BlockStateProperties.DOWN;
    public static final BooleanProperty BELOW = BooleanProperty.create("below");

    public static final Map<Direction, BooleanProperty> FACING_TO_PROPERTY_MAP = SixWayBlock.FACING_TO_PROPERTY_MAP;
    protected static final VoxelShape UP_AABB = Block.makeCuboidShape(0.0D, 15.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape DOWN_AABB = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 1.0D, 16.0D);
    protected static final VoxelShape WEST_AABB = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 1.0D, 16.0D, 16.0D);
    protected static final VoxelShape EAST_AABB = Block.makeCuboidShape(15.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape NORTH_AABB = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 1.0D);
    protected static final VoxelShape SOUTH_AABB = Block.makeCuboidShape(0.0D, 0.0D, 15.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape WEST_SMALL_AABB = Block.makeCuboidShape(0.0D, 3.0D, 0.0D, 1.0D, 16.0D, 16.0D);
    protected static final VoxelShape EAST_SMALL_AABB = Block.makeCuboidShape(15.0D, 3.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape NORTH_SMALL_AABB = Block.makeCuboidShape(0.0D, 3.0D, 0.0D, 16.0D, 16.0D, 1.0D);
    protected static final VoxelShape SOUTH_SMALL_AABB = Block.makeCuboidShape(0.0D, 3.0D, 15.0D, 16.0D, 16.0D, 16.0D);

    public MossBlock(Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(UP, Boolean.valueOf(false)).with(DOWN, Boolean.valueOf(false)).with(NORTH, Boolean.valueOf(false)).with(EAST, Boolean.valueOf(false)).with(SOUTH, Boolean.valueOf(false)).with(WEST, Boolean.valueOf(false)).with(BELOW, Boolean.valueOf(false)));

    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        VoxelShape voxelshape = VoxelShapes.empty();
        if (state.get(UP)) {
            voxelshape = VoxelShapes.or(voxelshape, UP_AABB);
        }

        if (state.get(DOWN)) {
            voxelshape = VoxelShapes.or(voxelshape, DOWN_AABB);
        }


        if (state.get(NORTH) && !state.get(BELOW)) {
            voxelshape = VoxelShapes.or(voxelshape, NORTH_SMALL_AABB);
        }

        if (state.get(EAST) && !state.get(BELOW)) {
            voxelshape = VoxelShapes.or(voxelshape, EAST_SMALL_AABB);
        }

        if (state.get(SOUTH) && !state.get(BELOW)) {
            voxelshape = VoxelShapes.or(voxelshape, SOUTH_SMALL_AABB);
        }

        if (state.get(WEST) && !state.get(BELOW)) {
            voxelshape = VoxelShapes.or(voxelshape, WEST_SMALL_AABB);
        }


        if (state.get(NORTH) && state.get(BELOW)) {
            voxelshape = VoxelShapes.or(voxelshape, NORTH_AABB);
        }

        if (state.get(EAST) && state.get(BELOW)) {
            voxelshape = VoxelShapes.or(voxelshape, EAST_AABB);
        }

        if (state.get(SOUTH) && state.get(BELOW)) {
            voxelshape = VoxelShapes.or(voxelshape, SOUTH_AABB);
        }

        if (state.get(WEST) && state.get(BELOW)) {
            voxelshape = VoxelShapes.or(voxelshape, WEST_AABB);
        }

        return voxelshape;
    }

    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        return this.func_196543_i(this.func_196545_h(state, worldIn, pos));
    }

    private boolean func_196543_i(BlockState p_196543_1_) {
        return this.func_208496_w(p_196543_1_) > 0;
    }

    private int func_208496_w(BlockState p_208496_1_) {
        int i = 0;

        for(BooleanProperty booleanproperty : FACING_TO_PROPERTY_MAP.values()) {
            if (p_208496_1_.get(booleanproperty)) {
                ++i;
            }
        }

        return i;
    }

    private boolean func_196541_a(IBlockReader p_196541_1_, BlockPos p_196541_2_, Direction p_196541_3_) {

        BlockPos blockpos = p_196541_2_.offset(p_196541_3_);
        if (canAttachTo(p_196541_1_, blockpos, p_196541_3_)) {
            return true;
        } else if (p_196541_3_.getAxis() == Direction.Axis.Y) {
            return false;
        } else {
            BooleanProperty booleanproperty = FACING_TO_PROPERTY_MAP.get(p_196541_3_);
            BlockState blockstate = p_196541_1_.getBlockState(p_196541_2_.up());
            return blockstate.getBlock() == this && blockstate.get(booleanproperty);
        }

    }

    public static boolean canAttachTo(IBlockReader p_196542_0_, BlockPos worldIn, Direction neighborPos) {
        BlockState blockstate = p_196542_0_.getBlockState(worldIn);
        return Block.doesSideFillSquare(blockstate.getCollisionShape(p_196542_0_, worldIn), neighborPos.getOpposite());
    }

    private BlockState func_196545_h(BlockState p_196545_1_, IBlockReader p_196545_2_, BlockPos p_196545_3_) {
        BlockPos blockpos = p_196545_3_.up();
        if (p_196545_1_.get(UP)) {
            p_196545_1_ = p_196545_1_.with(UP, Boolean.valueOf(canAttachTo(p_196545_2_, blockpos, Direction.DOWN)));
        }

        blockpos = p_196545_3_.down();
        if (p_196545_1_.get(DOWN)) {
            p_196545_1_ = p_196545_1_.with(DOWN, Boolean.valueOf(canAttachTo(p_196545_2_, blockpos, Direction.UP)));
        }

        if (p_196545_2_.getBlockState(p_196545_3_.down()).getBlock() == this) {p_196545_1_ = p_196545_1_.with(BELOW, Boolean.valueOf(true));}


        BlockState blockstate = null;

        for(Direction direction : Direction.Plane.HORIZONTAL) {
            BooleanProperty booleanproperty = getPropertyFor(direction);
            if (p_196545_1_.get(booleanproperty)) {
                boolean flag = this.func_196541_a(p_196545_2_, p_196545_3_, direction);
                if (!flag) {
                    if (blockstate == null) {
                        blockstate = p_196545_2_.getBlockState(blockpos);
                    }

                    flag = blockstate.getBlock() == this && blockstate.get(booleanproperty);
                }

                p_196545_1_ = p_196545_1_.with(booleanproperty, Boolean.valueOf(flag));
            }
        }

        return p_196545_1_;
    }

    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {

            BlockState blockstate = this.func_196545_h(stateIn, worldIn, currentPos);
            return !this.func_196543_i(blockstate) ? Blocks.AIR.getDefaultState() : blockstate;

    }

    public static BooleanProperty getPropertyFor(Direction side) {
        return FACING_TO_PROPERTY_MAP.get(side);
    }

    public boolean isReplaceable(BlockState state, BlockItemUseContext useContext)
    {
        return true;
    }

    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        BlockState blockstate = context.getWorld().getBlockState(context.getPos());
        boolean flag = blockstate.getBlock() == this;
        BlockState blockstate1 = flag ? blockstate : this.getDefaultState();


        if (context.getWorld().getBlockState(context.getPos().down()).getBlock() == this) {blockstate1 = blockstate1.with(BELOW, Boolean.valueOf(true));}
        for(Direction direction : context.getNearestLookingDirections()) {
            BooleanProperty booleanproperty = getPropertyFor(direction);
            boolean flag1 = flag && blockstate.get(booleanproperty);
            if (!flag1 && this.func_196541_a(context.getWorld(), context.getPos(), direction)) {
                return blockstate1.with(booleanproperty, Boolean.valueOf(true));
            }
        }

        return flag ? blockstate1 : null;
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(UP, DOWN, NORTH, EAST, SOUTH, WEST, BELOW);
    }
}
