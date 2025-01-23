package net.alminoris.wildfields.world.tree;

import net.minecraft.block.sapling.LargeTreeSaplingGenerator;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

public class CustomLargeTreeSaplingGenerator extends LargeTreeSaplingGenerator
{
    private final RegistryKey<ConfiguredFeature<?, ?>> largeConfiguredFeatureRegistryKey;
    private RegistryKey<ConfiguredFeature<?, ?>> configuredFeatureRegistryKey;

    public CustomLargeTreeSaplingGenerator(RegistryKey<ConfiguredFeature<?, ?>> largeConfiguredFeatureRegistryKey)
    {
        this.largeConfiguredFeatureRegistryKey = largeConfiguredFeatureRegistryKey;
    }

    public CustomLargeTreeSaplingGenerator(RegistryKey<ConfiguredFeature<?, ?>> configuredFeatureRegistryKey, RegistryKey<ConfiguredFeature<?, ?>> largeConfiguredFeatureRegistryKey)
    {
        this.configuredFeatureRegistryKey = configuredFeatureRegistryKey;
        this.largeConfiguredFeatureRegistryKey = largeConfiguredFeatureRegistryKey;
    }

    @Override
    protected @Nullable RegistryKey<ConfiguredFeature<?, ?>> getTreeFeature(Random random, boolean bees)
    {
        return configuredFeatureRegistryKey;
    }

    @Override
    protected @Nullable RegistryKey<ConfiguredFeature<?, ?>> getLargeTreeFeature(Random random) {
        return largeConfiguredFeatureRegistryKey;
    }
}
