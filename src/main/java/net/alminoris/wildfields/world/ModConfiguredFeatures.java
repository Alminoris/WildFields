package net.alminoris.wildfields.world;

import net.alminoris.wildfields.WildFields;
import net.alminoris.wildfields.block.ModBlocks;
import net.alminoris.wildfields.util.helper.ModBlockSetsHelper;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.*;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.trunk.BendingTrunkPlacer;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

import java.util.List;

public class ModConfiguredFeatures
{
    public static RegistryKey<ConfiguredFeature<?, ?>> OLIVE_KEY = registerKey("olive");

    public static RegistryKey<ConfiguredFeature<?, ?>> TAMARISK_KEY = registerKey("tamarisk");

    public static RegistryKey<ConfiguredFeature<?, ?>> PLATANUS_KEY = registerKey("platanus");

    public static RegistryKey<ConfiguredFeature<?, ?>> PINK_LAPACHO_KEY = registerKey("pink_lapacho");

    public static RegistryKey<ConfiguredFeature<?, ?>> STEPPES_GRASS_KEY = registerKey("steppes_grass");

    public static RegistryKey<ConfiguredFeature<?, ?>> STEPPES_GRASS_1_KEY = registerKey("steppes_grass_1");

    public static RegistryKey<ConfiguredFeature<?, ?>> STEPPES_GRASS_2_KEY = registerKey("steppes_grass_2");

    public static RegistryKey<ConfiguredFeature<?, ?>> THYME_KEY = registerKey("thyme");

    public static RegistryKey<ConfiguredFeature<?, ?>> SALTMARSH_WATER_KEY = registerKey("saltmarsh_water");

    public static RegistryKey<ConfiguredFeature<?, ?>> SAND_WATER_KEY = registerKey("sand_water");

    public static RegistryKey<ConfiguredFeature<?, ?>> DOLOMITE_KEY = registerKey("dolomite");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context)
    {
        register(context, OLIVE_KEY, Feature.TREE, new TreeFeatureConfig.Builder(
                BlockStateProvider.of(ModBlockSetsHelper.LOGS.get("olive")),
                new BendingTrunkPlacer(
                        4,
                        2,
                        1,
                        5,
                        ConstantIntProvider.create(2)
                ),
                BlockStateProvider.of(ModBlockSetsHelper.LEAVES.get("olive")),
                new LargeOakFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(4), 4),
                new TwoLayersFeatureSize(1, 0, 2)
        ).build());

        register(context, TAMARISK_KEY, Feature.TREE, new TreeFeatureConfig.Builder(
                BlockStateProvider.of(ModBlockSetsHelper.LOGS.get("tamarisk")),
                new StraightTrunkPlacer(1, 0, 0),
                BlockStateProvider.of(ModBlockSetsHelper.LEAVES.get("tamarisk")),
                new BushFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(1), 2),
                new TwoLayersFeatureSize(1, 0, 2)
        ).build());

        register(context, STEPPES_GRASS_KEY, Feature.RANDOM_PATCH,
                ConfiguredFeatures.createRandomPatchFeatureConfig(
                        Feature.SIMPLE_BLOCK,
                        new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.FEATHER_GRASS)),
                        List.of(Blocks.GRASS_BLOCK),
                        4096
                )
        );

        register(context, STEPPES_GRASS_1_KEY, Feature.RANDOM_PATCH,
                ConfiguredFeatures.createRandomPatchFeatureConfig(
                        Feature.SIMPLE_BLOCK,
                        new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.TINY_GRASS)), List.of(Blocks.GRASS_BLOCK),1024));

        register(context, STEPPES_GRASS_2_KEY, Feature.RANDOM_PATCH,
                ConfiguredFeatures.createRandomPatchFeatureConfig(
                        Feature.SIMPLE_BLOCK,
                        new SimpleBlockFeatureConfig(BlockStateProvider.of(Blocks.SHORT_GRASS)), List.of(Blocks.GRASS_BLOCK),1024));

        context.register(
                SALTMARSH_WATER_KEY,
                new ConfiguredFeature<>(
                        Feature.ORE,
                        new OreFeatureConfig(
                                List.of(OreFeatureConfig.createTarget(
                                        new BlockMatchRuleTest(Blocks.GRASS_BLOCK),
                                        ModBlocks.SALTMARSH_BLOCK.getDefaultState()
                                ),OreFeatureConfig.createTarget(
                                        new BlockMatchRuleTest(Blocks.DIRT),
                                        ModBlocks.SALTMARSH_BLOCK.getDefaultState()
                                )),
                                39
                        )
                )
        );

        context.register(
                SAND_WATER_KEY,
                new ConfiguredFeature<>(
                        Feature.ORE,
                        new OreFeatureConfig(
                                List.of(OreFeatureConfig.createTarget(
                                        new BlockMatchRuleTest(Blocks.GRASS_BLOCK),
                                        Blocks.SAND.getDefaultState()
                                ),OreFeatureConfig.createTarget(
                                        new BlockMatchRuleTest(Blocks.DIRT),
                                        Blocks.SAND.getDefaultState()
                                )),
                                39
                        )
                )
        );

        register(context, THYME_KEY, Feature.RANDOM_PATCH,
                ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK,
                        new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.THYME)), List.of(Blocks.GRASS_BLOCK)));

        register(context, DOLOMITE_KEY, Feature.FOREST_ROCK, new SingleStateFeatureConfig(ModBlocks.DOLOMITE_BLOCK.getDefaultState()));
    }

    private static RandomPatchFeatureConfig createRandomPatchFeatureConfig(BlockStateProvider block, int tries)
    {
        return ConfiguredFeatures.createRandomPatchFeatureConfig(tries, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(block)));
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name)
    {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(WildFields.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration)
    {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}