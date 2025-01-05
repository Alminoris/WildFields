package net.alminoris.wildfields.entity;

import com.terraformersmc.terraform.boat.api.TerraformBoatType;
import com.terraformersmc.terraform.boat.api.TerraformBoatTypeRegistry;
import net.alminoris.wildfields.WildFields;
import net.alminoris.wildfields.util.helper.ModBlockSetsHelper;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

import java.util.Dictionary;
import java.util.Hashtable;


public class ModBoats
{
    public static Dictionary<String, Identifier> boatIDs = new Hashtable<>()
    {{
        for(String name : ModBlockSetsHelper.WOOD_NAMES)
            put(name, Identifier.of(WildFields.MOD_ID, name+"_boat"));
    }};

    public static Dictionary<String, Identifier> chestBoatIDs = new Hashtable<>()
    {{
        for(String name : ModBlockSetsHelper.WOOD_NAMES)
            put(name, Identifier.of(WildFields.MOD_ID, name+"_chest_boat"));
    }};

    public static Dictionary<String, RegistryKey<TerraformBoatType>> boatKeys = new Hashtable<>()
    {{
        for(String name : ModBlockSetsHelper.WOOD_NAMES)
            put(name, TerraformBoatTypeRegistry.createKey(boatIDs.get(name)));
    }};

    public static void registerBoats()
    {
        for (String name : ModBlockSetsHelper.WOOD_NAMES)
        {
            TerraformBoatType hazelnutBoat = new TerraformBoatType.Builder()
                    .item(ModBlockSetsHelper.WOODEN_BOATS.get(name))
                    .chestItem(ModBlockSetsHelper.WOODEN_CHEST_BOATS.get(name))
                    .planks(ModBlockSetsHelper.WOODEN_PLANKS.get(name).asItem())
                    .build();

            Registry.register(TerraformBoatTypeRegistry.INSTANCE, boatKeys.get(name), hazelnutBoat);
        }
    }
}
