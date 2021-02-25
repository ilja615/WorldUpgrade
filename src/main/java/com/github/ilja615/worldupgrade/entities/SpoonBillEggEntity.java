package com.github.ilja615.worldupgrade.entities;

import com.github.ilja615.worldupgrade.init.ModEntities;
import com.github.ilja615.worldupgrade.init.ModItems;
import net.minecraft.entity.*;
import net.minecraft.entity.monster.BlazeEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.particles.ItemParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

public class SpoonBillEggEntity extends ProjectileItemEntity implements IRendersAsItem
{
    public SpoonBillEggEntity(EntityType<SpoonBillEggEntity> p_i50159_1_, World p_i50159_2_) { super(p_i50159_1_, p_i50159_2_); }
    public SpoonBillEggEntity(World p_i1774_1_, LivingEntity p_i1774_2_) { super(ModEntities.SPOONBILL_EGG.get(), p_i1774_2_, p_i1774_1_); }
    public SpoonBillEggEntity(World p_i1775_1_, double p_i1775_2_, double p_i1775_4_, double p_i1775_6_) { super(ModEntities.SPOONBILL_EGG.get(), p_i1775_2_, p_i1775_4_, p_i1775_6_, p_i1775_1_); }

    @Override
    protected Item getDefaultItem()
    {
        return ModItems.SPOONBILL_EGG.get();
    }

    @OnlyIn(Dist.CLIENT)
    public void handleStatusUpdate(byte p_70103_1_)
    {
        if (p_70103_1_ == 3)
        {
            for(int lvt_4_1_ = 0; lvt_4_1_ < 8; ++lvt_4_1_)
            {
                this.world.addParticle(new ItemParticleData(ParticleTypes.ITEM, this.getItem()), this.getPosX(), this.getPosY(), this.getPosZ(), ((double)this.rand.nextFloat() - 0.5D) * 0.08D, ((double)this.rand.nextFloat() - 0.5D) * 0.08D, ((double)this.rand.nextFloat() - 0.5D) * 0.08D);
            }
        }
    }

    protected void onEntityHit(EntityRayTraceResult p_213868_1_)
    {
        super.onEntityHit(p_213868_1_);
        p_213868_1_.getEntity().attackEntityFrom(DamageSource.causeThrownDamage(this, this.func_234616_v_()), 0.0f);
    }

    protected void onImpact(RayTraceResult p_70227_1_)
    {
        super.onImpact(p_70227_1_);
        if (!this.world.isRemote)
        {
            this.world.setEntityState(this, (byte)3);
            SpoonBillEntity entity = ModEntities.SPOONBILL.get().create(this.world);
            entity.setGrowingAge(-24000);
            entity.setLocationAndAngles(this.getPosX(), this.getPosY(), this.getPosZ(), this.rotationYaw, 0.0F);
            entity.onInitialSpawn((ServerWorld)this.world, this.world.getDifficultyForLocation(this.getPosition()), SpawnReason.BREEDING, (ILivingEntityData)null, (CompoundNBT)null);
            this.world.addEntity(entity);
            this.remove();
        }
    }

    @Override
    public IPacket<?> createSpawnPacket()
    {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
