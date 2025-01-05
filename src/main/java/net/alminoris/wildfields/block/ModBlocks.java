package net.alminoris.wildfields.block;

import net.alminoris.wildfields.WildFields;
import net.alminoris.wildfields.block.custom.AnimalHideBlock;
import net.alminoris.wildfields.block.custom.BerryBushBlock;
import net.alminoris.wildfields.block.custom.BushLeavesBlock;
import net.minecraft.block.*;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.ColorCode;
import net.minecraft.util.Identifier;

public class ModBlocks
{
    public static final Block FEATHER_GRASS = registerBlock("feather_grass", new ShortPlantBlock(AbstractBlock.Settings.copy(Blocks.SHORT_GRASS)));

    public static final Block SALTMARSH_BLOCK = registerBlock("saltmarsh_block",
            new ColoredFallingBlock(new ColorCode(13684792), AbstractBlock.Settings.create()
                    .mapColor(MapColor.TERRACOTTA_LIGHT_GRAY)
                    .instrument(NoteBlockInstrument.BANJO)
                    .strength(0.6F, 0.5F)
                    .sounds(BlockSoundGroup.GRAVEL)
            ));

    public static final Block SALTMARSH_COBBLED = registerBlock("saltmarsh_cobbled",
            new Block(AbstractBlock.Settings.copy(SALTMARSH_BLOCK)));

    public static final Block SALTMARSH_POLISHED = registerBlock("saltmarsh_polished",
            new Block(AbstractBlock.Settings.copy(SALTMARSH_BLOCK)));

    public static final Block SALTMARSH_CHISELED = registerBlock("saltmarsh_chiseled",
            new Block(AbstractBlock.Settings.copy(SALTMARSH_BLOCK)));

    public static final Block SALTMARSH_BRICKS = registerBlock("saltmarsh_bricks",
            new Block(AbstractBlock.Settings.copy(SALTMARSH_BLOCK)));

    public static final Block SALTMARSH_BRICKS_SLAB = registerBlock("saltmarsh_bricks_slab",
            new SlabBlock(AbstractBlock.Settings.copy(SALTMARSH_BLOCK)));

    public static final Block SALTMARSH_BRICKS_STAIRS = registerBlock("saltmarsh_bricks_stairs",
            new StairsBlock(SALTMARSH_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(SALTMARSH_BLOCK)));

    public static final Block SALTMARSH_SLAB = registerBlock("saltmarsh_slab",
            new SlabBlock(AbstractBlock.Settings.copy(SALTMARSH_BLOCK)));

    public static final Block SALTMARSH_STAIRS = registerBlock("saltmarsh_stairs",
            new StairsBlock(SALTMARSH_BLOCK.getDefaultState(), AbstractBlock.Settings.copy(SALTMARSH_BLOCK)));

    public static final Block SALTMARSH_COBBLED_SLAB = registerBlock("saltmarsh_cobbled_slab",
            new SlabBlock(AbstractBlock.Settings.copy(SALTMARSH_BLOCK)));

    public static final Block SALTMARSH_COBBLED_STAIRS = registerBlock("saltmarsh_cobbled_stairs",
            new StairsBlock(SALTMARSH_COBBLED.getDefaultState(), AbstractBlock.Settings.copy(SALTMARSH_BLOCK)));

    public static final Block SALTMARSH_POLISHED_SLAB = registerBlock("saltmarsh_polished_slab",
            new SlabBlock(AbstractBlock.Settings.copy(SALTMARSH_BLOCK)));

    public static final Block SALTMARSH_POLISHED_STAIRS = registerBlock("saltmarsh_polished_stairs",
            new StairsBlock(SALTMARSH_POLISHED.getDefaultState(), AbstractBlock.Settings.copy(SALTMARSH_BLOCK)));

    public static final Block SALTMARSH_WALL = registerBlock("saltmarsh_wall",
            new WallBlock(AbstractBlock.Settings.copy(SALTMARSH_BLOCK)));

    public static final Block SALTMARSH_COBBLED_WALL = registerBlock("saltmarsh_cobbled_wall",
            new WallBlock(AbstractBlock.Settings.copy(SALTMARSH_BLOCK)));

    public static final Block SALTMARSH_BRICKS_WALL = registerBlock("saltmarsh_bricks_wall",
            new WallBlock(AbstractBlock.Settings.copy(SALTMARSH_BLOCK)));

    public static final Block DOLOMITE_BLOCK = registerBlock("dolomite_block",
            new Block(AbstractBlock.Settings.copy(Blocks.GRANITE)));

    public static final Block DOLOMITE_COBBLED = registerBlock("dolomite_cobbled",
            new Block(AbstractBlock.Settings.copy(Blocks.GRANITE)));

    public static final Block DOLOMITE_POLISHED = registerBlock("dolomite_polished",
            new Block(AbstractBlock.Settings.copy(Blocks.GRANITE)));

    public static final Block DOLOMITE_CHISELED = registerBlock("dolomite_chiseled",
            new Block(AbstractBlock.Settings.copy(Blocks.GRANITE)));

    public static final Block DOLOMITE_BRICKS = registerBlock("dolomite_bricks",
            new Block(AbstractBlock.Settings.copy(Blocks.GRANITE)));

    public static final Block DOLOMITE_BRICKS_SLAB = registerBlock("dolomite_bricks_slab",
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.GRANITE_SLAB)));

    public static final Block DOLOMITE_BRICKS_STAIRS = registerBlock("dolomite_bricks_stairs",
            new StairsBlock(DOLOMITE_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.GRANITE_STAIRS)));

    public static final Block DOLOMITE_SLAB = registerBlock("dolomite_slab",
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.GRANITE_SLAB)));

    public static final Block DOLOMITE_STAIRS = registerBlock("dolomite_stairs",
            new StairsBlock(DOLOMITE_BLOCK.getDefaultState(), AbstractBlock.Settings.copy(Blocks.GRANITE_STAIRS)));

    public static final Block DOLOMITE_COBBLED_SLAB = registerBlock("dolomite_cobbled_slab",
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.GRANITE_SLAB)));

    public static final Block DOLOMITE_COBBLED_STAIRS = registerBlock("dolomite_cobbled_stairs",
            new StairsBlock(DOLOMITE_COBBLED.getDefaultState(), AbstractBlock.Settings.copy(Blocks.GRANITE_STAIRS)));

    public static final Block DOLOMITE_POLISHED_SLAB = registerBlock("dolomite_polished_slab",
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.GRANITE_SLAB)));

    public static final Block DOLOMITE_POLISHED_STAIRS = registerBlock("dolomite_polished_stairs",
            new StairsBlock(DOLOMITE_POLISHED.getDefaultState(), AbstractBlock.Settings.copy(Blocks.GRANITE_STAIRS)));

    public static final Block DOLOMITE_WALL = registerBlock("dolomite_wall",
            new WallBlock(AbstractBlock.Settings.copy(Blocks.GRANITE)));

    public static final Block DOLOMITE_COBBLED_WALL = registerBlock("dolomite_cobbled_wall",
            new WallBlock(AbstractBlock.Settings.copy(Blocks.GRANITE)));

    public static final Block DOLOMITE_BRICKS_WALL = registerBlock("dolomite_bricks_wall",
            new WallBlock(AbstractBlock.Settings.copy(Blocks.GRANITE)));

    public static final Block TINY_GRASS = registerBlock("tiny_grass", new ShortPlantBlock(AbstractBlock.Settings.copy(Blocks.SHORT_GRASS)));

    public static final Block THYME = registerBlock("thyme",
            new FlowerBlock(StatusEffects.ABSORPTION, 0.35F, AbstractBlock.Settings.copy(Blocks.PEONY)));

    public static final Block POTTED_THYME = registerBlock("potted_thyme",
            new FlowerPotBlock(THYME, AbstractBlock.Settings.copy(Blocks.POTTED_DANDELION)));

    public static final Block SERVAL_HIDE = registerBlock("serval_hide",
            new AnimalHideBlock(AbstractBlock.Settings.copy(Blocks.YELLOW_WOOL)));

    private static Block registerBlock(String name, Block block)
    {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(WildFields.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block)
    {
        Registry.register(Registries.ITEM, Identifier.of(WildFields.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks()
    {

    }

    public static Block registerBushBlock(String name)
    {
        return registerBlock(name, new BerryBushBlock(name));
    }

    public static Block registerLeavesBlock(String name)
    {
        if (name.equals("tamarisk"))
            return registerBlock(name+"_leaves",
                    new BushLeavesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES).noCollision()));

        return registerBlock(name+"_leaves",
                new LeavesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES)));
    }

    public static Block registerLogsBlock(String name)
    {
        return registerBlock(name+"_log",
                new PillarBlock(AbstractBlock.Settings.copy(Blocks.OAK_LOG)));
    }

    public static Block registerWoodsBlock(String name)
    {
        return registerBlock(name+"_wood",
                new PillarBlock(AbstractBlock.Settings.copy(Blocks.OAK_WOOD)));
    }

    public static Block registerStrippedLogsBlock(String name)
    {
        return registerBlock("stripped_"+name+"_log",
                new PillarBlock(AbstractBlock.Settings.copy(Blocks.STRIPPED_OAK_LOG)));
    }

    public static Block registerStrippedWoodsBlock(String name)
    {
        return registerBlock("stripped_"+name+"_wood",
                new PillarBlock(AbstractBlock.Settings.copy(Blocks.STRIPPED_OAK_WOOD)));
    }

    public static Block registerPlanksBlock(String name)
    {
        return registerBlock(name+"_planks",
                new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
    }

    public static Block registerSlabBlock(String name)
    {
        return registerBlock(name+"_slab",
                new SlabBlock(AbstractBlock.Settings.copy(Blocks.OAK_SLAB)));
    }

    public static Block registerStairsBlock(String name, Block planks)
    {
        return registerBlock(name+"_stairs",
                new StairsBlock(planks.getDefaultState(), AbstractBlock.Settings.copy(Blocks.OAK_STAIRS)));
    }

    public static Block registerFenceBlock(String name)
    {
        return registerBlock(name+"_fence",
                new FenceBlock(AbstractBlock.Settings.copy(Blocks.OAK_FENCE)));
    }

    public static Block registerFenceGateBlock(String name)
    {
        return registerBlock(name+"_fence_gate",
                new FenceGateBlock(WoodType.OAK, AbstractBlock.Settings.copy(Blocks.OAK_FENCE_GATE)));
    }

    public static Block registerDoorBlock(String name)
    {
        return registerBlock(name+"_door",
                new DoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(Blocks.OAK_DOOR)));
    }

    public static Block registerTrapdoorBlock(String name)
    {
        return registerBlock(name+"_trapdoor",
                new TrapdoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(Blocks.OAK_TRAPDOOR)));
    }

    public static Block registerButtonBlock(String name)
    {
        return registerBlock(name+"_button",
                new ButtonBlock(BlockSetType.OAK, 30, AbstractBlock.Settings.copy(Blocks.OAK_BUTTON)));
    }

    public static Block registerPressurePlateBlock(String name)
    {
        return registerBlock(name+"_pressure_plate",
                new PressurePlateBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(Blocks.OAK_PRESSURE_PLATE)));
    }

    public static Block registerSaplingBlock(String name, SaplingGenerator saplingGenerator)
    {
        return registerBlock(name+"_sapling",
                new SaplingBlock(saplingGenerator, AbstractBlock.Settings.copy(Blocks.OAK_SAPLING)));
    }
}
