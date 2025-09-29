package net.alminoris.wildfields.entity.client;

import net.alminoris.wildfields.WildFields;
import net.alminoris.wildfields.entity.custom.WesternMeadowlarkEntity;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class WesternMeadowlarkRenderer extends GeoEntityRenderer<WesternMeadowlarkEntity>
{
    public WesternMeadowlarkRenderer(EntityRendererFactory.Context renderManager)
    {
        super(renderManager, new WesternMeadowlarkModel());
    }

    @Override
    public Identifier getTextureLocation(WesternMeadowlarkEntity animatable)
    {
        return Identifier.of(WildFields.MOD_ID, "textures/entity/western_meadowlark.png");
    }

    @Override
    public void preRender(MatrixStack poseStack, WesternMeadowlarkEntity entity, BakedGeoModel model, @Nullable VertexConsumerProvider bufferSource, @Nullable VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, int colour)
    {
        if (entity.isBaby())
            poseStack.scale(0.45f, 0.45f, 0.45f);
        else { poseStack.scale(0.8F, 0.8F, 0.8F); }
        super.preRender(poseStack, entity, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, colour);
    }
}
