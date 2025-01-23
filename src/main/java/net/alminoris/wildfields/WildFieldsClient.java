package net.alminoris.wildfields;

import com.terraformersmc.terraform.boat.api.client.TerraformBoatClientHelper;
import net.alminoris.wildfields.block.ModBlocks;
import net.alminoris.wildfields.entity.ModBoats;
import net.alminoris.wildfields.entity.ModEntities;
import net.alminoris.wildfields.entity.client.*;
import net.alminoris.wildfields.entity.client.projectile.SteppeArrowRenderer;
import net.alminoris.wildfields.item.ModItems;
import net.alminoris.wildfields.particle.LeavesParticle;
import net.alminoris.wildfields.particle.ModParticles;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.component.type.DyedColorComponent;
import net.minecraft.world.biome.GrassColors;

import static net.alminoris.wildfields.util.helper.ModBlockSetsHelper.*;

public class WildFieldsClient implements ClientModInitializer
{
    @Override
    public void onInitializeClient()
    {
        for (String name : WOOD_NAMES)
        {
            BlockRenderLayerMap.INSTANCE.putBlock(LEAVES.get(name), RenderLayer.getCutout());
            BlockRenderLayerMap.INSTANCE.putBlock(WOODEN_SAPLINGS.get(name), RenderLayer.getCutout());
            BlockRenderLayerMap.INSTANCE.putBlock(WOODEN_DOORS.get(name), RenderLayer.getCutout());
            BlockRenderLayerMap.INSTANCE.putBlock(WOODEN_TRAPDOORS.get(name), RenderLayer.getCutout());
            TerraformBoatClientHelper.registerModelLayers(ModBoats.boatIDs.get(name), false);
        }

        for (String name : BUSHES_NAMES)
        {
            BlockRenderLayerMap.INSTANCE.putBlock(BUSHES.get(name), RenderLayer.getCutout());
        }

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FEATHER_GRASS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.THYME, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.TINY_GRASS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SERVAL_HIDE, RenderLayer.getCutout());

        ColorProviderRegistry.BLOCK.register(
                (state, world, pos, tintIndex) -> world != null && pos != null
                        ? BiomeColors.getGrassColor(world, pos)
                        : GrassColors.getColor(0.5D, 1.0D),
                ModBlocks.TINY_GRASS
        );

        ColorProviderRegistry.ITEM.register(
                (stack, tintIndex) -> GrassColors.getColor(0.5D, 1.0D),
                ModBlocks.TINY_GRASS.asItem()
        );

        ColorProviderRegistry.ITEM.register(
                (stack, tintIndex) -> tintIndex > 0 ? -1 : DyedColorComponent.getColor(stack, -6265536),
                ModItems.FURRED_LEATHER_HELMET,
                ModItems.FURRED_LEATHER_CHESTPLATE,
                ModItems.FURRED_LEATHER_LEGGINGS,
                ModItems.FURRED_LEATHER_BOOTS
        );

        ParticleFactoryRegistry.getInstance().register(ModParticles.OLIVE_LEAVES,
                spriteProvider -> (parameters, world, x, y, z, velocityX, velocityY, velocityZ) ->
                        new LeavesParticle(world, x, y, z, spriteProvider, 12));

        EntityRendererRegistry.register(ModEntities.MARMOT, MarmotRenderer::new);
        EntityRendererRegistry.register(ModEntities.STEPPE_VIPER, SteppeViperRenderer::new);
        EntityRendererRegistry.register(ModEntities.DARKLING_BEETLE, DarklingBeetleRenderer::new);
        EntityRendererRegistry.register(ModEntities.STEPPE_EAGLE, SteppeEagleRenderer::new);
        EntityRendererRegistry.register(ModEntities.SAIGA, SaigaRenderer::new);
        EntityRendererRegistry.register(ModEntities.SERVAL, ServalRenderer::new);

        EntityRendererRegistry.register(ModEntities.STEPPE_ARROW, SteppeArrowRenderer::new);
    }
}