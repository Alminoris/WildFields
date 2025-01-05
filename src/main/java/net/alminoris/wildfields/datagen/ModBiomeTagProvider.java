package net.alminoris.wildfields.datagen;

import net.alminoris.wildfields.util.ModTags;
import net.alminoris.wildfields.world.biome.ModBiomes;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.world.biome.Biome;

import java.util.concurrent.CompletableFuture;

public class ModBiomeTagProvider extends FabricTagProvider<Biome>
{
    public ModBiomeTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture)
    {
        super(output, RegistryKeys.BIOME, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup)
    {
        this.getOrCreateTagBuilder(ModTags.Biomes.IS_STEPPES).add(ModBiomes.STEPPES).setReplace(false);
        this.getOrCreateTagBuilder(BiomeTags.IS_OVERWORLD).add(ModBiomes.STEPPES).setReplace(false);

        /*this.getOrCreateTagBuilder(BiomeTags.IS_SAVANNA).add(ModBiomes.PRAIRIES).setReplace(false);
        this.getOrCreateTagBuilder(BiomeTags.IS_OVERWORLD).add(ModBiomes.PRAIRIES).setReplace(false);

        this.getOrCreateTagBuilder(BiomeTags.IS_SAVANNA).add(ModBiomes.PAMPASAS).setReplace(false);
        this.getOrCreateTagBuilder(BiomeTags.IS_OVERWORLD).add(ModBiomes.PAMPASAS).setReplace(false);*/
    }
}
