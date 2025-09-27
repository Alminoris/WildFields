package net.alminoris.wildfields.entity.custom;

import net.alminoris.wildfields.entity.ModEntities;
import net.alminoris.wildfields.sound.ModSounds;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
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