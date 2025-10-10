package net.alminoris.wildfields.datagen;

import net.alminoris.wildfields.block.ModBlocks;
import net.alminoris.wildfields.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

import static net.alminoris.wildfields.util.helper.ModBlockSetsHelper.*;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider
{
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture)
    {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup)
    {
        for (String name : WOOD_NAMES)
        {
            getOrCreateTagBuilder(BlockTags.SAPLINGS)
                    .add(WOODEN_SAPLINGS.get(name));

            getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN)
                    .add(LOGS.get(name))
                    .add(STRIPPED_LOGS.get(name))
                    .add(WOODS.get(name))
                    .add(STRIPPED_WOODS.get(name));

            getOrCreateTagBuilder(BlockTags.PLANKS)
                    .add(WOODEN_PLANKS.get(name));

            getOrCreateTagBuilder(BlockTags.WOODEN_FENCES)
                    .add(WOODEN_FENCES.get(name));

            getOrCreateTagBuilder(BlockTags.WOODEN_SLABS)
                    .add(WOODEN_SLABS.get(name));

            getOrCreateTagBuilder(BlockTags.WOODEN_STAIRS)
                    .add(WOODEN_STAIRS.get(name));

            getOrCreateTagBuilder(BlockTags.WOODEN_BUTTONS)
                    .add(WOODEN_BUTTONS.get(name));

            getOrCreateTagBuilder(BlockTags.WOODEN_PRESSURE_PLATES)
                    .add(WOODEN_PRESSURE_PLATES.get(name));

            getOrCreateTagBuilder(BlockTags.WOODEN_TRAPDOORS)
                    .add(WOODEN_TRAPDOORS.get(name));

            getOrCreateTagBuilder(BlockTags.WOODEN_DOORS)
                    .add(WOODEN_DOORS.get(name));

            getOrCreateTagBuilder(BlockTags.STANDING_SIGNS)
                    .add(WOODEN_SIGNS.get(name));

            getOrCreateTagBuilder(BlockTags.WALL_SIGNS)
                    .add(WOODEN_WALL_SIGNS.get(name));

            getOrCreateTagBuilder(BlockTags.CEILING_HANGING_SIGNS)
                    .add(WOODEN_HANGING_SIGNS.get(name));

            getOrCreateTagBuilder(BlockTags.WALL_HANGING_SIGNS)
                    .add(WOODEN_WALL_HANGING_SIGNS.get(name));
        }

        /*
        getOrCreateTagBuilder(BlockTags.TALL_FLOWERS);
        getOrCreateTagBuilder(BlockTags.FROG_PREFER_JUMP_TO);
        getOrCreateTagBuilder(BlockTags.CLIMBABLE);*/

        for (String name : CROP_NAMES)
        {
            getOrCreateTagBuilder(BlockTags.CROPS)
                    .add(CROPS.get(name));

            getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                    .add(CROPS.get(name));
        }

        for (String name : WILD_CROP_NAMES)
        {
            getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                    .add(WILD_CROPS.get(name));
        }

        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE)
                .add(ModBlocks.FEATHER_GRASS)
                .add(ModBlocks.TINY_GRASS)
                .add(ModBlocks.PRAIRIE_SAGE)
                .add(ModBlocks.BLUE_GRAMA_GRASS);

        for (String name : STONE_NAMES)
        {
            for (String type : STONE_TYPES)
            {
                getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                        .add(STONE_BLOCKS.get(name).get(type))
                        .add(STONE_SLABS.get(name).get(type))
                        .add(STONE_STAIRS.get(name).get(type))
                        .add(STONE_WALLS.get(name).get(type));

                getOrCreateTagBuilder(BlockTags.SLABS)
                        .add(STONE_SLABS.get(name).get(type));

                getOrCreateTagBuilder(BlockTags.STAIRS)
                        .add(STONE_STAIRS.get(name).get(type));

                getOrCreateTagBuilder(BlockTags.WALLS)
                        .add(STONE_WALLS.get(name).get(type));
            }
        }

        getOrCreateTagBuilder(BlockTags.FLOWERS)
                .add(ModBlocks.THYME)
                .add(ModBlocks.WORMWOOD)
                .add(ModBlocks.SPIDER_MILKWEED)
                .add(ModBlocks.VIOLA)
                .add(ModBlocks.PRAIRIE_ROSE)
                .add(ModBlocks.SMOOTH_ASTER);

        getOrCreateTagBuilder(BlockTags.FLOWER_POTS)
                .add(ModBlocks.POTTED_THYME)
                .add(ModBlocks.POTTED_SPIDER_MILKWEED)
                .add(ModBlocks.POTTED_WORMWOOD)
                .add(ModBlocks.POTTED_PRAIRIE_ROSE)
                .add(ModBlocks.POTTED_SMOOTH_ASTER);

        getOrCreateTagBuilder(ModTags.Blocks.STEPPE_VIPER_PLANTS)
                .add(ModBlocks.FEATHER_GRASS)
                .add(ModBlocks.THYME)
                .add(Blocks.GRASS);

        getOrCreateTagBuilder(ModTags.Blocks.SAIGA_SPAWNABLE_ON)
                .add(Blocks.GRASS_BLOCK)
                .add(STONE_BLOCKS.get("saltmarsh").get("block"));

        getOrCreateTagBuilder(ModTags.Blocks.BISON_SPAWNABLE_ON)
                .add(Blocks.GRASS_BLOCK)
                .add(Blocks.SAND);
    }
}