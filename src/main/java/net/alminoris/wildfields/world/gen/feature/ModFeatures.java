package net.alminoris.wildfields.world.gen.feature;

import net.alminoris.wildfields.WildFields;
import net.alminoris.wildfields.world.gen.feature.custom.TripleTallPlantConfig;
import net.alminoris.wildfields.world.gen.feature.custom.TripleTallPlantFeature;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.HugeMushroomFeatureConfig;

public abstract class ModFeatures<FC extends FeatureConfig>
{
    public static final Feature<TripleTallPlantConfig> TRIPLE_TALL_PLANT = new TripleTallPlantFeature(TripleTallPlantConfig.CODEC);

    public static void registerFeatures()
    {
       Registry.register(Registries.FEATURE, Identifier.of(WildFields.MOD_ID, "triple_tall_plant"), TRIPLE_TALL_PLANT);
    }
}
