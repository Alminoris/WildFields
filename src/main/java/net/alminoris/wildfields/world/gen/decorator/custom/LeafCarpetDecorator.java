package net.alminoris.wildfields.world.gen.decorator.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.alminoris.wildfields.block.ModBlocks;
import net.alminoris.wildfields.world.gen.decorator.ModTreeDecorators;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class LeafCarpetDecorator extends TreeDecorator
{
    public static final Codec<LeafCarpetDecorator> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    BlockStateProvider.TYPE_CODEC.fieldOf("provider").forGetter(decorator -> decorator.provider),
                    Codec.INT.fieldOf("radius").forGetter(decorator -> decorator.radius),
                    Codec.INT.fieldOf("trunk_thickness").forGetter(decorator -> decorator.trunkThickness),
                    Codec.floatRange(0.0F, 1.0F).fieldOf("probability").forGetter(decorator -> decorator.probability)
            ).apply(instance, LeafCarpetDecorator::new)
    );
    private final BlockStateProvider provider;
    private final int radius;
    private final int trunkThickness;
    private final float probability;

    public LeafCarpetDecorator(BlockStateProvider provider, int radius, int trunkThickness, float probability)
    {
        this.provider = provider;
        this.radius = radius;
        this.trunkThickness = trunkThickness;
        this.probability = probability;
    }

    @Override
    protected TreeDecoratorType<?> getType()
    {
        return ModTreeDecorators.LEAF_CARPET_DECORATOR;
    }

    @Override
    public void generate(Generator generator)
    {
        // Get the world
        TestableWorld world = generator.getWorld();

        // Find the lowest log of the tree by iterating over logs
        List<BlockPos> logPositions = generator.getLogPositions(); // Method for getting log positions
        BlockPos lowestLog = logPositions.stream().min(Comparator.comparingInt(BlockPos::getY)).orElse(logPositions.get(0));

        // Place leaf carpets around the base of the tree
        placeLeafCarpets(generator, world, lowestLog, generator.getRandom());
    }

    // inside your LeafCarpetDecorator class — replace the old placeLeafCarpets method with this
    private void placeLeafCarpets(Generator generator, TestableWorld world, BlockPos basePos, Random random)
    {
        List<Block> validBlocks = Arrays.asList(
                Blocks.DIRT, Blocks.GRASS_BLOCK, Blocks.STONE, Blocks.PODZOL, Blocks.SAND,
                Blocks.GRAVEL, Blocks.SNOW_BLOCK, Blocks.MYCELIUM, Blocks.COARSE_DIRT, Blocks.ROOTED_DIRT, Blocks.WATER
        );

        List<Block> validBlocksWithoutWater = Arrays.asList(
                Blocks.DIRT, Blocks.GRASS_BLOCK, Blocks.STONE, Blocks.PODZOL, Blocks.SAND,
                Blocks.GRAVEL, Blocks.SNOW_BLOCK, Blocks.MYCELIUM, Blocks.COARSE_DIRT, Blocks.ROOTED_DIRT
        );

        // Capture log positions once (immutable coordinates)
        List<BlockPos> logPositions = generator.getLogPositions();

        BlockPos.Mutable carpetPos = new BlockPos.Mutable();
        BlockPos.Mutable groundPos = new BlockPos.Mutable();

        // Define trunk boundaries (keeps your original logic)
        int min = -(trunkThickness / 2);
        int max = min + trunkThickness - 1;

        outer:
        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                // Skip positions inside the trunk footprint
                if (x >= min && x <= max && z >= min && z <= max) {
                    continue;
                }

                // Optional: circular patch instead of square
                if (x * x + z * z > radius * radius) {
                    continue;
                }

                if (random.nextFloat() < probability) {
                    carpetPos.set(basePos.getX() + x, basePos.getY() + 1, basePos.getZ() + z);
                    groundPos.set(basePos.getX() + x, basePos.getY(), basePos.getZ() + z);

                    // create immutable copies for testing/replacing (avoid mutable aliasing)
                    BlockPos carpetImmutable = new BlockPos(carpetPos.getX(), carpetPos.getY(), carpetPos.getZ());
                    BlockPos groundImmutable = new BlockPos(groundPos.getX(), groundPos.getY(), groundPos.getZ());

                    // if any log occupies the carpet or ground position, skip
                    boolean carpetIsLog = logPositions.stream()
                            .anyMatch(lp -> lp.getX() == carpetImmutable.getX() && lp.getY() == carpetImmutable.getY() && lp.getZ() == carpetImmutable.getZ());
                    if (carpetIsLog) continue;

                    boolean groundIsLog = logPositions.stream()
                            .anyMatch(lp -> lp.getX() == groundImmutable.getX() && lp.getY() == groundImmutable.getY() && lp.getZ() == groundImmutable.getZ());
                    if (groundIsLog) {
                        // if ground is a log we must not replace it
                        // but if provider isn't FLAT_GRASS we still need to ensure carpet is air — we already checked carpet above
                        // Here we skip this position entirely
                        continue;
                    }

                    // Ask provider for the blockstate at the actual placement position
                    BlockState providerState = provider.get(generator.getRandom(), carpetImmutable);

                    // Only place carpets on air and only replace ground when it's a valid ground block

                    if (world.testBlockState(carpetImmutable, BlockState::isAir) &&
                            world.testBlockState(groundImmutable, state -> validBlocks.contains(state.getBlock()))) {
                        generator.replace(carpetImmutable, providerState);
                    }
                }
            }
        }
    }
}