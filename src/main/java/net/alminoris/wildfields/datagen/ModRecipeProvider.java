package net.alminoris.wildfields.datagen;

import net.alminoris.wildfields.block.ModBlocks;
import net.alminoris.wildfields.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.CookingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.StonecuttingRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.CampfireCookingRecipe;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SmokingRecipe;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

import static net.alminoris.wildfields.util.helper.ModBlockSetsHelper.*;

public class ModRecipeProvider extends FabricRecipeProvider
{
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture)
    {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter recipeExporter)
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

        offerSingleOutputShapelessRecipe(recipeExporter, Items.MAGENTA_DYE, ModBlocks.THYME, String.valueOf(RecipeCategory.MISC));
        offerSingleOutputShapelessRecipe(recipeExporter, Items.LIGHT_GRAY_DYE, ModBlocks.FEATHER_GRASS, String.valueOf(RecipeCategory.MISC));

        offerSlabRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SALTMARSH_SLAB, ModBlocks.SALTMARSH_BLOCK);
        offerSlabRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.DOLOMITE_SLAB, ModBlocks.DOLOMITE_BLOCK);
        offerSlabRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SALTMARSH_COBBLED_SLAB, ModBlocks.SALTMARSH_COBBLED);
        offerSlabRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.DOLOMITE_COBBLED_SLAB, ModBlocks.DOLOMITE_COBBLED);
        offerSlabRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SALTMARSH_BRICKS_SLAB, ModBlocks.SALTMARSH_BRICKS);
        offerSlabRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.DOLOMITE_BRICKS_SLAB, ModBlocks.DOLOMITE_BRICKS);
        offerSlabRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SALTMARSH_POLISHED_SLAB, ModBlocks.SALTMARSH_POLISHED);
        offerSlabRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.DOLOMITE_POLISHED_SLAB, ModBlocks.DOLOMITE_POLISHED);

        offerWallRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SALTMARSH_WALL, ModBlocks.SALTMARSH_BLOCK);
        offerWallRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SALTMARSH_COBBLED_WALL, ModBlocks.SALTMARSH_COBBLED);
        offerWallRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SALTMARSH_BRICKS_WALL, ModBlocks.SALTMARSH_BRICKS);

        offerWallRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.DOLOMITE_WALL, ModBlocks.DOLOMITE_BLOCK);
        offerWallRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.DOLOMITE_COBBLED_WALL, ModBlocks.DOLOMITE_COBBLED);
        offerWallRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.DOLOMITE_BRICKS_WALL, ModBlocks.DOLOMITE_BRICKS);

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

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SALTMARSH_STAIRS, 4)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .input('#', ModBlocks.SALTMARSH_BLOCK)
                .criterion(hasItem(ModBlocks.SALTMARSH_BLOCK), conditionsFromItem(ModBlocks.SALTMARSH_BLOCK))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.DOLOMITE_STAIRS, 4)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .input('#', ModBlocks.DOLOMITE_BLOCK)
                .criterion(hasItem(ModBlocks.DOLOMITE_BLOCK), conditionsFromItem(ModBlocks.DOLOMITE_BLOCK))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SALTMARSH_BRICKS_STAIRS, 4)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .input('#', ModBlocks.SALTMARSH_BRICKS)
                .criterion(hasItem(ModBlocks.SALTMARSH_BRICKS), conditionsFromItem(ModBlocks.SALTMARSH_BRICKS))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.DOLOMITE_BRICKS_STAIRS, 4)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .input('#', ModBlocks.DOLOMITE_BRICKS)
                .criterion(hasItem(ModBlocks.DOLOMITE_BRICKS), conditionsFromItem(ModBlocks.DOLOMITE_BRICKS))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SALTMARSH_POLISHED_STAIRS, 4)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .input('#', ModBlocks.SALTMARSH_POLISHED)
                .criterion(hasItem(ModBlocks.SALTMARSH_POLISHED), conditionsFromItem(ModBlocks.SALTMARSH_POLISHED))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.DOLOMITE_COBBLED_STAIRS, 4)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .input('#', ModBlocks.DOLOMITE_COBBLED)
                .criterion(hasItem(ModBlocks.DOLOMITE_COBBLED), conditionsFromItem(ModBlocks.DOLOMITE_COBBLED))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SALTMARSH_COBBLED_STAIRS, 4)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .input('#', ModBlocks.SALTMARSH_COBBLED)
                .criterion(hasItem(ModBlocks.SALTMARSH_COBBLED), conditionsFromItem(ModBlocks.SALTMARSH_COBBLED))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.DOLOMITE_POLISHED_STAIRS, 4)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .input('#', ModBlocks.DOLOMITE_POLISHED)
                .criterion(hasItem(ModBlocks.DOLOMITE_POLISHED), conditionsFromItem(ModBlocks.DOLOMITE_POLISHED))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SALTMARSH_POLISHED, 4)
                .pattern("##")
                .pattern("##")
                .input('#', ModBlocks.SALTMARSH_BLOCK)
                .criterion(hasItem(ModBlocks.SALTMARSH_BLOCK), conditionsFromItem(ModBlocks.SALTMARSH_BLOCK))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.DOLOMITE_POLISHED, 4)
                .pattern("##")
                .pattern("##")
                .input('#', ModBlocks.DOLOMITE_BLOCK)
                .criterion(hasItem(ModBlocks.DOLOMITE_BLOCK), conditionsFromItem(ModBlocks.DOLOMITE_BLOCK))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SALTMARSH_BRICKS, 4)
                .pattern("##")
                .pattern("##")
                .input('#', ModBlocks.SALTMARSH_POLISHED)
                .criterion(hasItem(ModBlocks.SALTMARSH_POLISHED), conditionsFromItem(ModBlocks.SALTMARSH_POLISHED))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.DOLOMITE_BRICKS, 4)
                .pattern("##")
                .pattern("##")
                .input('#', ModBlocks.DOLOMITE_POLISHED)
                .criterion(hasItem(ModBlocks.DOLOMITE_POLISHED), conditionsFromItem(ModBlocks.DOLOMITE_POLISHED))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SALTMARSH_CHISELED, 1)
                .pattern("#")
                .pattern("#")
                .input('#', ModBlocks.SALTMARSH_BRICKS_SLAB)
                .criterion(hasItem(ModBlocks.SALTMARSH_BRICKS_SLAB), conditionsFromItem(ModBlocks.SALTMARSH_BRICKS_SLAB))
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

        CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(ModBlocks.SALTMARSH_COBBLED), RecipeCategory.BUILDING_BLOCKS,
                        ModBlocks.SALTMARSH_BLOCK.asItem(), 0.1F, 200)
                .criterion(hasItem(ModBlocks.SALTMARSH_COBBLED), conditionsFromItem(ModBlocks.SALTMARSH_COBBLED))
                .offerTo(recipeExporter);

        CookingRecipeJsonBuilder.createBlasting(Ingredient.ofItems(ModBlocks.DOLOMITE_COBBLED), RecipeCategory.BUILDING_BLOCKS,
                        ModBlocks.DOLOMITE_BLOCK.asItem(), 0.1F, 100)
                .criterion(hasItem(ModBlocks.DOLOMITE_COBBLED), conditionsFromItem(ModBlocks.DOLOMITE_COBBLED))
                .offerTo(recipeExporter, "dolomite_block_blasting");

        CookingRecipeJsonBuilder.createBlasting(Ingredient.ofItems(ModBlocks.SALTMARSH_COBBLED), RecipeCategory.BUILDING_BLOCKS,
                        ModBlocks.SALTMARSH_BLOCK.asItem(), 0.1F, 100)
                .criterion(hasItem(ModBlocks.SALTMARSH_COBBLED), conditionsFromItem(ModBlocks.SALTMARSH_COBBLED))
                .offerTo(recipeExporter, "saltmarsh_block_blasting");

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

        offerFoodCookingRecipe(recipeExporter, "smoking", RecipeSerializer.SMOKING, SmokingRecipe::new,
                100, ModItems.SAIGA, ModItems.COOKED_SAIGA, 0.35f);

        offerFoodCookingRecipe(recipeExporter, "campfire_cooking", RecipeSerializer.CAMPFIRE_COOKING, CampfireCookingRecipe::new,
                600, ModItems.SAIGA, ModItems.COOKED_SAIGA, 0.35f);

        CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(ModItems.SAIGA), RecipeCategory.FOOD,
                        ModItems.COOKED_SAIGA, 0.35F, 200)
                .criterion("has_saiga", conditionsFromItem(ModItems.SAIGA))
                .offerTo(recipeExporter);
    }
}