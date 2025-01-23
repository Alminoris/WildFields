package net.alminoris.wildfields.entity.custom;

import net.alminoris.wildfields.block.ModBlocks;
import net.alminoris.wildfields.entity.ModEntities;
import net.alminoris.wildfields.entity.custom.ai.goal.EatHeldItemGoal;
import net.alminoris.wildfields.entity.custom.ai.goal.PickUpItemGoal;
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
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.EntityView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.Animation;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;

public class MarmotEntity extends TameableEntity implements GeoEntity
{
    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);
    private boolean isPickUp = false;

    private static final int EXPLORE_DURATION = 400;

    private static int exploreInterval = 1800;
    private int exploreTimer = exploreInterval;

    private static final TrackedData<Boolean> IS_EXPLORE = DataTracker.registerData(MarmotEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    public MarmotEntity(EntityType<? extends TameableEntity> entityType, World world)
    {
        super(entityType, world);
        this.setTamed(false);
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
            this.dropStack(new ItemStack(ModItems.MARMOT_FUR, 1));
        }

        if (this.random.nextFloat() < 0.05F)
        {
            this.dropStack(new ItemStack(ModBlocks.TINY_GRASS, 1));
        }
    }

    public static DefaultAttributeContainer.Builder setAttributes()
    {
        return AnimalEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 9.0D)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.4D)
                .add(EntityAttributes.GENERIC_ARMOR, 0.5f);
    }

    @Override
    protected void initGoals()
    {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new MarmotEscapeDangerGoal(1.5));
        this.goalSelector.add(2, new SitGoal(this));
        this.goalSelector.add(3, new PickUpItemGoal(this, 1.0D, ModItems.OLIVES, Items.SWEET_BERRIES, ModBlocks.TINY_GRASS.asItem(), Items.GRASS, Items.WHEAT_SEEDS)
        {
            @Override
            public boolean canStart()
            {
                return !MarmotEntity.this.isExploring() && super.canStart();
            }
        });
        this.goalSelector.add(4, new EatHeldItemGoal(this, ModBlocks.TINY_GRASS.asItem(), Items.GRASS, Items.WHEAT_SEEDS)
        {
            @Override
            public boolean canStart()
            {
                return !MarmotEntity.this.isExploring() && super.canStart();
            }
        });
        this.goalSelector.add(5, new FollowOwnerGoal(this, 1.0, 10.0F, 2.0F, false));
        this.goalSelector.add(6, new AnimalMateGoal(this, 0.85D));
        this.goalSelector.add(7, new TemptGoal(this, 0.8, Ingredient.fromTag(ModTags.Items.MARMOT_FOOD), false));
        this.goalSelector.add(8, new FollowParentGoal(this, 0.75));
        this.goalSelector.add(9, new EscapeDangerGoal(this, 1.1D));
        this.goalSelector.add(0, new WanderAroundGoal(this, 0.75D));
        this.goalSelector.add(10, new LookAtEntityGoal(this, LivingEntity.class, 8.0F));

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
        return stack.isIn(ModTags.Items.MARMOT_FOOD);
    }

    @Override
    public @Nullable MarmotEntity createChild(ServerWorld world, PassiveEntity entity)
    {
        MarmotEntity marmotEntity = ModEntities.MARMOT.create(world);
        if (marmotEntity != null && entity instanceof MarmotEntity marmotEntity2)
        {
            if (this.isTamed())
            {
                marmotEntity.setOwnerUuid(this.getOwnerUuid());
                marmotEntity.setTamed(true);
            }
        }
        return marmotEntity;
    }

    @Override
    protected void initDataTracker()
    {
        super.initDataTracker();
        this.dataTracker.startTracking(IS_EXPLORE, false);
    }

    public boolean isExploring()
    {
        return this.dataTracker.get(IS_EXPLORE);
    }

    public void setExploring(boolean sitting)
    {
        this.dataTracker.set(IS_EXPLORE, sitting);
        this.setPose(sitting ? EntityPose.ROARING : EntityPose.STANDING);

        if (sitting)
            this.getNavigation().stop();
    }


    @Override
    public void travel(Vec3d movementInput)
    {
        if (this.isExploring())
        {
            this.setVelocity(Vec3d.ZERO);
            return;
        }
        super.travel(movementInput);
    }

    @Override
    public void tickMovement()
    {
        if (this.isExploring())
        {
            this.bodyYaw = this.prevBodyYaw;
            return;
        }
        super.tickMovement();
    }

    @Override
    public void tick() {
        super.tick();

        if (!this.getWorld().isClient && !this.isInSittingPose())
        {
            exploreInterval = 3200 + this.random.nextInt( 3801);

            if (!this.isExploring() && exploreTimer > 0)
            {
                exploreTimer--;
            }
            else if (!this.isExploring() && exploreTimer <= 0)
            {
                this.setExploring(true);
                exploreTimer = EXPLORE_DURATION;
            }

            if (this.isExploring() && exploreTimer > 0)
            {
                exploreTimer--;
            }
            else if (this.isExploring() && exploreTimer <= 0)
            {
                this.setExploring(false);
                exploreTimer = exploreInterval;
            }

            if (this.isExploring() && this.isTouchingWater())
            {
                this.setExploring(false);
                exploreTimer = exploreInterval;
            }
        }
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar)
    {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 0, state ->
        {
            if (this.isInSittingPose())
                return state.setAndContinue(RawAnimation.begin().then("sit", Animation.LoopType.HOLD_ON_LAST_FRAME));

            if (this.isExploring())
                return state.setAndContinue(RawAnimation.begin().then("exploring", Animation.LoopType.PLAY_ONCE));

            if (isPickUp)
            {
                resetPickUpAnimation();
                return state.setAndContinue(RawAnimation.begin().then("pickup", Animation.LoopType.PLAY_ONCE));
            }

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

    public void triggerPickUpAnimation()
    {
        this.isPickUp = true;
    }

    public void resetPickUpAnimation()
    {
        this.isPickUp = false;
    }

    @Override
    public EntityDimensions getDimensions(EntityPose pose)
    {
        if (pose == EntityPose.ROARING)
            return EntityDimensions.changing(this.getType().getWidth(), this.getType().getHeight() - 0.5F);
        return super.getDimensions(pose);
    }

    @Override
    public int getMaxLookPitchChange() {
        return this.isInSittingPose() ? 20 : super.getMaxLookPitchChange();
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
    public ActionResult interactMob(PlayerEntity player, Hand hand)
    {
        ItemStack itemStack = player.getStackInHand(hand);
        Item item = itemStack.getItem();
        if (this.getWorld().isClient)
        {
            boolean bl = this.isOwner(player) || this.isTamed() || itemStack.isOf(Items.WHEAT_SEEDS) && !this.isTamed();
            return bl ? ActionResult.CONSUME : ActionResult.PASS;
        }
        else if (this.isTamed())
        {
            if (this.isBreedingItem(itemStack) && this.getHealth() < this.getMaxHealth())
            {
                if (!player.getAbilities().creativeMode)
                {
                    itemStack.decrement(1);
                }

                this.heal((float)item.getFoodComponent().getHunger());
                return ActionResult.SUCCESS;
            }
            else
            {
                ActionResult actionResult = super.interactMob(player, hand);
                if ((!actionResult.isAccepted() || this.isBaby()) && this.isOwner(player))
                {
                    this.setSitting(!this.isSitting());
                    this.jumping = false;
                    this.navigation.stop();
                    this.setTarget(null);
                    return ActionResult.SUCCESS;
                }
                else
                {
                    return actionResult;
                }
            }
        }
        else if (itemStack.isOf(Items.WHEAT_SEEDS))
        {
            if (!player.getAbilities().creativeMode)
            {
                itemStack.decrement(1);
            }

            if (this.random.nextInt(3) == 0)
            {
                this.setOwner(player);
                this.navigation.stop();
                this.setTarget(null);
                this.setSitting(true);
                this.getWorld().sendEntityStatus(this, EntityStatuses.ADD_POSITIVE_PLAYER_REACTION_PARTICLES);
            }
            else
            {
                this.getWorld().sendEntityStatus(this, EntityStatuses.ADD_NEGATIVE_PLAYER_REACTION_PARTICLES);
            }

            return ActionResult.SUCCESS;
        }
        else
        {
            return super.interactMob(player, hand);
        }
    }

    @Override
    public int getLimitPerChunk()
    {
        return 15;
    }

    @Override
    public EntityView method_48926()
    {
        return this.getWorld();
    }

    class MarmotEscapeDangerGoal extends EscapeDangerGoal
    {
        public MarmotEscapeDangerGoal(double speed) {
            super(MarmotEntity.this, speed);
        }

        @Override
        protected boolean isInDanger() {
            return this.mob.shouldEscapePowderSnow() || this.mob.isOnFire();
        }
    }
}