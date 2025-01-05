package net.alminoris.wildfields.item;

import net.alminoris.wildfields.WildFields;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public class ModArmorMaterials
{
    public static final RegistryEntry<ArmorMaterial> DARKLING_BEETLE_SHELL = registerArmorMaterial("darkling_beetle_shell",
            () -> new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), map ->
            {
                map.put(ArmorItem.Type.BOOTS, 4);
                map.put(ArmorItem.Type.LEGGINGS, 8);
                map.put(ArmorItem.Type.CHESTPLATE, 12);
                map.put(ArmorItem.Type.HELMET, 4);
                map.put(ArmorItem.Type.BODY, 8);
            }), 10, SoundEvents.ITEM_ARMOR_EQUIP_TURTLE, () -> Ingredient.ofItems(ModItems.DARKLING_BEETLE_SHELL),
                    List.of(new ArmorMaterial.Layer(Identifier.of(WildFields.MOD_ID, "darkling_beetle_shell"))), 2.0f,0.5f));

    public static final RegistryEntry<ArmorMaterial> FURRED_LEATHER = registerArmorMaterial(
            "furred_leather",
            () -> new ArmorMaterial(
                    Util.make(new EnumMap<>(ArmorItem.Type.class), map ->
                    {
                        map.put(ArmorItem.Type.BOOTS, 1);
                        map.put(ArmorItem.Type.LEGGINGS, 3);
                        map.put(ArmorItem.Type.CHESTPLATE, 4);
                        map.put(ArmorItem.Type.HELMET, 2);
                        map.put(ArmorItem.Type.BODY, 4);
                    }),
                    20,
                    SoundEvents.ITEM_ARMOR_EQUIP_LEATHER,
                    () -> Ingredient.ofItems(ModItems.MARMOT_FUR),
                    List.of(new ArmorMaterial.Layer(Identifier.of(WildFields.MOD_ID,"furred_leather"), "", true),
                            new ArmorMaterial.Layer(Identifier.of(WildFields.MOD_ID,"furred_leather"), "_overlay", false)),
                    0.0F,
                    0.0F)

    );


    public static RegistryEntry<ArmorMaterial> registerArmorMaterial(String name, Supplier<ArmorMaterial> material)
    {
        return Registry.registerReference(Registries.ARMOR_MATERIAL, Identifier.of(WildFields.MOD_ID, name), material.get());
    }
}
