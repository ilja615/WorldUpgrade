package ilja615.worldupgrade.blocks.special;

import ilja615.worldupgrade.init.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.GrassBlock;
import net.minecraft.block.material.Material;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;

public class OvergrownJungleRockBlock extends GrassBlock
{
    public OvergrownJungleRockBlock(Properties properties)
    {
        super(properties);

        setRegistryName("jungle_rock_overgrown");
        ModBlocks.BLOCKS.add(this);
    }

    @Override
    public boolean canSustainPlant(BlockState state, IBlockReader world, BlockPos pos, Direction facing, IPlantable plantable) {
        PlantType type = plantable.getPlantType(world, pos.up());

        switch (type) {
            case Desert: return true;
            case Nether: return false;
            case Crop: return false;
            case Cave: return true;
            case Plains: return true;
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
