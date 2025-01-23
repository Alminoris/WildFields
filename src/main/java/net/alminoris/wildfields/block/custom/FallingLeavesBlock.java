package net.alminoris.wildfields.block.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.client.util.ParticleUtil;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class FallingLeavesBlock extends LeavesBlock
{
    private final DefaultParticleType particleType;
    private final int fallingProbability;

    public FallingLeavesBlock(Settings settings, ParticleType<?> particleType, int fallingProbability)
    {
        super(settings);
        this.particleType = (DefaultParticleType) particleType;
        this.fallingProbability = fallingProbability;
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random)
    {
        super.randomDisplayTick(state, world, pos, random);
        if (random.nextInt(fallingProbability) == 0)
        {
            BlockPos blockPos = pos.down();
            BlockState blockState = world.getBlockState(blockPos);
            if (!isFaceFullSquare(blockState.getCollisionShape(world, blockPos), Direction.UP))
            {
                ParticleUtil.spawnParticle(world, pos, random, particleType);
            }
        }
    }
}
