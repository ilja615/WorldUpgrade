package com.github.ilja615.worldupgrade.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;

public class CharEmbersBlock extends Block
{
    protected static final VoxelShape CHAR_EMBERS_AABB = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 3.0D, 14.0D);

    public CharEmbersBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public Block.OffsetType getOffsetType()
    {
        return Block.OffsetType.XZ;
    }

    @Override
    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn)
    {
        if (!entityIn.isImmuneToFire() && entityIn instanceof LivingEntity && !EnchantmentHelper.hasFrostWalker((LivingEntity) entityIn))
        {
            entityIn.attackEntityFrom(DamageSource.IN_FIRE, 1.0F);
        }
        super.onEntityWalk(worldIn, pos, entityIn);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return CHAR_EMBERS_AABB;


    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos)
    {
        return true;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
        if (rand.nextInt(10) == 0)
        {
            worldIn.playSound((float) pos.getX() + 0.5F, (float) pos.getY() + 0.5F, (float) pos.getZ() + 0.5F, SoundEvents.BLOCK_CAMPFIRE_CRACKLE, SoundCategory.BLOCKS, 0.5F + rand.nextFloat(), rand.nextFloat() * 0.7F + 0.6F, false);
        }
        double d0 = (double) pos.getX() + (rand.nextFloat());
        double d1 = (double) pos.getY() + 0.2F;
        double d2 = (double) pos.getZ() + (rand.nextFloat());
        worldIn.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
        worldIn.addParticle(ParticleTypes.FLAME, d0, d1, d2, 0.0D, 0.0D, 0.0D);
    }
}
