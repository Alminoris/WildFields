package net.alminoris.wildfields.datagen;

import net.alminoris.wildfields.block.ModBlocks;
import net.alminoris.wildfields.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.CookingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import static net.alminoris.wildfields.util.helper.ModBlockSetsHelper.*;

public class ModRecipeProvider extends FabricRecipeProvider
{
    public ModRecipeProvider(FabricDataOutput output)
    {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> recipeExporter)
    {
        for(String name : WOOD_NAMES)
        {
            offerShapelessRecipe(recipeExporter, WOODEN_PLANKS.get(name), LOGS.get(name), String.valueOf(RecipeCategory.MISC), 4);
            offerSlabRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, WOODEN_SLABS.get(name), WOODEN_PLANKS.get(name));
            offerBoatRecipe(recipeExporter, WOODEN_BOATS.get(name), WOODEN_PLANKS.get(name));
            offerChestBoatRecipe(recipeExporter, WOODEN_CHEST_BOATS.get(name), WOODEN_PLANKS.get(name));
            offerHangingSignRecipe(recipeExporter, WOODEN_HANGING_SIGN_ITEMS.get(name), WOODEN_PLANKS.get(name));
            offerPressurePlateRecipe(recipeExporter, WOODEN_PRESSURE_PLATES.get(name), WOODEN_PLANKS.get(name));
            offerSingleOutputShapelessRecipe(recipeExporter, WOODEN_BUTTONS.get(name), WOODEN_PLANKS.get(name), String.valueOf(RecipeCategory.MISC));

            ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, WOODS.get(name), 3)
                    .pattern("##")
                    .pattern("##")
                    .input('#', LOGS.get(name))
                    .criterion(hasItem(LOGS.get(name)), conditionsFromItem(LOGS.get(name)))
                    .offerTo(recipeExporter);

            ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, STRIPPED_WOODS.get(name), 3)
                    .pattern("##")
                    .pattern("##")
                    .input('#', STRIPPED_LOGS.get(name))
                    .criterion(hasItem(STRIPPED_LOGS.get(name)), conditionsFromItem(STRIPPED_LOGS.get(name)))
                    .offerTo(recipeExporter);

            ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, WOODEN_STAIRS.get(name), 4)
                    .pattern("#  ")
                    .pattern("## ")
                    .pattern("###")
                    .input('#', WOODEN_PLANKS.get(name))
                    .criterion(hasItem(WOODEN_PLANKS.get(name)), conditionsFromItem(WOODEN_PLANKS.get(name)))
                    .offerTo(recipeExporter);

            ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, WOODEN_FENCES.get(name), 3)
                    .pattern("#/#")
                    .pattern("#/#")
                    .input('#', WOODEN_PLANKS.get(name))
                    .input('/', Items.STICK)
                    .criterion(hasItem(WOODEN_PLANKS.get(name)), conditionsFromItem(WOODEN_PLANKS.get(name)))
                    .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                    .offerTo(recipeExporter);

            ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, WOODEN_FENCE_GATES.get(name))
                    .pattern("/#/")
                    .pattern("/#/")
                    .input('#', WOODEN_PLANKS.get(name))
                    .input('/', Items.STICK)
                    .criterion(hasItem(WOODEN_PLANKS.get(name)), conditionsFromItem(WOODEN_PLANKS.get(name)))
                    .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                    .offerTo(recipeExporter);

            ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, WOODEN_SIGN_ITEMS.get(name), 3)
                    .pattern("###")
                    .pattern("###")
                    .pattern(" / ")
                    .input('#', WOODEN_PLANKS.get(name))
                    .input('/', Items.STICK)
                    .criterion(hasItem(WOODEN_PLANKS.get(name)), conditionsFromItem(WOODEN_PLANKS.get(name)))
                    .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                    .offerTo(recipeExporter);

            ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, WOODEN_DOORS.get(name), 3)
                    .pattern("##")
                    .pattern("##")
                    .pattern("##")
                    .input('#', WOODEN_PLANKS.get(name))
                    .criterion(hasItem(WOODEN_PLANKS.get(name)), conditionsFromItem(WOODEN_PLANKS.get(name)))
                    .offerTo(recipeExporter);

            ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, WOODEN_TRAPDOORS.get(name), 2)
                    .pattern("###")
                    .pattern("###")
                    .input('#', WOODEN_PLANKS.get(name))
                    .criterion(hasItem(WOODEN_PLANKS.get(name)), conditionsFromItem(WOODEN_PLANKS.get(name)))
                    .offerTo(recipeExporter);
        }

        offerSingleOutputShapelessRecipe(recipeExporter, Items.PINK_DYE, ModBlocks.PRAIRIE_ROSE, String.valueOf(RecipeCategory.MISC));
        offerSingleOutputShapelessRecipe(recipeExporter, Items.MAGENTA_DYE, ModBlocks.SMOOTH_ASTER, String.valueOf(RecipeCategory.MISC));
        offerSingleOutputShapelessRecipe(recipeExporter, Items.MAGENTA_DYE, ModBlocks.THYME, String.valueOf(RecipeCategory.MISC));
        offerSingleOutputShapelessRecipe(recipeExporter, Items.YELLOW_DYE, ModBlocks.WORMWOOD, String.valueOf(RecipeCategory.MISC));
        offerSingleOutputShapelessRecipe(recipeExporter, Items.WHITE_DYE, ModBlocks.SPIDER_MILKWEED, String.valueOf(RecipeCategory.MISC));
        offerSingleOutputShapelessRecipe(recipeExporter, Items.PURPLE_DYE, ModBlocks.VIOLA, String.valueOf(RecipeCategory.MISC));
        offerSingleOutputShapelessRecipe(recipeExporter, Items.LIGHT_GRAY_DYE, ModBlocks.FEATHER_GRASS, String.valueOf(RecipeCategory.MISC));

        offerSlabRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LIMESTONE_SLAB, ModBlocks.LIMESTONE_BLOCK);
        offerSlabRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LIMESTONE_COBBLED_SLAB, ModBlocks.LIMESTONE_COBBLED);
        offerSlabRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LIMESTONE_BRICKS_SLAB, ModBlocks.LIMESTONE_BRICKS);
        offerSlabRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LIMESTONE_POLISHED_SLAB, ModBlocks.LIMESTONE_POLISHED);
        offerWallRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LIMESTONE_WALL, ModBlocks.LIMESTONE_BLOCK);
        offerWallRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LIMESTONE_COBBLED_WALL, ModBlocks.LIMESTONE_COBBLED);
        offerWallRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LIMESTONE_BRICKS_WALL, ModBlocks.LIMESTONE_BRICKS);

        offerSlabRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SALTMARSH_SLAB, ModBlocks.SALTMARSH_BLOCK);
        offerSlabRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SALTMARSH_COBBLED_SLAB, ModBlocks.SALTMARSH_COBBLED);
        offerSlabRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SALTMARSH_BRICKS_SLAB, ModBlocks.SALTMARSH_BRICKS);
        offerSlabRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SALTMARSH_POLISHED_SLAB, ModBlocks.SALTMARSH_POLISHED);
        offerWallRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SALTMARSH_WALL, ModBlocks.SALTMARSH_BLOCK);
        offerWallRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SALTMARSH_COBBLED_WALL, ModBlocks.SALTMARSH_COBBLED);
        offerWallRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SALTMARSH_BRICKS_WALL, ModBlocks.SALTMARSH_BRICKS);

        offerSlabRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.DOLOMITE_SLAB, ModBlocks.DOLOMITE_BLOCK);
        offerSlabRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.DOLOMITE_COBBLED_SLAB, ModBlocks.DOLOMITE_COBBLED);
        offerSlabRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.DOLOMITE_BRICKS_SLAB, ModBlocks.DOLOMITE_BRICKS);
        offerSlabRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.DOLOMITE_POLISHED_SLAB, ModBlocks.DOLOMITE_POLISHED);
        offerWallRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.DOLOMITE_WALL, ModBlocks.DOLOMITE_BLOCK);
        offerWallRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.DOLOMITE_COBBLED_WALL, ModBlocks.DOLOMITE_COBBLED);
        offerWallRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.DOLOMITE_BRICKS_WALL, ModBlocks.DOLOMITE_BRICKS);

        offerSlabRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LOESSIC_MARL_SLAB, ModBlocks.LOESSIC_MARL_BLOCK);
        offerSlabRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LOESSIC_MARL_COBBLED_SLAB, ModBlocks.LOESSIC_MARL_COBBLED);
        offerSlabRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LOESSIC_MARL_BRICKS_SLAB, ModBlocks.LOESSIC_MARL_BRICKS);
        offerSlabRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LOESSIC_MARL_POLISHED_SLAB, ModBlocks.LOESSIC_MARL_POLISHED);
        offerWallRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LOESSIC_MARL_WALL, ModBlocks.LOESSIC_MARL_BLOCK);
        offerWallRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LOESSIC_MARL_COBBLED_WALL, ModBlocks.LOESSIC_MARL_COBBLED);
        offerWallRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LOESSIC_MARL_BRICKS_WALL, ModBlocks.LOESSIC_MARL_BRICKS);

        offerSlabRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LOAMY_MARL_SLAB, ModBlocks.LOAMY_MARL_BLOCK);
        offerSlabRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LOAMY_MARL_COBBLED_SLAB, ModBlocks.LOAMY_MARL_COBBLED);
        offerSlabRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LOAMY_MARL_BRICKS_SLAB, ModBlocks.LOAMY_MARL_BRICKS);
        offerSlabRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LOAMY_MARL_POLISHED_SLAB, ModBlocks.LOAMY_MARL_POLISHED);
        offerWallRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LOAMY_MARL_WALL, ModBlocks.LOAMY_MARL_BLOCK);
        offerWallRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LOAMY_MARL_COBBLED_WALL, ModBlocks.LOAMY_MARL_COBBLED);
        offerWallRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LOAMY_MARL_BRICKS_WALL, ModBlocks.LOAMY_MARL_BRICKS);

        offerSlabRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.FOSSIL_MARLSTONE_SLAB, ModBlocks.FOSSIL_MARLSTONE_BLOCK);
        offerSlabRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.FOSSIL_MARLSTONE_COBBLED_SLAB, ModBlocks.FOSSIL_MARLSTONE_COBBLED);
        offerSlabRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.FOSSIL_MARLSTONE_BRICKS_SLAB, ModBlocks.FOSSIL_MARLSTONE_BRICKS);
        offerSlabRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.FOSSIL_MARLSTONE_POLISHED_SLAB, ModBlocks.FOSSIL_MARLSTONE_POLISHED);
        offerWallRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.FOSSIL_MARLSTONE_WALL, ModBlocks.FOSSIL_MARLSTONE_BLOCK);
        offerWallRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.FOSSIL_MARLSTONE_COBBLED_WALL, ModBlocks.FOSSIL_MARLSTONE_COBBLED);
        offerWallRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.FOSSIL_MARLSTONE_BRICKS_WALL, ModBlocks.FOSSIL_MARLSTONE_BRICKS);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.STEPPE_VIPER_DAGGER, 1)
                .pattern(" #")
                .pattern("/ ")
                .input('#', ModItems.STEPPE_VIPER_FANG)
                .input('/', Items.STICK)
                .criterion(hasItem(ModItems.STEPPE_VIPER_FANG), conditionsFromItem(ModItems.STEPPE_VIPER_FANG))
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.SAIGA_SICKLE, 1)
                .pattern(" #")
                .pattern("/ ")
                .input('#', ModItems.SAIGA_HORN)
                .input('/', Items.STICK)
                .criterion(hasItem(ModItems.SAIGA_HORN), conditionsFromItem(ModItems.SAIGA_HORN))
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.STEPPE_ARROW, 1)
                .pattern("  #")
                .pattern(" / ")
                .pattern("*  ")
                .input('#', ModItems.STEPPE_EAGLE_BEAK)
                .input('*', ModItems.STEPPE_EAGLE_FEATHER)
                .input('/', Items.STICK)
                .criterion(hasItem(ModItems.STEPPE_EAGLE_BEAK), conditionsFromItem(ModItems.STEPPE_EAGLE_BEAK))
                .criterion(hasItem(ModItems.STEPPE_EAGLE_FEATHER), conditionsFromItem(ModItems.STEPPE_EAGLE_FEATHER))
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.PRAIRIES_TALISMAN, 1)
                .pattern(" R ")
                .pattern("ABC")
                .pattern(" S ")
                .input('A', ModItems.FERRUGINOUS_HAWK_FEATHER)
                .input('B', ModItems.BLACK_BILLED_MAGPIE_FEATHER)
                .input('C', ModItems.WESTERN_MEADOWLARK_FEATHER)
                .input('R', ModItems.BISON_HORN)
                .input('S', Items.STRING)
                .criterion(hasItem(ModItems.FERRUGINOUS_HAWK_FEATHER), conditionsFromItem(ModItems.FERRUGINOUS_HAWK_FEATHER))
                .criterion(hasItem(ModItems.BLACK_BILLED_MAGPIE_FEATHER), conditionsFromItem(ModItems.BLACK_BILLED_MAGPIE_FEATHER))
                .criterion(hasItem(ModItems.WESTERN_MEADOWLARK_FEATHER), conditionsFromItem(ModItems.WESTERN_MEADOWLARK_FEATHER))
                .criterion(hasItem(ModItems.BISON_HORN), conditionsFromItem(ModItems.BISON_HORN))
                .criterion(hasItem(Items.STRING), conditionsFromItem(Items.STRING))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.DARKLING_BEETLE_CHESTPLATE, 1)
                .pattern("# #")
                .pattern("###")
                .pattern("###")
                .input('#', ModItems.DARKLING_BEETLE_SHELL)
                .criterion(hasItem(ModItems.DARKLING_BEETLE_SHELL), conditionsFromItem(ModItems.DARKLING_BEETLE_SHELL))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.FURRED_LEATHER_HELMET, 1)
                .pattern("#")
                .pattern("*")
                .input('#', ModItems.MARMOT_FUR)
                .input('*', Items.LEATHER_HELMET)
                .criterion(hasItem(ModItems.MARMOT_FUR), conditionsFromItem(ModItems.MARMOT_FUR))
                .criterion(hasItem(Items.LEATHER_HELMET), conditionsFromItem(Items.LEATHER_HELMET))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.FURRED_LEATHER_CHESTPLATE, 1)
                .pattern("#")
                .pattern("*")
                .input('#', ModItems.MARMOT_FUR)
                .input('*', Items.LEATHER_CHESTPLATE)
                .criterion(hasItem(ModItems.MARMOT_FUR), conditionsFromItem(ModItems.MARMOT_FUR))
                .criterion(hasItem(Items.LEATHER_CHESTPLATE), conditionsFromItem(Items.LEATHER_CHESTPLATE))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.FURRED_LEATHER_LEGGINGS, 1)
                .pattern("#")
                .pattern("*")
                .input('#', ModItems.MARMOT_FUR)
                .input('*', Items.LEATHER_LEGGINGS)
                .criterion(hasItem(ModItems.MARMOT_FUR), conditionsFromItem(ModItems.MARMOT_FUR))
                .criterion(hasItem(Items.LEATHER_LEGGINGS), conditionsFromItem(Items.LEATHER_LEGGINGS))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.FURRED_LEATHER_BOOTS, 1)
                .pattern("#")
                .pattern("*")
                .input('#', ModItems.MARMOT_FUR)
                .input('*', Items.LEATHER_BOOTS)
                .criterion(hasItem(ModItems.MARMOT_FUR), conditionsFromItem(ModItems.MARMOT_FUR))
                .criterion(hasItem(Items.LEATHER_BOOTS), conditionsFromItem(Items.LEATHER_BOOTS))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.DOLOMITE_STAIRS, 4)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .input('#', ModBlocks.DOLOMITE_BLOCK)
                .criterion(hasItem(ModBlocks.DOLOMITE_BLOCK), conditionsFromItem(ModBlocks.DOLOMITE_BLOCK))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.DOLOMITE_BRICKS_STAIRS, 4)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .input('#', ModBlocks.DOLOMITE_BRICKS)
                .criterion(hasItem(ModBlocks.DOLOMITE_BRICKS), conditionsFromItem(ModBlocks.DOLOMITE_BRICKS))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.DOLOMITE_COBBLED_STAIRS, 4)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .input('#', ModBlocks.DOLOMITE_COBBLED)
                .criterion(hasItem(ModBlocks.DOLOMITE_COBBLED), conditionsFromItem(ModBlocks.DOLOMITE_COBBLED))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.DOLOMITE_POLISHED_STAIRS, 4)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .input('#', ModBlocks.DOLOMITE_POLISHED)
                .criterion(hasItem(ModBlocks.DOLOMITE_POLISHED), conditionsFromItem(ModBlocks.DOLOMITE_POLISHED))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.DOLOMITE_POLISHED, 4)
                .pattern("##")
                .pattern("##")
                .input('#', ModBlocks.DOLOMITE_BLOCK)
                .criterion(hasItem(ModBlocks.DOLOMITE_BLOCK), conditionsFromItem(ModBlocks.DOLOMITE_BLOCK))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.DOLOMITE_BRICKS, 4)
                .pattern("##")
                .pattern("##")
                .input('#', ModBlocks.DOLOMITE_POLISHED)
                .criterion(hasItem(ModBlocks.DOLOMITE_POLISHED), conditionsFromItem(ModBlocks.DOLOMITE_POLISHED))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.DOLOMITE_CHISELED, 1)
                .pattern("#")
                .pattern("#")
                .input('#', ModBlocks.DOLOMITE_BRICKS_SLAB)
                .criterion(hasItem(ModBlocks.DOLOMITE_BRICKS_SLAB), conditionsFromItem(ModBlocks.DOLOMITE_BRICKS_SLAB))
                .offerTo(recipeExporter);

        CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(ModBlocks.DOLOMITE_COBBLED), RecipeCategory.BUILDING_BLOCKS,
                        ModBlocks.DOLOMITE_BLOCK.asItem(), 0.1F, 200)
                .criterion(hasItem(ModBlocks.DOLOMITE_COBBLED), conditionsFromItem(ModBlocks.DOLOMITE_COBBLED))
                .offerTo(recipeExporter);

        CookingRecipeJsonBuilder.createBlasting(Ingredient.ofItems(ModBlocks.DOLOMITE_COBBLED), RecipeCategory.BUILDING_BLOCKS,
                        ModBlocks.DOLOMITE_BLOCK.asItem(), 0.1F, 100)
                .criterion(hasItem(ModBlocks.DOLOMITE_COBBLED), conditionsFromItem(ModBlocks.DOLOMITE_COBBLED))
                .offerTo(recipeExporter, "dolomite_block_blasting");

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SALTMARSH_STAIRS, 4)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .input('#', ModBlocks.SALTMARSH_BLOCK)
                .criterion(hasItem(ModBlocks.SALTMARSH_BLOCK), conditionsFromItem(ModBlocks.SALTMARSH_BLOCK))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SALTMARSH_BRICKS_STAIRS, 4)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .input('#', ModBlocks.SALTMARSH_BRICKS)
                .criterion(hasItem(ModBlocks.SALTMARSH_BRICKS), conditionsFromItem(ModBlocks.SALTMARSH_BRICKS))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SALTMARSH_POLISHED_STAIRS, 4)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .input('#', ModBlocks.SALTMARSH_POLISHED)
                .criterion(hasItem(ModBlocks.SALTMARSH_POLISHED), conditionsFromItem(ModBlocks.SALTMARSH_POLISHED))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SALTMARSH_COBBLED_STAIRS, 4)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .input('#', ModBlocks.SALTMARSH_COBBLED)
                .criterion(hasItem(ModBlocks.SALTMARSH_COBBLED), conditionsFromItem(ModBlocks.SALTMARSH_COBBLED))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SALTMARSH_POLISHED, 4)
                .pattern("##")
                .pattern("##")
                .input('#', ModBlocks.SALTMARSH_BLOCK)
                .criterion(hasItem(ModBlocks.SALTMARSH_BLOCK), conditionsFromItem(ModBlocks.SALTMARSH_BLOCK))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SALTMARSH_BRICKS, 4)
                .pattern("##")
                .pattern("##")
                .input('#', ModBlocks.SALTMARSH_POLISHED)
                .criterion(hasItem(ModBlocks.SALTMARSH_POLISHED), conditionsFromItem(ModBlocks.SALTMARSH_POLISHED))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SALTMARSH_CHISELED, 1)
                .pattern("#")
                .pattern("#")
                .input('#', ModBlocks.SALTMARSH_BRICKS_SLAB)
                .criterion(hasItem(ModBlocks.SALTMARSH_BRICKS_SLAB), conditionsFromItem(ModBlocks.SALTMARSH_BRICKS_SLAB))
                .offerTo(recipeExporter);

        CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(ModBlocks.SALTMARSH_COBBLED), RecipeCategory.BUILDING_BLOCKS,
                        ModBlocks.SALTMARSH_BLOCK.asItem(), 0.1F, 200)
                .criterion(hasItem(ModBlocks.SALTMARSH_COBBLED), conditionsFromItem(ModBlocks.SALTMARSH_COBBLED))
                .offerTo(recipeExporter);

        CookingRecipeJsonBuilder.createBlasting(Ingredient.ofItems(ModBlocks.SALTMARSH_COBBLED), RecipeCategory.BUILDING_BLOCKS,
                        ModBlocks.SALTMARSH_BLOCK.asItem(), 0.1F, 100)
                .criterion(hasItem(ModBlocks.SALTMARSH_COBBLED), conditionsFromItem(ModBlocks.SALTMARSH_COBBLED))
                .offerTo(recipeExporter, "saltmarsh_block_blasting");

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LIMESTONE_STAIRS, 4)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .input('#', ModBlocks.LIMESTONE_BLOCK)
                .criterion(hasItem(ModBlocks.LIMESTONE_BLOCK), conditionsFromItem(ModBlocks.LIMESTONE_BLOCK))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LIMESTONE_BRICKS_STAIRS, 4)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .input('#', ModBlocks.LIMESTONE_BRICKS)
                .criterion(hasItem(ModBlocks.LIMESTONE_BRICKS), conditionsFromItem(ModBlocks.LIMESTONE_BRICKS))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LIMESTONE_COBBLED_STAIRS, 4)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .input('#', ModBlocks.LIMESTONE_COBBLED)
                .criterion(hasItem(ModBlocks.LIMESTONE_COBBLED), conditionsFromItem(ModBlocks.LIMESTONE_COBBLED))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LIMESTONE_POLISHED_STAIRS, 4)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .input('#', ModBlocks.LIMESTONE_POLISHED)
                .criterion(hasItem(ModBlocks.LIMESTONE_POLISHED), conditionsFromItem(ModBlocks.LIMESTONE_POLISHED))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LIMESTONE_POLISHED, 4)
                .pattern("##")
                .pattern("##")
                .input('#', ModBlocks.LIMESTONE_BLOCK)
                .criterion(hasItem(ModBlocks.LIMESTONE_BLOCK), conditionsFromItem(ModBlocks.LIMESTONE_BLOCK))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LIMESTONE_BRICKS, 4)
                .pattern("##")
                .pattern("##")
                .input('#', ModBlocks.LIMESTONE_POLISHED)
                .criterion(hasItem(ModBlocks.LIMESTONE_POLISHED), conditionsFromItem(ModBlocks.LIMESTONE_POLISHED))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LIMESTONE_CHISELED, 1)
                .pattern("#")
                .pattern("#")
                .input('#', ModBlocks.LIMESTONE_BRICKS_SLAB)
                .criterion(hasItem(ModBlocks.LIMESTONE_BRICKS_SLAB), conditionsFromItem(ModBlocks.LIMESTONE_BRICKS_SLAB))
                .offerTo(recipeExporter);

        CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(ModBlocks.LIMESTONE_COBBLED), RecipeCategory.BUILDING_BLOCKS,
                        ModBlocks.LIMESTONE_BLOCK.asItem(), 0.1F, 200)
                .criterion(hasItem(ModBlocks.LIMESTONE_COBBLED), conditionsFromItem(ModBlocks.LIMESTONE_COBBLED))
                .offerTo(recipeExporter);

        CookingRecipeJsonBuilder.createBlasting(Ingredient.ofItems(ModBlocks.LIMESTONE_COBBLED), RecipeCategory.BUILDING_BLOCKS,
                        ModBlocks.LIMESTONE_BLOCK.asItem(), 0.1F, 100)
                .criterion(hasItem(ModBlocks.LIMESTONE_COBBLED), conditionsFromItem(ModBlocks.LIMESTONE_COBBLED))
                .offerTo(recipeExporter, "limestone_block_blasting");

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.FOSSIL_MARLSTONE_STAIRS, 4)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .input('#', ModBlocks.FOSSIL_MARLSTONE_BLOCK)
                .criterion(hasItem(ModBlocks.FOSSIL_MARLSTONE_BLOCK), conditionsFromItem(ModBlocks.FOSSIL_MARLSTONE_BLOCK))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.FOSSIL_MARLSTONE_BRICKS_STAIRS, 4)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .input('#', ModBlocks.FOSSIL_MARLSTONE_BRICKS)
                .criterion(hasItem(ModBlocks.FOSSIL_MARLSTONE_BRICKS), conditionsFromItem(ModBlocks.FOSSIL_MARLSTONE_BRICKS))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.FOSSIL_MARLSTONE_POLISHED_STAIRS, 4)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .input('#', ModBlocks.FOSSIL_MARLSTONE_POLISHED)
                .criterion(hasItem(ModBlocks.FOSSIL_MARLSTONE_POLISHED), conditionsFromItem(ModBlocks.FOSSIL_MARLSTONE_POLISHED))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.FOSSIL_MARLSTONE_COBBLED_STAIRS, 4)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .input('#', ModBlocks.FOSSIL_MARLSTONE_COBBLED)
                .criterion(hasItem(ModBlocks.FOSSIL_MARLSTONE_COBBLED), conditionsFromItem(ModBlocks.FOSSIL_MARLSTONE_COBBLED))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.FOSSIL_MARLSTONE_POLISHED, 4)
                .pattern("##")
                .pattern("##")
                .input('#', ModBlocks.FOSSIL_MARLSTONE_BLOCK)
                .criterion(hasItem(ModBlocks.FOSSIL_MARLSTONE_BLOCK), conditionsFromItem(ModBlocks.FOSSIL_MARLSTONE_BLOCK))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.FOSSIL_MARLSTONE_BRICKS, 4)
                .pattern("##")
                .pattern("##")
                .input('#', ModBlocks.FOSSIL_MARLSTONE_POLISHED)
                .criterion(hasItem(ModBlocks.FOSSIL_MARLSTONE_POLISHED), conditionsFromItem(ModBlocks.FOSSIL_MARLSTONE_POLISHED))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.FOSSIL_MARLSTONE_CHISELED, 1)
                .pattern("#")
                .pattern("#")
                .input('#', ModBlocks.FOSSIL_MARLSTONE_BRICKS_SLAB)
                .criterion(hasItem(ModBlocks.FOSSIL_MARLSTONE_BRICKS_SLAB), conditionsFromItem(ModBlocks.FOSSIL_MARLSTONE_BRICKS_SLAB))
                .offerTo(recipeExporter);

        CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(ModBlocks.FOSSIL_MARLSTONE_COBBLED), RecipeCategory.BUILDING_BLOCKS,
                        ModBlocks.FOSSIL_MARLSTONE_BLOCK.asItem(), 0.1F, 200)
                .criterion(hasItem(ModBlocks.FOSSIL_MARLSTONE_COBBLED), conditionsFromItem(ModBlocks.FOSSIL_MARLSTONE_COBBLED))
                .offerTo(recipeExporter);

        CookingRecipeJsonBuilder.createBlasting(Ingredient.ofItems(ModBlocks.FOSSIL_MARLSTONE_COBBLED), RecipeCategory.BUILDING_BLOCKS,
                        ModBlocks.FOSSIL_MARLSTONE_BLOCK.asItem(), 0.1F, 100)
                .criterion(hasItem(ModBlocks.FOSSIL_MARLSTONE_COBBLED), conditionsFromItem(ModBlocks.FOSSIL_MARLSTONE_COBBLED))
                .offerTo(recipeExporter, "fossil_marlstone_block_blasting");

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LOAMY_MARL_STAIRS, 4)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .input('#', ModBlocks.LOAMY_MARL_BLOCK)
                .criterion(hasItem(ModBlocks.LOAMY_MARL_BLOCK), conditionsFromItem(ModBlocks.LOAMY_MARL_BLOCK))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LOAMY_MARL_BRICKS_STAIRS, 4)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .input('#', ModBlocks.LOAMY_MARL_BRICKS)
                .criterion(hasItem(ModBlocks.LOAMY_MARL_BRICKS), conditionsFromItem(ModBlocks.LOAMY_MARL_BRICKS))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LOAMY_MARL_POLISHED_STAIRS, 4)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .input('#', ModBlocks.LOAMY_MARL_POLISHED)
                .criterion(hasItem(ModBlocks.LOAMY_MARL_POLISHED), conditionsFromItem(ModBlocks.LOAMY_MARL_POLISHED))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LOAMY_MARL_COBBLED_STAIRS, 4)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .input('#', ModBlocks.LOAMY_MARL_COBBLED)
                .criterion(hasItem(ModBlocks.LOAMY_MARL_COBBLED), conditionsFromItem(ModBlocks.LOAMY_MARL_COBBLED))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LOAMY_MARL_POLISHED, 4)
                .pattern("##")
                .pattern("##")
                .input('#', ModBlocks.LOAMY_MARL_BLOCK)
                .criterion(hasItem(ModBlocks.LOAMY_MARL_BLOCK), conditionsFromItem(ModBlocks.LOAMY_MARL_BLOCK))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LOAMY_MARL_BRICKS, 4)
                .pattern("##")
                .pattern("##")
                .input('#', ModBlocks.LOAMY_MARL_POLISHED)
                .criterion(hasItem(ModBlocks.LOAMY_MARL_POLISHED), conditionsFromItem(ModBlocks.LOAMY_MARL_POLISHED))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LOAMY_MARL_CHISELED, 1)
                .pattern("#")
                .pattern("#")
                .input('#', ModBlocks.LOAMY_MARL_BRICKS_SLAB)
                .criterion(hasItem(ModBlocks.LOAMY_MARL_BRICKS_SLAB), conditionsFromItem(ModBlocks.LOAMY_MARL_BRICKS_SLAB))
                .offerTo(recipeExporter);

        CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(ModBlocks.LOAMY_MARL_COBBLED), RecipeCategory.BUILDING_BLOCKS,
                        ModBlocks.LOAMY_MARL_BLOCK.asItem(), 0.1F, 200)
                .criterion(hasItem(ModBlocks.LOAMY_MARL_COBBLED), conditionsFromItem(ModBlocks.LOAMY_MARL_COBBLED))
                .offerTo(recipeExporter);

        CookingRecipeJsonBuilder.createBlasting(Ingredient.ofItems(ModBlocks.LOAMY_MARL_COBBLED), RecipeCategory.BUILDING_BLOCKS,
                        ModBlocks.LOAMY_MARL_BLOCK.asItem(), 0.1F, 100)
                .criterion(hasItem(ModBlocks.LOAMY_MARL_COBBLED), conditionsFromItem(ModBlocks.LOAMY_MARL_COBBLED))
                .offerTo(recipeExporter, "loamy_marl_block_blasting");

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LOESSIC_MARL_STAIRS, 4)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .input('#', ModBlocks.LOESSIC_MARL_BLOCK)
                .criterion(hasItem(ModBlocks.LOESSIC_MARL_BLOCK), conditionsFromItem(ModBlocks.LOESSIC_MARL_BLOCK))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LOESSIC_MARL_BRICKS_STAIRS, 4)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .input('#', ModBlocks.LOESSIC_MARL_BRICKS)
                .criterion(hasItem(ModBlocks.LOESSIC_MARL_BRICKS), conditionsFromItem(ModBlocks.LOESSIC_MARL_BRICKS))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LOESSIC_MARL_POLISHED_STAIRS, 4)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .input('#', ModBlocks.LOESSIC_MARL_POLISHED)
                .criterion(hasItem(ModBlocks.LOESSIC_MARL_POLISHED), conditionsFromItem(ModBlocks.LOESSIC_MARL_POLISHED))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LOESSIC_MARL_COBBLED_STAIRS, 4)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .input('#', ModBlocks.LOESSIC_MARL_COBBLED)
                .criterion(hasItem(ModBlocks.LOESSIC_MARL_COBBLED), conditionsFromItem(ModBlocks.LOESSIC_MARL_COBBLED))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LOESSIC_MARL_POLISHED, 4)
                .pattern("##")
                .pattern("##")
                .input('#', ModBlocks.LOESSIC_MARL_BLOCK)
                .criterion(hasItem(ModBlocks.LOESSIC_MARL_BLOCK), conditionsFromItem(ModBlocks.LOESSIC_MARL_BLOCK))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LOESSIC_MARL_BRICKS, 4)
                .pattern("##")
                .pattern("##")
                .input('#', ModBlocks.LOESSIC_MARL_POLISHED)
                .criterion(hasItem(ModBlocks.LOESSIC_MARL_POLISHED), conditionsFromItem(ModBlocks.LOESSIC_MARL_POLISHED))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LOESSIC_MARL_CHISELED, 1)
                .pattern("#")
                .pattern("#")
                .input('#', ModBlocks.LOESSIC_MARL_BRICKS_SLAB)
                .criterion(hasItem(ModBlocks.LOESSIC_MARL_BRICKS_SLAB), conditionsFromItem(ModBlocks.LOESSIC_MARL_BRICKS_SLAB))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.BARLEY_BREAD)
                .pattern("###")
                .input('#', ModItems.BARLEY)
                .criterion(hasItem(ModItems.BARLEY), conditionsFromItem(ModItems.BARLEY))
                .offerTo(recipeExporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.BARLEY_STEW)
                .input(ModItems.BARLEY)
                .input(Items.CARROT)
                .input(Items.POTATO)
                .input(Items.BOWL)
                .criterion(hasItem(ModItems.BARLEY), conditionsFromItem(ModItems.BARLEY))
                .criterion(hasItem(Items.CARROT), conditionsFromItem(Items.CARROT))
                .criterion(hasItem(Items.POTATO), conditionsFromItem(Items.POTATO))
                .criterion(hasItem(Items.BOWL), conditionsFromItem(Items.BOWL))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.OAT_COOKIE)
                .pattern("#X#")
                .input('#', ModItems.OAT)
                .input('X', Items.COCOA_BEANS)
                .criterion(hasItem(ModItems.OAT), conditionsFromItem(ModItems.OAT))
                .criterion(hasItem(Items.COCOA_BEANS), conditionsFromItem(Items.COCOA_BEANS))
                .offerTo(recipeExporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.OATMEAL)
                .input(ModItems.OAT, 2)
                .input(Items.MILK_BUCKET)
                .input(Items.BOWL)
                .criterion(hasItem(ModItems.OAT), conditionsFromItem(ModItems.OAT))
                .criterion(hasItem(Items.MILK_BUCKET), conditionsFromItem(Items.MILK_BUCKET))
                .criterion(hasItem(Items.BOWL), conditionsFromItem(Items.BOWL))
                .offerTo(recipeExporter);

        CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(ModBlocks.LOESSIC_MARL_COBBLED), RecipeCategory.BUILDING_BLOCKS,
                        ModBlocks.LOESSIC_MARL_BLOCK.asItem(), 0.1F, 200)
                .criterion(hasItem(ModBlocks.LOESSIC_MARL_COBBLED), conditionsFromItem(ModBlocks.LOESSIC_MARL_COBBLED))
                .offerTo(recipeExporter);

        CookingRecipeJsonBuilder.createBlasting(Ingredient.ofItems(ModBlocks.LOESSIC_MARL_COBBLED), RecipeCategory.BUILDING_BLOCKS,
                        ModBlocks.LOESSIC_MARL_BLOCK.asItem(), 0.1F, 100)
                .criterion(hasItem(ModBlocks.LOESSIC_MARL_COBBLED), conditionsFromItem(ModBlocks.LOESSIC_MARL_COBBLED))
                .offerTo(recipeExporter, "loessic_marl_block_blasting");

        offerStonecuttingRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SALTMARSH_SLAB, ModBlocks.SALTMARSH_BLOCK, 2);
        offerStonecuttingRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SALTMARSH_STAIRS, ModBlocks.SALTMARSH_BLOCK);
        offerStonecuttingRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SALTMARSH_BRICKS, ModBlocks.SALTMARSH_BLOCK);
        offerStonecuttingRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SALTMARSH_WALL, ModBlocks.SALTMARSH_BLOCK);
        offerStonecuttingRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SALTMARSH_POLISHED, ModBlocks.SALTMARSH_BLOCK);
        offerStonecuttingRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SALTMARSH_COBBLED_SLAB, ModBlocks.SALTMARSH_COBBLED, 2);
        offerStonecuttingRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SALTMARSH_COBBLED_STAIRS, ModBlocks.SALTMARSH_COBBLED);
        offerStonecuttingRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SALTMARSH_COBBLED_WALL, ModBlocks.SALTMARSH_COBBLED);
        offerStonecuttingRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SALTMARSH_POLISHED_SLAB, ModBlocks.SALTMARSH_BLOCK, 2);
        offerStonecuttingRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SALTMARSH_POLISHED_STAIRS, ModBlocks.SALTMARSH_BLOCK);
        offerStonecuttingRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SALTMARSH_BRICKS_SLAB, ModBlocks.SALTMARSH_BLOCK, 2);
        offerStonecuttingRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SALTMARSH_BRICKS_STAIRS, ModBlocks.SALTMARSH_BLOCK);
        offerStonecuttingRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SALTMARSH_BRICKS_WALL, ModBlocks.SALTMARSH_BLOCK);

        offerStonecuttingRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.DOLOMITE_SLAB, ModBlocks.DOLOMITE_BLOCK, 2);
        offerStonecuttingRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.DOLOMITE_STAIRS, ModBlocks.DOLOMITE_BLOCK);
        offerStonecuttingRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.DOLOMITE_BRICKS, ModBlocks.DOLOMITE_BLOCK);
        offerStonecuttingRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.DOLOMITE_WALL, ModBlocks.DOLOMITE_BLOCK);
        offerStonecuttingRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.DOLOMITE_POLISHED, ModBlocks.DOLOMITE_BLOCK);
        offerStonecuttingRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.DOLOMITE_COBBLED_SLAB, ModBlocks.DOLOMITE_COBBLED, 2);
        offerStonecuttingRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.DOLOMITE_COBBLED_STAIRS, ModBlocks.DOLOMITE_COBBLED);
        offerStonecuttingRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.DOLOMITE_COBBLED_WALL, ModBlocks.DOLOMITE_COBBLED);
        offerStonecuttingRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.DOLOMITE_POLISHED_SLAB, ModBlocks.DOLOMITE_BLOCK, 2);
        offerStonecuttingRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.DOLOMITE_POLISHED_STAIRS, ModBlocks.DOLOMITE_BLOCK);
        offerStonecuttingRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.DOLOMITE_BRICKS_SLAB, ModBlocks.DOLOMITE_BLOCK, 2);
        offerStonecuttingRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.DOLOMITE_BRICKS_STAIRS, ModBlocks.DOLOMITE_BLOCK);
        offerStonecuttingRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.DOLOMITE_BRICKS_WALL, ModBlocks.DOLOMITE_BLOCK);

        offerStonecuttingRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.FOSSIL_MARLSTONE_SLAB, ModBlocks.FOSSIL_MARLSTONE_BLOCK, 2);
        offerStonecuttingRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.FOSSIL_MARLSTONE_STAIRS, ModBlocks.FOSSIL_MARLSTONE_BLOCK);
        offerStonecuttingRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.FOSSIL_MARLSTONE_BRICKS, ModBlocks.FOSSIL_MARLSTONE_BLOCK);
        offerStonecuttingRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.FOSSIL_MARLSTONE_WALL, ModBlocks.FOSSIL_MARLSTONE_BLOCK);
        offerStonecuttingRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.FOSSIL_MARLSTONE_POLISHED, ModBlocks.FOSSIL_MARLSTONE_BLOCK);
        offerStonecuttingRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.FOSSIL_MARLSTONE_COBBLED_SLAB, ModBlocks.FOSSIL_MARLSTONE_COBBLED, 2);
        offerStonecuttingRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.FOSSIL_MARLSTONE_COBBLED_STAIRS, ModBlocks.FOSSIL_MARLSTONE_COBBLED);
        offerStonecuttingRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.FOSSIL_MARLSTONE_COBBLED_WALL, ModBlocks.FOSSIL_MARLSTONE_COBBLED);
        offerStonecuttingRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.FOSSIL_MARLSTONE_POLISHED_SLAB, ModBlocks.FOSSIL_MARLSTONE_BLOCK, 2);
        offerStonecuttingRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.FOSSIL_MARLSTONE_POLISHED_STAIRS, ModBlocks.FOSSIL_MARLSTONE_BLOCK);
        offerStonecuttingRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.FOSSIL_MARLSTONE_BRICKS_SLAB, ModBlocks.FOSSIL_MARLSTONE_BLOCK, 2);
        offerStonecuttingRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.FOSSIL_MARLSTONE_BRICKS_STAIRS, ModBlocks.FOSSIL_MARLSTONE_BLOCK);
        offerStonecuttingRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.FOSSIL_MARLSTONE_BRICKS_WALL, ModBlocks.FOSSIL_MARLSTONE_BLOCK);

        offerStonecuttingRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LIMESTONE_SLAB, ModBlocks.LIMESTONE_BLOCK, 2);
        offerStonecuttingRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LIMESTONE_STAIRS, ModBlocks.LIMESTONE_BLOCK);
        offerStonecuttingRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LIMESTONE_BRICKS, ModBlocks.LIMESTONE_BLOCK);
        offerStonecuttingRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LIMESTONE_WALL, ModBlocks.LIMESTONE_BLOCK);
        offerStonecuttingRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LIMESTONE_POLISHED, ModBlocks.LIMESTONE_BLOCK);
        offerStonecuttingRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LIMESTONE_COBBLED_SLAB, ModBlocks.LIMESTONE_COBBLED, 2);
        offerStonecuttingRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LIMESTONE_COBBLED_STAIRS, ModBlocks.LIMESTONE_COBBLED);
        offerStonecuttingRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LIMESTONE_COBBLED_WALL, ModBlocks.LIMESTONE_COBBLED);
        offerStonecuttingRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LIMESTONE_POLISHED_SLAB, ModBlocks.LIMESTONE_BLOCK, 2);
        offerStonecuttingRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LIMESTONE_POLISHED_STAIRS, ModBlocks.LIMESTONE_BLOCK);
        offerStonecuttingRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LIMESTONE_BRICKS_SLAB, ModBlocks.LIMESTONE_BLOCK, 2);
        offerStonecuttingRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LIMESTONE_BRICKS_STAIRS, ModBlocks.LIMESTONE_BLOCK);
        offerStonecuttingRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LIMESTONE_BRICKS_WALL, ModBlocks.LIMESTONE_BLOCK);

        offerStonecuttingRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LOAMY_MARL_SLAB, ModBlocks.LOAMY_MARL_BLOCK, 2);
        offerStonecuttingRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LOAMY_MARL_STAIRS, ModBlocks.LOAMY_MARL_BLOCK);
        offerStonecuttingRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LOAMY_MARL_BRICKS, ModBlocks.LOAMY_MARL_BLOCK);
        offerStonecuttingRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LOAMY_MARL_WALL, ModBlocks.LOAMY_MARL_BLOCK);
        offerStonecuttingRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LOAMY_MARL_POLISHED, ModBlocks.LOAMY_MARL_BLOCK);
        offerStonecuttingRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LOAMY_MARL_COBBLED_SLAB, ModBlocks.LOAMY_MARL_COBBLED, 2);
        offerStonecuttingRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LOAMY_MARL_COBBLED_STAIRS, ModBlocks.LOAMY_MARL_COBBLED);
        offerStonecuttingRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LOAMY_MARL_COBBLED_WALL, ModBlocks.LOAMY_MARL_COBBLED);
        offerStonecuttingRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LOAMY_MARL_POLISHED_SLAB, ModBlocks.LOAMY_MARL_BLOCK, 2);
        offerStonecuttingRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LOAMY_MARL_POLISHED_STAIRS, ModBlocks.LOAMY_MARL_BLOCK);
        offerStonecuttingRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LOAMY_MARL_BRICKS_SLAB, ModBlocks.LOAMY_MARL_BLOCK, 2);
        offerStonecuttingRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LOAMY_MARL_BRICKS_STAIRS, ModBlocks.LOAMY_MARL_BLOCK);
        offerStonecuttingRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LOAMY_MARL_BRICKS_WALL, ModBlocks.LOAMY_MARL_BLOCK);

        offerStonecuttingRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LOESSIC_MARL_SLAB, ModBlocks.LOESSIC_MARL_BLOCK, 2);
        offerStonecuttingRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LOESSIC_MARL_STAIRS, ModBlocks.LOESSIC_MARL_BLOCK);
        offerStonecuttingRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LOESSIC_MARL_BRICKS, ModBlocks.LOESSIC_MARL_BLOCK);
        offerStonecuttingRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LOESSIC_MARL_WALL, ModBlocks.LOESSIC_MARL_BLOCK);
        offerStonecuttingRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LOESSIC_MARL_POLISHED, ModBlocks.LOESSIC_MARL_BLOCK);
        offerStonecuttingRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LOESSIC_MARL_COBBLED_SLAB, ModBlocks.LOESSIC_MARL_COBBLED, 2);
        offerStonecuttingRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LOESSIC_MARL_COBBLED_STAIRS, ModBlocks.LOESSIC_MARL_COBBLED);
        offerStonecuttingRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LOESSIC_MARL_COBBLED_WALL, ModBlocks.LOESSIC_MARL_COBBLED);
        offerStonecuttingRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LOESSIC_MARL_POLISHED_SLAB, ModBlocks.LOESSIC_MARL_BLOCK, 2);
        offerStonecuttingRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LOESSIC_MARL_POLISHED_STAIRS, ModBlocks.LOESSIC_MARL_BLOCK);
        offerStonecuttingRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LOESSIC_MARL_BRICKS_SLAB, ModBlocks.LOESSIC_MARL_BLOCK, 2);
        offerStonecuttingRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LOESSIC_MARL_BRICKS_STAIRS, ModBlocks.LOESSIC_MARL_BLOCK);
        offerStonecuttingRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LOESSIC_MARL_BRICKS_WALL, ModBlocks.LOESSIC_MARL_BLOCK);

        offerFoodCookingRecipe(recipeExporter, "smoking", RecipeSerializer.SMOKING,
                100, ModItems.SAIGA, ModItems.COOKED_SAIGA, 0.35f);

        offerFoodCookingRecipe(recipeExporter, "campfire_cooking", RecipeSerializer.CAMPFIRE_COOKING,
                600, ModItems.SAIGA, ModItems.COOKED_SAIGA, 0.35f);

        CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(ModItems.SAIGA), RecipeCategory.FOOD,
                        ModItems.COOKED_SAIGA, 0.35F, 200)
                .criterion("has_saiga", conditionsFromItem(ModItems.SAIGA))
                .offerTo(recipeExporter);

        offerFoodCookingRecipe(recipeExporter, "smoking", RecipeSerializer.SMOKING,
                100, ModItems.BISON, ModItems.COOKED_BISON, 0.35f);

        offerFoodCookingRecipe(recipeExporter, "campfire_cooking", RecipeSerializer.CAMPFIRE_COOKING,
                600, ModItems.BISON, ModItems.COOKED_BISON, 0.35f);

        CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(ModItems.BISON), RecipeCategory.FOOD,
                        ModItems.COOKED_BISON, 0.35F, 200)
                .criterion("has_bison", conditionsFromItem(ModItems.BISON))
                .offerTo(recipeExporter);

        offerFoodCookingRecipe(recipeExporter, "smoking", RecipeSerializer.SMOKING,
                100, ModItems.JACKRABBIT, ModItems.COOKED_JACKRABBIT, 0.35f);

        offerFoodCookingRecipe(recipeExporter, "campfire_cooking", RecipeSerializer.CAMPFIRE_COOKING,
                600, ModItems.JACKRABBIT, ModItems.COOKED_JACKRABBIT, 0.35f);

        CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(ModItems.JACKRABBIT), RecipeCategory.FOOD,
                        ModItems.COOKED_JACKRABBIT, 0.35F, 200)
                .criterion("has_jackrabbit", conditionsFromItem(ModItems.JACKRABBIT))
                .offerTo(recipeExporter);

        offerFoodCookingRecipe(recipeExporter, "smoking", RecipeSerializer.SMOKING,
                100, ModItems.PALLID_WINGED_GRASSHOPPER_LEG, ModItems.COOKED_PALLID_WINGED_GRASSHOPPER_LEG, 0.35f);

        offerFoodCookingRecipe(recipeExporter, "campfire_cooking", RecipeSerializer.CAMPFIRE_COOKING,
                600, ModItems.PALLID_WINGED_GRASSHOPPER_LEG, ModItems.COOKED_PALLID_WINGED_GRASSHOPPER_LEG, 0.35f);

        CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(ModItems.PALLID_WINGED_GRASSHOPPER_LEG), RecipeCategory.FOOD,
                        ModItems.COOKED_PALLID_WINGED_GRASSHOPPER_LEG, 0.35F, 200)
                .criterion("has_pallid_winged_grasshopper_leg", conditionsFromItem(ModItems.PALLID_WINGED_GRASSHOPPER_LEG))
                .offerTo(recipeExporter);

        offerCompactingRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.BARLEY_HAY_BLOCK, ModItems.BARLEY);
        offerCompactingRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.OAT_HAY_BLOCK, ModItems.OAT);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.OAT, 9)
                .input(ModBlocks.OAT_HAY_BLOCK)
                .criterion("has_oat_hay_block", conditionsFromItem(ModBlocks.OAT_HAY_BLOCK))
                .offerTo(recipeExporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.BARLEY, 9)
                .input(ModBlocks.BARLEY_HAY_BLOCK)
                .criterion("has_barley_hay_block", conditionsFromItem(ModBlocks.BARLEY_HAY_BLOCK))
                .offerTo(recipeExporter);
    }
}