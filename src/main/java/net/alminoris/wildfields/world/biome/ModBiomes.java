package net.alminoris.wildfields.world.biome;

import net.alminoris.wildfields.WildFields;
import net.alminoris.wildfields.entity.ModEntities;
import net.alminoris.wildfields.world.ModPlacedFeatures;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.carver.ConfiguredCarver;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.MiscPlacedFeatures;
import net.minecraft.world.gen.feature.PlacedFeature;

public class ModBiomes
{
    public static final RegistryKey<Biome> STEPPES = RegistryKey.of(RegistryKeys.BIOME,
            Identifier.of(WildFields.MOD_ID, "steppes_biome"));

    public static final RegistryKey<Biome> PRAIRIES = RegistryKey.of(RegistryKeys.BIOME,
            Identifier.of(WildFields.MOD_ID, "prairies_biome"));

    public static final RegistryKey<Biome> PAMPASAS = RegistryKey.of(RegistryKeys.BIOME,
            Identifier.of(WildFields.MOD_ID, "pampasas_biome"));

    public static void bootstrap(Registerable<Biome> context)
    {
        RegistryEntryLookup<PlacedFeature> placedFeatures = context.getRegistryLookup(RegistryKeys.PLACED_FEATURE);
        RegistryEntryLookup<ConfiguredCarver<?>> configuredCarvers = context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER);

        context.register(STEPPES, steppesBiome(placedFeatures, configuredCarvers));

        //context.register(PRAIRIES, prairiesBiome(placedFeatures, configuredCarvers));

        //context.register(PAMPASAS, pampasasBiome(placedFeatures, configuredCarvers));
    }

    public static void globalOverworldGeneration(GenerationSettings.LookupBackedBuilder builder)
    {
        DefaultBiomeFeatures.addAmethystGeodes(builder);
        DefaultBiomeFeatures.addDungeons(builder);
        DefaultBiomeFeatures.addMineables(builder);
        DefaultBiomeFeatures.addDefaultOres(builder);
    }

    public static Biome steppesBiome(RegistryEntryLookup<PlacedFeature> placedFeatures, RegistryEntryLookup<ConfiguredCarver<?>> configuredCarvers)
    {
        GenerationSettings.LookupBackedBuilder biomeBuilder = new GenerationSettings.LookupBackedBuilder(placedFeatures, configuredCarvers);
        SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder();

        spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.RABBIT, 6, 2, 5));
        spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.SHEEP, 4, 4, 4));
        spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(ModEntities.MARMOT, 5, 6, 10));
        spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(ModEntities.SAIGA, 7, 4, 4));
        spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(ModEntities.SERVAL, 7, 1, 2));
        spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(ModEntities.DARKLING_BEETLE, 8, 1, 2));
        spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(ModEntities.STEPPE_VIPER, 15, 1, 2));
        spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(ModEntities.STEPPE_EAGLE, 10, 1, 1));

        DefaultBiomeFeatures.addBatsAndMonsters(spawnBuilder);

        globalOverworldGeneration(biomeBuilder);

        biomeBuilder.feature(GenerationStep.Feature.FLUID_SPRINGS, MiscPlacedFeatures.SPRING_WATER);

        biomeBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.DOLOMITE_PLACED_KEY);

        biomeBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.SALTMARSH_WATER_PLACED_KEY);

        biomeBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.SAND_WATER_PLACED_KEY);

        biomeBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.OLIVE_PLACED_KEY);

        biomeBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.TAMARISK_PLACED_KEY);

        biomeBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.STEPPES_GRASS_PLACED_KEY);

        biomeBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.STEPPES_GRASS_1_PLACED_KEY);

        biomeBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.STEPPES_GRASS_2_PLACED_KEY);

        biomeBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.THYME_PLACED_KEY);

        return new Biome.Builder()
                .precipitation(true)
                .downfall(0.1f)
                .temperature(0.7f)
                .generationSettings(biomeBuilder.build())
                .spawnSettings(spawnBuilder.build())
                .effects((new BiomeEffects.Builder())
                        .waterColor(0xa0ced8)
                        .waterFogColor(0x49757f)
                        .skyColor(0Xa8daf4)
                        .grassColor(0xe4d362)
                        .foliageColor(0xa9a42e)
                        .fogColor(0Xe5e8bb)
                        .moodSound(BiomeMoodSound.CAVE).build())
                .build();
    }

    public static Biome prairiesBiome(RegistryEntryLookup<PlacedFeature> placedFeatures, RegistryEntryLookup<ConfiguredCarver<?>> configuredCarvers)
    {
        GenerationSettings.LookupBackedBuilder biomeBuilder = new GenerationSettings.LookupBackedBuilder(placedFeatures, configuredCarvers);
        SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder();



        DefaultBiomeFeatures.addBatsAndMonsters(spawnBuilder);

        globalOverworldGeneration(biomeBuilder);

        biomeBuilder.feature(GenerationStep.Feature.FLUID_SPRINGS, MiscPlacedFeatures.SPRING_WATER);



        return new Biome.Builder()
                .precipitation(true)
                .downfall(0.4f)
                .temperature(0.8f)
                .generationSettings(biomeBuilder.build())
                .spawnSettings(spawnBuilder.build())
                .effects((new BiomeEffects.Builder())
                        .waterColor(0x4d9b87)
                        .waterFogColor(0x176c5a)
                        .skyColor(0X78b3e7)
                        .grassColor(0x94bb62)
                        .foliageColor(0x5b8922)
                        .fogColor(0Xc9d1c2)
                        .moodSound(BiomeMoodSound.CAVE).build())
                .build();
    }

    public static Biome pampasasBiome(RegistryEntryLookup<PlacedFeature> placedFeatures, RegistryEntryLookup<ConfiguredCarver<?>> configuredCarvers)
    {
        GenerationSettings.LookupBackedBuilder biomeBuilder = new GenerationSettings.LookupBackedBuilder(placedFeatures, configuredCarvers);
        SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder();



        DefaultBiomeFeatures.addBatsAndMonsters(spawnBuilder);

        globalOverworldGeneration(biomeBuilder);

        biomeBuilder.feature(GenerationStep.Feature.FLUID_SPRINGS, MiscPlacedFeatures.SPRING_WATER);

        return new Biome.Builder()
                .precipitation(true)
                .downfall(0.6f)
                .temperature(0.75f)
                .generationSettings(biomeBuilder.build())
                .spawnSettings(spawnBuilder.build())
                .effects((new BiomeEffects.Builder())
                        .waterColor(0x8bdad4)
                        .waterFogColor(0x51a09b)
                        .skyColor(0X94f5ee)
                        .grassColor(0xe1ee85)
                        .foliageColor(0x3d8c3d)
                        .fogColor(0Xf1eae0)
                        .moodSound(BiomeMoodSound.CAVE).build())
                .build();
    }
}