package net.alminoris.wildfields.world.gen;

import net.alminoris.wildfields.world.ModPlacedFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;

public class ModOreGeneration {
    public static void generateOres()
    {
        //BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.MARL_PLACED_KEY);

        // Example for individual Bioms
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.PLAINS),
                GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.MARL_PLACED_KEY);

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.PLAINS),
                GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.LOESSIC_MARL_PLACED_KEY);
    }
}
