package net.alminoris.wildfields.item;

import net.alminoris.wildfields.WildFields;
import net.alminoris.wildfields.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static net.alminoris.wildfields.util.helper.ModBlockSetsHelper.*;

public class ModItemGroups
{
    public static final ItemGroup WILDFIELDS_TAB = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(WildFields.MOD_ID, "wildfieldstab"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.wildfieldstab"))
                    .icon(() -> new ItemStack(ModBlocks.FEATHER_GRASS)).entries((displayContext, entries) ->
                    {
                        entries.add(ModBlocks.FEATHER_GRASS);
                        entries.add(ModBlocks.TINY_GRASS);
                        entries.add(ModBlocks.THYME);
                        entries.add(ModBlocks.SPIDER_MILKWEED);
                        entries.add(ModBlocks.WORMWOOD);
                        entries.add(ModBlocks.PRAIRIE_ROSE);
                        entries.add(ModBlocks.SMOOTH_ASTER);
                        entries.add(ModBlocks.BLUE_GRAMA_GRASS);
                        entries.add(ModBlocks.PRAIRIE_SAGE);
                        entries.add(ModBlocks.VIOLA);
                        for (String name : WILD_CROP_NAMES)
                        {
                            entries.add(WILD_CROPS.get(name));
                        }
                        for (String name : CROP_NAMES)
                        {
                            entries.add(CROP_SEEDS.get(name));
                            entries.add(HAY_BLOCKS.get(name));
                            entries.add(CROP_ITEMS.get(name));
                        }
                        entries.add(ModBlocks.GREEN_LICHEN);
                        entries.add(ModBlocks.COTTONWOOD_FLUFF);
                        for (String name : STONE_NAMES)
                        {
                            for (String type : STONE_TYPES)
                            {
                                entries.add(STONE_BLOCKS.get(name).get(type));
                                entries.add(STONE_SLABS.get(name).get(type));
                                entries.add(STONE_STAIRS.get(name).get(type));
                                entries.add(STONE_WALLS.get(name).get(type));
                            }
                        }

                        for (String name : WOOD_NAMES)
                        {
                            entries.add(WOODEN_SAPLINGS.get(name));
                        }
                        for (String name : WOOD_NAMES)
                        {
                            entries.add(LEAVES.get(name));
                        }
                        for (String name : WOOD_NAMES)
                        {
                            entries.add(LOGS.get(name));
                            entries.add(WOODS.get(name));
                            entries.add(STRIPPED_LOGS.get(name));
                            entries.add(STRIPPED_WOODS.get(name));
                            entries.add(WOODEN_PLANKS.get(name));
                            entries.add(WOODEN_SLABS.get(name));
                            entries.add(WOODEN_STAIRS.get(name));
                            entries.add(WOODEN_FENCES.get(name));
                            entries.add(WOODEN_FENCE_GATES.get(name));
                            entries.add(WOODEN_DOORS.get(name));
                            entries.add(WOODEN_TRAPDOORS.get(name));
                            entries.add(WOODEN_BUTTONS.get(name));
                            entries.add(WOODEN_PRESSURE_PLATES.get(name));
                            entries.add(WOODEN_SIGN_ITEMS.get(name));
                            entries.add(WOODEN_HANGING_SIGN_ITEMS.get(name));
                            entries.add(WOODEN_BOATS.get(name));
                            entries.add(WOODEN_CHEST_BOATS.get(name));
                        }
                        entries.add(ModItems.OLIVES);
                        for (String name : BUSHES_NAMES)
                        {
                            entries.add(BERRIES.get(name));
                        }
                        entries.add(ModItems.PRICKLY_PEAR);
                        entries.add(ModItems.FURRED_LEATHER_HELMET);
                        entries.add(ModItems.FURRED_LEATHER_CHESTPLATE);
                        entries.add(ModItems.FURRED_LEATHER_LEGGINGS);
                        entries.add(ModItems.FURRED_LEATHER_BOOTS);
                        entries.add(ModItems.DARKLING_BEETLE_CHESTPLATE);
                        entries.add(ModBlocks.SERVAL_HIDE);
                        entries.add(ModItems.STEPPE_ARROW);
                        entries.add(ModItems.SAIGA_SICKLE);
                        entries.add(ModItems.STEPPE_VIPER_DAGGER);
                        entries.add(ModItems.MARMOT_FUR);
                        entries.add(ModItems.DARKLING_BEETLE_SHELL);
                        entries.add(ModItems.STEPPE_EAGLE_BEAK);
                        entries.add(ModItems.STEPPE_EAGLE_FEATHER);
                        entries.add(ModItems.FERRUGINOUS_HAWK_FEATHER);
                        entries.add(ModItems.BLACK_BILLED_MAGPIE_FEATHER);
                        entries.add(ModItems.WESTERN_MEADOWLARK_FEATHER);
                        entries.add(ModItems.SAIGA_HORN);
                        entries.add(ModItems.BISON_HORN);
                        entries.add(ModItems.EARTHWORM);
                        entries.add(ModItems.PRAIRIES_TALISMAN);
                        entries.add(ModItems.STEPPE_VIPER_FANG);
                        entries.add(ModItems.SAIGA);
                        entries.add(ModItems.COOKED_SAIGA);
                        entries.add(ModItems.BISON);
                        entries.add(ModItems.COOKED_BISON);
                        entries.add(ModItems.JACKRABBIT);
                        entries.add(ModItems.COOKED_JACKRABBIT);
                        entries.add(ModItems.PALLID_WINGED_GRASSHOPPER_LEG);
                        entries.add(ModItems.COOKED_PALLID_WINGED_GRASSHOPPER_LEG);
                        entries.add(ModItems.BARLEY_BREAD);
                        entries.add(ModItems.BARLEY_STEW);
                        entries.add(ModItems.OAT_COOKIE);
                        entries.add(ModItems.OATMEAL);
                        entries.add(ModItems.MARMOT_SPAWN_EGG);
                        entries.add(ModItems.DARKLING_BEETLE_SPAWN_EGG);
                        entries.add(ModItems.SERVAL_SPAWN_EGG);
                        entries.add(ModItems.STEPPE_EAGLE_SPAWN_EGG);
                        entries.add(ModItems.SAIGA_SPAWN_EGG);
                        entries.add(ModItems.STEPPE_VIPER_SPAWN_EGG);
                        entries.add(ModItems.MOLE_SPAWN_EGG);
                        entries.add(ModItems.COYOTE_SPAWN_EGG);
                        entries.add(ModItems.BISON_SPAWN_EGG);
                        entries.add(ModItems.FERRUGINOUS_HAWK_SPAWN_EGG);
                        entries.add(ModItems.WHITE_TAILED_JACKRABBIT_SPAWN_EGG);
                        entries.add(ModItems.PALLID_WINGED_GRASSHOPPER_SPAWN_EGG);
                        entries.add(ModItems.BLACK_BILLED_MAGPIE_SPAWN_EGG);
                        entries.add(ModItems.WESTERN_MEADOWLARK_SPAWN_EGG);
                    }).build());

    public static void registerItemGroups()
    {

    }
}