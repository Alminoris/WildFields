package net.alminoris.wildfields.entity.custom;

import net.alminoris.wildfields.entity.ModEntities;
import net.alminoris.wildfields.item.ModItems;
import net.alminoris.wildfields.sound.ModSounds;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class WesternMeadowlarkEntity extends BlackBilledMagpieEntity
{
    public WesternMeadowlarkEntity(EntityType<? extends AnimalEntity> entityType, World world)
    {
        super(entityType, world);
    }

    @Override
    public @Nullable WesternMeadowlarkEntity createChild(ServerWorld world, PassiveEntity entity)
    {
        return ModEntities.WESTERN_MEADOWLARK.create(world);
    }

    @Override
    protected void dropLoot(DamageSource damageSource, boolean causedByPlayer)
    {
        if (this.random.nextFloat() < 0.25F)
        {
            this.dropStack(new ItemStack(ModItems.WESTERN_MEADOWLARK_FEATHER, 1));
        }
    }

    @Override
    protected void tickControlled(PlayerEntity controllingPlayer, Vec3d movementInput)
    {
        if (this.isOnGround())
        {
            this.setVelocity(Vec3d.ZERO);
        }
        else
        {
            super.tickControlled(controllingPlayer, movementInput);
        }
    }

    @Override
    public void travel(Vec3d movementInput)
    {
        if (this.isOnGround())
        {
            return;
        }
        super.travel(movementInput);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return ModSounds.SOUND_WESTERN_MEADOWLARK_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return ModSounds.SOUND_WESTERN_MEADOWLARK_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.SOUND_WESTERN_MEADOWLARK_DEATH;
    }
}