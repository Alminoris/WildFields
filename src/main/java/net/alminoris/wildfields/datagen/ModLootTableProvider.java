package net.alminoris.wildfields.datagen;

import net.alminoris.wildfields.block.ModBlocks;
import net.alminoris.wildfields.item.ModItems;
import net.alminoris.wildfields.util.helper.ModBlockSetsHelper;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.block.SweetBerryBushBlock;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
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
                                                            BlockStatePropertyLootCondition.builder(BUSHES.get(name)).properties(StatePredicate.Builder.create().exactMatch(SweetBerryBushBlock.AGE, 3))
                                                    )
                                                    .with(ItemEntry.builder(BERRIES.get(name)))
                                                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 3.0F)))
                                                    .apply(ApplyBonusLootFunction.uniformBonusCount(Enchantments.FORTUNE))
                                    )
                                    .pool(
                                            LootPool.builder()
                                                    .conditionally(
                                                            BlockStatePropertyLootCondition.builder(BUSHES.get(name)).properties(StatePredicate.Builder.create().exactMatch(SweetBerryBushBlock.AGE, 2))
                                                    )
                                                    .with(ItemEntry.builder(BERRIES.get(name)))
                                                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F)))
                                                    .apply(ApplyBonusLootFunction.uniformBonusCount(Enchantments.FORTUNE))
                                    )
                    )
            );
        }


        addDrop(ModBlockSetsHelper.LEAVES.get("olive"), leavesDrops(ModBlockSetsHelper.LEAVES.get("olive"),
                ModBlockSetsHelper.WOODEN_SAPLINGS.get("olive"), 0.0025f));

        addDrop(ModBlockSetsHelper.LEAVES.get("tamarisk"), leavesDrops(ModBlockSetsHelper.LEAVES.get("tamarisk"),
                ModBlockSetsHelper.WOODEN_SAPLINGS.get("tamarisk"), 0.0025f));

        addDrop(ModBlockSetsHelper.LEAVES.get("western_serviceberry"), leavesDrops(ModBlockSetsHelper.LEAVES.get("western_serviceberry"),
                ModBlockSetsHelper.WOODEN_SAPLINGS.get("western_serviceberry"), 0.0025f));

        addDrop(ModBlocks.FEATHER_GRASS, this::grassDrops);
        addDrop(ModBlocks.TINY_GRASS, this::tinyPlantDrops);
        addDrop(ModBlocks.THYME);
        addDrop(ModBlocks.SPIDER_MILKWEED);
        addDrop(ModBlocks.WORMWOOD);
        addDrop(ModBlocks.SERVAL_HIDE, dropsWithSilkTouch(ModBlocks.SERVAL_HIDE));

        addDrop(ModBlocks.GREEN_LICHEN, dropsWithSilkTouch(ModBlocks.GREEN_LICHEN));

        addDrop(ModBlocks.SALTMARSH_BLOCK, drops(ModBlocks.SALTMARSH_COBBLED));
        addDrop(ModBlocks.SALTMARSH_WALL);
        addDrop(ModBlocks.SALTMARSH_COBBLED);
        addDrop(ModBlocks.SALTMARSH_COBBLED_WALL);
        addDrop(ModBlocks.SALTMARSH_POLISHED);
        addDrop(ModBlocks.SALTMARSH_CHISELED);
        addDrop(ModBlocks.SALTMARSH_BRICKS);
        addDrop(ModBlocks.SALTMARSH_BRICKS_WALL);
        addDrop(ModBlocks.SALTMARSH_STAIRS);
        addDrop(ModBlocks.SALTMARSH_SLAB);
        addDrop(ModBlocks.SALTMARSH_COBBLED_STAIRS);
        addDrop(ModBlocks.SALTMARSH_COBBLED_SLAB);
        addDrop(ModBlocks.SALTMARSH_POLISHED_STAIRS);
        addDrop(ModBlocks.SALTMARSH_POLISHED_SLAB);
        addDrop(ModBlocks.SALTMARSH_BRICKS_STAIRS);
        addDrop(ModBlocks.SALTMARSH_BRICKS_SLAB);
        addDrop(ModBlocks.DOLOMITE_BLOCK, drops(ModBlocks.DOLOMITE_COBBLED));
        addDrop(ModBlocks.DOLOMITE_WALL);
        addDrop(ModBlocks.DOLOMITE_COBBLED);
        addDrop(ModBlocks.DOLOMITE_COBBLED_WALL);
        addDrop(ModBlocks.DOLOMITE_POLISHED);
        addDrop(ModBlocks.DOLOMITE_CHISELED);
        addDrop(ModBlocks.DOLOMITE_BRICKS);
        addDrop(ModBlocks.DOLOMITE_BRICKS_WALL);
        addDrop(ModBlocks.DOLOMITE_STAIRS);
        addDrop(ModBlocks.DOLOMITE_SLAB);
        addDrop(ModBlocks.DOLOMITE_COBBLED_STAIRS);
        addDrop(ModBlocks.DOLOMITE_COBBLED_SLAB);
        addDrop(ModBlocks.DOLOMITE_BRICKS_STAIRS);
        addDrop(ModBlocks.DOLOMITE_BRICKS_SLAB);
        addDrop(ModBlocks.DOLOMITE_POLISHED_STAIRS);
        addDrop(ModBlocks.DOLOMITE_POLISHED_SLAB);
        addDrop(ModBlocks.FOSSIL_MARLSTONE_BLOCK, drops(ModBlocks.FOSSIL_MARLSTONE_COBBLED));
        addDrop(ModBlocks.FOSSIL_MARLSTONE_WALL);
        addDrop(ModBlocks.FOSSIL_MARLSTONE_COBBLED);
        addDrop(ModBlocks.FOSSIL_MARLSTONE_COBBLED_WALL);
        addDrop(ModBlocks.FOSSIL_MARLSTONE_POLISHED);
        addDrop(ModBlocks.FOSSIL_MARLSTONE_CHISELED);
        addDrop(ModBlocks.FOSSIL_MARLSTONE_BRICKS);
        addDrop(ModBlocks.FOSSIL_MARLSTONE_BRICKS_WALL);
        addDrop(ModBlocks.FOSSIL_MARLSTONE_STAIRS);
        addDrop(ModBlocks.FOSSIL_MARLSTONE_SLAB);
        addDrop(ModBlocks.FOSSIL_MARLSTONE_COBBLED_STAIRS);
        addDrop(ModBlocks.FOSSIL_MARLSTONE_COBBLED_SLAB);
        addDrop(ModBlocks.FOSSIL_MARLSTONE_POLISHED_STAIRS);
        addDrop(ModBlocks.FOSSIL_MARLSTONE_POLISHED_SLAB);
        addDrop(ModBlocks.FOSSIL_MARLSTONE_BRICKS_STAIRS);
        addDrop(ModBlocks.FOSSIL_MARLSTONE_BRICKS_SLAB);
        addDrop(ModBlocks.LOAMY_MARL_BLOCK, drops(ModBlocks.LOAMY_MARL_COBBLED));
        addDrop(ModBlocks.LOAMY_MARL_WALL);
        addDrop(ModBlocks.LOAMY_MARL_COBBLED);
        addDrop(ModBlocks.LOAMY_MARL_COBBLED_WALL);
        addDrop(ModBlocks.LOAMY_MARL_POLISHED);
        addDrop(ModBlocks.LOAMY_MARL_CHISELED);
        addDrop(ModBlocks.LOAMY_MARL_BRICKS);
        addDrop(ModBlocks.LOAMY_MARL_BRICKS_WALL);
        addDrop(ModBlocks.LOAMY_MARL_STAIRS);
        addDrop(ModBlocks.LOAMY_MARL_SLAB);
        addDrop(ModBlocks.LOAMY_MARL_COBBLED_STAIRS);
        addDrop(ModBlocks.LOAMY_MARL_COBBLED_SLAB);
        addDrop(ModBlocks.LOAMY_MARL_POLISHED_STAIRS);
        addDrop(ModBlocks.LOAMY_MARL_POLISHED_SLAB);
        addDrop(ModBlocks.LOAMY_MARL_BRICKS_STAIRS);
        addDrop(ModBlocks.LOAMY_MARL_BRICKS_SLAB);
        addDrop(ModBlocks.LOESSIC_MARL_BLOCK, drops(ModBlocks.LOESSIC_MARL_COBBLED));
        addDrop(ModBlocks.LOESSIC_MARL_WALL);
        addDrop(ModBlocks.LOESSIC_MARL_COBBLED);
        addDrop(ModBlocks.LOESSIC_MARL_COBBLED_WALL);
        addDrop(ModBlocks.LOESSIC_MARL_POLISHED);
        addDrop(ModBlocks.LOESSIC_MARL_CHISELED);
        addDrop(ModBlocks.LOESSIC_MARL_BRICKS);
        addDrop(ModBlocks.LOESSIC_MARL_BRICKS_WALL);
        addDrop(ModBlocks.LOESSIC_MARL_STAIRS);
        addDrop(ModBlocks.LOESSIC_MARL_SLAB);
        addDrop(ModBlocks.LOESSIC_MARL_COBBLED_STAIRS);
        addDrop(ModBlocks.LOESSIC_MARL_COBBLED_SLAB);
        addDrop(ModBlocks.LOESSIC_MARL_POLISHED_STAIRS);
        addDrop(ModBlocks.LOESSIC_MARL_POLISHED_SLAB);
        addDrop(ModBlocks.LOESSIC_MARL_BRICKS_STAIRS);
        addDrop(ModBlocks.LOESSIC_MARL_BRICKS_SLAB);

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
