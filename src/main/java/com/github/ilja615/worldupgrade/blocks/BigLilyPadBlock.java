package com.github.ilja615.worldupgrade.blocks;

import com.github.ilja615.worldupgrade.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BigLilyPadBlock extends BushBlock
{
    protected static final VoxelShape AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D);

    public BigLilyPadBlock(BlockBehaviour.Properties p_58162_) {
        super(p_58162_);
    }

    public VoxelShape getShape(BlockState p_58169_, BlockGetter p_58170_, BlockPos p_58171_, CollisionContext p_58172_) {
        return AABB;
    }

    protected boolean mayPlaceOn(BlockState state, BlockGetter blockGetter, BlockPos pos) {
        FluidState fluidstate = blockGetter.getFluidState(pos);
        FluidState fluidstate1 = blockGetter.getFluidState(pos.above());
        return (fluidstate.getType() == Fluids.WATER || state.getBlock() == ModBlocks.BIG_PLANT_STEM.get()) && fluidstate1.getType() == Fluids.EMPTY;
    }
}