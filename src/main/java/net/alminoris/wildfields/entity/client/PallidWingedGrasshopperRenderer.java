package net.alminoris.wildfields.entity.client;

import net.alminoris.wildfields.WildFields;
import net.alminoris.wildfields.entity.custom.PallidWingedGrasshopperEntity;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class PallidWingedGrasshopperRenderer extends GeoEntityRenderer<PallidWingedGrasshopperEntity>
{
    public PallidWingedGrasshopperRenderer(EntityRendererFactory.Context renderManager)
    {
        super(renderManager, new PallidWingedGrasshopperModel());
    }

    @Override
    public Identifier getTextureLocation(PallidWingedGrasshopperEntity animatable)
    {
        return Identifier.of(WildFields.MOD_ID, "textures/entity/pallid_winged_grasshopper.png");
    }

    @Override
    public void preRender(MatrixStack poseStack, PallidWingedGrasshopperEntity entity, BakedGeoModel model, VertexConsumerProvider bufferSource,
                          VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay,
                          float red, float green, float blue, float alpha)
    {
        if (entity.isBaby())
            poseStack.scale(0.35f, 0.35f, 0.35f);
        else { poseStack.scale(0.6F, 0.6F, 0.6F); }
        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
    }
}