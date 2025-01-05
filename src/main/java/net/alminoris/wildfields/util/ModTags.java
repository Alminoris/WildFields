package net.alminoris.wildfields.util;

import net.alminoris.wildfields.WildFields;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;

public class ModTags
{
    public static class Blocks
    {
        public static final TagKey<Block> STEPPE_VIPER_PLANTS = createTag("steppe_viper_plants");

        public static final TagKey<Block> SAIGA_SPAWNABLE_ON = createTag("saiga_spawnable_on");

        private static TagKey<Block> createTag(String name)
        {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(WildFields.MOD_ID, name));
        }
    }

    public static class Items
    {
        public static final TagKey<Item> MARMOT_FOOD = createTag("marmot_food");

        public static final TagKey<Item> SAIGA_FOOD = createTag("saiga_food");

        private static TagKey<Item> createTag(String name)
        {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(WildFields.MOD_ID, name));
        }
    }

    public static class Entities
    {
        private static TagKey<EntityType<?>> createTag(String name)
        {
            return TagKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(WildFields.MOD_ID, name));
        }
    }

    public static class Biomes
    {
        public static final TagKey<Biome> IS_STEPPES = createTag("is_steppes");

        private static TagKey<Biome> createTag(String name)
        {
            return TagKey.of(RegistryKeys.BIOME, Identifier.of(WildFields.MOD_ID, name));
        }
    }
}
