package com.github.ilja615.worldupgrade.entities;

import com.github.ilja615.worldupgrade.init.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.passive.fish.AbstractGroupFishEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class BubbleEelEntity extends AbstractGroupFishEntity
{
    public BubbleEelEntity(EntityType<? extends AbstractGroupFishEntity> p_i49856_1_, World p_i49856_2_)
    {
        super(p_i49856_1_, p_i49856_2_);
    }

    @Override
    protected ItemStack getFishBucket()
    {
        return new ItemStack(ModItems.BUBBLE_EEL_BUCKET.get());
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return SoundEvents.ENTITY_COD_AMBIENT;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return SoundEvents.ENTITY_COD_DEATH;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource p_184601_1_)
    {
        return SoundEvents.ENTITY_COD_HURT;
    }

    @Override
    protected SoundEvent getFlopSound()
    {
        return SoundEvents.ENTITY_COD_FLOP;
    }

    public static AttributeModifierMap.MutableAttribute prepareAttributes()
    {
        return MobEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 8.0D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 5.0D)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 3.0D);
    }
}
