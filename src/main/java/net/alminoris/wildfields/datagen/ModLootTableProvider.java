package net.alminoris.wildfields.datagen;

import net.alminoris.wildfields.block.ModBlocks;
import net.alminoris.wildfields.block.custom.BerryBushBlock;
import net.alminoris.wildfields.block.custom.PricklyPearCactusBlock;
import net.alminoris.wildfields.block.custom.TripleTallPlantBlock;
import net.alminoris.wildfields.item.ModItems;
import net.alminoris.wildfields.util.helper.ModBlockSetsHelper;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropBlock;
import net.minecraft.block.TallPlantBlock;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.condition.TableBonusLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

import static net.alminoris.wildfields.util.helper.ModBlockSetsHelper.*;

public class ModLootTableProvider extends FabricBlockLootTableProvider
{
    public ModLootTableProvider(FabricDataOutput dataOutput)
    {
        super(dataOutput);
    }

    @Override
    public void generate()
    {
        for (String name : ModBlockSetsHelper.WOOD_NAMES)
        {
            addDrop(ModBlockSetsHelper.LOGS.get(name));
            addDrop(ModBlockSetsHelper.STRIPPED_LOGS.get(name));
            addDrop(ModBlockSetsHelper.WOODS.get(name));
            addDrop(ModBlockSetsHelper.STRIPPED_WOODS.get(name));
            addDrop(ModBlockSetsHelper.WOODEN_PLANKS.get(name));
            addDrop(ModBlockSetsHelper.WOODEN_SLABS.get(name));
            addDrop(ModBlockSetsHelper.WOODEN_STAIRS.get(name));
            addDrop(ModBlockSetsHelper.WOODEN_FENCES.get(name));
            addDrop(ModBlockSetsHelper.WOODEN_FENCE_GATES.get(name));
            addDrop(ModBlockSetsHelper.WOODEN_TRAPDOORS.get(name));
            addDrop(ModBlockSetsHelper.WOODEN_BUTTONS.get(name));
            addDrop(ModBlockSetsHelper.WOODEN_PRESSURE_PLATES.get(name));
            addDrop(ModBlockSetsHelper.WOODEN_SIGNS.get(name), drops(ModBlockSetsHelper.WOODEN_WALL_SIGNS.get(name)));
            addDrop(ModBlockSetsHelper.WOODEN_HANGING_SIGNS.get(name), drops(ModBlockSetsHelper.WOODEN_WALL_HANGING_SIGNS.get(name)));
            addDrop(ModBlockSetsHelper.WOODEN_SAPLINGS.get(name));
        }

        for (String name : BUSHES_NAMES)
        {
            addDrop(
                    BUSHES.get(name),
                    block -> this.applyExplosionDecay(
                            block,
                            LootTable.builder()
                                    .pool(
                                            LootPool.builder()
                                                    .conditionally(
                                                            BlockStatePropertyLootCondition.builder(BUSHES.get(name)).properties(StatePredicate.Builder.create().exactMatch(BerryBushBlock.AGE, 3))
                                                    )
                                                    .with(ItemEntry.builder(BERRIES.get(name)))
                                                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 3.0F)))
                                                    .apply(ApplyBonusLootFunction.uniformBonusCount(Enchantments.FORTUNE))
                                    )
                                    .pool(
                                            LootPool.builder()
                                                    .conditionally(
                                                            BlockStatePropertyLootCondition.builder(BUSHES.get(name)).properties(StatePredicate.Builder.create().exactMatch(BerryBushBlock.AGE, 2))
                                                    )
                                                    .with(ItemEntry.builder(BERRIES.get(name)))
                                                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F)))
                                                    .apply(ApplyBonusLootFunction.uniformBonusCount(Enchantments.FORTUNE))
                                    )
                    )
            );
        }

        addDrop(
                ModBlocks.PRICKLY_PEAR_CACTUS,
                block -> this.applyExplosionDecay(
                        block,
                        LootTable.builder()
                                .pool(
                                        LootPool.builder()
                                                .conditionally(
                                                        BlockStatePropertyLootCondition.builder(ModBlocks.PRICKLY_PEAR_CACTUS).properties(StatePredicate.Builder.create().exactMatch(PricklyPearCactusBlock.AGE, 3))
                                                )
                                                .with(ItemEntry.builder(ModItems.PRICKLY_PEAR))
                                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 3.0F)))
                                                .apply(ApplyBonusLootFunction.uniformBonusCount(Enchantments.FORTUNE))
                                )
                                .pool(
                                        LootPool.builder()
                                                .conditionally(
                                                        BlockStatePropertyLootCondition.builder(ModBlocks.PRICKLY_PEAR_CACTUS).properties(StatePredicate.Builder.create().exactMatch(PricklyPearCactusBlock.AGE, 2))
                                                )
                                                .with(ItemEntry.builder(ModItems.PRICKLY_PEAR))
                                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F)))
                                                .apply(ApplyBonusLootFunction.uniformBonusCount(Enchantments.FORTUNE))
                                )
                )
        );


        addDrop(ModBlockSetsHelper.LEAVES.get("olive"), leavesDrops(ModBlockSetsHelper.LEAVES.get("olive"),
                ModBlockSetsHelper.WOODEN_SAPLINGS.get("olive"), 0.0025f));

        addDrop(ModBlockSetsHelper.LEAVES.get("tamarisk"), leavesDrops(ModBlockSetsHelper.LEAVES.get("tamarisk"),
                ModBlockSetsHelper.WOODEN_SAPLINGS.get("tamarisk"), 0.0025f));

        addDrop(ModBlockSetsHelper.LEAVES.get("western_serviceberry"), leavesDrops(ModBlockSetsHelper.LEAVES.get("western_serviceberry"),
                ModBlockSetsHelper.WOODEN_SAPLINGS.get("western_serviceberry"), 0.0025f));

        addDrop(ModBlocks.FEATHER_GRASS, this::grassDrops);
        addDrop(ModBlocks.TINY_GRASS, this::tinyPlantDrops);
        addDrop(ModBlocks.BLUE_GRAMA_GRASS, block -> this.dropsWithProperty(block, TallPlantBlock.HALF, DoubleBlockHalf.LOWER));
        addDrop(ModBlocks.PRAIRIE_SAGE, block -> this.dropsWithProperty(block, TripleTallPlantBlock.PART, TripleTallPlantBlock.PlantPart.LOWER));
        addDrop(ModBlocks.THYME);
        addDrop(ModBlocks.SPIDER_MILKWEED);
        addDrop(ModBlocks.WORMWOOD);
        addDrop(ModBlocks.COTTONWOOD_FLUFF);
        addDrop(ModBlocks.PRAIRIE_ROSE);
        addDrop(ModBlocks.SMOOTH_ASTER);
        addDrop(ModBlocks.SERVAL_HIDE, dropsWithSilkTouch(ModBlocks.SERVAL_HIDE));

        for (String name : WILD_CROP_NAMES)
        {
            addDrop(WILD_CROPS.get(name));
        }

        for (String name : CROP_NAMES)
        {
            addDrop(HAY_BLOCKS.get(name));

            LootCondition.Builder builder1 = BlockStatePropertyLootCondition.builder(CROPS.get(name))
                    .properties(StatePredicate.Builder.create().exactMatch(CropBlock.AGE, 7));
            this.addDrop(CROPS.get(name), this.cropDrops(CROPS.get(name), CROP_ITEMS.get(name), CROP_SEEDS.get(name), builder1));
        }

        addDrop(ModBlocks.GREEN_LICHEN, dropsWithSilkTouch(ModBlocks.GREEN_LICHEN));

        for (String name : STONE_NAMES)
        {
            addDrop(STONE_BLOCKS.get(name).get("block"), drops(STONE_BLOCKS.get(name).get("cobbled")));
            for (String type : STONE_TYPES)
            {
                if (!type.equals("block")) addDrop(STONE_BLOCKS.get(name).get(type));
                addDrop(STONE_SLABS.get(name).get(type));
                addDrop(STONE_STAIRS.get(name).get(type));
                addDrop(STONE_WALLS.get(name).get(type));
            }
        }

        addDrop(ModBlocks.VIOLA);

        addDrop(ModBlockSetsHelper.LEAVES.get("olive"), leavesItemDrops(ModBlockSetsHelper.LEAVES.get("olive"),
                ModBlockSetsHelper.WOODEN_SAPLINGS.get("olive"), ModItems.OLIVES, 0.0025f));
    }

    public LootTable.Builder tinyPlantDrops(Block withShears)
    {
        return dropsWithShears(
                withShears,
                (LootPoolEntry.Builder<?>)this.applyExplosionDecay(
                        withShears,
                        ItemEntry.builder(Items.WHEAT_SEEDS)
                                .conditionally(RandomChanceLootCondition.builder(0.025F))
                                .apply(ApplyBonusLootFunction.uniformBonusCount(Enchantments.FORTUNE, 2))
                )
        );
    }

    private LootTable.Builder multipleOreDrops(Block drop, Item item, float minDrops, float maxDrops)
    {
        return dropsWithSilkTouch(
                drop,
                (LootPoolEntry.Builder<?>)this.applyExplosionDecay(
                        drop,
                        ItemEntry.builder(Items.RAW_COPPER)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(minDrops, maxDrops)))
                                .apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE))
                )
        );
    }

    private LootTable.Builder leavesItemDrops(Block leaves, Block sapling, Item item, float... saplingChance)
    {
        return this.leavesDrops(leaves, sapling, saplingChance)
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .conditionally(WITHOUT_SILK_TOUCH_NOR_SHEARS)
                                .with(
                                        ((LeafEntry.Builder)this.addSurvivesExplosionCondition(leaves, ItemEntry.builder(item)))
                                                .conditionally(TableBonusLootCondition.builder(Enchantments.FORTUNE, 0.005F, 0.0055555557F, 0.00625F, 0.008333334F, 0.025F))
                                )
                );
    }
}