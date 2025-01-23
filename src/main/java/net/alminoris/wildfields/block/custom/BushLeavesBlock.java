package net.alminoris.wildfields.block.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.entity.Entity;
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
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity)
    {
        entity.slowMovement(state, new Vec3d(0.5F, 0.5F, 0.5F));
    }
}
