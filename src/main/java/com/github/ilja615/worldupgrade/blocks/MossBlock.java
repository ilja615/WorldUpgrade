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

import net.minecraft.block.AbstractBlock.Properties;

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

    private static final Map<Direction, BooleanProperty> FACING_TO_PROPERTY_MAP = SixWayBlock.PROPERTY_BY_DIRECTION;
    private static final VoxelShape UP_AABB = Block.box(0.0D, 15.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    private static final VoxelShape DOWN_AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 1.0D, 16.0D);
    private static final VoxelShape WEST_AABB = Block.box(0.0D, 0.0D, 0.0D, 1.0D, 16.0D, 16.0D);
    private static final VoxelShape EAST_AABB = Block.box(15.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    private static final VoxelShape NORTH_AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 1.0D);
    private static final VoxelShape SOUTH_AABB = Block.box(0.0D, 0.0D, 15.0D, 16.0D, 16.0D, 16.0D);
    private static final VoxelShape WEST_SMALL_AABB = Block.box(0.0D, 3.0D, 0.0D, 1.0D, 16.0D, 16.0D);
    private static final VoxelShape EAST_SMALL_AABB = Block.box(15.0D, 3.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    private static final VoxelShape NORTH_SMALL_AABB = Block.box(0.0D, 3.0D, 0.0D, 16.0D, 16.0D, 1.0D);
    private static final VoxelShape SOUTH_SMALL_AABB = Block.box(0.0D, 3.0D, 15.0D, 16.0D, 16.0D, 16.0D);

    public MossBlock(Properties properties)
    {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(UP, false)
                .setValue(DOWN, false)
                .setValue(NORTH, false)
                .setValue(EAST, false)
                .setValue(SOUTH, false)
                .setValue(WEST, false)
                .setValue(BELOW, false)
                .setValue(CAN_GROW, true));
    }

    public static boolean canAttachTo(IBlockReader p_196542_0_, BlockPos worldIn, Direction neighborPos)
    {
        BlockState blockstate = p_196542_0_.getBlockState(worldIn);
        return Block.isFaceFull(blockstate.getCollisionShape(p_196542_0_, worldIn), neighborPos.getOpposite());
    }

    public static BooleanProperty getPropertyFor(Direction side)
    {
        return FACING_TO_PROPERTY_MAP.get(side);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        VoxelShape voxelshape = VoxelShapes.empty();
        if (state.getValue(UP))
        {
            voxelshape = VoxelShapes.or(voxelshape, UP_AABB);
        }

        if (state.getValue(DOWN))
        {
            voxelshape = VoxelShapes.or(voxelshape, DOWN_AABB);
        }


        if (state.getValue(NORTH) && !state.getValue(BELOW))
        {
            voxelshape = VoxelShapes.or(voxelshape, NORTH_SMALL_AABB);
        }

        if (state.getValue(EAST) && !state.getValue(BELOW))
        {
            voxelshape = VoxelShapes.or(voxelshape, EAST_SMALL_AABB);
        }

        if (state.getValue(SOUTH) && !state.getValue(BELOW))
        {
            voxelshape = VoxelShapes.or(voxelshape, SOUTH_SMALL_AABB);
        }

        if (state.getValue(WEST) && !state.getValue(BELOW))
        {
            voxelshape = VoxelShapes.or(voxelshape, WEST_SMALL_AABB);
        }


        if (state.getValue(NORTH) && state.getValue(BELOW))
        {
            voxelshape = VoxelShapes.or(voxelshape, NORTH_AABB);
        }

        if (state.getValue(EAST) && state.getValue(BELOW))
        {
            voxelshape = VoxelShapes.or(voxelshape, EAST_AABB);
        }

        if (state.getValue(SOUTH) && state.getValue(BELOW))
        {
            voxelshape = VoxelShapes.or(voxelshape, SOUTH_AABB);
        }

        if (state.getValue(WEST) && state.getValue(BELOW))
        {
            voxelshape = VoxelShapes.or(voxelshape, WEST_AABB);
        }

        return voxelshape;
    }

    @Override
    public boolean canSurvive(BlockState state, IWorldReader worldIn, BlockPos pos)
    {
        return this.hasFaces(this.getUpdatedState(state, worldIn, pos));
    }

    private boolean hasFaces(BlockState p_196543_1_)
    {
        return this.countFaces(p_196543_1_) > 0;
    }

    private int countFaces(BlockState p_208496_1_)
    {
        int i = 0;

        for (BooleanProperty booleanproperty : FACING_TO_PROPERTY_MAP.values())
        {
            if (p_208496_1_.getValue(booleanproperty))
            {
                ++i;
            }
        }

        return i;
    }

    private boolean hasAttachment(IBlockReader p_196541_1_, BlockPos p_196541_2_, Direction p_196541_3_)
    {

        BlockPos blockpos = p_196541_2_.relative(p_196541_3_);
        if (canAttachTo(p_196541_1_, blockpos, p_196541_3_))
        {
            return true;
        } else if (p_196541_3_.getAxis() == Direction.Axis.Y)
        {
            return false;
        } else
        {
            BooleanProperty booleanproperty = FACING_TO_PROPERTY_MAP.get(p_196541_3_);
            BlockState blockstate = p_196541_1_.getBlockState(p_196541_2_.above());
            return blockstate.getBlock() == this && blockstate.getValue(booleanproperty);
        }

    }

    private BlockState getUpdatedState(BlockState blockState, IBlockReader worldIn, BlockPos pos)
    {
        BlockPos blockpos = pos.above();
        BlockState aboveBlockState = worldIn.getBlockState(pos.above());
        boolean b;
        if (blockState.getValue(UP))
        {
            blockState = blockState.setValue(UP, canAttachTo(worldIn, blockpos, Direction.DOWN));
        }

        blockpos = pos.below();
        if (blockState.getValue(DOWN))
        {
            blockState = blockState.setValue(DOWN, canAttachTo(worldIn, blockpos, Direction.UP));
        }

        for (Direction direction : Direction.values())
        {
            if (direction.getAxis() != Direction.Axis.Y)
            {
                blockpos = pos.relative(direction);
                if (blockState.getValue(FACING_TO_PROPERTY_MAP.get(direction)))
                {
                    if (aboveBlockState.getBlock() instanceof MossBlock)
                    {
                        b = canAttachTo(worldIn, blockpos, direction.getOpposite()) || aboveBlockState.getValue(FACING_TO_PROPERTY_MAP.get(direction));
                    } else
                    {
                        b = canAttachTo(worldIn, blockpos, direction.getOpposite());
                    }
                    blockState = blockState.setValue(FACING_TO_PROPERTY_MAP.get(direction), b);
                }
            } else
            {
                blockpos = pos.relative(direction);
                if (blockState.getValue(FACING_TO_PROPERTY_MAP.get(direction)))
                {
                    blockState = blockState.setValue(FACING_TO_PROPERTY_MAP.get(direction), canAttachTo(worldIn, blockpos, direction.getOpposite()));
                }
            }
        }

        if (worldIn.getBlockState(pos.below()).getBlock() == this)
        {
            blockState = blockState.setValue(BELOW, true);
        }
        if (blockState.getValue(DOWN))
        {
            blockState = blockState.setValue(BELOW, true);
        }

        return blockState;
    }

    @Override
    public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos)
    {

        BlockState blockstate = this.getUpdatedState(stateIn, worldIn, currentPos);
        return !this.hasFaces(blockstate) ? Blocks.AIR.defaultBlockState() : blockstate;

    }

    @Override
    public boolean canBeReplaced(BlockState state, BlockItemUseContext useContext)
    {
        return true;
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext context)
    {
        BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos());
        boolean flag = blockstate.getBlock() == this;
        BlockState blockstate1 = flag ? blockstate : this.defaultBlockState();

        if (context.getLevel().getBlockState(context.getClickedPos().below()).getBlock() == this)
        {
            blockstate1 = blockstate1.setValue(BELOW, true);
        }
        for (Direction direction : context.getNearestLookingDirections())
        {
            BooleanProperty booleanproperty = getPropertyFor(direction);
            boolean flag1 = flag && blockstate.getValue(booleanproperty);
            if (!flag1 && this.hasAttachment(context.getLevel(), context.getClickedPos(), direction))
            {
                return blockstate1.setValue(booleanproperty, true);
            }
        }

        return flag ? blockstate1 : null;
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder)
    {
        builder.add(UP, DOWN, NORTH, EAST, SOUTH, WEST, BELOW, CAN_GROW);
    }

    @Override
    public boolean isValidBonemealTarget(IBlockReader iBlockReader, BlockPos blockPos, BlockState blockState, boolean b)
    {
        return blockState.getValue(CAN_GROW);
    }

    @Override
    public boolean isBonemealSuccess(World world, Random random, BlockPos blockPos, BlockState blockState)
    {
        return true;
    }

    @Override
    public void performBonemeal(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state)
    {
        if (state.getBlock() instanceof MossBlock)
        {
            if (state.getValue(CAN_GROW))
            {
                if (worldIn.isAreaLoaded(pos, 4)) // Forge: check area to prevent loading unloaded chunks
                {
                    Direction face = getRandomSide(rand, getAttachedSides(state));
                    if (rand.nextFloat() > 0.4f)
                    {
                        //growInnerCorner
                        face = getRandomSide(rand, getNotAttachedSides(state));
                        if (canAttachTo(worldIn, pos.relative(face), face))
                            worldIn.setBlock(pos, state.setValue(FACING_TO_PROPERTY_MAP.get(face), true), 3);
                    }
                    if (rand.nextFloat() > 0.4f)
                    {
                        //growOuterCorner
                        face = getRandomSide(rand, getAttachedSides(state));
                        BlockPos bp = pos.relative(face);
                        Direction direction1 = Direction.getRandom(rand);
                        if (direction1 != face && direction1 != face.getOpposite())
                        {
                            BlockPos newPos = bp.relative(direction1);
                            if (!worldIn.getBlockState(newPos).isAir(worldIn, newPos))
                            {
                                return;
                            }
                            BlockState newMossBlockState = this.defaultBlockState().setValue(FACING_TO_PROPERTY_MAP.get(direction1.getOpposite()), true);
                            if (rand.nextFloat() > 0.3f)
                                newMossBlockState = newMossBlockState.setValue(CAN_GROW, false);
                            if (canSurvive(newMossBlockState, worldIn, newPos))
                            {
                                worldIn.setBlock(newPos, newMossBlockState, 3);
                                updateNeighbors(worldIn, pos, 2);
                                if (rand.nextFloat() > 0.6f && worldIn.getBlockState(pos).getBlock() instanceof MossBlock)
                                    worldIn.setBlock(pos, worldIn.getBlockState(pos).setValue(CAN_GROW, false), 3);
                            }
                        }
                    }
                    {
                        //growAtSameLevel
                        if (!state.getValue(FACING_TO_PROPERTY_MAP.get(face)))
                        {
                            return;
                        }
                        Direction growingDirection = DirectionUtil.getRandomMossGrowthDirection(face, rand);
                        BlockPos newPos = pos.relative(growingDirection);
                        if (!worldIn.getBlockState(newPos).isAir(worldIn, newPos))
                        {
                            return;
                        }
                        BlockState newMossBlockState = this.defaultBlockState().setValue(FACING_TO_PROPERTY_MAP.get(face), true);
                        if (rand.nextFloat() > 0.4f)
                            newMossBlockState = newMossBlockState.setValue(CAN_GROW, false);
                        if (canSurvive(newMossBlockState, worldIn, newPos))
                        {
                            worldIn.setBlock(newPos, newMossBlockState, 3);
                            updateNeighbors(worldIn, pos, 2);
                            if (rand.nextFloat() > 0.6f && worldIn.getBlockState(pos).getBlock() instanceof MossBlock)
                                worldIn.setBlock(pos, worldIn.getBlockState(pos).setValue(CAN_GROW, false), 3);
                            if (rand.nextFloat() > 0.5f)
                            {
                                BlockPos pos1 = pos.relative(growingDirection.getOpposite());
                                BlockState state1 = worldIn.getBlockState(pos1);
                                if (worldIn.getBlockState(pos1).getBlock() instanceof MossBlock)
                                    if (!worldIn.getBlockState(pos1).getValue(BELOW))
                                        worldIn.setBlock(pos1, state1.setValue(FACING_TO_PROPERTY_MAP.get(face), false), 3);
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
        performBonemeal(worldIn, rand, pos, state);
    }

    public ArrayList<Direction> getAttachedSides(BlockState state)
    {
        ArrayList<Direction> sides = new ArrayList<>();
        for (Direction side : Direction.values())
        {
            if (state.getBlock() instanceof MossBlock)
            {
                if (state.getValue(FACING_TO_PROPERTY_MAP.get(side)))
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
                if (!state.getValue(FACING_TO_PROPERTY_MAP.get(side)))
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

    public void updateNeighbors(ServerWorld worldIn, BlockPos pos, int flags)
    {
        worldIn.getBlockState(pos).updateNeighbourShapes(worldIn, pos, flags);
    }
}
