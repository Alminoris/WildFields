package net.alminoris.wildfields.item;

import com.terraformersmc.terraform.boat.api.TerraformBoatType;
import com.terraformersmc.terraform.boat.api.item.TerraformBoatItemHelper;
import net.alminoris.wildfields.WildFields;
import net.alminoris.wildfields.entity.ModEntities;
import net.alminoris.wildfields.item.custom.SaigaSickle;
import net.alminoris.wildfields.item.custom.SteppeArrowItem;
import net.alminoris.wildfields.item.custom.SteppeViperDagger;
import net.minecraft.block.Block;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class ModItems
{
    public static final Item MARMOT_SPAWN_EGG = registerItem("marmot_spawn_egg", new SpawnEggItem(
            ModEntities.MARMOT, 0x8b694c, 0X745b48, new Item.Settings()));

    public static final Item STEPPE_VIPER_SPAWN_EGG = registerItem("steppe_viper_spawn_egg", new SpawnEggItem(
            ModEntities.STEPPE_VIPER, 0xa19678, 0X645541, new Item.Settings()));

    public static final Item DARKLING_BEETLE_SPAWN_EGG = registerItem("darkling_beetle_spawn_egg", new SpawnEggItem(
            ModEntities.DARKLING_BEETLE, 0x1f212b, 0X0a0c0f, new Item.Settings()));

    public static final Item STEPPE_EAGLE_SPAWN_EGG = registerItem("steppe_eagle_spawn_egg", new SpawnEggItem(
            ModEntities.STEPPE_EAGLE, 0x694e3e, 0X9c7c5f, new Item.Settings()));

    public static final Item SAIGA_SPAWN_EGG = registerItem("saiga_spawn_egg", new SpawnEggItem(
            ModEntities.SAIGA, 0xf4b765, 0Xdba96f, new Item.Settings()));

    public static final Item SERVAL_SPAWN_EGG = registerItem("serval_spawn_egg", new SpawnEggItem(
            ModEntities.SERVAL, 0xd39c51, 0X55351d, new Item.Settings()));

    public static final Item OLIVES = registerItem("olives", new Item(new Item.Settings().maxCount(16).food(ModFoodComponents.OLIVES)));

    public static final Item SAIGA = registerItem("saiga",
            new Item(new Item.Settings().food(ModFoodComponents.SAIGA)));

    public static final Item COOKED_SAIGA = registerItem("cooked_saiga",
            new Item(new Item.Settings().food(ModFoodComponents.COOKED_SAIGA)));

    public static final Item STEPPE_VIPER_FANG = registerItem("steppe_viper_fang", new Item(new Item.Settings()));

    public static final Item STEPPE_EAGLE_BEAK = registerItem("steppe_eagle_beak", new Item(new Item.Settings()));

    public static final Item STEPPE_EAGLE_FEATHER = registerItem("steppe_eagle_feather", new Item(new Item.Settings()));

    public static final Item MARMOT_FUR = registerItem("marmot_fur", new Item(new Item.Settings()));

    public static final Item SAIGA_HORN = registerItem("saiga_horn", new Item(new Item.Settings()));

    public static final Item DARKLING_BEETLE_SHELL = registerItem("darkling_beetle_shell", new Item(new Item.Settings()));

    public static final Item STEPPE_ARROW = registerItem("steppe_arrow", new SteppeArrowItem(new Item.Settings()));

    public static final Item DARKLING_BEETLE_CHESTPLATE = registerItem("darkling_beetle_chestplate",
            new ArmorItem(
                    ModArmorMaterials.DARKLING_BEETLE_SHELL,
                    ArmorItem.Type.CHESTPLATE,
                    new Item.Settings().maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(25))));

    public static final Item FURRED_LEATHER_HELMET = registerItem("furred_leather_helmet",
            new ArmorItem(
                    ModArmorMaterials.FURRED_LEATHER,
                    ArmorItem.Type.HELMET,
                    new Item.Settings().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(10))));

    public static final Item FURRED_LEATHER_CHESTPLATE = registerItem("furred_leather_chestplate",
            new ArmorItem(
                    ModArmorMaterials.FURRED_LEATHER,
                    ArmorItem.Type.CHESTPLATE,
                    new Item.Settings().maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(10))));

    public static final Item FURRED_LEATHER_LEGGINGS = registerItem("furred_leather_leggings",
            new ArmorItem(
                    ModArmorMaterials.FURRED_LEATHER,
                    ArmorItem.Type.LEGGINGS,
                    new Item.Settings().maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(10))));

    public static final Item FURRED_LEATHER_BOOTS = registerItem("furred_leather_boots",
            new ArmorItem(
                    ModArmorMaterials.FURRED_LEATHER,
                    ArmorItem.Type.BOOTS,
                    new Item.Settings().maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(10))));

    public static final Item STEPPE_VIPER_DAGGER = registerItem("steppe_viper_dagger", new SteppeViperDagger(
            ToolMaterials.STONE, new Item.Settings().maxCount(1)
            .attributeModifiers(SteppeViperDagger.createAttributeModifiers(ToolMaterials.STONE, 2, -1f))));

    public static final Item SAIGA_SICKLE = registerItem("saiga_sickle", new SaigaSickle(
            ToolMaterials.STONE, new Item.Settings().maxCount(1)));

    private static Item registerItem(String name, Item item)
    {
        return Registry.register(Registries.ITEM, Identifier.of(WildFields.MOD_ID, name), item);
    }

    public static void registerModItems()
    {

    }

    public static Item registerBerryItem(String name, int nutrition, float saturation, Block block)
    {
        return registerItem(name, new AliasedBlockItem(block, new Item.Settings().maxCount(16).food(ModFoodComponents.registerFood(nutrition, saturation))));
    }

    public static Item registerBoatItem(Identifier boatID,  RegistryKey<TerraformBoatType> boatKey)
    {
        return TerraformBoatItemHelper.registerBoatItem(boatID, boatKey, false);
    }

    public static Item registerChestBoatItem(Identifier chestBoatID,  RegistryKey<TerraformBoatType> boatKey)
    {
        return TerraformBoatItemHelper.registerBoatItem(chestBoatID, boatKey, true);
    }
}
