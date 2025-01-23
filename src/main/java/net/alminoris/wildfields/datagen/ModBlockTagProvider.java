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

        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE)
                .add(ModBlocks.FEATHER_GRASS)
                .add(ModBlocks.TINY_GRASS);
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.SALTMARSH_BLOCK)
                .add(ModBlocks.SALTMARSH_POLISHED)
                .add(ModBlocks.SALTMARSH_CHISELED)
                .add(ModBlocks.SALTMARSH_BRICKS)
                .add(ModBlocks.SALTMARSH_BRICKS_STAIRS)
                .add(ModBlocks.SALTMARSH_BRICKS_SLAB)
                .add(ModBlocks.DOLOMITE_BLOCK)
                .add(ModBlocks.DOLOMITE_POLISHED)
                .add(ModBlocks.DOLOMITE_CHISELED)
                .add(ModBlocks.DOLOMITE_BRICKS)
                .add(ModBlocks.DOLOMITE_BRICKS_STAIRS)
                .add(ModBlocks.DOLOMITE_BRICKS_SLAB);

        getOrCreateTagBuilder(BlockTags.WALLS)
                .add(ModBlocks.SALTMARSH_WALL)
                .add(ModBlocks.SALTMARSH_COBBLED_WALL)
                .add(ModBlocks.SALTMARSH_BRICKS_WALL)
                .add(ModBlocks.DOLOMITE_WALL)
                .add(ModBlocks.DOLOMITE_COBBLED_WALL)
                .add(ModBlocks.DOLOMITE_BRICKS_WALL);

        getOrCreateTagBuilder(BlockTags.SLABS)
                .add(ModBlocks.SALTMARSH_SLAB)
                .add(ModBlocks.DOLOMITE_SLAB)
                .add(ModBlocks.SALTMARSH_COBBLED_SLAB)
                .add(ModBlocks.DOLOMITE_COBBLED_SLAB)
                .add(ModBlocks.SALTMARSH_BRICKS_SLAB)
                .add(ModBlocks.DOLOMITE_BRICKS_SLAB)
                .add(ModBlocks.SALTMARSH_POLISHED_SLAB)
                .add(ModBlocks.DOLOMITE_POLISHED_SLAB);

        getOrCreateTagBuilder(BlockTags.STAIRS)
                .add(ModBlocks.SALTMARSH_STAIRS)
                .add(ModBlocks.DOLOMITE_STAIRS)
                .add(ModBlocks.SALTMARSH_COBBLED_STAIRS)
                .add(ModBlocks.DOLOMITE_COBBLED_STAIRS)
                .add(ModBlocks.SALTMARSH_BRICKS_STAIRS)
                .add(ModBlocks.DOLOMITE_BRICKS_STAIRS)
                .add(ModBlocks.SALTMARSH_POLISHED_STAIRS)
                .add(ModBlocks.DOLOMITE_POLISHED_STAIRS);

        getOrCreateTagBuilder(BlockTags.FLOWERS)
                .add(ModBlocks.THYME);

        getOrCreateTagBuilder(BlockTags.FLOWER_POTS)
                .add(ModBlocks.POTTED_THYME);

        getOrCreateTagBuilder(ModTags.Blocks.STEPPE_VIPER_PLANTS)
                .add(ModBlocks.FEATHER_GRASS)
                .add(ModBlocks.THYME)
                .add(Blocks.GRASS);

        getOrCreateTagBuilder(ModTags.Blocks.SAIGA_SPAWNABLE_ON)
                .add(Blocks.GRASS_BLOCK)
                .add(ModBlocks.SALTMARSH_BLOCK);
    }
}
