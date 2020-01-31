package ilja615.worldupgrade.blocks.special;

import ilja615.worldupgrade.init.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.FallingBlock;
import net.minecraft.block.material.Material;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;
import net.minecraftforge.common.ToolType;

public class RedPebbleBlock extends FallingBlock
{
    private int dustColor = -8356741;
    public RedPebbleBlock(String name, int dustColor, Properties properties)
    {
        super(properties);

        this.dustColor = dustColor;
        setRegistryName(name);
        ModBlocks.BLOCKS.add(this);
    }

    @OnlyIn(Dist.CLIENT)
    public int getDustColor(BlockState state) {
        return this.dustColor;
    }

    @Override
    public ToolType getHarvestTool(BlockState state) {
        return ToolType.SHOVEL;
    }

    @Override
    public boolean canSustainPlant(BlockState state, IBlockReader world, BlockPos pos, Direction facing, IPlantable plantable) {
        PlantType type = plantable.getPlantType(world, pos.up());

        switch (type) {
            case Desert: return true;
            case Nether: return false;
            case Crop: return false;
            case Cave: return false;
            case Plains: return false;
            case Water: return false;
            case Beach:
                boolean hasWater = (world.getBlockState(pos.east()).getMaterial() == Material.WATER ||
                        world.getBlockState(pos.west()).getMaterial() == Material.WATER ||
                        world.getBlockState(pos.north()).getMaterial() == Material.WATER ||
                        world.getBlockState(pos.south()).getMaterial() == Material.WATER);
                return hasWater;
        }
        return false;
    }
}
