package net.alminoris.wildfields.entity.client;

import net.alminoris.wildfields.WildFields;
import net.alminoris.wildfields.entity.custom.DarklingBeetleEntity;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class DarklingBeetleRenderer extends GeoEntityRenderer<DarklingBeetleEntity>
{
    public DarklingBeetleRenderer(EntityRendererFactory.Context renderManager)
    {
        super(renderManager, new DarklingBeetleModel());
    }

    @Override
    public Identifier getTextureLocation(DarklingBeetleEntity animatable)
    {
        return Identifier.of(WildFields.MOD_ID, "textures/entity/darkling_beetle.png");
    }

    @Override
    public void preRender(MatrixStack poseStack, DarklingBeetleEntity entity, BakedGeoModel model, @Nullable VertexConsumerProvider bufferSource, @Nullable VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, int colour)
    {
        if (entity.isBaby())
            poseStack.scale(0.4f, 0.4f, 0.4f);
        else { poseStack.scale(1.0F, 1.0F, 1.0F); }
        super.preRender(poseStack, entity, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, colour);
    }
}
