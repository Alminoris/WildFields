package net.alminoris.wildfields.datagen;

import net.alminoris.wildfields.WildFields;
import net.alminoris.wildfields.block.ModBlocks;
import net.alminoris.wildfields.item.ModArmorMaterials;
import net.alminoris.wildfields.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.client.*;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static net.alminoris.wildfields.util.helper.ModBlockSetsHelper.*;
import static net.minecraft.data.client.BlockStateModelGenerator.createBlockStateWithRandomHorizontalRotations;
import static net.minecraft.data.client.BlockStateModelGenerator.createSingletonBlockState;

public class ModModelProvider extends FabricModelProvider
{
    public ModModelProvider(FabricDataOutput output)
    {
        super(output);
    }

    private static final List<TrimMaterial> TRIM_MATERIALS = List.of(
            new TrimMaterial("quartz", 0.1F, Map.of()),
            new TrimMaterial("iron", 0.2F, Map.of(ArmorMaterials.IRON, "iron_darker")),
            new TrimMaterial("netherite", 0.3F, Map.of(ArmorMaterials.NETHERITE, "netherite_darker")),
            new TrimMaterial("redstone", 0.4F, Map.of()),
            new TrimMaterial("copper", 0.5F, Map.of()),
            new TrimMaterial("gold", 0.6F, Map.of(ArmorMaterials.GOLD, "gold_darker")),
            new TrimMaterial("emerald", 0.7F, Map.of()),
            new TrimMaterial("diamond", 0.8F, Map.of(ArmorMaterials.DIAMOND, "diamond_darker")),
            new TrimMaterial("lapis", 0.9F, Map.of()),
            new TrimMaterial("amethyst", 1.0F, Map.of())
    );

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator)
    {
        Dictionary<String, BlockStateModelGenerator.BlockTexturePool> woodenPlanksPool = new Hashtable<>()
        {{
            for(String name : WOOD_NAMES)
                put(name, blockStateModelGenerator.registerCubeAllModelTexturePool(WOODEN_PLANKS.get(name)));
        }};

        for(String name : WOOD_NAMES)
        {
            woodenPlanksPool.get(name).slab(WOODEN_SLABS.get(name));
            woodenPlanksPool.get(name).stairs(WOODEN_STAIRS.get(name));

            registerLogBlock(blockStateModelGenerator, LOGS.get(name),
                    Identifier.of(WildFields.MOD_ID, "block/"+name+"_log_top"),
                    Identifier.of(WildFields.MOD_ID, "block/"+name+"_log"));

            registerLogBlock(blockStateModelGenerator, STRIPPED_LOGS.get(name),
                    Identifier.of(WildFields.MOD_ID, "block/stripped_"+name+"_log_top"),
                    Identifier.of(WildFields.MOD_ID, "block/stripped_"+name+"_log"));

            registerLogBlock(blockStateModelGenerator, WOODS.get(name),
                    Identifier.of(WildFields.MOD_ID, "block/"+name+"_log"),
                    Identifier.of(WildFields.MOD_ID, "block/"+name+"_log"));

            registerLogBlock(blockStateModelGenerator, STRIPPED_WOODS.get(name),
                    Identifier.of(WildFields.MOD_ID, "block/stripped_"+name+"_log"),
                    Identifier.of(WildFields.MOD_ID, "block/stripped_"+name+"_log"));

            blockStateModelGenerator.registerSingleton(LEAVES.get(name), TexturedModel.LEAVES);
            blockStateModelGenerator.registerTintableCross(WOODEN_SAPLINGS.get(name), BlockStateModelGenerator.TintType.NOT_TINTED);

            blockStateModelGenerator.registerDoor(WOODEN_DOORS.get(name));

            blockStateModelGenerator.registerOrientableTrapdoor(WOODEN_TRAPDOORS.get(name));

            woodenPlanksPool.get(name).fence(WOODEN_FENCES.get(name));
            woodenPlanksPool.get(name).fenceGate(WOODEN_FENCE_GATES.get(name));
            woodenPlanksPool.get(name).button(WOODEN_BUTTONS.get(name));
            woodenPlanksPool.get(name).pressurePlate(WOODEN_PRESSURE_PLATES.get(name));

            woodenPlanksPool.get(name).family(WOODEN_BLOCK_FAMILIES.get(name));
            registerHangingSign(blockStateModelGenerator, STRIPPED_LOGS.get(name), WOODEN_HANGING_SIGNS.get(name), WOODEN_WALL_HANGING_SIGNS.get(name));
        }

        for (String name : BUSHES_NAMES)
        {
            blockStateModelGenerator.registerTintableCrossBlockStateWithStages(BUSHES.get(name),
                    BlockStateModelGenerator.TintType.NOT_TINTED, Properties.AGE_3, 0, 1, 2, 3);
        }

        blockStateModelGenerator.registerTintableCrossBlockStateWithStages(ModBlocks.PRICKLY_PEAR_CACTUS,
                BlockStateModelGenerator.TintType.NOT_TINTED, Properties.AGE_3, 0, 1, 2, 3);

        blockStateModelGenerator.registerDoubleBlock(ModBlocks.BLUE_GRAMA_GRASS, BlockStateModelGenerator.TintType.TINTED);

        blockStateModelGenerator.registerFlowerPotPlant(ModBlocks.THYME, ModBlocks.POTTED_THYME, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerFlowerPotPlant(ModBlocks.SPIDER_MILKWEED, ModBlocks.POTTED_SPIDER_MILKWEED, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerFlowerPotPlant(ModBlocks.WORMWOOD, ModBlocks.POTTED_WORMWOOD, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerFlowerPotPlant(ModBlocks.PRAIRIE_ROSE, ModBlocks.POTTED_PRAIRIE_ROSE, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerFlowerPotPlant(ModBlocks.SMOOTH_ASTER, ModBlocks.POTTED_SMOOTH_ASTER, BlockStateModelGenerator.TintType.NOT_TINTED);

        blockStateModelGenerator.registerTintableCross(ModBlocks.FEATHER_GRASS, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerTintableCross(ModBlocks.TINY_GRASS, BlockStateModelGenerator.TintType.TINTED);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.LIMESTONE_CHISELED);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SALTMARSH_CHISELED);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DOLOMITE_CHISELED);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.LOESSIC_MARL_CHISELED);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.LOAMY_MARL_CHISELED);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.FOSSIL_MARLSTONE_CHISELED);

        blockStateModelGenerator.registerWallPlant(ModBlocks.GREEN_LICHEN);

        blockStateModelGenerator.registerAxisRotated(ModBlocks.OAT_HAY_BLOCK, TexturedModel.CUBE_COLUMN, TexturedModel.CUBE_COLUMN_HORIZONTAL);
        blockStateModelGenerator.registerAxisRotated(ModBlocks.BARLEY_HAY_BLOCK, TexturedModel.CUBE_COLUMN, TexturedModel.CUBE_COLUMN_HORIZONTAL);

        blockStateModelGenerator.registerSimpleState(ModBlocks.WILD_WHEAT);
        blockStateModelGenerator.registerSimpleState(ModBlocks.WILD_BARLEY);
        blockStateModelGenerator.registerSimpleState(ModBlocks.WILD_OAT);

        BlockStateModelGenerator.BlockTexturePool limestonePool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.LIMESTONE_BLOCK);
        BlockStateModelGenerator.BlockTexturePool saltmarshPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.SALTMARSH_BLOCK);
        BlockStateModelGenerator.BlockTexturePool dolomitePool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.DOLOMITE_BLOCK);
        BlockStateModelGenerator.BlockTexturePool limestoneBricksPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.LIMESTONE_BRICKS);
        BlockStateModelGenerator.BlockTexturePool saltmarshBricksPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.SALTMARSH_BRICKS);
        BlockStateModelGenerator.BlockTexturePool dolomiteBricksPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.DOLOMITE_BRICKS);
        BlockStateModelGenerator.BlockTexturePool limestonePolishedPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.LIMESTONE_POLISHED);
        BlockStateModelGenerator.BlockTexturePool saltmarshPolishedPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.SALTMARSH_POLISHED);
        BlockStateModelGenerator.BlockTexturePool dolomitePolishedPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.DOLOMITE_POLISHED);
        BlockStateModelGenerator.BlockTexturePool limestoneCobbledPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.LIMESTONE_COBBLED);
        BlockStateModelGenerator.BlockTexturePool saltmarshCobbledPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.SALTMARSH_COBBLED);
        BlockStateModelGenerator.BlockTexturePool dolomiteCobbledPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.DOLOMITE_COBBLED);

        BlockStateModelGenerator.BlockTexturePool loessicMarlPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.LOESSIC_MARL_BLOCK);
        BlockStateModelGenerator.BlockTexturePool loamyMarlPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.LOAMY_MARL_BLOCK);
        BlockStateModelGenerator.BlockTexturePool fossilMarlstonePool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.FOSSIL_MARLSTONE_BLOCK);
        BlockStateModelGenerator.BlockTexturePool loessicMarlBricksPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.LOESSIC_MARL_BRICKS);
        BlockStateModelGenerator.BlockTexturePool loamyMarlBricksPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.LOAMY_MARL_BRICKS);
        BlockStateModelGenerator.BlockTexturePool fossilMarlstoneBricksPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.FOSSIL_MARLSTONE_BRICKS);
        BlockStateModelGenerator.BlockTexturePool loessicMarlCobbledPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.LOESSIC_MARL_COBBLED);
        BlockStateModelGenerator.BlockTexturePool loamyMarlCobbledPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.LOAMY_MARL_COBBLED);
        BlockStateModelGenerator.BlockTexturePool fossilMarlstoneCobbledPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.FOSSIL_MARLSTONE_COBBLED);
        BlockStateModelGenerator.BlockTexturePool loessicMarlPolishedPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.LOESSIC_MARL_POLISHED);
        BlockStateModelGenerator.BlockTexturePool loamyMarlPolishedPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.LOAMY_MARL_POLISHED);
        BlockStateModelGenerator.BlockTexturePool fossilMarlstonePolishedPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.FOSSIL_MARLSTONE_POLISHED);

        limestonePool.slab(ModBlocks.LIMESTONE_SLAB);
        limestonePool.stairs(ModBlocks.LIMESTONE_STAIRS);
        limestoneCobbledPool.slab(ModBlocks.LIMESTONE_COBBLED_SLAB);
        limestoneCobbledPool.stairs(ModBlocks.LIMESTONE_COBBLED_STAIRS);
        limestoneBricksPool.slab(ModBlocks.LIMESTONE_BRICKS_SLAB);
        limestoneBricksPool.stairs(ModBlocks.LIMESTONE_BRICKS_STAIRS);
        limestonePolishedPool.slab(ModBlocks.LIMESTONE_POLISHED_SLAB);
        limestonePolishedPool.stairs(ModBlocks.LIMESTONE_POLISHED_STAIRS);
        limestonePool.wall(ModBlocks.LIMESTONE_WALL);
        limestoneCobbledPool.wall(ModBlocks.LIMESTONE_COBBLED_WALL);
        limestoneBricksPool.wall(ModBlocks.LIMESTONE_BRICKS_WALL);
        
        saltmarshPool.slab(ModBlocks.SALTMARSH_SLAB);
        saltmarshPool.stairs(ModBlocks.SALTMARSH_STAIRS);
        saltmarshCobbledPool.slab(ModBlocks.SALTMARSH_COBBLED_SLAB);
        saltmarshCobbledPool.stairs(ModBlocks.SALTMARSH_COBBLED_STAIRS);
        saltmarshBricksPool.slab(ModBlocks.SALTMARSH_BRICKS_SLAB);
        saltmarshBricksPool.stairs(ModBlocks.SALTMARSH_BRICKS_STAIRS);
        saltmarshPolishedPool.slab(ModBlocks.SALTMARSH_POLISHED_SLAB);
        saltmarshPolishedPool.stairs(ModBlocks.SALTMARSH_POLISHED_STAIRS);
        saltmarshPool.wall(ModBlocks.SALTMARSH_WALL);
        saltmarshCobbledPool.wall(ModBlocks.SALTMARSH_COBBLED_WALL);
        saltmarshBricksPool.wall(ModBlocks.SALTMARSH_BRICKS_WALL);

        dolomitePool.slab(ModBlocks.DOLOMITE_SLAB);
        dolomitePool.stairs(ModBlocks.DOLOMITE_STAIRS);
        dolomiteCobbledPool.slab(ModBlocks.DOLOMITE_COBBLED_SLAB);
        dolomiteCobbledPool.stairs(ModBlocks.DOLOMITE_COBBLED_STAIRS);
        dolomiteBricksPool.slab(ModBlocks.DOLOMITE_BRICKS_SLAB);
        dolomiteBricksPool.stairs(ModBlocks.DOLOMITE_BRICKS_STAIRS);
        dolomitePolishedPool.slab(ModBlocks.DOLOMITE_POLISHED_SLAB);
        dolomitePolishedPool.stairs(ModBlocks.DOLOMITE_POLISHED_STAIRS);
        dolomitePool.wall(ModBlocks.DOLOMITE_WALL);
        dolomiteCobbledPool.wall(ModBlocks.DOLOMITE_COBBLED_WALL);
        dolomiteBricksPool.wall(ModBlocks.DOLOMITE_BRICKS_WALL);

        loamyMarlPool.slab(ModBlocks.LOAMY_MARL_SLAB);
        loamyMarlPool.stairs(ModBlocks.LOAMY_MARL_STAIRS);
        loamyMarlCobbledPool.slab(ModBlocks.LOAMY_MARL_COBBLED_SLAB);
        loamyMarlCobbledPool.stairs(ModBlocks.LOAMY_MARL_COBBLED_STAIRS);
        loamyMarlBricksPool.slab(ModBlocks.LOAMY_MARL_BRICKS_SLAB);
        loamyMarlBricksPool.stairs(ModBlocks.LOAMY_MARL_BRICKS_STAIRS);
        loamyMarlPolishedPool.slab(ModBlocks.LOAMY_MARL_POLISHED_SLAB);
        loamyMarlPolishedPool.stairs(ModBlocks.LOAMY_MARL_POLISHED_STAIRS);
        loamyMarlPool.wall(ModBlocks.LOAMY_MARL_WALL);
        loamyMarlCobbledPool.wall(ModBlocks.LOAMY_MARL_COBBLED_WALL);
        loamyMarlBricksPool.wall(ModBlocks.LOAMY_MARL_BRICKS_WALL);

        loessicMarlPool.slab(ModBlocks.LOESSIC_MARL_SLAB);
        loessicMarlPool.stairs(ModBlocks.LOESSIC_MARL_STAIRS);
        loessicMarlCobbledPool.slab(ModBlocks.LOESSIC_MARL_COBBLED_SLAB);
        loessicMarlCobbledPool.stairs(ModBlocks.LOESSIC_MARL_COBBLED_STAIRS);
        loessicMarlBricksPool.slab(ModBlocks.LOESSIC_MARL_BRICKS_SLAB);
        loessicMarlBricksPool.stairs(ModBlocks.LOESSIC_MARL_BRICKS_STAIRS);
        loessicMarlPolishedPool.slab(ModBlocks.LOESSIC_MARL_POLISHED_SLAB);
        loessicMarlPolishedPool.stairs(ModBlocks.LOESSIC_MARL_POLISHED_STAIRS);
        loessicMarlPool.wall(ModBlocks.LOESSIC_MARL_WALL);
        loessicMarlCobbledPool.wall(ModBlocks.LOESSIC_MARL_COBBLED_WALL);
        loessicMarlBricksPool.wall(ModBlocks.LOESSIC_MARL_BRICKS_WALL);

        fossilMarlstonePool.slab(ModBlocks.FOSSIL_MARLSTONE_SLAB);
        fossilMarlstonePool.stairs(ModBlocks.FOSSIL_MARLSTONE_STAIRS);
        fossilMarlstoneCobbledPool.slab(ModBlocks.FOSSIL_MARLSTONE_COBBLED_SLAB);
        fossilMarlstoneCobbledPool.stairs(ModBlocks.FOSSIL_MARLSTONE_COBBLED_STAIRS);
        fossilMarlstoneBricksPool.slab(ModBlocks.FOSSIL_MARLSTONE_BRICKS_SLAB);
        fossilMarlstoneBricksPool.stairs(ModBlocks.FOSSIL_MARLSTONE_BRICKS_STAIRS);
        fossilMarlstonePolishedPool.slab(ModBlocks.FOSSIL_MARLSTONE_POLISHED_SLAB);
        fossilMarlstonePolishedPool.stairs(ModBlocks.FOSSIL_MARLSTONE_POLISHED_STAIRS);
        fossilMarlstonePool.wall(ModBlocks.FOSSIL_MARLSTONE_WALL);
        fossilMarlstoneCobbledPool.wall(ModBlocks.FOSSIL_MARLSTONE_COBBLED_WALL);
        fossilMarlstoneBricksPool.wall(ModBlocks.FOSSIL_MARLSTONE_BRICKS_WALL);

        blockStateModelGenerator.registerParentedItemModel(ModItems.MARMOT_SPAWN_EGG, ModelIds.getMinecraftNamespacedItem("template_spawn_egg"));
        blockStateModelGenerator.registerParentedItemModel(ModItems.STEPPE_EAGLE_SPAWN_EGG, ModelIds.getMinecraftNamespacedItem("template_spawn_egg"));
        blockStateModelGenerator.registerParentedItemModel(ModItems.DARKLING_BEETLE_SPAWN_EGG, ModelIds.getMinecraftNamespacedItem("template_spawn_egg"));
        blockStateModelGenerator.registerParentedItemModel(ModItems.STEPPE_VIPER_SPAWN_EGG, ModelIds.getMinecraftNamespacedItem("template_spawn_egg"));
        blockStateModelGenerator.registerParentedItemModel(ModItems.SAIGA_SPAWN_EGG, ModelIds.getMinecraftNamespacedItem("template_spawn_egg"));
        blockStateModelGenerator.registerParentedItemModel(ModItems.SERVAL_SPAWN_EGG, ModelIds.getMinecraftNamespacedItem("template_spawn_egg"));
        blockStateModelGenerator.registerParentedItemModel(ModItems.MOLE_SPAWN_EGG, ModelIds.getMinecraftNamespacedItem("template_spawn_egg"));
        blockStateModelGenerator.registerParentedItemModel(ModItems.COYOTE_SPAWN_EGG, ModelIds.getMinecraftNamespacedItem("template_spawn_egg"));
        blockStateModelGenerator.registerParentedItemModel(ModItems.BISON_SPAWN_EGG, ModelIds.getMinecraftNamespacedItem("template_spawn_egg"));
        blockStateModelGenerator.registerParentedItemModel(ModItems.FERRUGINOUS_HAWK_SPAWN_EGG, ModelIds.getMinecraftNamespacedItem("template_spawn_egg"));
        blockStateModelGenerator.registerParentedItemModel(ModItems.WHITE_TAILED_JACKRABBIT_SPAWN_EGG, ModelIds.getMinecraftNamespacedItem("template_spawn_egg"));
        blockStateModelGenerator.registerParentedItemModel(ModItems.PALLID_WINGED_GRASSHOPPER_SPAWN_EGG, ModelIds.getMinecraftNamespacedItem("template_spawn_egg"));
        blockStateModelGenerator.registerParentedItemModel(ModItems.BLACK_BILLED_MAGPIE_SPAWN_EGG, ModelIds.getMinecraftNamespacedItem("template_spawn_egg"));
        blockStateModelGenerator.registerParentedItemModel(ModItems.WESTERN_MEADOWLARK_SPAWN_EGG, ModelIds.getMinecraftNamespacedItem("template_spawn_egg"));

        blockStateModelGenerator.registerCrop(ModBlocks.OAT, Properties.AGE_7, 0, 1, 2, 3, 4, 5, 6, 7);
        blockStateModelGenerator.registerCrop(ModBlocks.BARLEY, Properties.AGE_7, 0, 1, 2, 3, 4, 5, 6, 7);

        blockStateModelGenerator.registerFlowerbed(ModBlocks.VIOLA);
    }

    private void registerLilyPad(BlockStateModelGenerator blockStateModelGenerator, Block block)
    {
        blockStateModelGenerator.registerItemModel(block);
        blockStateModelGenerator.blockStateCollector.accept(createBlockStateWithRandomHorizontalRotations(block, ModelIds.getBlockModelId(block)));
    }

    private void registerGrassBlock(BlockStateModelGenerator generator, Block grassBlock)
    {
        TextureMap textureMap = new TextureMap()
                .put(TextureKey.BOTTOM, TextureMap.getId(Blocks.DIRT))
                .put(TextureKey.TOP, TextureMap.getSubId(grassBlock, "_top"))
                .put(TextureKey.SIDE, TextureMap.getSubId(grassBlock, "_side"));
        generator.blockStateCollector.accept(createSingletonBlockState(grassBlock, Models.CUBE_BOTTOM_TOP.upload(grassBlock, textureMap, generator.modelCollector)));
    }

    public final void registerCarpet(BlockStateModelGenerator blockStateModelGenerator, Block wool, Block carpet)
    {
        Identifier identifier = TexturedModel.CARPET.get(wool).upload(carpet, blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(createSingletonBlockState(carpet, identifier));
    }

    private void registerHangingSign(BlockStateModelGenerator blockStateModelGenerator, Block strippedLog, Block hangingSign, Block wallHangingSign)
    {
        TextureMap textureMap = TextureMap.particle(strippedLog);
        Identifier identifier = Models.PARTICLE.upload(hangingSign, textureMap, blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(createSingletonBlockState(hangingSign, identifier));
        blockStateModelGenerator.blockStateCollector.accept(createSingletonBlockState(wallHangingSign, identifier));
    }

    private void registerLogBlock(BlockStateModelGenerator generator, Block logBlock, Identifier topTexture, Identifier sideTexture)
    {
        Function<Block, TextureMap> texturesGetter = block -> new TextureMap()
                .put(TextureKey.TOP, topTexture)
                .put(TextureKey.SIDE, sideTexture)
                .put(TextureKey.END, topTexture);

        generator.registerAxisRotated(
                logBlock,
                TexturedModel.makeFactory(texturesGetter, Models.CUBE_COLUMN),
                TexturedModel.makeFactory(texturesGetter, Models.CUBE_COLUMN_HORIZONTAL)
        );
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator)
    {
        for(String name : WOOD_NAMES)
        {
            itemModelGenerator.register(WOODEN_BOATS.get(name), Models.GENERATED);
            itemModelGenerator.register(WOODEN_CHEST_BOATS.get(name), Models.GENERATED);
            itemModelGenerator.register(WOODEN_HANGING_SIGN_ITEMS.get(name), Models.GENERATED);
        }
        itemModelGenerator.register(ModItems.OLIVES, Models.GENERATED);
        itemModelGenerator.register(ModBlocks.SERVAL_HIDE.asItem(), Models.GENERATED);
        itemModelGenerator.register(ModItems.DARKLING_BEETLE_SHELL, Models.GENERATED);
        itemModelGenerator.register(ModItems.MARMOT_FUR, Models.GENERATED);
        itemModelGenerator.register(ModItems.STEPPE_ARROW, Models.GENERATED);
        itemModelGenerator.register(ModItems.STEPPE_EAGLE_FEATHER, Models.GENERATED);
        itemModelGenerator.register(ModItems.STEPPE_EAGLE_BEAK, Models.GENERATED);
        itemModelGenerator.register(ModItems.STEPPE_VIPER_FANG, Models.GENERATED);
        itemModelGenerator.register(ModItems.STEPPE_VIPER_DAGGER, Models.GENERATED);
        itemModelGenerator.register(ModItems.SAIGA, Models.GENERATED);
        itemModelGenerator.register(ModItems.EARTHWORM, Models.GENERATED);
        itemModelGenerator.register(ModItems.SAIGA_HORN, Models.GENERATED);
        itemModelGenerator.register(ModItems.COOKED_SAIGA, Models.GENERATED);
        itemModelGenerator.register(ModItems.SAIGA_SICKLE, Models.GENERATED);
        itemModelGenerator.register(ModBlocks.PRAIRIE_SAGE.asItem(), Models.GENERATED);
        itemModelGenerator.register(ModBlocks.COTTONWOOD_FLUFF.asItem(), Models.GENERATED);

        itemModelGenerator.register(ModBlocks.WILD_WHEAT.asItem(), Models.GENERATED);
        itemModelGenerator.register(ModBlocks.WILD_BARLEY.asItem(), Models.GENERATED);
        itemModelGenerator.register(ModBlocks.WILD_OAT.asItem(), Models.GENERATED);

        itemModelGenerator.register(ModItems.BARLEY, Models.GENERATED);
        itemModelGenerator.register(ModItems.OAT, Models.GENERATED);

        itemModelGenerator.register(ModItems.BARLEY_BREAD, Models.GENERATED);
        itemModelGenerator.register(ModItems.BARLEY_STEW, Models.GENERATED);
        itemModelGenerator.register(ModItems.OAT_COOKIE, Models.GENERATED);
        itemModelGenerator.register(ModItems.OATMEAL, Models.GENERATED);

        itemModelGenerator.register(ModItems.JACKRABBIT, Models.GENERATED);
        itemModelGenerator.register(ModItems.COOKED_JACKRABBIT, Models.GENERATED);

        itemModelGenerator.register(ModItems.BISON_HORN, Models.GENERATED);
        itemModelGenerator.register(ModItems.BISON, Models.GENERATED);
        itemModelGenerator.register(ModItems.COOKED_BISON, Models.GENERATED);

        itemModelGenerator.register(ModItems.FERRUGINOUS_HAWK_FEATHER, Models.GENERATED);

        itemModelGenerator.register(ModItems.PALLID_WINGED_GRASSHOPPER_LEG, Models.GENERATED);
        itemModelGenerator.register(ModItems.COOKED_PALLID_WINGED_GRASSHOPPER_LEG, Models.GENERATED);

        itemModelGenerator.register(ModItems.BLACK_BILLED_MAGPIE_FEATHER, Models.GENERATED);

        itemModelGenerator.register(ModItems.WESTERN_MEADOWLARK_FEATHER, Models.GENERATED);

        itemModelGenerator.register(ModItems.PRAIRIES_TALISMAN, Models.GENERATED);

        registerArmor(itemModelGenerator, (ArmorItem) ModItems.DARKLING_BEETLE_CHESTPLATE);
        registerArmor(itemModelGenerator, (ArmorItem) ModItems.FURRED_LEATHER_HELMET);
        registerArmor(itemModelGenerator, (ArmorItem) ModItems.FURRED_LEATHER_BOOTS);
        registerArmor(itemModelGenerator, (ArmorItem) ModItems.FURRED_LEATHER_CHESTPLATE);
        registerArmor(itemModelGenerator, (ArmorItem) ModItems.FURRED_LEATHER_LEGGINGS);
    }

    public final void registerArmor(ItemModelGenerator itemModelGenerator, ArmorItem armor)
    {
        if (armor.getType().isTrimmable())
        {
            Identifier identifier = ModelIds.getItemModelId(armor);
            Identifier identifier2 = TextureMap.getId(armor);
            Identifier identifier3 = TextureMap.getSubId(armor, "_overlay");
            if (armor.getMaterial().matches(ModArmorMaterials.FURRED_LEATHER))
            {
                Models.GENERATED_TWO_LAYERS
                        .upload(identifier, TextureMap.layered(identifier2, identifier3), itemModelGenerator.writer, (id, textures) -> itemModelGenerator.createArmorJson(id, textures, armor.getMaterial()));
            }
            else
            {
                Models.GENERATED.upload(identifier, TextureMap.layer0(identifier2), itemModelGenerator.writer, (id, textures) -> itemModelGenerator.createArmorJson(id, textures, armor.getMaterial()));
            }

            for (TrimMaterial trimMaterial : TRIM_MATERIALS)
            {
                String string = trimMaterial.getAppliedName(armor.getMaterial());
                Identifier identifier4 = itemModelGenerator.suffixTrim(identifier, string);
                String string2 = armor.getType().getName() + "_trim_" + string;
                Identifier identifier5 = Identifier.ofVanilla(string2).withPrefixedPath("trims/items/");
                if (armor.getMaterial().matches(ModArmorMaterials.FURRED_LEATHER))
                {
                    itemModelGenerator.uploadArmor(identifier4, identifier2, identifier3, identifier5);
                }
                else
                {
                    itemModelGenerator.uploadArmor(identifier4, identifier2, identifier5);
                }
            }
        }
    }

    record TrimMaterial(String name, float itemModelIndex, Map<RegistryEntry<ArmorMaterial>, String> overrideArmorMaterials)
    {
        public String getAppliedName(RegistryEntry<ArmorMaterial> armorMaterial)
        {
            return (String)this.overrideArmorMaterials.getOrDefault(armorMaterial, this.name);
        }
    }
}
