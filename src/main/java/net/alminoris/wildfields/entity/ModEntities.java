package net.alminoris.wildfields.entity;

import net.alminoris.wildfields.WildFields;
import net.alminoris.wildfields.entity.custom.*;
import net.alminoris.wildfields.entity.custom.projectile.SteppeArrowEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities
{
    public static final EntityType<MarmotEntity> MARMOT = Registry.register(
            Registries.ENTITY_TYPE, Identifier.of(WildFields.MOD_ID, "marmot"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, MarmotEntity::new)
                    .dimensions(EntityDimensions.fixed(0.8f, 0.7f)).build());

    public static final EntityType<SteppeViperEntity> STEPPE_VIPER = Registry.register(
            Registries.ENTITY_TYPE, Identifier.of(WildFields.MOD_ID, "steppe_viper"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, SteppeViperEntity::new)
                    .dimensions(EntityDimensions.fixed(0.3f, 0.2f)).build());

    public static final EntityType<DarklingBeetleEntity> DARKLING_BEETLE = Registry.register(
            Registries.ENTITY_TYPE, Identifier.of(WildFields.MOD_ID, "darkling_beetle"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, DarklingBeetleEntity::new)
                    .dimensions(EntityDimensions.fixed(0.5f, 0.5f)).build());

    public static final EntityType<SteppeEagleEntity> STEPPE_EAGLE = Registry.register(
            Registries.ENTITY_TYPE, Identifier.of(WildFields.MOD_ID, "steppe_eagle"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, SteppeEagleEntity::new)
                    .dimensions(EntityDimensions.fixed(0.7f, 1.6f)).build());

    public static final EntityType<SaigaEntity> SAIGA = Registry.register(
            Registries.ENTITY_TYPE, Identifier.of(WildFields.MOD_ID, "saiga"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, SaigaEntity::new)
                    .dimensions(EntityDimensions.fixed(1.8f, 1.9f)).build());

    public static final EntityType<ServalEntity> SERVAL = Registry.register(
            Registries.ENTITY_TYPE, Identifier.of(WildFields.MOD_ID, "serval"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, ServalEntity::new)
                    .dimensions(EntityDimensions.fixed(1.3f, 0.8f)).build());

    public static final EntityType<SteppeArrowEntity> STEPPE_ARROW = Registry.register(
            Registries.ENTITY_TYPE, Identifier.of(WildFields.MOD_ID, "silent_arrow"),
            FabricEntityTypeBuilder.<SteppeArrowEntity>create(SpawnGroup.MISC, SteppeArrowEntity::new)
                    .dimensions(EntityDimensions.fixed(0.5f, 0.5f)).build());
}
