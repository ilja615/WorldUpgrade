package ilja615.worldupgrade.entities;

import ilja615.worldupgrade.init.ModEntities;
import ilja615.worldupgrade.init.ModItemsNew;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.entity.passive.fish.AbstractGroupFishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class BubbleEelEntity extends AbstractGroupFishEntity
{
    public BubbleEelEntity(EntityType<? extends AbstractGroupFishEntity> p_i49856_1_, World p_i49856_2_) {
        super(p_i49856_1_, p_i49856_2_);
    }

    protected ItemStack getFishBucket() {
        return new ItemStack(ModItemsNew.BUBBLE_EEL_BUCKET.get());
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_COD_AMBIENT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_COD_DEATH;
    }

    protected SoundEvent getHurtSound(DamageSource p_184601_1_) {
        return SoundEvents.ENTITY_COD_HURT;
    }

    protected SoundEvent getFlopSound() {
        return SoundEvents.ENTITY_COD_FLOP;
    }

//    @Override
//    protected void registerAttributes() {
//        super.registerAttributes();
//        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(8.0d);
//        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(5.0d);
//        this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0d);
//    }

}
