package net.alminoris.wildfields.world.gen.decorator.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.alminoris.wildfields.world.gen.decorator.ModTreeDecorators;
import net.minecraft.block.Block;
import net.minecraft.block.VineBlock;
import net.minecraft.registry.Registries;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;


public class CustomVineLogDecorator extends TreeDecorator
{
    public static final MapCodec<CustomVineLogDecorator> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.floatRange(0.0F, 1.0F).fieldOf("probability").forGetter(decorator -> decorator.probability),
            Registries.BLOCK.getCodec().fieldOf("block").forGetter(decorator -> decorator.block)
    ).apply(instance, CustomVineLogDecorator::new));

    private final float probability;
    private static Block block;

    public CustomVineLogDecorator(float probability, Block block)
    {
        this.probability = probability;
        this.block = block;
    }

    @Override
    protected TreeDecoratorType<?> getType()
    {
        return ModTreeDecorators.CUSTOM_VINE_LOG_DECORATOR;
    }

    @Override
    public void generate(Generator generator)
    {
        Random random = generator.getRandom();

        BlockPos pos = generator.getLogPositions().get(2 + random.nextInt((generator.getLogPositions().size() - 4) + 2));

        if (random.nextFloat() < this.probability)
        {
            BlockPos blockPos = pos.west();
            if (generator.isAir(blockPos))
            {
                placeVines(blockPos, VineBlock.EAST, generator);
            }
        }
        else if (random.nextFloat() < this.probability)
        {
            BlockPos blockPos = pos.east();
            if (generator.isAir(blockPos))
            {
                placeVines(blockPos, VineBlock.WEST, generator);
            }
        }
        else if (random.nextFloat() < this.probability)
        {
            BlockPos blockPos = pos.north();
            if (generator.isAir(blockPos))
            {
                placeVines(blockPos, VineBlock.SOUTH, generator);
            }
        }
        else if (random.nextFloat() < this.probability)
        {
            BlockPos blockPos = pos.south();
            if (generator.isAir(blockPos))
            {
                placeVines(blockPos, VineBlock.NORTH, generator);
            }
        }
    }

    /**
     * Places a vine at a given position and then up to 4 more vines going downwards.
     */
    private static void placeVines(BlockPos pos, BooleanProperty faceProperty, Generator generator)
    {
        generator.replace(pos, block.getDefaultState().with(faceProperty, Boolean.valueOf(true)));
    }
}