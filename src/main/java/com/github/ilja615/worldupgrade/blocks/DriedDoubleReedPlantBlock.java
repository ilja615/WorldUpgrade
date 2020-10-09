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

public class DriedDoubleReedPlantBlock extends DoublePlantBlock implements ILiquidContainer
{
    public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final BooleanProperty ABOVE = BooleanProperty.create("above");

    public static final IntegerProperty AGE = BlockStateProperties.AGE_0_3;
    protected static final VoxelShape SHAPE = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D);

    public DriedDoubleReedPlantBlock(Properties properties)
    {
        super(properties);

        this.setDefaultState(this.stateContainer.getBaseState().with(HALF, DoubleBlockHalf.LOWER).with(ABOVE, false));

    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return SHAPE;
    }

    @Override
    public boolean canContainFluid(IBlockReader iBlockReader, BlockPos blockPos, BlockState blockState, Fluid fluid)
    {
        return false;
    }

    @Override
    public boolean receiveFluid(IWorld iWorld, BlockPos blockPos, BlockState blockState, FluidState iFluidState)
    {
        return false;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
    {
        builder.add(HALF, WATERLOGGED, ABOVE, AGE);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context)
    { //ty Squishling for help mate
        BlockPos blockpos = context.getPos();
        FluidState fluidstate = context.getWorld().getFluidState(context.getPos());
        boolean water = context.getWorld().getFluidState(context.getPos()).isTagged(FluidTags.WATER) && context.getWorld().getFluidState(context.getPos()).getLevel() == 8;
        BlockState state = getDefaultState().with(WATERLOGGED, water);

        return blockpos.getY() < context.getWorld().getHeight() - 1 && context.getWorld().getBlockState(blockpos.up()).isReplaceable(context) && !context.getWorld().getFluidState(blockpos.up()).isTagged(FluidTags.WATER) ? state : null;
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack)
    {
        BlockState blockState = worldIn.getBlockState(pos.up());
        if (worldIn.getBlockState(pos.down()).getBlock() == ModBlocks.DRY_TALL_REED.get() || worldIn.getBlockState(pos.down()).getBlock() == ModBlocks.DRY_TOP_REED.get())
        {
            worldIn.setBlockState(pos, ModBlocks.DRY_TOP_REED.get().getDefaultState(), 3);
        } else if (blockState.isAir(worldIn, pos.up()))
            worldIn.setBlockState(pos.up(), this.getDefaultState().with(HALF, DoubleBlockHalf.UPPER).with(WATERLOGGED, false), 3);
    }

    @Override
    public BlockState updatePostPlacement(BlockState state, Direction direction, BlockState state2, IWorld world, BlockPos pos, BlockPos pos2)
    {
        if (state.get(WATERLOGGED))
            world.getPendingFluidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));

        if (world.getBlockState(pos.up()).getBlock() == ModBlocks.DRY_TALL_REED.get() || world.getBlockState(pos.up()).getBlock() == ModBlocks.DRY_TOP_REED.get())
        {
            state = state.with(ABOVE, true);
        } else
        {
            state = state.with(ABOVE, false);
        }

        return super.updatePostPlacement(state, direction, state2, world, pos, pos2);
    }

    @Override
    public FluidState getFluidState(BlockState state)
    {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
    }

    @Override
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand)
    {
        if (!worldIn.isAreaLoaded(pos, 1)) return; // prevent growing from loading unloaded chunks with block update
        if (!state.isValidPosition(worldIn, pos))
        {
            worldIn.destroyBlock(pos, true);
        } else
        {
            BlockPos blockpos = pos.up();
            if (worldIn.isAirBlock(blockpos))
            {

                int j = state.get(AGE);
                if (net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, blockpos, state, true))
                {
                    if (j == 3)
                    {
                        worldIn.setBlockState(blockpos, ModBlocks.DRY_TOP_REED.get().getDefaultState());
                        BlockState blockstate = state.with(AGE, 0);
                        worldIn.setBlockState(pos, blockstate, 3);
                        blockstate.neighborChanged(worldIn, blockpos, this, pos, false);
                    } else
                    {
                        worldIn.setBlockState(pos, state.with(AGE, j + 1).with(ABOVE, true), 4);
                    }
                    net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state);
                }
                //:smileW:
            }
        }
    }

    @Override
    public Block.OffsetType getOffsetType()
    {
        return OffsetType.NONE;
    }

    @Override
    protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos)
    {
        Block block = state.getBlock();
        return block == Blocks.GRASS_BLOCK || block == Blocks.DIRT || block == Blocks.COARSE_DIRT || block == Blocks.PODZOL || block == Blocks.SAND || block == Blocks.RED_SAND || (block == ModBlocks.DRY_TALL_REED.get() && state.get(BlockStateProperties.DOUBLE_BLOCK_HALF) == DoubleBlockHalf.UPPER);
    }
}
