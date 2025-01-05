package net.alminoris.wildfields.world.gen.feature;

import net.alminoris.wildfields.WildFields;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.HugeMushroomFeatureConfig;

public abstract class ModFeatures<FC extends FeatureConfig>
{
    //public static final Feature<HugeMushroomFeatureConfig> HUGE_WHITE_MUSHROOM = new HugeWhiteMushroomFeature(HugeMushroomFeatureConfig.CODEC);

    public static void registerFeatures()
    {
       // Registry.register(Registries.FEATURE, Identifier.of(WildFields.MOD_ID, "huge_white_mushroom"), HUGE_WHITE_MUSHROOM);
    }
}
