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

        SpawnRestriction.register(ModEntities.MOLE, SpawnRestriction.Location.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MoleEntity::isValidNaturalSpawn);

        SpawnRestriction.register(ModEntities.COYOTE, SpawnRestriction.Location.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, CoyoteEntity::isValidNaturalSpawn);

        SpawnRestriction.register(ModEntities.FERRUGINOUS_HAWK, SpawnRestriction.Location.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, FerruginousHawkEntity::isValidNaturalSpawn);

        SpawnRestriction.register(ModEntities.WHITE_TAILED_JACKRABBIT, SpawnRestriction.Location.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, WhiteTailedJackrabbitEntity::canSpawn);

        SpawnRestriction.register(ModEntities.BISON, SpawnRestriction.Location.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, BisonEntity::isValidNaturalSpawn);

        SpawnRestriction.register(ModEntities.PALLID_WINGED_GRASSHOPPER, SpawnRestriction.Location.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, PallidWingedGrasshopperEntity::isValidNaturalSpawn);

        SpawnRestriction.register(ModEntities.BLACK_BILLED_MAGPIE, SpawnRestriction.Location.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, BlackBilledMagpieEntity::isValidNaturalSpawn);

        SpawnRestriction.register(ModEntities.WESTERN_MEADOWLARK, SpawnRestriction.Location.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, WesternMeadowlarkEntity::isValidNaturalSpawn);
    }
}