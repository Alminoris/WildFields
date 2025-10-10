package net.alminoris.wildfields.util.helper;

import net.alminoris.wildfields.entity.ModBoats;
import net.alminoris.wildfields.world.tree.ModSaplingGenerators;
import net.minecraft.block.Block;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.item.Item;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import static net.alminoris.wildfields.block.ModBlocks.*;
import static net.alminoris.wildfields.block.ModBlocks.registerStoneWall;
import static net.alminoris.wildfields.item.ModItems.*;

public class ModBlockSetsHelper
{
    public static final String[] WOOD_NAMES = new String[] { "olive", "tamarisk", "western_serviceberry", "trembling_aspen", "cottonwood" };

    public static final String[] STONE_NAMES = new String[] { "dolomite", "saltmarsh", "loessic_marl", "loamy_marl",
            "fossil_marlstone", "limestone" };

    public static final String[] STONE_TYPES = new String[] { "block", "cobbled", "polished", "bricks", "chiseled"};

    public static final String[] BUSHES_NAMES = new String[] { "western_snowberry" };

    public static final Dictionary<String, Block> BUSHES = new Hashtable<>();
    public static final Dictionary<String, Item> BERRIES = new Hashtable<>();

    public static final String[] CROP_NAMES = new String[] { "oat", "barley" };
    public static final String[] WILD_CROP_NAMES = new String[] { "wheat", "oat", "barley" };

    public static final Dictionary<String, Block> WILD_CROPS = new Hashtable<>();
    public static final Dictionary<String, Block> HAY_BLOCKS = new Hashtable<>();
    public static final Dictionary<String, Block> CROPS = new Hashtable<>();
    public static final Dictionary<String, Item> CROP_ITEMS = new Hashtable<>();
    public static final Dictionary<String, Item> CROP_SEEDS = new Hashtable<>();

    public static final Map<String, Map<String , Block>> STONE_BLOCKS = new HashMap<>();
    public static final Map<String, Map<String , Block>> STONE_SLABS = new HashMap<>();
    public static final Map<String, Map<String , Block>> STONE_STAIRS = new HashMap<>();
    public static final Map<String, Map<String , Block>> STONE_WALLS = new HashMap<>();

    static
    {
        for (String name : BUSHES_NAMES)
        {
            BUSHES.put(name, registerBushBlock(name));
            BERRIES.put(name, registerBerryItem(name, 2, 0.4f, BUSHES.get(name)));
        }

        for (String name : WILD_CROP_NAMES)
        {
            WILD_CROPS.put(name, registerWildCropBlock(name));
        }

        for (String name : CROP_NAMES)
        {
            CROP_ITEMS.put(name, registerCropItem(name));
            HAY_BLOCKS.put(name, registerHayBlock(name));
            CROPS.put(name, registerCropBlock(name));
            CROP_SEEDS.put(name, registerSeedsItem(name, CROPS.get(name)));
        }
    }

    public static final Dictionary<String, Block> LEAVES = new Hashtable<>()
    {{
        for(String name : WOOD_NAMES)
            put(name, registerLeavesBlock(name));
    }};

    public static final Dictionary<String, Block> LOGS = new Hashtable<>()
    {{
        for(String name : WOOD_NAMES)
            put(name, registerLogsBlock(name));
    }};

    public static final Dictionary<String, Block> WOODS = new Hashtable<>()
    {{
        for(String name : WOOD_NAMES)
            put(name, registerWoodsBlock(name));
    }};

    public static final Dictionary<String, Block> STRIPPED_LOGS = new Hashtable<>()
    {{
        for(String name : WOOD_NAMES)
            put(name, registerStrippedLogsBlock(name));
    }};

    public static final Dictionary<String, Block> STRIPPED_WOODS = new Hashtable<>()
    {{
        for(String name : WOOD_NAMES)
            put(name, registerStrippedWoodsBlock(name));
    }};

    public static final Dictionary<String, Block> WOODEN_PLANKS = new Hashtable<>()
    {{
        for(String name : WOOD_NAMES)
            put(name, registerPlanksBlock(name));
    }};

    public static final Dictionary<String, Block> WOODEN_SLABS = new Hashtable<>()
    {{
        for(String name : WOOD_NAMES)
            put(name, registerSlabBlock(name));
    }};

    public static final Dictionary<String, Block> WOODEN_STAIRS = new Hashtable<>()
    {{
        for(String name : WOOD_NAMES)
            put(name, registerStairsBlock(name, WOODEN_PLANKS.get(name)));
    }};

    public static final Dictionary<String, Block> WOODEN_FENCES = new Hashtable<>()
    {{
        for(String name : WOOD_NAMES)
            put(name, registerFenceBlock(name));
    }};

    public static final Dictionary<String, Block> WOODEN_FENCE_GATES = new Hashtable<>()
    {{
        for(String name : WOOD_NAMES)
            put(name, registerFenceGateBlock(name));
    }};

    public static final Dictionary<String, Block> WOODEN_DOORS = new Hashtable<>()
    {{
        for(String name : WOOD_NAMES)
            put(name, registerDoorBlock(name));
    }};

    public static final Dictionary<String, Block> WOODEN_TRAPDOORS = new Hashtable<>()
    {{
        for(String name : WOOD_NAMES)
            put(name, registerTrapdoorBlock(name));
    }};

    public static final Dictionary<String, Block> WOODEN_BUTTONS = new Hashtable<>()
    {{
        for(String name : WOOD_NAMES)
            put(name, registerButtonBlock(name));
    }};

    public static final Dictionary<String, Block> WOODEN_PRESSURE_PLATES = new Hashtable<>()
    {{
        for(String name : WOOD_NAMES)
            put(name, registerPressurePlateBlock(name));
    }};

    public static final Dictionary<String, Block> WOODEN_SIGNS = new Hashtable<>();
    public static final Dictionary<String, Block> WOODEN_WALL_SIGNS = new Hashtable<>();
    public static final Dictionary<String, Block> WOODEN_HANGING_SIGNS = new Hashtable<>();
    public static final Dictionary<String, Block> WOODEN_WALL_HANGING_SIGNS = new Hashtable<>();
    public static final Dictionary<String, Item> WOODEN_SIGN_ITEMS = new Hashtable<>();
    public static final Dictionary<String, Item> WOODEN_HANGING_SIGN_ITEMS = new Hashtable<>();

    static
    {
        for (String name : WOOD_NAMES)
        {
            WOODEN_SIGNS.put(name, registerSignBlock(name));
            WOODEN_WALL_SIGNS.put(name, registerWallSignBlock(name));
            WOODEN_HANGING_SIGNS.put(name, registerHangingSignBlock(name));
            WOODEN_WALL_HANGING_SIGNS.put(name, registerWallHangingSignBlock(name));
            WOODEN_SIGN_ITEMS.put(name, registerSignItem(name, WOODEN_SIGNS.get(name), WOODEN_WALL_SIGNS.get(name)));
            WOODEN_HANGING_SIGN_ITEMS.put(name, registerHangingSignItem(name, WOODEN_HANGING_SIGNS.get(name), WOODEN_WALL_HANGING_SIGNS.get(name)));
        }

        for (String name : STONE_NAMES)
        {
            Map<String, Block> typeMap1 = new HashMap<>();
            Map<String, Block> typeMap2 = new HashMap<>();
            Map<String, Block> typeMap3 = new HashMap<>();

            for (String type : STONE_TYPES)
            {
                typeMap1.put(type, registerStoneBlock(name, type));
                typeMap2.put(type, registerStoneSlab(name, type));
                typeMap3.put(type, registerStoneWall(name, type));
            }

            STONE_BLOCKS.put(name, typeMap1);
            STONE_SLABS.put(name, typeMap2);
            STONE_WALLS.put(name, typeMap3);
        }

        for (String name : STONE_NAMES)
        {
            Map<String, Block> typeMap1 = new HashMap<>();

            for (String type : STONE_TYPES)
            {
                typeMap1.put(type, registerStoneStairs(name, type, STONE_BLOCKS.get(name).get(type)));
            }

            STONE_STAIRS.put(name, typeMap1);
        }
    }

    public static final Dictionary<String, Block> WOODEN_SAPLINGS = new Hashtable<>()
    {{
        for(String name : WOOD_NAMES)
            put(name, registerSaplingBlock(name, ModSaplingGenerators.saplingGenerators.get(name)));
    }};

    public static final Dictionary<String, Item> WOODEN_BOATS = new Hashtable<>()
    {{
        for(String name : WOOD_NAMES)
            put(name, registerBoatItem(ModBoats.boatIDs.get(name), ModBoats.boatKeys.get(name)));
    }};

    public static final Dictionary<String, Item> WOODEN_CHEST_BOATS = new Hashtable<>()
    {{
        for(String name : WOOD_NAMES)
            put(name, registerChestBoatItem(ModBoats.chestBoatIDs.get(name), ModBoats.boatKeys.get(name)));
    }};

    public static final Dictionary<String, BlockFamily> WOODEN_BLOCK_FAMILIES = new Hashtable<>()
    {{
        for(String name : WOOD_NAMES)
            put(name, registerBlockFamily(WOODEN_PLANKS.get(name), WOODEN_SIGNS.get(name), WOODEN_WALL_SIGNS.get(name)));
    }};
}
