package net.alminoris.wildfields.entity.custom;

import net.alminoris.wildfields.entity.ModEntities;
import net.alminoris.wildfields.item.ModItems;
import net.alminoris.wildfields.sound.ModSounds;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.BeetrootsBlock;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.control.JumpControl;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.Path;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.*;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.Animation;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.RawAnimation;

public class WhiteTailedJackrabbitEntity extends AnimalEntity implements GeoEntity
{
    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    private int jumpTicks;
    private int jumpDuration;
    private boolean lastOnGround;
    private int ticksUntilJump;
    int moreBeetrootTicks;

    public WhiteTailedJackrabbitEntity(EntityType<? extends AnimalEntity> entityType, World world)
    {
        super(entityType, world);
        this.jumpControl = new WhiteTailedJackrabbitEntity.WhiteTailedJackrabbitJumpControl(this);
        this.moveControl = new WhiteTailedJackrabbitEntity.WhiteTailedJackrabbitMoveControl(this);
        this.setSpeed(0.0);
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

        this.dropStack(new ItemStack(ModItems.JACKRABBIT, 1));
    }

    public static DefaultAttributeContainer.Builder setAttributes()
    {
        return AnimalEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 6.0D)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.35D)
                .add(EntityAttributes.GENERIC_ARMOR, 1.5f);
    }

    @Override
    protected void initGoals()
    {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new EatBeetrootCropGoal(this));
        this.goalSelector.add(2, new AnimalMateGoal(this, 0.85D));
        this.goalSelector.add(3, new TemptGoal(this, 0.8, stack -> stack.isOf(Items.BEETROOT), false));
        this.goalSelector.add(4, new FollowParentGoal(this, 0.75));
        this.goalSelector.add(5, new WhiteTailedJackrabbitEntity.EscapeDangerGoal(this, 1.5D));
        this.goalSelector.add(6, new WanderAroundGoal(this, 0.75D));
        this.goalSelector.add(7, new LookAtEntityGoal(this, LivingEntity.class, 8.0F));

        super.initGoals();
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return ModSounds.SOUND_WHITE_TAILED_JACKRABBIT_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source)
    {
        return ModSounds.SOUND_WHITE_TAILED_JACKRABBIT_HURT;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return ModSounds.SOUND_WHITE_TAILED_JACKRABBIT_DEATH;
    }

    @Override
    public boolean isBreedingItem(ItemStack stack)
    {
        return stack.isOf(Items.BEETROOT);
    }

    @Override
    public @Nullable WhiteTailedJackrabbitEntity createChild(ServerWorld world, PassiveEntity entity)
    {
        return ModEntities.WHITE_TAILED_JACKRABBIT.create(world);
    }

    public static boolean canSpawn(EntityType<WhiteTailedJackrabbitEntity> entity, WorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random) {
        return world.getBlockState(pos.down()).isIn(BlockTags.RABBITS_SPAWNABLE_ON) && isLightLevelValidForNaturalSpawn(world, pos);
    }

    @Override
    protected float getJumpVelocity()
    {
        float f = 0.35F;
        if (this.horizontalCollision || (this.moveControl.isMoving() && this.moveControl.getTargetY() > this.getY() + 0.5)) {
            f = 0.6F;
        }

        Path path = this.navigation.getCurrentPath();
        if (path != null && !path.isFinished()) {
            Vec3d vec3d = path.getNodePosition(this);
            if (vec3d.y > this.getY() + 0.5) {
                f = 0.6F;
            }
        }

        if (this.moveControl.getSpeed() <= 0.6) {
            f = 0.25F;
        }

        return super.getJumpVelocity(f / 0.42F);
    }

    @Override
    public void jump() {
        super.jump();
        double d = this.moveControl.getSpeed();
        if (d > 0.0) {
            double e = this.getVelocity().horizontalLengthSquared();
            if (e < 0.01) {
                this.updateVelocity(0.1F, new Vec3d(0.0, 0.0, 1.0));
            }
        }

        if (!this.getWorld().isClient) {
            this.getWorld().sendEntityStatus(this, EntityStatuses.ADD_SPRINTING_PARTICLES_OR_RESET_SPAWNER_MINECART_SPAWN_DELAY);
        }
    }

    public void setSpeed(double speed) {
        this.getNavigation().setSpeed(speed);
        this.moveControl.moveTo(this.moveControl.getTargetX(), this.moveControl.getTargetY(), this.moveControl.getTargetZ(), speed);
    }

    @Override
    public void setJumping(boolean jumping) {
        super.setJumping(jumping);
        if (jumping) {
            this.playSound(this.getJumpSound(), this.getSoundVolume(), ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F) * 0.8F);
        }
    }

    public void startJump() {
        this.setJumping(true);
        this.jumpDuration = 10;
        this.jumpTicks = 0;
    }

    @Override
    public void mobTick() {
        if (this.ticksUntilJump > 0) {
            this.ticksUntilJump--;
        }

        if (this.moreBeetrootTicks > 0) {
            this.moreBeetrootTicks = this.moreBeetrootTicks - this.random.nextInt(3);
            if (this.moreBeetrootTicks < 0) {
                this.moreBeetrootTicks = 0;
            }
        }

        if (this.isOnGround()) {
            if (!this.lastOnGround) {
                this.setJumping(false);
                this.scheduleJump();
            }

            WhiteTailedJackrabbitEntity.WhiteTailedJackrabbitJumpControl rabbitJumpControl = (WhiteTailedJackrabbitEntity.WhiteTailedJackrabbitJumpControl)this.jumpControl;
            if (!rabbitJumpControl.isActive()) {
                if (this.moveControl.isMoving() && this.ticksUntilJump == 0) {
                    Path path = this.navigation.getCurrentPath();
                    Vec3d vec3d = new Vec3d(this.moveControl.getTargetX(), this.moveControl.getTargetY(), this.moveControl.getTargetZ());
                    if (path != null && !path.isFinished()) {
                        vec3d = path.getNodePosition(this);
                    }

                    this.lookTowards(vec3d.x, vec3d.z);
                    this.startJump();
                }
            } else if (!rabbitJumpControl.canJump()) {
                this.enableJump();
            }
        }

        this.lastOnGround = this.isOnGround();
    }

    @Override
    public boolean shouldSpawnSprintingParticles() {
        return false;
    }

    private void lookTowards(double x, double z) {
        this.setYaw((float)(MathHelper.atan2(z - this.getZ(), x - this.getX()) * 180.0F / (float)Math.PI) - 90.0F);
    }

    private void enableJump() {
        ((WhiteTailedJackrabbitEntity.WhiteTailedJackrabbitJumpControl)this.jumpControl).setCanJump(true);
    }

    private void disableJump() {
        ((WhiteTailedJackrabbitEntity.WhiteTailedJackrabbitJumpControl)this.jumpControl).setCanJump(false);
    }

    private void doScheduleJump() {
        if (this.moveControl.getSpeed() < 2.2) {
            this.ticksUntilJump = 10;
        } else {
            this.ticksUntilJump = 1;
        }
    }

    private void scheduleJump() {
        this.doScheduleJump();
        this.disableJump();
    }

    @Override
    public void tickMovement() {
        super.tickMovement();
        if (this.jumpTicks != this.jumpDuration) {
            this.jumpTicks++;
        } else if (this.jumpDuration != 0) {
            this.jumpTicks = 0;
            this.jumpDuration = 0;
            this.setJumping(false);
        }
    }

    protected SoundEvent getJumpSound() {
        return SoundEvents.ENTITY_RABBIT_JUMP;
    }

    boolean wantsBeetroots() {
        return this.moreBeetrootTicks <= 0;
    }

    @Override
    public void handleStatus(byte status) {
        if (status == EntityStatuses.ADD_SPRINTING_PARTICLES_OR_RESET_SPAWNER_MINECART_SPAWN_DELAY) {
            this.spawnSprintingParticles();
            this.jumpDuration = 10;
            this.jumpTicks = 0;
        } else {
            super.handleStatus(status);
        }
    }

    @Override
    public Vec3d getLeashOffset() {
        return new Vec3d(0.0, (double)(0.6F * this.getStandingEyeHeight()), (double)(this.getWidth() * 0.4F));
    }

    static class EatBeetrootCropGoal extends MoveToTargetPosGoal {
        private final WhiteTailedJackrabbitEntity whiteTailedJackrabbitEntity;
        private boolean wantsBeetroots;
        private boolean hasTarget;

        public EatBeetrootCropGoal(WhiteTailedJackrabbitEntity rabbit) {
            super(rabbit, 0.7F, 16);
            this.whiteTailedJackrabbitEntity = rabbit;
        }

        @Override
        public boolean canStart() {
            if (this.cooldown <= 0) {
                if (!this.whiteTailedJackrabbitEntity.getWorld().getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING)) {
                    return false;
                }

                this.hasTarget = false;
                this.wantsBeetroots = this.whiteTailedJackrabbitEntity.wantsBeetroots();
            }

            return super.canStart();
        }

        @Override
        public boolean shouldContinue() {
            return this.hasTarget && super.shouldContinue();
        }

        @Override
        public void tick() {
            super.tick();
            this.whiteTailedJackrabbitEntity
                    .getLookControl()
                    .lookAt(
                            (double)this.targetPos.getX() + 0.5,
                            (double)(this.targetPos.getY() + 1),
                            (double)this.targetPos.getZ() + 0.5,
                            10.0F,
                            (float)this.whiteTailedJackrabbitEntity.getMaxLookPitchChange()
                    );
            if (this.hasReached()) {
                World world = this.whiteTailedJackrabbitEntity.getWorld();
                BlockPos blockPos = this.targetPos.up();
                BlockState blockState = world.getBlockState(blockPos);
                Block block = blockState.getBlock();
                if (this.hasTarget && block instanceof BeetrootsBlock) {
                    int i = (Integer)blockState.get(BeetrootsBlock.AGE);
                    if (i == 0) {
                        world.setBlockState(blockPos, Blocks.AIR.getDefaultState(), Block.NOTIFY_LISTENERS);
                        world.breakBlock(blockPos, true, this.whiteTailedJackrabbitEntity);
                    } else {
                        world.setBlockState(blockPos, blockState.with(BeetrootsBlock.AGE, Integer.valueOf(i - 1)), Block.NOTIFY_LISTENERS);
                        world.emitGameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Emitter.of(this.whiteTailedJackrabbitEntity));
                        world.syncWorldEvent(WorldEvents.BLOCK_BROKEN, blockPos, Block.getRawIdFromState(blockState));
                    }

                    this.whiteTailedJackrabbitEntity.moreBeetrootTicks = 40;
                }

                this.hasTarget = false;
                this.cooldown = 10;
            }
        }

        @Override
        protected boolean isTargetPos(WorldView world, BlockPos pos) {
            BlockState blockState = world.getBlockState(pos);
            if (blockState.isOf(Blocks.FARMLAND) && this.wantsBeetroots && !this.hasTarget) {
                blockState = world.getBlockState(pos.up());
                if (blockState.getBlock() instanceof BeetrootsBlock && ((BeetrootsBlock)blockState.getBlock()).isMature(blockState)) {
                    this.hasTarget = true;
                    return true;
                }
            }

            return false;
        }
    }

    static class EscapeDangerGoal extends net.minecraft.entity.ai.goal.EscapeDangerGoal {
        private final WhiteTailedJackrabbitEntity whiteTailedJackrabbitEntity;

        public EscapeDangerGoal(WhiteTailedJackrabbitEntity rabbit, double speed) {
            super(rabbit, speed);
            this.whiteTailedJackrabbitEntity = rabbit;
        }

        @Override
        public void tick() {
            super.tick();
            this.whiteTailedJackrabbitEntity.setSpeed(this.speed);
        }
    }

    public static class WhiteTailedJackrabbitJumpControl extends JumpControl {
        private final WhiteTailedJackrabbitEntity whiteTailedJackrabbitEntity;
        private boolean canJump;

        public WhiteTailedJackrabbitJumpControl(WhiteTailedJackrabbitEntity rabbit) {
            super(rabbit);
            this.whiteTailedJackrabbitEntity = rabbit;
        }

        public boolean isActive() {
            return this.active;
        }

        public boolean canJump() {
            return this.canJump;
        }

        public void setCanJump(boolean canJump) {
            this.canJump = canJump;
        }

        @Override
        public void tick() {
            if (this.active) {
                this.whiteTailedJackrabbitEntity.startJump();
                this.active = false;
            }
        }
    }

    static class WhiteTailedJackrabbitMoveControl extends MoveControl {
        private final WhiteTailedJackrabbitEntity whiteTailedJackrabbitEntity;
        private double whiteTailedJackrabbitSpeed;

        public WhiteTailedJackrabbitMoveControl(WhiteTailedJackrabbitEntity owner) {
            super(owner);
            this.whiteTailedJackrabbitEntity = owner;
        }

        @Override
        public void tick() {
            if (this.whiteTailedJackrabbitEntity.isOnGround() && !this.whiteTailedJackrabbitEntity.jumping && !((WhiteTailedJackrabbitEntity.WhiteTailedJackrabbitJumpControl)this.whiteTailedJackrabbitEntity.jumpControl).isActive()) {
                this.whiteTailedJackrabbitEntity.setSpeed(0.0);
            } else if (this.isMoving()) {
                this.whiteTailedJackrabbitEntity.setSpeed(this.whiteTailedJackrabbitSpeed);
            }

            super.tick();
        }

        @Override
        public void moveTo(double x, double y, double z, double speed) {
            if (this.whiteTailedJackrabbitEntity.isTouchingWater()) {
                speed = 1.5;
            }

            super.moveTo(x, y, z, speed);
            if (speed > 0.0) {
                this.whiteTailedJackrabbitSpeed = speed;
            }
        }
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
    public int getLimitPerChunk()
    {
        return 12;
    }
}