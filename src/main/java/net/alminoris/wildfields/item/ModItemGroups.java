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
                        entries.add(ModBlocks.SALTMARSH_BLOCK);
                        entries.add(ModBlocks.SALTMARSH_STAIRS);
                        entries.add(ModBlocks.SALTMARSH_SLAB);
                        entries.add(ModBlocks.SALTMARSH_WALL);
                        entries.add(ModBlocks.SALTMARSH_COBBLED);
                        entries.add(ModBlocks.SALTMARSH_COBBLED_STAIRS);
                        entries.add(ModBlocks.SALTMARSH_COBBLED_SLAB);
                        entries.add(ModBlocks.SALTMARSH_COBBLED_WALL);
                        entries.add(ModBlocks.SALTMARSH_POLISHED);
                        entries.add(ModBlocks.SALTMARSH_POLISHED_STAIRS);
                        entries.add(ModBlocks.SALTMARSH_POLISHED_SLAB);
                        entries.add(ModBlocks.SALTMARSH_CHISELED);
                        entries.add(ModBlocks.SALTMARSH_BRICKS);
                        entries.add(ModBlocks.SALTMARSH_BRICKS_WALL);
                        entries.add(ModBlocks.SALTMARSH_BRICKS_STAIRS);
                        entries.add(ModBlocks.SALTMARSH_BRICKS_SLAB);
                        entries.add(ModBlocks.DOLOMITE_BLOCK);
                        entries.add(ModBlocks.DOLOMITE_STAIRS);
                        entries.add(ModBlocks.DOLOMITE_SLAB);
                        entries.add(ModBlocks.DOLOMITE_WALL);
                        entries.add(ModBlocks.DOLOMITE_COBBLED);
                        entries.add(ModBlocks.DOLOMITE_COBBLED_STAIRS);
                        entries.add(ModBlocks.DOLOMITE_COBBLED_SLAB);
                        entries.add(ModBlocks.DOLOMITE_COBBLED_WALL);
                        entries.add(ModBlocks.DOLOMITE_POLISHED);
                        entries.add(ModBlocks.DOLOMITE_POLISHED_STAIRS);
                        entries.add(ModBlocks.DOLOMITE_POLISHED_SLAB);
                        entries.add(ModBlocks.DOLOMITE_CHISELED);
                        entries.add(ModBlocks.DOLOMITE_BRICKS);
                        entries.add(ModBlocks.DOLOMITE_BRICKS_WALL);
                        entries.add(ModBlocks.DOLOMITE_BRICKS_STAIRS);
                        entries.add(ModBlocks.DOLOMITE_BRICKS_SLAB);
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
                        entries.add(ModItems.SAIGA_HORN);
                        entries.add(ModItems.STEPPE_VIPER_FANG);
                        entries.add(ModItems.SAIGA);
                        entries.add(ModItems.COOKED_SAIGA);
                        entries.add(ModItems.MARMOT_SPAWN_EGG);
                        entries.add(ModItems.DARKLING_BEETLE_SPAWN_EGG);
                        entries.add(ModItems.SERVAL_SPAWN_EGG);
                        entries.add(ModItems.STEPPE_EAGLE_SPAWN_EGG);
                        entries.add(ModItems.SAIGA_SPAWN_EGG);
                        entries.add(ModItems.STEPPE_VIPER_SPAWN_EGG);
                    }).build());

    public static void registerItemGroups()
    {

    }
}