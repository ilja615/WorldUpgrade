package ilja615.worldupgrade.blocks;

import ilja615.worldupgrade.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.IProperty;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Collection;
import java.util.Random;

public class FireJetBlock extends Block
{
    public FireJetBlock(Properties properties)
    {
        super(properties);

        setRegistryName("fire_jet");
        //ModBlocks.BLOCKS.add(this);
    }

    @Override
    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn)
    {
        entityIn.setFire(5);
        super.onEntityWalk(worldIn, pos, entityIn);
    }

    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
        if (rand.nextInt(10) == 0)
        {
            worldIn.playSound((double)((float)pos.getX() + 0.5F), (double)((float)pos.getY() + 0.5F), (double)((float)pos.getZ() + 0.5F), SoundEvents.BLOCK_CAMPFIRE_CRACKLE, SoundCategory.BLOCKS, 0.5F + rand.nextFloat(), rand.nextFloat() * 0.7F + 0.6F, false);
        }

        for(int i = 0; i < rand.nextInt(5) + 5; ++i) {
            worldIn.addParticle(ParticleTypes.LAVA, (double)((float)pos.getX() + 0.5F), (double)((float)pos.getY() + 0.5F), (double)((float)pos.getZ() + 0.5F), (double)(rand.nextFloat() / 3.0F), 12.0F, (double)(rand.nextFloat() / 3.0F));
        }
    }
}
