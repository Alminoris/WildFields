package net.alminoris.wildfields.datagen;

import net.alminoris.wildfields.WildFields;
import net.alminoris.wildfields.block.ModBlocks;
import net.alminoris.wildfields.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.block.HayBlock;
import net.minecraft.data.server.recipe.CookingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.CampfireCookingRecipe;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SmokingRecipe;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

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

        offerSingleOutputShapelessRecipe(recipeExporter, Items.PINK_DYE, ModBlocks.PRAIRIE_ROSE, String.valueOf(RecipeCategory.MISC));
        offerSingleOutputShapelessRecipe(recipeExporter, Items.MAGENTA_DYE, ModBlocks.SMOOTH_ASTER, String.valueOf(RecipeCategory.MISC));
        offerSingleOutputShapelessRecipe(recipeExporter, Items.MAGENTA_DYE, ModBlocks.THYME, String.valueOf(RecipeCategory.MISC));
        offerSingleOutputShapelessRecipe(recipeExporter, Items.YELLOW_DYE, ModBlocks.WORMWOOD, String.valueOf(RecipeCategory.MISC));
        offerSingleOutputShapelessRecipe(recipeExporter, Items.WHITE_DYE, ModBlocks.SPIDER_MILKWEED, String.valueOf(RecipeCategory.MISC));
        offerSingleOutputShapelessRecipe(recipeExporter, Items.PURPLE_DYE, ModBlocks.VIOLA, String.valueOf(RecipeCategory.MISC));
        offerSingleOutputShapelessRecipe(recipeExporter, Items.LIGHT_GRAY_DYE, ModBlocks.FEATHER_GRASS, String.valueOf(RecipeCategory.MISC));

        for (String name : STONE_NAMES)
        {
            offerStoneSetRecipes(recipeExporter, name);
            for (String type : STONE_TYPES)
            {
                offerStoneSetRecipes(recipeExporter, name, type);
            }
        }

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

        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.BARLEY_BREAD)
                .pattern("###")
                .input('#', CROP_ITEMS.get("barley"))
                .criterion(hasItem(CROP_ITEMS.get("barley")), conditionsFromItem(CROP_ITEMS.get("barley")))
                .offerTo(recipeExporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.BARLEY_STEW)
                .input(CROP_ITEMS.get("barley"))
                .input(Items.CARROT)
                .input(Items.POTATO)
                .input(Items.BOWL)
                .criterion(hasItem(CROP_ITEMS.get("barley")), conditionsFromItem(CROP_ITEMS.get("barley")))
                .criterion(hasItem(Items.CARROT), conditionsFromItem(Items.CARROT))
                .criterion(hasItem(Items.POTATO), conditionsFromItem(Items.POTATO))
                .criterion(hasItem(Items.BOWL), conditionsFromItem(Items.BOWL))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.OAT_COOKIE)
                .pattern("#X#")
                .input('#', CROP_ITEMS.get("oat"))
                .input('X', Items.COCOA_BEANS)
                .criterion(hasItem(CROP_ITEMS.get("oat")), conditionsFromItem(CROP_ITEMS.get("oat")))
                .criterion(hasItem(Items.COCOA_BEANS), conditionsFromItem(Items.COCOA_BEANS))
                .offerTo(recipeExporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.OATMEAL)
                .input(CROP_ITEMS.get("oat"), 2)
                .input(Items.MILK_BUCKET)
                .input(Items.BOWL)
                .criterion(hasItem(CROP_ITEMS.get("oat")), conditionsFromItem(CROP_ITEMS.get("oat")))
                .criterion(hasItem(Items.MILK_BUCKET), conditionsFromItem(Items.MILK_BUCKET))
                .criterion(hasItem(Items.BOWL), conditionsFromItem(Items.BOWL))
                .offerTo(recipeExporter);


        offerFoodCookingRecipe(recipeExporter, "smoking", RecipeSerializer.SMOKING, SmokingRecipe::new,
                100, ModItems.SAIGA, ModItems.COOKED_SAIGA, 0.35f);

        offerFoodCookingRecipe(recipeExporter, "campfire_cooking", RecipeSerializer.CAMPFIRE_COOKING, CampfireCookingRecipe::new,
                600, ModItems.SAIGA, ModItems.COOKED_SAIGA, 0.35f);

        CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(ModItems.SAIGA), RecipeCategory.FOOD,
                        ModItems.COOKED_SAIGA, 0.35F, 200)
                .criterion("has_saiga", conditionsFromItem(ModItems.SAIGA))
                .offerTo(recipeExporter);

        offerFoodCookingRecipe(recipeExporter, "smoking", RecipeSerializer.SMOKING, SmokingRecipe::new,
                100, ModItems.BISON, ModItems.COOKED_BISON, 0.35f);

        offerFoodCookingRecipe(recipeExporter, "campfire_cooking", RecipeSerializer.CAMPFIRE_COOKING, CampfireCookingRecipe::new,
                600, ModItems.BISON, ModItems.COOKED_BISON, 0.35f);

        CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(ModItems.BISON), RecipeCategory.FOOD,
                        ModItems.COOKED_BISON, 0.35F, 200)
                .criterion("has_bison", conditionsFromItem(ModItems.BISON))
                .offerTo(recipeExporter);

        offerFoodCookingRecipe(recipeExporter, "smoking", RecipeSerializer.SMOKING, SmokingRecipe::new,
                100, ModItems.JACKRABBIT, ModItems.COOKED_JACKRABBIT, 0.35f);

        offerFoodCookingRecipe(recipeExporter, "campfire_cooking", RecipeSerializer.CAMPFIRE_COOKING, CampfireCookingRecipe::new,
                600, ModItems.JACKRABBIT, ModItems.COOKED_JACKRABBIT, 0.35f);

        CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(ModItems.JACKRABBIT), RecipeCategory.FOOD,
                        ModItems.COOKED_JACKRABBIT, 0.35F, 200)
                .criterion("has_jackrabbit", conditionsFromItem(ModItems.JACKRABBIT))
                .offerTo(recipeExporter);

        offerFoodCookingRecipe(recipeExporter, "smoking", RecipeSerializer.SMOKING, SmokingRecipe::new,
                100, ModItems.PALLID_WINGED_GRASSHOPPER_LEG, ModItems.COOKED_PALLID_WINGED_GRASSHOPPER_LEG, 0.35f);

        offerFoodCookingRecipe(recipeExporter, "campfire_cooking", RecipeSerializer.CAMPFIRE_COOKING, CampfireCookingRecipe::new,
                600, ModItems.PALLID_WINGED_GRASSHOPPER_LEG, ModItems.COOKED_PALLID_WINGED_GRASSHOPPER_LEG, 0.35f);

        CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(ModItems.PALLID_WINGED_GRASSHOPPER_LEG), RecipeCategory.FOOD,
                        ModItems.COOKED_PALLID_WINGED_GRASSHOPPER_LEG, 0.35F, 200)
                .criterion("has_pallid_winged_grasshopper_leg", conditionsFromItem(ModItems.PALLID_WINGED_GRASSHOPPER_LEG))
                .offerTo(recipeExporter);

        for (String name : CROP_NAMES)
        {
            ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, CROP_ITEMS.get(name), 9)
                    .input(HAY_BLOCKS.get(name))
                    .criterion(hasItem(HAY_BLOCKS.get(name)), conditionsFromItem(HAY_BLOCKS.get(name)))
                    .offerTo(recipeExporter);

            offerCompactingRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, HAY_BLOCKS.get(name), CROP_ITEMS.get(name));
        }
    }

    private void offerStoneSetRecipes(RecipeExporter recipeOutput, String name)
    {
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, STONE_BLOCKS.get(name).get("polished"), 4)
                .pattern("##")
                .pattern("##")
                .input('#', STONE_BLOCKS.get(name).get("block"))
                .criterion("has_" + name + "_block", conditionsFromItem(STONE_BLOCKS.get(name).get("block")))
                .offerTo(recipeOutput);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, STONE_BLOCKS.get(name).get("bricks"), 4)
                .pattern("##")
                .pattern("##")
                .input('#', STONE_BLOCKS.get(name).get("polished"))
                .criterion("has_" + name + "_polished", conditionsFromItem(STONE_BLOCKS.get(name).get("polished")))
                .offerTo(recipeOutput);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, STONE_BLOCKS.get(name).get("chiseled"), 1)
                .pattern("#")
                .pattern("#")
                .input('#', STONE_SLABS.get(name).get("bricks"))
                .criterion("has_" + name + "_bricks_slab", conditionsFromItem(STONE_SLABS.get(name).get("bricks")))
                .offerTo(recipeOutput);
        CookingRecipeJsonBuilder.createSmelting(
                        Ingredient.ofItems(STONE_BLOCKS.get(name).get("cobbled")),
                        RecipeCategory.BUILDING_BLOCKS,
                        STONE_BLOCKS.get(name).get("block").asItem(),
                        0.1F,
                        200)
                .criterion("has_" + name + "_cobbled", conditionsFromItem(STONE_BLOCKS.get(name).get("cobbled")))
                .offerTo(recipeOutput, Identifier.of(WildFields.MOD_ID, name + "_block_from_smelting"));
        CookingRecipeJsonBuilder.createBlasting(
                        Ingredient.ofItems(STONE_BLOCKS.get(name).get("cobbled")),
                        RecipeCategory.BUILDING_BLOCKS,
                        STONE_BLOCKS.get(name).get("block").asItem(),
                        0.1F,
                        100)
                .criterion("has_" + name + "_cobbled", conditionsFromItem(STONE_BLOCKS.get(name).get("cobbled")))
                .offerTo(recipeOutput, Identifier.of(WildFields.MOD_ID, name + "_block_blasting"));
    }

    private void offerStoneSetRecipes(RecipeExporter recipeOutput, String name, String type)
    {
        offerSlabRecipe(recipeOutput, RecipeCategory.BUILDING_BLOCKS, STONE_SLABS.get(name).get(type), STONE_BLOCKS.get(name).get(type));
        offerWallRecipe(recipeOutput, RecipeCategory.BUILDING_BLOCKS, STONE_WALLS.get(name).get(type), STONE_BLOCKS.get(name).get(type));
        createStairsRecipe(STONE_STAIRS.get(name).get(type), Ingredient.ofItems(STONE_BLOCKS.get(name).get(type)))
                .group(name)
                .criterion(hasItem(STONE_BLOCKS.get(name).get(type)), conditionsFromItem(STONE_BLOCKS.get(name).get(type)))
                .offerTo(recipeOutput);


        offerStonecuttingRecipe(recipeOutput, RecipeCategory.BUILDING_BLOCKS, STONE_SLABS.get(name).get(type), STONE_BLOCKS.get(name).get(type), 2);
        offerStonecuttingRecipe(recipeOutput, RecipeCategory.BUILDING_BLOCKS, STONE_STAIRS.get(name).get(type), STONE_BLOCKS.get(name).get(type));
        offerStonecuttingRecipe(recipeOutput, RecipeCategory.BUILDING_BLOCKS, STONE_WALLS.get(name).get(type), STONE_BLOCKS.get(name).get(type));
        if (!type.equals("block"))
            offerStonecuttingRecipe(recipeOutput, RecipeCategory.BUILDING_BLOCKS, STONE_BLOCKS.get(name).get(type), STONE_BLOCKS.get(name).get("block"));
    }
}