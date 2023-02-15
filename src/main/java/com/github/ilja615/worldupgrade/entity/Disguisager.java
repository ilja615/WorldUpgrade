package com.github.ilja615.worldupgrade.entity;

import com.github.ilja615.worldupgrade.init.ModParticles;
import com.github.ilja615.worldupgrade.init.ModSounds;
import net.minecraft.core.HolderSet;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.InstrumentTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.JumpControl;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.*;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.util.GoalUtils;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.AbstractIllager;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Ravager;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.item.Instrument;
import net.minecraft.world.item.InstrumentItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class Disguisager extends AbstractIllager
{
    private static final EntityDataAccessor<Boolean> DATA_IS_DISGUISED = SynchedEntityData.defineId(Disguisager.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> DATA_IS_BLOWING_HORN = SynchedEntityData.defineId(Disguisager.class, EntityDataSerializers.BOOLEAN);

    private int jumpTicks;
    private int jumpDuration;
    private boolean wasOnGround;
    private int jumpDelayTicks;

    public Disguisager(EntityType<? extends AbstractIllager> entityType, Level level)
    {
        super(entityType, level);
        this.jumpControl = new Disguisager.JumpHelperController(this);
        this.moveControl = new Disguisager.MoveHelperController(this);
    }

    @Override
    protected void registerGoals()
    {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new JumpOutOfDisguiseGoal(this));
        this.goalSelector.addGoal(2, new AbstractIllager.RaiderOpenDoorGoal(this));
        this.goalSelector.addGoal(3, new Raider.HoldGroundAttackGoal(this, 10.0F));
        this.goalSelector.addGoal(4, new Disguisager.AttackGoal(this));
        this.targetSelector.addGoal(2, (new HurtByTargetGoal(this, Raider.class)).setAlertOthers());
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
        this.goalSelector.addGoal(8, new RandomStrollGoal(this, 0.6D));
        this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Player.class, 3.0F, 1.0F));
        this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Mob.class, 8.0F));
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_IS_DISGUISED, true);
        this.entityData.define(DATA_IS_BLOWING_HORN, false);

    }

    @Override
    public void applyRaidBuffs(int p_37844_, boolean p_37845_)
    {}

    public void readAdditionalSaveData(CompoundTag compound)
    {
        super.addAdditionalSaveData(compound);
        this.entityData.set(DATA_IS_DISGUISED, compound.getBoolean("disguised"));
        this.entityData.set(DATA_IS_BLOWING_HORN, compound.getBoolean("blowing_horn"));

    }

    public void addAdditionalSaveData(CompoundTag compound)
    {
        super.readAdditionalSaveData(compound);
        compound.putBoolean("disguised", this.entityData.get(DATA_IS_DISGUISED));
        compound.putBoolean("blowing_horn", this.entityData.get(DATA_IS_BLOWING_HORN));

    }

    public boolean isDisguised() {
        return this.entityData.get(DATA_IS_DISGUISED);
    }

    public boolean isBlowingHorn() {
        return this.entityData.get(DATA_IS_BLOWING_HORN);
    }


    @Override
    protected int calculateFallDamage(float p_225508_1_, float p_225508_2_)
    {
        return (int)(0.2D * super.calculateFallDamage(p_225508_1_, p_225508_2_));
    }

    protected float getJumpPower()
    {
        if (this.isDisguised())
            return 0.2f;

        if (!this.horizontalCollision && (!this.moveControl.hasWanted() || !(this.moveControl.getWantedY() > this.getY() + 0.5D))) {
            Path path = this.navigation.getPath();
            if (path != null && !path.isDone()) {
                Vec3 vec3 = path.getNextEntityPos(this);
                if (vec3.y > this.getY() + 3.0D) {
                    return 0.9F;
                }
                if (vec3.y > this.getY() + 1.5D) {
                    return 0.7F;
                }
                if (vec3.y > this.getY() + 0.5D) {
                    return 0.5F;
                }
            }

            return this.moveControl.getSpeedModifier() <= 0.6D ? 0.2F : 0.3F;
        } else {
            return 0.5F;
        }
    }

    protected void jumpFromGround() {
        super.jumpFromGround();
        double d0 = this.moveControl.getSpeedModifier();
        if (d0 > 0.0D) {
            double d1 = this.getDeltaMovement().horizontalDistanceSqr();
            if (d1 < 0.01D) {
                this.moveRelative(0.1F, new Vec3(0.0D, 0.0D, 1.0D));
            }
        }

        if (!this.level.isClientSide) {
            this.level.broadcastEntityEvent(this, (byte)1);
        }
    }

    public float getJumpCompletion(float p_29736_) {
        return this.jumpDuration == 0 ? 0.0F : ((float)this.jumpTicks + p_29736_) / (float)this.jumpDuration;
    }

    public void setSpeedModifier(double p_29726_) {
        this.getNavigation().setSpeedModifier(p_29726_);
        this.moveControl.setWantedPosition(this.moveControl.getWantedX(), this.moveControl.getWantedY(), this.moveControl.getWantedZ(), p_29726_);
    }

    public void setJumping(boolean p_29732_) {
        super.setJumping(p_29732_);
        if (p_29732_) {
            this.playSound(this.getJumpSound(), this.getSoundVolume(), ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F) * 0.8F);
        }
    }

    public void startJumping() {
        this.setJumping(true);
        this.jumpDuration = 10;
        this.jumpTicks = 0;
    }

    protected void customServerAiStep()
    {
        if (this.jumpDelayTicks > 0) {
            --this.jumpDelayTicks;
        }

        if (!this.isNoAi() && GoalUtils.hasGroundPathNavigation(this)) {
            boolean flag = ((ServerLevel)this.level).isRaided(this.blockPosition());
            ((GroundPathNavigation)this.getNavigation()).setCanOpenDoors(flag);
        }

        if (this.onGround) {
            if (!this.wasOnGround) {
                this.setJumping(false);
                this.checkLandingDelay();
            }

            if (this.jumpDelayTicks == 0) {
                LivingEntity livingentity = this.getTarget();
                if (livingentity != null && this.distanceToSqr(livingentity) < 16.0D) {
                    this.facePoint(livingentity.getX(), livingentity.getZ());
                    this.moveControl.setWantedPosition(livingentity.getX(), livingentity.getY(), livingentity.getZ(), this.moveControl.getSpeedModifier());
                    this.startJumping();
                    this.wasOnGround = true;
                }
            }

            Disguisager.JumpHelperController jumpControl = (Disguisager.JumpHelperController)this.jumpControl;
            if (!jumpControl.wantJump()) {
                if (this.moveControl.hasWanted() && this.jumpDelayTicks == 0) {
                    Path path = this.navigation.getPath();
                    Vec3 vec3 = new Vec3(this.moveControl.getWantedX(), this.moveControl.getWantedY(), this.moveControl.getWantedZ());
                    if (path != null && !path.isDone()) {
                        vec3 = path.getNextEntityPos(this);
                    }

                    this.facePoint(vec3.x, vec3.z);
                    this.startJumping();
                }
            } else if (!jumpControl.canJump()) {
                this.enableJumpControl();
            }
        }

        this.wasOnGround = this.onGround;

        //super.customServerAiStep();
    }

    private void facePoint(double p_29687_, double p_29688_) {
        this.setYRot((float)(Mth.atan2(p_29688_ - this.getZ(), p_29687_ - this.getX()) * (double)(180F / (float)Math.PI)) - 90.0F);
    }

    private void enableJumpControl() {
        ((Disguisager.JumpHelperController)this.jumpControl).setCanJump(true);
    }

    private void disableJumpControl() {
        ((Disguisager.JumpHelperController)this.jumpControl).setCanJump(false);
    }

    private void setLandingDelay() {
        if (this.moveControl.getSpeedModifier() < 2.2D) {
            this.jumpDelayTicks = 10;
        } else {
            this.jumpDelayTicks = 1;
        }

    }

    private void checkLandingDelay() {
        this.setLandingDelay();
        this.disableJumpControl();
    }

    public void aiStep() {
        super.aiStep();
        if (this.jumpTicks != this.jumpDuration) {
            ++this.jumpTicks;
        } else if (this.jumpDuration != 0) {
            this.jumpTicks = 0;
            this.jumpDuration = 0;
            this.setJumping(false);
        }
    }

    protected SoundEvent getJumpSound() {
        return SoundEvents.RABBIT_JUMP;
    }

    public void handleEntityEvent(byte p_70103_1_) {
        if (p_70103_1_ == 1) {
            this.spawnSprintParticle();
            this.jumpDuration = 10;
            this.jumpTicks = 0;
        } else {
            super.handleEntityEvent(p_70103_1_);
        }
    }

    public static class JumpHelperController extends JumpControl
    {
        private final Disguisager disguisager;
        private boolean canJump;

        public JumpHelperController(Disguisager disguisager) {
            super(disguisager);
            this.disguisager = disguisager;
        }

        public boolean wantJump() {
            return this.jump && !this.disguisager.isBlowingHorn();
        }

        public boolean canJump() {
            return this.canJump && !this.disguisager.isBlowingHorn();
        }

        public void setCanJump(boolean canJump) {
            this.canJump = canJump;
        }

        public void tick() {
            if (this.jump && !this.disguisager.isBlowingHorn()) {
                this.disguisager.startJumping();
                this.jump = false;
            }
        }
    }

    static class MoveHelperController extends MoveControl
    {
        private final Disguisager disguisager;
        private double nextJumpSpeed;

        public MoveHelperController(Disguisager disguisager) {
            super(disguisager);
            this.disguisager = disguisager;
        }

        public void tick() {
            if (this.disguisager.onGround && !this.disguisager.jumping && !((Disguisager.JumpHelperController)this.disguisager.jumpControl).wantJump()) {
                this.disguisager.setSpeedModifier(0.0D);
            } else if (this.hasWanted()) {
                this.disguisager.setSpeedModifier(this.nextJumpSpeed);
            }

            super.tick();
        }

        public void setWantedPosition(double x, double y, double z, double speed) {
            if (this.disguisager.isInWater()) {
                speed = 1.5D;
            }

            super.setWantedPosition(x, y, z, speed);
            if (speed > 0.0D) {
                this.nextJumpSpeed = speed;
            }
        }
    }

    @Override
    public SoundEvent getCelebrateSound()
    {
        return SoundEvents.VINDICATOR_CELEBRATE;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MOVEMENT_SPEED, (double)0.4F)
                .add(Attributes.FOLLOW_RANGE, 12.0D)
                .add(Attributes.MAX_HEALTH, 30.0D)
                .add(Attributes.ATTACK_DAMAGE, 5.0D);
    }

    public AbstractIllager.IllagerArmPose getArmPose() {
        if (this.isAggressive()) {
            return AbstractIllager.IllagerArmPose.ATTACKING;
        } else {
            return this.isCelebrating() ? AbstractIllager.IllagerArmPose.CELEBRATING : AbstractIllager.IllagerArmPose.CROSSED;
        }
    }

    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor serverLevelAccessor, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData, @Nullable CompoundTag coumpound) {
        SpawnGroupData spawngroupdata = super.finalizeSpawn(serverLevelAccessor, difficulty, spawnType, spawnGroupData, coumpound);
        ((GroundPathNavigation)this.getNavigation()).setCanOpenDoors(true);
        RandomSource randomsource = serverLevelAccessor.getRandom();
        this.populateDefaultEquipmentSlots(randomsource, difficulty);
        this.populateDefaultEquipmentEnchantments(randomsource, difficulty);
        this.entityData.set(DATA_IS_DISGUISED, true);
        return spawngroupdata;
    }

    public boolean isAlliedTo(Entity entity) {
        if (super.isAlliedTo(entity)) {
            return true;
        } else if (entity instanceof LivingEntity && ((LivingEntity)entity).getMobType() == MobType.ILLAGER) {
            return this.getTeam() == null && entity.getTeam() == null;
        } else {
            return false;
        }
    }

    class AttackGoal extends MeleeAttackGoal
    {
        public AttackGoal(Disguisager disguisager) {
            super(disguisager, 1.0D, false);
        }

        protected double getAttackReachSqr(LivingEntity livingEntity) {
            if (this.mob.getVehicle() instanceof Ravager) {
                float f = this.mob.getVehicle().getBbWidth() - 0.1F;
                return (double)(f * 2.0F * f * 2.0F + livingEntity.getBbWidth());
            } else {
                return super.getAttackReachSqr(livingEntity);
            }
        }
    }

    public class JumpOutOfDisguiseGoal extends Goal
    {
        private final Disguisager disguisager;
        private LivingEntity target;
        protected int delay;
        protected boolean blewHorn = false;

        public JumpOutOfDisguiseGoal(Disguisager p_i1655_1_)
        {
            this.disguisager = p_i1655_1_;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        public boolean canUse()
        {
            this.target = this.disguisager.getTarget();
            if (this.target == null)
                return false;
            else if (this.target instanceof Player && ( ((Player)this.target).isCreative() || ((Player)this.target).isSpectator()) )
                return false;
            else return this.disguisager.isDisguised() && this.disguisager.distanceToSqr(this.target) < 16.0D;
        }

        public boolean canContinueToUse() {
            return this.target != null && this.target.isAlive() && this.delay > 0;
        }

        public void start()
        {
            this.target = this.disguisager.getTarget();
            HolderSet<Instrument> holderset = BuiltInRegistries.INSTRUMENT.getOrCreateTag(InstrumentTags.REGULAR_GOAT_HORNS);
            ItemStack horn = InstrumentItem.create(Items.GOAT_HORN, holderset.getRandomElement(random).get());
            this.disguisager.setItemInHand(InteractionHand.MAIN_HAND, horn);
            this.disguisager.entityData.set(DATA_IS_BLOWING_HORN, true);
            this.disguisager.entityData.set(DATA_IS_DISGUISED, false);
            this.disguisager.move(MoverType.SELF, new Vec3(0.0f, 1.5f, 0.0f));
            this.delay = 70;
            if (this.disguisager.level instanceof ServerLevel serverLevel)
                for (int i = 0; i < 15; i++)
                    serverLevel.sendParticles(ModParticles.LEAF_PARTICLE.get(), this.disguisager.position().x, this.disguisager.position().y + 0.05d, this.disguisager.position().z, 1, 0.0d, 0.0d, 0.0d, 0.0d);
        }

        public void tick() {
            --this.delay;
            this.disguisager.navigation.stop();
            if (this.delay == 0) {
                this.disguisager.entityData.set(DATA_IS_BLOWING_HORN, false);
                this.disguisager.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(Items.WOODEN_AXE));
            } else if (!this.blewHorn && this.disguisager.onGround)
            {
                this.blewHorn = true;
                this.disguisager.playSound(ModSounds.DISGUISAGER_CALL_HORN.get(), 1.0f, 1.0f);
            } else if (this.blewHorn && this.delay % 10 == 0) {
                if (this.disguisager.level instanceof ServerLevel serverLevel)
                    serverLevel.sendParticles(ParticleTypes.NOTE, this.disguisager.position().x, this.disguisager.position().y + 2.2d, this.disguisager.position().z, 1, 0.0d, 0.0d, 0.0d, 0.0d);
            }
        }
    }
}
