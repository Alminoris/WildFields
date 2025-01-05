package net.alminoris.wildfields.block.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.VineBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

public class CustomVineBlock extends VineBlock
{

    public CustomVineBlock(Settings settings)
    {
        super(settings);
    }


    @Override
    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random)
    {

    }
}
