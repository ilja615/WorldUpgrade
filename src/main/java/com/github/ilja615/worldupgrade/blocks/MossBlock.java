package com.github.ilja615.worldupgrade.blocks;

import com.github.ilja615.worldupgrade.util.DirectionUtil;
import net.minecraft.block.*;
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
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class MossBlock extends Block implements IGrowable
{
    private static final BooleanProperty NORTH = BlockStateProperties.NORTH;
    private static final BooleanProperty EAST = BlockStateProperties.EAST;
    private static final BooleanProperty SOUTH = BlockStateProperties.SOUTH;
    private static final BooleanProperty WEST = BlockStateProperties.WEST;
    private static final BooleanProperty UP = BlockStateProperties.UP;
    private static final BooleanProperty DOWN = BlockStateProperties.DOWN;
    private static final BooleanProperty BELOW = BooleanProperty.create("below");
    private static final BooleanProperty CAN_GROW = BooleanProperty.create("can_grow");

    private static final Map<Direction, BooleanProperty> FACING_TO_PROPERTY_MAP = SixWayBlock.FACING_TO_PROPERTY_MAP;
    private static final VoxelShape UP_AABB = Block.makeCuboidShape(0.0D, 15.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    private static final VoxelShape DOWN_AABB = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 1.0D, 16.0D);
    private static final VoxelShape WEST_AABB = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 1.0D, 16.0D, 16.0D);
    private static final VoxelShape EAST_AABB = Block.makeCuboidShape(15.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    private static final VoxelShape NORTH_AABB = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 1.0D);
    private static final VoxelShape SOUTH_AABB = Block.makeCuboidShape(0.0D, 0.0D, 15.0D, 16.0D, 16.0D, 16.0D);
    private static final VoxelShape WEST_SMALL_AABB = Block.makeCuboidShape(0.0D, 3.0D, 0.0D, 1.0D, 16.0D, 16.0D);
    private static final VoxelShape EAST_SMALL_AABB = Block.makeCuboidShape(15.0D, 3.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    private static final VoxelShape NORTH_SMALL_AABB = Block.makeCuboidShape(0.0D, 3.0D, 0.0D, 16.0D, 16.0D, 1.0D);
    private static final VoxelShape SOUTH_SMALL_AABB = Block.makeCuboidShape(0.0D, 3.0D, 15.0D, 16.0D, 16.0D, 16.0D);

    public MossBlock(Properties properties)
    {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState()
                .with(UP, false)
                .with(DOWN, false)
                .with(NORTH, false)
                .with(EAST, false)
                .with(SOUTH, false)
                .with(WEST, false)
                .with(BELOW, false)
                .with(CAN_GROW, true));
    }

    public static boolean canAttachTo(IBlockReader p_196542_0_, BlockPos worldIn, Direction neighborPos)
    {
        BlockState blockstate = p_196542_0_.getBlockState(worldIn);
        return Block.doesSideFillSquare(blockstate.getCollisionShape(p_196542_0_, worldIn), neighborPos.getOpposite());
    }

    public static BooleanProperty getPropertyFor(Direction side)
    {
        return FACING_TO_PROPERTY_MAP.get(side);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        VoxelShape voxelshape = VoxelShapes.empty();
        if (state.get(UP))
        {
            voxelshape = VoxelShapes.or(voxelshape, UP_AABB);
        }

        if (state.get(DOWN))
        {
            voxelshape = VoxelShapes.or(voxelshape, DOWN_AABB);
        }


        if (state.get(NORTH) && !state.get(BELOW))
        {
            voxelshape = VoxelShapes.or(voxelshape, NORTH_SMALL_AABB);
        }

        if (state.get(EAST) && !state.get(BELOW))
        {
            voxelshape = VoxelShapes.or(voxelshape, EAST_SMALL_AABB);
        }

        if (state.get(SOUTH) && !state.get(BELOW))
        {
            voxelshape = VoxelShapes.or(voxelshape, SOUTH_SMALL_AABB);
        }

        if (state.get(WEST) && !state.get(BELOW))
        {
            voxelshape = VoxelShapes.or(voxelshape, WEST_SMALL_AABB);
        }


        if (state.get(NORTH) && state.get(BELOW))
        {
            voxelshape = VoxelShapes.or(voxelshape, NORTH_AABB);
        }

        if (state.get(EAST) && state.get(BELOW))
        {
            voxelshape = VoxelShapes.or(voxelshape, EAST_AABB);
        }

        if (state.get(SOUTH) && state.get(BELOW))
        {
            voxelshape = VoxelShapes.or(voxelshape, SOUTH_AABB);
        }

        if (state.get(WEST) && state.get(BELOW))
        {
            voxelshape = VoxelShapes.or(voxelshape, WEST_AABB);
        }

        return voxelshape;
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos)
    {
        return this.func_196543_i(this.func_196545_h(state, worldIn, pos));
    }

    private boolean func_196543_i(BlockState p_196543_1_)
    {
        return this.func_208496_w(p_196543_1_) > 0;
    }

    private int func_208496_w(BlockState p_208496_1_)
    {
        int i = 0;

        for (BooleanProperty booleanproperty : FACING_TO_PROPERTY_MAP.values())
        {
            if (p_208496_1_.get(booleanproperty))
            {
                ++i;
            }
        }

        return i;
    }

    private boolean func_196541_a(IBlockReader p_196541_1_, BlockPos p_196541_2_, Direction p_196541_3_)
    {

        BlockPos blockpos = p_196541_2_.offset(p_196541_3_);
        if (canAttachTo(p_196541_1_, blockpos, p_196541_3_))
        {
            return true;
        } else if (p_196541_3_.getAxis() == Direction.Axis.Y)
        {
            return false;
        } else
        {
            BooleanProperty booleanproperty = FACING_TO_PROPERTY_MAP.get(p_196541_3_);
            BlockState blockstate = p_196541_1_.getBlockState(p_196541_2_.up());
            return blockstate.getBlock() == this && blockstate.get(booleanproperty);
        }

    }

    private BlockState func_196545_h(BlockState blockState, IBlockReader worldIn, BlockPos pos)
    {
        BlockPos blockpos = pos.up();
        BlockState aboveBlockState = worldIn.getBlockState(pos.up());
        boolean b;
        if (blockState.get(UP))
        {
            blockState = blockState.with(UP, canAttachTo(worldIn, blockpos, Direction.DOWN));
        }

        blockpos = pos.down();
        if (blockState.get(DOWN))
        {
            blockState = blockState.with(DOWN, canAttachTo(worldIn, blockpos, Direction.UP));
        }

        for (Direction direction : Direction.values())
        {
            if (direction.getAxis() != Direction.Axis.Y)
            {
                blockpos = pos.offset(direction);
                if (blockState.get(FACING_TO_PROPERTY_MAP.get(direction)))
                {
                    if (aboveBlockState.getBlock() instanceof MossBlock)
                    {
                        b = canAttachTo(worldIn, blockpos, direction.getOpposite()) || aboveBlockState.get(FACING_TO_PROPERTY_MAP.get(direction));
                    } else
                    {
                        b = canAttachTo(worldIn, blockpos, direction.getOpposite());
                    }
                    blockState = blockState.with(FACING_TO_PROPERTY_MAP.get(direction), b);
                }
            } else
            {
                blockpos = pos.offset(direction);
                if (blockState.get(FACING_TO_PROPERTY_MAP.get(direction)))
                {
                    blockState = blockState.with(FACING_TO_PROPERTY_MAP.get(direction), canAttachTo(worldIn, blockpos, direction.getOpposite()));
                }
            }
        }

        if (worldIn.getBlockState(pos.down()).getBlock() == this)
        {
            blockState = blockState.with(BELOW, true);
        }
        if (blockState.get(DOWN))
        {
            blockState = blockState.with(BELOW, true);
        }

        return blockState;
    }

    @Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos)
    {

        BlockState blockstate = this.func_196545_h(stateIn, worldIn, currentPos);
        return !this.func_196543_i(blockstate) ? Blocks.AIR.getDefaultState() : blockstate;

    }

    @Override
    public boolean isReplaceable(BlockState state, BlockItemUseContext useContext)
    {
        return true;
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext context)
    {
        BlockState blockstate = context.getWorld().getBlockState(context.getPos());
        boolean flag = blockstate.getBlock() == this;
        BlockState blockstate1 = flag ? blockstate : this.getDefaultState();

        if (context.getWorld().getBlockState(context.getPos().down()).getBlock() == this)
        {
            blockstate1 = blockstate1.with(BELOW, true);
        }
        for (Direction direction : context.getNearestLookingDirections())
        {
            BooleanProperty booleanproperty = getPropertyFor(direction);
            boolean flag1 = flag && blockstate.get(booleanproperty);
            if (!flag1 && this.func_196541_a(context.getWorld(), context.getPos(), direction))
            {
                return blockstate1.with(booleanproperty, true);
            }
        }

        return flag ? blockstate1 : null;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
    {
        builder.add(UP, DOWN, NORTH, EAST, SOUTH, WEST, BELOW, CAN_GROW);
    }

    @Override
    public boolean canGrow(IBlockReader iBlockReader, BlockPos blockPos, BlockState blockState, boolean b)
    {
        return blockState.get(CAN_GROW);
    }

    @Override
    public boolean canUseBonemeal(World world, Random random, BlockPos blockPos, BlockState blockState)
    {
        return true;
    }

    @Override
    public void grow(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state)
    {
        if (state.getBlock() instanceof MossBlock)
        {
            if (state.get(CAN_GROW))
            {
                if (worldIn.isAreaLoaded(pos, 4)) // Forge: check area to prevent loading unloaded chunks
                {
                    Direction face = getRandomSide(rand, getAttachedSides(state));
                    if (rand.nextFloat() > 0.4f)
                    {
                        //growInnerCorner
                        face = getRandomSide(rand, getNotAttachedSides(state));
                        if (canAttachTo(worldIn, pos.offset(face), face))
                            worldIn.setBlockState(pos, state.with(FACING_TO_PROPERTY_MAP.get(face), true), 3);
                    }
                    if (rand.nextFloat() > 0.4f)
                    {
                        //growOuterCorner
                        face = getRandomSide(rand, getAttachedSides(state));
                        BlockPos bp = pos.offset(face);
                        Direction direction1 = Direction.random(rand);
                        if (direction1 != face && direction1 != face.getOpposite())
                        {
                            BlockPos newPos = bp.offset(direction1);
                            if (!worldIn.getBlockState(newPos).isAir(worldIn, newPos))
                            {
                                return;
                            }
                            BlockState newMossBlockState = this.getDefaultState().with(FACING_TO_PROPERTY_MAP.get(direction1.getOpposite()), true);
                            if (rand.nextFloat() > 0.3f)
                                newMossBlockState = newMossBlockState.with(CAN_GROW, false);
                            if (isValidPosition(newMossBlockState, worldIn, newPos))
                            {
                                worldIn.setBlockState(newPos, newMossBlockState, 3);
                                updateNeighbors(state, worldIn, pos, 2);
                                if (rand.nextFloat() > 0.6f && worldIn.getBlockState(pos).getBlock() instanceof MossBlock)
                                    worldIn.setBlockState(pos, worldIn.getBlockState(pos).with(CAN_GROW, false), 3);
                            }
                        }
                    }
                    {
                        //growAtSameLevel
                        if (!state.get(FACING_TO_PROPERTY_MAP.get(face)))
                        {
                            return;
                        }
                        Direction growingDirection = DirectionUtil.getRandomMossGrowthDirection(face, rand);
                        BlockPos newPos = pos.offset(growingDirection);
                        if (!worldIn.getBlockState(newPos).isAir(worldIn, newPos))
                        {
                            return;
                        }
                        BlockState newMossBlockState = this.getDefaultState().with(FACING_TO_PROPERTY_MAP.get(face), true);
                        if (rand.nextFloat() > 0.4f)
                            newMossBlockState = newMossBlockState.with(CAN_GROW, false);
                        if (isValidPosition(newMossBlockState, worldIn, newPos))
                        {
                            worldIn.setBlockState(newPos, newMossBlockState, 3);
                            updateNeighbors(state, worldIn, pos, 2);
                            if (rand.nextFloat() > 0.6f && worldIn.getBlockState(pos).getBlock() instanceof MossBlock)
                                worldIn.setBlockState(pos, worldIn.getBlockState(pos).with(CAN_GROW, false), 3);
                            if (rand.nextFloat() > 0.5f)
                            {
                                BlockPos pos1 = pos.offset(growingDirection.getOpposite());
                                BlockState state1 = worldIn.getBlockState(pos1);
                                if (worldIn.getBlockState(pos1).getBlock() instanceof MossBlock)
                                    if (!worldIn.getBlockState(pos1).get(BELOW))
                                        worldIn.setBlockState(pos1, state1.with(FACING_TO_PROPERTY_MAP.get(face), false), 3);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand)
    {
        super.tick(state, worldIn, pos, rand);
        grow(worldIn, rand, pos, state);
    }

    public ArrayList<Direction> getAttachedSides(BlockState state)
    {
        ArrayList<Direction> sides = new ArrayList<>();
        for (Direction side : Direction.values())
        {
            if (state.getBlock() instanceof MossBlock)
            {
                if (state.get(FACING_TO_PROPERTY_MAP.get(side)))
                {
                    sides.add(side);
                }
            }
        }
        return sides;
    }

    public ArrayList<Direction> getNotAttachedSides(BlockState state)
    {
        ArrayList<Direction> sides = new ArrayList<>();
        for (Direction side : Direction.values())
        {
            if (state.getBlock() instanceof MossBlock)
            {
                if (!state.get(FACING_TO_PROPERTY_MAP.get(side)))
                {
                    sides.add(side);
                }
            }
        }
        return sides;
    }

    public Direction getRandomSide(Random rand, ArrayList<Direction> sides)
    {
        if (sides.size() > 0)
            return sides.get(rand.nextInt(sides.size()));
        else
            return Direction.DOWN;
    }
}
