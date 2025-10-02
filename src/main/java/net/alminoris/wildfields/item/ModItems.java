package net.alminoris.wildfields.item;

import com.terraformersmc.terraform.boat.api.TerraformBoatType;
import com.terraformersmc.terraform.boat.api.item.TerraformBoatItemHelper;
import net.alminoris.wildfields.WildFields;
import net.alminoris.wildfields.block.ModBlocks;
import net.alminoris.wildfields.entity.ModEntities;
import net.alminoris.wildfields.item.custom.EarthwormItem;
import net.alminoris.wildfields.item.custom.SaigaSickle;
import net.alminoris.wildfields.item.custom.SteppeArrowItem;
import net.alminoris.wildfields.item.custom.SteppeViperDagger;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.component.type.FoodComponents;
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

    public static final Item MOLE_SPAWN_EGG = registerItem("mole_spawn_egg", new SpawnEggItem(
            ModEntities.MOLE, 0x131112, 0Xa28081, new Item.Settings()));

    public static final Item COYOTE_SPAWN_EGG = registerItem("coyote_spawn_egg", new SpawnEggItem(
            ModEntities.COYOTE, 0xa69f95, 0Xb9a583, new Item.Settings()));

    public static final Item BISON_SPAWN_EGG = registerItem("bison_spawn_egg", new SpawnEggItem(
            ModEntities.BISON, 0x383028, 0X261f1f, new Item.Settings()));

    public static final Item FERRUGINOUS_HAWK_SPAWN_EGG = registerItem("ferruginous_hawk_spawn_egg", new SpawnEggItem(
            ModEntities.FERRUGINOUS_HAWK, 0x8c5b4d, 0Xdfdce5, new Item.Settings()));

    public static final Item WHITE_TAILED_JACKRABBIT_SPAWN_EGG = registerItem("white_tailed_jackrabbit_spawn_egg", new SpawnEggItem(
            ModEntities.WHITE_TAILED_JACKRABBIT, 0xb3b1b0, 0Xf6f6f4, new Item.Settings()));

    public static final Item PALLID_WINGED_GRASSHOPPER_SPAWN_EGG = registerItem("pallid_winged_grasshopper_spawn_egg", new SpawnEggItem(
            ModEntities.PALLID_WINGED_GRASSHOPPER, 0x797163, 0X5b5850, new Item.Settings()));

    public static final Item BLACK_BILLED_MAGPIE_SPAWN_EGG = registerItem("black_billed_magpie_spawn_egg", new SpawnEggItem(
            ModEntities.BLACK_BILLED_MAGPIE, 0x16151a, 0Xfaf7fc, new Item.Settings()));

    public static final Item WESTERN_MEADOWLARK_SPAWN_EGG = registerItem("western_meadowlark_spawn_egg", new SpawnEggItem(
            ModEntities.WESTERN_MEADOWLARK, 0x8d7e5e, 0Xecbf04, new Item.Settings()));

    public static final Item EARTHWORM = registerItem("earthworm", new EarthwormItem(new Item.Settings()));

    public static final Item PRAIRIES_TALISMAN = registerItem("prairies_talisman", new Item(new Item.Settings().maxDamage(500)));

    public static final Item OLIVES = registerItem("olives", new Item(new Item.Settings().maxCount(16).food(ModFoodComponents.OLIVES)));

    public static final Item SAIGA = registerItem("saiga",
            new Item(new Item.Settings().food(ModFoodComponents.SAIGA)));

    public static final Item COOKED_SAIGA = registerItem("cooked_saiga",
            new Item(new Item.Settings().food(ModFoodComponents.COOKED_SAIGA)));

    public static final Item BISON = registerItem("bison",
            new Item(new Item.Settings().food(ModFoodComponents.BISON)));

    public static final Item COOKED_BISON = registerItem("cooked_bison",
            new Item(new Item.Settings().food(ModFoodComponents.COOKED_BISON)));

    public static final Item JACKRABBIT = registerItem("jackrabbit",
            new Item(new Item.Settings().food(ModFoodComponents.JACKRABBIT)));

    public static final Item COOKED_JACKRABBIT = registerItem("cooked_jackrabbit",
            new Item(new Item.Settings().food(ModFoodComponents.COOKED_JACKRABBIT)));

    public static final Item PALLID_WINGED_GRASSHOPPER_LEG = registerItem("pallid_winged_grasshopper_leg",
            new Item(new Item.Settings().food(ModFoodComponents.PALLID_WINGED_GRASSHOPPER_LEG)));

    public static final Item COOKED_PALLID_WINGED_GRASSHOPPER_LEG = registerItem("cooked_pallid_winged_grasshopper_leg",
            new Item(new Item.Settings().food(ModFoodComponents.COOKED_PALLID_WINGED_GRASSHOPPER_LEG)));

    public static final Item BARLEY_BREAD = registerItem("barley_bread", new Item(new Item.Settings().food(ModFoodComponents.BARLEY_BREAD)));

    public static final Item BARLEY_STEW = registerItem("barley_stew", new Item(new Item.Settings().maxCount(1).food(ModFoodComponents.BARLEY_STEW)));

    public static final Item OAT_COOKIE = registerItem("oat_cookie", new Item(new Item.Settings().food(ModFoodComponents.OAT_COOKIE)));

    public static final Item OATMEAL = registerItem("oatmeal", new Item(new Item.Settings().maxCount(1).food(ModFoodComponents.OATMEAL)));

    public static final Item STEPPE_VIPER_FANG = registerItem("steppe_viper_fang", new Item(new Item.Settings()));

    public static final Item STEPPE_EAGLE_BEAK = registerItem("steppe_eagle_beak", new Item(new Item.Settings()));

    public static final Item STEPPE_EAGLE_FEATHER = registerItem("steppe_eagle_feather", new Item(new Item.Settings()));

    public static final Item FERRUGINOUS_HAWK_FEATHER = registerItem("ferruginous_hawk_feather", new Item(new Item.Settings()));

    public static final Item BLACK_BILLED_MAGPIE_FEATHER = registerItem("black_billed_magpie_feather", new Item(new Item.Settings()));

    public static final Item WESTERN_MEADOWLARK_FEATHER = registerItem("western_meadowlark_feather", new Item(new Item.Settings()));

    public static final Item MARMOT_FUR = registerItem("marmot_fur", new Item(new Item.Settings()));

    public static final Item SAIGA_HORN = registerItem("saiga_horn", new Item(new Item.Settings()));

    public static final Item BISON_HORN = registerItem("bison_horn", new Item(new Item.Settings()));

    public static final Item DARKLING_BEETLE_SHELL = registerItem("darkling_beetle_shell", new Item(new Item.Settings()));

    public static final Item STEPPE_ARROW = registerItem("steppe_arrow", new SteppeArrowItem(new Item.Settings()));

    public static final Item OAT_SEEDS = registerItem("oat_seeds", new AliasedBlockItem(ModBlocks.OAT, new Item.Settings()));
    public static final Item OAT = registerItem("oat_item", new Item(new Item.Settings()));

    public static final Item BARLEY_SEEDS = registerItem("barley_seeds", new AliasedBlockItem(ModBlocks.BARLEY, new Item.Settings()));
    public static final Item BARLEY = registerItem("barley_item", new Item(new Item.Settings()));

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

    public static final Item PRICKLY_PEAR = registerItem("prickly_pear",
            new AliasedBlockItem(ModBlocks.PRICKLY_PEAR_CACTUS, new Item.Settings().maxCount(16).food(ModFoodComponents.registerFood(3, 5f))));

    private static Item registerItem(String name, Item item)
    {
        return Registry.register(Registries.ITEM, Identifier.of(WildFields.MOD_ID, name), item);
    }

    public static void registerModItems()
    {

    }

    public static Item registerBerryItem(String name, int nutrition, float saturation, Block block)
    {
        return registerItem(name+"_item", new AliasedBlockItem(block, new Item.Settings().maxCount(16).food(ModFoodComponents.registerFood(nutrition, saturation))));
    }

    public static Item registerBoatItem(Identifier boatID,  RegistryKey<TerraformBoatType> boatKey)
    {
        return TerraformBoatItemHelper.registerBoatItem(boatID, boatKey, false);
    }

    public static Item registerChestBoatItem(Identifier chestBoatID,  RegistryKey<TerraformBoatType> boatKey)
    {
        return TerraformBoatItemHelper.registerBoatItem(chestBoatID, boatKey, true);
    }

    public static Item registerSignItem(String name, Block signBlock, Block wallSignBlock)
    {
        return registerItem(name+"_sign_item", new SignItem(new Item.Settings().maxCount(16), signBlock, wallSignBlock));
    }

    public static Item registerHangingSignItem(String name, Block hangingSignBlock, Block wallHangingSignBlock)
    {
        return registerItem(name+"_hanging_sign_item", new HangingSignItem(hangingSignBlock, wallHangingSignBlock, new Item.Settings().maxCount(16)));
    }
}