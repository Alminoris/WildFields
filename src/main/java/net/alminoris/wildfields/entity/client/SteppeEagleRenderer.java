package net.alminoris.wildfields.entity.client;

import net.alminoris.wildfields.WildFields;
import net.alminoris.wildfields.entity.custom.SteppeEagleEntity;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SteppeEagleRenderer extends GeoEntityRenderer<SteppeEagleEntity>
{
    public SteppeEagleRenderer(EntityRendererFactory.Context renderManager)
    {
        super(renderManager, new SteppeEagleModel());
    }

    @Override
    public Identifier getTextureLocation(SteppeEagleEntity animatable)
    {
        return Identifier.of(WildFields.MOD_ID, "textures/entity/steppe_eagle.png");
    }

    @Override
    public void preRender(MatrixStack poseStack, SteppeEagleEntity entity, BakedGeoModel model, VertexConsumerProvider bufferSource,
                          VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay,
                          float red, float green, float blue, float alpha)
    {
        if (entity.isBaby())
            poseStack.scale(0.6f, 0.6f, 0.6f);
        else { poseStack.scale(1.0F, 1.0F, 1.0F); }
        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
