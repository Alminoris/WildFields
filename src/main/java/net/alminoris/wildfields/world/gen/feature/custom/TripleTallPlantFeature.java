package net.alminoris.wildfields.world.gen.feature.custom;

import com.mojang.serialization.Codec;
import net.alminoris.wildfields.block.ModBlocks;
import net.alminoris.wildfields.block.custom.TripleTallPlantBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class TripleTallPlantFeature extends Feature<TripleTallPlantConfig>
{
    public TripleTallPlantFeature(Codec<TripleTallPlantConfig> codec) {
        super(codec);
    }

    @Override
    public boolean generate(FeatureContext<TripleTallPlantConfig> context)
    {
        WorldAccess world = context.getWorld();
        BlockPos origin = context.getOrigin();
        Random random = context.getRandom();

        BlockState baseState = context.getConfig().blockProvider.get(random, origin);
        if (!(baseState.getBlock() instanceof TripleTallPlantBlock)) {
            return false; // safety
        }

        if (origin.getY() >= world.getTopY() - 2) return false;

        BlockPos groundPos = origin.down();
        BlockState groundState = world.getBlockState(groundPos);
        if (!groundState.isOf(Blocks.GRASS_BLOCK) && !groundState.isOf(Blocks.DIRT)) return false;

        if (!world.getBlockState(origin).isReplaceable()
                || !world.getBlockState(origin.up()).isReplaceable()
                || !world.getBlockState(origin.up(2)).isReplaceable()) {
            return false;
        }

        Block lower  = baseState.getBlock();
        BlockState lowerState  = baseState.with(TripleTallPlantBlock.PART, TripleTallPlantBlock.PlantPart.LOWER);
        BlockState middleState = baseState.with(TripleTallPlantBlock.PART, TripleTallPlantBlock.PlantPart.MIDDLE);
        BlockState upperState  = baseState.with(TripleTallPlantBlock.PART, TripleTallPlantBlock.PlantPart.UPPER);

        world.setBlockState(origin, lowerState, Block.NOTIFY_ALL);
        world.setBlockState(origin.up(), middleState, Block.NOTIFY_ALL);
        world.setBlockState(origin.up(2), upperState, Block.NOTIFY_ALL);

        return true;
    }
}