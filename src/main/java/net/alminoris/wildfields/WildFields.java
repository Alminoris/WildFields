package net.alminoris.wildfields;

import net.alminoris.wildfields.block.ModBlocks;
import net.alminoris.wildfields.entity.ModBoats;
import net.alminoris.wildfields.entity.ModEntities;
import net.alminoris.wildfields.entity.custom.*;
import net.alminoris.wildfields.item.ModItemGroups;
import net.alminoris.wildfields.item.ModItems;
import net.alminoris.wildfields.particle.ModParticles;
import net.alminoris.wildfields.sound.ModSounds;
import net.alminoris.wildfields.world.gen.ModWorldGeneration;
import net.alminoris.wildfields.world.gen.decorator.ModTreeDecorators;
import net.alminoris.wildfields.world.gen.feature.ModFeatures;
import net.alminoris.wildfields.world.tree.ModFoliagePlacerTypes;
import net.alminoris.wildfields.world.tree.ModTrunkPlacerTypes;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.color.block.BlockColorProvider;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockRenderView;
import net.minecraft.world.biome.FoliageColors;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

import static net.alminoris.wildfields.util.helper.ModBlockSetsHelper.*;

public class WildFields implements ModInitializer
{
	public static final String MOD_ID = "wildfields";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize()
	{
		LOGGER.info("Wild Fields Mod Initialization");

		ModItems.registerModItems();

		ModBlocks.registerModBlocks();

		ModSounds.registerSounds();

		ModParticles.registerParticles();

		for (String name : WOOD_NAMES)
		{
			StrippableBlockRegistry.register(LOGS.get(name), STRIPPED_LOGS.get(name));
			StrippableBlockRegistry.register(WOODS.get(name), STRIPPED_WOODS.get(name));

			FlammableBlockRegistry.getDefaultInstance().add(LOGS.get(name), 5, 5);
			FlammableBlockRegistry.getDefaultInstance().add(STRIPPED_LOGS.get(name), 5, 5);
			FlammableBlockRegistry.getDefaultInstance().add(WOODS.get(name), 5, 5);
			FlammableBlockRegistry.getDefaultInstance().add(STRIPPED_WOODS.get(name), 5, 5);

			FlammableBlockRegistry.getDefaultInstance().add(WOODEN_PLANKS.get(name), 5, 20);
			FlammableBlockRegistry.getDefaultInstance().add(LEAVES.get(name), 30, 60);
		}

		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.TINY_GRASS, 20, 90);

		ModBoats.registerBoats();

		ModItemGroups.registerItemGroups();

		ModFeatures.registerFeatures();

		ModTrunkPlacerTypes.register();
		ModFoliagePlacerTypes.register();
		ModTreeDecorators.register();

		ModWorldGeneration.generateModWorldGen();

		FabricDefaultAttributeRegistry.register(ModEntities.MARMOT, MarmotEntity.setAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.STEPPE_EAGLE, SteppeEagleEntity.setAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.DARKLING_BEETLE, DarklingBeetleEntity.setAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.STEPPE_VIPER, SteppeViperEntity.setAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.SAIGA, SaigaEntity.setAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.SERVAL, ServalEntity.setAttributes());
	}
}