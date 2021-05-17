package com.github.ilja615.worldupgrade.blocks;

import com.github.ilja615.worldupgrade.init.ModBlocks;
import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.Random;

import net.minecraft.block.AbstractBlock.OffsetType;
import net.minecraft.block.AbstractBlock.Properties;

public class DoubleReedPlantBlock extends DoublePlantBlock implements ILiquidContainer
{
    public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final BooleanProperty ABOVE = BooleanProperty.create("above");

    public static final IntegerProperty AGE = BlockStateProperties.AGE_3;
    protected static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D);

    public DoubleReedPlantBlock(Properties properties)
    {
        super(properties);

        this.registerDefaultState(this.stateDefinition.any().setValue(HALF, DoubleBlockHalf.LOWER).setValue(ABOVE, false));

    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return SHAPE;
    }

    @Override
    public boolean canPlaceLiquid(IBlockReader iBlockReader, BlockPos blockPos, BlockState blockState, Fluid fluid)
    {
        return false;
    }

    @Override
    public boolean placeLiquid(IWorld iWorld, BlockPos blockPos, BlockState blockState, FluidState iFluidState)
    {
        return false;
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder)
    {
        builder.add(HALF, WATERLOGGED, ABOVE, AGE);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context)
    { //ty Squishling for help mate
        BlockPos blockpos = context.getClickedPos();
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        boolean water = context.getLevel().getFluidState(context.getClickedPos()).is(FluidTags.WATER) && context.getLevel().getFluidState(context.getClickedPos()).getAmount() == 8;
        BlockState state = defaultBlockState().setValue(WATERLOGGED, water);

        return blockpos.getY() < context.getLevel().getMaxBuildHeight() - 1 && context.getLevel().getBlockState(blockpos.above()).canBeReplaced(context) && !context.getLevel().getFluidState(blockpos.above()).is(FluidTags.WATER) ? state : null;
    }

    @Override
    public void setPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack)
    {
        BlockState blockState = worldIn.getBlockState(pos.above());
        if (worldIn.getBlockState(pos.below()).getBlock() == ModBlocks.TALL_REED.get() || worldIn.getBlockState(pos.below()).getBlock() == ModBlocks.TOP_REED.get())
        {
            worldIn.setBlock(pos, ModBlocks.TOP_REED.get().defaultBlockState(), 3);
        } else if (blockState.isAir(worldIn, pos.above()))
            worldIn.setBlock(pos.above(), this.defaultBlockState().setValue(HALF, DoubleBlockHalf.UPPER).setValue(WATERLOGGED, false), 3);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState state2, IWorld world, BlockPos pos, BlockPos pos2)
    {
        if (state.getValue(WATERLOGGED))
            world.getLiquidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world));

        if (world.getBlockState(pos.above()).getBlock() == ModBlocks.TALL_REED.get() || world.getBlockState(pos.above()).getBlock() == ModBlocks.TOP_REED.get())
        {
            state = state.setValue(ABOVE, true);
        } else
        {
            state = state.setValue(ABOVE, false);
        }

        return super.updateShape(state, direction, state2, world, pos, pos2);
    }

    @Override
    public FluidState getFluidState(BlockState state)
    {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand)
    {
        if (!worldIn.isAreaLoaded(pos, 1)) return; // prevent growing from loading unloaded chunks with block update
        if (!state.canSurvive(worldIn, pos))
        {
            worldIn.destroyBlock(pos, true);
        } else
        {
            BlockPos blockpos = pos.above();
            if (worldIn.isEmptyBlock(blockpos))
            {

                int j = state.getValue(AGE);
                if (net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, blockpos, state, true))
                {
                    if (j == 3)
                    {
                        worldIn.setBlockAndUpdate(blockpos, ModBlocks.TOP_REED.get().defaultBlockState());
                        BlockState blockstate = state.setValue(AGE, 0);
                        worldIn.setBlock(pos, blockstate, 3);
                        blockstate.neighborChanged(worldIn, blockpos, this, pos, false);
                    } else
                    {
                        worldIn.setBlock(pos, state.setValue(AGE, j + 1).setValue(ABOVE, true), 4);
                    }
                    net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state);
                }
                //:smileW:
            }
        }
    }

    @Override
    public AbstractBlock.OffsetType getOffsetType()
    {
        return OffsetType.XZ;
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, IBlockReader worldIn, BlockPos pos)
    {
        Block block = state.getBlock();
        return block == Blocks.GRASS_BLOCK || block == Blocks.DIRT || block == Blocks.COARSE_DIRT || block == Blocks.PODZOL || block == Blocks.SAND || block == Blocks.RED_SAND || (block == ModBlocks.TALL_REED.get() && state.getValue(BlockStateProperties.DOUBLE_BLOCK_HALF) == DoubleBlockHalf.UPPER);
    }
}
