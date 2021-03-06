package com.github.ilja615.worldupgrade.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

import net.minecraft.block.AbstractBlock.Properties;

public class LeavesPileBlock extends Block
{
    public static final IntegerProperty LAYERS = IntegerProperty.create("layers", 1, 4);
    protected static final VoxelShape[] SHAPES;

    static
    {
        SHAPES = new VoxelShape[]{
//                Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D),
//                Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D),
//                Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D),
//                Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D)
                Block.box(0.0D, 3.0D, 0.0D, 16.0D, 4.0D, 16.0D),
                Block.box(0.0D, 7.0D, 0.0D, 16.0D, 8.0D, 16.0D),
                Block.box(0.0D, 11.0D, 0.0D, 16.0D, 12.0D, 16.0D),
                Block.box(0.0D, 15.0D, 0.0D, 16.0D, 16.0D, 16.0D)
        };
    }

    //it's bit jankly code but i just copy from SnowBlock :T i hope it works !!!!!!!!!!!!!
    public LeavesPileBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState p_220053_1_, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_)
    {
        return SHAPES[p_220053_1_.getValue(LAYERS) - 1];
    }

    @Override
    public boolean useShapeForLightOcclusion(BlockState p_220074_1_)
    {
        return true;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState p_220071_1_, IBlockReader p_220071_2_, BlockPos p_220071_3_, ISelectionContext p_220071_4_)
    {
        return VoxelShapes.empty();
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> p_206840_1_)
    {
        p_206840_1_.add(LAYERS);
    }

    @Override
    public boolean canBeReplaced(BlockState blockState, BlockItemUseContext blockItemUseContext)
    {
//        int lvt_3_1_ = (Integer)p_196253_1_.get(LAYERS);
//        if (p_196253_2_.getItem().getItem() == this.asItem() && lvt_3_1_ < 8) {
//            if (p_196253_2_.replacingClickedOnBlock()) {
//                return p_196253_2_.getFace() == Direction.UP;
//            } else {
//                return true;
//            }
//        } else {
//            return lvt_3_1_ == 1;
//        }
        if (blockItemUseContext.getItemInHand().getItem() == this.asItem() && blockState.getValue(LAYERS) < 4)
        {
            if (blockItemUseContext.replacingClickedOnBlock())
            {
                return blockItemUseContext.getClickedFace() == Direction.UP;
            } else
            {
                return true;
            }
        } else
        {
            return false;
        }
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext blockItemUseContext)
    {
        BlockState blockState = blockItemUseContext.getLevel().getBlockState(blockItemUseContext.getClickedPos());
        if (blockState.getBlock() == this)
        {
            return blockState.setValue(LAYERS, Math.min(4, blockState.getValue(LAYERS) + 1));
        } else
        {
            return super.getStateForPlacement(blockItemUseContext);
        }
    }

    @Override
    public void entityInside(BlockState state, World worldIn, BlockPos pos, Entity entityIn)
    {
        //entityIn.setMotionMultiplier(state, new Vec3d((double)0.99F, 1.0D, (double)0.99F));
        entityIn.setDeltaMovement(new Vector3d(entityIn.getDeltaMovement().x() * 0.75, entityIn.getDeltaMovement().y(), entityIn.getDeltaMovement().z() * 0.75));
    }
}
