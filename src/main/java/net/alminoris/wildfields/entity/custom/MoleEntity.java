package net.alminoris.wildfields.entity.custom;

import net.alminoris.wildfields.block.ModBlocks;
import net.alminoris.wildfields.entity.ModEntities;
import net.alminoris.wildfields.entity.custom.ai.goal.EatHeldItemGoal;
import net.alminoris.wildfields.entity.custom.ai.goal.PickUpItemGoal;
import net.alminoris.wildfields.item.ModItems;
import net.alminoris.wildfields.sound.ModSounds;
import net.alminoris.wildfields.util.ModTags;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.FoodComponent;
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
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.Animation;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.RawAnimation;

public class MoleEntity extends TameableEntity implements GeoEntity
{
    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    public MoleEntity(EntityType<? extends TameableEntity> entityType, World world)
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

        if (this.random.nextFloat() < 0.2F)
        {
            this.dropStack(new ItemStack(ModItems.EARTHWORM, 2));
        }
    }

    public static DefaultAttributeContainer.Builder setAttributes()
    {
        return AnimalEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 12.0D)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.35D)
                .add(EntityAttributes.GENERIC_ARMOR, 1.5f);
    }

    @Override
    protected void initGoals()
    {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new FollowOwnerGoal(this, 1.0, 10.0F, 2.0F));
        this.goalSelector.add(2, new AnimalMateGoal(this, 0.85D));
        this.goalSelector.add(3, new TemptGoal(this, 0.8, stack -> stack.isOf(ModItems.EARTHWORM), false));
        this.goalSelector.add(4, new FollowParentGoal(this, 0.75));
        this.goalSelector.add(5, new EscapeDangerGoal(this, 1.1D));
        this.goalSelector.add(6, new WanderAroundGoal(this, 0.75D));
        this.goalSelector.add(7, new LookAtEntityGoal(this, LivingEntity.class, 8.0F));

        super.initGoals();
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return ModSounds.SOUND_MARMOT_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source)
    {
        return ModSounds.SOUND_MARMOT_HURT;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return ModSounds.SOUND_MARMOT_DEATH;
    }

    @Override
    public boolean isBreedingItem(ItemStack stack)
    {
        return stack.isOf(ModItems.EARTHWORM);
    }

    @Override
    public @Nullable MoleEntity createChild(ServerWorld world, PassiveEntity entity)
    {
        return ModEntities.MOLE.create(world);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar)
    {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 0, state ->
        {
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

    @Override
    public boolean damage(DamageSource source, float amount)
    {
        if (this.isInvulnerableTo(source))
        {
            return false;
        }
        else
        {
            if (!this.getWorld().isClient)
                this.setSitting(false);

            return super.damage(source, amount);
        }
    }

    @Override
    public int getLimitPerChunk()
    {
        return 12;
    }
}