package net.alminoris.wildfields.world.tree;

import net.alminoris.wildfields.util.helper.ModBlockSetsHelper;
import net.alminoris.wildfields.world.ModConfiguredFeatures;
import net.minecraft.block.SaplingGenerator;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Optional;

public class ModSaplingGenerators
{
    public static final Dictionary<String, RegistryKey<ConfiguredFeature<?, ?>>> keys = new Hashtable<>()
    {{
        put("olive", ModConfiguredFeatures.OLIVE_KEY);
        put("tamarisk", ModConfiguredFeatures.TAMARISK_KEY);
        put("western_serviceberry", ModConfiguredFeatures.WESTERN_SERVICEBERRY_KEY);
        put("trembling_aspen", ModConfiguredFeatures.TREMBLING_ASPEN_KEY);
        put("cottonwood", ModConfiguredFeatures.COTTONWOOD_KEY);
    }};

    public static final Dictionary<String, SaplingGenerator> saplingGenerators = new Hashtable<>()
    {{
        for(String name : ModBlockSetsHelper.WOOD_NAMES)
        {
            put(name, new SaplingGenerator(name, 0f, Optional.empty(),
                    Optional.empty(),
                    Optional.of(keys.get(name)),
                    Optional.empty(),
                    Optional.empty(),
                    Optional.empty()));
        }
    }};
}
