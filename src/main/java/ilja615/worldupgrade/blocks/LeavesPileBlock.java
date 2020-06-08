package ilja615.worldupgrade.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FallingBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.IProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;

public class LeavesPileBlock extends Block
{
    public static final IntegerProperty LAYERS = IntegerProperty.create("layers", 1, 4);;
    protected static final VoxelShape[] SHAPES;

    //it's bit jankly code but i just copy from SnowBlock :T i hope it works !!!!!!!!!!!!!
    public LeavesPileBlock(Properties properties)
    {
        super(properties);
    }

    public VoxelShape getShape(BlockState p_220053_1_, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
        return SHAPES[p_220053_1_.get(LAYERS) - 1];
    }

    public boolean isTransparent(BlockState p_220074_1_) {
        return true;
    }

    public VoxelShape getCollisionShape(BlockState p_220071_1_, IBlockReader p_220071_2_, BlockPos p_220071_3_, ISelectionContext p_220071_4_) {
        return VoxelShapes.empty();
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> p_206840_1_) {
        p_206840_1_.add(new IProperty[]{LAYERS});
    }

    public boolean isReplaceable(BlockState blockState, BlockItemUseContext blockItemUseContext) {
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
        if (blockItemUseContext.getItem().getItem() == this.asItem() && blockState.get(LAYERS) < 4) {
            if (blockItemUseContext.replacingClickedOnBlock()) {
                return blockItemUseContext.getFace() == Direction.UP;
            } else {
                return true;
            }
        } else { return false; }
    }

    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext blockItemUseContext)
    {
        BlockState blockState = blockItemUseContext.getWorld().getBlockState(blockItemUseContext.getPos());
        if (blockState.getBlock() == this) {
            return blockState.with(LAYERS, Math.min(4, blockState.get(LAYERS) + 1));
        } else {
            return super.getStateForPlacement(blockItemUseContext);
        }
    }


    static {
        SHAPES = new VoxelShape[]{
                Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D),
                Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D),
                Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D),
                Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D)
        };
    }

    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
        //entityIn.setMotionMultiplier(state, new Vec3d((double)0.99F, 1.0D, (double)0.99F));
        entityIn.setMotion(new Vec3d(entityIn.getMotion().getX()*0.75,entityIn.getMotion().getY(),entityIn.getMotion().getZ()*0.75));
    }
}
