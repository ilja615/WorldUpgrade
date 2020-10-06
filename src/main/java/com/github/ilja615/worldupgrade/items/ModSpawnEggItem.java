package com.github.ilja615.worldupgrade.items;

import net.minecraft.entity.EntityType;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.nbt.CompoundNBT;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class ModSpawnEggItem extends SpawnEggItem
{
    private final Supplier<EntityType<?>> entityTypeSupplier;

    public ModSpawnEggItem(Supplier<EntityType<?>> entityTypeSupplier, int primaryColorIn, int secondaryColorIn, Properties builder)
    {
        super(null, primaryColorIn, secondaryColorIn, builder);
        this.entityTypeSupplier = entityTypeSupplier;
    }

    @Override
    public EntityType<?> getType(@Nullable CompoundNBT compoundNbtIn)
    {
        if (compoundNbtIn != null && compoundNbtIn.contains("EntityTag", 10))
        {
            CompoundNBT compoundNbt = compoundNbtIn.getCompound("EntityTag");
            if (compoundNbt.contains("id", 8))
            {
                return EntityType.byKey(compoundNbt.getString("id")).orElse(this.entityTypeSupplier.get());
            }
        }

        return this.entityTypeSupplier.get();
    }
}
