package net.alminoris.wildfields.world.gen;

import net.alminoris.wildfields.entity.ModEntities;
import net.alminoris.wildfields.entity.custom.*;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnLocationTypes;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.BiomeKeys;

public class ModEntityGeneration
{
    public static void addSpawns()
    {
        SpawnRestriction.register(ModEntities.MARMOT, SpawnLocationTypes.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MarmotEntity::isValidNaturalSpawn);

        SpawnRestriction.register(ModEntities.DARKLING_BEETLE, SpawnLocationTypes.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, DarklingBeetleEntity::isValidNaturalSpawn);

        SpawnRestriction.register(ModEntities.STEPPE_EAGLE, SpawnLocationTypes.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SteppeEagleEntity::isValidNaturalSpawn);

        SpawnRestriction.register(ModEntities.STEPPE_VIPER, SpawnLocationTypes.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SteppeViperEntity::isValidNaturalSpawn);

        SpawnRestriction.register(ModEntities.SAIGA, SpawnLocationTypes.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SaigaEntity::isValidNaturalSpawn);

        SpawnRestriction.register(ModEntities.SERVAL, SpawnLocationTypes.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, ServalEntity::isValidNaturalSpawn);
    }
}
