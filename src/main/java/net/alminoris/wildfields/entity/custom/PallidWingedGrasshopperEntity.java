package net.alminoris.wildfields.entity.custom;

import net.alminoris.wildfields.entity.ModEntities;
import net.alminoris.wildfields.item.ModItems;
import net.alminoris.wildfields.sound.ModSounds;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.Animation;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.RawAnimation;


public class PallidWingedGrasshopperEntity extends AnimalEntity implements GeoEntity
{
    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    public PallidWingedGrasshopperEntity(EntityType<? extends AnimalEntity> entityType, World world)
    {
        super(entityType, world);
        this.setPathfindingPenalty(PathNodeType.WATER, -1.0F);
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

        if (this.random.nextFloat() < 0.15F)
        {
            this.dropStack(new ItemStack(ModItems.PALLID_WINGED_GRASSHOPPER_LEG, 1));
        }
    }

    public static DefaultAttributeContainer.Builder setAttributes()
    {
        return AnimalEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 5.0D)
                .add(EntityAttributes.GENERIC_JUMP_STRENGTH, 0.7D)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2D);
    }

    @Override
    protected void initGoals()
    {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new WanderAroundGoal(this, 1.0));
        this.goalSelector.add(2, new AnimalMateGoal(this, 0.5D));
        this.goalSelector.add(3, new TemptGoal(this, 0.55D, stack -> stack.isOf(Items.SHORT_GRASS), true));
        this.goalSelector.add(4, new FollowParentGoal(this, 0.5D));
        this.goalSelector.add(5, new EscapeDangerGoal(this, 0.6D));
        this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 5.0F));

        super.initGoals();
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return ModSounds.SOUND_PALLID_WINGED_GRASSHOPPER_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source)
    {
        return ModSounds.SOUND_PALLID_WINGED_GRASSHOPPER_HURT;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return ModSounds.SOUND_PALLID_WINGED_GRASSHOPPER_DEATH;
    }

    @Override
    public boolean isBreedingItem(ItemStack stack)
    {
        return stack.isOf(Items.SHORT_GRASS);
    }

    @Override
    public @Nullable PallidWingedGrasshopperEntity createChild(ServerWorld world, PassiveEntity entity)
    {
        return ModEntities.PALLID_WINGED_GRASSHOPPER.create(world);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar)
    {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 0, state ->
        {
            if (this.jumping)
            {
                return state.setAndContinue(RawAnimation.begin().then("jump", Animation.LoopType.HOLD_ON_LAST_FRAME));
            }

            if(state.isMoving())
            {
                return state.setAndContinue(RawAnimation.begin().then("run", Animation.LoopType.LOOP));
            }

            return state.setAndContinue(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
        }));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache()
    {
        return cache;
    }
}