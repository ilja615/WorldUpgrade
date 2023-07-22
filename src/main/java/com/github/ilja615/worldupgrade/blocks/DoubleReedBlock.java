package com.github.ilja615.worldupgrade.blocks;

import com.github.ilja615.worldupgrade.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;

import javax.annotation.Nullable;

import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class DoubleReedBlock extends DoublePlantBlock implements SimpleWaterloggedBlock
{
    public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final BooleanProperty ABOVE = BooleanProperty.create("above");

    public static final IntegerProperty AGE = BlockStateProperties.AGE_3;
    protected static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D);

    public DoubleReedBlock(Properties properties)
    {
        super(properties);

        this.registerDefaultState(this.stateDefinition.any().setValue(HALF, DoubleBlockHalf.LOWER).setValue(ABOVE, false));

    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter blockGetter, BlockPos pos, CollisionContext context) {
        Vec3 vec3 = state.getOffset(blockGetter, pos);
        return SHAPE.move(vec3.x, 0, vec3.z);
    }

    @Override
    public boolean canPlaceLiquid(BlockGetter p_56301_, BlockPos p_56302_, BlockState p_56303_, Fluid p_56304_)
    {
        return false;
    }

    @Override
    public boolean placeLiquid(LevelAccessor p_56306_, BlockPos p_56307_, BlockState p_56308_, FluidState p_56309_)
    {
        return false;
    }


    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        builder.add(HALF, WATERLOGGED, ABOVE, AGE);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context)
    { //ty Squishling for help mate
        BlockPos blockpos = context.getClickedPos();
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        boolean water = context.getLevel().getFluidState(context.getClickedPos()).is(FluidTags.WATER) && context.getLevel().getFluidState(context.getClickedPos()).getAmount() == 8;
        BlockState state = defaultBlockState().setValue(WATERLOGGED, water);

        return blockpos.getY() < context.getLevel().getMaxBuildHeight() - 1 && context.getLevel().getBlockState(blockpos.above()).canBeReplaced(context) && !context.getLevel().getFluidState(blockpos.above()).is(FluidTags.WATER) ? state : null;
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack)
    {
        BlockState blockState = level.getBlockState(pos.above());
        if (level.getBlockState(pos.below()).getBlock() == ModBlocks.TALL_REED.get() || level.getBlockState(pos.below()).getBlock() == ModBlocks.TOP_REED.get())
        {
            level.setBlock(pos, ModBlocks.TOP_REED.get().defaultBlockState(), 3);
        } else if (blockState.isAir())
            level.setBlock(pos.above(), this.defaultBlockState().setValue(HALF, DoubleBlockHalf.UPPER).setValue(WATERLOGGED, false), 3);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState state2, LevelAccessor level, BlockPos pos, BlockPos pos2)
    {
        if (state.getValue(WATERLOGGED))
            level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));

        if (level.getBlockState(pos.above()).getBlock() == ModBlocks.TALL_REED.get() || level.getBlockState(pos.above()).getBlock() == ModBlocks.TOP_REED.get())
        {
            state = state.setValue(ABOVE, true);
        } else
        {
            state = state.setValue(ABOVE, false);
        }

        return super.updateShape(state, direction, state2, level, pos, pos2);
    }

    @Override
    public FluidState getFluidState(BlockState state)
    {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource rand)
    {
        if (!level.isAreaLoaded(pos, 1)) return; // prevent growing from loading unloaded chunks with block update
        if (!state.canSurvive(level, pos))
        {
            level.destroyBlock(pos, true);
        } else
        {
            BlockPos blockpos = pos.above();
            if (level.isEmptyBlock(blockpos))
            {

                int j = state.getValue(AGE);
                if (net.minecraftforge.common.ForgeHooks.onCropsGrowPre(level, blockpos, state, true))
                {
                    if (j == 3)
                    {
                        level.setBlockAndUpdate(blockpos, ModBlocks.TOP_REED.get().defaultBlockState());
                        BlockState blockstate = state.setValue(AGE, 0);
                        level.setBlock(pos, blockstate, 3);
                        blockstate.neighborChanged(level, blockpos, this, pos, false);
                    } else
                    {
                        level.setBlock(pos, state.setValue(AGE, j + 1).setValue(ABOVE, true), 4);
                    }
                    net.minecraftforge.common.ForgeHooks.onCropsGrowPost(level, pos, state);
                }
                //:smileW:
            }
        }
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos)
    {
        Block block = state.getBlock();
        return block == Blocks.GRASS_BLOCK || block == Blocks.DIRT || block == Blocks.COARSE_DIRT || block == Blocks.PODZOL || block == Blocks.SAND || block == Blocks.RED_SAND || block == Blocks.MUD || (block == ModBlocks.TALL_REED.get() && state.getValue(BlockStateProperties.DOUBLE_BLOCK_HALF) == DoubleBlockHalf.UPPER);
    }
}
