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

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.biome.Biome;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

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
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.BLUE_GRAMA_GRASS, 20, 90);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.PRAIRIE_SAGE, 20, 90);

		for (String name : CROP_NAMES)
		{
			FlammableBlockRegistry.getDefaultInstance().add(HAY_BLOCKS.get(name), 60, 20);
		}

		for (String name : WILD_CROP_NAMES)
		{
			FlammableBlockRegistry.getDefaultInstance().add(WILD_CROPS.get(name), 30, 40);
		}

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
		FabricDefaultAttributeRegistry.register(ModEntities.MOLE, MoleEntity.setAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.COYOTE, CoyoteEntity.setAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.FERRUGINOUS_HAWK, FerruginousHawkEntity.setAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.WHITE_TAILED_JACKRABBIT, WhiteTailedJackrabbitEntity.setAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.PALLID_WINGED_GRASSHOPPER, PallidWingedGrasshopperEntity.setAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.BLACK_BILLED_MAGPIE, BlackBilledMagpieEntity.setAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.WESTERN_MEADOWLARK, WesternMeadowlarkEntity.setAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.BISON, BisonEntity.setAttributes());


		Map<UUID, Vec3d> LAST_POS = new ConcurrentHashMap<>();

		ServerTickEvents.END_SERVER_TICK.register(server ->
		{
			for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList())
			{
				Vec3d currPos = player.getPos();
				Vec3d prevPos = LAST_POS.get(player.getUuid());

				if (prevPos == null) {
					LAST_POS.put(player.getUuid(), currPos);
					continue;
				}

				double dx = currPos.x - prevPos.x;
				double dy = currPos.y - prevPos.y;
				double dz = currPos.z - prevPos.z;

				double horizSq = dx * dx + dz * dz;

				boolean isMoving = horizSq > 1e-6;
				boolean isSprinting = player.isSprinting();
				boolean isFalling = !player.isOnGround() && dy < -0.08;

				ItemStack talismanStack = null;
				int talismanSlot = -1;
				for (int i = 0; i < player.getInventory().main.size(); i++) {
					ItemStack stack = player.getInventory().main.get(i);
					if (stack.isOf(ModItems.PRAIRIES_TALISMAN)) {
						talismanStack = stack;
						talismanSlot = i;
						break;
					}
				}

				if (talismanStack != null) {
					if (talismanStack.getDamage() >= talismanStack.getMaxDamage()) {

					} else {
						RegistryEntry<Biome> biome = player.getWorld().getBiome(player.getBlockPos());
						if (biome.matchesId(Identifier.of(WildFields.MOD_ID, "prairies_biome"))) {
							player.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 40, 0, true, false, false));
							player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 40, 0, true, false, false));

							int damage = 0;
							if (isFalling) {
								damage = 10;
							} else if (isSprinting) {
								damage = 5;
							} else if (isMoving) {
								damage = 2;
							}

							if (damage > 0 && player.getWorld().getTime() % 20 == 0) {
								talismanStack.damage(damage, player, p -> p.sendToolBreakStatus(Hand.MAIN_HAND));

								if (talismanStack.getDamage() >= talismanStack.getMaxDamage()) {
									player.getInventory().removeStack(talismanSlot);
								}
							}
						}
					}
				}

				LAST_POS.put(player.getUuid(), currPos);
			}
		});

	}
}