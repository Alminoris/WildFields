package net.alminoris.wildfields.world.gen;

import net.alminoris.wildfields.entity.ModEntities;
import net.alminoris.wildfields.entity.custom.*;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.world.Heightmap;

public class ModEntityGeneration
{
    public static void addSpawns()
    {
        SpawnRestriction.register(ModEntities.MARMOT, SpawnRestriction.Location.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MarmotEntity::isValidNaturalSpawn);

        SpawnRestriction.register(ModEntities.DARKLING_BEETLE, SpawnRestriction.Location.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, DarklingBeetleEntity::isValidNaturalSpawn);

        SpawnRestriction.register(ModEntities.STEPPE_EAGLE, SpawnRestriction.Location.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SteppeEagleEntity::isValidNaturalSpawn);

        SpawnRestriction.register(ModEntities.STEPPE_VIPER, SpawnRestriction.Location.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SteppeViperEntity::isValidNaturalSpawn);

        SpawnRestriction.register(ModEntities.SAIGA, SpawnRestriction.Location.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SaigaEntity::isValidNaturalSpawn);

        SpawnRestriction.register(ModEntities.SERVAL, SpawnRestriction.Location.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, ServalEntity::isValidNaturalSpawn);
    }
}
