package com.github.ilja615.worldupgrade.entities;

import com.github.ilja615.worldupgrade.init.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.SpectralArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;

public class FlightArrowEntity extends AbstractArrowEntity
{
    public FlightArrowEntity(EntityType<? extends SpectralArrowEntity> p_i50158_1_, World p_i50158_2_)
    { super(p_i50158_1_, p_i50158_2_);
    }

    public FlightArrowEntity(World worldIn, LivingEntity shooter)
    { super(EntityType.SPECTRAL_ARROW, shooter, worldIn);
    }

    public FlightArrowEntity(World worldIn, double x, double y, double z)
    { super(EntityType.SPECTRAL_ARROW, x, y, z, worldIn);
    }

    @Override
    protected ItemStack getArrowStack() {
         return new ItemStack(ModItems.FLIGHT_ARROW.get());
    }

    @Override
    protected float getWaterDrag() {
        return 0.0f;
    }

    @Override
    public boolean hasNoGravity() {
        return true;
    }
}
