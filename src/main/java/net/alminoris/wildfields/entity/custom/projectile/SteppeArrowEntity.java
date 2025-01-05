package net.alminoris.wildfields.entity.custom.projectile;

import net.alminoris.wildfields.entity.ModEntities;
import net.alminoris.wildfields.item.ModItems;
import net.alminoris.wildfields.sound.ModSounds;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class SteppeArrowEntity extends PersistentProjectileEntity
{
    public SteppeArrowEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world)
    {
        super(entityType, world);
    }

    public SteppeArrowEntity(World world, LivingEntity owner, ItemStack stack, @Nullable ItemStack shotFrom)
    {
        super(ModEntities.STEPPE_ARROW, owner, world, stack, shotFrom);
    }

    public SteppeArrowEntity(World world, double x, double y, double z, ItemStack stack, @Nullable ItemStack shotFrom)
    {
        super(ModEntities.STEPPE_ARROW, x, y, z, world, stack, shotFrom);
    }

    @Override
    public void tick()
    {
        super.tick();
        if (this.getWorld().isClient && !this.inGround)
        {
            this.getWorld().addParticle(ParticleTypes.INSTANT_EFFECT,
                    this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
        }
    }

    @Override
    protected void onHit(LivingEntity target)
    {
        super.onHit(target);

        if (this.getOwner() instanceof LivingEntity shooter)
        {
            playSound(ModSounds.SOUND_STEPPE_EAGLE_DEATH, 1.5f, 1.0f);
            StatusEffectInstance statusEffectInstance = new StatusEffectInstance(StatusEffects.SLOWNESS, 200, 3);
            target.addStatusEffect(statusEffectInstance);
        }
    }

    @Override
    protected ItemStack getDefaultItemStack() {
        return new ItemStack(ModItems.STEPPE_ARROW);
    }
}
