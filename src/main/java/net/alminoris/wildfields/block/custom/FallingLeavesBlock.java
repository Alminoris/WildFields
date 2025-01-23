package net.alminoris.wildfields.block.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.particle.ParticleType;
import net.minecraft.particle.ParticleUtil;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class FallingLeavesBlock extends LeavesBlock
{
    private final SimpleParticleType particleType;
    private final int fallingProbability;

    public static final MapCodec<FallingLeavesBlock> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            createSettingsCodec(),
            Registries.PARTICLE_TYPE.getCodec().fieldOf("particle_type").forGetter(block -> block.particleType),
                    Codec.intRange(1, 100).fieldOf("falling_probability").forGetter(block -> block.fallingProbability))
            .apply(instance, FallingLeavesBlock::new));

    public FallingLeavesBlock(Settings settings, ParticleType<?> particleType, int fallingProbability)
    {
        super(settings);
        this.particleType = (SimpleParticleType) particleType;
        this.fallingProbability = fallingProbability;
    }

    @Override
    public MapCodec<FallingLeavesBlock> getCodec()
    {
        return CODEC;
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
