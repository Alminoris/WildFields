package net.alminoris.wildfields.world.tree;

import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

public class CustomSaplingGenerator extends SaplingGenerator
{
    private final RegistryKey<ConfiguredFeature<?, ?>> configuredFeatureRegistryKey;

    public CustomSaplingGenerator(RegistryKey<ConfiguredFeature<?, ?>> configuredFeatureRegistryKey)
    {
        this.configuredFeatureRegistryKey = configuredFeatureRegistryKey;
    }

    @Override
    protected @Nullable RegistryKey<ConfiguredFeature<?, ?>> getTreeFeature(Random random, boolean bees)
    {
        return this.configuredFeatureRegistryKey;
    }
}
