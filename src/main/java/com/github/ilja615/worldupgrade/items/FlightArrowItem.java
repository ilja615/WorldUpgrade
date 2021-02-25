package com.github.ilja615.worldupgrade.items;

import com.github.ilja615.worldupgrade.entities.FlightArrowEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.SpectralArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class FlightArrowItem extends ArrowItem {
    public FlightArrowItem(Item.Properties builder) {
        super(builder);
    }

    public AbstractArrowEntity createArrow(World worldIn, ItemStack stack, LivingEntity shooter) {
        return new FlightArrowEntity(worldIn, shooter);
    }
}
