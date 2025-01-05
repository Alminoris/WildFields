package net.alminoris.wildfields.entity.custom;

import net.alminoris.wildfields.entity.ModEntities;
import net.alminoris.wildfields.item.ModItems;
import net.alminoris.wildfields.sound.ModSounds;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.control.FlightMoveControl;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.BirdNavigation;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.Heightmap;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.Animation;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.RawAnimation;

public class SteppeEagleEntity extends AnimalEntity implements GeoEntity, Flutterer
{
    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    private int featherDropCooldown = 1200;

    public SteppeEagleEntity(EntityType<? extends AnimalEntity> entityType, World world)
    {
        super(entityType, world);
        this.moveControl = new FlightMoveControl(this, 10, false);
        this.setPathfindingPenalty(PathNodeType.DANGER_FIRE, -1.0F);
        this.setPathfindingPenalty(PathNodeType.DAMAGE_FIRE, -1.0F);
    }

    @Override
    protected EntityNavigation createNavigation(World world)
    {
        BirdNavigation birdNavigation = new BirdNavigation(this, world);
        birdNavigation.setCanPathThroughDoors(false);
        birdNavigation.setCanSwim(false);
        birdNavigation.setCanEnterOpenDoors(true);
        return birdNavigation;
    }

    @Override
    protected void fall(double heightDifference, boolean onGround, BlockState state, BlockPos landedPosition)
    {
    }

    @Override
    public boolean isPushable() {
        return true;
    }

    @Override
    public @Nullable ItemEntity dropStack(ItemStack stack) {
        return super.dropStack(stack);
    }

    @Override
    protected void dropLoot(DamageSource damageSource, boolean causedByPlayer)
    {
        super.dropLoot(damageSource, causedByPlayer);

        if (this.random.nextFloat() < 0.05F)
        {
            this.dropStack(new ItemStack(ModItems.STEPPE_EAGLE_BEAK, 1));
        }

        if (this.random.nextFloat() < 0.25F)
        {
            this.dropStack(new ItemStack(ModItems.STEPPE_EAGLE_FEATHER, 1));
        }
    }

    @Override
    public void tick()
    {
        super.tick();

        if (!this.getWorld().isClient && !this.isBaby() && this.featherDropCooldown-- <= 0)
        {
            dropFeather();
            this.featherDropCooldown = 3600 + this.random.nextInt(2400);
        }
    }

    private void dropFeather()
    {
        if (this.isAlive() && !this.isInsideWaterOrBubbleColumn())
        {
            this.getWorld().spawnEntity(new ItemEntity(
                    this.getWorld(),
                    this.getX(),
                    this.getY(),
                    this.getZ(),
                    new ItemStack(ModItems.STEPPE_EAGLE_FEATHER)
            ));
        }
    }

    public static DefaultAttributeContainer.Builder setAttributes()
    {
        return AnimalEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 10.0)
                .add(EntityAttributes.GENERIC_FLYING_SPEED, 0.6F)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2F)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 4.0);
    }

    @Override
    protected void initGoals()
    {

        this.goalSelector.add(0, new HighAltitudeWanderGoal(this, 1.0, 200, 80, 0.05f));
        this.goalSelector.add(1, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.add(2, new AnimalMateGoal(this, 1.0D));
        this.goalSelector.add(3, new TemptGoal(this, 1.05, stack -> stack.isOf(Items.RABBIT_FOOT), true));
        this.goalSelector.add(4, new FollowParentGoal(this, 1.0D));
        this.goalSelector.add(5, new EscapeDangerGoal(this, 1.2D));
        this.goalSelector.add(6, new WanderAroundGoal(this, 1.0D));
        this.goalSelector.add(7, new LookAtEntityGoal(this, LivingEntity.class, 5.0F));

        this.targetSelector.add(1, new ActiveTargetGoal<>(this, MarmotEntity.class, true));
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, RabbitEntity.class, true));
        this.targetSelector.add(3, (new RevengeGoal(this)));

        super.initGoals();
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return ModSounds.SOUND_STEPPE_EAGLE_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return ModSounds.SOUND_STEPPE_EAGLE_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.SOUND_STEPPE_EAGLE_DEATH;
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isOf(Items.RABBIT_FOOT);
    }

    @Override
    public @Nullable SteppeEagleEntity createChild(ServerWorld world, PassiveEntity entity)
    {
        return ModEntities.STEPPE_EAGLE.create(world);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar)
    {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 0, state ->
        {
            if (this.isAttacking())
                return state.setAndContinue(RawAnimation.begin().then("attack", Animation.LoopType.PLAY_ONCE));

            if (this.isInAir())
                return state.setAndContinue(RawAnimation.begin().then("flying", Animation.LoopType.LOOP));

            return state.setAndContinue(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
        }));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public boolean isInAir()
    {
        return !this.isOnGround();
    }

    @Override
    public Vec3d getLeashOffset()
    {
        return new Vec3d(0.0, (double) (0.5F * this.getStandingEyeHeight()), (double) (this.getWidth() * 0.4F));
    }

    static class HighAltitudeWanderGoal extends Goal
    {
        private final PathAwareEntity mob;
        private final double speed;
        private final int maxHeight;
        private final int minHeight;
        private final float chance;
        private Vec3d target;
        private boolean descending = false;

        public HighAltitudeWanderGoal(PathAwareEntity mob, double speed, int maxHeight, int minHeight, float chance) {
            this.mob = mob;
            this.speed = speed;
            this.maxHeight = maxHeight;
            this.minHeight = minHeight;
            this.chance = chance;
        }

        @Override
        public boolean canStart()
        {
            if (this.mob.getNavigation().isIdle() && this.mob.getRandom().nextFloat() < this.chance)
            {
                if (this.descending)
                {
                    this.target = findGroundTarget();
                    return this.target != null;
                }
                else
                {
                    World world = this.mob.getWorld();
                    double x = this.mob.getX() + this.mob.getRandom().nextGaussian() * 20;
                    double z = this.mob.getZ() + this.mob.getRandom().nextGaussian() * 20;
                    double y = Math.min(Math.max(this.minHeight, this.mob.getY() + this.mob.getRandom().nextGaussian() * 10), this.maxHeight);

                    this.target = new Vec3d(x, y, z);
                    return true;
                }
            }
            return false;
        }

        @Override
        public void start()
        {
            if (this.target != null)
            {
                this.mob.getNavigation().startMovingTo(this.target.x, this.target.y, this.target.z, this.speed);
            }
        }

        @Override
        public boolean shouldContinue()
        {
            if (this.descending)
            {
                return !this.mob.getNavigation().isIdle() && this.mob.getY() > this.mob.getWorld().getBottomY();
            }
            return !this.mob.getNavigation().isIdle() && this.target != null;
        }

        @Override
        public void stop()
        {
            if (!this.descending && this.mob.getY() >= this.maxHeight - 5)
            {
                this.descending = true;
            }
            else
            {
                this.descending = false;
            }
            this.target = null;
        }

        private Vec3d findGroundTarget()
        {
            World world = this.mob.getWorld();
            Random random = this.mob.getRandom();
            BlockPos groundPos = new BlockPos(
                    (int) (this.mob.getX() + random.nextGaussian() * 20),
                    (int) this.mob.getY(),
                    (int) (this.mob.getZ() + random.nextGaussian() * 20)
            );

            // Шукаємо найближчий блок на землі
            groundPos = world.getTopPosition(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, groundPos);

            if (world.isAir(groundPos))
            {
                return Vec3d.ofBottomCenter(groundPos);
            }
            return null;
        }
    }

}