package net.alminoris.wildfields.datagen;

import net.alminoris.wildfields.block.ModBlocks;
import net.alminoris.wildfields.item.ModItems;
import net.alminoris.wildfields.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

import static net.alminoris.wildfields.util.helper.ModBlockSetsHelper.*;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider
{
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture)
    {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup)
    {
        for (String name : WOOD_NAMES)
        {
            getOrCreateTagBuilder(ItemTags.HANGING_SIGNS)
                    .add(WOODEN_HANGING_SIGN_ITEMS.get(name));

            getOrCreateTagBuilder(ItemTags.SIGNS)
                    .add(WOODEN_SIGN_ITEMS.get(name));
        }

        getOrCreateTagBuilder(ModTags.Items.MARMOT_FOOD)
                .add(ModBlocks.TINY_GRASS.asItem(), Items.GRASS, Items.SWEET_BERRIES, Items.WHEAT_SEEDS);

        getOrCreateTagBuilder(ModTags.Items.SAIGA_FOOD)
                .add(ModBlocks.TINY_GRASS.asItem(), Items.GRASS, Items.WHEAT);

        getOrCreateTagBuilder(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.DARKLING_BEETLE_CHESTPLATE)
                .add(ModItems.FURRED_LEATHER_HELMET)
                .add(ModItems.FURRED_LEATHER_CHESTPLATE)
                .add(ModItems.FURRED_LEATHER_LEGGINGS)
                .add(ModItems.FURRED_LEATHER_BOOTS);

        getOrCreateTagBuilder(ItemTags.ARROWS)
                .add(ModItems.STEPPE_ARROW);

        getOrCreateTagBuilder(ItemTags.FREEZE_IMMUNE_WEARABLES)
                .add(ModItems.FURRED_LEATHER_HELMET, ModItems.FURRED_LEATHER_CHESTPLATE, ModItems.FURRED_LEATHER_LEGGINGS, ModItems.FURRED_LEATHER_BOOTS);
    }
}
