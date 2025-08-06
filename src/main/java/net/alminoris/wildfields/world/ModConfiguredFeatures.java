package net.alminoris.wildfields.world;

import net.alminoris.wildfields.WildFields;
import net.alminoris.wildfields.block.ModBlocks;
import net.alminoris.wildfields.util.helper.ModBlockSetsHelper;
import net.alminoris.wildfields.world.gen.decorator.custom.CustomVineLogDecorator;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerbedBlock;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.*;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.trunk.BendingTrunkPlacer;
import net.minecraft.world.gen.trunk.ForkingTrunkPlacer;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

import java.util.List;

public class ModConfiguredFeatures
{
    public static RegistryKey<ConfiguredFeature<?, ?>> OLIVE_KEY = registerKey("olive");

    public static RegistryKey<ConfiguredFeature<?, ?>> TAMARISK_KEY = registerKey("tamarisk");

    public static RegistryKey<ConfiguredFeature<?, ?>> WESTERN_SERVICEBERRY_KEY = registerKey("western_serviceberry");

    public static RegistryKey<ConfiguredFeature<?, ?>> PLATANUS_KEY = registerKey("platanus");

    public static RegistryKey<ConfiguredFeature<?, ?>> PINK_LAPACHO_KEY = registerKey("pink_lapacho");

    public static RegistryKey<ConfiguredFeature<?, ?>> STEPPES_GRASS_KEY = registerKey("steppes_grass");

    public static RegistryKey<ConfiguredFeature<?, ?>> STEPPES_GRASS_1_KEY = registerKey("steppes_grass_1");

    public static RegistryKey<ConfiguredFeature<?, ?>> STEPPES_GRASS_2_KEY = registerKey("steppes_grass_2");

    public static RegistryKey<ConfiguredFeature<?, ?>> THYME_KEY = registerKey("thyme");

    public static RegistryKey<ConfiguredFeature<?, ?>> SPIDER_MILKWEED_KEY = registerKey("spider_milkweed");

    public static RegistryKey<ConfiguredFeature<?, ?>> WORMWOOD_KEY = registerKey("wormwood");

    public static RegistryKey<ConfiguredFeature<?, ?>> SALTMARSH_WATER_KEY = registerKey("saltmarsh_water");

    public static RegistryKey<ConfiguredFeature<?, ?>> SAND_WATER_KEY = registerKey("sand_water");

    public static RegistryKey<ConfiguredFeature<?, ?>> DOLOMITE_KEY = registerKey("dolomite");

    public static RegistryKey<ConfiguredFeature<?, ?>> MARL_KEY = registerKey("marl");

    public static RegistryKey<ConfiguredFeature<?, ?>> LOESSIC_MARL_KEY = registerKey("loessic_marl");

    public static RegistryKey<ConfiguredFeature<?, ?>> VIOLA_KEY = registerKey("viola");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context)
    {
        RuleTest stoneReplaceables = new TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        List<OreFeatureConfig.Target> overworldMarls =
                List.of(OreFeatureConfig.createTarget(stoneReplaceables, ModBlocks.LOAMY_MARL_BLOCK.getDefaultState()),
                        OreFeatureConfig.createTarget(deepslateReplaceables, ModBlocks.FOSSIL_MARLSTONE_BLOCK.getDefaultState()));

        List<OreFeatureConfig.Target> overworldLoessicMarls =
                List.of(OreFeatureConfig.createTarget(stoneReplaceables, ModBlocks.LOESSIC_MARL_BLOCK.getDefaultState()));

        register(context, MARL_KEY, Feature.ORE, new OreFeatureConfig(overworldMarls, 64));

        register(context, LOESSIC_MARL_KEY, Feature.ORE, new OreFeatureConfig(overworldLoessicMarls, 48));

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

        register(context, WESTERN_SERVICEBERRY_KEY, Feature.TREE, new TreeFeatureConfig.Builder(
                BlockStateProvider.of(ModBlockSetsHelper.LOGS.get("western_serviceberry")),
                new ForkingTrunkPlacer(2, 0, 1),
                BlockStateProvider.of(ModBlockSetsHelper.LEAVES.get("western_serviceberry")),
                new BushFoliagePlacer(ConstantIntProvider.create(1), ConstantIntProvider.create(2), 3),
                new TwoLayersFeatureSize(1, 0, 2)
        ).decorators(List.of(new CustomVineLogDecorator(0.85f, ModBlocks.GREEN_LICHEN))).build());

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
                        new SimpleBlockFeatureConfig(BlockStateProvider.of(Blocks.GRASS)), List.of(Blocks.GRASS_BLOCK),1024));

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

        register(context, SPIDER_MILKWEED_KEY, Feature.RANDOM_PATCH,
                ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK,
                        new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.SPIDER_MILKWEED)), List.of(Blocks.GRASS_BLOCK)));

        register(context, WORMWOOD_KEY, Feature.RANDOM_PATCH,
                ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK,
                        new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.WORMWOOD)), List.of(Blocks.GRASS_BLOCK)));

        register(context, DOLOMITE_KEY, Feature.FOREST_ROCK, new SingleStateFeatureConfig(ModBlocks.DOLOMITE_BLOCK.getDefaultState()));

        DataPool.Builder<BlockState> builder = DataPool.builder();
        for (int i = 1; i <= 4; i++)
            for (Direction direction : Direction.Type.HORIZONTAL)
                builder.add(ModBlocks.VIOLA.getDefaultState().with(FlowerbedBlock.FLOWER_AMOUNT, Integer.valueOf(i)).with(FlowerbedBlock.FACING, direction), 1);

        register(context, VIOLA_KEY, Feature.RANDOM_PATCH,
                new RandomPatchFeatureConfig(2, 1, 1, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                        new SimpleBlockFeatureConfig(new WeightedBlockStateProvider(builder)))));
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