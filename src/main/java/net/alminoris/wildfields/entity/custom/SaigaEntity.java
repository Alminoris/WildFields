package net.alminoris.wildfields.entity.custom;

import net.alminoris.wildfields.entity.ModEntities;
import net.alminoris.wildfields.item.ModItems;
import net.alminoris.wildfields.sound.ModSounds;
import net.alminoris.wildfields.util.ModTags;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.Animation;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.RawAnimation;

import java.util.function.Predicate;

public class SaigaEntity extends AnimalEntity implements GeoEntity
{
    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    private static final int EAT_DURATION = 200;

    private static int eatInterval = 3600;
    private int eatingTimer = eatInterval;

    private static final TrackedData<Boolean> IS_EATING = DataTracker.registerData(SaigaEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    public static final Predicate<LivingEntity> BABY_SAIGA = LivingEntity::isBaby;

    public SaigaEntity(EntityType<? extends AnimalEntity> entityType, World world)
    {
        super(entityType, world);
    }

    @Override
    public @Nullable ItemEntity dropStack(ItemStack stack)
    {
        return super.dropStack(stack);
    }

    @Override
    protected void dropLoot(DamageSource damageSource, boolean causedByPlayer)
    {
        super.dropLoot(damageSource, causedByPlayer);

        if (this.random.nextFloat() < 0.05F)
        {
            this.dropStack(new ItemStack(ModItems.SAIGA_HORN, random.nextInt(2)));
        }

        if (this.random.nextFloat() < 0.5F)
        {
            this.dropStack(new ItemStack(ModItems.SAIGA, random.nextInt(5)));
        }

        if (this.random.nextFloat() < 0.25F)
        {
            this.dropStack(new ItemStack(Items.LEATHER, random.nextInt(3)));
        }
    }

    public static DefaultAttributeContainer.Builder setAttributes()
    {
        return AnimalEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 25.0D)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25D);
    }

    @Override
    protected void initGoals()
    {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new AnimalMateGoal(this, 0.85D));
        this.goalSelector.add(2, new TemptGoal(this, 0.8, stack -> stack.isIn(ModTags.Items.SAIGA_FOOD), false));
        this.goalSelector.add(3, new FollowParentGoal(this, 0.75D));
        this.goalSelector.add(4, new EscapeDangerGoal(this, 1.0D));
        this.goalSelector.add(5, new WanderAroundGoal(this, 0.75D));
        this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 7.0F));

        super.initGoals();
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return ModSounds.SOUND_SAIGA_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source)
    {
        return ModSounds.SOUND_SAIGA_HURT;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return ModSounds.SOUND_SAIGA_DEATH;
    }

    @Override
    public boolean isBreedingItem(ItemStack stack)
    {
        return stack.isIn(ModTags.Items.SAIGA_FOOD);
    }

    @Override
    public @Nullable SaigaEntity createChild(ServerWorld world, PassiveEntity entity)
    {
        return ModEntities.SAIGA.create(world);
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder)
    {
        super.initDataTracker(builder);
        builder.add(IS_EATING, false);
    }

    public boolean isEating()
    {
        return this.dataTracker.get(IS_EATING);
    }

    public void setEating(boolean sitting)
    {
        this.dataTracker.set(IS_EATING, sitting);
        this.setPose(sitting ? EntityPose.SITTING : EntityPose.STANDING);

        if (sitting)
            this.getNavigation().stop();
    }


    @Override
    public void travel(Vec3d movementInput)
    {
        if (this.isEating())
        {
            this.setVelocity(Vec3d.ZERO);
            return;
        }
        super.travel(movementInput);
    }

    @Override
    public void tickMovement()
    {
        if (this.isEating())
        {
            this.bodyYaw = this.prevBodyYaw;
            return;
        }
        super.tickMovement();
    }

    @Override
    public void tick() {
        super.tick();

        if (!this.getWorld().isClient)
        {
            eatInterval = 3600 + this.random.nextInt( 2401);

            if (!this.isEating() && eatingTimer > 0)
            {
                eatingTimer--;
            }
            else if (!this.isEating() && eatingTimer <= 0)
            {
                this.setEating(true);
                eatingTimer = EAT_DURATION;
            }

            if (this.isEating() && eatingTimer > 0)
            {
                eatingTimer--;
            }
            else if (this.isEating() && eatingTimer <= 0)
            {
                this.setEating(false);
                eatingTimer = eatInterval;
            }

            if (this.isEating() && this.isTouchingWater())
            {
                this.setEating(false);
                eatingTimer = eatInterval;
            }
        }
    }

    public static boolean isValidNaturalSpawn(EntityType<? extends AnimalEntity> type, WorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random)
    {
        boolean bl = SpawnReason.isTrialSpawner(spawnReason) || isLightLevelValidForNaturalSpawn(world, pos);
        boolean isSpawnableBlock = world.getBlockState(pos.down()).isIn(ModTags.Blocks.SAIGA_SPAWNABLE_ON);
        return isSpawnableBlock && bl;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar)
    {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 0, state ->
        {
            if (this.isEating())
                return state.setAndContinue(RawAnimation.begin().then("eating", Animation.LoopType.PLAY_ONCE));

            if (state.isMoving())
                return state.setAndContinue(RawAnimation.begin().then("run", Animation.LoopType.LOOP));

            return state.setAndContinue(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
        }));
    }

    @Override
    public EntityDimensions getBaseDimensions(EntityPose pose)
    {
        if (pose == EntityPose.SITTING)
            return EntityDimensions.changing(this.getType().getWidth(), this.getType().getHeight() - 0.5F);

        return super.getBaseDimensions(pose);
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache()
    {
        return cache;
    }

    @Override
    public int getLimitPerChunk()
    {
        return 7;
    }
}