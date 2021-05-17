package com.github.ilja615.worldupgrade.entities;

import com.github.ilja615.worldupgrade.init.ModEntities;
import com.github.ilja615.worldupgrade.init.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.SpectralArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.IPacket;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class FlightArrowEntity extends AbstractArrowEntity
{

    public FlightArrowEntity(World worldIn, LivingEntity shooter)
    {
        super(ModEntities.FLIGHT_ARROW.get(), shooter, worldIn);
    }

    public FlightArrowEntity(EntityType<FlightArrowEntity> flightArrowEntityEntityType, World world)
    {
        super(flightArrowEntityEntityType, world);
    }

    @Override
    protected ItemStack getPickupItem() {
         return new ItemStack(ModItems.FLIGHT_ARROW.get());
    }

    @Override
    protected float getWaterInertia() {
        return 0.0f;
    }

    @Override
    public boolean isNoGravity() {
        return true;
    }

    @Override
    public IPacket<?> getAddEntityPacket() {

        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
