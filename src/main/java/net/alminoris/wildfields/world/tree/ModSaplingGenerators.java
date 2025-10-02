package net.alminoris.wildfields.world.tree;

import net.alminoris.wildfields.util.helper.ModBlockSetsHelper;
import net.alminoris.wildfields.world.ModConfiguredFeatures;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import java.util.Dictionary;
import java.util.Hashtable;

public class ModSaplingGenerators
{
    public static final Dictionary<String, RegistryKey<ConfiguredFeature<?, ?>>> keys = new Hashtable<>()
    {{
        put("olive", ModConfiguredFeatures.OLIVE_KEY);
        put("tamarisk", ModConfiguredFeatures.TAMARISK_KEY);
        put("trembling_aspen", ModConfiguredFeatures.TREMBLING_ASPEN_KEY);
        put("cottonwood", ModConfiguredFeatures.COTTONWOOD_KEY);
    }};

    public static final Dictionary<String, net.minecraft.block.sapling.SaplingGenerator> saplingGenerators = new Hashtable<>()
    {{
        for(String name : ModBlockSetsHelper.WOOD_NAMES)
        {
            if (name.equals("cottonwood"))
                put(name, new CustomLargeTreeSaplingGenerator(keys.get(name)));
            else
                put(name, new CustomSaplingGenerator(keys.get(name)));
        }
    }};
}