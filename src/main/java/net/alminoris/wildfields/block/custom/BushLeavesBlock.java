package net.alminoris.wildfields.block.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class BushLeavesBlock extends LeavesBlock
{
    public BushLeavesBlock(Settings settings)
    {
        super(settings);
    }

    @Override
    protected void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity)
    {
        Vec3d vec3d = new Vec3d(0.4, 0.15F, 0.4);
        if (entity instanceof LivingEntity livingEntity && livingEntity.hasStatusEffect(StatusEffects.WEAVING)) {
            vec3d = new Vec3d(0.7, 0.35, 0.7);
        }

        entity.slowMovement(state, vec3d);
    }
}
