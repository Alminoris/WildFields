package net.alminoris.wildfields.block;

import com.terraformersmc.terraform.sign.api.block.TerraformHangingSignBlock;
import com.terraformersmc.terraform.sign.api.block.TerraformSignBlock;
import com.terraformersmc.terraform.sign.api.block.TerraformWallHangingSignBlock;
import com.terraformersmc.terraform.sign.api.block.TerraformWallSignBlock;
import net.alminoris.wildfields.WildFields;
import net.alminoris.wildfields.block.custom.*;
import net.alminoris.wildfields.particle.ModParticles;
import net.minecraft.block.*;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import static net.alminoris.wildfields.util.helper.ModBlockSetsHelper.STONE_BLOCKS;

public class ModBlocks
{
    public static final Block FEATHER_GRASS = registerBlock("feather_grass", new ShortPlantBlock(AbstractBlock.Settings.copy(Blocks.SHORT_GRASS)));

    public static final Block GREEN_LICHEN = registerBlock("green_lichen", new VineBlock(AbstractBlock.Settings.copy(Blocks.VINE)));

    public static final Block TINY_GRASS = registerBlock("tiny_grass", new ShortPlantBlock(AbstractBlock.Settings.copy(Blocks.SHORT_GRASS)));

    public static final Block THYME = registerBlock("thyme",
            new FlowerBlock(StatusEffects.ABSORPTION, 0.35F, AbstractBlock.Settings.copy(Blocks.PEONY)));

    public static final Block POTTED_THYME = registerBlock("potted_thyme",
            new FlowerPotBlock(THYME, AbstractBlock.Settings.copy(Blocks.POTTED_DANDELION)));

    public static final Block SPIDER_MILKWEED = registerBlock("spider_milkweed",
            new FlowerBlock(StatusEffects.HEALTH_BOOST, 0.35F, AbstractBlock.Settings.copy(Blocks.PEONY)));

    public static final Block POTTED_SPIDER_MILKWEED = registerBlock("potted_spider_milkweed",
            new FlowerPotBlock(SPIDER_MILKWEED, AbstractBlock.Settings.copy(Blocks.POTTED_DANDELION)));

    public static final Block WORMWOOD = registerBlock("wormwood",
            new FlowerBlock(StatusEffects.FIRE_RESISTANCE, 0.35F, AbstractBlock.Settings.copy(Blocks.PEONY)));

    public static final Block POTTED_WORMWOOD = registerBlock("potted_wormwood",
            new FlowerPotBlock(WORMWOOD, AbstractBlock.Settings.copy(Blocks.POTTED_DANDELION)));

    public static final Block PRAIRIE_ROSE = registerBlock("prairie_rose",
            new FlowerBlock(StatusEffects.REGENERATION, 0.35F, AbstractBlock.Settings.copy(Blocks.PEONY)));

    public static final Block POTTED_PRAIRIE_ROSE = registerBlock("potted_prairie_rose",
            new FlowerPotBlock(PRAIRIE_ROSE, AbstractBlock.Settings.copy(Blocks.POTTED_DANDELION)));

    public static final Block SMOOTH_ASTER = registerBlock("smooth_aster",
            new FlowerBlock(StatusEffects.REGENERATION, 0.35F, AbstractBlock.Settings.copy(Blocks.PEONY)));

    public static final Block POTTED_SMOOTH_ASTER = registerBlock("potted_smooth_aster",
            new FlowerPotBlock(SMOOTH_ASTER, AbstractBlock.Settings.copy(Blocks.POTTED_DANDELION)));

    public static final Block BLUE_GRAMA_GRASS = registerBlock("blue_grama_grass",
            new TallPlantBlock(AbstractBlock.Settings.copy(Blocks.TALL_GRASS)));

    public static final Block PRAIRIE_SAGE = registerBlock("prairie_sage",
            new TripleTallPlantBlock(AbstractBlock.Settings.copy(Blocks.TALL_GRASS)));

    public static final Block SERVAL_HIDE = registerBlock("serval_hide",
            new AnimalHideBlock(AbstractBlock.Settings.copy(Blocks.YELLOW_WOOL)));

    public static final Block VIOLA = registerBlock("viola",
            new FlowerbedBlock(AbstractBlock.Settings.copy(Blocks.PINK_PETALS)));

    public static final Block PRICKLY_PEAR_CACTUS = registerBlock("prickly_pear_cactus", new PricklyPearCactusBlock());

    public static final Block COTTONWOOD_FLUFF = registerBlock("cottonwood_fluff", new CottonwoodFluffBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES).noCollision()));

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

    public static Block registerHayBlock(String name)
    {
        return registerBlock(name+"_hay_block", new HayBlock(AbstractBlock.Settings.create()
                .mapColor(MapColor.YELLOW).instrument(NoteBlockInstrument.BANJO).strength(0.5F).sounds(BlockSoundGroup.GRASS)));
    }

    public static Block registerWildCropBlock(String name)
    {
        return registerBlock("wild_"+name,
                new FlowerBlock(StatusEffects.LUCK, 0.35F, AbstractBlock.Settings.copy(Blocks.SHORT_GRASS)));
    }

    public static Block registerCropBlock(String name)
    {
        return registerBlock(name, new CustomCropBlock(name));
    }

    public static Block registerStoneBlock(String name, String type)
    {
        return registerBlock(name+"_"+type,
                new Block(AbstractBlock.Settings.copy(Blocks.GRANITE)));
    }

    public static Block registerStoneSlab(String name, String type)
    {
        return registerBlock(name+"_"+(type.equals("block") ? "" : "_"+type)+"_slab",
                new SlabBlock(AbstractBlock.Settings.copy(Blocks.GRANITE)));
    }

    public static Block registerStoneStairs(String name, String type, Block block)
    {
        return registerBlock(name+"_"+(type.equals("block") ? "" : "_"+type)+"_stairs",
                new StairsBlock(block.getDefaultState(), AbstractBlock.Settings.copy(Blocks.GRANITE)));
    }

    public static Block registerStoneWall(String name, String type)
    {
        return registerBlock(name+"_"+(type.equals("block") ? "" : "_"+type)+"_wall",
                new WallBlock(AbstractBlock.Settings.copy(Blocks.GRANITE)));
    }

    public static Block registerLeavesBlock(String name)
    {
        if (name.equals("tamarisk"))
            return registerBlock(name+"_leaves",
                    new BushLeavesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES).noCollision()));

        if (name.equals("olive"))
            return registerBlock(name+"_leaves",
                    new FallingLeavesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES), ModParticles.OLIVE_LEAVES, 30));

        if (name.equals("trembling_aspen"))
            return registerBlock(name+"_leaves",
                    new FallingLeavesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES), ModParticles.TREMBLING_ASPEN_LEAVES, 50));

        if (name.equals("cottonwood"))
            return registerBlock(name+"_leaves",
                    new FallingLeavesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES), ModParticles.COTTONWOOD_LEAVES, 70));

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

    public static Block registerSignBlock(String name)
    {
        return registerBlock(name+"_sign",
                new TerraformSignBlock(Identifier.of(WildFields.MOD_ID, "entity/signs/"+name), AbstractBlock.Settings.copy(Blocks.OAK_SIGN)));
    }

    public static Block registerWallSignBlock(String name)
    {
        return registerBlock(name+"_wall_sign",
                new TerraformWallSignBlock(Identifier.of(WildFields.MOD_ID, "entity/signs/"+name), AbstractBlock.Settings.copy(Blocks.OAK_WALL_SIGN)));
    }

    public static Block registerHangingSignBlock(String name)
    {
        return registerBlock(name+"_hanging_sign",
                new TerraformHangingSignBlock(Identifier.of(WildFields.MOD_ID, "entity/signs/hanging/"+name),
                        Identifier.of(WildFields.MOD_ID, "textures/gui/hanging_signs/"+name), AbstractBlock.Settings.copy(Blocks.OAK_HANGING_SIGN)));
    }

    public static Block registerWallHangingSignBlock(String name)
    {
        return registerBlock(name+"_wall_hanging_sign",
                new TerraformWallHangingSignBlock(Identifier.of(WildFields.MOD_ID, "entity/signs/hanging/"+name),
                        Identifier.of(WildFields.MOD_ID, "textures/gui/hanging_signs/"+name), AbstractBlock.Settings.copy(Blocks.OAK_WALL_HANGING_SIGN)));
    }

    public static BlockFamily registerBlockFamily(Block planks, Block sign, Block wallSign)
    {
        return new BlockFamily.Builder(planks)
                .sign(sign, wallSign)
                .group("wooden")
                .unlockCriterionName("has_planks").build();
    }
}
