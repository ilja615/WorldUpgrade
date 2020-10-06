package com.github.ilja615.worldupgrade.entities;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomFlyingGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.IFlyingAnimal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class SpoonBillEntity extends AnimalEntity implements IFlyingAnimal
{
    public final boolean flying = true;
    public float wingSwing = 0.0f;

    public SpoonBillEntity(EntityType<? extends AnimalEntity> p_i49856_1_, World p_i49856_2_)
    {
        super(p_i49856_1_, p_i49856_2_);
    }

    @Override
    protected void registerGoals()
    {
        this.goalSelector.addGoal(0, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomFlyingGoal(this, 1.0D));
    }

    @Override
    public void livingTick()
    {
        super.livingTick();
        this.wingSwing += 0.2f;
        this.wingSwing %= 6.2f;
    }

    @Override
    public boolean isBreedingItem(ItemStack stack)
    {
        return false;
    }

    @Nullable
    @Override
    public AgeableEntity createChild(AgeableEntity ageable)
    {
        return null;
    }
}
