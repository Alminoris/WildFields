package net.alminoris.wildfields.entity.custom;

import net.alminoris.wildfields.entity.ModEntities;
import net.alminoris.wildfields.item.ModItems;
import net.alminoris.wildfields.sound.ModSounds;
import net.alminoris.wildfields.util.ModTags;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
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

import java.util.EnumSet;

public class SteppeViperEntity extends AnimalEntity implements GeoEntity
{
    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    public SteppeViperEntity(EntityType<? extends AnimalEntity> entityType, World world)
    {
        super(entityType, world);

        this.setPathfindingPenalty(PathNodeType.POWDER_SNOW, -1.0F);
        this.setPathfindingPenalty(PathNodeType.DANGER_POWDER_SNOW, -1.0F);
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

        if (this.random.nextFloat() < 0.25F)
        {
            this.dropStack(new ItemStack(ModItems.STEPPE_VIPER_FANG, (int) (Math.random() * 2)));
        }
    }

    public static boolean isValidNaturalSpawn(EntityType<? extends AnimalEntity> type, WorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random)
    {
        boolean bl = SpawnReason.isTrialSpawner(spawnReason) || isLightLevelValidForNaturalSpawn(world, pos);

        boolean isSpawnableBlock = world.getBlockState(pos.down()).isIn(BlockTags.ANIMALS_SPAWNABLE_ON);

        boolean hasFlowersNearby = false;
        BlockPos.Mutable mutablePos = new BlockPos.Mutable();

        for (int dx = -3; dx <= 3; dx++)
        {
            for (int dz = -3; dz <= 3; dz++)
            {
                mutablePos.set(pos.getX() + dx, pos.getY() - 1, pos.getZ() + dz);
                if (world.getBlockState(mutablePos.up()).isIn(ModTags.Blocks.STEPPE_VIPER_PLANTS))
                {
                    hasFlowersNearby = true;
                    break;
                }
            }
            if (hasFlowersNearby) break;
        }

        return isSpawnableBlock && hasFlowersNearby && bl;
    }


    @Override
    public boolean canHaveStatusEffect(StatusEffectInstance effect)
    {
        if (effect.getEffectType() == StatusEffects.POISON)
            return false;
        return super.canHaveStatusEffect(effect);
    }

    public static DefaultAttributeContainer.Builder setAttributes()
    {
        return AnimalEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 12.0D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 4.0D)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 1.4D)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.35D);
    }

    @Override
    protected void initGoals()
    {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new MeleeAttackGoal(this, 1.15D, false)
        {
            @Override
            protected void attack(LivingEntity target)
            {
                if (this.canAttack(target)) target.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 200, 2));
                super.attack(target);
            }
        });
        this.goalSelector.add(2, new AnimalMateGoal(this, 1.1D));
        this.goalSelector.add(3, new TemptGoal(this, 1.0, stack -> stack.isOf(Items.RABBIT), false));
        this.goalSelector.add(4, new FollowParentGoal(this, 1.1));
        this.goalSelector.add(5, new WanderAroundGoal(this, 1.0D));
        this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 0.5F));
        this.goalSelector.add(7, new WanderNearTargetGoal(this, 1.0D, 4F));

        this.targetSelector.add(1, (new RevengeGoal(this)));
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, RabbitEntity.class, true));
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return ModSounds.SOUND_STEPPE_VIPER_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source)
    {
        return ModSounds.SOUND_STEPPE_VIPER_HURT;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return ModSounds.SOUND_STEPPE_VIPER_DEATH;
    }

    @Override
    public boolean isBreedingItem(ItemStack stack)
    {
        return stack.isOf(Items.RABBIT);
    }

    @Override
    public @Nullable SteppeViperEntity createChild(ServerWorld world, PassiveEntity entity)
    {
        return ModEntities.STEPPE_VIPER.create(world);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar)
    {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 0, state ->
        {
            if (this.isAttacking())
                return state.setAndContinue(RawAnimation.begin().then("attack", Animation.LoopType.PLAY_ONCE));

            if(state.isMoving())
                return state.setAndContinue(RawAnimation.begin().then("run", Animation.LoopType.LOOP));

            return state.setAndContinue(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
        }));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache()
    {
        return cache;
    }
}