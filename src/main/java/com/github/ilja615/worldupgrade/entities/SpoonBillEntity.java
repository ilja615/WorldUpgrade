package com.github.ilja615.worldupgrade.entities;

import com.github.ilja615.worldupgrade.init.ModEntities;
import com.github.ilja615.worldupgrade.init.ModItems;
import com.github.ilja615.worldupgrade.util.FoodChecker;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomFlyingGoal;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.IFlyingAnimal;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.stats.Stats;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.*;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.Random;

public class SpoonBillEntity extends AnimalEntity implements IFlyingAnimal
{
    private static final DataParameter<Integer> SPOONBILL_TYPE = EntityDataManager.defineId(SpoonBillEntity.class, DataSerializers.INT);

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
        this.goalSelector.addGoal(1, new SpoonBillEntity.SpoonBillBreedGoal(this, 1.0D));
        this.goalSelector.addGoal(1, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomFlyingGoal(this, 1.0D));
    }

    @Override
    public void aiStep()
    {
        super.aiStep();
        this.wingSwing += 0.2f;
        this.wingSwing %= 6.2f;
    }

    @Override
    public boolean isFood(ItemStack stack)
    {
        return stack.getItem().is(ItemTags.FISHES) && !FoodChecker.isPoisonous(stack.getItem());
    }

    @Nullable
    @Override
    public AgeableEntity getBreedOffspring(ServerWorld serverWorld, AgeableEntity ageableEntity) {
        return ModEntities.SPOONBILL.get().create(serverWorld);
    }

    protected void defineSynchedData()
    {
        super.defineSynchedData();
        this.entityData.define(SPOONBILL_TYPE, 0);
    }

    public int getVariant() { return MathHelper.clamp(this.entityData.get(SPOONBILL_TYPE), 0, 3); }

    public void setVariant(int variantIn) {
        this.entityData.set(SPOONBILL_TYPE, variantIn);
    }

    @Nullable
    public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag)
    {
        int i;
        int f;
        if (spawnDataIn instanceof SpoonBillEntity.SpoonBillData)
        {
            i = ((SpoonBillEntity.SpoonBillData)spawnDataIn).variant;
        } else {
            i = 0;
            f = random.nextInt(10);
                 if (f > 6)  i = 0;
            else if (f > 4)  i = 1;
            else if (f > 2)  i = 2;
            else             i = 3;
            spawnDataIn = new SpoonBillEntity.SpoonBillData(i);
        }

        this.setVariant(i);
        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    static class SpoonBillData extends AgeableEntity.AgeableData
    {
        public final int variant;

        private SpoonBillData(int variantIn)
        {
            super(true);
            this.variant = variantIn;
        }
    }

    public void addAdditionalSaveData(CompoundNBT compound) { super.addAdditionalSaveData(compound); compound.putInt("Variant", this.getVariant()); }
    public void readAdditionalSaveData(CompoundNBT compound) { super.readAdditionalSaveData(compound); this.setVariant(compound.getInt("Variant")); }

    public static AttributeModifierMap.MutableAttribute prepareAttributes()
    {
        return MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 8.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D);
    }

    static class SpoonBillBreedGoal extends BreedGoal {
        private final SpoonBillEntity spoonBillEntity;

        SpoonBillBreedGoal(SpoonBillEntity spoonBillEntity, double speedIn) {
            super(spoonBillEntity, speedIn);
            this.spoonBillEntity = spoonBillEntity;
        }

        /**
         * Spawns a baby animal of the same type.
         */
        protected void breed() {
            ServerPlayerEntity serverplayerentity = this.animal.getLoveCause();
            if (serverplayerentity == null && this.partner.getLoveCause() != null) {
                serverplayerentity = this.partner.getLoveCause();
            }

            if (serverplayerentity != null) {
                serverplayerentity.awardStat(Stats.ANIMALS_BRED);
                CriteriaTriggers.BRED_ANIMALS.trigger(serverplayerentity, this.animal, this.partner, (AgeableEntity)null);
            }

            this.animal.spawnAtLocation(ModItems.SPOONBILL_EGG.get(), 1);
            this.animal.resetLove();
            this.partner.resetLove();
            Random random = this.animal.getRandom();
            if (this.level.getGameRules().getBoolean(GameRules.RULE_DOMOBLOOT)) {
                this.level.addFreshEntity(new ExperienceOrbEntity(this.level, this.animal.getX(), this.animal.getY(), this.animal.getZ(), random.nextInt(7) + 1));
            }
        }
    }

    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
