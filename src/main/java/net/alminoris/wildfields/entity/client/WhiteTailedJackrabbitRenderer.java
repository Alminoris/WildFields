package net.alminoris.wildfields.entity.client;

import net.alminoris.wildfields.WildFields;
import net.alminoris.wildfields.entity.custom.WhiteTailedJackrabbitEntity;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class WhiteTailedJackrabbitRenderer extends GeoEntityRenderer<WhiteTailedJackrabbitEntity>
{
    public WhiteTailedJackrabbitRenderer(EntityRendererFactory.Context renderManager)
    {
        super(renderManager, new WhiteTailedJackrabbitModel());
    }

    @Override
    public Identifier getTextureLocation(WhiteTailedJackrabbitEntity animatable)
    {
        return Identifier.of(WildFields.MOD_ID, "textures/entity/white_tailed_jackrabbit.png");
    }

    @Override
    public void preRender(MatrixStack poseStack, WhiteTailedJackrabbitEntity entity, BakedGeoModel model, VertexConsumerProvider bufferSource,
                          VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay,
                          float red, float green, float blue, float alpha)
    {
        if (entity.isBaby())
            poseStack.scale(0.4f, 0.4f, 0.4f);
        else { poseStack.scale(0.75F, 0.75F, 0.75F); }
        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
    }
}