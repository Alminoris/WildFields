package net.alminoris.wildfields.datagen;

import net.alminoris.wildfields.block.ModBlocks;
import net.alminoris.wildfields.item.ModItems;
import net.alminoris.wildfields.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
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

        for (String name : WOOD_NAMES)
        {
            getOrCreateTagBuilder(ItemTags.SAPLINGS)
                    .add(WOODEN_SAPLINGS.get(name).asItem());

            getOrCreateTagBuilder(ItemTags.LOGS_THAT_BURN)
                    .add(LOGS.get(name).asItem())
                    .add(STRIPPED_LOGS.get(name).asItem())
                    .add(WOODS.get(name).asItem())
                    .add(STRIPPED_WOODS.get(name).asItem());

            getOrCreateTagBuilder(ItemTags.PLANKS)
                    .add(WOODEN_PLANKS.get(name).asItem());

            getOrCreateTagBuilder(ItemTags.WOODEN_FENCES)
                    .add(WOODEN_FENCES.get(name).asItem());

            getOrCreateTagBuilder(ItemTags.WOODEN_SLABS)
                    .add(WOODEN_SLABS.get(name).asItem());

            getOrCreateTagBuilder(ItemTags.WOODEN_STAIRS)
                    .add(WOODEN_STAIRS.get(name).asItem());

            getOrCreateTagBuilder(ItemTags.WOODEN_BUTTONS)
                    .add(WOODEN_BUTTONS.get(name).asItem());

            getOrCreateTagBuilder(ItemTags.WOODEN_PRESSURE_PLATES)
                    .add(WOODEN_PRESSURE_PLATES.get(name).asItem());

            getOrCreateTagBuilder(ItemTags.WOODEN_TRAPDOORS)
                    .add(WOODEN_TRAPDOORS.get(name).asItem());

            getOrCreateTagBuilder(ItemTags.WOODEN_DOORS)
                    .add(WOODEN_DOORS.get(name).asItem());
        }

        for (String name : STONE_NAMES)
        {
            getOrCreateTagBuilder(ItemTags.STONE_BRICKS)
                    .add(STONE_BLOCKS.get(name).get("bricks").asItem());
            for (String type : STONE_TYPES)
            {
                getOrCreateTagBuilder(ItemTags.SLABS)
                        .add(STONE_SLABS.get(name).get(type).asItem());

                getOrCreateTagBuilder(ItemTags.STAIRS)
                        .add(STONE_STAIRS.get(name).get(type).asItem());

                getOrCreateTagBuilder(ItemTags.WALLS)
                        .add(STONE_WALLS.get(name).get(type).asItem());
            }
        }

        getOrCreateTagBuilder(ModTags.Items.MARMOT_FOOD)
                .add(ModBlocks.TINY_GRASS.asItem(), Items.SHORT_GRASS, Items.SWEET_BERRIES, Items.WHEAT_SEEDS);

        getOrCreateTagBuilder(ModTags.Items.SAIGA_FOOD)
                .add(ModBlocks.TINY_GRASS.asItem(), Items.SHORT_GRASS, Items.WHEAT);

        getOrCreateTagBuilder(ModTags.Items.BISON_FOOD)
                .add(Items.TALL_GRASS, Items.SHORT_GRASS, Items.WHEAT, ModBlocks.BLUE_GRAMA_GRASS.asItem());

        getOrCreateTagBuilder(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.DARKLING_BEETLE_CHESTPLATE)
                .add(ModItems.FURRED_LEATHER_HELMET)
                .add(ModItems.FURRED_LEATHER_CHESTPLATE)
                .add(ModItems.FURRED_LEATHER_LEGGINGS)
                .add(ModItems.FURRED_LEATHER_BOOTS);

        getOrCreateTagBuilder(ItemTags.HEAD_ARMOR)
                .add(ModItems.FURRED_LEATHER_HELMET);

        getOrCreateTagBuilder(ItemTags.CHEST_ARMOR)
                .add(ModItems.FURRED_LEATHER_CHESTPLATE, ModItems.DARKLING_BEETLE_CHESTPLATE);

        getOrCreateTagBuilder(ItemTags.LEG_ARMOR)
                .add(ModItems.FURRED_LEATHER_LEGGINGS);

        getOrCreateTagBuilder(ItemTags.FOOT_ARMOR)
                .add(ModItems.FURRED_LEATHER_BOOTS);

        getOrCreateTagBuilder(ItemTags.ARROWS)
                .add(ModItems.STEPPE_ARROW);

        getOrCreateTagBuilder(ItemTags.DYEABLE)
                .add(ModItems.FURRED_LEATHER_HELMET, ModItems.FURRED_LEATHER_CHESTPLATE, ModItems.FURRED_LEATHER_LEGGINGS, ModItems.FURRED_LEATHER_BOOTS);

        getOrCreateTagBuilder(ItemTags.FREEZE_IMMUNE_WEARABLES)
                .add(ModItems.FURRED_LEATHER_HELMET, ModItems.FURRED_LEATHER_CHESTPLATE, ModItems.FURRED_LEATHER_LEGGINGS, ModItems.FURRED_LEATHER_BOOTS);

        getOrCreateTagBuilder(ItemTags.MEAT)
                .add(ModItems.SAIGA, ModItems.COOKED_SAIGA, ModItems.BISON, ModItems.COOKED_BISON, ModItems.JACKRABBIT, ModItems.COOKED_JACKRABBIT,
                        ModItems.PALLID_WINGED_GRASSHOPPER_LEG, ModItems.COOKED_PALLID_WINGED_GRASSHOPPER_LEG);
    }
}
