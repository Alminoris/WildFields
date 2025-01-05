package net.alminoris.wildfields.world;

import com.google.common.collect.ImmutableList;
import net.alminoris.wildfields.WildFields;
import net.alminoris.wildfields.util.helper.ModBlockSetsHelper;
import net.minecraft.fluid.Fluids;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.*;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ModPlacedFeatures
{
    public static final RegistryKey<PlacedFeature> OLIVE_PLACED_KEY = registerKey("olive_placed");

    public static final RegistryKey<PlacedFeature> TAMARISK_PLACED_KEY = registerKey("tamarisk_placed");

    public static final RegistryKey<PlacedFeature> STEPPES_GRASS_PLACED_KEY = registerKey("steppes_grass_placed");

    public static final RegistryKey<PlacedFeature> STEPPES_GRASS_1_PLACED_KEY = registerKey("steppes_grass_1_placed");

    public static final RegistryKey<PlacedFeature> STEPPES_GRASS_2_PLACED_KEY = registerKey("steppes_grass_2_placed");

    public static RegistryKey<PlacedFeature> SALTMARSH_WATER_PLACED_KEY = registerKey("saltmarsh_water_placed");

    public static RegistryKey<PlacedFeature> SAND_WATER_PLACED_KEY = registerKey("sand_water_placed");

    public static RegistryKey<PlacedFeature> THYME_PLACED_KEY = registerKey("thyme_placed");

    public static RegistryKey<PlacedFeature> DOLOMITE_PLACED_KEY = registerKey("dolomite_placed");

    public static void bootstrap(Registerable<PlacedFeature> context)
    {
        var configuredFeatureRegistryEntryLookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);;

        register(context, OLIVE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.OLIVE_KEY),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(PlacedFeatures.createCountExtraModifier(0, 0.05f, 1),
                        ModBlockSetsHelper.WOODEN_SAPLINGS.get("olive")));

        register(context, TAMARISK_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.TAMARISK_KEY),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(PlacedFeatures.createCountExtraModifier(0, 0.1f, 1),
                        ModBlockSetsHelper.WOODEN_SAPLINGS.get("tamarisk")));

        register(context, STEPPES_GRASS_PLACED_KEY,
                configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.STEPPES_GRASS_KEY),
                List.of(
                        RarityFilterPlacementModifier.of(32),
                        SquarePlacementModifier.of(),
                        CountPlacementModifier.of(1),
                        PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
                        BiomePlacementModifier.of()));

        register(context, STEPPES_GRASS_1_PLACED_KEY,
                configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.STEPPES_GRASS_1_KEY),
                List.of(
                        SquarePlacementModifier.of(),
                        PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP,
                        BiomePlacementModifier.of()));

        register(context, STEPPES_GRASS_2_PLACED_KEY,
                configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.STEPPES_GRASS_2_KEY),
                List.of(
                        SquarePlacementModifier.of(),
                        PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP,
                        BiomePlacementModifier.of()));

        context.register(
                SALTMARSH_WATER_PLACED_KEY,
                new PlacedFeature(
                        configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.SALTMARSH_WATER_KEY),
                        List.of(
                                CountPlacementModifier.of(UniformIntProvider.create(40, 60)),
                                SquarePlacementModifier.of(),
                                HeightRangePlacementModifier.uniform(YOffset.aboveBottom(60), YOffset.fixed(90)),
                                BiomePlacementModifier.of(),
                                BlockFilterPlacementModifier.of(
                                        BlockPredicate.anyOf(
                                                BlockPredicate.matchingFluids(new Vec3i(1, 0, 0), Fluids.WATER),
                                                BlockPredicate.matchingFluids(new Vec3i(-1, 0, 0), Fluids.WATER),
                                                BlockPredicate.matchingFluids(new Vec3i(0, 0, 1), Fluids.WATER),
                                                BlockPredicate.matchingFluids(new Vec3i(0, 0, -1), Fluids.WATER)
                                        )
                                )
                        )
                )
        );

        context.register(
                SAND_WATER_PLACED_KEY,
                new PlacedFeature(
                        configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.SAND_WATER_KEY),
                        List.of(
                                CountPlacementModifier.of(UniformIntProvider.create(40, 60)),
                                SquarePlacementModifier.of(),
                                HeightRangePlacementModifier.uniform(YOffset.aboveBottom(60), YOffset.fixed(90)),
                                BiomePlacementModifier.of(),
                                BlockFilterPlacementModifier.of(
                                        BlockPredicate.anyOf(
                                                BlockPredicate.matchingFluids(new Vec3i(1, 0, 0), Fluids.WATER),
                                                BlockPredicate.matchingFluids(new Vec3i(-1, 0, 0), Fluids.WATER),
                                                BlockPredicate.matchingFluids(new Vec3i(0, 0, 1), Fluids.WATER),
                                                BlockPredicate.matchingFluids(new Vec3i(0, 0, -1), Fluids.WATER)
                                        )
                                )
                        )
                )
        );

        register(context, THYME_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.THYME_KEY),
                List.of(RarityFilterPlacementModifier.of(48),
                        SquarePlacementModifier.of(),
                        PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
                        BiomePlacementModifier.of()));

        register(
                context,
                DOLOMITE_PLACED_KEY,
                configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.DOLOMITE_KEY),
                List.of(
                        RarityFilterPlacementModifier.of(24),
                        CountPlacementModifier.of(1),
                        SquarePlacementModifier.of(),
                        PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
                        BiomePlacementModifier.of()
                )
        );
    }

    private static List<PlacementModifier> mushroomModifiers(int chance, @Nullable PlacementModifier modifier)
    {
        ImmutableList.Builder<PlacementModifier> builder = ImmutableList.builder();
        if (modifier != null)
            builder.add(modifier);

        if (chance != 0)
            builder.add(RarityFilterPlacementModifier.of(chance));

        builder.add(SquarePlacementModifier.of());
        builder.add(PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP);
        builder.add(BiomePlacementModifier.of());
        return builder.build();
    }

    public static RegistryKey<PlacedFeature> registerKey(String name)
    {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(WildFields.MOD_ID, name));
    }

    private static void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key, RegistryEntry<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers)
    {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}